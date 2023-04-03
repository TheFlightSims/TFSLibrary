/*     */ package com.typesafe.config.impl;
/*     */ 
/*     */ import com.typesafe.config.ConfigException;
/*     */ import com.typesafe.config.ConfigIncludeContext;
/*     */ import com.typesafe.config.ConfigObject;
/*     */ import com.typesafe.config.ConfigOrigin;
/*     */ import com.typesafe.config.ConfigParseOptions;
/*     */ import com.typesafe.config.ConfigParseable;
/*     */ import com.typesafe.config.ConfigSyntax;
/*     */ import com.typesafe.config.ConfigValue;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FilterReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.Reader;
/*     */ import java.io.StringReader;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.Properties;
/*     */ 
/*     */ public abstract class Parseable implements ConfigParseable {
/*     */   private ConfigIncludeContext includeContext;
/*     */   
/*     */   private ConfigParseOptions initialOptions;
/*     */   
/*     */   private ConfigOrigin initialOrigin;
/*     */   
/*  48 */   private static final ThreadLocal<LinkedList<Parseable>> parseStack = new ThreadLocal<LinkedList<Parseable>>() {
/*     */       protected LinkedList<Parseable> initialValue() {
/*  51 */         return new LinkedList<Parseable>();
/*     */       }
/*     */     };
/*     */   
/*     */   private static final int MAX_INCLUDE_DEPTH = 50;
/*     */   
/*     */   private ConfigParseOptions fixupOptions(ConfigParseOptions baseOptions) {
/*  61 */     ConfigSyntax syntax = baseOptions.getSyntax();
/*  62 */     if (syntax == null)
/*  63 */       syntax = guessSyntax(); 
/*  65 */     if (syntax == null)
/*  66 */       syntax = ConfigSyntax.CONF; 
/*  68 */     ConfigParseOptions modified = baseOptions.setSyntax(syntax);
/*  71 */     modified = modified.appendIncluder(ConfigImpl.defaultIncluder());
/*  73 */     modified = modified.setIncluder(SimpleIncluder.makeFull(modified.getIncluder()));
/*  75 */     return modified;
/*     */   }
/*     */   
/*     */   protected void postConstruct(ConfigParseOptions baseOptions) {
/*  79 */     this.initialOptions = fixupOptions(baseOptions);
/*  81 */     this.includeContext = new SimpleIncludeContext(this);
/*  83 */     if (this.initialOptions.getOriginDescription() != null) {
/*  84 */       this.initialOrigin = SimpleConfigOrigin.newSimple(this.initialOptions.getOriginDescription());
/*     */     } else {
/*  86 */       this.initialOrigin = createOrigin();
/*     */     } 
/*     */   }
/*     */   
/*     */   protected static void trace(String message) {
/*  99 */     if (ConfigImpl.traceLoadsEnabled())
/* 100 */       ConfigImpl.trace(message); 
/*     */   }
/*     */   
/*     */   ConfigSyntax guessSyntax() {
/* 105 */     return null;
/*     */   }
/*     */   
/*     */   ConfigSyntax contentType() {
/* 109 */     return null;
/*     */   }
/*     */   
/*     */   ConfigParseable relativeTo(String filename) {
/* 117 */     String resource = filename;
/* 118 */     if (filename.startsWith("/"))
/* 119 */       resource = filename.substring(1); 
/* 120 */     return newResources(resource, options().setOriginDescription(null));
/*     */   }
/*     */   
/*     */   ConfigIncludeContext includeContext() {
/* 124 */     return this.includeContext;
/*     */   }
/*     */   
/*     */   static AbstractConfigObject forceParsedToObject(ConfigValue value) {
/* 128 */     if (value instanceof AbstractConfigObject)
/* 129 */       return (AbstractConfigObject)value; 
/* 131 */     throw new ConfigException.WrongType(value.origin(), "", "object at file root", value.valueType().name());
/*     */   }
/*     */   
/*     */   public ConfigObject parse(ConfigParseOptions baseOptions) {
/* 139 */     LinkedList<Parseable> stack = parseStack.get();
/* 140 */     if (stack.size() >= 50)
/* 141 */       throw new ConfigException.Parse(this.initialOrigin, "include statements nested more than 50 times, you probably have a cycle in your includes. Trace: " + stack); 
/* 146 */     stack.addFirst(this);
/*     */     try {
/* 148 */       return forceParsedToObject(parseValue(baseOptions));
/*     */     } finally {
/* 150 */       stack.removeFirst();
/* 151 */       if (stack.isEmpty())
/* 152 */         parseStack.remove(); 
/*     */     } 
/*     */   }
/*     */   
/*     */   final AbstractConfigValue parseValue(ConfigParseOptions baseOptions) {
/*     */     ConfigOrigin origin;
/* 162 */     ConfigParseOptions options = fixupOptions(baseOptions);
/* 166 */     if (options.getOriginDescription() != null) {
/* 167 */       origin = SimpleConfigOrigin.newSimple(options.getOriginDescription());
/*     */     } else {
/* 169 */       origin = this.initialOrigin;
/*     */     } 
/* 170 */     return parseValue(origin, options);
/*     */   }
/*     */   
/*     */   private final AbstractConfigValue parseValue(ConfigOrigin origin, ConfigParseOptions finalOptions) {
/*     */     try {
/* 176 */       return rawParseValue(origin, finalOptions);
/* 177 */     } catch (IOException e) {
/* 178 */       if (finalOptions.getAllowMissing())
/* 179 */         return SimpleConfigObject.emptyMissing(origin); 
/* 181 */       trace("exception loading " + origin.description() + ": " + e.getClass().getName() + ": " + e.getMessage());
/* 183 */       throw new ConfigException.IO(origin, e.getClass().getName() + ": " + e.getMessage(), e);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected AbstractConfigValue rawParseValue(ConfigOrigin origin, ConfigParseOptions finalOptions) throws IOException {
/*     */     ConfigParseOptions optionsWithContentType;
/* 193 */     Reader reader = reader();
/* 196 */     ConfigSyntax contentType = contentType();
/* 199 */     if (contentType != null) {
/* 200 */       if (ConfigImpl.traceLoadsEnabled() && finalOptions.getSyntax() != null)
/* 201 */         trace("Overriding syntax " + finalOptions.getSyntax() + " with Content-Type which specified " + contentType); 
/* 204 */       optionsWithContentType = finalOptions.setSyntax(contentType);
/*     */     } else {
/* 206 */       optionsWithContentType = finalOptions;
/*     */     } 
/*     */     try {
/* 210 */       return rawParseValue(reader, origin, optionsWithContentType);
/*     */     } finally {
/* 212 */       reader.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   protected AbstractConfigValue rawParseValue(Reader reader, ConfigOrigin origin, ConfigParseOptions finalOptions) throws IOException {
/* 218 */     if (finalOptions.getSyntax() == ConfigSyntax.PROPERTIES)
/* 219 */       return PropertiesParser.parse(reader, origin); 
/* 221 */     Iterator<Token> tokens = Tokenizer.tokenize(origin, reader, finalOptions.getSyntax());
/* 222 */     return Parser.parse(tokens, origin, finalOptions, includeContext());
/*     */   }
/*     */   
/*     */   public ConfigObject parse() {
/* 227 */     return forceParsedToObject(parseValue(options()));
/*     */   }
/*     */   
/*     */   AbstractConfigValue parseValue() {
/* 231 */     return parseValue(options());
/*     */   }
/*     */   
/*     */   public final ConfigOrigin origin() {
/* 236 */     return this.initialOrigin;
/*     */   }
/*     */   
/*     */   public ConfigParseOptions options() {
/* 243 */     return this.initialOptions;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 248 */     return getClass().getSimpleName();
/*     */   }
/*     */   
/*     */   private static ConfigSyntax syntaxFromExtension(String name) {
/* 252 */     if (name.endsWith(".json"))
/* 253 */       return ConfigSyntax.JSON; 
/* 254 */     if (name.endsWith(".conf"))
/* 255 */       return ConfigSyntax.CONF; 
/* 256 */     if (name.endsWith(".properties"))
/* 257 */       return ConfigSyntax.PROPERTIES; 
/* 259 */     return null;
/*     */   }
/*     */   
/*     */   private static Reader readerFromStream(InputStream input) {
/* 263 */     return readerFromStream(input, "UTF-8");
/*     */   }
/*     */   
/*     */   private static Reader readerFromStream(InputStream input, String encoding) {
/*     */     try {
/* 272 */       Reader reader = new InputStreamReader(input, encoding);
/* 273 */       return new BufferedReader(reader);
/* 274 */     } catch (UnsupportedEncodingException e) {
/* 275 */       throw new ConfigException.BugOrBroken("Java runtime does not support UTF-8", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static Reader doNotClose(Reader input) {
/* 280 */     return new FilterReader(input) {
/*     */         public void close() {}
/*     */       };
/*     */   }
/*     */   
/*     */   static URL relativeTo(URL url, String filename) {
/* 290 */     if ((new File(filename)).isAbsolute())
/* 291 */       return null; 
/*     */     try {
/* 294 */       URI siblingURI = url.toURI();
/* 295 */       URI relative = new URI(filename);
/* 301 */       URL resolved = siblingURI.resolve(relative).toURL();
/* 303 */       return resolved;
/* 304 */     } catch (MalformedURLException e) {
/* 305 */       return null;
/* 306 */     } catch (URISyntaxException e) {
/* 307 */       return null;
/* 308 */     } catch (IllegalArgumentException e) {
/* 309 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   static File relativeTo(File file, String filename) {
/* 314 */     File child = new File(filename);
/* 316 */     if (child.isAbsolute())
/* 317 */       return null; 
/* 319 */     File parent = file.getParentFile();
/* 321 */     if (parent == null)
/* 322 */       return null; 
/* 324 */     return new File(parent, filename);
/*     */   }
/*     */   
/*     */   private static final class ParseableNotFound extends Parseable {
/*     */     private final String what;
/*     */     
/*     */     private final String message;
/*     */     
/*     */     ParseableNotFound(String what, String message, ConfigParseOptions options) {
/* 334 */       this.what = what;
/* 335 */       this.message = message;
/* 336 */       postConstruct(options);
/*     */     }
/*     */     
/*     */     protected Reader reader() throws IOException {
/* 341 */       throw new FileNotFoundException(this.message);
/*     */     }
/*     */     
/*     */     protected ConfigOrigin createOrigin() {
/* 346 */       return SimpleConfigOrigin.newSimple(this.what);
/*     */     }
/*     */   }
/*     */   
/*     */   public static Parseable newNotFound(String whatNotFound, String message, ConfigParseOptions options) {
/* 352 */     return new ParseableNotFound(whatNotFound, message, options);
/*     */   }
/*     */   
/*     */   private static final class ParseableReader extends Parseable {
/*     */     private final Reader reader;
/*     */     
/*     */     ParseableReader(Reader reader, ConfigParseOptions options) {
/* 359 */       this.reader = reader;
/* 360 */       postConstruct(options);
/*     */     }
/*     */     
/*     */     protected Reader reader() {
/* 365 */       if (ConfigImpl.traceLoadsEnabled())
/* 366 */         trace("Loading config from reader " + this.reader); 
/* 367 */       return this.reader;
/*     */     }
/*     */     
/*     */     protected ConfigOrigin createOrigin() {
/* 372 */       return SimpleConfigOrigin.newSimple("Reader");
/*     */     }
/*     */   }
/*     */   
/*     */   public static Parseable newReader(Reader reader, ConfigParseOptions options) {
/* 382 */     return new ParseableReader(doNotClose(reader), options);
/*     */   }
/*     */   
/*     */   private static final class ParseableString extends Parseable {
/*     */     private final String input;
/*     */     
/*     */     ParseableString(String input, ConfigParseOptions options) {
/* 389 */       this.input = input;
/* 390 */       postConstruct(options);
/*     */     }
/*     */     
/*     */     protected Reader reader() {
/* 395 */       if (ConfigImpl.traceLoadsEnabled())
/* 396 */         trace("Loading config from a String " + this.input); 
/* 397 */       return new StringReader(this.input);
/*     */     }
/*     */     
/*     */     protected ConfigOrigin createOrigin() {
/* 402 */       return SimpleConfigOrigin.newSimple("String");
/*     */     }
/*     */     
/*     */     public String toString() {
/* 407 */       return getClass().getSimpleName() + "(" + this.input + ")";
/*     */     }
/*     */   }
/*     */   
/*     */   public static Parseable newString(String input, ConfigParseOptions options) {
/* 412 */     return new ParseableString(input, options);
/*     */   }
/*     */   
/*     */   private static final class ParseableURL extends Parseable {
/*     */     private final URL input;
/*     */     
/* 417 */     private String contentType = null;
/*     */     
/*     */     ParseableURL(URL input, ConfigParseOptions options) {
/* 420 */       this.input = input;
/* 421 */       postConstruct(options);
/*     */     }
/*     */     
/*     */     protected Reader reader() throws IOException {
/* 426 */       if (ConfigImpl.traceLoadsEnabled())
/* 427 */         trace("Loading config from a URL: " + this.input.toExternalForm()); 
/* 428 */       URLConnection connection = this.input.openConnection();
/* 429 */       connection.connect();
/* 432 */       this.contentType = connection.getContentType();
/* 433 */       if (this.contentType != null) {
/* 434 */         if (ConfigImpl.traceLoadsEnabled())
/* 435 */           trace("URL sets Content-Type: '" + this.contentType + "'"); 
/* 436 */         this.contentType = this.contentType.trim();
/* 437 */         int semi = this.contentType.indexOf(';');
/* 438 */         if (semi >= 0)
/* 439 */           this.contentType = this.contentType.substring(0, semi); 
/*     */       } 
/* 442 */       InputStream stream = connection.getInputStream();
/* 444 */       return Parseable.readerFromStream(stream);
/*     */     }
/*     */     
/*     */     ConfigSyntax guessSyntax() {
/* 449 */       return Parseable.syntaxFromExtension(this.input.getPath());
/*     */     }
/*     */     
/*     */     ConfigSyntax contentType() {
/* 454 */       if (this.contentType != null) {
/* 455 */         if (this.contentType.equals("application/json"))
/* 456 */           return ConfigSyntax.JSON; 
/* 457 */         if (this.contentType.equals("text/x-java-properties"))
/* 458 */           return ConfigSyntax.PROPERTIES; 
/* 459 */         if (this.contentType.equals("application/hocon"))
/* 460 */           return ConfigSyntax.CONF; 
/* 462 */         if (ConfigImpl.traceLoadsEnabled())
/* 463 */           trace("'" + this.contentType + "' isn't a known content type"); 
/* 464 */         return null;
/*     */       } 
/* 467 */       return null;
/*     */     }
/*     */     
/*     */     ConfigParseable relativeTo(String filename) {
/* 473 */       URL url = relativeTo(this.input, filename);
/* 474 */       if (url == null)
/* 475 */         return null; 
/* 476 */       return newURL(url, options().setOriginDescription(null));
/*     */     }
/*     */     
/*     */     protected ConfigOrigin createOrigin() {
/* 481 */       return SimpleConfigOrigin.newURL(this.input);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 486 */       return getClass().getSimpleName() + "(" + this.input.toExternalForm() + ")";
/*     */     }
/*     */   }
/*     */   
/*     */   public static Parseable newURL(URL input, ConfigParseOptions options) {
/* 493 */     if (input.getProtocol().equals("file"))
/* 494 */       return newFile(ConfigImplUtil.urlToFile(input), options); 
/* 496 */     return new ParseableURL(input, options);
/*     */   }
/*     */   
/*     */   private static final class ParseableFile extends Parseable {
/*     */     private final File input;
/*     */     
/*     */     ParseableFile(File input, ConfigParseOptions options) {
/* 504 */       this.input = input;
/* 505 */       postConstruct(options);
/*     */     }
/*     */     
/*     */     protected Reader reader() throws IOException {
/* 510 */       if (ConfigImpl.traceLoadsEnabled())
/* 511 */         trace("Loading config from a file: " + this.input); 
/* 512 */       InputStream stream = new FileInputStream(this.input);
/* 513 */       return Parseable.readerFromStream(stream);
/*     */     }
/*     */     
/*     */     ConfigSyntax guessSyntax() {
/* 518 */       return Parseable.syntaxFromExtension(this.input.getName());
/*     */     }
/*     */     
/*     */     ConfigParseable relativeTo(String filename) {
/*     */       File sibling;
/* 524 */       if ((new File(filename)).isAbsolute()) {
/* 525 */         sibling = new File(filename);
/*     */       } else {
/* 528 */         sibling = relativeTo(this.input, filename);
/*     */       } 
/* 530 */       if (sibling == null)
/* 531 */         return null; 
/* 532 */       if (sibling.exists())
/* 533 */         return newFile(sibling, options().setOriginDescription(null)); 
/* 535 */       return super.relativeTo(filename);
/*     */     }
/*     */     
/*     */     protected ConfigOrigin createOrigin() {
/* 541 */       return SimpleConfigOrigin.newFile(this.input.getPath());
/*     */     }
/*     */     
/*     */     public String toString() {
/* 546 */       return getClass().getSimpleName() + "(" + this.input.getPath() + ")";
/*     */     }
/*     */   }
/*     */   
/*     */   public static Parseable newFile(File input, ConfigParseOptions options) {
/* 551 */     return new ParseableFile(input, options);
/*     */   }
/*     */   
/*     */   private static final class ParseableResources extends Parseable {
/*     */     private final String resource;
/*     */     
/*     */     ParseableResources(String resource, ConfigParseOptions options) {
/* 558 */       this.resource = resource;
/* 559 */       postConstruct(options);
/*     */     }
/*     */     
/*     */     protected Reader reader() throws IOException {
/* 564 */       throw new ConfigException.BugOrBroken("reader() should not be called on resources");
/*     */     }
/*     */     
/*     */     protected AbstractConfigObject rawParseValue(ConfigOrigin origin, ConfigParseOptions finalOptions) throws IOException {
/* 570 */       ClassLoader loader = finalOptions.getClassLoader();
/* 571 */       if (loader == null)
/* 572 */         throw new ConfigException.BugOrBroken("null class loader; pass in a class loader or use Thread.currentThread().setContextClassLoader()"); 
/* 574 */       Enumeration<URL> e = loader.getResources(this.resource);
/* 575 */       if (!e.hasMoreElements()) {
/* 576 */         if (ConfigImpl.traceLoadsEnabled())
/* 577 */           trace("Loading config from class loader " + loader + " but there were no resources called " + this.resource); 
/* 579 */         throw new IOException("resource not found on classpath: " + this.resource);
/*     */       } 
/* 581 */       AbstractConfigObject merged = SimpleConfigObject.empty(origin);
/* 582 */       while (e.hasMoreElements()) {
/*     */         AbstractConfigValue v;
/* 583 */         URL url = e.nextElement();
/* 585 */         if (ConfigImpl.traceLoadsEnabled())
/* 586 */           trace("Loading config from URL " + url.toExternalForm() + " from class loader " + loader); 
/* 589 */         ConfigOrigin elementOrigin = ((SimpleConfigOrigin)origin).addURL(url);
/* 595 */         InputStream stream = url.openStream();
/*     */         try {
/* 597 */           Reader reader = Parseable.readerFromStream(stream);
/* 598 */           stream = null;
/*     */           try {
/* 602 */             v = rawParseValue(reader, elementOrigin, finalOptions);
/*     */           } finally {
/* 604 */             reader.close();
/*     */           } 
/*     */         } finally {
/* 608 */           if (stream != null)
/* 609 */             stream.close(); 
/*     */         } 
/* 612 */         merged = merged.withFallback(v);
/*     */       } 
/* 615 */       return merged;
/*     */     }
/*     */     
/*     */     ConfigSyntax guessSyntax() {
/* 620 */       return Parseable.syntaxFromExtension(this.resource);
/*     */     }
/*     */     
/*     */     static String parent(String resource) {
/* 628 */       int i = resource.lastIndexOf('/');
/* 629 */       if (i < 0)
/* 630 */         return null; 
/* 632 */       return resource.substring(0, i);
/*     */     }
/*     */     
/*     */     ConfigParseable relativeTo(String sibling) {
/* 638 */       if (sibling.startsWith("/"))
/* 641 */         return newResources(sibling.substring(1), options().setOriginDescription(null)); 
/* 648 */       String parent = parent(this.resource);
/* 649 */       if (parent == null)
/* 650 */         return newResources(sibling, options().setOriginDescription(null)); 
/* 652 */       return newResources(parent + "/" + sibling, options().setOriginDescription(null));
/*     */     }
/*     */     
/*     */     protected ConfigOrigin createOrigin() {
/* 659 */       return SimpleConfigOrigin.newResource(this.resource);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 664 */       return getClass().getSimpleName() + "(" + this.resource + ")";
/*     */     }
/*     */   }
/*     */   
/*     */   public static Parseable newResources(Class<?> klass, String resource, ConfigParseOptions options) {
/* 669 */     return newResources(convertResourceName(klass, resource), options.setClassLoader(klass.getClassLoader()));
/*     */   }
/*     */   
/*     */   private static String convertResourceName(Class<?> klass, String resource) {
/* 680 */     if (resource.startsWith("/"))
/* 682 */       return resource.substring(1); 
/* 684 */     String className = klass.getName();
/* 685 */     int i = className.lastIndexOf('.');
/* 686 */     if (i < 0)
/* 688 */       return resource; 
/* 691 */     String packageName = className.substring(0, i);
/* 692 */     String packagePath = packageName.replace('.', '/');
/* 693 */     return packagePath + "/" + resource;
/*     */   }
/*     */   
/*     */   public static Parseable newResources(String resource, ConfigParseOptions options) {
/* 699 */     if (options.getClassLoader() == null)
/* 700 */       throw new ConfigException.BugOrBroken("null class loader; pass in a class loader or use Thread.currentThread().setContextClassLoader()"); 
/* 702 */     return new ParseableResources(resource, options);
/*     */   }
/*     */   
/*     */   private static final class ParseableProperties extends Parseable {
/*     */     private final Properties props;
/*     */     
/*     */     ParseableProperties(Properties props, ConfigParseOptions options) {
/* 709 */       this.props = props;
/* 710 */       postConstruct(options);
/*     */     }
/*     */     
/*     */     protected Reader reader() throws IOException {
/* 715 */       throw new ConfigException.BugOrBroken("reader() should not be called on props");
/*     */     }
/*     */     
/*     */     protected AbstractConfigObject rawParseValue(ConfigOrigin origin, ConfigParseOptions finalOptions) {
/* 721 */       if (ConfigImpl.traceLoadsEnabled())
/* 722 */         trace("Loading config from properties " + this.props); 
/* 723 */       return PropertiesParser.fromProperties(origin, this.props);
/*     */     }
/*     */     
/*     */     ConfigSyntax guessSyntax() {
/* 728 */       return ConfigSyntax.PROPERTIES;
/*     */     }
/*     */     
/*     */     protected ConfigOrigin createOrigin() {
/* 733 */       return SimpleConfigOrigin.newSimple("properties");
/*     */     }
/*     */     
/*     */     public String toString() {
/* 738 */       return getClass().getSimpleName() + "(" + this.props.size() + " props)";
/*     */     }
/*     */   }
/*     */   
/*     */   public static Parseable newProperties(Properties properties, ConfigParseOptions options) {
/* 743 */     return new ParseableProperties(properties, options);
/*     */   }
/*     */   
/*     */   protected abstract Reader reader() throws IOException;
/*     */   
/*     */   protected abstract ConfigOrigin createOrigin();
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\typesafe\config\impl\Parseable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
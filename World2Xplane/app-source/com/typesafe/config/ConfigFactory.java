/*     */ package com.typesafe.config;
/*     */ 
/*     */ import com.typesafe.config.impl.ConfigImpl;
/*     */ import com.typesafe.config.impl.Parseable;
/*     */ import java.io.File;
/*     */ import java.io.Reader;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ import java.util.concurrent.Callable;
/*     */ 
/*     */ public final class ConfigFactory {
/*     */   public static Config load(String resourceBasename) {
/*  74 */     return load(resourceBasename, ConfigParseOptions.defaults(), ConfigResolveOptions.defaults());
/*     */   }
/*     */   
/*     */   public static Config load(ClassLoader loader, String resourceBasename) {
/*  94 */     return load(resourceBasename, ConfigParseOptions.defaults().setClassLoader(loader), ConfigResolveOptions.defaults());
/*     */   }
/*     */   
/*     */   public static Config load(String resourceBasename, ConfigParseOptions parseOptions, ConfigResolveOptions resolveOptions) {
/* 112 */     ConfigParseOptions withLoader = ensureClassLoader(parseOptions, "load");
/* 113 */     Config appConfig = parseResourcesAnySyntax(resourceBasename, withLoader);
/* 114 */     return load(withLoader.getClassLoader(), appConfig, resolveOptions);
/*     */   }
/*     */   
/*     */   public static Config load(ClassLoader loader, String resourceBasename, ConfigParseOptions parseOptions, ConfigResolveOptions resolveOptions) {
/* 136 */     return load(resourceBasename, parseOptions.setClassLoader(loader), resolveOptions);
/*     */   }
/*     */   
/*     */   private static ClassLoader checkedContextClassLoader(String methodName) {
/* 140 */     ClassLoader loader = Thread.currentThread().getContextClassLoader();
/* 141 */     if (loader == null)
/* 142 */       throw new ConfigException.BugOrBroken("Context class loader is not set for the current thread; if Thread.currentThread().getContextClassLoader() returns null, you must pass a ClassLoader explicitly to ConfigFactory." + methodName); 
/* 146 */     return loader;
/*     */   }
/*     */   
/*     */   private static ConfigParseOptions ensureClassLoader(ConfigParseOptions options, String methodName) {
/* 150 */     if (options.getClassLoader() == null)
/* 151 */       return options.setClassLoader(checkedContextClassLoader(methodName)); 
/* 153 */     return options;
/*     */   }
/*     */   
/*     */   public static Config load(Config config) {
/* 167 */     return load(checkedContextClassLoader("load"), config);
/*     */   }
/*     */   
/*     */   public static Config load(ClassLoader loader, Config config) {
/* 171 */     return load(loader, config, ConfigResolveOptions.defaults());
/*     */   }
/*     */   
/*     */   public static Config load(Config config, ConfigResolveOptions resolveOptions) {
/* 185 */     return load(checkedContextClassLoader("load"), config, resolveOptions);
/*     */   }
/*     */   
/*     */   public static Config load(ClassLoader loader, Config config, ConfigResolveOptions resolveOptions) {
/* 202 */     return defaultOverrides(loader).withFallback(config).withFallback(defaultReference(loader)).resolve(resolveOptions);
/*     */   }
/*     */   
/*     */   private static Config loadDefaultConfig(ConfigParseOptions parseOptions, ConfigResolveOptions resolveOptions) {
/* 207 */     ClassLoader loader = parseOptions.getClassLoader();
/* 208 */     if (loader == null)
/* 209 */       throw new ConfigException.BugOrBroken("ClassLoader should have been set here; bug in ConfigFactory. (You can probably work around this bug by passing in a class loader or calling currentThread().setContextClassLoader() though.)"); 
/* 213 */     int specified = 0;
/* 217 */     String resource = System.getProperty("config.resource");
/* 218 */     if (resource != null)
/* 219 */       specified++; 
/* 220 */     String file = System.getProperty("config.file");
/* 221 */     if (file != null)
/* 222 */       specified++; 
/* 223 */     String url = System.getProperty("config.url");
/* 224 */     if (url != null)
/* 225 */       specified++; 
/* 227 */     if (specified == 0)
/* 228 */       return load(loader, "application", parseOptions, resolveOptions); 
/* 229 */     if (specified > 1)
/* 230 */       throw new ConfigException.Generic("You set more than one of config.file='" + file + "', config.url='" + url + "', config.resource='" + resource + "'; don't know which one to use!"); 
/* 235 */     ConfigParseOptions overrideOptions = parseOptions.setAllowMissing(false);
/* 236 */     if (resource != null) {
/* 237 */       if (resource.startsWith("/"))
/* 238 */         resource = resource.substring(1); 
/* 241 */       Config parsedResources = parseResources(loader, resource, overrideOptions);
/* 242 */       return load(loader, parsedResources, resolveOptions);
/*     */     } 
/* 243 */     if (file != null) {
/* 244 */       Config parsedFile = parseFile(new File(file), overrideOptions);
/* 245 */       return load(loader, parsedFile, resolveOptions);
/*     */     } 
/*     */     try {
/* 248 */       Config parsedURL = parseURL(new URL(url), overrideOptions);
/* 249 */       return load(loader, parsedURL, resolveOptions);
/* 250 */     } catch (MalformedURLException e) {
/* 251 */       throw new ConfigException.Generic("Bad URL in config.url system property: '" + url + "': " + e.getMessage(), e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Config load() {
/* 288 */     return load(ConfigParseOptions.defaults());
/*     */   }
/*     */   
/*     */   public static Config load(ConfigParseOptions parseOptions) {
/* 299 */     return load(parseOptions, ConfigResolveOptions.defaults());
/*     */   }
/*     */   
/*     */   public static Config load(ClassLoader loader) {
/* 311 */     return load(ConfigParseOptions.defaults().setClassLoader(loader));
/*     */   }
/*     */   
/*     */   public static Config load(ClassLoader loader, ConfigParseOptions parseOptions) {
/* 325 */     return load(parseOptions.setClassLoader(loader));
/*     */   }
/*     */   
/*     */   public static Config load(ClassLoader loader, ConfigResolveOptions resolveOptions) {
/* 339 */     return load(loader, ConfigParseOptions.defaults(), resolveOptions);
/*     */   }
/*     */   
/*     */   public static Config load(ClassLoader loader, ConfigParseOptions parseOptions, ConfigResolveOptions resolveOptions) {
/* 356 */     return load(parseOptions.setClassLoader(loader), resolveOptions);
/*     */   }
/*     */   
/*     */   private static Config load(ConfigParseOptions parseOptions, final ConfigResolveOptions resolveOptions) {
/* 374 */     final ConfigParseOptions withLoader = ensureClassLoader(parseOptions, "load");
/* 375 */     return ConfigImpl.computeCachedConfig(withLoader.getClassLoader(), "load", new Callable<Config>() {
/*     */           public Config call() {
/* 378 */             return ConfigFactory.loadDefaultConfig(withLoader, resolveOptions);
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public static Config defaultReference() {
/* 412 */     return defaultReference(checkedContextClassLoader("defaultReference"));
/*     */   }
/*     */   
/*     */   public static Config defaultReference(ClassLoader loader) {
/* 423 */     return ConfigImpl.defaultReference(loader);
/*     */   }
/*     */   
/*     */   public static Config defaultOverrides() {
/* 442 */     return systemProperties();
/*     */   }
/*     */   
/*     */   public static Config defaultOverrides(ClassLoader loader) {
/* 453 */     return systemProperties();
/*     */   }
/*     */   
/*     */   public static void invalidateCaches() {
/* 478 */     ConfigImpl.reloadSystemPropertiesConfig();
/*     */   }
/*     */   
/*     */   public static Config empty() {
/* 489 */     return empty(null);
/*     */   }
/*     */   
/*     */   public static Config empty(String originDescription) {
/* 505 */     return ConfigImpl.emptyConfig(originDescription);
/*     */   }
/*     */   
/*     */   public static Config systemProperties() {
/* 528 */     return ConfigImpl.systemPropertiesAsConfig();
/*     */   }
/*     */   
/*     */   public static Config systemEnvironment() {
/* 546 */     return ConfigImpl.envVariablesAsConfig();
/*     */   }
/*     */   
/*     */   public static Config parseProperties(Properties properties, ConfigParseOptions options) {
/* 571 */     return Parseable.newProperties(properties, options).parse().toConfig();
/*     */   }
/*     */   
/*     */   public static Config parseProperties(Properties properties) {
/* 575 */     return parseProperties(properties, ConfigParseOptions.defaults());
/*     */   }
/*     */   
/*     */   public static Config parseReader(Reader reader, ConfigParseOptions options) {
/* 579 */     return Parseable.newReader(reader, options).parse().toConfig();
/*     */   }
/*     */   
/*     */   public static Config parseReader(Reader reader) {
/* 583 */     return parseReader(reader, ConfigParseOptions.defaults());
/*     */   }
/*     */   
/*     */   public static Config parseURL(URL url, ConfigParseOptions options) {
/* 587 */     return Parseable.newURL(url, options).parse().toConfig();
/*     */   }
/*     */   
/*     */   public static Config parseURL(URL url) {
/* 591 */     return parseURL(url, ConfigParseOptions.defaults());
/*     */   }
/*     */   
/*     */   public static Config parseFile(File file, ConfigParseOptions options) {
/* 595 */     return Parseable.newFile(file, options).parse().toConfig();
/*     */   }
/*     */   
/*     */   public static Config parseFile(File file) {
/* 599 */     return parseFile(file, ConfigParseOptions.defaults());
/*     */   }
/*     */   
/*     */   public static Config parseFileAnySyntax(File fileBasename, ConfigParseOptions options) {
/* 637 */     return ConfigImpl.parseFileAnySyntax(fileBasename, options).toConfig();
/*     */   }
/*     */   
/*     */   public static Config parseFileAnySyntax(File fileBasename) {
/* 641 */     return parseFileAnySyntax(fileBasename, ConfigParseOptions.defaults());
/*     */   }
/*     */   
/*     */   public static Config parseResources(Class<?> klass, String resource, ConfigParseOptions options) {
/* 675 */     return Parseable.newResources(klass, resource, options).parse().toConfig();
/*     */   }
/*     */   
/*     */   public static Config parseResources(Class<?> klass, String resource) {
/* 680 */     return parseResources(klass, resource, ConfigParseOptions.defaults());
/*     */   }
/*     */   
/*     */   public static Config parseResourcesAnySyntax(Class<?> klass, String resourceBasename, ConfigParseOptions options) {
/* 715 */     return ConfigImpl.parseResourcesAnySyntax(klass, resourceBasename, options).toConfig();
/*     */   }
/*     */   
/*     */   public static Config parseResourcesAnySyntax(Class<?> klass, String resourceBasename) {
/* 720 */     return parseResourcesAnySyntax(klass, resourceBasename, ConfigParseOptions.defaults());
/*     */   }
/*     */   
/*     */   public static Config parseResources(ClassLoader loader, String resource, ConfigParseOptions options) {
/* 747 */     return parseResources(resource, options.setClassLoader(loader));
/*     */   }
/*     */   
/*     */   public static Config parseResources(ClassLoader loader, String resource) {
/* 751 */     return parseResources(loader, resource, ConfigParseOptions.defaults());
/*     */   }
/*     */   
/*     */   public static Config parseResourcesAnySyntax(ClassLoader loader, String resourceBasename, ConfigParseOptions options) {
/* 779 */     return ConfigImpl.parseResourcesAnySyntax(resourceBasename, options.setClassLoader(loader)).toConfig();
/*     */   }
/*     */   
/*     */   public static Config parseResourcesAnySyntax(ClassLoader loader, String resourceBasename) {
/* 784 */     return parseResourcesAnySyntax(loader, resourceBasename, ConfigParseOptions.defaults());
/*     */   }
/*     */   
/*     */   public static Config parseResources(String resource, ConfigParseOptions options) {
/* 793 */     ConfigParseOptions withLoader = ensureClassLoader(options, "parseResources");
/* 794 */     return Parseable.newResources(resource, withLoader).parse().toConfig();
/*     */   }
/*     */   
/*     */   public static Config parseResources(String resource) {
/* 802 */     return parseResources(resource, ConfigParseOptions.defaults());
/*     */   }
/*     */   
/*     */   public static Config parseResourcesAnySyntax(String resourceBasename, ConfigParseOptions options) {
/* 811 */     return ConfigImpl.parseResourcesAnySyntax(resourceBasename, options).toConfig();
/*     */   }
/*     */   
/*     */   public static Config parseResourcesAnySyntax(String resourceBasename) {
/* 819 */     return parseResourcesAnySyntax(resourceBasename, ConfigParseOptions.defaults());
/*     */   }
/*     */   
/*     */   public static Config parseString(String s, ConfigParseOptions options) {
/* 823 */     return Parseable.newString(s, options).parse().toConfig();
/*     */   }
/*     */   
/*     */   public static Config parseString(String s) {
/* 827 */     return parseString(s, ConfigParseOptions.defaults());
/*     */   }
/*     */   
/*     */   public static Config parseMap(Map<String, ? extends Object> values, String originDescription) {
/* 855 */     return ConfigImpl.fromPathMap(values, originDescription).toConfig();
/*     */   }
/*     */   
/*     */   public static Config parseMap(Map<String, ? extends Object> values) {
/* 866 */     return parseMap(values, null);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\typesafe\config\ConfigFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
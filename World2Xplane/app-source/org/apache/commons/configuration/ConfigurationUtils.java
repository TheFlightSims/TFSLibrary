/*     */ package org.apache.commons.configuration;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.PrintStream;
/*     */ import java.io.PrintWriter;
/*     */ import java.io.StringWriter;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.util.Iterator;
/*     */ import org.apache.commons.configuration.event.ConfigurationErrorEvent;
/*     */ import org.apache.commons.configuration.event.ConfigurationErrorListener;
/*     */ import org.apache.commons.configuration.event.EventSource;
/*     */ import org.apache.commons.configuration.reloading.Reloadable;
/*     */ import org.apache.commons.configuration.tree.ExpressionEngine;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ 
/*     */ public final class ConfigurationUtils {
/*     */   static final String PROTOCOL_FILE = "file";
/*     */   
/*     */   static final String RESOURCE_PATH_SEPARATOR = "/";
/*     */   
/*     */   private static final String FILE_SCHEME = "file:";
/*     */   
/*     */   private static final String METHOD_CLONE = "clone";
/*     */   
/*     */   private static final int HEX = 16;
/*     */   
/*  66 */   private static final Log LOG = LogFactory.getLog(ConfigurationUtils.class);
/*     */   
/*     */   public static void dump(Configuration configuration, PrintStream out) {
/*  84 */     dump(configuration, new PrintWriter(out));
/*     */   }
/*     */   
/*     */   public static void dump(Configuration configuration, PrintWriter out) {
/*  95 */     Iterator keys = configuration.getKeys();
/*  96 */     while (keys.hasNext()) {
/*  98 */       String key = keys.next();
/*  99 */       Object value = configuration.getProperty(key);
/* 100 */       out.print(key);
/* 101 */       out.print("=");
/* 102 */       out.print(value);
/* 104 */       if (keys.hasNext())
/* 106 */         out.println(); 
/*     */     } 
/* 110 */     out.flush();
/*     */   }
/*     */   
/*     */   public static String toString(Configuration configuration) {
/* 122 */     StringWriter writer = new StringWriter();
/* 123 */     dump(configuration, new PrintWriter(writer));
/* 124 */     return writer.toString();
/*     */   }
/*     */   
/*     */   public static void copy(Configuration source, Configuration target) {
/* 143 */     Iterator keys = source.getKeys();
/* 144 */     while (keys.hasNext()) {
/* 146 */       String key = keys.next();
/* 147 */       target.setProperty(key, source.getProperty(key));
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void append(Configuration source, Configuration target) {
/* 167 */     Iterator keys = source.getKeys();
/* 168 */     while (keys.hasNext()) {
/* 170 */       String key = keys.next();
/* 171 */       target.addProperty(key, source.getProperty(key));
/*     */     } 
/*     */   }
/*     */   
/*     */   public static HierarchicalConfiguration convertToHierarchical(Configuration conf) {
/* 188 */     return convertToHierarchical(conf, null);
/*     */   }
/*     */   
/*     */   public static HierarchicalConfiguration convertToHierarchical(Configuration conf, ExpressionEngine engine) {
/* 217 */     if (conf == null)
/* 219 */       return null; 
/* 222 */     if (conf instanceof HierarchicalConfiguration) {
/*     */       HierarchicalConfiguration hierarchicalConfiguration;
/* 225 */       if (conf instanceof Reloadable) {
/* 227 */         Object lock = ((Reloadable)conf).getReloadLock();
/* 228 */         synchronized (lock) {
/* 230 */           hierarchicalConfiguration = new HierarchicalConfiguration((HierarchicalConfiguration)conf);
/*     */         } 
/*     */       } else {
/* 235 */         hierarchicalConfiguration = (HierarchicalConfiguration)conf;
/*     */       } 
/* 237 */       if (engine != null)
/* 239 */         hierarchicalConfiguration.setExpressionEngine(engine); 
/* 242 */       return hierarchicalConfiguration;
/*     */     } 
/* 246 */     HierarchicalConfiguration hc = new HierarchicalConfiguration();
/* 247 */     if (engine != null)
/* 249 */       hc.setExpressionEngine(engine); 
/* 253 */     boolean delimiterParsingStatus = hc.isDelimiterParsingDisabled();
/* 254 */     hc.setDelimiterParsingDisabled(true);
/* 255 */     hc.append(conf);
/* 256 */     hc.setDelimiterParsingDisabled(delimiterParsingStatus);
/* 257 */     return hc;
/*     */   }
/*     */   
/*     */   public static Configuration cloneConfiguration(Configuration config) throws ConfigurationRuntimeException {
/* 277 */     if (config == null)
/* 279 */       return null; 
/*     */     try {
/* 285 */       return (Configuration)clone(config);
/* 287 */     } catch (CloneNotSupportedException cnex) {
/* 289 */       throw new ConfigurationRuntimeException(cnex);
/*     */     } 
/*     */   }
/*     */   
/*     */   static Object clone(Object obj) throws CloneNotSupportedException {
/* 309 */     if (obj instanceof Cloneable)
/*     */       try {
/* 313 */         Method m = obj.getClass().getMethod("clone", null);
/* 314 */         return m.invoke(obj, null);
/* 316 */       } catch (NoSuchMethodException nmex) {
/* 318 */         throw new CloneNotSupportedException("No clone() method found for class" + obj.getClass().getName());
/* 322 */       } catch (IllegalAccessException iaex) {
/* 324 */         throw new ConfigurationRuntimeException(iaex);
/* 326 */       } catch (InvocationTargetException itex) {
/* 328 */         throw new ConfigurationRuntimeException(itex);
/*     */       }  
/* 333 */     throw new CloneNotSupportedException(obj.getClass().getName() + " does not implement Cloneable");
/*     */   }
/*     */   
/*     */   public static URL getURL(String basePath, String file) throws MalformedURLException {
/* 350 */     return FileSystem.getDefaultFileSystem().getURL(basePath, file);
/*     */   }
/*     */   
/*     */   static File constructFile(String basePath, String fileName) {
/* 366 */     File file, absolute = null;
/* 367 */     if (fileName != null)
/* 369 */       absolute = new File(fileName); 
/* 372 */     if (StringUtils.isEmpty(basePath) || (absolute != null && absolute.isAbsolute())) {
/* 374 */       file = new File(fileName);
/*     */     } else {
/* 378 */       StringBuffer fName = new StringBuffer();
/* 379 */       fName.append(basePath);
/* 382 */       if (!basePath.endsWith(File.separator))
/* 384 */         fName.append(File.separator); 
/* 393 */       if (fileName.startsWith("." + File.separator)) {
/* 395 */         fName.append(fileName.substring(2));
/*     */       } else {
/* 399 */         fName.append(fileName);
/*     */       } 
/* 402 */       file = new File(fName.toString());
/*     */     } 
/* 405 */     return file;
/*     */   }
/*     */   
/*     */   public static URL locate(String name) {
/* 418 */     return locate(null, name);
/*     */   }
/*     */   
/*     */   public static URL locate(String base, String name) {
/* 432 */     return locate(FileSystem.getDefaultFileSystem(), base, name);
/*     */   }
/*     */   
/*     */   public static URL locate(FileSystem fileSystem, String base, String name) {
/* 447 */     if (LOG.isDebugEnabled()) {
/* 449 */       StringBuffer buf = new StringBuffer();
/* 450 */       buf.append("ConfigurationUtils.locate(): base is ").append(base);
/* 451 */       buf.append(", name is ").append(name);
/* 452 */       LOG.debug(buf.toString());
/*     */     } 
/* 455 */     if (name == null)
/* 458 */       return null; 
/* 463 */     URL url = fileSystem.locateFromURL(base, name);
/* 466 */     if (url == null) {
/* 468 */       File file = new File(name);
/* 469 */       if (file.isAbsolute() && file.exists())
/*     */         try {
/* 473 */           url = toURL(file);
/* 474 */           LOG.debug("Loading configuration from the absolute path " + name);
/* 476 */         } catch (MalformedURLException e) {
/* 478 */           LOG.warn("Could not obtain URL from file", e);
/*     */         }  
/*     */     } 
/* 484 */     if (url == null)
/*     */       try {
/* 488 */         File file = constructFile(base, name);
/* 489 */         if (file != null && file.exists())
/* 491 */           url = toURL(file); 
/* 494 */         if (url != null)
/* 496 */           LOG.debug("Loading configuration from the path " + file); 
/* 499 */       } catch (MalformedURLException e) {
/* 501 */         LOG.warn("Could not obtain URL from file", e);
/*     */       }  
/* 506 */     if (url == null)
/*     */       try {
/* 510 */         File file = constructFile(System.getProperty("user.home"), name);
/* 511 */         if (file != null && file.exists())
/* 513 */           url = toURL(file); 
/* 516 */         if (url != null)
/* 518 */           LOG.debug("Loading configuration from the home path " + file); 
/* 522 */       } catch (MalformedURLException e) {
/* 524 */         LOG.warn("Could not obtain URL from file", e);
/*     */       }  
/* 529 */     if (url == null)
/* 531 */       url = locateFromClasspath(name); 
/* 533 */     return url;
/*     */   }
/*     */   
/*     */   static URL locateFromClasspath(String resourceName) {
/* 544 */     URL url = null;
/* 546 */     ClassLoader loader = Thread.currentThread().getContextClassLoader();
/* 547 */     if (loader != null) {
/* 549 */       url = loader.getResource(resourceName);
/* 551 */       if (url != null)
/* 553 */         LOG.debug("Loading configuration from the context classpath (" + resourceName + ")"); 
/*     */     } 
/* 558 */     if (url == null) {
/* 560 */       url = ClassLoader.getSystemResource(resourceName);
/* 562 */       if (url != null)
/* 564 */         LOG.debug("Loading configuration from the system classpath (" + resourceName + ")"); 
/*     */     } 
/* 567 */     return url;
/*     */   }
/*     */   
/*     */   static String getBasePath(URL url) {
/* 579 */     if (url == null)
/* 581 */       return null; 
/* 584 */     String s = url.toString();
/* 585 */     if (s.startsWith("file:") && !s.startsWith("file://"))
/* 587 */       s = "file://" + s.substring("file:".length()); 
/* 590 */     if (s.endsWith("/") || StringUtils.isEmpty(url.getPath()))
/* 592 */       return s; 
/* 596 */     return s.substring(0, s.lastIndexOf("/") + 1);
/*     */   }
/*     */   
/*     */   static String getFileName(URL url) {
/* 608 */     if (url == null)
/* 610 */       return null; 
/* 613 */     String path = url.getPath();
/* 615 */     if (path.endsWith("/") || StringUtils.isEmpty(path))
/* 617 */       return null; 
/* 621 */     return path.substring(path.lastIndexOf("/") + 1);
/*     */   }
/*     */   
/*     */   public static File getFile(String basePath, String fileName) {
/*     */     URL uRL;
/* 653 */     File f = new File(fileName);
/* 654 */     if (f.isAbsolute())
/* 656 */       return f; 
/*     */     try {
/* 663 */       uRL = new URL(new URL(basePath), fileName);
/* 665 */     } catch (MalformedURLException mex1) {
/*     */       try {
/* 669 */         uRL = new URL(fileName);
/* 671 */       } catch (MalformedURLException mex2) {
/* 673 */         uRL = null;
/*     */       } 
/*     */     } 
/* 677 */     if (uRL != null)
/* 679 */       return fileFromURL(uRL); 
/* 682 */     return constructFile(basePath, fileName);
/*     */   }
/*     */   
/*     */   public static File fileFromURL(URL url) {
/* 695 */     if (url == null || !url.getProtocol().equals("file"))
/* 697 */       return null; 
/* 701 */     String filename = url.getFile().replace('/', File.separatorChar);
/* 702 */     int pos = 0;
/* 703 */     while ((pos = filename.indexOf('%', pos)) >= 0) {
/* 705 */       if (pos + 2 < filename.length()) {
/* 707 */         String hexStr = filename.substring(pos + 1, pos + 3);
/* 708 */         char ch = (char)Integer.parseInt(hexStr, 16);
/* 709 */         filename = filename.substring(0, pos) + ch + filename.substring(pos + 3);
/*     */       } 
/*     */     } 
/* 713 */     return new File(filename);
/*     */   }
/*     */   
/*     */   static URL toURL(File file) throws MalformedURLException {
/* 729 */     return file.toURI().toURL();
/*     */   }
/*     */   
/*     */   public static void enableRuntimeExceptions(Configuration src) {
/* 748 */     if (!(src instanceof EventSource))
/* 750 */       throw new IllegalArgumentException("Configuration must be derived from EventSource!"); 
/* 753 */     ((EventSource)src).addErrorListener(new ConfigurationErrorListener() {
/*     */           public void configurationError(ConfigurationErrorEvent event) {
/* 758 */             throw new ConfigurationRuntimeException(event.getCause());
/*     */           }
/*     */         });
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\ConfigurationUtils.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */
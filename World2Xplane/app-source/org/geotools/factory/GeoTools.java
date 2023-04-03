/*     */ package org.geotools.factory;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ import java.util.Set;
/*     */ import java.util.StringTokenizer;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import javax.naming.Context;
/*     */ import javax.naming.InitialContext;
/*     */ import javax.naming.NamingException;
/*     */ import javax.swing.event.ChangeEvent;
/*     */ import javax.swing.event.ChangeListener;
/*     */ import javax.swing.event.EventListenerList;
/*     */ import org.geotools.resources.Arguments;
/*     */ import org.geotools.resources.Classes;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.geotools.util.Version;
/*     */ import org.geotools.util.logging.LoggerFactory;
/*     */ import org.geotools.util.logging.Logging;
/*     */ 
/*     */ public final class GeoTools {
/*  84 */   private static final Properties PROPS = loadProperites("GeoTools.properties");
/*     */   
/*     */   private static Properties loadProperites(String resource) {
/*  88 */     Properties props = new Properties();
/*  89 */     InputStream stream = GeoTools.class.getResourceAsStream(resource);
/*  90 */     if (stream != null)
/*     */       try {
/*  92 */         props.load(stream);
/*  93 */       } catch (IOException ignore) {
/*     */         try {
/*  96 */           stream.close();
/*  97 */         } catch (IOException iOException) {}
/*     */       } finally {
/*     */         try {
/*     */           stream.close();
/*  97 */         } catch (IOException ignore) {}
/*     */       }  
/* 102 */     return props;
/*     */   }
/*     */   
/* 108 */   private static final Version VERSION = new Version(PROPS.getProperty("version", "10-SNAPSHOT"));
/*     */   
/* 115 */   private static final String BUILD_REVISION = PROPS.getProperty("build.revision", "-1");
/*     */   
/* 121 */   private static final String BUILD_TIMESTAMP = PROPS.getProperty("build.timestamp", "");
/*     */   
/* 128 */   private static final EventListenerList LISTENERS = new EventListenerList();
/*     */   
/* 135 */   private static final Map<String, RenderingHints.Key> BINDINGS = new HashMap<String, RenderingHints.Key>();
/*     */   
/*     */   public static final String CRS_AUTHORITY_EXTRA_DIRECTORY = "org.geotools.referencing.crs-directory";
/*     */   
/*     */   public static final String EPSG_DATA_SOURCE = "org.geotools.referencing.epsg-datasource";
/*     */   
/*     */   public static final String FORCE_LONGITUDE_FIRST_AXIS_ORDER = "org.geotools.referencing.forceXY";
/*     */   
/*     */   public static final String RESAMPLE_TOLERANCE = "org.geotools.referencing.resampleTolerance";
/*     */   
/*     */   private static InitialContext context;
/*     */   
/*     */   static {
/* 149 */     bind("org.geotools.referencing.crs-directory", Hints.CRS_AUTHORITY_EXTRA_DIRECTORY);
/* 162 */     bind("org.geotools.referencing.epsg-datasource", Hints.EPSG_DATA_SOURCE);
/* 188 */     bind("org.geotools.referencing.forceXY", Hints.FORCE_LONGITUDE_FIRST_AXIS_ORDER);
/* 205 */     bind("org.geotools.referencing.resampleTolerance", Hints.RESAMPLE_TOLERANCE);
/*     */   }
/*     */   
/* 218 */   private static final Set<ClassLoader> addedClassLoaders = Collections.synchronizedSet(new HashSet<ClassLoader>());
/*     */   
/*     */   private static void bind(String property, RenderingHints.Key key) {
/* 239 */     synchronized (BINDINGS) {
/* 240 */       RenderingHints.Key old = BINDINGS.put(property, key);
/* 241 */       if (old == null)
/*     */         return; 
/* 245 */       BINDINGS.put(property, old);
/*     */     } 
/* 247 */     throw new IllegalArgumentException(Errors.format(58, "property", property));
/*     */   }
/*     */   
/*     */   public static String getAboutInfo() {
/* 259 */     StringBuilder sb = new StringBuilder();
/* 261 */     sb.append(getEnvironmentInfo());
/* 262 */     sb.append(String.format("%n", new Object[0]));
/* 263 */     sb.append(getGeoToolsJarInfo());
/* 265 */     return sb.toString();
/*     */   }
/*     */   
/*     */   public static String getEnvironmentInfo() {
/* 274 */     String newline = String.format("%n", new Object[0]);
/* 275 */     String indent = "    ";
/* 277 */     StringBuilder sb = new StringBuilder();
/* 278 */     sb.append("GeoTools version ").append(getVersion().toString());
/* 279 */     if (sb.toString().endsWith("SNAPSHOT"))
/* 280 */       sb.append(" (built from r").append(getBuildRevision().toString()).append(")"); 
/* 283 */     sb.append(newline).append("Java version: ");
/* 284 */     sb.append(System.getProperty("java.version"));
/* 286 */     sb.append(newline).append("Operating system: ");
/* 287 */     sb.append(System.getProperty("os.name")).append(' ').append(System.getProperty("os.version"));
/* 289 */     return sb.toString();
/*     */   }
/*     */   
/*     */   public static String getGeoToolsJarInfo() {
/* 298 */     StringBuilder sb = new StringBuilder();
/* 299 */     String newline = String.format("%n", new Object[0]);
/* 300 */     String indent = "    ";
/* 302 */     sb.append("GeoTools jars on classpath:");
/* 303 */     for (String jarName : getGeoToolsJars())
/* 304 */       sb.append(newline).append("    ").append(jarName); 
/* 307 */     return sb.toString();
/*     */   }
/*     */   
/*     */   private static List<String> getGeoToolsJars() {
/* 317 */     Pattern pattern = Pattern.compile(".*\\/" + getVersion() + "\\/(gt-.*jar$)");
/* 318 */     List<String> jarNames = new ArrayList<String>();
/* 320 */     String pathSep = System.getProperty("path.separator");
/* 321 */     String classpath = System.getProperty("java.class.path");
/* 322 */     StringTokenizer st = new StringTokenizer(classpath, pathSep);
/* 323 */     while (st.hasMoreTokens()) {
/* 324 */       String path = st.nextToken();
/* 325 */       Matcher matcher = pattern.matcher(path);
/* 326 */       if (matcher.find())
/* 327 */         jarNames.add(matcher.group(1)); 
/*     */     } 
/* 331 */     Collections.sort(jarNames);
/* 332 */     return jarNames;
/*     */   }
/*     */   
/*     */   public static Version getVersion() {
/* 341 */     return VERSION;
/*     */   }
/*     */   
/*     */   public static String getBuildRevision() {
/* 350 */     return BUILD_REVISION;
/*     */   }
/*     */   
/*     */   public static String getBuildTimestamp() {
/* 359 */     return BUILD_TIMESTAMP;
/*     */   }
/*     */   
/*     */   public static Properties getBuildProperties() {
/* 366 */     Properties props = new Properties();
/* 367 */     props.putAll(PROPS);
/* 368 */     return props;
/*     */   }
/*     */   
/*     */   public void setLoggerFactory(LoggerFactory factory) {
/* 388 */     Logging.GEOTOOLS.setLoggerFactory(factory);
/*     */   }
/*     */   
/*     */   public static void init(Hints hints) {
/* 436 */     Logging log = Logging.GEOTOOLS;
/*     */     try {
/* 438 */       log.setLoggerFactory("org.geotools.util.logging.CommonsLoggerFactory");
/* 439 */     } catch (ClassNotFoundException commonsException) {
/*     */       try {
/* 441 */         log.setLoggerFactory("org.geotools.util.logging.Log4JLoggerFactory");
/* 442 */       } catch (ClassNotFoundException log4jException) {}
/*     */     } 
/* 447 */     if (log.getLoggerFactory() == null)
/* 448 */       log.forceMonolineConsoleOutput(); 
/* 450 */     if (hints != null)
/* 451 */       Hints.putSystemDefault(hints); 
/*     */   }
/*     */   
/*     */   public static void init(InitialContext applicationContext) {
/* 466 */     synchronized (GeoTools.class) {
/* 467 */       context = applicationContext;
/*     */     } 
/* 469 */     fireConfigurationChanged();
/*     */   }
/*     */   
/*     */   static boolean scanForSystemHints(Map<RenderingHints.Key, Object> hints) {
/* 483 */     boolean changed = false;
/* 484 */     synchronized (BINDINGS) {
/* 485 */       for (Map.Entry<String, RenderingHints.Key> entry : BINDINGS.entrySet()) {
/* 486 */         String property, propertyKey = entry.getKey();
/*     */         try {
/* 489 */           property = System.getProperty(propertyKey);
/* 490 */         } catch (SecurityException e) {
/* 491 */           unexpectedException(e);
/*     */           continue;
/*     */         } 
/* 494 */         if (property != null) {
/* 502 */           Object old, value = property;
/* 503 */           RenderingHints.Key hintKey = entry.getValue();
/* 504 */           if (hintKey.getClass().equals(Hints.Key.class)) {
/* 505 */             Class<?> type = ((Hints.Key)hintKey).getValueClass();
/* 506 */             if (type.equals(Boolean.class)) {
/* 507 */               value = Boolean.valueOf(property);
/* 508 */             } else if (Number.class.isAssignableFrom(type)) {
/*     */               try {
/* 509 */                 value = Classes.valueOf(type, property);
/* 510 */               } catch (NumberFormatException e) {
/* 511 */                 unexpectedException(e);
/*     */                 continue;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */           try {
/* 517 */             old = hints.put(hintKey, value);
/* 518 */           } catch (IllegalArgumentException e) {
/* 520 */             unexpectedException(e);
/*     */             continue;
/*     */           } 
/* 523 */           if (!changed && !Utilities.equals(old, value))
/* 524 */             changed = true; 
/*     */         } 
/*     */       } 
/*     */     } 
/* 529 */     return changed;
/*     */   }
/*     */   
/*     */   private static void unexpectedException(Exception exception) {
/* 537 */     Logging.unexpectedException(Hints.class, "scanSystemProperties", exception);
/*     */   }
/*     */   
/*     */   public static Hints getDefaultHints() {
/* 563 */     return Hints.getDefaults(false);
/*     */   }
/*     */   
/*     */   public static Hints addDefaultHints(Hints hints) {
/* 572 */     Hints completed = getDefaultHints();
/* 573 */     if (hints != null)
/* 574 */       completed.add(hints); 
/* 576 */     return completed;
/*     */   }
/*     */   
/*     */   public static synchronized InitialContext getInitialContext(Hints hints) throws NamingException {
/* 593 */     if (context == null)
/* 594 */       context = new InitialContext(); 
/* 596 */     return context;
/*     */   }
/*     */   
/*     */   public static String fixName(String name) {
/* 613 */     return fixName(null, name, null);
/*     */   }
/*     */   
/*     */   public static String fixName(Context context, String name) {
/* 629 */     return (context != null) ? fixName(context, name, null) : name;
/*     */   }
/*     */   
/*     */   private static String fixName(Context context, String name, Hints hints) {
/* 638 */     String fixed = null;
/* 639 */     if (name != null) {
/* 640 */       StringTokenizer tokens = new StringTokenizer(name, ":/");
/* 641 */       while (tokens.hasMoreTokens()) {
/* 642 */         String part = tokens.nextToken();
/* 643 */         if (fixed == null) {
/* 644 */           fixed = part;
/*     */           continue;
/*     */         } 
/*     */         try {
/* 646 */           if (context == null)
/* 647 */             context = getInitialContext(hints); 
/* 649 */           fixed = context.composeName(fixed, part);
/* 650 */         } catch (NamingException e) {
/* 651 */           Logging.unexpectedException(GeoTools.class, "fixName", e);
/* 652 */           return name;
/*     */         } 
/*     */       } 
/*     */     } 
/* 656 */     return fixed;
/*     */   }
/*     */   
/*     */   public static void addFactoryIteratorProvider(FactoryIteratorProvider provider) {
/* 670 */     FactoryIteratorProviders.addFactoryIteratorProvider(provider);
/*     */   }
/*     */   
/*     */   public static void removeFactoryIteratorProvider(FactoryIteratorProvider provider) {
/* 681 */     FactoryIteratorProviders.removeFactoryIteratorProvider(provider);
/*     */   }
/*     */   
/*     */   public static void addChangeListener(ChangeListener listener) {
/* 691 */     removeChangeListener(listener);
/* 692 */     LISTENERS.add(ChangeListener.class, listener);
/*     */   }
/*     */   
/*     */   public static void removeChangeListener(ChangeListener listener) {
/* 702 */     LISTENERS.remove(ChangeListener.class, listener);
/*     */   }
/*     */   
/*     */   public static void fireConfigurationChanged() {
/* 709 */     ChangeEvent event = new ChangeEvent(GeoTools.class);
/* 710 */     Object[] listeners = LISTENERS.getListenerList();
/* 711 */     for (int i = 0; i < listeners.length; i += 2) {
/* 712 */       if (listeners[i] == ChangeListener.class)
/* 713 */         ((ChangeListener)listeners[i + 1]).stateChanged(event); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void addClassLoader(ClassLoader classLoader) {
/* 730 */     addedClassLoaders.add(classLoader);
/* 731 */     fireConfigurationChanged();
/*     */   }
/*     */   
/*     */   static Set<ClassLoader> getClassLoaders() {
/* 738 */     return addedClassLoaders;
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {
/* 748 */     Arguments arguments = new Arguments(args);
/* 749 */     args = arguments.getRemainingArguments(0);
/* 750 */     arguments.out.print("GeoTools version ");
/* 751 */     arguments.out.println(getVersion());
/* 752 */     Hints hints = getDefaultHints();
/* 753 */     if (hints != null && !hints.isEmpty())
/* 754 */       arguments.out.println(hints); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\factory\GeoTools.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
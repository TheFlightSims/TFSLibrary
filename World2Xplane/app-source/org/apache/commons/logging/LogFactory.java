/*      */ package org.apache.commons.logging;
/*      */ 
/*      */ import java.io.BufferedReader;
/*      */ import java.io.FileOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.InputStreamReader;
/*      */ import java.io.PrintStream;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.lang.reflect.InvocationTargetException;
/*      */ import java.lang.reflect.Method;
/*      */ import java.net.URL;
/*      */ import java.security.AccessController;
/*      */ import java.security.PrivilegedAction;
/*      */ import java.util.Enumeration;
/*      */ import java.util.Hashtable;
/*      */ import java.util.Properties;
/*      */ 
/*      */ public abstract class LogFactory {
/*      */   public static final String PRIORITY_KEY = "priority";
/*      */   
/*      */   public static final String TCCL_KEY = "use_tccl";
/*      */   
/*      */   public static final String FACTORY_PROPERTY = "org.apache.commons.logging.LogFactory";
/*      */   
/*      */   public static final String FACTORY_DEFAULT = "org.apache.commons.logging.impl.LogFactoryImpl";
/*      */   
/*      */   public static final String FACTORY_PROPERTIES = "commons-logging.properties";
/*      */   
/*      */   protected static final String SERVICE_ID = "META-INF/services/org.apache.commons.logging.LogFactory";
/*      */   
/*      */   public static final String DIAGNOSTICS_DEST_PROPERTY = "org.apache.commons.logging.diagnostics.dest";
/*      */   
/*  148 */   private static PrintStream diagnosticsStream = null;
/*      */   
/*      */   private static String diagnosticPrefix;
/*      */   
/*      */   public static final String HASHTABLE_IMPLEMENTATION_PROPERTY = "org.apache.commons.logging.LogFactory.HashtableImpl";
/*      */   
/*      */   private static final String WEAK_HASHTABLE_CLASSNAME = "org.apache.commons.logging.impl.WeakHashtable";
/*      */   
/*      */   private static ClassLoader thisClassLoader;
/*      */   
/*  309 */   protected static Hashtable factories = null;
/*      */   
/*  325 */   protected static LogFactory nullClassLoaderFactory = null;
/*      */   
/*      */   private static final Hashtable createFactoryStore() {
/*      */     String storeImplementationClass;
/*  343 */     Hashtable result = null;
/*      */     try {
/*  346 */       storeImplementationClass = getSystemProperty("org.apache.commons.logging.LogFactory.HashtableImpl", null);
/*  347 */     } catch (SecurityException ex) {
/*  350 */       storeImplementationClass = null;
/*      */     } 
/*  353 */     if (storeImplementationClass == null)
/*  354 */       storeImplementationClass = "org.apache.commons.logging.impl.WeakHashtable"; 
/*      */     try {
/*  357 */       Class implementationClass = Class.forName(storeImplementationClass);
/*  358 */       result = (Hashtable)implementationClass.newInstance();
/*  360 */     } catch (Throwable t) {
/*  362 */       if (!"org.apache.commons.logging.impl.WeakHashtable".equals(storeImplementationClass))
/*  364 */         if (isDiagnosticsEnabled()) {
/*  366 */           logDiagnostic("[ERROR] LogFactory: Load of custom hashtable failed");
/*      */         } else {
/*  370 */           System.err.println("[ERROR] LogFactory: Load of custom hashtable failed");
/*      */         }  
/*      */     } 
/*  374 */     if (result == null)
/*  375 */       result = new Hashtable(); 
/*  377 */     return result;
/*      */   }
/*      */   
/*      */   private static String trim(String src) {
/*  385 */     if (src == null)
/*  386 */       return null; 
/*  388 */     return src.trim();
/*      */   }
/*      */   
/*      */   public static LogFactory getFactory() throws LogConfigurationException {
/*  423 */     ClassLoader contextClassLoader = getContextClassLoaderInternal();
/*  425 */     if (contextClassLoader == null)
/*  429 */       if (isDiagnosticsEnabled())
/*  430 */         logDiagnostic("Context classloader is null.");  
/*  435 */     LogFactory factory = getCachedFactory(contextClassLoader);
/*  436 */     if (factory != null)
/*  437 */       return factory; 
/*  440 */     if (isDiagnosticsEnabled()) {
/*  441 */       logDiagnostic("[LOOKUP] LogFactory implementation requested for the first time for context classloader " + objectId(contextClassLoader));
/*  444 */       logHierarchy("[LOOKUP] ", contextClassLoader);
/*      */     } 
/*  457 */     Properties props = getConfigurationFile(contextClassLoader, "commons-logging.properties");
/*  461 */     ClassLoader baseClassLoader = contextClassLoader;
/*  462 */     if (props != null) {
/*  463 */       String useTCCLStr = props.getProperty("use_tccl");
/*  464 */       if (useTCCLStr != null)
/*  467 */         if (!Boolean.valueOf(useTCCLStr).booleanValue())
/*  475 */           baseClassLoader = thisClassLoader;  
/*      */     } 
/*  482 */     if (isDiagnosticsEnabled())
/*  483 */       logDiagnostic("[LOOKUP] Looking for system property [org.apache.commons.logging.LogFactory] to define the LogFactory subclass to use..."); 
/*      */     try {
/*  489 */       String factoryClass = getSystemProperty("org.apache.commons.logging.LogFactory", null);
/*  490 */       if (factoryClass != null) {
/*  491 */         if (isDiagnosticsEnabled())
/*  492 */           logDiagnostic("[LOOKUP] Creating an instance of LogFactory class '" + factoryClass + "' as specified by system property " + "org.apache.commons.logging.LogFactory"); 
/*  497 */         factory = newFactory(factoryClass, baseClassLoader, contextClassLoader);
/*  499 */       } else if (isDiagnosticsEnabled()) {
/*  500 */         logDiagnostic("[LOOKUP] No system property [org.apache.commons.logging.LogFactory] defined.");
/*      */       } 
/*  505 */     } catch (SecurityException e) {
/*  506 */       if (isDiagnosticsEnabled())
/*  507 */         logDiagnostic("[LOOKUP] A security exception occurred while trying to create an instance of the custom factory class: [" + trim(e.getMessage()) + "]. Trying alternative implementations..."); 
/*  514 */     } catch (RuntimeException e) {
/*  520 */       if (isDiagnosticsEnabled())
/*  521 */         logDiagnostic("[LOOKUP] An exception occurred while trying to create an instance of the custom factory class: [" + trim(e.getMessage()) + "] as specified by a system property."); 
/*  527 */       throw e;
/*      */     } 
/*  537 */     if (factory == null) {
/*  538 */       if (isDiagnosticsEnabled())
/*  539 */         logDiagnostic("[LOOKUP] Looking for a resource file of name [META-INF/services/org.apache.commons.logging.LogFactory] to define the LogFactory subclass to use..."); 
/*      */       try {
/*  544 */         InputStream is = getResourceAsStream(contextClassLoader, "META-INF/services/org.apache.commons.logging.LogFactory");
/*  547 */         if (is != null) {
/*      */           BufferedReader rd;
/*      */           try {
/*  552 */             rd = new BufferedReader(new InputStreamReader(is, "UTF-8"));
/*  553 */           } catch (UnsupportedEncodingException e) {
/*  554 */             rd = new BufferedReader(new InputStreamReader(is));
/*      */           } 
/*  557 */           String factoryClassName = rd.readLine();
/*  558 */           rd.close();
/*  560 */           if (factoryClassName != null && !"".equals(factoryClassName)) {
/*  562 */             if (isDiagnosticsEnabled())
/*  563 */               logDiagnostic("[LOOKUP]  Creating an instance of LogFactory class " + factoryClassName + " as specified by file '" + "META-INF/services/org.apache.commons.logging.LogFactory" + "' which was present in the path of the context" + " classloader."); 
/*  569 */             factory = newFactory(factoryClassName, baseClassLoader, contextClassLoader);
/*      */           } 
/*  573 */         } else if (isDiagnosticsEnabled()) {
/*  574 */           logDiagnostic("[LOOKUP] No resource file with name 'META-INF/services/org.apache.commons.logging.LogFactory' found.");
/*      */         } 
/*  579 */       } catch (Exception ex) {
/*  583 */         if (isDiagnosticsEnabled())
/*  584 */           logDiagnostic("[LOOKUP] A security exception occurred while trying to create an instance of the custom factory class: [" + trim(ex.getMessage()) + "]. Trying alternative implementations..."); 
/*      */       } 
/*      */     } 
/*  597 */     if (factory == null)
/*  598 */       if (props != null) {
/*  599 */         if (isDiagnosticsEnabled())
/*  600 */           logDiagnostic("[LOOKUP] Looking in properties file for entry with key 'org.apache.commons.logging.LogFactory' to define the LogFactory subclass to use..."); 
/*  605 */         String factoryClass = props.getProperty("org.apache.commons.logging.LogFactory");
/*  606 */         if (factoryClass != null) {
/*  607 */           if (isDiagnosticsEnabled())
/*  608 */             logDiagnostic("[LOOKUP] Properties file specifies LogFactory subclass '" + factoryClass + "'"); 
/*  612 */           factory = newFactory(factoryClass, baseClassLoader, contextClassLoader);
/*  616 */         } else if (isDiagnosticsEnabled()) {
/*  617 */           logDiagnostic("[LOOKUP] Properties file has no entry specifying LogFactory subclass.");
/*      */         } 
/*  622 */       } else if (isDiagnosticsEnabled()) {
/*  623 */         logDiagnostic("[LOOKUP] No properties file available to determine LogFactory subclass from..");
/*      */       }  
/*  633 */     if (factory == null) {
/*  634 */       if (isDiagnosticsEnabled())
/*  635 */         logDiagnostic("[LOOKUP] Loading the default LogFactory implementation 'org.apache.commons.logging.impl.LogFactoryImpl' via the same classloader that loaded this LogFactory class (ie not looking in the context classloader)."); 
/*  650 */       factory = newFactory("org.apache.commons.logging.impl.LogFactoryImpl", thisClassLoader, contextClassLoader);
/*      */     } 
/*  653 */     if (factory != null) {
/*  657 */       cacheFactory(contextClassLoader, factory);
/*  659 */       if (props != null) {
/*  660 */         Enumeration names = props.propertyNames();
/*  661 */         while (names.hasMoreElements()) {
/*  662 */           String name = (String)names.nextElement();
/*  663 */           String value = props.getProperty(name);
/*  664 */           factory.setAttribute(name, value);
/*      */         } 
/*      */       } 
/*      */     } 
/*  669 */     return factory;
/*      */   }
/*      */   
/*      */   public static Log getLog(Class clazz) throws LogConfigurationException {
/*  685 */     return getFactory().getInstance(clazz);
/*      */   }
/*      */   
/*      */   public static Log getLog(String name) throws LogConfigurationException {
/*  704 */     return getFactory().getInstance(name);
/*      */   }
/*      */   
/*      */   public static void release(ClassLoader classLoader) {
/*  719 */     if (isDiagnosticsEnabled())
/*  720 */       logDiagnostic("Releasing factory for classloader " + objectId(classLoader)); 
/*  722 */     synchronized (factories) {
/*  723 */       if (classLoader == null) {
/*  724 */         if (nullClassLoaderFactory != null) {
/*  725 */           nullClassLoaderFactory.release();
/*  726 */           nullClassLoaderFactory = null;
/*      */         } 
/*      */       } else {
/*  729 */         LogFactory factory = (LogFactory)factories.get(classLoader);
/*  730 */         if (factory != null) {
/*  731 */           factory.release();
/*  732 */           factories.remove(classLoader);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void releaseAll() {
/*  750 */     if (isDiagnosticsEnabled())
/*  751 */       logDiagnostic("Releasing factory for all classloaders."); 
/*  753 */     synchronized (factories) {
/*  754 */       Enumeration elements = factories.elements();
/*  755 */       while (elements.hasMoreElements()) {
/*  756 */         LogFactory element = elements.nextElement();
/*  757 */         element.release();
/*      */       } 
/*  759 */       factories.clear();
/*  761 */       if (nullClassLoaderFactory != null) {
/*  762 */         nullClassLoaderFactory.release();
/*  763 */         nullClassLoaderFactory = null;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   protected static ClassLoader getClassLoader(Class clazz) {
/*      */     try {
/*  801 */       return clazz.getClassLoader();
/*  802 */     } catch (SecurityException ex) {
/*  803 */       if (isDiagnosticsEnabled())
/*  804 */         logDiagnostic("Unable to get classloader for class '" + clazz + "' due to security restrictions - " + ex.getMessage()); 
/*  808 */       throw ex;
/*      */     } 
/*      */   }
/*      */   
/*      */   protected static ClassLoader getContextClassLoader() throws LogConfigurationException {
/*  836 */     return directGetContextClassLoader();
/*      */   }
/*      */   
/*      */   private static ClassLoader getContextClassLoaderInternal() throws LogConfigurationException {
/*  859 */     return AccessController.<ClassLoader>doPrivileged(new PrivilegedAction() {
/*      */           public Object run() {
/*  862 */             return LogFactory.directGetContextClassLoader();
/*      */           }
/*      */         });
/*      */   }
/*      */   
/*      */   protected static ClassLoader directGetContextClassLoader() throws LogConfigurationException {
/*  892 */     ClassLoader classLoader = null;
/*      */     try {
/*  896 */       Method method = Thread.class.getMethod("getContextClassLoader", (Class[])null);
/*      */       try {
/*  901 */         classLoader = (ClassLoader)method.invoke(Thread.currentThread(), (Object[])null);
/*  903 */       } catch (IllegalAccessException e) {
/*  904 */         throw new LogConfigurationException("Unexpected IllegalAccessException", e);
/*  906 */       } catch (InvocationTargetException e) {
/*  923 */         if (!(e.getTargetException() instanceof SecurityException))
/*  928 */           throw new LogConfigurationException("Unexpected InvocationTargetException", e.getTargetException()); 
/*      */       } 
/*  932 */     } catch (NoSuchMethodException e) {
/*  934 */       classLoader = getClassLoader(LogFactory.class);
/*      */     } 
/*  951 */     return classLoader;
/*      */   }
/*      */   
/*      */   private static LogFactory getCachedFactory(ClassLoader contextClassLoader) {
/*  970 */     LogFactory factory = null;
/*  972 */     if (contextClassLoader == null) {
/*  977 */       factory = nullClassLoaderFactory;
/*      */     } else {
/*  979 */       factory = (LogFactory)factories.get(contextClassLoader);
/*      */     } 
/*  982 */     return factory;
/*      */   }
/*      */   
/*      */   private static void cacheFactory(ClassLoader classLoader, LogFactory factory) {
/* 1000 */     if (factory != null)
/* 1001 */       if (classLoader == null) {
/* 1002 */         nullClassLoaderFactory = factory;
/*      */       } else {
/* 1004 */         factories.put(classLoader, factory);
/*      */       }  
/*      */   }
/*      */   
/*      */   protected static LogFactory newFactory(String factoryClass, ClassLoader classLoader, ClassLoader contextClassLoader) throws LogConfigurationException {
/* 1062 */     Object result = AccessController.doPrivileged(new PrivilegedAction(factoryClass, classLoader) {
/*      */           private final String val$factoryClass;
/*      */           
/*      */           private final ClassLoader val$classLoader;
/*      */           
/*      */           public Object run() {
/* 1065 */             return LogFactory.createFactory(this.val$factoryClass, this.val$classLoader);
/*      */           }
/*      */         });
/* 1069 */     if (result instanceof LogConfigurationException) {
/* 1070 */       LogConfigurationException ex = (LogConfigurationException)result;
/* 1071 */       if (isDiagnosticsEnabled())
/* 1072 */         logDiagnostic("An error occurred while loading the factory class:" + ex.getMessage()); 
/* 1076 */       throw ex;
/*      */     } 
/* 1078 */     if (isDiagnosticsEnabled())
/* 1079 */       logDiagnostic("Created object " + objectId(result) + " to manage classloader " + objectId(contextClassLoader)); 
/* 1083 */     return (LogFactory)result;
/*      */   }
/*      */   
/*      */   protected static LogFactory newFactory(String factoryClass, ClassLoader classLoader) {
/* 1103 */     return newFactory(factoryClass, classLoader, null);
/*      */   }
/*      */   
/*      */   protected static Object createFactory(String factoryClass, ClassLoader classLoader) {
/* 1123 */     Class logFactoryClass = null;
/*      */     try {
/* 1125 */       if (classLoader != null)
/*      */         try {
/* 1131 */           logFactoryClass = classLoader.loadClass(factoryClass);
/* 1132 */           if (LogFactory.class.isAssignableFrom(logFactoryClass)) {
/* 1133 */             if (isDiagnosticsEnabled())
/* 1134 */               logDiagnostic("Loaded class " + logFactoryClass.getName() + " from classloader " + objectId(classLoader)); 
/* 1150 */           } else if (isDiagnosticsEnabled()) {
/* 1151 */             logDiagnostic("Factory class " + logFactoryClass.getName() + " loaded from classloader " + objectId(logFactoryClass.getClassLoader()) + " does not extend '" + LogFactory.class.getName() + "' as loaded by this classloader.");
/* 1156 */             logHierarchy("[BAD CL TREE] ", classLoader);
/*      */           } 
/* 1160 */           return logFactoryClass.newInstance();
/* 1162 */         } catch (ClassNotFoundException ex) {
/* 1163 */           if (classLoader == thisClassLoader) {
/* 1165 */             if (isDiagnosticsEnabled())
/* 1166 */               logDiagnostic("Unable to locate any class called '" + factoryClass + "' via classloader " + objectId(classLoader)); 
/* 1170 */             throw ex;
/*      */           } 
/* 1173 */         } catch (NoClassDefFoundError e) {
/* 1174 */           if (classLoader == thisClassLoader) {
/* 1176 */             if (isDiagnosticsEnabled())
/* 1177 */               logDiagnostic("Class '" + factoryClass + "' cannot be loaded" + " via classloader " + objectId(classLoader) + " - it depends on some other class that cannot" + " be found."); 
/* 1183 */             throw e;
/*      */           } 
/* 1186 */         } catch (ClassCastException e) {
/* 1187 */           if (classLoader == thisClassLoader) {
/* 1193 */             boolean implementsLogFactory = implementsLogFactory(logFactoryClass);
/* 1200 */             String msg = "The application has specified that a custom LogFactory implementation should be used but Class '" + factoryClass + "' cannot be converted to '" + LogFactory.class.getName() + "'. ";
/* 1204 */             if (implementsLogFactory) {
/* 1205 */               msg = msg + "The conflict is caused by the presence of multiple LogFactory classes in incompatible classloaders. " + "Background can be found in http://commons.apache.org/logging/tech.html. " + "If you have not explicitly specified a custom LogFactory then it is likely that " + "the container has set one without your knowledge. " + "In this case, consider using the commons-logging-adapters.jar file or " + "specifying the standard LogFactory from the command line. ";
/*      */             } else {
/* 1212 */               msg = msg + "Please check the custom implementation. ";
/*      */             } 
/* 1214 */             msg = msg + "Help can be found @http://commons.apache.org/logging/troubleshooting.html.";
/* 1216 */             if (isDiagnosticsEnabled())
/* 1217 */               logDiagnostic(msg); 
/* 1220 */             ClassCastException ex = new ClassCastException(msg);
/* 1221 */             throw ex;
/*      */           } 
/*      */         }  
/* 1255 */       if (isDiagnosticsEnabled())
/* 1256 */         logDiagnostic("Unable to load factory class via classloader " + objectId(classLoader) + " - trying the classloader associated with this LogFactory."); 
/* 1261 */       logFactoryClass = Class.forName(factoryClass);
/* 1262 */       return logFactoryClass.newInstance();
/* 1263 */     } catch (Exception e) {
/* 1265 */       if (isDiagnosticsEnabled())
/* 1266 */         logDiagnostic("Unable to create LogFactory instance."); 
/* 1268 */       if (logFactoryClass != null && !LogFactory.class.isAssignableFrom(logFactoryClass))
/* 1271 */         return new LogConfigurationException("The chosen LogFactory implementation does not extend LogFactory. Please check your configuration.", e); 
/* 1276 */       return new LogConfigurationException(e);
/*      */     } 
/*      */   }
/*      */   
/*      */   private static boolean implementsLogFactory(Class logFactoryClass) {
/* 1293 */     boolean implementsLogFactory = false;
/* 1294 */     if (logFactoryClass != null)
/*      */       try {
/* 1296 */         ClassLoader logFactoryClassLoader = logFactoryClass.getClassLoader();
/* 1297 */         if (logFactoryClassLoader == null) {
/* 1298 */           logDiagnostic("[CUSTOM LOG FACTORY] was loaded by the boot classloader");
/*      */         } else {
/* 1300 */           logHierarchy("[CUSTOM LOG FACTORY] ", logFactoryClassLoader);
/* 1301 */           Class factoryFromCustomLoader = Class.forName("org.apache.commons.logging.LogFactory", false, logFactoryClassLoader);
/* 1303 */           implementsLogFactory = factoryFromCustomLoader.isAssignableFrom(logFactoryClass);
/* 1304 */           if (implementsLogFactory) {
/* 1305 */             logDiagnostic("[CUSTOM LOG FACTORY] " + logFactoryClass.getName() + " implements LogFactory but was loaded by an incompatible classloader.");
/*      */           } else {
/* 1308 */             logDiagnostic("[CUSTOM LOG FACTORY] " + logFactoryClass.getName() + " does not implement LogFactory.");
/*      */           } 
/*      */         } 
/* 1312 */       } catch (SecurityException e) {
/* 1318 */         logDiagnostic("[CUSTOM LOG FACTORY] SecurityException thrown whilst trying to determine whether the compatibility was caused by a classloader conflict: " + e.getMessage());
/* 1321 */       } catch (LinkageError e) {
/* 1328 */         logDiagnostic("[CUSTOM LOG FACTORY] LinkageError thrown whilst trying to determine whether the compatibility was caused by a classloader conflict: " + e.getMessage());
/* 1331 */       } catch (ClassNotFoundException e) {
/* 1339 */         logDiagnostic("[CUSTOM LOG FACTORY] LogFactory class cannot be loaded by classloader which loaded the custom LogFactory implementation. Is the custom factory in the right classloader?");
/*      */       }  
/* 1343 */     return implementsLogFactory;
/*      */   }
/*      */   
/*      */   private static InputStream getResourceAsStream(ClassLoader loader, String name) {
/* 1355 */     return AccessController.<InputStream>doPrivileged(new PrivilegedAction(loader, name) {
/*      */           private final ClassLoader val$loader;
/*      */           
/*      */           private final String val$name;
/*      */           
/*      */           public Object run() {
/* 1358 */             if (this.val$loader != null)
/* 1359 */               return this.val$loader.getResourceAsStream(this.val$name); 
/* 1361 */             return ClassLoader.getSystemResourceAsStream(this.val$name);
/*      */           }
/*      */         });
/*      */   }
/*      */   
/*      */   private static Enumeration getResources(ClassLoader loader, String name) {
/* 1383 */     PrivilegedAction action = new PrivilegedAction(loader, name) {
/*      */         private final ClassLoader val$loader;
/*      */         
/*      */         private final String val$name;
/*      */         
/*      */         public Object run() {
/*      */           try {
/* 1387 */             if (this.val$loader != null)
/* 1388 */               return this.val$loader.getResources(this.val$name); 
/* 1390 */             return ClassLoader.getSystemResources(this.val$name);
/* 1392 */           } catch (IOException e) {
/* 1393 */             if (LogFactory.isDiagnosticsEnabled())
/* 1394 */               LogFactory.logDiagnostic("Exception while trying to find configuration file " + this.val$name + ":" + e.getMessage()); 
/* 1398 */             return null;
/* 1399 */           } catch (NoSuchMethodError e) {
/* 1403 */             return null;
/*      */           } 
/*      */         }
/*      */       };
/* 1407 */     Object result = AccessController.doPrivileged(action);
/* 1408 */     return (Enumeration)result;
/*      */   }
/*      */   
/*      */   private static Properties getProperties(URL url) {
/* 1420 */     PrivilegedAction action = new PrivilegedAction(url) {
/*      */         private final URL val$url;
/*      */         
/*      */         public Object run() {
/*      */           try {
/* 1424 */             InputStream stream = this.val$url.openStream();
/* 1425 */             if (stream != null) {
/* 1426 */               Properties props = new Properties();
/* 1427 */               props.load(stream);
/* 1428 */               stream.close();
/* 1429 */               return props;
/*      */             } 
/* 1431 */           } catch (IOException e) {
/* 1432 */             if (LogFactory.isDiagnosticsEnabled())
/* 1433 */               LogFactory.logDiagnostic("Unable to read URL " + this.val$url); 
/*      */           } 
/* 1437 */           return null;
/*      */         }
/*      */       };
/* 1440 */     return AccessController.<Properties>doPrivileged(action);
/*      */   }
/*      */   
/*      */   private static final Properties getConfigurationFile(ClassLoader classLoader, String fileName) {
/* 1465 */     Properties props = null;
/* 1466 */     double priority = 0.0D;
/* 1467 */     URL propsUrl = null;
/*      */     try {
/* 1469 */       Enumeration urls = getResources(classLoader, fileName);
/* 1471 */       if (urls == null)
/* 1472 */         return null; 
/* 1475 */       while (urls.hasMoreElements()) {
/* 1476 */         URL url = urls.nextElement();
/* 1478 */         Properties newProps = getProperties(url);
/* 1479 */         if (newProps != null) {
/* 1480 */           if (props == null) {
/* 1481 */             propsUrl = url;
/* 1482 */             props = newProps;
/* 1483 */             String priorityStr = props.getProperty("priority");
/* 1484 */             priority = 0.0D;
/* 1485 */             if (priorityStr != null)
/* 1486 */               priority = Double.parseDouble(priorityStr); 
/* 1489 */             if (isDiagnosticsEnabled())
/* 1490 */               logDiagnostic("[LOOKUP] Properties file found at '" + url + "'" + " with priority " + priority); 
/*      */             continue;
/*      */           } 
/* 1495 */           String newPriorityStr = newProps.getProperty("priority");
/* 1496 */           double newPriority = 0.0D;
/* 1497 */           if (newPriorityStr != null)
/* 1498 */             newPriority = Double.parseDouble(newPriorityStr); 
/* 1501 */           if (newPriority > priority) {
/* 1502 */             if (isDiagnosticsEnabled())
/* 1503 */               logDiagnostic("[LOOKUP] Properties file at '" + url + "'" + " with priority " + newPriority + " overrides file at '" + propsUrl + "'" + " with priority " + priority); 
/* 1510 */             propsUrl = url;
/* 1511 */             props = newProps;
/* 1512 */             priority = newPriority;
/*      */             continue;
/*      */           } 
/* 1514 */           if (isDiagnosticsEnabled())
/* 1515 */             logDiagnostic("[LOOKUP] Properties file at '" + url + "'" + " with priority " + newPriority + " does not override file at '" + propsUrl + "'" + " with priority " + priority); 
/*      */         } 
/*      */       } 
/* 1526 */     } catch (SecurityException e) {
/* 1527 */       if (isDiagnosticsEnabled())
/* 1528 */         logDiagnostic("SecurityException thrown while trying to find/read config files."); 
/*      */     } 
/* 1532 */     if (isDiagnosticsEnabled())
/* 1533 */       if (props == null) {
/* 1534 */         logDiagnostic("[LOOKUP] No properties file of name '" + fileName + "' found.");
/*      */       } else {
/* 1538 */         logDiagnostic("[LOOKUP] Properties file of name '" + fileName + "' found at '" + propsUrl + '"');
/*      */       }  
/* 1544 */     return props;
/*      */   }
/*      */   
/*      */   private static String getSystemProperty(String key, String def) throws SecurityException {
/* 1558 */     return AccessController.<String>doPrivileged(new PrivilegedAction(key, def) {
/*      */           private final String val$key;
/*      */           
/*      */           private final String val$def;
/*      */           
/*      */           public Object run() {
/* 1561 */             return System.getProperty(this.val$key, this.val$def);
/*      */           }
/*      */         });
/*      */   }
/*      */   
/*      */   private static void initDiagnostics() {
/*      */     String dest, classLoaderName;
/*      */     try {
/* 1575 */       dest = getSystemProperty("org.apache.commons.logging.diagnostics.dest", null);
/* 1576 */       if (dest == null)
/*      */         return; 
/* 1579 */     } catch (SecurityException ex) {
/*      */       return;
/*      */     } 
/* 1585 */     if (dest.equals("STDOUT")) {
/* 1586 */       diagnosticsStream = System.out;
/* 1587 */     } else if (dest.equals("STDERR")) {
/* 1588 */       diagnosticsStream = System.err;
/*      */     } else {
/*      */       try {
/* 1592 */         FileOutputStream fos = new FileOutputStream(dest, true);
/* 1593 */         diagnosticsStream = new PrintStream(fos);
/* 1594 */       } catch (IOException ex) {
/*      */         return;
/*      */       } 
/*      */     } 
/*      */     try {
/* 1611 */       ClassLoader classLoader = thisClassLoader;
/* 1612 */       if (thisClassLoader == null) {
/* 1613 */         classLoaderName = "BOOTLOADER";
/*      */       } else {
/* 1615 */         classLoaderName = objectId(classLoader);
/*      */       } 
/* 1617 */     } catch (SecurityException e) {
/* 1618 */       classLoaderName = "UNKNOWN";
/*      */     } 
/* 1620 */     diagnosticPrefix = "[LogFactory from " + classLoaderName + "] ";
/*      */   }
/*      */   
/*      */   protected static boolean isDiagnosticsEnabled() {
/* 1633 */     return (diagnosticsStream != null);
/*      */   }
/*      */   
/*      */   private static final void logDiagnostic(String msg) {
/* 1655 */     if (diagnosticsStream != null) {
/* 1656 */       diagnosticsStream.print(diagnosticPrefix);
/* 1657 */       diagnosticsStream.println(msg);
/* 1658 */       diagnosticsStream.flush();
/*      */     } 
/*      */   }
/*      */   
/*      */   protected static final void logRawDiagnostic(String msg) {
/* 1669 */     if (diagnosticsStream != null) {
/* 1670 */       diagnosticsStream.println(msg);
/* 1671 */       diagnosticsStream.flush();
/*      */     } 
/*      */   }
/*      */   
/*      */   private static void logClassLoaderEnvironment(Class clazz) {
/*      */     ClassLoader classLoader;
/* 1693 */     if (!isDiagnosticsEnabled())
/*      */       return; 
/*      */     try {
/* 1701 */       logDiagnostic("[ENV] Extension directories (java.ext.dir): " + System.getProperty("java.ext.dir"));
/* 1702 */       logDiagnostic("[ENV] Application classpath (java.class.path): " + System.getProperty("java.class.path"));
/* 1703 */     } catch (SecurityException ex) {
/* 1704 */       logDiagnostic("[ENV] Security setting prevent interrogation of system classpaths.");
/*      */     } 
/* 1707 */     String className = clazz.getName();
/*      */     try {
/* 1711 */       classLoader = getClassLoader(clazz);
/* 1712 */     } catch (SecurityException ex) {
/* 1714 */       logDiagnostic("[ENV] Security forbids determining the classloader for " + className);
/*      */       return;
/*      */     } 
/* 1719 */     logDiagnostic("[ENV] Class " + className + " was loaded via classloader " + objectId(classLoader));
/* 1722 */     logHierarchy("[ENV] Ancestry of classloader which loaded " + className + " is ", classLoader);
/*      */   }
/*      */   
/*      */   private static void logHierarchy(String prefix, ClassLoader classLoader) {
/*      */     ClassLoader systemClassLoader;
/* 1733 */     if (!isDiagnosticsEnabled())
/*      */       return; 
/* 1737 */     if (classLoader != null) {
/* 1738 */       String classLoaderString = classLoader.toString();
/* 1739 */       logDiagnostic(prefix + objectId(classLoader) + " == '" + classLoaderString + "'");
/*      */     } 
/*      */     try {
/* 1743 */       systemClassLoader = ClassLoader.getSystemClassLoader();
/* 1744 */     } catch (SecurityException ex) {
/* 1745 */       logDiagnostic(prefix + "Security forbids determining the system classloader.");
/*      */       return;
/*      */     } 
/* 1749 */     if (classLoader != null) {
/* 1750 */       StringBuffer buf = new StringBuffer(prefix + "ClassLoader tree:");
/*      */       while (true) {
/* 1752 */         buf.append(objectId(classLoader));
/* 1753 */         if (classLoader == systemClassLoader)
/* 1754 */           buf.append(" (SYSTEM) "); 
/*      */         try {
/* 1758 */           classLoader = classLoader.getParent();
/* 1759 */         } catch (SecurityException ex) {
/* 1760 */           buf.append(" --> SECRET");
/*      */           break;
/*      */         } 
/* 1764 */         buf.append(" --> ");
/* 1765 */         if (classLoader == null) {
/* 1766 */           buf.append("BOOT");
/*      */           break;
/*      */         } 
/*      */       } 
/* 1770 */       logDiagnostic(buf.toString());
/*      */     } 
/*      */   }
/*      */   
/*      */   public static String objectId(Object o) {
/* 1787 */     if (o == null)
/* 1788 */       return "null"; 
/* 1790 */     return o.getClass().getName() + "@" + System.identityHashCode(o);
/*      */   }
/*      */   
/*      */   static {
/* 1816 */     thisClassLoader = getClassLoader(LogFactory.class);
/* 1817 */     initDiagnostics();
/* 1818 */     logClassLoaderEnvironment(LogFactory.class);
/* 1819 */     factories = createFactoryStore();
/* 1820 */     if (isDiagnosticsEnabled())
/* 1821 */       logDiagnostic("BOOTSTRAP COMPLETED"); 
/*      */   }
/*      */   
/*      */   public abstract Object getAttribute(String paramString);
/*      */   
/*      */   public abstract String[] getAttributeNames();
/*      */   
/*      */   public abstract Log getInstance(Class paramClass) throws LogConfigurationException;
/*      */   
/*      */   public abstract Log getInstance(String paramString) throws LogConfigurationException;
/*      */   
/*      */   public abstract void release();
/*      */   
/*      */   public abstract void removeAttribute(String paramString);
/*      */   
/*      */   public abstract void setAttribute(String paramString, Object paramObject);
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\logging\LogFactory.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */
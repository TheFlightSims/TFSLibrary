/*     */ package org.geotools.util.logging;
/*     */ 
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.Arrays;
/*     */ import java.util.Comparator;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.LogRecord;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.resources.Classes;
/*     */ import org.geotools.resources.XArray;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ 
/*     */ public final class Logging {
/*  59 */   private static final Comparator<Object> COMPARATOR = new Comparator() {
/*     */       public int compare(Object o1, Object o2) {
/*  61 */         String n1 = (o1 instanceof Logging) ? ((Logging)o1).name : o1.toString();
/*  62 */         String n2 = (o2 instanceof Logging) ? ((Logging)o2).name : o2.toString();
/*  63 */         return n1.compareTo(n2);
/*     */       }
/*     */     };
/*     */   
/*  70 */   private static final Logging[] EMPTY = new Logging[0];
/*     */   
/*  75 */   public static final Logging ALL = new Logging();
/*     */   
/*  81 */   public static final Logging GEOTOOLS = getLogging("org.geotools");
/*     */   
/*     */   final String name;
/*     */   
/*  95 */   private Logging[] children = EMPTY;
/*     */   
/*     */   private LoggerFactory factory;
/*     */   
/*     */   private static boolean sameLoggerFactory = true;
/*     */   
/*     */   private Logging() {
/* 115 */     this.name = "";
/*     */   }
/*     */   
/*     */   private Logging(Logging parent, String name) {
/* 126 */     this.name = name;
/* 127 */     this.factory = parent.factory;
/* 128 */     assert name.startsWith(parent.name) : name;
/*     */   }
/*     */   
/*     */   public static Logger getLogger(Class<?> classe) {
/* 141 */     String name = classe.getName();
/* 142 */     int separator = name.lastIndexOf('.');
/* 143 */     name = (separator >= 1) ? name.substring(0, separator) : "";
/* 144 */     return getLogger(name);
/*     */   }
/*     */   
/*     */   public static Logger getLogger(String name) {
/* 161 */     synchronized (EMPTY) {
/* 162 */       Logging logging = sameLoggerFactory ? ALL : getLogging(name, false);
/* 163 */       if (logging != null) {
/* 164 */         LoggerFactory factory = logging.factory;
/* 165 */         assert (getLogging(name, false)).factory == factory : name;
/* 166 */         if (factory != null) {
/* 167 */           Logger logger = factory.getLogger(name);
/* 168 */           if (logger != null)
/* 169 */             return logger; 
/*     */         } 
/*     */       } 
/*     */     } 
/* 174 */     return Logger.getLogger(name);
/*     */   }
/*     */   
/*     */   public static Logging getLogging(String name) {
/* 189 */     synchronized (EMPTY) {
/* 190 */       return getLogging(name, true);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static Logging getLogging(String base, boolean create) {
/* 203 */     assert Thread.holdsLock(EMPTY);
/* 204 */     Logging logging = ALL;
/* 205 */     if (base.length() != 0) {
/* 206 */       int offset = 0;
/*     */       do {
/* 208 */         Logging[] children = logging.children;
/* 209 */         offset = base.indexOf('.', offset);
/* 210 */         String name = (offset >= 0) ? base.substring(0, offset) : base;
/* 211 */         int i = Arrays.binarySearch((Object[])children, name, COMPARATOR);
/* 212 */         if (i < 0) {
/* 214 */           if (!create)
/*     */             break; 
/* 219 */           i ^= 0xFFFFFFFF;
/* 220 */           children = (Logging[])XArray.insert((Object[])children, i, 1);
/* 221 */           children[i] = new Logging(logging, name);
/* 222 */           logging.children = children;
/*     */         } 
/* 224 */         logging = children[i];
/* 225 */       } while (++offset != 0);
/*     */     } 
/* 227 */     return logging;
/*     */   }
/*     */   
/*     */   final Logging[] getChildren() {
/* 234 */     synchronized (EMPTY) {
/* 235 */       return (Logging[])this.children.clone();
/*     */     } 
/*     */   }
/*     */   
/*     */   public LoggerFactory getLoggerFactory() {
/* 245 */     synchronized (EMPTY) {
/* 246 */       return this.factory;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setLoggerFactory(LoggerFactory factory) {
/* 256 */     synchronized (EMPTY) {
/* 257 */       this.factory = factory;
/* 258 */       for (int i = 0; i < this.children.length; i++)
/* 259 */         this.children[i].setLoggerFactory(factory); 
/* 261 */       sameLoggerFactory = sameLoggerFactory(ALL.children, ALL.factory);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static boolean sameLoggerFactory(Logging[] children, LoggerFactory factory) {
/* 270 */     assert Thread.holdsLock(EMPTY);
/* 271 */     for (int i = 0; i < children.length; i++) {
/* 272 */       Logging logging = children[i];
/* 273 */       if (logging.factory != factory || !sameLoggerFactory(logging.children, factory))
/* 274 */         return false; 
/*     */     } 
/* 277 */     return true;
/*     */   }
/*     */   
/*     */   public void setLoggerFactory(String className) throws ClassNotFoundException, IllegalArgumentException {
/*     */     LoggerFactory factory;
/* 295 */     if (className == null) {
/* 296 */       factory = null;
/*     */     } else {
/*     */       Class<?> factoryClass;
/*     */       try {
/* 300 */         factoryClass = Class.forName(className);
/* 301 */       } catch (NoClassDefFoundError error) {
/* 302 */         throw factoryNotFound(className, error);
/*     */       } 
/* 304 */       if (!LoggerFactory.class.isAssignableFrom(factoryClass))
/* 305 */         throw new IllegalArgumentException(Errors.format(61, factoryClass, LoggerFactory.class)); 
/*     */       try {
/* 309 */         Method method = factoryClass.getMethod("getInstance", (Class[])null);
/* 310 */         factory = LoggerFactory.class.cast(method.invoke(null, (Object[])null));
/* 311 */       } catch (Exception e) {
/* 318 */         Throwable cause = e;
/* 319 */         if (e instanceof java.lang.reflect.InvocationTargetException)
/* 320 */           cause = e.getCause(); 
/* 322 */         if (cause instanceof ClassNotFoundException)
/* 323 */           throw (ClassNotFoundException)e; 
/* 325 */         if (cause instanceof NoClassDefFoundError)
/* 326 */           throw factoryNotFound(className, (NoClassDefFoundError)cause); 
/* 328 */         throw new IllegalArgumentException(Errors.format(23, className, cause));
/*     */       } 
/*     */     } 
/* 332 */     setLoggerFactory(factory);
/*     */   }
/*     */   
/*     */   private static ClassNotFoundException factoryNotFound(String name, NoClassDefFoundError error) {
/* 339 */     return new ClassNotFoundException(Errors.format(49, name), error);
/*     */   }
/*     */   
/*     */   public void forceMonolineConsoleOutput() {
/* 353 */     forceMonolineConsoleOutput(null);
/*     */   }
/*     */   
/*     */   public void forceMonolineConsoleOutput(Level level) {
/* 368 */     Logger logger = Logger.getLogger(this.name);
/* 369 */     synchronized (EMPTY) {
/* 370 */       MonolineFormatter f = MonolineFormatter.configureConsoleHandler(logger, level);
/* 371 */       if (f.getSourceFormat() == null)
/* 374 */         f.setSourceFormat("class:short"); 
/* 376 */       if (level != null) {
/* 379 */         Level current = logger.getLevel();
/* 380 */         if (current == null || current.intValue() > level.intValue())
/* 381 */           logger.setLevel(level); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean unexpectedException(Logger logger, Throwable error) {
/* 404 */     return unexpectedException(logger, null, null, error, Level.WARNING);
/*     */   }
/*     */   
/*     */   public static boolean unexpectedException(Logger logger, Class<?> classe, String method, Throwable error) {
/* 437 */     String classname = (classe != null) ? classe.getName() : null;
/* 438 */     return unexpectedException(logger, classname, method, error, Level.WARNING);
/*     */   }
/*     */   
/*     */   public static boolean unexpectedException(String paquet, Class<?> classe, String method, Throwable error) {
/* 462 */     Logger logger = (paquet != null) ? getLogger(paquet) : null;
/* 463 */     return unexpectedException(logger, classe, method, error);
/*     */   }
/*     */   
/*     */   public static boolean unexpectedException(Class<?> classe, String method, Throwable error) {
/* 479 */     return unexpectedException((Logger)null, classe, method, error);
/*     */   }
/*     */   
/*     */   private static boolean unexpectedException(Logger logger, String classe, String method, Throwable error, Level level) {
/* 499 */     if (error == null)
/* 500 */       return false; 
/* 502 */     if (logger == null && classe != null) {
/* 503 */       int separator = classe.lastIndexOf('.');
/* 504 */       String paquet = (separator >= 1) ? classe.substring(0, separator - 1) : "";
/* 505 */       logger = getLogger(paquet);
/*     */     } 
/* 507 */     if (logger != null && !logger.isLoggable(level))
/* 508 */       return false; 
/* 513 */     if (logger == null || classe == null || method == null) {
/* 514 */       String paquet = (logger != null) ? logger.getName() : null;
/* 515 */       StackTraceElement[] elements = error.getStackTrace();
/* 516 */       for (int i = 0; i < elements.length; i++) {
/* 522 */         StackTraceElement element = elements[i];
/* 523 */         String classname = element.getClassName();
/* 524 */         if (classe != null) {
/* 525 */           if (!classname.equals(classe))
/*     */             continue; 
/* 528 */         } else if (paquet != null) {
/* 529 */           if (!classname.startsWith(paquet))
/*     */             continue; 
/* 532 */           int length = paquet.length();
/* 533 */           if (classname.length() > length) {
/* 535 */             char separator = classname.charAt(length);
/* 536 */             if (Character.isJavaIdentifierPart(separator))
/*     */               continue; 
/*     */           } 
/*     */         } 
/* 545 */         String methodName = element.getMethodName();
/* 546 */         if (method == null || methodName.equals(method)) {
/* 552 */           if (paquet == null) {
/* 553 */             int separator = classname.lastIndexOf('.');
/* 554 */             paquet = (separator >= 1) ? classname.substring(0, separator - 1) : "";
/* 555 */             logger = getLogger(paquet);
/* 556 */             if (!logger.isLoggable(level))
/* 557 */               return false; 
/*     */           } 
/* 560 */           if (classe == null)
/* 561 */             classe = classname; 
/* 563 */           if (method == null)
/* 564 */             method = methodName; 
/*     */           break;
/*     */         } 
/*     */         continue;
/*     */       } 
/* 574 */       if (logger == null) {
/* 575 */         logger = getLogger("global");
/* 576 */         if (!logger.isLoggable(level))
/* 577 */           return false; 
/*     */       } 
/*     */     } 
/* 586 */     StringBuilder buffer = new StringBuilder(Classes.getShortClassName(error));
/* 587 */     String message = error.getLocalizedMessage();
/* 588 */     if (message != null)
/* 589 */       buffer.append(": ").append(message); 
/* 591 */     LogRecord record = new LogRecord(level, buffer.toString());
/* 592 */     if (classe != null)
/* 593 */       record.setSourceClassName(classe); 
/* 595 */     if (method != null)
/* 596 */       record.setSourceMethodName(method); 
/* 598 */     if (level.intValue() > 500)
/* 599 */       record.setThrown(error); 
/* 601 */     record.setLoggerName(logger.getName());
/* 602 */     logger.log(record);
/* 603 */     return true;
/*     */   }
/*     */   
/*     */   public static boolean recoverableException(Logger logger, Class<?> classe, String method, Throwable error) {
/* 623 */     String classname = (classe != null) ? classe.getName() : null;
/* 624 */     return unexpectedException(logger, classname, method, error, Level.FINE);
/*     */   }
/*     */   
/*     */   public static boolean recoverableException(Class<?> classe, String method, Throwable error) {
/* 643 */     return recoverableException(null, classe, method, error);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\logging\Logging.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
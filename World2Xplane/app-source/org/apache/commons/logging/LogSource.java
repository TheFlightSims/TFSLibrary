/*     */ package org.apache.commons.logging;
/*     */ 
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.util.Hashtable;
/*     */ import org.apache.commons.logging.impl.NoOpLog;
/*     */ 
/*     */ public class LogSource {
/*  62 */   protected static Hashtable logs = new Hashtable();
/*     */   
/*     */   protected static boolean log4jIsAvailable = false;
/*     */   
/*     */   protected static boolean jdk14IsAvailable = false;
/*     */   
/*  71 */   protected static Constructor logImplctor = null;
/*     */   
/*     */   static {
/*     */     try {
/*  80 */       if (null != Class.forName("org.apache.log4j.Logger")) {
/*  81 */         log4jIsAvailable = true;
/*     */       } else {
/*  83 */         log4jIsAvailable = false;
/*     */       } 
/*  85 */     } catch (Throwable t) {
/*  86 */       log4jIsAvailable = false;
/*     */     } 
/*     */     try {
/*  91 */       if (null != Class.forName("java.util.logging.Logger") && null != Class.forName("org.apache.commons.logging.impl.Jdk14Logger")) {
/*  93 */         jdk14IsAvailable = true;
/*     */       } else {
/*  95 */         jdk14IsAvailable = false;
/*     */       } 
/*  97 */     } catch (Throwable t) {
/*  98 */       jdk14IsAvailable = false;
/*     */     } 
/* 102 */     String name = null;
/*     */     try {
/* 104 */       name = System.getProperty("org.apache.commons.logging.log");
/* 105 */       if (name == null)
/* 106 */         name = System.getProperty("org.apache.commons.logging.Log"); 
/* 108 */     } catch (Throwable t) {}
/* 110 */     if (name != null) {
/*     */       try {
/* 112 */         setLogImplementation(name);
/* 113 */       } catch (Throwable t) {
/*     */         try {
/* 115 */           setLogImplementation("org.apache.commons.logging.impl.NoOpLog");
/* 117 */         } catch (Throwable u) {}
/*     */       } 
/*     */     } else {
/*     */       try {
/* 123 */         if (log4jIsAvailable) {
/* 124 */           setLogImplementation("org.apache.commons.logging.impl.Log4JLogger");
/* 126 */         } else if (jdk14IsAvailable) {
/* 127 */           setLogImplementation("org.apache.commons.logging.impl.Jdk14Logger");
/*     */         } else {
/* 130 */           setLogImplementation("org.apache.commons.logging.impl.NoOpLog");
/*     */         } 
/* 133 */       } catch (Throwable t) {
/*     */         try {
/* 135 */           setLogImplementation("org.apache.commons.logging.impl.NoOpLog");
/* 137 */         } catch (Throwable u) {}
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void setLogImplementation(String classname) throws LinkageError, ExceptionInInitializerError, NoSuchMethodException, SecurityException, ClassNotFoundException {
/*     */     try {
/* 169 */       Class logclass = Class.forName(classname);
/* 170 */       Class[] argtypes = new Class[1];
/* 171 */       argtypes[0] = "".getClass();
/* 172 */       logImplctor = logclass.getConstructor(argtypes);
/* 173 */     } catch (Throwable t) {
/* 174 */       logImplctor = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void setLogImplementation(Class logclass) throws LinkageError, ExceptionInInitializerError, NoSuchMethodException, SecurityException {
/* 188 */     Class[] argtypes = new Class[1];
/* 189 */     argtypes[0] = "".getClass();
/* 190 */     logImplctor = logclass.getConstructor(argtypes);
/*     */   }
/*     */   
/*     */   public static Log getInstance(String name) {
/* 196 */     Log log = (Log)logs.get(name);
/* 197 */     if (null == log) {
/* 198 */       log = makeNewLogInstance(name);
/* 199 */       logs.put(name, log);
/*     */     } 
/* 201 */     return log;
/*     */   }
/*     */   
/*     */   public static Log getInstance(Class clazz) {
/* 207 */     return getInstance(clazz.getName());
/*     */   }
/*     */   
/*     */   public static Log makeNewLogInstance(String name) {
/*     */     NoOpLog noOpLog;
/* 237 */     Log log = null;
/*     */     try {
/* 239 */       Object[] args = new Object[1];
/* 240 */       args[0] = name;
/* 241 */       log = logImplctor.newInstance(args);
/* 242 */     } catch (Throwable t) {
/* 243 */       log = null;
/*     */     } 
/* 245 */     if (null == log)
/* 246 */       noOpLog = new NoOpLog(name); 
/* 248 */     return (Log)noOpLog;
/*     */   }
/*     */   
/*     */   public static String[] getLogNames() {
/* 258 */     return (String[])logs.keySet().toArray((Object[])new String[logs.size()]);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\logging\LogSource.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */
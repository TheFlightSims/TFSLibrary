/*     */ package org.slf4j;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.URL;
/*     */ import java.util.Arrays;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import org.slf4j.helpers.NOPLoggerFactory;
/*     */ import org.slf4j.helpers.SubstituteLoggerFactory;
/*     */ import org.slf4j.helpers.Util;
/*     */ import org.slf4j.impl.StaticLoggerBinder;
/*     */ 
/*     */ public final class LoggerFactory {
/*     */   static final String CODES_PREFIX = "http://www.slf4j.org/codes.html";
/*     */   
/*     */   static final String NO_STATICLOGGERBINDER_URL = "http://www.slf4j.org/codes.html#StaticLoggerBinder";
/*     */   
/*     */   static final String MULTIPLE_BINDINGS_URL = "http://www.slf4j.org/codes.html#multiple_bindings";
/*     */   
/*     */   static final String NULL_LF_URL = "http://www.slf4j.org/codes.html#null_LF";
/*     */   
/*     */   static final String VERSION_MISMATCH = "http://www.slf4j.org/codes.html#version_mismatch";
/*     */   
/*     */   static final String SUBSTITUTE_LOGGER_URL = "http://www.slf4j.org/codes.html#substituteLogger";
/*     */   
/*     */   static final String UNSUCCESSFUL_INIT_URL = "http://www.slf4j.org/codes.html#unsuccessfulInit";
/*     */   
/*     */   static final String UNSUCCESSFUL_INIT_MSG = "org.slf4j.LoggerFactory could not be successfully initialized. See also http://www.slf4j.org/codes.html#unsuccessfulInit";
/*     */   
/*     */   static final int UNINITIALIZED = 0;
/*     */   
/*     */   static final int ONGOING_INITILIZATION = 1;
/*     */   
/*     */   static final int FAILED_INITILIZATION = 2;
/*     */   
/*     */   static final int SUCCESSFUL_INITILIZATION = 3;
/*     */   
/*     */   static final int NOP_FALLBACK_INITILIZATION = 4;
/*     */   
/*  73 */   static int INITIALIZATION_STATE = 0;
/*     */   
/*  74 */   static SubstituteLoggerFactory TEMP_FACTORY = new SubstituteLoggerFactory();
/*     */   
/*  75 */   static NOPLoggerFactory NOP_FALLBACK_FACTORY = new NOPLoggerFactory();
/*     */   
/*  84 */   private static final String[] API_COMPATIBILITY_LIST = new String[] { "1.6" };
/*     */   
/*     */   static void reset() {
/* 102 */     INITIALIZATION_STATE = 0;
/* 103 */     TEMP_FACTORY = new SubstituteLoggerFactory();
/*     */   }
/*     */   
/*     */   private static final void performInitialization() {
/* 107 */     singleImplementationSanityCheck();
/* 108 */     bind();
/* 109 */     if (INITIALIZATION_STATE == 3)
/* 110 */       versionSanityCheck(); 
/*     */   }
/*     */   
/*     */   private static boolean messageContainsOrgSlf4jImplStaticLoggerBinder(String msg) {
/* 116 */     if (msg == null)
/* 117 */       return false; 
/* 118 */     if (msg.indexOf("org/slf4j/impl/StaticLoggerBinder") != -1)
/* 119 */       return true; 
/* 120 */     if (msg.indexOf("org.slf4j.impl.StaticLoggerBinder") != -1)
/* 121 */       return true; 
/* 122 */     return false;
/*     */   }
/*     */   
/*     */   private static final void bind() {
/*     */     try {
/* 128 */       StaticLoggerBinder.getSingleton();
/* 129 */       INITIALIZATION_STATE = 3;
/* 130 */       emitSubstituteLoggerWarning();
/* 131 */     } catch (NoClassDefFoundError ncde) {
/* 132 */       String msg = ncde.getMessage();
/* 133 */       if (messageContainsOrgSlf4jImplStaticLoggerBinder(msg)) {
/* 134 */         INITIALIZATION_STATE = 4;
/* 135 */         Util.report("Failed to load class \"org.slf4j.impl.StaticLoggerBinder\".");
/* 137 */         Util.report("Defaulting to no-operation (NOP) logger implementation");
/* 138 */         Util.report("See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.");
/*     */       } else {
/* 141 */         failedBinding(ncde);
/* 142 */         throw ncde;
/*     */       } 
/* 144 */     } catch (NoSuchMethodError nsme) {
/* 145 */       String msg = nsme.getMessage();
/* 146 */       if (msg != null && msg.indexOf("org.slf4j.impl.StaticLoggerBinder.getSingleton()") != -1) {
/* 147 */         INITIALIZATION_STATE = 2;
/* 148 */         Util.report("slf4j-api 1.6.x (or later) is incompatible with this binding.");
/* 149 */         Util.report("Your binding is version 1.5.5 or earlier.");
/* 150 */         Util.report("Upgrade your binding to version 1.6.x. or 2.0.x");
/*     */       } 
/* 152 */       throw nsme;
/* 153 */     } catch (Exception e) {
/* 154 */       failedBinding(e);
/* 155 */       throw new IllegalStateException("Unexpected initialization failure", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   static void failedBinding(Throwable t) {
/* 160 */     INITIALIZATION_STATE = 2;
/* 161 */     Util.report("Failed to instantiate SLF4J LoggerFactory", t);
/*     */   }
/*     */   
/*     */   private static final void emitSubstituteLoggerWarning() {
/* 165 */     List loggerNameList = TEMP_FACTORY.getLoggerNameList();
/* 166 */     if (loggerNameList.size() == 0)
/*     */       return; 
/* 169 */     Util.report("The following loggers will not work becasue they were created");
/* 171 */     Util.report("during the default configuration phase of the underlying logging system.");
/* 173 */     Util.report("See also http://www.slf4j.org/codes.html#substituteLogger");
/* 174 */     for (int i = 0; i < loggerNameList.size(); i++) {
/* 175 */       String loggerName = loggerNameList.get(i);
/* 176 */       Util.report(loggerName);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static final void versionSanityCheck() {
/*     */     try {
/* 182 */       String requested = StaticLoggerBinder.REQUESTED_API_VERSION;
/* 184 */       boolean match = false;
/* 185 */       for (int i = 0; i < API_COMPATIBILITY_LIST.length; i++) {
/* 186 */         if (requested.startsWith(API_COMPATIBILITY_LIST[i]))
/* 187 */           match = true; 
/*     */       } 
/* 190 */       if (!match) {
/* 191 */         Util.report("The requested version " + requested + " by your slf4j binding is not compatible with " + Arrays.<String>asList(API_COMPATIBILITY_LIST).toString());
/* 194 */         Util.report("See http://www.slf4j.org/codes.html#version_mismatch for further details.");
/*     */       } 
/* 196 */     } catch (NoSuchFieldError nsfe) {
/*     */     
/* 201 */     } catch (Throwable e) {
/* 203 */       Util.report("Unexpected problem occured during version sanity check", e);
/*     */     } 
/*     */   }
/*     */   
/* 209 */   private static String STATIC_LOGGER_BINDER_PATH = "org/slf4j/impl/StaticLoggerBinder.class";
/*     */   
/*     */   private static void singleImplementationSanityCheck() {
/*     */     try {
/*     */       Enumeration paths;
/* 213 */       ClassLoader loggerFactoryClassLoader = LoggerFactory.class.getClassLoader();
/* 216 */       if (loggerFactoryClassLoader == null) {
/* 217 */         paths = ClassLoader.getSystemResources(STATIC_LOGGER_BINDER_PATH);
/*     */       } else {
/* 219 */         paths = loggerFactoryClassLoader.getResources(STATIC_LOGGER_BINDER_PATH);
/*     */       } 
/* 224 */       Set implementationSet = new LinkedHashSet();
/* 225 */       while (paths.hasMoreElements()) {
/* 226 */         URL path = paths.nextElement();
/* 227 */         implementationSet.add(path);
/*     */       } 
/* 229 */       if (implementationSet.size() > 1) {
/* 230 */         Util.report("Class path contains multiple SLF4J bindings.");
/* 231 */         Iterator iterator = implementationSet.iterator();
/* 232 */         while (iterator.hasNext()) {
/* 233 */           URL path = iterator.next();
/* 234 */           Util.report("Found binding in [" + path + "]");
/*     */         } 
/* 236 */         Util.report("See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.");
/*     */       } 
/* 238 */     } catch (IOException ioe) {
/* 239 */       Util.report("Error getting resources from path", ioe);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Logger getLogger(String name) {
/* 252 */     ILoggerFactory iLoggerFactory = getILoggerFactory();
/* 253 */     return iLoggerFactory.getLogger(name);
/*     */   }
/*     */   
/*     */   public static Logger getLogger(Class clazz) {
/* 265 */     return getLogger(clazz.getName());
/*     */   }
/*     */   
/*     */   public static ILoggerFactory getILoggerFactory() {
/* 277 */     if (INITIALIZATION_STATE == 0) {
/* 278 */       INITIALIZATION_STATE = 1;
/* 279 */       performInitialization();
/*     */     } 
/* 282 */     switch (INITIALIZATION_STATE) {
/*     */       case 3:
/* 284 */         return StaticLoggerBinder.getSingleton().getLoggerFactory();
/*     */       case 4:
/* 286 */         return (ILoggerFactory)NOP_FALLBACK_FACTORY;
/*     */       case 2:
/* 288 */         throw new IllegalStateException("org.slf4j.LoggerFactory could not be successfully initialized. See also http://www.slf4j.org/codes.html#unsuccessfulInit");
/*     */       case 1:
/* 292 */         return (ILoggerFactory)TEMP_FACTORY;
/*     */     } 
/* 294 */     throw new IllegalStateException("Unreachable code");
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\slf4j\LoggerFactory.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
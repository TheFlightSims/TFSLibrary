/*     */ package ch.qos.logback.core.util;
/*     */ 
/*     */ import ch.qos.logback.core.Context;
/*     */ import java.io.IOException;
/*     */ import java.net.URL;
/*     */ import java.security.AccessControlException;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedAction;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class Loader {
/*     */   static final String TSTR = "Caught Exception while in Loader.getResource. This may be innocuous.";
/*     */   
/*     */   private static boolean ignoreTCL = false;
/*     */   
/*     */   public static final String IGNORE_TCL_PROPERTY_NAME = "logback.ignoreTCL";
/*     */   
/*     */   private static boolean HAS_GET_CLASS_LOADER_PERMISSION = false;
/*     */   
/*     */   static {
/*  40 */     String ignoreTCLProp = OptionHelper.getSystemProperty("logback.ignoreTCL", null);
/*  43 */     if (ignoreTCLProp != null)
/*  44 */       ignoreTCL = OptionHelper.toBoolean(ignoreTCLProp, true); 
/*  47 */     HAS_GET_CLASS_LOADER_PERMISSION = ((Boolean)AccessController.<Boolean>doPrivileged(new PrivilegedAction<Boolean>() {
/*     */           public Boolean run() {
/*     */             try {
/*  51 */               AccessController.checkPermission(new RuntimePermission("getClassLoader"));
/*  53 */               return Boolean.valueOf(true);
/*  54 */             } catch (AccessControlException e) {
/*  55 */               return Boolean.valueOf(false);
/*     */             } 
/*     */           }
/*     */         })).booleanValue();
/*     */   }
/*     */   
/*     */   public static Set<URL> getResourceOccurrenceCount(String resource, ClassLoader classLoader) throws IOException {
/*  74 */     Set<URL> urlSet = new HashSet<URL>();
/*  75 */     Enumeration<URL> urlEnum = classLoader.getResources(resource);
/*  76 */     while (urlEnum.hasMoreElements()) {
/*  77 */       URL url = urlEnum.nextElement();
/*  78 */       urlSet.add(url);
/*     */     } 
/*  80 */     return urlSet;
/*     */   }
/*     */   
/*     */   public static URL getResource(String resource, ClassLoader classLoader) {
/*     */     try {
/*  91 */       return classLoader.getResource(resource);
/*  92 */     } catch (Throwable t) {
/*  93 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static URL getResourceBySelfClassLoader(String resource) {
/* 105 */     return getResource(resource, getClassLoaderOfClass(Loader.class));
/*     */   }
/*     */   
/*     */   public static ClassLoader getTCL() {
/* 118 */     return Thread.currentThread().getContextClassLoader();
/*     */   }
/*     */   
/*     */   public static Class<?> loadClass(String clazz, Context context) throws ClassNotFoundException {
/* 123 */     ClassLoader cl = getClassLoaderOfObject(context);
/* 124 */     return cl.loadClass(clazz);
/*     */   }
/*     */   
/*     */   public static ClassLoader getClassLoaderOfObject(Object o) {
/* 135 */     if (o == null)
/* 136 */       throw new NullPointerException("Argument cannot be null"); 
/* 138 */     return getClassLoaderOfClass(o.getClass());
/*     */   }
/*     */   
/*     */   public static ClassLoader getClassLoaderAsPrivileged(final Class<?> clazz) {
/* 148 */     if (!HAS_GET_CLASS_LOADER_PERMISSION)
/* 149 */       return null; 
/* 151 */     return AccessController.<ClassLoader>doPrivileged(new PrivilegedAction<ClassLoader>() {
/*     */           public ClassLoader run() {
/* 154 */             return clazz.getClassLoader();
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public static ClassLoader getClassLoaderOfClass(Class<?> clazz) {
/* 167 */     ClassLoader cl = clazz.getClassLoader();
/* 168 */     if (cl == null)
/* 169 */       return ClassLoader.getSystemClassLoader(); 
/* 171 */     return cl;
/*     */   }
/*     */   
/*     */   public static Class<?> loadClass(String clazz) throws ClassNotFoundException {
/* 183 */     if (ignoreTCL)
/* 184 */       return Class.forName(clazz); 
/*     */     try {
/* 187 */       return getTCL().loadClass(clazz);
/* 188 */     } catch (Throwable e) {
/* 192 */       return Class.forName(clazz);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\cor\\util\Loader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
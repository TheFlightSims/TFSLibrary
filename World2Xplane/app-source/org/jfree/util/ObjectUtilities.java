/*     */ package org.jfree.util;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.net.URL;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public final class ObjectUtilities {
/*     */   public static final String THREAD_CONTEXT = "ThreadContext";
/*     */   
/*     */   public static final String CLASS_CONTEXT = "ClassContext";
/*     */   
/*  81 */   private static String classLoaderSource = "ThreadContext";
/*     */   
/*     */   private static ClassLoader classLoader;
/*     */   
/*     */   public static String getClassLoaderSource() {
/* 100 */     return classLoaderSource;
/*     */   }
/*     */   
/*     */   public static void setClassLoaderSource(String classLoaderSource) {
/* 114 */     ObjectUtilities.classLoaderSource = classLoaderSource;
/*     */   }
/*     */   
/*     */   public static boolean equal(Object o1, Object o2) {
/* 126 */     if (o1 == o2)
/* 127 */       return true; 
/* 129 */     if (o1 != null)
/* 130 */       return o1.equals(o2); 
/* 133 */     return false;
/*     */   }
/*     */   
/*     */   public static int hashCode(Object object) {
/* 146 */     int result = 0;
/* 147 */     if (object != null)
/* 148 */       result = object.hashCode(); 
/* 150 */     return result;
/*     */   }
/*     */   
/*     */   public static Object clone(Object object) throws CloneNotSupportedException {
/* 163 */     if (object == null)
/* 164 */       throw new IllegalArgumentException("Null 'object' argument."); 
/* 166 */     if (object instanceof PublicCloneable) {
/* 167 */       PublicCloneable pc = (PublicCloneable)object;
/* 168 */       return pc.clone();
/*     */     } 
/*     */     try {
/* 172 */       Method method = object.getClass().getMethod("clone", (Class[])null);
/* 174 */       if (Modifier.isPublic(method.getModifiers()))
/* 175 */         return method.invoke(object, (Object[])null); 
/* 178 */     } catch (NoSuchMethodException e) {
/* 179 */       Log.warn("Object without clone() method is impossible.");
/* 181 */     } catch (IllegalAccessException e) {
/* 182 */       Log.warn("Object.clone(): unable to call method.");
/* 184 */     } catch (InvocationTargetException e) {
/* 185 */       Log.warn("Object without clone() method is impossible.");
/*     */     } 
/* 188 */     throw new CloneNotSupportedException("Failed to clone.");
/*     */   }
/*     */   
/*     */   public static Collection deepClone(Collection collection) throws CloneNotSupportedException {
/* 204 */     if (collection == null)
/* 205 */       throw new IllegalArgumentException("Null 'collection' argument."); 
/* 210 */     Collection result = (Collection)clone(collection);
/* 212 */     result.clear();
/* 213 */     Iterator iterator = collection.iterator();
/* 214 */     while (iterator.hasNext()) {
/* 215 */       Object item = iterator.next();
/* 216 */       if (item != null) {
/* 217 */         result.add(clone(item));
/*     */         continue;
/*     */       } 
/* 220 */       result.add(null);
/*     */     } 
/* 223 */     return result;
/*     */   }
/*     */   
/*     */   public static synchronized void setClassLoader(ClassLoader classLoader) {
/* 233 */     ObjectUtilities.classLoader = classLoader;
/*     */   }
/*     */   
/*     */   public static ClassLoader getClassLoader() {
/* 242 */     return classLoader;
/*     */   }
/*     */   
/*     */   public static synchronized ClassLoader getClassLoader(Class c) {
/* 256 */     if (classLoader != null)
/* 257 */       return classLoader; 
/* 259 */     if ("ThreadContext".equals(classLoaderSource)) {
/* 260 */       ClassLoader threadLoader = Thread.currentThread().getContextClassLoader();
/* 262 */       return threadLoader;
/*     */     } 
/* 266 */     ClassLoader applicationCL = c.getClassLoader();
/* 267 */     if (applicationCL == null)
/* 268 */       return ClassLoader.getSystemClassLoader(); 
/* 271 */     return applicationCL;
/*     */   }
/*     */   
/*     */   public static URL getResource(String name, Class c) {
/* 285 */     ClassLoader cl = getClassLoader(c);
/* 286 */     return cl.getResource(name);
/*     */   }
/*     */   
/*     */   public static URL getResourceRelative(String name, Class c) {
/* 297 */     ClassLoader cl = getClassLoader(c);
/* 298 */     String cname = convertName(name, c);
/* 299 */     return cl.getResource(cname);
/*     */   }
/*     */   
/*     */   private static String convertName(String name, Class c) {
/* 313 */     if (name.startsWith("/"))
/* 315 */       return name.substring(1); 
/* 319 */     while (c.isArray())
/* 320 */       c = c.getComponentType(); 
/* 323 */     String baseName = c.getName();
/* 324 */     int index = baseName.lastIndexOf('.');
/* 325 */     if (index == -1)
/* 326 */       return name; 
/* 329 */     String pkgName = baseName.substring(0, index);
/* 330 */     return pkgName.replace('.', '/') + "/" + name;
/*     */   }
/*     */   
/*     */   public static InputStream getResourceAsStream(String name, Class context) {
/* 343 */     URL url = getResource(name, context);
/* 344 */     if (url == null)
/* 345 */       return null; 
/*     */     try {
/* 349 */       return url.openStream();
/* 351 */     } catch (IOException e) {
/* 352 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static InputStream getResourceRelativeAsStream(String name, Class context) {
/* 366 */     URL url = getResourceRelative(name, context);
/* 367 */     if (url == null)
/* 368 */       return null; 
/*     */     try {
/* 372 */       return url.openStream();
/* 374 */     } catch (IOException e) {
/* 375 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Object loadAndInstantiate(String className, Class source) {
/*     */     try {
/* 390 */       ClassLoader loader = getClassLoader(source);
/* 391 */       Class c = loader.loadClass(className);
/* 392 */       return c.newInstance();
/* 394 */     } catch (Exception e) {
/* 395 */       return null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\util\ObjectUtilities.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */
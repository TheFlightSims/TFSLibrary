/*     */ package com.google.common.base;
/*     */ 
/*     */ import com.google.common.annotations.VisibleForTesting;
/*     */ import java.io.Closeable;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.lang.ref.PhantomReference;
/*     */ import java.lang.ref.Reference;
/*     */ import java.lang.ref.ReferenceQueue;
/*     */ import java.lang.reflect.Method;
/*     */ import java.net.URL;
/*     */ import java.net.URLClassLoader;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ 
/*     */ public class FinalizableReferenceQueue implements Closeable {
/* 129 */   private static final Logger logger = Logger.getLogger(FinalizableReferenceQueue.class.getName());
/*     */   
/*     */   private static final String FINALIZER_CLASS_NAME = "com.google.common.base.internal.Finalizer";
/*     */   
/*     */   private static final Method startFinalizer;
/*     */   
/*     */   static {
/* 136 */     Class<?> finalizer = loadFinalizer(new FinalizerLoader[] { new SystemLoader(), new DecoupledLoader(), new DirectLoader() });
/* 138 */     startFinalizer = getStartFinalizer(finalizer);
/*     */   }
/*     */   
/* 158 */   final ReferenceQueue<Object> queue = new ReferenceQueue();
/*     */   
/* 159 */   final PhantomReference<Object> frqRef = new PhantomReference(this, this.queue);
/*     */   
/*     */   final boolean threadStarted;
/*     */   
/*     */   public FinalizableReferenceQueue() {
/* 160 */     boolean threadStarted = false;
/*     */     try {
/* 162 */       startFinalizer.invoke(null, new Object[] { FinalizableReference.class, this.queue, this.frqRef });
/* 163 */       threadStarted = true;
/* 164 */     } catch (IllegalAccessException impossible) {
/* 165 */       throw new AssertionError(impossible);
/* 166 */     } catch (Throwable t) {
/* 167 */       logger.log(Level.INFO, "Failed to start reference finalizer thread. Reference cleanup will only occur when new references are created.", t);
/*     */     } 
/* 171 */     this.threadStarted = threadStarted;
/*     */   }
/*     */   
/*     */   public void close() {
/* 176 */     this.frqRef.enqueue();
/* 177 */     cleanUp();
/*     */   }
/*     */   
/*     */   void cleanUp() {
/* 186 */     if (this.threadStarted)
/*     */       return; 
/*     */     Reference<?> reference;
/* 191 */     while ((reference = this.queue.poll()) != null) {
/* 196 */       reference.clear();
/*     */       try {
/* 198 */         ((FinalizableReference)reference).finalizeReferent();
/* 199 */       } catch (Throwable t) {
/* 200 */         logger.log(Level.SEVERE, "Error cleaning up after reference.", t);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static Class<?> loadFinalizer(FinalizerLoader... loaders) {
/* 211 */     for (FinalizerLoader loader : loaders) {
/* 212 */       Class<?> finalizer = loader.loadFinalizer();
/* 213 */       if (finalizer != null)
/* 214 */         return finalizer; 
/*     */     } 
/* 218 */     throw new AssertionError();
/*     */   }
/*     */   
/*     */   static interface FinalizerLoader {
/*     */     Class<?> loadFinalizer();
/*     */   }
/*     */   
/*     */   static class SystemLoader implements FinalizerLoader {
/*     */     @VisibleForTesting
/*     */     static boolean disabled;
/*     */     
/*     */     public Class<?> loadFinalizer() {
/*     */       ClassLoader systemLoader;
/* 246 */       if (disabled)
/* 247 */         return null; 
/*     */       try {
/* 251 */         systemLoader = ClassLoader.getSystemClassLoader();
/* 252 */       } catch (SecurityException e) {
/* 253 */         FinalizableReferenceQueue.logger.info("Not allowed to access system class loader.");
/* 254 */         return null;
/*     */       } 
/* 256 */       if (systemLoader != null)
/*     */         try {
/* 258 */           return systemLoader.loadClass("com.google.common.base.internal.Finalizer");
/* 259 */         } catch (ClassNotFoundException e) {
/* 261 */           return null;
/*     */         }  
/* 264 */       return null;
/*     */     }
/*     */   }
/*     */   
/*     */   static class DecoupledLoader implements FinalizerLoader {
/*     */     private static final String LOADING_ERROR = "Could not load Finalizer in its own class loader.Loading Finalizer in the current class loader instead. As a result, you will not be ableto garbage collect this class loader. To support reclaiming this class loader, eitherresolve the underlying issue, or move Google Collections to your system class path.";
/*     */     
/*     */     public Class<?> loadFinalizer() {
/*     */       try {
/* 292 */         ClassLoader finalizerLoader = newLoader(getBaseUrl());
/* 293 */         return finalizerLoader.loadClass("com.google.common.base.internal.Finalizer");
/* 294 */       } catch (Exception e) {
/* 295 */         FinalizableReferenceQueue.logger.log(Level.WARNING, "Could not load Finalizer in its own class loader.Loading Finalizer in the current class loader instead. As a result, you will not be ableto garbage collect this class loader. To support reclaiming this class loader, eitherresolve the underlying issue, or move Google Collections to your system class path.", e);
/* 296 */         return null;
/*     */       } 
/*     */     }
/*     */     
/*     */     URL getBaseUrl() throws IOException {
/* 305 */       String finalizerPath = "com.google.common.base.internal.Finalizer".replace('.', '/') + ".class";
/* 306 */       URL finalizerUrl = getClass().getClassLoader().getResource(finalizerPath);
/* 307 */       if (finalizerUrl == null)
/* 308 */         throw new FileNotFoundException(finalizerPath); 
/* 312 */       String urlString = finalizerUrl.toString();
/* 313 */       if (!urlString.endsWith(finalizerPath))
/* 314 */         throw new IOException("Unsupported path style: " + urlString); 
/* 316 */       urlString = urlString.substring(0, urlString.length() - finalizerPath.length());
/* 317 */       return new URL(finalizerUrl, urlString);
/*     */     }
/*     */     
/*     */     URLClassLoader newLoader(URL base) {
/* 325 */       return new URLClassLoader(new URL[] { base }, null);
/*     */     }
/*     */   }
/*     */   
/*     */   static class DirectLoader implements FinalizerLoader {
/*     */     public Class<?> loadFinalizer() {
/*     */       try {
/* 337 */         return Class.forName("com.google.common.base.internal.Finalizer");
/* 338 */       } catch (ClassNotFoundException e) {
/* 339 */         throw new AssertionError(e);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   static Method getStartFinalizer(Class<?> finalizer) {
/*     */     try {
/* 349 */       return finalizer.getMethod("startFinalizer", new Class[] { Class.class, ReferenceQueue.class, PhantomReference.class });
/* 354 */     } catch (NoSuchMethodException e) {
/* 355 */       throw new AssertionError(e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\common\base\FinalizableReferenceQueue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
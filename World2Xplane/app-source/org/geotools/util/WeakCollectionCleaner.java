/*     */ package org.geotools.util;
/*     */ 
/*     */ import java.lang.ref.Reference;
/*     */ import java.lang.ref.ReferenceQueue;
/*     */ import org.geotools.util.logging.Logging;
/*     */ 
/*     */ public final class WeakCollectionCleaner extends Thread {
/*  44 */   public static final WeakCollectionCleaner DEFAULT = new WeakCollectionCleaner();
/*     */   
/*  50 */   ReferenceQueue<Object> referenceQueue = new ReferenceQueue();
/*     */   
/*     */   private WeakCollectionCleaner() {
/*  58 */     super("WeakCollectionCleaner");
/*  59 */     setPriority(8);
/*  60 */     setDaemon(true);
/*  61 */     start();
/*     */   }
/*     */   
/*     */   public synchronized ReferenceQueue<Object> getReferenceQueue() {
/*  65 */     return this.referenceQueue;
/*     */   }
/*     */   
/*     */   public void run() {
/*     */     ReferenceQueue<Object> rq;
/*  74 */     while ((rq = getReferenceQueue()) != null) {
/*     */       try {
/*  77 */         Reference<?> ref = rq.remove();
/*  78 */         if (ref == null) {
/*  88 */           sleep(15000L);
/*     */           break;
/*     */         } 
/*  91 */         ref.clear();
/*  94 */       } catch (InterruptedException exception) {
/*     */       
/*  96 */       } catch (Exception exception) {
/*  97 */         Logging.unexpectedException(WeakCollectionCleaner.class, "remove", exception);
/*  98 */       } catch (AssertionError exception) {
/*  99 */         Logging.unexpectedException(WeakCollectionCleaner.class, "remove", exception);
/*     */       } 
/*     */     } 
/* 104 */     Logging.getLogger(WeakCollectionCleaner.class).info("Weak collection cleaner stopped");
/*     */   }
/*     */   
/*     */   public void exit() {
/* 113 */     synchronized (this) {
/* 114 */       this.referenceQueue = null;
/*     */     } 
/* 116 */     interrupt();
/*     */     try {
/* 118 */       join(500L);
/* 119 */     } catch (InterruptedException e) {}
/* 123 */     if (isAlive())
/* 124 */       stop(); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\WeakCollectionCleaner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
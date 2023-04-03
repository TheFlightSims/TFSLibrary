/*     */ package com.sun.media.jai.util;
/*     */ 
/*     */ import java.util.LinkedList;
/*     */ import java.util.Vector;
/*     */ 
/*     */ class WorkerThread extends Thread {
/* 409 */   public static final Object TERMINATE = new Object();
/*     */   
/*     */   SunTileScheduler scheduler;
/*     */   
/*     */   boolean isPrefetch;
/*     */   
/*     */   public WorkerThread(ThreadGroup group, SunTileScheduler scheduler, boolean isPrefetch) {
/* 421 */     super(group, group.getName() + group.activeCount());
/* 422 */     this.scheduler = scheduler;
/* 423 */     this.isPrefetch = isPrefetch;
/* 425 */     setDaemon(true);
/* 426 */     start();
/*     */   }
/*     */   
/*     */   public void run() {
/* 431 */     LinkedList jobQueue = this.scheduler.getQueue(this.isPrefetch);
/*     */     while (true) {
/* 434 */       Object dequeuedObject = null;
/* 437 */       synchronized (jobQueue) {
/* 438 */         if (jobQueue.size() > 0) {
/* 440 */           dequeuedObject = jobQueue.removeFirst();
/*     */         } else {
/*     */           try {
/* 444 */             jobQueue.wait();
/*     */             continue;
/* 446 */           } catch (InterruptedException ie) {}
/*     */         } 
/*     */       } 
/* 452 */       if (dequeuedObject == TERMINATE || getThreadGroup() == null || getThreadGroup().isDestroyed()) {
/*     */         Vector threads;
/* 456 */         synchronized (threads = this.scheduler.getWorkers(this.isPrefetch)) {
/* 457 */           threads.remove(this);
/*     */         } 
/*     */         return;
/*     */       } 
/* 464 */       Job job = (Job)dequeuedObject;
/* 467 */       if (job != null) {
/* 468 */         job.compute();
/* 471 */         if (job.isBlocking())
/* 472 */           synchronized (this.scheduler) {
/* 473 */             this.scheduler.notify();
/*     */           }  
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\ja\\util\WorkerThread.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
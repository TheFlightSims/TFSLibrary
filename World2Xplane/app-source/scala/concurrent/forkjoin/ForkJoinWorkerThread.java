/*     */ package scala.concurrent.forkjoin;
/*     */ 
/*     */ public class ForkJoinWorkerThread extends Thread {
/*     */   final ForkJoinPool pool;
/*     */   
/*     */   final ForkJoinPool.WorkQueue workQueue;
/*     */   
/*     */   protected ForkJoinWorkerThread(ForkJoinPool pool) {
/*  48 */     super("aForkJoinWorkerThread");
/*  49 */     this.pool = pool;
/*  50 */     this.workQueue = pool.registerWorker(this);
/*     */   }
/*     */   
/*     */   public ForkJoinPool getPool() {
/*  59 */     return this.pool;
/*     */   }
/*     */   
/*     */   public int getPoolIndex() {
/*  72 */     return this.workQueue.poolIndex;
/*     */   }
/*     */   
/*     */   protected void onStart() {}
/*     */   
/*     */   protected void onTermination(Throwable exception) {}
/*     */   
/*     */   public void run() {
/* 104 */     Throwable exception = null;
/*     */     try {
/* 106 */       onStart();
/* 107 */       this.pool.runWorker(this.workQueue);
/* 108 */     } catch (Throwable ex) {
/* 109 */       exception = ex;
/*     */     } finally {
/*     */       try {
/* 112 */         onTermination(exception);
/* 113 */       } catch (Throwable ex) {
/* 114 */         if (exception == null)
/* 115 */           exception = ex; 
/*     */       } finally {
/* 117 */         this.pool.deregisterWorker(this, exception);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\concurrent\forkjoin\ForkJoinWorkerThread.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
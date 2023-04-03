/*     */ package scala.collection.parallel;
/*     */ 
/*     */ import scala.Function0;
/*     */ import scala.concurrent.forkjoin.ForkJoinPool;
/*     */ import scala.concurrent.forkjoin.ForkJoinTask;
/*     */ import scala.runtime.BoxedUnit;
/*     */ 
/*     */ public abstract class ForkJoinTasks$class {
/*     */   public static void $init$(ForkJoinTasks $this) {}
/*     */   
/*     */   public static ForkJoinPool forkJoinPool(ForkJoinTasks $this) {
/* 453 */     return $this.environment();
/*     */   }
/*     */   
/*     */   public static Function0 execute(ForkJoinTasks $this, Task<?, ?> task) {
/* 461 */     ForkJoinTasks.WrappedTask<?, ?> fjtask = $this.newWrappedTask(task);
/* 466 */     $this.forkJoinPool().execute((ForkJoinTask)fjtask);
/* 466 */     (Thread.currentThread() instanceof scala.concurrent.forkjoin.ForkJoinWorkerThread) ? ((ForkJoinTask)fjtask).fork() : BoxedUnit.UNIT;
/* 469 */     return (Function0)new ForkJoinTasks$$anonfun$execute$3($this, fjtask);
/*     */   }
/*     */   
/*     */   public static Object executeAndWaitResult(ForkJoinTasks $this, Task<?, ?> task) {
/* 484 */     ForkJoinTasks.WrappedTask<?, ?> fjtask = $this.newWrappedTask(task);
/* 489 */     $this.forkJoinPool().execute((ForkJoinTask)fjtask);
/* 489 */     (Thread.currentThread() instanceof scala.concurrent.forkjoin.ForkJoinWorkerThread) ? ((ForkJoinTask)fjtask).fork() : BoxedUnit.UNIT;
/* 492 */     fjtask.sync();
/* 494 */     fjtask.body().forwardThrowable();
/* 495 */     return fjtask.body().result();
/*     */   }
/*     */   
/*     */   public static int parallelismLevel(ForkJoinTasks $this) {
/* 498 */     return $this.forkJoinPool().getParallelism();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\ForkJoinTasks$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
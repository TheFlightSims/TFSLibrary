/*     */ package scala.collection.parallel;
/*     */ 
/*     */ import java.util.concurrent.ThreadPoolExecutor;
/*     */ import scala.Function0;
/*     */ 
/*     */ public abstract class FutureThreadPoolTasks$class {
/*     */   public static void $init$(FutureThreadPoolTasks $this) {
/* 383 */     $this.scala$collection$parallel$FutureThreadPoolTasks$_setter_$environment_$eq(FutureThreadPoolTasks$.MODULE$.defaultThreadPool());
/*     */   }
/*     */   
/*     */   public static ThreadPoolExecutor executor(FutureThreadPoolTasks $this) {
/* 384 */     return (ThreadPoolExecutor)$this.environment();
/*     */   }
/*     */   
/*     */   public static Function0 execute(FutureThreadPoolTasks $this, Task<?, ?> task) {
/* 387 */     FutureThreadPoolTasks.WrappedTask<?, ?> t = $this.newWrappedTask(task);
/* 390 */     t.start();
/* 392 */     return (Function0)new FutureThreadPoolTasks$$anonfun$execute$2($this, t);
/*     */   }
/*     */   
/*     */   public static Object executeAndWaitResult(FutureThreadPoolTasks $this, Task<?, ?> task) {
/* 400 */     FutureThreadPoolTasks.WrappedTask<?, ?> t = $this.newWrappedTask(task);
/* 403 */     t.start();
/* 405 */     t.sync();
/* 406 */     t.body().forwardThrowable();
/* 407 */     return t.body().result();
/*     */   }
/*     */   
/*     */   public static int parallelismLevel(FutureThreadPoolTasks $this) {
/* 410 */     return FutureThreadPoolTasks$.MODULE$.numCores();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\FutureThreadPoolTasks$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package scala.collection.parallel;
/*     */ 
/*     */ import java.util.concurrent.LinkedBlockingQueue;
/*     */ import java.util.concurrent.ThreadPoolExecutor;
/*     */ import scala.Function0;
/*     */ 
/*     */ public abstract class ThreadPoolTasks$class {
/*     */   public static ThreadPoolExecutor executor(ThreadPoolTasks $this) {
/* 296 */     return $this.environment();
/*     */   }
/*     */   
/*     */   public static LinkedBlockingQueue queue(ThreadPoolTasks $this) {
/* 297 */     return (LinkedBlockingQueue)$this.executor().getQueue();
/*     */   }
/*     */   
/*     */   public static void $init$(ThreadPoolTasks $this) {
/* 298 */     $this.totaltasks_$eq(0);
/*     */   }
/*     */   
/*     */   public static void scala$collection$parallel$ThreadPoolTasks$$incrTasks(ThreadPoolTasks $this) {
/* 300 */     synchronized ($this) {
/* 301 */       $this.totaltasks_$eq($this.totaltasks() + 1);
/*     */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/parallel/ThreadPoolTasks}, name=$this} */
/*     */       return;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void scala$collection$parallel$ThreadPoolTasks$$decrTasks(ThreadPoolTasks $this) {
/* 304 */     synchronized ($this) {
/* 305 */       $this.totaltasks_$eq($this.totaltasks() - 1);
/*     */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/parallel/ThreadPoolTasks}, name=$this} */
/*     */       return;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Function0 execute(ThreadPoolTasks $this, Task<?, ?> task) {
/* 309 */     ThreadPoolTasks.WrappedTask<?, ?> t = $this.newWrappedTask(task);
/* 312 */     t.start();
/* 314 */     return (Function0)new ThreadPoolTasks$$anonfun$execute$1($this, t);
/*     */   }
/*     */   
/*     */   public static Object executeAndWaitResult(ThreadPoolTasks $this, Task<?, ?> task) {
/* 322 */     ThreadPoolTasks.WrappedTask<?, ?> t = $this.newWrappedTask(task);
/* 325 */     t.start();
/* 327 */     t.sync();
/* 328 */     t.body().forwardThrowable();
/* 329 */     return t.body().result();
/*     */   }
/*     */   
/*     */   public static int parallelismLevel(ThreadPoolTasks $this) {
/* 332 */     return ThreadPoolTasks$.MODULE$.numCores();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\ThreadPoolTasks$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
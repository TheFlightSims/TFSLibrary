/*     */ package scala.collection.parallel;
/*     */ 
/*     */ import java.util.concurrent.Executor;
/*     */ import java.util.concurrent.ThreadPoolExecutor;
/*     */ import scala.Function0;
/*     */ import scala.Predef$;
/*     */ import scala.concurrent.ExecutionContext;
/*     */ import scala.concurrent.forkjoin.ForkJoinPool;
/*     */ import scala.concurrent.impl.ExecutionContextImpl;
/*     */ 
/*     */ public abstract class ExecutionContextTasks$class {
/*     */   public static ExecutionContext executionContext(ExecutionContextTasks $this) {
/* 538 */     return $this.environment();
/*     */   }
/*     */   
/*     */   public static void $init$(ExecutionContextTasks $this) {
/* 543 */     ExecutionContext executionContext = $this.executionContext();
/* 544 */     if (executionContext instanceof ExecutionContextImpl) {
/*     */       ThreadPoolTaskSupport threadPoolTaskSupport;
/* 544 */       ExecutionContextImpl executionContextImpl = (ExecutionContextImpl)executionContext;
/* 544 */       Executor executor = executionContextImpl.executor();
/* 545 */       if (executor instanceof ForkJoinPool) {
/* 545 */         ForkJoinPool forkJoinPool = (ForkJoinPool)executor;
/* 545 */         ForkJoinTaskSupport forkJoinTaskSupport = new ForkJoinTaskSupport(forkJoinPool);
/* 546 */       } else if (executor instanceof ThreadPoolExecutor) {
/* 546 */         ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor)executor;
/* 546 */         threadPoolTaskSupport = new ThreadPoolTaskSupport(threadPoolExecutor);
/*     */       } else {
/* 547 */         throw Predef$.MODULE$.$qmark$qmark$qmark();
/*     */       } 
/*     */       $this.scala$collection$parallel$ExecutionContextTasks$_setter_$driver_$eq(threadPoolTaskSupport);
/*     */       return;
/*     */     } 
/* 549 */     throw Predef$.MODULE$.$qmark$qmark$qmark();
/*     */   }
/*     */   
/*     */   public static Function0 execute(ExecutionContextTasks $this, Task<?, ?> task) {
/* 552 */     return $this.driver().execute(task);
/*     */   }
/*     */   
/*     */   public static Object executeAndWaitResult(ExecutionContextTasks $this, Task<?, ?> task) {
/* 554 */     return $this.driver().executeAndWaitResult(task);
/*     */   }
/*     */   
/*     */   public static int parallelismLevel(ExecutionContextTasks $this) {
/* 556 */     return $this.driver().parallelismLevel();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\ExecutionContextTasks$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
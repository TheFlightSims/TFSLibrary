/*    */ package scala.concurrent;
/*    */ 
/*    */ import java.util.concurrent.LinkedBlockingQueue;
/*    */ import java.util.concurrent.ThreadPoolExecutor;
/*    */ import java.util.concurrent.TimeUnit;
/*    */ 
/*    */ public final class TaskRunners$ {
/*    */   public static final TaskRunners$ MODULE$;
/*    */   
/*    */   private final FutureTaskRunner threadRunner;
/*    */   
/*    */   private final FutureTaskRunner threadPoolRunner;
/*    */   
/*    */   private TaskRunners$() {
/* 18 */     MODULE$ = this;
/* 20 */     this.threadRunner = 
/* 21 */       new ThreadRunner();
/* 24 */     int numCores = Runtime.getRuntime().availableProcessors();
/* 25 */     LinkedBlockingQueue<Runnable> workQueue = new LinkedBlockingQueue();
/* 27 */     ThreadPoolExecutor exec = new ThreadPoolExecutor(numCores, 
/* 28 */         numCores, 
/* 29 */         60000L, 
/* 30 */         TimeUnit.MILLISECONDS, 
/* 31 */         workQueue, 
/* 32 */         new ThreadPoolExecutor.CallerRunsPolicy());
/* 33 */     this.threadPoolRunner = JavaConversions$.MODULE$.asTaskRunner(exec);
/*    */   }
/*    */   
/*    */   public FutureTaskRunner threadRunner() {
/*    */     return this.threadRunner;
/*    */   }
/*    */   
/*    */   public FutureTaskRunner threadPoolRunner() {
/*    */     return this.threadPoolRunner;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\concurrent\TaskRunners$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
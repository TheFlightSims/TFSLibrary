/*    */ package scala.concurrent;
/*    */ 
/*    */ import java.util.concurrent.Callable;
/*    */ import java.util.concurrent.Executor;
/*    */ import java.util.concurrent.ExecutorService;
/*    */ import java.util.concurrent.Future;
/*    */ import scala.Function0;
/*    */ 
/*    */ public final class JavaConversions$ {
/*    */   public static final JavaConversions$ MODULE$;
/*    */   
/*    */   private JavaConversions$() {
/* 19 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public FutureTaskRunner asTaskRunner(ExecutorService exec) {
/* 23 */     return new JavaConversions$$anon$1(exec);
/*    */   }
/*    */   
/*    */   public static class JavaConversions$$anon$1 implements ThreadPoolRunner {
/*    */     private final ExecutorService exec$1;
/*    */     
/*    */     public <S> Callable<S> functionAsTask(Function0 fun) {
/* 23 */       return ThreadPoolRunner$class.functionAsTask(this, fun);
/*    */     }
/*    */     
/*    */     public <S> Function0<S> futureAsFunction(Future x) {
/* 23 */       return ThreadPoolRunner$class.futureAsFunction(this, x);
/*    */     }
/*    */     
/*    */     public <S> Future<S> submit(Callable task) {
/* 23 */       return ThreadPoolRunner$class.submit(this, task);
/*    */     }
/*    */     
/*    */     public <S> void execute(Callable task) {
/* 23 */       ThreadPoolRunner$class.execute(this, task);
/*    */     }
/*    */     
/*    */     public void managedBlock(ManagedBlocker blocker) {
/* 23 */       ThreadPoolRunner$class.managedBlock(this, blocker);
/*    */     }
/*    */     
/*    */     public JavaConversions$$anon$1(ExecutorService exec$1) {
/* 23 */       ThreadPoolRunner$class.$init$(this);
/*    */     }
/*    */     
/*    */     public ExecutorService executor() {
/* 25 */       return this.exec$1;
/*    */     }
/*    */     
/*    */     public void shutdown() {
/* 28 */       this.exec$1.shutdown();
/*    */     }
/*    */   }
/*    */   
/*    */   public TaskRunner asTaskRunner(Executor exec) {
/* 33 */     return new JavaConversions$$anon$2(exec);
/*    */   }
/*    */   
/*    */   public static class JavaConversions$$anon$2 implements TaskRunner {
/*    */     private final Executor exec$2;
/*    */     
/*    */     public JavaConversions$$anon$2(Executor exec$2) {}
/*    */     
/*    */     public <T> Runnable functionAsTask(Function0 fun) {
/* 36 */       return new JavaConversions$$anon$2$$anon$3(this, fun);
/*    */     }
/*    */     
/*    */     public class JavaConversions$$anon$2$$anon$3 implements Runnable {
/*    */       private final Function0 fun$1;
/*    */       
/*    */       public JavaConversions$$anon$2$$anon$3(JavaConversions$$anon$2 $outer, Function0 fun$1) {}
/*    */       
/*    */       public void run() {
/* 37 */         this.fun$1.apply();
/*    */       }
/*    */     }
/*    */     
/*    */     public <S> void execute(Runnable task) {
/* 41 */       this.exec$2.execute(task);
/*    */     }
/*    */     
/*    */     private void managedBlock(ManagedBlocker blocker) {
/* 45 */       blocker.block();
/*    */     }
/*    */     
/*    */     public void shutdown() {}
/*    */   }
/*    */   
/*    */   public ExecutionContextExecutorService asExecutionContext(ExecutorService exec) {
/* 57 */     return ExecutionContext$.MODULE$.fromExecutorService(exec);
/*    */   }
/*    */   
/*    */   public ExecutionContextExecutor asExecutionContext(Executor exec) {
/* 63 */     return ExecutionContext$.MODULE$.fromExecutor(exec);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\concurrent\JavaConversions$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
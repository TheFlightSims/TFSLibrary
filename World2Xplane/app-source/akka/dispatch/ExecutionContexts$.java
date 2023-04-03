/*    */ package akka.dispatch;
/*    */ 
/*    */ import akka.japi.Procedure;
/*    */ import java.util.concurrent.Executor;
/*    */ import java.util.concurrent.ExecutorService;
/*    */ import scala.Function1;
/*    */ import scala.Serializable;
/*    */ import scala.concurrent.ExecutionContextExecutor;
/*    */ import scala.concurrent.ExecutionContextExecutorService;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxedUnit;
/*    */ 
/*    */ public final class ExecutionContexts$ {
/*    */   public static final ExecutionContexts$ MODULE$;
/*    */   
/*    */   private ExecutionContexts$() {
/* 18 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public ExecutionContextExecutor fromExecutor(Executor executor) {
/* 27 */     return scala.concurrent.ExecutionContext$.MODULE$.fromExecutor(executor);
/*    */   }
/*    */   
/*    */   public ExecutionContextExecutor fromExecutor(Executor executor, Procedure errorReporter) {
/* 38 */     return scala.concurrent.ExecutionContext$.MODULE$.fromExecutor(executor, (Function1)new ExecutionContexts$$anonfun$fromExecutor$1(errorReporter));
/*    */   }
/*    */   
/*    */   public static class ExecutionContexts$$anonfun$fromExecutor$1 extends AbstractFunction1<Throwable, BoxedUnit> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final Procedure errorReporter$1;
/*    */     
/*    */     public final void apply(Throwable param) {
/* 38 */       this.errorReporter$1.apply(param);
/*    */     }
/*    */     
/*    */     public ExecutionContexts$$anonfun$fromExecutor$1(Procedure errorReporter$1) {}
/*    */   }
/*    */   
/*    */   public ExecutionContextExecutorService fromExecutorService(ExecutorService executorService) {
/* 48 */     return scala.concurrent.ExecutionContext$.MODULE$.fromExecutorService(executorService);
/*    */   }
/*    */   
/*    */   public ExecutionContextExecutorService fromExecutorService(ExecutorService executorService, Procedure errorReporter) {
/* 59 */     return scala.concurrent.ExecutionContext$.MODULE$.fromExecutorService(executorService, (Function1)new ExecutionContexts$$anonfun$fromExecutorService$1(errorReporter));
/*    */   }
/*    */   
/*    */   public static class ExecutionContexts$$anonfun$fromExecutorService$1 extends AbstractFunction1<Throwable, BoxedUnit> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final Procedure errorReporter$2;
/*    */     
/*    */     public final void apply(Throwable param) {
/* 59 */       this.errorReporter$2.apply(param);
/*    */     }
/*    */     
/*    */     public ExecutionContexts$$anonfun$fromExecutorService$1(Procedure errorReporter$2) {}
/*    */   }
/*    */   
/*    */   public ExecutionContextExecutor global() {
/* 64 */     return scala.concurrent.ExecutionContext$.MODULE$.global();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\ExecutionContexts$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package scala.concurrent;
/*    */ 
/*    */ import java.util.concurrent.Executor;
/*    */ import java.util.concurrent.ExecutorService;
/*    */ import scala.Function1;
/*    */ import scala.Serializable;
/*    */ import scala.concurrent.impl.ExecutionContextImpl;
/*    */ import scala.concurrent.impl.ExecutionContextImpl$;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxedUnit;
/*    */ 
/*    */ public final class ExecutionContext$ {
/*    */   public static final ExecutionContext$ MODULE$;
/*    */   
/*    */   private ExecutionContext$() {
/* 51 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public ExecutionContextExecutor global() {
/* 56 */     return ExecutionContext.Implicits$.MODULE$.global();
/*    */   }
/*    */   
/*    */   public ExecutionContextExecutorService fromExecutorService(ExecutorService e, Function1 reporter) {
/* 69 */     ExecutionContextImpl$ executionContextImpl$ = ExecutionContextImpl$.MODULE$;
/* 69 */     return (ExecutionContextExecutorService)new Object(e, reporter);
/*    */   }
/*    */   
/*    */   public ExecutionContextExecutorService fromExecutorService(ExecutorService e) {
/* 73 */     return fromExecutorService(e, defaultReporter());
/*    */   }
/*    */   
/*    */   public ExecutionContextExecutor fromExecutor(Executor e, Function1 reporter) {
/* 78 */     ExecutionContextImpl$ executionContextImpl$ = ExecutionContextImpl$.MODULE$;
/* 78 */     return (ExecutionContextExecutor)new ExecutionContextImpl(e, reporter);
/*    */   }
/*    */   
/*    */   public ExecutionContextExecutor fromExecutor(Executor e) {
/* 82 */     return fromExecutor(e, defaultReporter());
/*    */   }
/*    */   
/*    */   public Function1<Throwable, BoxedUnit> defaultReporter() {
/* 86 */     return (Function1<Throwable, BoxedUnit>)new ExecutionContext$$anonfun$defaultReporter$1();
/*    */   }
/*    */   
/*    */   public static class ExecutionContext$$anonfun$defaultReporter$1 extends AbstractFunction1<Throwable, BoxedUnit> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final void apply(Throwable t) {
/* 86 */       t.printStackTrace();
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\concurrent\ExecutionContext$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
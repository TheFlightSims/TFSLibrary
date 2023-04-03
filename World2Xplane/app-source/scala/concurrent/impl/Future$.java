/*    */ package scala.concurrent.impl;
/*    */ 
/*    */ import scala.Function0;
/*    */ import scala.concurrent.ExecutionContext;
/*    */ import scala.concurrent.Future;
/*    */ 
/*    */ public final class Future$ {
/*    */   public static final Future$ MODULE$;
/*    */   
/*    */   private Future$() {
/* 18 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <T> Future<T> apply(Function0<?> body, ExecutionContext executor) {
/* 30 */     Future.PromiseCompletingRunnable<T> runnable = new Future.PromiseCompletingRunnable(body);
/* 31 */     executor.prepare().execute(runnable);
/* 32 */     return runnable.promise().future();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\concurrent\impl\Future$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
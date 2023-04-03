/*    */ package scala.concurrent;
/*    */ 
/*    */ import scala.Function0;
/*    */ import scala.concurrent.impl.Future;
/*    */ import scala.concurrent.impl.Future$;
/*    */ 
/*    */ public final class package$ {
/*    */   public static final package$ MODULE$;
/*    */   
/*    */   private package$() {
/* 16 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <T> Future<T> future(Function0 body, ExecutionContext execctx) {
/* 30 */     Future$ future$ = Future$.MODULE$;
/* 30 */     Future$ future$1 = Future$.MODULE$;
/* 30 */     Future.PromiseCompletingRunnable runnable1 = new Future.PromiseCompletingRunnable(body);
/* 30 */     execctx.prepare().execute((Runnable)runnable1);
/* 30 */     return (Future<T>)runnable1.promise().future();
/*    */   }
/*    */   
/*    */   public <T> Promise<T> promise() {
/* 37 */     return Promise$.MODULE$.apply();
/*    */   }
/*    */   
/*    */   public <T> T blocking(Function0<T> body) throws Exception {
/* 50 */     return BlockContext$.MODULE$.current().blockOn(body, AwaitPermission$.MODULE$);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\concurrent\package$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
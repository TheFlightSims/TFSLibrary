/*    */ package scala.concurrent.impl;
/*    */ 
/*    */ import scala.Function0;
/*    */ import scala.Option;
/*    */ import scala.concurrent.ExecutionContext;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.util.Failure;
/*    */ import scala.util.Try;
/*    */ import scala.util.control.NonFatal$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001q;a!\001\002\t\002\021A\021A\002$viV\024XM\003\002\004\t\005!\021.\0349m\025\t)a!\001\006d_:\034WO\035:f]RT\021aB\001\006g\016\fG.\031\t\003\023)i\021A\001\004\007\027\tA\t\001\002\007\003\r\031+H/\036:f'\tQQ\002\005\002\017\0375\ta!\003\002\021\r\t1\021I\\=SK\032DQA\005\006\005\002Q\ta\001P5oSRt4\001\001\013\002\021\031!aC\003\001\030\005e\001&o\\7jg\026\034u.\0349mKRLgn\032*v]:\f'\r\\3\026\005aY3cA\013\032CA\021!dH\007\0027)\021A$H\001\005Y\006twMC\001\037\003\021Q\027M^1\n\005\001Z\"AB(cU\026\034G\017\005\002\033E%\0211e\007\002\t%Vtg.\0312mK\"AQ%\006B\001J\003%a%\001\003c_\022L\bc\001\b(S%\021\001F\002\002\ty\tLh.Y7f}A\021!f\013\007\001\t\025aSC1\001.\005\005!\026C\001\0302!\tqq&\003\0021\r\t9aj\034;iS:<\007C\001\b3\023\t\031dAA\002B]fDQAE\013\005\002U\"\"A\016\035\021\007]*\022&D\001\013\021\031)C\007\"a\001M!9!(\006b\001\n\003Y\024a\0029s_6L7/Z\013\002yA\031Q\bQ\025\017\005%q\024BA \003\003\035\001&o\\7jg\026L!!\021\"\003\035\021+g-Y;miB\023x.\\5tK*\021qH\001\005\007\tV\001\013\021\002\037\002\021A\024x.\\5tK\002BQAR\013\005B\035\0131A];o)\005A\005C\001\bJ\023\tQeA\001\003V]&$\b\"\002'\013\t\003i\025!B1qa2LXC\001(U)\ty%\f\006\002Q+B\031\021KU*\016\003\021I!a\003\003\021\005)\"F!\002\027L\005\004i\003\"\002,L\001\b9\026\001C3yK\016,Ho\034:\021\005EC\026BA-\005\005A)\0050Z2vi&|gnQ8oi\026DH\017\003\004&\027\022\005\ra\027\t\004\035\035\032\006")
/*    */ public final class Future {
/*    */   public static <T> scala.concurrent.Future<T> apply(Function0<T> paramFunction0, ExecutionContext paramExecutionContext) {
/*    */     return Future$.MODULE$.apply(paramFunction0, paramExecutionContext);
/*    */   }
/*    */   
/*    */   public static class PromiseCompletingRunnable<T> implements Runnable {
/*    */     private final Function0<T> body;
/*    */     
/* 20 */     private final Promise.DefaultPromise<T> promise = new Promise.DefaultPromise<T>();
/*    */     
/*    */     public Promise.DefaultPromise<T> promise() {
/* 20 */       return this.promise;
/*    */     }
/*    */     
/*    */     public void run() {
/* 23 */       promise().complete(
/* 24 */           liftedTree1$1());
/*    */     }
/*    */     
/*    */     private final Try liftedTree1$1() {
/*    */       try {
/*    */       
/*    */       } finally {
/* 24 */         Exception exception = null;
/* 24 */         Option option = NonFatal$.MODULE$.unapply(exception);
/*    */       } 
/* 24 */       return (Try)new Failure((Throwable)option.get());
/*    */     }
/*    */     
/*    */     public PromiseCompletingRunnable(Function0<T> body) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\concurrent\impl\Future.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
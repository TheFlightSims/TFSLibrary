/*    */ package akka.pattern;
/*    */ 
/*    */ import akka.actor.Scheduler;
/*    */ import scala.Function0;
/*    */ import scala.Option;
/*    */ import scala.concurrent.ExecutionContext;
/*    */ import scala.concurrent.Future;
/*    */ import scala.concurrent.Promise;
/*    */ import scala.concurrent.Promise$;
/*    */ import scala.concurrent.duration.FiniteDuration;
/*    */ import scala.util.control.NonFatal$;
/*    */ 
/*    */ public abstract class FutureTimeoutSupport$class {
/*    */   public static void $init$(FutureTimeoutSupport $this) {}
/*    */   
/*    */   public static Future after(FutureTimeoutSupport $this, FiniteDuration duration, Scheduler using, Function0 value, ExecutionContext ec) {
/* 19 */     if (duration.isFinite() && duration.length() < 1L) {
/*    */       try {
/*    */       
/*    */       } finally {
/*    */         Future future;
/* 20 */         Exception exception1 = null, exception2 = exception1;
/* 20 */         Option option = NonFatal$.MODULE$.unapply(exception2);
/* 20 */         if (option.isEmpty())
/* 20 */           throw exception1; 
/* 20 */         Throwable t = (Throwable)option.get();
/*    */       } 
/*    */     } else {
/* 22 */       Promise p = Promise$.MODULE$.apply();
/* 23 */       using.scheduleOnce(duration, (Function0)new FutureTimeoutSupport$$anonfun$after$1($this, p, value), ec);
/*    */     } 
/* 24 */     return p.future();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\pattern\FutureTimeoutSupport$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
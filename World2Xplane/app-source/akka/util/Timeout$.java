/*    */ package akka.util;
/*    */ 
/*    */ import java.util.concurrent.TimeUnit;
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.concurrent.duration.FiniteDuration;
/*    */ 
/*    */ public final class Timeout$ implements Serializable {
/*    */   public static final Timeout$ MODULE$;
/*    */   
/*    */   private final Timeout zero;
/*    */   
/*    */   public Timeout apply(FiniteDuration duration) {
/* 14 */     return new Timeout(duration);
/*    */   }
/*    */   
/*    */   public Option<FiniteDuration> unapply(Timeout x$0) {
/* 14 */     return (x$0 == null) ? (Option<FiniteDuration>)scala.None$.MODULE$ : (Option<FiniteDuration>)new Some(x$0.duration());
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 31 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private Timeout$() {
/* 31 */     MODULE$ = this;
/* 36 */     this.zero = new Timeout(scala.concurrent.duration.Duration$.MODULE$.Zero());
/*    */   }
/*    */   
/*    */   public Timeout zero() {
/* 36 */     return this.zero;
/*    */   }
/*    */   
/*    */   public Timeout apply(long timeout) {
/* 42 */     return new Timeout(timeout);
/*    */   }
/*    */   
/*    */   public Timeout apply(long length, TimeUnit unit) {
/* 47 */     return new Timeout(length, unit);
/*    */   }
/*    */   
/*    */   public Timeout durationToTimeout(FiniteDuration duration) {
/* 49 */     return new Timeout(duration);
/*    */   }
/*    */   
/*    */   public Timeout intToTimeout(int timeout) {
/* 50 */     return new Timeout(timeout);
/*    */   }
/*    */   
/*    */   public Timeout longToTimeout(long timeout) {
/* 51 */     return new Timeout(timeout);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akk\\util\Timeout$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
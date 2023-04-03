/*    */ package scala.concurrent.duration;
/*    */ 
/*    */ import java.util.concurrent.TimeUnit;
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ 
/*    */ public final class Deadline$ implements Serializable {
/*    */   public static final Deadline$ MODULE$;
/*    */   
/*    */   public Deadline apply(FiniteDuration time) {
/* 26 */     return new Deadline(time);
/*    */   }
/*    */   
/*    */   public Option<FiniteDuration> unapply(Deadline x$0) {
/* 26 */     return (x$0 == null) ? (Option<FiniteDuration>)scala.None$.MODULE$ : (Option<FiniteDuration>)new Some(x$0.time());
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 66 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private Deadline$() {
/* 66 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public Deadline now() {
/* 72 */     return new Deadline(Duration$.MODULE$.apply(System.nanoTime(), TimeUnit.NANOSECONDS));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\concurrent\duration\Deadline$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
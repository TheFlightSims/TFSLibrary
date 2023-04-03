/*     */ package akka.actor;
/*     */ 
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple3;
/*     */ import scala.concurrent.duration.Duration;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class OneForOneStrategy$ implements Serializable {
/*     */   public static final OneForOneStrategy$ MODULE$;
/*     */   
/*     */   public final String toString() {
/* 460 */     return "OneForOneStrategy";
/*     */   }
/*     */   
/*     */   public OneForOneStrategy apply(int maxNrOfRetries, Duration withinTimeRange, boolean loggingEnabled, PartialFunction<Throwable, SupervisorStrategy.Directive> decider) {
/* 460 */     return new OneForOneStrategy(maxNrOfRetries, withinTimeRange, loggingEnabled, decider);
/*     */   }
/*     */   
/*     */   public Option<Tuple3<Object, Duration, Object>> unapply(OneForOneStrategy x$0) {
/* 460 */     return (x$0 == null) ? (Option<Tuple3<Object, Duration, Object>>)scala.None$.MODULE$ : (Option<Tuple3<Object, Duration, Object>>)new Some(new Tuple3(BoxesRunTime.boxToInteger(x$0.maxNrOfRetries()), x$0.withinTimeRange(), BoxesRunTime.boxToBoolean(x$0.loggingEnabled())));
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 460 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private OneForOneStrategy$() {
/* 460 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public int apply$default$1() {
/* 461 */     return -1;
/*     */   }
/*     */   
/*     */   public int $lessinit$greater$default$1() {
/* 461 */     return -1;
/*     */   }
/*     */   
/*     */   public Duration apply$default$2() {
/* 462 */     return (Duration)scala.concurrent.duration.Duration$.MODULE$.Inf();
/*     */   }
/*     */   
/*     */   public Duration $lessinit$greater$default$2() {
/* 462 */     return (Duration)scala.concurrent.duration.Duration$.MODULE$.Inf();
/*     */   }
/*     */   
/*     */   public boolean apply$default$3() {
/* 463 */     return true;
/*     */   }
/*     */   
/*     */   public boolean $lessinit$greater$default$3() {
/* 463 */     return true;
/*     */   }
/*     */   
/*     */   public class $anonfun$5 extends AbstractFunction1<Duration, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final int apply(Duration x$7) {
/* 512 */       return (int)x$7.toMillis();
/*     */     }
/*     */     
/*     */     public $anonfun$5(OneForOneStrategy $outer) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\OneForOneStrategy$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package akka.actor;
/*     */ 
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple3;
/*     */ import scala.concurrent.duration.Duration;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class AllForOneStrategy$ implements Serializable {
/*     */   public static final AllForOneStrategy$ MODULE$;
/*     */   
/*     */   public final String toString() {
/* 382 */     return "AllForOneStrategy";
/*     */   }
/*     */   
/*     */   public AllForOneStrategy apply(int maxNrOfRetries, Duration withinTimeRange, boolean loggingEnabled, PartialFunction<Throwable, SupervisorStrategy.Directive> decider) {
/* 382 */     return new AllForOneStrategy(maxNrOfRetries, withinTimeRange, loggingEnabled, decider);
/*     */   }
/*     */   
/*     */   public Option<Tuple3<Object, Duration, Object>> unapply(AllForOneStrategy x$0) {
/* 382 */     return (x$0 == null) ? (Option<Tuple3<Object, Duration, Object>>)scala.None$.MODULE$ : (Option<Tuple3<Object, Duration, Object>>)new Some(new Tuple3(BoxesRunTime.boxToInteger(x$0.maxNrOfRetries()), x$0.withinTimeRange(), BoxesRunTime.boxToBoolean(x$0.loggingEnabled())));
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 382 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private AllForOneStrategy$() {
/* 382 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public int $lessinit$greater$default$1() {
/* 383 */     return -1;
/*     */   }
/*     */   
/*     */   public int apply$default$1() {
/* 383 */     return -1;
/*     */   }
/*     */   
/*     */   public Duration $lessinit$greater$default$2() {
/* 384 */     return (Duration)scala.concurrent.duration.Duration$.MODULE$.Inf();
/*     */   }
/*     */   
/*     */   public Duration apply$default$2() {
/* 384 */     return (Duration)scala.concurrent.duration.Duration$.MODULE$.Inf();
/*     */   }
/*     */   
/*     */   public boolean $lessinit$greater$default$3() {
/* 385 */     return true;
/*     */   }
/*     */   
/*     */   public boolean apply$default$3() {
/* 385 */     return true;
/*     */   }
/*     */   
/*     */   public class $anonfun$4 extends AbstractFunction1<Duration, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final int apply(Duration x$5) {
/* 434 */       return (int)x$5.toMillis();
/*     */     }
/*     */     
/*     */     public $anonfun$4(AllForOneStrategy $outer) {}
/*     */   }
/*     */   
/*     */   public class AllForOneStrategy$$anonfun$processFailure$1 extends AbstractFunction1<ChildRestartStats, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(ChildRestartStats x$6) {
/* 440 */       return x$6.requestRestartPermission(this.$outer.akka$actor$AllForOneStrategy$$retriesWindow());
/*     */     }
/*     */     
/*     */     public AllForOneStrategy$$anonfun$processFailure$1(AllForOneStrategy $outer) {}
/*     */   }
/*     */   
/*     */   public class AllForOneStrategy$$anonfun$processFailure$2 extends AbstractFunction1<ChildRestartStats, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ActorRef child$1;
/*     */     
/*     */     private final Throwable cause$1;
/*     */     
/*     */     public final void apply(ChildRestartStats crs) {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: getfield $outer : Lakka/actor/AllForOneStrategy;
/*     */       //   4: aload_1
/*     */       //   5: invokevirtual child : ()Lakka/actor/ActorRef;
/*     */       //   8: aload_0
/*     */       //   9: getfield cause$1 : Ljava/lang/Throwable;
/*     */       //   12: aload_1
/*     */       //   13: invokevirtual child : ()Lakka/actor/ActorRef;
/*     */       //   16: aload_0
/*     */       //   17: getfield child$1 : Lakka/actor/ActorRef;
/*     */       //   20: astore_2
/*     */       //   21: dup
/*     */       //   22: ifnonnull -> 33
/*     */       //   25: pop
/*     */       //   26: aload_2
/*     */       //   27: ifnull -> 40
/*     */       //   30: goto -> 44
/*     */       //   33: aload_2
/*     */       //   34: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   37: ifeq -> 44
/*     */       //   40: iconst_0
/*     */       //   41: goto -> 45
/*     */       //   44: iconst_1
/*     */       //   45: invokevirtual restartChild : (Lakka/actor/ActorRef;Ljava/lang/Throwable;Z)V
/*     */       //   48: return
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #441	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	49	0	this	Lakka/actor/AllForOneStrategy$$anonfun$processFailure$2;
/*     */       //   0	49	1	crs	Lakka/actor/ChildRestartStats;
/*     */     }
/*     */     
/*     */     public AllForOneStrategy$$anonfun$processFailure$2(AllForOneStrategy $outer, ActorRef child$1, Throwable cause$1) {}
/*     */   }
/*     */   
/*     */   public class AllForOneStrategy$$anonfun$processFailure$3 extends AbstractFunction1<ChildRestartStats, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ActorContext context$1;
/*     */     
/*     */     public final void apply(ChildRestartStats c) {
/* 443 */       this.context$1.stop(c.child());
/*     */     }
/*     */     
/*     */     public AllForOneStrategy$$anonfun$processFailure$3(AllForOneStrategy $outer, ActorContext context$1) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\AllForOneStrategy$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
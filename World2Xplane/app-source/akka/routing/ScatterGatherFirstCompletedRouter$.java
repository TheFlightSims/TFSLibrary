/*     */ package akka.routing;
/*     */ 
/*     */ import akka.actor.ActorRef;
/*     */ import akka.actor.SupervisorStrategy;
/*     */ import scala.Function1;
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple6;
/*     */ import scala.collection.immutable.Iterable;
/*     */ import scala.concurrent.duration.FiniteDuration;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class ScatterGatherFirstCompletedRouter$ implements Serializable {
/*     */   public static final ScatterGatherFirstCompletedRouter$ MODULE$;
/*     */   
/*     */   private Object readResolve() {
/* 446 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private ScatterGatherFirstCompletedRouter$() {
/* 446 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public ScatterGatherFirstCompletedRouter apply(Iterable routees, FiniteDuration within) {
/* 451 */     Iterable<String> x$126 = (Iterable)routees.map((Function1)new ScatterGatherFirstCompletedRouter$$anonfun$5(), scala.collection.immutable.Iterable$.MODULE$.canBuildFrom());
/* 451 */     FiniteDuration x$127 = within;
/* 451 */     int x$128 = $lessinit$greater$default$1();
/* 451 */     Option<Resizer> x$129 = $lessinit$greater$default$4();
/* 451 */     String x$130 = $lessinit$greater$default$5();
/* 451 */     SupervisorStrategy x$131 = $lessinit$greater$default$6();
/* 451 */     return new ScatterGatherFirstCompletedRouter(x$128, x$126, x$127, x$129, x$130, x$131);
/*     */   }
/*     */   
/*     */   public static class ScatterGatherFirstCompletedRouter$$anonfun$5 extends AbstractFunction1<ActorRef, String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String apply(ActorRef x$5) {
/* 451 */       return x$5.path().toString();
/*     */     }
/*     */   }
/*     */   
/*     */   public ScatterGatherFirstCompletedRouter create(Iterable routees, FiniteDuration within) {
/* 457 */     return apply((Iterable<ActorRef>)akka.japi.Util$.MODULE$.immutableSeq(routees), within);
/*     */   }
/*     */   
/*     */   public int apply$default$1() {
/* 491 */     return 0;
/*     */   }
/*     */   
/*     */   public Iterable<String> apply$default$2() {
/* 491 */     return (Iterable<String>)scala.collection.immutable.Nil$.MODULE$;
/*     */   }
/*     */   
/*     */   public ScatterGatherFirstCompletedRouter apply(int nrOfInstances, Iterable<String> routees, FiniteDuration within, Option<Resizer> resizer, String routerDispatcher, SupervisorStrategy supervisorStrategy) {
/* 491 */     return new ScatterGatherFirstCompletedRouter(nrOfInstances, routees, within, resizer, routerDispatcher, supervisorStrategy);
/*     */   }
/*     */   
/*     */   public Option<Tuple6<Object, Iterable<String>, FiniteDuration, Option<Resizer>, String, SupervisorStrategy>> unapply(ScatterGatherFirstCompletedRouter x$0) {
/* 491 */     return (x$0 == null) ? (Option<Tuple6<Object, Iterable<String>, FiniteDuration, Option<Resizer>, String, SupervisorStrategy>>)scala.None$.MODULE$ : (Option<Tuple6<Object, Iterable<String>, FiniteDuration, Option<Resizer>, String, SupervisorStrategy>>)new Some(new Tuple6(BoxesRunTime.boxToInteger(x$0.nrOfInstances()), x$0.routees(), x$0.within(), x$0.resizer(), x$0.routerDispatcher(), x$0.supervisorStrategy()));
/*     */   }
/*     */   
/*     */   public int $lessinit$greater$default$1() {
/* 491 */     return 0;
/*     */   }
/*     */   
/*     */   public Iterable<String> $lessinit$greater$default$2() {
/* 491 */     return (Iterable<String>)scala.collection.immutable.Nil$.MODULE$;
/*     */   }
/*     */   
/*     */   public Option<Resizer> apply$default$4() {
/* 492 */     return (Option<Resizer>)scala.None$.MODULE$;
/*     */   }
/*     */   
/*     */   public Option<Resizer> $lessinit$greater$default$4() {
/* 492 */     return (Option<Resizer>)scala.None$.MODULE$;
/*     */   }
/*     */   
/*     */   public String apply$default$5() {
/* 493 */     return "akka.actor.default-dispatcher";
/*     */   }
/*     */   
/*     */   public String $lessinit$greater$default$5() {
/* 493 */     return "akka.actor.default-dispatcher";
/*     */   }
/*     */   
/*     */   public SupervisorStrategy apply$default$6() {
/* 494 */     return Pool$.MODULE$.defaultSupervisorStrategy();
/*     */   }
/*     */   
/*     */   public SupervisorStrategy $lessinit$greater$default$6() {
/* 494 */     return Pool$.MODULE$.defaultSupervisorStrategy();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\ScatterGatherFirstCompletedRouter$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
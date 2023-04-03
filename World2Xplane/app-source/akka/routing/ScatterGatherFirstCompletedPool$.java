/*     */ package akka.routing;
/*     */ 
/*     */ import akka.actor.SupervisorStrategy;
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple6;
/*     */ import scala.concurrent.duration.FiniteDuration;
/*     */ import scala.runtime.AbstractFunction6;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class ScatterGatherFirstCompletedPool$ extends AbstractFunction6<Object, Option<Resizer>, FiniteDuration, SupervisorStrategy, String, Object, ScatterGatherFirstCompletedPool> implements Serializable {
/*     */   public static final ScatterGatherFirstCompletedPool$ MODULE$;
/*     */   
/*     */   public final String toString() {
/*  95 */     return "ScatterGatherFirstCompletedPool";
/*     */   }
/*     */   
/*     */   public ScatterGatherFirstCompletedPool apply(int nrOfInstances, Option<Resizer> resizer, FiniteDuration within, SupervisorStrategy supervisorStrategy, String routerDispatcher, boolean usePoolDispatcher) {
/*  95 */     return new ScatterGatherFirstCompletedPool(nrOfInstances, resizer, within, supervisorStrategy, routerDispatcher, usePoolDispatcher);
/*     */   }
/*     */   
/*     */   public Option<Tuple6<Object, Option<Resizer>, FiniteDuration, SupervisorStrategy, String, Object>> unapply(ScatterGatherFirstCompletedPool x$0) {
/*  95 */     return (x$0 == null) ? (Option<Tuple6<Object, Option<Resizer>, FiniteDuration, SupervisorStrategy, String, Object>>)scala.None$.MODULE$ : (Option<Tuple6<Object, Option<Resizer>, FiniteDuration, SupervisorStrategy, String, Object>>)new Some(new Tuple6(BoxesRunTime.boxToInteger(x$0.nrOfInstances()), x$0.resizer(), x$0.within(), x$0.supervisorStrategy(), x$0.routerDispatcher(), BoxesRunTime.boxToBoolean(x$0.usePoolDispatcher())));
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/*  95 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private ScatterGatherFirstCompletedPool$() {
/*  95 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public Option<Resizer> $lessinit$greater$default$2() {
/*  96 */     return (Option<Resizer>)scala.None$.MODULE$;
/*     */   }
/*     */   
/*     */   public Option<Resizer> apply$default$2() {
/*  96 */     return (Option<Resizer>)scala.None$.MODULE$;
/*     */   }
/*     */   
/*     */   public SupervisorStrategy $lessinit$greater$default$4() {
/*  98 */     return Pool$.MODULE$.defaultSupervisorStrategy();
/*     */   }
/*     */   
/*     */   public SupervisorStrategy apply$default$4() {
/*  98 */     return Pool$.MODULE$.defaultSupervisorStrategy();
/*     */   }
/*     */   
/*     */   public String $lessinit$greater$default$5() {
/*  99 */     return "akka.actor.default-dispatcher";
/*     */   }
/*     */   
/*     */   public String apply$default$5() {
/*  99 */     return "akka.actor.default-dispatcher";
/*     */   }
/*     */   
/*     */   public boolean $lessinit$greater$default$6() {
/* 100 */     return false;
/*     */   }
/*     */   
/*     */   public boolean apply$default$6() {
/* 100 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\ScatterGatherFirstCompletedPool$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
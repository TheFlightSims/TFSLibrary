/*     */ package akka.routing;
/*     */ 
/*     */ import akka.actor.SupervisorStrategy;
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple5;
/*     */ import scala.runtime.AbstractFunction5;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class SmallestMailboxPool$ extends AbstractFunction5<Object, Option<Resizer>, SupervisorStrategy, String, Object, SmallestMailboxPool> implements Serializable {
/*     */   public static final SmallestMailboxPool$ MODULE$;
/*     */   
/*     */   public final String toString() {
/* 176 */     return "SmallestMailboxPool";
/*     */   }
/*     */   
/*     */   public SmallestMailboxPool apply(int nrOfInstances, Option<Resizer> resizer, SupervisorStrategy supervisorStrategy, String routerDispatcher, boolean usePoolDispatcher) {
/* 176 */     return new SmallestMailboxPool(nrOfInstances, resizer, supervisorStrategy, routerDispatcher, usePoolDispatcher);
/*     */   }
/*     */   
/*     */   public Option<Tuple5<Object, Option<Resizer>, SupervisorStrategy, String, Object>> unapply(SmallestMailboxPool x$0) {
/* 176 */     return (x$0 == null) ? (Option<Tuple5<Object, Option<Resizer>, SupervisorStrategy, String, Object>>)scala.None$.MODULE$ : (Option<Tuple5<Object, Option<Resizer>, SupervisorStrategy, String, Object>>)new Some(new Tuple5(BoxesRunTime.boxToInteger(x$0.nrOfInstances()), x$0.resizer(), x$0.supervisorStrategy(), x$0.routerDispatcher(), BoxesRunTime.boxToBoolean(x$0.usePoolDispatcher())));
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 176 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private SmallestMailboxPool$() {
/* 176 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public Option<Resizer> $lessinit$greater$default$2() {
/* 177 */     return (Option<Resizer>)scala.None$.MODULE$;
/*     */   }
/*     */   
/*     */   public Option<Resizer> apply$default$2() {
/* 177 */     return (Option<Resizer>)scala.None$.MODULE$;
/*     */   }
/*     */   
/*     */   public SupervisorStrategy $lessinit$greater$default$3() {
/* 178 */     return Pool$.MODULE$.defaultSupervisorStrategy();
/*     */   }
/*     */   
/*     */   public SupervisorStrategy apply$default$3() {
/* 178 */     return Pool$.MODULE$.defaultSupervisorStrategy();
/*     */   }
/*     */   
/*     */   public String $lessinit$greater$default$4() {
/* 179 */     return "akka.actor.default-dispatcher";
/*     */   }
/*     */   
/*     */   public String apply$default$4() {
/* 179 */     return "akka.actor.default-dispatcher";
/*     */   }
/*     */   
/*     */   public boolean $lessinit$greater$default$5() {
/* 180 */     return false;
/*     */   }
/*     */   
/*     */   public boolean apply$default$5() {
/* 180 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\SmallestMailboxPool$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
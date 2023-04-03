/*     */ package akka.routing;
/*     */ 
/*     */ import akka.actor.SupervisorStrategy;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple7;
/*     */ import scala.runtime.AbstractFunction7;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class ConsistentHashingPool$ extends AbstractFunction7<Object, Option<Resizer>, Object, PartialFunction<Object, Object>, SupervisorStrategy, String, Object, ConsistentHashingPool> implements Serializable {
/*     */   public static final ConsistentHashingPool$ MODULE$;
/*     */   
/*     */   public final String toString() {
/* 275 */     return "ConsistentHashingPool";
/*     */   }
/*     */   
/*     */   public ConsistentHashingPool apply(int nrOfInstances, Option<Resizer> resizer, int virtualNodesFactor, PartialFunction<Object, Object> hashMapping, SupervisorStrategy supervisorStrategy, String routerDispatcher, boolean usePoolDispatcher) {
/* 275 */     return new ConsistentHashingPool(nrOfInstances, resizer, virtualNodesFactor, hashMapping, supervisorStrategy, routerDispatcher, usePoolDispatcher);
/*     */   }
/*     */   
/*     */   public Option<Tuple7<Object, Option<Resizer>, Object, PartialFunction<Object, Object>, SupervisorStrategy, String, Object>> unapply(ConsistentHashingPool x$0) {
/* 275 */     return (x$0 == null) ? (Option<Tuple7<Object, Option<Resizer>, Object, PartialFunction<Object, Object>, SupervisorStrategy, String, Object>>)scala.None$.MODULE$ : (Option<Tuple7<Object, Option<Resizer>, Object, PartialFunction<Object, Object>, SupervisorStrategy, String, Object>>)new Some(new Tuple7(BoxesRunTime.boxToInteger(x$0.nrOfInstances()), x$0.resizer(), BoxesRunTime.boxToInteger(x$0.virtualNodesFactor()), x$0.hashMapping(), x$0.supervisorStrategy(), x$0.routerDispatcher(), BoxesRunTime.boxToBoolean(x$0.usePoolDispatcher())));
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 275 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private ConsistentHashingPool$() {
/* 275 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public Option<Resizer> $lessinit$greater$default$2() {
/* 276 */     return (Option<Resizer>)scala.None$.MODULE$;
/*     */   }
/*     */   
/*     */   public Option<Resizer> apply$default$2() {
/* 276 */     return (Option<Resizer>)scala.None$.MODULE$;
/*     */   }
/*     */   
/*     */   public int $lessinit$greater$default$3() {
/* 277 */     return 0;
/*     */   }
/*     */   
/*     */   public int apply$default$3() {
/* 277 */     return 0;
/*     */   }
/*     */   
/*     */   public PartialFunction<Object, Object> $lessinit$greater$default$4() {
/* 278 */     return ConsistentHashingRouter.emptyConsistentHashMapping$.MODULE$;
/*     */   }
/*     */   
/*     */   public PartialFunction<Object, Object> apply$default$4() {
/* 278 */     return ConsistentHashingRouter.emptyConsistentHashMapping$.MODULE$;
/*     */   }
/*     */   
/*     */   public SupervisorStrategy $lessinit$greater$default$5() {
/* 279 */     return Pool$.MODULE$.defaultSupervisorStrategy();
/*     */   }
/*     */   
/*     */   public SupervisorStrategy apply$default$5() {
/* 279 */     return Pool$.MODULE$.defaultSupervisorStrategy();
/*     */   }
/*     */   
/*     */   public String $lessinit$greater$default$6() {
/* 280 */     return "akka.actor.default-dispatcher";
/*     */   }
/*     */   
/*     */   public String apply$default$6() {
/* 280 */     return "akka.actor.default-dispatcher";
/*     */   }
/*     */   
/*     */   public boolean $lessinit$greater$default$7() {
/* 281 */     return false;
/*     */   }
/*     */   
/*     */   public boolean apply$default$7() {
/* 281 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\ConsistentHashingPool$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
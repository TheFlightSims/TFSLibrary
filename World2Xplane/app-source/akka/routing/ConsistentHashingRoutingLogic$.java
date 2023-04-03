/*     */ package akka.routing;
/*     */ 
/*     */ import akka.actor.ActorSystem;
/*     */ import akka.actor.Address;
/*     */ import akka.actor.ExtendedActorSystem;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple3;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class ConsistentHashingRoutingLogic$ implements Serializable {
/*     */   public static final ConsistentHashingRoutingLogic$ MODULE$;
/*     */   
/*     */   private Object readResolve() {
/* 116 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private ConsistentHashingRoutingLogic$() {
/* 116 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public Address defaultAddress(ActorSystem system) {
/* 121 */     return ((ExtendedActorSystem)system).provider().getDefaultAddress();
/*     */   }
/*     */   
/*     */   public ConsistentHashingRoutingLogic apply(ActorSystem system, int virtualNodesFactor, PartialFunction<Object, Object> hashMapping) {
/* 153 */     return new ConsistentHashingRoutingLogic(system, virtualNodesFactor, hashMapping);
/*     */   }
/*     */   
/*     */   public Option<Tuple3<ActorSystem, Object, PartialFunction<Object, Object>>> unapply(ConsistentHashingRoutingLogic x$0) {
/* 153 */     return (x$0 == null) ? (Option<Tuple3<ActorSystem, Object, PartialFunction<Object, Object>>>)scala.None$.MODULE$ : (Option<Tuple3<ActorSystem, Object, PartialFunction<Object, Object>>>)new Some(new Tuple3(x$0.system(), BoxesRunTime.boxToInteger(x$0.virtualNodesFactor()), x$0.hashMapping()));
/*     */   }
/*     */   
/*     */   public int $lessinit$greater$default$2() {
/* 155 */     return 0;
/*     */   }
/*     */   
/*     */   public int apply$default$2() {
/* 155 */     return 0;
/*     */   }
/*     */   
/*     */   public PartialFunction<Object, Object> $lessinit$greater$default$3() {
/* 156 */     return ConsistentHashingRouter.emptyConsistentHashMapping$.MODULE$;
/*     */   }
/*     */   
/*     */   public PartialFunction<Object, Object> apply$default$3() {
/* 156 */     return ConsistentHashingRouter.emptyConsistentHashMapping$.MODULE$;
/*     */   }
/*     */   
/*     */   public class ConsistentHashingRoutingLogic$$anonfun$2 extends AbstractFunction1<Routee, ConsistentRoutee> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final ConsistentRoutee apply(Routee x$3) {
/* 203 */       return new ConsistentRoutee(x$3, this.$outer.akka$routing$ConsistentHashingRoutingLogic$$selfAddress());
/*     */     }
/*     */     
/*     */     public ConsistentHashingRoutingLogic$$anonfun$2(ConsistentHashingRoutingLogic $outer) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\ConsistentHashingRoutingLogic$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
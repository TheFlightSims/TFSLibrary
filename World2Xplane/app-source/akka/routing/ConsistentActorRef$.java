/*     */ package akka.routing;
/*     */ 
/*     */ import akka.actor.ActorRef;
/*     */ import akka.actor.Address;
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ 
/*     */ public final class ConsistentActorRef$ extends AbstractFunction2<ActorRef, Address, ConsistentActorRef> implements Serializable {
/*     */   public static final ConsistentActorRef$ MODULE$;
/*     */   
/*     */   public final String toString() {
/* 564 */     return "ConsistentActorRef";
/*     */   }
/*     */   
/*     */   public ConsistentActorRef apply(ActorRef actorRef, Address selfAddress) {
/* 564 */     return new ConsistentActorRef(actorRef, selfAddress);
/*     */   }
/*     */   
/*     */   public Option<Tuple2<ActorRef, Address>> unapply(ConsistentActorRef x$0) {
/* 564 */     return (x$0 == null) ? (Option<Tuple2<ActorRef, Address>>)scala.None$.MODULE$ : (Option<Tuple2<ActorRef, Address>>)new Some(new Tuple2(x$0.actorRef(), x$0.selfAddress()));
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 564 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private ConsistentActorRef$() {
/* 564 */     MODULE$ = this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\ConsistentActorRef$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
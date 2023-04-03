/*     */ package akka.actor;
/*     */ 
/*     */ import scala.Serializable;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ 
/*     */ public final class Actor$ {
/*     */   public static final Actor$ MODULE$;
/*     */   
/*     */   private final ActorRef noSender;
/*     */   
/*     */   private Actor$() {
/* 323 */     MODULE$ = this;
/* 345 */     null;
/* 345 */     this.noSender = null;
/*     */   }
/*     */   
/*     */   public final ActorRef noSender() {
/* 345 */     return this.noSender;
/*     */   }
/*     */   
/*     */   public class Actor$$anonfun$aroundReceive$1 extends AbstractFunction1<Object, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply(Object message) {
/* 465 */       this.$outer.unhandled(message);
/*     */     }
/*     */     
/*     */     public Actor$$anonfun$aroundReceive$1(Actor $outer) {}
/*     */   }
/*     */   
/*     */   public class Actor$$anonfun$preRestart$1 extends AbstractFunction1<ActorRef, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public Actor$$anonfun$preRestart$1(Actor $outer) {}
/*     */     
/*     */     public final void apply(ActorRef child) {
/* 530 */       this.$outer.context().unwatch(child);
/* 531 */       this.$outer.context().stop(child);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\Actor$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
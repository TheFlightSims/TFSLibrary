/*     */ package akka.actor;
/*     */ 
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ 
/*     */ public final class DeathPactException$ extends AbstractFunction1<ActorRef, DeathPactException> implements Serializable {
/*     */   public static final DeathPactException$ MODULE$;
/*     */   
/*     */   public final String toString() {
/* 230 */     return "DeathPactException";
/*     */   }
/*     */   
/*     */   public DeathPactException apply(ActorRef dead) {
/* 230 */     return new DeathPactException(dead);
/*     */   }
/*     */   
/*     */   public Option<ActorRef> unapply(DeathPactException x$0) {
/* 230 */     return (x$0 == null) ? (Option<ActorRef>)scala.None$.MODULE$ : (Option<ActorRef>)new Some(x$0.dead());
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 230 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private DeathPactException$() {
/* 230 */     MODULE$ = this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\DeathPactException$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
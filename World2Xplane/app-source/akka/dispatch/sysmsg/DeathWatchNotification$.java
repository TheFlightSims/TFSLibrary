/*     */ package akka.dispatch.sysmsg;
/*     */ 
/*     */ import akka.actor.ActorRef;
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple3;
/*     */ import scala.runtime.AbstractFunction3;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class DeathWatchNotification$ extends AbstractFunction3<ActorRef, Object, Object, DeathWatchNotification> implements Serializable {
/*     */   public static final DeathWatchNotification$ MODULE$;
/*     */   
/*     */   public final String toString() {
/* 261 */     return "DeathWatchNotification";
/*     */   }
/*     */   
/*     */   public DeathWatchNotification apply(ActorRef actor, boolean existenceConfirmed, boolean addressTerminated) {
/* 261 */     return new DeathWatchNotification(actor, existenceConfirmed, addressTerminated);
/*     */   }
/*     */   
/*     */   public Option<Tuple3<ActorRef, Object, Object>> unapply(DeathWatchNotification x$0) {
/* 261 */     return (x$0 == null) ? (Option<Tuple3<ActorRef, Object, Object>>)scala.None$.MODULE$ : (Option<Tuple3<ActorRef, Object, Object>>)new Some(new Tuple3(x$0.actor(), BoxesRunTime.boxToBoolean(x$0.existenceConfirmed()), BoxesRunTime.boxToBoolean(x$0.addressTerminated())));
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 261 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private DeathWatchNotification$() {
/* 261 */     MODULE$ = this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\sysmsg\DeathWatchNotification$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
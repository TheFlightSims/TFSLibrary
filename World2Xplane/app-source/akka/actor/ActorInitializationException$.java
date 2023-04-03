/*     */ package akka.actor;
/*     */ 
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple3;
/*     */ 
/*     */ public final class ActorInitializationException$ implements Serializable {
/*     */   public static final ActorInitializationException$ MODULE$;
/*     */   
/*     */   private Object readResolve() {
/* 162 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private ActorInitializationException$() {
/* 162 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public Throwable apply$default$3() {
/* 163 */     null;
/* 163 */     return null;
/*     */   }
/*     */   
/*     */   public ActorInitializationException apply(ActorRef actor, String message, Throwable cause) {
/* 164 */     return new ActorInitializationException(actor, message, cause);
/*     */   }
/*     */   
/*     */   public ActorInitializationException apply(String message) {
/* 165 */     null;
/* 165 */     null;
/* 165 */     return new ActorInitializationException(null, message, null);
/*     */   }
/*     */   
/*     */   public Option<Tuple3<ActorRef, String, Throwable>> unapply(ActorInitializationException ex) {
/* 166 */     return (Option<Tuple3<ActorRef, String, Throwable>>)new Some(new Tuple3(ex.getActor(), ex.getMessage(), ex.getCause()));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\ActorInitializationException$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
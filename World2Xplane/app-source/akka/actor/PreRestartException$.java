/*     */ package akka.actor;
/*     */ 
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple4;
/*     */ import scala.runtime.AbstractFunction4;
/*     */ 
/*     */ public final class PreRestartException$ extends AbstractFunction4<ActorRef, Throwable, Throwable, Option<Object>, PreRestartException> implements Serializable {
/*     */   public static final PreRestartException$ MODULE$;
/*     */   
/*     */   public final String toString() {
/* 181 */     return "PreRestartException";
/*     */   }
/*     */   
/*     */   public PreRestartException apply(ActorRef actor, Throwable cause, Throwable originalCause, Option<Object> messageOption) {
/* 181 */     return new PreRestartException(actor, cause, originalCause, messageOption);
/*     */   }
/*     */   
/*     */   public Option<Tuple4<ActorRef, Throwable, Throwable, Option<Object>>> unapply(PreRestartException x$0) {
/* 181 */     return (x$0 == null) ? (Option<Tuple4<ActorRef, Throwable, Throwable, Option<Object>>>)scala.None$.MODULE$ : (Option<Tuple4<ActorRef, Throwable, Throwable, Option<Object>>>)new Some(new Tuple4(x$0.actor(), x$0.cause(), x$0.originalCause(), x$0.messageOption()));
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 181 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private PreRestartException$() {
/* 181 */     MODULE$ = this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\PreRestartException$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
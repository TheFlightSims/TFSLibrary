/*     */ package akka.actor;
/*     */ 
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple3;
/*     */ import scala.runtime.AbstractFunction3;
/*     */ 
/*     */ public final class PostRestartException$ extends AbstractFunction3<ActorRef, Throwable, Throwable, PostRestartException> implements Serializable {
/*     */   public static final PostRestartException$ MODULE$;
/*     */   
/*     */   public final String toString() {
/* 197 */     return "PostRestartException";
/*     */   }
/*     */   
/*     */   public PostRestartException apply(ActorRef actor, Throwable cause, Throwable originalCause) {
/* 197 */     return new PostRestartException(actor, cause, originalCause);
/*     */   }
/*     */   
/*     */   public Option<Tuple3<ActorRef, Throwable, Throwable>> unapply(PostRestartException x$0) {
/* 197 */     return (x$0 == null) ? (Option<Tuple3<ActorRef, Throwable, Throwable>>)scala.None$.MODULE$ : (Option<Tuple3<ActorRef, Throwable, Throwable>>)new Some(new Tuple3(x$0.actor(), x$0.cause(), x$0.originalCause()));
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 197 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private PostRestartException$() {
/* 197 */     MODULE$ = this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\PostRestartException$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
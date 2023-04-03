/*     */ package akka.actor;
/*     */ 
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ 
/*     */ public final class ActorNotFound$ extends AbstractFunction1<ActorSelection, ActorNotFound> implements Serializable {
/*     */   public static final ActorNotFound$ MODULE$;
/*     */   
/*     */   public final String toString() {
/* 287 */     return "ActorNotFound";
/*     */   }
/*     */   
/*     */   public ActorNotFound apply(ActorSelection selection) {
/* 287 */     return new ActorNotFound(selection);
/*     */   }
/*     */   
/*     */   public Option<ActorSelection> unapply(ActorNotFound x$0) {
/* 287 */     return (x$0 == null) ? (Option<ActorSelection>)scala.None$.MODULE$ : (Option<ActorSelection>)new Some(x$0.selection());
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 287 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private ActorNotFound$() {
/* 287 */     MODULE$ = this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\ActorNotFound$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
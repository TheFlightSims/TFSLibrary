/*     */ package akka.actor;
/*     */ 
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ 
/*     */ public final class ActorKilledException$ extends AbstractFunction1<String, ActorKilledException> implements Serializable {
/*     */   public static final ActorKilledException$ MODULE$;
/*     */   
/*     */   public final String toString() {
/* 137 */     return "ActorKilledException";
/*     */   }
/*     */   
/*     */   public ActorKilledException apply(String message) {
/* 137 */     return new ActorKilledException(message);
/*     */   }
/*     */   
/*     */   public Option<String> unapply(ActorKilledException x$0) {
/* 137 */     return (x$0 == null) ? (Option<String>)scala.None$.MODULE$ : (Option<String>)new Some(x$0.message());
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 137 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private ActorKilledException$() {
/* 137 */     MODULE$ = this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\ActorKilledException$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
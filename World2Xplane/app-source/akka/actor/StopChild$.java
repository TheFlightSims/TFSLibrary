/*     */ package akka.actor;
/*     */ 
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ 
/*     */ public final class StopChild$ extends AbstractFunction1<ActorRef, StopChild> implements Serializable {
/*     */   public static final StopChild$ MODULE$;
/*     */   
/*     */   public final String toString() {
/* 343 */     return "StopChild";
/*     */   }
/*     */   
/*     */   public StopChild apply(ActorRef child) {
/* 343 */     return new StopChild(child);
/*     */   }
/*     */   
/*     */   public Option<ActorRef> unapply(StopChild x$0) {
/* 343 */     return (x$0 == null) ? (Option<ActorRef>)scala.None$.MODULE$ : (Option<ActorRef>)new Some(x$0.child());
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 343 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private StopChild$() {
/* 343 */     MODULE$ = this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\StopChild$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package akka.dispatch.sysmsg;
/*     */ 
/*     */ import akka.actor.ActorInitializationException;
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ 
/*     */ public final class Create$ extends AbstractFunction1<Option<ActorInitializationException>, Create> implements Serializable {
/*     */   public static final Create$ MODULE$;
/*     */   
/*     */   public final String toString() {
/* 210 */     return "Create";
/*     */   }
/*     */   
/*     */   public Create apply(Option<ActorInitializationException> failure) {
/* 210 */     return new Create(failure);
/*     */   }
/*     */   
/*     */   public Option<Option<ActorInitializationException>> unapply(Create x$0) {
/* 210 */     return (x$0 == null) ? (Option<Option<ActorInitializationException>>)scala.None$.MODULE$ : (Option<Option<ActorInitializationException>>)new Some(x$0.failure());
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 210 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private Create$() {
/* 210 */     MODULE$ = this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\sysmsg\Create$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
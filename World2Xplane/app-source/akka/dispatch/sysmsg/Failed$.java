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
/*     */ public final class Failed$ extends AbstractFunction3<ActorRef, Throwable, Object, Failed> implements Serializable {
/*     */   public static final Failed$ MODULE$;
/*     */   
/*     */   public final String toString() {
/* 256 */     return "Failed";
/*     */   }
/*     */   
/*     */   public Failed apply(ActorRef child, Throwable cause, int uid) {
/* 256 */     return new Failed(child, cause, uid);
/*     */   }
/*     */   
/*     */   public Option<Tuple3<ActorRef, Throwable, Object>> unapply(Failed x$0) {
/* 256 */     return (x$0 == null) ? (Option<Tuple3<ActorRef, Throwable, Object>>)scala.None$.MODULE$ : (Option<Tuple3<ActorRef, Throwable, Object>>)new Some(new Tuple3(x$0.child(), x$0.cause(), BoxesRunTime.boxToInteger(x$0.uid())));
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 256 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private Failed$() {
/* 256 */     MODULE$ = this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\sysmsg\Failed$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
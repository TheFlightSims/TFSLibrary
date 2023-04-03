/*     */ package akka.dispatch.sysmsg;
/*     */ 
/*     */ import akka.actor.ActorRef;
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class Supervise$ extends AbstractFunction2<ActorRef, Object, Supervise> implements Serializable {
/*     */   public static final Supervise$ MODULE$;
/*     */   
/*     */   public final String toString() {
/* 235 */     return "Supervise";
/*     */   }
/*     */   
/*     */   public Supervise apply(ActorRef child, boolean async) {
/* 235 */     return new Supervise(child, async);
/*     */   }
/*     */   
/*     */   public Option<Tuple2<ActorRef, Object>> unapply(Supervise x$0) {
/* 235 */     return (x$0 == null) ? (Option<Tuple2<ActorRef, Object>>)scala.None$.MODULE$ : (Option<Tuple2<ActorRef, Object>>)new Some(new Tuple2(x$0.child(), BoxesRunTime.boxToBoolean(x$0.async())));
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 235 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private Supervise$() {
/* 235 */     MODULE$ = this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\sysmsg\Supervise$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
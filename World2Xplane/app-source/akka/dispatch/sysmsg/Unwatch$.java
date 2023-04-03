/*     */ package akka.dispatch.sysmsg;
/*     */ 
/*     */ import akka.actor.ActorRef;
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ 
/*     */ public final class Unwatch$ extends AbstractFunction2<ActorRef, ActorRef, Unwatch> implements Serializable {
/*     */   public static final Unwatch$ MODULE$;
/*     */   
/*     */   public final String toString() {
/* 245 */     return "Unwatch";
/*     */   }
/*     */   
/*     */   public Unwatch apply(ActorRef watchee, ActorRef watcher) {
/* 245 */     return new Unwatch(watchee, watcher);
/*     */   }
/*     */   
/*     */   public Option<Tuple2<ActorRef, ActorRef>> unapply(Unwatch x$0) {
/* 245 */     return (x$0 == null) ? (Option<Tuple2<ActorRef, ActorRef>>)scala.None$.MODULE$ : (Option<Tuple2<ActorRef, ActorRef>>)new Some(new Tuple2(x$0.watchee(), x$0.watcher()));
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 245 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private Unwatch$() {
/* 245 */     MODULE$ = this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\sysmsg\Unwatch$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
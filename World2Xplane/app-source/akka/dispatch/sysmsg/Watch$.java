/*     */ package akka.dispatch.sysmsg;
/*     */ 
/*     */ import akka.actor.InternalActorRef;
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ 
/*     */ public final class Watch$ extends AbstractFunction2<InternalActorRef, InternalActorRef, Watch> implements Serializable {
/*     */   public static final Watch$ MODULE$;
/*     */   
/*     */   public final String toString() {
/* 240 */     return "Watch";
/*     */   }
/*     */   
/*     */   public Watch apply(InternalActorRef watchee, InternalActorRef watcher) {
/* 240 */     return new Watch(watchee, watcher);
/*     */   }
/*     */   
/*     */   public Option<Tuple2<InternalActorRef, InternalActorRef>> unapply(Watch x$0) {
/* 240 */     return (x$0 == null) ? (Option<Tuple2<InternalActorRef, InternalActorRef>>)scala.None$.MODULE$ : (Option<Tuple2<InternalActorRef, InternalActorRef>>)new Some(new Tuple2(x$0.watchee(), x$0.watcher()));
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 240 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private Watch$() {
/* 240 */     MODULE$ = this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\sysmsg\Watch$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
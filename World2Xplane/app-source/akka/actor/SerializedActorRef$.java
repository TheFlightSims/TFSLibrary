/*     */ package akka.actor;
/*     */ 
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ 
/*     */ public final class SerializedActorRef$ implements Serializable {
/*     */   public static final SerializedActorRef$ MODULE$;
/*     */   
/*     */   public SerializedActorRef apply(String path) {
/* 397 */     return new SerializedActorRef(path);
/*     */   }
/*     */   
/*     */   public Option<String> unapply(SerializedActorRef x$0) {
/* 397 */     return (x$0 == null) ? (Option<String>)scala.None$.MODULE$ : (Option<String>)new Some(x$0.path());
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 418 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private SerializedActorRef$() {
/* 418 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public SerializedActorRef apply(ActorRef actorRef) {
/* 420 */     return new SerializedActorRef(actorRef);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\SerializedActorRef$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
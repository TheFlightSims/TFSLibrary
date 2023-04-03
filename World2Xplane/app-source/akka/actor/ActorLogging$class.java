/*     */ package akka.actor;
/*     */ 
/*     */ import akka.event.LogSource$;
/*     */ import akka.event.Logging$;
/*     */ import akka.event.LoggingAdapter;
/*     */ 
/*     */ public abstract class ActorLogging$class {
/*     */   public static void $init$(ActorLogging $this) {}
/*     */   
/*     */   public static LoggingAdapter log(ActorLogging $this) {
/* 285 */     if ($this.akka$actor$ActorLogging$$_log() == null)
/* 286 */       $this.akka$actor$ActorLogging$$_log_$eq(Logging$.MODULE$.apply(((Actor)$this).context().system(), $this, LogSource$.MODULE$.fromActor())); 
/* 287 */     return $this.akka$actor$ActorLogging$$_log();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\ActorLogging$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
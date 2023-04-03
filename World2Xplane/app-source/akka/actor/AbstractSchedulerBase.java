/*     */ package akka.actor;
/*     */ 
/*     */ import scala.Function0;
/*     */ import scala.concurrent.ExecutionContext;
/*     */ import scala.concurrent.duration.FiniteDuration;
/*     */ import scala.reflect.ScalaSignature;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001Y1Q!\001\002\002\002\035\021Q#\0212tiJ\f7\r^*dQ\026$W\017\\3s\005\006\034XM\003\002\004\t\005)\021m\031;pe*\tQ!\001\003bW.\f7\001A\n\004\001!q\001CA\005\r\033\005Q!\"A\006\002\013M\034\027\r\\1\n\0055Q!AB!osJ+g\r\005\002\020!5\t!!\003\002\022\005\tI1k\0315fIVdWM\035\005\006'\001!\t\001F\001\007y%t\027\016\036 \025\003U\001\"a\004\001")
/*     */ public abstract class AbstractSchedulerBase implements Scheduler {
/*     */   public final Cancellable schedule(FiniteDuration initialDelay, FiniteDuration interval, ActorRef receiver, Object message, ExecutionContext executor, ActorRef sender) {
/* 139 */     return Scheduler$class.schedule(this, initialDelay, interval, receiver, message, executor, sender);
/*     */   }
/*     */   
/*     */   public final Cancellable schedule(FiniteDuration initialDelay, FiniteDuration interval, Function0 f, ExecutionContext executor) {
/* 139 */     return Scheduler$class.schedule(this, initialDelay, interval, f, executor);
/*     */   }
/*     */   
/*     */   public final Cancellable scheduleOnce(FiniteDuration delay, ActorRef receiver, Object message, ExecutionContext executor, ActorRef sender) {
/* 139 */     return Scheduler$class.scheduleOnce(this, delay, receiver, message, executor, sender);
/*     */   }
/*     */   
/*     */   public final Cancellable scheduleOnce(FiniteDuration delay, Function0 f, ExecutionContext executor) {
/* 139 */     return Scheduler$class.scheduleOnce(this, delay, f, executor);
/*     */   }
/*     */   
/*     */   public final ActorRef schedule$default$6(FiniteDuration initialDelay, FiniteDuration interval, ActorRef receiver, Object message) {
/* 139 */     return Scheduler$class.schedule$default$6(this, initialDelay, interval, receiver, message);
/*     */   }
/*     */   
/*     */   public final ActorRef scheduleOnce$default$5(FiniteDuration delay, ActorRef receiver, Object message) {
/* 139 */     return Scheduler$class.scheduleOnce$default$5(this, delay, receiver, message);
/*     */   }
/*     */   
/*     */   public AbstractSchedulerBase() {
/* 139 */     Scheduler$class.$init$(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\AbstractSchedulerBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
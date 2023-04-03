/*     */ package akka.actor;
/*     */ 
/*     */ import scala.Function0;
/*     */ import scala.concurrent.ExecutionContext;
/*     */ import scala.concurrent.duration.FiniteDuration;
/*     */ 
/*     */ public abstract class Scheduler$class {
/*     */   public static void $init$(Scheduler $this) {}
/*     */   
/*     */   public static final ActorRef schedule$default$6(Scheduler $this, FiniteDuration initialDelay, FiniteDuration interval, ActorRef receiver, Object message) {
/*  58 */     return Actor$.MODULE$.noSender();
/*     */   }
/*     */   
/*     */   public static final Cancellable schedule(Scheduler $this, FiniteDuration initialDelay, FiniteDuration interval, ActorRef receiver, Object message, ExecutionContext executor, ActorRef sender) {
/*  59 */     return $this.schedule(initialDelay, interval, new Scheduler$$anon$4($this, receiver, message, sender), executor);
/*     */   }
/*     */   
/*     */   public static final Cancellable schedule(Scheduler $this, FiniteDuration initialDelay, FiniteDuration interval, Function0 f, ExecutionContext executor) {
/*  79 */     return $this.schedule(initialDelay, interval, new Scheduler$$anon$5($this, f), executor);
/*     */   }
/*     */   
/*     */   public static final ActorRef scheduleOnce$default$5(Scheduler $this, FiniteDuration delay, ActorRef receiver, Object message) {
/* 104 */     return Actor$.MODULE$.noSender();
/*     */   }
/*     */   
/*     */   public static final Cancellable scheduleOnce(Scheduler $this, FiniteDuration delay, ActorRef receiver, Object message, ExecutionContext executor, ActorRef sender) {
/* 105 */     return $this.scheduleOnce(delay, new Scheduler$$anon$6($this, receiver, message, sender), executor);
/*     */   }
/*     */   
/*     */   public static final Cancellable scheduleOnce(Scheduler $this, FiniteDuration delay, Function0 f, ExecutionContext executor) {
/* 117 */     return $this.scheduleOnce(delay, new Scheduler$$anon$7($this, f), executor);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\Scheduler$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
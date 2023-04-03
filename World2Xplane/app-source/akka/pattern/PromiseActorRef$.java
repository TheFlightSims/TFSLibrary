/*     */ package akka.pattern;
/*     */ 
/*     */ import akka.actor.ActorRef;
/*     */ import akka.actor.ActorRefProvider;
/*     */ import akka.actor.Cancellable;
/*     */ import akka.actor.InternalActorRef;
/*     */ import akka.actor.Scheduler;
/*     */ import akka.dispatch.sysmsg.DeathWatchNotification;
/*     */ import akka.dispatch.sysmsg.SystemMessage;
/*     */ import akka.util.Timeout;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Serializable;
/*     */ import scala.StringContext;
/*     */ import scala.collection.Seq;
/*     */ import scala.concurrent.ExecutionContext;
/*     */ import scala.concurrent.Promise;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.util.Failure;
/*     */ import scala.util.Try;
/*     */ 
/*     */ public final class PromiseActorRef$ implements Serializable {
/*     */   public static final PromiseActorRef$ MODULE$;
/*     */   
/*     */   public class PromiseActorRef$$anonfun$ensureCompleted$1$1 extends AbstractFunction1<ActorRef, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public PromiseActorRef$$anonfun$ensureCompleted$1$1(PromiseActorRef $outer) {}
/*     */     
/*     */     public final void apply(ActorRef watcher) {
/* 303 */       ((InternalActorRef)watcher)
/* 304 */         .sendSystemMessage((SystemMessage)new DeathWatchNotification(watcher, true, false));
/*     */     }
/*     */   }
/*     */   
/*     */   private PromiseActorRef$() {
/* 322 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 322 */     return MODULE$;
/*     */   }
/*     */   
/*     */   public PromiseActorRef apply(ActorRefProvider provider, Timeout timeout, String targetName) {
/* 328 */     Promise<Object> result = scala.concurrent.Promise$.MODULE$.apply();
/* 329 */     Scheduler scheduler = provider.guardian().underlying().system().scheduler();
/* 330 */     PromiseActorRef a = new PromiseActorRef(provider, result);
/* 331 */     ExecutionContext ec = a.internalCallingThreadExecutionContext();
/* 332 */     Cancellable f = scheduler.scheduleOnce(timeout.duration(), 
/* 333 */         (Function0)new PromiseActorRef$$anonfun$1(timeout, targetName, result), ec);
/* 335 */     result.future().onComplete((Function1)new PromiseActorRef$$anonfun$apply$1(a, f), ec);
/* 336 */     return a;
/*     */   }
/*     */   
/*     */   public static class PromiseActorRef$$anonfun$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Timeout timeout$1;
/*     */     
/*     */     private final String targetName$1;
/*     */     
/*     */     private final Promise result$1;
/*     */     
/*     */     public final void apply() {
/*     */       apply$mcV$sp();
/*     */     }
/*     */     
/*     */     public void apply$mcV$sp() {
/*     */       (new String[3])[0] = "Ask timed out on [";
/*     */       (new String[3])[1] = "] after [";
/*     */       (new String[3])[2] = " ms]";
/*     */       this.result$1.tryComplete((Try)new Failure(new AskTimeoutException((new StringContext((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new String[3]))).s((Seq)scala.Predef$.MODULE$.genericWrapArray(new Object[] { this.targetName$1, BoxesRunTime.boxToLong(this.timeout$1.duration().toMillis()) })))));
/*     */     }
/*     */     
/*     */     public PromiseActorRef$$anonfun$1(Timeout timeout$1, String targetName$1, Promise result$1) {}
/*     */   }
/*     */   
/*     */   public static class PromiseActorRef$$anonfun$apply$1 extends AbstractFunction1<Try<Object>, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final PromiseActorRef a$1;
/*     */     
/*     */     private final Cancellable f$1;
/*     */     
/*     */     public final void apply(Try x$1) {
/*     */       try {
/*     */         this.a$1.stop();
/*     */         return;
/*     */       } finally {
/*     */         this.f$1.cancel();
/*     */       } 
/*     */     }
/*     */     
/*     */     public PromiseActorRef$$anonfun$apply$1(PromiseActorRef a$1, Cancellable f$1) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\pattern\PromiseActorRef$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package akka.pattern;
/*     */ 
/*     */ import akka.actor.ActorRef;
/*     */ import akka.actor.ActorSelection;
/*     */ import akka.actor.Scheduler;
/*     */ import akka.util.Timeout;
/*     */ import java.util.concurrent.Callable;
/*     */ import scala.Function0;
/*     */ import scala.Serializable;
/*     */ import scala.concurrent.ExecutionContext;
/*     */ import scala.concurrent.Future;
/*     */ import scala.concurrent.duration.FiniteDuration;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ 
/*     */ public final class Patterns$ {
/*     */   public static final Patterns$ MODULE$;
/*     */   
/*     */   private Patterns$() {
/*  11 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public Future<Object> ask(ActorRef actor, Object message, Timeout timeout) {
/*  47 */     return package$.MODULE$.ask(actor, message, timeout);
/*     */   }
/*     */   
/*     */   public Future<Object> ask(ActorRef actor, Object message, long timeoutMillis) {
/*  79 */     return package$.MODULE$.ask(actor, message, new Timeout(timeoutMillis));
/*     */   }
/*     */   
/*     */   public Future<Object> ask(ActorSelection selection, Object message, Timeout timeout) {
/* 111 */     return package$.MODULE$.ask(selection, message, timeout);
/*     */   }
/*     */   
/*     */   public Future<Object> ask(ActorSelection selection, Object message, long timeoutMillis) {
/* 143 */     return package$.MODULE$.ask(selection, message, new Timeout(timeoutMillis));
/*     */   }
/*     */   
/*     */   public <T> PipeToSupport.PipeableFuture<T> pipe(Future<T> future, ExecutionContext context) {
/* 162 */     return package$.MODULE$.pipe(future, context);
/*     */   }
/*     */   
/*     */   public Future<Boolean> gracefulStop(ActorRef target, FiniteDuration timeout) {
/* 175 */     return (Future)package$.MODULE$.gracefulStop(target, timeout, package$.MODULE$.gracefulStop$default$3());
/*     */   }
/*     */   
/*     */   public Future<Boolean> gracefulStop(ActorRef target, FiniteDuration timeout, Object stopMessage) {
/* 191 */     return (Future)package$.MODULE$.gracefulStop(target, timeout, stopMessage);
/*     */   }
/*     */   
/*     */   public <T> Future<T> after(FiniteDuration duration, Scheduler scheduler, ExecutionContext context, Callable value) {
/* 198 */     return package$.MODULE$.after(duration, scheduler, (Function0<Future<T>>)new Patterns$$anonfun$after$1(value), context);
/*     */   }
/*     */   
/*     */   public static class Patterns$$anonfun$after$1 extends AbstractFunction0<Future<T>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Callable value$1;
/*     */     
/*     */     public final Future<T> apply() {
/* 198 */       return this.value$1.call();
/*     */     }
/*     */     
/*     */     public Patterns$$anonfun$after$1(Callable value$1) {}
/*     */   }
/*     */   
/*     */   public <T> Future<T> after(FiniteDuration duration, Scheduler scheduler, ExecutionContext context, Future value) {
/* 205 */     return package$.MODULE$.after(duration, scheduler, (Function0<Future<T>>)new Patterns$$anonfun$after$2(value), context);
/*     */   }
/*     */   
/*     */   public static class Patterns$$anonfun$after$2 extends AbstractFunction0<Future<T>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Future value$2;
/*     */     
/*     */     public final Future<T> apply() {
/* 205 */       return this.value$2;
/*     */     }
/*     */     
/*     */     public Patterns$$anonfun$after$2(Future value$2) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\pattern\Patterns$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
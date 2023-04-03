/*     */ package akka.pattern;
/*     */ 
/*     */ import akka.actor.Scheduler;
/*     */ import java.util.concurrent.Callable;
/*     */ import scala.Function0;
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.concurrent.ExecutionContext;
/*     */ import scala.concurrent.Future;
/*     */ import scala.concurrent.duration.FiniteDuration;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ 
/*     */ public final class CircuitBreaker$ {
/*     */   public static final CircuitBreaker$ MODULE$;
/*     */   
/*     */   private CircuitBreaker$() {
/*  21 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public CircuitBreaker apply(Scheduler scheduler, int maxFailures, FiniteDuration callTimeout, FiniteDuration resetTimeout) {
/*  36 */     return new CircuitBreaker(scheduler, maxFailures, callTimeout, resetTimeout, (ExecutionContext)akka.dispatch.ExecutionContexts$sameThreadExecutionContext$.MODULE$);
/*     */   }
/*     */   
/*     */   public CircuitBreaker create(Scheduler scheduler, int maxFailures, FiniteDuration callTimeout, FiniteDuration resetTimeout) {
/*  51 */     return apply(scheduler, maxFailures, callTimeout, resetTimeout);
/*     */   }
/*     */   
/*     */   public class CircuitBreaker$$anonfun$callWithCircuitBreaker$1 extends AbstractFunction0<Future<T>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Callable body$1;
/*     */     
/*     */     public final Future<T> apply() {
/* 122 */       return this.body$1.call();
/*     */     }
/*     */     
/*     */     public CircuitBreaker$$anonfun$callWithCircuitBreaker$1(CircuitBreaker $outer, Callable body$1) {}
/*     */   }
/*     */   
/*     */   public class CircuitBreaker$$anonfun$withSyncCircuitBreaker$1 extends AbstractFunction0<Future<T>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function0 body$2;
/*     */     
/*     */     public final Future<T> apply() {
/*     */       try {
/*     */       
/*     */       } finally {
/*     */         Future<T> future;
/* 135 */         Exception exception1 = null, exception2 = exception1;
/* 135 */         Option option = scala.util.control.NonFatal$.MODULE$.unapply(exception2);
/* 135 */         if (option.isEmpty())
/* 135 */           throw exception1; 
/* 135 */         Throwable t = (Throwable)option.get();
/*     */       } 
/* 135 */       return future;
/*     */     }
/*     */     
/*     */     public CircuitBreaker$$anonfun$withSyncCircuitBreaker$1(CircuitBreaker $outer, Function0 body$2) {}
/*     */   }
/*     */   
/*     */   public class CircuitBreaker$$anonfun$callWithSyncCircuitBreaker$1 extends AbstractFunction0<T> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Callable body$3;
/*     */     
/*     */     public final T apply() {
/* 146 */       return this.body$3.call();
/*     */     }
/*     */     
/*     */     public CircuitBreaker$$anonfun$callWithSyncCircuitBreaker$1(CircuitBreaker $outer, Callable body$3) {}
/*     */   }
/*     */   
/*     */   public class CircuitBreaker$$anon$1 implements Runnable {
/*     */     private final Function0 callback$1;
/*     */     
/*     */     public void run() {
/* 156 */       this.callback$1.apply$mcV$sp();
/*     */     }
/*     */     
/*     */     public CircuitBreaker$$anon$1(CircuitBreaker $outer, Function0 callback$1) {}
/*     */   }
/*     */   
/*     */   public class CircuitBreaker$$anon$2 implements Runnable {
/*     */     private final Function0 callback$2;
/*     */     
/*     */     public void run() {
/* 177 */       this.callback$2.apply$mcV$sp();
/*     */     }
/*     */     
/*     */     public CircuitBreaker$$anon$2(CircuitBreaker $outer, Function0 callback$2) {}
/*     */   }
/*     */   
/*     */   public class CircuitBreaker$$anon$3 implements Runnable {
/*     */     private final Function0 callback$3;
/*     */     
/*     */     public void run() {
/* 198 */       this.callback$3.apply$mcV$sp();
/*     */     }
/*     */     
/*     */     public CircuitBreaker$$anon$3(CircuitBreaker $outer, Function0 callback$3) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\pattern\CircuitBreaker$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
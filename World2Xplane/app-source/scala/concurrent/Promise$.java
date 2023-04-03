/*     */ package scala.concurrent;
/*     */ 
/*     */ import scala.Serializable;
/*     */ import scala.concurrent.impl.Promise;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.util.Failure;
/*     */ import scala.util.Success;
/*     */ import scala.util.Try;
/*     */ 
/*     */ public final class Promise$ {
/*     */   public static final Promise$ MODULE$;
/*     */   
/*     */   public class Promise$$anonfun$completeWith$1 extends AbstractFunction1<Try<T>, Promise<T>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Promise<T> apply(Try<T> x$1) {
/*  70 */       return this.$outer.complete(x$1);
/*     */     }
/*     */     
/*     */     public Promise$$anonfun$completeWith$1(Promise $outer) {}
/*     */   }
/*     */   
/*     */   public class Promise$$anonfun$tryCompleteWith$1 extends AbstractFunction1<Try<T>, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(Try x$2) {
/*  79 */       return this.$outer.tryComplete(x$2);
/*     */     }
/*     */     
/*     */     public Promise$$anonfun$tryCompleteWith$1(Promise $outer) {}
/*     */   }
/*     */   
/*     */   private Promise$() {
/* 120 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public <T> Promise<T> apply() {
/* 127 */     return (Promise<T>)new Promise.DefaultPromise();
/*     */   }
/*     */   
/*     */   public <T> Promise<T> failed(Throwable exception) {
/* 134 */     return (Promise<T>)new Promise.KeptPromise((Try)new Failure(exception));
/*     */   }
/*     */   
/*     */   public <T> Promise<T> successful(Object result) {
/* 141 */     return (Promise<T>)new Promise.KeptPromise((Try)new Success(result));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\concurrent\Promise$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package scala.concurrent;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.util.Failure;
/*     */ import scala.util.Success;
/*     */ import scala.util.Try;
/*     */ 
/*     */ public abstract class Promise$class {
/*     */   public static void $init$(Promise $this) {}
/*     */   
/*     */   private static ExecutionContext internalExecutor(Promise $this) {
/*  33 */     return Future.InternalCallbackExecutor$.MODULE$;
/*     */   }
/*     */   
/*     */   public static Promise complete(Promise $this, Try result) {
/*  55 */     if ($this.tryComplete(result))
/*  55 */       return $this; 
/*  55 */     throw new IllegalStateException("Promise already completed.");
/*     */   }
/*     */   
/*     */   public static final Promise completeWith(Promise<T> $this, Future other) {
/*  70 */     other.onComplete((Function1)new Promise$$anonfun$completeWith$1($this), internalExecutor($this));
/*  71 */     return $this;
/*     */   }
/*     */   
/*     */   public static final Promise tryCompleteWith(Promise<T> $this, Future other) {
/*  79 */     other.onComplete((Function1)new Promise$$anonfun$tryCompleteWith$1($this), internalExecutor($this));
/*  80 */     return $this;
/*     */   }
/*     */   
/*     */   public static Promise success(Promise $this, Object v) {
/*  89 */     return $this.complete((Try)new Success(v));
/*     */   }
/*     */   
/*     */   public static boolean trySuccess(Promise $this, Object value) {
/*  97 */     return $this.tryComplete((Try)new Success(value));
/*     */   }
/*     */   
/*     */   public static Promise failure(Promise $this, Throwable t) {
/* 107 */     return $this.complete((Try)new Failure(t));
/*     */   }
/*     */   
/*     */   public static boolean tryFailure(Promise $this, Throwable t) {
/* 115 */     return $this.tryComplete((Try)new Failure(t));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\concurrent\Promise$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
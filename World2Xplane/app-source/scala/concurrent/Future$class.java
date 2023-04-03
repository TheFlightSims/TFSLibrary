/*     */ package scala.concurrent;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.PartialFunction;
/*     */ import scala.Predef$;
/*     */ import scala.concurrent.impl.Promise;
/*     */ import scala.reflect.ClassTag;
/*     */ 
/*     */ public abstract class Future$class {
/*     */   public static void $init$(Future $this) {}
/*     */   
/*     */   public static Future.InternalCallbackExecutor$ scala$concurrent$Future$$internalExecutor(Future $this) {
/* 101 */     return Future.InternalCallbackExecutor$.MODULE$;
/*     */   }
/*     */   
/*     */   public static void onSuccess(Future $this, PartialFunction pf, ExecutionContext executor) {
/* 115 */     $this.onComplete((Function1)new Future$$anonfun$onSuccess$1($this, (Future<T>)pf), executor);
/*     */   }
/*     */   
/*     */   public static void onFailure(Future $this, PartialFunction callback, ExecutionContext executor) {
/* 134 */     $this.onComplete((Function1)new Future$$anonfun$onFailure$1($this, (Future<T>)callback), executor);
/*     */   }
/*     */   
/*     */   public static Future failed(Future $this) {
/* 188 */     Future.InternalCallbackExecutor$ ec = scala$concurrent$Future$$internalExecutor($this);
/* 189 */     Promise<?> p = Promise$.MODULE$.apply();
/* 190 */     $this.onComplete((Function1)new Future$$anonfun$failed$1($this, (Future)p), ec);
/* 194 */     return p.future();
/*     */   }
/*     */   
/*     */   public static void foreach(Future $this, Function1 f, ExecutionContext executor) {
/* 204 */     $this.onComplete((Function1)new Future$$anonfun$foreach$1($this, (Future<T>)f), executor);
/*     */   }
/*     */   
/*     */   public static Future transform(Future $this, Function1 s, Function1 f, ExecutionContext executor) {
/* 218 */     Promise<?> p = Promise$.MODULE$.apply();
/* 220 */     $this.onComplete((Function1)new Future$$anonfun$transform$1($this, p, s, (Future<T>)f), executor);
/* 224 */     return p.future();
/*     */   }
/*     */   
/*     */   public static Future map(Future $this, Function1 f, ExecutionContext executor) {
/* 234 */     Promise<?> p = Promise$.MODULE$.apply();
/* 235 */     $this.onComplete((Function1)new Future$$anonfun$map$1($this, p, (Future<T>)f), executor);
/* 236 */     return p.future();
/*     */   }
/*     */   
/*     */   public static Future flatMap(Future $this, Function1 f, ExecutionContext executor) {
/* 248 */     Promise.DefaultPromise p = new Promise.DefaultPromise();
/* 249 */     $this.onComplete((Function1)new Future$$anonfun$flatMap$1($this, p, (Future<T>)f), executor);
/* 257 */     return (Future)p.future();
/*     */   }
/*     */   
/*     */   public static Future filter(Future $this, Function1 pred, ExecutionContext executor) {
/* 277 */     return $this.map(
/* 278 */         (Function1)new Future$$anonfun$filter$1($this, (Future<T>)pred), executor);
/*     */   }
/*     */   
/*     */   public static final Future withFilter(Future $this, Function1 p, ExecutionContext executor) {
/* 283 */     return $this.filter(p, executor);
/*     */   }
/*     */   
/*     */   public static Future collect(Future $this, PartialFunction pf, ExecutionContext executor) {
/* 306 */     return $this.map(
/* 307 */         (Function1)new Future$$anonfun$collect$1($this, (Future<T>)pf), executor);
/*     */   }
/*     */   
/*     */   public static Future recover(Future $this, PartialFunction pf, ExecutionContext executor) {
/* 323 */     Promise<?> p = Promise$.MODULE$.apply();
/* 324 */     $this.onComplete((Function1)new Future$$anonfun$recover$1($this, p, (Future<T>)pf), executor);
/* 325 */     return p.future();
/*     */   }
/*     */   
/*     */   public static Future recoverWith(Future $this, PartialFunction pf, ExecutionContext executor) {
/* 342 */     Promise<?> p = Promise$.MODULE$.apply();
/* 343 */     $this.onComplete((Function1)new Future$$anonfun$recoverWith$1($this, p, (Future<T>)pf), executor);
/* 347 */     return p.future();
/*     */   }
/*     */   
/*     */   public static Future zip(Future $this, Future<T> that) {
/* 359 */     Future.InternalCallbackExecutor$ ec = scala$concurrent$Future$$internalExecutor($this);
/* 360 */     Promise<?> p = Promise$.MODULE$.apply();
/* 361 */     $this.onComplete((Function1)new Future$$anonfun$zip$1($this, ec, p, that), ec);
/* 365 */     return p.future();
/*     */   }
/*     */   
/*     */   public static Future fallbackTo(Future $this, Future<T> that) {
/* 383 */     Future.InternalCallbackExecutor$ ec = scala$concurrent$Future$$internalExecutor($this);
/* 384 */     Promise<?> p = Promise$.MODULE$.apply();
/* 385 */     $this.onComplete((Function1)new Future$$anonfun$fallbackTo$1($this, ec, p, that), ec);
/* 392 */     return p.future();
/*     */   }
/*     */   
/*     */   public static Future mapTo(Future $this, ClassTag tag) {
/* 399 */     Future.InternalCallbackExecutor$ ec = scala$concurrent$Future$$internalExecutor($this);
/* 401 */     Class c = tag.runtimeClass();
/* 402 */     Class boxedClass = c.isPrimitive() ? (Class)Future$.MODULE$.toBoxed().apply(c) : c;
/* 404 */     Predef$.MODULE$.require((boxedClass != null));
/* 405 */     return $this.map((Function1)new Future$$anonfun$mapTo$1($this, (Future<T>)boxedClass), ec);
/*     */   }
/*     */   
/*     */   public static Future andThen(Future $this, PartialFunction pf, ExecutionContext executor) {
/* 432 */     Promise<?> p = Promise$.MODULE$.apply();
/* 433 */     $this.onComplete((Function1)new Future$$anonfun$andThen$1($this, p, (Future<T>)pf), executor);
/* 436 */     return p.future();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\concurrent\Future$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
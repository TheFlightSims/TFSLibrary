/*     */ package scala.concurrent;
/*     */ 
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.MatchError;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.immutable.Map;
/*     */ import scala.collection.mutable.Buffer;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.concurrent.impl.Future$;
/*     */ import scala.concurrent.impl.Promise;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.util.Failure;
/*     */ import scala.util.Success;
/*     */ import scala.util.Try;
/*     */ 
/*     */ public final class Future$ {
/*     */   public static final Future$ MODULE$;
/*     */   
/*     */   private final Map<Class<?>, Class<?>> toBoxed;
/*     */   
/*     */   public class Future$$anonfun$onSuccess$1 extends AbstractFunction1<Try<T>, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final PartialFunction pf$1;
/*     */     
/*     */     public Future$$anonfun$onSuccess$1(Future $outer, PartialFunction pf$1) {}
/*     */     
/*     */     public final Object apply(Try x0$1) {
/*     */       Object object;
/* 115 */       if (x0$1 instanceof Success) {
/* 115 */         Success success = (Success)x0$1;
/* 117 */         object = this.pf$1.applyOrElse(success.value(), (Function1)scala.Predef$.MODULE$.conforms());
/*     */       } else {
/* 118 */         object = BoxedUnit.UNIT;
/*     */       } 
/*     */       return object;
/*     */     }
/*     */   }
/*     */   
/*     */   public class Future$$anonfun$onFailure$1 extends AbstractFunction1<Try<T>, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final PartialFunction callback$1;
/*     */     
/*     */     public Future$$anonfun$onFailure$1(Future $outer, PartialFunction callback$1) {}
/*     */     
/*     */     public final Object apply(Try x0$2) {
/*     */       Object object;
/* 134 */       if (x0$2 instanceof Failure) {
/* 134 */         Failure failure = (Failure)x0$2;
/* 136 */         object = this.callback$1.applyOrElse(failure.exception(), (Function1)scala.Predef$.MODULE$.conforms());
/*     */       } else {
/* 137 */         object = BoxedUnit.UNIT;
/*     */       } 
/*     */       return object;
/*     */     }
/*     */   }
/*     */   
/*     */   public class Future$$anonfun$failed$1 extends AbstractFunction1<Try<T>, Promise<Throwable>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Promise p$1;
/*     */     
/*     */     public Future$$anonfun$failed$1(Future $outer, Promise p$1) {}
/*     */     
/*     */     public final Promise<Throwable> apply(Try x0$3) {
/* 190 */       if (x0$3 instanceof Failure) {
/* 190 */         Failure failure = (Failure)x0$3;
/* 191 */         Promise<Throwable> promise = this.p$1.success(failure.exception());
/*     */       } else {
/* 192 */         if (x0$3 instanceof Success)
/* 192 */           return this.p$1.failure(new NoSuchElementException("Future.failed not completed with a throwable.")); 
/*     */         throw new MatchError(x0$3);
/*     */       } 
/*     */       return (Promise<Throwable>)SYNTHETIC_LOCAL_VARIABLE_3;
/*     */     }
/*     */   }
/*     */   
/*     */   public class Future$$anonfun$foreach$1 extends AbstractFunction1<Try<T>, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function1 f$1;
/*     */     
/*     */     public final void apply(Try x$1) {
/* 204 */       x$1.foreach(this.f$1);
/*     */     }
/*     */     
/*     */     public Future$$anonfun$foreach$1(Future $outer, Function1 f$1) {}
/*     */   }
/*     */   
/*     */   public class Future$$anonfun$transform$1 extends AbstractFunction1<Try<T>, Promise<S>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Promise p$2;
/*     */     
/*     */     public final Function1 s$1;
/*     */     
/*     */     public final Function1 f$2;
/*     */     
/*     */     public Future$$anonfun$transform$1(Future $outer, Promise p$2, Function1 s$1, Function1 f$2) {}
/*     */     
/*     */     public final Promise<S> apply(Try x0$4) {
/* 220 */       if (x0$4 instanceof Success) {
/* 220 */         Success success = (Success)x0$4;
/* 220 */         Promise promise = this.p$2.complete(scala.util.Try$.MODULE$.apply((Function0)new Future$$anonfun$transform$1$$anonfun$apply$1(this, ($anonfun$transform$1)success)));
/*     */       } else {
/* 222 */         if (x0$4 instanceof Failure) {
/* 222 */           Failure failure = (Failure)x0$4;
/* 222 */           return this.p$2.complete(scala.util.Try$.MODULE$.apply((Function0)new Future$$anonfun$transform$1$$anonfun$apply$2(this, ($anonfun$transform$1)failure)));
/*     */         } 
/*     */         throw new MatchError(x0$4);
/*     */       } 
/*     */       return (Promise<S>)SYNTHETIC_LOCAL_VARIABLE_4;
/*     */     }
/*     */     
/*     */     public class Future$$anonfun$transform$1$$anonfun$apply$1 extends AbstractFunction0<S> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Success x2$1;
/*     */       
/*     */       public final S apply() {
/*     */         return (S)this.$outer.s$1.apply(this.x2$1.value());
/*     */       }
/*     */       
/*     */       public Future$$anonfun$transform$1$$anonfun$apply$1(Future$$anonfun$transform$1 $outer, Success x2$1) {}
/*     */     }
/*     */     
/*     */     public class Future$$anonfun$transform$1$$anonfun$apply$2 extends AbstractFunction0<scala.runtime.Nothing$> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Failure x3$1;
/*     */       
/*     */       public final scala.runtime.Nothing$ apply() {
/* 222 */         throw (Throwable)this.$outer.f$2.apply(this.x3$1.exception());
/*     */       }
/*     */       
/*     */       public Future$$anonfun$transform$1$$anonfun$apply$2(Future$$anonfun$transform$1 $outer, Failure x3$1) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public class Future$$anonfun$map$1 extends AbstractFunction1<Try<T>, Promise<S>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Promise p$3;
/*     */     
/*     */     private final Function1 f$3;
/*     */     
/*     */     public final Promise<S> apply(Try v) {
/* 235 */       return this.p$3.complete(v.map(this.f$3));
/*     */     }
/*     */     
/*     */     public Future$$anonfun$map$1(Future $outer, Promise p$3, Function1 f$3) {}
/*     */   }
/*     */   
/*     */   public class Future$$anonfun$flatMap$1 extends AbstractFunction1<Try<T>, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Promise.DefaultPromise p$4;
/*     */     
/*     */     private final Function1 f$4;
/*     */     
/*     */     public Future$$anonfun$flatMap$1(Future $outer, Promise.DefaultPromise p$4, Function1 f$4) {}
/*     */     
/*     */     public final Object apply(Try x0$5) {
/*     */       // Byte code:
/*     */       //   0: aload_1
/*     */       //   1: instanceof scala/util/Failure
/*     */       //   4: ifeq -> 25
/*     */       //   7: aload_1
/*     */       //   8: checkcast scala/util/Failure
/*     */       //   11: astore_2
/*     */       //   12: aload_0
/*     */       //   13: getfield p$4 : Lscala/concurrent/impl/Promise$DefaultPromise;
/*     */       //   16: aload_2
/*     */       //   17: invokevirtual complete : (Lscala/util/Try;)Lscala/concurrent/Promise;
/*     */       //   20: astore #8
/*     */       //   22: goto -> 159
/*     */       //   25: aload_1
/*     */       //   26: instanceof scala/util/Success
/*     */       //   29: ifeq -> 110
/*     */       //   32: aload_1
/*     */       //   33: checkcast scala/util/Success
/*     */       //   36: astore_3
/*     */       //   37: aload_0
/*     */       //   38: getfield f$4 : Lscala/Function1;
/*     */       //   41: aload_3
/*     */       //   42: invokevirtual value : ()Ljava/lang/Object;
/*     */       //   45: invokeinterface apply : (Ljava/lang/Object;)Ljava/lang/Object;
/*     */       //   50: checkcast scala/concurrent/Future
/*     */       //   53: astore #5
/*     */       //   55: aload #5
/*     */       //   57: instanceof scala/concurrent/impl/Promise$DefaultPromise
/*     */       //   60: ifeq -> 82
/*     */       //   63: aload #5
/*     */       //   65: checkcast scala/concurrent/impl/Promise$DefaultPromise
/*     */       //   68: astore #4
/*     */       //   70: aload #4
/*     */       //   72: aload_0
/*     */       //   73: getfield p$4 : Lscala/concurrent/impl/Promise$DefaultPromise;
/*     */       //   76: invokevirtual linkRootOf : (Lscala/concurrent/impl/Promise$DefaultPromise;)V
/*     */       //   79: goto -> 104
/*     */       //   82: aload #5
/*     */       //   84: new scala/concurrent/Future$$anonfun$flatMap$1$$anonfun$apply$3
/*     */       //   87: dup
/*     */       //   88: aload_0
/*     */       //   89: invokespecial <init> : (Lscala/concurrent/Future$$anonfun$flatMap$1;)V
/*     */       //   92: aload_0
/*     */       //   93: getfield $outer : Lscala/concurrent/Future;
/*     */       //   96: invokestatic scala$concurrent$Future$$internalExecutor : (Lscala/concurrent/Future;)Lscala/concurrent/Future$InternalCallbackExecutor$;
/*     */       //   99: invokeinterface onComplete : (Lscala/Function1;Lscala/concurrent/ExecutionContext;)V
/*     */       //   104: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   107: goto -> 157
/*     */       //   110: new scala/MatchError
/*     */       //   113: dup
/*     */       //   114: aload_1
/*     */       //   115: invokespecial <init> : (Ljava/lang/Object;)V
/*     */       //   118: athrow
/*     */       //   119: astore #6
/*     */       //   121: getstatic scala/util/control/NonFatal$.MODULE$ : Lscala/util/control/NonFatal$;
/*     */       //   124: aload #6
/*     */       //   126: invokevirtual unapply : (Ljava/lang/Throwable;)Lscala/Option;
/*     */       //   129: astore #7
/*     */       //   131: aload #7
/*     */       //   133: invokevirtual isEmpty : ()Z
/*     */       //   136: ifeq -> 142
/*     */       //   139: aload #6
/*     */       //   141: athrow
/*     */       //   142: aload_0
/*     */       //   143: getfield p$4 : Lscala/concurrent/impl/Promise$DefaultPromise;
/*     */       //   146: aload #7
/*     */       //   148: invokevirtual get : ()Ljava/lang/Object;
/*     */       //   151: checkcast java/lang/Throwable
/*     */       //   154: invokevirtual failure : (Ljava/lang/Throwable;)Lscala/concurrent/Promise;
/*     */       //   157: astore #8
/*     */       //   159: aload #8
/*     */       //   161: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #250	-> 0
/*     */       //   #249	-> 0
/*     */       //   #251	-> 25
/*     */       //   #249	-> 41
/*     */       //   #251	-> 42
/*     */       //   #253	-> 55
/*     */       //   #254	-> 82
/*     */       //   #251	-> 104
/*     */       //   #249	-> 110
/*     */       //   #251	-> 119
/*     */       //   #255	-> 121
/*     */       //   #251	-> 139
/*     */       //   #255	-> 142
/*     */       //   #249	-> 146
/*     */       //   #255	-> 148
/*     */       //   #251	-> 157
/*     */       //   #249	-> 159
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	162	0	this	Lscala/concurrent/Future$$anonfun$flatMap$1;
/*     */       //   0	162	1	x0$5	Lscala/util/Try;
/*     */       // Exception table:
/*     */       //   from	to	target	type
/*     */       //   37	110	119	finally
/*     */     }
/*     */     
/*     */     public class Future$$anonfun$flatMap$1$$anonfun$apply$3 extends AbstractFunction1<Try<S>, Promise.DefaultPromise<S>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final Promise.DefaultPromise<S> apply(Try result) {
/* 254 */         return (Promise.DefaultPromise<S>)this.$outer.p$4.complete(result);
/*     */       }
/*     */       
/*     */       public Future$$anonfun$flatMap$1$$anonfun$apply$3(Future$$anonfun$flatMap$1 $outer) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public class Future$$anonfun$filter$1 extends AbstractFunction1<T, T> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function1 pred$1;
/*     */     
/*     */     public final T apply(Object r) {
/* 278 */       if (BoxesRunTime.unboxToBoolean(this.pred$1.apply(r)))
/* 278 */         return (T)r; 
/* 278 */       throw new NoSuchElementException("Future.filter predicate is not satisfied");
/*     */     }
/*     */     
/*     */     public Future$$anonfun$filter$1(Future $outer, Function1 pred$1) {}
/*     */   }
/*     */   
/*     */   public class Future$$anonfun$collect$1 extends AbstractFunction1<T, S> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final PartialFunction pf$2;
/*     */     
/*     */     public final S apply(Object r) {
/* 307 */       return (S)this.pf$2.applyOrElse(r, (Function1)new Future$$anonfun$collect$1$$anonfun$apply$4(this));
/*     */     }
/*     */     
/*     */     public Future$$anonfun$collect$1(Future $outer, PartialFunction pf$2) {}
/*     */     
/*     */     public class Future$$anonfun$collect$1$$anonfun$apply$4 extends AbstractFunction1<T, scala.runtime.Nothing$> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final scala.runtime.Nothing$ apply(Object t) {
/* 307 */         throw new NoSuchElementException((new StringBuilder()).append("Future.collect partial function is not defined at: ").append(t).toString());
/*     */       }
/*     */       
/*     */       public Future$$anonfun$collect$1$$anonfun$apply$4(Future$$anonfun$collect$1 $outer) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public class Future$$anonfun$recover$1 extends AbstractFunction1<Try<T>, Promise<U>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Promise p$5;
/*     */     
/*     */     private final PartialFunction pf$3;
/*     */     
/*     */     public final Promise<U> apply(Try v) {
/* 324 */       return this.p$5.complete(v.recover(this.pf$3));
/*     */     }
/*     */     
/*     */     public Future$$anonfun$recover$1(Future $outer, Promise p$5, PartialFunction pf$3) {}
/*     */   }
/*     */   
/*     */   public class Future$$anonfun$recoverWith$1 extends AbstractFunction1<Try<T>, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Promise p$6;
/*     */     
/*     */     private final PartialFunction pf$4;
/*     */     
/*     */     public Future$$anonfun$recoverWith$1(Future $outer, Promise p$6, PartialFunction pf$4) {}
/*     */     
/*     */     public final Object apply(Try x0$6) {
/*     */       // Byte code:
/*     */       //   0: aload_1
/*     */       //   1: instanceof scala/util/Failure
/*     */       //   4: ifeq -> 62
/*     */       //   7: aload_1
/*     */       //   8: checkcast scala/util/Failure
/*     */       //   11: astore_2
/*     */       //   12: aload_0
/*     */       //   13: getfield pf$4 : Lscala/PartialFunction;
/*     */       //   16: aload_2
/*     */       //   17: invokevirtual exception : ()Ljava/lang/Throwable;
/*     */       //   20: new scala/concurrent/Future$$anonfun$recoverWith$1$$anonfun$apply$5
/*     */       //   23: dup
/*     */       //   24: aload_0
/*     */       //   25: invokespecial <init> : (Lscala/concurrent/Future$$anonfun$recoverWith$1;)V
/*     */       //   28: invokeinterface applyOrElse : (Ljava/lang/Object;Lscala/Function1;)Ljava/lang/Object;
/*     */       //   33: checkcast scala/concurrent/Future
/*     */       //   36: new scala/concurrent/Future$$anonfun$recoverWith$1$$anonfun$apply$6
/*     */       //   39: dup
/*     */       //   40: aload_0
/*     */       //   41: invokespecial <init> : (Lscala/concurrent/Future$$anonfun$recoverWith$1;)V
/*     */       //   44: aload_0
/*     */       //   45: getfield $outer : Lscala/concurrent/Future;
/*     */       //   48: invokestatic scala$concurrent$Future$$internalExecutor : (Lscala/concurrent/Future;)Lscala/concurrent/Future$InternalCallbackExecutor$;
/*     */       //   51: invokeinterface onComplete : (Lscala/Function1;Lscala/concurrent/ExecutionContext;)V
/*     */       //   56: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   59: goto -> 114
/*     */       //   62: aload_0
/*     */       //   63: getfield p$6 : Lscala/concurrent/Promise;
/*     */       //   66: aload_1
/*     */       //   67: invokeinterface complete : (Lscala/util/Try;)Lscala/concurrent/Promise;
/*     */       //   72: astore #5
/*     */       //   74: goto -> 116
/*     */       //   77: astore_3
/*     */       //   78: getstatic scala/util/control/NonFatal$.MODULE$ : Lscala/util/control/NonFatal$;
/*     */       //   81: aload_3
/*     */       //   82: invokevirtual unapply : (Ljava/lang/Throwable;)Lscala/Option;
/*     */       //   85: astore #4
/*     */       //   87: aload #4
/*     */       //   89: invokevirtual isEmpty : ()Z
/*     */       //   92: ifeq -> 97
/*     */       //   95: aload_3
/*     */       //   96: athrow
/*     */       //   97: aload_0
/*     */       //   98: getfield p$6 : Lscala/concurrent/Promise;
/*     */       //   101: aload #4
/*     */       //   103: invokevirtual get : ()Ljava/lang/Object;
/*     */       //   106: checkcast java/lang/Throwable
/*     */       //   109: invokeinterface failure : (Ljava/lang/Throwable;)Lscala/concurrent/Promise;
/*     */       //   114: astore #5
/*     */       //   116: aload #5
/*     */       //   118: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #344	-> 0
/*     */       //   #343	-> 0
/*     */       //   #343	-> 16
/*     */       //   #344	-> 17
/*     */       //   #345	-> 62
/*     */       //   #344	-> 77
/*     */       //   #343	-> 101
/*     */       //   #344	-> 103
/*     */       //   #343	-> 116
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	119	0	this	Lscala/concurrent/Future$$anonfun$recoverWith$1;
/*     */       //   0	119	1	x0$6	Lscala/util/Try;
/*     */       // Exception table:
/*     */       //   from	to	target	type
/*     */       //   12	62	77	finally
/*     */     }
/*     */     
/*     */     public class Future$$anonfun$recoverWith$1$$anonfun$apply$5 extends AbstractFunction1<Throwable, Future<T>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final Future<T> apply(Throwable x$2) {
/* 344 */         return this.$outer.$outer;
/*     */       }
/*     */       
/*     */       public Future$$anonfun$recoverWith$1$$anonfun$apply$5(Future$$anonfun$recoverWith$1 $outer) {}
/*     */     }
/*     */     
/*     */     public class Future$$anonfun$recoverWith$1$$anonfun$apply$6 extends AbstractFunction1<Try<U>, Promise<U>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final Promise<U> apply(Try<U> result) {
/* 344 */         return this.$outer.p$6.complete(result);
/*     */       }
/*     */       
/*     */       public Future$$anonfun$recoverWith$1$$anonfun$apply$6(Future$$anonfun$recoverWith$1 $outer) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public class Future$$anonfun$zip$1 extends AbstractFunction1<Try<T>, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Future.InternalCallbackExecutor$ ec$1;
/*     */     
/*     */     public final Promise p$7;
/*     */     
/*     */     private final Future that$1;
/*     */     
/*     */     public Future$$anonfun$zip$1(Future $outer, Future.InternalCallbackExecutor$ ec$1, Promise p$7, Future that$1) {}
/*     */     
/*     */     public final Object apply(Try x0$7) {
/* 361 */       if (x0$7 instanceof Failure) {
/* 361 */         Failure failure = (Failure)x0$7;
/* 361 */         Promise promise = this.p$7.complete((Try)failure);
/*     */       } else {
/* 363 */         if (x0$7 instanceof Success) {
/* 363 */           Success success = (Success)x0$7;
/* 363 */           this.that$1.onComplete((Function1)new Future$$anonfun$zip$1$$anonfun$apply$7(this, ($anonfun$zip$1)success), this.ec$1);
/* 363 */           return BoxedUnit.UNIT;
/*     */         } 
/*     */         throw new MatchError(x0$7);
/*     */       } 
/*     */       return SYNTHETIC_LOCAL_VARIABLE_4;
/*     */     }
/*     */     
/*     */     public class Future$$anonfun$zip$1$$anonfun$apply$7 extends AbstractFunction1<Try<U>, Promise<Tuple2<T, U>>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final Success x2$2;
/*     */       
/*     */       public final Promise<Tuple2<T, U>> apply(Try c) {
/* 363 */         return this.$outer.p$7.complete(c.map((Function1)new Future$$anonfun$zip$1$$anonfun$apply$7$$anonfun$apply$8(this)));
/*     */       }
/*     */       
/*     */       public Future$$anonfun$zip$1$$anonfun$apply$7(Future$$anonfun$zip$1 $outer, Success x2$2) {}
/*     */       
/*     */       public class Future$$anonfun$zip$1$$anonfun$apply$7$$anonfun$apply$8 extends AbstractFunction1<U, Tuple2<T, U>> implements Serializable {
/*     */         public static final long serialVersionUID = 0L;
/*     */         
/*     */         public final Tuple2<T, U> apply(Object s2) {
/* 363 */           return new Tuple2(this.$outer.x2$2.value(), s2);
/*     */         }
/*     */         
/*     */         public Future$$anonfun$zip$1$$anonfun$apply$7$$anonfun$apply$8(Future$$anonfun$zip$1$$anonfun$apply$7 $outer) {}
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public class Future$$anonfun$fallbackTo$1 extends AbstractFunction1<Try<T>, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Future.InternalCallbackExecutor$ ec$2;
/*     */     
/*     */     public final Promise p$8;
/*     */     
/*     */     private final Future that$2;
/*     */     
/*     */     public Future$$anonfun$fallbackTo$1(Future $outer, Future.InternalCallbackExecutor$ ec$2, Promise p$8, Future that$2) {}
/*     */     
/*     */     public final Object apply(Try x0$8) {
/* 385 */       if (x0$8 instanceof Success) {
/* 385 */         Success success = (Success)x0$8;
/* 385 */         Promise promise = this.p$8.complete((Try)success);
/*     */       } else {
/* 387 */         if (x0$8 instanceof Failure) {
/* 387 */           Failure failure = (Failure)x0$8;
/* 387 */           this.that$2.onComplete((Function1)new Future$$anonfun$fallbackTo$1$$anonfun$apply$9(this, ($anonfun$fallbackTo$1)failure), this.ec$2);
/* 387 */           return BoxedUnit.UNIT;
/*     */         } 
/*     */         throw new MatchError(x0$8);
/*     */       } 
/*     */       return SYNTHETIC_LOCAL_VARIABLE_4;
/*     */     }
/*     */     
/*     */     public class Future$$anonfun$fallbackTo$1$$anonfun$apply$9 extends AbstractFunction1<Try<U>, Promise<U>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Failure x5$1;
/*     */       
/*     */       public Future$$anonfun$fallbackTo$1$$anonfun$apply$9(Future$$anonfun$fallbackTo$1 $outer, Failure x5$1) {}
/*     */       
/*     */       public final Promise<U> apply(Try x0$9) {
/*     */         Promise<U> promise;
/* 387 */         if (x0$9 instanceof Success) {
/* 387 */           Success success = (Success)x0$9;
/* 387 */           promise = this.$outer.p$8.complete((Try)success);
/*     */         } else {
/* 389 */           promise = this.$outer.p$8.complete((Try)this.x5$1);
/*     */         } 
/*     */         return promise;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public class Future$$anonfun$mapTo$1 extends AbstractFunction1<T, S> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Class boxedClass$1;
/*     */     
/*     */     public final S apply(Object s) {
/* 405 */       return this.boxedClass$1.cast(s);
/*     */     }
/*     */     
/*     */     public Future$$anonfun$mapTo$1(Future $outer, Class boxedClass$1) {}
/*     */   }
/*     */   
/*     */   public class Future$$anonfun$andThen$1 extends AbstractFunction1<Try<T>, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Promise p$9;
/*     */     
/*     */     private final PartialFunction pf$5;
/*     */     
/*     */     public final Object apply(Try x0$10) {
/* 433 */       Try try_ = x0$10;
/*     */       try {
/* 433 */         return 
/* 434 */           this.pf$5.applyOrElse(x0$10, (Function1)scala.Predef$.MODULE$.conforms());
/*     */       } finally {
/* 434 */         this.p$9.complete(try_);
/*     */       } 
/*     */     }
/*     */     
/*     */     public Future$$anonfun$andThen$1(Future $outer, Promise p$9, PartialFunction pf$5) {}
/*     */   }
/*     */   
/*     */   private Future$() {
/* 448 */     MODULE$ = this;
/* 451 */     Class<boolean> clazz = boolean.class;
/* 451 */     scala.Predef$ predef$1 = scala.Predef$.MODULE$;
/* 451 */     scala.Predef$ArrowAssoc$ predef$ArrowAssoc$1 = scala.Predef$ArrowAssoc$.MODULE$;
/* 451 */     (new Tuple2[9])[0] = new Tuple2(clazz, Boolean.class);
/* 452 */     Class<byte> clazz1 = byte.class;
/* 452 */     scala.Predef$ predef$2 = scala.Predef$.MODULE$;
/* 452 */     scala.Predef$ArrowAssoc$ predef$ArrowAssoc$2 = scala.Predef$ArrowAssoc$.MODULE$;
/* 452 */     (new Tuple2[9])[1] = new Tuple2(clazz1, Byte.class);
/* 453 */     Class<char> clazz2 = char.class;
/* 453 */     scala.Predef$ predef$3 = scala.Predef$.MODULE$;
/* 453 */     scala.Predef$ArrowAssoc$ predef$ArrowAssoc$3 = scala.Predef$ArrowAssoc$.MODULE$;
/* 453 */     (new Tuple2[9])[2] = new Tuple2(clazz2, Character.class);
/* 454 */     Class<short> clazz3 = short.class;
/* 454 */     scala.Predef$ predef$4 = scala.Predef$.MODULE$;
/* 454 */     scala.Predef$ArrowAssoc$ predef$ArrowAssoc$4 = scala.Predef$ArrowAssoc$.MODULE$;
/* 454 */     (new Tuple2[9])[3] = new Tuple2(clazz3, Short.class);
/* 455 */     Class<int> clazz4 = int.class;
/* 455 */     scala.Predef$ predef$5 = scala.Predef$.MODULE$;
/* 455 */     scala.Predef$ArrowAssoc$ predef$ArrowAssoc$5 = scala.Predef$ArrowAssoc$.MODULE$;
/* 455 */     (new Tuple2[9])[4] = new Tuple2(clazz4, Integer.class);
/* 456 */     Class<long> clazz5 = long.class;
/* 456 */     scala.Predef$ predef$6 = scala.Predef$.MODULE$;
/* 456 */     scala.Predef$ArrowAssoc$ predef$ArrowAssoc$6 = scala.Predef$ArrowAssoc$.MODULE$;
/* 456 */     (new Tuple2[9])[5] = new Tuple2(clazz5, Long.class);
/* 457 */     Class<float> clazz6 = float.class;
/* 457 */     scala.Predef$ predef$7 = scala.Predef$.MODULE$;
/* 457 */     scala.Predef$ArrowAssoc$ predef$ArrowAssoc$7 = scala.Predef$ArrowAssoc$.MODULE$;
/* 457 */     (new Tuple2[9])[6] = new Tuple2(clazz6, Float.class);
/* 458 */     Class<double> clazz7 = double.class;
/* 458 */     scala.Predef$ predef$8 = scala.Predef$.MODULE$;
/* 458 */     (new Tuple2[9])[7] = scala.Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(clazz7, Double.class);
/* 459 */     (new Tuple2[9])[8] = scala.Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(scala.Predef$.MODULE$.any2ArrowAssoc(BoxedUnit.TYPE), BoxedUnit.class);
/*     */     this.toBoxed = (Map<Class<?>, Class<?>>)scala.Predef$.MODULE$.Map().apply((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new Tuple2[9]));
/*     */   }
/*     */   
/*     */   public Map<Class<?>, Class<?>> toBoxed() {
/*     */     return this.toBoxed;
/*     */   }
/*     */   
/*     */   public <T> Future<T> failed(Throwable exception) {
/* 467 */     return Promise$.MODULE$.<T>failed(exception).future();
/*     */   }
/*     */   
/*     */   public <T> Future<T> successful(Object result) {
/* 474 */     return Promise$.MODULE$.<T>successful((T)result).future();
/*     */   }
/*     */   
/*     */   public <T> Future<T> apply(Function0 body, ExecutionContext execctx) {
/* 485 */     return Future$.MODULE$.apply(body, execctx);
/*     */   }
/*     */   
/*     */   public <A, M extends TraversableOnce<Object>> Future<M> sequence(TraversableOnce in, CanBuildFrom cbf, ExecutionContext executor) {
/* 493 */     return ((Future)in.foldLeft(Promise$.MODULE$.<Builder>successful(cbf.apply(in)).future(), (Function2)new Future$$anonfun$sequence$1(executor))).map((Function1)new Future$$anonfun$sequence$2(), executor);
/*     */   }
/*     */   
/*     */   public static class Future$$anonfun$sequence$1 extends AbstractFunction2<Future<Builder<A, M>>, Object, Future<Builder<A, M>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final ExecutionContext executor$1;
/*     */     
/*     */     public final Future<Builder<A, M>> apply(Future fr, Object fa) {
/*     */       return fr.flatMap((Function1)new Future$$anonfun$sequence$1$$anonfun$apply$10(this, fa), this.executor$1);
/*     */     }
/*     */     
/*     */     public Future$$anonfun$sequence$1(ExecutionContext executor$1) {}
/*     */     
/*     */     public class Future$$anonfun$sequence$1$$anonfun$apply$10 extends AbstractFunction1<Builder<A, M>, Future<Builder<A, M>>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Object fa$1;
/*     */       
/*     */       public final Future<Builder<A, M>> apply(Builder r) {
/*     */         return ((Future)this.fa$1).map((Function1)new Future$$anonfun$sequence$1$$anonfun$apply$10$$anonfun$apply$11(this, r), this.$outer.executor$1);
/*     */       }
/*     */       
/*     */       public Future$$anonfun$sequence$1$$anonfun$apply$10(Future$$anonfun$sequence$1 $outer, Object fa$1) {}
/*     */       
/*     */       public class Future$$anonfun$sequence$1$$anonfun$apply$10$$anonfun$apply$11 extends AbstractFunction1<A, Builder<A, M>> implements Serializable {
/*     */         public static final long serialVersionUID = 0L;
/*     */         
/*     */         private final Builder r$1;
/*     */         
/*     */         public final Builder<A, M> apply(Object a) {
/*     */           return this.r$1.$plus$eq(a);
/*     */         }
/*     */         
/*     */         public Future$$anonfun$sequence$1$$anonfun$apply$10$$anonfun$apply$11(Future$$anonfun$sequence$1$$anonfun$apply$10 $outer, Builder r$1) {}
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Future$$anonfun$sequence$2 extends AbstractFunction1<Builder<A, M>, M> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final M apply(Builder x$3) {
/* 493 */       return (M)x$3.result();
/*     */     }
/*     */   }
/*     */   
/*     */   public <T> Future<T> firstCompletedOf(TraversableOnce futures, ExecutionContext executor) {
/* 499 */     Promise<?> p = Promise$.MODULE$.apply();
/* 500 */     Future$$anonfun$1 future$$anonfun$1 = new Future$$anonfun$1(p);
/* 501 */     futures.foreach((Function1)new Future$$anonfun$firstCompletedOf$1(executor, (Function1)future$$anonfun$1));
/* 502 */     return (Future)p.future();
/*     */   }
/*     */   
/*     */   public static class Future$$anonfun$1 extends AbstractFunction1<Try<T>, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Promise p$10;
/*     */     
/*     */     public final void apply(Try x$4) {
/*     */       this.p$10.tryComplete(x$4);
/*     */     }
/*     */     
/*     */     public Future$$anonfun$1(Promise p$10) {}
/*     */   }
/*     */   
/*     */   public static class Future$$anonfun$firstCompletedOf$1 extends AbstractFunction1<Future<T>, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ExecutionContext executor$2;
/*     */     
/*     */     private final Function1 completeFirst$1;
/*     */     
/*     */     public final void apply(Future x$5) {
/*     */       x$5.onComplete(this.completeFirst$1, this.executor$2);
/*     */     }
/*     */     
/*     */     public Future$$anonfun$firstCompletedOf$1(ExecutionContext executor$2, Function1 completeFirst$1) {}
/*     */   }
/*     */   
/*     */   public <T> Future<Option<T>> find(TraversableOnce futurestravonce, Function1 predicate, ExecutionContext executor) {
/* 508 */     Buffer futures = futurestravonce.toBuffer();
/* 511 */     Promise<?> result = Promise$.MODULE$.apply();
/* 512 */     AtomicInteger ref = new AtomicInteger(futures.size());
/* 513 */     Future$$anonfun$2 future$$anonfun$2 = new Future$$anonfun$2(predicate, result, ref);
/* 524 */     futures.foreach((Function1)new Future$$anonfun$find$1(executor, (Function1)future$$anonfun$2));
/* 526 */     return futures.isEmpty() ? Promise$.MODULE$.successful(scala.None$.MODULE$).future() : (Future)result.future();
/*     */   }
/*     */   
/*     */   public static class Future$$anonfun$2 extends AbstractFunction1<Try<T>, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function1 predicate$1;
/*     */     
/*     */     private final Promise result$1;
/*     */     
/*     */     private final AtomicInteger ref$1;
/*     */     
/*     */     public Future$$anonfun$2(Function1 predicate$1, Promise result$1, AtomicInteger ref$1) {}
/*     */     
/*     */     public final void apply(Try v) {
/*     */       // Byte code:
/*     */       //   0: nop
/*     */       //   1: aload_1
/*     */       //   2: instanceof scala/util/Success
/*     */       //   5: ifeq -> 60
/*     */       //   8: aload_1
/*     */       //   9: checkcast scala/util/Success
/*     */       //   12: astore_2
/*     */       //   13: aload_0
/*     */       //   14: getfield predicate$1 : Lscala/Function1;
/*     */       //   17: aload_2
/*     */       //   18: invokevirtual value : ()Ljava/lang/Object;
/*     */       //   21: invokeinterface apply : (Ljava/lang/Object;)Ljava/lang/Object;
/*     */       //   26: invokestatic unboxToBoolean : (Ljava/lang/Object;)Z
/*     */       //   29: ifeq -> 60
/*     */       //   32: aload_0
/*     */       //   33: getfield result$1 : Lscala/concurrent/Promise;
/*     */       //   36: new scala/util/Success
/*     */       //   39: dup
/*     */       //   40: new scala/Some
/*     */       //   43: dup
/*     */       //   44: aload_2
/*     */       //   45: invokevirtual value : ()Ljava/lang/Object;
/*     */       //   48: invokespecial <init> : (Ljava/lang/Object;)V
/*     */       //   51: invokespecial <init> : (Ljava/lang/Object;)V
/*     */       //   54: invokeinterface tryComplete : (Lscala/util/Try;)Z
/*     */       //   59: pop
/*     */       //   60: aload_0
/*     */       //   61: getfield ref$1 : Ljava/util/concurrent/atomic/AtomicInteger;
/*     */       //   64: invokevirtual decrementAndGet : ()I
/*     */       //   67: iconst_0
/*     */       //   68: if_icmpne -> 91
/*     */       //   71: aload_0
/*     */       //   72: getfield result$1 : Lscala/concurrent/Promise;
/*     */       //   75: new scala/util/Success
/*     */       //   78: dup
/*     */       //   79: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */       //   82: invokespecial <init> : (Ljava/lang/Object;)V
/*     */       //   85: invokeinterface tryComplete : (Lscala/util/Try;)Z
/*     */       //   90: pop
/*     */       //   91: return
/*     */       //   92: astore_3
/*     */       //   93: aload_0
/*     */       //   94: getfield ref$1 : Ljava/util/concurrent/atomic/AtomicInteger;
/*     */       //   97: invokevirtual decrementAndGet : ()I
/*     */       //   100: iconst_0
/*     */       //   101: if_icmpne -> 124
/*     */       //   104: aload_0
/*     */       //   105: getfield result$1 : Lscala/concurrent/Promise;
/*     */       //   108: new scala/util/Success
/*     */       //   111: dup
/*     */       //   112: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */       //   115: invokespecial <init> : (Ljava/lang/Object;)V
/*     */       //   118: invokeinterface tryComplete : (Lscala/util/Try;)Z
/*     */       //   123: pop
/*     */       //   124: aload_3
/*     */       //   125: athrow
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #514	-> 0
/*     */       //   #515	-> 1
/*     */       //   #514	-> 17
/*     */       //   #515	-> 18
/*     */       //   #514	-> 44
/*     */       //   #515	-> 45
/*     */       //   #519	-> 60
/*     */       //   #520	-> 71
/*     */       //   #513	-> 91
/*     */       //   #519	-> 92
/*     */       //   #520	-> 104
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	126	0	this	Lscala/concurrent/Future$$anonfun$2;
/*     */       //   0	126	1	v	Lscala/util/Try;
/*     */       // Exception table:
/*     */       //   from	to	target	type
/*     */       //   0	60	92	finally
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Future$$anonfun$find$1 extends AbstractFunction1<Future<T>, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ExecutionContext executor$3;
/*     */     
/*     */     private final Function1 search$1;
/*     */     
/*     */     public final void apply(Future x$6) {
/*     */       x$6.onComplete(this.search$1, this.executor$3);
/*     */     }
/*     */     
/*     */     public Future$$anonfun$find$1(ExecutionContext executor$3, Function1 search$1) {}
/*     */   }
/*     */   
/*     */   public <T, R> Future<R> fold(TraversableOnce futures, Object zero, Function2 foldFun, ExecutionContext executor) {
/* 541 */     return futures.isEmpty() ? successful((R)zero) : 
/* 542 */       sequence(futures, (CanBuildFrom<TraversableOnce, ?, TraversableOnce>)scala.collection.TraversableOnce$.MODULE$.OnceCanBuildFrom(), executor).map((Function1)new Future$$anonfun$fold$1(zero, foldFun), executor);
/*     */   }
/*     */   
/*     */   public static class Future$$anonfun$fold$1 extends AbstractFunction1<TraversableOnce<T>, R> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Object zero$1;
/*     */     
/*     */     private final Function2 foldFun$1;
/*     */     
/*     */     public final R apply(TraversableOnce x$7) {
/* 542 */       return (R)x$7.foldLeft(this.zero$1, this.foldFun$1);
/*     */     }
/*     */     
/*     */     public Future$$anonfun$fold$1(Object zero$1, Function2 foldFun$1) {}
/*     */   }
/*     */   
/*     */   public <T, R> Future<R> reduce(TraversableOnce futures, Function2 op, ExecutionContext executor) {
/* 553 */     return futures.isEmpty() ? failed(new NoSuchElementException("reduce attempted on empty collection")) : 
/* 554 */       sequence(futures, (CanBuildFrom<TraversableOnce, ?, TraversableOnce>)scala.collection.TraversableOnce$.MODULE$.OnceCanBuildFrom(), executor).map((Function1)new Future$$anonfun$reduce$1(op), executor);
/*     */   }
/*     */   
/*     */   public static class Future$$anonfun$reduce$1 extends AbstractFunction1<TraversableOnce<T>, R> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function2 op$1;
/*     */     
/*     */     public final R apply(TraversableOnce x$8) {
/* 554 */       return (R)x$8.reduceLeft(this.op$1);
/*     */     }
/*     */     
/*     */     public Future$$anonfun$reduce$1(Function2 op$1) {}
/*     */   }
/*     */   
/*     */   public <A, B, M extends TraversableOnce<Object>> Future<M> traverse(TraversableOnce in, Function1 fn, CanBuildFrom cbf, ExecutionContext executor) {
/* 569 */     return ((Future)in.foldLeft(Promise$.MODULE$.<Builder>successful(cbf.apply(in)).future(), (Function2)new Future$$anonfun$traverse$1(fn, executor))).map((Function1)new Future$$anonfun$traverse$2(), executor);
/*     */   }
/*     */   
/*     */   public static class Future$$anonfun$traverse$1 extends AbstractFunction2<Future<Builder<B, M>>, Object, Future<Builder<B, M>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function1 fn$1;
/*     */     
/*     */     public final ExecutionContext executor$4;
/*     */     
/*     */     public Future$$anonfun$traverse$1(Function1 fn$1, ExecutionContext executor$4) {}
/*     */     
/*     */     public final Future<Builder<B, M>> apply(Future fr, Object a) {
/*     */       Future fb = (Future)this.fn$1.apply(a);
/*     */       return fr.flatMap((Function1)new Future$$anonfun$traverse$1$$anonfun$apply$12(this, fb), this.executor$4);
/*     */     }
/*     */     
/*     */     public class Future$$anonfun$traverse$1$$anonfun$apply$12 extends AbstractFunction1<Builder<B, M>, Future<Builder<B, M>>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Future fb$1;
/*     */       
/*     */       public final Future<Builder<B, M>> apply(Builder r) {
/*     */         return this.fb$1.map((Function1)new Future$$anonfun$traverse$1$$anonfun$apply$12$$anonfun$apply$13(this, r), this.$outer.executor$4);
/*     */       }
/*     */       
/*     */       public Future$$anonfun$traverse$1$$anonfun$apply$12(Future$$anonfun$traverse$1 $outer, Future fb$1) {}
/*     */       
/*     */       public class Future$$anonfun$traverse$1$$anonfun$apply$12$$anonfun$apply$13 extends AbstractFunction1<B, Builder<B, M>> implements Serializable {
/*     */         public static final long serialVersionUID = 0L;
/*     */         
/*     */         private final Builder r$2;
/*     */         
/*     */         public final Builder<B, M> apply(Object b) {
/*     */           return this.r$2.$plus$eq(b);
/*     */         }
/*     */         
/*     */         public Future$$anonfun$traverse$1$$anonfun$apply$12$$anonfun$apply$13(Future$$anonfun$traverse$1$$anonfun$apply$12 $outer, Builder r$2) {}
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Future$$anonfun$traverse$2 extends AbstractFunction1<Builder<B, M>, M> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final M apply(Builder x$9) {
/* 569 */       return (M)x$9.result();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\concurrent\Future$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
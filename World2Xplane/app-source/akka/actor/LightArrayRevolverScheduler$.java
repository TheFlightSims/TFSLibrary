/*     */ package akka.actor;
/*     */ 
/*     */ import akka.dispatch.AbstractNodeQueue;
/*     */ import akka.util.Unsafe;
/*     */ import java.util.concurrent.atomic.AtomicLong;
/*     */ import java.util.concurrent.atomic.AtomicReference;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Serializable;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.TraversableLike;
/*     */ import scala.collection.immutable.Seq;
/*     */ import scala.collection.immutable.Vector;
/*     */ import scala.concurrent.ExecutionContext;
/*     */ import scala.concurrent.Promise;
/*     */ import scala.concurrent.duration.FiniteDuration;
/*     */ import scala.concurrent.duration.package;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class LightArrayRevolverScheduler$ {
/*     */   public static final LightArrayRevolverScheduler$ MODULE$;
/*     */   
/*     */   public final long akka$actor$LightArrayRevolverScheduler$$taskOffset;
/*     */   
/*     */   public final Runnable akka$actor$LightArrayRevolverScheduler$$CancelledTask;
/*     */   
/*     */   public final Runnable akka$actor$LightArrayRevolverScheduler$$ExecutedTask;
/*     */   
/*     */   private final LightArrayRevolverScheduler.TimerTask akka$actor$LightArrayRevolverScheduler$$NotCancellable;
/*     */   
/*     */   private final Cancellable akka$actor$LightArrayRevolverScheduler$$InitialRepeatMarker;
/*     */   
/*     */   public class $anonfun$1 extends AbstractFunction1.mcZI.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(int ticks) {
/* 192 */       return apply$mcZI$sp(ticks);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZI$sp(int ticks) {
/* 192 */       return ((ticks & ticks - 1) == 0);
/*     */     }
/*     */     
/*     */     public $anonfun$1(LightArrayRevolverScheduler $outer) {}
/*     */   }
/*     */   
/*     */   public class $anonfun$2 extends AbstractFunction0<String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String apply() {
/* 192 */       return "ticks-per-wheel must be a power of 2";
/*     */     }
/*     */     
/*     */     public $anonfun$2(LightArrayRevolverScheduler $outer) {}
/*     */   }
/*     */   
/*     */   public class $anonfun$4 extends AbstractFunction1<FiniteDuration, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(FiniteDuration x$1) {
/* 195 */       return !(!x$1.$greater$eq((new package.DurationInt(scala.concurrent.duration.package$.MODULE$.DurationInt(10))).millis()) && akka.util.Helpers$.MODULE$.isWindows());
/*     */     }
/*     */     
/*     */     public $anonfun$4(LightArrayRevolverScheduler $outer) {}
/*     */   }
/*     */   
/*     */   public class $anonfun$3 extends AbstractFunction0<String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String apply() {
/* 195 */       return "minimum supported akka.scheduler.tick-duration on Windows is 10ms";
/*     */     }
/*     */     
/*     */     public $anonfun$3(LightArrayRevolverScheduler $outer) {}
/*     */   }
/*     */   
/*     */   public class $anonfun$6 extends AbstractFunction1<FiniteDuration, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(FiniteDuration x$2) {
/* 196 */       return x$2.$greater$eq((new package.DurationInt(scala.concurrent.duration.package$.MODULE$.DurationInt(1))).millis());
/*     */     }
/*     */     
/*     */     public $anonfun$6(LightArrayRevolverScheduler $outer) {}
/*     */   }
/*     */   
/*     */   public class $anonfun$5 extends AbstractFunction0<String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String apply() {
/* 196 */       return "minimum supported akka.scheduler.tick-duration is 1ms";
/*     */     }
/*     */     
/*     */     public $anonfun$5(LightArrayRevolverScheduler $outer) {}
/*     */   }
/*     */   
/*     */   public class LightArrayRevolverScheduler$$anon$2 extends AtomicReference<Cancellable> implements Cancellable {
/*     */     public LightArrayRevolverScheduler$$anon$2(LightArrayRevolverScheduler $outer, FiniteDuration initialDelay$1, FiniteDuration delay$1, Runnable runnable$1, ExecutionContext preparedEC$1) {
/* 236 */       super(LightArrayRevolverScheduler$.MODULE$.akka$actor$LightArrayRevolverScheduler$$InitialRepeatMarker());
/* 237 */       compareAndSet(LightArrayRevolverScheduler$.MODULE$.akka$actor$LightArrayRevolverScheduler$$InitialRepeatMarker(), $outer.akka$actor$LightArrayRevolverScheduler$$schedule(
/* 238 */             preparedEC$1, 
/* 239 */             new LightArrayRevolverScheduler$$anon$2$$anon$1(this, initialDelay$1, delay$1, runnable$1, preparedEC$1), 
/*     */             
/* 250 */             $outer.akka$actor$LightArrayRevolverScheduler$$roundUp(initialDelay$1)));
/*     */     }
/*     */     
/*     */     public class LightArrayRevolverScheduler$$anon$2$$anon$1 extends AtomicLong implements Runnable {
/*     */       private final FiniteDuration delay$1;
/*     */       
/*     */       private final Runnable runnable$1;
/*     */       
/*     */       private final ExecutionContext preparedEC$1;
/*     */       
/*     */       public LightArrayRevolverScheduler$$anon$2$$anon$1(LightArrayRevolverScheduler$$anon$2 $outer, FiniteDuration initialDelay$1, FiniteDuration delay$1, Runnable runnable$1, ExecutionContext preparedEC$1) {
/*     */         super($outer.akka$actor$LightArrayRevolverScheduler$$anon$$$outer().clock() + initialDelay$1.toNanos());
/*     */       }
/*     */       
/*     */       public void run() {
/*     */         try {
/*     */           this.runnable$1.run();
/*     */           long driftNanos = this.$outer.akka$actor$LightArrayRevolverScheduler$$anon$$$outer().clock() - getAndAdd(this.delay$1.toNanos());
/*     */           if (this.$outer.get() != null)
/*     */             this.$outer.akka$actor$LightArrayRevolverScheduler$$anon$$swap(this.$outer.akka$actor$LightArrayRevolverScheduler$$anon$$$outer().akka$actor$LightArrayRevolverScheduler$$schedule(this.preparedEC$1, this, scala.concurrent.duration.Duration$.MODULE$.fromNanos(Math.max(this.delay$1.toNanos() - driftNanos, 1L)))); 
/*     */         } catch (SchedulerException schedulerException) {}
/*     */       }
/*     */     }
/*     */     
/*     */     public void akka$actor$LightArrayRevolverScheduler$$anon$$swap(Cancellable c) {
/*     */       while (true) {
/* 253 */         Cancellable cancellable = get();
/* 254 */         if (cancellable == null) {
/* 254 */           c.cancel();
/* 254 */           BoxedUnit boxedUnit = (c == null) ? BoxedUnit.UNIT : BoxedUnit.UNIT;
/* 255 */         } else if (compareAndSet(cancellable, c)) {
/* 255 */           BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */         } else {
/* 255 */           c = c;
/*     */           continue;
/*     */         } 
/*     */         return;
/*     */       } 
/*     */     }
/*     */     
/*     */     public final boolean cancel() {
/*     */       boolean bool;
/*     */       while (true) {
/* 260 */         Cancellable cancellable = get();
/* 261 */         if (cancellable == null) {
/* 261 */           boolean bool1 = false;
/*     */           break;
/*     */         } 
/* 263 */         null;
/* 264 */         null;
/* 264 */         if (compareAndSet(cancellable, null)) {
/*     */         
/*     */         } else {
/*     */           continue;
/*     */         } 
/* 264 */         bool = cancellable.cancel() ? compareAndSet(cancellable, null) : true;
/*     */         break;
/*     */       } 
/*     */       return bool;
/*     */     }
/*     */     
/*     */     public boolean isCancelled() {
/* 268 */       return (get() == null);
/*     */     }
/*     */   }
/*     */   
/*     */   public class LightArrayRevolverScheduler$$anonfun$close$1 extends AbstractFunction1<LightArrayRevolverScheduler.TimerTask, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public LightArrayRevolverScheduler$$anonfun$close$1(LightArrayRevolverScheduler $outer) {}
/*     */     
/*     */     public final void apply(LightArrayRevolverScheduler.TimerTask task) {
/*     */       try {
/* 282 */         task.run();
/*     */       } finally {
/* 282 */         Exception exception1 = null, exception2 = exception1;
/* 283 */         if (exception2 instanceof InterruptedException) {
/* 283 */           InterruptedException interruptedException = (InterruptedException)exception2;
/* 283 */           throw interruptedException;
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public class $anon$8 implements Runnable {
/*     */     private int tick;
/*     */     
/*     */     private final LightArrayRevolverScheduler.TaskQueue[] akka$actor$LightArrayRevolverScheduler$$anon$$wheel;
/*     */     
/*     */     public $anon$8(LightArrayRevolverScheduler $outer) {
/* 339 */       this.tick = 0;
/* 340 */       this.akka$actor$LightArrayRevolverScheduler$$anon$$wheel = (LightArrayRevolverScheduler.TaskQueue[])scala.Array$.MODULE$.fill($outer.WheelSize(), (Function0)new $anonfun$7(this), scala.reflect.ClassTag$.MODULE$.apply(LightArrayRevolverScheduler.TaskQueue.class));
/*     */     }
/*     */     
/*     */     private int tick() {
/*     */       return this.tick;
/*     */     }
/*     */     
/*     */     private void tick_$eq(int x$1) {
/*     */       this.tick = x$1;
/*     */     }
/*     */     
/*     */     public LightArrayRevolverScheduler.TaskQueue[] akka$actor$LightArrayRevolverScheduler$$anon$$wheel() {
/* 340 */       return this.akka$actor$LightArrayRevolverScheduler$$anon$$wheel;
/*     */     }
/*     */     
/*     */     public class $anonfun$7 extends AbstractFunction0<LightArrayRevolverScheduler.TaskQueue> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final LightArrayRevolverScheduler.TaskQueue apply() {
/* 340 */         return new LightArrayRevolverScheduler.TaskQueue();
/*     */       }
/*     */       
/*     */       public $anonfun$7(LightArrayRevolverScheduler.$anon$8 $outer) {}
/*     */     }
/*     */     
/*     */     public final Vector akka$actor$LightArrayRevolverScheduler$$anon$$collect$1(LightArrayRevolverScheduler.TaskQueue q, Vector acc) {
/*     */       while (true) {
/* 344 */         LightArrayRevolverScheduler.TaskHolder taskHolder = (LightArrayRevolverScheduler.TaskHolder)q.poll();
/* 345 */         if (taskHolder == null)
/* 345 */           return acc; 
/* 346 */         acc = (Vector)acc.$colon$plus(taskHolder, scala.collection.immutable.Vector$.MODULE$.canBuildFrom());
/* 346 */         q = q;
/*     */       } 
/*     */     }
/*     */     
/*     */     private Seq<LightArrayRevolverScheduler.TimerTask> clearAll() {
/* 349 */       return (Seq<LightArrayRevolverScheduler.TimerTask>)((TraversableLike)scala.runtime.RichInt$.MODULE$.until$extension0(scala.Predef$.MODULE$.intWrapper(0), this.$outer.WheelSize()).flatMap((Function1)new LightArrayRevolverScheduler$$anon$8$$anonfun$clearAll$1(this), scala.collection.immutable.IndexedSeq$.MODULE$.canBuildFrom())).$plus$plus((GenTraversableOnce)akka$actor$LightArrayRevolverScheduler$$anon$$collect$1(this.$outer.akka$actor$LightArrayRevolverScheduler$$queue(), scala.package$.MODULE$.Vector().empty()), scala.collection.immutable.IndexedSeq$.MODULE$.canBuildFrom());
/*     */     }
/*     */     
/*     */     public class LightArrayRevolverScheduler$$anon$8$$anonfun$clearAll$1 extends AbstractFunction1<Object, Vector<LightArrayRevolverScheduler.TimerTask>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final Vector<LightArrayRevolverScheduler.TimerTask> apply(int i) {
/* 349 */         return this.$outer.akka$actor$LightArrayRevolverScheduler$$anon$$collect$1(this.$outer.akka$actor$LightArrayRevolverScheduler$$anon$$wheel()[i], scala.package$.MODULE$.Vector().empty());
/*     */       }
/*     */       
/*     */       public LightArrayRevolverScheduler$$anon$8$$anonfun$clearAll$1(LightArrayRevolverScheduler.$anon$8 $outer) {}
/*     */     }
/*     */     
/*     */     private void checkQueue(long time) {
/*     */       while (true) {
/*     */         int futureTick, offset, bucket;
/* 353 */         AbstractNodeQueue.Node node = this.$outer.akka$actor$LightArrayRevolverScheduler$$queue().pollNode();
/* 354 */         if (node == null)
/* 354 */           BoxedUnit boxedUnit = BoxedUnit.UNIT; 
/* 356 */         int i = ((LightArrayRevolverScheduler.TaskHolder)node.value).ticks();
/* 356 */         switch (i) {
/*     */           default:
/* 359 */             futureTick = 
/*     */               
/* 363 */               (int)((time - this.$outer.akka$actor$LightArrayRevolverScheduler$$start() + i * this.$outer.akka$actor$LightArrayRevolverScheduler$$tickNanos() + this.$outer.akka$actor$LightArrayRevolverScheduler$$tickNanos() - 1L) / this.$outer.akka$actor$LightArrayRevolverScheduler$$tickNanos());
/* 366 */             offset = futureTick - tick();
/* 367 */             bucket = futureTick & this.$outer.akka$actor$LightArrayRevolverScheduler$$wheelMask();
/* 368 */             ((LightArrayRevolverScheduler.TaskHolder)node.value).ticks_$eq(offset);
/* 369 */             akka$actor$LightArrayRevolverScheduler$$anon$$wheel()[bucket].addNode(node);
/*     */           case 0:
/*     */             break;
/*     */         } 
/*     */         BoxesRunTime.boxToBoolean(((LightArrayRevolverScheduler.TaskHolder)node.value).executeTask());
/* 371 */         time = time;
/*     */       } 
/*     */     }
/*     */     
/*     */     public final void run() {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: invokespecial nextTick : ()V
/*     */       //   4: return
/*     */       //   5: astore_1
/*     */       //   6: aload_0
/*     */       //   7: getfield $outer : Lakka/actor/LightArrayRevolverScheduler;
/*     */       //   10: getfield akka$actor$LightArrayRevolverScheduler$$log : Lakka/event/LoggingAdapter;
/*     */       //   13: aload_1
/*     */       //   14: ldc 'exception on LARS’ timer thread'
/*     */       //   16: invokeinterface error : (Ljava/lang/Throwable;Ljava/lang/String;)V
/*     */       //   21: aload_0
/*     */       //   22: getfield $outer : Lakka/actor/LightArrayRevolverScheduler;
/*     */       //   25: invokevirtual akka$actor$LightArrayRevolverScheduler$$stopped : ()Ljava/util/concurrent/atomic/AtomicReference;
/*     */       //   28: invokevirtual get : ()Ljava/lang/Object;
/*     */       //   31: checkcast scala/concurrent/Promise
/*     */       //   34: astore_2
/*     */       //   35: aload_2
/*     */       //   36: ifnonnull -> 79
/*     */       //   39: aload_0
/*     */       //   40: getfield $outer : Lakka/actor/LightArrayRevolverScheduler;
/*     */       //   43: getfield akka$actor$LightArrayRevolverScheduler$$threadFactory : Ljava/util/concurrent/ThreadFactory;
/*     */       //   46: aload_0
/*     */       //   47: invokeinterface newThread : (Ljava/lang/Runnable;)Ljava/lang/Thread;
/*     */       //   52: astore #4
/*     */       //   54: aload_0
/*     */       //   55: getfield $outer : Lakka/actor/LightArrayRevolverScheduler;
/*     */       //   58: getfield akka$actor$LightArrayRevolverScheduler$$log : Lakka/event/LoggingAdapter;
/*     */       //   61: ldc 'starting new LARS thread'
/*     */       //   63: invokeinterface info : (Ljava/lang/String;)V
/*     */       //   68: aload #4
/*     */       //   70: invokevirtual start : ()V
/*     */       //   73: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   76: goto -> 168
/*     */       //   79: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */       //   82: aload_0
/*     */       //   83: getfield $outer : Lakka/actor/LightArrayRevolverScheduler;
/*     */       //   86: invokevirtual akka$actor$LightArrayRevolverScheduler$$stopped : ()Ljava/util/concurrent/atomic/AtomicReference;
/*     */       //   89: aload_2
/*     */       //   90: getstatic scala/concurrent/Promise$.MODULE$ : Lscala/concurrent/Promise$;
/*     */       //   93: getstatic scala/collection/immutable/Nil$.MODULE$ : Lscala/collection/immutable/Nil$;
/*     */       //   96: invokevirtual successful : (Ljava/lang/Object;)Lscala/concurrent/Promise;
/*     */       //   99: invokevirtual compareAndSet : (Ljava/lang/Object;Ljava/lang/Object;)Z
/*     */       //   102: new akka/actor/LightArrayRevolverScheduler$$anon$8$$anonfun$run$1
/*     */       //   105: dup
/*     */       //   106: aload_0
/*     */       //   107: invokespecial <init> : (Lakka/actor/LightArrayRevolverScheduler$$anon$8;)V
/*     */       //   110: invokevirtual assert : (ZLscala/Function0;)V
/*     */       //   113: aload_2
/*     */       //   114: aload_0
/*     */       //   115: invokespecial clearAll : ()Lscala/collection/immutable/Seq;
/*     */       //   118: invokeinterface success : (Ljava/lang/Object;)Lscala/concurrent/Promise;
/*     */       //   123: astore_3
/*     */       //   124: goto -> 182
/*     */       //   127: astore #5
/*     */       //   129: aload_0
/*     */       //   130: getfield $outer : Lakka/actor/LightArrayRevolverScheduler;
/*     */       //   133: getfield akka$actor$LightArrayRevolverScheduler$$log : Lakka/event/LoggingAdapter;
/*     */       //   136: aload #5
/*     */       //   138: ldc 'LARS cannot start new thread, ship’s going down!'
/*     */       //   140: invokeinterface error : (Ljava/lang/Throwable;Ljava/lang/String;)V
/*     */       //   145: aload_0
/*     */       //   146: getfield $outer : Lakka/actor/LightArrayRevolverScheduler;
/*     */       //   149: invokevirtual akka$actor$LightArrayRevolverScheduler$$stopped : ()Ljava/util/concurrent/atomic/AtomicReference;
/*     */       //   152: getstatic scala/concurrent/Promise$.MODULE$ : Lscala/concurrent/Promise$;
/*     */       //   155: getstatic scala/collection/immutable/Nil$.MODULE$ : Lscala/collection/immutable/Nil$;
/*     */       //   158: invokevirtual successful : (Ljava/lang/Object;)Lscala/concurrent/Promise;
/*     */       //   161: invokevirtual set : (Ljava/lang/Object;)V
/*     */       //   164: aload_0
/*     */       //   165: invokespecial clearAll : ()Lscala/collection/immutable/Seq;
/*     */       //   168: pop
/*     */       //   169: aload_0
/*     */       //   170: getfield $outer : Lakka/actor/LightArrayRevolverScheduler;
/*     */       //   173: aload #4
/*     */       //   175: invokevirtual akka$actor$LightArrayRevolverScheduler$$timerThread_$eq : (Ljava/lang/Thread;)V
/*     */       //   178: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   181: astore_3
/*     */       //   182: aload_3
/*     */       //   183: pop
/*     */       //   184: aload_1
/*     */       //   185: athrow
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #375	-> 0
/*     */       //   #377	-> 5
/*     */       //   #378	-> 6
/*     */       //   #379	-> 21
/*     */       //   #380	-> 35
/*     */       //   #381	-> 39
/*     */       //   #382	-> 54
/*     */       //   #383	-> 68
/*     */       //   #392	-> 79
/*     */       //   #393	-> 113
/*     */       //   #391	-> 123
/*     */       //   #385	-> 127
/*     */       //   #383	-> 127
/*     */       //   #386	-> 129
/*     */       //   #387	-> 145
/*     */       //   #388	-> 164
/*     */       //   #383	-> 168
/*     */       //   #390	-> 169
/*     */       //   #380	-> 181
/*     */       //   #379	-> 182
/*     */       //   #395	-> 184
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	186	0	this	Lakka/actor/LightArrayRevolverScheduler$$anon$8;
/*     */       //   54	127	4	thread	Ljava/lang/Thread;
/*     */       // Exception table:
/*     */       //   from	to	target	type
/*     */       //   0	4	5	finally
/*     */       //   68	79	127	finally
/*     */     }
/*     */     
/*     */     public class LightArrayRevolverScheduler$$anon$8$$anonfun$run$1 extends AbstractFunction0<String> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final String apply() {
/* 392 */         return "Stop signal violated in LARS";
/*     */       }
/*     */       
/*     */       public LightArrayRevolverScheduler$$anon$8$$anonfun$run$1(LightArrayRevolverScheduler.$anon$8 $outer) {}
/*     */     }
/*     */     
/*     */     private final void nextTick() {
/*     */       Promise<Seq<LightArrayRevolverScheduler.TimerTask>> promise;
/*     */       do {
/* 399 */         long time = this.$outer.clock();
/* 400 */         long sleepTime = this.$outer.akka$actor$LightArrayRevolverScheduler$$start() + tick() * this.$outer.akka$actor$LightArrayRevolverScheduler$$tickNanos() - time;
/* 402 */         if (sleepTime > 0L) {
/* 404 */           checkQueue(time);
/* 405 */           this.$outer.waitNanos(sleepTime);
/*     */         } else {
/* 407 */           int bucket = tick() & this.$outer.akka$actor$LightArrayRevolverScheduler$$wheelMask();
/* 408 */           LightArrayRevolverScheduler.TaskQueue tasks = akka$actor$LightArrayRevolverScheduler$$anon$$wheel()[bucket];
/* 409 */           LightArrayRevolverScheduler.TaskQueue putBack = new LightArrayRevolverScheduler.TaskQueue();
/* 423 */           executeBucket$1(tasks, putBack);
/* 424 */           akka$actor$LightArrayRevolverScheduler$$anon$$wheel()[bucket] = putBack;
/* 426 */           tick_$eq(tick() + 1);
/*     */         } 
/* 428 */         promise = this.$outer.akka$actor$LightArrayRevolverScheduler$$stopped().get();
/* 429 */       } while (promise == null);
/* 431 */       scala.Predef$.MODULE$.assert(this.$outer.akka$actor$LightArrayRevolverScheduler$$stopped().compareAndSet(promise, scala.concurrent.Promise$.MODULE$.successful(scala.collection.immutable.Nil$.MODULE$)), (Function0)new LightArrayRevolverScheduler$$anon$8$$anonfun$nextTick$1(this));
/* 432 */       promise.success(clearAll());
/* 432 */       BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */     }
/*     */     
/*     */     private final void executeBucket$1(LightArrayRevolverScheduler.TaskQueue tasks$1, LightArrayRevolverScheduler.TaskQueue putBack$1) {
/*     */       while (true) {
/*     */         AbstractNodeQueue.Node node = tasks$1.pollNode();
/*     */         if (node == null)
/*     */           BoxedUnit boxedUnit = BoxedUnit.UNIT; 
/*     */         LightArrayRevolverScheduler.TaskHolder task = (LightArrayRevolverScheduler.TaskHolder)node.value;
/*     */         task.ticks_$eq(task.ticks() - this.$outer.WheelSize());
/*     */         putBack$1.addNode(node);
/*     */         task.isCancelled() ? BoxedUnit.UNIT : ((task.ticks() >= this.$outer.WheelSize()) ? BoxedUnit.UNIT : BoxesRunTime.boxToBoolean(task.executeTask()));
/*     */       } 
/*     */     }
/*     */     
/*     */     public class LightArrayRevolverScheduler$$anon$8$$anonfun$nextTick$1 extends AbstractFunction0<String> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final String apply() {
/*     */         return "Stop signal violated in LARS";
/*     */       }
/*     */       
/*     */       public LightArrayRevolverScheduler$$anon$8$$anonfun$nextTick$1(LightArrayRevolverScheduler.$anon$8 $outer) {}
/*     */     }
/*     */   }
/*     */   
/*     */   private LightArrayRevolverScheduler$() {
/* 440 */     MODULE$ = this;
/* 441 */     this.akka$actor$LightArrayRevolverScheduler$$taskOffset = Unsafe.instance.objectFieldOffset(LightArrayRevolverScheduler.TaskHolder.class.getDeclaredField("task"));
/* 486 */     this.akka$actor$LightArrayRevolverScheduler$$CancelledTask = new LightArrayRevolverScheduler.$anon$10();
/* 487 */     this.akka$actor$LightArrayRevolverScheduler$$ExecutedTask = new LightArrayRevolverScheduler.$anon$9();
/* 489 */     this.akka$actor$LightArrayRevolverScheduler$$NotCancellable = new LightArrayRevolverScheduler.$anon$3();
/* 495 */     this.akka$actor$LightArrayRevolverScheduler$$InitialRepeatMarker = new LightArrayRevolverScheduler.$anon$11();
/*     */   }
/*     */   
/*     */   public static class $anon$10 implements Runnable {
/*     */     public void run() {}
/*     */   }
/*     */   
/*     */   public static class $anon$9 implements Runnable {
/*     */     public void run() {}
/*     */   }
/*     */   
/*     */   public LightArrayRevolverScheduler.TimerTask akka$actor$LightArrayRevolverScheduler$$NotCancellable() {
/*     */     return this.akka$actor$LightArrayRevolverScheduler$$NotCancellable;
/*     */   }
/*     */   
/*     */   public static class $anon$3 implements LightArrayRevolverScheduler.TimerTask {
/*     */     public boolean cancel() {
/*     */       return false;
/*     */     }
/*     */     
/*     */     public boolean isCancelled() {
/*     */       return false;
/*     */     }
/*     */     
/*     */     public void run() {}
/*     */   }
/*     */   
/*     */   public Cancellable akka$actor$LightArrayRevolverScheduler$$InitialRepeatMarker() {
/* 495 */     return this.akka$actor$LightArrayRevolverScheduler$$InitialRepeatMarker;
/*     */   }
/*     */   
/*     */   public static class $anon$11 implements Cancellable {
/*     */     public boolean cancel() {
/* 496 */       return false;
/*     */     }
/*     */     
/*     */     public boolean isCancelled() {
/* 497 */       return false;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\LightArrayRevolverScheduler$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
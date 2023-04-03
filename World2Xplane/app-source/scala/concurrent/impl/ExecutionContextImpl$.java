/*     */ package scala.concurrent.impl;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.Callable;
/*     */ import java.util.concurrent.Executor;
/*     */ import java.util.concurrent.ExecutorService;
/*     */ import java.util.concurrent.Future;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import scala.Function1;
/*     */ import scala.Serializable;
/*     */ import scala.collection.immutable.StringOps;
/*     */ import scala.concurrent.ExecutionContextExecutorService;
/*     */ import scala.concurrent.forkjoin.ForkJoinTask;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class ExecutionContextImpl$ {
/*     */   public static final ExecutionContextImpl$ MODULE$;
/*     */   
/*     */   public class $anon$4 implements Thread.UncaughtExceptionHandler {
/*     */     public $anon$4(ExecutionContextImpl $outer) {}
/*     */     
/*     */     public void uncaughtException(Thread thread, Throwable cause) {
/*  24 */       this.$outer.scala$concurrent$impl$ExecutionContextImpl$$reporter.apply(cause);
/*     */     }
/*     */   }
/*     */   
/*     */   public class ExecutionContextImpl$$anonfun$1 extends AbstractFunction1<String, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final int apply(String x$1) {
/*  66 */       scala.Predef$ predef$ = scala.Predef$.MODULE$;
/*  66 */       return (new StringOps(x$1)).toInt();
/*     */     }
/*     */     
/*     */     public ExecutionContextImpl$$anonfun$1(ExecutionContextImpl $outer) {}
/*     */   }
/*     */   
/*     */   public class ExecutionContextImpl$$anonfun$2 extends AbstractFunction1<String, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public ExecutionContextImpl$$anonfun$2(ExecutionContextImpl $outer) {}
/*     */     
/*     */     public final int apply(String x0$1) {
/*     */       // Byte code:
/*     */       //   0: aload_1
/*     */       //   1: ifnonnull -> 9
/*     */       //   4: iconst_1
/*     */       //   5: istore_2
/*     */       //   6: goto -> 37
/*     */       //   9: ldc ''
/*     */       //   11: dup
/*     */       //   12: ifnonnull -> 23
/*     */       //   15: pop
/*     */       //   16: aload_1
/*     */       //   17: ifnull -> 30
/*     */       //   20: goto -> 35
/*     */       //   23: aload_1
/*     */       //   24: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   27: ifeq -> 35
/*     */       //   30: iconst_1
/*     */       //   31: istore_2
/*     */       //   32: goto -> 37
/*     */       //   35: iconst_0
/*     */       //   36: istore_2
/*     */       //   37: iload_2
/*     */       //   38: ifeq -> 52
/*     */       //   41: invokestatic getRuntime : ()Ljava/lang/Runtime;
/*     */       //   44: invokevirtual availableProcessors : ()I
/*     */       //   47: istore #9
/*     */       //   49: goto -> 132
/*     */       //   52: aload_1
/*     */       //   53: iconst_0
/*     */       //   54: invokevirtual charAt : (I)C
/*     */       //   57: bipush #120
/*     */       //   59: if_icmpne -> 114
/*     */       //   62: getstatic scala/runtime/RichDouble$.MODULE$ : Lscala/runtime/RichDouble$;
/*     */       //   65: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */       //   68: invokestatic getRuntime : ()Ljava/lang/Runtime;
/*     */       //   71: invokevirtual availableProcessors : ()I
/*     */       //   74: i2d
/*     */       //   75: new scala/collection/immutable/StringOps
/*     */       //   78: dup
/*     */       //   79: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */       //   82: aload_1
/*     */       //   83: iconst_1
/*     */       //   84: invokevirtual substring : (I)Ljava/lang/String;
/*     */       //   87: astore #4
/*     */       //   89: astore_3
/*     */       //   90: aload #4
/*     */       //   92: invokespecial <init> : (Ljava/lang/String;)V
/*     */       //   95: invokevirtual toDouble : ()D
/*     */       //   98: dmul
/*     */       //   99: dstore #6
/*     */       //   101: astore #5
/*     */       //   103: dload #6
/*     */       //   105: invokevirtual ceil$extension : (D)D
/*     */       //   108: d2i
/*     */       //   109: istore #9
/*     */       //   111: goto -> 132
/*     */       //   114: new scala/collection/immutable/StringOps
/*     */       //   117: dup
/*     */       //   118: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */       //   121: astore #8
/*     */       //   123: aload_1
/*     */       //   124: invokespecial <init> : (Ljava/lang/String;)V
/*     */       //   127: invokevirtual toInt : ()I
/*     */       //   130: istore #9
/*     */       //   132: iload #9
/*     */       //   134: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #68	-> 0
/*     */       //   #67	-> 0
/*     */       //   #69	-> 52
/*     */       //   #70	-> 114
/*     */       //   #67	-> 132
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	135	0	this	Lscala/concurrent/impl/ExecutionContextImpl$$anonfun$2;
/*     */       //   0	135	1	x0$1	Ljava/lang/String;
/*     */     }
/*     */   }
/*     */   
/*     */   public class ExecutionContextImpl$$anonfun$3 extends AbstractFunction1<String, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final int apply(String x$2) {
/*  72 */       scala.Predef$ predef$ = scala.Predef$.MODULE$;
/*  72 */       return (new StringOps(x$2)).toInt();
/*     */     }
/*     */     
/*     */     public ExecutionContextImpl$$anonfun$3(ExecutionContextImpl $outer) {}
/*     */   }
/*     */   
/*     */   public class ExecutionContextImpl$$anon$3 extends ForkJoinTask<BoxedUnit> {
/*     */     private final Runnable x1$1;
/*     */     
/*     */     public ExecutionContextImpl$$anon$3(ExecutionContextImpl $outer, Runnable x1$1) {}
/*     */     
/*     */     public final void setRawResult(BoxedUnit u) {}
/*     */     
/*     */     public final void getRawResult() {}
/*     */     
/*     */     public final boolean exec() {
/*     */       try {
/* 107 */         return true;
/*     */       } finally {
/* 109 */         Thread t = Thread.currentThread();
/* 110 */         Thread.UncaughtExceptionHandler uncaughtExceptionHandler = t.getUncaughtExceptionHandler();
/* 111 */         if (uncaughtExceptionHandler != null)
/* 112 */           uncaughtExceptionHandler.uncaughtException(t, null); 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private ExecutionContextImpl$() {
/* 128 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public ExecutionContextImpl fromExecutor(Executor e, Function1<Throwable, BoxedUnit> reporter) {
/* 129 */     return new ExecutionContextImpl(e, reporter);
/*     */   }
/*     */   
/*     */   public Function1<Throwable, BoxedUnit> fromExecutor$default$2() {
/* 129 */     return scala.concurrent.ExecutionContext$.MODULE$.defaultReporter();
/*     */   }
/*     */   
/*     */   public Function1<Throwable, BoxedUnit> fromExecutorService$default$2() {
/* 130 */     return scala.concurrent.ExecutionContext$.MODULE$.defaultReporter();
/*     */   }
/*     */   
/*     */   public ExecutionContextImpl fromExecutorService(ExecutorService es, Function1 reporter) {
/* 131 */     return new ExecutionContextImpl$$anon$1(es, reporter);
/*     */   }
/*     */   
/*     */   public static class ExecutionContextImpl$$anon$1 extends ExecutionContextImpl implements ExecutionContextExecutorService {
/*     */     public ExecutionContextImpl$$anon$1(ExecutorService es$1, Function1<Throwable, BoxedUnit> reporter$1) {
/* 131 */       super(es$1, reporter$1);
/*     */     }
/*     */     
/*     */     private final ExecutorService asExecutorService() {
/* 132 */       return (ExecutorService)executor();
/*     */     }
/*     */     
/*     */     public void execute(Runnable command) {
/* 133 */       executor().execute(command);
/*     */     }
/*     */     
/*     */     public void shutdown() {
/* 134 */       asExecutorService().shutdown();
/*     */     }
/*     */     
/*     */     public List<Runnable> shutdownNow() {
/* 135 */       return asExecutorService().shutdownNow();
/*     */     }
/*     */     
/*     */     public boolean isShutdown() {
/* 136 */       return asExecutorService().isShutdown();
/*     */     }
/*     */     
/*     */     public boolean isTerminated() {
/* 137 */       return asExecutorService().isTerminated();
/*     */     }
/*     */     
/*     */     public boolean awaitTermination(long l, TimeUnit timeUnit) {
/* 138 */       return asExecutorService().awaitTermination(l, timeUnit);
/*     */     }
/*     */     
/*     */     public <T> Future<T> submit(Callable<T> callable) {
/* 139 */       return asExecutorService().submit(callable);
/*     */     }
/*     */     
/*     */     public <T> Future<T> submit(Runnable runnable, Object t) {
/* 140 */       return asExecutorService().submit(runnable, (T)t);
/*     */     }
/*     */     
/*     */     public Future<?> submit(Runnable runnable) {
/* 141 */       return asExecutorService().submit(runnable);
/*     */     }
/*     */     
/*     */     public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> callables) {
/* 142 */       return asExecutorService().invokeAll(callables);
/*     */     }
/*     */     
/*     */     public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> callables, long l, TimeUnit timeUnit) {
/* 143 */       return asExecutorService().invokeAll(callables, l, timeUnit);
/*     */     }
/*     */     
/*     */     public <T> T invokeAny(Collection<? extends Callable<T>> callables) {
/* 144 */       return asExecutorService().invokeAny(callables);
/*     */     }
/*     */     
/*     */     public <T> T invokeAny(Collection<? extends Callable<T>> callables, long l, TimeUnit timeUnit) {
/* 145 */       return asExecutorService().invokeAny(callables, l, timeUnit);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\concurrent\impl\ExecutionContextImpl$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
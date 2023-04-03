/*     */ package scala.concurrent.impl;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.Callable;
/*     */ import java.util.concurrent.Executor;
/*     */ import java.util.concurrent.ExecutorService;
/*     */ import java.util.concurrent.Future;
/*     */ import java.util.concurrent.LinkedBlockingQueue;
/*     */ import java.util.concurrent.ThreadFactory;
/*     */ import java.util.concurrent.ThreadPoolExecutor;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Option;
/*     */ import scala.Predef$;
/*     */ import scala.Serializable;
/*     */ import scala.collection.immutable.StringOps;
/*     */ import scala.concurrent.BlockContext;
/*     */ import scala.concurrent.CanAwait;
/*     */ import scala.concurrent.ExecutionContext;
/*     */ import scala.concurrent.ExecutionContextExecutor;
/*     */ import scala.concurrent.ExecutionContextExecutorService;
/*     */ import scala.concurrent.forkjoin.ForkJoinPool;
/*     */ import scala.concurrent.forkjoin.ForkJoinTask;
/*     */ import scala.concurrent.forkjoin.ForkJoinWorkerThread;
/*     */ import scala.math.package$;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ObjectRef;
/*     */ import scala.util.control.NonFatal$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005}d!B\001\003\001\031A!\001F#yK\016,H/[8o\007>tG/\032=u\0236\004HN\003\002\004\t\005!\021.\0349m\025\t)a!\001\006d_:\034WO\035:f]RT\021aB\001\006g\016\fG.Y\n\004\001%i\001C\001\006\f\033\0051\021B\001\007\007\005\031\te.\037*fMB\021abD\007\002\t%\021\001\003\002\002\031\013b,7-\036;j_:\034uN\034;fqR,\0050Z2vi>\024\b\002\003\n\001\005\003\005\013\021\002\013\002\005\025\0348\001\001\t\003+mi\021A\006\006\003\013]Q!\001G\r\002\tU$\030\016\034\006\0025\005!!.\031<b\023\tabC\001\005Fq\026\034W\017^8s\021!q\002A!A!\002\023y\022\001\003:fa>\024H/\032:\021\t)\001#EL\005\003C\031\021\021BR;oGRLwN\\\031\021\005\rZcB\001\023*\035\t)\003&D\001'\025\t93#\001\004=e>|GOP\005\002\017%\021!FB\001\ba\006\0347.Y4f\023\taSFA\005UQJ|w/\0312mK*\021!F\002\t\003\025=J!\001\r\004\003\tUs\027\016\036\005\007e\001!\tAA\032\002\rqJg.\033;?)\r!dg\016\t\003k\001i\021A\001\005\006%E\002\r\001\006\005\006=E\002\ra\b\005\007s\001\001\013\021\002\036\0021Ut7-Y;hQR,\005pY3qi&|g\016S1oI2,'\017\005\002<\003:\021AhP\007\002{)\021a(G\001\005Y\006tw-\003\002A{\0051A\013\033:fC\022L!AQ\"\0031Us7-Y;hQR,\005pY3qi&|g\016S1oI2,'O\003\002A{!9Q\t\001b\001\n\0031\025\001C3yK\016,Ho\034:\026\003QAa\001\023\001!\002\023!\022!C3yK\016,Ho\034:!\r\021Q\005\001A&\003)\021+g-Y;miRC'/Z1e\r\006\034Go\034:z'\021IEj\024*\021\005qj\025B\001(>\005\031y%M[3diB\021Q\003U\005\003#Z\021Q\002\0265sK\006$g)Y2u_JL\bCA*Z\035\t!v+D\001V\025\t1F!\001\005g_J\\'n\\5o\023\tAV+\001\007G_J\\'j\\5o!>|G.\003\002[7\nYbi\034:l\025>LgnV8sW\026\024H\013\033:fC\0224\025m\031;pefT!\001W+\t\021uK%\021!Q\001\ny\013\001\002Z1f[>t\027n\031\t\003\025}K!\001\031\004\003\017\t{w\016\\3b]\")!'\023C\001ER\0211-\032\t\003I&k\021\001\001\005\006;\006\004\rA\030\005\006O&#\t\001[\001\005o&\024X-\006\002jYR\021!.\036\t\003W2d\001\001B\003nM\n\007aNA\001U#\ty'\017\005\002\013a&\021\021O\002\002\b\035>$\b.\0338h!\ta4/\003\002u{\t1A\013\033:fC\022DQA\0364A\002)\fa\001\0365sK\006$\007\"\002=J\t\003I\030!\0038foRC'/Z1e)\t\021(\020C\003|o\002\007A0\001\005sk:t\027M\0317f!\taT0\003\002{\tA!+\0368oC\ndW\r\003\004y\023\022\005\021\021\001\013\005\003\007\tI\001E\002U\003\013I1!a\002V\005Q1uN]6K_&twk\034:lKJ$\006N]3bI\"9\0211B@A\002\0055\021a\0014kaB\031A+a\004\n\007\005EQK\001\007G_J\\'j\\5o!>|G\016C\004\002\026\001!\t!a\006\002+\r\024X-\031;f\013b,7-\036;peN+'O^5dKV\021\021\021\004\t\004+\005m\021bAA\017-\tyQ\t_3dkR|'oU3sm&\034W\rC\004\002\"\001!\t!a\t\002\017\025DXmY;uKR\031a&!\n\t\rm\fy\0021\001}\021\035\tI\003\001C\001\003W\tQB]3q_J$h)Y5mkJ,Gc\001\030\002.!9\021qFA\024\001\004\021\023!\001;\b\021\005M\"\001#\001\005\003k\tA#\022=fGV$\030n\0348D_:$X\r\037;J[Bd\007cA\033\0028\0319\021A\001E\001\t\005e2cAA\034\023!9!'a\016\005\002\005uBCAA\033\021!\t\t%a\016\005\002\005\r\023\001\0044s_6,\0050Z2vi>\024H#\002\033\002F\005%\003bBA$\003\001\r\001F\001\002K\"Aa$a\020\021\002\003\007q\004\003\005\002N\005]B\021AA(\003M1'o\\7Fq\026\034W\017^8s'\026\024h/[2f)\031\t\t&!\030\002`I)\0211\013\033\002X\0319\021QKA\034\001\005E#\001\004\037sK\032Lg.Z7f]Rt\004c\001\b\002Z%\031\0211\f\003\003?\025CXmY;uS>t7i\0348uKb$X\t_3dkR|'oU3sm&\034W\rC\004\023\003\027\002\r!!\007\t\021y\tY\005%AA\002}A!\"a\031\0028E\005I\021AA3\003Y1'o\\7Fq\026\034W\017^8sI\021,g-Y;mi\022\022TCAA4U\ry\022\021N\026\003\003W\002B!!\034\002x5\021\021q\016\006\005\003c\n\031(A\005v]\016DWmY6fI*\031\021Q\017\004\002\025\005tgn\034;bi&|g.\003\003\002z\005=$!E;oG\",7m[3e-\006\024\030.\0318dK\"Q\021QPA\034#\003%\t!!\032\002;\031\024x.\\#yK\016,Ho\034:TKJ4\030nY3%I\0264\027-\0367uII\002")
/*     */ public class ExecutionContextImpl implements ExecutionContextExecutor {
/*     */   public final Function1<Throwable, BoxedUnit> scala$concurrent$impl$ExecutionContextImpl$$reporter;
/*     */   
/*     */   public final Thread.UncaughtExceptionHandler scala$concurrent$impl$ExecutionContextImpl$$uncaughtExceptionHandler;
/*     */   
/*     */   private final Executor executor;
/*     */   
/*     */   public ExecutionContext prepare() {
/*  21 */     return ExecutionContext.class.prepare((ExecutionContext)this);
/*     */   }
/*     */   
/*     */   public ExecutionContextImpl(Executor es, Function1<Throwable, BoxedUnit> reporter) {
/*     */     Executor executor;
/*  21 */     ExecutionContext.class.$init$((ExecutionContext)this);
/*  23 */     this.scala$concurrent$impl$ExecutionContextImpl$$uncaughtExceptionHandler = new $anon$4(this);
/*  28 */     if (es == null) {
/*  28 */       executor = createExecutorService();
/*     */     } else {
/*  29 */       executor = es;
/*     */     } 
/*     */     this.executor = executor;
/*     */   }
/*     */   
/*     */   public class $anon$4 implements Thread.UncaughtExceptionHandler {
/*     */     public $anon$4(ExecutionContextImpl $outer) {}
/*     */     
/*     */     public void uncaughtException(Thread thread, Throwable cause) {
/*     */       this.$outer.scala$concurrent$impl$ExecutionContextImpl$$reporter.apply(cause);
/*     */     }
/*     */   }
/*     */   
/*     */   public Executor executor() {
/*     */     return this.executor;
/*     */   }
/*     */   
/*     */   public class DefaultThreadFactory implements ThreadFactory, ForkJoinPool.ForkJoinWorkerThreadFactory {
/*     */     private final boolean daemonic;
/*     */     
/*     */     public DefaultThreadFactory(ExecutionContextImpl $outer, boolean daemonic) {}
/*     */     
/*     */     public <T extends Thread> T wire(Thread thread) {
/*  35 */       thread.setDaemon(this.daemonic);
/*  36 */       thread.setUncaughtExceptionHandler((scala$concurrent$impl$ExecutionContextImpl$DefaultThreadFactory$$$outer()).scala$concurrent$impl$ExecutionContextImpl$$uncaughtExceptionHandler);
/*  37 */       return (T)thread;
/*     */     }
/*     */     
/*     */     public Thread newThread(Runnable runnable) {
/*  40 */       return wire(new Thread(runnable));
/*     */     }
/*     */     
/*     */     public ForkJoinWorkerThread newThread(ForkJoinPool fjp) {
/*  42 */       return wire(new ExecutionContextImpl$DefaultThreadFactory$$anon$2(this, fjp));
/*     */     }
/*     */     
/*     */     public class ExecutionContextImpl$DefaultThreadFactory$$anon$2 extends ForkJoinWorkerThread implements BlockContext {
/*     */       public ExecutionContextImpl$DefaultThreadFactory$$anon$2(ExecutionContextImpl.DefaultThreadFactory $outer, ForkJoinPool fjp$1) {
/*  42 */         super(fjp$1);
/*     */       }
/*     */       
/*     */       public <T> T blockOn(Function0 thunk, CanAwait permission) {
/*  44 */         ObjectRef result = new ObjectRef(null);
/*  45 */         ForkJoinPool.managedBlock(new ExecutionContextImpl$DefaultThreadFactory$$anon$2$$anon$5(this, thunk, result));
/*  53 */         return (T)result.elem;
/*     */       }
/*     */       
/*     */       public class ExecutionContextImpl$DefaultThreadFactory$$anon$2$$anon$5 implements ForkJoinPool.ManagedBlocker {
/*     */         private volatile boolean isdone = false;
/*     */         
/*     */         private final Function0 thunk$1;
/*     */         
/*     */         private final ObjectRef result$1;
/*     */         
/*     */         private boolean isdone() {
/*     */           return this.isdone;
/*     */         }
/*     */         
/*     */         private void isdone_$eq(boolean x$1) {
/*     */           this.isdone = x$1;
/*     */         }
/*     */         
/*     */         public boolean block() {
/*     */           try {
/*     */             isdone_$eq(true);
/*     */             return true;
/*     */           } finally {
/*     */             isdone_$eq(true);
/*     */           } 
/*     */         }
/*     */         
/*     */         public boolean isReleasable() {
/*     */           return isdone();
/*     */         }
/*     */         
/*     */         public ExecutionContextImpl$DefaultThreadFactory$$anon$2$$anon$5(ExecutionContextImpl$DefaultThreadFactory$$anon$2 $outer, Function0 thunk$1, ObjectRef result$1) {}
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private final int getInt$1(String name, Function1 f) {
/*     */     try {
/*     */     
/*  61 */     } catch (Exception exception) {}
/*  61 */     return Runtime.getRuntime().availableProcessors();
/*     */   }
/*     */   
/*     */   private final int range$1(int floor, int desired, int ceiling) {
/*  63 */     while (ceiling < floor) {
/*  63 */       ceiling = floor;
/*  63 */       desired = desired;
/*  63 */       floor = ceiling;
/*     */     } 
/*  63 */     return package$.MODULE$.min(package$.MODULE$.max(desired, floor), ceiling);
/*     */   }
/*     */   
/*     */   public ExecutorService createExecutorService() {
/*  65 */     int desiredParallelism = range$1(
/*  66 */         getInt$1("scala.concurrent.context.minThreads", (Function1)new ExecutionContextImpl$$anonfun$1(this)), 
/*  67 */         getInt$1("scala.concurrent.context.numThreads", (Function1)new ExecutionContextImpl$$anonfun$2(this)), 
/*     */         
/*  72 */         getInt$1("scala.concurrent.context.maxThreads", (Function1)new ExecutionContextImpl$$anonfun$3(this)));
/*  74 */     DefaultThreadFactory threadFactory = new DefaultThreadFactory(this, true);
/*     */     try {
/*     */     
/*     */     } finally {
/*  76 */       Exception exception = null;
/*  83 */       Option option = NonFatal$.MODULE$.unapply(exception);
/*  83 */       if (option.isEmpty())
/*     */         throw exception; 
/*  84 */       System.err.println("Failed to create ForkJoinPool for the default ExecutionContext, falling back to ThreadPoolExecutor");
/*  84 */       ((Throwable)option
/*  85 */         .get()).printStackTrace(System.err);
/*  86 */       ThreadPoolExecutor exec = new ThreadPoolExecutor(
/*  87 */           desiredParallelism, 
/*  88 */           desiredParallelism, 
/*  89 */           5L, 
/*  90 */           TimeUnit.MINUTES, 
/*  91 */           new LinkedBlockingQueue<Runnable>(), 
/*  92 */           threadFactory);
/*     */     } 
/*  95 */     return exec;
/*     */   }
/*     */   
/*     */   public class ExecutionContextImpl$$anonfun$1 extends AbstractFunction1<String, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final int apply(String x$1) {
/*     */       Predef$ predef$ = Predef$.MODULE$;
/*     */       return (new StringOps(x$1)).toInt();
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
/*     */       Predef$ predef$ = Predef$.MODULE$;
/*     */       return (new StringOps(x$2)).toInt();
/*     */     }
/*     */     
/*     */     public ExecutionContextImpl$$anonfun$3(ExecutionContextImpl $outer) {}
/*     */   }
/*     */   
/*     */   public void execute(Runnable runnable) {
/* 100 */     Executor executor = executor();
/* 101 */     if (executor instanceof ForkJoinPool) {
/*     */       ForkJoinTask forkJoinTask;
/* 101 */       ForkJoinPool forkJoinPool = (ForkJoinPool)executor;
/* 102 */       if (runnable instanceof ForkJoinTask) {
/* 102 */         ForkJoinTask forkJoinTask1 = (ForkJoinTask)runnable;
/*     */       } else {
/* 104 */         forkJoinTask = new ExecutionContextImpl$$anon$3(this, runnable);
/*     */       } 
/*     */       Thread thread = Thread.currentThread();
/* 119 */       if (thread instanceof ForkJoinWorkerThread) {
/* 119 */         ForkJoinWorkerThread forkJoinWorkerThread = (ForkJoinWorkerThread)thread;
/* 119 */         if (forkJoinWorkerThread.getPool() == forkJoinPool) {
/* 119 */           forkJoinTask.fork();
/*     */           return;
/*     */         } 
/*     */       } 
/* 120 */       forkJoinPool.execute(forkJoinTask);
/*     */     } else {
/* 122 */       executor.execute(runnable);
/*     */     } 
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
/*     */         return true;
/*     */       } finally {
/*     */         Thread t = Thread.currentThread();
/*     */         Thread.UncaughtExceptionHandler uncaughtExceptionHandler = t.getUncaughtExceptionHandler();
/*     */         if (uncaughtExceptionHandler != null)
/*     */           uncaughtExceptionHandler.uncaughtException(t, null); 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public void reportFailure(Throwable t) {
/* 125 */     this.scala$concurrent$impl$ExecutionContextImpl$$reporter.apply(t);
/*     */   }
/*     */   
/*     */   public static Function1<Throwable, BoxedUnit> fromExecutorService$default$2() {
/*     */     return ExecutionContextImpl$.MODULE$.fromExecutorService$default$2();
/*     */   }
/*     */   
/*     */   public static Function1<Throwable, BoxedUnit> fromExecutor$default$2() {
/*     */     return ExecutionContextImpl$.MODULE$.fromExecutor$default$2();
/*     */   }
/*     */   
/*     */   public static ExecutionContextImpl fromExecutorService(ExecutorService paramExecutorService, Function1<Throwable, BoxedUnit> paramFunction1) {
/*     */     return ExecutionContextImpl$.MODULE$.fromExecutorService(paramExecutorService, paramFunction1);
/*     */   }
/*     */   
/*     */   public static ExecutionContextImpl fromExecutor(Executor paramExecutor, Function1<Throwable, BoxedUnit> paramFunction1) {
/*     */     return ExecutionContextImpl$.MODULE$.fromExecutor(paramExecutor, paramFunction1);
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


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\concurrent\impl\ExecutionContextImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
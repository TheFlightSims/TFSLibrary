/*     */ package akka.dispatch;
/*     */ 
/*     */ import akka.util.Helpers$;
/*     */ import akka.util.Helpers$ConfigOps$;
/*     */ import com.typesafe.config.Config;
/*     */ import java.util.concurrent.BlockingQueue;
/*     */ import java.util.concurrent.ThreadFactory;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.None$;
/*     */ import scala.Option;
/*     */ import scala.Predef$;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.collection.Seq;
/*     */ import scala.concurrent.duration.Duration;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001A3A!\001\002\001\017\tqB\013\033:fC\022\004vn\0347Fq\026\034W\017^8s\007>tg-[4ve\006$xN\035\006\003\007\021\t\001\002Z5ta\006$8\r\033\006\002\013\005!\021m[6b\007\001\031\"\001\001\005\021\005%QQ\"\001\002\n\005-\021!aG#yK\016,Ho\034:TKJ4\030nY3D_:4\027nZ;sCR|'\017\003\005\016\001\t\005\t\025!\003\017\003\031\031wN\0344jOB\021q\"F\007\002!)\021Q\"\005\006\003%M\t\001\002^=qKN\fg-\032\006\002)\005\0311m\\7\n\005Y\001\"AB\"p]\032Lw\r\003\005\031\001\t\005\t\025!\003\032\0035\001(/\032:fcVL7/\033;fgB\021\021BG\005\0037\t\021q\003R5ta\006$8\r[3s!J,'/Z9vSNLG/Z:\t\013u\001A\021\001\020\002\rqJg.\033;?)\ry\002%\t\t\003\023\001AQ!\004\017A\0029AQ\001\007\017A\002eAqa\t\001C\002\023\005A%\001\tuQJ,\027\r\032)p_2\034uN\0344jOV\tQ\005\005\002\nM%\021qE\001\002\021)\"\024X-\0313Q_>d7i\0348gS\036Da!\013\001!\002\023)\023!\005;ie\026\fG\rU8pY\016{gNZ5hA!)1\006\001C\tY\005i2M]3bi\026$\006N]3bIB{w\016\\\"p]\032LwMQ;jY\022,'\017F\002.aE\002\"!\003\030\n\005=\022!a\006+ie\026\fG\rU8pY\016{gNZ5h\005VLG\016Z3s\021\025i!\0061\001\017\021\025A\"\0061\001\032\021\025\031\004\001\"\0015\003q\031'/Z1uK\026CXmY;u_J\034VM\035<jG\0264\025m\031;pef$2!\016\035E!\tIa'\003\0028\005\t1R\t_3dkR|'oU3sm&\034WMR1di>\024\030\020C\003:e\001\007!(\001\002jIB\0211(\021\b\003y}j\021!\020\006\002}\005)1oY1mC&\021\001)P\001\007!J,G-\0324\n\005\t\033%AB*ue&twM\003\002A{!)QI\ra\001\r\006iA\017\033:fC\0224\025m\031;pef\004\"a\022(\016\003!S!!\023&\002\025\r|gnY;se\026tGO\003\002L\031\006!Q\017^5m\025\005i\025\001\0026bm\006L!a\024%\003\033QC'/Z1e\r\006\034Go\034:z\001")
/*     */ public class ThreadPoolExecutorConfigurator extends ExecutorServiceConfigurator {
/*     */   private final ThreadPoolConfig threadPoolConfig;
/*     */   
/*     */   public ThreadPoolExecutorConfigurator(Config config, DispatcherPrerequisites prerequisites) {
/* 344 */     super(config, prerequisites);
/* 346 */     this.threadPoolConfig = createThreadPoolConfigBuilder(config, prerequisites).config();
/*     */   }
/*     */   
/*     */   public ThreadPoolConfig threadPoolConfig() {
/* 346 */     return this.threadPoolConfig;
/*     */   }
/*     */   
/*     */   public ThreadPoolConfigBuilder createThreadPoolConfigBuilder(Config config, DispatcherPrerequisites prerequisites) {
/* 355 */     (new Option[1])[0] = (
/* 356 */       new Some(BoxesRunTime.boxToInteger(config.getInt("task-queue-size")))).flatMap((Function1)new ThreadPoolExecutorConfigurator$$anonfun$createThreadPoolConfigBuilder$1(this, config));
/*     */     return (new ThreadPoolConfigBuilder(new ThreadPoolConfig(ThreadPoolConfig$.MODULE$.apply$default$1(), ThreadPoolConfig$.MODULE$.apply$default$2(), ThreadPoolConfig$.MODULE$.apply$default$3(), ThreadPoolConfig$.MODULE$.apply$default$4(), ThreadPoolConfig$.MODULE$.apply$default$5(), ThreadPoolConfig$.MODULE$.apply$default$6()))).setKeepAliveTime((Duration)Helpers$ConfigOps$.MODULE$.getMillisDuration$extension(Helpers$.MODULE$.ConfigOps(config), "keep-alive-time")).setAllowCoreThreadTimeout(config.getBoolean("allow-core-timeout")).setCorePoolSizeFromFactor(config.getInt("core-pool-size-min"), config.getDouble("core-pool-size-factor"), config.getInt("core-pool-size-max")).setMaxPoolSizeFromFactor(config.getInt("max-pool-size-min"), config.getDouble("max-pool-size-factor"), config.getInt("max-pool-size-max")).configure((Seq<Option<Function1<ThreadPoolConfigBuilder, ThreadPoolConfigBuilder>>>)Predef$.MODULE$.wrapRefArray((Object[])new Option[1]));
/*     */   }
/*     */   
/*     */   public class ThreadPoolExecutorConfigurator$$anonfun$createThreadPoolConfigBuilder$1 extends AbstractFunction1<Object, Option<Function1<ThreadPoolConfigBuilder, ThreadPoolConfigBuilder>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Config config$1;
/*     */     
/*     */     public final Option<Function1<ThreadPoolConfigBuilder, ThreadPoolConfigBuilder>> apply(int x0$1) {
/* 356 */       int i = x0$1;
/* 356 */       switch (i) {
/*     */       
/*     */       } 
/* 356 */       return 
/* 357 */         (i > 0) ? (
/* 358 */         new Some(this.config$1.getString("task-queue-type"))).map((Function1)new ThreadPoolExecutorConfigurator$$anonfun$createThreadPoolConfigBuilder$1$$anonfun$apply$1(this, i))
/*     */         
/* 362 */         .map((Function1)new ThreadPoolExecutorConfigurator$$anonfun$createThreadPoolConfigBuilder$1$$anonfun$apply$2(this)) : 
/* 363 */         (Option<Function1<ThreadPoolConfigBuilder, ThreadPoolConfigBuilder>>)None$.MODULE$;
/*     */     }
/*     */     
/*     */     public ThreadPoolExecutorConfigurator$$anonfun$createThreadPoolConfigBuilder$1(ThreadPoolExecutorConfigurator $outer, Config config$1) {}
/*     */     
/*     */     public class ThreadPoolExecutorConfigurator$$anonfun$createThreadPoolConfigBuilder$1$$anonfun$apply$1 extends AbstractFunction1<String, Function0<BlockingQueue<Runnable>>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final int x1$2;
/*     */       
/*     */       public final Function0<BlockingQueue<Runnable>> apply(String x0$2) {
/*     */         // Byte code:
/*     */         //   0: aload_1
/*     */         //   1: astore_2
/*     */         //   2: ldc 'array'
/*     */         //   4: aload_2
/*     */         //   5: astore_3
/*     */         //   6: dup
/*     */         //   7: ifnonnull -> 18
/*     */         //   10: pop
/*     */         //   11: aload_3
/*     */         //   12: ifnull -> 25
/*     */         //   15: goto -> 41
/*     */         //   18: aload_3
/*     */         //   19: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */         //   22: ifeq -> 41
/*     */         //   25: getstatic akka/dispatch/ThreadPoolConfig$.MODULE$ : Lakka/dispatch/ThreadPoolConfig$;
/*     */         //   28: aload_0
/*     */         //   29: getfield x1$2 : I
/*     */         //   32: iconst_0
/*     */         //   33: invokevirtual arrayBlockingQueue : (IZ)Lscala/Function0;
/*     */         //   36: astore #4
/*     */         //   38: goto -> 125
/*     */         //   41: ldc ''
/*     */         //   43: aload_2
/*     */         //   44: astore #5
/*     */         //   46: dup
/*     */         //   47: ifnonnull -> 59
/*     */         //   50: pop
/*     */         //   51: aload #5
/*     */         //   53: ifnull -> 67
/*     */         //   56: goto -> 73
/*     */         //   59: aload #5
/*     */         //   61: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */         //   64: ifeq -> 73
/*     */         //   67: iconst_1
/*     */         //   68: istore #6
/*     */         //   70: goto -> 108
/*     */         //   73: ldc 'linked'
/*     */         //   75: aload_2
/*     */         //   76: astore #7
/*     */         //   78: dup
/*     */         //   79: ifnonnull -> 91
/*     */         //   82: pop
/*     */         //   83: aload #7
/*     */         //   85: ifnull -> 99
/*     */         //   88: goto -> 105
/*     */         //   91: aload #7
/*     */         //   93: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */         //   96: ifeq -> 105
/*     */         //   99: iconst_1
/*     */         //   100: istore #6
/*     */         //   102: goto -> 108
/*     */         //   105: iconst_0
/*     */         //   106: istore #6
/*     */         //   108: iload #6
/*     */         //   110: ifeq -> 128
/*     */         //   113: getstatic akka/dispatch/ThreadPoolConfig$.MODULE$ : Lakka/dispatch/ThreadPoolConfig$;
/*     */         //   116: aload_0
/*     */         //   117: getfield x1$2 : I
/*     */         //   120: invokevirtual linkedBlockingQueue : (I)Lscala/Function0;
/*     */         //   123: astore #4
/*     */         //   125: aload #4
/*     */         //   127: areturn
/*     */         //   128: new java/lang/IllegalArgumentException
/*     */         //   131: dup
/*     */         //   132: new scala/collection/immutable/StringOps
/*     */         //   135: dup
/*     */         //   136: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */         //   139: ldc '[%s] is not a valid task-queue-type [array|linked]!'
/*     */         //   141: invokevirtual augmentString : (Ljava/lang/String;)Ljava/lang/String;
/*     */         //   144: invokespecial <init> : (Ljava/lang/String;)V
/*     */         //   147: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */         //   150: iconst_1
/*     */         //   151: anewarray java/lang/Object
/*     */         //   154: dup
/*     */         //   155: iconst_0
/*     */         //   156: aload_2
/*     */         //   157: aastore
/*     */         //   158: invokevirtual genericWrapArray : (Ljava/lang/Object;)Lscala/collection/mutable/WrappedArray;
/*     */         //   161: invokevirtual format : (Lscala/collection/Seq;)Ljava/lang/String;
/*     */         //   164: invokespecial <init> : (Ljava/lang/String;)V
/*     */         //   167: athrow
/*     */         // Line number table:
/*     */         //   Java source line number -> byte code offset
/*     */         //   #358	-> 0
/*     */         //   #359	-> 2
/*     */         //   #360	-> 41
/*     */         //   #358	-> 125
/*     */         //   #361	-> 128
/*     */         // Local variable table:
/*     */         //   start	length	slot	name	descriptor
/*     */         //   0	168	0	this	Lakka/dispatch/ThreadPoolExecutorConfigurator$$anonfun$createThreadPoolConfigBuilder$1$$anonfun$apply$1;
/*     */         //   0	168	1	x0$2	Ljava/lang/String;
/*     */       }
/*     */       
/*     */       public ThreadPoolExecutorConfigurator$$anonfun$createThreadPoolConfigBuilder$1$$anonfun$apply$1(ThreadPoolExecutorConfigurator$$anonfun$createThreadPoolConfigBuilder$1 $outer, int x1$2) {}
/*     */     }
/*     */     
/*     */     public class ThreadPoolExecutorConfigurator$$anonfun$createThreadPoolConfigBuilder$1$$anonfun$apply$2 extends AbstractFunction1<Function0<BlockingQueue<Runnable>>, Function1<ThreadPoolConfigBuilder, ThreadPoolConfigBuilder>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final Function1<ThreadPoolConfigBuilder, ThreadPoolConfigBuilder> apply(Function0 qf) {
/*     */         return (Function1<ThreadPoolConfigBuilder, ThreadPoolConfigBuilder>)new ThreadPoolExecutorConfigurator$$anonfun$createThreadPoolConfigBuilder$1$$anonfun$apply$2$$anonfun$apply$3(this, qf);
/*     */       }
/*     */       
/*     */       public ThreadPoolExecutorConfigurator$$anonfun$createThreadPoolConfigBuilder$1$$anonfun$apply$2(ThreadPoolExecutorConfigurator$$anonfun$createThreadPoolConfigBuilder$1 $outer) {}
/*     */       
/*     */       public class ThreadPoolExecutorConfigurator$$anonfun$createThreadPoolConfigBuilder$1$$anonfun$apply$2$$anonfun$apply$3 extends AbstractFunction1<ThreadPoolConfigBuilder, ThreadPoolConfigBuilder> implements Serializable {
/*     */         public static final long serialVersionUID = 0L;
/*     */         
/*     */         private final Function0 qf$1;
/*     */         
/*     */         public final ThreadPoolConfigBuilder apply(ThreadPoolConfigBuilder q) {
/*     */           return q.setQueueFactory(this.qf$1);
/*     */         }
/*     */         
/*     */         public ThreadPoolExecutorConfigurator$$anonfun$createThreadPoolConfigBuilder$1$$anonfun$apply$2$$anonfun$apply$3(ThreadPoolExecutorConfigurator$$anonfun$createThreadPoolConfigBuilder$1$$anonfun$apply$2 $outer, Function0 qf$1) {}
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public ExecutorServiceFactory createExecutorServiceFactory(String id, ThreadFactory threadFactory) {
/* 368 */     return threadPoolConfig().createExecutorServiceFactory(id, threadFactory);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\ThreadPoolExecutorConfigurator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
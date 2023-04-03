/*     */ package akka.dispatch;
/*     */ 
/*     */ import com.typesafe.config.Config;
/*     */ import java.util.concurrent.ExecutorService;
/*     */ import java.util.concurrent.ThreadFactory;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.concurrent.forkjoin.ForkJoinPool;
/*     */ import scala.concurrent.forkjoin.ForkJoinTask;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxedUnit;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005}t!B\001\003\021\0039\021\001\b$pe.Tu.\0338Fq\026\034W\017^8s\007>tg-[4ve\006$xN\035\006\003\007\021\t\001\002Z5ta\006$8\r\033\006\002\013\005!\021m[6b\007\001\001\"\001C\005\016\003\t1QA\003\002\t\002-\021ADR8sW*{\027N\\#yK\016,Ho\034:D_:4\027nZ;sCR|'o\005\002\n\031A\021Q\002E\007\002\035)\tq\"A\003tG\006d\027-\003\002\022\035\t1\021I\\=SK\032DQaE\005\005\002Q\ta\001P5oSRtD#A\004\007\tYI!a\006\002\021\003.\\\027MR8sW*{\027N\034)p_2\0342!\006\r!!\tIb$D\001\033\025\tYB$\001\005g_J\\'n\\5o\025\tib\"\001\006d_:\034WO\035:f]RL!a\b\016\003\031\031{'o\033&pS:\004vn\0347\021\005!\t\023B\001\022\003\005-au.\0313NKR\024\030nY:\t\021\021*\"\021!Q\001\n\025\n1\002]1sC2dW\r\\5t[B\021QBJ\005\003O9\0211!\0238u\021!ISC!A!\002\023Q\023!\004;ie\026\fGMR1di>\024\030\020\005\002,]9\021\021\004L\005\003[i\tABR8sW*{\027N\034)p_2L!a\f\031\0037\031{'o\033&pS:<vN]6feRC'/Z1e\r\006\034Go\034:z\025\ti#\004\003\0053+\t\005\t\025!\0034\003e)h\016[1oI2,G-\022=dKB$\030n\0348IC:$G.\032:\021\005QbdBA\033;\033\0051$BA\0349\003\021a\027M\\4\013\003e\nAA[1wC&\0211HN\001\007)\"\024X-\0313\n\005ur$\001G+oG\006,x\r\033;Fq\016,\007\017^5p]\"\013g\016\0327fe*\0211H\016\005\006'U!\t\001\021\013\005\003\016#U\t\005\002C+5\t\021\002C\003%\001\007Q\005C\003*\001\007!\006C\0033\001\0071\007C\003H+\021\005\003*A\004fq\026\034W\017^3\025\005%c\005CA\007K\023\tYeB\001\003V]&$\b\"B'G\001\004q\025!\001:\021\005Uz\025B\001)7\005!\021VO\0348bE2,\007\"\002*\026\t\003\031\026AD1u\rVdG\016\0265s_R$H.\032\013\002)B\021Q\"V\005\003-:\021qAQ8pY\026\fgN\002\003Y\023\tI&\001E!lW\0064uN]6K_&tG+Y:l'\t9&\fE\002\0327&K!\001\030\016\003\031\031{'o\033&pS:$\026m]6\t\021y;&\021!Q\001\n9\013\001B];o]\006\024G.\032\005\006']#\t\001\031\013\003C\n\004\"AQ,\t\013y{\006\031\001(\t\013\021<F\021I3\002\031\035,GOU1x%\026\034X\017\034;\025\003%CQaZ,\005B!\fAb]3u%\006<(+Z:vYR$\"!S5\t\013)4\007\031A%\002\tUt\027\016\036\005\006Y^#)eU\001\005Kb,7\rK\002X]F\004\"!D8\n\005At!\001E*fe&\fGNV3sg&|g.V%E=\005\ta\001\002\006\003\001M\034\"A\035;\021\005!)\030B\001<\003\005m)\0050Z2vi>\0248+\032:wS\016,7i\0348gS\036,(/\031;pe\"A\001P\035B\001B\003%\0210\001\004d_:4\027n\032\t\004u\006\005Q\"A>\013\005ad(BA?\003!!\030\020]3tC\032,'\"A@\002\007\r|W.C\002\002\004m\024aaQ8oM&<\007BCA\004e\n\005\t\025!\003\002\n\005i\001O]3sKF,\030n]5uKN\0042\001CA\006\023\r\tiA\001\002\030\t&\034\b/\031;dQ\026\024\bK]3sKF,\030n]5uKNDaa\005:\005\002\005EACBA\n\003+\t9\002\005\002\te\"1\0010a\004A\002eD\001\"a\002\002\020\001\007\021\021\002\005\b\0037\021H\021AA\017\003!1\030\r\\5eCR,Gc\001\026\002 !A\021\021EA\r\001\004\t\031#A\001u!\021\t)#!\f\016\005\005\035\"bA\017\002*)\031\0211\006\035\002\tU$\030\016\\\005\005\003_\t9CA\007UQJ,\027\r\032$bGR|'/\037\004\007\003g\021\b!!\016\003=\031{'o\033&pS:,\0050Z2vi>\0248+\032:wS\016,g)Y2u_JL8#BA\031\031\005]\002c\001\005\002:%\031\0211\b\002\003-\025CXmY;u_J\034VM\035<jG\0264\025m\031;pefD!\"KA\031\005\013\007I\021AA +\005Q\003BCA\"\003c\021\t\021)A\005U\005qA\017\033:fC\0224\025m\031;pef\004\003B\003\023\0022\t\025\r\021\"\001\002HU\tQ\005\003\006\002L\005E\"\021!Q\001\n\025\nA\002]1sC2dW\r\\5t[\002BqaEA\031\t\003\ty\005\006\004\002R\005U\023q\013\t\005\003'\n\t$D\001s\021\031I\023Q\na\001U!1A%!\024A\002\025B\001\"a\027\0022\021\005\021QL\001\026GJ,\027\r^3Fq\026\034W\017^8s'\026\024h/[2f+\t\ty\006\005\003\002&\005\005\024\002BA2\003O\021q\"\022=fGV$xN]*feZL7-\032\005\b\003O\022HQAA5\003q\031'/Z1uK\026CXmY;u_J\034VM\035<jG\0264\025m\031;pef$b!a\016\002l\005u\004\002CA7\003K\002\r!a\034\002\005%$\007\003BA9\003or1!DA:\023\r\t)HD\001\007!J,G-\0324\n\t\005e\0241\020\002\007'R\024\030N\\4\013\007\005Ud\002C\004*\003K\002\r!a\t")
/*     */ public class ForkJoinExecutorConfigurator extends ExecutorServiceConfigurator {
/*     */   private final Config config;
/*     */   
/*     */   public static class AkkaForkJoinPool extends ForkJoinPool implements LoadMetrics {
/*     */     public AkkaForkJoinPool(int parallelism, ForkJoinPool.ForkJoinWorkerThreadFactory threadFactory, Thread.UncaughtExceptionHandler unhandledExceptionHandler) {
/* 376 */       super(
/*     */           
/* 379 */           parallelism, threadFactory, unhandledExceptionHandler, true);
/*     */     }
/*     */     
/*     */     public void execute(Runnable r) {
/* 381 */       if (r == null)
/* 381 */         throw new NullPointerException(); 
/* 381 */       execute(new ForkJoinExecutorConfigurator.AkkaForkJoinTask(r));
/*     */     }
/*     */     
/*     */     public boolean atFullThrottle() {
/* 383 */       return (getActiveThreadCount() >= getParallelism());
/*     */     }
/*     */   }
/*     */   
/*     */   public static class AkkaForkJoinTask extends ForkJoinTask<BoxedUnit> {
/*     */     public static final long serialVersionUID = 1L;
/*     */     
/*     */     private final Runnable runnable;
/*     */     
/*     */     public AkkaForkJoinTask(Runnable runnable) {}
/*     */     
/*     */     public void getRawResult() {}
/*     */     
/*     */     public void setRawResult(BoxedUnit unit) {}
/*     */     
/*     */     public final boolean exec() {
/*     */       try {
/* 393 */         return true;
/*     */       } finally {
/* 395 */         Thread t = Thread.currentThread();
/* 396 */         Thread.UncaughtExceptionHandler uncaughtExceptionHandler = t.getUncaughtExceptionHandler();
/* 397 */         if (uncaughtExceptionHandler == null) {
/* 397 */           BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */         } else {
/* 398 */           uncaughtExceptionHandler.uncaughtException(t, null);
/* 398 */           BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public ForkJoinExecutorConfigurator(Config config, DispatcherPrerequisites prerequisites) {
/* 405 */     super(config, prerequisites);
/*     */   }
/*     */   
/*     */   public ForkJoinPool.ForkJoinWorkerThreadFactory validate(ThreadFactory t) {
/* 408 */     ThreadFactory threadFactory = t;
/* 409 */     if (threadFactory instanceof ForkJoinPool.ForkJoinWorkerThreadFactory) {
/* 409 */       ThreadFactory threadFactory1 = threadFactory;
/* 409 */       return (ForkJoinPool.ForkJoinWorkerThreadFactory)threadFactory1;
/*     */     } 
/* 410 */     throw new IllegalStateException("The prerequisites for the ForkJoinExecutorConfigurator is a ForkJoinPool.ForkJoinWorkerThreadFactory!");
/*     */   }
/*     */   
/*     */   public class ForkJoinExecutorServiceFactory implements ExecutorServiceFactory {
/*     */     private final ForkJoinPool.ForkJoinWorkerThreadFactory threadFactory;
/*     */     
/*     */     private final int parallelism;
/*     */     
/*     */     public ForkJoinPool.ForkJoinWorkerThreadFactory threadFactory() {
/* 413 */       return this.threadFactory;
/*     */     }
/*     */     
/*     */     public ForkJoinExecutorServiceFactory(ForkJoinExecutorConfigurator $outer, ForkJoinPool.ForkJoinWorkerThreadFactory threadFactory, int parallelism) {}
/*     */     
/*     */     public int parallelism() {
/* 414 */       return this.parallelism;
/*     */     }
/*     */     
/*     */     public ExecutorService createExecutorService() {
/* 415 */       return (ExecutorService)new ForkJoinExecutorConfigurator.AkkaForkJoinPool(parallelism(), threadFactory(), MonitorableThreadFactory$.MODULE$.doNothing());
/*     */     }
/*     */   }
/*     */   
/*     */   public final ExecutorServiceFactory createExecutorServiceFactory(String id, ThreadFactory threadFactory) {
/* 418 */     ThreadFactory threadFactory2, threadFactory1 = threadFactory;
/* 419 */     if (threadFactory1 instanceof MonitorableThreadFactory) {
/* 419 */       MonitorableThreadFactory monitorableThreadFactory = (MonitorableThreadFactory)threadFactory1;
/* 421 */       threadFactory2 = monitorableThreadFactory.withName((new StringBuilder()).append(monitorableThreadFactory.name()).append("-").append(id).toString());
/*     */     } else {
/* 422 */       threadFactory2 = threadFactory1;
/*     */     } 
/*     */     ThreadFactory tf = threadFactory2;
/* 424 */     return new ForkJoinExecutorServiceFactory(this, 
/* 425 */         validate(tf), 
/* 426 */         ThreadPoolConfig$.MODULE$.scaledPoolSize(
/* 427 */           this.config.getInt("parallelism-min"), 
/* 428 */           this.config.getDouble("parallelism-factor"), 
/* 429 */           this.config.getInt("parallelism-max")));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\ForkJoinExecutorConfigurator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
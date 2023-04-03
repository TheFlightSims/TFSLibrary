/*    */ package akka.dispatch;
/*    */ 
/*    */ import java.util.concurrent.ArrayBlockingQueue;
/*    */ import java.util.concurrent.BlockingQueue;
/*    */ import java.util.concurrent.LinkedBlockingQueue;
/*    */ import java.util.concurrent.RejectedExecutionHandler;
/*    */ import java.util.concurrent.SynchronousQueue;
/*    */ import java.util.concurrent.TimeUnit;
/*    */ import scala.Function0;
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.Tuple6;
/*    */ import scala.concurrent.duration.Duration;
/*    */ import scala.runtime.AbstractFunction0;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ public final class ThreadPoolConfig$ implements Serializable {
/*    */   public static final ThreadPoolConfig$ MODULE$;
/*    */   
/*    */   private final boolean defaultAllowCoreThreadTimeout;
/*    */   
/*    */   private final int defaultCorePoolSize;
/*    */   
/*    */   private final int defaultMaxPoolSize;
/*    */   
/*    */   private final Duration defaultTimeout;
/*    */   
/*    */   private final RejectedExecutionHandler defaultRejectionPolicy;
/*    */   
/*    */   private Object readResolve() {
/* 26 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private ThreadPoolConfig$() {
/* 26 */     MODULE$ = this;
/* 29 */     this.defaultAllowCoreThreadTimeout = false;
/* 30 */     this.defaultCorePoolSize = 16;
/* 31 */     this.defaultMaxPoolSize = 128;
/* 32 */     this.defaultTimeout = (Duration)scala.concurrent.duration.Duration$.MODULE$.apply(60000L, TimeUnit.MILLISECONDS);
/* 33 */     this.defaultRejectionPolicy = new SaneRejectedExecutionHandler();
/*    */   }
/*    */   
/*    */   public boolean defaultAllowCoreThreadTimeout() {
/*    */     return this.defaultAllowCoreThreadTimeout;
/*    */   }
/*    */   
/*    */   public int defaultCorePoolSize() {
/*    */     return this.defaultCorePoolSize;
/*    */   }
/*    */   
/*    */   public int defaultMaxPoolSize() {
/*    */     return this.defaultMaxPoolSize;
/*    */   }
/*    */   
/*    */   public Duration defaultTimeout() {
/*    */     return this.defaultTimeout;
/*    */   }
/*    */   
/*    */   public RejectedExecutionHandler defaultRejectionPolicy() {
/* 33 */     return this.defaultRejectionPolicy;
/*    */   }
/*    */   
/*    */   public int scaledPoolSize(int floor, double multiplier, int ceiling) {
/* 36 */     return scala.math.package$.MODULE$.min(scala.math.package$.MODULE$.max((int)scala.runtime.RichDouble$.MODULE$.ceil$extension(scala.Predef$.MODULE$.doubleWrapper(Runtime.getRuntime().availableProcessors() * multiplier)), floor), ceiling);
/*    */   }
/*    */   
/*    */   public Function0<BlockingQueue<Runnable>> arrayBlockingQueue(int capacity, boolean fair) {
/* 38 */     return (Function0<BlockingQueue<Runnable>>)new ThreadPoolConfig$$anonfun$arrayBlockingQueue$1(capacity, fair);
/*    */   }
/*    */   
/*    */   public static class ThreadPoolConfig$$anonfun$arrayBlockingQueue$1 extends AbstractFunction0<ArrayBlockingQueue<Runnable>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final int capacity$1;
/*    */     
/*    */     private final boolean fair$1;
/*    */     
/*    */     public final ArrayBlockingQueue<Runnable> apply() {
/* 38 */       return new ArrayBlockingQueue<Runnable>(this.capacity$1, this.fair$1);
/*    */     }
/*    */     
/*    */     public ThreadPoolConfig$$anonfun$arrayBlockingQueue$1(int capacity$1, boolean fair$1) {}
/*    */   }
/*    */   
/*    */   public Function0<BlockingQueue<Runnable>> synchronousQueue(boolean fair) {
/* 40 */     return (Function0<BlockingQueue<Runnable>>)new ThreadPoolConfig$$anonfun$synchronousQueue$1(fair);
/*    */   }
/*    */   
/*    */   public static class ThreadPoolConfig$$anonfun$synchronousQueue$1 extends AbstractFunction0<SynchronousQueue<Runnable>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final boolean fair$2;
/*    */     
/*    */     public final SynchronousQueue<Runnable> apply() {
/* 40 */       return new SynchronousQueue<Runnable>(this.fair$2);
/*    */     }
/*    */     
/*    */     public ThreadPoolConfig$$anonfun$synchronousQueue$1(boolean fair$2) {}
/*    */   }
/*    */   
/*    */   public Function0<BlockingQueue<Runnable>> linkedBlockingQueue() {
/* 42 */     return (Function0<BlockingQueue<Runnable>>)new ThreadPoolConfig$$anonfun$linkedBlockingQueue$1();
/*    */   }
/*    */   
/*    */   public static class ThreadPoolConfig$$anonfun$linkedBlockingQueue$1 extends AbstractFunction0<LinkedBlockingQueue<Runnable>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final LinkedBlockingQueue<Runnable> apply() {
/* 42 */       return new LinkedBlockingQueue<Runnable>();
/*    */     }
/*    */   }
/*    */   
/*    */   public Function0<BlockingQueue<Runnable>> linkedBlockingQueue(int capacity) {
/* 44 */     return (Function0<BlockingQueue<Runnable>>)new ThreadPoolConfig$$anonfun$linkedBlockingQueue$2(capacity);
/*    */   }
/*    */   
/*    */   public static class ThreadPoolConfig$$anonfun$linkedBlockingQueue$2 extends AbstractFunction0<LinkedBlockingQueue<Runnable>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final int capacity$2;
/*    */     
/*    */     public final LinkedBlockingQueue<Runnable> apply() {
/* 44 */       return new LinkedBlockingQueue<Runnable>(this.capacity$2);
/*    */     }
/*    */     
/*    */     public ThreadPoolConfig$$anonfun$linkedBlockingQueue$2(int capacity$2) {}
/*    */   }
/*    */   
/*    */   public Function0<BlockingQueue<Runnable>> reusableQueue(BlockingQueue queue) {
/* 46 */     return (Function0<BlockingQueue<Runnable>>)new ThreadPoolConfig$$anonfun$reusableQueue$1(queue);
/*    */   }
/*    */   
/*    */   public static class ThreadPoolConfig$$anonfun$reusableQueue$1 extends AbstractFunction0<BlockingQueue<Runnable>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final BlockingQueue queue$1;
/*    */     
/*    */     public final BlockingQueue<Runnable> apply() {
/* 46 */       return this.queue$1;
/*    */     }
/*    */     
/*    */     public ThreadPoolConfig$$anonfun$reusableQueue$1(BlockingQueue queue$1) {}
/*    */   }
/*    */   
/*    */   public Function0<BlockingQueue<Runnable>> reusableQueue(Function0 queueFactory) {
/* 48 */     return reusableQueue((BlockingQueue<Runnable>)queueFactory.apply());
/*    */   }
/*    */   
/*    */   public ThreadPoolConfig apply(boolean allowCorePoolTimeout, int corePoolSize, int maxPoolSize, Duration threadTimeout, Function0<BlockingQueue<Runnable>> queueFactory, RejectedExecutionHandler rejectionPolicy) {
/* 68 */     return new ThreadPoolConfig(allowCorePoolTimeout, corePoolSize, maxPoolSize, threadTimeout, queueFactory, rejectionPolicy);
/*    */   }
/*    */   
/*    */   public Option<Tuple6<Object, Object, Object, Duration, Function0<BlockingQueue<Runnable>>, RejectedExecutionHandler>> unapply(ThreadPoolConfig x$0) {
/* 68 */     return (x$0 == null) ? (Option<Tuple6<Object, Object, Object, Duration, Function0<BlockingQueue<Runnable>>, RejectedExecutionHandler>>)scala.None$.MODULE$ : (Option<Tuple6<Object, Object, Object, Duration, Function0<BlockingQueue<Runnable>>, RejectedExecutionHandler>>)new Some(new Tuple6(BoxesRunTime.boxToBoolean(x$0.allowCorePoolTimeout()), BoxesRunTime.boxToInteger(x$0.corePoolSize()), BoxesRunTime.boxToInteger(x$0.maxPoolSize()), x$0.threadTimeout(), x$0.queueFactory(), x$0.rejectionPolicy()));
/*    */   }
/*    */   
/*    */   public boolean apply$default$1() {
/* 68 */     return defaultAllowCoreThreadTimeout();
/*    */   }
/*    */   
/*    */   public boolean $lessinit$greater$default$1() {
/* 68 */     return defaultAllowCoreThreadTimeout();
/*    */   }
/*    */   
/*    */   public int apply$default$2() {
/* 69 */     return defaultCorePoolSize();
/*    */   }
/*    */   
/*    */   public int $lessinit$greater$default$2() {
/* 69 */     return defaultCorePoolSize();
/*    */   }
/*    */   
/*    */   public int apply$default$3() {
/* 70 */     return defaultMaxPoolSize();
/*    */   }
/*    */   
/*    */   public int $lessinit$greater$default$3() {
/* 70 */     return defaultMaxPoolSize();
/*    */   }
/*    */   
/*    */   public Duration apply$default$4() {
/* 71 */     return defaultTimeout();
/*    */   }
/*    */   
/*    */   public Duration $lessinit$greater$default$4() {
/* 71 */     return defaultTimeout();
/*    */   }
/*    */   
/*    */   public Function0<BlockingQueue<Runnable>> apply$default$5() {
/* 72 */     return linkedBlockingQueue();
/*    */   }
/*    */   
/*    */   public Function0<BlockingQueue<Runnable>> $lessinit$greater$default$5() {
/* 72 */     return linkedBlockingQueue();
/*    */   }
/*    */   
/*    */   public RejectedExecutionHandler apply$default$6() {
/* 73 */     return defaultRejectionPolicy();
/*    */   }
/*    */   
/*    */   public RejectedExecutionHandler $lessinit$greater$default$6() {
/* 73 */     return defaultRejectionPolicy();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\ThreadPoolConfig$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
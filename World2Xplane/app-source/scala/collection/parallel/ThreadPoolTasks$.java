/*     */ package scala.collection.parallel;
/*     */ 
/*     */ import java.util.concurrent.LinkedBlockingQueue;
/*     */ import java.util.concurrent.ThreadFactory;
/*     */ import java.util.concurrent.ThreadPoolExecutor;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import java.util.concurrent.atomic.AtomicLong;
/*     */ import scala.Serializable;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class ThreadPoolTasks$ {
/*     */   public static final ThreadPoolTasks$ MODULE$;
/*     */   
/*     */   private final int numCores;
/*     */   
/*     */   private final AtomicLong tcount;
/*     */   
/*     */   private final ThreadPoolExecutor defaultThreadPool;
/*     */   
/*     */   public class ThreadPoolTasks$$anonfun$execute$1 extends AbstractFunction0<R> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ThreadPoolTasks.WrappedTask t$1;
/*     */     
/*     */     public ThreadPoolTasks$$anonfun$execute$1(ThreadPoolTasks $outer, ThreadPoolTasks.WrappedTask t$1) {}
/*     */     
/*     */     public final R apply() {
/* 315 */       this.t$1.sync();
/* 316 */       this.t$1.body().forwardThrowable();
/* 317 */       return (R)this.t$1.body().result();
/*     */     }
/*     */   }
/*     */   
/*     */   private ThreadPoolTasks$() {
/* 336 */     MODULE$ = this;
/* 339 */     this.numCores = Runtime.getRuntime().availableProcessors();
/* 341 */     this.tcount = new AtomicLong(0L);
/* 343 */     this.defaultThreadPool = new ThreadPoolExecutor(
/* 344 */         numCores(), 
/* 345 */         2147483647, 
/* 346 */         60L, TimeUnit.MILLISECONDS, 
/* 347 */         new LinkedBlockingQueue<Runnable>(), 
/* 348 */         new ThreadPoolTasks.$anon$1(), 
/*     */         
/* 356 */         new ThreadPoolExecutor.CallerRunsPolicy());
/*     */   }
/*     */   
/*     */   public int numCores() {
/*     */     return this.numCores;
/*     */   }
/*     */   
/*     */   public AtomicLong tcount() {
/*     */     return this.tcount;
/*     */   }
/*     */   
/*     */   public ThreadPoolExecutor defaultThreadPool() {
/*     */     return this.defaultThreadPool;
/*     */   }
/*     */   
/*     */   public static class $anon$1 implements ThreadFactory {
/*     */     public Thread newThread(Runnable r) {
/*     */       Thread t = new Thread(r);
/*     */       t.setName((new StringBuilder()).append("pc-thread-").append(BoxesRunTime.boxToLong(ThreadPoolTasks$.MODULE$.tcount().incrementAndGet())).toString());
/*     */       t.setDaemon(true);
/*     */       return t;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\ThreadPoolTasks$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
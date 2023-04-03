/*     */ package scala.collection.parallel;
/*     */ 
/*     */ import java.util.concurrent.ExecutorService;
/*     */ import java.util.concurrent.Executors;
/*     */ import java.util.concurrent.atomic.AtomicLong;
/*     */ import scala.Serializable;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ 
/*     */ public final class FutureThreadPoolTasks$ {
/*     */   public static final FutureThreadPoolTasks$ MODULE$;
/*     */   
/*     */   private final int numCores;
/*     */   
/*     */   private final AtomicLong tcount;
/*     */   
/*     */   private final ExecutorService defaultThreadPool;
/*     */   
/*     */   public class FutureThreadPoolTasks$$anonfun$execute$2 extends AbstractFunction0<R> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final FutureThreadPoolTasks.WrappedTask t$2;
/*     */     
/*     */     public FutureThreadPoolTasks$$anonfun$execute$2(FutureThreadPoolTasks $outer, FutureThreadPoolTasks.WrappedTask t$2) {}
/*     */     
/*     */     public final R apply() {
/* 393 */       this.t$2.sync();
/* 394 */       this.t$2.body().forwardThrowable();
/* 395 */       return (R)this.t$2.body().result();
/*     */     }
/*     */   }
/*     */   
/*     */   private FutureThreadPoolTasks$() {
/* 414 */     MODULE$ = this;
/* 417 */     this.numCores = Runtime.getRuntime().availableProcessors();
/* 419 */     this.tcount = new AtomicLong(0L);
/* 421 */     this.defaultThreadPool = Executors.newCachedThreadPool();
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
/*     */   public ExecutorService defaultThreadPool() {
/* 421 */     return this.defaultThreadPool;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\FutureThreadPoolTasks$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
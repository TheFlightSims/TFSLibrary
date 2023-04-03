/*     */ package scala.collection.parallel;
/*     */ 
/*     */ import scala.Serializable;
/*     */ import scala.concurrent.forkjoin.ForkJoinPool;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ 
/*     */ public final class ForkJoinTasks$ {
/*     */   public static final ForkJoinTasks$ MODULE$;
/*     */   
/*     */   private final ForkJoinPool defaultForkJoinPool;
/*     */   
/*     */   public class ForkJoinTasks$$anonfun$execute$3 extends AbstractFunction0<R> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ForkJoinTasks.WrappedTask fjtask$1;
/*     */     
/*     */     public ForkJoinTasks$$anonfun$execute$3(ForkJoinTasks $outer, ForkJoinTasks.WrappedTask fjtask$1) {}
/*     */     
/*     */     public final R apply() {
/* 470 */       this.fjtask$1.sync();
/* 471 */       this.fjtask$1.body().forwardThrowable();
/* 472 */       return (R)this.fjtask$1.body().result();
/*     */     }
/*     */   }
/*     */   
/*     */   private ForkJoinTasks$() {
/* 503 */     MODULE$ = this;
/* 504 */     this.defaultForkJoinPool = new ForkJoinPool();
/*     */   }
/*     */   
/*     */   public ForkJoinPool defaultForkJoinPool() {
/* 504 */     return this.defaultForkJoinPool;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\ForkJoinTasks$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
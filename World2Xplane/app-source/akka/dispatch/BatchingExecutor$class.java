/*     */ package akka.dispatch;
/*     */ 
/*     */ import scala.Predef$;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.List$;
/*     */ import scala.runtime.BoxedUnit;
/*     */ 
/*     */ public abstract class BatchingExecutor$class {
/*     */   public static void $init$(BatchingExecutor $this) {
/*  49 */     $this.akka$dispatch$BatchingExecutor$_setter_$akka$dispatch$BatchingExecutor$$_tasksLocal_$eq(new ThreadLocal());
/*     */   }
/*     */   
/*     */   public static void execute(BatchingExecutor $this, Runnable runnable) {
/* 108 */     if ($this.batchable(runnable)) {
/* 109 */       List list = $this.akka$dispatch$BatchingExecutor$$_tasksLocal().get();
/* 110 */       if (list == null) {
/* 110 */         (new Runnable[1])[0] = runnable;
/* 110 */         $this.unbatchedExecute(new BatchingExecutor.Batch($this, List$.MODULE$.apply((Seq)Predef$.MODULE$.wrapRefArray((Object[])new Runnable[1]))));
/* 110 */         BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */       } else {
/* 111 */         Runnable runnable1 = runnable;
/* 111 */         $this.akka$dispatch$BatchingExecutor$$_tasksLocal().set(list.$colon$colon(runnable1));
/* 111 */         BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */       } 
/*     */     } else {
/* 113 */       $this.unbatchedExecute(runnable);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean batchable(BatchingExecutor $this, Runnable runnable) {
/*     */     boolean bool;
/* 117 */     Runnable runnable1 = runnable;
/* 118 */     if (runnable1 instanceof Batchable) {
/* 118 */       Batchable batchable = (Batchable)runnable1;
/* 118 */       bool = batchable.isBatchable();
/* 119 */     } else if (runnable1 instanceof scala.concurrent.OnCompleteRunnable) {
/* 119 */       bool = true;
/*     */     } else {
/* 120 */       bool = false;
/*     */     } 
/*     */     return bool;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\BatchingExecutor$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
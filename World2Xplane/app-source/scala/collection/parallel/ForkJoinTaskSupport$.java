/*    */ package scala.collection.parallel;
/*    */ 
/*    */ import scala.concurrent.forkjoin.ForkJoinPool;
/*    */ 
/*    */ public final class ForkJoinTaskSupport$ {
/*    */   public static final ForkJoinTaskSupport$ MODULE$;
/*    */   
/*    */   public ForkJoinPool $lessinit$greater$default$1() {
/* 64 */     return ForkJoinTasks$.MODULE$.defaultForkJoinPool();
/*    */   }
/*    */   
/*    */   private ForkJoinTaskSupport$() {
/* 64 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\ForkJoinTaskSupport$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package scala.collection.parallel;
/*    */ 
/*    */ import java.util.concurrent.ThreadPoolExecutor;
/*    */ 
/*    */ public final class ThreadPoolTaskSupport$ {
/*    */   public static final ThreadPoolTaskSupport$ MODULE$;
/*    */   
/*    */   public ThreadPoolExecutor $lessinit$greater$default$1() {
/* 71 */     return ThreadPoolTasks$.MODULE$.defaultThreadPool();
/*    */   }
/*    */   
/*    */   private ThreadPoolTaskSupport$() {
/* 71 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\ThreadPoolTaskSupport$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
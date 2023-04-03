/*    */ package scala.concurrent;
/*    */ 
/*    */ import java.util.concurrent.Callable;
/*    */ import java.util.concurrent.Future;
/*    */ import scala.Function0;
/*    */ 
/*    */ public abstract class ThreadPoolRunner$class {
/*    */   public static void $init$(ThreadPoolRunner $this) {}
/*    */   
/*    */   public static Callable functionAsTask(ThreadPoolRunner $this, Function0<?> fun) {
/* 31 */     return new ThreadPoolRunner.RunCallable($this, fun);
/*    */   }
/*    */   
/*    */   public static Function0 futureAsFunction(ThreadPoolRunner $this, Future x) {
/* 34 */     return (Function0)new ThreadPoolRunner$$anonfun$futureAsFunction$1($this, x);
/*    */   }
/*    */   
/*    */   public static Future submit(ThreadPoolRunner $this, Callable<?> task) {
/* 39 */     return $this.executor().submit(task);
/*    */   }
/*    */   
/*    */   public static void execute(ThreadPoolRunner $this, Callable task) {
/* 43 */     $this.executor().execute((Runnable)task);
/*    */   }
/*    */   
/*    */   public static void managedBlock(ThreadPoolRunner $this, ManagedBlocker blocker) {
/* 48 */     blocker.block();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\concurrent\ThreadPoolRunner$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
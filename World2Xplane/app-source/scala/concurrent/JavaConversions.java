/*    */ package scala.concurrent;
/*    */ 
/*    */ import java.util.concurrent.Callable;
/*    */ import java.util.concurrent.Executor;
/*    */ import java.util.concurrent.ExecutorService;
/*    */ import java.util.concurrent.Future;
/*    */ import scala.Function0;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\r;Q!\001\002\t\002\035\tqBS1wC\016{gN^3sg&|gn\035\006\003\007\021\t!bY8oGV\024(/\0328u\025\005)\021!B:dC2\f7\001\001\t\003\021%i\021A\001\004\006\025\tA\ta\003\002\020\025\0064\030mQ8om\026\0248/[8ogN\021\021\002\004\t\003\0339i\021\001B\005\003\037\021\021a!\0218z%\0264\007\"B\t\n\t\003\021\022A\002\037j]&$h\bF\001\b\021\025!\022\002b\001\026\0031\t7\017V1tWJ+hN\\3s)\t1\022\004\005\002\t/%\021\001D\001\002\021\rV$XO]3UCN\\'+\0368oKJDQAG\nA\002m\tA!\032=fGB\021ADI\007\002;)\0211A\b\006\003?\001\nA!\036;jY*\t\021%\001\003kCZ\f\027BA\022\036\005=)\0050Z2vi>\0248+\032:wS\016,\007\006B\n&Q)\002\"!\004\024\n\005\035\"!A\0033faJ,7-\031;fI\006\n\021&A\021Vg\026\004\003-Y:Fq\026\034W\017^5p]\016{g\016^3yi\002\004\023N\\:uK\006$g&I\001,\003\031\021d&\r\031/a!)A#\003C\002[Q\021a&\r\t\003\021=J!\001\r\002\003\025Q\0137o\033*v]:,'\017C\003\033Y\001\007!\007\005\002\035g%\021A'\b\002\t\013b,7-\036;pe\"\"A&\n\025+\021\0259\024\002b\0019\003I\t7/\022=fGV$\030n\0348D_:$X\r\037;\025\005eb\004C\001\005;\023\tY$AA\020Fq\026\034W\017^5p]\016{g\016^3yi\026CXmY;u_J\034VM\035<jG\026DQA\007\034A\002mAQaN\005\005\004y\"\"a\020\"\021\005!\001\025BA!\003\005a)\0050Z2vi&|gnQ8oi\026DH/\022=fGV$xN\035\005\0065u\002\rA\r")
/*    */ public final class JavaConversions {
/*    */   public static ExecutionContextExecutor asExecutionContext(Executor paramExecutor) {
/*    */     return JavaConversions$.MODULE$.asExecutionContext(paramExecutor);
/*    */   }
/*    */   
/*    */   public static ExecutionContextExecutorService asExecutionContext(ExecutorService paramExecutorService) {
/*    */     return JavaConversions$.MODULE$.asExecutionContext(paramExecutorService);
/*    */   }
/*    */   
/*    */   public static TaskRunner asTaskRunner(Executor paramExecutor) {
/*    */     return JavaConversions$.MODULE$.asTaskRunner(paramExecutor);
/*    */   }
/*    */   
/*    */   public static FutureTaskRunner asTaskRunner(ExecutorService paramExecutorService) {
/*    */     return JavaConversions$.MODULE$.asTaskRunner(paramExecutorService);
/*    */   }
/*    */   
/*    */   public static class JavaConversions$$anon$1 implements ThreadPoolRunner {
/*    */     private final ExecutorService exec$1;
/*    */     
/*    */     public <S> Callable<S> functionAsTask(Function0 fun) {
/* 23 */       return ThreadPoolRunner$class.functionAsTask(this, fun);
/*    */     }
/*    */     
/*    */     public <S> Function0<S> futureAsFunction(Future x) {
/* 23 */       return ThreadPoolRunner$class.futureAsFunction(this, x);
/*    */     }
/*    */     
/*    */     public <S> Future<S> submit(Callable task) {
/* 23 */       return ThreadPoolRunner$class.submit(this, task);
/*    */     }
/*    */     
/*    */     public <S> void execute(Callable task) {
/* 23 */       ThreadPoolRunner$class.execute(this, task);
/*    */     }
/*    */     
/*    */     public void managedBlock(ManagedBlocker blocker) {
/* 23 */       ThreadPoolRunner$class.managedBlock(this, blocker);
/*    */     }
/*    */     
/*    */     public JavaConversions$$anon$1(ExecutorService exec$1) {
/* 23 */       ThreadPoolRunner$class.$init$(this);
/*    */     }
/*    */     
/*    */     public ExecutorService executor() {
/* 25 */       return this.exec$1;
/*    */     }
/*    */     
/*    */     public void shutdown() {
/* 28 */       this.exec$1.shutdown();
/*    */     }
/*    */   }
/*    */   
/*    */   public static class JavaConversions$$anon$2 implements TaskRunner {
/*    */     private final Executor exec$2;
/*    */     
/*    */     public JavaConversions$$anon$2(Executor exec$2) {}
/*    */     
/*    */     public <T> Runnable functionAsTask(Function0 fun) {
/* 36 */       return new JavaConversions$$anon$2$$anon$3(this, fun);
/*    */     }
/*    */     
/*    */     public class JavaConversions$$anon$2$$anon$3 implements Runnable {
/*    */       private final Function0 fun$1;
/*    */       
/*    */       public JavaConversions$$anon$2$$anon$3(JavaConversions$$anon$2 $outer, Function0 fun$1) {}
/*    */       
/*    */       public void run() {
/* 37 */         this.fun$1.apply();
/*    */       }
/*    */     }
/*    */     
/*    */     public <S> void execute(Runnable task) {
/* 41 */       this.exec$2.execute(task);
/*    */     }
/*    */     
/*    */     private void managedBlock(ManagedBlocker blocker) {
/* 45 */       blocker.block();
/*    */     }
/*    */     
/*    */     public void shutdown() {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\concurrent\JavaConversions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
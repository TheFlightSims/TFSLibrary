/*    */ package scala.collection.parallel;
/*    */ 
/*    */ import scala.Function0;
/*    */ import scala.collection.mutable.ArrayBuffer;
/*    */ import scala.concurrent.forkjoin.ForkJoinPool;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001m2A!\001\002\001\023\t\031bi\034:l\025>Lg\016V1tWN+\b\017]8si*\0211\001B\001\ta\006\024\030\r\0347fY*\021QAB\001\013G>dG.Z2uS>t'\"A\004\002\013M\034\027\r\\1\004\001M!\001A\003\b\023!\tYA\"D\001\007\023\tiaA\001\004B]f\024VM\032\t\003\037Ai\021AA\005\003#\t\0211\002V1tWN+\b\017]8siB\021qbE\005\003)\t\021\021%\0213baRLg/Z,pe.\034F/Z1mS:<gi\034:l\025>Lg\016V1tWND\001B\006\001\003\006\004%\taF\001\fK:4\030N]8o[\026tG/F\001\031!\tIb$D\001\033\025\tYB$\001\005g_J\\'n\\5o\025\tib!\001\006d_:\034WO\035:f]RL!a\b\016\003\031\031{'o\033&pS:\004vn\0347\t\021\005\002!\021!Q\001\na\tA\"\0328wSJ|g.\\3oi\002BQa\t\001\005\002\021\na\001P5oSRtDCA\023'!\ty\001\001C\004\027EA\005\t\031\001\r\b\017!\022\021\021!E\001S\005\031bi\034:l\025>Lg\016V1tWN+\b\017]8siB\021qB\013\004\b\003\t\t\t\021#\001,'\tQ#\002C\003$U\021\005Q\006F\001*\021\035y#&%A\005\002A\n1\004\n7fgNLg.\033;%OJ,\027\r^3sI\021,g-Y;mi\022\nT#A\031+\005a\0214&A\032\021\005QJT\"A\033\013\005Y:\024!C;oG\",7m[3e\025\tAd!\001\006b]:|G/\031;j_:L!AO\033\003#Ut7\r[3dW\026$g+\031:jC:\034W\r")
/*    */ public class ForkJoinTaskSupport implements TaskSupport, AdaptiveWorkStealingForkJoinTasks {
/*    */   private final ForkJoinPool environment;
/*    */   
/*    */   private final ArrayBuffer<String> debugMessages;
/*    */   
/*    */   public static ForkJoinPool $lessinit$greater$default$1() {
/*    */     return ForkJoinTaskSupport$.MODULE$.$lessinit$greater$default$1();
/*    */   }
/*    */   
/*    */   public <R, Tp> AdaptiveWorkStealingForkJoinTasks.WrappedTask<R, Tp> newWrappedTask(Task b) {
/* 64 */     return AdaptiveWorkStealingForkJoinTasks$class.newWrappedTask(this, b);
/*    */   }
/*    */   
/*    */   public ForkJoinPool forkJoinPool() {
/* 64 */     return ForkJoinTasks$class.forkJoinPool(this);
/*    */   }
/*    */   
/*    */   public <R, Tp> Function0<R> execute(Task task) {
/* 64 */     return ForkJoinTasks$class.execute(this, task);
/*    */   }
/*    */   
/*    */   public <R, Tp> R executeAndWaitResult(Task task) {
/* 64 */     return (R)ForkJoinTasks$class.executeAndWaitResult(this, task);
/*    */   }
/*    */   
/*    */   public int parallelismLevel() {
/* 64 */     return ForkJoinTasks$class.parallelismLevel(this);
/*    */   }
/*    */   
/*    */   public ArrayBuffer<String> debugMessages() {
/* 64 */     return this.debugMessages;
/*    */   }
/*    */   
/*    */   public void scala$collection$parallel$Tasks$_setter_$debugMessages_$eq(ArrayBuffer<String> x$1) {
/* 64 */     this.debugMessages = x$1;
/*    */   }
/*    */   
/*    */   public ArrayBuffer<String> debuglog(String s) {
/* 64 */     return Tasks$class.debuglog(this, s);
/*    */   }
/*    */   
/*    */   public ForkJoinPool environment() {
/* 64 */     return this.environment;
/*    */   }
/*    */   
/*    */   public ForkJoinTaskSupport(ForkJoinPool environment) {
/* 64 */     Tasks$class.$init$(this);
/* 64 */     ForkJoinTasks$class.$init$(this);
/* 64 */     AdaptiveWorkStealingTasks$class.$init$(this);
/* 64 */     AdaptiveWorkStealingForkJoinTasks$class.$init$(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\ForkJoinTaskSupport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package scala.collection.parallel;
/*    */ 
/*    */ import java.util.concurrent.LinkedBlockingQueue;
/*    */ import java.util.concurrent.ThreadPoolExecutor;
/*    */ import scala.Function0;
/*    */ import scala.collection.mutable.ArrayBuffer;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001u2A!\001\002\001\023\t)B\013\033:fC\022\004vn\0347UCN\\7+\0369q_J$(BA\002\005\003!\001\030M]1mY\026d'BA\003\007\003)\031w\016\0347fGRLwN\034\006\002\017\005)1oY1mC\016\0011\003\002\001\013\035I\001\"a\003\007\016\003\031I!!\004\004\003\r\005s\027PU3g!\ty\001#D\001\003\023\t\t\"AA\006UCN\\7+\0369q_J$\bCA\b\024\023\t!\"AA\022BI\006\004H/\033<f/>\0248n\025;fC2Lgn\032+ie\026\fG\rU8pYR\0137o[:\t\021Y\001!Q1A\005\002]\t1\"\0328wSJ|g.\\3oiV\t\001\004\005\002\032A5\t!D\003\002\0349\005Q1m\0348dkJ\024XM\034;\013\005uq\022\001B;uS2T\021aH\001\005U\0064\030-\003\002\"5\t\021B\013\033:fC\022\004vn\0347Fq\026\034W\017^8s\021!\031\003A!A!\002\023A\022\001D3om&\024xN\\7f]R\004\003\"B\023\001\t\0031\023A\002\037j]&$h\b\006\002(QA\021q\002\001\005\b-\021\002\n\0211\001\031\017\035Q#!!A\t\002-\nQ\003\0265sK\006$\007k\\8m)\006\0348nU;qa>\024H\017\005\002\020Y\0319\021AAA\001\022\003i3C\001\027\013\021\025)C\006\"\0010)\005Y\003bB\031-#\003%\tAM\001\034I1,7o]5oSR$sM]3bi\026\024H\005Z3gCVdG\017J\031\026\003MR#\001\007\033,\003U\002\"AN\036\016\003]R!\001O\035\002\023Ut7\r[3dW\026$'B\001\036\007\003)\tgN\\8uCRLwN\\\005\003y]\022\021#\0368dQ\026\0347.\0323WCJL\027M\\2f\001")
/*    */ public class ThreadPoolTaskSupport implements TaskSupport, AdaptiveWorkStealingThreadPoolTasks {
/*    */   private final ThreadPoolExecutor environment;
/*    */   
/*    */   private volatile int totaltasks;
/*    */   
/*    */   private final ArrayBuffer<String> debugMessages;
/*    */   
/*    */   public static ThreadPoolExecutor $lessinit$greater$default$1() {
/*    */     return ThreadPoolTaskSupport$.MODULE$.$lessinit$greater$default$1();
/*    */   }
/*    */   
/*    */   public <R, Tp> AdaptiveWorkStealingThreadPoolTasks.WrappedTask<R, Tp> newWrappedTask(Task b) {
/* 71 */     return AdaptiveWorkStealingThreadPoolTasks$class.newWrappedTask(this, b);
/*    */   }
/*    */   
/*    */   public int totaltasks() {
/* 71 */     return this.totaltasks;
/*    */   }
/*    */   
/*    */   public void totaltasks_$eq(int x$1) {
/* 71 */     this.totaltasks = x$1;
/*    */   }
/*    */   
/*    */   public ThreadPoolExecutor executor() {
/* 71 */     return ThreadPoolTasks$class.executor(this);
/*    */   }
/*    */   
/*    */   public LinkedBlockingQueue<Runnable> queue() {
/* 71 */     return ThreadPoolTasks$class.queue(this);
/*    */   }
/*    */   
/*    */   public <R, Tp> Function0<R> execute(Task task) {
/* 71 */     return ThreadPoolTasks$class.execute(this, task);
/*    */   }
/*    */   
/*    */   public <R, Tp> R executeAndWaitResult(Task task) {
/* 71 */     return (R)ThreadPoolTasks$class.executeAndWaitResult(this, task);
/*    */   }
/*    */   
/*    */   public int parallelismLevel() {
/* 71 */     return ThreadPoolTasks$class.parallelismLevel(this);
/*    */   }
/*    */   
/*    */   public ArrayBuffer<String> debugMessages() {
/* 71 */     return this.debugMessages;
/*    */   }
/*    */   
/*    */   public void scala$collection$parallel$Tasks$_setter_$debugMessages_$eq(ArrayBuffer<String> x$1) {
/* 71 */     this.debugMessages = x$1;
/*    */   }
/*    */   
/*    */   public ArrayBuffer<String> debuglog(String s) {
/* 71 */     return Tasks$class.debuglog(this, s);
/*    */   }
/*    */   
/*    */   public ThreadPoolExecutor environment() {
/* 71 */     return this.environment;
/*    */   }
/*    */   
/*    */   public ThreadPoolTaskSupport(ThreadPoolExecutor environment) {
/* 71 */     Tasks$class.$init$(this);
/* 71 */     ThreadPoolTasks$class.$init$(this);
/* 71 */     AdaptiveWorkStealingTasks$class.$init$(this);
/* 71 */     AdaptiveWorkStealingThreadPoolTasks$class.$init$(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\ThreadPoolTaskSupport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package akka.pattern;
/*    */ 
/*    */ import akka.actor.Scheduler;
/*    */ import scala.Function0;
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.concurrent.ExecutionContext;
/*    */ import scala.concurrent.Future;
/*    */ import scala.concurrent.Promise;
/*    */ import scala.concurrent.duration.FiniteDuration;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction0;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.util.control.NonFatal$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\0213q!\001\002\021\002\007\005qA\001\013GkR,(/\032+j[\026|W\017^*vaB|'\017\036\006\003\007\021\tq\001]1ui\026\024hNC\001\006\003\021\t7n[1\004\001M\021\001\001\003\t\003\0231i\021A\003\006\002\027\005)1oY1mC&\021QB\003\002\007\003:L(+\0324\t\013=\001A\021\001\t\002\r\021Jg.\033;%)\005\t\002CA\005\023\023\t\031\"B\001\003V]&$\b\"B\013\001\t\0031\022!B1gi\026\024XCA\f#)\rAR\007\020\013\0033A\"\"AG\026\021\007mq\002%D\001\035\025\ti\"\"\001\006d_:\034WO\035:f]RL!a\b\017\003\r\031+H/\036:f!\t\t#\005\004\001\005\013\r\"\"\031\001\023\003\003Q\013\"!\n\025\021\005%1\023BA\024\013\005\035qu\016\0365j]\036\004\"!C\025\n\005)R!aA!os\")A\006\006a\002[\005\021Qm\031\t\00379J!a\f\017\003!\025CXmY;uS>t7i\0348uKb$\bBB\031\025\t\003\007!'A\003wC2,X\rE\002\ngiI!\001\016\006\003\021q\022\027P\\1nKzBQA\016\013A\002]\n\001\002Z;sCRLwN\034\t\003qij\021!\017\006\003mqI!aO\035\003\035\031Kg.\033;f\tV\024\030\r^5p]\")Q\b\006a\001}\005)Qo]5oOB\021qHQ\007\002\001*\021\021\tB\001\006C\016$xN]\005\003\007\002\023\021bU2iK\022,H.\032:")
/*    */ public interface FutureTimeoutSupport {
/*    */   <T> Future<T> after(FiniteDuration paramFiniteDuration, Scheduler paramScheduler, Function0<Future<T>> paramFunction0, ExecutionContext paramExecutionContext);
/*    */   
/*    */   public class FutureTimeoutSupport$$anonfun$after$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final Promise p$1;
/*    */     
/*    */     private final Function0 value$1;
/*    */     
/*    */     public final void apply() {
/* 23 */       apply$mcV$sp();
/*    */     }
/*    */     
/*    */     public void apply$mcV$sp() {
/* 23 */       this.p$1.completeWith(liftedTree1$1());
/*    */     }
/*    */     
/*    */     private final Future liftedTree1$1() {
/*    */       try {
/*    */       
/*    */       } finally {
/*    */         Future future;
/* 23 */         Exception exception1 = null, exception2 = exception1;
/* 23 */         Option option = NonFatal$.MODULE$.unapply(exception2);
/* 23 */         if (option.isEmpty())
/* 23 */           throw exception1; 
/* 23 */         Throwable t = (Throwable)option.get();
/*    */       } 
/* 23 */       return future;
/*    */     }
/*    */     
/*    */     public FutureTimeoutSupport$$anonfun$after$1(FutureTimeoutSupport $outer, Promise p$1, Function0 value$1) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\pattern\FutureTimeoutSupport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
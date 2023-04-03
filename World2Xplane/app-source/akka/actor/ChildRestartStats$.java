/*    */ package akka.actor;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.Tuple3;
/*    */ import scala.runtime.AbstractFunction3;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ public final class ChildRestartStats$ extends AbstractFunction3<ActorRef, Object, Object, ChildRestartStats> implements Serializable {
/*    */   public static final ChildRestartStats$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 32 */     return "ChildRestartStats";
/*    */   }
/*    */   
/*    */   public ChildRestartStats apply(ActorRef child, int maxNrOfRetriesCount, long restartTimeWindowStartNanos) {
/* 32 */     return new ChildRestartStats(child, maxNrOfRetriesCount, restartTimeWindowStartNanos);
/*    */   }
/*    */   
/*    */   public Option<Tuple3<ActorRef, Object, Object>> unapply(ChildRestartStats x$0) {
/* 32 */     return (x$0 == null) ? (Option<Tuple3<ActorRef, Object, Object>>)scala.None$.MODULE$ : (Option<Tuple3<ActorRef, Object, Object>>)new Some(new Tuple3(x$0.child(), BoxesRunTime.boxToInteger(x$0.maxNrOfRetriesCount()), BoxesRunTime.boxToLong(x$0.restartTimeWindowStartNanos())));
/*    */   }
/*    */   
/*    */   public int $lessinit$greater$default$2() {
/* 32 */     return 0;
/*    */   }
/*    */   
/*    */   public long $lessinit$greater$default$3() {
/* 32 */     return 0L;
/*    */   }
/*    */   
/*    */   public int apply$default$2() {
/* 32 */     return 0;
/*    */   }
/*    */   
/*    */   public long apply$default$3() {
/* 32 */     return 0L;
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 32 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private ChildRestartStats$() {
/* 32 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\ChildRestartStats$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
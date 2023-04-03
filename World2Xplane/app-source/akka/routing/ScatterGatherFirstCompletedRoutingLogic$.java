/*    */ package akka.routing;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.concurrent.duration.FiniteDuration;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ 
/*    */ public final class ScatterGatherFirstCompletedRoutingLogic$ extends AbstractFunction1<FiniteDuration, ScatterGatherFirstCompletedRoutingLogic> implements Serializable {
/*    */   public static final ScatterGatherFirstCompletedRoutingLogic$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 32 */     return "ScatterGatherFirstCompletedRoutingLogic";
/*    */   }
/*    */   
/*    */   public ScatterGatherFirstCompletedRoutingLogic apply(FiniteDuration within) {
/* 32 */     return new ScatterGatherFirstCompletedRoutingLogic(within);
/*    */   }
/*    */   
/*    */   public Option<FiniteDuration> unapply(ScatterGatherFirstCompletedRoutingLogic x$0) {
/* 32 */     return (x$0 == null) ? (Option<FiniteDuration>)scala.None$.MODULE$ : (Option<FiniteDuration>)new Some(x$0.within());
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 32 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private ScatterGatherFirstCompletedRoutingLogic$() {
/* 32 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\ScatterGatherFirstCompletedRoutingLogic$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
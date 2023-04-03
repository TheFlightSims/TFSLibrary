/*    */ package akka.actor;
/*    */ 
/*    */ import scala.PartialFunction;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.Iterable;
/*    */ 
/*    */ public abstract class SupervisorStrategyLowPriorityImplicits$class {
/*    */   public static void $init$(SupervisorStrategy$ $this) {}
/*    */   
/*    */   public static PartialFunction seqCauseDirective2Decider(SupervisorStrategy$ $this, Iterable<Tuple2<Class<? extends Throwable>, SupervisorStrategy.Directive>> trapExit) {
/* 93 */     return $this.makeDecider(trapExit);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\SupervisorStrategyLowPriorityImplicits$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
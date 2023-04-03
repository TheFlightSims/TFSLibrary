/*    */ package ch.qos.logback.classic.joran.action;
/*    */ 
/*    */ import ch.qos.logback.classic.boolex.JaninoEventEvaluator;
/*    */ import ch.qos.logback.core.joran.action.AbstractEventEvaluatorAction;
/*    */ 
/*    */ public class EvaluatorAction extends AbstractEventEvaluatorAction {
/*    */   protected String defaultClassName() {
/* 21 */     return JaninoEventEvaluator.class.getName();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classic\joran\action\EvaluatorAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
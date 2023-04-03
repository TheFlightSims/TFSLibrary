/*    */ package ch.qos.logback.core.joran.action;
/*    */ 
/*    */ import ch.qos.logback.core.joran.spi.ElementSelector;
/*    */ import ch.qos.logback.core.joran.spi.InterpretationContext;
/*    */ import ch.qos.logback.core.util.OptionHelper;
/*    */ import org.xml.sax.Attributes;
/*    */ 
/*    */ public class NewRuleAction extends Action {
/*    */   boolean inError = false;
/*    */   
/*    */   public void begin(InterpretationContext ec, String localName, Attributes attributes) {
/* 31 */     this.inError = false;
/* 33 */     String pattern = attributes.getValue("pattern");
/* 34 */     String actionClass = attributes.getValue("actionClass");
/* 36 */     if (OptionHelper.isEmpty(pattern)) {
/* 37 */       this.inError = true;
/* 38 */       String errorMsg = "No 'pattern' attribute in <newRule>";
/* 39 */       addError(errorMsg);
/*    */       return;
/*    */     } 
/* 43 */     if (OptionHelper.isEmpty(actionClass)) {
/* 44 */       this.inError = true;
/* 45 */       String errorMsg = "No 'actionClass' attribute in <newRule>";
/* 46 */       addError(errorMsg);
/*    */       return;
/*    */     } 
/*    */     try {
/* 51 */       addInfo("About to add new Joran parsing rule [" + pattern + "," + actionClass + "].");
/* 53 */       ec.getJoranInterpreter().getRuleStore().addRule(new ElementSelector(pattern), actionClass);
/* 55 */     } catch (Exception oops) {
/* 56 */       this.inError = true;
/* 57 */       String errorMsg = "Could not add new Joran parsing rule [" + pattern + "," + actionClass + "]";
/* 59 */       addError(errorMsg);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void end(InterpretationContext ec, String n) {}
/*    */   
/*    */   public void finish(InterpretationContext ec) {}
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\joran\action\NewRuleAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
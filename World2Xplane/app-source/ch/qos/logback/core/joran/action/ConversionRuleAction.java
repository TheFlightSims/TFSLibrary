/*    */ package ch.qos.logback.core.joran.action;
/*    */ 
/*    */ import ch.qos.logback.core.joran.spi.InterpretationContext;
/*    */ import ch.qos.logback.core.util.OptionHelper;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.xml.sax.Attributes;
/*    */ 
/*    */ public class ConversionRuleAction extends Action {
/*    */   boolean inError = false;
/*    */   
/*    */   public void begin(InterpretationContext ec, String localName, Attributes attributes) {
/* 38 */     this.inError = false;
/* 41 */     String conversionWord = attributes.getValue("conversionWord");
/* 43 */     String converterClass = attributes.getValue("converterClass");
/* 46 */     if (OptionHelper.isEmpty(conversionWord)) {
/* 47 */       this.inError = true;
/* 48 */       String errorMsg = "No 'conversionWord' attribute in <conversionRule>";
/* 49 */       addError(errorMsg);
/*    */       return;
/*    */     } 
/* 54 */     if (OptionHelper.isEmpty(converterClass)) {
/* 55 */       this.inError = true;
/* 56 */       String errorMsg = "No 'converterClass' attribute in <conversionRule>";
/* 57 */       ec.addError(errorMsg);
/*    */       return;
/*    */     } 
/*    */     try {
/* 63 */       Map<String, String> ruleRegistry = (Map<String, String>)this.context.getObject("PATTERN_RULE_REGISTRY");
/* 64 */       if (ruleRegistry == null) {
/* 65 */         ruleRegistry = new HashMap<String, String>();
/* 66 */         this.context.putObject("PATTERN_RULE_REGISTRY", ruleRegistry);
/*    */       } 
/* 69 */       addInfo("registering conversion word " + conversionWord + " with class [" + converterClass + "]");
/* 70 */       ruleRegistry.put(conversionWord, converterClass);
/* 71 */     } catch (Exception oops) {
/* 72 */       this.inError = true;
/* 73 */       String errorMsg = "Could not add conversion rule to PatternLayout.";
/* 74 */       addError(errorMsg);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void end(InterpretationContext ec, String n) {}
/*    */   
/*    */   public void finish(InterpretationContext ec) {}
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\joran\action\ConversionRuleAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
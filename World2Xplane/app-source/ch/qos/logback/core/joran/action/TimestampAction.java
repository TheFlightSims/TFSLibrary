/*    */ package ch.qos.logback.core.joran.action;
/*    */ 
/*    */ import ch.qos.logback.core.joran.spi.ActionException;
/*    */ import ch.qos.logback.core.joran.spi.InterpretationContext;
/*    */ import ch.qos.logback.core.util.CachingDateFormatter;
/*    */ import ch.qos.logback.core.util.OptionHelper;
/*    */ import org.xml.sax.Attributes;
/*    */ 
/*    */ public class TimestampAction extends Action {
/* 33 */   static String DATE_PATTERN_ATTRIBUTE = "datePattern";
/*    */   
/* 34 */   static String TIME_REFERENCE_ATTRIBUTE = "timeReference";
/*    */   
/* 35 */   static String CONTEXT_BIRTH = "contextBirth";
/*    */   
/*    */   boolean inError = false;
/*    */   
/*    */   public void begin(InterpretationContext ec, String name, Attributes attributes) throws ActionException {
/*    */     long timeReference;
/* 42 */     String keyStr = attributes.getValue("key");
/* 43 */     if (OptionHelper.isEmpty(keyStr)) {
/* 44 */       addError("Attribute named [key] cannot be empty");
/* 45 */       this.inError = true;
/*    */     } 
/* 47 */     String datePatternStr = attributes.getValue(DATE_PATTERN_ATTRIBUTE);
/* 48 */     if (OptionHelper.isEmpty(datePatternStr)) {
/* 49 */       addError("Attribute named [" + DATE_PATTERN_ATTRIBUTE + "] cannot be empty");
/* 51 */       this.inError = true;
/*    */     } 
/* 54 */     String timeReferenceStr = attributes.getValue(TIME_REFERENCE_ATTRIBUTE);
/* 56 */     if (CONTEXT_BIRTH.equalsIgnoreCase(timeReferenceStr)) {
/* 57 */       addInfo("Using context birth as time reference.");
/* 58 */       timeReference = this.context.getBirthTime();
/*    */     } else {
/* 60 */       timeReference = System.currentTimeMillis();
/* 61 */       addInfo("Using current interpretation time, i.e. now, as time reference.");
/*    */     } 
/* 65 */     if (this.inError)
/*    */       return; 
/* 68 */     String scopeStr = attributes.getValue("scope");
/* 69 */     ActionUtil.Scope scope = ActionUtil.stringToScope(scopeStr);
/* 71 */     CachingDateFormatter sdf = new CachingDateFormatter(datePatternStr);
/* 72 */     String val = sdf.format(timeReference);
/* 74 */     addInfo("Adding property to the context with key=\"" + keyStr + "\" and value=\"" + val + "\" to the " + scope + " scope");
/* 76 */     ActionUtil.setProperty(ec, keyStr, val, scope);
/*    */   }
/*    */   
/*    */   public void end(InterpretationContext ec, String name) throws ActionException {}
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\joran\action\TimestampAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
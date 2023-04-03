/*    */ package ch.qos.logback.core.joran.action;
/*    */ 
/*    */ import ch.qos.logback.core.joran.spi.ActionException;
/*    */ import ch.qos.logback.core.joran.spi.InterpretationContext;
/*    */ import org.xml.sax.Attributes;
/*    */ 
/*    */ public class ContextPropertyAction extends Action {
/*    */   public void begin(InterpretationContext ec, String name, Attributes attributes) throws ActionException {
/* 29 */     addError("The [contextProperty] element has been removed. Please use [substitutionProperty] element instead");
/*    */   }
/*    */   
/*    */   public void end(InterpretationContext ec, String name) throws ActionException {}
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\joran\action\ContextPropertyAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package ch.qos.logback.core.layout;
/*    */ 
/*    */ import ch.qos.logback.core.CoreConstants;
/*    */ import ch.qos.logback.core.LayoutBase;
/*    */ 
/*    */ public class EchoLayout<E> extends LayoutBase<E> {
/*    */   public String doLayout(E event) {
/* 27 */     return (new StringBuilder()).append(event).append(CoreConstants.LINE_SEPARATOR).toString();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\layout\EchoLayout.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
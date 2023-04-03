/*    */ package ch.qos.logback.core.pattern.color;
/*    */ 
/*    */ public class GrayCompositeConverter<E> extends ForegroundCompositeConverterBase<E> {
/*    */   protected String getForegroundColorCode(E event) {
/* 30 */     return "1;30";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\pattern\color\GrayCompositeConverter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
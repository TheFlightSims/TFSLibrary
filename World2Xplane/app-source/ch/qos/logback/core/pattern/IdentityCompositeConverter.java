/*    */ package ch.qos.logback.core.pattern;
/*    */ 
/*    */ public class IdentityCompositeConverter<E> extends CompositeConverter<E> {
/*    */   protected String transform(E event, String in) {
/* 20 */     return in;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\pattern\IdentityCompositeConverter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
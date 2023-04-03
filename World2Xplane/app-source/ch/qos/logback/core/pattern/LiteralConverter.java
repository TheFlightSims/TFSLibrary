/*    */ package ch.qos.logback.core.pattern;
/*    */ 
/*    */ public final class LiteralConverter<E> extends Converter<E> {
/*    */   String literal;
/*    */   
/*    */   public LiteralConverter(String literal) {
/* 21 */     this.literal = literal;
/*    */   }
/*    */   
/*    */   public String convert(E o) {
/* 25 */     return this.literal;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\pattern\LiteralConverter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
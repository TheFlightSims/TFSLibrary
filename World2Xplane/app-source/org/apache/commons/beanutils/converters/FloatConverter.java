/*    */ package org.apache.commons.beanutils.converters;
/*    */ 
/*    */ public final class FloatConverter extends NumberConverter {
/*    */   public FloatConverter() {
/* 42 */     super(true);
/*    */   }
/*    */   
/*    */   public FloatConverter(Object defaultValue) {
/* 54 */     super(true, defaultValue);
/*    */   }
/*    */   
/*    */   protected Class getDefaultType() {
/* 64 */     return Float.class;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\converters\FloatConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package org.apache.commons.beanutils.converters;
/*    */ 
/*    */ public final class IntegerConverter extends NumberConverter {
/*    */   public IntegerConverter() {
/* 42 */     super(false);
/*    */   }
/*    */   
/*    */   public IntegerConverter(Object defaultValue) {
/* 54 */     super(false, defaultValue);
/*    */   }
/*    */   
/*    */   protected Class getDefaultType() {
/* 64 */     return Integer.class;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\converters\IntegerConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
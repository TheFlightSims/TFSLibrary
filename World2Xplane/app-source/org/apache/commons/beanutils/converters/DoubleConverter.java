/*    */ package org.apache.commons.beanutils.converters;
/*    */ 
/*    */ public final class DoubleConverter extends NumberConverter {
/*    */   public DoubleConverter() {
/* 42 */     super(true);
/*    */   }
/*    */   
/*    */   public DoubleConverter(Object defaultValue) {
/* 54 */     super(true, defaultValue);
/*    */   }
/*    */   
/*    */   protected Class getDefaultType() {
/* 64 */     return Double.class;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\converters\DoubleConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
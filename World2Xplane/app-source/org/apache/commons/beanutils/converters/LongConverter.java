/*    */ package org.apache.commons.beanutils.converters;
/*    */ 
/*    */ public final class LongConverter extends NumberConverter {
/*    */   public LongConverter() {
/* 42 */     super(false);
/*    */   }
/*    */   
/*    */   public LongConverter(Object defaultValue) {
/* 54 */     super(false, defaultValue);
/*    */   }
/*    */   
/*    */   protected Class getDefaultType() {
/* 64 */     return Long.class;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\converters\LongConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
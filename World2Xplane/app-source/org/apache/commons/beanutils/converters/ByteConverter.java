/*    */ package org.apache.commons.beanutils.converters;
/*    */ 
/*    */ public final class ByteConverter extends NumberConverter {
/*    */   public ByteConverter() {
/* 42 */     super(false);
/*    */   }
/*    */   
/*    */   public ByteConverter(Object defaultValue) {
/* 54 */     super(false, defaultValue);
/*    */   }
/*    */   
/*    */   protected Class getDefaultType() {
/* 64 */     return Byte.class;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\converters\ByteConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package org.apache.commons.beanutils.converters;
/*    */ 
/*    */ public final class ShortConverter extends NumberConverter {
/*    */   public ShortConverter() {
/* 42 */     super(false);
/*    */   }
/*    */   
/*    */   public ShortConverter(Object defaultValue) {
/* 54 */     super(false, defaultValue);
/*    */   }
/*    */   
/*    */   protected Class getDefaultType() {
/* 64 */     return Short.class;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\converters\ShortConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package org.apache.commons.beanutils.converters;
/*    */ 
/*    */ import java.math.BigDecimal;
/*    */ 
/*    */ public final class BigDecimalConverter extends NumberConverter {
/*    */   public BigDecimalConverter() {
/* 44 */     super(true);
/*    */   }
/*    */   
/*    */   public BigDecimalConverter(Object defaultValue) {
/* 56 */     super(true, defaultValue);
/*    */   }
/*    */   
/*    */   protected Class getDefaultType() {
/* 66 */     return BigDecimal.class;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\converters\BigDecimalConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
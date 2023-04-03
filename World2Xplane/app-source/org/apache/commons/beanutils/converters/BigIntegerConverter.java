/*    */ package org.apache.commons.beanutils.converters;
/*    */ 
/*    */ import java.math.BigInteger;
/*    */ 
/*    */ public final class BigIntegerConverter extends NumberConverter {
/*    */   public BigIntegerConverter() {
/* 44 */     super(false);
/*    */   }
/*    */   
/*    */   public BigIntegerConverter(Object defaultValue) {
/* 56 */     super(false, defaultValue);
/*    */   }
/*    */   
/*    */   protected Class getDefaultType() {
/* 66 */     return BigInteger.class;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\converters\BigIntegerConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package org.apache.commons.math3.util;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.apache.commons.math3.Field;
/*    */ import org.apache.commons.math3.FieldElement;
/*    */ 
/*    */ public class BigRealField implements Field<BigReal>, Serializable {
/*    */   private static final long serialVersionUID = 4756431066541037559L;
/*    */   
/*    */   private BigRealField() {}
/*    */   
/*    */   public static BigRealField getInstance() {
/* 48 */     return LazyHolder.INSTANCE;
/*    */   }
/*    */   
/*    */   public BigReal getOne() {
/* 53 */     return BigReal.ONE;
/*    */   }
/*    */   
/*    */   public BigReal getZero() {
/* 58 */     return BigReal.ZERO;
/*    */   }
/*    */   
/*    */   public Class<? extends FieldElement<BigReal>> getRuntimeClass() {
/* 63 */     return (Class)BigReal.class;
/*    */   }
/*    */   
/*    */   private static class LazyHolder {
/* 72 */     private static final BigRealField INSTANCE = new BigRealField();
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 81 */     return LazyHolder.INSTANCE;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math\\util\BigRealField.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
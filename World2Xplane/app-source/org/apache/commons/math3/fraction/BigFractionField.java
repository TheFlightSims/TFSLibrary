/*    */ package org.apache.commons.math3.fraction;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.apache.commons.math3.Field;
/*    */ import org.apache.commons.math3.FieldElement;
/*    */ 
/*    */ public class BigFractionField implements Field<BigFraction>, Serializable {
/*    */   private static final long serialVersionUID = -1699294557189741703L;
/*    */   
/*    */   private BigFractionField() {}
/*    */   
/*    */   public static BigFractionField getInstance() {
/* 48 */     return LazyHolder.INSTANCE;
/*    */   }
/*    */   
/*    */   public BigFraction getOne() {
/* 53 */     return BigFraction.ONE;
/*    */   }
/*    */   
/*    */   public BigFraction getZero() {
/* 58 */     return BigFraction.ZERO;
/*    */   }
/*    */   
/*    */   public Class<? extends FieldElement<BigFraction>> getRuntimeClass() {
/* 63 */     return (Class)BigFraction.class;
/*    */   }
/*    */   
/*    */   private static class LazyHolder {
/* 71 */     private static final BigFractionField INSTANCE = new BigFractionField();
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 80 */     return LazyHolder.INSTANCE;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\fraction\BigFractionField.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
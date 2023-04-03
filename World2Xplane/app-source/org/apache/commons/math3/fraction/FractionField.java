/*    */ package org.apache.commons.math3.fraction;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.apache.commons.math3.Field;
/*    */ import org.apache.commons.math3.FieldElement;
/*    */ 
/*    */ public class FractionField implements Field<Fraction>, Serializable {
/*    */   private static final long serialVersionUID = -1257768487499119313L;
/*    */   
/*    */   private FractionField() {}
/*    */   
/*    */   public static FractionField getInstance() {
/* 48 */     return LazyHolder.INSTANCE;
/*    */   }
/*    */   
/*    */   public Fraction getOne() {
/* 53 */     return Fraction.ONE;
/*    */   }
/*    */   
/*    */   public Fraction getZero() {
/* 58 */     return Fraction.ZERO;
/*    */   }
/*    */   
/*    */   public Class<? extends FieldElement<Fraction>> getRuntimeClass() {
/* 63 */     return (Class)Fraction.class;
/*    */   }
/*    */   
/*    */   private static class LazyHolder {
/* 71 */     private static final FractionField INSTANCE = new FractionField();
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 80 */     return LazyHolder.INSTANCE;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\fraction\FractionField.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
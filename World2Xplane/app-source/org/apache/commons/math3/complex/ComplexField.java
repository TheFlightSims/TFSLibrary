/*    */ package org.apache.commons.math3.complex;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.apache.commons.math3.Field;
/*    */ import org.apache.commons.math3.FieldElement;
/*    */ 
/*    */ public class ComplexField implements Field<Complex>, Serializable {
/*    */   private static final long serialVersionUID = -6130362688700788798L;
/*    */   
/*    */   private ComplexField() {}
/*    */   
/*    */   public static ComplexField getInstance() {
/* 48 */     return LazyHolder.INSTANCE;
/*    */   }
/*    */   
/*    */   public Complex getOne() {
/* 53 */     return Complex.ONE;
/*    */   }
/*    */   
/*    */   public Complex getZero() {
/* 58 */     return Complex.ZERO;
/*    */   }
/*    */   
/*    */   public Class<? extends FieldElement<Complex>> getRuntimeClass() {
/* 63 */     return (Class)Complex.class;
/*    */   }
/*    */   
/*    */   private static class LazyHolder {
/* 72 */     private static final ComplexField INSTANCE = new ComplexField();
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 81 */     return LazyHolder.INSTANCE;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\complex\ComplexField.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
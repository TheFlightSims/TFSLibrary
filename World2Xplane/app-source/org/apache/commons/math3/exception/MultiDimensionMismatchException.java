/*    */ package org.apache.commons.math3.exception;
/*    */ 
/*    */ import org.apache.commons.math3.exception.util.Localizable;
/*    */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*    */ 
/*    */ public class MultiDimensionMismatchException extends MathIllegalArgumentException {
/*    */   private static final long serialVersionUID = -8415396756375798143L;
/*    */   
/*    */   private final Integer[] wrong;
/*    */   
/*    */   private final Integer[] expected;
/*    */   
/*    */   public MultiDimensionMismatchException(Integer[] wrong, Integer[] expected) {
/* 45 */     this((Localizable)LocalizedFormats.DIMENSIONS_MISMATCH, wrong, expected);
/*    */   }
/*    */   
/*    */   public MultiDimensionMismatchException(Localizable specific, Integer[] wrong, Integer[] expected) {
/* 59 */     super(specific, new Object[] { wrong, expected });
/* 60 */     this.wrong = (Integer[])wrong.clone();
/* 61 */     this.expected = (Integer[])expected.clone();
/*    */   }
/*    */   
/*    */   public Integer[] getWrongDimensions() {
/* 68 */     return (Integer[])this.wrong.clone();
/*    */   }
/*    */   
/*    */   public Integer[] getExpectedDimensions() {
/* 74 */     return (Integer[])this.expected.clone();
/*    */   }
/*    */   
/*    */   public int getWrongDimension(int index) {
/* 82 */     return this.wrong[index].intValue();
/*    */   }
/*    */   
/*    */   public int getExpectedDimension(int index) {
/* 89 */     return this.expected[index].intValue();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\exception\MultiDimensionMismatchException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
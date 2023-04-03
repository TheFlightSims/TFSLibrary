/*    */ package org.apache.commons.math3.exception;
/*    */ 
/*    */ import org.apache.commons.math3.exception.util.Localizable;
/*    */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*    */ 
/*    */ public class DimensionMismatchException extends MathIllegalNumberException {
/*    */   private static final long serialVersionUID = -8415396756375798143L;
/*    */   
/*    */   private final int dimension;
/*    */   
/*    */   public DimensionMismatchException(Localizable specific, int wrong, int expected) {
/* 44 */     super(specific, Integer.valueOf(wrong), new Object[] { Integer.valueOf(expected) });
/* 45 */     this.dimension = expected;
/*    */   }
/*    */   
/*    */   public DimensionMismatchException(int wrong, int expected) {
/* 56 */     this((Localizable)LocalizedFormats.DIMENSIONS_MISMATCH_SIMPLE, wrong, expected);
/*    */   }
/*    */   
/*    */   public int getDimension() {
/* 63 */     return this.dimension;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\exception\DimensionMismatchException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
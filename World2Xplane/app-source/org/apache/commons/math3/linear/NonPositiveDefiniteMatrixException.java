/*    */ package org.apache.commons.math3.linear;
/*    */ 
/*    */ import org.apache.commons.math3.exception.NumberIsTooSmallException;
/*    */ import org.apache.commons.math3.exception.util.ExceptionContext;
/*    */ import org.apache.commons.math3.exception.util.Localizable;
/*    */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*    */ 
/*    */ public class NonPositiveDefiniteMatrixException extends NumberIsTooSmallException {
/*    */   private static final long serialVersionUID = 1641613838113738061L;
/*    */   
/*    */   private final int index;
/*    */   
/*    */   private final double threshold;
/*    */   
/*    */   public NonPositiveDefiniteMatrixException(double wrong, int index, double threshold) {
/* 47 */     super(Double.valueOf(wrong), Double.valueOf(threshold), false);
/* 48 */     this.index = index;
/* 49 */     this.threshold = threshold;
/* 51 */     ExceptionContext context = getContext();
/* 52 */     context.addMessage((Localizable)LocalizedFormats.NOT_POSITIVE_DEFINITE_MATRIX, new Object[0]);
/* 53 */     context.addMessage((Localizable)LocalizedFormats.ARRAY_ELEMENT, new Object[] { Double.valueOf(wrong), Integer.valueOf(index) });
/*    */   }
/*    */   
/*    */   public int getRow() {
/* 60 */     return this.index;
/*    */   }
/*    */   
/*    */   public int getColumn() {
/* 66 */     return this.index;
/*    */   }
/*    */   
/*    */   public double getThreshold() {
/* 72 */     return this.threshold;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\linear\NonPositiveDefiniteMatrixException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package org.apache.commons.math3.linear;
/*    */ 
/*    */ import org.apache.commons.math3.exception.MathIllegalArgumentException;
/*    */ import org.apache.commons.math3.exception.util.Localizable;
/*    */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*    */ 
/*    */ public class NonSymmetricMatrixException extends MathIllegalArgumentException {
/*    */   private static final long serialVersionUID = -7518495577824189882L;
/*    */   
/*    */   private final int row;
/*    */   
/*    */   private final int column;
/*    */   
/*    */   private final double threshold;
/*    */   
/*    */   public NonSymmetricMatrixException(int row, int column, double threshold) {
/* 48 */     super((Localizable)LocalizedFormats.NON_SYMMETRIC_MATRIX, new Object[] { Integer.valueOf(row), Integer.valueOf(column), Double.valueOf(threshold) });
/* 49 */     this.row = row;
/* 50 */     this.column = column;
/* 51 */     this.threshold = threshold;
/*    */   }
/*    */   
/*    */   public int getRow() {
/* 58 */     return this.row;
/*    */   }
/*    */   
/*    */   public int getColumn() {
/* 64 */     return this.column;
/*    */   }
/*    */   
/*    */   public double getThreshold() {
/* 70 */     return this.threshold;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\linear\NonSymmetricMatrixException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
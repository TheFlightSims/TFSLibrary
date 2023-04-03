/*    */ package org.apache.commons.math3.linear;
/*    */ 
/*    */ public class DefaultRealMatrixChangingVisitor implements RealMatrixChangingVisitor {
/*    */   public void start(int rows, int columns, int startRow, int endRow, int startColumn, int endColumn) {}
/*    */   
/*    */   public double visit(int row, int column, double value) {
/* 38 */     return value;
/*    */   }
/*    */   
/*    */   public double end() {
/* 43 */     return 0.0D;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\linear\DefaultRealMatrixChangingVisitor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
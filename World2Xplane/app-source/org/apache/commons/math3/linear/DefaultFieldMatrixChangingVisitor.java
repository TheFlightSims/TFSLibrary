/*    */ package org.apache.commons.math3.linear;
/*    */ 
/*    */ import org.apache.commons.math3.FieldElement;
/*    */ 
/*    */ public class DefaultFieldMatrixChangingVisitor<T extends FieldElement<T>> implements FieldMatrixChangingVisitor<T> {
/*    */   private final T zero;
/*    */   
/*    */   public DefaultFieldMatrixChangingVisitor(T zero) {
/* 42 */     this.zero = zero;
/*    */   }
/*    */   
/*    */   public void start(int rows, int columns, int startRow, int endRow, int startColumn, int endColumn) {}
/*    */   
/*    */   public T visit(int row, int column, T value) {
/* 52 */     return value;
/*    */   }
/*    */   
/*    */   public T end() {
/* 57 */     return this.zero;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\linear\DefaultFieldMatrixChangingVisitor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
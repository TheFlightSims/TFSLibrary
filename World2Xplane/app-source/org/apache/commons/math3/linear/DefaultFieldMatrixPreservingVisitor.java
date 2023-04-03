/*    */ package org.apache.commons.math3.linear;
/*    */ 
/*    */ import org.apache.commons.math3.FieldElement;
/*    */ 
/*    */ public class DefaultFieldMatrixPreservingVisitor<T extends FieldElement<T>> implements FieldMatrixPreservingVisitor<T> {
/*    */   private final T zero;
/*    */   
/*    */   public DefaultFieldMatrixPreservingVisitor(T zero) {
/* 42 */     this.zero = zero;
/*    */   }
/*    */   
/*    */   public void start(int rows, int columns, int startRow, int endRow, int startColumn, int endColumn) {}
/*    */   
/*    */   public void visit(int row, int column, T value) {}
/*    */   
/*    */   public T end() {
/* 55 */     return this.zero;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\linear\DefaultFieldMatrixPreservingVisitor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
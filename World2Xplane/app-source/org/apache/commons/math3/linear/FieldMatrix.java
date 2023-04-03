package org.apache.commons.math3.linear;

import org.apache.commons.math3.Field;

public interface FieldMatrix<T extends org.apache.commons.math3.FieldElement<T>> extends AnyMatrix {
  Field<T> getField();
  
  FieldMatrix<T> createMatrix(int paramInt1, int paramInt2);
  
  FieldMatrix<T> copy();
  
  FieldMatrix<T> add(FieldMatrix<T> paramFieldMatrix);
  
  FieldMatrix<T> subtract(FieldMatrix<T> paramFieldMatrix);
  
  FieldMatrix<T> scalarAdd(T paramT);
  
  FieldMatrix<T> scalarMultiply(T paramT);
  
  FieldMatrix<T> multiply(FieldMatrix<T> paramFieldMatrix);
  
  FieldMatrix<T> preMultiply(FieldMatrix<T> paramFieldMatrix);
  
  FieldMatrix<T> power(int paramInt);
  
  T[][] getData();
  
  FieldMatrix<T> getSubMatrix(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  FieldMatrix<T> getSubMatrix(int[] paramArrayOfint1, int[] paramArrayOfint2);
  
  void copySubMatrix(int paramInt1, int paramInt2, int paramInt3, int paramInt4, T[][] paramArrayOfT);
  
  void copySubMatrix(int[] paramArrayOfint1, int[] paramArrayOfint2, T[][] paramArrayOfT);
  
  void setSubMatrix(T[][] paramArrayOfT, int paramInt1, int paramInt2);
  
  FieldMatrix<T> getRowMatrix(int paramInt);
  
  void setRowMatrix(int paramInt, FieldMatrix<T> paramFieldMatrix);
  
  FieldMatrix<T> getColumnMatrix(int paramInt);
  
  void setColumnMatrix(int paramInt, FieldMatrix<T> paramFieldMatrix);
  
  FieldVector<T> getRowVector(int paramInt);
  
  void setRowVector(int paramInt, FieldVector<T> paramFieldVector);
  
  FieldVector<T> getColumnVector(int paramInt);
  
  void setColumnVector(int paramInt, FieldVector<T> paramFieldVector);
  
  T[] getRow(int paramInt);
  
  void setRow(int paramInt, T[] paramArrayOfT);
  
  T[] getColumn(int paramInt);
  
  void setColumn(int paramInt, T[] paramArrayOfT);
  
  T getEntry(int paramInt1, int paramInt2);
  
  void setEntry(int paramInt1, int paramInt2, T paramT);
  
  void addToEntry(int paramInt1, int paramInt2, T paramT);
  
  void multiplyEntry(int paramInt1, int paramInt2, T paramT);
  
  FieldMatrix<T> transpose();
  
  T getTrace();
  
  T[] operate(T[] paramArrayOfT);
  
  FieldVector<T> operate(FieldVector<T> paramFieldVector);
  
  T[] preMultiply(T[] paramArrayOfT);
  
  FieldVector<T> preMultiply(FieldVector<T> paramFieldVector);
  
  T walkInRowOrder(FieldMatrixChangingVisitor<T> paramFieldMatrixChangingVisitor);
  
  T walkInRowOrder(FieldMatrixPreservingVisitor<T> paramFieldMatrixPreservingVisitor);
  
  T walkInRowOrder(FieldMatrixChangingVisitor<T> paramFieldMatrixChangingVisitor, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  T walkInRowOrder(FieldMatrixPreservingVisitor<T> paramFieldMatrixPreservingVisitor, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  T walkInColumnOrder(FieldMatrixChangingVisitor<T> paramFieldMatrixChangingVisitor);
  
  T walkInColumnOrder(FieldMatrixPreservingVisitor<T> paramFieldMatrixPreservingVisitor);
  
  T walkInColumnOrder(FieldMatrixChangingVisitor<T> paramFieldMatrixChangingVisitor, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  T walkInColumnOrder(FieldMatrixPreservingVisitor<T> paramFieldMatrixPreservingVisitor, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  T walkInOptimizedOrder(FieldMatrixChangingVisitor<T> paramFieldMatrixChangingVisitor);
  
  T walkInOptimizedOrder(FieldMatrixPreservingVisitor<T> paramFieldMatrixPreservingVisitor);
  
  T walkInOptimizedOrder(FieldMatrixChangingVisitor<T> paramFieldMatrixChangingVisitor, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  T walkInOptimizedOrder(FieldMatrixPreservingVisitor<T> paramFieldMatrixPreservingVisitor, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\linear\FieldMatrix.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
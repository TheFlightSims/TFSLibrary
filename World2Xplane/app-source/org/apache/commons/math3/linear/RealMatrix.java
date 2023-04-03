package org.apache.commons.math3.linear;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.ZeroException;

public interface RealMatrix extends AnyMatrix {
  RealMatrix createMatrix(int paramInt1, int paramInt2);
  
  RealMatrix copy();
  
  RealMatrix add(RealMatrix paramRealMatrix);
  
  RealMatrix subtract(RealMatrix paramRealMatrix);
  
  RealMatrix scalarAdd(double paramDouble);
  
  RealMatrix scalarMultiply(double paramDouble);
  
  RealMatrix multiply(RealMatrix paramRealMatrix);
  
  RealMatrix preMultiply(RealMatrix paramRealMatrix);
  
  RealMatrix power(int paramInt);
  
  double[][] getData();
  
  double getNorm();
  
  double getFrobeniusNorm();
  
  RealMatrix getSubMatrix(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  RealMatrix getSubMatrix(int[] paramArrayOfint1, int[] paramArrayOfint2);
  
  void copySubMatrix(int paramInt1, int paramInt2, int paramInt3, int paramInt4, double[][] paramArrayOfdouble);
  
  void copySubMatrix(int[] paramArrayOfint1, int[] paramArrayOfint2, double[][] paramArrayOfdouble);
  
  void setSubMatrix(double[][] paramArrayOfdouble, int paramInt1, int paramInt2) throws ZeroException, OutOfRangeException, DimensionMismatchException, NullArgumentException;
  
  RealMatrix getRowMatrix(int paramInt);
  
  void setRowMatrix(int paramInt, RealMatrix paramRealMatrix);
  
  RealMatrix getColumnMatrix(int paramInt);
  
  void setColumnMatrix(int paramInt, RealMatrix paramRealMatrix);
  
  RealVector getRowVector(int paramInt);
  
  void setRowVector(int paramInt, RealVector paramRealVector);
  
  RealVector getColumnVector(int paramInt);
  
  void setColumnVector(int paramInt, RealVector paramRealVector);
  
  double[] getRow(int paramInt);
  
  void setRow(int paramInt, double[] paramArrayOfdouble);
  
  double[] getColumn(int paramInt);
  
  void setColumn(int paramInt, double[] paramArrayOfdouble);
  
  double getEntry(int paramInt1, int paramInt2);
  
  void setEntry(int paramInt1, int paramInt2, double paramDouble);
  
  void addToEntry(int paramInt1, int paramInt2, double paramDouble);
  
  void multiplyEntry(int paramInt1, int paramInt2, double paramDouble);
  
  RealMatrix transpose();
  
  double getTrace();
  
  double[] operate(double[] paramArrayOfdouble);
  
  RealVector operate(RealVector paramRealVector);
  
  double[] preMultiply(double[] paramArrayOfdouble);
  
  RealVector preMultiply(RealVector paramRealVector);
  
  double walkInRowOrder(RealMatrixChangingVisitor paramRealMatrixChangingVisitor);
  
  double walkInRowOrder(RealMatrixPreservingVisitor paramRealMatrixPreservingVisitor);
  
  double walkInRowOrder(RealMatrixChangingVisitor paramRealMatrixChangingVisitor, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  double walkInRowOrder(RealMatrixPreservingVisitor paramRealMatrixPreservingVisitor, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  double walkInColumnOrder(RealMatrixChangingVisitor paramRealMatrixChangingVisitor);
  
  double walkInColumnOrder(RealMatrixPreservingVisitor paramRealMatrixPreservingVisitor);
  
  double walkInColumnOrder(RealMatrixChangingVisitor paramRealMatrixChangingVisitor, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  double walkInColumnOrder(RealMatrixPreservingVisitor paramRealMatrixPreservingVisitor, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  double walkInOptimizedOrder(RealMatrixChangingVisitor paramRealMatrixChangingVisitor);
  
  double walkInOptimizedOrder(RealMatrixPreservingVisitor paramRealMatrixPreservingVisitor);
  
  double walkInOptimizedOrder(RealMatrixChangingVisitor paramRealMatrixChangingVisitor, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  double walkInOptimizedOrder(RealMatrixPreservingVisitor paramRealMatrixPreservingVisitor, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\linear\RealMatrix.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
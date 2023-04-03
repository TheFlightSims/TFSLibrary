package org.apache.commons.math3.linear;

public interface FieldMatrixPreservingVisitor<T extends org.apache.commons.math3.FieldElement<?>> {
  void start(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6);
  
  void visit(int paramInt1, int paramInt2, T paramT);
  
  T end();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\linear\FieldMatrixPreservingVisitor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
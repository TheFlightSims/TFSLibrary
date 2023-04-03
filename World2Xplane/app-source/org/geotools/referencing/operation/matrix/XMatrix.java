package org.geotools.referencing.operation.matrix;

import javax.vecmath.SingularMatrixException;
import org.opengis.referencing.operation.Matrix;

public interface XMatrix extends Matrix {
  int getNumRow();
  
  int getNumCol();
  
  double getElement(int paramInt1, int paramInt2);
  
  void setElement(int paramInt1, int paramInt2, double paramDouble);
  
  void setZero();
  
  void setIdentity();
  
  boolean isIdentity();
  
  boolean isIdentity(double paramDouble);
  
  boolean isAffine();
  
  void negate();
  
  void transpose();
  
  void invert() throws SingularMatrixException;
  
  void multiply(Matrix paramMatrix);
  
  boolean equals(Matrix paramMatrix, double paramDouble);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\matrix\XMatrix.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
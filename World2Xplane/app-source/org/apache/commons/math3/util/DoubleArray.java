package org.apache.commons.math3.util;

public interface DoubleArray {
  int getNumElements();
  
  double getElement(int paramInt);
  
  void setElement(int paramInt, double paramDouble);
  
  void addElement(double paramDouble);
  
  void addElements(double[] paramArrayOfdouble);
  
  double addElementRolling(double paramDouble);
  
  double[] getElements();
  
  void clear();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math\\util\DoubleArray.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
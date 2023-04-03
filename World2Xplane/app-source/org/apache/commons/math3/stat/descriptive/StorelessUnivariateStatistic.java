package org.apache.commons.math3.stat.descriptive;

public interface StorelessUnivariateStatistic extends UnivariateStatistic {
  void increment(double paramDouble);
  
  void incrementAll(double[] paramArrayOfdouble);
  
  void incrementAll(double[] paramArrayOfdouble, int paramInt1, int paramInt2);
  
  double getResult();
  
  long getN();
  
  void clear();
  
  StorelessUnivariateStatistic copy();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\stat\descriptive\StorelessUnivariateStatistic.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
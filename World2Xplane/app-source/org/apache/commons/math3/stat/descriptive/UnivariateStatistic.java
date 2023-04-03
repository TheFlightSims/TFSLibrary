package org.apache.commons.math3.stat.descriptive;

public interface UnivariateStatistic {
  double evaluate(double[] paramArrayOfdouble);
  
  double evaluate(double[] paramArrayOfdouble, int paramInt1, int paramInt2);
  
  UnivariateStatistic copy();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\stat\descriptive\UnivariateStatistic.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
package org.apache.commons.math3.stat.descriptive;

import org.apache.commons.math3.linear.RealMatrix;

public interface StatisticalMultivariateSummary {
  int getDimension();
  
  double[] getMean();
  
  RealMatrix getCovariance();
  
  double[] getStandardDeviation();
  
  double[] getMax();
  
  double[] getMin();
  
  long getN();
  
  double[] getGeometricMean();
  
  double[] getSum();
  
  double[] getSumSq();
  
  double[] getSumLog();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\stat\descriptive\StatisticalMultivariateSummary.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
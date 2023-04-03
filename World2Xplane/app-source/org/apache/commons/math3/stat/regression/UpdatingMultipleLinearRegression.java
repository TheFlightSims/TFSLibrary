package org.apache.commons.math3.stat.regression;

public interface UpdatingMultipleLinearRegression {
  boolean hasIntercept();
  
  long getN();
  
  void addObservation(double[] paramArrayOfdouble, double paramDouble) throws ModelSpecificationException;
  
  void addObservations(double[][] paramArrayOfdouble, double[] paramArrayOfdouble1) throws ModelSpecificationException;
  
  void clear();
  
  RegressionResults regress() throws ModelSpecificationException;
  
  RegressionResults regress(int[] paramArrayOfint) throws ModelSpecificationException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\stat\regression\UpdatingMultipleLinearRegression.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
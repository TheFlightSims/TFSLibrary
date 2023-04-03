package org.apache.commons.math3.stat.regression;

public interface MultipleLinearRegression {
  double[] estimateRegressionParameters();
  
  double[][] estimateRegressionParametersVariance();
  
  double[] estimateResiduals();
  
  double estimateRegressandVariance();
  
  double[] estimateRegressionParametersStandardErrors();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\stat\regression\MultipleLinearRegression.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
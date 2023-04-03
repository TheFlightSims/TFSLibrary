package org.apache.commons.math3.analysis;

public interface ParametricUnivariateFunction {
  double value(double paramDouble, double... paramVarArgs);
  
  double[] gradient(double paramDouble, double... paramVarArgs);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\ParametricUnivariateFunction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
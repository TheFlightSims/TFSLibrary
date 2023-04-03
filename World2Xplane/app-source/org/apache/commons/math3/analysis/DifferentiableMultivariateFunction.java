package org.apache.commons.math3.analysis;

public interface DifferentiableMultivariateFunction extends MultivariateFunction {
  MultivariateFunction partialDerivative(int paramInt);
  
  MultivariateVectorFunction gradient();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\DifferentiableMultivariateFunction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
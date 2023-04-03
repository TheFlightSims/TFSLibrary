package org.apache.commons.math3.analysis.integration;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;

public interface UnivariateIntegrator {
  double getRelativeAccuracy();
  
  double getAbsoluteAccuracy();
  
  int getMinimalIterationCount();
  
  int getMaximalIterationCount();
  
  double integrate(int paramInt, UnivariateFunction paramUnivariateFunction, double paramDouble1, double paramDouble2) throws TooManyEvaluationsException, MaxCountExceededException, MathIllegalArgumentException, NullArgumentException;
  
  int getEvaluations();
  
  int getIterations();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\integration\UnivariateIntegrator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
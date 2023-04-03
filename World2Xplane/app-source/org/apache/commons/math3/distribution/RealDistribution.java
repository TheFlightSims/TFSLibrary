package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.OutOfRangeException;

public interface RealDistribution {
  double probability(double paramDouble);
  
  double density(double paramDouble);
  
  double cumulativeProbability(double paramDouble);
  
  double cumulativeProbability(double paramDouble1, double paramDouble2) throws NumberIsTooLargeException;
  
  double inverseCumulativeProbability(double paramDouble) throws OutOfRangeException;
  
  double getNumericalMean();
  
  double getNumericalVariance();
  
  double getSupportLowerBound();
  
  double getSupportUpperBound();
  
  boolean isSupportLowerBoundInclusive();
  
  boolean isSupportUpperBoundInclusive();
  
  boolean isSupportConnected();
  
  void reseedRandomGenerator(long paramLong);
  
  double sample();
  
  double[] sample(int paramInt);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\distribution\RealDistribution.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
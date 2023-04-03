package org.apache.commons.math3.ode.sampling;

import java.io.Externalizable;

public interface StepInterpolator extends Externalizable {
  double getPreviousTime();
  
  double getCurrentTime();
  
  double getInterpolatedTime();
  
  void setInterpolatedTime(double paramDouble);
  
  double[] getInterpolatedState();
  
  double[] getInterpolatedDerivatives();
  
  double[] getInterpolatedSecondaryState(int paramInt);
  
  double[] getInterpolatedSecondaryDerivatives(int paramInt);
  
  boolean isForward();
  
  StepInterpolator copy();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\ode\sampling\StepInterpolator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
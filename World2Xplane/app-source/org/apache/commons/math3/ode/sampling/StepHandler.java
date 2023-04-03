package org.apache.commons.math3.ode.sampling;

public interface StepHandler {
  void init(double paramDouble1, double[] paramArrayOfdouble, double paramDouble2);
  
  void handleStep(StepInterpolator paramStepInterpolator, boolean paramBoolean);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\ode\sampling\StepHandler.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
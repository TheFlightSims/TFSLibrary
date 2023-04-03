package org.apache.commons.math3.ode;

public interface SecondOrderDifferentialEquations {
  int getDimension();
  
  void computeSecondDerivatives(double paramDouble, double[] paramArrayOfdouble1, double[] paramArrayOfdouble2, double[] paramArrayOfdouble3);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\ode\SecondOrderDifferentialEquations.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
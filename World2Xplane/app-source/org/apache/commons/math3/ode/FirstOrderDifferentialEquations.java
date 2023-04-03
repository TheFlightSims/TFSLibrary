package org.apache.commons.math3.ode;

public interface FirstOrderDifferentialEquations {
  int getDimension();
  
  void computeDerivatives(double paramDouble, double[] paramArrayOfdouble1, double[] paramArrayOfdouble2);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\ode\FirstOrderDifferentialEquations.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
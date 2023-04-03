package org.apache.commons.math3.ode;

public interface MainStateJacobianProvider extends FirstOrderDifferentialEquations {
  void computeMainStateJacobian(double paramDouble, double[] paramArrayOfdouble1, double[] paramArrayOfdouble2, double[][] paramArrayOfdouble);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\ode\MainStateJacobianProvider.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
package org.apache.commons.math3.ode;

public interface SecondaryEquations {
  int getDimension();
  
  void computeDerivatives(double paramDouble, double[] paramArrayOfdouble1, double[] paramArrayOfdouble2, double[] paramArrayOfdouble3, double[] paramArrayOfdouble4);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\ode\SecondaryEquations.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
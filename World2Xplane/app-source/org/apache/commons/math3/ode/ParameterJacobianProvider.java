package org.apache.commons.math3.ode;

import org.apache.commons.math3.exception.MathIllegalArgumentException;

public interface ParameterJacobianProvider extends Parameterizable {
  void computeParameterJacobian(double paramDouble, double[] paramArrayOfdouble1, double[] paramArrayOfdouble2, String paramString, double[] paramArrayOfdouble3) throws MathIllegalArgumentException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\ode\ParameterJacobianProvider.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
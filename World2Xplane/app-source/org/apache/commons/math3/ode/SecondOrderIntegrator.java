package org.apache.commons.math3.ode;

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MathIllegalStateException;

public interface SecondOrderIntegrator extends ODEIntegrator {
  void integrate(SecondOrderDifferentialEquations paramSecondOrderDifferentialEquations, double paramDouble1, double[] paramArrayOfdouble1, double[] paramArrayOfdouble2, double paramDouble2, double[] paramArrayOfdouble3, double[] paramArrayOfdouble4) throws MathIllegalStateException, MathIllegalArgumentException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\ode\SecondOrderIntegrator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
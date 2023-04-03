package org.apache.commons.math3.ode;

public interface ParameterizedODE extends Parameterizable {
  double getParameter(String paramString) throws IllegalArgumentException;
  
  void setParameter(String paramString, double paramDouble) throws IllegalArgumentException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\ode\ParameterizedODE.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
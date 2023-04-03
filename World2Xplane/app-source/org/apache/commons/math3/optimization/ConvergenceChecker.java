package org.apache.commons.math3.optimization;

public interface ConvergenceChecker<PAIR> {
  boolean converged(int paramInt, PAIR paramPAIR1, PAIR paramPAIR2);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\optimization\ConvergenceChecker.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
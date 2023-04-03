package org.apache.commons.math3.linear;

public interface DecompositionSolver {
  RealVector solve(RealVector paramRealVector);
  
  RealMatrix solve(RealMatrix paramRealMatrix);
  
  boolean isNonSingular();
  
  RealMatrix getInverse();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\linear\DecompositionSolver.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
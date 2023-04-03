package org.apache.commons.math3.optimization;

public interface BaseOptimizer<PAIR> {
  int getMaxEvaluations();
  
  int getEvaluations();
  
  ConvergenceChecker<PAIR> getConvergenceChecker();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\optimization\BaseOptimizer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
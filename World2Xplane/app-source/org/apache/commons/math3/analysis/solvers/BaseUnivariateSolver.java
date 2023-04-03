package org.apache.commons.math3.analysis.solvers;

public interface BaseUnivariateSolver<FUNC extends org.apache.commons.math3.analysis.UnivariateFunction> {
  int getMaxEvaluations();
  
  int getEvaluations();
  
  double getAbsoluteAccuracy();
  
  double getRelativeAccuracy();
  
  double getFunctionValueAccuracy();
  
  double solve(int paramInt, FUNC paramFUNC, double paramDouble1, double paramDouble2);
  
  double solve(int paramInt, FUNC paramFUNC, double paramDouble1, double paramDouble2, double paramDouble3);
  
  double solve(int paramInt, FUNC paramFUNC, double paramDouble);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\solvers\BaseUnivariateSolver.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
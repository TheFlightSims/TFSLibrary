package org.apache.commons.math3.optimization.univariate;

import org.apache.commons.math3.optimization.BaseOptimizer;
import org.apache.commons.math3.optimization.GoalType;

public interface BaseUnivariateOptimizer<FUNC extends org.apache.commons.math3.analysis.UnivariateFunction> extends BaseOptimizer<UnivariatePointValuePair> {
  UnivariatePointValuePair optimize(int paramInt, FUNC paramFUNC, GoalType paramGoalType, double paramDouble1, double paramDouble2);
  
  UnivariatePointValuePair optimize(int paramInt, FUNC paramFUNC, GoalType paramGoalType, double paramDouble1, double paramDouble2, double paramDouble3);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\optimizatio\\univariate\BaseUnivariateOptimizer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
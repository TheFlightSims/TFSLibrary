package org.apache.commons.math3.optimization;

public interface BaseMultivariateOptimizer<FUNC extends org.apache.commons.math3.analysis.MultivariateFunction> extends BaseOptimizer<PointValuePair> {
  PointValuePair optimize(int paramInt, FUNC paramFUNC, GoalType paramGoalType, double[] paramArrayOfdouble);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\optimization\BaseMultivariateOptimizer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
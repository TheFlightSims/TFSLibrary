package org.apache.commons.math3.optimization;

public interface BaseMultivariateSimpleBoundsOptimizer<FUNC extends org.apache.commons.math3.analysis.MultivariateFunction> extends BaseMultivariateOptimizer<FUNC> {
  PointValuePair optimize(int paramInt, FUNC paramFUNC, GoalType paramGoalType, double[] paramArrayOfdouble1, double[] paramArrayOfdouble2, double[] paramArrayOfdouble3);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\optimization\BaseMultivariateSimpleBoundsOptimizer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
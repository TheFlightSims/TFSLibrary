package org.apache.commons.math3.optimization.linear;

import java.util.Collection;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.optimization.GoalType;
import org.apache.commons.math3.optimization.PointValuePair;

public interface LinearOptimizer {
  void setMaxIterations(int paramInt);
  
  int getMaxIterations();
  
  int getIterations();
  
  PointValuePair optimize(LinearObjectiveFunction paramLinearObjectiveFunction, Collection<LinearConstraint> paramCollection, GoalType paramGoalType, boolean paramBoolean) throws MathIllegalStateException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\optimization\linear\LinearOptimizer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
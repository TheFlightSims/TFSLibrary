package org.apache.commons.math3.filter;

import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

public interface ProcessModel {
  RealMatrix getStateTransitionMatrix();
  
  RealMatrix getControlMatrix();
  
  RealMatrix getProcessNoise();
  
  RealVector getInitialStateEstimate();
  
  RealMatrix getInitialErrorCovariance();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\filter\ProcessModel.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
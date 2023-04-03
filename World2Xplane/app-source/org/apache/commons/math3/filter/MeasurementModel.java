package org.apache.commons.math3.filter;

import org.apache.commons.math3.linear.RealMatrix;

public interface MeasurementModel {
  RealMatrix getMeasurementMatrix();
  
  RealMatrix getMeasurementNoise();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\filter\MeasurementModel.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
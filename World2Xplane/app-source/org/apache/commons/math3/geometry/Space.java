package org.apache.commons.math3.geometry;

import java.io.Serializable;

public interface Space extends Serializable {
  int getDimension();
  
  Space getSubSpace();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\geometry\Space.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
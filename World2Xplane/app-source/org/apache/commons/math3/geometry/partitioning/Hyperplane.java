package org.apache.commons.math3.geometry.partitioning;

import org.apache.commons.math3.geometry.Vector;

public interface Hyperplane<S extends org.apache.commons.math3.geometry.Space> {
  Hyperplane<S> copySelf();
  
  double getOffset(Vector<S> paramVector);
  
  boolean sameOrientationAs(Hyperplane<S> paramHyperplane);
  
  SubHyperplane<S> wholeHyperplane();
  
  Region<S> wholeSpace();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\geometry\partitioning\Hyperplane.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
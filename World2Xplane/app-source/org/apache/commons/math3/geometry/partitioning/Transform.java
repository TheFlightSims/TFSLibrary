package org.apache.commons.math3.geometry.partitioning;

import org.apache.commons.math3.geometry.Vector;

public interface Transform<S extends org.apache.commons.math3.geometry.Space, T extends org.apache.commons.math3.geometry.Space> {
  Vector<S> apply(Vector<S> paramVector);
  
  Hyperplane<S> apply(Hyperplane<S> paramHyperplane);
  
  SubHyperplane<T> apply(SubHyperplane<T> paramSubHyperplane, Hyperplane<S> paramHyperplane1, Hyperplane<S> paramHyperplane2);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\geometry\partitioning\Transform.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
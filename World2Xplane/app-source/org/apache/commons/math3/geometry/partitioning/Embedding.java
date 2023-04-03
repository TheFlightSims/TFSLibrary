package org.apache.commons.math3.geometry.partitioning;

import org.apache.commons.math3.geometry.Vector;

public interface Embedding<S extends org.apache.commons.math3.geometry.Space, T extends org.apache.commons.math3.geometry.Space> {
  Vector<T> toSubSpace(Vector<S> paramVector);
  
  Vector<S> toSpace(Vector<T> paramVector);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\geometry\partitioning\Embedding.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
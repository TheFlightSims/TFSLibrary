package org.apache.commons.math3.stat.clustering;

import java.util.Collection;

public interface Clusterable<T> {
  double distanceFrom(T paramT);
  
  T centroidOf(Collection<T> paramCollection);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\stat\clustering\Clusterable.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
package org.geotools.filter;

import org.opengis.feature.simple.SimpleFeature;
import org.opengis.filter.spatial.DistanceBufferOperator;

public interface GeometryDistanceFilter extends GeometryFilter, DistanceBufferOperator {
  boolean equals(Object paramObject);
  
  void setDistance(double paramDouble) throws IllegalFilterException;
  
  boolean contains(SimpleFeature paramSimpleFeature);
  
  double getDistance();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\GeometryDistanceFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package org.geotools.filter;

import org.opengis.feature.simple.SimpleFeature;
import org.opengis.filter.spatial.BinarySpatialOperator;

public interface GeometryFilter extends Filter, BinarySpatialOperator {
  void addRightGeometry(Expression paramExpression) throws IllegalFilterException;
  
  void addLeftGeometry(Expression paramExpression) throws IllegalFilterException;
  
  boolean contains(SimpleFeature paramSimpleFeature);
  
  Expression getRightGeometry();
  
  Expression getLeftGeometry();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\GeometryFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
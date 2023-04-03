package org.geotools.filter;

import org.opengis.feature.simple.SimpleFeature;
import org.opengis.filter.BinaryComparisonOperator;

public interface CompareFilter extends Filter, BinaryComparisonOperator {
  void addLeftValue(Expression paramExpression) throws IllegalFilterException;
  
  void addRightValue(Expression paramExpression) throws IllegalFilterException;
  
  Expression getLeftValue();
  
  Expression getRightValue();
  
  boolean contains(SimpleFeature paramSimpleFeature);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\CompareFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
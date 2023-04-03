package org.geotools.filter;

import org.opengis.feature.simple.SimpleFeature;
import org.opengis.filter.PropertyIsNull;

public interface NullFilter extends Filter, PropertyIsNull {
  void nullCheckValue(Expression paramExpression) throws IllegalFilterException;
  
  Expression getNullCheckValue();
  
  boolean contains(SimpleFeature paramSimpleFeature);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\NullFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
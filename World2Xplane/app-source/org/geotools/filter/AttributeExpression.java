package org.geotools.filter;

import org.opengis.feature.simple.SimpleFeature;
import org.opengis.filter.expression.PropertyName;

public interface AttributeExpression extends Expression, PropertyName {
  void setAttributePath(String paramString) throws IllegalFilterException;
  
  Object getValue(SimpleFeature paramSimpleFeature);
  
  String getAttributePath();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\AttributeExpression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
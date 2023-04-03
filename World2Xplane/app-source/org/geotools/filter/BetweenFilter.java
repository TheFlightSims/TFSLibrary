package org.geotools.filter;

import org.opengis.feature.simple.SimpleFeature;
import org.opengis.filter.PropertyIsBetween;
import org.opengis.filter.expression.Expression;

public interface BetweenFilter extends CompareFilter, PropertyIsBetween {
  boolean contains(SimpleFeature paramSimpleFeature);
  
  Expression getExpression1();
  
  Expression getExpression2();
  
  void setExpression1(Expression paramExpression);
  
  void setExpression2(Expression paramExpression);
  
  Expression getMiddleValue();
  
  void addMiddleValue(Expression paramExpression);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\BetweenFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
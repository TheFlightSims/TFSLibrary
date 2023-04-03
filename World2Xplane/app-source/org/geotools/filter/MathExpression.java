package org.geotools.filter;

import org.opengis.feature.simple.SimpleFeature;
import org.opengis.filter.expression.BinaryExpression;

public interface MathExpression extends Expression, BinaryExpression {
  Object getValue(SimpleFeature paramSimpleFeature);
  
  void addRightValue(Expression paramExpression) throws IllegalFilterException;
  
  short getType();
  
  Expression getLeftValue();
  
  Expression getRightValue();
  
  void addLeftValue(Expression paramExpression) throws IllegalFilterException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\MathExpression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
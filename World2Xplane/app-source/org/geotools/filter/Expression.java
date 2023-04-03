package org.geotools.filter;

import org.opengis.feature.simple.SimpleFeature;
import org.opengis.filter.expression.Expression;

public interface Expression extends ExpressionType, Expression {
  short getType();
  
  Object evaluate(SimpleFeature paramSimpleFeature);
  
  Object getValue(SimpleFeature paramSimpleFeature);
  
  void accept(FilterVisitor paramFilterVisitor);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\Expression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
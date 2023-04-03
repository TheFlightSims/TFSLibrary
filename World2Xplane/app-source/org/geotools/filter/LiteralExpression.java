package org.geotools.filter;

import org.opengis.feature.simple.SimpleFeature;
import org.opengis.filter.expression.Literal;

public interface LiteralExpression extends Expression, Literal {
  void setLiteral(Object paramObject) throws IllegalFilterException;
  
  Object getValue(SimpleFeature paramSimpleFeature);
  
  short getType();
  
  Object getLiteral();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\LiteralExpression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
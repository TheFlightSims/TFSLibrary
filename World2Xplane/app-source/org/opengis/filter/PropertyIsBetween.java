package org.opengis.filter;

import org.opengis.annotation.XmlElement;
import org.opengis.filter.expression.Expression;

@XmlElement("PropertyIsBetween")
public interface PropertyIsBetween extends MultiValuedFilter {
  public static final String NAME = "Between";
  
  @XmlElement("expression")
  Expression getExpression();
  
  @XmlElement("LowerBoundary")
  Expression getLowerBoundary();
  
  @XmlElement("UpperBoundary")
  Expression getUpperBoundary();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\filter\PropertyIsBetween.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
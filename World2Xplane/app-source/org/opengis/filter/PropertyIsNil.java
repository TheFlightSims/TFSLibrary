package org.opengis.filter;

import org.opengis.annotation.XmlElement;
import org.opengis.filter.expression.Expression;

@XmlElement("PropertyIsNil")
public interface PropertyIsNil extends Filter {
  public static final String NAME = "Nil";
  
  @XmlElement("expression")
  Expression getExpression();
  
  @XmlElement("nilReason")
  Object getNilReason();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\filter\PropertyIsNil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package org.opengis.filter;

import org.opengis.annotation.XmlElement;
import org.opengis.filter.expression.Expression;

@XmlElement("PropertyIsNull")
public interface PropertyIsNull extends Filter {
  public static final String NAME = "NullCheck";
  
  @XmlElement("PropertyName")
  Expression getExpression();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\filter\PropertyIsNull.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
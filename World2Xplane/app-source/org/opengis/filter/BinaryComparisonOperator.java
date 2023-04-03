package org.opengis.filter;

import org.opengis.annotation.XmlElement;
import org.opengis.filter.expression.Expression;

@XmlElement("BinaryComparisonOpType")
public interface BinaryComparisonOperator extends MultiValuedFilter {
  @XmlElement("expression")
  Expression getExpression1();
  
  @XmlElement("expression")
  Expression getExpression2();
  
  @XmlElement("matchCase")
  boolean isMatchingCase();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\filter\BinaryComparisonOperator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
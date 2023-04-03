package org.opengis.filter.expression;

import org.opengis.annotation.XmlElement;

@XmlElement("BinaryOperatorType")
public interface BinaryExpression extends Expression {
  @XmlElement("expression")
  Expression getExpression1();
  
  @XmlElement("expression")
  Expression getExpression2();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\filter\expression\BinaryExpression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
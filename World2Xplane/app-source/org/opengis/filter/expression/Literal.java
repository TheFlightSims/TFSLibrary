package org.opengis.filter.expression;

import org.opengis.annotation.XmlElement;

@XmlElement("Literal")
public interface Literal extends Expression {
  Object getValue();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\filter\expression\Literal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
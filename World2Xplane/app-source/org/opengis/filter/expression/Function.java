package org.opengis.filter.expression;

import java.util.List;
import org.opengis.annotation.XmlElement;
import org.opengis.filter.capability.FunctionName;

@XmlElement("Function")
public interface Function extends Expression {
  String getName();
  
  FunctionName getFunctionName();
  
  List<Expression> getParameters();
  
  @XmlElement("fallbackValue")
  Literal getFallbackValue();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\filter\expression\Function.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
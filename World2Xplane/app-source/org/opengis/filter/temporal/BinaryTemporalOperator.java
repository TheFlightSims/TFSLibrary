package org.opengis.filter.temporal;

import org.opengis.annotation.XmlElement;
import org.opengis.filter.MultiValuedFilter;
import org.opengis.filter.expression.Expression;

@XmlElement("BinaryTemporalOpType")
public interface BinaryTemporalOperator extends MultiValuedFilter {
  @XmlElement("expression")
  Expression getExpression1();
  
  @XmlElement("expression")
  Expression getExpression2();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\filter\temporal\BinaryTemporalOperator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
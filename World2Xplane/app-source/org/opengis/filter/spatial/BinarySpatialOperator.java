package org.opengis.filter.spatial;

import org.opengis.annotation.XmlElement;
import org.opengis.filter.expression.Expression;

@XmlElement("BinarySpatialOpType")
public interface BinarySpatialOperator extends SpatialOperator {
  @XmlElement("expression")
  Expression getExpression1();
  
  @XmlElement("expression")
  Expression getExpression2();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\filter\spatial\BinarySpatialOperator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
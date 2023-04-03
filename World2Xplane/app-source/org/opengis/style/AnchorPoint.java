package org.opengis.style;

import org.opengis.annotation.XmlElement;
import org.opengis.filter.expression.Expression;

@XmlElement("AnchorPoint")
public interface AnchorPoint {
  @XmlElement("AnchorPointX")
  Expression getAnchorPointX();
  
  @XmlElement("AnchorPointY")
  Expression getAnchorPointY();
  
  Object accept(StyleVisitor paramStyleVisitor, Object paramObject);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\style\AnchorPoint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
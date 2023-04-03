package org.opengis.style;

import org.opengis.annotation.XmlElement;
import org.opengis.filter.expression.Expression;

@XmlElement("PointPlacement")
public interface PointPlacement extends LabelPlacement {
  @XmlElement("PointPlacement")
  AnchorPoint getAnchorPoint();
  
  @XmlElement("Displacement")
  Displacement getDisplacement();
  
  @XmlElement("Rotation")
  Expression getRotation();
  
  Object accept(StyleVisitor paramStyleVisitor, Object paramObject);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\style\PointPlacement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
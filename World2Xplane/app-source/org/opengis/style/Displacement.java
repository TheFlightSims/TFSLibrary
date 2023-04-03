package org.opengis.style;

import org.opengis.annotation.XmlElement;
import org.opengis.filter.expression.Expression;

@XmlElement("Displacement")
public interface Displacement {
  @XmlElement("DisplacementX")
  Expression getDisplacementX();
  
  @XmlElement("DisplacementY")
  Expression getDisplacementY();
  
  Object accept(StyleVisitor paramStyleVisitor, Object paramObject);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\style\Displacement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package org.opengis.style;

import org.opengis.annotation.XmlElement;
import org.opengis.filter.expression.Expression;

@XmlElement("ShadedRelief")
public interface ShadedRelief {
  @XmlElement("BrightnessOnly")
  boolean isBrightnessOnly();
  
  @XmlElement("ReliefFactor")
  Expression getReliefFactor();
  
  Object accept(StyleVisitor paramStyleVisitor, Object paramObject);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\style\ShadedRelief.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
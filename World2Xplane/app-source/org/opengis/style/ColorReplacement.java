package org.opengis.style;

import org.opengis.annotation.XmlElement;
import org.opengis.filter.expression.Function;

@XmlElement("ColorReplacement")
public interface ColorReplacement {
  @XmlElement("Recode")
  Function getRecoding();
  
  Object accept(StyleVisitor paramStyleVisitor, Object paramObject);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\style\ColorReplacement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
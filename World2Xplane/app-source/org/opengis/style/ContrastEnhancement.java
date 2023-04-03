package org.opengis.style;

import org.opengis.annotation.XmlElement;
import org.opengis.filter.expression.Expression;

@XmlElement("ContrastEnhancement")
public interface ContrastEnhancement {
  @XmlElement("Normalize,Histogram")
  ContrastMethod getMethod();
  
  @XmlElement("GammaValue")
  Expression getGammaValue();
  
  Object accept(StyleVisitor paramStyleVisitor, Object paramObject);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\style\ContrastEnhancement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
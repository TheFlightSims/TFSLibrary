package org.opengis.style;

import org.opengis.annotation.XmlElement;
import org.opengis.filter.expression.Expression;

@XmlElement("PolygonSymbolizer")
public interface PolygonSymbolizer extends Symbolizer {
  @XmlElement("Stroke")
  Stroke getStroke();
  
  @XmlElement("Fill")
  Fill getFill();
  
  @XmlElement("Displacement")
  Displacement getDisplacement();
  
  @XmlElement("PerpendicularOffset")
  Expression getPerpendicularOffset();
  
  Object accept(StyleVisitor paramStyleVisitor, Object paramObject);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\style\PolygonSymbolizer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
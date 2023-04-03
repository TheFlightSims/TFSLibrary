package org.opengis.style;

import org.opengis.annotation.XmlElement;
import org.opengis.filter.expression.Expression;

@XmlElement("Mark")
public interface Mark extends GraphicalSymbol {
  @XmlElement("WellKnownName")
  Expression getWellKnownName();
  
  ExternalMark getExternalMark();
  
  @XmlElement("Fill")
  Fill getFill();
  
  @XmlElement("Stroke")
  Stroke getStroke();
  
  Object accept(StyleVisitor paramStyleVisitor, Object paramObject);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\style\Mark.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
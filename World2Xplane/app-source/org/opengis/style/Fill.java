package org.opengis.style;

import org.opengis.annotation.XmlElement;
import org.opengis.annotation.XmlParameter;
import org.opengis.filter.expression.Expression;

@XmlElement("Fill")
public interface Fill {
  @XmlElement("GraphicFill")
  GraphicFill getGraphicFill();
  
  @XmlParameter("Fill")
  Expression getColor();
  
  @XmlParameter("Opacity")
  Expression getOpacity();
  
  Object accept(StyleVisitor paramStyleVisitor, Object paramObject);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\style\Fill.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
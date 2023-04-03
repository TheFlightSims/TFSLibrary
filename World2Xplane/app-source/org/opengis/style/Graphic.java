package org.opengis.style;

import java.util.List;
import org.opengis.annotation.XmlElement;
import org.opengis.annotation.XmlParameter;
import org.opengis.filter.expression.Expression;

@XmlElement("Graphic")
public interface Graphic {
  @XmlElement("ExternalGraphic,Mark")
  List<GraphicalSymbol> graphicalSymbols();
  
  @XmlParameter("stroke-opacity")
  Expression getOpacity();
  
  @XmlParameter("Size")
  Expression getSize();
  
  @XmlParameter("Rotation")
  Expression getRotation();
  
  @XmlParameter("AnchorPoint")
  AnchorPoint getAnchorPoint();
  
  @XmlParameter("Displacement")
  Displacement getDisplacement();
  
  Object accept(StyleVisitor paramStyleVisitor, Object paramObject);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\style\Graphic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
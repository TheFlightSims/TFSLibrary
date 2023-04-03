package org.opengis.style;

import org.opengis.annotation.XmlElement;
import org.opengis.annotation.XmlParameter;
import org.opengis.filter.expression.Expression;

@XmlElement("Stroke")
public interface Stroke {
  @XmlElement("GraphicFill")
  GraphicFill getGraphicFill();
  
  @XmlElement("GraphicStroke")
  GraphicStroke getGraphicStroke();
  
  @XmlParameter("stroke")
  Expression getColor();
  
  @XmlParameter("stroke-opacity")
  Expression getOpacity();
  
  @XmlParameter("stroke-width")
  Expression getWidth();
  
  @XmlParameter("stroke-linejoin")
  Expression getLineJoin();
  
  @XmlParameter("stroke-linecap")
  Expression getLineCap();
  
  @XmlParameter("stroke-dasharray")
  float[] getDashArray();
  
  @XmlParameter("stroke-dashoffset")
  Expression getDashOffset();
  
  Object accept(StyleVisitor paramStyleVisitor, Object paramObject);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\style\Stroke.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
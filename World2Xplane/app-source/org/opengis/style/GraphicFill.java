package org.opengis.style;

import org.opengis.annotation.XmlElement;

@XmlElement("GraphicFill")
public interface GraphicFill extends Graphic {
  Object accept(StyleVisitor paramStyleVisitor, Object paramObject);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\style\GraphicFill.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
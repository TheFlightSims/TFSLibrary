package org.opengis.style;

import org.opengis.annotation.XmlElement;
import org.opengis.filter.expression.Expression;

@XmlElement("TextSymbolizer")
public interface TextSymbolizer extends Symbolizer {
  @XmlElement("Label")
  Expression getLabel();
  
  @XmlElement("Font")
  Font getFont();
  
  @XmlElement("LabelPlacement")
  LabelPlacement getLabelPlacement();
  
  @XmlElement("Halo")
  Halo getHalo();
  
  @XmlElement("Fill")
  Fill getFill();
  
  Object accept(StyleVisitor paramStyleVisitor, Object paramObject);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\style\TextSymbolizer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
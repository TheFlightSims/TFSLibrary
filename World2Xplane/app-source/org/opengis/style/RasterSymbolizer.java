package org.opengis.style;

import org.opengis.annotation.XmlElement;
import org.opengis.filter.expression.Expression;

@XmlElement("RasterSymbolizer")
public interface RasterSymbolizer extends Symbolizer {
  @XmlElement("Opacity")
  Expression getOpacity();
  
  @XmlElement("ChannelSelection")
  ChannelSelection getChannelSelection();
  
  @XmlElement("OverlapBehavior")
  OverlapBehavior getOverlapBehavior();
  
  @XmlElement("ColorMap")
  ColorMap getColorMap();
  
  @XmlElement("ContrastEnhancement")
  ContrastEnhancement getContrastEnhancement();
  
  @XmlElement("ShadedRelief")
  ShadedRelief getShadedRelief();
  
  @XmlElement("ImageOutline")
  Symbolizer getImageOutline();
  
  Object accept(StyleVisitor paramStyleVisitor, Object paramObject);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\style\RasterSymbolizer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
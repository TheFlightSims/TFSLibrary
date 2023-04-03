package org.geotools.styling;

import org.opengis.filter.expression.Expression;
import org.opengis.style.ChannelSelection;
import org.opengis.style.ColorMap;
import org.opengis.style.ContrastEnhancement;
import org.opengis.style.OverlapBehavior;
import org.opengis.style.RasterSymbolizer;
import org.opengis.style.ShadedRelief;
import org.opengis.style.Symbolizer;

public interface RasterSymbolizer extends RasterSymbolizer, Symbolizer {
  void setOpacity(Expression paramExpression);
  
  void setChannelSelection(ChannelSelection paramChannelSelection);
  
  ChannelSelection getChannelSelection();
  
  void setOverlap(Expression paramExpression);
  
  Expression getOverlap();
  
  void setOverlapBehavior(OverlapBehavior paramOverlapBehavior);
  
  void setColorMap(ColorMap paramColorMap);
  
  ColorMap getColorMap();
  
  void setContrastEnhancement(ContrastEnhancement paramContrastEnhancement);
  
  ContrastEnhancement getContrastEnhancement();
  
  void setShadedRelief(ShadedRelief paramShadedRelief);
  
  ShadedRelief getShadedRelief();
  
  void setImageOutline(Symbolizer paramSymbolizer);
  
  Symbolizer getImageOutline();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\RasterSymbolizer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
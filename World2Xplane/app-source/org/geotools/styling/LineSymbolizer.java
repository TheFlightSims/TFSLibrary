package org.geotools.styling;

import org.opengis.filter.expression.Expression;
import org.opengis.style.LineSymbolizer;
import org.opengis.style.Stroke;

public interface LineSymbolizer extends LineSymbolizer, Symbolizer {
  Stroke getStroke();
  
  void setStroke(Stroke paramStroke);
  
  void setPerpendicularOffset(Expression paramExpression);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\LineSymbolizer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
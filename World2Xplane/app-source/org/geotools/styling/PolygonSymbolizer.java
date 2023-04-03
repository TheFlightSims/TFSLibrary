package org.geotools.styling;

import org.opengis.filter.expression.Expression;
import org.opengis.style.Displacement;
import org.opengis.style.Fill;
import org.opengis.style.PolygonSymbolizer;
import org.opengis.style.Stroke;

public interface PolygonSymbolizer extends PolygonSymbolizer, Symbolizer {
  Fill getFill();
  
  void setFill(Fill paramFill);
  
  Stroke getStroke();
  
  void setStroke(Stroke paramStroke);
  
  void setPerpendicularOffset(Expression paramExpression);
  
  Displacement getDisplacement();
  
  void setDisplacement(Displacement paramDisplacement);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\PolygonSymbolizer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
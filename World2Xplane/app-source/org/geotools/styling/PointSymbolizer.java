package org.geotools.styling;

import org.opengis.style.Graphic;
import org.opengis.style.PointSymbolizer;

public interface PointSymbolizer extends PointSymbolizer, Symbolizer {
  Graphic getGraphic();
  
  void setGraphic(Graphic paramGraphic);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\PointSymbolizer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
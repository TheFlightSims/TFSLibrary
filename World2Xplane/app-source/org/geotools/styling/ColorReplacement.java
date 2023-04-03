package org.geotools.styling;

import org.opengis.filter.expression.Function;
import org.opengis.style.ColorReplacement;

public interface ColorReplacement extends ColorReplacement {
  Function getRecoding();
  
  void setRecoding(Function paramFunction);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\ColorReplacement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
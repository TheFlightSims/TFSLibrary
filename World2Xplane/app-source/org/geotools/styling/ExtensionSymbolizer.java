package org.geotools.styling;

import java.util.Map;
import org.opengis.filter.expression.Expression;
import org.opengis.style.ExtensionSymbolizer;

public interface ExtensionSymbolizer extends ExtensionSymbolizer, Symbolizer {
  String getExtensionName();
  
  void setExtensionName(String paramString);
  
  Map<String, Expression> getParameters();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\ExtensionSymbolizer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
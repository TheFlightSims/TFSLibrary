package org.opengis.style;

import java.util.Map;
import org.opengis.filter.expression.Expression;

public interface ExtensionSymbolizer extends Symbolizer {
  String getExtensionName();
  
  Map<String, Expression> getParameters();
  
  Object accept(StyleVisitor paramStyleVisitor, Object paramObject);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\style\ExtensionSymbolizer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
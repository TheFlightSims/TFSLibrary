package org.geotools.styling;

import java.util.Map;
import javax.measure.quantity.Length;
import javax.measure.unit.Unit;
import org.opengis.filter.expression.Expression;
import org.opengis.style.Description;
import org.opengis.style.Symbolizer;

public interface Symbolizer extends Symbolizer {
  void accept(StyleVisitor paramStyleVisitor);
  
  void setUnitOfMeasure(Unit<Length> paramUnit);
  
  Description getDescription();
  
  void setDescription(Description paramDescription);
  
  void setName(String paramString);
  
  String getGeometryPropertyName();
  
  void setGeometryPropertyName(String paramString);
  
  Expression getGeometry();
  
  void setGeometry(Expression paramExpression);
  
  boolean hasOption(String paramString);
  
  Map<String, String> getOptions();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\Symbolizer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
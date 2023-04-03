package org.geotools.styling;

import org.opengis.filter.expression.Expression;
import org.opengis.style.Fill;
import org.opengis.style.Halo;

public interface Halo extends Halo {
  void setRadius(Expression paramExpression);
  
  Fill getFill();
  
  void setFill(Fill paramFill);
  
  void accept(StyleVisitor paramStyleVisitor);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\Halo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
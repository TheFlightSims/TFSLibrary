package org.geotools.styling;

import org.opengis.filter.expression.Expression;
import org.opengis.style.ShadedRelief;

public interface ShadedRelief extends ShadedRelief {
  void setBrightnessOnly(boolean paramBoolean);
  
  void setReliefFactor(Expression paramExpression);
  
  void accept(StyleVisitor paramStyleVisitor);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\ShadedRelief.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
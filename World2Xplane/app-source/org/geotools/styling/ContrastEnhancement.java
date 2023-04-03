package org.geotools.styling;

import org.opengis.filter.expression.Expression;
import org.opengis.style.ContrastEnhancement;
import org.opengis.style.ContrastMethod;

public interface ContrastEnhancement extends ContrastEnhancement {
  void setMethod(ContrastMethod paramContrastMethod);
  
  void setType(Expression paramExpression);
  
  Expression getType();
  
  void setGammaValue(Expression paramExpression);
  
  Expression getGammaValue();
  
  @Deprecated
  void setNormalize();
  
  @Deprecated
  void setHistogram();
  
  @Deprecated
  void setLogarithmic();
  
  void setExponential();
  
  void accept(StyleVisitor paramStyleVisitor);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\ContrastEnhancement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
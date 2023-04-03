package org.geotools.styling;

import org.opengis.filter.expression.Expression;

public interface OtherText {
  String getTarget();
  
  void setTarget(String paramString);
  
  Expression getText();
  
  void setText(Expression paramExpression);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\OtherText.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
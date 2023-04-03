package org.geotools.styling;

import org.opengis.filter.expression.Expression;

public interface ColorMapEntry {
  String getLabel();
  
  void setLabel(String paramString);
  
  void setColor(Expression paramExpression);
  
  Expression getColor();
  
  void setOpacity(Expression paramExpression);
  
  Expression getOpacity();
  
  void setQuantity(Expression paramExpression);
  
  Expression getQuantity();
  
  void accept(StyleVisitor paramStyleVisitor);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\ColorMapEntry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
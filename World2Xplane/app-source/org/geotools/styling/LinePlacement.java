package org.geotools.styling;

import org.opengis.filter.expression.Expression;
import org.opengis.style.LinePlacement;

public interface LinePlacement extends LinePlacement, LabelPlacement {
  boolean IsAligned();
  
  boolean isAligned();
  
  Expression getPerpendicularOffset();
  
  void setPerpendicularOffset(Expression paramExpression);
  
  void setRepeated(boolean paramBoolean);
  
  void setGeneralized(boolean paramBoolean);
  
  void setAligned(boolean paramBoolean);
  
  void setGap(Expression paramExpression);
  
  void setInitialGap(Expression paramExpression);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\LinePlacement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
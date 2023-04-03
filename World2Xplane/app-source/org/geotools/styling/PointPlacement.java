package org.geotools.styling;

import org.opengis.filter.expression.Expression;
import org.opengis.style.AnchorPoint;
import org.opengis.style.Displacement;
import org.opengis.style.PointPlacement;

public interface PointPlacement extends PointPlacement, LabelPlacement {
  AnchorPoint getAnchorPoint();
  
  void setAnchorPoint(AnchorPoint paramAnchorPoint);
  
  Displacement getDisplacement();
  
  void setDisplacement(Displacement paramDisplacement);
  
  Expression getRotation();
  
  void setRotation(Expression paramExpression);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\PointPlacement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
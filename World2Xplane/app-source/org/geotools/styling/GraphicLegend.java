package org.geotools.styling;

import java.util.List;
import org.opengis.filter.expression.Expression;
import org.opengis.style.AnchorPoint;
import org.opengis.style.Displacement;
import org.opengis.style.GraphicLegend;
import org.opengis.style.GraphicalSymbol;

public interface GraphicLegend extends GraphicLegend {
  AnchorPoint getAnchorPoint();
  
  void setAnchorPoint(AnchorPoint paramAnchorPoint);
  
  Displacement getDisplacement();
  
  void setDisplacement(Displacement paramDisplacement);
  
  Expression getOpacity();
  
  void setOpacity(Expression paramExpression);
  
  Expression getRotation();
  
  void setRotation(Expression paramExpression);
  
  Expression getSize();
  
  void setSize(Expression paramExpression);
  
  List<GraphicalSymbol> graphicalSymbols();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\GraphicLegend.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
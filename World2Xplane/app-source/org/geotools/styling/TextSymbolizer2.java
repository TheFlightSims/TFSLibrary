package org.geotools.styling;

import org.opengis.filter.expression.Expression;

public interface TextSymbolizer2 extends TextSymbolizer {
  Graphic getGraphic();
  
  void setGraphic(Graphic paramGraphic);
  
  Expression getSnippet();
  
  void setSnippet(Expression paramExpression);
  
  Expression getFeatureDescription();
  
  void setFeatureDescription(Expression paramExpression);
  
  OtherText getOtherText();
  
  void setOtherText(OtherText paramOtherText);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\TextSymbolizer2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
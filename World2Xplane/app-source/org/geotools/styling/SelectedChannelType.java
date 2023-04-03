package org.geotools.styling;

import org.opengis.filter.expression.Expression;
import org.opengis.style.ContrastEnhancement;
import org.opengis.style.SelectedChannelType;

public interface SelectedChannelType extends SelectedChannelType {
  void setChannelName(String paramString);
  
  String getChannelName();
  
  void setContrastEnhancement(Expression paramExpression);
  
  void setContrastEnhancement(ContrastEnhancement paramContrastEnhancement);
  
  ContrastEnhancement getContrastEnhancement();
  
  void accept(StyleVisitor paramStyleVisitor);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\SelectedChannelType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
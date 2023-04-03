package org.geotools.styling;

import java.util.List;
import org.opengis.filter.expression.Expression;
import org.opengis.style.Font;

public interface Font extends Font {
  public static final int DEFAULT_FONTSIZE = 10;
  
  Expression getFontFamily();
  
  List<Expression> getFamily();
  
  void setFontFamily(Expression paramExpression);
  
  Expression getStyle();
  
  void setStyle(Expression paramExpression);
  
  Expression getWeight();
  
  void setWeight(Expression paramExpression);
  
  Expression getSize();
  
  void setSize(Expression paramExpression);
  
  Expression getFontStyle();
  
  void setFontStyle(Expression paramExpression);
  
  Expression getFontWeight();
  
  void setFontWeight(Expression paramExpression);
  
  Expression getFontSize();
  
  void setFontSize(Expression paramExpression);
  
  public static interface Weight {
    public static final String NORMAL = "normal";
    
    public static final String BOLD = "bold";
  }
  
  public static interface Style {
    public static final String NORMAL = "normal";
    
    public static final String ITALIC = "italic";
    
    public static final String OBLIQUE = "oblique";
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\Font.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
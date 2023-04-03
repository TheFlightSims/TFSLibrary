package org.geotools.styling;

import java.util.List;
import org.opengis.style.Style;

public interface Style extends Style {
  void setName(String paramString);
  
  Description getDescription();
  
  String getTitle();
  
  void setTitle(String paramString);
  
  String getAbstract();
  
  void setAbstract(String paramString);
  
  void setDefault(boolean paramBoolean);
  
  List<FeatureTypeStyle> featureTypeStyles();
  
  Symbolizer getDefaultSpecification();
  
  void setDefaultSpecification(Symbolizer paramSymbolizer);
  
  FeatureTypeStyle[] getFeatureTypeStyles();
  
  void setFeatureTypeStyles(FeatureTypeStyle[] paramArrayOfFeatureTypeStyle);
  
  void addFeatureTypeStyle(FeatureTypeStyle paramFeatureTypeStyle);
  
  void accept(StyleVisitor paramStyleVisitor);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\Style.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
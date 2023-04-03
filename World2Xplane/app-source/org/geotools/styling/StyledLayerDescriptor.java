package org.geotools.styling;

import java.util.List;

public interface StyledLayerDescriptor {
  StyledLayer[] getStyledLayers();
  
  void setStyledLayers(StyledLayer[] paramArrayOfStyledLayer);
  
  void addStyledLayer(StyledLayer paramStyledLayer);
  
  List<StyledLayer> layers();
  
  String getName();
  
  void setName(String paramString);
  
  String getTitle();
  
  void setTitle(String paramString);
  
  String getAbstract();
  
  void setAbstract(String paramString);
  
  void accept(StyleVisitor paramStyleVisitor);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\StyledLayerDescriptor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
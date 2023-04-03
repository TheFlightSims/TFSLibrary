package org.geotools.styling;

import java.util.List;

public interface NamedLayer extends StyledLayer {
  List<FeatureTypeConstraint> layerFeatureConstraints();
  
  FeatureTypeConstraint[] getLayerFeatureConstraints();
  
  void setLayerFeatureConstraints(FeatureTypeConstraint[] paramArrayOfFeatureTypeConstraint);
  
  List<Style> styles();
  
  Style[] getStyles();
  
  void addStyle(Style paramStyle);
  
  void accept(StyleVisitor paramStyleVisitor);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\NamedLayer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
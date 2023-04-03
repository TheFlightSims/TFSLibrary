package org.geotools.styling;

import java.util.List;
import org.geotools.data.DataStore;
import org.opengis.feature.simple.SimpleFeatureType;

public interface UserLayer extends StyledLayer {
  RemoteOWS getRemoteOWS();
  
  DataStore getInlineFeatureDatastore();
  
  SimpleFeatureType getInlineFeatureType();
  
  void setInlineFeatureDatastore(DataStore paramDataStore);
  
  void setInlineFeatureType(SimpleFeatureType paramSimpleFeatureType);
  
  void setRemoteOWS(RemoteOWS paramRemoteOWS);
  
  List<FeatureTypeConstraint> layerFeatureConstraints();
  
  FeatureTypeConstraint[] getLayerFeatureConstraints();
  
  void setLayerFeatureConstraints(FeatureTypeConstraint[] paramArrayOfFeatureTypeConstraint);
  
  List<Style> userStyles();
  
  Style[] getUserStyles();
  
  void setUserStyles(Style[] paramArrayOfStyle);
  
  void addUserStyle(Style paramStyle);
  
  void accept(StyleVisitor paramStyleVisitor);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\UserLayer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package org.opengis.feature;

import org.opengis.feature.type.FeatureType;
import org.opengis.filter.identity.FeatureId;
import org.opengis.geometry.BoundingBox;

public interface Feature extends ComplexAttribute {
  FeatureType getType();
  
  FeatureId getIdentifier();
  
  BoundingBox getBounds();
  
  GeometryAttribute getDefaultGeometryProperty();
  
  void setDefaultGeometryProperty(GeometryAttribute paramGeometryAttribute);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\feature\Feature.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
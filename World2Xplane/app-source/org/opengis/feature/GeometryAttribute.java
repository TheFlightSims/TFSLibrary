package org.opengis.feature;

import org.opengis.feature.type.GeometryDescriptor;
import org.opengis.feature.type.GeometryType;
import org.opengis.geometry.BoundingBox;

public interface GeometryAttribute extends Attribute {
  GeometryType getType();
  
  GeometryDescriptor getDescriptor();
  
  BoundingBox getBounds();
  
  void setBounds(BoundingBox paramBoundingBox);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\feature\GeometryAttribute.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package org.opengis.feature.type;

import org.opengis.referencing.crs.CoordinateReferenceSystem;

public interface GeometryDescriptor extends AttributeDescriptor {
  GeometryType getType();
  
  CoordinateReferenceSystem getCoordinateReferenceSystem();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\feature\type\GeometryDescriptor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package org.opengis.geometry;

import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.TransformException;

public interface BoundingBox3D extends BoundingBox {
  double getMinZ();
  
  double getMaxZ();
  
  void include(double paramDouble1, double paramDouble2, double paramDouble3);
  
  boolean contains(double paramDouble1, double paramDouble2, double paramDouble3);
  
  BoundingBox toBounds(CoordinateReferenceSystem paramCoordinateReferenceSystem) throws TransformException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\BoundingBox3D.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
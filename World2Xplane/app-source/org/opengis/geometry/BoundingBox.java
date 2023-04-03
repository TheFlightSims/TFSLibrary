package org.opengis.geometry;

import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.TransformException;

public interface BoundingBox extends Envelope {
  void setBounds(BoundingBox paramBoundingBox);
  
  double getMinX();
  
  double getMaxX();
  
  double getMinY();
  
  double getMaxY();
  
  double getWidth();
  
  double getHeight();
  
  boolean isEmpty();
  
  void include(BoundingBox paramBoundingBox);
  
  void include(double paramDouble1, double paramDouble2);
  
  boolean intersects(BoundingBox paramBoundingBox);
  
  boolean contains(BoundingBox paramBoundingBox);
  
  boolean contains(DirectPosition paramDirectPosition);
  
  boolean contains(double paramDouble1, double paramDouble2);
  
  BoundingBox toBounds(CoordinateReferenceSystem paramCoordinateReferenceSystem) throws TransformException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\BoundingBox.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
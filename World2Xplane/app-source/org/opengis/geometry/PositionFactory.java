package org.opengis.geometry;

import org.opengis.geometry.coordinate.PointArray;
import org.opengis.geometry.coordinate.Position;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

public interface PositionFactory {
  CoordinateReferenceSystem getCoordinateReferenceSystem();
  
  Precision getPrecision();
  
  DirectPosition createDirectPosition(double[] paramArrayOfdouble) throws MismatchedDimensionException;
  
  Position createPosition(Position paramPosition);
  
  PointArray createPointArray();
  
  PointArray createPointArray(double[] paramArrayOfdouble, int paramInt1, int paramInt2);
  
  PointArray createPointArray(float[] paramArrayOffloat, int paramInt1, int paramInt2);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\PositionFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package org.opengis.geometry.coordinate;

import java.util.List;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.geometry.DirectPosition;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

@UML(identifier = "GM_PointArray", specification = Specification.ISO_19107)
public interface PointArray extends List<Position> {
  int getDimension();
  
  CoordinateReferenceSystem getCoordinateReferenceSystem();
  
  DirectPosition getDirectPosition(int paramInt, DirectPosition paramDirectPosition) throws IndexOutOfBoundsException;
  
  void setDirectPosition(int paramInt, DirectPosition paramDirectPosition) throws IndexOutOfBoundsException, UnsupportedOperationException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\coordinate\PointArray.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
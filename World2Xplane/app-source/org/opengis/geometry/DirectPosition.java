package org.opengis.geometry;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.geometry.coordinate.Position;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

@UML(identifier = "DirectPosition", specification = Specification.ISO_19107)
public interface DirectPosition extends Position {
  @UML(identifier = "coordinateReferenceSystem", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  CoordinateReferenceSystem getCoordinateReferenceSystem();
  
  @UML(identifier = "dimension", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  int getDimension();
  
  @UML(identifier = "coordinate", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  double[] getCoordinate();
  
  double getOrdinate(int paramInt) throws IndexOutOfBoundsException;
  
  void setOrdinate(int paramInt, double paramDouble) throws IndexOutOfBoundsException, UnsupportedOperationException;
  
  boolean equals(Object paramObject);
  
  int hashCode();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\DirectPosition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
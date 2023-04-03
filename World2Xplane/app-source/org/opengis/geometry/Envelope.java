package org.opengis.geometry;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

@UML(identifier = "GM_Envelope", specification = Specification.ISO_19107)
public interface Envelope {
  CoordinateReferenceSystem getCoordinateReferenceSystem();
  
  int getDimension();
  
  @UML(identifier = "lowerCorner", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  DirectPosition getLowerCorner();
  
  @UML(identifier = "upperCorner", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  DirectPosition getUpperCorner();
  
  double getMinimum(int paramInt) throws IndexOutOfBoundsException;
  
  double getMaximum(int paramInt) throws IndexOutOfBoundsException;
  
  double getMedian(int paramInt) throws IndexOutOfBoundsException;
  
  double getSpan(int paramInt) throws IndexOutOfBoundsException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\Envelope.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
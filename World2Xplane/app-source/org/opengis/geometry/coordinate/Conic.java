package org.opengis.geometry.coordinate;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.geometry.primitive.CurveSegment;

@UML(identifier = "GM_Conic", specification = Specification.ISO_19107)
public interface Conic extends CurveSegment {
  @UML(identifier = "position", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  AffinePlacement getPosition();
  
  @UML(identifier = "shifted", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  boolean isShifted();
  
  @UML(identifier = "eccentricity", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  double getEccentricity();
  
  @UML(identifier = "semiLatusRectum", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  double getSemiLatusRectum();
  
  @UML(identifier = "startConstrParam", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  double getStartConstructiveParam();
  
  @UML(identifier = "endConstrParam", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  double getEndConstructiveParam();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\coordinate\Conic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
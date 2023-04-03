package org.opengis.geometry.coordinate;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.geometry.primitive.CurveSegment;

@UML(identifier = "GM_Clothoid", specification = Specification.ISO_19107)
public interface Clothoid extends CurveSegment {
  @UML(identifier = "refLocation", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  AffinePlacement getReferenceLocation();
  
  @UML(identifier = "scaleFactor", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  double getScaleFactor();
  
  @UML(identifier = "startParameter", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  double getStartConstructiveParam();
  
  @UML(identifier = "endParameter", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  double getEndConstructiveParam();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\coordinate\Clothoid.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
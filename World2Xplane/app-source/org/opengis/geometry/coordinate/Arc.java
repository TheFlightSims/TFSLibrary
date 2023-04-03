package org.opengis.geometry.coordinate;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.primitive.Bearing;

@UML(identifier = "GM_Arc", specification = Specification.ISO_19107)
public interface Arc extends ArcString {
  @UML(identifier = "center", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  DirectPosition getCenter();
  
  @UML(identifier = "radius", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  double getRadius();
  
  @UML(identifier = "startAngle", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  Bearing getStartAngle();
  
  @UML(identifier = "endAngle", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  Bearing getEndAngle();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\coordinate\Arc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
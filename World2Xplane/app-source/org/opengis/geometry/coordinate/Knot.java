package org.opengis.geometry.coordinate;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "GM_Knot", specification = Specification.ISO_19107)
public interface Knot {
  @UML(identifier = "value", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  double getValue();
  
  @UML(identifier = "multiplicity", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  int getMultiplicity();
  
  @UML(identifier = "weight", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  double getWeight();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\coordinate\Knot.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
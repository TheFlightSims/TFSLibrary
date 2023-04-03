package org.opengis.geometry.coordinate;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "GM_AffinePlacement", specification = Specification.ISO_19107)
public interface AffinePlacement extends Placement {
  @UML(identifier = "location", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  Position getLocation();
  
  @UML(identifier = "refDirection", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  double[] getReferenceDirection(int paramInt);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\coordinate\AffinePlacement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
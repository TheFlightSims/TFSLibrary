package org.opengis.geometry.coordinate;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.geometry.DirectPosition;

@UML(identifier = "GM_Position", specification = Specification.ISO_19107)
public interface Position {
  @UML(identifier = "direct", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19107)
  DirectPosition getDirectPosition();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\coordinate\Position.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
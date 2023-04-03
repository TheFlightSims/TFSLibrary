package org.opengis.geometry;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.geometry.complex.Complex;

@UML(identifier = "GM_Boundary", specification = Specification.ISO_19107)
public interface Boundary extends Complex {
  @UML(identifier = "isCycle", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  boolean isCycle();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\Boundary.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
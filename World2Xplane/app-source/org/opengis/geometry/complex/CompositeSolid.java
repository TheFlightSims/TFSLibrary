package org.opengis.geometry.complex;

import java.util.Set;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.geometry.primitive.Solid;

@UML(identifier = "GM_CompositeSurface", specification = Specification.ISO_19107)
public interface CompositeSolid extends Composite, Solid {
  @UML(identifier = "generator", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  Set<Solid> getGenerators();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\complex\CompositeSolid.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
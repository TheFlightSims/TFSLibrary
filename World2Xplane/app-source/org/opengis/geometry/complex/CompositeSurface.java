package org.opengis.geometry.complex;

import java.util.Set;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.geometry.primitive.OrientableSurface;

@UML(identifier = "GM_CompositeSurface", specification = Specification.ISO_19107)
public interface CompositeSurface extends Composite, OrientableSurface {
  @UML(identifier = "generator", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  Set<OrientableSurface> getGenerators();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\complex\CompositeSurface.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package org.opengis.geometry.complex;

import java.util.List;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.geometry.primitive.OrientableCurve;

@UML(identifier = "GM_CompositeCurve", specification = Specification.ISO_19107)
public interface CompositeCurve extends Composite, OrientableCurve {
  @UML(identifier = "generator", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  List<OrientableCurve> getGenerators();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\complex\CompositeCurve.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
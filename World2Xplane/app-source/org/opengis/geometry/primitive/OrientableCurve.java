package org.opengis.geometry.primitive;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.geometry.complex.CompositeCurve;

@UML(identifier = "GM_OrientableCurve", specification = Specification.ISO_19107)
public interface OrientableCurve extends OrientablePrimitive {
  @UML(identifier = "boundary", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  CurveBoundary getBoundary();
  
  @UML(identifier = "primitive", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19107)
  Curve getPrimitive();
  
  @UML(identifier = "composite", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19107)
  CompositeCurve getComposite();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\primitive\OrientableCurve.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
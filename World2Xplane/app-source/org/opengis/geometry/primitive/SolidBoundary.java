package org.opengis.geometry.primitive;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "GM_SolidBoundary", specification = Specification.ISO_19107)
public interface SolidBoundary extends PrimitiveBoundary {
  @UML(identifier = "exterior", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  Shell getExterior();
  
  @UML(identifier = "interior", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  Shell[] getInteriors();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\primitive\SolidBoundary.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
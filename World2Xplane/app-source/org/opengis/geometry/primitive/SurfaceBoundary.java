package org.opengis.geometry.primitive;

import java.util.List;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "GM_SurfaceBoundary", specification = Specification.ISO_19107)
public interface SurfaceBoundary extends PrimitiveBoundary {
  @UML(identifier = "exterior", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  Ring getExterior();
  
  @UML(identifier = "interior", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  List<Ring> getInteriors();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\primitive\SurfaceBoundary.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
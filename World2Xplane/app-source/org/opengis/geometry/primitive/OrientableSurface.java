package org.opengis.geometry.primitive;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.geometry.complex.CompositeSurface;

@UML(identifier = "GM_OrientableSurface", specification = Specification.ISO_19107)
public interface OrientableSurface extends OrientablePrimitive {
  @UML(identifier = "boundary", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  SurfaceBoundary getBoundary();
  
  @UML(identifier = "primitive", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19107)
  Surface getPrimitive();
  
  @UML(identifier = "composite", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19107)
  CompositeSurface getComposite();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\primitive\OrientableSurface.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
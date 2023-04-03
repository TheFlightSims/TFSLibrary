package org.opengis.geometry.primitive;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.geometry.coordinate.GenericSurface;

@UML(identifier = "GM_SurfacePatch", specification = Specification.ISO_19107)
public interface SurfacePatch extends GenericSurface {
  @UML(identifier = "surface", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19107)
  Surface getSurface();
  
  @UML(identifier = "interpolation", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  SurfaceInterpolation getInterpolation();
  
  @UML(identifier = "numDerivativesOnBoundary", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  int getNumDerivativesOnBoundary();
  
  @UML(identifier = "boundary", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  SurfaceBoundary getBoundary();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\primitive\SurfacePatch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
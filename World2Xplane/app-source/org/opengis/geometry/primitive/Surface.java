package org.opengis.geometry.primitive;

import java.util.List;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.geometry.coordinate.GenericSurface;

@UML(identifier = "GM_Surface", specification = Specification.ISO_19107)
public interface Surface extends OrientableSurface, GenericSurface {
  @UML(identifier = "patch", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  List<? extends SurfacePatch> getPatches();
  
  @UML(identifier = "proxy", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  OrientableSurface[] getProxy();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\primitive\Surface.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package org.opengis.geometry.coordinate;

import java.util.List;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.geometry.primitive.Surface;
import org.opengis.geometry.primitive.SurfaceBoundary;
import org.opengis.geometry.primitive.SurfacePatch;

@UML(identifier = "GM_Polygon", specification = Specification.ISO_19107)
public interface Polygon extends SurfacePatch {
  @UML(identifier = "boundary", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  SurfaceBoundary getBoundary();
  
  @UML(identifier = "spanningSurface", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19107)
  List<Surface> getSpanningSurface();
  
  @UML(identifier = "surface", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  PolyhedralSurface getSurface();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\coordinate\Polygon.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
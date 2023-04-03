package org.opengis.geometry.coordinate;

import java.util.List;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.geometry.primitive.Surface;

@UML(identifier = "GM_PolyhedralSurface", specification = Specification.ISO_19107)
public interface PolyhedralSurface extends Surface {
  @UML(identifier = "patch", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  List<? extends Polygon> getPatches();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\coordinate\PolyhedralSurface.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
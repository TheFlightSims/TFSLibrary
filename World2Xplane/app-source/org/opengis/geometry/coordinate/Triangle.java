package org.opengis.geometry.coordinate;

import java.util.List;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "GM_Triangle", specification = Specification.ISO_19107)
public interface Triangle extends Polygon {
  @UML(identifier = "corners", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  List<Position> getCorners();
  
  @UML(identifier = "surface", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  TriangulatedSurface getSurface();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\coordinate\Triangle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
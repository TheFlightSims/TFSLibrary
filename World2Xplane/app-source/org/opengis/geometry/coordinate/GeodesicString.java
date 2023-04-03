package org.opengis.geometry.coordinate;

import java.util.List;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.geometry.primitive.CurveInterpolation;
import org.opengis.geometry.primitive.CurveSegment;

@UML(identifier = "GM_GeodesicString", specification = Specification.ISO_19107)
public interface GeodesicString extends CurveSegment {
  @UML(identifier = "controlPoint", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  PointArray getControlPoints();
  
  @UML(identifier = "interpolation", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  CurveInterpolation getInterpolation();
  
  @UML(identifier = "asGM_Geodesic", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  List<Geodesic> asGeodesics();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\coordinate\GeodesicString.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
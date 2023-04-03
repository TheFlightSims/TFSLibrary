package org.opengis.geometry.coordinate;

import java.util.List;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.geometry.primitive.CurveSegment;

@UML(identifier = "GM_SplineCurve", specification = Specification.ISO_19107)
public interface SplineCurve extends CurveSegment {
  @UML(identifier = "knot", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  List<Knot> getKnots();
  
  @UML(identifier = "degree", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  int getDegree();
  
  @UML(identifier = "controlPoints", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  PointArray getControlPoints();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\coordinate\SplineCurve.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
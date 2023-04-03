package org.opengis.geometry.coordinate;

import java.util.List;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.geometry.primitive.CurveInterpolation;
import org.opengis.geometry.primitive.CurveSegment;

@UML(identifier = "GM_ArcString", specification = Specification.ISO_19107)
public interface ArcString extends CurveSegment {
  @UML(identifier = "numArc", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  int getNumArc();
  
  @UML(identifier = "controlPoints", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  PointArray getControlPoints();
  
  @UML(identifier = "interpolation", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  CurveInterpolation getInterpolation();
  
  @UML(identifier = "asGM_Arc", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  List<Arc> asArcs();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\coordinate\ArcString.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
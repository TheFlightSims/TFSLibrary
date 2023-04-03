package org.opengis.geometry.coordinate;

import java.util.List;
import java.util.Set;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "GM_Tin", specification = Specification.ISO_19107)
public interface Tin extends TriangulatedSurface {
  @UML(identifier = "stopLines", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  Set<LineString> getStopLines();
  
  @UML(identifier = "breakLines", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  Set<LineString> getBreakLines();
  
  @UML(identifier = "maxLength", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  double getMaxLength();
  
  @UML(identifier = "controlPoint", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  List<Position> getControlPoints();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\coordinate\Tin.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
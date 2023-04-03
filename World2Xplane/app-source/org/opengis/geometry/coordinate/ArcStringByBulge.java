package org.opengis.geometry.coordinate;

import java.util.List;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.geometry.primitive.CurveInterpolation;
import org.opengis.geometry.primitive.CurveSegment;

@UML(identifier = "GM_ArcStringByBulge", specification = Specification.ISO_19107)
public interface ArcStringByBulge extends CurveSegment {
  @UML(identifier = "bulge", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  double[] getBulges();
  
  @UML(identifier = "numArc", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  int getNumArc();
  
  @UML(identifier = "normal", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  List<double[]> getNormals();
  
  @UML(identifier = "interpolation", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  CurveInterpolation getInterpolation();
  
  @UML(identifier = "asGM_ArcString", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  ArcString asArcString();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\coordinate\ArcStringByBulge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
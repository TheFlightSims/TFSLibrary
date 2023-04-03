package org.opengis.geometry.coordinate;

import java.util.List;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.geometry.primitive.CurveInterpolation;

@UML(identifier = "GM_PolynomialSpline", specification = Specification.ISO_19107)
public interface PolynomialSpline extends SplineCurve {
  @UML(identifier = "interpolation", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  CurveInterpolation getInterpolation();
  
  @UML(identifier = "vectorAtStart", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  List getVectorAtStart();
  
  @UML(identifier = "vectorAtEnd", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  List getVectorAtEnd();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\coordinate\PolynomialSpline.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
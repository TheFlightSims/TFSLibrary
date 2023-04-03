package org.opengis.geometry.coordinate;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.primitive.Curve;
import org.opengis.geometry.primitive.CurveInterpolation;
import org.opengis.geometry.primitive.SurfacePatch;

@UML(identifier = "GM_ParametricCurveSurface", specification = Specification.ISO_19107)
public interface ParametricCurveSurface extends SurfacePatch {
  @UML(identifier = "horizontalCurveType", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  CurveInterpolation getHorizontalCurveType();
  
  @UML(identifier = "verticalCurveType", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  CurveInterpolation getVerticalCurveType();
  
  @UML(identifier = "horizontalCurve", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  Curve horizontalCurve(double paramDouble);
  
  @UML(identifier = "verticalCurve", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  Curve verticalCurve(double paramDouble);
  
  @UML(identifier = "surface", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  DirectPosition surface(double paramDouble1, double paramDouble2);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\coordinate\ParametricCurveSurface.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
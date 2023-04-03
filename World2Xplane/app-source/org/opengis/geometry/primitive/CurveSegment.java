package org.opengis.geometry.primitive;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.geometry.coordinate.GenericCurve;
import org.opengis.geometry.coordinate.PointArray;

@UML(identifier = "GM_CurveSegment", specification = Specification.ISO_19107)
public interface CurveSegment extends GenericCurve {
  @UML(identifier = "curve", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19107)
  Curve getCurve();
  
  @UML(identifier = "interpolation", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  CurveInterpolation getInterpolation();
  
  @UML(identifier = "numDerivativesAtStart", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  int getNumDerivativesAtStart();
  
  @UML(identifier = "numDerivativesInterior", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  int getNumDerivativesInterior();
  
  @UML(identifier = "numDerivativesAtEnd", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  int getNumDerivativesAtEnd();
  
  @UML(identifier = "samplePoint", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  PointArray getSamplePoints();
  
  @UML(identifier = "boundary", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  CurveBoundary getBoundary();
  
  @UML(identifier = "reverse", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  CurveSegment reverse();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\primitive\CurveSegment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
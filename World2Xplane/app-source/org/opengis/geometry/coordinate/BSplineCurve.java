package org.opengis.geometry.coordinate;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "GM_BSplineCurve", specification = Specification.ISO_19107)
public interface BSplineCurve extends SplineCurve {
  @UML(identifier = "degree", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  int getDegree();
  
  @UML(identifier = "curveForm", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19107)
  SplineCurveForm getCurveForm();
  
  @UML(identifier = "knotSpec", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19107)
  KnotType getKnotSpec();
  
  @UML(identifier = "isPolynomial", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  boolean isPolynomial();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\coordinate\BSplineCurve.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
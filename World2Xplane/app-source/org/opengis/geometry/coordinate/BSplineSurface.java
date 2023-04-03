package org.opengis.geometry.coordinate;

import java.util.List;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "GM_BSplineSurface", specification = Specification.ISO_19107)
public interface BSplineSurface extends GriddedSurface {
  @UML(identifier = "degree", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  int[] getDegrees();
  
  @UML(identifier = "surfaceForm", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19107)
  BSplineSurfaceForm getSurfaceForm();
  
  @UML(identifier = "knot", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  List<Knot>[] getKnots();
  
  @UML(identifier = "knotSpec", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19107)
  KnotType getKnotSpec();
  
  @UML(identifier = "isPolynomial", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  boolean isPolynomial();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\coordinate\BSplineSurface.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
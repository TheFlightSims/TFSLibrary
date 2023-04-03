package org.opengis.geometry.primitive;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "GM_CurveBoundary", specification = Specification.ISO_19107)
public interface CurveBoundary extends PrimitiveBoundary {
  @UML(identifier = "startPoint", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  Point getStartPoint();
  
  @UML(identifier = "endPoint", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  Point getEndPoint();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\primitive\CurveBoundary.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
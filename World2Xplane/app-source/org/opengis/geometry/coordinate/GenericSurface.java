package org.opengis.geometry.coordinate;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.geometry.DirectPosition;

@UML(identifier = "GM_GenericSurface", specification = Specification.ISO_19107)
public interface GenericSurface {
  @UML(identifier = "upNormal", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  double[] getUpNormal(DirectPosition paramDirectPosition);
  
  @UML(identifier = "perimeter", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  double getPerimeter();
  
  @UML(identifier = "area", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  double getArea();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\coordinate\GenericSurface.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
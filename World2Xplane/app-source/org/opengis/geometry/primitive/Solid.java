package org.opengis.geometry.primitive;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "GM_Solid", specification = Specification.ISO_19107)
public interface Solid extends Primitive {
  @UML(identifier = "boundary", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  SolidBoundary getBoundary();
  
  @UML(identifier = "area", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  double getArea();
  
  @UML(identifier = "volume", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  double getVolume();
  
  @UML(identifier = "proxy", obligation = Obligation.FORBIDDEN, specification = Specification.ISO_19107)
  OrientablePrimitive[] getProxy();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\primitive\Solid.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package org.opengis.geometry.primitive;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "GM_OrientablePrimitive", specification = Specification.ISO_19107)
public interface OrientablePrimitive extends Primitive {
  @UML(identifier = "orientation", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  int getOrientation();
  
  @UML(identifier = "primitive", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19107)
  Primitive getPrimitive();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\primitive\OrientablePrimitive.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
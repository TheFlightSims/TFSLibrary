package org.opengis.geometry.primitive;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "Bearing", specification = Specification.ISO_19107)
public interface Bearing {
  @UML(identifier = "angle", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  double[] getAngles();
  
  @UML(identifier = "direction", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  double[] getDirection();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\primitive\Bearing.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
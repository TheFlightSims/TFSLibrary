package org.opengis.geometry.coordinate;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "GM_Placement", specification = Specification.ISO_19107)
public interface Placement {
  @UML(identifier = "inDimension", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  int getInDimension();
  
  @UML(identifier = "outDimension", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  int getOutDimension();
  
  @UML(identifier = "transform", obligation = Obligation.MANDATORY, specification = Specification.ISO_19107)
  double[] transform(double[] paramArrayOfdouble);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\coordinate\Placement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package org.opengis.geometry.coordinate;

import java.util.Set;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.geometry.DirectPosition;

@UML(identifier = "paramForPoint", specification = Specification.ISO_19107)
public interface ParamForPoint {
  Set<Number> getDistances();
  
  double getDistance();
  
  DirectPosition getPosition();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\coordinate\ParamForPoint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
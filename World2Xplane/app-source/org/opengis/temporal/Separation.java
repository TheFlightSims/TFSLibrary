package org.opengis.temporal;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "TM_Separation", specification = Specification.ISO_19108)
public interface Separation {
  @UML(identifier = "distance", obligation = Obligation.MANDATORY, specification = Specification.ISO_19108)
  Duration distance(TemporalGeometricPrimitive paramTemporalGeometricPrimitive);
  
  @UML(identifier = "length", obligation = Obligation.MANDATORY, specification = Specification.ISO_19108)
  Duration length();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\temporal\Separation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
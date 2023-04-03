package org.opengis.temporal;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "TM_Period", specification = Specification.ISO_19108)
public interface Period extends TemporalGeometricPrimitive {
  @UML(identifier = "Beginning", obligation = Obligation.MANDATORY, specification = Specification.ISO_19108)
  Instant getBeginning();
  
  @UML(identifier = "Ending", obligation = Obligation.MANDATORY, specification = Specification.ISO_19108)
  Instant getEnding();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\temporal\Period.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
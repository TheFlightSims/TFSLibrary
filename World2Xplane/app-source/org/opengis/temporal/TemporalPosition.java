package org.opengis.temporal;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "TM_TemporalPosition", specification = Specification.ISO_19108)
public interface TemporalPosition {
  @UML(identifier = "indeterminatePosition", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19108)
  IndeterminateValue getIndeterminatePosition();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\temporal\TemporalPosition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
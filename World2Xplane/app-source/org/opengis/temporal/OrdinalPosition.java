package org.opengis.temporal;

import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "TM_OrdinalPosition", specification = Specification.ISO_19108)
public interface OrdinalPosition extends TemporalPosition {
  @UML(identifier = "ordinalPosition", specification = Specification.ISO_19108)
  OrdinalEra getOrdinalPosition();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\temporal\OrdinalPosition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
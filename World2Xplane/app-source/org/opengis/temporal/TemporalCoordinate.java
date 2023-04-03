package org.opengis.temporal;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "TM_Coordinate", specification = Specification.ISO_19108)
public interface TemporalCoordinate extends TemporalPosition {
  @UML(identifier = "CoordinateValue", obligation = Obligation.MANDATORY, specification = Specification.ISO_19108)
  Number getCoordinateValue();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\temporal\TemporalCoordinate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
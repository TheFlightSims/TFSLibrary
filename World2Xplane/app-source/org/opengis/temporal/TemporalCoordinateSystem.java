package org.opengis.temporal;

import java.util.Date;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.util.InternationalString;

@UML(identifier = "TM_CoordinateSystem", specification = Specification.ISO_19108)
public interface TemporalCoordinateSystem extends TemporalReferenceSystem {
  @UML(identifier = "origin", obligation = Obligation.MANDATORY, specification = Specification.ISO_19108)
  Date getOrigin();
  
  @UML(identifier = "interval", obligation = Obligation.MANDATORY, specification = Specification.ISO_19108)
  InternationalString getInterval();
  
  @UML(identifier = "transformCoord", obligation = Obligation.MANDATORY, specification = Specification.ISO_19108)
  Date transformCoord(TemporalCoordinate paramTemporalCoordinate);
  
  @UML(identifier = "transformDateTime", obligation = Obligation.MANDATORY, specification = Specification.ISO_19108)
  TemporalCoordinate transformDateTime(Date paramDate);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\temporal\TemporalCoordinateSystem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
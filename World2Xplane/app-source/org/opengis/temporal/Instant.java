package org.opengis.temporal;

import java.util.Collection;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "TM_Instant", specification = Specification.ISO_19108)
public interface Instant extends TemporalGeometricPrimitive {
  @UML(identifier = "position", obligation = Obligation.MANDATORY, specification = Specification.ISO_19108)
  Position getPosition();
  
  @UML(identifier = "begunBy", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19108)
  Collection<Period> getBegunBy();
  
  @UML(identifier = "endedBy", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19108)
  Collection<Period> getEndedBy();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\temporal\Instant.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
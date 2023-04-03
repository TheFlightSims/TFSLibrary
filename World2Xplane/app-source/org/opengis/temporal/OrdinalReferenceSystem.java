package org.opengis.temporal;

import java.util.Collection;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

public interface OrdinalReferenceSystem extends TemporalReferenceSystem {
  @UML(identifier = "structure", obligation = Obligation.MANDATORY, specification = Specification.ISO_19108)
  Collection<OrdinalEra> getOrdinalEraSequence();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\temporal\OrdinalReferenceSystem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package org.opengis.coverage.processing;

import java.util.Collection;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "GP_GridCoverageProcessor", specification = Specification.OGC_01004)
public interface GridCoverageProcessor {
  @UML(identifier = "getOperation", obligation = Obligation.MANDATORY, specification = Specification.OGC_01004)
  Collection<Operation> getOperations();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\coverage\processing\GridCoverageProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
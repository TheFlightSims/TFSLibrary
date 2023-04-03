package org.opengis.metadata.quality;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.metadata.citation.Citation;
import org.opengis.util.InternationalString;

@UML(identifier = "DQ_ConformanceResult", specification = Specification.ISO_19115)
public interface ConformanceResult extends Result {
  @UML(identifier = "specification", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  Citation getSpecification();
  
  @UML(identifier = "explanation", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  InternationalString getExplanation();
  
  @UML(identifier = "pass", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  boolean pass();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\quality\ConformanceResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
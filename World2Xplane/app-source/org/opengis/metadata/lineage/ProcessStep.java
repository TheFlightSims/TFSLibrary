package org.opengis.metadata.lineage;

import java.util.Collection;
import java.util.Date;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.metadata.citation.ResponsibleParty;
import org.opengis.util.InternationalString;

@UML(identifier = "LI_ProcessStep", specification = Specification.ISO_19115)
public interface ProcessStep {
  @UML(identifier = "description", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  InternationalString getDescription();
  
  @UML(identifier = "rationale", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  InternationalString getRationale();
  
  @UML(identifier = "dateTime", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Date getDate();
  
  @UML(identifier = "processor", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Collection<? extends ResponsibleParty> getProcessors();
  
  @UML(identifier = "source", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Collection<? extends Source> getSources();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\lineage\ProcessStep.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
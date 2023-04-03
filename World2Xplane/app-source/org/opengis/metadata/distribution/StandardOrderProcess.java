package org.opengis.metadata.distribution;

import java.util.Date;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.util.InternationalString;

@UML(identifier = "MD_StandardOrderProcess", specification = Specification.ISO_19115)
public interface StandardOrderProcess {
  @UML(identifier = "fees", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  InternationalString getFees();
  
  @UML(identifier = "plannedAvailableDateTime", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Date getPlannedAvailableDateTime();
  
  @UML(identifier = "orderingInstructions", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  InternationalString getOrderingInstructions();
  
  @UML(identifier = "turnaround", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  InternationalString getTurnaround();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\distribution\StandardOrderProcess.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
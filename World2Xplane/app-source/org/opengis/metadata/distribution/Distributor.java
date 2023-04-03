package org.opengis.metadata.distribution;

import java.util.Collection;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.metadata.citation.ResponsibleParty;

@UML(identifier = "MD_Distributor", specification = Specification.ISO_19115)
public interface Distributor {
  @UML(identifier = "distributorContact", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  ResponsibleParty getDistributorContact();
  
  @UML(identifier = "distributionOrderProcess", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Collection<? extends StandardOrderProcess> getDistributionOrderProcesses();
  
  @UML(identifier = "distributorFormat", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
  Collection<? extends Format> getDistributorFormats();
  
  @UML(identifier = "distributorTransferOptions", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Collection<? extends DigitalTransferOptions> getDistributorTransferOptions();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\distribution\Distributor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
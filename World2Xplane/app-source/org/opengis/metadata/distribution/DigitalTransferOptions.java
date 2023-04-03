package org.opengis.metadata.distribution;

import java.util.Collection;
import org.opengis.annotation.ComplianceLevel;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Profile;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.metadata.citation.OnLineResource;
import org.opengis.util.InternationalString;

@UML(identifier = "MD_DigitalTransferOptions", specification = Specification.ISO_19115)
@Profile(level = ComplianceLevel.CORE)
public interface DigitalTransferOptions {
  @UML(identifier = "unitsOfDistribution", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  InternationalString getUnitsOfDistribution();
  
  @UML(identifier = "transferSize", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Double getTransferSize();
  
  @UML(identifier = "onLine", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  @Profile(level = ComplianceLevel.CORE)
  Collection<? extends OnLineResource> getOnLines();
  
  @UML(identifier = "offLine", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Medium getOffLine();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\distribution\DigitalTransferOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
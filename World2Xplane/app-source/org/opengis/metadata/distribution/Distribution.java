package org.opengis.metadata.distribution;

import java.util.Collection;
import org.opengis.annotation.ComplianceLevel;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Profile;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "MD_Distribution", specification = Specification.ISO_19115)
@Profile(level = ComplianceLevel.CORE)
public interface Distribution {
  @UML(identifier = "distributionFormat", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
  @Profile(level = ComplianceLevel.CORE)
  Collection<? extends Format> getDistributionFormats();
  
  @UML(identifier = "distributor", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Collection<? extends Distributor> getDistributors();
  
  @UML(identifier = "transferOptions", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  @Profile(level = ComplianceLevel.CORE)
  Collection<? extends DigitalTransferOptions> getTransferOptions();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\distribution\Distribution.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
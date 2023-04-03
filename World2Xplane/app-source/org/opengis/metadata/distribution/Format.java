package org.opengis.metadata.distribution;

import java.util.Collection;
import org.opengis.annotation.ComplianceLevel;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Profile;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.util.InternationalString;

@UML(identifier = "MD_Format", specification = Specification.ISO_19115)
@Profile(level = ComplianceLevel.CORE)
public interface Format {
  @UML(identifier = "name", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  @Profile(level = ComplianceLevel.CORE)
  InternationalString getName();
  
  @UML(identifier = "version", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  @Profile(level = ComplianceLevel.CORE)
  InternationalString getVersion();
  
  @UML(identifier = "amendmentNumber", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  InternationalString getAmendmentNumber();
  
  @UML(identifier = "specification", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  InternationalString getSpecification();
  
  @UML(identifier = "fileDecompressionTechnique", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  InternationalString getFileDecompressionTechnique();
  
  @UML(identifier = "formatDistributor", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Collection<? extends Distributor> getFormatDistributors();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\distribution\Format.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
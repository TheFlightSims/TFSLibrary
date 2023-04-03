package org.opengis.metadata.identification;

import org.opengis.annotation.ComplianceLevel;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Profile;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "MD_Resolution", specification = Specification.ISO_19115)
@Profile(level = ComplianceLevel.CORE)
public interface Resolution {
  @UML(identifier = "equivalentScale", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
  @Profile(level = ComplianceLevel.CORE)
  RepresentativeFraction getEquivalentScale();
  
  @UML(identifier = "distance", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
  @Profile(level = ComplianceLevel.CORE)
  Double getDistance();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\identification\Resolution.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
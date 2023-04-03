package org.opengis.metadata.citation;

import org.opengis.annotation.ComplianceLevel;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Profile;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.util.InternationalString;

@UML(identifier = "CI_ResponsibleParty", specification = Specification.ISO_19115)
@Profile(level = ComplianceLevel.CORE)
public interface ResponsibleParty {
  @UML(identifier = "individualName", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
  String getIndividualName();
  
  @UML(identifier = "organisationName", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
  InternationalString getOrganisationName();
  
  @UML(identifier = "positionName", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
  InternationalString getPositionName();
  
  @UML(identifier = "contactInfo", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Contact getContactInfo();
  
  @UML(identifier = "role", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  Role getRole();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\citation\ResponsibleParty.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
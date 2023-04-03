package org.opengis.metadata.citation;

import java.net.URI;
import org.opengis.annotation.ComplianceLevel;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Profile;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.util.InternationalString;

@UML(identifier = "CI_OnlineResource", specification = Specification.ISO_19115)
@Profile(level = ComplianceLevel.CORE)
public interface OnLineResource {
  @UML(identifier = "linkage", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  URI getLinkage();
  
  @UML(identifier = "protocol", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  String getProtocol();
  
  @UML(identifier = "applicationProfile", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  String getApplicationProfile();
  
  @UML(identifier = "name", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  String getName();
  
  @UML(identifier = "description", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  InternationalString getDescription();
  
  @UML(identifier = "function", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  OnLineFunction getFunction();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\citation\OnLineResource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
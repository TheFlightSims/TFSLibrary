package org.opengis.metadata.identification;

import java.util.Collection;
import org.opengis.annotation.ComplianceLevel;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Profile;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.metadata.citation.Citation;
import org.opengis.metadata.citation.ResponsibleParty;
import org.opengis.metadata.constraint.Constraints;
import org.opengis.metadata.distribution.Format;
import org.opengis.metadata.maintenance.MaintenanceInformation;
import org.opengis.util.InternationalString;

@UML(identifier = "MD_Identification", specification = Specification.ISO_19115)
@Profile(level = ComplianceLevel.CORE)
public interface Identification {
  @UML(identifier = "citation", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  @Profile(level = ComplianceLevel.CORE)
  Citation getCitation();
  
  @UML(identifier = "abstract", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  @Profile(level = ComplianceLevel.CORE)
  InternationalString getAbstract();
  
  @UML(identifier = "purpose", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  InternationalString getPurpose();
  
  @UML(identifier = "credit", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Collection<String> getCredits();
  
  @UML(identifier = "status", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Collection<Progress> getStatus();
  
  @UML(identifier = "pointOfContact", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  @Profile(level = ComplianceLevel.CORE)
  Collection<? extends ResponsibleParty> getPointOfContacts();
  
  @UML(identifier = "resourceMaintenance", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Collection<? extends MaintenanceInformation> getResourceMaintenance();
  
  @UML(identifier = "graphicOverview", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Collection<? extends BrowseGraphic> getGraphicOverviews();
  
  @UML(identifier = "resourceFormat", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Collection<? extends Format> getResourceFormat();
  
  @UML(identifier = "descriptiveKeywords", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Collection<? extends Keywords> getDescriptiveKeywords();
  
  @UML(identifier = "resourceSpecificUsage", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Collection<? extends Usage> getResourceSpecificUsages();
  
  @UML(identifier = "resourceConstraints", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Collection<? extends Constraints> getResourceConstraints();
  
  @UML(identifier = "aggregationInfo", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Collection<? extends AggregateInformation> getAggregationInfo();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\identification\Identification.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
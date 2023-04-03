package org.opengis.metadata.citation;

import java.util.Collection;
import java.util.Date;
import org.opengis.annotation.ComplianceLevel;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Profile;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.metadata.Identifier;
import org.opengis.util.InternationalString;

@UML(identifier = "CI_Citation", specification = Specification.ISO_19115)
@Profile(level = ComplianceLevel.CORE)
public interface Citation {
  @UML(identifier = "title", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  @Profile(level = ComplianceLevel.CORE)
  InternationalString getTitle();
  
  @UML(identifier = "alternateTitle", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Collection<? extends InternationalString> getAlternateTitles();
  
  @UML(identifier = "date", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  @Profile(level = ComplianceLevel.CORE)
  Collection<? extends CitationDate> getDates();
  
  @UML(identifier = "edition", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  InternationalString getEdition();
  
  @UML(identifier = "editionDate", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Date getEditionDate();
  
  @UML(identifier = "identifier", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Collection<? extends Identifier> getIdentifiers();
  
  @UML(identifier = "citedResponsibleParty", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Collection<? extends ResponsibleParty> getCitedResponsibleParties();
  
  @UML(identifier = "presentationForm", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Collection<PresentationForm> getPresentationForm();
  
  @UML(identifier = "series", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Series getSeries();
  
  @UML(identifier = "otherCitationDetails", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  InternationalString getOtherCitationDetails();
  
  @UML(identifier = "collectiveTitle", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  InternationalString getCollectiveTitle();
  
  @UML(identifier = "ISBN", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  String getISBN();
  
  @UML(identifier = "ISSN", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  String getISSN();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\citation\Citation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
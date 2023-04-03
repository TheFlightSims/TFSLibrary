package org.opengis.metadata;

import java.util.Collection;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.metadata.citation.ResponsibleParty;
import org.opengis.util.InternationalString;

@UML(identifier = "MD_ExtendedElementInformation", specification = Specification.ISO_19115)
public interface ExtendedElementInformation {
  @UML(identifier = "name", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  String getName();
  
  @UML(identifier = "shortName", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
  String getShortName();
  
  @UML(identifier = "domainCode", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
  Integer getDomainCode();
  
  @UML(identifier = "definition", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  InternationalString getDefinition();
  
  @UML(identifier = "obligation", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
  Obligation getObligation();
  
  @UML(identifier = "condition", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
  InternationalString getCondition();
  
  @UML(identifier = "dataType", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  Datatype getDataType();
  
  @UML(identifier = "maximumOccurrence", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
  Integer getMaximumOccurrence();
  
  @UML(identifier = "domainValue", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
  InternationalString getDomainValue();
  
  @UML(identifier = "parentEntity", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  Collection<String> getParentEntity();
  
  @UML(identifier = "rule", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  InternationalString getRule();
  
  @UML(identifier = "rationale", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Collection<? extends InternationalString> getRationales();
  
  @UML(identifier = "source", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  Collection<? extends ResponsibleParty> getSources();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\ExtendedElementInformation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
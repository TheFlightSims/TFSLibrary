package org.opengis.metadata.identification;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.metadata.Identifier;
import org.opengis.metadata.citation.Citation;

@UML(identifier = "MD_AggregateInformation", specification = Specification.ISO_19115)
public interface AggregateInformation {
  @UML(identifier = "aggregateDataSetName", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
  Citation getAggregateDataSetName();
  
  @UML(identifier = "aggregateDataSetIdentifier", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
  Identifier getAggregateDataSetIdentifier();
  
  @UML(identifier = "associationType", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  AssociationType getAssociationType();
  
  @UML(identifier = "initiativeType", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  InitiativeType getInitiativeType();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\identification\AggregateInformation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
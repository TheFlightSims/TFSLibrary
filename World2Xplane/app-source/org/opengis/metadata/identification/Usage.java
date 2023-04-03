package org.opengis.metadata.identification;

import java.util.Collection;
import java.util.Date;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.metadata.citation.ResponsibleParty;
import org.opengis.util.InternationalString;

@UML(identifier = "MD_Usage", specification = Specification.ISO_19115)
public interface Usage {
  @UML(identifier = "specificUsage", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  InternationalString getSpecificUsage();
  
  @UML(identifier = "usageDateTime", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Date getUsageDate();
  
  @UML(identifier = "userDeterminedLimitations", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  InternationalString getUserDeterminedLimitations();
  
  @UML(identifier = "userContactInfo", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  Collection<? extends ResponsibleParty> getUserContactInfo();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\identification\Usage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
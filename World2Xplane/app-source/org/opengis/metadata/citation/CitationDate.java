package org.opengis.metadata.citation;

import java.util.Date;
import org.opengis.annotation.ComplianceLevel;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Profile;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "CI_Date", specification = Specification.ISO_19115)
@Profile(level = ComplianceLevel.CORE)
public interface CitationDate {
  @UML(identifier = "date", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  @Profile(level = ComplianceLevel.CORE)
  Date getDate();
  
  @UML(identifier = "dateType", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  @Profile(level = ComplianceLevel.CORE)
  DateType getDateType();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\citation\CitationDate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
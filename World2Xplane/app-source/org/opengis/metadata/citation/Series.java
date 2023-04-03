package org.opengis.metadata.citation;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.util.InternationalString;

@UML(identifier = "CI_Series", specification = Specification.ISO_19115)
public interface Series {
  @UML(identifier = "name", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  InternationalString getName();
  
  @UML(identifier = "issueIdentification", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  String getIssueIdentification();
  
  @UML(identifier = "page", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  String getPage();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\citation\Series.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
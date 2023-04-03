package org.opengis.metadata.citation;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.util.InternationalString;

@UML(identifier = "CI_Contact", specification = Specification.ISO_19115)
public interface Contact {
  @UML(identifier = "phone", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Telephone getPhone();
  
  @UML(identifier = "address", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Address getAddress();
  
  @UML(identifier = "onLineResource", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  OnLineResource getOnLineResource();
  
  @UML(identifier = "hoursOfService", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  InternationalString getHoursOfService();
  
  @UML(identifier = "contactInstructions", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  InternationalString getContactInstructions();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\citation\Contact.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package org.opengis.metadata.citation;

import java.util.Collection;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.util.InternationalString;

@UML(identifier = "CI_Address", specification = Specification.ISO_19115)
public interface Address {
  @UML(identifier = "deliveryPoint", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Collection<String> getDeliveryPoints();
  
  @UML(identifier = "city", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  InternationalString getCity();
  
  @UML(identifier = "administrativeArea", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  InternationalString getAdministrativeArea();
  
  @UML(identifier = "postalCode", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  String getPostalCode();
  
  @UML(identifier = "country", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  InternationalString getCountry();
  
  @UML(identifier = "electronicMailAddress", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Collection<String> getElectronicMailAddresses();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\citation\Address.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
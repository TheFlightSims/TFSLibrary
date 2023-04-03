package org.opengis.referencing;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.metadata.extent.Extent;
import org.opengis.util.InternationalString;

@UML(identifier = "RS_ReferenceSystem", specification = Specification.ISO_19115)
public interface ReferenceSystem extends IdentifiedObject {
  public static final String DOMAIN_OF_VALIDITY_KEY = "domainOfValidity";
  
  public static final String SCOPE_KEY = "scope";
  
  @UML(identifier = "domainOfValidity", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19111)
  Extent getDomainOfValidity();
  
  @UML(identifier = "SC_CRS.scope", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19111)
  InternationalString getScope();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\referencing\ReferenceSystem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package org.opengis.referencing;

import java.util.Set;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.metadata.citation.Citation;
import org.opengis.util.InternationalString;

@UML(identifier = "CS_CoordinateSystemAuthorityFactory", specification = Specification.OGC_01009)
public interface AuthorityFactory extends Factory {
  @UML(identifier = "getAuthority", specification = Specification.OGC_01009)
  Citation getAuthority();
  
  Set<String> getAuthorityCodes(Class<? extends IdentifiedObject> paramClass) throws FactoryException;
  
  @UML(identifier = "descriptionText", specification = Specification.OGC_01009)
  InternationalString getDescriptionText(String paramString) throws NoSuchAuthorityCodeException, FactoryException;
  
  IdentifiedObject createObject(String paramString) throws NoSuchAuthorityCodeException, FactoryException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\referencing\AuthorityFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
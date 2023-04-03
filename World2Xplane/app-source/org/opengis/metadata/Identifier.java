package org.opengis.metadata;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.metadata.citation.Citation;

@UML(identifier = "MD_Identifier", specification = Specification.ISO_19115)
public interface Identifier {
  public static final String CODE_KEY = "code";
  
  public static final String AUTHORITY_KEY = "authority";
  
  @UML(identifier = "code", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  String getCode();
  
  @UML(identifier = "authority", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Citation getAuthority();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\Identifier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
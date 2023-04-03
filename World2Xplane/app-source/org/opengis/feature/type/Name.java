package org.opengis.feature.type;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

public interface Name {
  boolean isGlobal();
  
  @UML(identifier = "scope", obligation = Obligation.MANDATORY, specification = Specification.ISO_19103)
  String getNamespaceURI();
  
  String getSeparator();
  
  String getLocalPart();
  
  @UML(identifier = "parsedName", obligation = Obligation.MANDATORY, specification = Specification.ISO_19103)
  String getURI();
  
  int hashCode();
  
  boolean equals(Object paramObject);
  
  String toString();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\feature\type\Name.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
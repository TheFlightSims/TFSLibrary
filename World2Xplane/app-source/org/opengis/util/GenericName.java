package org.opengis.util;

import java.util.List;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "GenericName", specification = Specification.ISO_19103)
public interface GenericName extends Comparable<GenericName> {
  @UML(identifier = "scope", obligation = Obligation.MANDATORY, specification = Specification.ISO_19103)
  NameSpace scope();
  
  @UML(identifier = "depth", obligation = Obligation.MANDATORY, specification = Specification.ISO_19103)
  int depth();
  
  @UML(identifier = "parsedName", obligation = Obligation.MANDATORY, specification = Specification.ISO_19103)
  List<? extends LocalName> getParsedNames();
  
  @UML(identifier = "ScopedName.head", obligation = Obligation.MANDATORY, specification = Specification.ISO_19103)
  LocalName head();
  
  LocalName tip();
  
  @Deprecated
  LocalName name();
  
  GenericName toFullyQualifiedName();
  
  @UML(identifier = "push", obligation = Obligation.MANDATORY, specification = Specification.ISO_19103)
  ScopedName push(GenericName paramGenericName);
  
  String toString();
  
  InternationalString toInternationalString();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengi\\util\GenericName.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package org.opengis.util;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "ScopedName", specification = Specification.ISO_19103)
public interface ScopedName extends GenericName {
  @UML(identifier = "head", obligation = Obligation.MANDATORY, specification = Specification.ISO_19103)
  LocalName head();
  
  @UML(identifier = "tail", obligation = Obligation.MANDATORY, specification = Specification.ISO_19103)
  GenericName tail();
  
  GenericName path();
  
  LocalName tip();
  
  @UML(identifier = "scopedName", obligation = Obligation.MANDATORY, specification = Specification.ISO_19103)
  String toString();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengi\\util\ScopedName.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
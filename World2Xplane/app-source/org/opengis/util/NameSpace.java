package org.opengis.util;

import java.util.Set;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "NameSpace", specification = Specification.ISO_19103)
public interface NameSpace {
  @UML(identifier = "isGlobal", obligation = Obligation.MANDATORY, specification = Specification.ISO_19103)
  boolean isGlobal();
  
  @UML(identifier = "name", obligation = Obligation.MANDATORY, specification = Specification.ISO_19103)
  GenericName name();
  
  @Deprecated
  @UML(identifier = "names", obligation = Obligation.MANDATORY, specification = Specification.ISO_19103)
  Set<GenericName> getNames();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengi\\util\NameSpace.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
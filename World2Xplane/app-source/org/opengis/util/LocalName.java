package org.opengis.util;

import java.util.List;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "LocalName", specification = Specification.ISO_19103)
public interface LocalName extends GenericName {
  int depth();
  
  @UML(identifier = "parsedName", obligation = Obligation.MANDATORY, specification = Specification.ISO_19103)
  List<? extends LocalName> getParsedNames();
  
  LocalName head();
  
  LocalName tip();
  
  @UML(identifier = "aName", obligation = Obligation.MANDATORY, specification = Specification.ISO_19103)
  String toString();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengi\\util\LocalName.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
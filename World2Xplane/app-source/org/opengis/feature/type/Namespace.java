package org.opengis.feature.type;

import java.util.Set;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "NameSpace", specification = Specification.ISO_19103)
public interface Namespace extends Set<Name> {
  String getURI();
  
  Name lookup(String paramString);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\feature\type\Namespace.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
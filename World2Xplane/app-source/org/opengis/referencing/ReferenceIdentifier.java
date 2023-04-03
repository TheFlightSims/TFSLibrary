package org.opengis.referencing;

import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.metadata.Identifier;

@UML(identifier = "RS_Identifier", specification = Specification.ISO_19115)
public interface ReferenceIdentifier extends Identifier {
  public static final String CODESPACE_KEY = "codespace";
  
  public static final String VERSION_KEY = "version";
  
  @UML(identifier = "codeSpace", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  String getCodeSpace();
  
  @UML(identifier = "version", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  String getVersion();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\referencing\ReferenceIdentifier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
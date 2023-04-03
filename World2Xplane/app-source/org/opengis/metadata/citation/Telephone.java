package org.opengis.metadata.citation;

import java.util.Collection;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;

@UML(identifier = "CI_Telephone", specification = Specification.ISO_19115)
public interface Telephone {
  @UML(identifier = "voice", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Collection<String> getVoices();
  
  @UML(identifier = "facsimile", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Collection<String> getFacsimiles();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\citation\Telephone.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
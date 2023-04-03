package org.opengis.metadata;

import java.util.Collection;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.metadata.citation.OnLineResource;

@UML(identifier = "MD_MetadataExtensionInformation", specification = Specification.ISO_19115)
public interface MetadataExtensionInformation {
  @UML(identifier = "extensionOnLineResource", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  OnLineResource getExtensionOnLineResource();
  
  @UML(identifier = "extendedElementInformation", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Collection<? extends ExtendedElementInformation> getExtendedElementInformation();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\MetadataExtensionInformation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
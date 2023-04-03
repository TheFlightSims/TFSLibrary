package org.opengis.metadata;

import java.util.Collection;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.metadata.citation.Citation;

@UML(identifier = "MD_PortrayalCatalogueReference", specification = Specification.ISO_19115)
public interface PortrayalCatalogueReference {
  @UML(identifier = "portrayalCatalogueCitation", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  Collection<? extends Citation> getPortrayalCatalogueCitations();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\PortrayalCatalogueReference.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
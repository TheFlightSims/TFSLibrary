package org.opengis.metadata.content;

import java.util.Collection;
import java.util.Locale;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.metadata.citation.Citation;
import org.opengis.util.GenericName;

@UML(identifier = "MD_FeatureCatalogueDescription", specification = Specification.ISO_19115)
public interface FeatureCatalogueDescription extends ContentInformation {
  @UML(identifier = "complianceCode", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Boolean isCompliant();
  
  @UML(identifier = "language", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Collection<Locale> getLanguages();
  
  @UML(identifier = "includedWithDataset", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  boolean isIncludedWithDataset();
  
  @UML(identifier = "featureTypes", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Collection<? extends GenericName> getFeatureTypes();
  
  @UML(identifier = "featureCatalogueCitation", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  Collection<? extends Citation> getFeatureCatalogueCitations();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\content\FeatureCatalogueDescription.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
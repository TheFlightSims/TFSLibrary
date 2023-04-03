package org.opengis.metadata.identification;

import java.util.Collection;
import java.util.Locale;
import org.opengis.annotation.ComplianceLevel;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Profile;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.metadata.extent.Extent;
import org.opengis.metadata.spatial.SpatialRepresentationType;
import org.opengis.util.InternationalString;

@UML(identifier = "MD_DataIdentification", specification = Specification.ISO_19115)
@Profile(level = ComplianceLevel.CORE)
public interface DataIdentification extends Identification {
  @UML(identifier = "spatialRepresentationType", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  @Profile(level = ComplianceLevel.CORE)
  Collection<SpatialRepresentationType> getSpatialRepresentationTypes();
  
  @UML(identifier = "spatialResolution", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  @Profile(level = ComplianceLevel.CORE)
  Collection<? extends Resolution> getSpatialResolutions();
  
  @UML(identifier = "language", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  @Profile(level = ComplianceLevel.CORE)
  Collection<Locale> getLanguage();
  
  @UML(identifier = "characterSet", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
  @Profile(level = ComplianceLevel.CORE)
  Collection<CharacterSet> getCharacterSets();
  
  @UML(identifier = "topicCategory", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  @Profile(level = ComplianceLevel.CORE)
  Collection<TopicCategory> getTopicCategories();
  
  @UML(identifier = "environmentDescription", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  InternationalString getEnvironmentDescription();
  
  @UML(identifier = "extent", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  @Profile(level = ComplianceLevel.CORE)
  Collection<? extends Extent> getExtent();
  
  @UML(identifier = "supplementalInformation", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  InternationalString getSupplementalInformation();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\identification\DataIdentification.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
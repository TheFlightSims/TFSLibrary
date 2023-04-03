package org.opengis.metadata;

import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import org.opengis.annotation.ComplianceLevel;
import org.opengis.annotation.Obligation;
import org.opengis.annotation.Profile;
import org.opengis.annotation.Specification;
import org.opengis.annotation.UML;
import org.opengis.metadata.citation.ResponsibleParty;
import org.opengis.metadata.constraint.Constraints;
import org.opengis.metadata.content.ContentInformation;
import org.opengis.metadata.distribution.Distribution;
import org.opengis.metadata.identification.CharacterSet;
import org.opengis.metadata.identification.Identification;
import org.opengis.metadata.maintenance.MaintenanceInformation;
import org.opengis.metadata.maintenance.ScopeCode;
import org.opengis.metadata.quality.DataQuality;
import org.opengis.metadata.spatial.SpatialRepresentation;
import org.opengis.referencing.ReferenceSystem;

@UML(identifier = "MD_MetaData", specification = Specification.ISO_19115)
@Profile(level = ComplianceLevel.CORE)
public interface MetaData {
  @UML(identifier = "fileIdentifier", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  @Profile(level = ComplianceLevel.CORE)
  String getFileIdentifier();
  
  @UML(identifier = "language", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
  @Profile(level = ComplianceLevel.CORE)
  Locale getLanguage();
  
  @UML(identifier = "characterSet", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
  @Profile(level = ComplianceLevel.CORE)
  CharacterSet getCharacterSet();
  
  @UML(identifier = "parentIdentifier", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
  String getParentIdentifier();
  
  @UML(identifier = "hierarchyLevel", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
  Collection<ScopeCode> getHierarchyLevels();
  
  @UML(identifier = "hierarchyLevelName", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19115)
  Collection<String> getHierarchyLevelNames();
  
  @UML(identifier = "contact", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  @Profile(level = ComplianceLevel.CORE)
  Collection<? extends ResponsibleParty> getContacts();
  
  @UML(identifier = "dateStamp", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  @Profile(level = ComplianceLevel.CORE)
  Date getDateStamp();
  
  @UML(identifier = "metadataStandardName", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  @Profile(level = ComplianceLevel.CORE)
  String getMetadataStandardName();
  
  @UML(identifier = "metadataStandardVersion", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  @Profile(level = ComplianceLevel.CORE)
  String getMetadataStandardVersion();
  
  @UML(identifier = "dataSetURI", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  String getDataSetUri();
  
  @UML(identifier = "locale", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Collection<Locale> getLocales();
  
  @UML(identifier = "spatialRepresentationInfo", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Collection<? extends SpatialRepresentation> getSpatialRepresentationInfo();
  
  @UML(identifier = "referenceSystemInfo", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  @Profile(level = ComplianceLevel.CORE)
  Collection<? extends ReferenceSystem> getReferenceSystemInfo();
  
  @UML(identifier = "metadataExtensionInfo", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Collection<? extends MetadataExtensionInformation> getMetadataExtensionInfo();
  
  @UML(identifier = "identificationInfo", obligation = Obligation.MANDATORY, specification = Specification.ISO_19115)
  @Profile(level = ComplianceLevel.CORE)
  Collection<? extends Identification> getIdentificationInfo();
  
  @UML(identifier = "contentInfo", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Collection<? extends ContentInformation> getContentInfo();
  
  @UML(identifier = "distributionInfo", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  @Profile(level = ComplianceLevel.CORE)
  Distribution getDistributionInfo();
  
  @UML(identifier = "dataQualityInfo", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  @Profile(level = ComplianceLevel.CORE)
  Collection<? extends DataQuality> getDataQualityInfo();
  
  @UML(identifier = "portrayalCatalogueInfo", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Collection<? extends PortrayalCatalogueReference> getPortrayalCatalogueInfo();
  
  @UML(identifier = "metadataConstraints", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Collection<? extends Constraints> getMetadataConstraints();
  
  @UML(identifier = "applicationSchemaInfo", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  Collection<? extends ApplicationSchemaInformation> getApplicationSchemaInfo();
  
  @UML(identifier = "metadataMaintenance", obligation = Obligation.OPTIONAL, specification = Specification.ISO_19115)
  MaintenanceInformation getMetadataMaintenance();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\metadata\MetaData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
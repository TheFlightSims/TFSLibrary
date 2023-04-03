/*     */ package org.geotools.metadata.iso;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Date;
/*     */ import java.util.Locale;
/*     */ import org.opengis.metadata.ApplicationSchemaInformation;
/*     */ import org.opengis.metadata.MetaData;
/*     */ import org.opengis.metadata.MetadataExtensionInformation;
/*     */ import org.opengis.metadata.PortrayalCatalogueReference;
/*     */ import org.opengis.metadata.citation.ResponsibleParty;
/*     */ import org.opengis.metadata.constraint.Constraints;
/*     */ import org.opengis.metadata.content.ContentInformation;
/*     */ import org.opengis.metadata.distribution.Distribution;
/*     */ import org.opengis.metadata.identification.CharacterSet;
/*     */ import org.opengis.metadata.identification.Identification;
/*     */ import org.opengis.metadata.maintenance.MaintenanceInformation;
/*     */ import org.opengis.metadata.maintenance.ScopeCode;
/*     */ import org.opengis.metadata.quality.DataQuality;
/*     */ import org.opengis.metadata.spatial.SpatialRepresentation;
/*     */ import org.opengis.referencing.ReferenceSystem;
/*     */ 
/*     */ public class MetaDataImpl extends MetadataEntity implements MetaData {
/*     */   private static final long serialVersionUID = -5600409558876701144L;
/*     */   
/*     */   private String fileIdentifier;
/*     */   
/*     */   private Locale language;
/*     */   
/*     */   private Collection<Locale> locales;
/*     */   
/*     */   private CharacterSet characterSet;
/*     */   
/*     */   private String parentIdentifier;
/*     */   
/*     */   private Collection<ScopeCode> hierarchyLevels;
/*     */   
/*     */   private Collection<String> hierarchyLevelNames;
/*     */   
/*     */   private Collection<ResponsibleParty> contacts;
/*     */   
/*     */   private String dataSetUri;
/*     */   
/* 112 */   private long dateStamp = Long.MIN_VALUE;
/*     */   
/*     */   private String metadataStandardName;
/*     */   
/*     */   private String metadataStandardVersion;
/*     */   
/*     */   private Collection<SpatialRepresentation> spatialRepresentationInfo;
/*     */   
/*     */   private Collection<ReferenceSystem> referenceSystemInfo;
/*     */   
/*     */   private Collection<MetadataExtensionInformation> metadataExtensionInfo;
/*     */   
/*     */   private Collection<Identification> identificationInfo;
/*     */   
/*     */   private Collection<ContentInformation> contentInfo;
/*     */   
/*     */   private Distribution distributionInfo;
/*     */   
/*     */   private Collection<DataQuality> dataQualityInfo;
/*     */   
/*     */   private Collection<PortrayalCatalogueReference> portrayalCatalogueInfo;
/*     */   
/*     */   private Collection<Constraints> metadataConstraints;
/*     */   
/*     */   private Collection<ApplicationSchemaInformation> applicationSchemaInfo;
/*     */   
/*     */   private MaintenanceInformation metadataMaintenance;
/*     */   
/*     */   public MetaDataImpl() {}
/*     */   
/*     */   public MetaDataImpl(MetaData source) {
/* 192 */     super(source);
/*     */   }
/*     */   
/*     */   public MetaDataImpl(ResponsibleParty contact, Date dateStamp, Identification identificationInfo) {
/* 207 */     setContacts(Collections.singleton(contact));
/* 208 */     setDateStamp(dateStamp);
/* 209 */     setIdentificationInfo(Collections.singleton(identificationInfo));
/*     */   }
/*     */   
/*     */   public String getFileIdentifier() {
/* 216 */     return this.fileIdentifier;
/*     */   }
/*     */   
/*     */   public synchronized void setFileIdentifier(String newValue) {
/* 223 */     checkWritePermission();
/* 224 */     this.fileIdentifier = newValue;
/*     */   }
/*     */   
/*     */   public Locale getLanguage() {
/* 231 */     return this.language;
/*     */   }
/*     */   
/*     */   public synchronized void setLanguage(Locale newValue) {
/* 238 */     checkWritePermission();
/* 239 */     this.language = newValue;
/*     */   }
/*     */   
/*     */   public CharacterSet getCharacterSet() {
/* 246 */     return this.characterSet;
/*     */   }
/*     */   
/*     */   public synchronized void setCharacterSet(CharacterSet newValue) {
/* 253 */     checkWritePermission();
/* 254 */     this.characterSet = newValue;
/*     */   }
/*     */   
/*     */   public String getParentIdentifier() {
/* 261 */     return this.parentIdentifier;
/*     */   }
/*     */   
/*     */   public synchronized void setParentIdentifier(String newValue) {
/* 268 */     checkWritePermission();
/* 269 */     this.parentIdentifier = newValue;
/*     */   }
/*     */   
/*     */   public synchronized Collection<ScopeCode> getHierarchyLevels() {
/* 276 */     return this.hierarchyLevels = nonNullCollection(this.hierarchyLevels, ScopeCode.class);
/*     */   }
/*     */   
/*     */   public synchronized void setHierarchyLevels(Collection<? extends ScopeCode> newValues) {
/* 285 */     this.hierarchyLevels = copyCollection(newValues, this.hierarchyLevels, ScopeCode.class);
/*     */   }
/*     */   
/*     */   public synchronized Collection<String> getHierarchyLevelNames() {
/* 292 */     return this.hierarchyLevelNames = nonNullCollection(this.hierarchyLevelNames, String.class);
/*     */   }
/*     */   
/*     */   public synchronized void setHierarchyLevelNames(Collection<? extends String> newValues) {
/* 301 */     this.hierarchyLevelNames = copyCollection(newValues, this.hierarchyLevelNames, String.class);
/*     */   }
/*     */   
/*     */   public synchronized Collection<ResponsibleParty> getContacts() {
/* 308 */     return this.contacts = nonNullCollection(this.contacts, ResponsibleParty.class);
/*     */   }
/*     */   
/*     */   public synchronized void setContacts(Collection<? extends ResponsibleParty> newValues) {
/* 317 */     checkWritePermission();
/* 318 */     this.contacts = copyCollection(newValues, this.contacts, ResponsibleParty.class);
/*     */   }
/*     */   
/*     */   public synchronized Date getDateStamp() {
/* 325 */     return (this.dateStamp != Long.MIN_VALUE) ? new Date(this.dateStamp) : (Date)null;
/*     */   }
/*     */   
/*     */   public synchronized void setDateStamp(Date newValue) {
/* 332 */     checkWritePermission();
/* 333 */     this.dateStamp = (newValue != null) ? newValue.getTime() : Long.MIN_VALUE;
/*     */   }
/*     */   
/*     */   public String getMetadataStandardName() {
/* 340 */     return this.metadataStandardName;
/*     */   }
/*     */   
/*     */   public synchronized void setMetadataStandardName(String newValue) {
/* 347 */     checkWritePermission();
/* 348 */     this.metadataStandardName = newValue;
/*     */   }
/*     */   
/*     */   public String getMetadataStandardVersion() {
/* 355 */     return this.metadataStandardVersion;
/*     */   }
/*     */   
/*     */   public synchronized void setMetadataStandardVersion(String newValue) {
/* 362 */     checkWritePermission();
/* 363 */     this.metadataStandardVersion = newValue;
/*     */   }
/*     */   
/*     */   public synchronized Collection<SpatialRepresentation> getSpatialRepresentationInfo() {
/* 370 */     return this.spatialRepresentationInfo = nonNullCollection(this.spatialRepresentationInfo, SpatialRepresentation.class);
/*     */   }
/*     */   
/*     */   public synchronized void setSpatialRepresentationInfo(Collection<? extends SpatialRepresentation> newValues) {
/* 380 */     this.spatialRepresentationInfo = copyCollection(newValues, this.spatialRepresentationInfo, SpatialRepresentation.class);
/*     */   }
/*     */   
/*     */   public synchronized Collection<ReferenceSystem> getReferenceSystemInfo() {
/* 390 */     return this.referenceSystemInfo = nonNullCollection(this.referenceSystemInfo, ReferenceSystem.class);
/*     */   }
/*     */   
/*     */   public synchronized void setReferenceSystemInfo(Collection<? extends ReferenceSystem> newValues) {
/* 399 */     this.referenceSystemInfo = copyCollection(newValues, this.referenceSystemInfo, ReferenceSystem.class);
/*     */   }
/*     */   
/*     */   public synchronized Collection<MetadataExtensionInformation> getMetadataExtensionInfo() {
/* 406 */     return this.metadataExtensionInfo = nonNullCollection(this.metadataExtensionInfo, MetadataExtensionInformation.class);
/*     */   }
/*     */   
/*     */   public synchronized void setMetadataExtensionInfo(Collection<? extends MetadataExtensionInformation> newValues) {
/* 416 */     this.metadataExtensionInfo = copyCollection(newValues, this.metadataExtensionInfo, MetadataExtensionInformation.class);
/*     */   }
/*     */   
/*     */   public synchronized Collection<Identification> getIdentificationInfo() {
/* 424 */     return this.identificationInfo = nonNullCollection(this.identificationInfo, Identification.class);
/*     */   }
/*     */   
/*     */   public synchronized void setIdentificationInfo(Collection<? extends Identification> newValues) {
/* 433 */     this.identificationInfo = copyCollection(newValues, this.identificationInfo, Identification.class);
/*     */   }
/*     */   
/*     */   public synchronized Collection<ContentInformation> getContentInfo() {
/* 441 */     return this.contentInfo = nonNullCollection(this.contentInfo, ContentInformation.class);
/*     */   }
/*     */   
/*     */   public synchronized void setContentInfo(Collection<? extends ContentInformation> newValues) {
/* 451 */     this.contentInfo = copyCollection(newValues, this.contentInfo, ContentInformation.class);
/*     */   }
/*     */   
/*     */   public Distribution getDistributionInfo() {
/* 458 */     return this.distributionInfo;
/*     */   }
/*     */   
/*     */   public synchronized void setDistributionInfo(Distribution newValue) {
/* 465 */     checkWritePermission();
/* 466 */     this.distributionInfo = newValue;
/*     */   }
/*     */   
/*     */   public synchronized Collection<DataQuality> getDataQualityInfo() {
/* 473 */     return this.dataQualityInfo = nonNullCollection(this.dataQualityInfo, DataQuality.class);
/*     */   }
/*     */   
/*     */   public synchronized void setDataQualityInfo(Collection<? extends DataQuality> newValues) {
/* 482 */     this.dataQualityInfo = copyCollection(newValues, this.dataQualityInfo, DataQuality.class);
/*     */   }
/*     */   
/*     */   public synchronized Collection<PortrayalCatalogueReference> getPortrayalCatalogueInfo() {
/* 490 */     return this.portrayalCatalogueInfo = nonNullCollection(this.portrayalCatalogueInfo, PortrayalCatalogueReference.class);
/*     */   }
/*     */   
/*     */   public synchronized void setPortrayalCatalogueInfo(Collection<? extends PortrayalCatalogueReference> newValues) {
/* 500 */     this.portrayalCatalogueInfo = copyCollection(newValues, this.portrayalCatalogueInfo, PortrayalCatalogueReference.class);
/*     */   }
/*     */   
/*     */   public synchronized Collection<Constraints> getMetadataConstraints() {
/* 508 */     return this.metadataConstraints = nonNullCollection(this.metadataConstraints, Constraints.class);
/*     */   }
/*     */   
/*     */   public synchronized void setMetadataConstraints(Collection<? extends Constraints> newValues) {
/* 517 */     this.metadataConstraints = copyCollection(newValues, this.metadataConstraints, Constraints.class);
/*     */   }
/*     */   
/*     */   public synchronized Collection<ApplicationSchemaInformation> getApplicationSchemaInfo() {
/* 524 */     return this.applicationSchemaInfo = nonNullCollection(this.applicationSchemaInfo, ApplicationSchemaInformation.class);
/*     */   }
/*     */   
/*     */   public synchronized void setApplicationSchemaInfo(Collection<? extends ApplicationSchemaInformation> newValues) {
/* 534 */     this.applicationSchemaInfo = copyCollection(newValues, this.applicationSchemaInfo, ApplicationSchemaInformation.class);
/*     */   }
/*     */   
/*     */   public MaintenanceInformation getMetadataMaintenance() {
/* 542 */     return this.metadataMaintenance;
/*     */   }
/*     */   
/*     */   public synchronized void setMetadataMaintenance(MaintenanceInformation newValue) {
/* 549 */     checkWritePermission();
/* 550 */     this.metadataMaintenance = newValue;
/*     */   }
/*     */   
/*     */   public synchronized Collection<Locale> getLocales() {
/* 560 */     return this.locales = nonNullCollection(this.locales, Locale.class);
/*     */   }
/*     */   
/*     */   public synchronized void setLocales(Collection<? extends Locale> newValues) {
/* 572 */     this.locales = copyCollection(newValues, this.locales, Locale.class);
/*     */   }
/*     */   
/*     */   public String getDataSetUri() {
/* 581 */     return this.dataSetUri;
/*     */   }
/*     */   
/*     */   public void setDataSetUri(String newValue) {
/* 590 */     checkWritePermission();
/* 591 */     this.dataSetUri = newValue;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\MetaDataImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
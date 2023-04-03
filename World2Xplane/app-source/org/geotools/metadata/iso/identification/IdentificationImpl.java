/*     */ package org.geotools.metadata.iso.identification;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.geotools.metadata.iso.MetadataEntity;
/*     */ import org.opengis.metadata.citation.Citation;
/*     */ import org.opengis.metadata.citation.ResponsibleParty;
/*     */ import org.opengis.metadata.constraint.Constraints;
/*     */ import org.opengis.metadata.distribution.Format;
/*     */ import org.opengis.metadata.identification.AggregateInformation;
/*     */ import org.opengis.metadata.identification.BrowseGraphic;
/*     */ import org.opengis.metadata.identification.Identification;
/*     */ import org.opengis.metadata.identification.Keywords;
/*     */ import org.opengis.metadata.identification.Progress;
/*     */ import org.opengis.metadata.identification.Usage;
/*     */ import org.opengis.metadata.maintenance.MaintenanceInformation;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class IdentificationImpl extends MetadataEntity implements Identification {
/*     */   private static final long serialVersionUID = -3715084806249419137L;
/*     */   
/*     */   private Citation citation;
/*     */   
/*     */   private InternationalString abstracts;
/*     */   
/*     */   private InternationalString purpose;
/*     */   
/*     */   private Collection<String> credits;
/*     */   
/*     */   private Collection<Progress> status;
/*     */   
/*     */   private Collection<ResponsibleParty> pointOfContacts;
/*     */   
/*     */   private Collection<MaintenanceInformation> resourceMaintenance;
/*     */   
/*     */   private Collection<BrowseGraphic> graphicOverviews;
/*     */   
/*     */   private Collection<Format> resourceFormat;
/*     */   
/*     */   private Collection<Keywords> descriptiveKeywords;
/*     */   
/*     */   private Collection<Usage> resourceSpecificUsages;
/*     */   
/*     */   private Collection<Constraints> resourceConstraints;
/*     */   
/*     */   private Collection<AggregateInformation> aggregationInfo;
/*     */   
/*     */   public IdentificationImpl() {}
/*     */   
/*     */   public IdentificationImpl(Identification source) {
/* 135 */     super(source);
/*     */   }
/*     */   
/*     */   public IdentificationImpl(Citation citation, InternationalString abstracts) {
/* 142 */     setCitation(citation);
/* 143 */     setAbstract(abstracts);
/*     */   }
/*     */   
/*     */   public Citation getCitation() {
/* 150 */     return this.citation;
/*     */   }
/*     */   
/*     */   public synchronized void setCitation(Citation newValue) {
/* 157 */     checkWritePermission();
/* 158 */     this.citation = newValue;
/*     */   }
/*     */   
/*     */   public InternationalString getAbstract() {
/* 165 */     return this.abstracts;
/*     */   }
/*     */   
/*     */   public synchronized void setAbstract(InternationalString newValue) {
/* 172 */     checkWritePermission();
/* 173 */     this.abstracts = newValue;
/*     */   }
/*     */   
/*     */   public InternationalString getPurpose() {
/* 180 */     return this.purpose;
/*     */   }
/*     */   
/*     */   public synchronized void setPurpose(InternationalString newValue) {
/* 187 */     checkWritePermission();
/* 188 */     this.purpose = newValue;
/*     */   }
/*     */   
/*     */   public synchronized Collection<String> getCredits() {
/* 195 */     return this.credits = nonNullCollection(this.credits, String.class);
/*     */   }
/*     */   
/*     */   public synchronized void setCredits(Collection<? extends String> newValues) {
/* 202 */     this.credits = copyCollection(newValues, this.credits, String.class);
/*     */   }
/*     */   
/*     */   public synchronized Collection<Progress> getStatus() {
/* 209 */     return this.status = nonNullCollection(this.status, Progress.class);
/*     */   }
/*     */   
/*     */   public synchronized void setStatus(Collection<? extends Progress> newValues) {
/* 216 */     this.status = copyCollection(newValues, this.status, Progress.class);
/*     */   }
/*     */   
/*     */   public synchronized Collection<ResponsibleParty> getPointOfContacts() {
/* 224 */     return this.pointOfContacts = nonNullCollection(this.pointOfContacts, ResponsibleParty.class);
/*     */   }
/*     */   
/*     */   public synchronized void setPointOfContacts(Collection<? extends ResponsibleParty> newValues) {
/* 233 */     this.pointOfContacts = copyCollection(newValues, this.pointOfContacts, ResponsibleParty.class);
/*     */   }
/*     */   
/*     */   public synchronized Collection<MaintenanceInformation> getResourceMaintenance() {
/* 240 */     return this.resourceMaintenance = nonNullCollection(this.resourceMaintenance, MaintenanceInformation.class);
/*     */   }
/*     */   
/*     */   public synchronized void setResourceMaintenance(Collection<? extends MaintenanceInformation> newValues) {
/* 250 */     this.resourceMaintenance = copyCollection(newValues, this.resourceMaintenance, MaintenanceInformation.class);
/*     */   }
/*     */   
/*     */   public synchronized Collection<BrowseGraphic> getGraphicOverviews() {
/* 258 */     return this.graphicOverviews = nonNullCollection(this.graphicOverviews, BrowseGraphic.class);
/*     */   }
/*     */   
/*     */   public synchronized void setGraphicOverviews(Collection<? extends BrowseGraphic> newValues) {
/* 267 */     this.graphicOverviews = copyCollection(newValues, this.graphicOverviews, BrowseGraphic.class);
/*     */   }
/*     */   
/*     */   public synchronized Collection<Format> getResourceFormat() {
/* 274 */     return this.resourceFormat = nonNullCollection(this.resourceFormat, Format.class);
/*     */   }
/*     */   
/*     */   public synchronized void setResourceFormat(Collection<? extends Format> newValues) {
/* 281 */     this.resourceFormat = copyCollection(newValues, this.resourceFormat, Format.class);
/*     */   }
/*     */   
/*     */   public synchronized Collection<Keywords> getDescriptiveKeywords() {
/* 288 */     return this.descriptiveKeywords = nonNullCollection(this.descriptiveKeywords, Keywords.class);
/*     */   }
/*     */   
/*     */   public synchronized void setDescriptiveKeywords(Collection<? extends Keywords> newValues) {
/* 297 */     this.descriptiveKeywords = copyCollection(newValues, this.descriptiveKeywords, Keywords.class);
/*     */   }
/*     */   
/*     */   public synchronized Collection<Usage> getResourceSpecificUsages() {
/* 305 */     return this.resourceSpecificUsages = nonNullCollection(this.resourceSpecificUsages, Usage.class);
/*     */   }
/*     */   
/*     */   public synchronized void setResourceSpecificUsages(Collection<? extends Usage> newValues) {
/* 314 */     this.resourceSpecificUsages = copyCollection(newValues, this.resourceSpecificUsages, Usage.class);
/*     */   }
/*     */   
/*     */   public synchronized Collection<Constraints> getResourceConstraints() {
/* 321 */     return this.resourceConstraints = nonNullCollection(this.resourceConstraints, Constraints.class);
/*     */   }
/*     */   
/*     */   public synchronized void setResourceConstraints(Collection<? extends Constraints> newValues) {
/* 330 */     this.resourceConstraints = copyCollection(newValues, this.resourceConstraints, Constraints.class);
/*     */   }
/*     */   
/*     */   public synchronized Collection<AggregateInformation> getAggregationInfo() {
/* 339 */     return this.aggregationInfo = nonNullCollection(this.aggregationInfo, AggregateInformation.class);
/*     */   }
/*     */   
/*     */   public synchronized void setAggregationInfo(Collection<? extends AggregateInformation> newValues) {
/* 350 */     this.aggregationInfo = copyCollection(newValues, this.aggregationInfo, AggregateInformation.class);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\identification\IdentificationImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package org.geotools.metadata.iso.identification;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Locale;
/*     */ import org.opengis.metadata.citation.Citation;
/*     */ import org.opengis.metadata.extent.Extent;
/*     */ import org.opengis.metadata.identification.CharacterSet;
/*     */ import org.opengis.metadata.identification.DataIdentification;
/*     */ import org.opengis.metadata.identification.Identification;
/*     */ import org.opengis.metadata.identification.Resolution;
/*     */ import org.opengis.metadata.identification.TopicCategory;
/*     */ import org.opengis.metadata.spatial.SpatialRepresentationType;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class DataIdentificationImpl extends IdentificationImpl implements DataIdentification {
/*     */   private static final long serialVersionUID = -4418520352804939785L;
/*     */   
/*     */   private Collection<SpatialRepresentationType> spatialRepresentationTypes;
/*     */   
/*     */   private Collection<Resolution> spatialResolutions;
/*     */   
/*     */   private Collection<Locale> language;
/*     */   
/*     */   private Collection<CharacterSet> characterSets;
/*     */   
/*     */   private Collection<TopicCategory> topicCategories;
/*     */   
/*     */   private InternationalString environmentDescription;
/*     */   
/*     */   private Collection<Extent> extent;
/*     */   
/*     */   private InternationalString supplementalInformation;
/*     */   
/*     */   public DataIdentificationImpl() {}
/*     */   
/*     */   public DataIdentificationImpl(DataIdentification source) {
/* 111 */     super((Identification)source);
/*     */   }
/*     */   
/*     */   public DataIdentificationImpl(Citation citation, InternationalString abstracts, Collection<? extends Locale> language, Collection<? extends TopicCategory> topicCategories) {
/* 122 */     super(citation, abstracts);
/* 123 */     setLanguage(language);
/* 124 */     setTopicCategories(topicCategories);
/*     */   }
/*     */   
/*     */   public synchronized Collection<SpatialRepresentationType> getSpatialRepresentationTypes() {
/* 131 */     return this.spatialRepresentationTypes = nonNullCollection(this.spatialRepresentationTypes, SpatialRepresentationType.class);
/*     */   }
/*     */   
/*     */   public synchronized void setSpatialRepresentationTypes(Collection<? extends SpatialRepresentationType> newValues) {
/* 141 */     this.spatialRepresentationTypes = copyCollection(newValues, this.spatialRepresentationTypes, SpatialRepresentationType.class);
/*     */   }
/*     */   
/*     */   public synchronized Collection<Resolution> getSpatialResolutions() {
/* 150 */     return this.spatialResolutions = nonNullCollection(this.spatialResolutions, Resolution.class);
/*     */   }
/*     */   
/*     */   public synchronized void setSpatialResolutions(Collection<? extends Resolution> newValues) {
/* 160 */     this.spatialResolutions = copyCollection(newValues, this.spatialResolutions, Resolution.class);
/*     */   }
/*     */   
/*     */   public synchronized Collection<Locale> getLanguage() {
/* 167 */     return this.language = nonNullCollection(this.language, Locale.class);
/*     */   }
/*     */   
/*     */   public synchronized void setLanguage(Collection<? extends Locale> newValues) {
/* 174 */     this.language = copyCollection(newValues, this.language, Locale.class);
/*     */   }
/*     */   
/*     */   public synchronized Collection<CharacterSet> getCharacterSets() {
/* 181 */     return this.characterSets = nonNullCollection(this.characterSets, CharacterSet.class);
/*     */   }
/*     */   
/*     */   public synchronized void setCharacterSets(Collection<? extends CharacterSet> newValues) {
/* 188 */     this.characterSets = copyCollection(newValues, this.characterSets, CharacterSet.class);
/*     */   }
/*     */   
/*     */   public synchronized Collection<TopicCategory> getTopicCategories() {
/* 195 */     return this.topicCategories = nonNullCollection(this.topicCategories, TopicCategory.class);
/*     */   }
/*     */   
/*     */   public synchronized void setTopicCategories(Collection<? extends TopicCategory> newValues) {
/* 204 */     this.topicCategories = copyCollection(newValues, this.topicCategories, TopicCategory.class);
/*     */   }
/*     */   
/*     */   public InternationalString getEnvironmentDescription() {
/* 212 */     return this.environmentDescription;
/*     */   }
/*     */   
/*     */   public synchronized void setEnvironmentDescription(InternationalString newValue) {
/* 219 */     checkWritePermission();
/* 220 */     this.environmentDescription = newValue;
/*     */   }
/*     */   
/*     */   public synchronized Collection<Extent> getExtent() {
/* 228 */     return this.extent = nonNullCollection(this.extent, Extent.class);
/*     */   }
/*     */   
/*     */   public synchronized void setExtent(Collection<? extends Extent> newValues) {
/* 235 */     this.extent = copyCollection(newValues, this.extent, Extent.class);
/*     */   }
/*     */   
/*     */   public InternationalString getSupplementalInformation() {
/* 242 */     return this.supplementalInformation;
/*     */   }
/*     */   
/*     */   public synchronized void setSupplementalInformation(InternationalString newValue) {
/* 249 */     checkWritePermission();
/* 250 */     this.supplementalInformation = newValue;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\identification\DataIdentificationImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
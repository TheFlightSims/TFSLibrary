/*     */ package org.geotools.metadata.iso.content;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Locale;
/*     */ import org.opengis.metadata.citation.Citation;
/*     */ import org.opengis.metadata.content.ContentInformation;
/*     */ import org.opengis.metadata.content.FeatureCatalogueDescription;
/*     */ import org.opengis.util.GenericName;
/*     */ 
/*     */ public class FeatureCatalogueDescriptionImpl extends ContentInformationImpl implements FeatureCatalogueDescription {
/*     */   private static final long serialVersionUID = 1984922846251567908L;
/*     */   
/*     */   private Boolean compliant;
/*     */   
/*     */   private Collection<Locale> languages;
/*     */   
/*     */   private boolean includeWithDataset;
/*     */   
/*     */   private Collection<GenericName> featureTypes;
/*     */   
/*     */   private Collection<Citation> featureCatalogueCitations;
/*     */   
/*     */   public FeatureCatalogueDescriptionImpl() {}
/*     */   
/*     */   public FeatureCatalogueDescriptionImpl(FeatureCatalogueDescription source) {
/*  86 */     super((ContentInformation)source);
/*     */   }
/*     */   
/*     */   public Boolean isCompliant() {
/*  93 */     return this.compliant;
/*     */   }
/*     */   
/*     */   public synchronized void setCompliant(Boolean newValue) {
/* 100 */     checkWritePermission();
/* 101 */     this.compliant = newValue;
/*     */   }
/*     */   
/*     */   public synchronized Collection<Locale> getLanguages() {
/* 108 */     return this.languages = nonNullCollection(this.languages, Locale.class);
/*     */   }
/*     */   
/*     */   public synchronized void setLanguages(Collection<? extends Locale> newValues) {
/* 115 */     this.languages = copyCollection(newValues, this.languages, Locale.class);
/*     */   }
/*     */   
/*     */   public boolean isIncludedWithDataset() {
/* 122 */     return this.includeWithDataset;
/*     */   }
/*     */   
/*     */   public synchronized void setIncludedWithDataset(boolean newValue) {
/* 129 */     checkWritePermission();
/* 130 */     this.includeWithDataset = newValue;
/*     */   }
/*     */   
/*     */   public synchronized Collection<GenericName> getFeatureTypes() {
/* 139 */     return this.featureTypes = nonNullCollection(this.featureTypes, GenericName.class);
/*     */   }
/*     */   
/*     */   public synchronized void setFeatureTypes(Collection<? extends GenericName> newValues) {
/* 146 */     this.featureTypes = copyCollection(newValues, this.featureTypes, GenericName.class);
/*     */   }
/*     */   
/*     */   public synchronized Collection<Citation> getFeatureCatalogueCitations() {
/* 153 */     return this.featureCatalogueCitations = nonNullCollection(this.featureCatalogueCitations, Citation.class);
/*     */   }
/*     */   
/*     */   public synchronized void setFeatureCatalogueCitations(Collection<? extends Citation> newValues) {
/* 162 */     this.featureCatalogueCitations = copyCollection(newValues, this.featureCatalogueCitations, Citation.class);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\content\FeatureCatalogueDescriptionImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
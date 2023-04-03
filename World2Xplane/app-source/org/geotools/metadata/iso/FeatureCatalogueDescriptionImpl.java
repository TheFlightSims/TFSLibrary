/*     */ package org.geotools.metadata.iso;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Locale;
/*     */ import org.geotools.metadata.iso.content.ContentInformationImpl;
/*     */ import org.opengis.metadata.citation.Citation;
/*     */ import org.opengis.metadata.content.ContentInformation;
/*     */ import org.opengis.metadata.content.FeatureCatalogueDescription;
/*     */ import org.opengis.util.GenericName;
/*     */ 
/*     */ public class FeatureCatalogueDescriptionImpl extends ContentInformationImpl implements FeatureCatalogueDescription {
/*     */   private static final long serialVersionUID = -5361236546997056467L;
/*     */   
/*     */   private Boolean compliant;
/*     */   
/*     */   private Collection<Locale> language;
/*     */   
/*     */   private Boolean includeWithDataset;
/*     */   
/*     */   private Collection<GenericName> featureTypes;
/*     */   
/*     */   private Collection<Citation> featureCatalogueCitations;
/*     */   
/*     */   public FeatureCatalogueDescriptionImpl() {}
/*     */   
/*     */   public FeatureCatalogueDescriptionImpl(FeatureCatalogueDescription source) {
/*  89 */     super((ContentInformation)source);
/*     */   }
/*     */   
/*     */   public Boolean isCompliant() {
/*  96 */     return this.compliant;
/*     */   }
/*     */   
/*     */   public synchronized void setCompliant(Boolean newValue) {
/* 102 */     checkWritePermission();
/* 103 */     this.compliant = newValue;
/*     */   }
/*     */   
/*     */   public synchronized Collection<Locale> getLanguages() {
/* 110 */     return this.language = nonNullCollection(this.language, Locale.class);
/*     */   }
/*     */   
/*     */   public synchronized void setLanguages(Collection<? extends Locale> newValues) {
/* 119 */     this.language = copyCollection(newValues, this.language, Locale.class);
/*     */   }
/*     */   
/*     */   public boolean isIncludedWithDataset() {
/* 128 */     return this.includeWithDataset.booleanValue();
/*     */   }
/*     */   
/*     */   public synchronized void setIncludedWithDataset(Boolean newValue) {
/* 135 */     checkWritePermission();
/* 136 */     this.includeWithDataset = newValue;
/*     */   }
/*     */   
/*     */   public synchronized Collection<GenericName> getFeatureTypes() {
/* 145 */     return this.featureTypes = nonNullCollection(this.featureTypes, GenericName.class);
/*     */   }
/*     */   
/*     */   public synchronized void setFeatureTypes(Collection<? extends GenericName> newValues) {
/* 154 */     this.featureTypes = copyCollection(newValues, this.featureTypes, GenericName.class);
/*     */   }
/*     */   
/*     */   public synchronized Collection<Citation> getFeatureCatalogueCitations() {
/* 161 */     return this.featureCatalogueCitations = nonNullCollection(this.featureCatalogueCitations, Citation.class);
/*     */   }
/*     */   
/*     */   public synchronized void setFeatureCatalogueCitations(Collection<? extends Citation> newValues) {
/* 170 */     this.featureCatalogueCitations = copyCollection(newValues, this.featureCatalogueCitations, Citation.class);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\FeatureCatalogueDescriptionImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
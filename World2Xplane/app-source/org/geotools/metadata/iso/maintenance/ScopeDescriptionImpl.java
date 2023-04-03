/*     */ package org.geotools.metadata.iso.maintenance;
/*     */ 
/*     */ import java.util.Set;
/*     */ import org.geotools.metadata.iso.MetadataEntity;
/*     */ import org.opengis.feature.type.AttributeType;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ import org.opengis.metadata.maintenance.ScopeDescription;
/*     */ 
/*     */ public class ScopeDescriptionImpl extends MetadataEntity implements ScopeDescription {
/*     */   private static final long serialVersionUID = -5671299759930976286L;
/*     */   
/*     */   private Set<AttributeType> attributes;
/*     */   
/*     */   private Set<FeatureType> features;
/*     */   
/*     */   private Set<FeatureType> featureInstances;
/*     */   
/*     */   private Set<AttributeType> attributeInstances;
/*     */   
/*     */   private String dataset;
/*     */   
/*     */   private String other;
/*     */   
/*     */   public ScopeDescriptionImpl() {}
/*     */   
/*     */   public ScopeDescriptionImpl(ScopeDescription source) {
/*  90 */     super(source);
/*     */   }
/*     */   
/*     */   public Set<AttributeType> getAttributes() {
/*  97 */     return this.attributes = nonNullSet(this.attributes, AttributeType.class);
/*     */   }
/*     */   
/*     */   public synchronized void setAttributes(Set<? extends AttributeType> newValues) {
/* 106 */     this.attributes = (Set<AttributeType>)copyCollection(newValues, this.attributes, AttributeType.class);
/*     */   }
/*     */   
/*     */   public Set<FeatureType> getFeatures() {
/* 113 */     return this.features = nonNullSet(this.features, FeatureType.class);
/*     */   }
/*     */   
/*     */   public synchronized void setFeatures(Set<? extends FeatureType> newValues) {
/* 122 */     this.features = (Set<FeatureType>)copyCollection(newValues, this.features, FeatureType.class);
/*     */   }
/*     */   
/*     */   public Set<FeatureType> getFeatureInstances() {
/* 129 */     return this.featureInstances = nonNullSet(this.featureInstances, FeatureType.class);
/*     */   }
/*     */   
/*     */   public synchronized void setFeatureInstances(Set<? extends FeatureType> newValues) {
/* 138 */     this.featureInstances = (Set<FeatureType>)copyCollection(newValues, this.featureInstances, FeatureType.class);
/*     */   }
/*     */   
/*     */   public Set<AttributeType> getAttributeInstances() {
/* 147 */     return this.attributeInstances = nonNullSet(this.attributeInstances, AttributeType.class);
/*     */   }
/*     */   
/*     */   public synchronized void setAttributeInstances(Set<? extends AttributeType> newValues) {
/* 156 */     this.attributeInstances = (Set<AttributeType>)copyCollection(newValues, this.attributeInstances, AttributeType.class);
/*     */   }
/*     */   
/*     */   public String getDataset() {
/* 165 */     return this.dataset;
/*     */   }
/*     */   
/*     */   public synchronized void setDataset(String newValue) {
/* 174 */     checkWritePermission();
/* 175 */     this.dataset = newValue;
/*     */   }
/*     */   
/*     */   public String getOther() {
/* 185 */     return this.other;
/*     */   }
/*     */   
/*     */   public synchronized void setOther(String newValue) {
/* 195 */     checkWritePermission();
/* 196 */     this.other = newValue;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\metadata\iso\maintenance\ScopeDescriptionImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
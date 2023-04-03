/*     */ package org.geotools.feature;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*     */ import org.opengis.feature.Feature;
/*     */ import org.opengis.feature.GeometryAttribute;
/*     */ import org.opengis.feature.Property;
/*     */ import org.opengis.feature.type.AttributeDescriptor;
/*     */ import org.opengis.feature.type.AttributeType;
/*     */ import org.opengis.feature.type.ComplexType;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ import org.opengis.feature.type.GeometryType;
/*     */ import org.opengis.feature.type.PropertyType;
/*     */ import org.opengis.filter.identity.FeatureId;
/*     */ import org.opengis.filter.identity.Identifier;
/*     */ import org.opengis.geometry.BoundingBox;
/*     */ 
/*     */ public class FeatureImpl extends ComplexAttributeImpl implements Feature {
/*     */   GeometryAttribute defaultGeometry;
/*     */   
/*     */   public FeatureImpl(Collection<Property> properties, AttributeDescriptor desc, FeatureId id) {
/*  59 */     super(properties, desc, (Identifier)id);
/*     */   }
/*     */   
/*     */   public FeatureImpl(Collection<Property> properties, FeatureType type, FeatureId id) {
/*  70 */     super(properties, (ComplexType)type, (Identifier)id);
/*     */   }
/*     */   
/*     */   public FeatureType getType() {
/*  74 */     return (FeatureType)super.getType();
/*     */   }
/*     */   
/*     */   public FeatureId getIdentifier() {
/*  77 */     return (FeatureId)this.id;
/*     */   }
/*     */   
/*     */   public BoundingBox getBounds() {
/*  91 */     ReferencedEnvelope bounds = new ReferencedEnvelope(getType().getCoordinateReferenceSystem());
/*  92 */     for (Iterator<? extends Property> itr = getValue().iterator(); itr.hasNext(); ) {
/*  93 */       Property property = itr.next();
/*  94 */       if (property instanceof GeometryAttribute)
/*  95 */         bounds.include(((GeometryAttribute)property).getBounds()); 
/*     */     } 
/*  99 */     return (BoundingBox)bounds;
/*     */   }
/*     */   
/*     */   public GeometryAttribute getDefaultGeometryProperty() {
/* 103 */     if (this.defaultGeometry != null)
/* 104 */       return this.defaultGeometry; 
/* 107 */     synchronized (this) {
/* 108 */       if (this.defaultGeometry == null) {
/* 110 */         if (getType().getGeometryDescriptor() == null)
/* 111 */           return null; 
/* 114 */         GeometryType geometryType = getType().getGeometryDescriptor().getType();
/* 117 */         if (geometryType != null)
/* 118 */           for (Iterator<? extends Property> itr = getValue().iterator(); itr.hasNext(); ) {
/* 119 */             Property property = itr.next();
/* 120 */             if (property instanceof GeometryAttribute && 
/* 121 */               property.getType().equals(geometryType)) {
/* 122 */               this.defaultGeometry = (GeometryAttribute)property;
/*     */               break;
/*     */             } 
/*     */           }  
/*     */       } 
/*     */     } 
/* 132 */     return this.defaultGeometry;
/*     */   }
/*     */   
/*     */   public void setDefaultGeometryProperty(GeometryAttribute defaultGeometry) {
/* 140 */     if (!getValue().contains(defaultGeometry))
/* 141 */       throw new IllegalArgumentException("specified attribute is not one of: " + getValue()); 
/* 144 */     synchronized (this) {
/* 145 */       this.defaultGeometry = defaultGeometry;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\FeatureImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
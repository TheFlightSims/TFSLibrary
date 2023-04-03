/*     */ package org.geotools.feature;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.geotools.factory.CommonFactoryFinder;
/*     */ import org.geotools.feature.simple.SimpleFeatureImpl;
/*     */ import org.opengis.feature.Association;
/*     */ import org.opengis.feature.Attribute;
/*     */ import org.opengis.feature.ComplexAttribute;
/*     */ import org.opengis.feature.Feature;
/*     */ import org.opengis.feature.FeatureFactory;
/*     */ import org.opengis.feature.GeometryAttribute;
/*     */ import org.opengis.feature.Property;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.AssociationDescriptor;
/*     */ import org.opengis.feature.type.AttributeDescriptor;
/*     */ import org.opengis.feature.type.ComplexType;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ import org.opengis.feature.type.GeometryDescriptor;
/*     */ import org.opengis.filter.FilterFactory2;
/*     */ import org.opengis.filter.identity.Identifier;
/*     */ import org.opengis.geometry.coordinate.GeometryFactory;
/*     */ import org.opengis.referencing.crs.CRSFactory;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ 
/*     */ public abstract class AbstractFeatureFactoryImpl implements FeatureFactory {
/*     */   CRSFactory crsFactory;
/*     */   
/*     */   GeometryFactory geometryFactory;
/*     */   
/*  65 */   FilterFactory2 ff = CommonFactoryFinder.getFilterFactory2(null);
/*     */   
/*     */   boolean validating = false;
/*     */   
/*     */   public CRSFactory getCRSFactory() {
/*  74 */     return this.crsFactory;
/*     */   }
/*     */   
/*     */   public void setCRSFactory(CRSFactory crsFactory) {
/*  78 */     this.crsFactory = crsFactory;
/*     */   }
/*     */   
/*     */   public GeometryFactory getGeometryFactory() {
/*  82 */     return this.geometryFactory;
/*     */   }
/*     */   
/*     */   public void setGeometryFactory(GeometryFactory geometryFactory) {
/*  86 */     this.geometryFactory = geometryFactory;
/*     */   }
/*     */   
/*     */   public Association createAssociation(Attribute related, AssociationDescriptor descriptor) {
/*  90 */     return new AssociationImpl(related, descriptor);
/*     */   }
/*     */   
/*     */   public Attribute createAttribute(Object value, AttributeDescriptor descriptor, String id) {
/*  94 */     return new AttributeImpl(value, descriptor, (id == null) ? null : (Identifier)this.ff.gmlObjectId(id));
/*     */   }
/*     */   
/*     */   public GeometryAttribute createGeometryAttribute(Object value, GeometryDescriptor descriptor, String id, CoordinateReferenceSystem crs) {
/* 101 */     return new GeometryAttributeImpl(value, descriptor, (id == null) ? null : (Identifier)this.ff.gmlObjectId(id));
/*     */   }
/*     */   
/*     */   public ComplexAttribute createComplexAttribute(Collection<Property> value, AttributeDescriptor descriptor, String id) {
/* 107 */     return new ComplexAttributeImpl(value, descriptor, (id == null) ? null : (Identifier)this.ff.gmlObjectId(id));
/*     */   }
/*     */   
/*     */   public ComplexAttribute createComplexAttribute(Collection<Property> value, ComplexType type, String id) {
/* 112 */     return new ComplexAttributeImpl(value, type, (id == null) ? null : (Identifier)this.ff.gmlObjectId(id));
/*     */   }
/*     */   
/*     */   public Feature createFeature(Collection<Property> value, AttributeDescriptor descriptor, String id) {
/* 116 */     return new FeatureImpl(value, descriptor, this.ff.featureId(id));
/*     */   }
/*     */   
/*     */   public Feature createFeature(Collection<Property> value, FeatureType type, String id) {
/* 120 */     return new FeatureImpl(value, type, this.ff.featureId(id));
/*     */   }
/*     */   
/*     */   public SimpleFeature createSimpleFeature(Object[] array, SimpleFeatureType type, String id) {
/* 125 */     if (type.isAbstract())
/* 126 */       throw new IllegalArgumentException("Cannot create an feature of an abstract FeatureType " + type.getTypeName()); 
/* 128 */     return (SimpleFeature)new SimpleFeatureImpl(array, type, this.ff.featureId(id), this.validating);
/*     */   }
/*     */   
/*     */   public SimpleFeature createSimpleFeautre(Object[] array, AttributeDescriptor descriptor, String id) {
/* 133 */     return createSimpleFeature(array, (SimpleFeatureType)descriptor, id);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\AbstractFeatureFactoryImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package org.geotools.feature.type;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import org.geotools.feature.simple.SimpleFeatureTypeImpl;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.AssociationDescriptor;
/*     */ import org.opengis.feature.type.AssociationType;
/*     */ import org.opengis.feature.type.AttributeDescriptor;
/*     */ import org.opengis.feature.type.AttributeType;
/*     */ import org.opengis.feature.type.ComplexType;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ import org.opengis.feature.type.FeatureTypeFactory;
/*     */ import org.opengis.feature.type.GeometryDescriptor;
/*     */ import org.opengis.feature.type.GeometryType;
/*     */ import org.opengis.feature.type.Name;
/*     */ import org.opengis.feature.type.PropertyDescriptor;
/*     */ import org.opengis.feature.type.Schema;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.filter.FilterFactory;
/*     */ import org.opengis.referencing.crs.CRSFactory;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class FeatureTypeFactoryImpl implements FeatureTypeFactory {
/*     */   CRSFactory crsFactory;
/*     */   
/*     */   FilterFactory filterFactory;
/*     */   
/*     */   public FeatureTypeFactoryImpl() {
/*  65 */     this.crsFactory = null;
/*  66 */     this.filterFactory = null;
/*     */   }
/*     */   
/*     */   public FeatureTypeFactoryImpl(CRSFactory crsFactory, FilterFactory filterFactory) {
/*  70 */     this.crsFactory = crsFactory;
/*  71 */     this.filterFactory = filterFactory;
/*     */   }
/*     */   
/*     */   public Schema createSchema(String uri) {
/*  75 */     return new SchemaImpl(uri);
/*     */   }
/*     */   
/*     */   public CRSFactory getCRSFactory() {
/*  79 */     return this.crsFactory;
/*     */   }
/*     */   
/*     */   public void setCRSFactory(CRSFactory crsFactory) {
/*  83 */     this.crsFactory = crsFactory;
/*     */   }
/*     */   
/*     */   public FilterFactory getFilterFactory() {
/*  87 */     return this.filterFactory;
/*     */   }
/*     */   
/*     */   public void setFilterFactory(FilterFactory filterFactory) {
/*  91 */     this.filterFactory = filterFactory;
/*     */   }
/*     */   
/*     */   public AssociationDescriptor createAssociationDescriptor(AssociationType type, Name name, int minOccurs, int maxOccurs, boolean isNillable) {
/*  97 */     return new AssociationDescriptorImpl(type, name, minOccurs, maxOccurs, isNillable);
/*     */   }
/*     */   
/*     */   public AttributeDescriptor createAttributeDescriptor(AttributeType type, Name name, int minOccurs, int maxOccurs, boolean isNillable, Object defaultValue) {
/* 103 */     return new AttributeDescriptorImpl(type, name, minOccurs, maxOccurs, isNillable, defaultValue);
/*     */   }
/*     */   
/*     */   public GeometryDescriptor createGeometryDescriptor(GeometryType type, Name name, int minOccurs, int maxOccurs, boolean isNillable, Object defaultValue) {
/* 109 */     return new GeometryDescriptorImpl(type, name, minOccurs, maxOccurs, isNillable, defaultValue);
/*     */   }
/*     */   
/*     */   public AssociationType createAssociationType(Name name, AttributeType relatedType, boolean isAbstract, List<Filter> restrictions, AssociationType superType, InternationalString description) {
/* 116 */     return new AssociationTypeImpl(name, relatedType, isAbstract, restrictions, superType, description);
/*     */   }
/*     */   
/*     */   public AttributeType createAttributeType(Name name, Class<?> binding, boolean isIdentifiable, boolean isAbstract, List<Filter> restrictions, AttributeType superType, InternationalString description) {
/* 124 */     return new AttributeTypeImpl(name, binding, isIdentifiable, isAbstract, restrictions, superType, description);
/*     */   }
/*     */   
/*     */   public ComplexType createComplexType(Name name, Collection<PropertyDescriptor> schema, boolean isIdentifiable, boolean isAbstract, List<Filter> restrictions, AttributeType superType, InternationalString description) {
/* 131 */     return new ComplexTypeImpl(name, schema, isIdentifiable, isAbstract, restrictions, superType, description);
/*     */   }
/*     */   
/*     */   public GeometryType createGeometryType(Name name, Class binding, CoordinateReferenceSystem crs, boolean isIdentifiable, boolean isAbstract, List<Filter> restrictions, AttributeType superType, InternationalString description) {
/* 140 */     return new GeometryTypeImpl(name, binding, crs, isIdentifiable, isAbstract, restrictions, superType, description);
/*     */   }
/*     */   
/*     */   public FeatureType createFeatureType(Name name, Collection<PropertyDescriptor> schema, GeometryDescriptor defaultGeometry, boolean isAbstract, List<Filter> restrictions, AttributeType superType, InternationalString description) {
/* 148 */     return new FeatureTypeImpl(name, schema, defaultGeometry, isAbstract, restrictions, superType, description);
/*     */   }
/*     */   
/*     */   public SimpleFeatureType createSimpleFeatureType(Name name, List<AttributeDescriptor> schema, GeometryDescriptor defaultGeometry, boolean isAbstract, List<Filter> restrictions, AttributeType superType, InternationalString description) {
/* 158 */     return (SimpleFeatureType)new SimpleFeatureTypeImpl(name, schema, defaultGeometry, isAbstract, restrictions, superType, description);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\type\FeatureTypeFactoryImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
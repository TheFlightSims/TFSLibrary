/*     */ package org.geotools.feature;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.geotools.feature.type.AttributeDescriptorImpl;
/*     */ import org.geotools.feature.type.ComplexTypeImpl;
/*     */ import org.geotools.feature.type.Types;
/*     */ import org.opengis.feature.Association;
/*     */ import org.opengis.feature.Attribute;
/*     */ import org.opengis.feature.ComplexAttribute;
/*     */ import org.opengis.feature.Feature;
/*     */ import org.opengis.feature.FeatureFactory;
/*     */ import org.opengis.feature.GeometryAttribute;
/*     */ import org.opengis.feature.Property;
/*     */ import org.opengis.feature.type.AssociationDescriptor;
/*     */ import org.opengis.feature.type.AttributeDescriptor;
/*     */ import org.opengis.feature.type.AttributeType;
/*     */ import org.opengis.feature.type.ComplexType;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ import org.opengis.feature.type.GeometryDescriptor;
/*     */ import org.opengis.feature.type.Name;
/*     */ import org.opengis.feature.type.PropertyDescriptor;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ 
/*     */ public class AttributeBuilder {
/*  63 */   protected static final ComplexType ANYTYPE_TYPE = (ComplexType)new ComplexTypeImpl(new NameImpl("http://www.w3.org/2001/XMLSchema", "anyType"), null, false, true, Collections.EMPTY_LIST, null, null);
/*     */   
/*     */   FeatureFactory attributeFactory;
/*     */   
/*     */   String namespace;
/*     */   
/*     */   AttributeType type;
/*     */   
/*     */   AttributeDescriptor descriptor;
/*     */   
/*     */   List properties;
/*     */   
/*     */   CoordinateReferenceSystem crs;
/*     */   
/*     */   Object defaultGeometry;
/*     */   
/*     */   public AttributeBuilder(FeatureFactory attributeFactory) {
/* 105 */     this.attributeFactory = attributeFactory;
/*     */   }
/*     */   
/*     */   public FeatureFactory getFeatureFactory() {
/* 117 */     return this.attributeFactory;
/*     */   }
/*     */   
/*     */   public void setFeatureFactory(FeatureFactory attributeFactory) {
/* 124 */     this.attributeFactory = attributeFactory;
/*     */   }
/*     */   
/*     */   public void init() {
/* 136 */     this.descriptor = null;
/* 137 */     this.type = null;
/* 138 */     this.properties = null;
/* 139 */     this.crs = null;
/* 140 */     this.defaultGeometry = null;
/*     */   }
/*     */   
/*     */   public void init(Attribute attribute) {
/* 150 */     init();
/* 152 */     this.descriptor = attribute.getDescriptor();
/* 153 */     this.type = attribute.getType();
/* 155 */     if (attribute instanceof ComplexAttribute) {
/* 156 */       ComplexAttribute complex = (ComplexAttribute)attribute;
/* 157 */       Collection properties = complex.getValue();
/* 158 */       for (Iterator<Property> itr = properties.iterator(); itr.hasNext(); ) {
/* 159 */         Property property = itr.next();
/* 160 */         if (property instanceof Attribute) {
/* 161 */           Attribute att = (Attribute)property;
/* 162 */           add((att.getIdentifier() == null) ? null : att.getIdentifier().toString(), att.getValue(), att.getName());
/*     */           continue;
/*     */         } 
/* 164 */         if (property instanceof Association) {
/* 165 */           Association assoc = (Association)property;
/* 166 */           associate(assoc.getValue(), assoc.getName());
/*     */         } 
/*     */       } 
/*     */     } 
/* 171 */     if (attribute instanceof Feature) {
/* 172 */       Feature feature = (Feature)attribute;
/* 174 */       if (feature.getDefaultGeometryProperty() != null) {
/* 175 */         if (this.crs == null)
/* 176 */           this.crs = feature.getDefaultGeometryProperty().getType().getCoordinateReferenceSystem(); 
/* 179 */         this.defaultGeometry = feature.getDefaultGeometryProperty().getValue();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setNamespaceURI(String namespace) {
/* 189 */     this.namespace = namespace;
/*     */   }
/*     */   
/*     */   public String getNamespaceURI() {
/* 198 */     return this.namespace;
/*     */   }
/*     */   
/*     */   public void setType(AttributeType type) {
/* 209 */     this.type = type;
/* 210 */     this.descriptor = null;
/*     */   }
/*     */   
/*     */   public void setDescriptor(AttributeDescriptor descriptor) {
/* 221 */     this.descriptor = descriptor;
/* 222 */     this.type = descriptor.getType();
/*     */   }
/*     */   
/*     */   public AttributeType getType() {
/* 229 */     return this.type;
/*     */   }
/*     */   
/*     */   public void setCRS(CoordinateReferenceSystem crs) {
/* 237 */     this.crs = crs;
/*     */   }
/*     */   
/*     */   public CoordinateReferenceSystem getCRS(Object geom) {
/* 244 */     if (this.crs != null)
/* 245 */       return this.crs; 
/* 246 */     if (geom != null && geom instanceof Geometry) {
/* 247 */       Object userData = ((Geometry)geom).getUserData();
/* 248 */       if (userData != null && userData instanceof CoordinateReferenceSystem)
/* 249 */         return (CoordinateReferenceSystem)userData; 
/*     */     } 
/* 252 */     return null;
/*     */   }
/*     */   
/*     */   public void setDefaultGeometry(Object defaultGeometry) {
/* 259 */     this.defaultGeometry = defaultGeometry;
/*     */   }
/*     */   
/*     */   public Object getDefaultGeometry() {
/* 266 */     return this.defaultGeometry;
/*     */   }
/*     */   
/*     */   public Attribute add(Object value, String name) {
/* 290 */     return add((String)null, value, name);
/*     */   }
/*     */   
/*     */   public void associate(Attribute value, String name) {
/* 310 */     associate(value, name, this.namespace);
/*     */   }
/*     */   
/*     */   public Attribute add(Object value, String name, String namespaceURI) {
/* 328 */     return add(null, value, name, namespaceURI);
/*     */   }
/*     */   
/*     */   public void associate(Attribute attribute, String name, String namespaceURI) {
/* 346 */     associate(attribute, Types.typeName(namespaceURI, name));
/*     */   }
/*     */   
/*     */   public Attribute add(Object value, Name name) {
/* 363 */     return add((String)null, value, name);
/*     */   }
/*     */   
/*     */   public void associate(Attribute value, Name name) {
/* 381 */     AssociationDescriptor descriptor = associationDescriptor(name);
/* 382 */     Association association = this.attributeFactory.createAssociation(value, descriptor);
/* 384 */     properties().add(association);
/*     */   }
/*     */   
/*     */   public Attribute add(String id, Object value, String name) {
/* 405 */     return add(id, value, name, this.namespace);
/*     */   }
/*     */   
/*     */   public Attribute add(String id, Object value, String name, String namespaceURI) {
/* 425 */     return add(id, value, Types.typeName(namespaceURI, name));
/*     */   }
/*     */   
/*     */   public Attribute add(String id, Object value, Name name) {
/* 444 */     AttributeDescriptor descriptor = attributeDescriptor(name);
/* 445 */     Attribute attribute = create(value, null, descriptor, id);
/* 446 */     properties().add(attribute);
/* 447 */     return attribute;
/*     */   }
/*     */   
/*     */   protected List properties() {
/* 454 */     if (this.properties == null)
/* 455 */       this.properties = new ArrayList(); 
/* 458 */     return this.properties;
/*     */   }
/*     */   
/*     */   protected AssociationDescriptor associationDescriptor(Name name) {
/* 462 */     PropertyDescriptor descriptor = Types.descriptor((ComplexType)this.type, name);
/* 464 */     if (descriptor == null) {
/* 465 */       String msg = "Could not locate association: " + name + " in type: " + this.type.getName();
/* 466 */       throw new IllegalArgumentException(msg);
/*     */     } 
/* 469 */     if (!(descriptor instanceof AssociationDescriptor)) {
/* 470 */       String msg = name + " references a non association";
/* 471 */       throw new IllegalArgumentException(msg);
/*     */     } 
/* 474 */     return (AssociationDescriptor)descriptor;
/*     */   }
/*     */   
/*     */   protected AttributeDescriptor attributeDescriptor(Name name) {
/* 478 */     PropertyDescriptor descriptor = Types.findDescriptor((ComplexType)this.type, name);
/* 480 */     if (descriptor == null) {
/* 481 */       String msg = "Could not locate attribute: " + name + " in type: " + this.type.getName();
/* 482 */       throw new IllegalArgumentException(msg);
/*     */     } 
/* 485 */     if (!(descriptor instanceof AttributeDescriptor)) {
/* 486 */       String msg = name + " references a non attribute";
/* 487 */       throw new IllegalArgumentException(msg);
/*     */     } 
/* 490 */     return (AttributeDescriptor)descriptor;
/*     */   }
/*     */   
/*     */   protected Attribute create(Object value, AttributeType type, AttributeDescriptor descriptor, String id) {
/* 499 */     if (descriptor != null)
/* 500 */       type = descriptor.getType(); 
/* 507 */     if (type instanceof FeatureType)
/* 508 */       return (descriptor != null) ? (Attribute)this.attributeFactory.createFeature((Collection)value, descriptor, id) : (Attribute)this.attributeFactory.createFeature((Collection)value, (FeatureType)type, id); 
/* 511 */     if (type instanceof ComplexType)
/* 512 */       return (Attribute)createComplexAttribute(value, (ComplexType)type, descriptor, id); 
/* 513 */     if (type instanceof org.opengis.feature.type.GeometryType)
/* 514 */       return (Attribute)this.attributeFactory.createGeometryAttribute(value, (GeometryDescriptor)descriptor, id, getCRS(value)); 
/* 517 */     return this.attributeFactory.createAttribute(value, descriptor, id);
/*     */   }
/*     */   
/*     */   public ComplexAttribute createComplexAttribute(Object value, ComplexType type, AttributeDescriptor descriptor, String id) {
/* 532 */     return (descriptor != null) ? this.attributeFactory.createComplexAttribute((Collection)value, descriptor, id) : this.attributeFactory.createComplexAttribute((Collection)value, type, id);
/*     */   }
/*     */   
/*     */   public Attribute build() {
/* 547 */     return build(null);
/*     */   }
/*     */   
/*     */   public Attribute build(String id) {
/* 563 */     Attribute built = create(properties(), this.type, this.descriptor, id);
/* 572 */     if (built instanceof Feature) {
/* 573 */       Feature feature = (Feature)built;
/* 575 */       if (this.defaultGeometry != null)
/* 576 */         for (Iterator<Attribute> itr = feature.getProperties().iterator(); itr.hasNext(); ) {
/* 577 */           Attribute att = itr.next();
/* 578 */           if (att instanceof GeometryAttribute && 
/* 579 */             this.defaultGeometry.equals(att.getValue()))
/* 580 */             feature.setDefaultGeometryProperty((GeometryAttribute)att); 
/*     */         }  
/*     */     } 
/* 587 */     properties().clear();
/* 588 */     return built;
/*     */   }
/*     */   
/*     */   public Attribute addAnyTypeValue(Object value, AttributeType type, AttributeDescriptor descriptor, String id) {
/* 606 */     Attribute attribute = create(value, type, descriptor, id);
/* 607 */     properties().add(attribute);
/* 608 */     return attribute;
/*     */   }
/*     */   
/*     */   public Attribute addComplexAnyTypeAttribute(Object value, AttributeDescriptor descriptor, String id) {
/* 624 */     Map<Object, Object> userData = descriptor.getUserData();
/* 625 */     AttributeDescriptorImpl attributeDescriptorImpl = new AttributeDescriptorImpl((AttributeType)ANYTYPE_TYPE, descriptor.getName(), descriptor.getMinOccurs(), descriptor.getMaxOccurs(), descriptor.isNillable(), descriptor.getDefaultValue());
/* 628 */     attributeDescriptorImpl.getUserData().putAll(userData);
/* 629 */     return (Attribute)createComplexAttribute(value, ANYTYPE_TYPE, (AttributeDescriptor)attributeDescriptorImpl, id);
/*     */   }
/*     */   
/*     */   public AttributeDescriptor getDescriptor() {
/* 636 */     return this.descriptor;
/*     */   }
/*     */   
/*     */   public CoordinateReferenceSystem getCRS() {
/* 643 */     return this.crs;
/*     */   }
/*     */   
/*     */   protected List getProperties() {
/* 650 */     if (this.properties == null)
/* 651 */       this.properties = new ArrayList(); 
/* 654 */     return this.properties;
/*     */   }
/*     */   
/*     */   public Attribute buildSimple(String id, Object value) {
/* 658 */     return create(value, this.type, this.descriptor, id);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\AttributeBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
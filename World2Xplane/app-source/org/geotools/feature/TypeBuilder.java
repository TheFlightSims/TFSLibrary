/*      */ package org.geotools.feature;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.Collections;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import org.geotools.feature.type.Types;
/*      */ import org.geotools.referencing.CRS;
/*      */ import org.opengis.feature.type.AssociationDescriptor;
/*      */ import org.opengis.feature.type.AssociationType;
/*      */ import org.opengis.feature.type.AttributeDescriptor;
/*      */ import org.opengis.feature.type.AttributeType;
/*      */ import org.opengis.feature.type.ComplexType;
/*      */ import org.opengis.feature.type.FeatureType;
/*      */ import org.opengis.feature.type.FeatureTypeFactory;
/*      */ import org.opengis.feature.type.GeometryDescriptor;
/*      */ import org.opengis.feature.type.GeometryType;
/*      */ import org.opengis.feature.type.Name;
/*      */ import org.opengis.feature.type.PropertyDescriptor;
/*      */ import org.opengis.feature.type.PropertyType;
/*      */ import org.opengis.feature.type.Schema;
/*      */ import org.opengis.filter.Filter;
/*      */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*      */ import org.opengis.util.InternationalString;
/*      */ 
/*      */ public class TypeBuilder {
/*      */   private FeatureTypeFactory factory;
/*      */   
/*      */   private String namespace;
/*      */   
/*      */   private String name;
/*      */   
/*      */   private Class<?> binding;
/*      */   
/*      */   private InternationalString description;
/*      */   
/*      */   private boolean isIdentified;
/*      */   
/*      */   private boolean isAbstract;
/*      */   
/*      */   private List<Filter> restrictions;
/*      */   
/*      */   private PropertyType superType;
/*      */   
/*      */   private boolean isNillable;
/*      */   
/*      */   private Map<Class<?>, AttributeType> bindings;
/*      */   
/*      */   private Collection<PropertyDescriptor> properties;
/*      */   
/*      */   private int minOccurs;
/*      */   
/*      */   private int maxOccurs;
/*      */   
/*  414 */   private Name defaultGeom = null;
/*      */   
/*  416 */   private CoordinateReferenceSystem crs = null;
/*      */   
/*      */   private AttributeType referenceType;
/*      */   
/*      */   protected Collection members;
/*      */   
/*      */   private PropertyType propertyType;
/*      */   
/*      */   public TypeBuilder(FeatureTypeFactory factory) {
/*  435 */     this.factory = factory;
/*  436 */     init();
/*      */   }
/*      */   
/*      */   public FeatureTypeFactory getTypeFactory() {
/*  440 */     return this.factory;
/*      */   }
/*      */   
/*      */   public void setTypeFactory(FeatureTypeFactory factory) {
/*  444 */     this.factory = factory;
/*      */   }
/*      */   
/*      */   public void reset() {
/*  464 */     this.name = null;
/*  465 */     this.description = null;
/*  466 */     this.properties = newCollection(this.properties);
/*  467 */     this.members = newCollection(this.members);
/*  468 */     this.defaultGeom = null;
/*      */   }
/*      */   
/*      */   public void clear() {
/*  472 */     this.minOccurs = 1;
/*  473 */     this.maxOccurs = 1;
/*  474 */     this.isNillable = false;
/*      */   }
/*      */   
/*      */   public void init() {
/*  486 */     this.namespace = null;
/*  487 */     this.name = null;
/*  488 */     this.description = null;
/*  489 */     this.isIdentified = false;
/*  491 */     this.binding = null;
/*  492 */     this.isAbstract = false;
/*  493 */     this.restrictions = null;
/*  494 */     this.superType = null;
/*  495 */     this.properties = null;
/*  497 */     this.isNillable = true;
/*  498 */     this.minOccurs = 1;
/*  499 */     this.maxOccurs = 1;
/*  500 */     this.propertyType = null;
/*  502 */     this.defaultGeom = null;
/*  503 */     this.crs = null;
/*  505 */     this.referenceType = null;
/*      */   }
/*      */   
/*      */   public void init(PropertyDescriptor descriptor) {
/*  509 */     init();
/*  510 */     this.namespace = descriptor.getName().getNamespaceURI();
/*  511 */     this.name = descriptor.getName().getLocalPart();
/*  512 */     this.minOccurs = descriptor.getMinOccurs();
/*  513 */     this.maxOccurs = descriptor.getMaxOccurs();
/*  514 */     if (descriptor instanceof AttributeDescriptor) {
/*  515 */       AttributeDescriptor attribute = (AttributeDescriptor)descriptor;
/*  516 */       this.isNillable = attribute.isNillable();
/*  517 */       this.propertyType = (PropertyType)attribute.getType();
/*      */     } 
/*  519 */     if (descriptor instanceof AssociationDescriptor) {
/*  520 */       AssociationDescriptor association = (AssociationDescriptor)descriptor;
/*  521 */       this.propertyType = (PropertyType)association.getType();
/*      */     } 
/*      */   }
/*      */   
/*      */   public void init(PropertyType type) {
/*  526 */     init();
/*  527 */     if (type == null)
/*      */       return; 
/*  530 */     this.namespace = type.getName().getNamespaceURI();
/*  531 */     this.name = type.getName().getLocalPart();
/*  532 */     this.description = type.getDescription();
/*  533 */     this.isAbstract = type.isAbstract();
/*  534 */     this.restrictions = null;
/*  535 */     restrictions().addAll(type.getRestrictions());
/*  537 */     if (type instanceof AssociationType) {
/*  538 */       AssociationType assType = (AssociationType)type;
/*  540 */       this.referenceType = assType.getRelatedType();
/*  541 */       this.superType = (PropertyType)assType.getSuper();
/*      */     } 
/*  543 */     if (type instanceof AttributeType) {
/*  544 */       AttributeType aType = (AttributeType)type;
/*  546 */       this.binding = aType.getBinding();
/*  547 */       this.isIdentified = aType.isIdentified();
/*  548 */       this.superType = (PropertyType)aType.getSuper();
/*      */     } 
/*  550 */     if (type instanceof GeometryType) {
/*  551 */       GeometryType geometryType = (GeometryType)type;
/*  553 */       this.crs = geometryType.getCoordinateReferenceSystem();
/*      */     } 
/*  555 */     if (type instanceof ComplexType) {
/*  556 */       ComplexType cType = (ComplexType)type;
/*  558 */       this.properties = null;
/*  559 */       this.properties.addAll(cType.getDescriptors());
/*      */     } 
/*  561 */     if (type instanceof FeatureType) {
/*  562 */       FeatureType featureType = (FeatureType)type;
/*  563 */       this.defaultGeom = featureType.getGeometryDescriptor().getType().getName();
/*  564 */       this.crs = featureType.getCoordinateReferenceSystem();
/*      */     } 
/*      */   }
/*      */   
/*      */   public AttributeType attribute() {
/*  592 */     AttributeType type = this.factory.createAttributeType(typeName(), getBinding(), isIdentified(), isAbstract(), restrictions(), getSuper(), getDescription());
/*  594 */     reset();
/*  595 */     return type;
/*      */   }
/*      */   
/*      */   public AssociationType association() {
/*  600 */     return this.factory.createAssociationType(typeName(), getReferenceType(), true, this.restrictions, getAssociationSuper(), getDescription());
/*      */   }
/*      */   
/*      */   public GeometryType geometry() {
/*  626 */     return getTypeFactory().createGeometryType(typeName(), getBinding(), getCRS(), isIdentified(), isAbstract(), restrictions(), getSuper(), getDescription());
/*      */   }
/*      */   
/*      */   public ComplexType complex() {
/*  657 */     ComplexType type = getTypeFactory().createComplexType(typeName(), properties(), isIdentified(), isAbstract(), restrictions(), getSuper(), getDescription());
/*  659 */     reset();
/*  660 */     return type;
/*      */   }
/*      */   
/*      */   public AttributeDescriptor attributeDescriptor() {
/*      */     AttributeDescriptor attribute;
/*  678 */     if (this.propertyType instanceof GeometryType) {
/*  679 */       GeometryDescriptor geometryDescriptor = getTypeFactory().createGeometryDescriptor((GeometryType)this.propertyType, typeName(), getMinOccurs(), getMaxOccurs(), isNillable(), null);
/*      */     } else {
/*  683 */       attribute = getTypeFactory().createAttributeDescriptor((AttributeType)this.propertyType, typeName(), getMinOccurs(), getMaxOccurs(), isNillable(), null);
/*      */     } 
/*  687 */     reset();
/*  688 */     return attribute;
/*      */   }
/*      */   
/*      */   public AssociationDescriptor associationDescriptor() {
/*  705 */     AssociationDescriptor association = getTypeFactory().createAssociationDescriptor((AssociationType)this.propertyType, typeName(), getMinOccurs(), getMaxOccurs(), isNillable());
/*  708 */     reset();
/*  709 */     return association;
/*      */   }
/*      */   
/*      */   public FeatureType feature() {
/*  719 */     FeatureType type = this.factory.createFeatureType(typeName(), properties(), defaultGeometry(), isAbstract(), restrictions(), getSuper(), getDescription());
/*  721 */     reset();
/*  722 */     return type;
/*      */   }
/*      */   
/*      */   public void setNamespaceURI(String namespace) {
/*  739 */     this.namespace = namespace;
/*      */   }
/*      */   
/*      */   public String getNamespaceURI() {
/*  743 */     return this.namespace;
/*      */   }
/*      */   
/*      */   public void setName(String name) {
/*  747 */     this.name = name;
/*      */   }
/*      */   
/*      */   public TypeBuilder name(String name) {
/*  751 */     setName(name);
/*  752 */     return this;
/*      */   }
/*      */   
/*      */   public String getName() {
/*  756 */     return this.name;
/*      */   }
/*      */   
/*      */   public void setBinding(Class<?> binding) {
/*  760 */     this.binding = binding;
/*      */   }
/*      */   
/*      */   public TypeBuilder bind(Class<?> binding) {
/*  764 */     setBinding(binding);
/*  765 */     return this;
/*      */   }
/*      */   
/*      */   public PropertyType getPropertyType() {
/*  769 */     return this.propertyType;
/*      */   }
/*      */   
/*      */   public void setPropertyType(PropertyType type) {
/*  773 */     this.propertyType = type;
/*      */   }
/*      */   
/*      */   public TypeBuilder property(PropertyType type) {
/*  783 */     setPropertyType(type);
/*  784 */     return this;
/*      */   }
/*      */   
/*      */   public Class<?> getBinding() {
/*  788 */     return this.binding;
/*      */   }
/*      */   
/*      */   public InternationalString getDescription() {
/*  792 */     return this.description;
/*      */   }
/*      */   
/*      */   public void setDescription(InternationalString description) {
/*  796 */     this.description = description;
/*      */   }
/*      */   
/*      */   public void setAbstract(boolean isAbstract) {
/*  800 */     this.isAbstract = isAbstract;
/*      */   }
/*      */   
/*      */   public boolean isAbstract() {
/*  804 */     return this.isAbstract;
/*      */   }
/*      */   
/*      */   public TypeBuilder nillable(boolean isNillable) {
/*  808 */     this.isNillable = isNillable;
/*  809 */     return this;
/*      */   }
/*      */   
/*      */   public void setNillable(boolean isNillable) {
/*  813 */     this.isNillable = isNillable;
/*      */   }
/*      */   
/*      */   public boolean isNillable() {
/*  817 */     return this.isNillable;
/*      */   }
/*      */   
/*      */   public void setIdentified(boolean isIdentified) {
/*  821 */     this.isIdentified = isIdentified;
/*      */   }
/*      */   
/*      */   public boolean isIdentified() {
/*  825 */     return this.isIdentified;
/*      */   }
/*      */   
/*      */   public void setSuper(PropertyType superType) {
/*  829 */     this.superType = superType;
/*      */   }
/*      */   
/*      */   public AttributeType getSuper() {
/*  833 */     return (AttributeType)this.superType;
/*      */   }
/*      */   
/*      */   public AssociationType getAssociationSuper() {
/*  837 */     return (AssociationType)this.superType;
/*      */   }
/*      */   
/*      */   public void addRestriction(Filter restriction) {
/*  841 */     this.restrictions.add(restriction);
/*      */   }
/*      */   
/*      */   public List<Filter> getRestrictions() {
/*  845 */     return Collections.unmodifiableList(this.restrictions);
/*      */   }
/*      */   
/*      */   public void setRestrictions(List<Filter> restrictions) {
/*  849 */     this.restrictions = restrictions;
/*      */   }
/*      */   
/*      */   protected List<Filter> restrictions() {
/*  856 */     if (this.restrictions == null)
/*  857 */       this.restrictions = createRestrictionSet(); 
/*  859 */     return this.restrictions;
/*      */   }
/*      */   
/*      */   protected List<Filter> createRestrictionSet() {
/*  869 */     return new ArrayList<Filter>();
/*      */   }
/*      */   
/*      */   protected Name typeName() {
/*  883 */     if (this.name != null)
/*  885 */       return createTypeName(this.namespace, this.name); 
/*  889 */     return null;
/*      */   }
/*      */   
/*      */   protected Name createTypeName(String ns, String local) {
/*  898 */     return Types.typeName(ns, local);
/*      */   }
/*      */   
/*      */   public AttributeType getBinding(Class binding) {
/*  910 */     AttributeType type = (AttributeType)bindings().get(binding);
/*  911 */     if (type == null)
/*  912 */       throw new IllegalArgumentException("No type bound to: " + binding); 
/*  914 */     return type;
/*      */   }
/*      */   
/*      */   public void addBinding(Class<?> binding, AttributeType type) {
/*  927 */     bindings().put(binding, type);
/*      */   }
/*      */   
/*      */   public void load(Schema schema) {
/*  937 */     for (Iterator<AttributeType> itr = schema.values().iterator(); itr.hasNext(); ) {
/*  938 */       AttributeType type = itr.next();
/*  939 */       addBinding(type.getBinding(), type);
/*      */     } 
/*      */   }
/*      */   
/*      */   public int getMinOccurs() {
/*  944 */     return this.minOccurs;
/*      */   }
/*      */   
/*      */   public TypeBuilder cardinality(int min, int max) {
/*  948 */     this.minOccurs = min;
/*  949 */     this.maxOccurs = max;
/*  950 */     return this;
/*      */   }
/*      */   
/*      */   public void setMinOccurs(int minOccurs) {
/*  954 */     this.minOccurs = minOccurs;
/*      */   }
/*      */   
/*      */   public int getMaxOccurs() {
/*  958 */     return this.maxOccurs;
/*      */   }
/*      */   
/*      */   public void setMaxOccurs(int maxOccurs) {
/*  962 */     this.maxOccurs = maxOccurs;
/*      */   }
/*      */   
/*      */   public TypeBuilder attribute(String name, Class binding) {
/*  978 */     return attribute(this.namespace, name, binding);
/*      */   }
/*      */   
/*      */   public TypeBuilder attribute(String namespaceURI, String name, Class binding) {
/*  992 */     return attribute(createName(namespaceURI, name), binding);
/*      */   }
/*      */   
/*      */   public TypeBuilder attribute(Name name, Class binding) {
/*  996 */     return attribute(name, getBinding(binding));
/*      */   }
/*      */   
/*      */   public TypeBuilder attribute(String name, String namespaceURI, AttributeType type) {
/* 1000 */     attribute(createName(namespaceURI, name), type);
/* 1001 */     return this;
/*      */   }
/*      */   
/*      */   public TypeBuilder attribute(String name, AttributeType type) {
/* 1005 */     attribute(name, getNamespaceURI(), type);
/* 1006 */     return this;
/*      */   }
/*      */   
/*      */   public TypeBuilder attribute(Name name, AttributeType type) {
/* 1011 */     AttributeDescriptor descriptor = getTypeFactory().createAttributeDescriptor(type, name, getMinOccurs(), getMaxOccurs(), isNillable(), null);
/* 1013 */     add((PropertyDescriptor)descriptor);
/* 1014 */     return this;
/*      */   }
/*      */   
/*      */   public void addAttribute(String name, Class binding) {
/* 1018 */     addAttribute(this.namespace, name, binding);
/*      */   }
/*      */   
/*      */   public void addAttribute(String uri, String name, Class binding) {
/* 1022 */     addAttribute(createName(uri, name), binding);
/*      */   }
/*      */   
/*      */   public void addAttribute(Name name, Class binding) {
/* 1026 */     addAttribute(name, getBinding(binding));
/*      */   }
/*      */   
/*      */   public void addAttribute(String name, AttributeType type) {
/* 1030 */     addAttribute(name, getNamespaceURI(), type);
/*      */   }
/*      */   
/*      */   public void addAttribute(String name, String namespaceURI, AttributeType type) {
/* 1034 */     addAttribute(createName(namespaceURI, name), type);
/*      */   }
/*      */   
/*      */   public void addAttribute(Name name, AttributeType type) {
/* 1039 */     AttributeDescriptor descriptor = getTypeFactory().createAttributeDescriptor(type, name, getMinOccurs(), getMaxOccurs(), isNillable(), null);
/* 1041 */     add((PropertyDescriptor)descriptor);
/*      */   }
/*      */   
/*      */   public void setReferenceType(AttributeType reference) {
/* 1048 */     this.referenceType = reference;
/*      */   }
/*      */   
/*      */   public TypeBuilder referenceType(AttributeType reference) {
/* 1052 */     setReferenceType(reference);
/* 1053 */     return this;
/*      */   }
/*      */   
/*      */   public AttributeType getReferenceType() {
/* 1057 */     return this.referenceType;
/*      */   }
/*      */   
/*      */   public TypeBuilder association(String name, AssociationType type) {
/* 1061 */     return association(getNamespaceURI(), name, type);
/*      */   }
/*      */   
/*      */   public TypeBuilder association(String namespaceURI, String name, AssociationType type) {
/* 1065 */     return association(createName(namespaceURI, name), type);
/*      */   }
/*      */   
/*      */   public TypeBuilder association(Name name, AssociationType type) {
/* 1069 */     AssociationDescriptor descriptor = getTypeFactory().createAssociationDescriptor(type, name, getMinOccurs(), getMaxOccurs(), isNillable());
/* 1072 */     add((PropertyDescriptor)descriptor);
/* 1073 */     return this;
/*      */   }
/*      */   
/*      */   public TypeBuilder add(PropertyDescriptor descriptor) {
/* 1085 */     if (!contains(properties(), descriptor))
/* 1086 */       this.properties.add(descriptor); 
/* 1088 */     clear();
/* 1089 */     return this;
/*      */   }
/*      */   
/*      */   public static boolean contains(Collection collection, PropertyDescriptor descriptor) {
/* 1094 */     for (Iterator<PropertyDescriptor> itr = collection.iterator(); itr.hasNext(); ) {
/* 1095 */       PropertyDescriptor d = itr.next();
/* 1096 */       if (d.getName().equals(descriptor.getName()))
/* 1097 */         return true; 
/*      */     } 
/* 1100 */     return false;
/*      */   }
/*      */   
/*      */   public Collection<PropertyDescriptor> getProperties() {
/* 1112 */     if (this.properties == null)
/* 1113 */       this.properties = newCollection(); 
/* 1115 */     return this.properties;
/*      */   }
/*      */   
/*      */   public void setProperties(Collection<PropertyDescriptor> properties) {
/* 1137 */     this.properties = properties;
/*      */   }
/*      */   
/*      */   protected Collection<PropertyDescriptor> newCollection() {
/* 1154 */     return new HashSet<PropertyDescriptor>();
/*      */   }
/*      */   
/*      */   protected Collection newCollection(Collection origional) {
/* 1172 */     if (origional == null)
/* 1173 */       return newCollection(); 
/*      */     try {
/* 1176 */       return (Collection)origional.getClass().newInstance();
/* 1177 */     } catch (InstantiationException e) {
/* 1178 */       return newCollection();
/* 1179 */     } catch (IllegalAccessException e) {
/* 1180 */       return newCollection();
/*      */     } 
/*      */   }
/*      */   
/*      */   protected Collection<PropertyDescriptor> properties() {
/* 1195 */     if (this.properties == null)
/* 1196 */       this.properties = newCollection(); 
/* 1198 */     return this.properties;
/*      */   }
/*      */   
/*      */   protected Map bindings() {
/* 1205 */     if (this.bindings == null)
/* 1206 */       this.bindings = new HashMap<Class<?>, AttributeType>(); 
/* 1208 */     return this.bindings;
/*      */   }
/*      */   
/*      */   protected Name createName(String ns, String local) {
/* 1217 */     return Types.typeName(ns, local);
/*      */   }
/*      */   
/*      */   public void setDefaultGeometry(String name) {
/* 1221 */     setDefaultGeometry(name, getNamespaceURI());
/*      */   }
/*      */   
/*      */   public void setDefaultGeometry(String name, String namespaceURI) {
/* 1225 */     setDefaultGeometry(createName(namespaceURI, name));
/*      */   }
/*      */   
/*      */   public void setDefaultGeometry(Name name) {
/* 1229 */     this.defaultGeom = name;
/*      */   }
/*      */   
/*      */   public TypeBuilder defaultGeometry(String name) {
/* 1233 */     setDefaultGeometry(name);
/* 1234 */     return this;
/*      */   }
/*      */   
/*      */   public Name getDefaultGeometry() {
/* 1238 */     return this.defaultGeom;
/*      */   }
/*      */   
/*      */   protected GeometryDescriptor defaultGeometry() {
/* 1248 */     if (this.defaultGeom != null)
/* 1249 */       for (PropertyDescriptor pd : this.properties) {
/* 1250 */         if (pd.getName().equals(this.defaultGeom))
/* 1251 */           return (GeometryDescriptor)pd; 
/*      */       }  
/* 1257 */     for (PropertyDescriptor pd : this.properties) {
/* 1258 */       if (pd instanceof GeometryDescriptor)
/* 1259 */         return (GeometryDescriptor)pd; 
/*      */     } 
/* 1262 */     return null;
/*      */   }
/*      */   
/*      */   public void setCRS(CoordinateReferenceSystem crs) {
/* 1266 */     this.crs = crs;
/*      */   }
/*      */   
/*      */   public TypeBuilder crs(CoordinateReferenceSystem crs) {
/* 1270 */     setCRS(crs);
/* 1271 */     return this;
/*      */   }
/*      */   
/*      */   public TypeBuilder crs(String SRS) {
/*      */     try {
/* 1291 */       setCRS(CRS.decode(SRS));
/* 1292 */     } catch (Exception e) {
/* 1293 */       IllegalArgumentException iae = new IllegalArgumentException("SRS '" + SRS + "' unknown:" + e);
/* 1295 */       iae.initCause(e);
/* 1296 */       throw iae;
/*      */     } 
/* 1298 */     return this;
/*      */   }
/*      */   
/*      */   public CoordinateReferenceSystem getCRS() {
/* 1302 */     return this.crs;
/*      */   }
/*      */   
/*      */   public Collection getMembers() {
/* 1317 */     if (this.members == null)
/* 1318 */       this.members = newCollection(); 
/* 1320 */     return this.members;
/*      */   }
/*      */   
/*      */   public void setMembers(Collection members) {
/* 1325 */     this.members = members;
/*      */   }
/*      */   
/*      */   protected Collection members() {
/* 1336 */     if (this.members == null)
/* 1337 */       this.members = newCollection(); 
/* 1339 */     return this.members;
/*      */   }
/*      */   
/*      */   public void addMemberType(String name, AssociationType memberType) {
/* 1352 */     addMemberType(getNamespaceURI(), name, memberType);
/*      */   }
/*      */   
/*      */   public void addMemberType(String namespaceURI, String name, AssociationType memberType) {
/* 1365 */     addMemberType(createName(namespaceURI, name), memberType);
/*      */   }
/*      */   
/*      */   public void addMemberType(Name name, AssociationType memberType) {
/* 1378 */     member(name, memberType);
/*      */   }
/*      */   
/*      */   public TypeBuilder member(String name, AssociationType type) {
/* 1392 */     return member(createName(getNamespaceURI(), name), type);
/*      */   }
/*      */   
/*      */   public TypeBuilder member(Name name, AssociationType type) {
/* 1406 */     AssociationDescriptor descriptor = getTypeFactory().createAssociationDescriptor(type, name, getMinOccurs(), getMaxOccurs(), isNillable());
/* 1408 */     clear();
/* 1409 */     return member(descriptor);
/*      */   }
/*      */   
/*      */   public TypeBuilder member(AssociationDescriptor memberOf) {
/* 1413 */     if (!contains(members(), (PropertyDescriptor)memberOf))
/* 1414 */       this.members.add(memberOf); 
/* 1416 */     return this;
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\TypeBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
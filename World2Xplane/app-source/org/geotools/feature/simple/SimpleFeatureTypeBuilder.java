/*      */ package org.geotools.feature.simple;
/*      */ 
/*      */ import com.vividsolutions.jts.geom.Geometry;
/*      */ import java.net.URI;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collections;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import java.util.logging.Logger;
/*      */ import org.geotools.feature.AttributeTypeBuilder;
/*      */ import org.geotools.feature.NameImpl;
/*      */ import org.geotools.feature.type.BasicFeatureTypes;
/*      */ import org.geotools.feature.type.FeatureTypeFactoryImpl;
/*      */ import org.geotools.referencing.CRS;
/*      */ import org.geotools.util.logging.Logging;
/*      */ import org.opengis.feature.simple.SimpleFeatureType;
/*      */ import org.opengis.feature.type.AttributeDescriptor;
/*      */ import org.opengis.feature.type.AttributeType;
/*      */ import org.opengis.feature.type.FeatureTypeFactory;
/*      */ import org.opengis.feature.type.GeometryDescriptor;
/*      */ import org.opengis.feature.type.GeometryType;
/*      */ import org.opengis.feature.type.Name;
/*      */ import org.opengis.feature.type.Schema;
/*      */ import org.opengis.filter.Filter;
/*      */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*      */ import org.opengis.util.InternationalString;
/*      */ 
/*      */ public class SimpleFeatureTypeBuilder {
/*  116 */   static Logger LOGGER = Logging.getLogger("org.geotools.feature");
/*      */   
/*      */   protected FeatureTypeFactory factory;
/*      */   
/*      */   protected Map<Class<?>, AttributeType> bindings;
/*      */   
/*      */   protected String local;
/*      */   
/*      */   protected String uri;
/*      */   
/*      */   protected InternationalString description;
/*      */   
/*      */   protected List<AttributeDescriptor> attributes;
/*      */   
/*      */   protected List<Filter> restrictions;
/*      */   
/*      */   protected String defaultGeometry;
/*      */   
/*      */   protected boolean defaultCrsSet = false;
/*      */   
/*      */   protected CoordinateReferenceSystem defaultCrs;
/*      */   
/*      */   protected boolean isAbstract = false;
/*      */   
/*      */   protected SimpleFeatureType superType;
/*      */   
/*      */   protected AttributeTypeBuilder attributeBuilder;
/*      */   
/*      */   public SimpleFeatureTypeBuilder() {
/*  188 */     this((FeatureTypeFactory)new FeatureTypeFactoryImpl());
/*      */   }
/*      */   
/*      */   public SimpleFeatureTypeBuilder(FeatureTypeFactory factory) {
/*  196 */     this.factory = factory;
/*  198 */     this.attributeBuilder = new AttributeTypeBuilder();
/*  199 */     setBindings((Schema)new SimpleSchema());
/*  200 */     reset();
/*      */   }
/*      */   
/*      */   public void setFeatureTypeFactory(FeatureTypeFactory factory) {
/*  209 */     this.factory = factory;
/*      */   }
/*      */   
/*      */   public FeatureTypeFactory getFeatureTypeFactory() {
/*  215 */     return this.factory;
/*      */   }
/*      */   
/*      */   public void init(SimpleFeatureType type) {
/*  224 */     init();
/*  225 */     if (type == null)
/*      */       return; 
/*  228 */     this.uri = type.getName().getNamespaceURI();
/*  229 */     this.local = type.getName().getLocalPart();
/*  230 */     this.description = type.getDescription();
/*  231 */     this.restrictions = null;
/*  232 */     restrictions().addAll(type.getRestrictions());
/*  233 */     this.defaultCrs = type.getCoordinateReferenceSystem();
/*  234 */     this.defaultCrsSet = true;
/*  235 */     this.attributes = null;
/*  236 */     attributes().addAll(type.getAttributeDescriptors());
/*  238 */     this.isAbstract = type.isAbstract();
/*  239 */     this.superType = (SimpleFeatureType)type.getSuper();
/*      */   }
/*      */   
/*      */   protected void init() {
/*  246 */     this.attributes = null;
/*      */   }
/*      */   
/*      */   protected void reset() {
/*  254 */     this.uri = "http://www.opengis.net/gml";
/*  255 */     this.local = null;
/*  256 */     this.description = null;
/*  257 */     this.restrictions = null;
/*  258 */     this.attributes = null;
/*  259 */     this.defaultCrsSet = false;
/*  260 */     this.defaultCrs = null;
/*  261 */     this.isAbstract = false;
/*  262 */     this.superType = BasicFeatureTypes.FEATURE;
/*      */   }
/*      */   
/*      */   public void setNamespaceURI(String namespaceURI) {
/*  269 */     this.uri = namespaceURI;
/*      */   }
/*      */   
/*      */   public void setNamespaceURI(URI namespaceURI) {
/*  272 */     if (namespaceURI != null) {
/*  273 */       setNamespaceURI(namespaceURI.toString());
/*      */     } else {
/*  276 */       setNamespaceURI((String)null);
/*      */     } 
/*      */   }
/*      */   
/*      */   public String getNamespaceURI() {
/*  283 */     return this.uri;
/*      */   }
/*      */   
/*      */   public void setName(String name) {
/*  289 */     this.local = name;
/*      */   }
/*      */   
/*      */   public String getName() {
/*  295 */     return this.local;
/*      */   }
/*      */   
/*      */   public void setName(Name name) {
/*  302 */     setName(name.getLocalPart());
/*  303 */     setNamespaceURI(name.getNamespaceURI());
/*      */   }
/*      */   
/*      */   public void setDescription(InternationalString description) {
/*  310 */     this.description = description;
/*      */   }
/*      */   
/*      */   public InternationalString getDescription() {
/*  316 */     return this.description;
/*      */   }
/*      */   
/*      */   public void setDefaultGeometry(String defaultGeometryName) {
/*  323 */     this.defaultGeometry = defaultGeometryName;
/*      */   }
/*      */   
/*      */   public String getDefaultGeometry() {
/*  329 */     return this.defaultGeometry;
/*      */   }
/*      */   
/*      */   public void setCRS(CoordinateReferenceSystem crs) {
/*  350 */     this.defaultCrs = crs;
/*  351 */     this.defaultCrsSet = true;
/*      */   }
/*      */   
/*      */   public CoordinateReferenceSystem getCRS() {
/*  359 */     return this.defaultCrs;
/*      */   }
/*      */   
/*      */   public void setSRS(String srs) {
/*  370 */     setCRS(decode(srs));
/*      */   }
/*      */   
/*      */   public void setAbstract(boolean isAbstract) {
/*  377 */     this.isAbstract = isAbstract;
/*      */   }
/*      */   
/*      */   public boolean isAbstract() {
/*  384 */     return this.isAbstract;
/*      */   }
/*      */   
/*      */   public void setSuperType(SimpleFeatureType superType) {
/*  391 */     this.superType = superType;
/*      */   }
/*      */   
/*      */   public SimpleFeatureType getSuperType() {
/*  397 */     return this.superType;
/*      */   }
/*      */   
/*      */   public void addBinding(AttributeType type) {
/*  413 */     bindings().put(type.getBinding(), type);
/*      */   }
/*      */   
/*      */   public void addBindings(Schema schema) {
/*  424 */     for (Iterator<AttributeType> itr = schema.values().iterator(); itr.hasNext(); ) {
/*  425 */       AttributeType type = itr.next();
/*  426 */       addBinding(type);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void setBindings(Schema schema) {
/*  439 */     bindings().clear();
/*  440 */     addBindings(schema);
/*      */   }
/*      */   
/*      */   public AttributeType getBinding(Class<?> binding) {
/*  451 */     return bindings().get(binding);
/*      */   }
/*      */   
/*      */   public SimpleFeatureTypeBuilder minOccurs(int minOccurs) {
/*  463 */     this.attributeBuilder.setMinOccurs(minOccurs);
/*  464 */     return this;
/*      */   }
/*      */   
/*      */   public SimpleFeatureTypeBuilder maxOccurs(int maxOccurs) {
/*  473 */     this.attributeBuilder.setMaxOccurs(maxOccurs);
/*  474 */     return this;
/*      */   }
/*      */   
/*      */   public SimpleFeatureTypeBuilder nillable(boolean isNillable) {
/*  483 */     this.attributeBuilder.setNillable(isNillable);
/*  484 */     return this;
/*      */   }
/*      */   
/*      */   public SimpleFeatureTypeBuilder length(int length) {
/*  496 */     this.attributeBuilder.setLength(length);
/*  497 */     return this;
/*      */   }
/*      */   
/*      */   public SimpleFeatureTypeBuilder restriction(Filter filter) {
/*  507 */     this.attributeBuilder.addRestriction(filter);
/*  508 */     return this;
/*      */   }
/*      */   
/*      */   public SimpleFeatureTypeBuilder restrictions(List<Filter> filters) {
/*  517 */     for (Filter f : filters)
/*  518 */       this.attributeBuilder.addRestriction(f); 
/*  520 */     return this;
/*      */   }
/*      */   
/*      */   public SimpleFeatureTypeBuilder description(String description) {
/*  530 */     this.attributeBuilder.setDescription(description);
/*  531 */     return this;
/*      */   }
/*      */   
/*      */   public SimpleFeatureTypeBuilder defaultValue(Object defaultValue) {
/*  540 */     this.attributeBuilder.setDefaultValue(defaultValue);
/*  541 */     return this;
/*      */   }
/*      */   
/*      */   public SimpleFeatureTypeBuilder crs(CoordinateReferenceSystem crs) {
/*  555 */     this.attributeBuilder.setCRS(crs);
/*  556 */     return this;
/*      */   }
/*      */   
/*      */   public SimpleFeatureTypeBuilder srs(String srs) {
/*  575 */     if (srs == null)
/*  576 */       return crs(null); 
/*  579 */     return crs(decode(srs));
/*      */   }
/*      */   
/*      */   public SimpleFeatureTypeBuilder srid(Integer srid) {
/*  598 */     if (srid == null)
/*  599 */       return crs(null); 
/*  602 */     return crs(decode("EPSG:" + srid));
/*      */   }
/*      */   
/*      */   public SimpleFeatureTypeBuilder userData(Object key, Object value) {
/*  614 */     this.attributeBuilder.addUserData(key, value);
/*  615 */     return this;
/*      */   }
/*      */   
/*      */   public SimpleFeatureTypeBuilder descriptor(AttributeDescriptor descriptor) {
/*  629 */     minOccurs(descriptor.getMinOccurs());
/*  630 */     maxOccurs(descriptor.getMaxOccurs());
/*  631 */     nillable(descriptor.isNillable());
/*  633 */     defaultValue(descriptor.getDefaultValue());
/*  635 */     if (descriptor instanceof GeometryDescriptor)
/*  636 */       crs(((GeometryDescriptor)descriptor).getCoordinateReferenceSystem()); 
/*  639 */     return this;
/*      */   }
/*      */   
/*      */   public void add(String name, Class<?> binding) {
/*  662 */     AttributeDescriptor descriptor = null;
/*  664 */     this.attributeBuilder.setBinding(binding);
/*  665 */     this.attributeBuilder.setName(name);
/*  673 */     if ((this.defaultGeometry != null && this.defaultGeometry.equals(name)) || Geometry.class.isAssignableFrom(binding)) {
/*  677 */       if (!this.attributeBuilder.isCRSSet()) {
/*  678 */         if (this.defaultCrs == null && !this.defaultCrsSet)
/*  679 */           LOGGER.warning("Creating " + name + " with null CoordinateReferenceSystem - did you mean to setCRS?"); 
/*  681 */         this.attributeBuilder.setCRS(this.defaultCrs);
/*      */       } 
/*  684 */       GeometryType type = this.attributeBuilder.buildGeometryType();
/*  685 */       GeometryDescriptor geometryDescriptor = this.attributeBuilder.buildDescriptor(name, type);
/*      */     } else {
/*  687 */       AttributeType type = this.attributeBuilder.buildType();
/*  688 */       descriptor = this.attributeBuilder.buildDescriptor(name, type);
/*      */     } 
/*  691 */     attributes().add(descriptor);
/*      */   }
/*      */   
/*      */   public void add(AttributeDescriptor descriptor) {
/*  701 */     attributes().add(descriptor);
/*      */   }
/*      */   
/*      */   public AttributeDescriptor remove(String attributeName) {
/*  713 */     for (Iterator<AttributeDescriptor> iterator = this.attributes.iterator(); iterator.hasNext(); ) {
/*  714 */       AttributeDescriptor descriptor = iterator.next();
/*  715 */       if (descriptor.getLocalName().equals(attributeName)) {
/*  716 */         iterator.remove();
/*  717 */         return descriptor;
/*      */       } 
/*      */     } 
/*  720 */     throw new IllegalArgumentException(attributeName + " is not an existing attribute descriptor in this builder");
/*      */   }
/*      */   
/*      */   public void add(int index, AttributeDescriptor descriptor) {
/*  730 */     attributes().add(index, descriptor);
/*      */   }
/*      */   
/*      */   public void addAll(List<AttributeDescriptor> descriptors) {
/*  742 */     if (descriptors != null)
/*  743 */       for (AttributeDescriptor ad : descriptors)
/*  744 */         add(ad);  
/*      */   }
/*      */   
/*      */   public void addAll(AttributeDescriptor[] descriptors) {
/*  754 */     if (descriptors != null)
/*  755 */       for (AttributeDescriptor ad : descriptors)
/*  756 */         add(ad);  
/*      */   }
/*      */   
/*      */   public void add(String name, Class<?> binding, CoordinateReferenceSystem crs) {
/*  772 */     this.attributeBuilder.setBinding(binding);
/*  773 */     this.attributeBuilder.setName(name);
/*  774 */     this.attributeBuilder.setCRS(crs);
/*  776 */     GeometryType type = this.attributeBuilder.buildGeometryType();
/*  777 */     GeometryDescriptor descriptor = this.attributeBuilder.buildDescriptor(name, type);
/*  778 */     attributes().add(descriptor);
/*      */   }
/*      */   
/*      */   public void add(String name, Class<?> binding, String srs) {
/*  792 */     if (srs == null) {
/*  793 */       add(name, binding, (CoordinateReferenceSystem)null);
/*      */       return;
/*      */     } 
/*  797 */     add(name, binding, decode(srs));
/*      */   }
/*      */   
/*      */   public void add(String name, Class<?> binding, Integer srid) {
/*  811 */     if (srid == null) {
/*  812 */       add(name, binding, (CoordinateReferenceSystem)null);
/*      */       return;
/*      */     } 
/*  816 */     add(name, binding, decode("EPSG:" + srid));
/*      */   }
/*      */   
/*      */   public void setAttributes(List<AttributeDescriptor> attributes) {
/*  824 */     List<AttributeDescriptor> atts = attributes();
/*  825 */     atts.clear();
/*  826 */     if (attributes != null)
/*  827 */       atts.addAll(attributes); 
/*      */   }
/*      */   
/*      */   public void setAttributes(AttributeDescriptor[] attributes) {
/*  835 */     List<AttributeDescriptor> atts = attributes();
/*  836 */     atts.clear();
/*  837 */     if (attributes != null)
/*  838 */       atts.addAll(Arrays.asList(attributes)); 
/*      */   }
/*      */   
/*      */   public SimpleFeatureType buildFeatureType() {
/*  850 */     GeometryDescriptor defGeom = null;
/*  853 */     if (this.defaultGeometry != null) {
/*  854 */       List<AttributeDescriptor> atts = attributes();
/*  855 */       for (int i = 0; i < atts.size(); i++) {
/*  856 */         AttributeDescriptor att = atts.get(i);
/*  857 */         if (this.defaultGeometry.equals(att.getName().getLocalPart())) {
/*      */           GeometryDescriptor geometryDescriptor;
/*  860 */           if (!(att instanceof GeometryDescriptor)) {
/*  861 */             LOGGER.warning("Default Geometry " + this.defaultGeometry + " was added as a geoemtry");
/*  862 */             this.attributeBuilder.init(att);
/*  863 */             this.attributeBuilder.setCRS(this.defaultCrs);
/*  864 */             GeometryType type = this.attributeBuilder.buildGeometryType();
/*  866 */             geometryDescriptor = this.attributeBuilder.buildDescriptor(att.getName(), type);
/*  867 */             atts.set(i, geometryDescriptor);
/*      */           } 
/*  869 */           defGeom = geometryDescriptor;
/*      */           break;
/*      */         } 
/*      */       } 
/*  874 */       if (defGeom == null) {
/*  875 */         String msg = "'" + this.defaultGeometry + " specified as default" + " but could find no such attribute.";
/*  877 */         throw new IllegalArgumentException(msg);
/*      */       } 
/*      */     } 
/*  881 */     if (defGeom == null)
/*  883 */       for (AttributeDescriptor att : attributes()) {
/*  884 */         if (att instanceof GeometryDescriptor) {
/*  885 */           defGeom = (GeometryDescriptor)att;
/*      */           break;
/*      */         } 
/*      */       }  
/*  891 */     SimpleFeatureType built = this.factory.createSimpleFeatureType(name(), attributes(), defGeom, this.isAbstract, restrictions(), (AttributeType)this.superType, this.description);
/*  895 */     init();
/*  896 */     return built;
/*      */   }
/*      */   
/*      */   protected Set newSet() {
/*  905 */     return new HashSet();
/*      */   }
/*      */   
/*      */   protected List newList() {
/*  913 */     return new ArrayList();
/*      */   }
/*      */   
/*      */   protected Map newMap() {
/*  921 */     return new HashMap<Object, Object>();
/*      */   }
/*      */   
/*      */   protected List newList(List origional) {
/*  933 */     if (origional == null)
/*  934 */       return newList(); 
/*  936 */     if (origional == Collections.EMPTY_LIST)
/*  937 */       return newList(); 
/*      */     try {
/*  940 */       return (List)origional.getClass().newInstance();
/*  941 */     } catch (InstantiationException e) {
/*  942 */       return newList();
/*  943 */     } catch (IllegalAccessException e) {
/*  944 */       return newList();
/*      */     } 
/*      */   }
/*      */   
/*      */   protected Name name() {
/*  960 */     if (this.local == null)
/*  961 */       return null; 
/*  963 */     return (Name)new NameImpl(this.uri, this.local);
/*      */   }
/*      */   
/*      */   protected List<AttributeDescriptor> attributes() {
/*  971 */     if (this.attributes == null)
/*  972 */       this.attributes = newList(); 
/*  974 */     return this.attributes;
/*      */   }
/*      */   
/*      */   protected List<Filter> restrictions() {
/*  982 */     if (this.restrictions == null)
/*  983 */       this.restrictions = newList(); 
/*  985 */     return this.restrictions;
/*      */   }
/*      */   
/*      */   protected Map<Class<?>, AttributeType> bindings() {
/*  993 */     if (this.bindings == null)
/*  994 */       this.bindings = newMap(); 
/*  996 */     return this.bindings;
/*      */   }
/*      */   
/*      */   protected CoordinateReferenceSystem decode(String srs) {
/*      */     try {
/* 1004 */       return CRS.decode(srs);
/* 1005 */     } catch (Exception e) {
/* 1006 */       String msg = "SRS '" + srs + "' unknown:" + e.getLocalizedMessage();
/* 1007 */       throw (IllegalArgumentException)(new IllegalArgumentException(msg)).initCause(e);
/*      */     } 
/*      */   }
/*      */   
/*      */   public static SimpleFeatureType retype(SimpleFeatureType original, String[] types) {
/* 1018 */     return retype(original, Arrays.asList(types));
/*      */   }
/*      */   
/*      */   public static SimpleFeatureType retype(SimpleFeatureType original, List<String> types) {
/* 1022 */     SimpleFeatureTypeBuilder b = new SimpleFeatureTypeBuilder();
/* 1025 */     b.init(original);
/* 1028 */     b.attributes().clear();
/* 1031 */     for (int i = 0; i < types.size(); i++)
/* 1032 */       b.add(original.getDescriptor(types.get(i))); 
/* 1035 */     return b.buildFeatureType();
/*      */   }
/*      */   
/*      */   public static SimpleFeatureType retype(SimpleFeatureType original, CoordinateReferenceSystem crs) {
/* 1045 */     SimpleFeatureTypeBuilder b = new SimpleFeatureTypeBuilder();
/* 1048 */     b.init(original);
/* 1051 */     b.attributes().clear();
/* 1054 */     for (AttributeDescriptor descriptor : original.getAttributeDescriptors()) {
/* 1055 */       if (descriptor instanceof GeometryDescriptor) {
/* 1056 */         GeometryDescriptor geometryDescriptor = (GeometryDescriptor)descriptor;
/* 1057 */         AttributeTypeBuilder adjust = new AttributeTypeBuilder(b.factory);
/* 1058 */         adjust.init((AttributeDescriptor)geometryDescriptor);
/* 1059 */         adjust.setCRS(crs);
/* 1060 */         b.add(adjust.buildDescriptor(geometryDescriptor.getLocalName()));
/*      */         continue;
/*      */       } 
/* 1063 */       b.add(descriptor);
/*      */     } 
/* 1065 */     return b.buildFeatureType();
/*      */   }
/*      */   
/*      */   public static SimpleFeatureType copy(SimpleFeatureType original) {
/* 1075 */     SimpleFeatureTypeBuilder b = new SimpleFeatureTypeBuilder();
/* 1078 */     b.init(original);
/* 1081 */     b.attributes().clear();
/* 1084 */     for (AttributeDescriptor descriptor : original.getAttributeDescriptors()) {
/* 1085 */       AttributeTypeBuilder ab = new AttributeTypeBuilder(b.factory);
/* 1086 */       ab.init(descriptor);
/* 1087 */       b.add(ab.buildDescriptor(descriptor.getLocalName()));
/*      */     } 
/* 1089 */     return b.buildFeatureType();
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\simple\SimpleFeatureTypeBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
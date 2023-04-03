/*     */ package org.geotools.feature;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.geotools.data.DataUtilities;
/*     */ import org.geotools.factory.CommonFactoryFinder;
/*     */ import org.geotools.feature.type.FeatureTypeFactoryImpl;
/*     */ import org.geotools.filter.IllegalFilterException;
/*     */ import org.geotools.filter.LengthFunction;
/*     */ import org.geotools.resources.Classes;
/*     */ import org.geotools.util.SimpleInternationalString;
/*     */ import org.opengis.feature.type.AttributeDescriptor;
/*     */ import org.opengis.feature.type.AttributeType;
/*     */ import org.opengis.feature.type.FeatureTypeFactory;
/*     */ import org.opengis.feature.type.GeometryDescriptor;
/*     */ import org.opengis.feature.type.GeometryType;
/*     */ import org.opengis.feature.type.Name;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.filter.FilterFactory;
/*     */ import org.opengis.filter.PropertyIsLessThanOrEqualTo;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class AttributeTypeBuilder {
/*     */   protected FeatureTypeFactory factory;
/*     */   
/*     */   protected String name;
/*     */   
/* 113 */   private String separator = ":";
/*     */   
/*     */   protected String namespaceURI;
/*     */   
/*     */   protected boolean isAbstract = false;
/*     */   
/*     */   protected List<Filter> restrictions;
/*     */   
/*     */   protected String description;
/*     */   
/*     */   protected boolean isIdentifiable = false;
/*     */   
/*     */   protected Class binding;
/*     */   
/*     */   protected AttributeType superType;
/*     */   
/*     */   protected Object defaultValue;
/*     */   
/*     */   protected boolean isDefaultValueSet = false;
/*     */   
/*     */   protected CoordinateReferenceSystem crs;
/*     */   
/*     */   protected boolean isCrsSet = false;
/*     */   
/* 161 */   protected Integer minOccurs = null;
/*     */   
/* 167 */   protected Integer maxOccurs = null;
/*     */   
/*     */   protected boolean isNillable = true;
/*     */   
/* 183 */   protected Integer length = null;
/*     */   
/* 188 */   protected Map userData = null;
/*     */   
/* 193 */   protected FilterFactory ff = CommonFactoryFinder.getFilterFactory(null);
/*     */   
/*     */   public AttributeTypeBuilder() {
/* 200 */     this((FeatureTypeFactory)new FeatureTypeFactoryImpl());
/* 201 */     init();
/*     */   }
/*     */   
/*     */   public AttributeTypeBuilder(FeatureTypeFactory factory) {
/* 211 */     this.factory = factory;
/* 212 */     init();
/*     */   }
/*     */   
/*     */   protected void init() {
/* 219 */     resetTypeState();
/* 220 */     resetDescriptorState();
/*     */   }
/*     */   
/*     */   protected void resetTypeState() {
/* 231 */     this.name = null;
/* 232 */     this.namespaceURI = null;
/* 233 */     this.isAbstract = false;
/* 234 */     this.restrictions = null;
/* 235 */     this.description = null;
/* 236 */     this.isIdentifiable = false;
/* 237 */     this.binding = null;
/* 238 */     this.superType = null;
/* 239 */     this.crs = null;
/* 240 */     this.length = null;
/* 241 */     this.isCrsSet = false;
/*     */   }
/*     */   
/*     */   protected void resetDescriptorState() {
/* 245 */     resetTypeState();
/* 246 */     this.minOccurs = null;
/* 247 */     this.maxOccurs = null;
/* 248 */     this.isNillable = true;
/* 249 */     this.userData = new HashMap<Object, Object>();
/* 250 */     this.defaultValue = null;
/* 251 */     this.isDefaultValueSet = false;
/*     */   }
/*     */   
/*     */   public AttributeTypeBuilder setFactory(FeatureTypeFactory factory) {
/* 255 */     this.factory = factory;
/* 256 */     return this;
/*     */   }
/*     */   
/*     */   public AttributeTypeBuilder init(AttributeType type) {
/* 263 */     this.name = type.getName().getLocalPart();
/* 264 */     this.separator = type.getName().getSeparator();
/* 265 */     this.namespaceURI = type.getName().getNamespaceURI();
/* 266 */     this.isAbstract = type.isAbstract();
/* 268 */     if (type.getRestrictions() != null)
/* 269 */       restrictions().addAll(type.getRestrictions()); 
/* 272 */     this.description = (type.getDescription() != null) ? type.getDescription().toString() : null;
/* 273 */     this.isIdentifiable = type.isIdentified();
/* 274 */     this.binding = type.getBinding();
/* 275 */     this.superType = type.getSuper();
/* 277 */     if (type instanceof GeometryType)
/* 278 */       this.crs = ((GeometryType)type).getCoordinateReferenceSystem(); 
/* 280 */     return this;
/*     */   }
/*     */   
/*     */   public void init(AttributeDescriptor descriptor) {
/* 288 */     init(descriptor.getType());
/* 289 */     this.minOccurs = Integer.valueOf(descriptor.getMinOccurs());
/* 290 */     this.maxOccurs = Integer.valueOf(descriptor.getMaxOccurs());
/* 291 */     this.isNillable = descriptor.isNillable();
/*     */   }
/*     */   
/*     */   public void setBinding(Class binding) {
/* 298 */     this.binding = binding;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/* 302 */     this.name = name;
/*     */   }
/*     */   
/*     */   public void setNamespaceURI(String namespaceURI) {
/* 306 */     this.namespaceURI = namespaceURI;
/*     */   }
/*     */   
/*     */   public void setCRS(CoordinateReferenceSystem crs) {
/* 310 */     this.crs = crs;
/* 311 */     this.isCrsSet = true;
/*     */   }
/*     */   
/*     */   public boolean isCRSSet() {
/* 315 */     return this.isCrsSet;
/*     */   }
/*     */   
/*     */   public void setDescription(String description) {
/* 319 */     this.description = description;
/*     */   }
/*     */   
/*     */   public void setAbstract(boolean isAbstract) {
/* 323 */     this.isAbstract = isAbstract;
/*     */   }
/*     */   
/*     */   public void setIdentifiable(boolean isIdentifiable) {
/* 327 */     this.isIdentifiable = isIdentifiable;
/*     */   }
/*     */   
/*     */   public void setLength(int length) {
/* 331 */     this.length = Integer.valueOf(length);
/*     */   }
/*     */   
/*     */   public void addRestriction(Filter restriction) {
/* 335 */     restrictions().add(restriction);
/*     */   }
/*     */   
/*     */   public void addUserData(Object key, Object value) {
/* 339 */     this.userData.put(key, value);
/*     */   }
/*     */   
/*     */   public void setNillable(boolean isNillable) {
/* 346 */     this.isNillable = isNillable;
/*     */   }
/*     */   
/*     */   public void setMaxOccurs(int maxOccurs) {
/* 350 */     this.maxOccurs = Integer.valueOf(maxOccurs);
/*     */   }
/*     */   
/*     */   public void setMinOccurs(int minOccurs) {
/* 354 */     this.minOccurs = Integer.valueOf(minOccurs);
/*     */   }
/*     */   
/*     */   public void setDefaultValue(Object defaultValue) {
/* 358 */     this.defaultValue = defaultValue;
/* 359 */     this.isDefaultValueSet = true;
/*     */   }
/*     */   
/*     */   public AttributeTypeBuilder binding(Class binding) {
/* 363 */     setBinding(binding);
/* 364 */     return this;
/*     */   }
/*     */   
/*     */   public AttributeTypeBuilder name(String name) {
/* 368 */     setName(name);
/* 369 */     return this;
/*     */   }
/*     */   
/*     */   public AttributeTypeBuilder namespaceURI(String namespaceURI) {
/* 373 */     setNamespaceURI(namespaceURI);
/* 374 */     return this;
/*     */   }
/*     */   
/*     */   public AttributeTypeBuilder crs(CoordinateReferenceSystem crs) {
/* 379 */     setCRS(crs);
/* 380 */     return this;
/*     */   }
/*     */   
/*     */   public AttributeTypeBuilder description(String description) {
/* 384 */     setDescription(description);
/* 385 */     return this;
/*     */   }
/*     */   
/*     */   public AttributeTypeBuilder abstrct(boolean isAbstract) {
/* 389 */     setAbstract(isAbstract);
/* 390 */     return this;
/*     */   }
/*     */   
/*     */   public AttributeTypeBuilder identifiable(boolean isIdentifiable) {
/* 394 */     setIdentifiable(isIdentifiable);
/* 395 */     return this;
/*     */   }
/*     */   
/*     */   public AttributeTypeBuilder length(int length) {
/* 399 */     setLength(length);
/* 400 */     return this;
/*     */   }
/*     */   
/*     */   public AttributeTypeBuilder restriction(Filter restriction) {
/* 404 */     addRestriction(restriction);
/* 405 */     return this;
/*     */   }
/*     */   
/*     */   public AttributeTypeBuilder nillable(boolean isNillable) {
/* 412 */     setNillable(isNillable);
/* 413 */     return this;
/*     */   }
/*     */   
/*     */   public AttributeTypeBuilder maxOccurs(int maxOccurs) {
/* 417 */     setMaxOccurs(maxOccurs);
/* 418 */     return this;
/*     */   }
/*     */   
/*     */   public AttributeTypeBuilder minOccurs(int minOccurs) {
/* 422 */     setMinOccurs(minOccurs);
/* 423 */     return this;
/*     */   }
/*     */   
/*     */   public AttributeTypeBuilder defaultValue(Object defaultValue) {
/* 427 */     setDefaultValue(defaultValue);
/* 428 */     return this;
/*     */   }
/*     */   
/*     */   public AttributeTypeBuilder userData(Object key, Object value) {
/* 432 */     addUserData(key, value);
/* 433 */     return this;
/*     */   }
/*     */   
/*     */   public AttributeType buildType() {
/* 446 */     if (this.length != null) {
/* 447 */       Filter lengthRestriction = lengthRestriction(this.length.intValue());
/* 448 */       restrictions().add(lengthRestriction);
/*     */     } 
/* 451 */     AttributeType type = this.factory.createAttributeType(name(), this.binding, this.isIdentifiable, this.isAbstract, restrictions(), this.superType, description());
/* 454 */     resetTypeState();
/* 456 */     return type;
/*     */   }
/*     */   
/*     */   protected String typeName() {
/* 460 */     if (this.name == null)
/* 461 */       return Classes.getShortName(this.binding); 
/* 463 */     return this.name;
/*     */   }
/*     */   
/*     */   private InternationalString description() {
/* 466 */     return (this.description != null) ? (InternationalString)new SimpleInternationalString(this.description) : null;
/*     */   }
/*     */   
/*     */   public GeometryType buildGeometryType() {
/* 476 */     GeometryType type = this.factory.createGeometryType(name(), this.binding, this.crs, this.isIdentifiable, this.isAbstract, restrictions(), this.superType, description());
/* 480 */     resetTypeState();
/* 482 */     return type;
/*     */   }
/*     */   
/*     */   public AttributeDescriptor buildDescriptor(String name) {
/* 501 */     setName(name);
/* 502 */     if (this.binding == null)
/* 503 */       throw new IllegalStateException("No binding has been provided for this attribute"); 
/* 504 */     if (this.crs != null || Geometry.class.isAssignableFrom(this.binding))
/* 505 */       return (AttributeDescriptor)buildDescriptor(name, buildGeometryType()); 
/* 508 */     return buildDescriptor(name, buildType());
/*     */   }
/*     */   
/*     */   public AttributeDescriptor buildDescriptor(String name, AttributeType type) {
/* 522 */     return buildDescriptor(new NameImpl(name), type);
/*     */   }
/*     */   
/*     */   public GeometryDescriptor buildDescriptor(String name, GeometryType type) {
/* 535 */     return buildDescriptor(new NameImpl(name), type);
/*     */   }
/*     */   
/*     */   public AttributeDescriptor buildDescriptor(Name name, AttributeType type) {
/* 541 */     AttributeDescriptor descriptor = this.factory.createAttributeDescriptor(type, name, minOccurs(), maxOccurs(), this.isNillable, defaultValue());
/* 545 */     descriptor.getUserData().putAll(this.userData);
/* 546 */     resetDescriptorState();
/* 547 */     return descriptor;
/*     */   }
/*     */   
/*     */   public GeometryDescriptor buildDescriptor(Name name, GeometryType type) {
/* 551 */     GeometryDescriptor descriptor = this.factory.createGeometryDescriptor(type, name, minOccurs(), maxOccurs(), this.isNillable, defaultValue());
/* 555 */     descriptor.getUserData().putAll(this.userData);
/* 556 */     resetDescriptorState();
/* 557 */     return descriptor;
/*     */   }
/*     */   
/*     */   private int minOccurs() {
/* 565 */     if (this.minOccurs == null)
/* 566 */       return this.isNillable ? 0 : 1; 
/* 568 */     return this.minOccurs.intValue();
/*     */   }
/*     */   
/*     */   private int maxOccurs() {
/* 575 */     if (this.maxOccurs == null)
/* 576 */       return 1; 
/* 578 */     return this.maxOccurs.intValue();
/*     */   }
/*     */   
/*     */   private Name name() {
/* 582 */     if (this.separator == null)
/* 583 */       return new NameImpl(this.namespaceURI, typeName()); 
/* 586 */     return new NameImpl(this.namespaceURI, this.separator, typeName());
/*     */   }
/*     */   
/*     */   private Object defaultValue() {
/* 590 */     if (this.defaultValue == null && !this.isNillable && this.binding != null)
/* 591 */       this.defaultValue = DataUtilities.defaultValue(this.binding); 
/* 593 */     return this.defaultValue;
/*     */   }
/*     */   
/*     */   protected List<Filter> restrictions() {
/* 600 */     if (this.restrictions == null)
/* 601 */       this.restrictions = new ArrayList<Filter>(); 
/* 604 */     return this.restrictions;
/*     */   }
/*     */   
/*     */   protected Filter lengthRestriction(int length) {
/*     */     PropertyIsLessThanOrEqualTo propertyIsLessThanOrEqualTo;
/* 613 */     if (length < 0)
/* 614 */       return null; 
/* 616 */     LengthFunction lengthFunction = (LengthFunction)this.ff.function("LengthFunction", new Expression[] { (Expression)this.ff.property(".") });
/* 618 */     if (lengthFunction == null)
/* 619 */       return null; 
/* 621 */     Filter cf = null;
/*     */     try {
/* 623 */       propertyIsLessThanOrEqualTo = this.ff.lessOrEqual((Expression)lengthFunction, (Expression)this.ff.literal(length));
/* 624 */     } catch (IllegalFilterException e) {}
/* 627 */     return (propertyIsLessThanOrEqualTo == null) ? (Filter)Filter.EXCLUDE : (Filter)propertyIsLessThanOrEqualTo;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\AttributeTypeBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
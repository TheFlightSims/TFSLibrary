/*     */ package org.geotools.feature.simple;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import java.util.AbstractList;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.geotools.feature.GeometryAttributeImpl;
/*     */ import org.geotools.feature.IllegalAttributeException;
/*     */ import org.geotools.feature.type.AttributeDescriptorImpl;
/*     */ import org.geotools.feature.type.Types;
/*     */ import org.geotools.geometry.jts.JTS;
/*     */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*     */ import org.geotools.util.Converters;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.feature.GeometryAttribute;
/*     */ import org.opengis.feature.Property;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.AttributeDescriptor;
/*     */ import org.opengis.feature.type.AttributeType;
/*     */ import org.opengis.feature.type.ComplexType;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ import org.opengis.feature.type.GeometryDescriptor;
/*     */ import org.opengis.feature.type.GeometryType;
/*     */ import org.opengis.feature.type.Name;
/*     */ import org.opengis.feature.type.PropertyDescriptor;
/*     */ import org.opengis.feature.type.PropertyType;
/*     */ import org.opengis.filter.identity.FeatureId;
/*     */ import org.opengis.filter.identity.Identifier;
/*     */ import org.opengis.geometry.BoundingBox;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ 
/*     */ public class SimpleFeatureImpl implements SimpleFeature {
/*     */   protected FeatureId id;
/*     */   
/*     */   protected SimpleFeatureType featureType;
/*     */   
/*     */   protected Object[] values;
/*     */   
/*     */   protected Map<String, Integer> index;
/*     */   
/*     */   protected Map<Object, Object> userData;
/*     */   
/*     */   protected Map<Object, Object>[] attributeUserData;
/*     */   
/*     */   protected boolean validating;
/*     */   
/*     */   public SimpleFeatureImpl(List<Object> values, SimpleFeatureType featureType, FeatureId id) {
/*  99 */     this(values.toArray(), featureType, id, false, index(featureType));
/*     */   }
/*     */   
/*     */   public SimpleFeatureImpl(Object[] values, SimpleFeatureType featureType, FeatureId id, boolean validating) {
/* 114 */     this(values, featureType, id, validating, index(featureType));
/*     */   }
/*     */   
/*     */   public SimpleFeatureImpl(Object[] values, SimpleFeatureType featureType, FeatureId id, boolean validating, Map<String, Integer> index) {
/* 131 */     this.id = id;
/* 132 */     this.featureType = featureType;
/* 133 */     this.values = values;
/* 134 */     this.validating = validating;
/* 135 */     this.index = index;
/* 138 */     if (validating)
/* 139 */       validate(); 
/*     */   }
/*     */   
/*     */   private static Map<String, Integer> index(SimpleFeatureType featureType) {
/* 158 */     if (featureType instanceof SimpleFeatureTypeImpl)
/* 159 */       return ((SimpleFeatureTypeImpl)featureType).index; 
/* 161 */     synchronized (featureType) {
/* 163 */       Object cache = featureType.getUserData().get("indexLookup");
/* 164 */       if (cache instanceof Map)
/* 165 */         return (Map<String, Integer>)cache; 
/* 168 */       Map<String, Integer> generatedIndex = SimpleFeatureTypeImpl.buildIndex(featureType);
/* 169 */       featureType.getUserData().put("indexLookup", generatedIndex);
/* 170 */       return generatedIndex;
/*     */     } 
/*     */   }
/*     */   
/*     */   public FeatureId getIdentifier() {
/* 177 */     return this.id;
/*     */   }
/*     */   
/*     */   public String getID() {
/* 180 */     return this.id.getID();
/*     */   }
/*     */   
/*     */   public int getNumberOfAttributes() {
/* 184 */     return this.values.length;
/*     */   }
/*     */   
/*     */   public Object getAttribute(int index) throws IndexOutOfBoundsException {
/* 188 */     return this.values[index];
/*     */   }
/*     */   
/*     */   public Object getAttribute(String name) {
/* 192 */     Integer idx = this.index.get(name);
/* 193 */     if (idx != null)
/* 194 */       return getAttribute(idx.intValue()); 
/* 196 */     return null;
/*     */   }
/*     */   
/*     */   public Object getAttribute(Name name) {
/* 200 */     return getAttribute(name.getLocalPart());
/*     */   }
/*     */   
/*     */   public int getAttributeCount() {
/* 204 */     return this.values.length;
/*     */   }
/*     */   
/*     */   public List<Object> getAttributes() {
/* 208 */     return new ArrayList(Arrays.asList(this.values));
/*     */   }
/*     */   
/*     */   public Object getDefaultGeometry() {
/* 213 */     Integer idx = this.index.get(null);
/* 214 */     Object defaultGeometry = (idx != null) ? getAttribute(idx.intValue()) : null;
/* 217 */     if (defaultGeometry == null) {
/* 218 */       GeometryDescriptor geometryDescriptor = this.featureType.getGeometryDescriptor();
/* 219 */       if (geometryDescriptor != null) {
/* 220 */         Integer defaultGeomIndex = this.index.get(geometryDescriptor.getName().getLocalPart());
/* 221 */         defaultGeometry = getAttribute(defaultGeomIndex.intValue());
/*     */       } 
/*     */     } 
/* 225 */     return defaultGeometry;
/*     */   }
/*     */   
/*     */   public SimpleFeatureType getFeatureType() {
/* 229 */     return this.featureType;
/*     */   }
/*     */   
/*     */   public SimpleFeatureType getType() {
/* 233 */     return this.featureType;
/*     */   }
/*     */   
/*     */   public void setAttribute(int index, Object value) throws IndexOutOfBoundsException {
/* 239 */     Object converted = Converters.convert(value, getFeatureType().getDescriptor(index).getType().getBinding());
/* 241 */     if (this.validating)
/* 242 */       Types.validate(this.featureType.getDescriptor(index), converted); 
/* 244 */     this.values[index] = converted;
/*     */   }
/*     */   
/*     */   public void setAttribute(String name, Object value) {
/* 248 */     Integer idx = this.index.get(name);
/* 249 */     if (idx == null)
/* 250 */       throw new IllegalAttributeException("Unknown attribute " + name); 
/* 251 */     setAttribute(idx.intValue(), value);
/*     */   }
/*     */   
/*     */   public void setAttribute(Name name, Object value) {
/* 255 */     setAttribute(name.getLocalPart(), value);
/*     */   }
/*     */   
/*     */   public void setAttributes(List<Object> values) {
/* 259 */     for (int i = 0; i < this.values.length; i++)
/* 260 */       this.values[i] = values.get(i); 
/*     */   }
/*     */   
/*     */   public void setAttributes(Object[] values) {
/* 265 */     setAttributes(Arrays.asList(values));
/*     */   }
/*     */   
/*     */   public void setDefaultGeometry(Object geometry) {
/* 269 */     Integer geometryIndex = this.index.get(null);
/* 270 */     if (geometryIndex != null)
/* 271 */       setAttribute(geometryIndex.intValue(), geometry); 
/*     */   }
/*     */   
/*     */   public BoundingBox getBounds() {
/* 277 */     CoordinateReferenceSystem crs = this.featureType.getCoordinateReferenceSystem();
/* 278 */     ReferencedEnvelope referencedEnvelope = ReferencedEnvelope.create(crs);
/* 280 */     for (Object o : this.values) {
/* 281 */       if (o instanceof Geometry) {
/* 282 */         Geometry g = (Geometry)o;
/* 285 */         if (referencedEnvelope.isNull()) {
/* 286 */           referencedEnvelope.init((Envelope)JTS.bounds(g, crs));
/*     */         } else {
/* 289 */           referencedEnvelope.expandToInclude((Envelope)JTS.bounds(g, crs));
/*     */         } 
/*     */       } 
/*     */     } 
/* 294 */     return (BoundingBox)referencedEnvelope;
/*     */   }
/*     */   
/*     */   public GeometryAttribute getDefaultGeometryProperty() {
/*     */     GeometryAttributeImpl geometryAttributeImpl;
/* 298 */     GeometryDescriptor geometryDescriptor = this.featureType.getGeometryDescriptor();
/* 299 */     GeometryAttribute geometryAttribute = null;
/* 300 */     if (geometryDescriptor != null) {
/* 301 */       Object defaultGeometry = getDefaultGeometry();
/* 302 */       geometryAttributeImpl = new GeometryAttributeImpl(defaultGeometry, geometryDescriptor, null);
/*     */     } 
/* 304 */     return (GeometryAttribute)geometryAttributeImpl;
/*     */   }
/*     */   
/*     */   public void setDefaultGeometryProperty(GeometryAttribute geometryAttribute) {
/* 308 */     if (geometryAttribute != null) {
/* 309 */       setDefaultGeometry(geometryAttribute.getValue());
/*     */     } else {
/* 311 */       setDefaultGeometry(null);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Collection<Property> getProperties() {
/* 315 */     return new AttributeList();
/*     */   }
/*     */   
/*     */   public Collection<Property> getProperties(Name name) {
/* 319 */     return getProperties(name.getLocalPart());
/*     */   }
/*     */   
/*     */   public Collection<Property> getProperties(String name) {
/* 323 */     Integer idx = this.index.get(name);
/* 324 */     if (idx != null) {
/* 326 */       Collection<Attribute> c = Collections.singleton(new Attribute(idx.intValue()));
/* 327 */       return (Collection)c;
/*     */     } 
/* 329 */     return Collections.emptyList();
/*     */   }
/*     */   
/*     */   public Property getProperty(Name name) {
/* 334 */     return getProperty(name.getLocalPart());
/*     */   }
/*     */   
/*     */   public Property getProperty(String name) {
/* 338 */     Integer idx = this.index.get(name);
/* 339 */     if (idx == null)
/* 340 */       return null; 
/* 342 */     int index = idx.intValue();
/* 343 */     AttributeDescriptor descriptor = this.featureType.getDescriptor(index);
/* 344 */     if (descriptor instanceof GeometryDescriptor)
/* 345 */       return (Property)new GeometryAttributeImpl(this.values[index], (GeometryDescriptor)descriptor, null); 
/* 347 */     return (Property)new Attribute(index);
/*     */   }
/*     */   
/*     */   public Collection<? extends Property> getValue() {
/* 353 */     return getProperties();
/*     */   }
/*     */   
/*     */   public void setValue(Collection<Property> values) {
/* 357 */     int i = 0;
/* 358 */     for (Property p : values)
/* 359 */       this.values[i++] = p.getValue(); 
/*     */   }
/*     */   
/*     */   public void setValue(Object newValue) {
/* 364 */     setValue((Collection<Property>)newValue);
/*     */   }
/*     */   
/*     */   public AttributeDescriptor getDescriptor() {
/* 371 */     return (AttributeDescriptor)new AttributeDescriptorImpl((AttributeType)this.featureType, this.featureType.getName(), 0, 2147483647, true, null);
/*     */   }
/*     */   
/*     */   public Name getName() {
/* 380 */     return this.featureType.getName();
/*     */   }
/*     */   
/*     */   public boolean isNillable() {
/* 384 */     return true;
/*     */   }
/*     */   
/*     */   public Map<Object, Object> getUserData() {
/* 388 */     if (this.userData == null)
/* 389 */       this.userData = new HashMap<Object, Object>(); 
/* 390 */     return this.userData;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 399 */     return this.id.hashCode() * this.featureType.hashCode();
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 411 */     if (obj == null)
/* 412 */       return false; 
/* 415 */     if (obj == this)
/* 416 */       return true; 
/* 419 */     if (!(obj instanceof SimpleFeatureImpl))
/* 420 */       return false; 
/* 423 */     SimpleFeatureImpl feat = (SimpleFeatureImpl)obj;
/* 427 */     if (this.id == null && 
/* 428 */       feat.getIdentifier() != null)
/* 429 */       return false; 
/* 433 */     if (!this.id.equals(feat.getIdentifier()))
/* 434 */       return false; 
/* 437 */     if (!feat.getFeatureType().equals(this.featureType))
/* 438 */       return false; 
/* 441 */     for (int i = 0, ii = this.values.length; i < ii; i++) {
/* 442 */       Object otherAtt = feat.getAttribute(i);
/* 444 */       if (this.values[i] == null) {
/* 445 */         if (otherAtt != null)
/* 446 */           return false; 
/* 449 */       } else if (!this.values[i].equals(otherAtt)) {
/* 450 */         return false;
/*     */       } 
/*     */     } 
/* 455 */     return true;
/*     */   }
/*     */   
/*     */   public void validate() {
/* 459 */     for (int i = 0; i < this.values.length; i++) {
/* 460 */       AttributeDescriptor descriptor = getType().getDescriptor(i);
/* 461 */       Types.validate(descriptor, this.values[i]);
/*     */     } 
/*     */   }
/*     */   
/*     */   class AttributeList extends AbstractList<Property> {
/*     */     public Property get(int index) {
/* 471 */       AttributeDescriptor descriptor = SimpleFeatureImpl.this.featureType.getDescriptor(index);
/* 472 */       if (descriptor instanceof GeometryDescriptor)
/* 473 */         return (Property)new SimpleFeatureImpl.SimpleGeometryAttribute(index); 
/* 475 */       return (Property)new SimpleFeatureImpl.Attribute(index);
/*     */     }
/*     */     
/*     */     public SimpleFeatureImpl.Attribute set(int index, Property element) {
/* 479 */       SimpleFeatureImpl.this.values[index] = element.getValue();
/* 480 */       return null;
/*     */     }
/*     */     
/*     */     public int size() {
/* 484 */       return SimpleFeatureImpl.this.values.length;
/*     */     }
/*     */   }
/*     */   
/*     */   public String toString() {
/* 489 */     StringBuffer sb = new StringBuffer("SimpleFeatureImpl:");
/* 490 */     sb.append(getType().getName().getLocalPart());
/* 491 */     sb.append("=");
/* 492 */     sb.append(getValue());
/* 493 */     return sb.toString();
/*     */   }
/*     */   
/*     */   class Attribute implements org.opengis.feature.Attribute {
/*     */     int index;
/*     */     
/*     */     Attribute(int index) {
/* 504 */       this.index = index;
/*     */     }
/*     */     
/*     */     public Identifier getIdentifier() {
/* 508 */       return null;
/*     */     }
/*     */     
/*     */     public AttributeDescriptor getDescriptor() {
/* 512 */       return SimpleFeatureImpl.this.featureType.getDescriptor(this.index);
/*     */     }
/*     */     
/*     */     public AttributeType getType() {
/* 516 */       return SimpleFeatureImpl.this.featureType.getType(this.index);
/*     */     }
/*     */     
/*     */     public Name getName() {
/* 520 */       return getDescriptor().getName();
/*     */     }
/*     */     
/*     */     public Map<Object, Object> getUserData() {
/* 525 */       if (SimpleFeatureImpl.this.attributeUserData == null)
/* 526 */         SimpleFeatureImpl.this.attributeUserData = (Map<Object, Object>[])new HashMap[SimpleFeatureImpl.this.values.length]; 
/* 528 */       if (SimpleFeatureImpl.this.attributeUserData[this.index] == null)
/* 529 */         SimpleFeatureImpl.this.attributeUserData[this.index] = new HashMap<Object, Object>(); 
/* 530 */       return SimpleFeatureImpl.this.attributeUserData[this.index];
/*     */     }
/*     */     
/*     */     public Object getValue() {
/* 534 */       return SimpleFeatureImpl.this.values[this.index];
/*     */     }
/*     */     
/*     */     public boolean isNillable() {
/* 538 */       return getDescriptor().isNillable();
/*     */     }
/*     */     
/*     */     public void setValue(Object newValue) {
/* 542 */       SimpleFeatureImpl.this.values[this.index] = newValue;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 550 */       return 37 * getDescriptor().hashCode() + 37 * ((getValue() == null) ? 0 : getValue().hashCode());
/*     */     }
/*     */     
/*     */     public boolean equals(Object obj) {
/* 563 */       if (this == obj)
/* 564 */         return true; 
/* 567 */       if (!(obj instanceof Attribute))
/* 568 */         return false; 
/* 570 */       Attribute other = (Attribute)obj;
/* 571 */       if (!Utilities.equals(getDescriptor(), other.getDescriptor()))
/* 572 */         return false; 
/* 574 */       if (!Utilities.deepEquals(getValue(), other.getValue()))
/* 575 */         return false; 
/* 577 */       return Utilities.equals(getIdentifier(), other.getIdentifier());
/*     */     }
/*     */     
/*     */     public void validate() {
/* 581 */       Types.validate(getDescriptor(), SimpleFeatureImpl.this.values[this.index]);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 585 */       StringBuffer sb = new StringBuffer("SimpleFeatureImpl.Attribute: ");
/* 586 */       sb.append(getDescriptor().getName().getLocalPart());
/* 587 */       if (!getDescriptor().getName().getLocalPart().equals(getDescriptor().getType().getName().getLocalPart()) || SimpleFeatureImpl.this.id != null) {
/* 590 */         sb.append("<");
/* 591 */         sb.append(getDescriptor().getType().getName().getLocalPart());
/* 592 */         if (SimpleFeatureImpl.this.id != null) {
/* 593 */           sb.append(" id=");
/* 594 */           sb.append(SimpleFeatureImpl.this.id);
/*     */         } 
/* 596 */         sb.append(">");
/*     */       } 
/* 598 */       sb.append("=");
/* 599 */       sb.append(SimpleFeatureImpl.this.values[this.index]);
/* 600 */       return sb.toString();
/*     */     }
/*     */   }
/*     */   
/*     */   class SimpleGeometryAttribute extends Attribute implements GeometryAttribute {
/*     */     SimpleGeometryAttribute(int index) {
/* 607 */       super(index);
/*     */     }
/*     */     
/*     */     public GeometryType getType() {
/* 612 */       return (GeometryType)super.getType();
/*     */     }
/*     */     
/*     */     public GeometryDescriptor getDescriptor() {
/* 617 */       return (GeometryDescriptor)super.getDescriptor();
/*     */     }
/*     */     
/*     */     public BoundingBox getBounds() {
/* 622 */       ReferencedEnvelope bounds = new ReferencedEnvelope(SimpleFeatureImpl.this.featureType.getCoordinateReferenceSystem());
/* 624 */       Object value = SimpleFeatureImpl.this.getAttribute(this.index);
/* 625 */       if (value instanceof Geometry)
/* 626 */         bounds.init(((Geometry)value).getEnvelopeInternal()); 
/* 628 */       return (BoundingBox)bounds;
/*     */     }
/*     */     
/*     */     public void setBounds(BoundingBox bounds) {}
/*     */     
/*     */     public int hashCode() {
/* 639 */       return 17 * super.hashCode();
/*     */     }
/*     */     
/*     */     public boolean equals(Object obj) {
/* 644 */       if (this == obj)
/* 645 */         return true; 
/* 648 */       if (!(obj instanceof SimpleGeometryAttribute))
/* 649 */         return false; 
/* 651 */       return super.equals(obj);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\simple\SimpleFeatureImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
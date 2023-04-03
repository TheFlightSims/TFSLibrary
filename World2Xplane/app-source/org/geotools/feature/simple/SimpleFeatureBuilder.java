/*     */ package org.geotools.feature.simple;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import java.rmi.server.UID;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.data.DataUtilities;
/*     */ import org.geotools.factory.CommonFactoryFinder;
/*     */ import org.geotools.feature.type.Types;
/*     */ import org.geotools.filter.identity.FeatureIdImpl;
/*     */ import org.geotools.util.Converters;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.feature.FeatureFactory;
/*     */ import org.opengis.feature.IllegalAttributeException;
/*     */ import org.opengis.feature.Property;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.AttributeDescriptor;
/*     */ import org.opengis.feature.type.Name;
/*     */ 
/*     */ public class SimpleFeatureBuilder {
/* 134 */   static Logger LOGGER = Logging.getLogger("org.geotools.feature");
/*     */   
/*     */   SimpleFeatureType featureType;
/*     */   
/*     */   FeatureFactory factory;
/*     */   
/*     */   Map<String, Integer> index;
/*     */   
/*     */   Object[] values;
/*     */   
/*     */   int next;
/*     */   
/*     */   Map<Object, Object>[] userData;
/*     */   
/*     */   Map<Object, Object> featureUserData;
/*     */   
/*     */   boolean validating;
/*     */   
/*     */   public SimpleFeatureBuilder(SimpleFeatureType featureType) {
/* 159 */     this(featureType, CommonFactoryFinder.getFeatureFactory(null));
/*     */   }
/*     */   
/*     */   public SimpleFeatureBuilder(SimpleFeatureType featureType, FeatureFactory factory) {
/* 163 */     this.featureType = featureType;
/* 164 */     this.factory = factory;
/* 166 */     if (featureType instanceof SimpleFeatureTypeImpl) {
/* 167 */       this.index = ((SimpleFeatureTypeImpl)featureType).index;
/*     */     } else {
/* 169 */       this.index = SimpleFeatureTypeImpl.buildIndex(featureType);
/*     */     } 
/* 171 */     reset();
/*     */   }
/*     */   
/*     */   public void reset() {
/* 175 */     this.values = new Object[this.featureType.getAttributeCount()];
/* 176 */     this.next = 0;
/* 177 */     this.userData = null;
/* 178 */     this.featureUserData = null;
/*     */   }
/*     */   
/*     */   public SimpleFeatureType getFeatureType() {
/* 186 */     return this.featureType;
/*     */   }
/*     */   
/*     */   public void init(SimpleFeature feature) {
/* 197 */     reset();
/* 200 */     if (feature instanceof SimpleFeatureImpl) {
/* 201 */       SimpleFeatureImpl impl = (SimpleFeatureImpl)feature;
/* 202 */       System.arraycopy(impl.values, 0, this.values, 0, impl.values.length);
/* 204 */       if (impl.userData != null)
/* 205 */         this.featureUserData = new HashMap<Object, Object>(impl.userData); 
/*     */     } else {
/* 208 */       for (Object value : feature.getAttributes())
/* 209 */         add(value); 
/* 212 */       if (!feature.getUserData().isEmpty())
/* 213 */         this.featureUserData = new HashMap<Object, Object>(feature.getUserData()); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void add(Object value) {
/* 229 */     set(this.next, value);
/* 230 */     this.next++;
/*     */   }
/*     */   
/*     */   public void addAll(List<Object> values) {
/* 237 */     for (int i = 0; i < values.size(); i++)
/* 238 */       add(values.get(i)); 
/*     */   }
/*     */   
/*     */   public void addAll(Object[] values) {
/* 246 */     addAll(Arrays.asList(values));
/*     */   }
/*     */   
/*     */   public void set(Name name, Object value) {
/* 264 */     set(name.getLocalPart(), value);
/*     */   }
/*     */   
/*     */   public void set(String name, Object value) {
/* 282 */     int index = this.featureType.indexOf(name);
/* 283 */     if (index == -1)
/* 284 */       throw new IllegalArgumentException("No such attribute:" + name); 
/* 286 */     set(index, value);
/*     */   }
/*     */   
/*     */   public void set(int index, Object value) {
/* 301 */     if (index >= this.values.length)
/* 302 */       throw new ArrayIndexOutOfBoundsException("Can handle " + this.values.length + " attributes only, index is " + index); 
/* 305 */     AttributeDescriptor descriptor = this.featureType.getDescriptor(index);
/* 306 */     this.values[index] = convert(value, descriptor);
/* 307 */     if (this.validating)
/* 308 */       Types.validate(descriptor, this.values[index]); 
/*     */   }
/*     */   
/*     */   private Object convert(Object value, AttributeDescriptor descriptor) {
/* 313 */     if (value != null) {
/* 314 */       Class<?> target = descriptor.getType().getBinding();
/* 315 */       Object converted = Converters.convert(value, target);
/* 316 */       if (converted != null)
/* 317 */         value = converted; 
/* 321 */     } else if (!descriptor.isNillable()) {
/* 322 */       value = descriptor.getDefaultValue();
/* 323 */       if (value == null)
/* 325 */         value = DataUtilities.defaultValue(descriptor.getType().getBinding()); 
/*     */     } 
/* 329 */     return value;
/*     */   }
/*     */   
/*     */   public SimpleFeature buildFeature(String id) {
/* 349 */     if (id == null)
/* 350 */       id = createDefaultFeatureId(); 
/* 353 */     Object[] values = this.values;
/* 354 */     Map<Object, Object>[] userData = this.userData;
/* 355 */     Map<Object, Object> featureUserData = this.featureUserData;
/* 356 */     reset();
/* 357 */     SimpleFeature sf = this.factory.createSimpleFeature(values, this.featureType, id);
/* 360 */     if (userData != null)
/* 361 */       for (int i = 0; i < userData.length; i++) {
/* 362 */         if (userData[i] != null)
/* 363 */           sf.getProperty(this.featureType.getDescriptor(i).getName()).getUserData().putAll(userData[i]); 
/*     */       }  
/* 369 */     if (featureUserData != null)
/* 370 */       sf.getUserData().putAll(featureUserData); 
/* 373 */     return sf;
/*     */   }
/*     */   
/*     */   public SimpleFeature buildFeature(String id, Object[] values) {
/* 383 */     addAll(values);
/* 384 */     return buildFeature(id);
/*     */   }
/*     */   
/*     */   public static String createDefaultFeatureId() {
/* 402 */     return "fid-" + (new UID()).toString().replace(':', '_');
/*     */   }
/*     */   
/*     */   public static FeatureIdImpl createDefaultFeatureIdentifier(String suggestedId) {
/* 410 */     if (suggestedId != null)
/* 411 */       return new FeatureIdImpl(suggestedId); 
/* 413 */     return new FeatureIdImpl(createDefaultFeatureId());
/*     */   }
/*     */   
/*     */   public static SimpleFeature build(SimpleFeatureType type, Object[] values, String id) {
/* 432 */     SimpleFeatureBuilder builder = new SimpleFeatureBuilder(type);
/* 433 */     builder.addAll(values);
/* 434 */     return builder.buildFeature(id);
/*     */   }
/*     */   
/*     */   public static SimpleFeature build(SimpleFeatureType type, List<Object> values, String id) {
/* 448 */     return build(type, values.toArray(), id);
/*     */   }
/*     */   
/*     */   public static SimpleFeature copy(SimpleFeature original) {
/* 463 */     if (original == null)
/* 463 */       return null; 
/* 465 */     SimpleFeatureBuilder builder = new SimpleFeatureBuilder(original.getFeatureType());
/* 466 */     builder.init(original);
/* 467 */     return builder.buildFeature(original.getID());
/*     */   }
/*     */   
/*     */   public static SimpleFeature deep(SimpleFeature original) {
/* 481 */     if (original == null)
/* 482 */       return null; 
/* 484 */     SimpleFeatureBuilder builder = new SimpleFeatureBuilder(original.getFeatureType());
/* 485 */     for (Property property : original.getProperties()) {
/* 486 */       Object value = property.getValue();
/*     */       try {
/* 488 */         Object copy = value;
/* 489 */         if (value instanceof Geometry) {
/* 490 */           Geometry geometry = (Geometry)value;
/* 491 */           copy = geometry.clone();
/*     */         } 
/* 493 */         builder.set(property.getName(), copy);
/* 494 */       } catch (Exception e) {
/* 495 */         throw new IllegalAttributeException((AttributeDescriptor)property.getDescriptor(), value, e);
/*     */       } 
/*     */     } 
/* 499 */     return builder.buildFeature(original.getID());
/*     */   }
/*     */   
/*     */   public static SimpleFeature template(SimpleFeatureType featureType, String featureId) {
/* 509 */     SimpleFeatureBuilder builder = new SimpleFeatureBuilder(featureType);
/* 510 */     for (AttributeDescriptor ad : featureType.getAttributeDescriptors())
/* 511 */       builder.add(ad.getDefaultValue()); 
/* 513 */     return builder.buildFeature(featureId);
/*     */   }
/*     */   
/*     */   public static SimpleFeature retype(SimpleFeature feature, SimpleFeatureType featureType) {
/* 534 */     SimpleFeatureBuilder builder = new SimpleFeatureBuilder(featureType);
/* 535 */     for (AttributeDescriptor att : featureType.getAttributeDescriptors()) {
/* 536 */       Object value = feature.getAttribute(att.getName());
/* 537 */       builder.set(att.getName(), value);
/*     */     } 
/* 539 */     return builder.buildFeature(feature.getID());
/*     */   }
/*     */   
/*     */   public static SimpleFeature retype(SimpleFeature feature, SimpleFeatureBuilder builder) {
/* 556 */     builder.reset();
/* 557 */     for (AttributeDescriptor att : builder.getFeatureType().getAttributeDescriptors()) {
/* 558 */       Object value = feature.getAttribute(att.getName());
/* 559 */       builder.set(att.getName(), value);
/*     */     } 
/* 561 */     return builder.buildFeature(feature.getID());
/*     */   }
/*     */   
/*     */   public SimpleFeatureBuilder userData(Object key, Object value) {
/* 573 */     return setUserData(this.next, key, value);
/*     */   }
/*     */   
/*     */   public SimpleFeatureBuilder setUserData(int index, Object key, Object value) {
/* 578 */     if (this.userData == null)
/* 579 */       this.userData = (Map<Object, Object>[])new Map[this.values.length]; 
/* 581 */     if (this.userData[index] == null)
/* 582 */       this.userData[index] = new HashMap<Object, Object>(); 
/* 584 */     this.userData[index].put(key, value);
/* 585 */     return this;
/*     */   }
/*     */   
/*     */   public SimpleFeatureBuilder featureUserData(Object key, Object value) {
/* 596 */     if (this.featureUserData == null)
/* 597 */       this.featureUserData = new HashMap<Object, Object>(); 
/* 599 */     this.featureUserData.put(key, value);
/* 600 */     return this;
/*     */   }
/*     */   
/*     */   public boolean isValidating() {
/* 604 */     return this.validating;
/*     */   }
/*     */   
/*     */   public void setValidating(boolean validating) {
/* 608 */     this.validating = validating;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\simple\SimpleFeatureBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
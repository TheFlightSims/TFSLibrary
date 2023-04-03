/*     */ package org.geotools.data;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.index.SpatialIndex;
/*     */ import com.vividsolutions.jts.index.quadtree.Quadtree;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.CopyOnWriteArrayList;
/*     */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*     */ import org.opengis.feature.GeometryAttribute;
/*     */ import org.opengis.feature.IllegalAttributeException;
/*     */ import org.opengis.feature.Property;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.AttributeDescriptor;
/*     */ import org.opengis.feature.type.AttributeType;
/*     */ import org.opengis.feature.type.ComplexType;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ import org.opengis.feature.type.Name;
/*     */ import org.opengis.feature.type.PropertyDescriptor;
/*     */ import org.opengis.feature.type.PropertyType;
/*     */ import org.opengis.filter.identity.FeatureId;
/*     */ import org.opengis.filter.identity.Identifier;
/*     */ import org.opengis.geometry.BoundingBox;
/*     */ 
/*     */ public class Diff {
/*     */   private final Map<String, SimpleFeature> modifiedFeatures;
/*     */   
/*     */   private final Map<String, SimpleFeature> addedFeatures;
/*     */   
/*     */   private final List<String> addedFidList;
/*     */   
/*     */   public final Map<String, SimpleFeature> modified2;
/*     */   
/*     */   public final Map<String, SimpleFeature> added;
/*     */   
/*     */   private final List<String> order;
/*     */   
/* 116 */   public int nextFID = 0;
/*     */   
/*     */   private SpatialIndex spatialIndex;
/*     */   
/*     */   Object mutex;
/*     */   
/*     */   public Diff() {
/* 127 */     this.modifiedFeatures = new ConcurrentHashMap<String, SimpleFeature>();
/* 128 */     this.addedFeatures = new ConcurrentHashMap<String, SimpleFeature>();
/* 129 */     this.addedFidList = new CopyOnWriteArrayList<String>();
/* 132 */     this.modified2 = Collections.unmodifiableMap(this.modifiedFeatures);
/* 133 */     this.added = Collections.unmodifiableMap(this.addedFeatures);
/* 134 */     this.order = Collections.unmodifiableList(this.addedFidList);
/* 136 */     this.spatialIndex = (SpatialIndex)new Quadtree();
/* 137 */     this.mutex = this;
/*     */   }
/*     */   
/*     */   public Diff(Diff other) {
/* 146 */     this.modifiedFeatures = new ConcurrentHashMap<String, SimpleFeature>(other.modifiedFeatures);
/* 147 */     this.addedFeatures = new ConcurrentHashMap<String, SimpleFeature>(other.addedFeatures);
/* 148 */     this.addedFidList = new CopyOnWriteArrayList<String>(other.addedFidList);
/* 151 */     this.modified2 = Collections.unmodifiableMap(this.modifiedFeatures);
/* 152 */     this.added = Collections.unmodifiableMap(this.addedFeatures);
/* 153 */     this.order = Collections.unmodifiableList(this.addedFidList);
/* 155 */     this.spatialIndex = (SpatialIndex)copySTRtreeFrom(other);
/* 156 */     this.nextFID = other.nextFID;
/* 157 */     this.mutex = this;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 166 */     synchronized (this.mutex) {
/* 167 */       return (this.modifiedFeatures.isEmpty() && this.addedFeatures.isEmpty());
/*     */     } 
/*     */   }
/*     */   
/*     */   public void clear() {
/* 174 */     synchronized (this.mutex) {
/* 175 */       this.nextFID = 0;
/* 176 */       this.addedFeatures.clear();
/* 177 */       this.addedFidList.clear();
/* 178 */       this.modifiedFeatures.clear();
/* 179 */       this.spatialIndex = (SpatialIndex)new Quadtree();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void modify(String fid, SimpleFeature f) {
/* 190 */     synchronized (this.mutex) {
/*     */       SimpleFeature old;
/* 192 */       if (this.addedFeatures.containsKey(fid)) {
/* 193 */         old = this.addedFeatures.get(fid);
/* 194 */         if (f == null) {
/* 195 */           this.addedFeatures.remove(fid);
/* 196 */           this.addedFidList.remove(fid);
/*     */         } else {
/* 199 */           this.addedFeatures.put(fid, f);
/*     */         } 
/*     */       } else {
/* 203 */         old = this.modifiedFeatures.get(fid);
/* 204 */         this.modifiedFeatures.put(fid, f);
/*     */       } 
/* 206 */       if (old != null)
/* 207 */         this.spatialIndex.remove((Envelope)ReferencedEnvelope.reference(old.getBounds()), old); 
/* 209 */       addToSpatialIndex(f);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void add(String fid, SimpleFeature f) {
/* 214 */     synchronized (this.mutex) {
/* 215 */       this.addedFeatures.put(fid, f);
/* 216 */       this.addedFidList.add(fid);
/* 217 */       addToSpatialIndex(f);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void addToSpatialIndex(SimpleFeature f) {
/* 222 */     if (f.getDefaultGeometry() != null) {
/* 223 */       BoundingBox bounds = f.getBounds();
/* 224 */       if (!bounds.isEmpty())
/* 225 */         this.spatialIndex.insert((Envelope)ReferencedEnvelope.reference(bounds), f); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void remove(String fid) {
/* 230 */     synchronized (this.mutex) {
/* 231 */       SimpleFeature old = null;
/* 233 */       if (this.addedFeatures.containsKey(fid)) {
/* 234 */         old = this.addedFeatures.get(fid);
/* 235 */         this.addedFeatures.remove(fid);
/* 236 */         this.addedFidList.remove(fid);
/*     */       } else {
/* 238 */         old = this.modifiedFeatures.get(fid);
/* 239 */         this.modifiedFeatures.put(fid, TransactionStateDiff.NULL);
/*     */       } 
/* 241 */       if (old != null)
/* 242 */         this.spatialIndex.remove((Envelope)ReferencedEnvelope.reference(old.getBounds()), old); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public List<SimpleFeature> queryIndex(Envelope env) {
/* 249 */     synchronized (this.mutex) {
/* 250 */       return this.spatialIndex.query(env);
/*     */     } 
/*     */   }
/*     */   
/*     */   public List<String> getAddedOrder() {
/* 256 */     return this.addedFidList;
/*     */   }
/*     */   
/*     */   public Map<String, SimpleFeature> getModified() {
/* 281 */     return this.modified2;
/*     */   }
/*     */   
/*     */   public Map<String, SimpleFeature> getAdded() {
/* 306 */     return this.added;
/*     */   }
/*     */   
/*     */   protected Quadtree copySTRtreeFrom(Diff diff) {
/* 309 */     Quadtree tree = new Quadtree();
/* 311 */     synchronized (diff) {
/* 312 */       Iterator<Map.Entry<String, SimpleFeature>> i = diff.added.entrySet().iterator();
/* 313 */       while (i.hasNext()) {
/* 314 */         Map.Entry<String, SimpleFeature> e = i.next();
/* 315 */         SimpleFeature f = e.getValue();
/* 316 */         if (!diff.modifiedFeatures.containsKey(f.getID()))
/* 317 */           tree.insert((Envelope)ReferencedEnvelope.reference(f.getBounds()), f); 
/*     */       } 
/* 320 */       Iterator<Map.Entry<String, SimpleFeature>> j = diff.getModified().entrySet().iterator();
/* 321 */       while (j.hasNext()) {
/* 322 */         Map.Entry<String, SimpleFeature> e = j.next();
/* 323 */         SimpleFeature f = e.getValue();
/* 324 */         tree.insert((Envelope)ReferencedEnvelope.reference(f.getBounds()), f);
/*     */       } 
/*     */     } 
/* 328 */     return tree;
/*     */   }
/*     */   
/* 337 */   public static final SimpleFeature NULL = new SimpleFeature() {
/*     */       public Object getAttribute(String path) {
/* 339 */         return null;
/*     */       }
/*     */       
/*     */       public Object getAttribute(int index) {
/* 343 */         return null;
/*     */       }
/*     */       
/*     */       public ReferencedEnvelope getBounds() {
/* 351 */         return null;
/*     */       }
/*     */       
/*     */       public Geometry getDefaultGeometry() {
/* 355 */         return null;
/*     */       }
/*     */       
/*     */       public SimpleFeatureType getFeatureType() {
/* 359 */         return null;
/*     */       }
/*     */       
/*     */       public String getID() {
/* 363 */         return null;
/*     */       }
/*     */       
/*     */       public FeatureId getIdentifier() {
/* 367 */         return null;
/*     */       }
/*     */       
/*     */       public void setAttribute(int position, Object val) {}
/*     */       
/*     */       public void setAttribute(String path, Object attribute) throws IllegalAttributeException {}
/*     */       
/*     */       public Object getAttribute(Name name) {
/* 385 */         return null;
/*     */       }
/*     */       
/*     */       public int getAttributeCount() {
/* 389 */         return 0;
/*     */       }
/*     */       
/*     */       public List<Object> getAttributes() {
/* 393 */         return null;
/*     */       }
/*     */       
/*     */       public SimpleFeatureType getType() {
/* 397 */         return null;
/*     */       }
/*     */       
/*     */       public void setAttribute(Name name, Object value) {}
/*     */       
/*     */       public void setAttributes(List<Object> values) {}
/*     */       
/*     */       public void setAttributes(Object[] values) {}
/*     */       
/*     */       public void setDefaultGeometry(Object geometry) {}
/*     */       
/*     */       public GeometryAttribute getDefaultGeometryProperty() {
/* 413 */         return null;
/*     */       }
/*     */       
/*     */       public void setDefaultGeometryProperty(GeometryAttribute geometryAttribute) {}
/*     */       
/*     */       public Collection<Property> getProperties(Name name) {
/* 420 */         return null;
/*     */       }
/*     */       
/*     */       public Collection<Property> getProperties() {
/* 424 */         return null;
/*     */       }
/*     */       
/*     */       public Collection<Property> getProperties(String name) {
/* 428 */         return null;
/*     */       }
/*     */       
/*     */       public Property getProperty(Name name) {
/* 432 */         return null;
/*     */       }
/*     */       
/*     */       public Property getProperty(String name) {
/* 436 */         return null;
/*     */       }
/*     */       
/*     */       public Collection<? extends Property> getValue() {
/* 440 */         return null;
/*     */       }
/*     */       
/*     */       public void setValue(Collection<Property> values) {}
/*     */       
/*     */       public AttributeDescriptor getDescriptor() {
/* 447 */         return null;
/*     */       }
/*     */       
/*     */       public Name getName() {
/* 451 */         return null;
/*     */       }
/*     */       
/*     */       public Map<Object, Object> getUserData() {
/* 455 */         return null;
/*     */       }
/*     */       
/*     */       public boolean isNillable() {
/* 459 */         return false;
/*     */       }
/*     */       
/*     */       public void setValue(Object newValue) {}
/*     */       
/*     */       public String toString() {
/* 466 */         return "<NullFeature>";
/*     */       }
/*     */       
/*     */       public int hashCode() {
/* 470 */         return 0;
/*     */       }
/*     */       
/*     */       public boolean equals(Object arg0) {
/* 474 */         return (arg0 == this);
/*     */       }
/*     */       
/*     */       public void validate() {}
/*     */     };
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\Diff.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package org.geotools.data;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import java.io.IOException;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.geotools.factory.Hints;
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
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.filter.identity.FeatureId;
/*     */ import org.opengis.filter.identity.Identifier;
/*     */ import org.opengis.geometry.BoundingBox;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ 
/*     */ public class TransactionStateDiff implements Transaction.State {
/*     */   AbstractDataStore store;
/*     */   
/*     */   Transaction transaction;
/*     */   
/*  73 */   Map<String, Diff> typeNameDiff = new HashMap<String, Diff>();
/*     */   
/*     */   public TransactionStateDiff(AbstractDataStore dataStore) {
/*  76 */     this.store = dataStore;
/*     */   }
/*     */   
/*     */   public synchronized void setTransaction(Transaction transaction) {
/*  80 */     if (transaction != null) {
/*  82 */       this.transaction = transaction;
/*     */     } else {
/*  84 */       this.transaction = null;
/*  86 */       if (this.typeNameDiff != null) {
/*  87 */         Iterator<Diff> i = this.typeNameDiff.values().iterator();
/*  88 */         while (i.hasNext()) {
/*  89 */           Diff diff = i.next();
/*  90 */           diff.clear();
/*     */         } 
/*  93 */         this.typeNameDiff.clear();
/*     */       } 
/*  96 */       this.store = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public synchronized Diff diff(String typeName) throws IOException {
/* 101 */     if (!exists(typeName))
/* 102 */       throw new IOException(typeName + " not defined"); 
/* 105 */     if (this.typeNameDiff.containsKey(typeName))
/* 106 */       return this.typeNameDiff.get(typeName); 
/* 108 */     Diff diff = new Diff();
/* 109 */     this.typeNameDiff.put(typeName, diff);
/* 111 */     return diff;
/*     */   }
/*     */   
/*     */   boolean exists(String typeName) {
/*     */     String[] types;
/*     */     try {
/* 118 */       types = this.store.getTypeNames();
/* 119 */     } catch (IOException e) {
/* 120 */       return false;
/*     */     } 
/* 122 */     Arrays.sort((Object[])types);
/* 124 */     return (Arrays.binarySearch((Object[])types, typeName) != -1);
/*     */   }
/*     */   
/*     */   public synchronized void addAuthorization(String AuthID) throws IOException {}
/*     */   
/*     */   public synchronized void commit() throws IOException {
/* 143 */     for (Iterator<Map.Entry<String, Diff>> i = this.typeNameDiff.entrySet().iterator(); i.hasNext(); ) {
/* 144 */       Map.Entry<String, Diff> entry = i.next();
/* 146 */       String typeName = entry.getKey();
/* 147 */       Diff diff = entry.getValue();
/* 148 */       applyDiff(typeName, diff);
/*     */     } 
/*     */   }
/*     */   
/*     */   void applyDiff(String typeName, Diff diff) throws IOException {
/*     */     FeatureWriter<SimpleFeatureType, SimpleFeature> writer;
/* 186 */     if (diff.isEmpty())
/*     */       return; 
/*     */     try {
/* 191 */       writer = this.store.createFeatureWriter(typeName, this.transaction);
/* 192 */     } catch (UnsupportedOperationException e) {
/* 193 */       throw e;
/*     */     } 
/* 198 */     Throwable cause = null;
/*     */     try {
/* 200 */       while (writer.hasNext()) {
/* 201 */         SimpleFeature feature = writer.next();
/* 202 */         String fid = feature.getID();
/* 204 */         if (diff.getModified().containsKey(fid)) {
/* 205 */           SimpleFeature update = diff.getModified().get(fid);
/* 207 */           if (update == NULL) {
/* 208 */             writer.remove();
/* 211 */             this.store.listenerManager.fireFeaturesRemoved(typeName, this.transaction, ReferencedEnvelope.reference(feature.getBounds()), true);
/*     */             continue;
/*     */           } 
/*     */           try {
/* 215 */             feature.setAttributes(update.getAttributes());
/* 216 */             writer.write();
/* 219 */             ReferencedEnvelope bounds = new ReferencedEnvelope((CoordinateReferenceSystem)null);
/* 220 */             bounds.include(feature.getBounds());
/* 221 */             bounds.include(update.getBounds());
/* 222 */             this.store.listenerManager.fireFeaturesChanged(typeName, this.transaction, bounds, true);
/* 224 */           } catch (IllegalAttributeException e) {
/* 225 */             throw new DataSourceException("Could update " + fid, e);
/*     */           } 
/*     */         } 
/*     */       } 
/* 235 */       synchronized (diff) {
/* 236 */         for (String fid : diff.getAddedOrder()) {
/* 237 */           SimpleFeature addedFeature = diff.getAdded().get(fid);
/* 239 */           SimpleFeature nextFeature = writer.next();
/* 241 */           if (nextFeature == null)
/* 242 */             throw new DataSourceException("Could not add " + fid); 
/*     */           try {
/* 245 */             nextFeature.setAttributes(addedFeature.getAttributes());
/* 248 */             nextFeature.getUserData().put(Hints.USE_PROVIDED_FID, Boolean.valueOf(true));
/* 249 */             if (addedFeature.getUserData().containsKey(Hints.PROVIDED_FID)) {
/* 250 */               String providedFid = (String)addedFeature.getUserData().get(Hints.PROVIDED_FID);
/* 251 */               nextFeature.getUserData().put(Hints.PROVIDED_FID, providedFid);
/*     */             } else {
/* 254 */               nextFeature.getUserData().put(Hints.PROVIDED_FID, addedFeature.getID());
/*     */             } 
/* 257 */             writer.write();
/* 260 */             this.store.listenerManager.fireFeaturesAdded(typeName, this.transaction, ReferencedEnvelope.reference(nextFeature.getBounds()), true);
/* 262 */           } catch (IllegalAttributeException e) {
/* 263 */             throw new DataSourceException("Could update " + fid, e);
/*     */           } 
/*     */         } 
/*     */       } 
/* 269 */     } catch (IOException e) {
/* 270 */       cause = e;
/* 271 */       throw e;
/* 272 */     } catch (RuntimeException e) {
/* 273 */       cause = e;
/* 274 */       throw e;
/*     */     } finally {
/*     */       try {
/* 277 */         writer.close();
/* 278 */         this.store.listenerManager.fireChanged(typeName, this.transaction, true);
/* 279 */         diff.clear();
/* 280 */       } catch (IOException e) {
/* 281 */         if (cause != null)
/* 282 */           e.initCause(cause); 
/* 284 */         throw e;
/* 285 */       } catch (RuntimeException e) {
/* 286 */         if (cause != null)
/* 287 */           e.initCause(cause); 
/* 289 */         throw e;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public synchronized void rollback() throws IOException {
/* 300 */     for (Iterator<Map.Entry<String, Diff>> i = this.typeNameDiff.entrySet().iterator(); i.hasNext(); ) {
/* 301 */       Map.Entry<String, Diff> entry = i.next();
/* 303 */       String typeName = entry.getKey();
/* 304 */       Diff diff = entry.getValue();
/* 306 */       diff.clear();
/* 307 */       this.store.listenerManager.fireChanged(typeName, this.transaction, false);
/*     */     } 
/*     */   }
/*     */   
/*     */   public synchronized FeatureReader<SimpleFeatureType, SimpleFeature> reader(String typeName) throws IOException {
/* 327 */     Diff diff = diff(typeName);
/* 328 */     FeatureReader<SimpleFeatureType, SimpleFeature> reader = this.store.getFeatureReader(typeName);
/* 330 */     return new DiffFeatureReader<SimpleFeatureType, SimpleFeature>(reader, diff);
/*     */   }
/*     */   
/*     */   public synchronized FeatureWriter<SimpleFeatureType, SimpleFeature> writer(final String typeName, Filter filter) throws IOException {
/* 350 */     Diff diff = diff(typeName);
/* 351 */     FeatureReader<SimpleFeatureType, SimpleFeature> reader = new FilteringFeatureReader<SimpleFeatureType, SimpleFeature>(this.store.getFeatureReader(typeName, new Query(typeName, filter)), filter);
/* 353 */     return new DiffFeatureWriter(reader, diff, filter) {
/*     */         public void fireNotification(int eventType, ReferencedEnvelope bounds) {
/* 355 */           switch (eventType) {
/*     */             case 1:
/* 357 */               TransactionStateDiff.this.store.listenerManager.fireFeaturesAdded(typeName, TransactionStateDiff.this.transaction, bounds, false);
/*     */               break;
/*     */             case 0:
/* 363 */               TransactionStateDiff.this.store.listenerManager.fireFeaturesChanged(typeName, TransactionStateDiff.this.transaction, bounds, false);
/*     */               break;
/*     */             case -1:
/* 369 */               TransactionStateDiff.this.store.listenerManager.fireFeaturesRemoved(typeName, TransactionStateDiff.this.transaction, bounds, false);
/*     */               break;
/*     */           } 
/*     */         }
/*     */         
/*     */         public String toString() {
/* 376 */           return "<DiffFeatureWriter>(" + this.reader.toString() + ")";
/*     */         }
/*     */       };
/*     */   }
/*     */   
/* 388 */   public static final SimpleFeature NULL = new SimpleFeature() {
/*     */       public Object getAttribute(String path) {
/* 390 */         return null;
/*     */       }
/*     */       
/*     */       public Object getAttribute(int index) {
/* 394 */         return null;
/*     */       }
/*     */       
/*     */       public ReferencedEnvelope getBounds() {
/* 402 */         return null;
/*     */       }
/*     */       
/*     */       public Geometry getDefaultGeometry() {
/* 406 */         return null;
/*     */       }
/*     */       
/*     */       public SimpleFeatureType getFeatureType() {
/* 410 */         return null;
/*     */       }
/*     */       
/*     */       public String getID() {
/* 414 */         return null;
/*     */       }
/*     */       
/*     */       public FeatureId getIdentifier() {
/* 417 */         return null;
/*     */       }
/*     */       
/*     */       public void setAttribute(int position, Object val) {}
/*     */       
/*     */       public void setAttribute(String path, Object attribute) throws IllegalAttributeException {}
/*     */       
/*     */       public Object getAttribute(Name name) {
/* 435 */         return null;
/*     */       }
/*     */       
/*     */       public int getAttributeCount() {
/* 439 */         return 0;
/*     */       }
/*     */       
/*     */       public List<Object> getAttributes() {
/* 443 */         return null;
/*     */       }
/*     */       
/*     */       public SimpleFeatureType getType() {
/* 447 */         return null;
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
/* 463 */         return null;
/*     */       }
/*     */       
/*     */       public void setDefaultGeometryProperty(GeometryAttribute geometryAttribute) {}
/*     */       
/*     */       public Collection<Property> getProperties(Name name) {
/* 471 */         return null;
/*     */       }
/*     */       
/*     */       public Collection<Property> getProperties() {
/* 475 */         return null;
/*     */       }
/*     */       
/*     */       public Collection<Property> getProperties(String name) {
/* 479 */         return null;
/*     */       }
/*     */       
/*     */       public Property getProperty(Name name) {
/* 483 */         return null;
/*     */       }
/*     */       
/*     */       public Property getProperty(String name) {
/* 487 */         return null;
/*     */       }
/*     */       
/*     */       public Collection<? extends Property> getValue() {
/* 491 */         return null;
/*     */       }
/*     */       
/*     */       public void setValue(Collection<Property> values) {}
/*     */       
/*     */       public AttributeDescriptor getDescriptor() {
/* 498 */         return null;
/*     */       }
/*     */       
/*     */       public Name getName() {
/* 502 */         return null;
/*     */       }
/*     */       
/*     */       public Map<Object, Object> getUserData() {
/* 506 */         return null;
/*     */       }
/*     */       
/*     */       public boolean isNillable() {
/* 510 */         return false;
/*     */       }
/*     */       
/*     */       public void setValue(Object newValue) {}
/*     */       
/*     */       public String toString() {
/* 516 */         return "<NullFeature>";
/*     */       }
/*     */       
/*     */       public int hashCode() {
/* 519 */         return 0;
/*     */       }
/*     */       
/*     */       public boolean equals(Object arg0) {
/* 522 */         return (arg0 == this);
/*     */       }
/*     */       
/*     */       public void validate() {}
/*     */     };
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\TransactionStateDiff.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
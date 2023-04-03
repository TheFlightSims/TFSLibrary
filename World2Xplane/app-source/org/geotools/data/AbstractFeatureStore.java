/*     */ package org.geotools.data;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.HashSet;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import org.geotools.data.simple.SimpleFeatureStore;
/*     */ import org.geotools.factory.Hints;
/*     */ import org.geotools.feature.FeatureCollection;
/*     */ import org.geotools.feature.FeatureIterator;
/*     */ import org.geotools.feature.IllegalAttributeException;
/*     */ import org.geotools.feature.NameImpl;
/*     */ import org.geotools.filter.identity.FeatureIdImpl;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.AttributeDescriptor;
/*     */ import org.opengis.feature.type.Name;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.filter.identity.FeatureId;
/*     */ 
/*     */ public abstract class AbstractFeatureStore extends AbstractFeatureSource implements SimpleFeatureStore {
/*  52 */   protected Transaction transaction = Transaction.AUTO_COMMIT;
/*     */   
/*     */   public AbstractFeatureStore() {}
/*     */   
/*     */   public AbstractFeatureStore(Set hints) {
/*  63 */     super(hints);
/*     */   }
/*     */   
/*     */   public Transaction getTransaction() {
/*  72 */     return this.transaction;
/*     */   }
/*     */   
/*     */   public final void modifyFeatures(AttributeDescriptor type, Object value, Filter filter) throws IOException {
/* 109 */     Name attributeName = type.getName();
/* 110 */     modifyFeatures(attributeName, value, filter);
/*     */   }
/*     */   
/*     */   public void modifyFeatures(Name attributeName, Object attributeValue, Filter filter) throws IOException {
/* 114 */     modifyFeatures(new Name[] { attributeName }, new Object[] { attributeValue }, filter);
/*     */   }
/*     */   
/*     */   public void modifyFeatures(String name, Object attributeValue, Filter filter) throws IOException {
/* 120 */     modifyFeatures(new Name[] { (Name)new NameImpl(name) }, new Object[] { attributeValue }, filter);
/*     */   }
/*     */   
/*     */   public void modifyFeatures(String[] names, Object[] values, Filter filter) throws IOException {
/* 126 */     Name[] attributeNames = new Name[names.length];
/* 127 */     for (int i = 0; i < names.length; i++)
/* 128 */       attributeNames[i] = (Name)new NameImpl(names[i]); 
/* 130 */     modifyFeatures(attributeNames, values, filter);
/*     */   }
/*     */   
/*     */   public final void modifyFeatures(AttributeDescriptor[] type, Object[] value, Filter filter) throws IOException {
/* 169 */     Name[] attributeNames = new Name[type.length];
/* 170 */     for (int i = 0; i < type.length; i++)
/* 171 */       attributeNames[i] = type[i].getName(); 
/* 173 */     modifyFeatures(attributeNames, value, filter);
/*     */   }
/*     */   
/*     */   public void modifyFeatures(Name[] attributeNames, Object[] attributeValues, Filter filter) throws IOException {
/* 177 */     String typeName = ((SimpleFeatureType)getSchema()).getTypeName();
/* 178 */     if (filter == null) {
/* 179 */       String msg = "Must specify a filter, must not be null.";
/* 180 */       throw new IllegalArgumentException(msg);
/*     */     } 
/* 182 */     FeatureWriter<SimpleFeatureType, SimpleFeature> writer = getDataStore().getFeatureWriter(typeName, filter, getTransaction());
/* 185 */     for (Name attributeName : attributeNames) {
/* 186 */       if (((SimpleFeatureType)getSchema()).getDescriptor(attributeName) == null)
/* 187 */         throw new DataSourceException("Cannot modify " + attributeName + " as it is not an attribute of " + ((SimpleFeatureType)getSchema()).getName()); 
/*     */     } 
/*     */     try {
/* 191 */       while (writer.hasNext()) {
/* 192 */         SimpleFeature feature = writer.next();
/* 194 */         for (int i = 0; i < attributeNames.length; i++) {
/*     */           try {
/* 196 */             feature.setAttribute(attributeNames[i], attributeValues[i]);
/* 197 */           } catch (Exception e) {
/* 198 */             throw new DataSourceException("Could not update feature " + feature.getID() + " with " + attributeNames[i] + "=" + attributeValues[i], e);
/*     */           } 
/*     */         } 
/* 204 */         writer.write();
/*     */       } 
/*     */     } finally {
/* 207 */       writer.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   public Set<String> addFeatures(FeatureReader<SimpleFeatureType, SimpleFeature> reader) throws IOException {
/* 254 */     Set<String> addedFids = new HashSet<String>();
/* 255 */     String typeName = ((SimpleFeatureType)getSchema()).getTypeName();
/* 256 */     SimpleFeature feature = null;
/* 258 */     FeatureWriter<SimpleFeatureType, SimpleFeature> writer = getDataStore().getFeatureWriterAppend(typeName, getTransaction());
/*     */     try {
/* 262 */       while (reader.hasNext()) {
/*     */         try {
/* 264 */           feature = reader.next();
/* 265 */         } catch (Exception e) {
/* 266 */           throw new DataSourceException("Could not add Features, problem with provided reader", e);
/*     */         } 
/* 270 */         SimpleFeature newFeature = writer.next();
/*     */         try {
/* 273 */           newFeature.setAttributes(feature.getAttributes());
/* 274 */         } catch (Exception writeProblem) {
/* 275 */           throw new DataSourceException("Could not create " + typeName + " out of provided feature: " + feature.getID(), writeProblem);
/*     */         } 
/* 280 */         boolean useExisting = Boolean.TRUE.equals(feature.getUserData().get(Hints.USE_PROVIDED_FID));
/* 281 */         if (getQueryCapabilities().isUseProvidedFIDSupported() && useExisting)
/* 282 */           ((FeatureIdImpl)newFeature.getIdentifier()).setID(feature.getID()); 
/* 285 */         writer.write();
/* 286 */         addedFids.add(newFeature.getID());
/*     */       } 
/*     */     } finally {
/* 289 */       reader.close();
/* 290 */       writer.close();
/*     */     } 
/* 293 */     return addedFids;
/*     */   }
/*     */   
/*     */   public List<FeatureId> addFeatures(FeatureCollection<SimpleFeatureType, SimpleFeature> collection) throws IOException {
/* 298 */     List<FeatureId> addedFids = new LinkedList<FeatureId>();
/* 299 */     String typeName = ((SimpleFeatureType)getSchema()).getTypeName();
/* 300 */     SimpleFeature feature = null;
/* 302 */     FeatureWriter<SimpleFeatureType, SimpleFeature> writer = getDataStore().getFeatureWriterAppend(typeName, getTransaction());
/* 305 */     FeatureIterator iterator = collection.features();
/*     */     try {
/* 308 */       while (iterator.hasNext()) {
/* 309 */         feature = (SimpleFeature)iterator.next();
/* 310 */         SimpleFeature newFeature = writer.next();
/*     */         try {
/* 312 */           newFeature.setAttributes(feature.getAttributes());
/* 313 */         } catch (Exception writeProblem) {
/* 314 */           throw new DataSourceException("Could not create " + typeName + " out of provided feature: " + feature.getID(), writeProblem);
/*     */         } 
/* 319 */         boolean useExisting = Boolean.TRUE.equals(feature.getUserData().get(Hints.USE_PROVIDED_FID));
/* 320 */         if (getQueryCapabilities().isUseProvidedFIDSupported() && useExisting)
/* 321 */           ((FeatureIdImpl)newFeature.getIdentifier()).setID(feature.getID()); 
/* 324 */         writer.write();
/* 325 */         addedFids.add(newFeature.getIdentifier());
/*     */       } 
/*     */     } finally {
/* 328 */       iterator.close();
/* 329 */       writer.close();
/*     */     } 
/* 331 */     return addedFids;
/*     */   }
/*     */   
/*     */   public void removeFeatures(Filter filter) throws IOException {
/* 361 */     String typeName = ((SimpleFeatureType)getSchema()).getTypeName();
/* 362 */     FeatureWriter<SimpleFeatureType, SimpleFeature> writer = getDataStore().getFeatureWriter(typeName, filter, getTransaction());
/*     */     try {
/* 366 */       while (writer.hasNext()) {
/* 367 */         writer.next();
/* 368 */         writer.remove();
/*     */       } 
/*     */     } finally {
/* 371 */       writer.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setFeatures(FeatureReader<SimpleFeatureType, SimpleFeature> reader) throws IOException {
/* 410 */     String typeName = ((SimpleFeatureType)getSchema()).getTypeName();
/* 411 */     FeatureWriter<SimpleFeatureType, SimpleFeature> writer = getDataStore().getFeatureWriter(typeName, getTransaction());
/*     */     try {
/* 417 */       while (writer.hasNext()) {
/* 418 */         SimpleFeature feature = writer.next();
/* 419 */         writer.remove();
/*     */       } 
/* 422 */       while (reader.hasNext()) {
/*     */         SimpleFeature feature;
/*     */         try {
/* 424 */           feature = reader.next();
/* 425 */         } catch (Exception readProblem) {
/* 426 */           throw new DataSourceException("Could not add Features, problem with provided reader", readProblem);
/*     */         } 
/* 430 */         SimpleFeature newFeature = writer.next();
/*     */         try {
/* 433 */           newFeature.setAttributes(feature.getAttributes());
/* 434 */         } catch (IllegalAttributeException writeProblem) {
/* 435 */           throw new DataSourceException("Could not create " + typeName + " out of provided feature: " + feature.getID(), writeProblem);
/*     */         } 
/* 440 */         writer.write();
/*     */       } 
/*     */     } finally {
/* 443 */       reader.close();
/* 444 */       writer.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setTransaction(Transaction transaction) {
/* 449 */     if (transaction == null)
/* 450 */       throw new IllegalArgumentException("Transaction cannot be null, did you mean Transaction.AUTO_COMMIT?"); 
/* 454 */     this.transaction = transaction;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\AbstractFeatureStore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
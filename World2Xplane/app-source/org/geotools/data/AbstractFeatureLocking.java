/*     */ package org.geotools.data;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Set;
/*     */ import org.geotools.data.simple.SimpleFeatureIterator;
/*     */ import org.geotools.data.simple.SimpleFeatureLocking;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.filter.Filter;
/*     */ 
/*     */ public abstract class AbstractFeatureLocking extends AbstractFeatureStore implements FeatureLocking<SimpleFeatureType, SimpleFeature>, SimpleFeatureLocking {
/*  66 */   FeatureLock featureLock = FeatureLock.TRANSACTION;
/*     */   
/*     */   public AbstractFeatureLocking() {}
/*     */   
/*     */   public AbstractFeatureLocking(Set hints) {
/*  77 */     super(hints);
/*     */   }
/*     */   
/*     */   public void setFeatureLock(FeatureLock lock) {
/*  95 */     if (lock == null)
/*  96 */       throw new NullPointerException("A FeatureLock is required - did you mean FeatureLock.TRANSACTION?"); 
/* 100 */     this.featureLock = lock;
/*     */   }
/*     */   
/*     */   public int lockFeatures() throws IOException {
/* 113 */     return lockFeatures((Filter)Filter.INCLUDE);
/*     */   }
/*     */   
/*     */   public int lockFeatures(Filter filter) throws IOException {
/* 126 */     return lockFeatures(new DefaultQuery(getSchema().getTypeName(), filter));
/*     */   }
/*     */   
/*     */   public int lockFeatures(Query query) throws IOException {
/* 150 */     LockingManager lockingManager = getDataStore().getLockingManager();
/* 152 */     if (lockingManager == null)
/* 153 */       throw new UnsupportedOperationException("DataStore not using lockingManager, must provide alternate implementation"); 
/* 159 */     SimpleFeatureIterator reader = getFeatures(query).features();
/* 160 */     String typeName = query.getTypeName();
/* 162 */     int count = 0;
/*     */     try {
/* 165 */       while (reader.hasNext()) {
/*     */         try {
/* 167 */           SimpleFeature feature = (SimpleFeature)reader.next();
/* 168 */           lockingManager.lockFeatureID(typeName, feature.getID(), getTransaction(), this.featureLock);
/* 170 */           count++;
/* 171 */         } catch (FeatureLockException locked) {
/*     */         
/* 173 */         } catch (NoSuchElementException nosuch) {
/* 174 */           throw new DataSourceException("Problem with " + query.getHandle() + " while locking", nosuch);
/*     */         } 
/*     */       } 
/*     */     } finally {
/* 179 */       reader.close();
/*     */     } 
/* 182 */     return count;
/*     */   }
/*     */   
/*     */   public void unLockFeatures() throws IOException {
/* 193 */     unLockFeatures((Filter)Filter.INCLUDE);
/*     */   }
/*     */   
/*     */   public void unLockFeatures(Filter filter) throws IOException {
/* 204 */     unLockFeatures(new DefaultQuery(getSchema().getTypeName(), filter));
/*     */   }
/*     */   
/*     */   public void unLockFeatures(Query query) throws IOException {
/* 226 */     LockingManager lockingManager = getDataStore().getLockingManager();
/* 228 */     if (lockingManager == null)
/* 229 */       throw new UnsupportedOperationException("DataStore not using lockingManager, must provide alternate implementation"); 
/* 235 */     SimpleFeatureIterator reader = getFeatures(query).features();
/* 236 */     String typeName = query.getTypeName();
/*     */     try {
/* 240 */       while (reader.hasNext()) {
/*     */         try {
/* 242 */           SimpleFeature feature = (SimpleFeature)reader.next();
/* 243 */           lockingManager.unLockFeatureID(typeName, feature.getID(), getTransaction(), this.featureLock);
/* 245 */         } catch (NoSuchElementException nosuch) {
/* 246 */           throw new DataSourceException("Problem with " + query.getHandle() + " while locking", nosuch);
/*     */         } 
/*     */       } 
/*     */     } finally {
/* 251 */       reader.close();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\AbstractFeatureLocking.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
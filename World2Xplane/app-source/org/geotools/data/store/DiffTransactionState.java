/*     */ package org.geotools.data.store;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.geotools.data.DataSourceException;
/*     */ import org.geotools.data.Diff;
/*     */ import org.geotools.data.FeatureSource;
/*     */ import org.geotools.data.FeatureWriter;
/*     */ import org.geotools.data.Transaction;
/*     */ import org.geotools.data.TransactionStateDiff;
/*     */ import org.geotools.factory.Hints;
/*     */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*     */ import org.opengis.feature.Feature;
/*     */ import org.opengis.feature.IllegalAttributeException;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.Name;
/*     */ import org.opengis.filter.Filter;
/*     */ 
/*     */ public class DiffTransactionState implements Transaction.State {
/*     */   protected Diff diff;
/*     */   
/*     */   protected Transaction transaction;
/*     */   
/*     */   protected ContentState state;
/*     */   
/*     */   public DiffTransactionState(ContentState state) {
/*  55 */     this.state = state;
/*  56 */     this.diff = new Diff();
/*     */   }
/*     */   
/*     */   public Diff getDiff() {
/*  63 */     return this.diff;
/*     */   }
/*     */   
/*     */   public synchronized void setTransaction(Transaction transaction) {
/*  74 */     if (this.transaction != null && transaction == null)
/*  76 */       this.state.getEntry().clearTransaction(this.transaction); 
/*  78 */     this.transaction = transaction;
/*     */   }
/*     */   
/*     */   public synchronized void commit() throws IOException {
/*     */     FeatureWriter<SimpleFeatureType, SimpleFeature> writer;
/*     */     ContentFeatureStore store;
/* 115 */     if (this.diff.isEmpty())
/*     */       return; 
/* 120 */     ContentEntry entry = this.state.getEntry();
/* 121 */     Name name = entry.getName();
/* 122 */     ContentDataStore dataStore = entry.getDataStore();
/* 123 */     ContentFeatureSource source = (ContentFeatureSource)dataStore.getFeatureSource(name);
/* 124 */     if (source instanceof ContentFeatureStore) {
/* 125 */       store = (ContentFeatureStore)dataStore.getFeatureSource(name);
/* 126 */       writer = store.getWriter((Filter)Filter.INCLUDE);
/*     */     } else {
/* 128 */       throw new UnsupportedOperationException("not writable");
/*     */     } 
/* 133 */     Throwable cause = null;
/*     */     try {
/* 135 */       while (writer.hasNext()) {
/* 136 */         SimpleFeature feature = (SimpleFeature)writer.next();
/* 137 */         String fid = feature.getID();
/* 139 */         if (this.diff.getModified().containsKey(fid)) {
/* 140 */           SimpleFeature update = (SimpleFeature)this.diff.getModified().get(fid);
/* 142 */           if (update == TransactionStateDiff.NULL) {
/* 143 */             writer.remove();
/* 146 */             this.state.fireFeatureRemoved((FeatureSource<?, ?>)store, (Feature)feature);
/*     */             continue;
/*     */           } 
/*     */           try {
/* 149 */             feature.setAttributes(update.getAttributes());
/* 150 */             writer.write();
/* 153 */             ReferencedEnvelope bounds = ReferencedEnvelope.reference(feature.getBounds());
/* 155 */             this.state.fireFeatureUpdated((FeatureSource<?, ?>)store, (Feature)update, bounds);
/* 156 */           } catch (IllegalAttributeException e) {
/* 157 */             throw new DataSourceException("Could update " + fid, e);
/*     */           } 
/*     */         } 
/*     */       } 
/* 166 */       synchronized (this.diff) {
/* 167 */         for (String fid : this.diff.getAddedOrder()) {
/* 168 */           SimpleFeature addedFeature = (SimpleFeature)this.diff.getAdded().get(fid);
/* 170 */           SimpleFeature nextFeature = (SimpleFeature)writer.next();
/* 172 */           if (nextFeature == null)
/* 173 */             throw new DataSourceException("Could not add " + fid); 
/*     */           try {
/* 176 */             nextFeature.setAttributes(addedFeature.getAttributes());
/* 179 */             nextFeature.getUserData().put(Hints.USE_PROVIDED_FID, Boolean.valueOf(true));
/* 180 */             if (addedFeature.getUserData().containsKey(Hints.PROVIDED_FID)) {
/* 181 */               String providedFid = (String)addedFeature.getUserData().get(Hints.PROVIDED_FID);
/* 183 */               nextFeature.getUserData().put(Hints.PROVIDED_FID, providedFid);
/*     */             } else {
/* 185 */               nextFeature.getUserData().put(Hints.PROVIDED_FID, addedFeature.getID());
/*     */             } 
/* 189 */             writer.write();
/* 192 */             this.state.fireFeatureAdded((FeatureSource<?, ?>)store, (Feature)nextFeature);
/* 193 */           } catch (IllegalAttributeException e) {
/* 194 */             throw new DataSourceException("Could update " + fid, e);
/*     */           } 
/*     */         } 
/*     */       } 
/* 199 */     } catch (IOException e) {
/* 200 */       cause = e;
/* 201 */       throw e;
/* 202 */     } catch (RuntimeException e) {
/* 203 */       cause = e;
/* 204 */       throw e;
/*     */     } finally {
/*     */       try {
/* 207 */         writer.close();
/* 208 */         this.state.fireBatchFeatureEvent(true);
/* 209 */         this.diff.clear();
/* 210 */       } catch (IOException e) {
/* 211 */         if (cause != null)
/* 212 */           e.initCause(cause); 
/* 214 */         throw e;
/* 215 */       } catch (RuntimeException e) {
/* 216 */         if (cause != null)
/* 217 */           e.initCause(cause); 
/* 219 */         throw e;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public synchronized void rollback() throws IOException {
/* 229 */     this.diff.clear();
/* 230 */     this.state.fireBatchFeatureEvent(false);
/*     */   }
/*     */   
/*     */   public synchronized void addAuthorization(String AuthID) throws IOException {}
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\store\DiffTransactionState.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
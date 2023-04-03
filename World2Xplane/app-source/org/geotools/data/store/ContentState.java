/*     */ package org.geotools.data.store;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import java.util.logging.Level;
/*     */ import org.geotools.data.BatchFeatureEvent;
/*     */ import org.geotools.data.FeatureEvent;
/*     */ import org.geotools.data.FeatureListener;
/*     */ import org.geotools.data.FeatureSource;
/*     */ import org.geotools.data.Transaction;
/*     */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*     */ import org.opengis.feature.Feature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.filter.FilterFactory;
/*     */ import org.opengis.filter.identity.FeatureId;
/*     */ 
/*     */ public class ContentState {
/*     */   protected Transaction tx;
/*     */   
/*     */   protected ContentEntry entry;
/*     */   
/*     */   protected SimpleFeatureType featureType;
/*     */   
/* 134 */   protected int count = -1;
/*     */   
/*     */   protected ReferencedEnvelope bounds;
/*     */   
/*     */   protected BatchFeatureEvent batchFeatureEvent;
/*     */   
/* 151 */   protected List<FeatureListener> listeners = Collections.synchronizedList(new ArrayList<FeatureListener>());
/*     */   
/* 158 */   protected DiffTransactionState transactionState = new DiffTransactionState(this);
/*     */   
/*     */   public ContentState(ContentEntry entry) {
/* 166 */     this.entry = entry;
/*     */   }
/*     */   
/*     */   protected ContentState(ContentState state) {
/* 179 */     this(state.getEntry());
/* 181 */     this.featureType = state.featureType;
/* 182 */     this.count = state.count;
/* 183 */     this.bounds = (state.bounds == null) ? null : new ReferencedEnvelope(state.bounds);
/* 184 */     this.batchFeatureEvent = null;
/*     */   }
/*     */   
/*     */   public ContentEntry getEntry() {
/* 191 */     return this.entry;
/*     */   }
/*     */   
/*     */   public Transaction getTransaction() {
/* 198 */     return this.tx;
/*     */   }
/*     */   
/*     */   public void setTransaction(Transaction tx) {
/* 205 */     this.tx = tx;
/* 206 */     if (tx != Transaction.AUTO_COMMIT)
/* 207 */       tx.putState(this.entry, this.transactionState); 
/*     */   }
/*     */   
/*     */   public final SimpleFeatureType getFeatureType() {
/* 215 */     return this.featureType;
/*     */   }
/*     */   
/*     */   public final void setFeatureType(SimpleFeatureType featureType) {
/* 222 */     this.featureType = featureType;
/*     */   }
/*     */   
/*     */   public final int getCount() {
/* 230 */     return this.count;
/*     */   }
/*     */   
/*     */   public final void setCount(int count) {
/* 237 */     this.count = count;
/*     */   }
/*     */   
/*     */   public final ReferencedEnvelope getBounds() {
/* 244 */     return this.bounds;
/*     */   }
/*     */   
/*     */   public final void setBounds(ReferencedEnvelope bounds) {
/* 251 */     this.bounds = bounds;
/*     */   }
/*     */   
/*     */   public final void addListener(FeatureListener listener) {
/* 260 */     this.listeners.add(listener);
/*     */   }
/*     */   
/*     */   public final void removeListener(FeatureListener listener) {
/* 269 */     this.listeners.remove(listener);
/*     */   }
/*     */   
/*     */   public BatchFeatureEvent getBatchFeatureEvent() {
/* 273 */     return this.batchFeatureEvent;
/*     */   }
/*     */   
/*     */   public final boolean hasListener() {
/* 282 */     if (!this.listeners.isEmpty())
/* 283 */       return true; 
/* 285 */     if (this.tx == Transaction.AUTO_COMMIT && this.entry.state.size() > 1)
/* 287 */       return true; 
/* 289 */     return false;
/*     */   }
/*     */   
/*     */   public void fireFeatureUpdated(FeatureSource<?, ?> source, Feature feature, ReferencedEnvelope before) {
/* 305 */     if (feature == null)
/*     */       return; 
/* 308 */     if (this.listeners.isEmpty() && this.tx != Transaction.AUTO_COMMIT)
/*     */       return; 
/* 311 */     Filter filter = idFilter(feature);
/* 312 */     ReferencedEnvelope bounds = new ReferencedEnvelope(feature.getBounds());
/* 313 */     if (bounds != null)
/* 314 */       bounds.expandToInclude((Envelope)before); 
/* 317 */     FeatureEvent event = new FeatureEvent(source, FeatureEvent.Type.CHANGED, bounds, filter);
/* 318 */     fireFeatureEvent(event);
/*     */   }
/*     */   
/*     */   public final void fireFeatureAdded(FeatureSource<?, ?> source, Feature feature) {
/* 328 */     if (this.listeners.isEmpty() && this.tx != Transaction.AUTO_COMMIT)
/*     */       return; 
/* 331 */     Filter filter = idFilter(feature);
/* 332 */     ReferencedEnvelope bounds = new ReferencedEnvelope(feature.getBounds());
/* 334 */     FeatureEvent event = new FeatureEvent(source, FeatureEvent.Type.ADDED, bounds, filter);
/* 336 */     fireFeatureEvent(event);
/*     */   }
/*     */   
/*     */   public void fireFeatureRemoved(FeatureSource<?, ?> source, Feature feature) {
/* 340 */     if (this.listeners.isEmpty() && this.tx != Transaction.AUTO_COMMIT)
/*     */       return; 
/* 343 */     Filter filter = idFilter(feature);
/* 344 */     ReferencedEnvelope bounds = new ReferencedEnvelope(feature.getBounds());
/* 346 */     FeatureEvent event = new FeatureEvent(source, FeatureEvent.Type.REMOVED, bounds, filter);
/* 348 */     fireFeatureEvent(event);
/*     */   }
/*     */   
/*     */   Filter idFilter(Feature feature) {
/* 355 */     FilterFactory ff = this.entry.dataStore.getFilterFactory();
/* 356 */     Set<FeatureId> fids = new HashSet<FeatureId>();
/* 357 */     fids.add(feature.getIdentifier());
/* 358 */     return (Filter)ff.id(fids);
/*     */   }
/*     */   
/*     */   public final void fireFeatureEvent(FeatureEvent event) {
/* 373 */     if (this.tx == Transaction.AUTO_COMMIT) {
/* 374 */       this.entry.notifiyFeatureEvent(this, event);
/*     */     } else {
/* 378 */       if (this.batchFeatureEvent == null)
/* 379 */         this.batchFeatureEvent = new BatchFeatureEvent(event.getFeatureSource()); 
/* 381 */       this.batchFeatureEvent.add(event);
/*     */     } 
/* 383 */     if (this.listeners.isEmpty())
/*     */       return; 
/* 386 */     for (FeatureListener listener : this.listeners) {
/*     */       try {
/* 388 */         listener.changed(event);
/* 389 */       } catch (Throwable t) {
/* 390 */         this.entry.dataStore.LOGGER.log(Level.WARNING, "Problem issuing batch feature event " + event, t);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public final void fireBatchFeatureEvent(boolean isCommit) {
/* 401 */     if (this.batchFeatureEvent == null)
/*     */       return; 
/* 404 */     if (this.listeners.isEmpty())
/*     */       return; 
/* 407 */     if (isCommit) {
/* 408 */       this.batchFeatureEvent.setType(FeatureEvent.Type.COMMIT);
/*     */     } else {
/* 410 */       this.batchFeatureEvent.setType(FeatureEvent.Type.ROLLBACK);
/*     */     } 
/* 412 */     for (FeatureListener listener : this.listeners) {
/*     */       try {
/* 414 */         listener.changed((FeatureEvent)this.batchFeatureEvent);
/* 415 */       } catch (Throwable t) {
/* 416 */         this.entry.dataStore.LOGGER.log(Level.WARNING, "Problem issuing batch feature event " + this.batchFeatureEvent, t);
/*     */       } 
/*     */     } 
/* 421 */     this.entry.notifiyFeatureEvent(this, (FeatureEvent)this.batchFeatureEvent);
/* 423 */     this.batchFeatureEvent = null;
/*     */   }
/*     */   
/*     */   public void flush() {
/* 434 */     this.featureType = null;
/* 435 */     this.count = -1;
/* 436 */     this.bounds = null;
/*     */   }
/*     */   
/*     */   public void close() {
/* 448 */     this.featureType = null;
/* 449 */     if (this.listeners != null) {
/* 450 */       this.listeners.clear();
/* 451 */       this.listeners = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public ContentState copy() {
/* 464 */     return new ContentState(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\store\ContentState.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
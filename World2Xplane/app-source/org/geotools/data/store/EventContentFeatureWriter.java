/*     */ package org.geotools.data.store;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.geotools.data.FeatureSource;
/*     */ import org.geotools.data.FeatureWriter;
/*     */ import org.geotools.data.Transaction;
/*     */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*     */ import org.opengis.feature.Feature;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ 
/*     */ public class EventContentFeatureWriter implements FeatureWriter<SimpleFeatureType, SimpleFeature> {
/*     */   ContentState state;
/*     */   
/*     */   SimpleFeature feature;
/*     */   
/*     */   ContentFeatureStore store;
/*     */   
/*     */   FeatureWriter<SimpleFeatureType, SimpleFeature> writer;
/*     */   
/*     */   public EventContentFeatureWriter(ContentFeatureStore store, FeatureWriter<SimpleFeatureType, SimpleFeature> writer) {
/*  70 */     this.store = store;
/*  71 */     this.writer = writer;
/*  72 */     this.state = store.getState();
/*  74 */     Transaction t = this.state.getTransaction();
/*  75 */     if (t != Transaction.AUTO_COMMIT)
/*  77 */       t.putState(this, new EventContentTransactionState()); 
/*     */   }
/*     */   
/*     */   public SimpleFeatureType getFeatureType() {
/*  87 */     return (SimpleFeatureType)this.writer.getFeatureType();
/*     */   }
/*     */   
/*     */   public SimpleFeature next() throws IOException {
/*  96 */     this.feature = (SimpleFeature)this.writer.next();
/*  97 */     return this.feature;
/*     */   }
/*     */   
/*     */   public void remove() throws IOException {
/* 104 */     this.state.fireFeatureRemoved((FeatureSource<?, ?>)this.store, (Feature)this.feature);
/* 105 */     this.writer.remove();
/*     */   }
/*     */   
/*     */   public void write() throws IOException {
/* 116 */     this.writer.write();
/* 117 */     if (this.feature == null)
/* 118 */       throw new IOException("No feature available to write"); 
/* 120 */     if (this.writer.hasNext()) {
/* 122 */       ReferencedEnvelope bounds = ReferencedEnvelope.reference(this.feature.getBounds());
/* 123 */       this.state.fireFeatureUpdated((FeatureSource<?, ?>)this.store, (Feature)this.feature, bounds);
/* 124 */       this.feature = null;
/*     */     } else {
/* 127 */       this.state.fireFeatureAdded((FeatureSource<?, ?>)this.store, (Feature)this.feature);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean hasNext() throws IOException {
/* 137 */     return this.writer.hasNext();
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 150 */     Transaction t = this.state.getTransaction();
/* 151 */     if (t != Transaction.AUTO_COMMIT)
/* 152 */       t.removeState(this); 
/* 154 */     if (this.writer != null) {
/* 155 */       this.writer.close();
/* 156 */       this.writer = null;
/*     */     } 
/* 158 */     this.feature = null;
/*     */   }
/*     */   
/*     */   class EventContentTransactionState implements Transaction.State {
/*     */     public void commit() throws IOException {
/* 168 */       EventContentFeatureWriter.this.store.getState().fireBatchFeatureEvent(true);
/*     */     }
/*     */     
/*     */     public void rollback() throws IOException {
/* 172 */       EventContentFeatureWriter.this.store.getState().fireBatchFeatureEvent(false);
/*     */     }
/*     */     
/*     */     public void setTransaction(Transaction transaction) {}
/*     */     
/*     */     public void addAuthorization(String AuthID) throws IOException {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\store\EventContentFeatureWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package org.geotools.data.store;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Map;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.geotools.data.DataSourceException;
/*     */ import org.geotools.data.Diff;
/*     */ import org.geotools.data.FeatureReader;
/*     */ import org.geotools.data.FeatureSource;
/*     */ import org.geotools.data.FeatureWriter;
/*     */ import org.geotools.factory.Hints;
/*     */ import org.geotools.feature.simple.SimpleFeatureBuilder;
/*     */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*     */ import org.opengis.feature.Feature;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ 
/*     */ public class DiffContentFeatureWriter implements FeatureWriter<SimpleFeatureType, SimpleFeature> {
/*     */   protected FeatureReader<SimpleFeatureType, SimpleFeature> reader;
/*     */   
/*     */   ContentState state;
/*     */   
/*     */   protected Diff diff;
/*     */   
/*     */   SimpleFeature next;
/*     */   
/*     */   SimpleFeature live;
/*     */   
/*     */   SimpleFeature current;
/*     */   
/*     */   ContentFeatureStore store;
/*     */   
/*     */   public DiffContentFeatureWriter(ContentFeatureStore store, Diff diff, FeatureReader<SimpleFeatureType, SimpleFeature> reader) {
/*  75 */     this.store = store;
/*  76 */     this.reader = reader;
/*  77 */     this.state = store.getState();
/*  78 */     this.diff = diff;
/*     */   }
/*     */   
/*     */   public SimpleFeatureType getFeatureType() {
/*  87 */     return (SimpleFeatureType)this.reader.getFeatureType();
/*     */   }
/*     */   
/*     */   public SimpleFeature next() throws IOException {
/*  96 */     SimpleFeatureType type = getFeatureType();
/*  97 */     if (hasNext()) {
/* 100 */       this.live = this.next;
/* 101 */       this.next = null;
/* 102 */       this.current = SimpleFeatureBuilder.copy(this.live);
/* 104 */       return this.current;
/*     */     } 
/* 109 */     this.live = null;
/* 110 */     this.next = null;
/* 111 */     this.current = SimpleFeatureBuilder.build(type, new Object[type.getAttributeCount()], "new" + this.diff.nextFID);
/* 113 */     this.diff.nextFID++;
/* 114 */     return this.current;
/*     */   }
/*     */   
/*     */   public void remove() throws IOException {
/* 122 */     if (this.live != null) {
/* 124 */       this.diff.remove(this.live.getID());
/* 125 */       this.state.fireFeatureRemoved((FeatureSource<?, ?>)this.store, (Feature)this.live);
/* 127 */       this.live = null;
/* 128 */       this.current = null;
/* 129 */     } else if (this.current != null) {
/* 131 */       this.current = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void write() throws IOException {
/* 146 */     if (this.live != null) {
/* 148 */       this.diff.modify(this.live.getID(), this.current);
/* 150 */       ReferencedEnvelope bounds = ReferencedEnvelope.reference(this.current.getBounds());
/* 151 */       this.state.fireFeatureUpdated((FeatureSource<?, ?>)this.store, (Feature)this.live, bounds);
/* 152 */       this.live = null;
/* 153 */       this.current = null;
/* 154 */     } else if (this.live == null && this.current != null) {
/* 157 */       String fid = this.current.getID();
/* 158 */       if (Boolean.TRUE.equals(this.current.getUserData().get(Hints.USE_PROVIDED_FID)) && 
/* 159 */         this.current.getUserData().containsKey(Hints.PROVIDED_FID)) {
/* 160 */         fid = (String)this.current.getUserData().get(Hints.PROVIDED_FID);
/* 161 */         Map<Object, Object> userData = this.current.getUserData();
/* 162 */         this.current = SimpleFeatureBuilder.build(this.current.getFeatureType(), this.current.getAttributes(), fid);
/* 164 */         this.current.getUserData().putAll(userData);
/*     */       } 
/* 167 */       this.diff.add(fid, this.current);
/* 168 */       this.state.fireFeatureAdded((FeatureSource<?, ?>)this.store, (Feature)this.current);
/* 169 */       this.current = null;
/*     */     } else {
/* 171 */       throw new IOException("No feature available to write");
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean hasNext() throws IOException {
/* 181 */     if (this.next != null)
/* 183 */       return true; 
/* 186 */     this.live = null;
/* 187 */     this.current = null;
/* 189 */     if (this.reader.hasNext()) {
/*     */       try {
/* 191 */         this.next = (SimpleFeature)this.reader.next();
/* 192 */       } catch (NoSuchElementException e) {
/* 193 */         throw new DataSourceException("No more content", e);
/*     */       } 
/* 196 */       return true;
/*     */     } 
/* 199 */     return false;
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 213 */     if (this.reader != null) {
/* 214 */       this.reader.close();
/* 215 */       this.reader = null;
/*     */     } 
/* 218 */     this.current = null;
/* 219 */     this.live = null;
/* 220 */     this.next = null;
/* 221 */     this.diff = null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\store\DiffContentFeatureWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
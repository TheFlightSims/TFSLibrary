/*     */ package org.geotools.data;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Map;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.geotools.factory.Hints;
/*     */ import org.geotools.feature.IllegalAttributeException;
/*     */ import org.geotools.feature.simple.SimpleFeatureBuilder;
/*     */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*     */ import org.opengis.feature.Feature;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ import org.opengis.filter.Filter;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ 
/*     */ public abstract class DiffFeatureWriter implements FeatureWriter<SimpleFeatureType, SimpleFeature> {
/*     */   protected FeatureReader<SimpleFeatureType, SimpleFeature> reader;
/*     */   
/*     */   protected Diff diff;
/*     */   
/*     */   SimpleFeature next;
/*     */   
/*     */   SimpleFeature live;
/*     */   
/*     */   SimpleFeature current;
/*     */   
/*     */   public DiffFeatureWriter(FeatureReader<SimpleFeatureType, SimpleFeature> reader, Diff diff) {
/*  66 */     this(reader, diff, (Filter)Filter.INCLUDE);
/*     */   }
/*     */   
/*     */   public DiffFeatureWriter(FeatureReader<SimpleFeatureType, SimpleFeature> reader, Diff diff, Filter filter) {
/*  77 */     this.reader = new DiffFeatureReader<SimpleFeatureType, SimpleFeature>(reader, diff, filter);
/*  78 */     this.diff = diff;
/*     */   }
/*     */   
/*     */   public SimpleFeatureType getFeatureType() {
/*  87 */     return this.reader.getFeatureType();
/*     */   }
/*     */   
/*     */   public SimpleFeature next() throws IOException {
/*  96 */     SimpleFeatureType type = getFeatureType();
/*  97 */     if (hasNext())
/*     */       try {
/* 101 */         this.live = this.next;
/* 102 */         this.next = null;
/* 103 */         this.current = SimpleFeatureBuilder.copy(this.live);
/* 105 */         return this.current;
/* 106 */       } catch (IllegalAttributeException e) {
/* 107 */         throw (IOException)(new IOException("Could not modify content")).initCause(e);
/*     */       }  
/*     */     try {
/* 114 */       this.live = null;
/* 115 */       this.next = null;
/* 116 */       this.current = SimpleFeatureBuilder.build(type, new Object[type.getAttributeCount()], "new" + this.diff.nextFID);
/* 118 */       this.diff.nextFID++;
/* 119 */       return this.current;
/* 120 */     } catch (IllegalAttributeException e) {
/* 121 */       throw new IOException("Could not create new content");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void remove() throws IOException {
/* 130 */     if (this.live != null) {
/* 132 */       this.diff.remove(this.live.getID());
/* 133 */       fireNotification(-1, ReferencedEnvelope.reference(this.live.getBounds()));
/* 134 */       this.live = null;
/* 135 */       this.current = null;
/* 136 */     } else if (this.current != null) {
/* 138 */       this.current = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void write() throws IOException {
/* 154 */     if (this.live != null) {
/* 157 */       this.diff.modify(this.live.getID(), this.current);
/* 161 */       ReferencedEnvelope bounds = new ReferencedEnvelope((CoordinateReferenceSystem)null);
/* 162 */       bounds.include(this.live.getBounds());
/* 163 */       bounds.include(this.current.getBounds());
/* 164 */       fireNotification(0, bounds);
/* 165 */       this.live = null;
/* 166 */       this.current = null;
/* 167 */     } else if (this.live == null && this.current != null) {
/* 170 */       String fid = this.current.getID();
/* 171 */       if (Boolean.TRUE.equals(this.current.getUserData().get(Hints.USE_PROVIDED_FID)) && 
/* 172 */         this.current.getUserData().containsKey(Hints.PROVIDED_FID)) {
/* 173 */         fid = (String)this.current.getUserData().get(Hints.PROVIDED_FID);
/* 174 */         Map<Object, Object> userData = this.current.getUserData();
/* 175 */         this.current = SimpleFeatureBuilder.build(this.current.getFeatureType(), this.current.getAttributes(), fid);
/* 176 */         this.current.getUserData().putAll(userData);
/*     */       } 
/* 179 */       this.diff.add(fid, this.current);
/* 180 */       fireNotification(1, ReferencedEnvelope.reference(this.current.getBounds()));
/* 181 */       this.current = null;
/*     */     } else {
/* 183 */       throw new IOException("No feature available to write");
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean hasNext() throws IOException {
/* 193 */     if (this.next != null)
/* 195 */       return true; 
/* 198 */     this.live = null;
/* 199 */     this.current = null;
/* 201 */     if (this.reader.hasNext()) {
/*     */       try {
/* 203 */         this.next = this.reader.next();
/* 204 */       } catch (NoSuchElementException e) {
/* 205 */         throw new DataSourceException("No more content", e);
/* 206 */       } catch (IllegalAttributeException e) {
/* 207 */         throw new DataSourceException("No more content", e);
/*     */       } 
/* 210 */       return true;
/*     */     } 
/* 213 */     return false;
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 227 */     if (this.reader != null) {
/* 228 */       this.reader.close();
/* 229 */       this.reader = null;
/*     */     } 
/* 232 */     this.current = null;
/* 233 */     this.live = null;
/* 234 */     this.next = null;
/* 235 */     this.diff = null;
/*     */   }
/*     */   
/*     */   protected abstract void fireNotification(int paramInt, ReferencedEnvelope paramReferencedEnvelope);
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\DiffFeatureWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
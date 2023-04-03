/*     */ package org.geotools.data.store;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.geotools.data.FeatureWriter;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ 
/*     */ final class FeatureWriterIterator implements Iterator<SimpleFeature> {
/*     */   FeatureWriter<SimpleFeatureType, SimpleFeature> writer;
/*     */   
/*     */   public FeatureWriterIterator(FeatureWriter<SimpleFeatureType, SimpleFeature> writer) {
/*  47 */     this.writer = writer;
/*     */   }
/*     */   
/*     */   public boolean hasNext() {
/*     */     try {
/*  51 */       if (this.writer == null)
/*  52 */         return false; 
/*  54 */       this.writer.write();
/*  55 */       if (this.writer.hasNext())
/*  56 */         return true; 
/*  59 */       close();
/*  60 */       return false;
/*  64 */     } catch (IOException e) {
/*  65 */       close();
/*  66 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public SimpleFeature next() {
/*  71 */     if (this.writer == null)
/*  72 */       throw new NoSuchElementException("Iterator has been closed"); 
/*     */     try {
/*  75 */       return (SimpleFeature)this.writer.next();
/*  76 */     } catch (IOException io) {
/*  77 */       NoSuchElementException problem = new NoSuchElementException("Could not obtain the next feature:" + io);
/*  78 */       problem.initCause(io);
/*  79 */       throw problem;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void remove() {
/*     */     try {
/*  84 */       this.writer.remove();
/*  85 */     } catch (IOException problem) {
/*  86 */       throw (IllegalStateException)(new IllegalStateException("Could not remove feature")).initCause(problem);
/*     */     } 
/*     */   }
/*     */   
/*     */   void close() {
/*  94 */     if (this.writer != null) {
/*     */       try {
/*  96 */         this.writer.close();
/*  97 */       } catch (IOException e) {}
/* 100 */       this.writer = null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\store\FeatureWriterIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
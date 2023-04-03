/*     */ package org.geotools.data;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.opengis.feature.Feature;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ import org.opengis.filter.Filter;
/*     */ 
/*     */ public class FilteringFeatureWriter implements FeatureWriter<SimpleFeatureType, SimpleFeature> {
/*     */   FeatureWriter<SimpleFeatureType, SimpleFeature> writer;
/*     */   
/*     */   Filter filter;
/*     */   
/*  47 */   SimpleFeature next = null;
/*     */   
/*  48 */   SimpleFeature current = null;
/*     */   
/*     */   public FilteringFeatureWriter(FeatureWriter<SimpleFeatureType, SimpleFeature> writer, Filter filter) {
/*  51 */     this.writer = writer;
/*  52 */     this.filter = filter;
/*     */   }
/*     */   
/*     */   public SimpleFeatureType getFeatureType() {
/*  56 */     return this.writer.getFeatureType();
/*     */   }
/*     */   
/*     */   public SimpleFeature next() throws IOException {
/*  60 */     if (hasNext()) {
/*  63 */       this.current = this.next;
/*  64 */       this.next = null;
/*  66 */       return this.current;
/*     */     } 
/*  70 */     throw new NoSuchElementException("FeatureWriter does not have additional content");
/*     */   }
/*     */   
/*     */   public void remove() throws IOException {
/*  75 */     if (this.writer == null)
/*  76 */       throw new IOException("FeatureWriter has been closed"); 
/*  79 */     if (this.current == null)
/*  86 */       throw new IOException("No feature available to remove"); 
/*  89 */     this.current = null;
/*  90 */     this.writer.remove();
/*     */   }
/*     */   
/*     */   public void write() throws IOException {
/*  94 */     if (this.writer == null)
/*  95 */       throw new IOException("FeatureWriter has been closed"); 
/*  98 */     if (this.current == null)
/* 105 */       throw new IOException("No feature available to write"); 
/* 108 */     this.writer.write();
/* 109 */     this.current = null;
/*     */   }
/*     */   
/*     */   public boolean hasNext() throws IOException {
/* 120 */     if (this.next != null)
/* 121 */       return true; 
/* 124 */     if (this.writer == null)
/* 125 */       return false; 
/* 128 */     if (this.current != null)
/* 131 */       this.current = null; 
/* 136 */     while (this.writer.hasNext()) {
/* 137 */       SimpleFeature peek = this.writer.next();
/* 139 */       if (this.filter.evaluate(peek)) {
/* 140 */         this.next = peek;
/* 142 */         return true;
/*     */       } 
/*     */     } 
/* 146 */     return false;
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 150 */     if (this.writer != null) {
/* 151 */       this.writer.close();
/* 152 */       this.writer = null;
/*     */     } 
/* 155 */     if (this.filter != null)
/* 156 */       this.filter = null; 
/* 159 */     this.current = null;
/* 160 */     this.next = null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\FilteringFeatureWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
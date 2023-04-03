/*     */ package org.geotools.data;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.geotools.feature.IllegalAttributeException;
/*     */ import org.opengis.feature.Feature;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ import org.opengis.filter.Filter;
/*     */ 
/*     */ public class FilteringFeatureReader<T extends FeatureType, F extends Feature> implements DelegatingFeatureReader<T, F> {
/*     */   protected final FeatureReader<T, F> featureReader;
/*     */   
/*     */   protected final Filter filter;
/*     */   
/*     */   protected F next;
/*     */   
/*     */   public FilteringFeatureReader(FeatureReader<T, F> featureReader, Filter filter) {
/*  65 */     this.featureReader = featureReader;
/*  66 */     this.filter = filter;
/*  67 */     this.next = null;
/*     */   }
/*     */   
/*     */   public FeatureReader<T, F> getDelegate() {
/*  74 */     return this.featureReader;
/*     */   }
/*     */   
/*     */   public F next() throws IOException, IllegalAttributeException, NoSuchElementException {
/*  79 */     F f = null;
/*  81 */     if (hasNext()) {
/*  83 */       f = this.next;
/*  84 */       this.next = null;
/*  86 */       return f;
/*     */     } 
/*  88 */     throw new NoSuchElementException("No such Feature exsists");
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/*  93 */     this.featureReader.close();
/*     */   }
/*     */   
/*     */   public T getFeatureType() {
/*  97 */     return this.featureReader.getFeatureType();
/*     */   }
/*     */   
/*     */   public boolean hasNext() throws IOException {
/* 121 */     if (this.next != null)
/* 122 */       return true; 
/*     */     try {
/* 127 */       while (this.featureReader.hasNext()) {
/* 128 */         F peek = this.featureReader.next();
/* 130 */         if (this.filter.evaluate(peek)) {
/* 131 */           this.next = peek;
/* 132 */           return true;
/*     */         } 
/*     */       } 
/* 135 */     } catch (IllegalAttributeException e) {
/* 136 */       throw new DataSourceException("Could not peek ahead", e);
/*     */     } 
/* 138 */     return (this.next != null);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\FilteringFeatureReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
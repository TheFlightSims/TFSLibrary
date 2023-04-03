/*     */ package org.geotools.feature;
/*     */ 
/*     */ import java.io.Closeable;
/*     */ import java.io.IOException;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.geotools.data.FeatureReader;
/*     */ import org.opengis.feature.Feature;
/*     */ import org.opengis.feature.IllegalAttributeException;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ 
/*     */ public class FeatureReaderIterator<F extends Feature> implements Iterator<F>, Closeable {
/*     */   FeatureReader<? extends FeatureType, F> reader;
/*     */   
/*     */   public FeatureReaderIterator(FeatureReader<? extends FeatureType, F> reader) {
/*  53 */     this.reader = reader;
/*     */   }
/*     */   
/*     */   public boolean hasNext() {
/*     */     try {
/*  58 */       if (this.reader == null)
/*  59 */         return false; 
/*  60 */       if (this.reader.hasNext())
/*  61 */         return true; 
/*  65 */       close();
/*  66 */       return false;
/*  68 */     } catch (Exception e) {
/*  69 */       close();
/*  70 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public F next() {
/*  75 */     if (this.reader == null)
/*  76 */       throw new NoSuchElementException("Iterator has been closed"); 
/*     */     try {
/*  79 */       return (F)this.reader.next();
/*  80 */     } catch (IOException io) {
/*  81 */       close();
/*  82 */       NoSuchElementException problem = new NoSuchElementException("Could not obtain the next feature:" + io);
/*  84 */       problem.initCause(io);
/*  85 */       throw problem;
/*  86 */     } catch (IllegalAttributeException create) {
/*  87 */       close();
/*  88 */       NoSuchElementException problem = new NoSuchElementException("Could not create the next feature:" + create);
/*  90 */       problem.initCause((Throwable)create);
/*  91 */       throw problem;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void remove() {
/*  97 */     throw new UnsupportedOperationException("Modification of contents is not supported");
/*     */   }
/*     */   
/*     */   public void close() {
/* 104 */     if (this.reader != null) {
/*     */       try {
/* 106 */         this.reader.close();
/* 107 */       } catch (Exception e) {}
/* 110 */       this.reader = null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\FeatureReaderIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
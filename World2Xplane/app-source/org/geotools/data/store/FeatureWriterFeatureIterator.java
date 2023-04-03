/*    */ package org.geotools.data.store;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.NoSuchElementException;
/*    */ import org.geotools.data.FeatureWriter;
/*    */ import org.geotools.data.simple.SimpleFeatureIterator;
/*    */ import org.opengis.feature.Feature;
/*    */ import org.opengis.feature.simple.SimpleFeature;
/*    */ import org.opengis.feature.simple.SimpleFeatureType;
/*    */ 
/*    */ final class FeatureWriterFeatureIterator implements SimpleFeatureIterator {
/*    */   FeatureWriter<SimpleFeatureType, SimpleFeature> writer;
/*    */   
/*    */   public FeatureWriterFeatureIterator(FeatureWriter<SimpleFeatureType, SimpleFeature> writer) {
/* 48 */     this.writer = writer;
/*    */   }
/*    */   
/*    */   public boolean hasNext() {
/*    */     try {
/* 52 */       if (this.writer == null)
/* 53 */         return false; 
/* 55 */       this.writer.write();
/* 56 */       if (this.writer.hasNext())
/* 57 */         return true; 
/* 60 */       close();
/* 61 */       return false;
/* 65 */     } catch (IOException e) {
/* 66 */       close();
/* 67 */       return false;
/*    */     } 
/*    */   }
/*    */   
/*    */   public SimpleFeature next() {
/* 72 */     if (this.writer == null)
/* 73 */       throw new NoSuchElementException("Iterator has been closed"); 
/*    */     try {
/* 76 */       return (SimpleFeature)this.writer.next();
/* 77 */     } catch (IOException io) {
/* 78 */       NoSuchElementException problem = new NoSuchElementException("Could not obtain the next feature:" + io);
/* 79 */       problem.initCause(io);
/* 80 */       throw problem;
/*    */     } 
/*    */   }
/*    */   
/*    */   public void remove() {
/*    */     try {
/* 85 */       this.writer.remove();
/* 86 */     } catch (IOException problem) {
/* 87 */       throw (IllegalStateException)(new IllegalStateException("Could not remove feature")).initCause(problem);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void close() {
/* 91 */     if (this.writer != null) {
/*    */       try {
/* 93 */         this.writer.close();
/* 94 */       } catch (IOException e) {}
/* 97 */       this.writer = null;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\store\FeatureWriterFeatureIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
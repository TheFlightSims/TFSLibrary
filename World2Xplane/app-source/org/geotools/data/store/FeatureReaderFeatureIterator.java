/*    */ package org.geotools.data.store;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.NoSuchElementException;
/*    */ import org.geotools.data.FeatureReader;
/*    */ import org.geotools.data.simple.SimpleFeatureIterator;
/*    */ import org.geotools.feature.IllegalAttributeException;
/*    */ import org.opengis.feature.Feature;
/*    */ import org.opengis.feature.simple.SimpleFeature;
/*    */ import org.opengis.feature.simple.SimpleFeatureType;
/*    */ 
/*    */ final class FeatureReaderFeatureIterator implements SimpleFeatureIterator {
/*    */   FeatureReader<SimpleFeatureType, SimpleFeature> reader;
/*    */   
/*    */   public FeatureReaderFeatureIterator(FeatureReader<SimpleFeatureType, SimpleFeature> reader) {
/* 43 */     this.reader = reader;
/*    */   }
/*    */   
/*    */   public boolean hasNext() {
/*    */     try {
/* 47 */       if (this.reader == null)
/* 47 */         return false; 
/* 48 */       if (this.reader.hasNext())
/* 49 */         return true; 
/* 54 */       close();
/* 55 */       return false;
/* 57 */     } catch (IOException e) {
/* 58 */       close();
/* 59 */       return false;
/*    */     } 
/*    */   }
/*    */   
/*    */   public SimpleFeature next() {
/* 64 */     if (this.reader == null)
/* 65 */       throw new NoSuchElementException("Iterator has been closed"); 
/*    */     try {
/* 68 */       return (SimpleFeature)this.reader.next();
/* 69 */     } catch (IOException io) {
/* 70 */       close();
/* 71 */       NoSuchElementException problem = new NoSuchElementException("Could not obtain the next feature:" + io);
/* 72 */       problem.initCause(io);
/* 73 */       throw problem;
/* 74 */     } catch (IllegalAttributeException create) {
/* 75 */       close();
/* 76 */       NoSuchElementException problem = new NoSuchElementException("Could not create the next feature:" + create);
/* 77 */       problem.initCause((Throwable)create);
/* 78 */       throw problem;
/*    */     } 
/*    */   }
/*    */   
/*    */   public void remove() {
/* 83 */     throw new UnsupportedOperationException("Modification of contents is not supported");
/*    */   }
/*    */   
/*    */   public void close() {
/* 90 */     if (this.reader != null) {
/*    */       try {
/* 92 */         this.reader.close();
/* 93 */       } catch (IOException e) {}
/* 96 */       this.reader = null;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\store\FeatureReaderFeatureIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
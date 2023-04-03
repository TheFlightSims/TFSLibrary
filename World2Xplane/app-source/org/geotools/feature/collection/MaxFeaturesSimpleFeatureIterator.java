/*    */ package org.geotools.feature.collection;
/*    */ 
/*    */ import java.util.NoSuchElementException;
/*    */ import org.geotools.data.simple.SimpleFeatureIterator;
/*    */ import org.opengis.feature.Feature;
/*    */ import org.opengis.feature.simple.SimpleFeature;
/*    */ 
/*    */ public class MaxFeaturesSimpleFeatureIterator implements SimpleFeatureIterator {
/*    */   SimpleFeatureIterator delegate;
/*    */   
/*    */   long start;
/*    */   
/*    */   long end;
/*    */   
/*    */   long counter;
/*    */   
/*    */   public MaxFeaturesSimpleFeatureIterator(SimpleFeatureIterator iterator, long max) {
/* 41 */     this(iterator, 0L, max);
/*    */   }
/*    */   
/*    */   public MaxFeaturesSimpleFeatureIterator(SimpleFeatureIterator delegate, long start, long max) {
/* 45 */     this.delegate = delegate;
/* 46 */     this.start = start;
/* 47 */     this.end = start + max;
/* 48 */     this.counter = 0L;
/*    */   }
/*    */   
/*    */   public SimpleFeatureIterator getDelegate() {
/* 52 */     return this.delegate;
/*    */   }
/*    */   
/*    */   public boolean hasNext() {
/* 56 */     if (this.counter < this.start)
/* 58 */       skip(); 
/* 60 */     return (this.delegate.hasNext() && this.counter < this.end);
/*    */   }
/*    */   
/*    */   public SimpleFeature next() {
/* 64 */     if (this.counter < this.start)
/* 66 */       skip(); 
/* 68 */     if (this.counter <= this.end) {
/* 69 */       this.counter++;
/* 70 */       SimpleFeature next = (SimpleFeature)this.delegate.next();
/* 71 */       return next;
/*    */     } 
/* 73 */     return null;
/*    */   }
/*    */   
/*    */   private void skip() {
/* 77 */     if (this.counter < this.start)
/* 78 */       while (this.delegate.hasNext() && this.counter < this.start) {
/* 79 */         this.counter++;
/* 81 */         SimpleFeature skip = (SimpleFeature)this.delegate.next();
/*    */       }  
/*    */   }
/*    */   
/*    */   public void close() {
/* 88 */     this.delegate.close();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\collection\MaxFeaturesSimpleFeatureIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
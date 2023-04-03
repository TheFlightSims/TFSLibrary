/*    */ package org.geotools.data.store;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import org.geotools.feature.FeatureIterator;
/*    */ import org.geotools.feature.collection.DelegateFeatureIterator;
/*    */ import org.opengis.feature.Feature;
/*    */ 
/*    */ public class MaxFeaturesIterator<F extends Feature> implements FeatureIterator<F> {
/*    */   FeatureIterator<F> delegate;
/*    */   
/*    */   long start;
/*    */   
/*    */   long end;
/*    */   
/*    */   long counter;
/*    */   
/*    */   public MaxFeaturesIterator(Iterator<F> iterator, long max) {
/* 43 */     this((FeatureIterator<F>)new DelegateFeatureIterator(iterator), 0L, max);
/*    */   }
/*    */   
/*    */   public MaxFeaturesIterator(Iterator<F> iterator, long start, long max) {
/* 47 */     this((FeatureIterator<F>)new DelegateFeatureIterator(iterator), start, max);
/*    */   }
/*    */   
/*    */   public MaxFeaturesIterator(FeatureIterator<F> delegate, long max) {
/* 51 */     this(delegate, 0L, max);
/*    */   }
/*    */   
/*    */   public MaxFeaturesIterator(FeatureIterator<F> delegate, long start, long max) {
/* 55 */     this.delegate = delegate;
/* 56 */     this.start = start;
/* 57 */     this.end = start + max;
/* 58 */     this.counter = 0L;
/*    */   }
/*    */   
/*    */   public FeatureIterator<F> getDelegate() {
/* 62 */     return this.delegate;
/*    */   }
/*    */   
/*    */   public boolean hasNext() {
/* 66 */     if (this.counter < this.start)
/* 68 */       skip(); 
/* 70 */     return (this.delegate.hasNext() && this.counter < this.end);
/*    */   }
/*    */   
/*    */   public F next() {
/* 74 */     if (this.counter < this.start)
/* 76 */       skip(); 
/* 78 */     if (this.counter <= this.end) {
/* 79 */       this.counter++;
/* 80 */       return (F)this.delegate.next();
/*    */     } 
/* 83 */     return null;
/*    */   }
/*    */   
/*    */   private void skip() {
/* 87 */     if (this.counter < this.start)
/* 88 */       while (this.delegate.hasNext() && this.counter < this.start) {
/* 89 */         this.counter++;
/* 90 */         Feature feature = this.delegate.next();
/*    */       }  
/*    */   }
/*    */   
/*    */   public void close() {
/* 97 */     this.delegate.close();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\store\MaxFeaturesIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
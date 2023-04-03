/*    */ package org.geotools.data.store;
/*    */ 
/*    */ import java.util.NoSuchElementException;
/*    */ import org.geotools.feature.FeatureIterator;
/*    */ import org.opengis.feature.Feature;
/*    */ import org.opengis.filter.Filter;
/*    */ 
/*    */ public class FilteringFeatureIterator<F extends Feature> implements FeatureIterator<F> {
/*    */   protected FeatureIterator<F> delegate;
/*    */   
/*    */   protected Filter filter;
/*    */   
/*    */   protected F next;
/*    */   
/*    */   public FilteringFeatureIterator(FeatureIterator<F> delegate, Filter filter) {
/* 49 */     this.delegate = delegate;
/* 50 */     this.filter = filter;
/*    */   }
/*    */   
/*    */   public boolean hasNext() {
/* 54 */     if (this.next != null)
/* 55 */       return true; 
/* 58 */     while (this.delegate.hasNext()) {
/* 59 */       Feature feature = this.delegate.next();
/* 60 */       if (this.filter.evaluate(feature)) {
/* 61 */         this.next = (F)feature;
/*    */         break;
/*    */       } 
/*    */     } 
/* 66 */     return (this.next != null);
/*    */   }
/*    */   
/*    */   public F next() throws NoSuchElementException {
/* 70 */     F f = this.next;
/* 71 */     this.next = null;
/* 72 */     return f;
/*    */   }
/*    */   
/*    */   public void close() {
/* 76 */     this.delegate.close();
/* 77 */     this.delegate = null;
/* 78 */     this.next = null;
/* 79 */     this.filter = null;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\store\FilteringFeatureIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package org.geotools.data.store;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import org.geotools.feature.FeatureIterator;
/*    */ import org.opengis.feature.Feature;
/*    */ 
/*    */ public class FeatureIteratorIterator<F extends Feature> implements Iterator<F> {
/*    */   FeatureIterator<F> delegate;
/*    */   
/*    */   public FeatureIteratorIterator(FeatureIterator<F> delegate) {
/* 48 */     this.delegate = delegate;
/*    */   }
/*    */   
/*    */   public boolean hasNext() {
/* 55 */     return this.delegate.hasNext();
/*    */   }
/*    */   
/*    */   public F next() {
/* 62 */     return (F)this.delegate.next();
/*    */   }
/*    */   
/*    */   public void remove() {
/* 69 */     throw new UnsupportedOperationException("remove is not supported");
/*    */   }
/*    */   
/*    */   public FeatureIterator<F> getDelegate() {
/* 76 */     return this.delegate;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\store\FeatureIteratorIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
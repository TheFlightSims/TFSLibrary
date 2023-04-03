/*    */ package org.geotools.feature.collection;
/*    */ 
/*    */ import java.util.NoSuchElementException;
/*    */ import org.geotools.feature.FeatureIterator;
/*    */ import org.opengis.feature.Feature;
/*    */ 
/*    */ public class DecoratingFeatureIterator<F extends Feature> implements FeatureIterator<F> {
/*    */   FeatureIterator<F> delegate;
/*    */   
/*    */   public DecoratingFeatureIterator(FeatureIterator<F> iterator) {
/* 44 */     this.delegate = iterator;
/*    */   }
/*    */   
/*    */   public boolean hasNext() {
/* 48 */     return (this.delegate != null && this.delegate.hasNext());
/*    */   }
/*    */   
/*    */   public F next() throws NoSuchElementException {
/* 52 */     if (this.delegate == null)
/* 52 */       throw new NoSuchElementException(); 
/* 53 */     return (F)this.delegate.next();
/*    */   }
/*    */   
/*    */   public void close() {
/* 57 */     if (this.delegate != null)
/* 58 */       this.delegate.close(); 
/* 60 */     this.delegate = null;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\collection\DecoratingFeatureIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
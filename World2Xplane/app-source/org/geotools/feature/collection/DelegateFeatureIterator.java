/*    */ package org.geotools.feature.collection;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.NoSuchElementException;
/*    */ import org.geotools.data.DataUtilities;
/*    */ import org.geotools.feature.FeatureCollection;
/*    */ import org.geotools.feature.FeatureIterator;
/*    */ import org.opengis.feature.Feature;
/*    */ import org.opengis.feature.type.FeatureType;
/*    */ 
/*    */ public class DelegateFeatureIterator<F extends Feature> implements FeatureIterator<F> {
/*    */   Iterator<F> delegate;
/*    */   
/*    */   public DelegateFeatureIterator(Iterator<F> iterator) {
/* 54 */     this.delegate = iterator;
/*    */   }
/*    */   
/*    */   public DelegateFeatureIterator(FeatureCollection<? extends FeatureType, F> collection, Iterator<F> iterator) {
/* 64 */     this.delegate = iterator;
/*    */   }
/*    */   
/*    */   public boolean hasNext() {
/* 67 */     return (this.delegate != null && this.delegate.hasNext());
/*    */   }
/*    */   
/*    */   public F next() throws NoSuchElementException {
/* 70 */     if (this.delegate == null)
/* 70 */       throw new NoSuchElementException(); 
/* 71 */     return this.delegate.next();
/*    */   }
/*    */   
/*    */   public void close() {
/* 74 */     DataUtilities.close(this.delegate);
/* 75 */     this.delegate = null;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\collection\DelegateFeatureIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
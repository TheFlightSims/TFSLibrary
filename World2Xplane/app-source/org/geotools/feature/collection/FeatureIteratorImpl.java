/*    */ package org.geotools.feature.collection;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import java.util.Iterator;
/*    */ import java.util.NoSuchElementException;
/*    */ import org.geotools.data.DataUtilities;
/*    */ import org.geotools.feature.FeatureIterator;
/*    */ import org.opengis.feature.Feature;
/*    */ 
/*    */ public class FeatureIteratorImpl<F extends Feature> implements FeatureIterator<F> {
/*    */   Iterator<F> iterator;
/*    */   
/*    */   Collection<F> collection;
/*    */   
/*    */   public FeatureIteratorImpl(Collection<F> collection) {
/* 54 */     this.collection = collection;
/* 55 */     this.iterator = collection.iterator();
/*    */   }
/*    */   
/*    */   public boolean hasNext() {
/* 66 */     return this.iterator.hasNext();
/*    */   }
/*    */   
/*    */   public F next() throws NoSuchElementException {
/* 77 */     return this.iterator.next();
/*    */   }
/*    */   
/*    */   public void close() {
/* 83 */     if (this.iterator != null) {
/* 84 */       DataUtilities.close(this.iterator);
/* 85 */       this.iterator = null;
/* 86 */       this.collection = null;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\collection\FeatureIteratorImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
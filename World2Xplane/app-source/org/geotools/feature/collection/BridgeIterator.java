/*    */ package org.geotools.feature.collection;
/*    */ 
/*    */ import java.io.Closeable;
/*    */ import java.io.IOException;
/*    */ import java.util.Iterator;
/*    */ import org.geotools.feature.FeatureIterator;
/*    */ import org.opengis.feature.Feature;
/*    */ 
/*    */ public class BridgeIterator<F extends Feature> implements Iterator<F>, Closeable {
/*    */   FeatureIterator<F> delegate;
/*    */   
/*    */   public BridgeIterator(FeatureIterator<F> features) {
/* 21 */     this.delegate = features;
/*    */   }
/*    */   
/*    */   public boolean hasNext() {
/* 26 */     return this.delegate.hasNext();
/*    */   }
/*    */   
/*    */   public F next() {
/* 31 */     return (F)this.delegate.next();
/*    */   }
/*    */   
/*    */   public void remove() {}
/*    */   
/*    */   public void close() throws IOException {
/* 40 */     this.delegate.close();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\collection\BridgeIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
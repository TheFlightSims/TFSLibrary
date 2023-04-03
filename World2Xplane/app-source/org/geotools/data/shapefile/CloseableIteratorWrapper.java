/*    */ package org.geotools.data.shapefile;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.Iterator;
/*    */ import org.geotools.data.shapefile.index.CloseableIterator;
/*    */ 
/*    */ class CloseableIteratorWrapper<E> implements CloseableIterator<E> {
/*    */   Iterator<E> delegate;
/*    */   
/*    */   public CloseableIteratorWrapper(Iterator<E> delegate) {
/* 31 */     this.delegate = delegate;
/*    */   }
/*    */   
/*    */   public boolean hasNext() {
/* 35 */     return this.delegate.hasNext();
/*    */   }
/*    */   
/*    */   public E next() {
/* 39 */     return this.delegate.next();
/*    */   }
/*    */   
/*    */   public void remove() {
/* 43 */     this.delegate.remove();
/*    */   }
/*    */   
/*    */   public void close() throws IOException {}
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\shapefile\CloseableIteratorWrapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
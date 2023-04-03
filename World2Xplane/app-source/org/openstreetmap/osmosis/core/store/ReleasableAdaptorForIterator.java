/*    */ package org.openstreetmap.osmosis.core.store;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import org.openstreetmap.osmosis.core.lifecycle.ReleasableIterator;
/*    */ 
/*    */ public class ReleasableAdaptorForIterator<T> implements ReleasableIterator<T> {
/*    */   private Iterator<T> source;
/*    */   
/*    */   public ReleasableAdaptorForIterator(Iterator<T> source) {
/* 29 */     this.source = source;
/*    */   }
/*    */   
/*    */   public boolean hasNext() {
/* 38 */     return this.source.hasNext();
/*    */   }
/*    */   
/*    */   public T next() {
/* 47 */     return this.source.next();
/*    */   }
/*    */   
/*    */   public void remove() {
/* 56 */     this.source.remove();
/*    */   }
/*    */   
/*    */   public void release() {}
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\store\ReleasableAdaptorForIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
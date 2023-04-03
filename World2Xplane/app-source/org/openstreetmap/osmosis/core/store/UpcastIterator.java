/*    */ package org.openstreetmap.osmosis.core.store;
/*    */ 
/*    */ import org.openstreetmap.osmosis.core.lifecycle.ReleasableIterator;
/*    */ 
/*    */ public class UpcastIterator<X, Y extends X> implements ReleasableIterator<X> {
/*    */   private ReleasableIterator<Y> source;
/*    */   
/*    */   public UpcastIterator(ReleasableIterator<Y> source) {
/* 30 */     this.source = source;
/*    */   }
/*    */   
/*    */   public boolean hasNext() {
/* 39 */     return this.source.hasNext();
/*    */   }
/*    */   
/*    */   public X next() {
/* 48 */     return (X)this.source.next();
/*    */   }
/*    */   
/*    */   public void remove() {
/* 57 */     this.source.remove();
/*    */   }
/*    */   
/*    */   public void release() {
/* 66 */     this.source.release();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\store\UpcastIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
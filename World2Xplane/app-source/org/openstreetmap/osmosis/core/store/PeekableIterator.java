/*    */ package org.openstreetmap.osmosis.core.store;
/*    */ 
/*    */ import org.openstreetmap.osmosis.core.lifecycle.ReleasableIterator;
/*    */ 
/*    */ public class PeekableIterator<T> implements ReleasableIterator<T> {
/*    */   private ReleasableIterator<T> sourceIterator;
/*    */   
/*    */   private T nextValue;
/*    */   
/*    */   private boolean nextValueAvailable;
/*    */   
/*    */   public PeekableIterator(ReleasableIterator<T> sourceIterator) {
/* 33 */     this.sourceIterator = sourceIterator;
/* 35 */     this.nextValue = null;
/* 36 */     this.nextValueAvailable = false;
/*    */   }
/*    */   
/*    */   public boolean hasNext() {
/* 44 */     return (this.nextValueAvailable || this.sourceIterator.hasNext());
/*    */   }
/*    */   
/*    */   public T peekNext() {
/* 54 */     if (!this.nextValueAvailable) {
/* 55 */       this.nextValue = (T)this.sourceIterator.next();
/* 56 */       this.nextValueAvailable = true;
/*    */     } 
/* 59 */     return this.nextValue;
/*    */   }
/*    */   
/*    */   public T next() {
/* 69 */     T result = peekNext();
/* 71 */     this.nextValue = null;
/* 72 */     this.nextValueAvailable = false;
/* 74 */     return result;
/*    */   }
/*    */   
/*    */   public void release() {
/* 82 */     this.sourceIterator.release();
/*    */   }
/*    */   
/*    */   public void remove() {
/* 90 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\store\PeekableIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
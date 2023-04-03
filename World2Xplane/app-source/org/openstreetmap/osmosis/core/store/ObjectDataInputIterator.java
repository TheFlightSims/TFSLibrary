/*    */ package org.openstreetmap.osmosis.core.store;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.NoSuchElementException;
/*    */ 
/*    */ public class ObjectDataInputIterator<T> implements Iterator<T> {
/*    */   private ObjectReader objectReader;
/*    */   
/*    */   private T nextElement;
/*    */   
/*    */   public ObjectDataInputIterator(ObjectReader objectReader) {
/* 28 */     this.objectReader = objectReader;
/*    */   }
/*    */   
/*    */   public boolean hasNext() {
/* 37 */     if (this.nextElement != null)
/* 38 */       return true; 
/*    */     try {
/* 42 */       this.nextElement = (T)this.objectReader.readObject();
/* 44 */     } catch (EndOfStoreException e) {
/* 45 */       return false;
/*    */     } 
/* 48 */     return true;
/*    */   }
/*    */   
/*    */   public T next() {
/* 56 */     if (hasNext()) {
/* 59 */       T result = this.nextElement;
/* 60 */       this.nextElement = null;
/* 62 */       return result;
/*    */     } 
/* 65 */     throw new NoSuchElementException();
/*    */   }
/*    */   
/*    */   public void remove() {
/* 74 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\store\ObjectDataInputIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
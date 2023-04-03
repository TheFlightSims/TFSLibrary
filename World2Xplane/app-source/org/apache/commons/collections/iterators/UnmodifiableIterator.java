/*    */ package org.apache.commons.collections.iterators;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import org.apache.commons.collections.Unmodifiable;
/*    */ 
/*    */ public final class UnmodifiableIterator implements Iterator, Unmodifiable {
/*    */   private Iterator iterator;
/*    */   
/*    */   public static Iterator decorate(Iterator iterator) {
/* 46 */     if (iterator == null)
/* 47 */       throw new IllegalArgumentException("Iterator must not be null"); 
/* 49 */     if (iterator instanceof Unmodifiable)
/* 50 */       return iterator; 
/* 52 */     return new UnmodifiableIterator(iterator);
/*    */   }
/*    */   
/*    */   private UnmodifiableIterator(Iterator iterator) {
/* 63 */     this.iterator = iterator;
/*    */   }
/*    */   
/*    */   public boolean hasNext() {
/* 68 */     return this.iterator.hasNext();
/*    */   }
/*    */   
/*    */   public Object next() {
/* 72 */     return this.iterator.next();
/*    */   }
/*    */   
/*    */   public void remove() {
/* 76 */     throw new UnsupportedOperationException("remove() is not supported");
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\iterators\UnmodifiableIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */
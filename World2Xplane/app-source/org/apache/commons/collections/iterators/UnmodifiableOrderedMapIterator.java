/*    */ package org.apache.commons.collections.iterators;
/*    */ 
/*    */ import org.apache.commons.collections.OrderedMapIterator;
/*    */ import org.apache.commons.collections.Unmodifiable;
/*    */ 
/*    */ public final class UnmodifiableOrderedMapIterator implements OrderedMapIterator, Unmodifiable {
/*    */   private OrderedMapIterator iterator;
/*    */   
/*    */   public static OrderedMapIterator decorate(OrderedMapIterator iterator) {
/* 43 */     if (iterator == null)
/* 44 */       throw new IllegalArgumentException("OrderedMapIterator must not be null"); 
/* 46 */     if (iterator instanceof Unmodifiable)
/* 47 */       return iterator; 
/* 49 */     return new UnmodifiableOrderedMapIterator(iterator);
/*    */   }
/*    */   
/*    */   private UnmodifiableOrderedMapIterator(OrderedMapIterator iterator) {
/* 60 */     this.iterator = iterator;
/*    */   }
/*    */   
/*    */   public boolean hasNext() {
/* 65 */     return this.iterator.hasNext();
/*    */   }
/*    */   
/*    */   public Object next() {
/* 69 */     return this.iterator.next();
/*    */   }
/*    */   
/*    */   public boolean hasPrevious() {
/* 73 */     return this.iterator.hasPrevious();
/*    */   }
/*    */   
/*    */   public Object previous() {
/* 77 */     return this.iterator.previous();
/*    */   }
/*    */   
/*    */   public Object getKey() {
/* 81 */     return this.iterator.getKey();
/*    */   }
/*    */   
/*    */   public Object getValue() {
/* 85 */     return this.iterator.getValue();
/*    */   }
/*    */   
/*    */   public Object setValue(Object value) {
/* 89 */     throw new UnsupportedOperationException("setValue() is not supported");
/*    */   }
/*    */   
/*    */   public void remove() {
/* 93 */     throw new UnsupportedOperationException("remove() is not supported");
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\iterators\UnmodifiableOrderedMapIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */
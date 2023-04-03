/*    */ package org.apache.commons.collections.iterators;
/*    */ 
/*    */ import org.apache.commons.collections.OrderedMapIterator;
/*    */ 
/*    */ public class AbstractOrderedMapIteratorDecorator implements OrderedMapIterator {
/*    */   protected final OrderedMapIterator iterator;
/*    */   
/*    */   public AbstractOrderedMapIteratorDecorator(OrderedMapIterator iterator) {
/* 45 */     if (iterator == null)
/* 46 */       throw new IllegalArgumentException("OrderedMapIterator must not be null"); 
/* 48 */     this.iterator = iterator;
/*    */   }
/*    */   
/*    */   protected OrderedMapIterator getOrderedMapIterator() {
/* 57 */     return this.iterator;
/*    */   }
/*    */   
/*    */   public boolean hasNext() {
/* 62 */     return this.iterator.hasNext();
/*    */   }
/*    */   
/*    */   public Object next() {
/* 66 */     return this.iterator.next();
/*    */   }
/*    */   
/*    */   public boolean hasPrevious() {
/* 70 */     return this.iterator.hasPrevious();
/*    */   }
/*    */   
/*    */   public Object previous() {
/* 74 */     return this.iterator.previous();
/*    */   }
/*    */   
/*    */   public void remove() {
/* 78 */     this.iterator.remove();
/*    */   }
/*    */   
/*    */   public Object getKey() {
/* 82 */     return this.iterator.getKey();
/*    */   }
/*    */   
/*    */   public Object getValue() {
/* 86 */     return this.iterator.getValue();
/*    */   }
/*    */   
/*    */   public Object setValue(Object obj) {
/* 90 */     return this.iterator.setValue(obj);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\iterators\AbstractOrderedMapIteratorDecorator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */
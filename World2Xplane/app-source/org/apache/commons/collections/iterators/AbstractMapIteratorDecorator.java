/*    */ package org.apache.commons.collections.iterators;
/*    */ 
/*    */ import org.apache.commons.collections.MapIterator;
/*    */ 
/*    */ public class AbstractMapIteratorDecorator implements MapIterator {
/*    */   protected final MapIterator iterator;
/*    */   
/*    */   public AbstractMapIteratorDecorator(MapIterator iterator) {
/* 45 */     if (iterator == null)
/* 46 */       throw new IllegalArgumentException("MapIterator must not be null"); 
/* 48 */     this.iterator = iterator;
/*    */   }
/*    */   
/*    */   protected MapIterator getMapIterator() {
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
/*    */   public void remove() {
/* 70 */     this.iterator.remove();
/*    */   }
/*    */   
/*    */   public Object getKey() {
/* 74 */     return this.iterator.getKey();
/*    */   }
/*    */   
/*    */   public Object getValue() {
/* 78 */     return this.iterator.getValue();
/*    */   }
/*    */   
/*    */   public Object setValue(Object obj) {
/* 82 */     return this.iterator.setValue(obj);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\iterators\AbstractMapIteratorDecorator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */
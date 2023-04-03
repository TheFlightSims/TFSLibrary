/*    */ package org.apache.commons.collections.iterators;
/*    */ 
/*    */ import org.apache.commons.collections.MapIterator;
/*    */ import org.apache.commons.collections.Unmodifiable;
/*    */ 
/*    */ public final class UnmodifiableMapIterator implements MapIterator, Unmodifiable {
/*    */   private MapIterator iterator;
/*    */   
/*    */   public static MapIterator decorate(MapIterator iterator) {
/* 43 */     if (iterator == null)
/* 44 */       throw new IllegalArgumentException("MapIterator must not be null"); 
/* 46 */     if (iterator instanceof Unmodifiable)
/* 47 */       return iterator; 
/* 49 */     return new UnmodifiableMapIterator(iterator);
/*    */   }
/*    */   
/*    */   private UnmodifiableMapIterator(MapIterator iterator) {
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
/*    */   public Object getKey() {
/* 73 */     return this.iterator.getKey();
/*    */   }
/*    */   
/*    */   public Object getValue() {
/* 77 */     return this.iterator.getValue();
/*    */   }
/*    */   
/*    */   public Object setValue(Object value) {
/* 81 */     throw new UnsupportedOperationException("setValue() is not supported");
/*    */   }
/*    */   
/*    */   public void remove() {
/* 85 */     throw new UnsupportedOperationException("remove() is not supported");
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\iterators\UnmodifiableMapIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */
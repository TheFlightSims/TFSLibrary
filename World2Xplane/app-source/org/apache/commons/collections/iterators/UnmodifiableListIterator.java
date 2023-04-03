/*    */ package org.apache.commons.collections.iterators;
/*    */ 
/*    */ import java.util.ListIterator;
/*    */ import org.apache.commons.collections.Unmodifiable;
/*    */ 
/*    */ public final class UnmodifiableListIterator implements ListIterator, Unmodifiable {
/*    */   private ListIterator iterator;
/*    */   
/*    */   public static ListIterator decorate(ListIterator iterator) {
/* 44 */     if (iterator == null)
/* 45 */       throw new IllegalArgumentException("ListIterator must not be null"); 
/* 47 */     if (iterator instanceof Unmodifiable)
/* 48 */       return iterator; 
/* 50 */     return new UnmodifiableListIterator(iterator);
/*    */   }
/*    */   
/*    */   private UnmodifiableListIterator(ListIterator iterator) {
/* 61 */     this.iterator = iterator;
/*    */   }
/*    */   
/*    */   public boolean hasNext() {
/* 66 */     return this.iterator.hasNext();
/*    */   }
/*    */   
/*    */   public Object next() {
/* 70 */     return this.iterator.next();
/*    */   }
/*    */   
/*    */   public int nextIndex() {
/* 74 */     return this.iterator.nextIndex();
/*    */   }
/*    */   
/*    */   public boolean hasPrevious() {
/* 78 */     return this.iterator.hasPrevious();
/*    */   }
/*    */   
/*    */   public Object previous() {
/* 82 */     return this.iterator.previous();
/*    */   }
/*    */   
/*    */   public int previousIndex() {
/* 86 */     return this.iterator.previousIndex();
/*    */   }
/*    */   
/*    */   public void remove() {
/* 90 */     throw new UnsupportedOperationException("remove() is not supported");
/*    */   }
/*    */   
/*    */   public void set(Object obj) {
/* 94 */     throw new UnsupportedOperationException("set() is not supported");
/*    */   }
/*    */   
/*    */   public void add(Object obj) {
/* 98 */     throw new UnsupportedOperationException("add() is not supported");
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\iterators\UnmodifiableListIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package org.apache.commons.collections.iterators;
/*    */ 
/*    */ import java.util.ListIterator;
/*    */ 
/*    */ public class AbstractListIteratorDecorator implements ListIterator {
/*    */   protected final ListIterator iterator;
/*    */   
/*    */   public AbstractListIteratorDecorator(ListIterator iterator) {
/* 46 */     if (iterator == null)
/* 47 */       throw new IllegalArgumentException("ListIterator must not be null"); 
/* 49 */     this.iterator = iterator;
/*    */   }
/*    */   
/*    */   protected ListIterator getListIterator() {
/* 58 */     return this.iterator;
/*    */   }
/*    */   
/*    */   public boolean hasNext() {
/* 63 */     return this.iterator.hasNext();
/*    */   }
/*    */   
/*    */   public Object next() {
/* 67 */     return this.iterator.next();
/*    */   }
/*    */   
/*    */   public int nextIndex() {
/* 71 */     return this.iterator.nextIndex();
/*    */   }
/*    */   
/*    */   public boolean hasPrevious() {
/* 75 */     return this.iterator.hasPrevious();
/*    */   }
/*    */   
/*    */   public Object previous() {
/* 79 */     return this.iterator.previous();
/*    */   }
/*    */   
/*    */   public int previousIndex() {
/* 83 */     return this.iterator.previousIndex();
/*    */   }
/*    */   
/*    */   public void remove() {
/* 87 */     this.iterator.remove();
/*    */   }
/*    */   
/*    */   public void set(Object obj) {
/* 91 */     this.iterator.set(obj);
/*    */   }
/*    */   
/*    */   public void add(Object obj) {
/* 95 */     this.iterator.add(obj);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\iterators\AbstractListIteratorDecorator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */
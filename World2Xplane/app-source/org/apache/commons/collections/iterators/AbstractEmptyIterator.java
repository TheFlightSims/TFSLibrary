/*    */ package org.apache.commons.collections.iterators;
/*    */ 
/*    */ import java.util.NoSuchElementException;
/*    */ 
/*    */ abstract class AbstractEmptyIterator {
/*    */   public boolean hasNext() {
/* 39 */     return false;
/*    */   }
/*    */   
/*    */   public Object next() {
/* 43 */     throw new NoSuchElementException("Iterator contains no elements");
/*    */   }
/*    */   
/*    */   public boolean hasPrevious() {
/* 47 */     return false;
/*    */   }
/*    */   
/*    */   public Object previous() {
/* 51 */     throw new NoSuchElementException("Iterator contains no elements");
/*    */   }
/*    */   
/*    */   public int nextIndex() {
/* 55 */     return 0;
/*    */   }
/*    */   
/*    */   public int previousIndex() {
/* 59 */     return -1;
/*    */   }
/*    */   
/*    */   public void add(Object obj) {
/* 63 */     throw new UnsupportedOperationException("add() not supported for empty Iterator");
/*    */   }
/*    */   
/*    */   public void set(Object obj) {
/* 67 */     throw new IllegalStateException("Iterator contains no elements");
/*    */   }
/*    */   
/*    */   public void remove() {
/* 71 */     throw new IllegalStateException("Iterator contains no elements");
/*    */   }
/*    */   
/*    */   public Object getKey() {
/* 75 */     throw new IllegalStateException("Iterator contains no elements");
/*    */   }
/*    */   
/*    */   public Object getValue() {
/* 79 */     throw new IllegalStateException("Iterator contains no elements");
/*    */   }
/*    */   
/*    */   public Object setValue(Object value) {
/* 83 */     throw new IllegalStateException("Iterator contains no elements");
/*    */   }
/*    */   
/*    */   public void reset() {}
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\iterators\AbstractEmptyIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */
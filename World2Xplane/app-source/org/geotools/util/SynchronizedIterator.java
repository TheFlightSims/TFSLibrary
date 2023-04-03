/*    */ package org.geotools.util;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ 
/*    */ final class SynchronizedIterator<E> implements Iterator<E> {
/*    */   private final Iterator<E> iterator;
/*    */   
/*    */   private final Object lock;
/*    */   
/*    */   SynchronizedIterator(Iterator<E> iterator, Object lock) {
/* 43 */     this.iterator = iterator;
/* 44 */     this.lock = lock;
/*    */   }
/*    */   
/*    */   public boolean hasNext() {
/* 51 */     synchronized (this.lock) {
/* 52 */       return this.iterator.hasNext();
/*    */     } 
/*    */   }
/*    */   
/*    */   public E next() {
/* 60 */     synchronized (this.lock) {
/* 61 */       return this.iterator.next();
/*    */     } 
/*    */   }
/*    */   
/*    */   public void remove() {
/* 69 */     synchronized (this.lock) {
/* 70 */       this.iterator.remove();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\SynchronizedIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
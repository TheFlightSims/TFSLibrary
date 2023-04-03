/*    */ package org.apache.commons.collections.iterators;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ 
/*    */ public class AbstractIteratorDecorator implements Iterator {
/*    */   protected final Iterator iterator;
/*    */   
/*    */   public AbstractIteratorDecorator(Iterator iterator) {
/* 46 */     if (iterator == null)
/* 47 */       throw new IllegalArgumentException("Iterator must not be null"); 
/* 49 */     this.iterator = iterator;
/*    */   }
/*    */   
/*    */   protected Iterator getIterator() {
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
/*    */   public void remove() {
/* 71 */     this.iterator.remove();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\iterators\AbstractIteratorDecorator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */
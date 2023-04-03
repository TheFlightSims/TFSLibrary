/*    */ package org.apache.commons.collections.iterators;
/*    */ 
/*    */ import java.util.Enumeration;
/*    */ import java.util.Iterator;
/*    */ 
/*    */ public class IteratorEnumeration implements Enumeration {
/*    */   private Iterator iterator;
/*    */   
/*    */   public IteratorEnumeration() {}
/*    */   
/*    */   public IteratorEnumeration(Iterator iterator) {
/* 53 */     this.iterator = iterator;
/*    */   }
/*    */   
/*    */   public boolean hasMoreElements() {
/* 65 */     return this.iterator.hasNext();
/*    */   }
/*    */   
/*    */   public Object nextElement() {
/* 76 */     return this.iterator.next();
/*    */   }
/*    */   
/*    */   public Iterator getIterator() {
/* 88 */     return this.iterator;
/*    */   }
/*    */   
/*    */   public void setIterator(Iterator iterator) {
/* 97 */     this.iterator = iterator;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\iterators\IteratorEnumeration.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */
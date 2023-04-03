/*    */ package org.jfree.util;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ 
/*    */ public class ReadOnlyIterator implements Iterator {
/*    */   private Iterator base;
/*    */   
/*    */   public ReadOnlyIterator(Iterator it) {
/* 63 */     if (it == null)
/* 64 */       throw new NullPointerException("Base iterator is null."); 
/* 66 */     this.base = it;
/*    */   }
/*    */   
/*    */   public boolean hasNext() {
/* 77 */     return this.base.hasNext();
/*    */   }
/*    */   
/*    */   public Object next() {
/* 87 */     return this.base.next();
/*    */   }
/*    */   
/*    */   public void remove() {
/* 94 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\util\ReadOnlyIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */
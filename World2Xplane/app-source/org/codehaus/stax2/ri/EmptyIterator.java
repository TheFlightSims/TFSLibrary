/*    */ package org.codehaus.stax2.ri;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.NoSuchElementException;
/*    */ 
/*    */ public final class EmptyIterator implements Iterator {
/* 12 */   static final EmptyIterator sInstance = new EmptyIterator();
/*    */   
/*    */   public static EmptyIterator getInstance() {
/* 16 */     return sInstance;
/*    */   }
/*    */   
/*    */   public boolean hasNext() {
/* 18 */     return false;
/*    */   }
/*    */   
/*    */   public Object next() {
/* 21 */     throw new NoSuchElementException();
/*    */   }
/*    */   
/*    */   public void remove() {
/* 29 */     throw new IllegalStateException();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\ri\EmptyIterator.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package org.codehaus.stax2.ri;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.NoSuchElementException;
/*    */ 
/*    */ public class SingletonIterator implements Iterator {
/*    */   private final Object mValue;
/*    */   
/*    */   private boolean mDone = false;
/*    */   
/*    */   public SingletonIterator(Object value) {
/* 17 */     this.mValue = value;
/*    */   }
/*    */   
/*    */   public boolean hasNext() {
/* 21 */     return !this.mDone;
/*    */   }
/*    */   
/*    */   public Object next() {
/* 25 */     if (this.mDone)
/* 26 */       throw new NoSuchElementException(); 
/* 28 */     this.mDone = true;
/* 29 */     return this.mValue;
/*    */   }
/*    */   
/*    */   public void remove() {
/* 34 */     throw new UnsupportedOperationException("Can not remove item from SingletonIterator.");
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\ri\SingletonIterator.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package org.geotools.data.store;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ 
/*    */ public class EmptyIterator implements Iterator {
/*    */   public void remove() {}
/*    */   
/*    */   public boolean hasNext() {
/* 37 */     return false;
/*    */   }
/*    */   
/*    */   public Object next() {
/* 41 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\store\EmptyIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
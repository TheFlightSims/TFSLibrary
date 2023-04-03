/*    */ package org.openstreetmap.osmosis.core.store;
/*    */ 
/*    */ import java.io.DataInputStream;
/*    */ 
/*    */ public class SubObjectStreamIterator<T extends Storeable> extends ObjectStreamIterator<T> {
/*    */   private long maxObjectCount;
/*    */   
/*    */   private long objectCount;
/*    */   
/*    */   public SubObjectStreamIterator(DataInputStream inStream, ObjectReader objectReader, long maxObjectCount) {
/* 31 */     super(inStream, objectReader);
/* 33 */     this.maxObjectCount = maxObjectCount;
/* 35 */     this.objectCount = 0L;
/*    */   }
/*    */   
/*    */   public boolean hasNext() {
/* 44 */     if (this.objectCount >= this.maxObjectCount)
/* 45 */       return false; 
/* 48 */     return super.hasNext();
/*    */   }
/*    */   
/*    */   public T next() {
/* 59 */     Storeable storeable = (Storeable)super.next();
/* 60 */     this.objectCount++;
/* 62 */     return (T)storeable;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\store\SubObjectStreamIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
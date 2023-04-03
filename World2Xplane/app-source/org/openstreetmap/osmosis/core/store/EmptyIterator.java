/*    */ package org.openstreetmap.osmosis.core.store;
/*    */ 
/*    */ import org.openstreetmap.osmosis.core.OsmosisRuntimeException;
/*    */ import org.openstreetmap.osmosis.core.lifecycle.ReleasableIterator;
/*    */ 
/*    */ public class EmptyIterator<DataType> implements ReleasableIterator<DataType> {
/*    */   public boolean hasNext() {
/* 23 */     return false;
/*    */   }
/*    */   
/*    */   public DataType next() {
/* 31 */     throw new OsmosisRuntimeException("This iterator contains no data.");
/*    */   }
/*    */   
/*    */   public void remove() {
/* 39 */     throw new UnsupportedOperationException();
/*    */   }
/*    */   
/*    */   public void release() {}
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\store\EmptyIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
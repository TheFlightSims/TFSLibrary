/*    */ package org.openstreetmap.osmosis.core.domain.v0_6;
/*    */ 
/*    */ import org.openstreetmap.osmosis.core.store.StoreClassRegister;
/*    */ import org.openstreetmap.osmosis.core.store.StoreReader;
/*    */ import org.openstreetmap.osmosis.core.store.StoreWriter;
/*    */ import org.openstreetmap.osmosis.core.store.Storeable;
/*    */ 
/*    */ public class WayNode implements Comparable<WayNode>, Storeable {
/*    */   private long nodeId;
/*    */   
/*    */   public WayNode(long nodeId) {
/* 27 */     this.nodeId = nodeId;
/*    */   }
/*    */   
/*    */   public WayNode(StoreReader sr, StoreClassRegister scr) {
/* 41 */     this(sr.readLong());
/*    */   }
/*    */   
/*    */   public void store(StoreWriter sw, StoreClassRegister scr) {
/* 49 */     sw.writeLong(this.nodeId);
/*    */   }
/*    */   
/*    */   public int compareTo(WayNode wayNode) {
/* 65 */     long result = this.nodeId - wayNode.nodeId;
/* 67 */     if (result > 0L)
/* 68 */       return 1; 
/* 69 */     if (result < 0L)
/* 70 */       return -1; 
/* 72 */     return 0;
/*    */   }
/*    */   
/*    */   public long getNodeId() {
/* 81 */     return this.nodeId;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 89 */     return "WayNode(nodeID=" + getNodeId() + ")";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\domain\v0_6\WayNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
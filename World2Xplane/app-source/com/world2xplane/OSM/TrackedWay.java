/*    */ package com.world2xplane.OSM;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ public class TrackedWay {
/*    */   long wayId;
/*    */   
/*    */   List<Node> nodes;
/*    */   
/*    */   String identifier;
/*    */   
/*    */   public long getWayId() {
/* 33 */     return this.wayId;
/*    */   }
/*    */   
/*    */   public void setWayId(long wayId) {
/* 37 */     this.wayId = wayId;
/*    */   }
/*    */   
/*    */   public List<Node> getNodes() {
/* 41 */     return this.nodes;
/*    */   }
/*    */   
/*    */   public void setNodes(List<Node> nodes) {
/* 45 */     this.nodes = nodes;
/*    */   }
/*    */   
/*    */   public String getIdentifier() {
/* 49 */     return this.identifier;
/*    */   }
/*    */   
/*    */   public void setIdentifier(String identifier) {
/* 53 */     this.identifier = identifier;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\OSM\TrackedWay.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
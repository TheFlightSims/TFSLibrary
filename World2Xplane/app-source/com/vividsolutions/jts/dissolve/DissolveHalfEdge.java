/*    */ package com.vividsolutions.jts.dissolve;
/*    */ 
/*    */ import com.vividsolutions.jts.edgegraph.MarkHalfEdge;
/*    */ import com.vividsolutions.jts.geom.Coordinate;
/*    */ 
/*    */ class DissolveHalfEdge extends MarkHalfEdge {
/*    */   private boolean isStart = false;
/*    */   
/*    */   public DissolveHalfEdge(Coordinate orig) {
/* 19 */     super(orig);
/*    */   }
/*    */   
/*    */   public boolean isStart() {
/* 30 */     return this.isStart;
/*    */   }
/*    */   
/*    */   public void setStart() {
/* 38 */     this.isStart = true;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\dissolve\DissolveHalfEdge.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
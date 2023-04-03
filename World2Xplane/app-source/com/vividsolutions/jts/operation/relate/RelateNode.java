/*    */ package com.vividsolutions.jts.operation.relate;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Coordinate;
/*    */ import com.vividsolutions.jts.geom.IntersectionMatrix;
/*    */ import com.vividsolutions.jts.geomgraph.EdgeEndStar;
/*    */ import com.vividsolutions.jts.geomgraph.Node;
/*    */ 
/*    */ public class RelateNode extends Node {
/*    */   public RelateNode(Coordinate coord, EdgeEndStar edges) {
/* 62 */     super(coord, edges);
/*    */   }
/*    */   
/*    */   protected void computeIM(IntersectionMatrix im) {
/* 71 */     im.setAtLeastIfValid(this.label.getLocation(0), this.label.getLocation(1), 0);
/*    */   }
/*    */   
/*    */   void updateIMFromEdges(IntersectionMatrix im) {
/* 78 */     ((EdgeEndBundleStar)this.edges).updateIM(im);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\relate\RelateNode.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
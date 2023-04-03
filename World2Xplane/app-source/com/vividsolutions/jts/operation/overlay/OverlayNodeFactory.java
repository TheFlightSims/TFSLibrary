/*    */ package com.vividsolutions.jts.operation.overlay;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Coordinate;
/*    */ import com.vividsolutions.jts.geomgraph.DirectedEdgeStar;
/*    */ import com.vividsolutions.jts.geomgraph.EdgeEndStar;
/*    */ import com.vividsolutions.jts.geomgraph.Node;
/*    */ import com.vividsolutions.jts.geomgraph.NodeFactory;
/*    */ 
/*    */ public class OverlayNodeFactory extends NodeFactory {
/*    */   public Node createNode(Coordinate coord) {
/* 54 */     return new Node(coord, (EdgeEndStar)new DirectedEdgeStar());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\overlay\OverlayNodeFactory.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
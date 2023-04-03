/*    */ package com.vividsolutions.jts.geomgraph;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Coordinate;
/*    */ 
/*    */ public class NodeFactory {
/*    */   public Node createNode(Coordinate coord) {
/* 48 */     return new Node(coord, null);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geomgraph\NodeFactory.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
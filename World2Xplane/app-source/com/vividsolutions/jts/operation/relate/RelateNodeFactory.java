/*    */ package com.vividsolutions.jts.operation.relate;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Coordinate;
/*    */ import com.vividsolutions.jts.geomgraph.Node;
/*    */ import com.vividsolutions.jts.geomgraph.NodeFactory;
/*    */ 
/*    */ public class RelateNodeFactory extends NodeFactory {
/*    */   public Node createNode(Coordinate coord) {
/* 50 */     return new RelateNode(coord, new EdgeEndBundleStar());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\relate\RelateNodeFactory.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
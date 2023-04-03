/*    */ package com.vividsolutions.jts.dissolve;
/*    */ 
/*    */ import com.vividsolutions.jts.edgegraph.EdgeGraph;
/*    */ import com.vividsolutions.jts.edgegraph.HalfEdge;
/*    */ import com.vividsolutions.jts.geom.Coordinate;
/*    */ 
/*    */ class DissolveEdgeGraph extends EdgeGraph {
/*    */   protected HalfEdge createEdge(Coordinate p0) {
/* 18 */     return (HalfEdge)new DissolveHalfEdge(p0);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\dissolve\DissolveEdgeGraph.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
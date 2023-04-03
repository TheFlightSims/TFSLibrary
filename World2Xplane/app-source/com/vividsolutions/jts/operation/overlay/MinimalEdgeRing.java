/*    */ package com.vividsolutions.jts.operation.overlay;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.GeometryFactory;
/*    */ import com.vividsolutions.jts.geomgraph.DirectedEdge;
/*    */ import com.vividsolutions.jts.geomgraph.EdgeRing;
/*    */ 
/*    */ public class MinimalEdgeRing extends EdgeRing {
/*    */   public MinimalEdgeRing(DirectedEdge start, GeometryFactory geometryFactory) {
/* 54 */     super(start, geometryFactory);
/*    */   }
/*    */   
/*    */   public DirectedEdge getNext(DirectedEdge de) {
/* 59 */     return de.getNextMin();
/*    */   }
/*    */   
/*    */   public void setEdgeRing(DirectedEdge de, EdgeRing er) {
/* 63 */     de.setMinEdgeRing(er);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\overlay\MinimalEdgeRing.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
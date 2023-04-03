/*    */ package com.vividsolutions.jts.operation.linemerge;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Coordinate;
/*    */ import com.vividsolutions.jts.planargraph.DirectedEdge;
/*    */ import com.vividsolutions.jts.planargraph.Node;
/*    */ import com.vividsolutions.jts.util.Assert;
/*    */ 
/*    */ public class LineMergeDirectedEdge extends DirectedEdge {
/*    */   public LineMergeDirectedEdge(Node from, Node to, Coordinate directionPt, boolean edgeDirection) {
/* 61 */     super(from, to, directionPt, edgeDirection);
/*    */   }
/*    */   
/*    */   public LineMergeDirectedEdge getNext() {
/* 70 */     if (getToNode().getDegree() != 2)
/* 71 */       return null; 
/* 73 */     if (getToNode().getOutEdges().getEdges().get(0) == getSym())
/* 74 */       return getToNode().getOutEdges().getEdges().get(1); 
/* 76 */     Assert.isTrue((getToNode().getOutEdges().getEdges().get(1) == getSym()));
/* 78 */     return getToNode().getOutEdges().getEdges().get(0);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\linemerge\LineMergeDirectedEdge.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
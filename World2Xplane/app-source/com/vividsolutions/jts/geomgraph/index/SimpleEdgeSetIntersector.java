/*    */ package com.vividsolutions.jts.geomgraph.index;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Coordinate;
/*    */ import com.vividsolutions.jts.geomgraph.Edge;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ 
/*    */ public class SimpleEdgeSetIntersector extends EdgeSetIntersector {
/*    */   int nOverlaps;
/*    */   
/*    */   public void computeIntersections(List edges, SegmentIntersector si, boolean testAllSegments) {
/* 60 */     this.nOverlaps = 0;
/* 62 */     for (Iterator<Edge> i0 = edges.iterator(); i0.hasNext(); ) {
/* 63 */       Edge edge0 = i0.next();
/* 64 */       for (Iterator<Edge> i1 = edges.iterator(); i1.hasNext(); ) {
/* 65 */         Edge edge1 = i1.next();
/* 66 */         if (testAllSegments || edge0 != edge1)
/* 67 */           computeIntersects(edge0, edge1, si); 
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public void computeIntersections(List edges0, List edges1, SegmentIntersector si) {
/* 75 */     this.nOverlaps = 0;
/* 77 */     for (Iterator<Edge> i0 = edges0.iterator(); i0.hasNext(); ) {
/* 78 */       Edge edge0 = i0.next();
/* 79 */       for (Iterator<Edge> i1 = edges1.iterator(); i1.hasNext(); ) {
/* 80 */         Edge edge1 = i1.next();
/* 81 */         computeIntersects(edge0, edge1, si);
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   private void computeIntersects(Edge e0, Edge e1, SegmentIntersector si) {
/* 93 */     Coordinate[] pts0 = e0.getCoordinates();
/* 94 */     Coordinate[] pts1 = e1.getCoordinates();
/* 95 */     for (int i0 = 0; i0 < pts0.length - 1; i0++) {
/* 96 */       for (int i1 = 0; i1 < pts1.length - 1; i1++)
/* 97 */         si.addIntersections(e0, i0, e1, i1); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geomgraph\index\SimpleEdgeSetIntersector.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
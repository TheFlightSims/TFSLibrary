/*    */ package com.vividsolutions.jts.operation.overlay;
/*    */ 
/*    */ import com.vividsolutions.jts.algorithm.LineIntersector;
/*    */ import com.vividsolutions.jts.geomgraph.Edge;
/*    */ import com.vividsolutions.jts.geomgraph.index.SegmentIntersector;
/*    */ import com.vividsolutions.jts.geomgraph.index.SimpleMCSweepLineIntersector;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ 
/*    */ public class EdgeSetNoder {
/*    */   private LineIntersector li;
/*    */   
/* 53 */   private List inputEdges = new ArrayList();
/*    */   
/*    */   public EdgeSetNoder(LineIntersector li) {
/* 56 */     this.li = li;
/*    */   }
/*    */   
/*    */   public void addEdges(List edges) {
/* 61 */     this.inputEdges.addAll(edges);
/*    */   }
/*    */   
/*    */   public List getNodedEdges() {
/* 66 */     SimpleMCSweepLineIntersector simpleMCSweepLineIntersector = new SimpleMCSweepLineIntersector();
/* 67 */     SegmentIntersector si = new SegmentIntersector(this.li, true, false);
/* 68 */     simpleMCSweepLineIntersector.computeIntersections(this.inputEdges, si, true);
/* 71 */     List splitEdges = new ArrayList();
/* 72 */     for (Iterator<Edge> i = this.inputEdges.iterator(); i.hasNext(); ) {
/* 73 */       Edge e = i.next();
/* 74 */       e.getEdgeIntersectionList().addSplitEdges(splitEdges);
/*    */     } 
/* 76 */     return splitEdges;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\overlay\EdgeSetNoder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
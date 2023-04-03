/*    */ package com.vividsolutions.jts.geomgraph.index;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Coordinate;
/*    */ import com.vividsolutions.jts.geomgraph.Edge;
/*    */ 
/*    */ public class SweepLineSegment {
/*    */   Edge edge;
/*    */   
/*    */   Coordinate[] pts;
/*    */   
/*    */   int ptIndex;
/*    */   
/*    */   public SweepLineSegment(Edge edge, int ptIndex) {
/* 52 */     this.edge = edge;
/* 53 */     this.ptIndex = ptIndex;
/* 54 */     this.pts = edge.getCoordinates();
/*    */   }
/*    */   
/*    */   public double getMinX() {
/* 59 */     double x1 = (this.pts[this.ptIndex]).x;
/* 60 */     double x2 = (this.pts[this.ptIndex + 1]).x;
/* 61 */     return (x1 < x2) ? x1 : x2;
/*    */   }
/*    */   
/*    */   public double getMaxX() {
/* 65 */     double x1 = (this.pts[this.ptIndex]).x;
/* 66 */     double x2 = (this.pts[this.ptIndex + 1]).x;
/* 67 */     return (x1 > x2) ? x1 : x2;
/*    */   }
/*    */   
/*    */   public void computeIntersections(SweepLineSegment ss, SegmentIntersector si) {
/* 71 */     si.addIntersections(this.edge, this.ptIndex, ss.edge, ss.ptIndex);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geomgraph\index\SweepLineSegment.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
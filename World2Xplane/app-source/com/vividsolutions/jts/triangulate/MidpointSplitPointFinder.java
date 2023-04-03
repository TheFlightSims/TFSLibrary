/*    */ package com.vividsolutions.jts.triangulate;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Coordinate;
/*    */ 
/*    */ public class MidpointSplitPointFinder implements ConstraintSplitPointFinder {
/*    */   public Coordinate findSplitPoint(Segment seg, Coordinate encroachPt) {
/* 57 */     Coordinate p0 = seg.getStart();
/* 58 */     Coordinate p1 = seg.getEnd();
/* 59 */     return new Coordinate((p0.x + p1.x) / 2.0D, (p0.y + p1.y) / 2.0D);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\triangulate\MidpointSplitPointFinder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
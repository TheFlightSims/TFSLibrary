/*    */ package com.vividsolutions.jts.triangulate;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Coordinate;
/*    */ import com.vividsolutions.jts.geom.LineSegment;
/*    */ 
/*    */ public class NonEncroachingSplitPointFinder implements ConstraintSplitPointFinder {
/*    */   public Coordinate findSplitPoint(Segment seg, Coordinate encroachPt) {
/* 59 */     LineSegment lineSeg = seg.getLineSegment();
/* 60 */     double segLen = lineSeg.getLength();
/* 61 */     double midPtLen = segLen / 2.0D;
/* 62 */     SplitSegment splitSeg = new SplitSegment(lineSeg);
/* 64 */     Coordinate projPt = projectedSplitPoint(seg, encroachPt);
/* 70 */     double nonEncroachDiam = projPt.distance(encroachPt) * 2.0D * 0.8D;
/* 71 */     double maxSplitLen = nonEncroachDiam;
/* 72 */     if (maxSplitLen > midPtLen)
/* 73 */       maxSplitLen = midPtLen; 
/* 75 */     splitSeg.setMinimumLength(maxSplitLen);
/* 77 */     splitSeg.splitAt(projPt);
/* 79 */     return splitSeg.getSplitPoint();
/*    */   }
/*    */   
/*    */   public static Coordinate projectedSplitPoint(Segment seg, Coordinate encroachPt) {
/* 90 */     LineSegment lineSeg = seg.getLineSegment();
/* 91 */     Coordinate projPt = lineSeg.project(encroachPt);
/* 92 */     return projPt;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\triangulate\NonEncroachingSplitPointFinder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
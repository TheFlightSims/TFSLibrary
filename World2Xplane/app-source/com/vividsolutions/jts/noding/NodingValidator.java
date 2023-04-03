/*     */ package com.vividsolutions.jts.noding;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.LineIntersector;
/*     */ import com.vividsolutions.jts.algorithm.RobustLineIntersector;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.util.Debug;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class NodingValidator {
/*  49 */   private LineIntersector li = (LineIntersector)new RobustLineIntersector();
/*     */   
/*     */   private Collection segStrings;
/*     */   
/*     */   public NodingValidator(Collection segStrings) {
/*  55 */     this.segStrings = segStrings;
/*     */   }
/*     */   
/*     */   public void checkValid() {
/*  61 */     checkEndPtVertexIntersections();
/*  62 */     checkInteriorIntersections();
/*  63 */     checkCollapses();
/*     */   }
/*     */   
/*     */   private void checkCollapses() {
/*  71 */     for (Iterator<SegmentString> i = this.segStrings.iterator(); i.hasNext(); ) {
/*  72 */       SegmentString ss = i.next();
/*  73 */       checkCollapses(ss);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void checkCollapses(SegmentString ss) {
/*  79 */     Coordinate[] pts = ss.getCoordinates();
/*  80 */     for (int i = 0; i < pts.length - 2; i++)
/*  81 */       checkCollapse(pts[i], pts[i + 1], pts[i + 2]); 
/*     */   }
/*     */   
/*     */   private void checkCollapse(Coordinate p0, Coordinate p1, Coordinate p2) {
/*  87 */     if (p0.equals(p2))
/*  88 */       throw new RuntimeException("found non-noded collapse at " + Debug.toLine(p0, p1, p2)); 
/*     */   }
/*     */   
/*     */   private void checkInteriorIntersections() {
/*  97 */     for (Iterator<SegmentString> i = this.segStrings.iterator(); i.hasNext(); ) {
/*  98 */       SegmentString ss0 = i.next();
/*  99 */       for (Iterator<SegmentString> j = this.segStrings.iterator(); j.hasNext(); ) {
/* 100 */         SegmentString ss1 = j.next();
/* 102 */         checkInteriorIntersections(ss0, ss1);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void checkInteriorIntersections(SegmentString ss0, SegmentString ss1) {
/* 109 */     Coordinate[] pts0 = ss0.getCoordinates();
/* 110 */     Coordinate[] pts1 = ss1.getCoordinates();
/* 111 */     for (int i0 = 0; i0 < pts0.length - 1; i0++) {
/* 112 */       for (int i1 = 0; i1 < pts1.length - 1; i1++)
/* 113 */         checkInteriorIntersections(ss0, i0, ss1, i1); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void checkInteriorIntersections(SegmentString e0, int segIndex0, SegmentString e1, int segIndex1) {
/* 120 */     if (e0 == e1 && segIndex0 == segIndex1)
/*     */       return; 
/* 122 */     Coordinate p00 = e0.getCoordinates()[segIndex0];
/* 123 */     Coordinate p01 = e0.getCoordinates()[segIndex0 + 1];
/* 124 */     Coordinate p10 = e1.getCoordinates()[segIndex1];
/* 125 */     Coordinate p11 = e1.getCoordinates()[segIndex1 + 1];
/* 127 */     this.li.computeIntersection(p00, p01, p10, p11);
/* 128 */     if (this.li.hasIntersection())
/* 130 */       if (this.li.isProper() || hasInteriorIntersection(this.li, p00, p01) || hasInteriorIntersection(this.li, p10, p11))
/* 133 */         throw new RuntimeException("found non-noded intersection at " + p00 + "-" + p01 + " and " + p10 + "-" + p11);  
/*     */   }
/*     */   
/*     */   private boolean hasInteriorIntersection(LineIntersector li, Coordinate p0, Coordinate p1) {
/* 145 */     for (int i = 0; i < li.getIntersectionNum(); i++) {
/* 146 */       Coordinate intPt = li.getIntersection(i);
/* 147 */       if (!intPt.equals(p0) && !intPt.equals(p1))
/* 148 */         return true; 
/*     */     } 
/* 150 */     return false;
/*     */   }
/*     */   
/*     */   private void checkEndPtVertexIntersections() {
/* 159 */     for (Iterator<SegmentString> i = this.segStrings.iterator(); i.hasNext(); ) {
/* 160 */       SegmentString ss = i.next();
/* 161 */       Coordinate[] pts = ss.getCoordinates();
/* 162 */       checkEndPtVertexIntersections(pts[0], this.segStrings);
/* 163 */       checkEndPtVertexIntersections(pts[pts.length - 1], this.segStrings);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void checkEndPtVertexIntersections(Coordinate testPt, Collection segStrings) {
/* 169 */     for (Iterator<SegmentString> i = segStrings.iterator(); i.hasNext(); ) {
/* 170 */       SegmentString ss = i.next();
/* 171 */       Coordinate[] pts = ss.getCoordinates();
/* 172 */       for (int j = 1; j < pts.length - 1; j++) {
/* 173 */         if (pts[j].equals(testPt))
/* 174 */           throw new RuntimeException("found endpt/interior pt intersection at index " + j + " :pt " + testPt); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\noding\NodingValidator.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
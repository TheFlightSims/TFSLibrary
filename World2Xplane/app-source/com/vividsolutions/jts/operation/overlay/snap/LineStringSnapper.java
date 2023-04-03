/*     */ package com.vividsolutions.jts.operation.overlay.snap;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateList;
/*     */ import com.vividsolutions.jts.geom.LineSegment;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ 
/*     */ public class LineStringSnapper {
/*  50 */   private double snapTolerance = 0.0D;
/*     */   
/*     */   private Coordinate[] srcPts;
/*     */   
/*  53 */   private LineSegment seg = new LineSegment();
/*     */   
/*     */   private boolean allowSnappingToSourceVertices = false;
/*     */   
/*     */   private boolean isClosed = false;
/*     */   
/*     */   public LineStringSnapper(LineString srcLine, double snapTolerance) {
/*  66 */     this(srcLine.getCoordinates(), snapTolerance);
/*     */   }
/*     */   
/*     */   public LineStringSnapper(Coordinate[] srcPts, double snapTolerance) {
/*  78 */     this.srcPts = srcPts;
/*  79 */     this.isClosed = isClosed(srcPts);
/*  80 */     this.snapTolerance = snapTolerance;
/*     */   }
/*     */   
/*     */   public void setAllowSnappingToSourceVertices(boolean allowSnappingToSourceVertices) {
/*  85 */     this.allowSnappingToSourceVertices = allowSnappingToSourceVertices;
/*     */   }
/*     */   
/*     */   private static boolean isClosed(Coordinate[] pts) {
/*  89 */     if (pts.length <= 1)
/*  89 */       return false; 
/*  90 */     return pts[0].equals2D(pts[pts.length - 1]);
/*     */   }
/*     */   
/*     */   public Coordinate[] snapTo(Coordinate[] snapPts) {
/* 101 */     CoordinateList coordList = new CoordinateList(this.srcPts);
/* 103 */     snapVertices(coordList, snapPts);
/* 104 */     snapSegments(coordList, snapPts);
/* 106 */     Coordinate[] newPts = coordList.toCoordinateArray();
/* 107 */     return newPts;
/*     */   }
/*     */   
/*     */   private void snapVertices(CoordinateList srcCoords, Coordinate[] snapPts) {
/* 120 */     int end = this.isClosed ? (srcCoords.size() - 1) : srcCoords.size();
/* 121 */     for (int i = 0; i < end; i++) {
/* 122 */       Coordinate srcPt = (Coordinate)srcCoords.get(i);
/* 123 */       Coordinate snapVert = findSnapForVertex(srcPt, snapPts);
/* 124 */       if (snapVert != null) {
/* 126 */         srcCoords.set(i, new Coordinate(snapVert));
/* 128 */         if (i == 0 && this.isClosed)
/* 129 */           srcCoords.set(srcCoords.size() - 1, new Coordinate(snapVert)); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private Coordinate findSnapForVertex(Coordinate pt, Coordinate[] snapPts) {
/* 136 */     for (int i = 0; i < snapPts.length; i++) {
/* 138 */       if (pt.equals2D(snapPts[i]))
/* 139 */         return null; 
/* 140 */       if (pt.distance(snapPts[i]) < this.snapTolerance)
/* 141 */         return snapPts[i]; 
/*     */     } 
/* 143 */     return null;
/*     */   }
/*     */   
/*     */   private void snapSegments(CoordinateList srcCoords, Coordinate[] snapPts) {
/* 163 */     if (snapPts.length == 0)
/*     */       return; 
/* 165 */     int distinctPtCount = snapPts.length;
/* 169 */     if (snapPts[0].equals2D(snapPts[snapPts.length - 1]))
/* 170 */       distinctPtCount = snapPts.length - 1; 
/* 172 */     for (int i = 0; i < distinctPtCount; i++) {
/* 173 */       Coordinate snapPt = snapPts[i];
/* 174 */       int index = findSegmentIndexToSnap(snapPt, srcCoords);
/* 181 */       if (index >= 0)
/* 182 */         srcCoords.add(index + 1, new Coordinate(snapPt), false); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private int findSegmentIndexToSnap(Coordinate snapPt, CoordinateList srcCoords) {
/* 209 */     double minDist = Double.MAX_VALUE;
/* 210 */     int snapIndex = -1;
/* 211 */     for (int i = 0; i < srcCoords.size() - 1; i++) {
/* 212 */       this.seg.p0 = (Coordinate)srcCoords.get(i);
/* 213 */       this.seg.p1 = (Coordinate)srcCoords.get(i + 1);
/* 220 */       if (this.seg.p0.equals2D(snapPt) || this.seg.p1.equals2D(snapPt)) {
/* 221 */         if (!this.allowSnappingToSourceVertices)
/* 224 */           return -1; 
/*     */       } else {
/* 227 */         double dist = this.seg.distance(snapPt);
/* 228 */         if (dist < this.snapTolerance && dist < minDist) {
/* 229 */           minDist = dist;
/* 230 */           snapIndex = i;
/*     */         } 
/*     */       } 
/*     */     } 
/* 233 */     return snapIndex;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\overlay\snap\LineStringSnapper.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
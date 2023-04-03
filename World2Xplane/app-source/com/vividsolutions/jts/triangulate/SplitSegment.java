/*     */ package com.vividsolutions.jts.triangulate;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.LineSegment;
/*     */ 
/*     */ public class SplitSegment {
/*     */   private LineSegment seg;
/*     */   
/*     */   private double segLen;
/*     */   
/*     */   private Coordinate splitPt;
/*     */   
/*     */   private static Coordinate pointAlongReverse(LineSegment seg, double segmentLengthFraction) {
/*  56 */     Coordinate coord = new Coordinate();
/*  57 */     seg.p1.x -= segmentLengthFraction * (seg.p1.x - seg.p0.x);
/*  58 */     seg.p1.y -= segmentLengthFraction * (seg.p1.y - seg.p0.y);
/*  59 */     return coord;
/*     */   }
/*     */   
/*  65 */   private double minimumLen = 0.0D;
/*     */   
/*     */   public SplitSegment(LineSegment seg) {
/*  68 */     this.seg = seg;
/*  69 */     this.segLen = seg.getLength();
/*     */   }
/*     */   
/*     */   public void setMinimumLength(double minLen) {
/*  73 */     this.minimumLen = minLen;
/*     */   }
/*     */   
/*     */   public Coordinate getSplitPoint() {
/*  77 */     return this.splitPt;
/*     */   }
/*     */   
/*     */   public void splitAt(double length, Coordinate endPt) {
/*  81 */     double actualLen = getConstrainedLength(length);
/*  82 */     double frac = actualLen / this.segLen;
/*  83 */     if (endPt.equals2D(this.seg.p0)) {
/*  84 */       this.splitPt = this.seg.pointAlong(frac);
/*     */     } else {
/*  86 */       this.splitPt = pointAlongReverse(this.seg, frac);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void splitAt(Coordinate pt) {
/*  91 */     double minFrac = this.minimumLen / this.segLen;
/*  92 */     if (pt.distance(this.seg.p0) < this.minimumLen) {
/*  93 */       this.splitPt = this.seg.pointAlong(minFrac);
/*     */       return;
/*     */     } 
/*  96 */     if (pt.distance(this.seg.p1) < this.minimumLen) {
/*  97 */       this.splitPt = pointAlongReverse(this.seg, minFrac);
/*     */       return;
/*     */     } 
/* 101 */     this.splitPt = pt;
/*     */   }
/*     */   
/*     */   private double getConstrainedLength(double len) {
/* 105 */     if (len < this.minimumLen)
/* 106 */       return this.minimumLen; 
/* 107 */     return len;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\triangulate\SplitSegment.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
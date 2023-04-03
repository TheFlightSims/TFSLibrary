/*     */ package com.vividsolutions.jts.simplify;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateList;
/*     */ import com.vividsolutions.jts.geom.LineSegment;
/*     */ 
/*     */ class DouglasPeuckerLineSimplifier {
/*     */   private Coordinate[] pts;
/*     */   
/*     */   private boolean[] usePt;
/*     */   
/*     */   private double distanceTolerance;
/*     */   
/*     */   private LineSegment seg;
/*     */   
/*     */   public static Coordinate[] simplify(Coordinate[] pts, double distanceTolerance) {
/*  48 */     DouglasPeuckerLineSimplifier simp = new DouglasPeuckerLineSimplifier(pts);
/*  49 */     simp.setDistanceTolerance(distanceTolerance);
/*  50 */     return simp.simplify();
/*     */   }
/*     */   
/*     */   public DouglasPeuckerLineSimplifier(Coordinate[] pts) {
/*  87 */     this.seg = new LineSegment();
/*     */     this.pts = pts;
/*     */   }
/*     */   
/*     */   public void setDistanceTolerance(double distanceTolerance) {
/*     */     this.distanceTolerance = distanceTolerance;
/*     */   }
/*     */   
/*     */   public Coordinate[] simplify() {
/*     */     this.usePt = new boolean[this.pts.length];
/*     */     for (int i = 0; i < this.pts.length; i++)
/*     */       this.usePt[i] = true; 
/*     */     simplifySection(0, this.pts.length - 1);
/*     */     CoordinateList coordList = new CoordinateList();
/*     */     for (int j = 0; j < this.pts.length; j++) {
/*     */       if (this.usePt[j])
/*     */         coordList.add(new Coordinate(this.pts[j])); 
/*     */     } 
/*     */     return coordList.toCoordinateArray();
/*     */   }
/*     */   
/*     */   private void simplifySection(int i, int j) {
/*  91 */     if (i + 1 == j)
/*     */       return; 
/*  94 */     this.seg.p0 = this.pts[i];
/*  95 */     this.seg.p1 = this.pts[j];
/*  96 */     double maxDistance = -1.0D;
/*  97 */     int maxIndex = i;
/*     */     int k;
/*  98 */     for (k = i + 1; k < j; k++) {
/*  99 */       double distance = this.seg.distance(this.pts[k]);
/* 100 */       if (distance > maxDistance) {
/* 101 */         maxDistance = distance;
/* 102 */         maxIndex = k;
/*     */       } 
/*     */     } 
/* 105 */     if (maxDistance <= this.distanceTolerance) {
/* 106 */       for (k = i + 1; k < j; k++)
/* 107 */         this.usePt[k] = false; 
/*     */     } else {
/* 111 */       simplifySection(i, maxIndex);
/* 112 */       simplifySection(maxIndex, j);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\simplify\DouglasPeuckerLineSimplifier.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package com.vividsolutions.jts.algorithm;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ 
/*     */ public class CentralEndpointIntersector {
/*     */   private Coordinate[] pts;
/*     */   
/*     */   public static Coordinate getIntersection(Coordinate p00, Coordinate p01, Coordinate p10, Coordinate p11) {
/*  65 */     CentralEndpointIntersector intor = new CentralEndpointIntersector(p00, p01, p10, p11);
/*  66 */     return intor.getIntersection();
/*     */   }
/*     */   
/*  70 */   private Coordinate intPt = null;
/*     */   
/*     */   private double minDist;
/*     */   
/*     */   private void Ocompute() {
/*  81 */     Coordinate centroid = average(this.pts);
/*  82 */     this.intPt = new Coordinate(findNearestPoint(centroid, this.pts));
/*     */   }
/*     */   
/*     */   public Coordinate getIntersection() {
/*  86 */     return this.intPt;
/*     */   }
/*     */   
/*     */   private static Coordinate average(Coordinate[] pts) {
/*  91 */     Coordinate avg = new Coordinate();
/*  92 */     int n = pts.length;
/*  93 */     for (int i = 0; i < pts.length; i++) {
/*  94 */       avg.x += (pts[i]).x;
/*  95 */       avg.y += (pts[i]).y;
/*     */     } 
/*  97 */     if (n > 0) {
/*  98 */       avg.x /= n;
/*  99 */       avg.y /= n;
/*     */     } 
/* 101 */     return avg;
/*     */   }
/*     */   
/*     */   private Coordinate findNearestPoint(Coordinate p, Coordinate[] pts) {
/* 116 */     double minDist = Double.MAX_VALUE;
/* 117 */     Coordinate result = null;
/* 118 */     for (int i = 0; i < pts.length; i++) {
/* 119 */       double dist = p.distance(pts[i]);
/* 121 */       if (i == 0 || dist < minDist) {
/* 122 */         minDist = dist;
/* 123 */         result = pts[i];
/*     */       } 
/*     */     } 
/* 126 */     return result;
/*     */   }
/*     */   
/*     */   public CentralEndpointIntersector(Coordinate p00, Coordinate p01, Coordinate p10, Coordinate p11) {
/* 129 */     this.minDist = Double.MAX_VALUE;
/*     */     this.pts = new Coordinate[] { p00, p01, p10, p11 };
/*     */     compute();
/*     */   }
/*     */   
/*     */   private void compute() {
/* 136 */     tryDist(this.pts[0], this.pts[2], this.pts[3]);
/* 137 */     tryDist(this.pts[1], this.pts[2], this.pts[3]);
/* 138 */     tryDist(this.pts[2], this.pts[0], this.pts[1]);
/* 139 */     tryDist(this.pts[3], this.pts[0], this.pts[1]);
/*     */   }
/*     */   
/*     */   private void tryDist(Coordinate p, Coordinate p0, Coordinate p1) {
/* 144 */     double dist = CGAlgorithms.distancePointLine(p, p0, p1);
/* 145 */     if (dist < this.minDist) {
/* 146 */       this.minDist = dist;
/* 147 */       this.intPt = p;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\algorithm\CentralEndpointIntersector.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
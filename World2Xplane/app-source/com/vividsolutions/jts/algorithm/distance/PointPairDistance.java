/*     */ package com.vividsolutions.jts.algorithm.distance;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.io.WKTWriter;
/*     */ 
/*     */ public class PointPairDistance {
/*  46 */   private Coordinate[] pt = new Coordinate[] { new Coordinate(), new Coordinate() };
/*     */   
/*  47 */   private double distance = Double.NaN;
/*     */   
/*     */   private boolean isNull = true;
/*     */   
/*     */   public void initialize() {
/*  54 */     this.isNull = true;
/*     */   }
/*     */   
/*     */   public void initialize(Coordinate p0, Coordinate p1) {
/*  58 */     this.pt[0].setCoordinate(p0);
/*  59 */     this.pt[1].setCoordinate(p1);
/*  60 */     this.distance = p0.distance(p1);
/*  61 */     this.isNull = false;
/*     */   }
/*     */   
/*     */   private void initialize(Coordinate p0, Coordinate p1, double distance) {
/*  72 */     this.pt[0].setCoordinate(p0);
/*  73 */     this.pt[1].setCoordinate(p1);
/*  74 */     this.distance = distance;
/*  75 */     this.isNull = false;
/*     */   }
/*     */   
/*     */   public double getDistance() {
/*  78 */     return this.distance;
/*     */   }
/*     */   
/*     */   public Coordinate[] getCoordinates() {
/*  80 */     return this.pt;
/*     */   }
/*     */   
/*     */   public Coordinate getCoordinate(int i) {
/*  82 */     return this.pt[i];
/*     */   }
/*     */   
/*     */   public void setMaximum(PointPairDistance ptDist) {
/*  86 */     setMaximum(ptDist.pt[0], ptDist.pt[1]);
/*     */   }
/*     */   
/*     */   public void setMaximum(Coordinate p0, Coordinate p1) {
/*  91 */     if (this.isNull) {
/*  92 */       initialize(p0, p1);
/*     */       return;
/*     */     } 
/*  95 */     double dist = p0.distance(p1);
/*  96 */     if (dist > this.distance)
/*  97 */       initialize(p0, p1, dist); 
/*     */   }
/*     */   
/*     */   public void setMinimum(PointPairDistance ptDist) {
/* 102 */     setMinimum(ptDist.pt[0], ptDist.pt[1]);
/*     */   }
/*     */   
/*     */   public void setMinimum(Coordinate p0, Coordinate p1) {
/* 107 */     if (this.isNull) {
/* 108 */       initialize(p0, p1);
/*     */       return;
/*     */     } 
/* 111 */     double dist = p0.distance(p1);
/* 112 */     if (dist < this.distance)
/* 113 */       initialize(p0, p1, dist); 
/*     */   }
/*     */   
/*     */   public String toString() {
/* 118 */     return WKTWriter.toLineString(this.pt[0], this.pt[1]);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\algorithm\distance\PointPairDistance.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
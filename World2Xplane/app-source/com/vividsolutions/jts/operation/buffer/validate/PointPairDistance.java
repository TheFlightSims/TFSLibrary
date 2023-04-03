/*     */ package com.vividsolutions.jts.operation.buffer.validate;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ 
/*     */ public class PointPairDistance {
/*  44 */   private Coordinate[] pt = new Coordinate[] { new Coordinate(), new Coordinate() };
/*     */   
/*  45 */   private double distance = Double.NaN;
/*     */   
/*     */   private boolean isNull = true;
/*     */   
/*     */   public void initialize() {
/*  52 */     this.isNull = true;
/*     */   }
/*     */   
/*     */   public void initialize(Coordinate p0, Coordinate p1) {
/*  56 */     this.pt[0].setCoordinate(p0);
/*  57 */     this.pt[1].setCoordinate(p1);
/*  58 */     this.distance = p0.distance(p1);
/*  59 */     this.isNull = false;
/*     */   }
/*     */   
/*     */   private void initialize(Coordinate p0, Coordinate p1, double distance) {
/*  70 */     this.pt[0].setCoordinate(p0);
/*  71 */     this.pt[1].setCoordinate(p1);
/*  72 */     this.distance = distance;
/*  73 */     this.isNull = false;
/*     */   }
/*     */   
/*     */   public double getDistance() {
/*  76 */     return this.distance;
/*     */   }
/*     */   
/*     */   public Coordinate[] getCoordinates() {
/*  78 */     return this.pt;
/*     */   }
/*     */   
/*     */   public Coordinate getCoordinate(int i) {
/*  80 */     return this.pt[i];
/*     */   }
/*     */   
/*     */   public void setMaximum(PointPairDistance ptDist) {
/*  84 */     setMaximum(ptDist.pt[0], ptDist.pt[1]);
/*     */   }
/*     */   
/*     */   public void setMaximum(Coordinate p0, Coordinate p1) {
/*  89 */     if (this.isNull) {
/*  90 */       initialize(p0, p1);
/*     */       return;
/*     */     } 
/*  93 */     double dist = p0.distance(p1);
/*  94 */     if (dist > this.distance)
/*  95 */       initialize(p0, p1, dist); 
/*     */   }
/*     */   
/*     */   public void setMinimum(PointPairDistance ptDist) {
/* 100 */     setMinimum(ptDist.pt[0], ptDist.pt[1]);
/*     */   }
/*     */   
/*     */   public void setMinimum(Coordinate p0, Coordinate p1) {
/* 105 */     if (this.isNull) {
/* 106 */       initialize(p0, p1);
/*     */       return;
/*     */     } 
/* 109 */     double dist = p0.distance(p1);
/* 110 */     if (dist < this.distance)
/* 111 */       initialize(p0, p1, dist); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\buffer\validate\PointPairDistance.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
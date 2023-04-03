/*     */ package org.jfree.chart.plot;
/*     */ 
/*     */ import java.awt.geom.Point2D;
/*     */ 
/*     */ public class CrosshairState {
/*     */   private boolean calculateDistanceInDataSpace = false;
/*     */   
/*     */   private double anchorX;
/*     */   
/*     */   private double anchorY;
/*     */   
/*     */   private Point2D anchor;
/*     */   
/*     */   private double crosshairX;
/*     */   
/*     */   private double crosshairY;
/*     */   
/*     */   private double distance;
/*     */   
/*     */   public CrosshairState() {
/*  89 */     this(false);
/*     */   }
/*     */   
/*     */   public CrosshairState(boolean calculateDistanceInDataSpace) {
/* 100 */     this.calculateDistanceInDataSpace = calculateDistanceInDataSpace;
/*     */   }
/*     */   
/*     */   public void setCrosshairDistance(double distance) {
/* 112 */     this.distance = distance;
/*     */   }
/*     */   
/*     */   public void updateCrosshairPoint(double x, double y, double transX, double transY, PlotOrientation orientation) {
/* 135 */     if (this.anchor != null) {
/* 136 */       double d = 0.0D;
/* 137 */       if (this.calculateDistanceInDataSpace) {
/* 138 */         d = (x - this.anchorX) * (x - this.anchorX) + (y - this.anchorY) * (y - this.anchorY);
/*     */       } else {
/* 142 */         double xx = this.anchor.getX();
/* 143 */         double yy = this.anchor.getY();
/* 144 */         if (orientation == PlotOrientation.HORIZONTAL) {
/* 145 */           double temp = yy;
/* 146 */           yy = xx;
/* 147 */           xx = temp;
/*     */         } 
/* 149 */         d = (transX - xx) * (transX - xx) + (transY - yy) * (transY - yy);
/*     */       } 
/* 153 */       if (d < this.distance) {
/* 154 */         this.crosshairX = x;
/* 155 */         this.crosshairY = y;
/* 156 */         this.distance = d;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void updateCrosshairX(double candidateX) {
/* 173 */     double d = Math.abs(candidateX - this.anchorX);
/* 174 */     if (d < this.distance) {
/* 175 */       this.crosshairX = candidateX;
/* 176 */       this.distance = d;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void updateCrosshairY(double candidateY) {
/* 192 */     double d = Math.abs(candidateY - this.anchorY);
/* 193 */     if (d < this.distance) {
/* 194 */       this.crosshairY = candidateY;
/* 195 */       this.distance = d;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setAnchor(Point2D anchor) {
/* 208 */     this.anchor = anchor;
/*     */   }
/*     */   
/*     */   public double getCrosshairX() {
/* 217 */     return this.crosshairX;
/*     */   }
/*     */   
/*     */   public void setCrosshairX(double x) {
/* 227 */     this.crosshairX = x;
/*     */   }
/*     */   
/*     */   public double getCrosshairY() {
/* 237 */     return this.crosshairY;
/*     */   }
/*     */   
/*     */   public void setCrosshairY(double y) {
/* 246 */     this.crosshairY = y;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\plot\CrosshairState.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */
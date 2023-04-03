/*     */ package com.vividsolutions.jts.algorithm;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ 
/*     */ public class RectangleLineIntersector {
/*  55 */   private LineIntersector li = new RobustLineIntersector();
/*     */   
/*     */   private Envelope rectEnv;
/*     */   
/*     */   private Coordinate diagUp0;
/*     */   
/*     */   private Coordinate diagUp1;
/*     */   
/*     */   private Coordinate diagDown0;
/*     */   
/*     */   private Coordinate diagDown1;
/*     */   
/*     */   public RectangleLineIntersector(Envelope rectEnv) {
/*  73 */     this.rectEnv = rectEnv;
/*  80 */     this.diagUp0 = new Coordinate(rectEnv.getMinX(), rectEnv.getMinY());
/*  81 */     this.diagUp1 = new Coordinate(rectEnv.getMaxX(), rectEnv.getMaxY());
/*  82 */     this.diagDown0 = new Coordinate(rectEnv.getMinX(), rectEnv.getMaxY());
/*  83 */     this.diagDown1 = new Coordinate(rectEnv.getMaxX(), rectEnv.getMinY());
/*     */   }
/*     */   
/*     */   public boolean intersects(Coordinate p0, Coordinate p1) {
/* 102 */     Envelope segEnv = new Envelope(p0, p1);
/* 103 */     if (!this.rectEnv.intersects(segEnv))
/* 104 */       return false; 
/* 110 */     if (this.rectEnv.intersects(p0))
/* 110 */       return true; 
/* 111 */     if (this.rectEnv.intersects(p1))
/* 111 */       return true; 
/* 119 */     if (p0.compareTo(p1) > 0) {
/* 120 */       Coordinate tmp = p0;
/* 121 */       p0 = p1;
/* 122 */       p1 = tmp;
/*     */     } 
/* 130 */     boolean isSegUpwards = false;
/* 131 */     if (p1.y > p0.y)
/* 132 */       isSegUpwards = true; 
/* 153 */     if (isSegUpwards) {
/* 154 */       this.li.computeIntersection(p0, p1, this.diagDown0, this.diagDown1);
/*     */     } else {
/* 157 */       this.li.computeIntersection(p0, p1, this.diagUp0, this.diagUp1);
/*     */     } 
/* 159 */     if (this.li.hasIntersection())
/* 160 */       return true; 
/* 161 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\algorithm\RectangleLineIntersector.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
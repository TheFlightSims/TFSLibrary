/*     */ package com.vividsolutions.jts.algorithm;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*     */ 
/*     */ public class RayCrossingCounter {
/*     */   private Coordinate p;
/*     */   
/*     */   public static int locatePointInRing(Coordinate p, Coordinate[] ring) {
/*  75 */     RayCrossingCounter counter = new RayCrossingCounter(p);
/*  77 */     for (int i = 1; i < ring.length; i++) {
/*  78 */       Coordinate p1 = ring[i];
/*  79 */       Coordinate p2 = ring[i - 1];
/*  80 */       counter.countSegment(p1, p2);
/*  81 */       if (counter.isOnSegment())
/*  82 */         return counter.getLocation(); 
/*     */     } 
/*  84 */     return counter.getLocation();
/*     */   }
/*     */   
/*     */   public static int locatePointInRing(Coordinate p, CoordinateSequence ring) {
/*  97 */     RayCrossingCounter counter = new RayCrossingCounter(p);
/*  99 */     Coordinate p1 = new Coordinate();
/* 100 */     Coordinate p2 = new Coordinate();
/* 101 */     for (int i = 1; i < ring.size(); i++) {
/* 102 */       ring.getCoordinate(i, p1);
/* 103 */       ring.getCoordinate(i - 1, p2);
/* 104 */       counter.countSegment(p1, p2);
/* 105 */       if (counter.isOnSegment())
/* 106 */         return counter.getLocation(); 
/*     */     } 
/* 108 */     return counter.getLocation();
/*     */   }
/*     */   
/* 112 */   private int crossingCount = 0;
/*     */   
/*     */   private boolean isPointOnSegment = false;
/*     */   
/*     */   public RayCrossingCounter(Coordinate p) {
/* 118 */     this.p = p;
/*     */   }
/*     */   
/*     */   public void countSegment(Coordinate p1, Coordinate p2) {
/* 134 */     if (p1.x < this.p.x && p2.x < this.p.x)
/*     */       return; 
/* 138 */     if (this.p.x == p2.x && this.p.y == p2.y) {
/* 139 */       this.isPointOnSegment = true;
/*     */       return;
/*     */     } 
/* 146 */     if (p1.y == this.p.y && p2.y == this.p.y) {
/* 147 */       double minx = p1.x;
/* 148 */       double maxx = p2.x;
/* 149 */       if (minx > maxx) {
/* 150 */         minx = p2.x;
/* 151 */         maxx = p1.x;
/*     */       } 
/* 153 */       if (this.p.x >= minx && this.p.x <= maxx)
/* 154 */         this.isPointOnSegment = true; 
/*     */       return;
/*     */     } 
/* 169 */     if ((p1.y > this.p.y && p2.y <= this.p.y) || (p2.y > this.p.y && p1.y <= this.p.y)) {
/* 172 */       double x1 = p1.x - this.p.x;
/* 173 */       double y1 = p1.y - this.p.y;
/* 174 */       double x2 = p2.x - this.p.x;
/* 175 */       double y2 = p2.y - this.p.y;
/* 185 */       double xIntSign = RobustDeterminant.signOfDet2x2(x1, y1, x2, y2);
/* 186 */       if (xIntSign == 0.0D) {
/* 187 */         this.isPointOnSegment = true;
/*     */         return;
/*     */       } 
/* 190 */       if (y2 < y1)
/* 191 */         xIntSign = -xIntSign; 
/* 196 */       if (xIntSign > 0.0D)
/* 197 */         this.crossingCount++; 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isOnSegment() {
/* 211 */     return this.isPointOnSegment;
/*     */   }
/*     */   
/*     */   public int getLocation() {
/* 225 */     if (this.isPointOnSegment)
/* 226 */       return 1; 
/* 230 */     if (this.crossingCount % 2 == 1)
/* 231 */       return 0; 
/* 233 */     return 2;
/*     */   }
/*     */   
/*     */   public boolean isPointInPolygon() {
/* 248 */     return (getLocation() != 2);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\algorithm\RayCrossingCounter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
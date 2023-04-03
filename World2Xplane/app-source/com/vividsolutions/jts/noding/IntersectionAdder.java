/*     */ package com.vividsolutions.jts.noding;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.LineIntersector;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ 
/*     */ public class IntersectionAdder implements SegmentIntersector {
/*     */   public static boolean isAdjacentSegments(int i1, int i2) {
/*  50 */     return (Math.abs(i1 - i2) == 1);
/*     */   }
/*     */   
/*     */   private boolean hasIntersection = false;
/*     */   
/*     */   private boolean hasProper = false;
/*     */   
/*     */   private boolean hasProperInterior = false;
/*     */   
/*     */   private boolean hasInterior = false;
/*     */   
/*  63 */   private Coordinate properIntersectionPoint = null;
/*     */   
/*     */   private LineIntersector li;
/*     */   
/*     */   private boolean isSelfIntersection;
/*     */   
/*  68 */   public int numIntersections = 0;
/*     */   
/*  69 */   public int numInteriorIntersections = 0;
/*     */   
/*  70 */   public int numProperIntersections = 0;
/*     */   
/*  73 */   public int numTests = 0;
/*     */   
/*     */   public IntersectionAdder(LineIntersector li) {
/*  77 */     this.li = li;
/*     */   }
/*     */   
/*     */   public LineIntersector getLineIntersector() {
/*  80 */     return this.li;
/*     */   }
/*     */   
/*     */   public Coordinate getProperIntersectionPoint() {
/*  85 */     return this.properIntersectionPoint;
/*     */   }
/*     */   
/*     */   public boolean hasIntersection() {
/*  87 */     return this.hasIntersection;
/*     */   }
/*     */   
/*     */   public boolean hasProperIntersection() {
/*  95 */     return this.hasProper;
/*     */   }
/*     */   
/*     */   public boolean hasProperInteriorIntersection() {
/* 100 */     return this.hasProperInterior;
/*     */   }
/*     */   
/*     */   public boolean hasInteriorIntersection() {
/* 105 */     return this.hasInterior;
/*     */   }
/*     */   
/*     */   private boolean isTrivialIntersection(SegmentString e0, int segIndex0, SegmentString e1, int segIndex1) {
/* 115 */     if (e0 == e1 && 
/* 116 */       this.li.getIntersectionNum() == 1) {
/* 117 */       if (isAdjacentSegments(segIndex0, segIndex1))
/* 118 */         return true; 
/* 119 */       if (e0.isClosed()) {
/* 120 */         int maxSegIndex = e0.size() - 1;
/* 121 */         if ((segIndex0 == 0 && segIndex1 == maxSegIndex) || (segIndex1 == 0 && segIndex0 == maxSegIndex))
/* 123 */           return true; 
/*     */       } 
/*     */     } 
/* 128 */     return false;
/*     */   }
/*     */   
/*     */   public void processIntersections(SegmentString e0, int segIndex0, SegmentString e1, int segIndex1) {
/* 144 */     if (e0 == e1 && segIndex0 == segIndex1)
/*     */       return; 
/* 145 */     this.numTests++;
/* 146 */     Coordinate p00 = e0.getCoordinates()[segIndex0];
/* 147 */     Coordinate p01 = e0.getCoordinates()[segIndex0 + 1];
/* 148 */     Coordinate p10 = e1.getCoordinates()[segIndex1];
/* 149 */     Coordinate p11 = e1.getCoordinates()[segIndex1 + 1];
/* 151 */     this.li.computeIntersection(p00, p01, p10, p11);
/* 153 */     if (this.li.hasIntersection()) {
/* 155 */       this.numIntersections++;
/* 156 */       if (this.li.isInteriorIntersection()) {
/* 157 */         this.numInteriorIntersections++;
/* 158 */         this.hasInterior = true;
/*     */       } 
/* 164 */       if (!isTrivialIntersection(e0, segIndex0, e1, segIndex1)) {
/* 165 */         this.hasIntersection = true;
/* 166 */         ((NodedSegmentString)e0).addIntersections(this.li, segIndex0, 0);
/* 167 */         ((NodedSegmentString)e1).addIntersections(this.li, segIndex1, 1);
/* 168 */         if (this.li.isProper()) {
/* 169 */           this.numProperIntersections++;
/* 172 */           this.hasProper = true;
/* 173 */           this.hasProperInterior = true;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isDone() {
/* 184 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\noding\IntersectionAdder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
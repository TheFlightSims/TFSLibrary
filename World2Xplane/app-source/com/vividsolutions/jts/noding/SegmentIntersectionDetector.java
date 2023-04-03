/*     */ package com.vividsolutions.jts.noding;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.LineIntersector;
/*     */ import com.vividsolutions.jts.algorithm.RobustLineIntersector;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ 
/*     */ public class SegmentIntersectionDetector implements SegmentIntersector {
/*     */   private LineIntersector li;
/*     */   
/*     */   private boolean findProper = false;
/*     */   
/*     */   private boolean findAllTypes = false;
/*     */   
/*     */   private boolean hasIntersection = false;
/*     */   
/*     */   private boolean hasProperIntersection = false;
/*     */   
/*     */   private boolean hasNonProperIntersection = false;
/*     */   
/*  60 */   private Coordinate intPt = null;
/*     */   
/*  61 */   private Coordinate[] intSegments = null;
/*     */   
/*     */   public SegmentIntersectionDetector() {
/*  68 */     this((LineIntersector)new RobustLineIntersector());
/*     */   }
/*     */   
/*     */   public SegmentIntersectionDetector(LineIntersector li) {
/*  78 */     this.li = li;
/*     */   }
/*     */   
/*     */   public void setFindProper(boolean findProper) {
/*  88 */     this.findProper = findProper;
/*     */   }
/*     */   
/*     */   public void setFindAllIntersectionTypes(boolean findAllTypes) {
/*  98 */     this.findAllTypes = findAllTypes;
/*     */   }
/*     */   
/*     */   public boolean hasIntersection() {
/* 108 */     return this.hasIntersection;
/*     */   }
/*     */   
/*     */   public boolean hasProperIntersection() {
/* 118 */     return this.hasProperIntersection;
/*     */   }
/*     */   
/*     */   public boolean hasNonProperIntersection() {
/* 128 */     return this.hasNonProperIntersection;
/*     */   }
/*     */   
/*     */   public Coordinate getIntersection() {
/* 139 */     return this.intPt;
/*     */   }
/*     */   
/*     */   public Coordinate[] getIntersectionSegments() {
/* 150 */     return this.intSegments;
/*     */   }
/*     */   
/*     */   public void processIntersections(SegmentString e0, int segIndex0, SegmentString e1, int segIndex1) {
/* 167 */     if (e0 == e1 && segIndex0 == segIndex1)
/*     */       return; 
/* 169 */     Coordinate p00 = e0.getCoordinates()[segIndex0];
/* 170 */     Coordinate p01 = e0.getCoordinates()[segIndex0 + 1];
/* 171 */     Coordinate p10 = e1.getCoordinates()[segIndex1];
/* 172 */     Coordinate p11 = e1.getCoordinates()[segIndex1 + 1];
/* 174 */     this.li.computeIntersection(p00, p01, p10, p11);
/* 177 */     if (this.li.hasIntersection()) {
/* 181 */       this.hasIntersection = true;
/* 183 */       boolean isProper = this.li.isProper();
/* 184 */       if (isProper)
/* 185 */         this.hasProperIntersection = true; 
/* 186 */       if (!isProper)
/* 187 */         this.hasNonProperIntersection = true; 
/* 194 */       boolean saveLocation = true;
/* 195 */       if (this.findProper && !isProper)
/* 195 */         saveLocation = false; 
/* 197 */       if (this.intPt == null || saveLocation) {
/* 200 */         this.intPt = this.li.getIntersection(0);
/* 203 */         this.intSegments = new Coordinate[4];
/* 204 */         this.intSegments[0] = p00;
/* 205 */         this.intSegments[1] = p01;
/* 206 */         this.intSegments[2] = p10;
/* 207 */         this.intSegments[3] = p11;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isDone() {
/* 225 */     if (this.findAllTypes)
/* 226 */       return (this.hasProperIntersection && this.hasNonProperIntersection); 
/* 232 */     if (this.findProper)
/* 233 */       return this.hasProperIntersection; 
/* 235 */     return this.hasIntersection;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\noding\SegmentIntersectionDetector.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
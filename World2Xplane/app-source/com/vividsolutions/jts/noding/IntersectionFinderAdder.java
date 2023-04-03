/*     */ package com.vividsolutions.jts.noding;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.LineIntersector;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class IntersectionFinderAdder implements SegmentIntersector {
/*     */   private LineIntersector li;
/*     */   
/*     */   private final List interiorIntersections;
/*     */   
/*     */   public IntersectionFinderAdder(LineIntersector li) {
/*  66 */     this.li = li;
/*  67 */     this.interiorIntersections = new ArrayList();
/*     */   }
/*     */   
/*     */   public List getInteriorIntersections() {
/*  70 */     return this.interiorIntersections;
/*     */   }
/*     */   
/*     */   public void processIntersections(SegmentString e0, int segIndex0, SegmentString e1, int segIndex1) {
/*  86 */     if (e0 == e1 && segIndex0 == segIndex1)
/*     */       return; 
/*  88 */     Coordinate p00 = e0.getCoordinates()[segIndex0];
/*  89 */     Coordinate p01 = e0.getCoordinates()[segIndex0 + 1];
/*  90 */     Coordinate p10 = e1.getCoordinates()[segIndex1];
/*  91 */     Coordinate p11 = e1.getCoordinates()[segIndex1 + 1];
/*  93 */     this.li.computeIntersection(p00, p01, p10, p11);
/*  96 */     if (this.li.hasIntersection() && 
/*  97 */       this.li.isInteriorIntersection()) {
/*  98 */       for (int intIndex = 0; intIndex < this.li.getIntersectionNum(); intIndex++)
/*  99 */         this.interiorIntersections.add(this.li.getIntersection(intIndex)); 
/* 101 */       ((NodedSegmentString)e0).addIntersections(this.li, segIndex0, 0);
/* 102 */       ((NodedSegmentString)e1).addIntersections(this.li, segIndex1, 1);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isDone() {
/* 112 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\noding\IntersectionFinderAdder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
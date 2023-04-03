/*     */ package com.vividsolutions.jts.noding;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.LineIntersector;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class InteriorIntersectionFinderAdder implements SegmentIntersector {
/*     */   private LineIntersector li;
/*     */   
/*     */   private final List interiorIntersections;
/*     */   
/*     */   public InteriorIntersectionFinderAdder(LineIntersector li) {
/*  65 */     this.li = li;
/*  66 */     this.interiorIntersections = new ArrayList();
/*     */   }
/*     */   
/*     */   public List getInteriorIntersections() {
/*  69 */     return this.interiorIntersections;
/*     */   }
/*     */   
/*     */   public void processIntersections(SegmentString e0, int segIndex0, SegmentString e1, int segIndex1) {
/*  85 */     if (e0 == e1 && segIndex0 == segIndex1)
/*     */       return; 
/*  87 */     Coordinate p00 = e0.getCoordinates()[segIndex0];
/*  88 */     Coordinate p01 = e0.getCoordinates()[segIndex0 + 1];
/*  89 */     Coordinate p10 = e1.getCoordinates()[segIndex1];
/*  90 */     Coordinate p11 = e1.getCoordinates()[segIndex1 + 1];
/*  92 */     this.li.computeIntersection(p00, p01, p10, p11);
/*  95 */     if (this.li.hasIntersection() && 
/*  96 */       this.li.isInteriorIntersection()) {
/*  97 */       for (int intIndex = 0; intIndex < this.li.getIntersectionNum(); intIndex++)
/*  98 */         this.interiorIntersections.add(this.li.getIntersection(intIndex)); 
/* 100 */       ((NodedSegmentString)e0).addIntersections(this.li, segIndex0, 0);
/* 101 */       ((NodedSegmentString)e1).addIntersections(this.li, segIndex1, 1);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isDone() {
/* 111 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\noding\InteriorIntersectionFinderAdder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
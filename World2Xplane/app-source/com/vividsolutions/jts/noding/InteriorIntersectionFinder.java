/*     */ package com.vividsolutions.jts.noding;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.LineIntersector;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class InteriorIntersectionFinder implements SegmentIntersector {
/*     */   private boolean findAllIntersections = false;
/*     */   
/*     */   private boolean isCheckEndSegmentsOnly = false;
/*     */   
/*     */   private LineIntersector li;
/*     */   
/*  54 */   private Coordinate interiorIntersection = null;
/*     */   
/*  55 */   private Coordinate[] intSegments = null;
/*     */   
/*  56 */   private List intersections = new ArrayList();
/*     */   
/*     */   public InteriorIntersectionFinder(LineIntersector li) {
/*  66 */     this.li = li;
/*  67 */     this.interiorIntersection = null;
/*     */   }
/*     */   
/*     */   public void setFindAllIntersections(boolean findAllIntersections) {
/*  72 */     this.findAllIntersections = findAllIntersections;
/*     */   }
/*     */   
/*     */   public List getIntersections() {
/*  77 */     return this.intersections;
/*     */   }
/*     */   
/*     */   public void setCheckEndSegmentsOnly(boolean isCheckEndSegmentsOnly) {
/*  91 */     this.isCheckEndSegmentsOnly = isCheckEndSegmentsOnly;
/*     */   }
/*     */   
/*     */   public boolean hasIntersection() {
/* 101 */     return (this.interiorIntersection != null);
/*     */   }
/*     */   
/*     */   public Coordinate getInteriorIntersection() {
/* 112 */     return this.interiorIntersection;
/*     */   }
/*     */   
/*     */   public Coordinate[] getIntersectionSegments() {
/* 122 */     return this.intSegments;
/*     */   }
/*     */   
/*     */   public void processIntersections(SegmentString e0, int segIndex0, SegmentString e1, int segIndex1) {
/* 139 */     if (hasIntersection())
/*     */       return; 
/* 143 */     if (e0 == e1 && segIndex0 == segIndex1)
/*     */       return; 
/* 149 */     if (this.isCheckEndSegmentsOnly) {
/* 150 */       boolean isEndSegPresent = (isEndSegment(e0, segIndex0) || isEndSegment(e1, segIndex1));
/* 151 */       if (!isEndSegPresent)
/*     */         return; 
/*     */     } 
/* 155 */     Coordinate p00 = e0.getCoordinates()[segIndex0];
/* 156 */     Coordinate p01 = e0.getCoordinates()[segIndex0 + 1];
/* 157 */     Coordinate p10 = e1.getCoordinates()[segIndex1];
/* 158 */     Coordinate p11 = e1.getCoordinates()[segIndex1 + 1];
/* 160 */     this.li.computeIntersection(p00, p01, p10, p11);
/* 163 */     if (this.li.hasIntersection() && 
/* 164 */       this.li.isInteriorIntersection()) {
/* 165 */       this.intSegments = new Coordinate[4];
/* 166 */       this.intSegments[0] = p00;
/* 167 */       this.intSegments[1] = p01;
/* 168 */       this.intSegments[2] = p10;
/* 169 */       this.intSegments[3] = p11;
/* 171 */       this.interiorIntersection = this.li.getIntersection(0);
/* 172 */       this.intersections.add(this.interiorIntersection);
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean isEndSegment(SegmentString segStr, int index) {
/* 187 */     if (index == 0)
/* 187 */       return true; 
/* 188 */     if (index >= segStr.size() - 2)
/* 188 */       return true; 
/* 189 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isDone() {
/* 194 */     if (this.findAllIntersections)
/* 194 */       return false; 
/* 195 */     return (this.interiorIntersection != null);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\noding\InteriorIntersectionFinder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
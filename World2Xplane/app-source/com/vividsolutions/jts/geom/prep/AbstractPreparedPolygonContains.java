/*     */ package com.vividsolutions.jts.geom.prep;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import com.vividsolutions.jts.noding.SegmentIntersectionDetector;
/*     */ import com.vividsolutions.jts.noding.SegmentStringUtil;
/*     */ import java.util.List;
/*     */ 
/*     */ abstract class AbstractPreparedPolygonContains extends PreparedPolygonPredicate {
/*     */   protected boolean requireSomePointInInterior = true;
/*     */   
/*     */   private boolean hasSegmentIntersection = false;
/*     */   
/*     */   private boolean hasProperIntersection = false;
/*     */   
/*     */   private boolean hasNonProperIntersection = false;
/*     */   
/*     */   public AbstractPreparedPolygonContains(PreparedPolygon prepPoly) {
/*  86 */     super(prepPoly);
/*     */   }
/*     */   
/*     */   protected boolean eval(Geometry geom) {
/* 104 */     boolean isAllInTargetArea = isAllTestComponentsInTarget(geom);
/* 105 */     if (!isAllInTargetArea)
/* 105 */       return false; 
/* 115 */     if (this.requireSomePointInInterior && geom.getDimension() == 0) {
/* 117 */       boolean isAnyInTargetInterior = isAnyTestComponentInTargetInterior(geom);
/* 118 */       return isAnyInTargetInterior;
/*     */     } 
/* 135 */     boolean properIntersectionImpliesNotContained = isProperIntersectionImpliesNotContainedSituation(geom);
/* 140 */     findAndClassifyIntersections(geom);
/* 142 */     if (properIntersectionImpliesNotContained && this.hasProperIntersection)
/* 143 */       return false; 
/* 159 */     if (this.hasSegmentIntersection && !this.hasNonProperIntersection)
/* 160 */       return false; 
/* 168 */     if (this.hasSegmentIntersection)
/* 169 */       return fullTopologicalPredicate(geom); 
/* 178 */     if (geom instanceof com.vividsolutions.jts.geom.Polygonal) {
/* 180 */       boolean isTargetInTestArea = isAnyTargetComponentInAreaTest(geom, this.prepPoly.getRepresentativePoints());
/* 181 */       if (isTargetInTestArea)
/* 181 */         return false; 
/*     */     } 
/* 183 */     return true;
/*     */   }
/*     */   
/*     */   private boolean isProperIntersectionImpliesNotContainedSituation(Geometry testGeom) {
/* 197 */     if (testGeom instanceof com.vividsolutions.jts.geom.Polygonal)
/* 197 */       return true; 
/* 203 */     if (isSingleShell(this.prepPoly.getGeometry()))
/* 203 */       return true; 
/* 204 */     return false;
/*     */   }
/*     */   
/*     */   private boolean isSingleShell(Geometry geom) {
/* 215 */     if (geom.getNumGeometries() != 1)
/* 215 */       return false; 
/* 217 */     Polygon poly = (Polygon)geom.getGeometryN(0);
/* 218 */     int numHoles = poly.getNumInteriorRing();
/* 219 */     if (numHoles == 0)
/* 219 */       return true; 
/* 220 */     return false;
/*     */   }
/*     */   
/*     */   private void findAndClassifyIntersections(Geometry geom) {
/* 225 */     List lineSegStr = SegmentStringUtil.extractSegmentStrings(geom);
/* 227 */     SegmentIntersectionDetector intDetector = new SegmentIntersectionDetector();
/* 228 */     intDetector.setFindAllIntersectionTypes(true);
/* 229 */     this.prepPoly.getIntersectionFinder().intersects(lineSegStr, intDetector);
/* 231 */     this.hasSegmentIntersection = intDetector.hasIntersection();
/* 232 */     this.hasProperIntersection = intDetector.hasProperIntersection();
/* 233 */     this.hasNonProperIntersection = intDetector.hasNonProperIntersection();
/*     */   }
/*     */   
/*     */   protected abstract boolean fullTopologicalPredicate(Geometry paramGeometry);
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geom\prep\AbstractPreparedPolygonContains.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
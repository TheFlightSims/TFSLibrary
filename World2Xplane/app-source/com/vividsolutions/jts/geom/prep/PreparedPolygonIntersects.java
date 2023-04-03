/*     */ package com.vividsolutions.jts.geom.prep;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.noding.SegmentStringUtil;
/*     */ import java.util.List;
/*     */ 
/*     */ class PreparedPolygonIntersects extends PreparedPolygonPredicate {
/*     */   public static boolean intersects(PreparedPolygon prep, Geometry geom) {
/*  60 */     PreparedPolygonIntersects polyInt = new PreparedPolygonIntersects(prep);
/*  61 */     return polyInt.intersects(geom);
/*     */   }
/*     */   
/*     */   public PreparedPolygonIntersects(PreparedPolygon prepPoly) {
/*  71 */     super(prepPoly);
/*     */   }
/*     */   
/*     */   public boolean intersects(Geometry geom) {
/*  88 */     boolean isInPrepGeomArea = isAnyTestComponentInTarget(geom);
/*  89 */     if (isInPrepGeomArea)
/*  90 */       return true; 
/*  95 */     if (geom.getDimension() == 0)
/*  96 */       return false; 
/* 100 */     List lineSegStr = SegmentStringUtil.extractSegmentStrings(geom);
/* 103 */     if (lineSegStr.size() > 0) {
/* 104 */       boolean segsIntersect = this.prepPoly.getIntersectionFinder().intersects(lineSegStr);
/* 106 */       if (segsIntersect)
/* 107 */         return true; 
/*     */     } 
/* 115 */     if (geom.getDimension() == 2) {
/* 117 */       boolean isPrepGeomInArea = isAnyTargetComponentInAreaTest(geom, this.prepPoly.getRepresentativePoints());
/* 119 */       if (isPrepGeomInArea)
/* 120 */         return true; 
/*     */     } 
/* 123 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geom\prep\PreparedPolygonIntersects.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
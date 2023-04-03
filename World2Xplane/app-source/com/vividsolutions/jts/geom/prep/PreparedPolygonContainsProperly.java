/*     */ package com.vividsolutions.jts.geom.prep;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.noding.SegmentStringUtil;
/*     */ import java.util.List;
/*     */ 
/*     */ class PreparedPolygonContainsProperly extends PreparedPolygonPredicate {
/*     */   public static boolean containsProperly(PreparedPolygon prep, Geometry geom) {
/*  73 */     PreparedPolygonContainsProperly polyInt = new PreparedPolygonContainsProperly(prep);
/*  74 */     return polyInt.containsProperly(geom);
/*     */   }
/*     */   
/*     */   public PreparedPolygonContainsProperly(PreparedPolygon prepPoly) {
/*  84 */     super(prepPoly);
/*     */   }
/*     */   
/*     */   public boolean containsProperly(Geometry geom) {
/* 101 */     boolean isAllInPrepGeomAreaInterior = isAllTestComponentsInTargetInterior(geom);
/* 102 */     if (!isAllInPrepGeomAreaInterior)
/* 102 */       return false; 
/* 107 */     List lineSegStr = SegmentStringUtil.extractSegmentStrings(geom);
/* 108 */     boolean segsIntersect = this.prepPoly.getIntersectionFinder().intersects(lineSegStr);
/* 109 */     if (segsIntersect)
/* 110 */       return false; 
/* 117 */     if (geom instanceof com.vividsolutions.jts.geom.Polygonal) {
/* 119 */       boolean isTargetGeomInTestArea = isAnyTargetComponentInAreaTest(geom, this.prepPoly.getRepresentativePoints());
/* 120 */       if (isTargetGeomInTestArea)
/* 120 */         return false; 
/*     */     } 
/* 123 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geom\prep\PreparedPolygonContainsProperly.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
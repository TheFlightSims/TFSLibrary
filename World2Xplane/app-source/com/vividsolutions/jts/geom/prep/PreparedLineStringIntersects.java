/*     */ package com.vividsolutions.jts.geom.prep;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.PointLocator;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.util.ComponentCoordinateExtracter;
/*     */ import com.vividsolutions.jts.noding.SegmentStringUtil;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ class PreparedLineStringIntersects {
/*     */   protected PreparedLineString prepLine;
/*     */   
/*     */   public static boolean intersects(PreparedLineString prep, Geometry geom) {
/*  63 */     PreparedLineStringIntersects op = new PreparedLineStringIntersects(prep);
/*  64 */     return op.intersects(geom);
/*     */   }
/*     */   
/*     */   public PreparedLineStringIntersects(PreparedLineString prepLine) {
/*  76 */     this.prepLine = prepLine;
/*     */   }
/*     */   
/*     */   public boolean intersects(Geometry geom) {
/*  90 */     List lineSegStr = SegmentStringUtil.extractSegmentStrings(geom);
/*  92 */     if (lineSegStr.size() > 0) {
/*  93 */       boolean segsIntersect = this.prepLine.getIntersectionFinder().intersects(lineSegStr);
/*  96 */       if (segsIntersect)
/*  97 */         return true; 
/*     */     } 
/* 102 */     if (geom.getDimension() == 1)
/* 102 */       return false; 
/* 107 */     if (geom.getDimension() == 2 && this.prepLine.isAnyTargetComponentInTest(geom))
/* 108 */       return true; 
/* 113 */     if (geom.getDimension() == 0)
/* 114 */       return isAnyTestPointInTarget(geom); 
/* 116 */     return false;
/*     */   }
/*     */   
/*     */   protected boolean isAnyTestPointInTarget(Geometry testGeom) {
/* 133 */     PointLocator locator = new PointLocator();
/* 134 */     List coords = ComponentCoordinateExtracter.getCoordinates(testGeom);
/* 135 */     for (Iterator<Coordinate> i = coords.iterator(); i.hasNext(); ) {
/* 136 */       Coordinate p = i.next();
/* 137 */       if (locator.intersects(p, this.prepLine.getGeometry()))
/* 138 */         return true; 
/*     */     } 
/* 140 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geom\prep\PreparedLineStringIntersects.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
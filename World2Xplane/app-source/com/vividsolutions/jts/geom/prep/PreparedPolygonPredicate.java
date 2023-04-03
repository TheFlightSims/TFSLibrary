/*     */ package com.vividsolutions.jts.geom.prep;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.locate.PointOnGeometryLocator;
/*     */ import com.vividsolutions.jts.algorithm.locate.SimplePointInAreaLocator;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.util.ComponentCoordinateExtracter;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ abstract class PreparedPolygonPredicate {
/*     */   protected PreparedPolygon prepPoly;
/*     */   
/*     */   private PointOnGeometryLocator targetPointLocator;
/*     */   
/*     */   public PreparedPolygonPredicate(PreparedPolygon prepPoly) {
/*  62 */     this.prepPoly = prepPoly;
/*  63 */     this.targetPointLocator = prepPoly.getPointLocator();
/*     */   }
/*     */   
/*     */   protected boolean isAllTestComponentsInTarget(Geometry testGeom) {
/*  76 */     List coords = ComponentCoordinateExtracter.getCoordinates(testGeom);
/*  77 */     for (Iterator<Coordinate> i = coords.iterator(); i.hasNext(); ) {
/*  78 */       Coordinate p = i.next();
/*  79 */       int loc = this.targetPointLocator.locate(p);
/*  80 */       if (loc == 2)
/*  81 */         return false; 
/*     */     } 
/*  83 */     return true;
/*     */   }
/*     */   
/*     */   protected boolean isAllTestComponentsInTargetInterior(Geometry testGeom) {
/*  96 */     List coords = ComponentCoordinateExtracter.getCoordinates(testGeom);
/*  97 */     for (Iterator<Coordinate> i = coords.iterator(); i.hasNext(); ) {
/*  98 */       Coordinate p = i.next();
/*  99 */       int loc = this.targetPointLocator.locate(p);
/* 100 */       if (loc != 0)
/* 101 */         return false; 
/*     */     } 
/* 103 */     return true;
/*     */   }
/*     */   
/*     */   protected boolean isAnyTestComponentInTarget(Geometry testGeom) {
/* 116 */     List coords = ComponentCoordinateExtracter.getCoordinates(testGeom);
/* 117 */     for (Iterator<Coordinate> i = coords.iterator(); i.hasNext(); ) {
/* 118 */       Coordinate p = i.next();
/* 119 */       int loc = this.targetPointLocator.locate(p);
/* 120 */       if (loc != 2)
/* 121 */         return true; 
/*     */     } 
/* 123 */     return false;
/*     */   }
/*     */   
/*     */   protected boolean isAnyTestComponentInTargetInterior(Geometry testGeom) {
/* 136 */     List coords = ComponentCoordinateExtracter.getCoordinates(testGeom);
/* 137 */     for (Iterator<Coordinate> i = coords.iterator(); i.hasNext(); ) {
/* 138 */       Coordinate p = i.next();
/* 139 */       int loc = this.targetPointLocator.locate(p);
/* 140 */       if (loc == 0)
/* 141 */         return true; 
/*     */     } 
/* 143 */     return false;
/*     */   }
/*     */   
/*     */   protected boolean isAnyTargetComponentInAreaTest(Geometry testGeom, List targetRepPts) {
/* 157 */     SimplePointInAreaLocator simplePointInAreaLocator = new SimplePointInAreaLocator(testGeom);
/* 158 */     for (Iterator<Coordinate> i = targetRepPts.iterator(); i.hasNext(); ) {
/* 159 */       Coordinate p = i.next();
/* 160 */       int loc = simplePointInAreaLocator.locate(p);
/* 161 */       if (loc != 2)
/* 162 */         return true; 
/*     */     } 
/* 164 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geom\prep\PreparedPolygonPredicate.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
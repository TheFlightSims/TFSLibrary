/*     */ package com.vividsolutions.jts.algorithm.locate;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.CGAlgorithms;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryCollectionIterator;
/*     */ import com.vividsolutions.jts.geom.LinearRing;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ 
/*     */ public class SimplePointInAreaLocator implements PointOnGeometryLocator {
/*     */   private Geometry geom;
/*     */   
/*     */   public static int locate(Coordinate p, Geometry geom) {
/*  66 */     if (geom.isEmpty())
/*  66 */       return 2; 
/*  68 */     if (containsPoint(p, geom))
/*  69 */       return 0; 
/*  70 */     return 2;
/*     */   }
/*     */   
/*     */   private static boolean containsPoint(Coordinate p, Geometry geom) {
/*  75 */     if (geom instanceof Polygon)
/*  76 */       return containsPointInPolygon(p, (Polygon)geom); 
/*  78 */     if (geom instanceof com.vividsolutions.jts.geom.GeometryCollection) {
/*  79 */       GeometryCollectionIterator<Geometry> geometryCollectionIterator = new GeometryCollectionIterator(geom);
/*  80 */       while (geometryCollectionIterator.hasNext()) {
/*  81 */         Geometry g2 = geometryCollectionIterator.next();
/*  82 */         if (g2 != geom && 
/*  83 */           containsPoint(p, g2))
/*  84 */           return true; 
/*     */       } 
/*     */     } 
/*  87 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean containsPointInPolygon(Coordinate p, Polygon poly) {
/*  92 */     if (poly.isEmpty())
/*  92 */       return false; 
/*  93 */     LinearRing shell = (LinearRing)poly.getExteriorRing();
/*  94 */     if (!isPointInRing(p, shell))
/*  94 */       return false; 
/*  96 */     for (int i = 0; i < poly.getNumInteriorRing(); i++) {
/*  97 */       LinearRing hole = (LinearRing)poly.getInteriorRingN(i);
/*  98 */       if (isPointInRing(p, hole))
/*  98 */         return false; 
/*     */     } 
/* 100 */     return true;
/*     */   }
/*     */   
/*     */   private static boolean isPointInRing(Coordinate p, LinearRing ring) {
/* 114 */     if (!ring.getEnvelopeInternal().intersects(p))
/* 115 */       return false; 
/* 116 */     return CGAlgorithms.isPointInRing(p, ring.getCoordinates());
/*     */   }
/*     */   
/*     */   public SimplePointInAreaLocator(Geometry geom) {
/* 122 */     this.geom = geom;
/*     */   }
/*     */   
/*     */   public int locate(Coordinate p) {
/* 126 */     return locate(p, this.geom);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\algorithm\locate\SimplePointInAreaLocator.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
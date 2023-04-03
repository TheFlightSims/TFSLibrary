/*    */ package com.vividsolutions.jts.algorithm.distance;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Coordinate;
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import com.vividsolutions.jts.geom.GeometryCollection;
/*    */ import com.vividsolutions.jts.geom.LineSegment;
/*    */ import com.vividsolutions.jts.geom.LineString;
/*    */ import com.vividsolutions.jts.geom.Polygon;
/*    */ 
/*    */ public class DistanceToPoint {
/*    */   public static void computeDistance(Geometry geom, Coordinate pt, PointPairDistance ptDist) {
/* 49 */     if (geom instanceof LineString) {
/* 50 */       computeDistance((LineString)geom, pt, ptDist);
/* 52 */     } else if (geom instanceof Polygon) {
/* 53 */       computeDistance((Polygon)geom, pt, ptDist);
/* 55 */     } else if (geom instanceof GeometryCollection) {
/* 56 */       GeometryCollection gc = (GeometryCollection)geom;
/* 57 */       for (int i = 0; i < gc.getNumGeometries(); i++) {
/* 58 */         Geometry g = gc.getGeometryN(i);
/* 59 */         computeDistance(g, pt, ptDist);
/*    */       } 
/*    */     } else {
/* 63 */       ptDist.setMinimum(geom.getCoordinate(), pt);
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void computeDistance(LineString line, Coordinate pt, PointPairDistance ptDist) {
/* 69 */     LineSegment tempSegment = new LineSegment();
/* 70 */     Coordinate[] coords = line.getCoordinates();
/* 71 */     for (int i = 0; i < coords.length - 1; i++) {
/* 72 */       tempSegment.setCoordinates(coords[i], coords[i + 1]);
/* 74 */       Coordinate closestPt = tempSegment.closestPoint(pt);
/* 75 */       ptDist.setMinimum(closestPt, pt);
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void computeDistance(LineSegment segment, Coordinate pt, PointPairDistance ptDist) {
/* 81 */     Coordinate closestPt = segment.closestPoint(pt);
/* 82 */     ptDist.setMinimum(closestPt, pt);
/*    */   }
/*    */   
/*    */   public static void computeDistance(Polygon poly, Coordinate pt, PointPairDistance ptDist) {
/* 87 */     computeDistance(poly.getExteriorRing(), pt, ptDist);
/* 88 */     for (int i = 0; i < poly.getNumInteriorRing(); i++)
/* 89 */       computeDistance(poly.getInteriorRingN(i), pt, ptDist); 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\algorithm\distance\DistanceToPoint.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
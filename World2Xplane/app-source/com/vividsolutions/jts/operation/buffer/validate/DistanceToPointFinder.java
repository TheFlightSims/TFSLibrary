/*    */ package com.vividsolutions.jts.operation.buffer.validate;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Coordinate;
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import com.vividsolutions.jts.geom.GeometryCollection;
/*    */ import com.vividsolutions.jts.geom.LineSegment;
/*    */ import com.vividsolutions.jts.geom.LineString;
/*    */ import com.vividsolutions.jts.geom.Polygon;
/*    */ 
/*    */ public class DistanceToPointFinder {
/*    */   public static void computeDistance(Geometry geom, Coordinate pt, PointPairDistance ptDist) {
/* 48 */     if (geom instanceof LineString) {
/* 49 */       computeDistance((LineString)geom, pt, ptDist);
/* 51 */     } else if (geom instanceof Polygon) {
/* 52 */       computeDistance((Polygon)geom, pt, ptDist);
/* 54 */     } else if (geom instanceof GeometryCollection) {
/* 55 */       GeometryCollection gc = (GeometryCollection)geom;
/* 56 */       for (int i = 0; i < gc.getNumGeometries(); i++) {
/* 57 */         Geometry g = gc.getGeometryN(i);
/* 58 */         computeDistance(g, pt, ptDist);
/*    */       } 
/*    */     } else {
/* 62 */       ptDist.setMinimum(geom.getCoordinate(), pt);
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void computeDistance(LineString line, Coordinate pt, PointPairDistance ptDist) {
/* 67 */     Coordinate[] coords = line.getCoordinates();
/* 68 */     LineSegment tempSegment = new LineSegment();
/* 69 */     for (int i = 0; i < coords.length - 1; i++) {
/* 70 */       tempSegment.setCoordinates(coords[i], coords[i + 1]);
/* 72 */       Coordinate closestPt = tempSegment.closestPoint(pt);
/* 73 */       ptDist.setMinimum(closestPt, pt);
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void computeDistance(LineSegment segment, Coordinate pt, PointPairDistance ptDist) {
/* 79 */     Coordinate closestPt = segment.closestPoint(pt);
/* 80 */     ptDist.setMinimum(closestPt, pt);
/*    */   }
/*    */   
/*    */   public static void computeDistance(Polygon poly, Coordinate pt, PointPairDistance ptDist) {
/* 85 */     computeDistance(poly.getExteriorRing(), pt, ptDist);
/* 86 */     for (int i = 0; i < poly.getNumInteriorRing(); i++)
/* 87 */       computeDistance(poly.getInteriorRingN(i), pt, ptDist); 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\buffer\validate\DistanceToPointFinder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
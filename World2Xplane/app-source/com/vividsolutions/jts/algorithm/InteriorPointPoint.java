/*    */ package com.vividsolutions.jts.algorithm;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Coordinate;
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import com.vividsolutions.jts.geom.GeometryCollection;
/*    */ 
/*    */ public class InteriorPointPoint {
/*    */   private Coordinate centroid;
/*    */   
/* 48 */   private double minDistance = Double.MAX_VALUE;
/*    */   
/* 50 */   private Coordinate interiorPoint = null;
/*    */   
/*    */   public InteriorPointPoint(Geometry g) {
/* 54 */     this.centroid = g.getCentroid().getCoordinate();
/* 55 */     add(g);
/*    */   }
/*    */   
/*    */   private void add(Geometry geom) {
/* 65 */     if (geom instanceof com.vividsolutions.jts.geom.Point) {
/* 66 */       add(geom.getCoordinate());
/* 68 */     } else if (geom instanceof GeometryCollection) {
/* 69 */       GeometryCollection gc = (GeometryCollection)geom;
/* 70 */       for (int i = 0; i < gc.getNumGeometries(); i++)
/* 71 */         add(gc.getGeometryN(i)); 
/*    */     } 
/*    */   }
/*    */   
/*    */   private void add(Coordinate point) {
/* 77 */     double dist = point.distance(this.centroid);
/* 78 */     if (dist < this.minDistance) {
/* 79 */       this.interiorPoint = new Coordinate(point);
/* 80 */       this.minDistance = dist;
/*    */     } 
/*    */   }
/*    */   
/*    */   public Coordinate getInteriorPoint() {
/* 86 */     return this.interiorPoint;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\algorithm\InteriorPointPoint.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
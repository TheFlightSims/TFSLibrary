/*    */ package com.vividsolutions.jts.operation.valid;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Coordinate;
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import com.vividsolutions.jts.geom.GeometryCollection;
/*    */ import com.vividsolutions.jts.geom.LineString;
/*    */ import com.vividsolutions.jts.geom.Polygon;
/*    */ 
/*    */ public class RepeatedPointTester {
/*    */   private Coordinate repeatedCoord;
/*    */   
/*    */   public Coordinate getCoordinate() {
/* 54 */     return this.repeatedCoord;
/*    */   }
/*    */   
/*    */   public boolean hasRepeatedPoint(Geometry g) {
/* 58 */     if (g.isEmpty())
/* 58 */       return false; 
/* 59 */     if (g instanceof com.vividsolutions.jts.geom.Point)
/* 59 */       return false; 
/* 60 */     if (g instanceof com.vividsolutions.jts.geom.MultiPoint)
/* 60 */       return false; 
/* 62 */     if (g instanceof LineString)
/* 62 */       return hasRepeatedPoint(((LineString)g).getCoordinates()); 
/* 63 */     if (g instanceof Polygon)
/* 63 */       return hasRepeatedPoint((Polygon)g); 
/* 64 */     if (g instanceof GeometryCollection)
/* 64 */       return hasRepeatedPoint((GeometryCollection)g); 
/* 65 */     throw new UnsupportedOperationException(g.getClass().getName());
/*    */   }
/*    */   
/*    */   public boolean hasRepeatedPoint(Coordinate[] coord) {
/* 70 */     for (int i = 1; i < coord.length; i++) {
/* 71 */       if (coord[i - 1].equals(coord[i])) {
/* 72 */         this.repeatedCoord = coord[i];
/* 73 */         return true;
/*    */       } 
/*    */     } 
/* 76 */     return false;
/*    */   }
/*    */   
/*    */   private boolean hasRepeatedPoint(Polygon p) {
/* 80 */     if (hasRepeatedPoint(p.getExteriorRing().getCoordinates()))
/* 80 */       return true; 
/* 81 */     for (int i = 0; i < p.getNumInteriorRing(); i++) {
/* 82 */       if (hasRepeatedPoint(p.getInteriorRingN(i).getCoordinates()))
/* 82 */         return true; 
/*    */     } 
/* 84 */     return false;
/*    */   }
/*    */   
/*    */   private boolean hasRepeatedPoint(GeometryCollection gc) {
/* 88 */     for (int i = 0; i < gc.getNumGeometries(); i++) {
/* 89 */       Geometry g = gc.getGeometryN(i);
/* 90 */       if (hasRepeatedPoint(g))
/* 90 */         return true; 
/*    */     } 
/* 92 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\valid\RepeatedPointTester.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
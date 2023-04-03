/*    */ package com.vividsolutions.jts.algorithm;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Coordinate;
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import com.vividsolutions.jts.geom.GeometryCollection;
/*    */ 
/*    */ public class CentroidPoint {
/* 48 */   private int ptCount = 0;
/*    */   
/* 49 */   private Coordinate centSum = new Coordinate();
/*    */   
/*    */   public void add(Geometry geom) {
/* 62 */     if (geom instanceof com.vividsolutions.jts.geom.Point) {
/* 63 */       add(geom.getCoordinate());
/* 65 */     } else if (geom instanceof GeometryCollection) {
/* 66 */       GeometryCollection gc = (GeometryCollection)geom;
/* 67 */       for (int i = 0; i < gc.getNumGeometries(); i++)
/* 68 */         add(gc.getGeometryN(i)); 
/*    */     } 
/*    */   }
/*    */   
/*    */   public void add(Coordinate pt) {
/* 79 */     this.ptCount++;
/* 80 */     this.centSum.x += pt.x;
/* 81 */     this.centSum.y += pt.y;
/*    */   }
/*    */   
/*    */   public Coordinate getCentroid() {
/* 86 */     Coordinate cent = new Coordinate();
/* 87 */     this.centSum.x /= this.ptCount;
/* 88 */     this.centSum.y /= this.ptCount;
/* 89 */     return cent;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\algorithm\CentroidPoint.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
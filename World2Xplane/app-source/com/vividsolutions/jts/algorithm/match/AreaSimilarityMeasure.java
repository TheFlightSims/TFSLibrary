/*    */ package com.vividsolutions.jts.algorithm.match;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ 
/*    */ public class AreaSimilarityMeasure implements SimilarityMeasure {
/*    */   public double measure(Geometry g1, Geometry g2) {
/* 67 */     double areaInt = g1.intersection(g2).getArea();
/* 68 */     double areaUnion = g1.union(g2).getArea();
/* 69 */     return areaInt / areaUnion;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\algorithm\match\AreaSimilarityMeasure.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package com.vividsolutions.jts.algorithm.match;
/*    */ 
/*    */ public class SimilarityMeasureCombiner {
/*    */   public static double combine(double measure1, double measure2) {
/* 46 */     return Math.min(measure1, measure2);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\algorithm\match\SimilarityMeasureCombiner.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
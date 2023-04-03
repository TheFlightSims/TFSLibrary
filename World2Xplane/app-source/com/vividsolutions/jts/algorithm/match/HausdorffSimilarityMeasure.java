/*    */ package com.vividsolutions.jts.algorithm.match;
/*    */ 
/*    */ import com.vividsolutions.jts.algorithm.distance.DiscreteHausdorffDistance;
/*    */ import com.vividsolutions.jts.geom.Envelope;
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ 
/*    */ public class HausdorffSimilarityMeasure implements SimilarityMeasure {
/*    */   private static final double DENSIFY_FRACTION = 0.25D;
/*    */   
/*    */   public double measure(Geometry g1, Geometry g2) {
/* 75 */     double distance = DiscreteHausdorffDistance.distance(g1, g2, 0.25D);
/* 77 */     Envelope env = new Envelope(g1.getEnvelopeInternal());
/* 78 */     env.expandToInclude(g2.getEnvelopeInternal());
/* 79 */     double envSize = diagonalSize(env);
/* 81 */     double measure = 1.0D - distance / envSize;
/* 84 */     return measure;
/*    */   }
/*    */   
/*    */   public static double diagonalSize(Envelope env) {
/* 89 */     if (env.isNull())
/* 89 */       return 0.0D; 
/* 91 */     double width = env.getWidth();
/* 92 */     double hgt = env.getHeight();
/* 93 */     return Math.sqrt(width * width + hgt * hgt);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\algorithm\match\HausdorffSimilarityMeasure.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
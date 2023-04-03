/*    */ package com.vividsolutions.jtsexample.geom.prep;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Coordinate;
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import com.vividsolutions.jts.geom.GeometryFactory;
/*    */ import com.vividsolutions.jts.geom.Point;
/*    */ import com.vividsolutions.jts.geom.prep.PreparedGeometry;
/*    */ import com.vividsolutions.jts.geom.prep.PreparedGeometryFactory;
/*    */ 
/*    */ public class PreparedGeometryExample {
/* 53 */   static GeometryFactory geomFact = new GeometryFactory();
/*    */   
/*    */   static final int MAX_ITER = 100000;
/*    */   
/*    */   public static void main(String[] args) throws Exception {
/* 60 */     Geometry circle = createCircle();
/* 61 */     PreparedGeometry prepCircle = PreparedGeometryFactory.prepare(circle);
/* 63 */     int count = 0;
/* 64 */     int inCount = 0;
/* 65 */     for (int i = 0; i < 100000; i++) {
/* 67 */       count++;
/* 68 */       Point randPt = createRandomPoint();
/* 69 */       if (prepCircle.intersects((Geometry)randPt))
/* 70 */         inCount++; 
/*    */     } 
/* 75 */     double approxPi = 4.0D * inCount / count;
/* 76 */     double approxDiffPct = 1.0D - approxPi / Math.PI;
/* 78 */     System.out.println("Approximation to PI: " + approxPi + "  ( % difference from actual = " + (100.0D * approxDiffPct) + " )");
/*    */   }
/*    */   
/*    */   static Geometry createCircle() {
/* 86 */     Point point = geomFact.createPoint(new Coordinate(0.5D, 0.5D));
/* 87 */     return point.buffer(0.5D, 20);
/*    */   }
/*    */   
/*    */   static Point createRandomPoint() {
/* 92 */     return geomFact.createPoint(new Coordinate(Math.random(), Math.random()));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jtsexample\geom\prep\PreparedGeometryExample.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
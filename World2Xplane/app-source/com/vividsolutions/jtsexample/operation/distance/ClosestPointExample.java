/*     */ package com.vividsolutions.jtsexample.operation.distance;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.io.WKTReader;
/*     */ import com.vividsolutions.jts.operation.distance.DistanceOp;
/*     */ 
/*     */ public class ClosestPointExample {
/*  48 */   static GeometryFactory fact = new GeometryFactory();
/*     */   
/*  49 */   static WKTReader wktRdr = new WKTReader(fact);
/*     */   
/*     */   public static void main(String[] args) {
/*  52 */     ClosestPointExample example = new ClosestPointExample();
/*  53 */     example.run();
/*     */   }
/*     */   
/*     */   public void run() {
/*  62 */     findClosestPoint("POLYGON ((200 180, 60 140, 60 260, 200 180))", "POINT (140 280)");
/*  65 */     findClosestPoint("POLYGON ((200 180, 60 140, 60 260, 200 180))", "MULTIPOINT (140 280, 140 320)");
/*  68 */     findClosestPoint("LINESTRING (100 100, 200 100, 200 200, 100 200, 100 100)", "POINT (10 10)");
/*  71 */     findClosestPoint("LINESTRING (100 100, 200 200)", "LINESTRING (100 200, 200 100)");
/*  74 */     findClosestPoint("LINESTRING (100 100, 200 200)", "LINESTRING (150 121, 200 0)");
/*  77 */     findClosestPoint("POLYGON (( 76 185, 125 283, 331 276, 324 122, 177 70, 184 155, 69 123, 76 185 ), ( 267 237, 148 248, 135 185, 223 189, 251 151, 286 183, 267 237 ))", "LINESTRING ( 153 204, 185 224, 209 207, 238 222, 254 186 )");
/*  80 */     findClosestPoint("POLYGON (( 76 185, 125 283, 331 276, 324 122, 177 70, 184 155, 69 123, 76 185 ), ( 267 237, 148 248, 135 185, 223 189, 251 151, 286 183, 267 237 ))", "LINESTRING ( 120 215, 185 224, 209 207, 238 222, 254 186 )");
/*     */   }
/*     */   
/*     */   public void findClosestPoint(String wktA, String wktB) {
/*  87 */     System.out.println("-------------------------------------");
/*     */     try {
/*  89 */       Geometry A = wktRdr.read(wktA);
/*  90 */       Geometry B = wktRdr.read(wktB);
/*  91 */       System.out.println("Geometry A: " + A);
/*  92 */       System.out.println("Geometry B: " + B);
/*  93 */       DistanceOp distOp = new DistanceOp(A, B);
/*  95 */       double distance = distOp.distance();
/*  96 */       System.out.println("Distance = " + distance);
/*  98 */       Coordinate[] closestPt = distOp.nearestPoints();
/*  99 */       LineString closestPtLine = fact.createLineString(closestPt);
/* 100 */       System.out.println("Closest points: " + closestPtLine + " (distance = " + closestPtLine.getLength() + ")");
/* 103 */     } catch (Exception ex) {
/* 104 */       ex.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jtsexample\operation\distance\ClosestPointExample.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
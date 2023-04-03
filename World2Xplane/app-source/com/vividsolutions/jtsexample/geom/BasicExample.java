/*    */ package com.vividsolutions.jtsexample.geom;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Coordinate;
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import com.vividsolutions.jts.geom.GeometryFactory;
/*    */ import com.vividsolutions.jts.geom.LineString;
/*    */ import com.vividsolutions.jts.io.WKTReader;
/*    */ 
/*    */ public class BasicExample {
/*    */   public static void main(String[] args) throws Exception {
/* 52 */     Geometry g1 = (new WKTReader()).read("LINESTRING (0 0, 10 10, 20 20)");
/* 53 */     System.out.println("Geometry 1: " + g1);
/* 56 */     Coordinate[] coordinates = { new Coordinate(0.0D, 0.0D), new Coordinate(10.0D, 10.0D), new Coordinate(20.0D, 20.0D) };
/* 59 */     LineString lineString = (new GeometryFactory()).createLineString(coordinates);
/* 60 */     System.out.println("Geometry 2: " + lineString);
/* 63 */     Geometry g3 = g1.intersection((Geometry)lineString);
/* 64 */     System.out.println("G1 intersection G2: " + g3);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jtsexample\geom\BasicExample.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package com.vividsolutions.jtsexample.technique;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Coordinate;
/*    */ import com.vividsolutions.jts.geom.CoordinateArrays;
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import com.vividsolutions.jts.geom.GeometryFactory;
/*    */ import com.vividsolutions.jts.geom.LineString;
/*    */ import com.vividsolutions.jts.geom.MultiLineString;
/*    */ import com.vividsolutions.jts.io.WKTReader;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class LineStringSelfIntersections {
/*    */   public static void main(String[] args) throws Exception {
/* 19 */     WKTReader rdr = new WKTReader();
/* 21 */     LineString line1 = (LineString)rdr.read("LINESTRING (0 0, 10 10, 20 20)");
/* 22 */     showSelfIntersections(line1);
/* 23 */     LineString line2 = (LineString)rdr.read("LINESTRING (0 40, 60 40, 60 0, 20 0, 20 60)");
/* 24 */     showSelfIntersections(line2);
/*    */   }
/*    */   
/*    */   public static void showSelfIntersections(LineString line) {
/* 30 */     System.out.println("Line: " + line);
/* 31 */     System.out.println("Self Intersections: " + lineStringSelfIntersections(line));
/*    */   }
/*    */   
/*    */   public static Geometry lineStringSelfIntersections(LineString line) {
/* 36 */     Geometry lineEndPts = getEndPoints((Geometry)line);
/* 37 */     Geometry nodedLine = line.union(lineEndPts);
/* 38 */     Geometry nodedEndPts = getEndPoints(nodedLine);
/* 39 */     Geometry selfIntersections = nodedEndPts.difference(lineEndPts);
/* 40 */     return selfIntersections;
/*    */   }
/*    */   
/*    */   public static Geometry getEndPoints(Geometry g) {
/* 45 */     List<Coordinate> endPtList = new ArrayList();
/* 46 */     if (g instanceof LineString) {
/* 47 */       LineString line = (LineString)g;
/* 49 */       endPtList.add(line.getCoordinateN(0));
/* 50 */       endPtList.add(line.getCoordinateN(line.getNumPoints() - 1));
/* 52 */     } else if (g instanceof MultiLineString) {
/* 53 */       MultiLineString mls = (MultiLineString)g;
/* 54 */       for (int i = 0; i < mls.getNumGeometries(); i++) {
/* 55 */         LineString line = (LineString)mls.getGeometryN(i);
/* 56 */         endPtList.add(line.getCoordinateN(0));
/* 57 */         endPtList.add(line.getCoordinateN(line.getNumPoints() - 1));
/*    */       } 
/*    */     } 
/* 60 */     Coordinate[] endPts = CoordinateArrays.toCoordinateArray(endPtList);
/* 61 */     return (Geometry)(new GeometryFactory()).createMultiPoint(endPts);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jtsexample\technique\LineStringSelfIntersections.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
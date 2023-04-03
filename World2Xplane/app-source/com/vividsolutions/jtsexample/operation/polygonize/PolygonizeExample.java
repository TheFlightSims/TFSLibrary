/*    */ package com.vividsolutions.jtsexample.operation.polygonize;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import com.vividsolutions.jts.io.WKTReader;
/*    */ import com.vividsolutions.jts.operation.polygonize.Polygonizer;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ 
/*    */ public class PolygonizeExample {
/*    */   public static void main(String[] args) throws Exception {
/* 50 */     PolygonizeExample test = new PolygonizeExample();
/*    */     try {
/* 52 */       test.run();
/* 54 */     } catch (Exception ex) {
/* 55 */       ex.printStackTrace();
/*    */     } 
/*    */   }
/*    */   
/*    */   void run() throws Exception {
/* 66 */     WKTReader rdr = new WKTReader();
/* 67 */     Collection<Geometry> lines = new ArrayList();
/* 69 */     lines.add(rdr.read("LINESTRING (0 0 , 10 10)"));
/* 70 */     lines.add(rdr.read("LINESTRING (185 221, 100 100)"));
/* 71 */     lines.add(rdr.read("LINESTRING (185 221, 88 275, 180 316)"));
/* 72 */     lines.add(rdr.read("LINESTRING (185 221, 292 281, 180 316)"));
/* 73 */     lines.add(rdr.read("LINESTRING (189 98, 83 187, 185 221)"));
/* 74 */     lines.add(rdr.read("LINESTRING (189 98, 325 168, 185 221)"));
/* 76 */     Polygonizer polygonizer = new Polygonizer();
/* 77 */     polygonizer.add(lines);
/* 79 */     Collection polys = polygonizer.getPolygons();
/* 81 */     System.out.println("Polygons formed (" + polys.size() + "):");
/* 82 */     System.out.println(polys);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jtsexample\operation\polygonize\PolygonizeExample.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
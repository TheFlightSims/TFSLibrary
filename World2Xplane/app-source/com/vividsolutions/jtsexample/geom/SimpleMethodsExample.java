/*    */ package com.vividsolutions.jtsexample.geom;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import com.vividsolutions.jts.geom.GeometryFactory;
/*    */ import com.vividsolutions.jts.io.ParseException;
/*    */ import com.vividsolutions.jts.io.WKTReader;
/*    */ 
/*    */ public class SimpleMethodsExample {
/*    */   public static void main(String[] args) {
/* 62 */     SimpleMethodsExample example = new SimpleMethodsExample();
/*    */     try {
/* 64 */       example.run();
/* 66 */     } catch (Exception ex) {
/* 67 */       ex.printStackTrace();
/*    */     } 
/*    */   }
/*    */   
/*    */   public void run() throws ParseException {
/* 77 */     GeometryFactory fact = new GeometryFactory();
/* 78 */     WKTReader wktRdr = new WKTReader(fact);
/* 80 */     String wktA = "POLYGON((40 100, 40 20, 120 20, 120 100, 40 100))";
/* 81 */     String wktB = "LINESTRING(20 80, 80 60, 100 140)";
/* 82 */     Geometry A = wktRdr.read(wktA);
/* 83 */     Geometry B = wktRdr.read(wktB);
/* 84 */     Geometry C = A.intersection(B);
/* 85 */     System.out.println("A = " + A);
/* 86 */     System.out.println("B = " + B);
/* 87 */     System.out.println("A intersection B = " + C);
/* 88 */     System.out.println("A relate C = " + A.relate(B));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jtsexample\geom\SimpleMethodsExample.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
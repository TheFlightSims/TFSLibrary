/*    */ package com.vividsolutions.jtsexample.linearref;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Coordinate;
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import com.vividsolutions.jts.geom.GeometryFactory;
/*    */ import com.vividsolutions.jts.io.ParseException;
/*    */ import com.vividsolutions.jts.io.WKTReader;
/*    */ import com.vividsolutions.jts.linearref.LengthIndexedLine;
/*    */ 
/*    */ public class LinearRefExample {
/* 47 */   static GeometryFactory fact = new GeometryFactory();
/*    */   
/* 48 */   static WKTReader rdr = new WKTReader(fact);
/*    */   
/*    */   public static void main(String[] args) throws Exception {
/* 53 */     LinearRefExample example = new LinearRefExample();
/* 54 */     example.run();
/*    */   }
/*    */   
/*    */   public void run() throws Exception {
/* 64 */     runExtractedLine("LINESTRING (0 0, 10 10, 20 20)", 1.0D, 10.0D);
/* 65 */     runExtractedLine("MULTILINESTRING ((0 0, 10 10), (20 20, 25 25, 30 40))", 1.0D, 20.0D);
/*    */   }
/*    */   
/*    */   public void runExtractedLine(String wkt, double start, double end) throws ParseException {
/* 71 */     System.out.println("=========================");
/* 72 */     Geometry g1 = rdr.read(wkt);
/* 73 */     System.out.println("Input Geometry: " + g1);
/* 74 */     System.out.println("Indices to extract: " + start + " " + end);
/* 76 */     LengthIndexedLine indexedLine = new LengthIndexedLine(g1);
/* 78 */     Geometry subLine = indexedLine.extractLine(start, end);
/* 79 */     System.out.println("Extracted Line: " + subLine);
/* 81 */     double[] index = indexedLine.indicesOf(subLine);
/* 82 */     System.out.println("Indices of extracted line: " + index[0] + " " + index[1]);
/* 84 */     Coordinate midpt = indexedLine.extractPoint((index[0] + index[1]) / 2.0D);
/* 85 */     System.out.println("Midpoint of extracted line: " + midpt);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jtsexample\linearref\LinearRefExample.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package com.vividsolutions.jtsexample.geom;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.PrecisionModel;
/*     */ import com.vividsolutions.jts.io.ParseException;
/*     */ import com.vividsolutions.jts.io.WKTReader;
/*     */ 
/*     */ public class PrecisionModelExample {
/*     */   public static void main(String[] args) {
/*  53 */     PrecisionModelExample example = new PrecisionModelExample();
/*     */     try {
/*  55 */       example.run();
/*  57 */     } catch (Exception ex) {
/*  58 */       ex.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void run() throws ParseException {
/*  68 */     example1();
/*  69 */     example2();
/*     */   }
/*     */   
/*     */   public void example1() throws ParseException {
/*  75 */     System.out.println("-------------------------------------------");
/*  76 */     System.out.println("Example 1 shows roundoff from computing in different precision models");
/*  77 */     String wktA = "POLYGON ((60 180, 160 260, 240 80, 60 180))";
/*  78 */     String wktB = "POLYGON ((200 260, 280 160, 80 100, 200 260))";
/*  79 */     System.out.println("A = " + wktA);
/*  80 */     System.out.println("B = " + wktB);
/*  82 */     intersection(wktA, wktB, new PrecisionModel());
/*  83 */     intersection(wktA, wktB, new PrecisionModel(PrecisionModel.FLOATING_SINGLE));
/*  84 */     intersection(wktA, wktB, new PrecisionModel(1.0D));
/*     */   }
/*     */   
/*     */   public void example2() throws ParseException {
/*  90 */     System.out.println("-------------------------------------------");
/*  91 */     System.out.println("Example 2 shows that roundoff can change the topology of geometry computed in different precision models");
/*  92 */     String wktA = "POLYGON ((0 0, 160 0, 160 1, 0 0))";
/*  93 */     String wktB = "POLYGON ((40 60, 40 -20, 140 -20, 140 60, 40 60))";
/*  94 */     System.out.println("A = " + wktA);
/*  95 */     System.out.println("B = " + wktB);
/*  97 */     difference(wktA, wktB, new PrecisionModel());
/*  98 */     difference(wktA, wktB, new PrecisionModel(1.0D));
/*     */   }
/*     */   
/*     */   public void intersection(String wktA, String wktB, PrecisionModel pm) throws ParseException {
/* 105 */     System.out.println("Running example using Precision Model = " + pm);
/* 106 */     GeometryFactory fact = new GeometryFactory(pm);
/* 107 */     WKTReader wktRdr = new WKTReader(fact);
/* 109 */     Geometry A = wktRdr.read(wktA);
/* 110 */     Geometry B = wktRdr.read(wktB);
/* 111 */     Geometry C = A.intersection(B);
/* 113 */     System.out.println("A intersection B = " + C);
/*     */   }
/*     */   
/*     */   public void difference(String wktA, String wktB, PrecisionModel pm) throws ParseException {
/* 119 */     System.out.println("-------------------------------------------");
/* 120 */     System.out.println("Running example using Precision Model = " + pm);
/* 121 */     GeometryFactory fact = new GeometryFactory(pm);
/* 122 */     WKTReader wktRdr = new WKTReader(fact);
/* 124 */     Geometry A = wktRdr.read(wktA);
/* 125 */     Geometry B = wktRdr.read(wktB);
/* 126 */     Geometry C = A.difference(B);
/* 128 */     System.out.println("A intersection B = " + C);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jtsexample\geom\PrecisionModelExample.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
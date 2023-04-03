/*     */ package com.vividsolutions.jtsexample.operation.linemerge;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.io.WKTReader;
/*     */ import com.vividsolutions.jts.operation.linemerge.LineMerger;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ 
/*     */ public class LineMergeExample {
/*  51 */   private WKTReader reader = new WKTReader();
/*     */   
/*     */   public static void main(String[] args) throws Exception {
/*  57 */     LineMergeExample test = new LineMergeExample();
/*     */     try {
/*  59 */       test.run();
/*  60 */     } catch (Exception ex) {
/*  61 */       ex.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   void run() throws Exception {
/*  66 */     Collection lineStrings = getData();
/*  68 */     LineMerger lineMerger = new LineMerger();
/*  69 */     lineMerger.add(lineStrings);
/*  70 */     Collection mergedLineStrings = lineMerger.getMergedLineStrings();
/*  72 */     System.out.println("Lines formed (" + mergedLineStrings.size() + "):");
/*  73 */     System.out.println(mergedLineStrings);
/*     */   }
/*     */   
/*     */   Collection getData() {
/*  77 */     Collection<Geometry> lines = new ArrayList();
/*  78 */     lines.add(read("LINESTRING (220 160, 240 150, 270 150, 290 170)"));
/*  79 */     lines.add(read("LINESTRING (60 210, 30 190, 30 160)"));
/*  80 */     lines.add(read("LINESTRING (70 430, 100 430, 120 420, 140 400)"));
/*  81 */     lines.add(read("LINESTRING (160 310, 160 280, 160 250, 170 230)"));
/*  82 */     lines.add(read("LINESTRING (170 230, 180 210, 200 180, 220 160)"));
/*  83 */     lines.add(read("LINESTRING (30 160, 40 150, 70 150)"));
/*  84 */     lines.add(read("LINESTRING (160 310, 200 330, 220 340, 240 360)"));
/*  85 */     lines.add(read("LINESTRING (140 400, 150 370, 160 340, 160 310)"));
/*  86 */     lines.add(read("LINESTRING (160 310, 130 300, 100 290, 70 270)"));
/*  87 */     lines.add(read("LINESTRING (240 360, 260 390, 260 410, 250 430)"));
/*  88 */     lines.add(read("LINESTRING (70 150, 100 180, 100 200)"));
/*  89 */     lines.add(read("LINESTRING (70 270, 60 260, 50 240, 50 220, 60 210)"));
/*  90 */     lines.add(read("LINESTRING (100 200, 90 210, 60 210)"));
/*  92 */     return lines;
/*     */   }
/*     */   
/*     */   Geometry read(String lineWKT) {
/*     */     try {
/*  97 */       Geometry geom = this.reader.read(lineWKT);
/*  99 */       return geom;
/* 100 */     } catch (Exception ex) {
/* 101 */       ex.printStackTrace();
/* 104 */       return null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jtsexample\operation\linemerge\LineMergeExample.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
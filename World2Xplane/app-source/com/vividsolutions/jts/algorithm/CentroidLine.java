/*     */ package com.vividsolutions.jts.algorithm;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryCollection;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ 
/*     */ public class CentroidLine {
/*  49 */   private Coordinate centSum = new Coordinate();
/*     */   
/*  50 */   private double totalLength = 0.0D;
/*     */   
/*     */   public void add(Geometry geom) {
/*  64 */     if (geom instanceof com.vividsolutions.jts.geom.LineString) {
/*  65 */       add(geom.getCoordinates());
/*  67 */     } else if (geom instanceof Polygon) {
/*  68 */       Polygon poly = (Polygon)geom;
/*  70 */       add(poly.getExteriorRing().getCoordinates());
/*  71 */       for (int i = 0; i < poly.getNumInteriorRing(); i++)
/*  72 */         add(poly.getInteriorRingN(i).getCoordinates()); 
/*  75 */     } else if (geom instanceof GeometryCollection) {
/*  76 */       GeometryCollection gc = (GeometryCollection)geom;
/*  77 */       for (int i = 0; i < gc.getNumGeometries(); i++)
/*  78 */         add(gc.getGeometryN(i)); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Coordinate getCentroid() {
/*  85 */     Coordinate cent = new Coordinate();
/*  86 */     this.centSum.x /= this.totalLength;
/*  87 */     this.centSum.y /= this.totalLength;
/*  88 */     return cent;
/*     */   }
/*     */   
/*     */   public void add(Coordinate[] pts) {
/*  97 */     for (int i = 0; i < pts.length - 1; i++) {
/*  98 */       double segmentLen = pts[i].distance(pts[i + 1]);
/*  99 */       this.totalLength += segmentLen;
/* 101 */       double midx = ((pts[i]).x + (pts[i + 1]).x) / 2.0D;
/* 102 */       this.centSum.x += segmentLen * midx;
/* 103 */       double midy = ((pts[i]).y + (pts[i + 1]).y) / 2.0D;
/* 104 */       this.centSum.y += segmentLen * midy;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\algorithm\CentroidLine.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package com.vividsolutions.jts.algorithm;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryCollection;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ 
/*     */ public class CentroidArea {
/*  56 */   private Coordinate basePt = null;
/*     */   
/*  57 */   private Coordinate triangleCent3 = new Coordinate();
/*     */   
/*  58 */   private double areasum2 = 0.0D;
/*     */   
/*  59 */   private Coordinate cg3 = new Coordinate();
/*     */   
/*  62 */   private Coordinate centSum = new Coordinate();
/*     */   
/*  63 */   private double totalLength = 0.0D;
/*     */   
/*     */   public CentroidArea() {
/*  67 */     this.basePt = null;
/*     */   }
/*     */   
/*     */   public void add(Geometry geom) {
/*  78 */     if (geom instanceof Polygon) {
/*  79 */       Polygon poly = (Polygon)geom;
/*  80 */       setBasePoint(poly.getExteriorRing().getCoordinateN(0));
/*  81 */       add(poly);
/*  83 */     } else if (geom instanceof GeometryCollection) {
/*  84 */       GeometryCollection gc = (GeometryCollection)geom;
/*  85 */       for (int i = 0; i < gc.getNumGeometries(); i++)
/*  86 */         add(gc.getGeometryN(i)); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void add(Coordinate[] ring) {
/*  99 */     setBasePoint(ring[0]);
/* 100 */     addShell(ring);
/*     */   }
/*     */   
/*     */   public Coordinate getCentroid() {
/* 105 */     Coordinate cent = new Coordinate();
/* 106 */     if (Math.abs(this.areasum2) > 0.0D) {
/* 107 */       cent.x = this.cg3.x / 3.0D / this.areasum2;
/* 108 */       cent.y = this.cg3.y / 3.0D / this.areasum2;
/*     */     } else {
/* 112 */       this.centSum.x /= this.totalLength;
/* 113 */       this.centSum.y /= this.totalLength;
/*     */     } 
/* 115 */     return cent;
/*     */   }
/*     */   
/*     */   private void setBasePoint(Coordinate basePt) {
/* 120 */     if (this.basePt == null)
/* 121 */       this.basePt = basePt; 
/*     */   }
/*     */   
/*     */   private void add(Polygon poly) {
/* 126 */     addShell(poly.getExteriorRing().getCoordinates());
/* 127 */     for (int i = 0; i < poly.getNumInteriorRing(); i++)
/* 128 */       addHole(poly.getInteriorRingN(i).getCoordinates()); 
/*     */   }
/*     */   
/*     */   private void addShell(Coordinate[] pts) {
/* 134 */     boolean isPositiveArea = !CGAlgorithms.isCCW(pts);
/* 135 */     for (int i = 0; i < pts.length - 1; i++)
/* 136 */       addTriangle(this.basePt, pts[i], pts[i + 1], isPositiveArea); 
/* 138 */     addLinearSegments(pts);
/*     */   }
/*     */   
/*     */   private void addHole(Coordinate[] pts) {
/* 142 */     boolean isPositiveArea = CGAlgorithms.isCCW(pts);
/* 143 */     for (int i = 0; i < pts.length - 1; i++)
/* 144 */       addTriangle(this.basePt, pts[i], pts[i + 1], isPositiveArea); 
/* 146 */     addLinearSegments(pts);
/*     */   }
/*     */   
/*     */   private void addTriangle(Coordinate p0, Coordinate p1, Coordinate p2, boolean isPositiveArea) {
/* 150 */     double sign = isPositiveArea ? 1.0D : -1.0D;
/* 151 */     centroid3(p0, p1, p2, this.triangleCent3);
/* 152 */     double area2 = area2(p0, p1, p2);
/* 153 */     this.cg3.x += sign * area2 * this.triangleCent3.x;
/* 154 */     this.cg3.y += sign * area2 * this.triangleCent3.y;
/* 155 */     this.areasum2 += sign * area2;
/*     */   }
/*     */   
/*     */   private static void centroid3(Coordinate p1, Coordinate p2, Coordinate p3, Coordinate c) {
/* 164 */     c.x = p1.x + p2.x + p3.x;
/* 165 */     c.y = p1.y + p2.y + p3.y;
/*     */   }
/*     */   
/*     */   private static double area2(Coordinate p1, Coordinate p2, Coordinate p3) {
/* 175 */     return (p2.x - p1.x) * (p3.y - p1.y) - (p3.x - p1.x) * (p2.y - p1.y);
/*     */   }
/*     */   
/*     */   private void addLinearSegments(Coordinate[] pts) {
/* 190 */     for (int i = 0; i < pts.length - 1; i++) {
/* 191 */       double segmentLen = pts[i].distance(pts[i + 1]);
/* 192 */       this.totalLength += segmentLen;
/* 194 */       double midx = ((pts[i]).x + (pts[i + 1]).x) / 2.0D;
/* 195 */       this.centSum.x += segmentLen * midx;
/* 196 */       double midy = ((pts[i]).y + (pts[i + 1]).y) / 2.0D;
/* 197 */       this.centSum.y += segmentLen * midy;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\algorithm\CentroidArea.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
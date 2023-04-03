/*     */ package com.vividsolutions.jts.algorithm;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryCollection;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ 
/*     */ public class Centroid {
/*     */   public static Coordinate getCentroid(Geometry geom) {
/*  77 */     Centroid cent = new Centroid(geom);
/*  78 */     return cent.getCentroid();
/*     */   }
/*     */   
/*  81 */   private Coordinate areaBasePt = null;
/*     */   
/*  82 */   private Coordinate triangleCent3 = new Coordinate();
/*     */   
/*  83 */   private double areasum2 = 0.0D;
/*     */   
/*  84 */   private Coordinate cg3 = new Coordinate();
/*     */   
/*  87 */   private Coordinate lineCentSum = new Coordinate();
/*     */   
/*  88 */   private double totalLength = 0.0D;
/*     */   
/*  90 */   private int ptCount = 0;
/*     */   
/*  91 */   private Coordinate ptCentSum = new Coordinate();
/*     */   
/*     */   public Centroid(Geometry geom) {
/*  98 */     this.areaBasePt = null;
/*  99 */     add(geom);
/*     */   }
/*     */   
/*     */   private void add(Geometry geom) {
/* 109 */     if (geom.isEmpty())
/*     */       return; 
/* 111 */     if (geom instanceof com.vividsolutions.jts.geom.Point) {
/* 112 */       addPoint(geom.getCoordinate());
/* 114 */     } else if (geom instanceof com.vividsolutions.jts.geom.LineString) {
/* 115 */       addLineSegments(geom.getCoordinates());
/* 117 */     } else if (geom instanceof Polygon) {
/* 118 */       Polygon poly = (Polygon)geom;
/* 119 */       add(poly);
/* 121 */     } else if (geom instanceof GeometryCollection) {
/* 122 */       GeometryCollection gc = (GeometryCollection)geom;
/* 123 */       for (int i = 0; i < gc.getNumGeometries(); i++)
/* 124 */         add(gc.getGeometryN(i)); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Coordinate getCentroid() {
/* 142 */     Coordinate cent = new Coordinate();
/* 143 */     if (Math.abs(this.areasum2) > 0.0D) {
/* 147 */       cent.x = this.cg3.x / 3.0D / this.areasum2;
/* 148 */       cent.y = this.cg3.y / 3.0D / this.areasum2;
/* 150 */     } else if (this.totalLength > 0.0D) {
/* 154 */       this.lineCentSum.x /= this.totalLength;
/* 155 */       this.lineCentSum.y /= this.totalLength;
/* 157 */     } else if (this.ptCount > 0) {
/* 161 */       this.ptCentSum.x /= this.ptCount;
/* 162 */       this.ptCentSum.y /= this.ptCount;
/*     */     } else {
/* 165 */       return null;
/*     */     } 
/* 167 */     return cent;
/*     */   }
/*     */   
/*     */   private void setBasePoint(Coordinate basePt) {
/* 172 */     if (this.areaBasePt == null)
/* 173 */       this.areaBasePt = basePt; 
/*     */   }
/*     */   
/*     */   private void add(Polygon poly) {
/* 178 */     addShell(poly.getExteriorRing().getCoordinates());
/* 179 */     for (int i = 0; i < poly.getNumInteriorRing(); i++)
/* 180 */       addHole(poly.getInteriorRingN(i).getCoordinates()); 
/*     */   }
/*     */   
/*     */   private void addShell(Coordinate[] pts) {
/* 186 */     if (pts.length > 0)
/* 187 */       setBasePoint(pts[0]); 
/* 188 */     boolean isPositiveArea = !CGAlgorithms.isCCW(pts);
/* 189 */     for (int i = 0; i < pts.length - 1; i++)
/* 190 */       addTriangle(this.areaBasePt, pts[i], pts[i + 1], isPositiveArea); 
/* 192 */     addLineSegments(pts);
/*     */   }
/*     */   
/*     */   private void addHole(Coordinate[] pts) {
/* 197 */     boolean isPositiveArea = CGAlgorithms.isCCW(pts);
/* 198 */     for (int i = 0; i < pts.length - 1; i++)
/* 199 */       addTriangle(this.areaBasePt, pts[i], pts[i + 1], isPositiveArea); 
/* 201 */     addLineSegments(pts);
/*     */   }
/*     */   
/*     */   private void addTriangle(Coordinate p0, Coordinate p1, Coordinate p2, boolean isPositiveArea) {
/* 205 */     double sign = isPositiveArea ? 1.0D : -1.0D;
/* 206 */     centroid3(p0, p1, p2, this.triangleCent3);
/* 207 */     double area2 = area2(p0, p1, p2);
/* 208 */     this.cg3.x += sign * area2 * this.triangleCent3.x;
/* 209 */     this.cg3.y += sign * area2 * this.triangleCent3.y;
/* 210 */     this.areasum2 += sign * area2;
/*     */   }
/*     */   
/*     */   private static void centroid3(Coordinate p1, Coordinate p2, Coordinate p3, Coordinate c) {
/* 219 */     c.x = p1.x + p2.x + p3.x;
/* 220 */     c.y = p1.y + p2.y + p3.y;
/*     */   }
/*     */   
/*     */   private static double area2(Coordinate p1, Coordinate p2, Coordinate p3) {
/* 230 */     return (p2.x - p1.x) * (p3.y - p1.y) - (p3.x - p1.x) * (p2.y - p1.y);
/*     */   }
/*     */   
/*     */   private void addLineSegments(Coordinate[] pts) {
/* 243 */     double lineLen = 0.0D;
/* 244 */     for (int i = 0; i < pts.length - 1; i++) {
/* 245 */       double segmentLen = pts[i].distance(pts[i + 1]);
/* 246 */       if (segmentLen != 0.0D) {
/* 249 */         lineLen += segmentLen;
/* 251 */         double midx = ((pts[i]).x + (pts[i + 1]).x) / 2.0D;
/* 252 */         this.lineCentSum.x += segmentLen * midx;
/* 253 */         double midy = ((pts[i]).y + (pts[i + 1]).y) / 2.0D;
/* 254 */         this.lineCentSum.y += segmentLen * midy;
/*     */       } 
/*     */     } 
/* 256 */     this.totalLength += lineLen;
/* 257 */     if (lineLen == 0.0D && pts.length > 0)
/* 258 */       addPoint(pts[0]); 
/*     */   }
/*     */   
/*     */   private void addPoint(Coordinate pt) {
/* 267 */     this.ptCount++;
/* 268 */     this.ptCentSum.x += pt.x;
/* 269 */     this.ptCentSum.y += pt.y;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\algorithm\Centroid.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
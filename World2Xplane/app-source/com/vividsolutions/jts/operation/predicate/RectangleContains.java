/*     */ package com.vividsolutions.jts.operation.predicate;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.Point;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ 
/*     */ public class RectangleContains {
/*     */   private Envelope rectEnv;
/*     */   
/*     */   public static boolean contains(Polygon rectangle, Geometry b) {
/*  62 */     RectangleContains rc = new RectangleContains(rectangle);
/*  63 */     return rc.contains(b);
/*     */   }
/*     */   
/*     */   public RectangleContains(Polygon rectangle) {
/*  74 */     this.rectEnv = rectangle.getEnvelopeInternal();
/*     */   }
/*     */   
/*     */   public boolean contains(Geometry geom) {
/*  80 */     if (!this.rectEnv.contains(geom.getEnvelopeInternal()))
/*  81 */       return false; 
/*  88 */     if (isContainedInBoundary(geom))
/*  89 */       return false; 
/*  90 */     return true;
/*     */   }
/*     */   
/*     */   private boolean isContainedInBoundary(Geometry geom) {
/*  96 */     if (geom instanceof Polygon)
/*  96 */       return false; 
/*  97 */     if (geom instanceof Point)
/*  97 */       return isPointContainedInBoundary((Point)geom); 
/*  98 */     if (geom instanceof LineString)
/*  98 */       return isLineStringContainedInBoundary((LineString)geom); 
/* 100 */     for (int i = 0; i < geom.getNumGeometries(); i++) {
/* 101 */       Geometry comp = geom.getGeometryN(i);
/* 102 */       if (!isContainedInBoundary(comp))
/* 103 */         return false; 
/*     */     } 
/* 105 */     return true;
/*     */   }
/*     */   
/*     */   private boolean isPointContainedInBoundary(Point point) {
/* 110 */     return isPointContainedInBoundary(point.getCoordinate());
/*     */   }
/*     */   
/*     */   private boolean isPointContainedInBoundary(Coordinate pt) {
/* 126 */     return (pt.x == this.rectEnv.getMinX() || pt.x == this.rectEnv.getMaxX() || pt.y == this.rectEnv.getMinY() || pt.y == this.rectEnv.getMaxY());
/*     */   }
/*     */   
/*     */   private boolean isLineStringContainedInBoundary(LineString line) {
/* 139 */     CoordinateSequence seq = line.getCoordinateSequence();
/* 140 */     Coordinate p0 = new Coordinate();
/* 141 */     Coordinate p1 = new Coordinate();
/* 142 */     for (int i = 0; i < seq.size() - 1; i++) {
/* 143 */       seq.getCoordinate(i, p0);
/* 144 */       seq.getCoordinate(i + 1, p1);
/* 146 */       if (!isLineSegmentContainedInBoundary(p0, p1))
/* 147 */         return false; 
/*     */     } 
/* 149 */     return true;
/*     */   }
/*     */   
/*     */   private boolean isLineSegmentContainedInBoundary(Coordinate p0, Coordinate p1) {
/* 160 */     if (p0.equals(p1))
/* 161 */       return isPointContainedInBoundary(p0); 
/* 164 */     if (p0.x == p1.x) {
/* 165 */       if (p0.x == this.rectEnv.getMinX() || p0.x == this.rectEnv.getMaxX())
/* 167 */         return true; 
/* 169 */     } else if (p0.y == p1.y && (
/* 170 */       p0.y == this.rectEnv.getMinY() || p0.y == this.rectEnv.getMaxY())) {
/* 172 */       return true;
/*     */     } 
/* 182 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\predicate\RectangleContains.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package com.world2xplane.Geom;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.PointLocator;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.Point;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ 
/*     */ public class IntersectFast {
/*     */   private Geometry g1;
/*     */   
/*     */   private Coordinate[] g1Coords;
/*     */   
/*     */   private PointLocator pointLocator;
/*     */   
/*     */   public IntersectFast(Geometry g1) {
/*  37 */     this.g1 = g1;
/*     */   }
/*     */   
/*     */   private PointLocator getPointLocator() {
/*  41 */     if (this.pointLocator == null)
/*  42 */       this.pointLocator = new PointLocator(); 
/*  44 */     return this.pointLocator;
/*     */   }
/*     */   
/*     */   public boolean intersect(Geometry g2) {
/*  52 */     if (this.g1 instanceof Point)
/*  53 */       return _intersectsPointGeometry((Point)this.g1, g2); 
/*  55 */     if (g2 instanceof Point)
/*  56 */       return _intersectsPointGeometry((Point)g2, this.g1); 
/*  60 */     if (this.g1 instanceof Polygon && g2 instanceof Polygon)
/*  61 */       return _intersectsPolyPoly(g2); 
/*  65 */     if (this.g1 instanceof LineString && g2 instanceof Polygon)
/*  66 */       return _intersects((LineString)this.g1, (Polygon)g2); 
/*  68 */     if (this.g1 instanceof Polygon && g2 instanceof LineString)
/*  69 */       return _intersects((LineString)g2, (Polygon)this.g1); 
/*  72 */     return this.g1.intersects(g2);
/*     */   }
/*     */   
/*     */   private boolean _intersectsPointGeometry(Point point, Geometry g) {
/*  77 */     Coordinate pointc = point.getCoordinate();
/*  80 */     if (g instanceof Point)
/*  81 */       return pointc.equals(g.getCoordinate()); 
/*  85 */     if (!g.getEnvelopeInternal().contains(pointc))
/*  86 */       return false; 
/* 100 */     return getPointLocator().intersects(pointc, g);
/*     */   }
/*     */   
/*     */   private Coordinate[] getG1Coordinates() {
/* 104 */     if (this.g1Coords == null)
/* 105 */       this.g1Coords = this.g1.getCoordinates(); 
/* 107 */     return this.g1Coords;
/*     */   }
/*     */   
/*     */   private boolean _intersectsPolyPoly(Geometry g2) {
/* 112 */     Polygon p1 = (Polygon)this.g1;
/* 113 */     Polygon p2 = (Polygon)g2;
/* 116 */     if (!p1.getEnvelopeInternal().intersects(p2.getEnvelopeInternal()))
/* 118 */       return false; 
/* 125 */     if (_intersects(getG1Coordinates(), p2))
/* 126 */       return true; 
/* 128 */     if (_intersects(p2.getCoordinates(), p1))
/* 129 */       return true; 
/* 133 */     if (p1.getNumInteriorRing() > 0 || p2.getNumInteriorRing() > 0) {
/* 135 */       LineString e1 = p1.getExteriorRing();
/* 136 */       LineString e2 = p2.getExteriorRing();
/* 137 */       if (!e1.intersects((Geometry)e2))
/* 138 */         return false; 
/*     */     } 
/* 143 */     return p1.intersects((Geometry)p2);
/*     */   }
/*     */   
/*     */   private boolean _intersects(LineString ls, Polygon p) {
/* 147 */     if (!ls.getEnvelopeInternal().intersects(p.getEnvelopeInternal()))
/* 149 */       return false; 
/* 152 */     if (_intersects(ls.getCoordinates(), p))
/* 153 */       return true; 
/* 159 */     if (p.getNumInteriorRing() > 0 && 
/* 160 */       !ls.intersects((Geometry)p.getExteriorRing()))
/* 161 */       return false; 
/* 166 */     return ls.intersects((Geometry)p);
/*     */   }
/*     */   
/*     */   private boolean _intersects(Coordinate[] coords, Polygon polygon) {
/* 170 */     for (int i = 0; i < coords.length; i++) {
/* 171 */       Coordinate c = coords[i];
/* 172 */       if (getPointLocator().intersects(c, (Geometry)polygon))
/* 173 */         return true; 
/*     */     } 
/* 176 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Geom\IntersectFast.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
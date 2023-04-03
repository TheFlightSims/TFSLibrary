/*     */ package com.vividsolutions.jts.algorithm;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryCollectionIterator;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.LinearRing;
/*     */ import com.vividsolutions.jts.geom.MultiLineString;
/*     */ import com.vividsolutions.jts.geom.MultiPolygon;
/*     */ import com.vividsolutions.jts.geom.Point;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ 
/*     */ public class PointLocator {
/*  56 */   private BoundaryNodeRule boundaryRule = BoundaryNodeRule.OGC_SFS_BOUNDARY_RULE;
/*     */   
/*     */   private boolean isIn;
/*     */   
/*     */   private int numBoundaries;
/*     */   
/*     */   public PointLocator() {}
/*     */   
/*     */   public PointLocator(BoundaryNodeRule boundaryRule) {
/*  68 */     if (boundaryRule == null)
/*  69 */       throw new IllegalArgumentException("Rule must be non-null"); 
/*  70 */     this.boundaryRule = boundaryRule;
/*     */   }
/*     */   
/*     */   public boolean intersects(Coordinate p, Geometry geom) {
/*  82 */     return (locate(p, geom) != 2);
/*     */   }
/*     */   
/*     */   public int locate(Coordinate p, Geometry geom) {
/*  97 */     if (geom.isEmpty())
/*  97 */       return 2; 
/*  99 */     if (geom instanceof LineString)
/* 100 */       return locate(p, (LineString)geom); 
/* 102 */     if (geom instanceof Polygon)
/* 103 */       return locate(p, (Polygon)geom); 
/* 106 */     this.isIn = false;
/* 107 */     this.numBoundaries = 0;
/* 108 */     computeLocation(p, geom);
/* 109 */     if (this.boundaryRule.isInBoundary(this.numBoundaries))
/* 110 */       return 1; 
/* 111 */     if (this.numBoundaries > 0 || this.isIn)
/* 112 */       return 0; 
/* 114 */     return 2;
/*     */   }
/*     */   
/*     */   private void computeLocation(Coordinate p, Geometry geom) {
/* 119 */     if (geom instanceof Point)
/* 120 */       updateLocationInfo(locate(p, (Point)geom)); 
/* 122 */     if (geom instanceof LineString) {
/* 123 */       updateLocationInfo(locate(p, (LineString)geom));
/* 125 */     } else if (geom instanceof Polygon) {
/* 126 */       updateLocationInfo(locate(p, (Polygon)geom));
/* 128 */     } else if (geom instanceof MultiLineString) {
/* 129 */       MultiLineString ml = (MultiLineString)geom;
/* 130 */       for (int i = 0; i < ml.getNumGeometries(); i++) {
/* 131 */         LineString l = (LineString)ml.getGeometryN(i);
/* 132 */         updateLocationInfo(locate(p, l));
/*     */       } 
/* 135 */     } else if (geom instanceof MultiPolygon) {
/* 136 */       MultiPolygon mpoly = (MultiPolygon)geom;
/* 137 */       for (int i = 0; i < mpoly.getNumGeometries(); i++) {
/* 138 */         Polygon poly = (Polygon)mpoly.getGeometryN(i);
/* 139 */         updateLocationInfo(locate(p, poly));
/*     */       } 
/* 142 */     } else if (geom instanceof com.vividsolutions.jts.geom.GeometryCollection) {
/* 143 */       GeometryCollectionIterator<Geometry> geometryCollectionIterator = new GeometryCollectionIterator(geom);
/* 144 */       while (geometryCollectionIterator.hasNext()) {
/* 145 */         Geometry g2 = geometryCollectionIterator.next();
/* 146 */         if (g2 != geom)
/* 147 */           computeLocation(p, g2); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateLocationInfo(int loc) {
/* 154 */     if (loc == 0)
/* 154 */       this.isIn = true; 
/* 155 */     if (loc == 1)
/* 155 */       this.numBoundaries++; 
/*     */   }
/*     */   
/*     */   private int locate(Coordinate p, Point pt) {
/* 162 */     Coordinate ptCoord = pt.getCoordinate();
/* 163 */     if (ptCoord.equals2D(p))
/* 164 */       return 0; 
/* 165 */     return 2;
/*     */   }
/*     */   
/*     */   private int locate(Coordinate p, LineString l) {
/* 171 */     if (!l.getEnvelopeInternal().intersects(p))
/* 171 */       return 2; 
/* 173 */     Coordinate[] pt = l.getCoordinates();
/* 174 */     if (!l.isClosed() && (
/* 175 */       p.equals(pt[0]) || p.equals(pt[pt.length - 1])))
/* 177 */       return 1; 
/* 180 */     if (CGAlgorithms.isOnLine(p, pt))
/* 181 */       return 0; 
/* 182 */     return 2;
/*     */   }
/*     */   
/*     */   private int locateInPolygonRing(Coordinate p, LinearRing ring) {
/* 188 */     if (!ring.getEnvelopeInternal().intersects(p))
/* 188 */       return 2; 
/* 190 */     return CGAlgorithms.locatePointInRing(p, ring.getCoordinates());
/*     */   }
/*     */   
/*     */   private int locate(Coordinate p, Polygon poly) {
/* 195 */     if (poly.isEmpty())
/* 195 */       return 2; 
/* 197 */     LinearRing shell = (LinearRing)poly.getExteriorRing();
/* 199 */     int shellLoc = locateInPolygonRing(p, shell);
/* 200 */     if (shellLoc == 2)
/* 200 */       return 2; 
/* 201 */     if (shellLoc == 1)
/* 201 */       return 1; 
/* 203 */     for (int i = 0; i < poly.getNumInteriorRing(); i++) {
/* 204 */       LinearRing hole = (LinearRing)poly.getInteriorRingN(i);
/* 205 */       int holeLoc = locateInPolygonRing(p, hole);
/* 206 */       if (holeLoc == 0)
/* 206 */         return 2; 
/* 207 */       if (holeLoc == 1)
/* 207 */         return 1; 
/*     */     } 
/* 209 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\algorithm\PointLocator.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
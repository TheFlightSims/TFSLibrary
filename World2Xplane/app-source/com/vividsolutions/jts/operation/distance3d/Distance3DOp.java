/*     */ package com.vividsolutions.jts.operation.distance3d;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.CGAlgorithms3D;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.LineSegment;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.Point;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import com.vividsolutions.jts.operation.distance.GeometryLocation;
/*     */ 
/*     */ public class Distance3DOp {
/*     */   private Geometry[] geom;
/*     */   
/*     */   public static double distance(Geometry g0, Geometry g1) {
/*  73 */     Distance3DOp distOp = new Distance3DOp(g0, g1);
/*  74 */     return distOp.distance();
/*     */   }
/*     */   
/*     */   public static boolean isWithinDistance(Geometry g0, Geometry g1, double distance) {
/*  90 */     Distance3DOp distOp = new Distance3DOp(g0, g1, distance);
/*  91 */     return (distOp.distance() <= distance);
/*     */   }
/*     */   
/*     */   public static Coordinate[] nearestPoints(Geometry g0, Geometry g1) {
/* 105 */     Distance3DOp distOp = new Distance3DOp(g0, g1);
/* 106 */     return distOp.nearestPoints();
/*     */   }
/*     */   
/* 111 */   private double terminateDistance = 0.0D;
/*     */   
/*     */   private GeometryLocation[] minDistanceLocation;
/*     */   
/* 114 */   private double minDistance = Double.MAX_VALUE;
/*     */   
/*     */   private boolean isDone = false;
/*     */   
/*     */   public Distance3DOp(Geometry g0, Geometry g1) {
/* 127 */     this(g0, g1, 0.0D);
/*     */   }
/*     */   
/*     */   public Distance3DOp(Geometry g0, Geometry g1, double terminateDistance) {
/* 142 */     this.geom = new Geometry[2];
/* 143 */     this.geom[0] = g0;
/* 144 */     this.geom[1] = g1;
/* 145 */     this.terminateDistance = terminateDistance;
/*     */   }
/*     */   
/*     */   public double distance() {
/* 156 */     if (this.geom[0] == null || this.geom[1] == null)
/* 157 */       throw new IllegalArgumentException("null geometries are not supported"); 
/* 159 */     if (this.geom[0].isEmpty() || this.geom[1].isEmpty())
/* 160 */       return 0.0D; 
/* 162 */     computeMinDistance();
/* 163 */     return this.minDistance;
/*     */   }
/*     */   
/*     */   public Coordinate[] nearestPoints() {
/* 173 */     computeMinDistance();
/* 174 */     Coordinate[] nearestPts = { this.minDistanceLocation[0].getCoordinate(), this.minDistanceLocation[1].getCoordinate() };
/* 177 */     return nearestPts;
/*     */   }
/*     */   
/*     */   public GeometryLocation[] nearestLocations() {
/* 187 */     computeMinDistance();
/* 188 */     return this.minDistanceLocation;
/*     */   }
/*     */   
/*     */   private void updateDistance(double dist, GeometryLocation loc0, GeometryLocation loc1, boolean flip) {
/* 194 */     this.minDistance = dist;
/* 195 */     int index = flip ? 1 : 0;
/* 196 */     this.minDistanceLocation[index] = loc0;
/* 197 */     this.minDistanceLocation[1 - index] = loc1;
/* 198 */     if (this.minDistance < this.terminateDistance)
/* 199 */       this.isDone = true; 
/*     */   }
/*     */   
/*     */   private void computeMinDistance() {
/* 204 */     if (this.minDistanceLocation != null)
/*     */       return; 
/* 206 */     this.minDistanceLocation = new GeometryLocation[2];
/* 208 */     int geomIndex = mostPolygonalIndex();
/* 209 */     boolean flip = (geomIndex == 0);
/* 210 */     computeMinDistanceMultiMulti(this.geom[geomIndex], this.geom[1 - geomIndex], flip);
/*     */   }
/*     */   
/*     */   private int mostPolygonalIndex() {
/* 221 */     int dim0 = this.geom[0].getDimension();
/* 222 */     int dim1 = this.geom[1].getDimension();
/* 223 */     if (dim0 >= 2 && dim1 >= 2) {
/* 224 */       if (this.geom[0].getNumPoints() > this.geom[1].getNumPoints())
/* 225 */         return 0; 
/* 226 */       return 1;
/*     */     } 
/* 229 */     if (dim0 >= 2)
/* 229 */       return 0; 
/* 230 */     if (dim1 >= 2)
/* 230 */       return 1; 
/* 232 */     return 0;
/*     */   }
/*     */   
/*     */   private void computeMinDistanceMultiMulti(Geometry g0, Geometry g1, boolean flip) {
/* 236 */     if (g0 instanceof com.vividsolutions.jts.geom.GeometryCollection) {
/* 237 */       int n = g0.getNumGeometries();
/* 238 */       for (int i = 0; i < n; i++) {
/* 239 */         Geometry g = g0.getGeometryN(i);
/* 240 */         computeMinDistanceMultiMulti(g, g1, flip);
/* 241 */         if (this.isDone)
/*     */           return; 
/*     */       } 
/*     */     } else {
/* 246 */       if (g0.isEmpty())
/*     */         return; 
/* 250 */       if (g0 instanceof Polygon) {
/* 251 */         computeMinDistanceOneMulti(polyPlane(g0), g1, flip);
/*     */       } else {
/* 254 */         computeMinDistanceOneMulti(g0, g1, flip);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeMinDistanceOneMulti(Geometry g0, Geometry g1, boolean flip) {
/* 259 */     if (g1 instanceof com.vividsolutions.jts.geom.GeometryCollection) {
/* 260 */       int n = g1.getNumGeometries();
/* 261 */       for (int i = 0; i < n; i++) {
/* 262 */         Geometry g = g1.getGeometryN(i);
/* 263 */         computeMinDistanceOneMulti(g0, g, flip);
/* 264 */         if (this.isDone)
/*     */           return; 
/*     */       } 
/*     */     } else {
/* 268 */       computeMinDistance(g0, g1, flip);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeMinDistanceOneMulti(PlanarPolygon3D poly, Geometry geom, boolean flip) {
/* 273 */     if (geom instanceof com.vividsolutions.jts.geom.GeometryCollection) {
/* 274 */       int n = geom.getNumGeometries();
/* 275 */       for (int i = 0; i < n; i++) {
/* 276 */         Geometry g = geom.getGeometryN(i);
/* 277 */         computeMinDistanceOneMulti(poly, g, flip);
/* 278 */         if (this.isDone)
/*     */           return; 
/*     */       } 
/*     */     } else {
/* 282 */       if (geom instanceof Point) {
/* 283 */         computeMinDistancePolygonPoint(poly, (Point)geom, flip);
/*     */         return;
/*     */       } 
/* 286 */       if (geom instanceof LineString) {
/* 287 */         computeMinDistancePolygonLine(poly, (LineString)geom, flip);
/*     */         return;
/*     */       } 
/* 290 */       if (geom instanceof Polygon) {
/* 291 */         computeMinDistancePolygonPolygon(poly, (Polygon)geom, flip);
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static PlanarPolygon3D polyPlane(Geometry poly) {
/* 304 */     return new PlanarPolygon3D((Polygon)poly);
/*     */   }
/*     */   
/*     */   private void computeMinDistance(Geometry g0, Geometry g1, boolean flip) {
/* 308 */     if (g0 instanceof Point) {
/* 309 */       if (g1 instanceof Point) {
/* 310 */         computeMinDistancePointPoint((Point)g0, (Point)g1, flip);
/*     */         return;
/*     */       } 
/* 313 */       if (g1 instanceof LineString) {
/* 314 */         computeMinDistanceLinePoint((LineString)g1, (Point)g0, !flip);
/*     */         return;
/*     */       } 
/* 317 */       if (g1 instanceof Polygon) {
/* 318 */         computeMinDistancePolygonPoint(polyPlane(g1), (Point)g0, !flip);
/*     */         return;
/*     */       } 
/*     */     } 
/* 322 */     if (g0 instanceof LineString) {
/* 323 */       if (g1 instanceof Point) {
/* 324 */         computeMinDistanceLinePoint((LineString)g0, (Point)g1, flip);
/*     */         return;
/*     */       } 
/* 327 */       if (g1 instanceof LineString) {
/* 328 */         computeMinDistanceLineLine((LineString)g0, (LineString)g1, flip);
/*     */         return;
/*     */       } 
/* 331 */       if (g1 instanceof Polygon) {
/* 332 */         computeMinDistancePolygonLine(polyPlane(g1), (LineString)g0, !flip);
/*     */         return;
/*     */       } 
/*     */     } 
/* 336 */     if (g0 instanceof Polygon) {
/* 337 */       if (g1 instanceof Point) {
/* 338 */         computeMinDistancePolygonPoint(polyPlane(g0), (Point)g1, flip);
/*     */         return;
/*     */       } 
/* 341 */       if (g1 instanceof LineString) {
/* 342 */         computeMinDistancePolygonLine(polyPlane(g0), (LineString)g1, flip);
/*     */         return;
/*     */       } 
/* 345 */       if (g1 instanceof Polygon) {
/* 346 */         computeMinDistancePolygonPolygon(polyPlane(g0), (Polygon)g1, flip);
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeMinDistancePolygonPolygon(PlanarPolygon3D poly0, Polygon poly1, boolean flip) {
/* 373 */     computeMinDistancePolygonRings(poly0, poly1, flip);
/* 374 */     if (this.isDone)
/*     */       return; 
/* 375 */     PlanarPolygon3D polyPlane1 = new PlanarPolygon3D(poly1);
/* 376 */     computeMinDistancePolygonRings(polyPlane1, poly0.getPolygon(), flip);
/*     */   }
/*     */   
/*     */   private void computeMinDistancePolygonRings(PlanarPolygon3D poly, Polygon ringPoly, boolean flip) {
/* 389 */     computeMinDistancePolygonLine(poly, ringPoly.getExteriorRing(), flip);
/* 390 */     if (this.isDone)
/*     */       return; 
/* 392 */     int nHole = ringPoly.getNumInteriorRing();
/* 393 */     for (int i = 0; i < nHole; i++) {
/* 394 */       computeMinDistancePolygonLine(poly, ringPoly.getInteriorRingN(i), flip);
/* 395 */       if (this.isDone)
/*     */         return; 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeMinDistancePolygonLine(PlanarPolygon3D poly, LineString line, boolean flip) {
/* 403 */     Coordinate intPt = intersection(poly, line);
/* 404 */     if (intPt != null) {
/* 405 */       updateDistance(0.0D, new GeometryLocation((Geometry)poly.getPolygon(), 0, intPt), new GeometryLocation((Geometry)line, 0, intPt), flip);
/*     */       return;
/*     */     } 
/* 414 */     computeMinDistanceLineLine(poly.getPolygon().getExteriorRing(), line, flip);
/* 415 */     if (this.isDone)
/*     */       return; 
/* 416 */     int nHole = poly.getPolygon().getNumInteriorRing();
/* 417 */     for (int i = 0; i < nHole; i++) {
/* 418 */       computeMinDistanceLineLine(poly.getPolygon().getInteriorRingN(i), line, flip);
/* 419 */       if (this.isDone)
/*     */         return; 
/*     */     } 
/*     */   }
/*     */   
/*     */   private Coordinate intersection(PlanarPolygon3D poly, LineString line) {
/* 424 */     CoordinateSequence seq = line.getCoordinateSequence();
/* 425 */     if (seq.size() == 0)
/* 426 */       return null; 
/* 429 */     Coordinate p0 = new Coordinate();
/* 430 */     seq.getCoordinate(0, p0);
/* 431 */     double d0 = poly.getPlane().orientedDistance(p0);
/* 434 */     Coordinate p1 = new Coordinate();
/* 435 */     for (int i = 0; i < seq.size() - 1; i++) {
/* 436 */       seq.getCoordinate(i, p0);
/* 437 */       seq.getCoordinate(i + 1, p1);
/* 438 */       double d1 = poly.getPlane().orientedDistance(p1);
/* 444 */       if (d0 * d1 <= 0.0D) {
/* 454 */         Coordinate intPt = segmentPoint(p0, p1, d0, d1);
/* 456 */         if (poly.intersects(intPt))
/* 457 */           return intPt; 
/* 461 */         d0 = d1;
/*     */       } 
/*     */     } 
/* 463 */     return null;
/*     */   }
/*     */   
/*     */   private void computeMinDistancePolygonPoint(PlanarPolygon3D polyPlane, Point point, boolean flip) {
/* 468 */     Coordinate pt = point.getCoordinate();
/* 470 */     LineString shell = polyPlane.getPolygon().getExteriorRing();
/* 471 */     if (polyPlane.intersects(pt, shell)) {
/* 474 */       int nHole = polyPlane.getPolygon().getNumInteriorRing();
/* 475 */       for (int i = 0; i < nHole; i++) {
/* 476 */         LineString hole = polyPlane.getPolygon().getInteriorRingN(i);
/* 477 */         if (polyPlane.intersects(pt, hole)) {
/* 478 */           computeMinDistanceLinePoint(hole, point, flip);
/*     */           return;
/*     */         } 
/*     */       } 
/* 484 */       double dist = Math.abs(polyPlane.getPlane().orientedDistance(pt));
/* 485 */       updateDistance(dist, new GeometryLocation((Geometry)polyPlane.getPolygon(), 0, pt), new GeometryLocation((Geometry)point, 0, pt), flip);
/*     */     } 
/* 492 */     computeMinDistanceLinePoint(shell, point, flip);
/*     */   }
/*     */   
/*     */   private void computeMinDistanceLineLine(LineString line0, LineString line1, boolean flip) {
/* 497 */     Coordinate[] coord0 = line0.getCoordinates();
/* 498 */     Coordinate[] coord1 = line1.getCoordinates();
/* 500 */     for (int i = 0; i < coord0.length - 1; i++) {
/* 501 */       for (int j = 0; j < coord1.length - 1; j++) {
/* 502 */         double dist = CGAlgorithms3D.distanceSegmentSegment(coord0[i], coord0[i + 1], coord1[j], coord1[j + 1]);
/* 504 */         if (dist < this.minDistance) {
/* 505 */           this.minDistance = dist;
/* 507 */           LineSegment seg0 = new LineSegment(coord0[i], coord0[i + 1]);
/* 508 */           LineSegment seg1 = new LineSegment(coord1[j], coord1[j + 1]);
/* 509 */           Coordinate[] closestPt = seg0.closestPoints(seg1);
/* 510 */           updateDistance(dist, new GeometryLocation((Geometry)line0, i, closestPt[0]), new GeometryLocation((Geometry)line1, j, closestPt[1]), flip);
/*     */         } 
/* 516 */         if (this.isDone)
/*     */           return; 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeMinDistanceLinePoint(LineString line, Point point, boolean flip) {
/* 523 */     Coordinate[] lineCoord = line.getCoordinates();
/* 524 */     Coordinate coord = point.getCoordinate();
/* 526 */     for (int i = 0; i < lineCoord.length - 1; i++) {
/* 527 */       double dist = CGAlgorithms3D.distancePointSegment(coord, lineCoord[i], lineCoord[i + 1]);
/* 529 */       if (dist < this.minDistance) {
/* 530 */         LineSegment seg = new LineSegment(lineCoord[i], lineCoord[i + 1]);
/* 531 */         Coordinate segClosestPoint = seg.closestPoint(coord);
/* 532 */         updateDistance(dist, new GeometryLocation((Geometry)line, i, segClosestPoint), new GeometryLocation((Geometry)point, 0, coord), flip);
/*     */       } 
/* 537 */       if (this.isDone)
/*     */         return; 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeMinDistancePointPoint(Point point0, Point point1, boolean flip) {
/* 542 */     double dist = CGAlgorithms3D.distance(point0.getCoordinate(), point1.getCoordinate());
/* 545 */     if (dist < this.minDistance)
/* 546 */       updateDistance(dist, new GeometryLocation((Geometry)point0, 0, point0.getCoordinate()), new GeometryLocation((Geometry)point1, 0, point1.getCoordinate()), flip); 
/*     */   }
/*     */   
/*     */   private static Coordinate segmentPoint(Coordinate p0, Coordinate p1, double d0, double d1) {
/* 570 */     if (d0 <= 0.0D)
/* 570 */       return new Coordinate(p0); 
/* 571 */     if (d1 <= 0.0D)
/* 571 */       return new Coordinate(p1); 
/* 573 */     double f = Math.abs(d0) / (Math.abs(d0) + Math.abs(d1));
/* 574 */     double intx = p0.x + f * (p1.x - p0.x);
/* 575 */     double inty = p0.y + f * (p1.y - p0.y);
/* 576 */     double intz = p0.z + f * (p1.z - p0.z);
/* 577 */     return new Coordinate(intx, inty, intz);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\distance3d\Distance3DOp.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package com.vividsolutions.jts.operation.distance;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.CGAlgorithms;
/*     */ import com.vividsolutions.jts.algorithm.PointLocator;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.LineSegment;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.Point;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import com.vividsolutions.jts.geom.util.LinearComponentExtracter;
/*     */ import com.vividsolutions.jts.geom.util.PointExtracter;
/*     */ import com.vividsolutions.jts.geom.util.PolygonExtracter;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DistanceOp {
/*     */   private Geometry[] geom;
/*     */   
/*     */   public static double distance(Geometry g0, Geometry g1) {
/*  68 */     DistanceOp distOp = new DistanceOp(g0, g1);
/*  69 */     return distOp.distance();
/*     */   }
/*     */   
/*     */   public static boolean isWithinDistance(Geometry g0, Geometry g1, double distance) {
/*  81 */     DistanceOp distOp = new DistanceOp(g0, g1, distance);
/*  82 */     return (distOp.distance() <= distance);
/*     */   }
/*     */   
/*     */   public static Coordinate[] nearestPoints(Geometry g0, Geometry g1) {
/*  95 */     DistanceOp distOp = new DistanceOp(g0, g1);
/*  96 */     return distOp.nearestPoints();
/*     */   }
/*     */   
/*     */   public static Coordinate[] closestPoints(Geometry g0, Geometry g1) {
/* 110 */     DistanceOp distOp = new DistanceOp(g0, g1);
/* 111 */     return distOp.nearestPoints();
/*     */   }
/*     */   
/* 116 */   private double terminateDistance = 0.0D;
/*     */   
/* 118 */   private PointLocator ptLocator = new PointLocator();
/*     */   
/*     */   private GeometryLocation[] minDistanceLocation;
/*     */   
/* 120 */   private double minDistance = Double.MAX_VALUE;
/*     */   
/*     */   public DistanceOp(Geometry g0, Geometry g1) {
/* 130 */     this(g0, g1, 0.0D);
/*     */   }
/*     */   
/*     */   public DistanceOp(Geometry g0, Geometry g1, double terminateDistance) {
/* 142 */     this.geom = new Geometry[2];
/* 143 */     this.geom[0] = g0;
/* 144 */     this.geom[1] = g1;
/* 145 */     this.terminateDistance = terminateDistance;
/*     */   }
/*     */   
/*     */   public double distance() {
/* 157 */     if (this.geom[0] == null || this.geom[1] == null)
/* 158 */       throw new IllegalArgumentException("null geometries are not supported"); 
/* 159 */     if (this.geom[0].isEmpty() || this.geom[1].isEmpty())
/* 160 */       return 0.0D; 
/* 162 */     computeMinDistance();
/* 163 */     return this.minDistance;
/*     */   }
/*     */   
/*     */   public Coordinate[] nearestPoints() {
/* 174 */     computeMinDistance();
/* 175 */     Coordinate[] nearestPts = { this.minDistanceLocation[0].getCoordinate(), this.minDistanceLocation[1].getCoordinate() };
/* 179 */     return nearestPts;
/*     */   }
/*     */   
/*     */   public Coordinate[] closestPoints() {
/* 189 */     return nearestPoints();
/*     */   }
/*     */   
/*     */   public GeometryLocation[] nearestLocations() {
/* 200 */     computeMinDistance();
/* 201 */     return this.minDistanceLocation;
/*     */   }
/*     */   
/*     */   public GeometryLocation[] closestLocations() {
/* 211 */     return nearestLocations();
/*     */   }
/*     */   
/*     */   private void updateMinDistance(GeometryLocation[] locGeom, boolean flip) {
/* 217 */     if (locGeom[0] == null)
/*     */       return; 
/* 219 */     if (flip) {
/* 220 */       this.minDistanceLocation[0] = locGeom[1];
/* 221 */       this.minDistanceLocation[1] = locGeom[0];
/*     */     } else {
/* 224 */       this.minDistanceLocation[0] = locGeom[0];
/* 225 */       this.minDistanceLocation[1] = locGeom[1];
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeMinDistance() {
/* 232 */     if (this.minDistanceLocation != null)
/*     */       return; 
/* 234 */     this.minDistanceLocation = new GeometryLocation[2];
/* 235 */     computeContainmentDistance();
/* 236 */     if (this.minDistance <= this.terminateDistance)
/*     */       return; 
/* 237 */     computeFacetDistance();
/*     */   }
/*     */   
/*     */   private void computeContainmentDistance() {
/* 242 */     GeometryLocation[] locPtPoly = new GeometryLocation[2];
/* 244 */     computeContainmentDistance(0, locPtPoly);
/* 245 */     if (this.minDistance <= this.terminateDistance)
/*     */       return; 
/* 246 */     computeContainmentDistance(1, locPtPoly);
/*     */   }
/*     */   
/*     */   private void computeContainmentDistance(int polyGeomIndex, GeometryLocation[] locPtPoly) {
/* 251 */     int locationsIndex = 1 - polyGeomIndex;
/* 252 */     List polys = PolygonExtracter.getPolygons(this.geom[polyGeomIndex]);
/* 253 */     if (polys.size() > 0) {
/* 254 */       List insideLocs = ConnectedElementLocationFilter.getLocations(this.geom[locationsIndex]);
/* 255 */       computeContainmentDistance(insideLocs, polys, locPtPoly);
/* 256 */       if (this.minDistance <= this.terminateDistance) {
/* 258 */         this.minDistanceLocation[locationsIndex] = locPtPoly[0];
/* 259 */         this.minDistanceLocation[polyGeomIndex] = locPtPoly[1];
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeContainmentDistance(List<GeometryLocation> locs, List<Polygon> polys, GeometryLocation[] locPtPoly) {
/* 267 */     for (int i = 0; i < locs.size(); i++) {
/* 268 */       GeometryLocation loc = locs.get(i);
/* 269 */       for (int j = 0; j < polys.size(); j++) {
/* 270 */         computeContainmentDistance(loc, polys.get(j), locPtPoly);
/* 271 */         if (this.minDistance <= this.terminateDistance)
/*     */           return; 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeContainmentDistance(GeometryLocation ptLoc, Polygon poly, GeometryLocation[] locPtPoly) {
/* 280 */     Coordinate pt = ptLoc.getCoordinate();
/* 282 */     if (2 != this.ptLocator.locate(pt, (Geometry)poly)) {
/* 283 */       this.minDistance = 0.0D;
/* 284 */       locPtPoly[0] = ptLoc;
/* 285 */       locPtPoly[1] = new GeometryLocation((Geometry)poly, pt);
/*     */       return;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeFacetDistance() {
/* 297 */     GeometryLocation[] locGeom = new GeometryLocation[2];
/* 303 */     List lines0 = LinearComponentExtracter.getLines(this.geom[0]);
/* 304 */     List lines1 = LinearComponentExtracter.getLines(this.geom[1]);
/* 306 */     List pts0 = PointExtracter.getPoints(this.geom[0]);
/* 307 */     List pts1 = PointExtracter.getPoints(this.geom[1]);
/* 310 */     computeMinDistanceLines(lines0, lines1, locGeom);
/* 311 */     updateMinDistance(locGeom, false);
/* 312 */     if (this.minDistance <= this.terminateDistance)
/*     */       return; 
/* 314 */     locGeom[0] = null;
/* 315 */     locGeom[1] = null;
/* 316 */     computeMinDistanceLinesPoints(lines0, pts1, locGeom);
/* 317 */     updateMinDistance(locGeom, false);
/* 318 */     if (this.minDistance <= this.terminateDistance)
/*     */       return; 
/* 320 */     locGeom[0] = null;
/* 321 */     locGeom[1] = null;
/* 322 */     computeMinDistanceLinesPoints(lines1, pts0, locGeom);
/* 323 */     updateMinDistance(locGeom, true);
/* 324 */     if (this.minDistance <= this.terminateDistance)
/*     */       return; 
/* 326 */     locGeom[0] = null;
/* 327 */     locGeom[1] = null;
/* 328 */     computeMinDistancePoints(pts0, pts1, locGeom);
/* 329 */     updateMinDistance(locGeom, false);
/*     */   }
/*     */   
/*     */   private void computeMinDistanceLines(List<LineString> lines0, List<LineString> lines1, GeometryLocation[] locGeom) {
/* 334 */     for (int i = 0; i < lines0.size(); i++) {
/* 335 */       LineString line0 = lines0.get(i);
/* 336 */       for (int j = 0; j < lines1.size(); j++) {
/* 337 */         LineString line1 = lines1.get(j);
/* 338 */         computeMinDistance(line0, line1, locGeom);
/* 339 */         if (this.minDistance <= this.terminateDistance)
/*     */           return; 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeMinDistancePoints(List<Point> points0, List<Point> points1, GeometryLocation[] locGeom) {
/* 346 */     for (int i = 0; i < points0.size(); i++) {
/* 347 */       Point pt0 = points0.get(i);
/* 348 */       for (int j = 0; j < points1.size(); j++) {
/* 349 */         Point pt1 = points1.get(j);
/* 350 */         double dist = pt0.getCoordinate().distance(pt1.getCoordinate());
/* 351 */         if (dist < this.minDistance) {
/* 352 */           this.minDistance = dist;
/* 353 */           locGeom[0] = new GeometryLocation((Geometry)pt0, 0, pt0.getCoordinate());
/* 354 */           locGeom[1] = new GeometryLocation((Geometry)pt1, 0, pt1.getCoordinate());
/*     */         } 
/* 356 */         if (this.minDistance <= this.terminateDistance)
/*     */           return; 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeMinDistanceLinesPoints(List<LineString> lines, List<Point> points, GeometryLocation[] locGeom) {
/* 364 */     for (int i = 0; i < lines.size(); i++) {
/* 365 */       LineString line = lines.get(i);
/* 366 */       for (int j = 0; j < points.size(); j++) {
/* 367 */         Point pt = points.get(j);
/* 368 */         computeMinDistance(line, pt, locGeom);
/* 369 */         if (this.minDistance <= this.terminateDistance)
/*     */           return; 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeMinDistance(LineString line0, LineString line1, GeometryLocation[] locGeom) {
/* 377 */     if (line0.getEnvelopeInternal().distance(line1.getEnvelopeInternal()) > this.minDistance)
/*     */       return; 
/* 380 */     Coordinate[] coord0 = line0.getCoordinates();
/* 381 */     Coordinate[] coord1 = line1.getCoordinates();
/* 383 */     for (int i = 0; i < coord0.length - 1; i++) {
/* 384 */       for (int j = 0; j < coord1.length - 1; j++) {
/* 385 */         double dist = CGAlgorithms.distanceLineLine(coord0[i], coord0[i + 1], coord1[j], coord1[j + 1]);
/* 388 */         if (dist < this.minDistance) {
/* 389 */           this.minDistance = dist;
/* 390 */           LineSegment seg0 = new LineSegment(coord0[i], coord0[i + 1]);
/* 391 */           LineSegment seg1 = new LineSegment(coord1[j], coord1[j + 1]);
/* 392 */           Coordinate[] closestPt = seg0.closestPoints(seg1);
/* 393 */           locGeom[0] = new GeometryLocation((Geometry)line0, i, closestPt[0]);
/* 394 */           locGeom[1] = new GeometryLocation((Geometry)line1, j, closestPt[1]);
/*     */         } 
/* 396 */         if (this.minDistance <= this.terminateDistance)
/*     */           return; 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeMinDistance(LineString line, Point pt, GeometryLocation[] locGeom) {
/* 404 */     if (line.getEnvelopeInternal().distance(pt.getEnvelopeInternal()) > this.minDistance)
/*     */       return; 
/* 407 */     Coordinate[] coord0 = line.getCoordinates();
/* 408 */     Coordinate coord = pt.getCoordinate();
/* 410 */     for (int i = 0; i < coord0.length - 1; i++) {
/* 411 */       double dist = CGAlgorithms.distancePointLine(coord, coord0[i], coord0[i + 1]);
/* 413 */       if (dist < this.minDistance) {
/* 414 */         this.minDistance = dist;
/* 415 */         LineSegment seg = new LineSegment(coord0[i], coord0[i + 1]);
/* 416 */         Coordinate segClosestPoint = seg.closestPoint(coord);
/* 417 */         locGeom[0] = new GeometryLocation((Geometry)line, i, segClosestPoint);
/* 418 */         locGeom[1] = new GeometryLocation((Geometry)pt, 0, coord);
/*     */       } 
/* 420 */       if (this.minDistance <= this.terminateDistance)
/*     */         return; 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\distance\DistanceOp.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
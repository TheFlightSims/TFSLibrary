/*     */ package com.vividsolutions.jts.operation.valid;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.CGAlgorithms;
/*     */ import com.vividsolutions.jts.algorithm.LineIntersector;
/*     */ import com.vividsolutions.jts.algorithm.MCPointInRing;
/*     */ import com.vividsolutions.jts.algorithm.RobustLineIntersector;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryCollection;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.LinearRing;
/*     */ import com.vividsolutions.jts.geom.MultiPoint;
/*     */ import com.vividsolutions.jts.geom.MultiPolygon;
/*     */ import com.vividsolutions.jts.geom.Point;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import com.vividsolutions.jts.geomgraph.Edge;
/*     */ import com.vividsolutions.jts.geomgraph.EdgeIntersection;
/*     */ import com.vividsolutions.jts.geomgraph.EdgeIntersectionList;
/*     */ import com.vividsolutions.jts.geomgraph.GeometryGraph;
/*     */ import com.vividsolutions.jts.util.Assert;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ 
/*     */ public class IsValidOp {
/*     */   private Geometry parentGeometry;
/*     */   
/*     */   public static boolean isValid(Geometry geom) {
/*  59 */     IsValidOp isValidOp = new IsValidOp(geom);
/*  60 */     return isValidOp.isValid();
/*     */   }
/*     */   
/*     */   public static boolean isValid(Coordinate coord) {
/*  73 */     if (Double.isNaN(coord.x))
/*  73 */       return false; 
/*  74 */     if (Double.isInfinite(coord.x))
/*  74 */       return false; 
/*  75 */     if (Double.isNaN(coord.y))
/*  75 */       return false; 
/*  76 */     if (Double.isInfinite(coord.y))
/*  76 */       return false; 
/*  77 */     return true;
/*     */   }
/*     */   
/*     */   public static Coordinate findPtNotNode(Coordinate[] testCoords, LinearRing searchRing, GeometryGraph graph) {
/*  91 */     Edge searchEdge = graph.findEdge((LineString)searchRing);
/*  93 */     EdgeIntersectionList eiList = searchEdge.getEdgeIntersectionList();
/*  95 */     for (int i = 0; i < testCoords.length; i++) {
/*  96 */       Coordinate pt = testCoords[i];
/*  97 */       if (!eiList.isIntersection(pt))
/*  98 */         return pt; 
/*     */     } 
/* 100 */     return null;
/*     */   }
/*     */   
/*     */   private boolean isSelfTouchingRingFormingHoleValid = false;
/*     */   
/*     */   private TopologyValidationError validErr;
/*     */   
/*     */   public IsValidOp(Geometry parentGeometry) {
/* 113 */     this.parentGeometry = parentGeometry;
/*     */   }
/*     */   
/*     */   public void setSelfTouchingRingFormingHoleValid(boolean isValid) {
/* 142 */     this.isSelfTouchingRingFormingHoleValid = isValid;
/*     */   }
/*     */   
/*     */   public boolean isValid() {
/* 153 */     checkValid(this.parentGeometry);
/* 154 */     return (this.validErr == null);
/*     */   }
/*     */   
/*     */   public TopologyValidationError getValidationError() {
/* 167 */     checkValid(this.parentGeometry);
/* 168 */     return this.validErr;
/*     */   }
/*     */   
/*     */   private void checkValid(Geometry g) {
/* 173 */     this.validErr = null;
/* 176 */     if (g.isEmpty())
/*     */       return; 
/* 178 */     if (g instanceof Point) {
/* 178 */       checkValid((Point)g);
/* 179 */     } else if (g instanceof MultiPoint) {
/* 179 */       checkValid((MultiPoint)g);
/* 181 */     } else if (g instanceof LinearRing) {
/* 181 */       checkValid((LinearRing)g);
/* 182 */     } else if (g instanceof LineString) {
/* 182 */       checkValid((LineString)g);
/* 183 */     } else if (g instanceof Polygon) {
/* 183 */       checkValid((Polygon)g);
/* 184 */     } else if (g instanceof MultiPolygon) {
/* 184 */       checkValid((MultiPolygon)g);
/* 185 */     } else if (g instanceof GeometryCollection) {
/* 185 */       checkValid((GeometryCollection)g);
/*     */     } else {
/* 186 */       throw new UnsupportedOperationException(g.getClass().getName());
/*     */     } 
/*     */   }
/*     */   
/*     */   private void checkValid(Point g) {
/* 194 */     checkInvalidCoordinates(g.getCoordinates());
/*     */   }
/*     */   
/*     */   private void checkValid(MultiPoint g) {
/* 201 */     checkInvalidCoordinates(g.getCoordinates());
/*     */   }
/*     */   
/*     */   private void checkValid(LineString g) {
/* 209 */     checkInvalidCoordinates(g.getCoordinates());
/* 210 */     if (this.validErr != null)
/*     */       return; 
/* 211 */     GeometryGraph graph = new GeometryGraph(0, (Geometry)g);
/* 212 */     checkTooFewPoints(graph);
/*     */   }
/*     */   
/*     */   private void checkValid(LinearRing g) {
/* 219 */     checkInvalidCoordinates(g.getCoordinates());
/* 220 */     if (this.validErr != null)
/*     */       return; 
/* 221 */     checkClosedRing(g);
/* 222 */     if (this.validErr != null)
/*     */       return; 
/* 224 */     GeometryGraph graph = new GeometryGraph(0, (Geometry)g);
/* 225 */     checkTooFewPoints(graph);
/* 226 */     if (this.validErr != null)
/*     */       return; 
/* 227 */     RobustLineIntersector robustLineIntersector = new RobustLineIntersector();
/* 228 */     graph.computeSelfNodes((LineIntersector)robustLineIntersector, true);
/* 229 */     checkNoSelfIntersectingRings(graph);
/*     */   }
/*     */   
/*     */   private void checkValid(Polygon g) {
/* 238 */     checkInvalidCoordinates(g);
/* 239 */     if (this.validErr != null)
/*     */       return; 
/* 240 */     checkClosedRings(g);
/* 241 */     if (this.validErr != null)
/*     */       return; 
/* 243 */     GeometryGraph graph = new GeometryGraph(0, (Geometry)g);
/* 245 */     checkTooFewPoints(graph);
/* 246 */     if (this.validErr != null)
/*     */       return; 
/* 247 */     checkConsistentArea(graph);
/* 248 */     if (this.validErr != null)
/*     */       return; 
/* 250 */     if (!this.isSelfTouchingRingFormingHoleValid) {
/* 251 */       checkNoSelfIntersectingRings(graph);
/* 252 */       if (this.validErr != null)
/*     */         return; 
/*     */     } 
/* 254 */     checkHolesInShell(g, graph);
/* 255 */     if (this.validErr != null)
/*     */       return; 
/* 257 */     checkHolesNotNested(g, graph);
/* 258 */     if (this.validErr != null)
/*     */       return; 
/* 259 */     checkConnectedInteriors(graph);
/*     */   }
/*     */   
/*     */   private void checkValid(MultiPolygon g) {
/* 264 */     for (int i = 0; i < g.getNumGeometries(); i++) {
/* 265 */       Polygon p = (Polygon)g.getGeometryN(i);
/* 266 */       checkInvalidCoordinates(p);
/* 267 */       if (this.validErr != null)
/*     */         return; 
/* 268 */       checkClosedRings(p);
/* 269 */       if (this.validErr != null)
/*     */         return; 
/*     */     } 
/* 272 */     GeometryGraph graph = new GeometryGraph(0, (Geometry)g);
/* 274 */     checkTooFewPoints(graph);
/* 275 */     if (this.validErr != null)
/*     */       return; 
/* 276 */     checkConsistentArea(graph);
/* 277 */     if (this.validErr != null)
/*     */       return; 
/* 278 */     if (!this.isSelfTouchingRingFormingHoleValid) {
/* 279 */       checkNoSelfIntersectingRings(graph);
/* 280 */       if (this.validErr != null)
/*     */         return; 
/*     */     } 
/*     */     int j;
/* 282 */     for (j = 0; j < g.getNumGeometries(); j++) {
/* 283 */       Polygon p = (Polygon)g.getGeometryN(j);
/* 284 */       checkHolesInShell(p, graph);
/* 285 */       if (this.validErr != null)
/*     */         return; 
/*     */     } 
/* 287 */     for (j = 0; j < g.getNumGeometries(); j++) {
/* 288 */       Polygon p = (Polygon)g.getGeometryN(j);
/* 289 */       checkHolesNotNested(p, graph);
/* 290 */       if (this.validErr != null)
/*     */         return; 
/*     */     } 
/* 292 */     checkShellsNotNested(g, graph);
/* 293 */     if (this.validErr != null)
/*     */       return; 
/* 294 */     checkConnectedInteriors(graph);
/*     */   }
/*     */   
/*     */   private void checkValid(GeometryCollection gc) {
/* 299 */     for (int i = 0; i < gc.getNumGeometries(); i++) {
/* 300 */       Geometry g = gc.getGeometryN(i);
/* 301 */       checkValid(g);
/* 302 */       if (this.validErr != null)
/*     */         return; 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void checkInvalidCoordinates(Coordinate[] coords) {
/* 308 */     for (int i = 0; i < coords.length; i++) {
/* 309 */       if (!isValid(coords[i])) {
/* 310 */         this.validErr = new TopologyValidationError(10, coords[i]);
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void checkInvalidCoordinates(Polygon poly) {
/* 320 */     checkInvalidCoordinates(poly.getExteriorRing().getCoordinates());
/* 321 */     if (this.validErr != null)
/*     */       return; 
/* 322 */     for (int i = 0; i < poly.getNumInteriorRing(); i++) {
/* 323 */       checkInvalidCoordinates(poly.getInteriorRingN(i).getCoordinates());
/* 324 */       if (this.validErr != null)
/*     */         return; 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void checkClosedRings(Polygon poly) {
/* 330 */     checkClosedRing((LinearRing)poly.getExteriorRing());
/* 331 */     if (this.validErr != null)
/*     */       return; 
/* 332 */     for (int i = 0; i < poly.getNumInteriorRing(); i++) {
/* 333 */       checkClosedRing((LinearRing)poly.getInteriorRingN(i));
/* 334 */       if (this.validErr != null)
/*     */         return; 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void checkClosedRing(LinearRing ring) {
/* 340 */     if (!ring.isClosed()) {
/* 341 */       Coordinate pt = null;
/* 342 */       if (ring.getNumPoints() >= 1)
/* 343 */         pt = ring.getCoordinateN(0); 
/* 344 */       this.validErr = new TopologyValidationError(11, pt);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void checkTooFewPoints(GeometryGraph graph) {
/* 352 */     if (graph.hasTooFewPoints()) {
/* 353 */       this.validErr = new TopologyValidationError(9, graph.getInvalidPoint());
/*     */       return;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void checkConsistentArea(GeometryGraph graph) {
/* 370 */     ConsistentAreaTester cat = new ConsistentAreaTester(graph);
/* 371 */     boolean isValidArea = cat.isNodeConsistentArea();
/* 372 */     if (!isValidArea) {
/* 373 */       this.validErr = new TopologyValidationError(5, cat.getInvalidPoint());
/*     */       return;
/*     */     } 
/* 378 */     if (cat.hasDuplicateRings())
/* 379 */       this.validErr = new TopologyValidationError(8, cat.getInvalidPoint()); 
/*     */   }
/*     */   
/*     */   private void checkNoSelfIntersectingRings(GeometryGraph graph) {
/* 394 */     for (Iterator<Edge> i = graph.getEdgeIterator(); i.hasNext(); ) {
/* 395 */       Edge e = i.next();
/* 396 */       checkNoSelfIntersectingRing(e.getEdgeIntersectionList());
/* 397 */       if (this.validErr != null)
/*     */         return; 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void checkNoSelfIntersectingRing(EdgeIntersectionList eiList) {
/* 409 */     Set<Coordinate> nodeSet = new TreeSet();
/* 410 */     boolean isFirst = true;
/* 411 */     for (Iterator<EdgeIntersection> i = eiList.iterator(); i.hasNext(); ) {
/* 412 */       EdgeIntersection ei = i.next();
/* 413 */       if (isFirst) {
/* 414 */         isFirst = false;
/*     */         continue;
/*     */       } 
/* 417 */       if (nodeSet.contains(ei.coord)) {
/* 418 */         this.validErr = new TopologyValidationError(6, ei.coord);
/*     */         return;
/*     */       } 
/* 424 */       nodeSet.add(ei.coord);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void checkHolesInShell(Polygon p, GeometryGraph graph) {
/* 443 */     LinearRing shell = (LinearRing)p.getExteriorRing();
/* 447 */     MCPointInRing mCPointInRing = new MCPointInRing(shell);
/* 449 */     for (int i = 0; i < p.getNumInteriorRing(); i++) {
/* 451 */       LinearRing hole = (LinearRing)p.getInteriorRingN(i);
/* 452 */       Coordinate holePt = findPtNotNode(hole.getCoordinates(), shell, graph);
/* 458 */       if (holePt == null)
/*     */         return; 
/* 460 */       boolean outside = !mCPointInRing.isInside(holePt);
/* 461 */       if (outside) {
/* 462 */         this.validErr = new TopologyValidationError(2, holePt);
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void checkHolesNotNested(Polygon p, GeometryGraph graph) {
/* 484 */     IndexedNestedRingTester nestedTester = new IndexedNestedRingTester(graph);
/* 488 */     for (int i = 0; i < p.getNumInteriorRing(); i++) {
/* 489 */       LinearRing innerHole = (LinearRing)p.getInteriorRingN(i);
/* 490 */       nestedTester.add(innerHole);
/*     */     } 
/* 492 */     boolean isNonNested = nestedTester.isNonNested();
/* 493 */     if (!isNonNested)
/* 494 */       this.validErr = new TopologyValidationError(3, nestedTester.getNestedPoint()); 
/*     */   }
/*     */   
/*     */   private void checkShellsNotNested(MultiPolygon mp, GeometryGraph graph) {
/* 514 */     for (int i = 0; i < mp.getNumGeometries(); i++) {
/* 515 */       Polygon p = (Polygon)mp.getGeometryN(i);
/* 516 */       LinearRing shell = (LinearRing)p.getExteriorRing();
/* 517 */       for (int j = 0; j < mp.getNumGeometries(); j++) {
/* 518 */         if (i != j) {
/* 519 */           Polygon p2 = (Polygon)mp.getGeometryN(j);
/* 520 */           checkShellNotNested(shell, p2, graph);
/* 521 */           if (this.validErr != null)
/*     */             return; 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void checkShellNotNested(LinearRing shell, Polygon p, GeometryGraph graph) {
/* 537 */     Coordinate[] shellPts = shell.getCoordinates();
/* 539 */     LinearRing polyShell = (LinearRing)p.getExteriorRing();
/* 540 */     Coordinate[] polyPts = polyShell.getCoordinates();
/* 541 */     Coordinate shellPt = findPtNotNode(shellPts, polyShell, graph);
/* 543 */     if (shellPt == null)
/*     */       return; 
/* 545 */     boolean insidePolyShell = CGAlgorithms.isPointInRing(shellPt, polyPts);
/* 546 */     if (!insidePolyShell)
/*     */       return; 
/* 549 */     if (p.getNumInteriorRing() <= 0) {
/* 550 */       this.validErr = new TopologyValidationError(7, shellPt);
/*     */       return;
/*     */     } 
/* 562 */     Coordinate badNestedPt = null;
/* 563 */     for (int i = 0; i < p.getNumInteriorRing(); i++) {
/* 564 */       LinearRing hole = (LinearRing)p.getInteriorRingN(i);
/* 565 */       badNestedPt = checkShellInsideHole(shell, hole, graph);
/* 566 */       if (badNestedPt == null)
/*     */         return; 
/*     */     } 
/* 569 */     this.validErr = new TopologyValidationError(7, badNestedPt);
/*     */   }
/*     */   
/*     */   private Coordinate checkShellInsideHole(LinearRing shell, LinearRing hole, GeometryGraph graph) {
/* 585 */     Coordinate[] shellPts = shell.getCoordinates();
/* 586 */     Coordinate[] holePts = hole.getCoordinates();
/* 588 */     Coordinate shellPt = findPtNotNode(shellPts, hole, graph);
/* 590 */     if (shellPt != null) {
/* 591 */       boolean insideHole = CGAlgorithms.isPointInRing(shellPt, holePts);
/* 592 */       if (!insideHole)
/* 593 */         return shellPt; 
/*     */     } 
/* 596 */     Coordinate holePt = findPtNotNode(holePts, shell, graph);
/* 598 */     if (holePt != null) {
/* 599 */       boolean insideShell = CGAlgorithms.isPointInRing(holePt, shellPts);
/* 600 */       if (insideShell)
/* 601 */         return holePt; 
/* 603 */       return null;
/*     */     } 
/* 605 */     Assert.shouldNeverReachHere("points in shell and hole appear to be equal");
/* 606 */     return null;
/*     */   }
/*     */   
/*     */   private void checkConnectedInteriors(GeometryGraph graph) {
/* 611 */     ConnectedInteriorTester cit = new ConnectedInteriorTester(graph);
/* 612 */     if (!cit.isInteriorsConnected())
/* 613 */       this.validErr = new TopologyValidationError(4, cit.getCoordinate()); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\valid\IsValidOp.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package com.world2xplane.XPlane;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryCollection;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.LinearRing;
/*     */ import com.vividsolutions.jts.geom.MultiPolygon;
/*     */ import com.vividsolutions.jts.geom.Point;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import com.vividsolutions.jts.operation.valid.IsValidOp;
/*     */ import com.vividsolutions.jts.operation.valid.TopologyValidationError;
/*     */ import com.vividsolutions.jts.simplify.TopologyPreservingSimplifier;
/*     */ import com.world2xplane.Geom.GeomUtils;
/*     */ import com.world2xplane.Geom.GeometryClipper;
/*     */ import com.world2xplane.Network.Road;
/*     */ import com.world2xplane.Network.RoadNetwork;
/*     */ import com.world2xplane.OSM.OSMPolygon;
/*     */ import com.world2xplane.OSM.OSMShape;
/*     */ import com.world2xplane.Rules.AutogenStringRule;
/*     */ import com.world2xplane.Rules.Rule;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import math.geom2d.Point2D;
/*     */ import math.geom2d.line.LineSegment2D;
/*     */ import math.geom2d.polygon.LinearRing2D;
/*     */ import org.geotools.geometry.jts.FactoryFinder;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class Autogen {
/*     */   private final DSFTile dsfTile;
/*     */   
/*  55 */   private double metreXSize = 0.0D;
/*     */   
/*     */   private RoadNetwork roadNetwork;
/*     */   
/*  58 */   private static Logger log = LoggerFactory.getLogger(Autogen.class);
/*     */   
/*  60 */   private List<OSMShape> autogenLines = new ArrayList<>();
/*     */   
/*  61 */   private List<AutogenObject> autogenObjects = new ArrayList<>();
/*     */   
/*     */   private List<Geometry> clipRoadsFromAutoGen(List<Road> collidingRoads, List<Geometry> roads, Geometry polygon, int sanityCheck) {
/*  66 */     if (sanityCheck > 100) {
/*  67 */       log.error("Can't clip, loop count reached");
/*  68 */       return new ArrayList<>();
/*     */     } 
/*  70 */     List<Geometry> processed = new ArrayList<>();
/*  71 */     Geometry clipped = polygon;
/*  73 */     if (collidingRoads == null || collidingRoads.size() == 0) {
/*  74 */       clipped = GeomUtils.preciseBuffer(clipped, 1.0D, 1, 2);
/*  75 */       TopologyPreservingSimplifier simplifier = new TopologyPreservingSimplifier(clipped);
/*  76 */       simplifier.setDistanceTolerance(this.metreXSize);
/*  77 */       processed.add(simplifier.getResultGeometry());
/*  78 */       return processed;
/*     */     } 
/*  82 */     if (roads == null) {
/*  83 */       List<Geometry> combinedRoads = new ArrayList<>();
/*  84 */       for (Road collidingRoad : collidingRoads) {
/*  85 */         if (collidingRoad.getRoadType() == 0)
/*     */           continue; 
/*  95 */         combinedRoads.add(GeomUtils.preciseBuffer(collidingRoad.getLineString().getGeometry(), collidingRoad.getSize()));
/*     */       } 
/*     */       try {
/* 100 */         Geometry road = combineIntoOneGeometry(combinedRoads);
/* 101 */         collidingRoads.clear();
/* 102 */         if (roads == null)
/* 103 */           roads = new ArrayList<>(); 
/* 105 */         roads.add(road);
/* 106 */         combinedRoads.clear();
/* 107 */       } catch (Exception e) {
/* 109 */         roads = combinedRoads;
/*     */       } 
/*     */     } 
/* 114 */     int count = 0;
/* 115 */     for (Geometry road : roads) {
/* 117 */       if (count % 1000 == 1000)
/* 118 */         log.info(count + " of " + roads.size()); 
/* 120 */       count++;
/* 122 */       if (clipped.getClass().getName().equals("com.vividsolutions.jts.geom.GeometryCollection"))
/* 137 */         clipped = clipped.union(); 
/* 141 */       IsValidOp validOp = new IsValidOp(polygon);
/* 142 */       TopologyValidationError err = validOp.getValidationError();
/* 144 */       if (err != null) {
/* 145 */         clipped = clipped.buffer(0.0D);
/* 146 */         validOp = new IsValidOp(polygon);
/* 147 */         err = validOp.getValidationError();
/* 148 */         if (err != null) {
/* 149 */           log.error(err.getMessage());
/* 150 */           clipped = null;
/*     */           break;
/*     */         } 
/*     */       } 
/*     */       try {
/* 157 */         clipped = clipped.difference(road);
/* 158 */       } catch (Exception e) {
/* 159 */         if (clipped instanceof MultiPolygon || clipped instanceof GeometryCollection) {
/* 160 */           List<Geometry> newCollection = new ArrayList<>();
/* 161 */           GeometryCollection gc = (GeometryCollection)clipped;
/* 162 */           for (int x = 0; x < gc.getNumGeometries(); x++)
/* 163 */             newCollection.add(gc.getGeometryN(x)); 
/* 165 */           for (Geometry item : newCollection) {
/* 166 */             if (item instanceof Polygon || item instanceof MultiPolygon) {
/* 167 */               if (roads.size() == 1) {
/* 169 */                 processed.add(item);
/*     */                 continue;
/*     */               } 
/* 171 */               processed.addAll(clipRoadsFromAutoGen(collidingRoads, roads, item, sanityCheck + 1));
/*     */             } 
/*     */           } 
/* 175 */           clipped = null;
/*     */           break;
/*     */         } 
/*     */         try {
/* 179 */           clipped = clipped.buffer(0.0D).difference(road.buffer(0.0D));
/* 180 */         } catch (Exception e1) {
/* 181 */           e1.printStackTrace();
/*     */         } 
/* 184 */         e.printStackTrace();
/* 185 */         clipped = clipped.union();
/*     */       } 
/*     */     } 
/* 190 */     if (clipped != null) {
/* 191 */       clipped = GeomUtils.preciseBuffer(clipped, 1.0D, 8, 3);
/* 192 */       processed.add(clipped);
/*     */     } 
/* 194 */     return processed;
/*     */   }
/*     */   
/*     */   static Geometry combineIntoOneGeometry(Collection<Geometry> collection) throws Exception {
/* 198 */     GeometryFactory factory = FactoryFinder.getGeometryFactory(null);
/*     */     try {
/* 202 */       Geometry geometryCollection = factory.buildGeometry(collection);
/* 203 */       if (geometryCollection instanceof GeometryCollection)
/* 204 */         return geometryCollection.union(); 
/* 206 */       return geometryCollection;
/* 208 */     } catch (Exception e) {
/* 211 */       Geometry merged = null;
/* 212 */       for (Geometry item : collection) {
/* 213 */         if (merged == null) {
/* 214 */           merged = item;
/*     */           continue;
/*     */         } 
/*     */         try {
/* 217 */           merged = merged.union(item);
/* 218 */         } catch (Exception e1) {
/* 219 */           throw new Exception(e1);
/*     */         } 
/*     */       } 
/* 223 */       return merged;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void process() {
/* 230 */     if (this.roadNetwork == null) {
/* 231 */       log.error("Cannot generate autogen without road network enabled.");
/*     */       return;
/*     */     } 
/* 237 */     List<OSMShape> lines = new ArrayList<>();
/* 238 */     for (OSMShape osmShape : this.autogenLines) {
/* 240 */       AutogenStringRule rule = (AutogenStringRule)osmShape.rule;
/* 241 */       HashSet<String> clipAreas = rule.getClipAreas();
/* 242 */       if (rule.isClipToArea() && osmShape.outer.getNamedAreas() != null && osmShape.outer.getNamedAreas().size() > 0) {
/* 245 */         Geometry area = null;
/*     */         try {
/* 247 */           for (int x = 0; x < osmShape.outer.getNamedAreas().size(); x++) {
/* 248 */             if (clipAreas.contains(((DSFTile.NamedArea)osmShape.outer.getNamedAreas().get(x)).identifier))
/* 249 */               if (area == null) {
/* 250 */                 area = ((DSFTile.NamedArea)osmShape.outer.getNamedAreas().get(x)).outer.getGeometry();
/*     */               } else {
/* 252 */                 area = area.union(((DSFTile.NamedArea)osmShape.outer.getNamedAreas().get(x)).outer.getGeometry());
/*     */               }  
/*     */           } 
/* 256 */           if (area != null)
/* 257 */             area = GeomUtils.preciseBuffer(area, 2.0D); 
/* 258 */         } catch (Exception e) {
/* 259 */           e.printStackTrace();
/*     */         } 
/*     */         try {
/*     */           Geometry geometry;
/* 264 */           LineString lineString = GeomUtils.osmPolygonToJTSLineString(osmShape.outer);
/* 266 */           if (area != null)
/*     */             try {
/* 268 */               geometry = lineString.intersection(area);
/* 269 */             } catch (Exception e1) {
/* 270 */               geometry = geometry.buffer(0.0D).intersection(area.buffer(0.0D));
/*     */             }  
/* 274 */           List<OSMPolygon> shapes = GeomUtils.jtsToOsmPolygon(geometry);
/* 275 */           for (OSMPolygon item : shapes) {
/* 276 */             if (item.vertexNumber() > 1) {
/* 277 */               OSMShape shape = new OSMShape(osmShape.rule);
/* 278 */               shape.outer = item;
/* 279 */               shape.outer.setIdentifier(osmShape.outer.getIdentifier());
/* 280 */               shape.objectDefinitionNumber = osmShape.objectDefinitionNumber;
/* 281 */               lines.add(shape);
/*     */             } 
/*     */           } 
/* 284 */         } catch (Exception e) {
/* 285 */           log.error("", e);
/*     */         } 
/*     */         continue;
/*     */       } 
/* 290 */       lines.add(osmShape);
/*     */     } 
/* 295 */     this.autogenLines.clear();
/* 296 */     this.autogenLines.addAll(lines);
/* 297 */     lines = null;
/* 298 */     AutogenStringRule autogenStringRule = null;
/* 301 */     int count = 0;
/* 302 */     Geometry combined = null;
/* 303 */     List<Geometry> group = new ArrayList<>();
/* 304 */     for (OSMShape item : this.autogenLines) {
/* 306 */       if (item.outer.getIdentifier().longValue() == 31696395L || item.outer.getIdentifier().longValue() == 31696398L || item.outer.getIdentifier().longValue() == 31696399L)
/* 308 */         int bob = 23; 
/* 311 */       if (item.outer.vertexNumber() < 2)
/*     */         continue; 
/* 314 */       if (count % 1000 == 0)
/* 315 */         log.info("Item " + count + " " + this.autogenLines.size()); 
/* 317 */       count++;
/* 318 */       autogenStringRule = (AutogenStringRule)item.rule;
/* 320 */       Geometry buffered = toLineString(item.outer.getGeometry());
/* 321 */       buffered = GeomUtils.preciseBuffer(buffered, 40.0D, 1, 2);
/* 322 */       IsValidOp validOp = new IsValidOp(buffered);
/* 323 */       if (validOp.isValid())
/* 324 */         group.add(buffered); 
/*     */     } 
/* 327 */     log.info("Merging " + group.size() + " objects");
/*     */     try {
/* 330 */       combined = combineIntoOneGeometry(group);
/* 331 */     } catch (Exception e) {
/* 333 */       e.printStackTrace();
/*     */     } 
/* 336 */     log.info("Combined");
/* 339 */     if (combined == null)
/*     */       return; 
/* 344 */     List<OSMPolygon> outerShells = new ArrayList<>();
/* 345 */     count = 0;
/* 347 */     if (combined instanceof MultiPolygon) {
/* 348 */       MultiPolygon multiPolygon = (MultiPolygon)combined;
/* 349 */       for (int x = 0; x < multiPolygon.getNumGeometries(); x++) {
/* 350 */         Polygon polygon = (Polygon)multiPolygon.getGeometryN(x);
/* 351 */         LineString exteriorRing = polygon.getExteriorRing();
/* 352 */         OSMPolygon shell = new OSMPolygon();
/* 353 */         for (int y = 0; y < exteriorRing.getNumPoints(); y++) {
/* 354 */           Point point = exteriorRing.getPointN(y);
/* 355 */           shell.addVertex(new Point2D(point.getX(), point.getY()));
/*     */         } 
/* 357 */         shell.checkAndClose();
/* 358 */         outerShells.add(shell);
/*     */       } 
/* 360 */     } else if (combined instanceof Polygon) {
/* 361 */       Polygon polygon = (Polygon)combined;
/* 362 */       LineString exteriorRing = polygon.getExteriorRing();
/* 363 */       OSMPolygon shell = new OSMPolygon();
/* 364 */       for (int y = 0; y < exteriorRing.getNumPoints(); y++) {
/* 365 */         Point point = exteriorRing.getPointN(y);
/* 366 */         shell.addVertex(new Point2D(point.getX(), point.getY()));
/*     */       } 
/* 368 */       shell.checkAndClose();
/* 369 */       outerShells.add(shell);
/*     */     } 
/* 372 */     List<Geometry> toAdd = new ArrayList<>();
/* 375 */     log.info("Clipping out roads from autogen for " + outerShells.size());
/* 376 */     count = 0;
/* 378 */     for (OSMPolygon item : outerShells) {
/* 379 */       if (count % 1000 == 1000)
/* 380 */         log.info(count + " / " + outerShells.size()); 
/* 382 */       Geometry outer = item.getGeometry();
/* 383 */       List<Road> collidingRoads = (this.roadNetwork != null) ? this.roadNetwork.getCollidingRoads(outer, null) : new ArrayList<>();
/* 384 */       toAdd.addAll(clipRoadsFromAutoGen(collidingRoads, null, outer, 1));
/*     */     } 
/* 388 */     log.info("To process " + toAdd.size());
/* 389 */     outerShells.clear();
/* 392 */     List<Geometry> geometries = new ArrayList<>();
/* 394 */     for (Geometry item : toAdd) {
/* 395 */       IsValidOp validOp = new IsValidOp(item);
/* 396 */       TopologyValidationError err = validOp.getValidationError();
/* 397 */       if (err != null) {
/* 398 */         for (int x = 0; x < item.getNumGeometries(); x++) {
/* 399 */           Geometry geometry = item.getGeometryN(x);
/* 401 */           validOp = new IsValidOp(geometry);
/* 402 */           err = validOp.getValidationError();
/* 403 */           if (err == null) {
/* 404 */             geometries.add(geometry);
/*     */           } else {
/* 407 */             log.error(err.getMessage());
/*     */           } 
/*     */         } 
/*     */         continue;
/*     */       } 
/* 412 */       geometries.add(item);
/*     */     } 
/* 415 */     toAdd.clear();
/* 417 */     Iterator<Geometry> iterator = geometries.iterator();
/* 418 */     while (iterator.hasNext()) {
/* 419 */       Geometry outer = iterator.next();
/* 420 */       Geometry clipped = outer;
/* 422 */       if (clipped == null)
/*     */         continue; 
/* 430 */       if (clipped instanceof MultiPolygon) {
/* 431 */         for (int x = 0; x < clipped.getNumGeometries(); x++) {
/* 432 */           Polygon polygon = (Polygon)clipped.getGeometryN(x);
/* 435 */           LineString exteriorRing = polygon.getExteriorRing();
/* 436 */           OSMPolygon shell = new OSMPolygon();
/* 437 */           for (int y = 0; y < exteriorRing.getNumPoints(); y++) {
/* 438 */             Point point = exteriorRing.getPointN(y);
/* 439 */             shell.addVertex(new Point2D(point.getX(), point.getY()));
/*     */           } 
/* 441 */           if (shell.vertexNumber() > 2) {
/* 442 */             shell.checkAndClose();
/* 444 */             double area = Math.abs(GeomUtils.areaInMeters(shell));
/* 445 */             if (area >= 20.0D)
/* 449 */               outerShells.add(shell); 
/*     */           } 
/*     */         } 
/*     */         continue;
/*     */       } 
/* 453 */       if (clipped instanceof Polygon) {
/* 454 */         Polygon polygon = (Polygon)clipped;
/* 457 */         LineString exteriorRing = polygon.getExteriorRing();
/* 458 */         OSMPolygon shell = new OSMPolygon();
/* 459 */         for (int y = 0; y < exteriorRing.getNumPoints(); y++) {
/* 460 */           Point point = exteriorRing.getPointN(y);
/* 461 */           shell.addVertex(new Point2D(point.getX(), point.getY()));
/*     */         } 
/* 463 */         if (shell.vertexNumber() > 2) {
/* 464 */           shell.checkAndClose();
/* 465 */           OSMShape osmShape = new OSMShape((Rule)autogenStringRule);
/* 466 */           osmShape.setObjectDefinitionNumber(autogenStringRule.getDefinitionNumber(null));
/* 467 */           osmShape.outer = shell;
/* 469 */           double area = Math.abs(GeomUtils.areaInMeters(shell));
/* 470 */           if (area < 25.0D)
/*     */             continue; 
/* 474 */           outerShells.add(shell);
/*     */         } 
/*     */       } 
/*     */     } 
/* 478 */     toAdd.clear();
/* 479 */     geometries.clear();
/* 480 */     this.autogenLines.clear();
/* 482 */     log.info("Clipping to tile");
/* 485 */     List<OSMPolygon> copy = new ArrayList<>();
/* 486 */     copy.addAll(outerShells);
/* 487 */     outerShells.clear();
/* 488 */     for (OSMPolygon osmPolygon : copy) {
/* 489 */       if (!this.dsfTile.fitsInside(osmPolygon)) {
/* 491 */         Map<OSMPolygon, List<OSMPolygon>> rings = GeometryClipper.clip(osmPolygon, null, this.dsfTile.getArea());
/* 492 */         if (rings == null || rings.size() == 0)
/*     */           continue; 
/* 495 */         for (Map.Entry<OSMPolygon, List<OSMPolygon>> item : rings.entrySet()) {
/* 496 */           ((OSMPolygon)item.getKey()).setCounterClockwise();
/* 497 */           outerShells.add(item.getKey());
/*     */         } 
/*     */         continue;
/*     */       } 
/* 500 */       outerShells.add(osmPolygon);
/*     */     } 
/* 503 */     copy.clear();
/* 505 */     log.info("Breaking large polys");
/* 509 */     copy = new ArrayList<>();
/* 510 */     copy.addAll(outerShells);
/* 511 */     outerShells.clear();
/* 512 */     for (OSMPolygon osmPolygon : copy)
/* 513 */       outerShells.addAll(divideLarge(osmPolygon, 200)); 
/* 515 */     copy.clear();
/* 564 */     log.info("Outer shells " + outerShells.size());
/* 566 */     for (OSMPolygon shell : outerShells) {
/* 569 */       shell.setCounterClockwise();
/* 572 */       AutogenObject autogenObject = new AutogenObject(autogenStringRule, shell.getGeometry());
/* 575 */       for (int x = 0; x < shell.vertexNumber() - 1; x++) {
/* 576 */         Point2D p1 = shell.vertex(x);
/* 577 */         Point2D p2 = shell.vertex(x + 1);
/* 578 */         LineString lineSegment = GeomUtils.geometryFactory.createLineString(new Coordinate[] { new Coordinate(p1.getX(), p1.getY()), new Coordinate(p2.getX(), p2.getY()) });
/* 580 */         Point centrePoint = lineSegment.getCentroid();
/* 581 */         Point2D centre = new Point2D(centrePoint.getX(), centrePoint.getY());
/* 582 */         double bearing = GeomUtils.getBearing(p1, p2);
/* 583 */         double b1 = bearing + 90.0D;
/* 584 */         double b2 = bearing - 90.0D;
/* 587 */         Point2D l1 = GeomUtils.translateLatLon(centre, b1, 11.0D);
/* 588 */         Point2D l2 = GeomUtils.translateLatLon(centre, b2, 11.0D);
/* 589 */         LineString rotated = GeomUtils.geometryFactory.createLineString(new Coordinate[] { new Coordinate(l1.getX(), l1.getY()), new Coordinate(l2.getX(), l2.getY()) });
/* 591 */         List<Road> collidingRoads = this.roadNetwork.getCollidingRoads((Geometry)rotated, new RoadNetwork.RoadCollisionCheck() {
/*     */               public boolean ignoreRoad(Road road) {
/* 594 */                 if (road.getRoadType() == Road.RAIL_PRIMARY || road.getRoadType() == Road.RAIL_SECONDARY || road.getRoadType() == Road.RAIL_TERTIARY)
/* 597 */                   return false; 
/* 599 */                 return true;
/*     */               }
/*     */             });
/* 602 */         for (Road road : collidingRoads)
/* 603 */           autogenObject.addRoadType(road.getRoadType()); 
/* 605 */         autogenObject.addEdge(new AutogenObject.Edge(p1, p2, (collidingRoads.size() > 0)));
/*     */       } 
/* 610 */       autogenObject.buildContours(this.dsfTile);
/* 611 */       if (autogenObject.getOuterContours().size() > 80 || autogenObject.getInnerContours().size() > 80) {
/* 612 */         log.info(autogenObject.getOuterContours().size() + " " + autogenObject.getInnerContours().size());
/*     */         continue;
/*     */       } 
/* 614 */       this.autogenObjects.add(autogenObject);
/*     */     } 
/*     */   }
/*     */   
/*     */   private Geometry toLineString(Geometry geometry) {
/* 619 */     if (geometry instanceof LineString)
/* 620 */       return geometry; 
/* 622 */     if (geometry instanceof Polygon) {
/* 623 */       Polygon polygon = (Polygon)geometry;
/* 624 */       return (Geometry)polygon.getExteriorRing();
/*     */     } 
/* 626 */     if (geometry instanceof LinearRing) {
/* 627 */       LinearRing polygon = (LinearRing)geometry;
/* 628 */       return polygon.getBoundary();
/*     */     } 
/* 630 */     return geometry;
/*     */   }
/*     */   
/*     */   private List<Geometry> clipAreas(List<Geometry> geometries, int sanityCheck) {
/* 635 */     if (sanityCheck > 100) {
/* 636 */       log.error("Can't clip, loop count reached");
/* 637 */       return new ArrayList<>();
/*     */     } 
/* 640 */     List<Geometry> processed = new ArrayList<>();
/* 642 */     for (Geometry polygon : geometries) {
/* 643 */       Geometry clipped = polygon;
/* 645 */       List<DSFTile.NamedArea> namedAreas = this.dsfTile.getAreaTree().query(new Envelope(polygon.getEnvelopeInternal()));
/* 646 */       if (namedAreas == null || namedAreas.size() == 0)
/* 647 */         processed.add(polygon); 
/* 650 */       for (DSFTile.NamedArea namedArea : namedAreas) {
/* 652 */         if (!namedArea.identifier.equals("water"))
/*     */           continue; 
/* 656 */         if (clipped.getClass().getName().equals("com.vividsolutions.jts.geom.GeometryCollection")) {
/* 658 */           List<Geometry> newCollection = new ArrayList<>();
/* 659 */           GeometryCollection gc = (GeometryCollection)clipped;
/* 660 */           for (int x = 0; x < gc.getNumGeometries(); x++)
/* 661 */             newCollection.add(gc.getGeometryN(x)); 
/* 663 */           for (Geometry item : newCollection) {
/* 664 */             if (item instanceof Polygon || item instanceof MultiPolygon) {
/* 665 */               ArrayList<Geometry> geo = new ArrayList<>();
/* 666 */               geo.add(item);
/* 667 */               processed.addAll(clipAreas(geo, sanityCheck + 1));
/*     */             } 
/*     */           } 
/* 670 */           clipped = null;
/*     */           break;
/*     */         } 
/* 675 */         IsValidOp validOp = new IsValidOp(polygon);
/* 676 */         TopologyValidationError err = validOp.getValidationError();
/* 678 */         if (err != null) {
/* 679 */           clipped = clipped.buffer(0.0D);
/* 680 */           validOp = new IsValidOp(polygon);
/* 681 */           err = validOp.getValidationError();
/* 682 */           if (err != null) {
/* 683 */             log.error(err.getMessage());
/* 684 */             clipped = null;
/*     */             break;
/*     */           } 
/*     */         } 
/*     */         try {
/* 692 */           Geometry geo = clipped.difference(namedArea.preparedGeometryOuter.getGeometry());
/* 693 */           clipped = geo;
/* 694 */         } catch (Exception e) {
/* 695 */           if (clipped instanceof MultiPolygon) {
/* 696 */             List<Geometry> newCollection = new ArrayList<>();
/* 697 */             GeometryCollection gc = (GeometryCollection)clipped;
/* 698 */             for (int x = 0; x < gc.getNumGeometries(); x++)
/* 699 */               newCollection.add(gc.getGeometryN(x)); 
/* 701 */             for (Geometry item : newCollection) {
/* 702 */               if (item instanceof Polygon || item instanceof MultiPolygon) {
/* 703 */                 ArrayList<Geometry> geo = new ArrayList<>();
/* 704 */                 geo.add(item);
/* 705 */                 processed.addAll(clipAreas(geo, sanityCheck + 1));
/*     */               } 
/*     */             } 
/* 708 */             clipped = null;
/*     */             break;
/*     */           } 
/* 711 */           e.printStackTrace();
/*     */         } 
/*     */       } 
/* 717 */       if (clipped != null) {
/* 718 */         TopologyPreservingSimplifier simplifier = new TopologyPreservingSimplifier(clipped);
/* 719 */         simplifier.setDistanceTolerance(this.metreXSize);
/* 720 */         processed.add(simplifier.getResultGeometry());
/*     */       } 
/*     */     } 
/* 723 */     return processed;
/*     */   }
/*     */   
/*     */   private List<OSMPolygon> geometryToPolygon(Geometry geometry) {
/* 728 */     List<OSMPolygon> outerShells = new ArrayList<>();
/* 729 */     if (geometry instanceof MultiPolygon) {
/* 730 */       for (int x = 0; x < geometry.getNumGeometries(); x++) {
/* 731 */         Polygon polygon = (Polygon)geometry.getGeometryN(x);
/* 734 */         IsValidOp validOp = new IsValidOp((Geometry)polygon);
/* 735 */         TopologyValidationError err = validOp.getValidationError();
/* 737 */         if (err != null) {
/* 738 */           log.error(err.getMessage());
/* 739 */           return outerShells;
/*     */         } 
/* 743 */         LineString exteriorRing = polygon.getExteriorRing();
/* 744 */         OSMPolygon shell = new OSMPolygon();
/* 745 */         for (int y = 0; y < exteriorRing.getNumPoints(); y++) {
/* 746 */           Point point = exteriorRing.getPointN(y);
/* 747 */           shell.addVertex(new Point2D(point.getX(), point.getY()));
/*     */         } 
/* 749 */         if (shell.vertexNumber() > 2) {
/* 750 */           shell.checkAndClose();
/* 752 */           validOp = new IsValidOp(shell.getGeometry());
/* 753 */           err = validOp.getValidationError();
/* 755 */           if (err != null) {
/* 756 */             log.error(err.getMessage());
/*     */           } else {
/* 759 */             outerShells.add(shell);
/*     */           } 
/*     */         } 
/*     */       } 
/* 763 */     } else if (geometry instanceof Polygon) {
/* 764 */       Polygon polygon = (Polygon)geometry;
/* 767 */       IsValidOp validOp = new IsValidOp((Geometry)polygon);
/* 768 */       TopologyValidationError err = validOp.getValidationError();
/* 769 */       if (err != null) {
/* 770 */         log.error(err.getMessage());
/* 771 */         return outerShells;
/*     */       } 
/* 775 */       LineString exteriorRing = polygon.getExteriorRing();
/* 776 */       OSMPolygon shell = new OSMPolygon();
/* 777 */       for (int y = 0; y < exteriorRing.getNumPoints(); y++) {
/* 778 */         Point point = exteriorRing.getPointN(y);
/* 779 */         shell.addVertex(new Point2D(point.getX(), point.getY()));
/*     */       } 
/* 781 */       if (shell.vertexNumber() > 2) {
/* 782 */         shell.checkAndClose();
/* 784 */         validOp = new IsValidOp(shell.getGeometry());
/* 785 */         err = validOp.getValidationError();
/* 787 */         if (err != null) {
/* 788 */           log.error(err.getMessage());
/* 789 */           return outerShells;
/*     */         } 
/* 791 */         outerShells.add(shell);
/*     */       } 
/*     */     } else {
/* 794 */       log.error("Cannot convert " + geometry.getClass().toString() + " to DSF shape");
/*     */     } 
/* 796 */     return outerShells;
/*     */   }
/*     */   
/*     */   private List<OSMPolygon> divideLarge(List<OSMPolygon> originals, int metreLimit) {
/* 801 */     List<OSMPolygon> divided = new ArrayList<>();
/* 802 */     for (OSMPolygon item : originals)
/* 803 */       divided.addAll(divideLarge(item, metreLimit)); 
/* 805 */     return divided;
/*     */   }
/*     */   
/*     */   private List<OSMPolygon> divideLarge(OSMPolygon original, int metreLimit) {
/* 809 */     List<OSMPolygon> divided = new ArrayList<>();
/* 813 */     LinearRing2D boundingRectangle = original.getMinRectangle();
/* 814 */     if (boundingRectangle != null && boundingRectangle.vertexNumber() > 3) {
/* 815 */       Point2D p1 = boundingRectangle.vertex(0);
/* 816 */       Point2D p2 = boundingRectangle.vertex(1);
/* 817 */       Point2D p3 = boundingRectangle.vertex(2);
/* 818 */       Point2D p4 = boundingRectangle.vertex(3);
/* 820 */       double distance1 = GeomUtils.distanceInMeters(new LineSegment2D(p1, p2));
/* 821 */       double distance2 = GeomUtils.distanceInMeters(new LineSegment2D(p2, p3));
/* 822 */       double distance3 = GeomUtils.distanceInMeters(new LineSegment2D(p3, p4));
/* 824 */       double angle1 = GeomUtils.getBearing(p1, p2);
/* 825 */       double angle2 = GeomUtils.getBearing(p2, p3);
/* 826 */       double angle3 = GeomUtils.getBearing(p3, p4);
/* 830 */       if (distance1 > metreLimit) {
/* 833 */         Point2D midpoint = GeomUtils.translateFastLatLon(p1, angle1, distance1 / 2.0D);
/* 835 */         Point2D endpoint = GeomUtils.translateFastLatLon(midpoint, angle2, distance2 + 50.0D);
/* 836 */         LineString cutLine = GeomUtils.geometryFactory.createLineString(new Coordinate[] { new Coordinate(midpoint.getX(), midpoint.getY()), new Coordinate(endpoint.getX(), endpoint.getY()) });
/* 839 */         Geometry buffered = cutLine.buffer(this.metreXSize, 8, 3);
/* 841 */         Geometry clipped = original.getGeometry().difference(buffered);
/* 842 */         if (clipped.getNumPoints() != 0 && !clipped.equalsTopo(original.getGeometry()) && cutLine.intersects(clipped)) {
/* 843 */           divided.addAll(divideLarge(geometryToPolygon(clipped), metreLimit));
/*     */         } else {
/* 845 */           divided.add(original);
/*     */         } 
/* 846 */       } else if (distance2 > metreLimit) {
/* 849 */         Point2D midpoint = GeomUtils.translateFastLatLon(p2, angle2, distance2 / 2.0D);
/* 851 */         Point2D endpoint = GeomUtils.translateFastLatLon(midpoint, angle3, distance3 + 50.0D);
/* 852 */         LineString cutLine = GeomUtils.geometryFactory.createLineString(new Coordinate[] { new Coordinate(midpoint.getX(), midpoint.getY()), new Coordinate(endpoint.getX(), endpoint.getY()) });
/* 855 */         Geometry buffered = cutLine.buffer(this.metreXSize, 8, 3);
/* 857 */         Geometry clipped = original.getGeometry().difference(buffered);
/* 858 */         if (clipped.getNumPoints() != 0 && !clipped.equalsTopo(original.getGeometry()) && cutLine.intersects(clipped)) {
/* 859 */           divided.addAll(divideLarge(geometryToPolygon(clipped), metreLimit));
/*     */         } else {
/* 861 */           divided.add(original);
/*     */         } 
/*     */       } else {
/* 864 */         divided.add(original);
/*     */       } 
/*     */     } else {
/* 867 */       divided.add(original);
/*     */     } 
/* 870 */     return divided;
/*     */   }
/*     */   
/*     */   public Autogen(DSFTile dsfTile) {
/* 875 */     this.dsfTile = dsfTile;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 879 */     this.autogenLines.clear();
/* 880 */     this.autogenObjects.clear();
/* 881 */     this.autogenLines = null;
/* 882 */     this.autogenObjects = null;
/*     */   }
/*     */   
/*     */   public void addShape(OSMShape singleShape) {
/* 891 */     this.autogenLines.add(singleShape);
/*     */   }
/*     */   
/*     */   public int size() {
/* 895 */     return this.autogenLines.size();
/*     */   }
/*     */   
/*     */   public double getMetreXSize() {
/* 899 */     return this.metreXSize;
/*     */   }
/*     */   
/*     */   public void setMetreXSize(double metreXSize) {
/* 903 */     this.metreXSize = metreXSize;
/*     */   }
/*     */   
/*     */   public RoadNetwork getRoadNetwork() {
/* 907 */     return this.roadNetwork;
/*     */   }
/*     */   
/*     */   public void setRoadNetwork(RoadNetwork roadNetwork) {
/* 911 */     this.roadNetwork = roadNetwork;
/*     */   }
/*     */   
/*     */   public List<AutogenObject> getAutogenObjects() {
/* 915 */     return this.autogenObjects;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\XPlane\Autogen.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
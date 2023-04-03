/*     */ package com.world2xplane.Geom;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryCollection;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.LinearRing;
/*     */ import com.vividsolutions.jts.geom.MultiPolygon;
/*     */ import com.vividsolutions.jts.geom.Point;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import com.vividsolutions.jts.geom.PrecisionModel;
/*     */ import com.vividsolutions.jts.operation.polygonize.Polygonizer;
/*     */ import com.vividsolutions.jts.operation.valid.IsValidOp;
/*     */ import com.vividsolutions.jts.operation.valid.TopologyValidationError;
/*     */ import com.world2xplane.DataStore.WayInfo;
/*     */ import com.world2xplane.OSM.OSMPolygon;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import math.geom2d.Box2D;
/*     */ import math.geom2d.Point2D;
/*     */ import math.geom2d.polygon.LinearRing2D;
/*     */ import org.poly2tri.Poly2Tri;
/*     */ import org.poly2tri.geometry.polygon.Polygon;
/*     */ import org.poly2tri.geometry.polygon.PolygonPoint;
/*     */ import org.poly2tri.triangulation.TriangulationPoint;
/*     */ import org.poly2tri.triangulation.delaunay.DelaunayTriangle;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class GeometryClipper {
/*  52 */   private static Logger log = LoggerFactory.getLogger(GeometryClipper.class);
/*     */   
/*     */   public static List<LinearRing2D> triangulate(LinearRing2D outer, List<LinearRing2D> inner) {
/*  57 */     List<PolygonPoint> polygonPoints = new ArrayList<>();
/*  58 */     for (int x = 0; x < outer.vertexNumber(); x++) {
/*  59 */       Point2D point = outer.vertex(x);
/*  60 */       polygonPoints.add(new PolygonPoint(point.x(), point.y()));
/*     */     } 
/*  63 */     Polygon polygon = new Polygon(polygonPoints);
/*  64 */     if (inner != null)
/*  65 */       for (LinearRing2D item : inner) {
/*  66 */         polygonPoints = new ArrayList<>();
/*  67 */         for (int i = 0; i < item.vertexNumber(); i++) {
/*  68 */           Point2D point = item.vertex(i);
/*  69 */           polygonPoints.add(new PolygonPoint(point.x(), point.y()));
/*     */         } 
/*  71 */         polygon.addHole(new Polygon(polygonPoints));
/*     */       }  
/*     */     try {
/*  77 */       Poly2Tri.triangulate(polygon);
/*  78 */     } catch (Exception e) {
/*  80 */       return null;
/*  82 */     } catch (StackOverflowError e) {
/*  84 */       return null;
/*     */     } 
/*  87 */     ArrayList<LinearRing2D> triangulated = new ArrayList<>();
/*  88 */     for (DelaunayTriangle item : polygon.getTriangles()) {
/*  90 */       LinearRing2D shape = new LinearRing2D();
/*  91 */       for (TriangulationPoint vertex : item.points)
/*  92 */         shape.addVertex(new Point2D(vertex.getX(), vertex.getY())); 
/*  94 */       shape.addVertex(shape.vertex(0));
/*  95 */       triangulated.add(shape);
/*     */     } 
/*  97 */     return triangulated;
/*     */   }
/*     */   
/*     */   public static Map<OSMPolygon, List<OSMPolygon>> clip(OSMPolygon outer, List<OSMPolygon> inner, Box2D box) {
/*     */     try {
/*     */       Geometry polygon;
/* 116 */       GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);
/* 118 */       if (inner != null && inner.size() > 0) {
/* 119 */         LinearRing[] polygons = new LinearRing[inner.size()];
/* 120 */         for (int i = 0; i < inner.size(); i++) {
/* 121 */           LinearRing innerPolygon = GeomUtils.linearRing2DToJTSLinearRing(((OSMPolygon)inner.get(i)).getShape());
/* 122 */           polygons[i] = innerPolygon;
/*     */         } 
/* 124 */         LinearRing outerPolygon = GeomUtils.linearRing2DToJTSLinearRing(outer.getShape());
/* 125 */         Polygon polygon1 = geometryFactory.createPolygon(outerPolygon, polygons);
/*     */       } else {
/* 129 */         polygon = GeomUtils.linearRingToJTSPolygon(outer.getShape());
/*     */       } 
/* 132 */       IsValidOp validOp = new IsValidOp(polygon);
/* 133 */       TopologyValidationError err = validOp.getValidationError();
/* 134 */       if (err != null) {
/* 135 */         polygon = polygon.buffer(0.0D);
/* 136 */         validOp = new IsValidOp(polygon);
/* 137 */         err = validOp.getValidationError();
/* 138 */         if (err != null) {
/* 139 */           log.error("Error in way http://www.openstreemap.org/way/" + outer.getIdentifier() + ": " + err);
/* 140 */           return null;
/*     */         } 
/*     */       } 
/* 149 */       HashMap<OSMPolygon, List<OSMPolygon>> data = new HashMap<>();
/* 151 */       Polygonizer polygonizer = new Polygonizer();
/* 152 */       polygonizer.add(polygon);
/* 153 */       LinearRing2D boundary = new LinearRing2D();
/* 154 */       boundary.addVertex(new Point2D(box.getMinX(), box.getMinY()));
/* 155 */       boundary.addVertex(new Point2D(box.getMaxX(), box.getMinY()));
/* 156 */       boundary.addVertex(new Point2D(box.getMaxX(), box.getMaxY()));
/* 157 */       boundary.addVertex(new Point2D(box.getMinX(), box.getMaxY()));
/* 158 */       boundary.addVertex(new Point2D(box.getMinX(), box.getMinY()));
/* 159 */       Geometry clipper = GeomUtils.linearRingToJTSPolygon(boundary);
/* 161 */       Geometry output = polygon.intersection(clipper);
/* 162 */       if (output instanceof Polygon) {
/* 163 */         Polygon result = (Polygon)output;
/* 164 */         if (result == null)
/* 165 */           return new HashMap<>(); 
/* 168 */         LineString item = result.getExteriorRing();
/* 169 */         LinearRing2D clippedOuter = new LinearRing2D();
/* 170 */         for (int i = 0; i < item.getNumPoints(); i++)
/* 171 */           clippedOuter.addVertex(new Point2D(item.getPointN(i).getX(), item.getPointN(i).getY())); 
/* 174 */         OSMPolygon newOuter = OSMPolygon.copyWithoutShape(outer);
/* 175 */         newOuter.setShape(clippedOuter);
/* 176 */         data.put(newOuter, new ArrayList<>());
/* 178 */         if (result.getNumInteriorRing() > 0)
/* 179 */           for (int j = 0; j < result.getNumInteriorRing(); j++) {
/* 180 */             LineString interior = result.getInteriorRingN(j);
/* 181 */             LinearRing2D clippedInner = new LinearRing2D();
/* 182 */             for (int y = 0; y < interior.getNumPoints(); y++)
/* 183 */               clippedInner.addVertex(new Point2D(interior.getPointN(y).getX(), interior.getPointN(y).getY())); 
/* 185 */             OSMPolygon innerPolygon = OSMPolygon.copyWithoutShape(outer);
/* 186 */             innerPolygon.setRole(WayInfo.INNER);
/* 187 */             innerPolygon.setShape(clippedInner);
/* 188 */             ((List<OSMPolygon>)data.get(newOuter)).add(innerPolygon);
/*     */           }  
/* 193 */         return data;
/*     */       } 
/* 194 */       if (output instanceof MultiPolygon) {
/* 195 */         MultiPolygon multiPolygon = (MultiPolygon)output;
/* 198 */         for (int i = 0; i < multiPolygon.getNumGeometries(); i++) {
/* 199 */           Polygon result = (Polygon)multiPolygon.getGeometryN(i);
/* 200 */           LineString item = result.getExteriorRing();
/* 201 */           LinearRing2D clippedOuter = new LinearRing2D();
/* 202 */           for (int z = 0; z < item.getNumPoints(); z++)
/* 203 */             clippedOuter.addVertex(new Point2D(item.getPointN(z).getX(), item.getPointN(z).getY())); 
/* 206 */           OSMPolygon newOuter = OSMPolygon.copyWithoutShape(outer);
/* 207 */           newOuter.setShape(clippedOuter);
/* 208 */           data.put(newOuter, new ArrayList<>());
/* 210 */           if (result.getNumInteriorRing() > 0)
/* 211 */             for (int y = 0; y < result.getNumInteriorRing(); y++) {
/* 212 */               LineString interior = result.getInteriorRingN(y);
/* 213 */               LinearRing2D clippedInner = new LinearRing2D();
/* 214 */               for (int a = 0; a < interior.getNumPoints(); a++)
/* 215 */                 clippedInner.addVertex(new Point2D(interior.getPointN(a).getX(), interior.getPointN(a).getY())); 
/* 217 */               OSMPolygon innerPolygon = OSMPolygon.copyWithoutShape(outer);
/* 218 */               innerPolygon.setRole(WayInfo.INNER);
/* 219 */               innerPolygon.setShape(clippedInner);
/* 220 */               ((List<OSMPolygon>)data.get(newOuter)).add(innerPolygon);
/*     */             }  
/*     */         } 
/* 226 */         return data;
/*     */       } 
/* 227 */       if (output instanceof GeometryCollection) {
/* 228 */         GeometryCollection multiPolygon = (GeometryCollection)output;
/* 229 */         for (int i = 0; i < output.getNumGeometries(); i++) {
/* 230 */           Geometry r = multiPolygon.getGeometryN(i);
/* 231 */           if (r instanceof Polygon) {
/* 232 */             Polygon result = (Polygon)r;
/* 233 */             LineString item = result.getExteriorRing();
/* 234 */             LinearRing2D clippedOuter = new LinearRing2D();
/* 235 */             for (int z = 0; z < item.getNumPoints(); z++)
/* 236 */               clippedOuter.addVertex(new Point2D(item.getPointN(z).getX(), item.getPointN(z).getY())); 
/* 239 */             OSMPolygon newOuter = OSMPolygon.copyWithoutShape(outer);
/* 240 */             newOuter.setShape(clippedOuter);
/* 241 */             data.put(newOuter, new ArrayList<>());
/* 243 */             if (result.getNumInteriorRing() > 0)
/* 244 */               for (int y = 0; y < result.getNumInteriorRing(); y++) {
/* 245 */                 LineString interior = result.getInteriorRingN(y);
/* 246 */                 LinearRing2D clippedInner = new LinearRing2D();
/* 247 */                 for (int a = 0; a < interior.getNumPoints(); a++)
/* 248 */                   clippedInner.addVertex(new Point2D(interior.getPointN(a).getX(), interior.getPointN(a).getY())); 
/* 250 */                 OSMPolygon innerPolygon = OSMPolygon.copyWithoutShape(outer);
/* 251 */                 innerPolygon.setRole(WayInfo.INNER);
/* 252 */                 innerPolygon.setShape(clippedInner);
/* 253 */                 ((List<OSMPolygon>)data.get(newOuter)).add(innerPolygon);
/*     */               }  
/*     */           } 
/*     */         } 
/* 258 */         return data;
/*     */       } 
/* 260 */       int x = 23;
/* 261 */       return data;
/* 265 */     } catch (Exception e) {
/* 266 */       log.error("Way http://www.openstreetmap.org/way/" + outer.getIdentifier() + " is invalid/or intersects.");
/* 267 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static List<OSMPolygon> mergeShapes(List<OSMPolygon> rings) {
/* 276 */     List<OSMPolygon> multis = new ArrayList<>();
/* 277 */     GeometryFactory geometryFactory = new GeometryFactory();
/* 278 */     Geometry[] shapes = new Geometry[rings.size()];
/* 279 */     for (int x = 0; x < rings.size(); x++)
/* 280 */       shapes[x] = GeomUtils.osmPolygonToJTSPolygon(rings.get(x)); 
/* 282 */     GeometryCollection collection = geometryFactory.createGeometryCollection(shapes);
/* 283 */     Geometry union = collection.union();
/* 284 */     Polygonizer polygonizer = new Polygonizer();
/* 285 */     polygonizer.add(union);
/* 286 */     Collection<Geometry> multi = polygonizer.getPolygons();
/* 288 */     for (Geometry item : multi) {
/* 289 */       if (item instanceof Polygon) {
/* 290 */         OSMPolygon outerRing = OSMPolygon.copyWithoutShape(rings.get(0));
/* 291 */         outerRing.setShape(GeomUtils.polygonToLinearRing2D(item));
/* 292 */         Polygon polygon = (Polygon)item;
/* 293 */         if (polygon.getNumInteriorRing() > 0)
/* 294 */           System.err.println("Interior Rings"); 
/* 296 */         multis.add(outerRing);
/*     */       } 
/*     */     } 
/* 299 */     return multis;
/*     */   }
/*     */   
/*     */   public static List<OSMPolygon> split(OSMPolygon shape, List<OSMPolygon> inner, int limit) {
/* 307 */     if (inner != null && inner.size() > 0) {
/* 308 */       List<OSMPolygon> data = splitMultipolygon(shape, inner);
/* 309 */       return data;
/*     */     } 
/* 313 */     List<OSMPolygon> clipped = new ArrayList<>();
/* 314 */     clipped.addAll(split(shape, limit));
/* 315 */     return clipped;
/*     */   }
/*     */   
/*     */   private static Collection<? extends OSMPolygon> split(OSMPolygon item, int limit) {
/* 320 */     List<OSMPolygon> data = new ArrayList<>();
/*     */     try {
/* 329 */       Geometry outer = GeomUtils.osmPolygonToJTSPolygon(item);
/* 330 */       Envelope outerCircle = outer.getEnvelopeInternal();
/* 332 */       Point centroid = outer.getCentroid();
/* 334 */       Box2D box1 = new Box2D(new Point2D(outerCircle.getMinX(), outerCircle.getMinY()), new Point2D(centroid.getX(), outerCircle.getMaxY()));
/* 337 */       Box2D box2 = new Box2D(new Point2D(centroid.getX(), outerCircle.getMinY()), new Point2D(outerCircle.getMaxX(), outerCircle.getMaxY()));
/* 341 */       Map<OSMPolygon, List<OSMPolygon>> left = clip(item, new ArrayList<>(), box1);
/* 342 */       Map<OSMPolygon, List<OSMPolygon>> right = clip(item, new ArrayList<>(), box2);
/* 344 */       for (Map.Entry<OSMPolygon, List<OSMPolygon>> clipped : left.entrySet()) {
/* 345 */         if (((OSMPolygon)clipped.getKey()).vertexNumber() > limit) {
/* 347 */           data.addAll(split(clipped.getKey(), limit));
/*     */           continue;
/*     */         } 
/* 349 */         data.add(clipped.getKey());
/*     */       } 
/* 353 */       for (Map.Entry<OSMPolygon, List<OSMPolygon>> clipped : right.entrySet()) {
/* 354 */         if (((OSMPolygon)clipped.getKey()).vertexNumber() > limit) {
/* 356 */           data.addAll(split(clipped.getKey(), limit));
/*     */           continue;
/*     */         } 
/* 358 */         data.add(clipped.getKey());
/*     */       } 
/* 363 */       return data;
/* 366 */     } catch (Exception e) {
/* 367 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static List<OSMPolygon> splitMultipolygon(OSMPolygon o, List<OSMPolygon> i) {
/*     */     try {
/* 375 */       List<OSMPolygon> data = new ArrayList<>();
/* 377 */       if (i != null && i.size() > 0) {
/* 379 */         o.checkAndClose();
/* 380 */         Geometry outer = GeomUtils.osmPolygonToJTSPolygon(o);
/* 381 */         outer = outer.buffer(0.0D);
/* 382 */         Envelope outerCircle = outer.getEnvelopeInternal();
/* 385 */         Geometry innerOne = GeomUtils.osmPolygonToJTSPolygon(i.get(0));
/* 386 */         innerOne = innerOne.buffer(0.0D);
/* 389 */         Point centroid = innerOne.getCentroid();
/* 391 */         Box2D box1 = new Box2D(new Point2D(outerCircle.getMinX(), outerCircle.getMinY()), new Point2D(centroid.getX(), outerCircle.getMaxY()));
/* 394 */         Box2D box2 = new Box2D(new Point2D(centroid.getX(), outerCircle.getMinY()), new Point2D(outerCircle.getMaxX(), outerCircle.getMaxY()));
/* 397 */         Map<OSMPolygon, List<OSMPolygon>> left = clip(o, i, box1);
/* 398 */         Map<OSMPolygon, List<OSMPolygon>> right = clip(o, i, box2);
/* 401 */         for (Map.Entry<OSMPolygon, List<OSMPolygon>> item : left.entrySet()) {
/* 402 */           if (((List)item.getValue()).size() > 0) {
/* 404 */             data.addAll(splitMultipolygon(item.getKey(), item.getValue()));
/*     */             continue;
/*     */           } 
/* 406 */           data.add(item.getKey());
/*     */         } 
/* 411 */         for (Map.Entry<OSMPolygon, List<OSMPolygon>> item : right.entrySet()) {
/* 412 */           if (((List)item.getValue()).size() > 0) {
/* 414 */             data.addAll(splitMultipolygon(item.getKey(), item.getValue()));
/*     */             continue;
/*     */           } 
/* 416 */           data.add(item.getKey());
/*     */         } 
/* 420 */         return data;
/*     */       } 
/* 423 */       data.add(o);
/* 424 */       return data;
/* 474 */     } catch (Exception e) {
/* 476 */       log.error("Couldn't split multipolygon with multiple holes http://www.openstreetmap.org/way/" + o.getIdentifier());
/* 477 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static List<OSMPolygon> splitArea(OSMPolygon polygon, double maxPerimeter) {
/* 486 */     Envelope envelope = polygon.getEnvelope();
/* 487 */     double perimeter = polygon.getPerimeter();
/* 488 */     if (perimeter > maxPerimeter) {
/* 489 */       List<Box2D> splits = new ArrayList<>();
/* 490 */       int cuts = (int)(perimeter / maxPerimeter);
/* 491 */       if (cuts == 0)
/* 492 */         cuts = 1; 
/* 495 */       Point2D topLeft = new Point2D(envelope.getMinX(), envelope.getMinY());
/* 496 */       Point2D bottomRight = new Point2D(envelope.getMaxX(), envelope.getMaxY());
/* 497 */       double splitX = (bottomRight.x() - topLeft.x()) / cuts;
/* 498 */       double splitY = (bottomRight.y() - topLeft.y()) / cuts;
/*     */       double x;
/* 500 */       for (x = topLeft.x(); x <= bottomRight.x(); x += splitX) {
/*     */         double y;
/* 501 */         for (y = topLeft.y(); y <= bottomRight.y(); y += splitY) {
/* 502 */           Box2D splitBox = new Box2D(x, y, x + splitX, y + splitY);
/* 503 */           splits.add(splitBox);
/*     */         } 
/*     */       } 
/* 506 */       if (splits.size() > 0) {
/* 507 */         List<OSMPolygon> splitShapes = new ArrayList<>();
/* 508 */         for (Box2D split : splits) {
/* 510 */           Map<OSMPolygon, List<OSMPolygon>> data = clip(polygon, null, split);
/* 511 */           if (data != null)
/* 512 */             for (Map.Entry<OSMPolygon, List<OSMPolygon>> entry : data.entrySet()) {
/* 513 */               if (((OSMPolygon)entry.getKey()).getShape().vertexNumber() == 0)
/*     */                 continue; 
/* 516 */               OSMPolygon splitPolygon = new OSMPolygon();
/* 518 */               splitPolygon.setShape(((OSMPolygon)entry.getKey()).getShape());
/* 519 */               splitShapes.add(splitPolygon);
/*     */             }  
/*     */         } 
/* 524 */         return splitShapes;
/*     */       } 
/*     */     } 
/* 531 */     List<OSMPolygon> polygons = new ArrayList<>();
/* 532 */     polygons.add(polygon);
/* 533 */     return polygons;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Geom\GeometryClipper.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
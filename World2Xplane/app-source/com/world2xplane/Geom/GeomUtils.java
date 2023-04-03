/*      */ package com.world2xplane.Geom;
/*      */ 
/*      */ import com.vividsolutions.jts.algorithm.CGAlgorithms;
/*      */ import com.vividsolutions.jts.algorithm.CentroidArea;
/*      */ import com.vividsolutions.jts.algorithm.MinimumDiameter;
/*      */ import com.vividsolutions.jts.geom.Coordinate;
/*      */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*      */ import com.vividsolutions.jts.geom.Envelope;
/*      */ import com.vividsolutions.jts.geom.Geometry;
/*      */ import com.vividsolutions.jts.geom.GeometryFactory;
/*      */ import com.vividsolutions.jts.geom.LineSegment;
/*      */ import com.vividsolutions.jts.geom.LineString;
/*      */ import com.vividsolutions.jts.geom.LinearRing;
/*      */ import com.vividsolutions.jts.geom.MultiLineString;
/*      */ import com.vividsolutions.jts.geom.Point;
/*      */ import com.vividsolutions.jts.geom.Polygon;
/*      */ import com.vividsolutions.jts.geom.impl.CoordinateArraySequenceFactory;
/*      */ import com.vividsolutions.jts.operation.buffer.BufferOp;
/*      */ import com.vividsolutions.jts.operation.buffer.BufferParameters;
/*      */ import com.vividsolutions.jts.operation.valid.IsValidOp;
/*      */ import com.vividsolutions.jts.operation.valid.TopologyValidationError;
/*      */ import com.vividsolutions.jts.simplify.TopologyPreservingSimplifier;
/*      */ import com.world2xplane.OSM.MultipolygonCreate;
/*      */ import com.world2xplane.OSM.Node;
/*      */ import com.world2xplane.OSM.OSMMultiPolygon;
/*      */ import com.world2xplane.OSM.OSMPolygon;
/*      */ import com.world2xplane.OSM.OSMRelation;
/*      */ import com.world2xplane.OSM.OSMShape;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.HashMap;
/*      */ import java.util.List;
/*      */ import math.geom2d.Box2D;
/*      */ import math.geom2d.Point2D;
/*      */ import math.geom2d.line.LineSegment2D;
/*      */ import math.geom2d.polygon.LinearRing2D;
/*      */ import net.sf.geographiclib.Geodesic;
/*      */ import net.sf.geographiclib.PolygonArea;
/*      */ import org.apache.commons.math3.util.FastMath;
/*      */ import org.slf4j.Logger;
/*      */ import org.slf4j.LoggerFactory;
/*      */ import uk.me.jstott.jcoord.LatLng;
/*      */ import uk.me.jstott.jcoord.UTMRef;
/*      */ 
/*      */ public class GeomUtils {
/*   59 */   public static GeometryFactory geometryFactory = new GeometryFactory();
/*      */   
/*   61 */   private static Logger log = LoggerFactory.getLogger(GeomUtils.class);
/*      */   
/*   63 */   private static double R_MAJOR = 6378137.0D;
/*      */   
/*   64 */   private static double R_MINOR = 6356752.3142D;
/*      */   
/*   65 */   private static double RATIO = R_MINOR / R_MAJOR;
/*      */   
/*   66 */   private static double ECCENT = FastMath.sqrt(1.0D - RATIO * RATIO);
/*      */   
/*   67 */   private static double COM = 0.5D * ECCENT;
/*      */   
/*   69 */   private static double DEG2RAD = 0.017453292519943295D;
/*      */   
/*   70 */   private static double RAD2Deg = 57.29577951308232D;
/*      */   
/*   71 */   private static double PI_2 = 1.5707963267948966D;
/*      */   
/*      */   public static final double EARTH_RAD = 6378137.0D;
/*      */   
/*      */   public static double merc_x(double lon) {
/*   75 */     return R_MAJOR * FastMath.toRadians(lon);
/*      */   }
/*      */   
/*      */   public static double merc_y(double lat) {
/*   79 */     lat = FastMath.min(89.5D, FastMath.max(lat, -89.5D));
/*   80 */     double phi = FastMath.toRadians(lat);
/*   81 */     double sinphi = FastMath.sin(phi);
/*   82 */     double con = ECCENT * sinphi;
/*   83 */     con = FastMath.pow((1.0D - con) / (1.0D + con), COM);
/*   84 */     double ts = FastMath.tan(0.5D * (1.5707963267948966D - phi)) / con;
/*   85 */     return 0.0D - R_MAJOR * FastMath.log(ts);
/*      */   }
/*      */   
/*      */   public static double merc_lon(double x) {
/*   90 */     return FastMath.toDegrees(x) / R_MAJOR;
/*      */   }
/*      */   
/*      */   public static double merc_lat(double y) {
/*   94 */     double ts = FastMath.exp(-y / R_MAJOR);
/*   95 */     double phi = PI_2 - 2.0D * FastMath.atan(ts);
/*   96 */     double dphi = 1.0D;
/*   97 */     int i = 0;
/*   98 */     while (FastMath.abs(dphi) > 1.0E-9D && i < 15) {
/*   99 */       double con = ECCENT * FastMath.sin(phi);
/*  100 */       dphi = PI_2 - 2.0D * FastMath.atan(ts * FastMath.pow((1.0D - con) / (1.0D + con), COM)) - phi;
/*  101 */       phi += dphi;
/*  102 */       i++;
/*      */     } 
/*  104 */     return FastMath.toDegrees(phi);
/*      */   }
/*      */   
/*      */   public static Coordinate[] getCoordsFromRing(LinearRing2D ring) {
/*  110 */     if (ring.vertexNumber() <= 1)
/*  111 */       return null; 
/*  113 */     Coordinate[] coords = new Coordinate[ring.vertexNumber()];
/*  114 */     for (int x = 0; x < ring.vertexNumber(); x++)
/*  115 */       coords[x] = new Coordinate(ring.vertex(x).x(), ring.vertex(x).y()); 
/*  118 */     return coords;
/*      */   }
/*      */   
/*      */   public static boolean isCounterClockwise(LinearRing2D ring) {
/*  122 */     if (ring.vertexNumber() < 4)
/*  123 */       return true; 
/*  125 */     return CGAlgorithms.isCCW(getCoordsFromRing(ring));
/*      */   }
/*      */   
/*      */   public static LinearRing2D setCounterClockwise(LinearRing2D ring) {
/*  135 */     if (ring.vertexNumber() <= 3)
/*  136 */       return ring; 
/*  138 */     if (isCounterClockwise(ring))
/*  139 */       return ring; 
/*  141 */     Collection<Point2D> clockwiseVectors = new ArrayList<>();
/*  142 */     for (int i = ring.vertexNumber() - 1; i > -1; i--)
/*  143 */       clockwiseVectors.add(ring.vertex(i)); 
/*  145 */     return new LinearRing2D(clockwiseVectors);
/*      */   }
/*      */   
/*      */   public static boolean isRound(OSMPolygon osmPolygon) {
/*  151 */     LinearRing2D object = osmPolygon.getShape().clone();
/*  152 */     if (!object.vertex(object.vertexNumber() - 1).equals(object.firstPoint()))
/*  153 */       object.addVertex(object.firstPoint()); 
/*  155 */     if (object.vertexNumber() <= 6)
/*  156 */       return false; 
/*  160 */     Geometry shape = linearRingToJTSPolygon(object);
/*  161 */     Coordinate c = shape.getCentroid().getCoordinate();
/*  163 */     Point2D centre = new Point2D(c.x, c.y);
/*  165 */     double tolerance = 1.0D;
/*  167 */     Point2D firstPoint = object.firstPoint();
/*  169 */     double firstDistance = distanceInMeters(new LineSegment2D(firstPoint, centre));
/*  171 */     for (int x = 0; x < object.vertexNumber(); x++) {
/*  173 */       double distance = distanceInMeters(new LineSegment2D(object.vertex(x), centre));
/*  174 */       if (distance < firstDistance - tolerance || distance > firstDistance + tolerance)
/*  175 */         return false; 
/*      */     } 
/*  179 */     return true;
/*      */   }
/*      */   
/*      */   public static Double radius(OSMPolygon object) {
/*  183 */     if (!object.vertex(object.vertexNumber() - 1).equals(object.firstPoint()))
/*  184 */       object.addVertex(object.firstPoint()); 
/*  186 */     if (object.vertexNumber() <= 2)
/*  187 */       return null; 
/*  188 */     if (object.vertexNumber() < 5)
/*  189 */       return null; 
/*  191 */     Geometry shape = osmPolygonToJTSPolygon(object);
/*  192 */     Coordinate c = shape.getCentroid().getCoordinate();
/*  194 */     Point2D centre = new Point2D(c.x, c.y);
/*  195 */     Point2D firstPoint = object.firstPoint();
/*  196 */     return Double.valueOf(distanceInMeters(new LineSegment2D(firstPoint, centre)));
/*      */   }
/*      */   
/*      */   public static UTMRef LonLat2UTM(Point point) {
/*  201 */     LatLng latlng = new LatLng(point.getY(), point.getX());
/*  202 */     return latlng.toUTMRef();
/*      */   }
/*      */   
/*      */   public static Point2D UTM2LonLat(UTMRef ref) {
/*  206 */     LatLng point = ref.toLatLng();
/*  207 */     return new Point2D(point.getLng(), point.getLat());
/*      */   }
/*      */   
/*      */   public static Geometry preciseBuffer(Geometry geometry, double metres) {
/*  211 */     return preciseBuffer(geometry, metres, 8, 2);
/*      */   }
/*      */   
/*      */   public static Geometry preciseBuffer(Geometry geometry, double metres, int quadrants, int capStyle) {
/*  223 */     char latZone = Character.MIN_VALUE;
/*  224 */     Integer lngZone = null;
/*  225 */     Geometry cloned = (Geometry)geometry.clone();
/*  227 */     Coordinate[] coordinates = cloned.getCoordinates();
/*  228 */     for (int x = 0; x < coordinates.length; x++) {
/*  229 */       Coordinate coord = coordinates[x];
/*  230 */       LatLng latlng = new LatLng(coord.y, coord.x);
/*  231 */       UTMRef p = latlng.toUTMRef();
/*  233 */       if (lngZone == null) {
/*  234 */         latZone = p.getLatZone();
/*  235 */         lngZone = Integer.valueOf(p.getLngZone());
/*  237 */       } else if (lngZone.intValue() != p.getLngZone() || latZone != p.getLatZone()) {
/*  238 */         return bufferFallback(geometry, metres, quadrants, capStyle);
/*      */       } 
/*  242 */       coord.x = p.getEasting();
/*  243 */       coord.y = p.getNorthing();
/*      */     } 
/*  245 */     cloned.geometryChanged();
/*  247 */     BufferOp bufOp = new BufferOp(cloned, new BufferParameters(quadrants, capStyle, 2, metres));
/*  248 */     cloned = bufOp.getResultGeometry(metres);
/*  251 */     coordinates = cloned.getCoordinates();
/*  252 */     for (int i = 0; i < coordinates.length; i++) {
/*  253 */       Coordinate coord = coordinates[i];
/*  254 */       UTMRef p = new UTMRef(lngZone.intValue(), latZone, coord.x, coord.y);
/*  255 */       LatLng point = p.toLatLng();
/*  256 */       coord.x = point.getLng();
/*  257 */       coord.y = point.getLat();
/*      */     } 
/*  259 */     cloned.geometryChanged();
/*  260 */     return cloned;
/*      */   }
/*      */   
/*      */   private static Geometry bufferFallback(Geometry geometry, double metres, int quadrants, int capStyle) {
/*  264 */     Coordinate[] coordinates = geometry.getCoordinates();
/*  265 */     if (coordinates == null || coordinates.length == 0)
/*  266 */       return null; 
/*  268 */     Point2D p1 = new Point2D((coordinates[0]).x, (coordinates[0]).y);
/*  269 */     Point2D p2 = translateFastLatLon(p1, 90.0D, metres);
/*  270 */     double metreXSize = Math.abs(p2.x() - p1.x());
/*  271 */     if (metreXSize == 0.0D)
/*  272 */       metreXSize = Math.abs(p2.y() - p1.y()); 
/*  274 */     if (metreXSize > 0.0D) {
/*  275 */       BufferOp bufOp = new BufferOp(geometry, new BufferParameters(quadrants, capStyle, 2, metreXSize));
/*  276 */       return bufOp.getResultGeometry(metreXSize);
/*      */     } 
/*  278 */     return geometry.buffer(metreXSize, 8, capStyle);
/*      */   }
/*      */   
/*      */   public static Geometry osmPolygonToJTSPolygon(OSMPolygon ring2d) {
/*  285 */     if (ring2d.vertexNumber() <= 3) {
/*  286 */       OSMPolygon oSMPolygon = ring2d.clone();
/*  288 */       CoordinateSequence coordinateSequence = CoordinateArraySequenceFactory.instance().create(getCoordsFromRing(oSMPolygon.getShape()));
/*  290 */       GeometryFactory geometryFactory1 = new GeometryFactory();
/*  292 */       LineString lineString = geometryFactory1.createLineString(coordinateSequence);
/*  293 */       return (Geometry)lineString;
/*      */     } 
/*  296 */     OSMPolygon object = ring2d.clone();
/*  297 */     CoordinateSequence coordSeq = CoordinateArraySequenceFactory.instance().create(getCoordsFromRing(object.getShape()));
/*  299 */     GeometryFactory geometryFactory = new GeometryFactory();
/*  301 */     LinearRing linearRing = geometryFactory.createLinearRing(coordSeq);
/*  302 */     Polygon jtsPolygon = geometryFactory.createPolygon(linearRing, null);
/*  303 */     return (Geometry)jtsPolygon;
/*      */   }
/*      */   
/*      */   public static LineString linearRingToJTSLine(LinearRing2D line) {
/*  307 */     if (line == null)
/*  308 */       return null; 
/*  313 */     CoordinateSequence coordSeq = CoordinateArraySequenceFactory.instance().create(getCoordsFromRing(line));
/*  315 */     GeometryFactory geometryFactory = new GeometryFactory();
/*  316 */     LineString linearRing = geometryFactory.createLineString(coordSeq);
/*  317 */     return linearRing;
/*      */   }
/*      */   
/*      */   public static Geometry linearRingToJTSPolygon(LinearRing2D ring2d) {
/*      */     try {
/*  324 */       if (ring2d == null)
/*  325 */         return null; 
/*  328 */       boolean closed = ring2d.vertex(0).equals(ring2d.vertex(ring2d.vertexNumber() - 1));
/*  330 */       if (ring2d.vertexNumber() <= 3) {
/*  332 */         CoordinateSequence coordinateSequence = CoordinateArraySequenceFactory.instance().create(getCoordsFromRing(ring2d));
/*  334 */         GeometryFactory geometryFactory1 = new GeometryFactory();
/*  335 */         LineString lineString = geometryFactory1.createLineString(coordinateSequence);
/*  336 */         return (Geometry)lineString;
/*      */       } 
/*  339 */       if (ring2d.vertexNumber() <= 1)
/*  340 */         return null; 
/*  343 */       Coordinate[] coords = new Coordinate[closed ? ring2d.vertexNumber() : (ring2d.vertexNumber() + 1)];
/*  344 */       for (int x = 0; x < ring2d.vertexNumber(); x++)
/*  345 */         coords[x] = new Coordinate(ring2d.vertex(x).x(), ring2d.vertex(x).y()); 
/*  348 */       if (!closed)
/*  349 */         coords[ring2d.vertexNumber()] = new Coordinate(ring2d.vertex(0).x(), ring2d.vertex(0).y()); 
/*  352 */       CoordinateSequence coordSeq = CoordinateArraySequenceFactory.instance().create(coords);
/*  354 */       GeometryFactory geometryFactory = new GeometryFactory();
/*  356 */       LinearRing linearRing = geometryFactory.createLinearRing(coordSeq);
/*  358 */       Polygon jtsPolygon = geometryFactory.createPolygon(linearRing, null);
/*  359 */       return (Geometry)jtsPolygon;
/*  360 */     } catch (Exception e) {
/*  361 */       e.printStackTrace();
/*  362 */       return null;
/*      */     } 
/*      */   }
/*      */   
/*      */   public static Geometry osmShapeToJTS(OSMShape shape) {
/*  368 */     if (shape.outer != null) {
/*  369 */       if (shape.inner != null && shape.inner.size() > 0) {
/*  370 */         LinearRing outer = linearRing2DToJTSLinearRing(shape.outer.getShape());
/*  371 */         LinearRing[] inners = new LinearRing[shape.inner.size()];
/*  372 */         int x = 0;
/*  374 */         for (OSMPolygon item : shape.inner) {
/*  375 */           LinearRing inner = linearRing2DToJTSLinearRing(item.getShape());
/*  376 */           inners[x] = inner;
/*  377 */           x++;
/*      */         } 
/*  380 */         Polygon poly = geometryFactory.createPolygon(outer, inners);
/*  381 */         return (Geometry)poly;
/*      */       } 
/*  384 */       return linearRingToJTSPolygon(shape.outer.getShape());
/*      */     } 
/*  386 */     return null;
/*      */   }
/*      */   
/*      */   public static LineString osmShapeToJTSLineString(OSMShape shape) {
/*  391 */     if (shape.outer != null)
/*      */       try {
/*  395 */         CoordinateSequence coordSeq = CoordinateArraySequenceFactory.instance().create(getCoordsFromRing(shape.outer.getShape()));
/*  397 */         GeometryFactory geometryFactory = new GeometryFactory();
/*  399 */         LineString lineString = geometryFactory.createLineString(coordSeq);
/*  400 */         return lineString;
/*  403 */       } catch (Exception e) {
/*  404 */         e.printStackTrace();
/*  405 */         return null;
/*      */       }  
/*  408 */     return null;
/*      */   }
/*      */   
/*      */   public static LineString osmPolygonToJTSLineString(OSMPolygon shape) {
/*  413 */     if (shape != null)
/*      */       try {
/*  417 */         CoordinateSequence coordSeq = CoordinateArraySequenceFactory.instance().create(getCoordsFromRing(shape.getShape()));
/*  419 */         GeometryFactory geometryFactory = new GeometryFactory();
/*  421 */         LineString lineString = geometryFactory.createLineString(coordSeq);
/*  422 */         return lineString;
/*  425 */       } catch (Exception e) {
/*  426 */         e.printStackTrace();
/*  427 */         return null;
/*      */       }  
/*  430 */     return null;
/*      */   }
/*      */   
/*      */   public static LinearRing linearRing2DToJTSLinearRing(LinearRing2D ring2d) {
/*      */     try {
/*  436 */       LinearRing2D object = ring2d.clone();
/*  437 */       if (!object.firstPoint().equals(object.vertex(object.vertexNumber() - 1)))
/*  438 */         object.addVertex(object.firstPoint()); 
/*  440 */       CoordinateSequence coordSeq = CoordinateArraySequenceFactory.instance().create(getCoordsFromRing(object));
/*  442 */       GeometryFactory geometryFactory = new GeometryFactory();
/*  444 */       LinearRing linearRing = geometryFactory.createLinearRing(coordSeq);
/*  446 */       return linearRing;
/*  447 */     } catch (Exception e) {
/*  448 */       return null;
/*      */     } 
/*      */   }
/*      */   
/*      */   public static LinearRing2D polygonToLinearRing2D(Geometry polygon) {
/*  455 */     List<Point2D> points = new ArrayList<>();
/*  457 */     if (polygon.getCoordinates() == null)
/*  458 */       return new LinearRing2D(); 
/*  461 */     if (polygon instanceof Polygon) {
/*  462 */       Polygon pol = (Polygon)polygon;
/*  463 */       LineString exteriorRing = pol.getExteriorRing();
/*  464 */       for (int x = 0; x < exteriorRing.getNumPoints(); x++) {
/*  465 */         Point coord = exteriorRing.getPointN(x);
/*  466 */         Point2D point2d = new Point2D(coord.getX(), coord.getY());
/*  467 */         points.add(point2d);
/*      */       } 
/*  469 */       return new LinearRing2D(points);
/*      */     } 
/*  472 */     if (polygon instanceof LineString) {
/*  473 */       LineString pol = (LineString)polygon;
/*  474 */       for (int x = 0; x < pol.getNumPoints(); x++) {
/*  475 */         Point coord = pol.getPointN(x);
/*  476 */         Point2D point2d = new Point2D(coord.getX(), coord.getY());
/*  477 */         points.add(point2d);
/*      */       } 
/*  479 */       return new LinearRing2D(points);
/*      */     } 
/*  482 */     for (Coordinate coordinate : polygon.getCoordinates()) {
/*  483 */       Point2D point2d = new Point2D(coordinate.x, coordinate.y);
/*  484 */       points.add(point2d);
/*      */     } 
/*  487 */     return new LinearRing2D(points);
/*      */   }
/*      */   
/*      */   public static LinearRing2D setClockwise(LinearRing2D ring) {
/*      */     try {
/*  499 */       if (!isCounterClockwise(ring))
/*  500 */         return ring; 
/*  502 */       Collection<Point2D> clockwiseVectors = new ArrayList<>();
/*  503 */       for (int i = ring.vertexNumber() - 1; i > -1; i--)
/*  504 */         clockwiseVectors.add(ring.vertex(i)); 
/*  506 */       return new LinearRing2D(clockwiseVectors);
/*  508 */     } catch (Exception e) {
/*  509 */       e.printStackTrace();
/*  510 */       return ring;
/*      */     } 
/*      */   }
/*      */   
/*      */   public static HashMap<OSMRelation.Member, List<OSMRelation.Member>> organiseOutersAndInner(Long relationId, List<OSMRelation.Member> outer, List<OSMRelation.Member> inner) {
/*  520 */     HashMap<OSMRelation.Member, List<OSMRelation.Member>> data = new HashMap<>();
/*  521 */     for (OSMRelation.Member item : outer)
/*  522 */       data.put(item, new ArrayList<>()); 
/*      */     try {
/*  527 */       for (OSMRelation.Member outside : outer) {
/*  528 */         outside.shape.checkAndClose();
/*  529 */         Geometry outerPolygon = osmPolygonToJTSPolygon(outside.shape);
/*  531 */         List<OSMRelation.Member> copy = new ArrayList<>();
/*  532 */         copy.addAll(inner);
/*  534 */         for (OSMRelation.Member inside : inner) {
/*  536 */           Geometry insidePolygon = osmPolygonToJTSPolygon(inside.shape);
/*  537 */           if (outerPolygon.contains(insidePolygon)) {
/*  538 */             if (data.get(outside) != null)
/*  539 */               ((List<OSMRelation.Member>)data.get(outside)).add(inside); 
/*  542 */             copy.remove(inside);
/*      */           } 
/*      */         } 
/*  545 */         inner = copy;
/*      */       } 
/*  548 */       if (inner.size() > 0);
/*  552 */       return data;
/*  553 */     } catch (Exception e) {
/*  554 */       e.printStackTrace();
/*  555 */       log.error("Couldn't sort inner and outer models of relation " + relationId);
/*  556 */       return null;
/*      */     } 
/*      */   }
/*      */   
/*      */   public static double greatCircleBearing(Point2D point1, Point2D point2) {
/*  563 */     double d2r = 0.017453292519943295D;
/*  565 */     double dLong = point1.x() - point2.x();
/*  567 */     double s = FastMath.cos(d2r * point2.y()) * FastMath.sin(d2r * dLong);
/*  568 */     double c = FastMath.cos(d2r * point1.y()) * FastMath.sin(d2r * point2.y()) - FastMath.sin(point1.y() * d2r) * FastMath.cos(d2r * point2.y()) * FastMath.cos(d2r * dLong);
/*  570 */     return FastMath.atan2(s, c);
/*      */   }
/*      */   
/*      */   private static double polygonArea(double[] x, double[] y) {
/*  575 */     double area = 0.0D;
/*  576 */     int j = x.length - 1;
/*  577 */     for (int i = 0; i < x.length; i++) {
/*  578 */       area += (x[j] + x[i]) * (y[j] - y[i]);
/*  579 */       j = i;
/*      */     } 
/*  581 */     area /= 2.0D;
/*  582 */     if (area < 0.0D)
/*  583 */       area *= -1.0D; 
/*  584 */     return area;
/*      */   }
/*      */   
/*      */   public static double areaInMeters(OSMPolygon linearRing2D) {
/*  595 */     PolygonArea p = new PolygonArea(Geodesic.WGS84, false);
/*  596 */     for (int x = 0; x < linearRing2D.vertexNumber(); x++) {
/*  597 */       Point2D po = linearRing2D.vertex(x);
/*  598 */       p.AddPoint(po.y(), po.x());
/*      */     } 
/*  601 */     return (p.Compute()).area;
/*      */   }
/*      */   
/*      */   public static double areaInMeters(Envelope envelope) {
/*  607 */     PolygonArea p = new PolygonArea(Geodesic.WGS84, false);
/*  608 */     p.AddPoint(envelope.getMinX(), envelope.getMinY());
/*  609 */     p.AddPoint(envelope.getMaxX(), envelope.getMinY());
/*  610 */     p.AddPoint(envelope.getMaxX(), envelope.getMaxY());
/*  611 */     p.AddPoint(envelope.getMinX(), envelope.getMaxY());
/*  612 */     p.AddPoint(envelope.getMinX(), envelope.getMinY());
/*  615 */     return (p.Compute()).area;
/*      */   }
/*      */   
/*      */   public static double areaInMeters(LinearRing2D linearRing2D) {
/*  622 */     PolygonArea p = new PolygonArea(Geodesic.WGS84, false);
/*  623 */     for (int x = 0; x < linearRing2D.vertexNumber(); x++) {
/*  624 */       Point2D po = linearRing2D.vertex(x);
/*  625 */       p.AddPoint(po.y(), po.x());
/*      */     } 
/*  628 */     return (p.Compute()).area;
/*      */   }
/*      */   
/*      */   public static Point2D translateLatLon(Point2D centroid, double bearing, double dist) {
/*  651 */     dist /= 1000.0D;
/*  653 */     dist /= 6371.0D;
/*  654 */     bearing = FastMath.toRadians(bearing);
/*  655 */     double lat1 = FastMath.toRadians(centroid.y());
/*  656 */     double lon1 = FastMath.toRadians(centroid.x());
/*  658 */     double lat2 = FastMath.asin(FastMath.sin(lat1) * FastMath.cos(dist) + FastMath.cos(lat1) * FastMath.sin(dist) * FastMath.cos(bearing));
/*  659 */     double lon2 = lon1 + FastMath.atan2(FastMath.sin(bearing) * FastMath.sin(dist) * FastMath.cos(lat1), FastMath.cos(dist) - FastMath.sin(lat1) * FastMath.sin(lat2));
/*  660 */     lon2 = (lon2 + 9.42477796076938D) % 6.283185307179586D - Math.PI;
/*  663 */     Point2D newPoint = new Point2D(FastMath.toDegrees(lon2), FastMath.toDegrees(lat2));
/*  664 */     return newPoint;
/*      */   }
/*      */   
/*      */   public static Point2D translateFastLatLon(Point2D centroid, double bearing, double dist) {
/*  679 */     dist /= 1000.0D;
/*  681 */     dist /= 6371.0D;
/*  682 */     bearing = FastMath.toRadians(bearing);
/*  683 */     double lat1 = FastMath.toRadians(centroid.y());
/*  684 */     double lon1 = FastMath.toRadians(centroid.x());
/*  686 */     double lat2 = FastMath.asin(FastMath.sin(lat1) * FastMath.cos(dist) + FastMath.cos(lat1) * FastMath.sin(dist) * FastMath.cos(bearing));
/*  687 */     double lon2 = lon1 + FastMath.atan2(FastMath.sin(bearing) * FastMath.sin(dist) * FastMath.cos(lat1), FastMath.cos(dist) - FastMath.sin(lat1) * FastMath.sin(lat2));
/*  688 */     lon2 = (lon2 + 9.42477796076938D) % 6.283185307179586D - Math.PI;
/*  691 */     Point2D newPoint = new Point2D(FastMath.toDegrees(lon2), FastMath.toDegrees(lat2));
/*  692 */     return newPoint;
/*      */   }
/*      */   
/*      */   public static double getBearing(Point2D firstPoint, Point2D secondPoint) {
/*  696 */     double longitude1 = firstPoint.x();
/*  697 */     double longitude2 = secondPoint.x();
/*  698 */     double latitude1 = FastMath.toRadians(firstPoint.y());
/*  699 */     double latitude2 = FastMath.toRadians(secondPoint.y());
/*  700 */     double longDiff = FastMath.toRadians(longitude2 - longitude1);
/*  701 */     double y = FastMath.sin(longDiff) * FastMath.cos(latitude2);
/*  702 */     double x = FastMath.cos(latitude1) * FastMath.sin(latitude2) - FastMath.sin(latitude1) * FastMath.cos(latitude2) * FastMath.cos(longDiff);
/*  704 */     return (FastMath.toDegrees(FastMath.atan2(y, x)) + 360.0D) % 360.0D;
/*      */   }
/*      */   
/*      */   public static double getBearing(double lat1, double long1, double lat2, double long2) {
/*  711 */     lat1 = FastMath.toRadians(lat1);
/*  712 */     long1 = FastMath.toRadians(long1);
/*  713 */     lat2 = FastMath.toRadians(lat2);
/*  714 */     long2 = FastMath.toRadians(long2);
/*  716 */     double deltaLong = long2 - long1;
/*  718 */     double y = FastMath.sin(deltaLong) * FastMath.cos(lat2);
/*  719 */     double x = FastMath.cos(lat1) * FastMath.sin(lat2) - FastMath.sin(lat1) * FastMath.cos(lat2) * FastMath.cos(deltaLong);
/*  721 */     double bearing = FastMath.atan2(y, x);
/*  722 */     return ConvertToBearing(FastMath.toDegrees(bearing));
/*      */   }
/*      */   
/*      */   public static double ConvertToBearing(double deg) {
/*  726 */     return (deg + 360.0D) % 360.0D;
/*      */   }
/*      */   
/*      */   public static double distanceInMeters(LineSegment2D lineSegment2D) {
/*  731 */     double lon1 = lineSegment2D.firstPoint().x();
/*  732 */     double lon2 = lineSegment2D.lastPoint().x();
/*  733 */     double lat1 = lineSegment2D.firstPoint().y();
/*  734 */     double lat2 = lineSegment2D.lastPoint().y();
/*  737 */     double theta = lon1 - lon2;
/*  738 */     double dist = FastMath.sin(deg2rad(lat1)) * FastMath.sin(deg2rad(lat2)) + FastMath.cos(deg2rad(lat1)) * FastMath.cos(deg2rad(lat2)) * FastMath.cos(deg2rad(theta));
/*  739 */     dist = FastMath.acos(dist);
/*  740 */     dist = rad2deg(dist);
/*  741 */     dist = dist * 60.0D * 1.1515D;
/*  742 */     dist *= 1.609344D;
/*  743 */     return dist * 1000.0D;
/*      */   }
/*      */   
/*      */   public static double distanceInMetersFastMath(LineSegment2D lineSegment2D) {
/*  750 */     double lon1 = lineSegment2D.firstPoint().x();
/*  751 */     double lon2 = lineSegment2D.lastPoint().x();
/*  752 */     double lat1 = lineSegment2D.firstPoint().y();
/*  753 */     double lat2 = lineSegment2D.lastPoint().y();
/*  756 */     double theta = lon1 - lon2;
/*  757 */     double dist = FastMath.sin(deg2rad(lat1)) * FastMath.sin(deg2rad(lat2)) + FastMath.cos(deg2rad(lat1)) * FastMath.cos(deg2rad(lat2)) * FastMath.cos(deg2rad(theta));
/*  758 */     dist = FastMath.acos(dist);
/*  759 */     dist = rad2deg(dist);
/*  760 */     dist = dist * 60.0D * 1.1515D;
/*  761 */     dist *= 1.609344D;
/*  762 */     return dist * 1000.0D;
/*      */   }
/*      */   
/*      */   private static double deg2rad(double deg) {
/*  768 */     return deg * Math.PI / 180.0D;
/*      */   }
/*      */   
/*      */   private static double rad2deg(double rad) {
/*  775 */     return rad * 180.0D / Math.PI;
/*      */   }
/*      */   
/*      */   public static boolean isSimplePolygon(OSMPolygon polygon) {
/*  780 */     return (polygon.edges().size() == 4 && areParallelsSegmentsIdentics(polygon));
/*      */   }
/*      */   
/*      */   public static boolean areParallelsSegmentsIdentics(OSMPolygon linearRing2D) {
/*  784 */     if (linearRing2D.vertexNumber() == 5) {
/*  786 */       LineSegment2D line1 = new LineSegment2D(linearRing2D.vertex(0), linearRing2D.vertex(1));
/*  788 */       LineSegment2D line2 = new LineSegment2D(linearRing2D.vertex(1), linearRing2D.vertex(2));
/*  790 */       LineSegment2D line3 = new LineSegment2D(linearRing2D.vertex(2), linearRing2D.vertex(3));
/*  792 */       LineSegment2D line4 = new LineSegment2D(linearRing2D.vertex(3), linearRing2D.vertex(4));
/*  795 */       double length1 = line1.length();
/*  796 */       double length2 = line2.length();
/*  797 */       double length3 = line3.length();
/*  798 */       double length4 = line4.length();
/*  800 */       double diff1 = FastMath.abs(length1 - length3);
/*  801 */       double diff2 = FastMath.abs(length2 - length4);
/*  803 */       boolean sameLength1 = (diff1 < line1.length() / 10.0D);
/*  804 */       boolean sameLength2 = (diff2 < line2.length() / 10.0D);
/*  805 */       if (sameLength1 && sameLength2)
/*  806 */         return true; 
/*      */     } 
/*  810 */     return false;
/*      */   }
/*      */   
/*      */   public static LinearRing2D simplifyWay(LinearRing2D w, double threshold) {
/*  817 */     int lower = 0;
/*  818 */     int i = 0;
/*  819 */     List<Point2D> newNodes = new ArrayList<>(w.vertexNumber());
/*  820 */     while (i < w.vertexNumber()) {
/*  822 */       i++;
/*  824 */       while (i < w.vertexNumber())
/*  825 */         i++; 
/*  828 */       List<Point2D> points = new ArrayList<>();
/*  829 */       for (int x = 0; x < w.vertexNumber(); x++)
/*  830 */         points.add(w.vertex(x)); 
/*  832 */       buildSimplifiedNodeList(points, lower, FastMath.min(w.vertexNumber() - 1, i), threshold, newNodes);
/*  833 */       lower = i;
/*  834 */       i++;
/*      */     } 
/*  837 */     LinearRing2D simplifiedWay = new LinearRing2D(newNodes);
/*  838 */     return simplifiedWay;
/*      */   }
/*      */   
/*      */   protected static void buildSimplifiedNodeList(List<Point2D> wnew, int from, int to, double threshold, List<Point2D> simplifiedNodes) {
/*  853 */     Point2D fromN = wnew.get(from);
/*  854 */     Point2D toN = wnew.get(to);
/*  857 */     int imax = -1;
/*  858 */     double xtemax = 0.0D;
/*  859 */     for (int i = from + 1; i < to; i++) {
/*  860 */       Point2D n = wnew.get(i);
/*  861 */       double xte = FastMath.abs(6378137.0D * xtd(fromN.y() * Math.PI / 180.0D, fromN.x() * Math.PI / 180.0D, toN.y() * Math.PI / 180.0D, toN.x() * Math.PI / 180.0D, n.y() * Math.PI / 180.0D, n.x() * Math.PI / 180.0D));
/*  865 */       if (xte > xtemax) {
/*  866 */         xtemax = xte;
/*  867 */         imax = i;
/*      */       } 
/*      */     } 
/*  871 */     if (imax != -1 && xtemax >= threshold) {
/*  873 */       buildSimplifiedNodeList(wnew, from, imax, threshold, simplifiedNodes);
/*  874 */       buildSimplifiedNodeList(wnew, imax, to, threshold, simplifiedNodes);
/*      */     } else {
/*  877 */       if (simplifiedNodes.isEmpty() || simplifiedNodes.get(simplifiedNodes.size() - 1) != fromN)
/*  878 */         simplifiedNodes.add(fromN); 
/*  880 */       if (fromN != toN)
/*  881 */         simplifiedNodes.add(toN); 
/*      */     } 
/*      */   }
/*      */   
/*      */   public static Point2D getPolygonCenter(LinearRing2D polygon) {
/*  887 */     Point2D center = null;
/*  888 */     if (polygon == null || polygon.vertexNumber() == 0)
/*  889 */       return null; 
/*  891 */     if (polygon.vertexNumber() > 3) {
/*  892 */       CentroidArea centroidArea = new CentroidArea();
/*  893 */       List<Coordinate> ring = new ArrayList<>();
/*  894 */       for (int x = 0; x < polygon.vertexNumber(); x++) {
/*  895 */         Point2D pt = polygon.vertex(x);
/*  896 */         ring.add(new Coordinate(pt.x(), pt.y()));
/*      */       } 
/*  898 */       Coordinate[] coordinates = new Coordinate[ring.size()];
/*  899 */       centroidArea.add(ring.<Coordinate>toArray(coordinates));
/*  900 */       center = new Point2D((centroidArea.getCentroid()).x, (centroidArea.getCentroid()).y);
/*      */     } else {
/*  903 */       center = polygon.firstPoint();
/*      */     } 
/*  905 */     return center;
/*      */   }
/*      */   
/*      */   public static double dist(double lat1, double lon1, double lat2, double lon2) {
/*  915 */     return 2.0D * FastMath.asin(FastMath.sqrt(FastMath.pow(FastMath.sin((lat1 - lat2) / 2.0D), 2.0D) + FastMath.cos(lat1) * FastMath.cos(lat2) * FastMath.pow(FastMath.sin((lon1 - lon2) / 2.0D), 2.0D)));
/*      */   }
/*      */   
/*      */   public static double course(double lat1, double lon1, double lat2, double lon2) {
/*  920 */     return FastMath.atan2(FastMath.sin(lon1 - lon2) * FastMath.cos(lat2), FastMath.cos(lat1) * FastMath.sin(lat2) - FastMath.sin(lat1) * FastMath.cos(lat2) * FastMath.cos(lon1 - lon2)) % 6.283185307179586D;
/*      */   }
/*      */   
/*      */   public static double xtd(double lat1, double lon1, double lat2, double lon2, double lat3, double lon3) {
/*  926 */     double dist_AD = dist(lat1, lon1, lat3, lon3);
/*  927 */     double crs_AD = course(lat1, lon1, lat3, lon3);
/*  928 */     double crs_AB = course(lat1, lon1, lat2, lon2);
/*  929 */     return FastMath.asin(FastMath.sin(dist_AD) * FastMath.sin(crs_AD - crs_AB));
/*      */   }
/*      */   
/*      */   public static OSMMultiPolygon mergeShapes(ArrayList<OSMRelation.Member> outer, ArrayList<OSMRelation.Member> inner) {
/*  937 */     OSMMultiPolygon outerMerged = mergeShapes(outer);
/*  939 */     OSMMultiPolygon innerMerged = mergeShapes(inner);
/*  941 */     OSMMultiPolygon merged = new OSMMultiPolygon();
/*  942 */     merged.setOuterRings(outerMerged.getOuterRings());
/*  943 */     merged.setInnerRings(innerMerged.getInnerRings());
/*  944 */     return merged;
/*      */   }
/*      */   
/*      */   public static OSMMultiPolygon mergeShapes(ArrayList<OSMRelation.Member> shapes) {
/*  950 */     OSMMultiPolygon merged = new OSMMultiPolygon();
/*  951 */     merged.setOuterRings(new ArrayList());
/*  952 */     merged.setInnerRings(new ArrayList());
/*  954 */     ArrayList<OSMRelation.Member> rings = new ArrayList<>();
/*  955 */     for (OSMRelation.Member item : shapes)
/*  956 */       rings.add(item); 
/*      */     try {
/*  962 */       MultipolygonCreate multipolygonCreate = new MultipolygonCreate();
/*  965 */       if (multipolygonCreate.makeFromRings(rings)) {
/*  967 */         for (MultipolygonCreate.JoinedPolygon item : multipolygonCreate.innerWays) {
/*  969 */           OSMRelation.Member member = new OSMRelation.Member();
/*  970 */           member.rules = item.member.rules;
/*  971 */           OSMPolygon linearRing2D = item.member.shape.clone();
/*  972 */           linearRing2D.setShape(new LinearRing2D());
/*  973 */           if (item.nodes != null)
/*  974 */             for (Node node : item.nodes)
/*  975 */               linearRing2D.addVertex(new Point2D(node.getLon(), node.getLat()));  
/*  979 */           if (!linearRing2D.firstPoint().equals(linearRing2D.vertex(linearRing2D.vertexNumber() - 1)))
/*  980 */             linearRing2D.addVertex(linearRing2D.firstPoint()); 
/*  982 */           member.shape = linearRing2D;
/*  983 */           merged.getInnerRings().add(member);
/*      */         } 
/*  986 */         for (MultipolygonCreate.JoinedPolygon item : multipolygonCreate.outerWays) {
/*  987 */           OSMRelation.Member member = new OSMRelation.Member();
/*  988 */           member.rules = item.member.rules;
/*  989 */           OSMPolygon linearRing2D = item.member.shape.clone();
/*  990 */           linearRing2D.setShape(new LinearRing2D());
/*  991 */           if (item.nodes != null)
/*  992 */             for (Node node : item.nodes)
/*  993 */               linearRing2D.addVertex(node.getCoord());  
/*  996 */           if (!linearRing2D.firstPoint().equals(linearRing2D.vertex(linearRing2D.vertexNumber() - 1)))
/*  997 */             linearRing2D.addVertex(linearRing2D.firstPoint()); 
/*  999 */           member.shape = linearRing2D;
/* 1000 */           merged.getOuterRings().add(member);
/*      */         } 
/*      */       } 
/* 1006 */       return merged;
/* 1007 */     } catch (Exception e) {
/* 1025 */       log.error("Couldn't merge multipolygon", e);
/* 1026 */       return new OSMMultiPolygon();
/*      */     } 
/*      */   }
/*      */   
/*      */   public static LinearRing2D getMinimumRectangle(LinearRing2D shape) {
/* 1034 */     if (shape == null)
/* 1035 */       return shape; 
/*      */     try {
/* 1038 */       LinearRing2D rect = MinimumBoundingBox.BoundingBox(shape);
/* 1039 */       return rect;
/* 1040 */     } catch (Exception e) {
/* 1041 */       e.printStackTrace();
/* 1042 */       return shape;
/*      */     } 
/*      */   }
/*      */   
/*      */   public static LinearRing2D getMinimumRectangle(Polygon polygon) {
/* 1047 */     MinimumDiameter minimumDiameter = new MinimumDiameter((Geometry)polygon);
/* 1048 */     Geometry out = minimumDiameter.getMinimumRectangle();
/* 1049 */     LinearRing2D outRing = polygonToLinearRing2D(out);
/* 1050 */     return outRing;
/*      */   }
/*      */   
/*      */   public static LineSegment2D getLongestLine(LinearRing2D shape) {
/* 1055 */     LineSegment2D longestLine = shape.firstEdge();
/* 1056 */     for (LineSegment2D edge : shape.edges()) {
/* 1057 */       if (edge.length() > longestLine.length())
/* 1058 */         longestLine = edge; 
/*      */     } 
/* 1061 */     return longestLine;
/*      */   }
/*      */   
/*      */   public static LineSegment2D getShortestLine(LinearRing2D shape) {
/* 1065 */     LineSegment2D shortestLine = shape.firstEdge();
/* 1066 */     for (LineSegment2D edge : shape.edges()) {
/* 1067 */       if (edge.length() < shortestLine.length())
/* 1068 */         shortestLine = edge; 
/*      */     } 
/* 1071 */     return shortestLine;
/*      */   }
/*      */   
/*      */   public static boolean isClosed(LinearRing2D closed) {
/* 1078 */     return closed.firstPoint().equals(closed.vertex(closed.vertexNumber() - 1));
/*      */   }
/*      */   
/*      */   public static boolean selfIntersects(OSMPolygon linearRing2D) {
/* 1082 */     if (linearRing2D.vertexNumber() < 5)
/* 1083 */       return false; 
/*      */     try {
/* 1086 */       Geometry jtsShape = osmPolygonToJTSPolygon(linearRing2D);
/* 1088 */       IsValidOp validOp = new IsValidOp(jtsShape);
/* 1089 */       TopologyValidationError err = validOp.getValidationError();
/* 1090 */       if (err != null && err.getErrorType() == 6)
/* 1091 */         return true; 
/* 1093 */     } catch (Exception e) {
/* 1094 */       return false;
/*      */     } 
/* 1096 */     return false;
/*      */   }
/*      */   
/*      */   public static OSMPolygon testAndRepairSelfIntersection(OSMPolygon osmPolygon) {
/* 1100 */     if (osmPolygon.vertexNumber() < 5)
/* 1101 */       return osmPolygon; 
/*      */     try {
/* 1104 */       Geometry jtsShape = osmPolygonToJTSPolygon(osmPolygon);
/* 1106 */       IsValidOp validOp = new IsValidOp(jtsShape);
/* 1107 */       TopologyValidationError err = validOp.getValidationError();
/* 1108 */       if (err != null && err.getErrorType() == 6) {
/* 1109 */         if (err != null)
/* 1110 */           log.error("Found self intersecting way for id #" + osmPolygon.getIdentifier() + ", please check OSM"); 
/* 1123 */         return osmPolygon;
/*      */       } 
/* 1125 */       return osmPolygon;
/* 1127 */     } catch (Exception e) {
/* 1128 */       return osmPolygon;
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void close(LinearRing2D item) {
/* 1134 */     item.addVertex(item.firstPoint());
/*      */   }
/*      */   
/* 1138 */   private static byte INSIDE = 0;
/*      */   
/* 1139 */   private static byte LEFT = 1;
/*      */   
/* 1140 */   private static byte RIGHT = 2;
/*      */   
/* 1141 */   private static byte BOTTOM = 4;
/*      */   
/* 1142 */   private static byte TOP = 8;
/*      */   
/*      */   private static int computeOutCode(double x, double y, double xmin, double ymin, double xmax, double ymax) {
/* 1150 */     int code = 0;
/* 1151 */     if (y > ymax) {
/* 1152 */       code |= TOP;
/* 1153 */     } else if (y < ymin) {
/* 1154 */       code |= BOTTOM;
/*      */     } 
/* 1155 */     if (x > xmax) {
/* 1156 */       code |= RIGHT;
/* 1157 */     } else if (x < xmin) {
/* 1158 */       code |= LEFT;
/*      */     } 
/* 1159 */     return code;
/*      */   }
/*      */   
/*      */   public static LineSegment clipSegment(LineSegment lineSegment, Box2D box) {
/* 1165 */     double x0 = lineSegment.p0.x;
/* 1166 */     double y0 = lineSegment.p0.y;
/* 1167 */     double x1 = lineSegment.p1.x;
/* 1168 */     double y1 = lineSegment.p1.y;
/* 1172 */     int outcode0 = computeOutCode(x0, y0, box.getMinX(), box.getMinY(), box.getMaxX(), box.getMaxY());
/* 1173 */     int outcode1 = computeOutCode(x1, y1, box.getMinX(), box.getMinY(), box.getMaxX(), box.getMaxY());
/* 1175 */     int step = 0;
/*      */     do {
/*      */       double x, y;
/* 1177 */       if ((outcode0 | outcode1) == 0) {
/* 1179 */         if (x0 == x1 && y0 == y1)
/* 1180 */           return null; 
/* 1184 */         return new LineSegment(x0, y0, x1, y1);
/*      */       } 
/* 1185 */       if ((outcode0 & outcode1) > 0)
/* 1188 */         return null; 
/* 1194 */       int outcodeOut = (outcode0 != 0) ? outcode0 : outcode1;
/* 1202 */       if ((outcodeOut & TOP) > 0) {
/* 1203 */         x = x0 + (x1 - x0) * (box.getMaxY() - y0) / (y1 - y0);
/* 1204 */         y = box.getMaxY();
/* 1205 */       } else if ((outcodeOut & BOTTOM) > 0) {
/* 1206 */         x = x0 + (x1 - x0) * (box.getMinY() - y0) / (y1 - y0);
/* 1207 */         y = box.getMinY();
/* 1208 */       } else if ((outcodeOut & RIGHT) > 0) {
/* 1209 */         y = y0 + (y1 - y0) * (box.getMaxX() - x0) / (x1 - x0);
/* 1210 */         x = box.getMaxX();
/*      */       } else {
/* 1212 */         y = y0 + (y1 - y0) * (box.getMinX() - x0) / (x1 - x0);
/* 1213 */         x = box.getMinX();
/*      */       } 
/* 1217 */       if (outcodeOut == outcode0) {
/* 1218 */         x0 = x;
/* 1219 */         y0 = y;
/* 1220 */         outcode0 = computeOutCode(x0, y0, box.getMinX(), box.getMinY(), box.getMaxX(), box.getMaxY());
/*      */       } else {
/* 1222 */         x1 = x;
/* 1223 */         y1 = y;
/* 1224 */         outcode1 = computeOutCode(x1, y1, box.getMinX(), box.getMinY(), box.getMaxX(), box.getMaxY());
/*      */       } 
/* 1228 */       ++step;
/* 1229 */     } while (step < 5);
/* 1233 */     throw new RuntimeException("Algorithm did not converge");
/*      */   }
/*      */   
/*      */   public static Point2D toMercator(Point2D latLon) {
/* 1237 */     return new Point2D(merc_x(latLon.x()), merc_y(latLon.y()));
/*      */   }
/*      */   
/*      */   public static Point2D toLatLon(Point2D merc) {
/* 1241 */     return new Point2D(merc_lon(merc.x()), merc_lat(merc.y()));
/*      */   }
/*      */   
/*      */   public static void setShapeFromJTS(OSMShape newShape, Geometry geometry) {
/* 1247 */     if (newShape.outer == null)
/* 1248 */       newShape.outer = new OSMPolygon(); 
/* 1249 */     if (geometry instanceof LineString) {
/* 1250 */       newShape.outer.setShape(polygonToLinearRing2D(geometry));
/*      */       return;
/*      */     } 
/* 1254 */     newShape.outer.setShape(polygonToLinearRing2D(geometry.buffer(0.0D)));
/* 1257 */     if (geometry instanceof Polygon) {
/* 1258 */       Polygon poly = (Polygon)geometry;
/* 1259 */       if (((Polygon)geometry).getNumInteriorRing() > 0) {
/* 1260 */         newShape.inner = new ArrayList();
/* 1261 */         for (int x = 0; x < poly.getNumInteriorRing(); x++) {
/* 1262 */           OSMPolygon inner = new OSMPolygon();
/* 1263 */           inner.setShape(polygonToLinearRing2D((Geometry)poly.getInteriorRingN(x)));
/* 1264 */           if (inner.getShape() != null && inner.getShape().vertexNumber() > 0)
/* 1265 */             newShape.inner.add(inner); 
/*      */         } 
/*      */       } 
/* 1270 */     } else if (geometry instanceof MultiLineString) {
/* 1271 */       MultiLineString poly = (MultiLineString)geometry;
/* 1272 */       if (poly.getNumGeometries() > 0) {
/* 1273 */         newShape.inner = new ArrayList();
/* 1274 */         for (int x = 0; x < poly.getNumGeometries(); x++) {
/* 1275 */           OSMPolygon inner = new OSMPolygon();
/* 1276 */           inner.setShape(polygonToLinearRing2D(poly.getGeometryN(x)));
/* 1277 */           if (inner.getShape() != null && inner.getShape().vertexNumber() > 0)
/* 1278 */             newShape.inner.add(inner); 
/*      */         } 
/*      */       } 
/*      */     } else {
/* 1284 */       throw new RuntimeException("Here");
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void simplify(OSMShape osmShape, double metreXSize) {
/* 1293 */     if (osmShape.outer.vertexNumber() < 2)
/*      */       return; 
/*      */     try {
/* 1297 */       if (!osmShape.outer.isClosed()) {
/* 1298 */         LineString line = osmShapeToJTSLineString(osmShape);
/* 1299 */         if (line == null)
/*      */           return; 
/* 1303 */         TopologyPreservingSimplifier simplifier = new TopologyPreservingSimplifier((Geometry)line);
/* 1304 */         simplifier.setDistanceTolerance(metreXSize * 0.5D);
/* 1305 */         Geometry buffer = simplifier.getResultGeometry();
/* 1306 */         if (buffer != null)
/* 1307 */           setShapeFromJTS(osmShape, buffer); 
/*      */       } else {
/* 1311 */         Geometry toBuffer = osmShapeToJTS(osmShape);
/* 1312 */         TopologyPreservingSimplifier simplifier = new TopologyPreservingSimplifier(toBuffer);
/* 1313 */         simplifier.setDistanceTolerance(metreXSize * 0.5D);
/* 1314 */         Geometry buffer = simplifier.getResultGeometry();
/* 1315 */         if (buffer != null)
/* 1316 */           setShapeFromJTS(osmShape, buffer); 
/* 1318 */         if (buffer != null)
/* 1319 */           setShapeFromJTS(osmShape, buffer); 
/*      */       } 
/* 1322 */     } catch (Exception e) {}
/*      */   }
/*      */   
/*      */   public static OSMPolygon test(OSMPolygon outer, double metreXSize) {
/* 1331 */     if (outer.vertexNumber() < 2)
/* 1332 */       return outer; 
/* 1335 */     Geometry geometry = outer.getGeometry();
/* 1339 */     Geometry buffer = ShortEdgesDeletion.get((Polygon)geometry, metreXSize * 15.0D);
/* 1340 */     if (buffer != null && buffer.getNumPoints() > 4)
/* 1341 */       outer.setShape(polygonToLinearRing2D(buffer)); 
/* 1344 */     return outer;
/*      */   }
/*      */   
/*      */   public static List<OSMPolygon> jtsToOsmPolygon(Geometry shape) {
/* 1350 */     ArrayList<OSMPolygon> osmPolygons = new ArrayList<>();
/* 1351 */     if (shape == null)
/* 1352 */       return osmPolygons; 
/* 1355 */     if (shape instanceof LineString) {
/* 1356 */       LinearRing2D linearRing2D = new LinearRing2D();
/* 1357 */       LineString lineString = (LineString)shape;
/* 1358 */       for (int x = 0; x < lineString.getNumPoints(); x++) {
/* 1359 */         Coordinate coord = lineString.getCoordinateN(x);
/* 1360 */         Point2D point2d = new Point2D(coord.x, coord.y);
/* 1361 */         linearRing2D.addVertex(point2d);
/*      */       } 
/* 1363 */       osmPolygons.add(new OSMPolygon(linearRing2D));
/*      */     } 
/* 1366 */     if (shape instanceof MultiLineString) {
/* 1368 */       MultiLineString multiLineString = (MultiLineString)shape;
/* 1369 */       for (int z = 0; z < multiLineString.getNumGeometries(); z++) {
/* 1371 */         LinearRing2D linearRing2D = new LinearRing2D();
/* 1372 */         LineString lineString = (LineString)multiLineString.getGeometryN(z);
/* 1373 */         for (int x = 0; x < lineString.getNumPoints(); x++) {
/* 1374 */           Coordinate coord = lineString.getCoordinateN(x);
/* 1375 */           Point2D point2d = new Point2D(coord.x, coord.y);
/* 1376 */           linearRing2D.addVertex(point2d);
/*      */         } 
/* 1378 */         osmPolygons.add(new OSMPolygon(linearRing2D));
/*      */       } 
/*      */     } 
/* 1382 */     if (shape instanceof Polygon) {
/* 1383 */       LinearRing2D linearRing2D = new LinearRing2D();
/* 1384 */       Polygon poly = (Polygon)shape;
/* 1385 */       LineString exteriorRing = poly.getExteriorRing();
/* 1386 */       for (int x = 0; x < exteriorRing.getNumPoints(); x++) {
/* 1387 */         Coordinate coord = exteriorRing.getCoordinateN(x);
/* 1388 */         Point2D point2d = new Point2D(coord.x, coord.y);
/* 1389 */         linearRing2D.addVertex(point2d);
/*      */       } 
/* 1391 */       osmPolygons.add(new OSMPolygon(linearRing2D));
/*      */     } 
/* 1393 */     return osmPolygons;
/*      */   }
/*      */   
/*      */   public static LineSegment2D getNextLine(LinearRing2D smallestRectangle, LineSegment2D longestLine) {
/* 1397 */     int index = 0;
/* 1398 */     for (int x = 0; x < smallestRectangle.edgeNumber(); x++) {
/* 1399 */       LineSegment2D edge = smallestRectangle.edge(x);
/* 1400 */       if (edge.equals(longestLine))
/* 1401 */         index = x; 
/*      */     } 
/* 1404 */     if (index >= smallestRectangle.edgeNumber())
/* 1405 */       index = 0; 
/* 1407 */     return smallestRectangle.edge(index);
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Geom\GeomUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
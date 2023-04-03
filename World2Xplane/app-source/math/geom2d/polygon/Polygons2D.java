/*     */ package math.geom2d.polygon;
/*     */ 
/*     */ import com.seisw.util.geom.Point2D;
/*     */ import com.seisw.util.geom.Poly;
/*     */ import com.seisw.util.geom.PolyDefault;
/*     */ import com.seisw.util.geom.PolySimple;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import math.geom2d.Box2D;
/*     */ import math.geom2d.Point2D;
/*     */ import math.geom2d.circulinear.CirculinearContourArray2D;
/*     */ import math.geom2d.circulinear.CirculinearCurve2D;
/*     */ import math.geom2d.circulinear.CirculinearDomain2D;
/*     */ import math.geom2d.circulinear.buffer.BufferCalculator;
/*     */ import math.geom2d.domain.Boundaries2D;
/*     */ import math.geom2d.domain.Boundary2D;
/*     */ import math.geom2d.domain.Contour2D;
/*     */ import math.geom2d.domain.ContourArray2D;
/*     */ import math.geom2d.point.PointSets2D;
/*     */ 
/*     */ public final class Polygons2D {
/*     */   public static final SimplePolygon2D createRectangle(Point2D p1, Point2D p2) {
/*  42 */     double x1 = p1.x();
/*  43 */     double y1 = p1.y();
/*  44 */     double x2 = p2.x();
/*  45 */     double y2 = p2.y();
/*  47 */     return createRectangle(x1, y1, x2, y2);
/*     */   }
/*     */   
/*     */   public static final SimplePolygon2D createRectangle(double x1, double y1, double x2, double y2) {
/*  58 */     double xmin = Math.min(x1, x2);
/*  59 */     double xmax = Math.max(x1, x2);
/*  60 */     double ymin = Math.min(y1, y2);
/*  61 */     double ymax = Math.max(y1, y2);
/*  64 */     return new SimplePolygon2D(new Point2D[] { new Point2D(xmin, ymin), 
/*  66 */           new Point2D(xmax, ymin), 
/*  67 */           new Point2D(xmax, ymax), 
/*  68 */           new Point2D(xmin, ymax) });
/*     */   }
/*     */   
/*     */   public static final SimplePolygon2D createCenteredRectangle(Point2D center, double length, double width) {
/*  80 */     double xc = center.x();
/*  81 */     double yc = center.y();
/*  82 */     double len = length / 2.0D;
/*  83 */     double wid = width / 2.0D;
/*  86 */     double x1 = xc - len;
/*  87 */     double y1 = yc - wid;
/*  88 */     double x2 = xc + len;
/*  89 */     double y2 = yc + wid;
/*  92 */     return new SimplePolygon2D(new Point2D[] { new Point2D(x1, y1), 
/*  94 */           new Point2D(x2, y1), 
/*  95 */           new Point2D(x2, y2), 
/*  96 */           new Point2D(x1, y2) });
/*     */   }
/*     */   
/*     */   public static final SimplePolygon2D createOrientedRectangle(Point2D center, double length, double width, double theta) {
/* 109 */     double xc = center.x();
/* 110 */     double yc = center.y();
/* 111 */     double len = length / 2.0D;
/* 112 */     double wid = width / 2.0D;
/* 115 */     double cot = Math.cos(theta);
/* 116 */     double sit = Math.sin(theta);
/* 119 */     return new SimplePolygon2D(new Point2D[] { new Point2D(-len * cot + wid * sit + xc, -len * sit - wid * cot + yc), 
/* 121 */           new Point2D(len * cot + wid * sit + xc, len * sit - wid * cot + yc), 
/* 122 */           new Point2D(len * cot - wid * sit + xc, len * sit + wid * cot + yc), 
/* 123 */           new Point2D(-len * cot - wid * sit + xc, -len * sit + wid * cot + yc) });
/*     */   }
/*     */   
/*     */   public static final Point2D computeCentroid(Polygon2D polygon) {
/* 133 */     if (polygon instanceof SimplePolygon2D) {
/* 134 */       LinearRing2D ring = ((SimplePolygon2D)polygon).getRing();
/* 135 */       return computeCentroid(ring);
/*     */     } 
/* 138 */     double xc = 0.0D;
/* 139 */     double yc = 0.0D;
/* 141 */     double cumArea = 0.0D;
/* 144 */     for (LinearRing2D ring : polygon.contours()) {
/* 145 */       double area = computeArea(ring);
/* 146 */       Point2D centroid = computeCentroid(ring);
/* 147 */       xc += centroid.x() * area;
/* 148 */       yc += centroid.y() * area;
/* 149 */       cumArea += area;
/*     */     } 
/* 152 */     xc /= cumArea;
/* 153 */     yc /= cumArea;
/* 154 */     return new Point2D(xc, yc);
/*     */   }
/*     */   
/*     */   public static final Point2D computeCentroid(LinearRing2D ring) {
/* 162 */     double xc = 0.0D;
/* 163 */     double yc = 0.0D;
/* 167 */     double tmp = 0.0D;
/* 170 */     int n = ring.vertexNumber();
/* 173 */     Point2D prev = ring.vertex(n - 1);
/* 174 */     double xp = prev.x();
/* 175 */     double yp = prev.y();
/* 178 */     for (Point2D point : ring.vertices()) {
/* 179 */       double x = point.x();
/* 180 */       double y = point.y();
/* 181 */       tmp = xp * y - yp * x;
/* 182 */       xc += (x + xp) * tmp;
/* 183 */       yc += (y + yp) * tmp;
/* 185 */       prev = point;
/* 186 */       xp = x;
/* 187 */       yp = y;
/*     */     } 
/* 190 */     double denom = computeArea(ring) * 6.0D;
/* 191 */     return new Point2D(xc / denom, yc / denom);
/*     */   }
/*     */   
/*     */   public static final double computeArea(Polygon2D polygon) {
/* 206 */     double area = 0.0D;
/* 207 */     for (LinearRing2D ring : polygon.contours())
/* 208 */       area += computeArea(ring); 
/* 210 */     return area;
/*     */   }
/*     */   
/*     */   public static final double computeArea(LinearRing2D ring) {
/* 224 */     double area = 0.0D;
/* 227 */     int n = ring.vertexNumber();
/* 230 */     Point2D prev = ring.vertex(n - 1);
/* 233 */     for (Point2D point : ring.vertices()) {
/* 234 */       area += prev.x() * point.y() - prev.y() * point.x();
/* 235 */       prev = point;
/*     */     } 
/* 238 */     return area /= 2.0D;
/*     */   }
/*     */   
/*     */   public static final int windingNumber(Collection<Point2D> vertices, Point2D point) {
/* 252 */     int wn = 0;
/* 255 */     Point2D previous = null;
/* 256 */     for (Point2D vertex : vertices)
/* 257 */       previous = vertex; 
/* 258 */     double y1 = previous.y();
/* 262 */     double y = point.y();
/* 265 */     for (Point2D current : vertices) {
/* 267 */       double y2 = current.y();
/* 269 */       if (y1 <= y) {
/* 270 */         if (y2 > y && 
/* 271 */           isLeft(previous, current, point) > 0)
/* 272 */           wn++; 
/* 274 */       } else if (y2 <= y && 
/* 275 */         isLeft(previous, current, point) < 0) {
/* 276 */         wn--;
/*     */       } 
/* 280 */       y1 = y2;
/* 281 */       previous = current;
/*     */     } 
/* 284 */     return wn;
/*     */   }
/*     */   
/*     */   private static final int isLeft(Point2D p1, Point2D p2, Point2D pt) {
/* 296 */     double x = p1.x();
/* 297 */     double y = p1.y();
/* 298 */     return (int)Math.signum((
/* 299 */         p2.x() - x) * (pt.y() - y) - (pt.x() - x) * (p2.y() - y));
/*     */   }
/*     */   
/*     */   public static final CirculinearDomain2D createBuffer(Polygon2D polygon, double dist) {
/* 310 */     BufferCalculator bc = BufferCalculator.getDefaultInstance();
/* 313 */     return bc.computeBuffer((CirculinearCurve2D)polygon.boundary(), dist);
/*     */   }
/*     */   
/*     */   public static final Polygon2D clipPolygon(Polygon2D polygon, Box2D box) {
/* 323 */     CirculinearContourArray2D<? extends LinearRing2D> circulinearContourArray2D = polygon.boundary();
/* 324 */     ContourArray2D<Contour2D> contours = 
/* 325 */       Boundaries2D.clipBoundary((Boundary2D)circulinearContourArray2D, box);
/* 328 */     ArrayList<LinearRing2D> rings = new ArrayList<LinearRing2D>();
/* 329 */     for (Contour2D contour : contours)
/* 330 */       rings.add(convertContourToLinearRing(contour)); 
/* 334 */     if (rings.size() == 1)
/* 335 */       return SimplePolygon2D.create(((LinearRing2D)rings.get(0)).vertices()); 
/* 337 */     return MultiPolygon2D.create(rings);
/*     */   }
/*     */   
/*     */   private static final LinearRing2D convertContourToLinearRing(Contour2D contour) {
/* 343 */     if (contour instanceof LinearRing2D)
/* 344 */       return (LinearRing2D)contour; 
/* 347 */     List<Point2D> vertices = new ArrayList<Point2D>();
/* 348 */     for (Point2D v : contour.singularPoints())
/* 349 */       vertices.add(v); 
/* 352 */     vertices = PointSets2D.filterMultipleVertices(vertices, true);
/* 355 */     return LinearRing2D.create(vertices);
/*     */   }
/*     */   
/*     */   public static final Polygon2D union(Polygon2D polygon1, Polygon2D polygon2) {
/* 365 */     Poly poly1 = convertToGpcjPolygon(polygon1);
/* 366 */     Poly poly2 = convertToGpcjPolygon(polygon2);
/* 369 */     Poly result = poly1.union(poly2);
/* 372 */     return convertFromGpcjPolygon(result);
/*     */   }
/*     */   
/*     */   public static final Polygon2D intersection(Polygon2D polygon1, Polygon2D polygon2) {
/* 382 */     Poly poly1 = convertToGpcjPolygon(polygon1);
/* 383 */     Poly poly2 = convertToGpcjPolygon(polygon2);
/* 386 */     Poly result = poly1.intersection(poly2);
/* 389 */     return convertFromGpcjPolygon(result);
/*     */   }
/*     */   
/*     */   public static final Polygon2D exclusiveOr(Polygon2D polygon1, Polygon2D polygon2) {
/* 399 */     Poly poly1 = convertToGpcjPolygon(polygon1);
/* 400 */     Poly poly2 = convertToGpcjPolygon(polygon2);
/* 403 */     Poly result = poly1.xor(poly2);
/* 406 */     return convertFromGpcjPolygon(result);
/*     */   }
/*     */   
/*     */   public static final Polygon2D difference(Polygon2D polygon1, Polygon2D polygon2) {
/* 417 */     Poly poly1 = convertToGpcjPolygon(polygon1);
/* 418 */     Poly poly2 = convertToGpcjPolygon(polygon2);
/* 421 */     Poly result = poly1.difference(poly2);
/* 424 */     return convertFromGpcjPolygon(result);
/*     */   }
/*     */   
/*     */   private static final Poly convertToGpcjPolygon(Polygon2D polygon) {
/* 428 */     PolyDefault result = new PolyDefault();
/* 429 */     for (LinearRing2D ring : polygon.contours())
/* 430 */       result.add((Poly)convertToGpcjSimplePolygon(ring)); 
/* 431 */     return (Poly)result;
/*     */   }
/*     */   
/*     */   private static final PolySimple convertToGpcjSimplePolygon(LinearRing2D ring) {
/* 436 */     PolySimple poly = new PolySimple();
/* 437 */     for (Point2D point : ring.vertices())
/* 438 */       poly.add(new Point2D(point.x(), point.y())); 
/* 439 */     return poly;
/*     */   }
/*     */   
/*     */   private static final Polygon2D convertFromGpcjPolygon(Poly poly) {
/* 444 */     int n = poly.getNumInnerPoly();
/* 447 */     if (n == 1) {
/* 448 */       Point2D[] points = extractPolyVertices(poly.getInnerPoly(0));
/* 449 */       return SimplePolygon2D.create(points);
/*     */     } 
/* 453 */     LinearRing2D[] rings = new LinearRing2D[n];
/* 454 */     for (int i = 0; i < n; i++)
/* 455 */       rings[i] = convertFromGpcjSimplePolygon(poly.getInnerPoly(i)); 
/* 458 */     return MultiPolygon2D.create(rings);
/*     */   }
/*     */   
/*     */   private static final LinearRing2D convertFromGpcjSimplePolygon(Poly poly) {
/* 463 */     return LinearRing2D.create(extractPolyVertices(poly));
/*     */   }
/*     */   
/*     */   private static final Point2D[] extractPolyVertices(Poly poly) {
/* 467 */     int n = poly.getNumPoints();
/* 468 */     Point2D[] points = new Point2D[n];
/* 469 */     for (int i = 0; i < n; i++)
/* 470 */       points[i] = new Point2D(poly.getX(i), poly.getY(i)); 
/* 471 */     return points;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\polygon\Polygons2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
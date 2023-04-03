/*     */ package math.geom2d.polygon;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import math.geom2d.AffineTransform2D;
/*     */ import math.geom2d.Box2D;
/*     */ import math.geom2d.GeometricObject2D;
/*     */ import math.geom2d.Point2D;
/*     */ import math.geom2d.Shape2D;
/*     */ import math.geom2d.circulinear.CirculinearBoundary2D;
/*     */ import math.geom2d.circulinear.CirculinearContour2D;
/*     */ import math.geom2d.circulinear.CirculinearContourArray2D;
/*     */ import math.geom2d.circulinear.CirculinearDomain2D;
/*     */ import math.geom2d.circulinear.CirculinearDomains2D;
/*     */ import math.geom2d.circulinear.CirculinearShape2D;
/*     */ import math.geom2d.circulinear.GenericCirculinearDomain2D;
/*     */ import math.geom2d.domain.Boundary2D;
/*     */ import math.geom2d.domain.Domain2D;
/*     */ import math.geom2d.line.LineSegment2D;
/*     */ import math.geom2d.point.PointSets2D;
/*     */ import math.geom2d.transform.CircleInversion2D;
/*     */ 
/*     */ public class SimplePolygon2D implements Polygon2D {
/*     */   protected ArrayList<Point2D> vertices;
/*     */   
/*     */   public static SimplePolygon2D create(Collection<? extends Point2D> points) {
/*  57 */     return new SimplePolygon2D(points);
/*     */   }
/*     */   
/*     */   public static SimplePolygon2D create(Point2D... points) {
/*  66 */     return new SimplePolygon2D(points);
/*     */   }
/*     */   
/*     */   public SimplePolygon2D() {
/*  86 */     this.vertices = new ArrayList<Point2D>();
/*     */   }
/*     */   
/*     */   public SimplePolygon2D(Point2D... vertices) {
/*  95 */     this.vertices = new ArrayList<Point2D>(vertices.length);
/*     */     byte b;
/*     */     int i;
/*     */     Point2D[] arrayOfPoint2D;
/*  96 */     for (i = (arrayOfPoint2D = vertices).length, b = 0; b < i; ) {
/*  96 */       Point2D vertex = arrayOfPoint2D[b];
/*  97 */       this.vertices.add(vertex);
/*     */       b++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public SimplePolygon2D(double[] xcoords, double[] ycoords) {
/* 109 */     this.vertices = new ArrayList<Point2D>(xcoords.length);
/* 110 */     for (int i = 0; i < xcoords.length; i++)
/* 111 */       this.vertices.add(new Point2D(xcoords[i], ycoords[i])); 
/*     */   }
/*     */   
/*     */   public SimplePolygon2D(Collection<? extends Point2D> points) {
/* 115 */     this.vertices = new ArrayList<Point2D>(points.size());
/* 116 */     this.vertices.addAll(points);
/*     */   }
/*     */   
/*     */   public SimplePolygon2D(LinearRing2D ring) {
/* 125 */     this.vertices = new ArrayList<Point2D>(ring.vertexNumber());
/* 126 */     this.vertices.addAll(ring.vertices());
/*     */   }
/*     */   
/*     */   public int getWindingNumber(double x, double y) {
/* 142 */     return Polygons2D.windingNumber(this.vertices, new Point2D(x, y));
/*     */   }
/*     */   
/*     */   public LinearRing2D getRing() {
/* 150 */     return new LinearRing2D(this.vertices);
/*     */   }
/*     */   
/*     */   public void addVertex(Point2D point) {
/* 160 */     this.vertices.add(point);
/*     */   }
/*     */   
/*     */   public void insertVertex(int index, Point2D point) {
/* 168 */     this.vertices.add(index, point);
/*     */   }
/*     */   
/*     */   public void setVertex(int index, Point2D position) {
/* 175 */     this.vertices.set(index, position);
/*     */   }
/*     */   
/*     */   public boolean removeVertex(Point2D point) {
/* 184 */     return this.vertices.remove(point);
/*     */   }
/*     */   
/*     */   public void removeVertex(int index) {
/* 192 */     this.vertices.remove(index);
/*     */   }
/*     */   
/*     */   public void clearVertices() {
/* 199 */     this.vertices.clear();
/*     */   }
/*     */   
/*     */   public int closestVertexIndex(Point2D point) {
/* 206 */     double minDist = Double.POSITIVE_INFINITY;
/* 207 */     int index = -1;
/* 209 */     for (int i = 0; i < this.vertices.size(); i++) {
/* 210 */       double dist = ((Point2D)this.vertices.get(i)).distance(point);
/* 211 */       if (dist < minDist) {
/* 212 */         index = i;
/* 213 */         minDist = dist;
/*     */       } 
/*     */     } 
/* 217 */     return index;
/*     */   }
/*     */   
/*     */   public double area() {
/* 230 */     return Polygons2D.computeArea(this);
/*     */   }
/*     */   
/*     */   public Point2D centroid() {
/* 242 */     return Polygons2D.computeCentroid(this);
/*     */   }
/*     */   
/*     */   public Collection<Point2D> vertices() {
/* 250 */     return this.vertices;
/*     */   }
/*     */   
/*     */   public Point2D vertex(int i) {
/* 259 */     return this.vertices.get(i);
/*     */   }
/*     */   
/*     */   public int vertexNumber() {
/* 268 */     return this.vertices.size();
/*     */   }
/*     */   
/*     */   public Collection<LineSegment2D> edges() {
/* 276 */     int nPoints = this.vertices.size();
/* 277 */     ArrayList<LineSegment2D> edges = new ArrayList<LineSegment2D>(nPoints);
/* 279 */     if (nPoints == 0)
/* 280 */       return edges; 
/* 282 */     for (int i = 0; i < nPoints - 1; i++)
/* 283 */       edges.add(new LineSegment2D(this.vertices.get(i), this.vertices.get(i + 1))); 
/* 285 */     edges.add(new LineSegment2D(this.vertices.get(nPoints - 1), this.vertices.get(0)));
/* 287 */     return edges;
/*     */   }
/*     */   
/*     */   public int edgeNumber() {
/* 295 */     return this.vertices.size();
/*     */   }
/*     */   
/*     */   public CirculinearDomain2D transform(CircleInversion2D inv) {
/* 306 */     CirculinearContourArray2D circulinearContourArray2D = 
/* 307 */       boundary().transform(inv).reverse();
/* 308 */     return (CirculinearDomain2D)new GenericCirculinearDomain2D((CirculinearBoundary2D)circulinearContourArray2D);
/*     */   }
/*     */   
/*     */   public CirculinearDomain2D buffer(double dist) {
/* 316 */     if (PointSets2D.hasMultipleVertices(this.vertices, true)) {
/* 317 */       List<Point2D> pts2 = 
/* 318 */         PointSets2D.filterMultipleVertices(this.vertices, true);
/* 319 */       SimplePolygon2D poly2 = new SimplePolygon2D(pts2);
/* 320 */       return CirculinearDomains2D.computeBuffer(poly2, dist);
/*     */     } 
/* 324 */     return CirculinearDomains2D.computeBuffer(this, dist);
/*     */   }
/*     */   
/*     */   public Polygon2D asPolygon(int n) {
/* 334 */     return this;
/*     */   }
/*     */   
/*     */   public CirculinearContourArray2D<LinearRing2D> boundary() {
/* 341 */     Point2D[] array = new Point2D[this.vertices.size()];
/* 342 */     for (int i = 0; i < this.vertices.size(); i++)
/* 343 */       array[i] = this.vertices.get(i); 
/* 345 */     return new CirculinearContourArray2D(
/* 346 */         (CirculinearContour2D)new LinearRing2D(array));
/*     */   }
/*     */   
/*     */   public Collection<LinearRing2D> contours() {
/* 353 */     ArrayList<LinearRing2D> rings = new ArrayList<LinearRing2D>(1);
/* 354 */     rings.add(new LinearRing2D(this.vertices));
/* 355 */     return rings;
/*     */   }
/*     */   
/*     */   public SimplePolygon2D complement() {
/* 362 */     int nPoints = this.vertices.size();
/* 364 */     Point2D[] res = new Point2D[nPoints];
/* 366 */     if (nPoints > 0)
/* 367 */       res[0] = this.vertices.get(0); 
/* 369 */     for (int i = 1; i < nPoints; i++)
/* 370 */       res[i] = this.vertices.get(nPoints - i); 
/* 372 */     return new SimplePolygon2D(res);
/*     */   }
/*     */   
/*     */   public double distance(Point2D p) {
/* 385 */     return distance(p.x(), p.y());
/*     */   }
/*     */   
/*     */   public double distance(double x, double y) {
/* 394 */     double dist = boundary().signedDistance(x, y);
/* 395 */     return Math.max(dist, 0.0D);
/*     */   }
/*     */   
/*     */   public Polygon2D clip(Box2D box) {
/* 402 */     return Polygons2D.clipPolygon(this, box);
/*     */   }
/*     */   
/*     */   public Box2D boundingBox() {
/* 409 */     return boundary().boundingBox();
/*     */   }
/*     */   
/*     */   public boolean isBounded() {
/* 416 */     return (area() > 0.0D);
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 420 */     return (this.vertices.size() == 0);
/*     */   }
/*     */   
/*     */   public SimplePolygon2D transform(AffineTransform2D trans) {
/* 428 */     int nPoints = this.vertices.size();
/* 430 */     Point2D[] array = new Point2D[nPoints];
/* 431 */     Point2D[] res = new Point2D[nPoints];
/* 433 */     for (int i = 0; i < nPoints; i++) {
/* 434 */       array[i] = this.vertices.get(i);
/* 435 */       res[i] = new Point2D();
/*     */     } 
/* 437 */     trans.transform(array, res);
/* 439 */     SimplePolygon2D poly = new SimplePolygon2D(res);
/* 440 */     if (!trans.isDirect())
/* 441 */       poly = poly.complement(); 
/* 443 */     return poly;
/*     */   }
/*     */   
/*     */   public boolean contains(Point2D p) {
/* 454 */     return contains(p.x(), p.y());
/*     */   }
/*     */   
/*     */   public boolean contains(double x, double y) {
/* 462 */     if (boundary().contains(x, y))
/* 463 */       return true; 
/* 465 */     double area = area();
/* 466 */     int winding = getWindingNumber(x, y);
/* 467 */     if (area > 0.0D)
/* 468 */       return (winding == 1); 
/* 470 */     return (winding == 0);
/*     */   }
/*     */   
/*     */   public GeneralPath getGeneralPath() {
/* 478 */     GeneralPath path = new GeneralPath();
/* 479 */     if (this.vertices.size() < 2)
/* 480 */       return path; 
/* 483 */     Point2D point = this.vertices.get(0);
/* 484 */     path.moveTo((float)point.x(), (float)point.y());
/* 487 */     for (int i = 0; i < this.vertices.size(); i++) {
/* 488 */       point = this.vertices.get(i);
/* 489 */       path.lineTo((float)point.x(), (float)point.y());
/*     */     } 
/* 493 */     point = this.vertices.get(0);
/* 494 */     path.lineTo((float)point.x(), (float)point.y());
/* 495 */     path.closePath();
/* 497 */     return path;
/*     */   }
/*     */   
/*     */   public void draw(Graphics2D g2) {
/* 501 */     g2.draw(getGeneralPath());
/*     */   }
/*     */   
/*     */   public void fill(Graphics2D g) {
/* 505 */     g.fill(getGeneralPath());
/*     */   }
/*     */   
/*     */   public boolean almostEquals(GeometricObject2D obj, double eps) {
/* 516 */     if (this == obj)
/* 517 */       return true; 
/* 519 */     if (!(obj instanceof SimplePolygon2D))
/* 520 */       return false; 
/* 521 */     SimplePolygon2D polygon = (SimplePolygon2D)obj;
/* 523 */     int nv = vertexNumber();
/* 524 */     if (polygon.vertexNumber() != nv)
/* 525 */       return false; 
/* 527 */     for (int i = 0; i < nv; i++) {
/* 528 */       if (!vertex(i).almostEquals((GeometricObject2D)polygon.vertex(i), eps))
/* 529 */         return false; 
/*     */     } 
/* 532 */     return true;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 545 */     if (this == obj)
/* 546 */       return true; 
/* 547 */     if (!(obj instanceof SimplePolygon2D))
/* 548 */       return false; 
/* 550 */     SimplePolygon2D polygon = (SimplePolygon2D)obj;
/* 552 */     int nv = vertexNumber();
/* 553 */     if (polygon.vertexNumber() != nv)
/* 554 */       return false; 
/* 556 */     for (int i = 0; i < nv; i++) {
/* 557 */       if (!vertex(i).equals(polygon.vertex(i)))
/* 558 */         return false; 
/*     */     } 
/* 561 */     return true;
/*     */   }
/*     */   
/*     */   public SimplePolygon2D clone() {
/* 566 */     ArrayList<Point2D> array = new ArrayList<Point2D>(this.vertices.size());
/* 567 */     for (Point2D point : this.vertices)
/* 568 */       array.add(point.clone()); 
/* 569 */     return new SimplePolygon2D(array);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\polygon\SimplePolygon2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package math.geom2d.polygon;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import math.geom2d.AffineTransform2D;
/*     */ import math.geom2d.Box2D;
/*     */ import math.geom2d.GeometricObject2D;
/*     */ import math.geom2d.Point2D;
/*     */ import math.geom2d.Shape2D;
/*     */ import math.geom2d.circulinear.CirculinearBoundary2D;
/*     */ import math.geom2d.circulinear.CirculinearContour2D;
/*     */ import math.geom2d.circulinear.CirculinearContourArray2D;
/*     */ import math.geom2d.circulinear.CirculinearCurve2D;
/*     */ import math.geom2d.circulinear.CirculinearDomain2D;
/*     */ import math.geom2d.circulinear.CirculinearShape2D;
/*     */ import math.geom2d.circulinear.GenericCirculinearDomain2D;
/*     */ import math.geom2d.circulinear.buffer.BufferCalculator;
/*     */ import math.geom2d.domain.Boundary2D;
/*     */ import math.geom2d.domain.Domain2D;
/*     */ import math.geom2d.line.LineSegment2D;
/*     */ import math.geom2d.transform.CircleInversion2D;
/*     */ import math.utils.EqualUtils;
/*     */ 
/*     */ public class Rectangle2D implements Polygon2D {
/*     */   protected double x0;
/*     */   
/*     */   protected double y0;
/*     */   
/*     */   protected double w;
/*     */   
/*     */   protected double h;
/*     */   
/*     */   public Rectangle2D(double x0, double y0, double w, double h) {
/*  71 */     this.x0 = x0;
/*  72 */     this.y0 = y0;
/*  73 */     this.w = w;
/*  74 */     this.h = h;
/*     */   }
/*     */   
/*     */   public Rectangle2D() {
/*  81 */     this(0.0D, 0.0D, 0.0D, 0.0D);
/*     */   }
/*     */   
/*     */   public Rectangle2D(java.awt.geom.Rectangle2D rect) {
/*  88 */     this.x0 = rect.getX();
/*  89 */     this.y0 = rect.getY();
/*  90 */     this.w = rect.getWidth();
/*  91 */     this.h = rect.getHeight();
/*     */   }
/*     */   
/*     */   public Rectangle2D(Point2D p1, Point2D p2) {
/* 100 */     this.x0 = Math.min(p1.x(), p2.x());
/* 101 */     this.y0 = Math.min(p1.y(), p2.y());
/* 102 */     this.w = Math.max(p1.x(), p2.x()) - this.x0;
/* 103 */     this.h = Math.max(p1.y(), p2.y()) - this.y0;
/*     */   }
/*     */   
/*     */   public double getX() {
/* 110 */     return this.x0;
/*     */   }
/*     */   
/*     */   public double getY() {
/* 114 */     return this.y0;
/*     */   }
/*     */   
/*     */   public double getWidth() {
/* 118 */     return this.w;
/*     */   }
/*     */   
/*     */   public double getHeight() {
/* 122 */     return this.h;
/*     */   }
/*     */   
/*     */   public Collection<Point2D> vertices() {
/* 136 */     ArrayList<Point2D> array = new ArrayList<Point2D>(4);
/* 139 */     array.add(new Point2D(this.x0, this.y0));
/* 140 */     array.add(new Point2D(this.x0 + this.w, this.y0));
/* 141 */     array.add(new Point2D(this.x0 + this.w, this.y0 + this.h));
/* 142 */     array.add(new Point2D(this.x0, this.y0 + this.h));
/* 145 */     return array;
/*     */   }
/*     */   
/*     */   public int vertexNumber() {
/* 154 */     return 4;
/*     */   }
/*     */   
/*     */   public Point2D vertex(int i) {
/* 163 */     switch (i) {
/*     */       case 0:
/* 165 */         return new Point2D(this.x0, this.y0);
/*     */       case 1:
/* 167 */         return new Point2D(this.x0 + this.w, this.y0);
/*     */       case 2:
/* 169 */         return new Point2D(this.x0 + this.w, this.y0 + this.h);
/*     */       case 3:
/* 171 */         return new Point2D(this.x0, this.y0 + this.h);
/*     */     } 
/* 173 */     throw new IndexOutOfBoundsException();
/*     */   }
/*     */   
/*     */   public void setVertex(int i, Point2D point) {
/* 178 */     throw new UnsupportedOperationException("Vertices of Rectangle objects can not be modified");
/*     */   }
/*     */   
/*     */   public void addVertex(Point2D point) {
/* 182 */     throw new UnsupportedOperationException("Vertices of Rectangle objects can not be modified");
/*     */   }
/*     */   
/*     */   public void insertVertex(int i, Point2D point) {
/* 186 */     throw new UnsupportedOperationException("Vertices of Rectangle objects can not be modified");
/*     */   }
/*     */   
/*     */   public void removeVertex(int i) {
/* 190 */     throw new UnsupportedOperationException("Vertices of Rectangle objects can not be modified");
/*     */   }
/*     */   
/*     */   public int closestVertexIndex(Point2D point) {
/* 197 */     double minDist = Double.POSITIVE_INFINITY;
/* 198 */     int index = -1;
/* 200 */     int i = 0;
/* 201 */     for (Point2D vertex : vertices()) {
/* 202 */       double dist = vertex.distance(point);
/* 203 */       if (dist < minDist) {
/* 204 */         index = i;
/* 205 */         minDist = dist;
/*     */       } 
/* 207 */       i++;
/*     */     } 
/* 210 */     return index;
/*     */   }
/*     */   
/*     */   public Collection<LineSegment2D> edges() {
/* 217 */     ArrayList<LineSegment2D> edges = new ArrayList<LineSegment2D>(4);
/* 218 */     edges.add(new LineSegment2D(this.x0, this.y0, this.x0 + this.w, this.y0));
/* 219 */     edges.add(new LineSegment2D(this.x0 + this.w, this.y0, this.x0 + this.w, this.y0 + this.h));
/* 220 */     edges.add(new LineSegment2D(this.x0 + this.w, this.y0 + this.h, this.x0, this.y0 + this.h));
/* 221 */     edges.add(new LineSegment2D(this.x0, this.y0 + this.h, this.x0, this.y0));
/* 222 */     return edges;
/*     */   }
/*     */   
/*     */   public int edgeNumber() {
/* 229 */     return 4;
/*     */   }
/*     */   
/*     */   public double area() {
/* 239 */     return this.w * this.h;
/*     */   }
/*     */   
/*     */   public Point2D centroid() {
/* 248 */     double xc = this.x0 + this.w / 2.0D;
/* 249 */     double yc = this.y0 + this.h / 2.0D;
/* 250 */     return new Point2D(xc, yc);
/*     */   }
/*     */   
/*     */   public Polygon2D asPolygon(int n) {
/* 260 */     return this;
/*     */   }
/*     */   
/*     */   public CirculinearDomain2D transform(CircleInversion2D inv) {
/* 270 */     return (CirculinearDomain2D)new GenericCirculinearDomain2D(
/* 271 */         (CirculinearBoundary2D)boundary().transform(inv));
/*     */   }
/*     */   
/*     */   public CirculinearDomain2D buffer(double dist) {
/* 278 */     BufferCalculator bc = BufferCalculator.getDefaultInstance();
/* 279 */     return bc.computeBuffer((CirculinearCurve2D)boundary(), dist);
/*     */   }
/*     */   
/*     */   public CirculinearContourArray2D<LinearRing2D> boundary() {
/* 287 */     return new CirculinearContourArray2D((CirculinearContour2D)asRing());
/*     */   }
/*     */   
/*     */   public Collection<LinearRing2D> contours() {
/* 294 */     ArrayList<LinearRing2D> rings = new ArrayList<LinearRing2D>(1);
/* 295 */     rings.add(asRing());
/* 296 */     return rings;
/*     */   }
/*     */   
/*     */   private LinearRing2D asRing() {
/* 304 */     Point2D[] pts = new Point2D[4];
/* 305 */     pts[0] = new Point2D(this.x0, this.y0);
/* 306 */     pts[1] = new Point2D(this.x0 + this.w, this.y0);
/* 307 */     pts[2] = new Point2D(this.x0 + this.w, this.y0 + this.h);
/* 308 */     pts[3] = new Point2D(this.x0, this.y0 + this.h);
/* 310 */     return new LinearRing2D(pts);
/*     */   }
/*     */   
/*     */   public Polygon2D complement() {
/* 318 */     Point2D[] pts = new Point2D[4];
/* 319 */     pts[0] = new Point2D(this.x0, this.y0);
/* 320 */     pts[1] = new Point2D(this.x0, this.y0 + this.h);
/* 321 */     pts[2] = new Point2D(this.x0 + this.w, this.y0 + this.h);
/* 322 */     pts[3] = new Point2D(this.x0 + this.w, this.y0);
/* 324 */     return new SimplePolygon2D(pts);
/*     */   }
/*     */   
/*     */   public boolean isBounded() {
/* 334 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 338 */     return false;
/*     */   }
/*     */   
/*     */   public double distance(Point2D p) {
/* 347 */     return distance(p.x(), p.y());
/*     */   }
/*     */   
/*     */   public double distance(double x, double y) {
/* 356 */     double dist = boundary().signedDistance(x, y);
/* 357 */     return Math.max(dist, 0.0D);
/*     */   }
/*     */   
/*     */   public Polygon2D clip(Box2D box) {
/* 364 */     return Polygons2D.clipPolygon(this, box);
/*     */   }
/*     */   
/*     */   public Box2D boundingBox() {
/* 371 */     return new Box2D(this.x0, this.x0 + this.w, this.y0, this.y0 + this.h);
/*     */   }
/*     */   
/*     */   public SimplePolygon2D transform(AffineTransform2D trans) {
/* 378 */     int nPoints = 4;
/* 379 */     Point2D[] array = new Point2D[nPoints];
/* 380 */     Point2D[] res = new Point2D[nPoints];
/* 381 */     Iterator<Point2D> iter = vertices().iterator();
/* 382 */     for (int i = 0; i < nPoints; i++) {
/* 383 */       array[i] = iter.next();
/* 384 */       res[i] = new Point2D();
/*     */     } 
/* 387 */     trans.transform(array, res);
/* 388 */     return new SimplePolygon2D(res);
/*     */   }
/*     */   
/*     */   public boolean contains(Point2D point) {
/* 398 */     return contains(point.x(), point.y());
/*     */   }
/*     */   
/*     */   public boolean contains(double x, double y) {
/* 405 */     if (x < this.x0)
/* 406 */       return false; 
/* 407 */     if (x > this.x0 + this.w)
/* 408 */       return false; 
/* 409 */     if (y < this.y0)
/* 410 */       return false; 
/* 411 */     if (y > this.y0 + this.h)
/* 412 */       return false; 
/* 413 */     return true;
/*     */   }
/*     */   
/*     */   public void draw(Graphics2D g2) {
/* 417 */     asRing().draw(g2);
/*     */   }
/*     */   
/*     */   public void fill(Graphics2D g2) {
/* 421 */     asRing().fill(g2);
/*     */   }
/*     */   
/*     */   public boolean almostEquals(GeometricObject2D obj, double eps) {
/* 432 */     if (this == obj)
/* 433 */       return true; 
/* 436 */     if (!(obj instanceof Rectangle2D))
/* 437 */       return false; 
/* 438 */     Rectangle2D rect = (Rectangle2D)obj;
/* 442 */     for (Point2D point : vertices()) {
/* 443 */       boolean ok = false;
/* 446 */       for (Point2D point2 : rect.vertices()) {
/* 447 */         if (point.almostEquals((GeometricObject2D)point2, eps)) {
/* 448 */           ok = true;
/*     */           break;
/*     */         } 
/*     */       } 
/* 454 */       if (!ok)
/* 455 */         return false; 
/*     */     } 
/* 459 */     return true;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 470 */     if (this == obj)
/* 471 */       return true; 
/* 474 */     if (!(obj instanceof Rectangle2D))
/* 475 */       return false; 
/* 476 */     Rectangle2D that = (Rectangle2D)obj;
/* 479 */     if (!EqualUtils.areEqual(this.x0, that.x0))
/* 480 */       return false; 
/* 481 */     if (!EqualUtils.areEqual(this.y0, that.y0))
/* 482 */       return false; 
/* 483 */     if (!EqualUtils.areEqual(this.w, that.w))
/* 484 */       return false; 
/* 485 */     if (!EqualUtils.areEqual(this.h, that.h))
/* 486 */       return false; 
/* 488 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\polygon\Rectangle2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
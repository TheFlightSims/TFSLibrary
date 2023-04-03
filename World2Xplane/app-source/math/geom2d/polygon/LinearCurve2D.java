/*     */ package math.geom2d.polygon;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import math.geom2d.Box2D;
/*     */ import math.geom2d.Point2D;
/*     */ import math.geom2d.Shape2D;
/*     */ import math.geom2d.Vector2D;
/*     */ import math.geom2d.circulinear.CirculinearContinuousCurve2D;
/*     */ import math.geom2d.circulinear.CirculinearCurve2D;
/*     */ import math.geom2d.circulinear.CirculinearDomain2D;
/*     */ import math.geom2d.circulinear.buffer.BufferCalculator;
/*     */ import math.geom2d.curve.AbstractContinuousCurve2D;
/*     */ import math.geom2d.curve.ContinuousCurve2D;
/*     */ import math.geom2d.curve.Curve2D;
/*     */ import math.geom2d.curve.CurveArray2D;
/*     */ import math.geom2d.curve.CurveSet2D;
/*     */ import math.geom2d.curve.Curves2D;
/*     */ import math.geom2d.line.LineSegment2D;
/*     */ import math.geom2d.line.LinearShape2D;
/*     */ import math.geom2d.point.PointSets2D;
/*     */ 
/*     */ public abstract class LinearCurve2D extends AbstractContinuousCurve2D implements CirculinearContinuousCurve2D {
/*  34 */   protected ArrayList<Point2D> vertices = new ArrayList<Point2D>();
/*     */   
/*     */   protected LinearCurve2D(int nVertices) {
/*  49 */     this.vertices = new ArrayList<Point2D>(nVertices);
/*     */   }
/*     */   
/*     */   protected LinearCurve2D(Point2D... vertices) {
/*     */     byte b;
/*     */     int i;
/*     */     Point2D[] arrayOfPoint2D;
/*  53 */     for (i = (arrayOfPoint2D = vertices).length, b = 0; b < i; ) {
/*  53 */       Point2D vertex = arrayOfPoint2D[b];
/*  54 */       this.vertices.add(vertex);
/*     */       b++;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected LinearCurve2D(Collection<? extends Point2D> vertices) {
/*  58 */     this.vertices.addAll(vertices);
/*     */   }
/*     */   
/*     */   protected LinearCurve2D(double[] xcoords, double[] ycoords) {
/*  62 */     int n = xcoords.length;
/*  63 */     this.vertices.ensureCapacity(n);
/*  64 */     for (int i = 0; i < n; i++)
/*  65 */       this.vertices.add(new Point2D(xcoords[i], ycoords[i])); 
/*     */   }
/*     */   
/*     */   public Iterator<Point2D> vertexIterator() {
/*  77 */     return this.vertices.iterator();
/*     */   }
/*     */   
/*     */   public Point2D[] vertexArray() {
/*  86 */     return this.vertices.<Point2D>toArray(new Point2D[0]);
/*     */   }
/*     */   
/*     */   public boolean addVertex(Point2D vertex) {
/*  95 */     return this.vertices.add(vertex);
/*     */   }
/*     */   
/*     */   public void insertVertex(int index, Point2D vertex) {
/* 103 */     this.vertices.add(index, vertex);
/*     */   }
/*     */   
/*     */   public boolean removeVertex(Point2D vertex) {
/* 113 */     return this.vertices.remove(vertex);
/*     */   }
/*     */   
/*     */   public Point2D removeVertex(int index) {
/* 123 */     return this.vertices.remove(index);
/*     */   }
/*     */   
/*     */   public void setVertex(int index, Point2D position) {
/* 131 */     this.vertices.set(index, position);
/*     */   }
/*     */   
/*     */   public void clearVertices() {
/* 135 */     this.vertices.clear();
/*     */   }
/*     */   
/*     */   public Collection<Point2D> vertices() {
/* 142 */     return this.vertices;
/*     */   }
/*     */   
/*     */   public Point2D vertex(int i) {
/* 151 */     return this.vertices.get(i);
/*     */   }
/*     */   
/*     */   public int vertexNumber() {
/* 160 */     return this.vertices.size();
/*     */   }
/*     */   
/*     */   public int closestVertexIndex(Point2D point) {
/* 167 */     double minDist = Double.POSITIVE_INFINITY;
/* 168 */     int index = -1;
/* 170 */     for (int i = 0; i < this.vertices.size(); i++) {
/* 171 */       double dist = ((Point2D)this.vertices.get(i)).distance(point);
/* 172 */       if (dist < minDist) {
/* 173 */         index = i;
/* 174 */         minDist = dist;
/*     */       } 
/*     */     } 
/* 178 */     return index;
/*     */   }
/*     */   
/*     */   public LineSegment2D firstEdge() {
/* 203 */     if (this.vertices.size() < 2)
/* 204 */       return null; 
/* 205 */     return new LineSegment2D(this.vertices.get(0), this.vertices.get(1));
/*     */   }
/*     */   
/*     */   public double length() {
/* 218 */     double sum = 0.0D;
/* 219 */     for (LineSegment2D edge : edges())
/* 220 */       sum += edge.length(); 
/* 221 */     return sum;
/*     */   }
/*     */   
/*     */   public double length(double pos) {
/* 229 */     double length = 0.0D;
/* 232 */     int index = (int)Math.floor(pos);
/* 233 */     for (int i = 0; i < index; i++)
/* 234 */       length += edge(i).length(); 
/* 237 */     if (index < this.vertices.size() - 1) {
/* 238 */       double pos2 = pos - index;
/* 239 */       length += edge(index).length(pos2);
/*     */     } 
/* 243 */     return length;
/*     */   }
/*     */   
/*     */   public double position(double length) {
/* 252 */     double pos = 0.0D;
/* 255 */     int index = 0;
/* 258 */     double cumLength = length(t0());
/* 261 */     for (LineSegment2D edge : edges()) {
/* 263 */       double edgeLength = edge.length();
/* 266 */       if (cumLength + edgeLength < length) {
/* 267 */         cumLength += edgeLength;
/* 268 */         index++;
/*     */         continue;
/*     */       } 
/* 271 */       double pos2 = edge.position(length - cumLength);
/* 272 */       pos = index + pos2;
/*     */       break;
/*     */     } 
/* 278 */     return pos;
/*     */   }
/*     */   
/*     */   public CirculinearDomain2D buffer(double dist) {
/* 285 */     BufferCalculator bc = BufferCalculator.getDefaultInstance();
/* 288 */     if (PointSets2D.hasMultipleVertices(this.vertices)) {
/* 289 */       Polyline2D poly2 = Polyline2D.create(
/* 290 */           PointSets2D.filterMultipleVertices(this.vertices));
/* 291 */       return bc.computeBuffer((CirculinearCurve2D)poly2, dist);
/*     */     } 
/* 294 */     return bc.computeBuffer((CirculinearCurve2D)this, dist);
/*     */   }
/*     */   
/*     */   public CirculinearContinuousCurve2D parallel(double d) {
/* 301 */     BufferCalculator bc = BufferCalculator.getDefaultInstance();
/* 302 */     return bc.createContinuousParallel(this, d);
/*     */   }
/*     */   
/*     */   public double signedDistance(Point2D point) {
/* 315 */     double dist = distance(point.x(), point.y());
/* 316 */     if (isInside(point))
/* 317 */       return -dist; 
/* 319 */     return dist;
/*     */   }
/*     */   
/*     */   public double signedDistance(double x, double y) {
/* 328 */     double dist = distance(x, y);
/* 329 */     if (isInside(new Point2D(x, y)))
/* 330 */       return -dist; 
/* 332 */     return dist;
/*     */   }
/*     */   
/*     */   public Vector2D leftTangent(double t) {
/* 343 */     int index = (int)Math.floor(t);
/* 344 */     if (Math.abs(t - index) < 1.0E-12D)
/* 345 */       index--; 
/* 346 */     return edge(index).tangent(0.0D);
/*     */   }
/*     */   
/*     */   public Vector2D rightTangent(double t) {
/* 353 */     int index = (int)Math.ceil(t);
/* 354 */     return edge(index).tangent(0.0D);
/*     */   }
/*     */   
/*     */   public double curvature(double t) {
/* 361 */     double index = Math.round(t);
/* 362 */     if (Math.abs(index - t) > 1.0E-12D)
/* 363 */       return 0.0D; 
/* 365 */     return Double.POSITIVE_INFINITY;
/*     */   }
/*     */   
/*     */   public Collection<? extends LineSegment2D> smoothPieces() {
/* 374 */     return edges();
/*     */   }
/*     */   
/*     */   public double t0() {
/* 385 */     return 0.0D;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public double getT0() {
/* 393 */     return t0();
/*     */   }
/*     */   
/*     */   public Point2D firstPoint() {
/* 400 */     if (this.vertices.size() == 0)
/* 401 */       return null; 
/* 402 */     return this.vertices.get(0);
/*     */   }
/*     */   
/*     */   public Collection<Point2D> singularPoints() {
/* 406 */     return this.vertices;
/*     */   }
/*     */   
/*     */   public boolean isSingular(double pos) {
/* 410 */     if (Math.abs(pos - Math.round(pos)) < 1.0E-12D)
/* 411 */       return true; 
/* 412 */     return false;
/*     */   }
/*     */   
/*     */   public double position(Point2D point) {
/* 421 */     int ind = 0;
/* 422 */     double minDist = Double.POSITIVE_INFINITY;
/* 423 */     double x = point.x();
/* 424 */     double y = point.y();
/* 426 */     int i = 0;
/* 427 */     LineSegment2D closest = null;
/* 428 */     for (LineSegment2D edge : edges()) {
/* 429 */       double dist = edge.distance(x, y);
/* 430 */       if (dist < minDist) {
/* 431 */         minDist = dist;
/* 432 */         ind = i;
/* 433 */         closest = edge;
/*     */       } 
/* 435 */       i++;
/*     */     } 
/* 438 */     return closest.position(point) + ind;
/*     */   }
/*     */   
/*     */   public Collection<Point2D> intersections(LinearShape2D line) {
/* 447 */     ArrayList<Point2D> list = new ArrayList<Point2D>();
/* 451 */     for (LineSegment2D edge : edges()) {
/* 453 */       if (edge.isParallel(line))
/*     */         continue; 
/* 456 */       Point2D point = edge.intersection(line);
/* 457 */       if (point != null && 
/* 458 */         !list.contains(point))
/* 459 */         list.add(point); 
/*     */     } 
/* 463 */     return list;
/*     */   }
/*     */   
/*     */   public Collection<? extends LinearCurve2D> continuousCurves() {
/* 467 */     return wrapCurve((ContinuousCurve2D)this);
/*     */   }
/*     */   
/*     */   public double project(Point2D point) {
/* 476 */     double minDist = Double.POSITIVE_INFINITY;
/* 477 */     double x = point.x();
/* 478 */     double y = point.y();
/* 479 */     double pos = Double.NaN;
/* 481 */     int i = 0;
/* 482 */     for (LineSegment2D edge : edges()) {
/* 483 */       double dist = edge.distance(x, y);
/* 484 */       if (dist < minDist) {
/* 485 */         minDist = dist;
/* 486 */         pos = edge.project(point) + i;
/*     */       } 
/* 488 */       i++;
/*     */     } 
/* 491 */     return pos;
/*     */   }
/*     */   
/*     */   public double distance(double x, double y) {
/* 504 */     double dist = Double.MAX_VALUE;
/* 505 */     for (LineSegment2D edge : edges()) {
/* 506 */       if (edge.length() == 0.0D)
/*     */         continue; 
/* 508 */       dist = Math.min(dist, edge.distance(x, y));
/*     */     } 
/* 510 */     return dist;
/*     */   }
/*     */   
/*     */   public double distance(Point2D point) {
/* 519 */     return distance(point.x(), point.y());
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 526 */     return (this.vertices.size() == 0);
/*     */   }
/*     */   
/*     */   public boolean isBounded() {
/* 531 */     return true;
/*     */   }
/*     */   
/*     */   public Box2D boundingBox() {
/* 538 */     double xmin = Double.MAX_VALUE;
/* 539 */     double ymin = Double.MAX_VALUE;
/* 540 */     double xmax = Double.MIN_VALUE;
/* 541 */     double ymax = Double.MIN_VALUE;
/* 543 */     Iterator<Point2D> iter = this.vertices.iterator();
/* 546 */     while (iter.hasNext()) {
/* 547 */       Point2D point = iter.next();
/* 548 */       double x = point.x();
/* 549 */       double y = point.y();
/* 550 */       xmin = Math.min(xmin, x);
/* 551 */       xmax = Math.max(xmax, x);
/* 552 */       ymin = Math.min(ymin, y);
/* 553 */       ymax = Math.max(ymax, y);
/*     */     } 
/* 556 */     return new Box2D(xmin, xmax, ymin, ymax);
/*     */   }
/*     */   
/*     */   public boolean contains(double x, double y) {
/* 565 */     for (LineSegment2D edge : edges()) {
/* 566 */       if (edge.length() == 0.0D)
/*     */         continue; 
/* 568 */       if (edge.contains(x, y))
/* 569 */         return true; 
/*     */     } 
/* 571 */     return false;
/*     */   }
/*     */   
/*     */   public boolean contains(Point2D point) {
/* 580 */     return contains(point.x(), point.y());
/*     */   }
/*     */   
/*     */   public CurveSet2D<? extends LinearCurve2D> clip(Box2D box) {
/* 591 */     CurveSet2D<? extends Curve2D> set = Curves2D.clipCurve((Curve2D)this, box);
/* 594 */     CurveArray2D<LinearCurve2D> result = 
/* 595 */       new CurveArray2D(set.size());
/* 598 */     for (Curve2D curve : set.curves()) {
/* 599 */       if (curve instanceof LinearCurve2D)
/* 600 */         result.add(curve); 
/*     */     } 
/* 602 */     return (CurveSet2D<? extends LinearCurve2D>)result;
/*     */   }
/*     */   
/*     */   public GeneralPath asGeneralPath() {
/* 609 */     GeneralPath path = new GeneralPath();
/* 610 */     if (this.vertices.size() < 2)
/* 611 */       return path; 
/* 612 */     return appendPath(path);
/*     */   }
/*     */   
/*     */   public void draw(Graphics2D g2) {
/* 616 */     g2.draw(asGeneralPath());
/*     */   }
/*     */   
/*     */   protected LinearCurve2D() {}
/*     */   
/*     */   public abstract LineSegment2D edge(int paramInt);
/*     */   
/*     */   public abstract int edgeNumber();
/*     */   
/*     */   public abstract Collection<LineSegment2D> edges();
/*     */   
/*     */   public abstract LineSegment2D lastEdge();
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\polygon\LinearCurve2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
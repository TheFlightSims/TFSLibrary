/*     */ package math.geom2d.point;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.Ellipse2D;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import math.geom2d.AffineTransform2D;
/*     */ import math.geom2d.Box2D;
/*     */ import math.geom2d.GeometricObject2D;
/*     */ import math.geom2d.Point2D;
/*     */ import math.geom2d.Shape2D;
/*     */ import math.geom2d.circulinear.CirculinearDomain2D;
/*     */ import math.geom2d.circulinear.CirculinearShape2D;
/*     */ import math.geom2d.circulinear.buffer.BufferCalculator;
/*     */ import math.geom2d.transform.CircleInversion2D;
/*     */ 
/*     */ public class PointArray2D implements PointSet2D, CirculinearShape2D, Cloneable {
/*     */   public static <T extends Point2D> PointArray2D create(Collection<T> points) {
/*  57 */     return new PointArray2D(points);
/*     */   }
/*     */   
/*     */   public static <T extends Point2D> PointArray2D create(Point2D... points) {
/*  61 */     return new PointArray2D(points);
/*     */   }
/*     */   
/*     */   public static PointArray2D create(int size) {
/*  68 */     return new PointArray2D(size);
/*     */   }
/*     */   
/*  77 */   protected ArrayList<Point2D> points = null;
/*     */   
/*     */   public PointArray2D() {
/*  86 */     this(0);
/*     */   }
/*     */   
/*     */   public PointArray2D(int n) {
/*  96 */     this.points = new ArrayList<Point2D>();
/*     */   }
/*     */   
/*     */   public PointArray2D(Point2D... points) {
/* 104 */     this(points.length);
/*     */     byte b;
/*     */     int i;
/*     */     Point2D[] arrayOfPoint2D;
/* 105 */     for (i = (arrayOfPoint2D = points).length, b = 0; b < i; ) {
/* 105 */       Point2D element = arrayOfPoint2D[b];
/* 106 */       this.points.add(element);
/*     */       b++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public PointArray2D(Collection<? extends Point2D> points) {
/* 117 */     this(points.size());
/* 119 */     for (Point2D point : points)
/* 120 */       this.points.add(point); 
/*     */   }
/*     */   
/*     */   public boolean add(Point2D point) {
/* 134 */     return this.points.add(point);
/*     */   }
/*     */   
/*     */   public void add(int index, Point2D point) {
/* 138 */     this.points.add(index, point);
/*     */   }
/*     */   
/*     */   public void addAll(Point2D[] points) {
/*     */     byte b;
/*     */     int i;
/*     */     Point2D[] arrayOfPoint2D;
/* 147 */     for (i = (arrayOfPoint2D = points).length, b = 0; b < i; ) {
/* 147 */       Point2D element = arrayOfPoint2D[b];
/* 148 */       add(element);
/*     */       b++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addAll(Collection<? extends Point2D> points) {
/* 152 */     this.points.addAll(points);
/*     */   }
/*     */   
/*     */   public Point2D get(int index) {
/* 156 */     return this.points.get(index);
/*     */   }
/*     */   
/*     */   public boolean remove(Point2D point) {
/* 160 */     return this.points.remove(point);
/*     */   }
/*     */   
/*     */   public Point2D remove(int index) {
/* 164 */     return this.points.remove(index);
/*     */   }
/*     */   
/*     */   public int indexOf(Point2D point) {
/* 168 */     return this.points.indexOf(point);
/*     */   }
/*     */   
/*     */   public Collection<Point2D> points() {
/* 177 */     return Collections.unmodifiableList(this.points);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 184 */     this.points.clear();
/*     */   }
/*     */   
/*     */   public int size() {
/* 193 */     return this.points.size();
/*     */   }
/*     */   
/*     */   public CirculinearDomain2D buffer(double dist) {
/* 204 */     BufferCalculator bc = BufferCalculator.getDefaultInstance();
/* 205 */     return bc.computeBuffer(this, dist);
/*     */   }
/*     */   
/*     */   public PointArray2D transform(CircleInversion2D inv) {
/* 210 */     PointArray2D array = new PointArray2D(this.points.size());
/* 212 */     for (Point2D point : this.points)
/* 213 */       array.add(point.transform(inv)); 
/* 215 */     return array;
/*     */   }
/*     */   
/*     */   public double distance(Point2D p) {
/* 222 */     return distance(p.x(), p.y());
/*     */   }
/*     */   
/*     */   public double distance(double x, double y) {
/* 232 */     if (this.points.isEmpty())
/* 233 */       return Double.NaN; 
/* 236 */     double dist = Double.MAX_VALUE;
/* 237 */     for (Point2D point : this.points)
/* 238 */       dist = Math.min(dist, point.distance(x, y)); 
/* 241 */     return dist;
/*     */   }
/*     */   
/*     */   public boolean isBounded() {
/* 248 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 255 */     return (this.points.size() == 0);
/*     */   }
/*     */   
/*     */   public PointArray2D clip(Box2D box) {
/* 265 */     PointArray2D res = new PointArray2D(this.points.size());
/* 268 */     for (Point2D point : this.points) {
/* 269 */       if (box.contains(point))
/* 270 */         res.add(point); 
/*     */     } 
/* 275 */     res.points.trimToSize();
/* 278 */     return res;
/*     */   }
/*     */   
/*     */   public Box2D boundingBox() {
/* 283 */     double xmin = Double.MAX_VALUE;
/* 284 */     double ymin = Double.MAX_VALUE;
/* 285 */     double xmax = Double.MIN_VALUE;
/* 286 */     double ymax = Double.MIN_VALUE;
/* 289 */     for (Point2D point : this.points) {
/* 290 */       xmin = Math.min(xmin, point.x());
/* 291 */       ymin = Math.min(ymin, point.y());
/* 292 */       xmax = Math.max(xmax, point.x());
/* 293 */       ymax = Math.max(ymax, point.y());
/*     */     } 
/* 297 */     return new Box2D(xmin, xmax, ymin, ymax);
/*     */   }
/*     */   
/*     */   public PointArray2D transform(AffineTransform2D trans) {
/* 306 */     PointArray2D res = new PointArray2D(this.points.size());
/* 308 */     for (Point2D point : this.points)
/* 309 */       res.add(point.transform(trans)); 
/* 311 */     return res;
/*     */   }
/*     */   
/*     */   public boolean contains(double x, double y) {
/* 320 */     for (Point2D point : this.points) {
/* 321 */       if (point.distance(x, y) < 1.0E-12D)
/* 322 */         return true; 
/*     */     } 
/* 323 */     return false;
/*     */   }
/*     */   
/*     */   public boolean contains(Point2D point) {
/* 332 */     return contains(point.x(), point.y());
/*     */   }
/*     */   
/*     */   public void draw(Graphics2D g2) {
/* 342 */     draw(g2, 1.0D);
/*     */   }
/*     */   
/*     */   public void draw(Graphics2D g2, double r) {
/* 353 */     double w = 2.0D * r;
/* 354 */     for (Point2D point : this.points) {
/* 355 */       double x = point.x();
/* 356 */       double y = point.y();
/* 357 */       g2.fill(new Ellipse2D.Double(x - r, y - r, w, w));
/*     */     } 
/*     */   }
/*     */   
/*     */   public Iterator<Point2D> iterator() {
/* 367 */     return this.points.iterator();
/*     */   }
/*     */   
/*     */   public boolean almostEquals(GeometricObject2D obj, double eps) {
/* 377 */     if (this == obj)
/* 378 */       return true; 
/* 380 */     if (!(obj instanceof PointSet2D))
/* 381 */       return false; 
/* 383 */     PointSet2D set = (PointSet2D)obj;
/* 384 */     if (this.points.size() != set.size())
/* 385 */       return false; 
/* 387 */     Iterator<Point2D> iter = set.iterator();
/* 388 */     for (Point2D point : this.points) {
/* 389 */       if (!point.almostEquals((GeometricObject2D)iter.next(), eps))
/* 390 */         return false; 
/*     */     } 
/* 393 */     return true;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 407 */     if (this == obj)
/* 408 */       return true; 
/* 410 */     if (!(obj instanceof PointSet2D))
/* 411 */       return false; 
/* 413 */     PointSet2D set = (PointSet2D)obj;
/* 414 */     if (this.points.size() != set.size())
/* 415 */       return false; 
/* 417 */     Iterator<Point2D> iter = set.iterator();
/* 418 */     for (Point2D point : this.points) {
/* 419 */       if (!point.equals(iter.next()))
/* 420 */         return false; 
/*     */     } 
/* 423 */     return true;
/*     */   }
/*     */   
/*     */   public PointArray2D clone() {
/* 428 */     PointArray2D set = new PointArray2D(size());
/* 429 */     for (Point2D point : this)
/* 430 */       set.add(point.clone()); 
/* 431 */     return set;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\point\PointArray2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package math.geom2d;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Point;
/*     */ import java.awt.geom.Ellipse2D;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import math.geom2d.circulinear.CirculinearBoundary2D;
/*     */ import math.geom2d.circulinear.CirculinearDomain2D;
/*     */ import math.geom2d.circulinear.CirculinearShape2D;
/*     */ import math.geom2d.circulinear.GenericCirculinearDomain2D;
/*     */ import math.geom2d.conic.Circle2D;
/*     */ import math.geom2d.point.PointArray2D;
/*     */ import math.geom2d.point.PointSet2D;
/*     */ import math.geom2d.point.PointShape2D;
/*     */ import math.geom2d.transform.CircleInversion2D;
/*     */ import math.utils.EqualUtils;
/*     */ 
/*     */ public class Point2D implements GeometricObject2D, PointShape2D, Cloneable, CirculinearShape2D {
/*     */   protected double x;
/*     */   
/*     */   protected double y;
/*     */   
/*     */   @Deprecated
/*     */   public static Point2D create(double x, double y) {
/*  75 */     return new Point2D(x, y);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static Point2D create(java.awt.geom.Point2D point) {
/*  86 */     return new Point2D(point.getX(), point.getY());
/*     */   }
/*     */   
/*     */   public static Point2D create(Point2D point) {
/*  96 */     return new Point2D(point.x, point.y);
/*     */   }
/*     */   
/*     */   public static Point2D createPolar(double rho, double theta) {
/* 104 */     return new Point2D(rho * Math.cos(theta), rho * Math.sin(theta));
/*     */   }
/*     */   
/*     */   public static Point2D createPolar(Point2D point, double rho, double theta) {
/* 112 */     return new Point2D(point.x + rho * Math.cos(theta), point.y + rho * Math.sin(theta));
/*     */   }
/*     */   
/*     */   public static Point2D createPolar(double x0, double y0, double rho, double theta) {
/* 121 */     return new Point2D(x0 + rho * Math.cos(theta), y0 + rho * Math.sin(theta));
/*     */   }
/*     */   
/*     */   public static double distance(double x1, double y1, double x2, double y2) {
/* 131 */     return Math.hypot(x2 - x1, y2 - y1);
/*     */   }
/*     */   
/*     */   public static double distance(Point2D p1, Point2D p2) {
/* 143 */     return Math.hypot(p1.x - p2.x, p1.y - p2.y);
/*     */   }
/*     */   
/*     */   public static boolean isColinear(Point2D p1, Point2D p2, Point2D p3) {
/* 153 */     double dx1 = p2.x - p1.x;
/* 154 */     double dy1 = p2.y - p1.y;
/* 155 */     double dx2 = p3.x - p1.x;
/* 156 */     double dy2 = p3.y - p1.y;
/* 159 */     return (Math.abs(dx1 * dy2 - dy1 * dx2) < 1.0E-12D);
/*     */   }
/*     */   
/*     */   public static int ccw(Point2D p0, Point2D p1, Point2D p2) {
/* 174 */     double x0 = p0.x;
/* 175 */     double y0 = p0.y;
/* 176 */     double dx1 = p1.x - x0;
/* 177 */     double dy1 = p1.y - y0;
/* 178 */     double dx2 = p2.x - x0;
/* 179 */     double dy2 = p2.y - y0;
/* 181 */     if (dx1 * dy2 > dy1 * dx2)
/* 182 */       return 1; 
/* 183 */     if (dx1 * dy2 < dy1 * dx2)
/* 184 */       return -1; 
/* 185 */     if (dx1 * dx2 < 0.0D || dy1 * dy2 < 0.0D)
/* 186 */       return -1; 
/* 187 */     if (Math.hypot(dx1, dy1) < Math.hypot(dx2, dy2))
/* 188 */       return 1; 
/* 189 */     return 0;
/*     */   }
/*     */   
/*     */   public static Point2D midPoint(Point2D p1, Point2D p2) {
/* 193 */     return new Point2D((p1.x + p2.x) / 2.0D, (p1.y + p2.y) / 2.0D);
/*     */   }
/*     */   
/*     */   public static Point2D centroid(Point2D[] points) {
/* 203 */     int n = points.length;
/* 204 */     double sx = 0.0D, sy = 0.0D;
/* 205 */     for (int i = 0; i < n; i++) {
/* 206 */       sx += (points[i]).x;
/* 207 */       sy += (points[i]).y;
/*     */     } 
/* 209 */     return new Point2D(sx / n, sy / n);
/*     */   }
/*     */   
/*     */   public static Point2D centroid(Point2D[] points, double[] weights) {
/* 222 */     int n = points.length;
/* 225 */     if (n != weights.length)
/* 226 */       throw new RuntimeException("Arrays must have the same size"); 
/* 230 */     double sx = 0.0D, sy = 0.0D, sw = 0.0D;
/* 232 */     for (int i = 0; i < n; i++) {
/* 233 */       double w = weights[i];
/* 234 */       sx += (points[i]).x * w;
/* 235 */       sy += (points[i]).y * w;
/* 236 */       sw += w;
/*     */     } 
/* 240 */     return new Point2D(sx / sw, sy / sw);
/*     */   }
/*     */   
/*     */   public static Point2D centroid(Collection<? extends Point2D> points) {
/* 250 */     int n = points.size();
/* 251 */     double sx = 0.0D, sy = 0.0D;
/* 252 */     for (Point2D point : points) {
/* 253 */       sx += point.x;
/* 254 */       sy += point.y;
/*     */     } 
/* 256 */     return new Point2D(sx / n, sy / n);
/*     */   }
/*     */   
/*     */   public static Point2D centroid(Point2D pt1, Point2D pt2, Point2D pt3) {
/* 268 */     return new Point2D((
/* 269 */         pt1.x + pt2.x + pt3.x) / 3.0D, (
/* 270 */         pt1.y + pt2.y + pt3.y) / 3.0D);
/*     */   }
/*     */   
/*     */   public Point2D() {}
/*     */   
/*     */   public Point2D(double x, double y) {
/* 290 */     this.x = x;
/* 291 */     this.y = y;
/*     */   }
/*     */   
/*     */   public Point2D(java.awt.geom.Point2D point) {
/* 299 */     this(point.getX(), point.getY());
/*     */   }
/*     */   
/*     */   public Point2D(Point2D point) {
/* 306 */     this(point.x, point.y);
/*     */   }
/*     */   
/*     */   public Point2D plus(Point2D p) {
/* 317 */     return new Point2D(this.x + p.x, this.y + p.y);
/*     */   }
/*     */   
/*     */   public Point2D plus(Vector2D v) {
/* 325 */     return new Point2D(this.x + v.x, this.y + v.y);
/*     */   }
/*     */   
/*     */   public Point2D minus(Point2D p) {
/* 333 */     return new Point2D(this.x - p.x, this.y - p.y);
/*     */   }
/*     */   
/*     */   public Point2D minus(Vector2D v) {
/* 341 */     return new Point2D(this.x - v.x, this.y - v.y);
/*     */   }
/*     */   
/*     */   public Point2D translate(double tx, double ty) {
/* 352 */     return new Point2D(this.x + tx, this.y + ty);
/*     */   }
/*     */   
/*     */   public Point2D scale(double kx, double ky) {
/* 363 */     return new Point2D(this.x * kx, this.y * ky);
/*     */   }
/*     */   
/*     */   public Point2D scale(double k) {
/* 373 */     return new Point2D(this.x * k, this.y * k);
/*     */   }
/*     */   
/*     */   public Point2D rotate(double theta) {
/* 383 */     double cot = Math.cos(theta);
/* 384 */     double sit = Math.sin(theta);
/* 385 */     return new Point2D(this.x * cot - this.y * sit, this.x * sit + this.y * cot);
/*     */   }
/*     */   
/*     */   public Point2D rotate(Point2D center, double theta) {
/* 396 */     double cx = center.x;
/* 397 */     double cy = center.y;
/* 398 */     double cot = Math.cos(theta);
/* 399 */     double sit = Math.sin(theta);
/* 400 */     return new Point2D(
/* 401 */         this.x * cot - this.y * sit + (1.0D - cot) * cx + sit * cy, 
/* 402 */         this.x * sit + this.y * cot + (1.0D - cot) * cy - sit * cx);
/*     */   }
/*     */   
/*     */   public Point getAsInt() {
/* 415 */     return new Point((int)this.x, (int)this.y);
/*     */   }
/*     */   
/*     */   public java.awt.geom.Point2D.Double getAsDouble() {
/* 422 */     return new java.awt.geom.Point2D.Double(this.x, this.y);
/*     */   }
/*     */   
/*     */   public java.awt.geom.Point2D.Float getAsFloat() {
/* 430 */     return new java.awt.geom.Point2D.Float((float)this.x, (float)this.y);
/*     */   }
/*     */   
/*     */   public double x() {
/* 440 */     return this.x;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public double getX() {
/* 448 */     return this.x;
/*     */   }
/*     */   
/*     */   public double y() {
/* 455 */     return this.y;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public double getY() {
/* 463 */     return this.y;
/*     */   }
/*     */   
/*     */   public CirculinearDomain2D buffer(double dist) {
/* 474 */     return (CirculinearDomain2D)new GenericCirculinearDomain2D(
/* 475 */         (CirculinearBoundary2D)new Circle2D(this, Math.abs(dist), (dist > 0.0D)));
/*     */   }
/*     */   
/*     */   public Point2D transform(CircleInversion2D inv) {
/* 485 */     Point2D center = inv.center();
/* 486 */     double r = inv.radius();
/* 489 */     double d = r * r / distance(this, center);
/* 490 */     double theta = Angle2D.horizontalAngle(center, this);
/* 493 */     return createPolar(center, d, theta);
/*     */   }
/*     */   
/*     */   public int size() {
/* 505 */     return 1;
/*     */   }
/*     */   
/*     */   public Collection<Point2D> points() {
/* 513 */     ArrayList<Point2D> array = new ArrayList<Point2D>(1);
/* 514 */     array.add(this);
/* 515 */     return array;
/*     */   }
/*     */   
/*     */   public double distance(Point2D point) {
/* 526 */     return distance(point.x, point.y);
/*     */   }
/*     */   
/*     */   public double distance(double x, double y) {
/* 535 */     return Math.hypot(this.x - x, this.y - y);
/*     */   }
/*     */   
/*     */   public boolean isBounded() {
/* 545 */     if (Double.isInfinite(this.x))
/* 546 */       return false; 
/* 547 */     if (Double.isInfinite(this.y))
/* 548 */       return false; 
/* 549 */     if (Double.isNaN(this.x))
/* 550 */       return false; 
/* 551 */     if (Double.isNaN(this.y))
/* 552 */       return false; 
/* 553 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 560 */     return false;
/*     */   }
/*     */   
/*     */   public boolean contains(double x, double y) {
/* 567 */     return equals(new Point2D(x, y));
/*     */   }
/*     */   
/*     */   public boolean contains(Point2D p) {
/* 574 */     return equals(p);
/*     */   }
/*     */   
/*     */   public PointSet2D clip(Box2D box) {
/* 583 */     PointArray2D pointArray2D = new PointArray2D(0);
/* 586 */     if (this.x < box.getMinX())
/* 587 */       return (PointSet2D)pointArray2D; 
/* 588 */     if (this.y < box.getMinY())
/* 589 */       return (PointSet2D)pointArray2D; 
/* 590 */     if (this.x > box.getMaxX())
/* 591 */       return (PointSet2D)pointArray2D; 
/* 592 */     if (this.y > box.getMaxY())
/* 593 */       return (PointSet2D)pointArray2D; 
/* 596 */     pointArray2D.add(this);
/* 597 */     return (PointSet2D)pointArray2D;
/*     */   }
/*     */   
/*     */   public Box2D boundingBox() {
/* 605 */     return new Box2D(this.x, this.x, this.y, this.y);
/*     */   }
/*     */   
/*     */   public Point2D transform(AffineTransform2D trans) {
/* 612 */     double[] tab = trans.coefficients();
/* 613 */     return new Point2D(
/* 614 */         this.x * tab[0] + this.y * tab[1] + tab[2], 
/* 615 */         this.x * tab[3] + this.y * tab[4] + tab[5]);
/*     */   }
/*     */   
/*     */   public void draw(Graphics2D g2) {
/* 628 */     draw(g2, 1.0D);
/*     */   }
/*     */   
/*     */   public void draw(Graphics2D g2, double r) {
/* 638 */     g2.fill(new Ellipse2D.Double(this.x - r, this.y - r, 2.0D * r, 2.0D * r));
/*     */   }
/*     */   
/*     */   public boolean almostEquals(GeometricObject2D obj, double eps) {
/* 649 */     if (this == obj)
/* 650 */       return true; 
/* 652 */     if (!(obj instanceof Point2D))
/* 653 */       return false; 
/* 654 */     Point2D p = (Point2D)obj;
/* 656 */     if (Math.abs(this.x - p.x) > eps)
/* 657 */       return false; 
/* 658 */     if (Math.abs(this.y - p.y) > eps)
/* 659 */       return false; 
/* 661 */     return true;
/*     */   }
/*     */   
/*     */   public Iterator<Point2D> iterator() {
/* 672 */     return points().iterator();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 680 */     return new String("Point2D(" + this.x + ", " + this.y + ")");
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 689 */     if (this == obj)
/* 690 */       return true; 
/* 692 */     if (!(obj instanceof Point2D))
/* 693 */       return false; 
/* 694 */     Point2D that = (Point2D)obj;
/* 697 */     if (!EqualUtils.areEqual(this.x, that.x))
/* 698 */       return false; 
/* 699 */     if (!EqualUtils.areEqual(this.y, that.y))
/* 700 */       return false; 
/* 702 */     return true;
/*     */   }
/*     */   
/*     */   public Point2D clone() {
/* 710 */     return new Point2D(this.x, this.y);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\Point2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
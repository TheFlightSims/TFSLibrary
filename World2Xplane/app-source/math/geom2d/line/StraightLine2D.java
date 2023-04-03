/*     */ package math.geom2d.line;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import math.geom2d.AffineTransform2D;
/*     */ import math.geom2d.Angle2D;
/*     */ import math.geom2d.Box2D;
/*     */ import math.geom2d.GeometricObject2D;
/*     */ import math.geom2d.Point2D;
/*     */ import math.geom2d.Shape2D;
/*     */ import math.geom2d.UnboundedShape2DException;
/*     */ import math.geom2d.Vector2D;
/*     */ import math.geom2d.circulinear.CircleLine2D;
/*     */ import math.geom2d.circulinear.CirculinearBoundary2D;
/*     */ import math.geom2d.circulinear.CirculinearContinuousCurve2D;
/*     */ import math.geom2d.circulinear.CirculinearContour2D;
/*     */ import math.geom2d.circulinear.CirculinearCurve2D;
/*     */ import math.geom2d.circulinear.CirculinearDomain2D;
/*     */ import math.geom2d.circulinear.CirculinearElement2D;
/*     */ import math.geom2d.circulinear.CirculinearShape2D;
/*     */ import math.geom2d.circulinear.GenericCirculinearDomain2D;
/*     */ import math.geom2d.conic.Circle2D;
/*     */ import math.geom2d.curve.ContinuousCurve2D;
/*     */ import math.geom2d.curve.Curve2D;
/*     */ import math.geom2d.curve.SmoothCurve2D;
/*     */ import math.geom2d.domain.Boundary2D;
/*     */ import math.geom2d.domain.ContinuousOrientedCurve2D;
/*     */ import math.geom2d.domain.Contour2D;
/*     */ import math.geom2d.domain.Domain2D;
/*     */ import math.geom2d.domain.OrientedCurve2D;
/*     */ import math.geom2d.domain.SmoothContour2D;
/*     */ import math.geom2d.domain.SmoothOrientedCurve2D;
/*     */ import math.geom2d.polygon.LinearCurve2D;
/*     */ import math.geom2d.polygon.Polyline2D;
/*     */ import math.geom2d.transform.CircleInversion2D;
/*     */ import math.utils.EqualUtils;
/*     */ 
/*     */ public class StraightLine2D extends AbstractLine2D implements SmoothContour2D, Cloneable, CircleLine2D {
/*     */   @Deprecated
/*     */   public static StraightLine2D create(Point2D point, double angle) {
/*  68 */     return new StraightLine2D(point.x(), point.y(), Math.cos(angle), 
/*  69 */         Math.sin(angle));
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static StraightLine2D create(Point2D p1, Point2D p2) {
/*  78 */     return new StraightLine2D(p1, p2);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static StraightLine2D create(Point2D origin, Vector2D direction) {
/*  88 */     return new StraightLine2D(origin, direction);
/*     */   }
/*     */   
/*     */   public static StraightLine2D createHorizontal(Point2D origin) {
/*  96 */     return new StraightLine2D(origin, new Vector2D(1.0D, 0.0D));
/*     */   }
/*     */   
/*     */   public static StraightLine2D createVertical(Point2D origin) {
/* 104 */     return new StraightLine2D(origin, new Vector2D(0.0D, 1.0D));
/*     */   }
/*     */   
/*     */   public static StraightLine2D createMedian(Point2D p1, Point2D p2) {
/* 116 */     Point2D mid = Point2D.midPoint(p1, p2);
/* 117 */     StraightLine2D line = create(p1, p2);
/* 118 */     return createPerpendicular(line, mid);
/*     */   }
/*     */   
/*     */   public static StraightLine2D createParallel(LinearShape2D line, Point2D point) {
/* 130 */     return new StraightLine2D(line, point);
/*     */   }
/*     */   
/*     */   public static StraightLine2D createParallel(LinearShape2D linear, double d) {
/* 142 */     StraightLine2D line = linear.supportingLine();
/* 143 */     double d2 = d / Math.hypot(line.dx, line.dy);
/* 144 */     return new StraightLine2D(
/* 145 */         line.x0 + line.dy * d2, line.y0 - line.dx * d2, 
/* 146 */         line.dx, line.dy);
/*     */   }
/*     */   
/*     */   public static StraightLine2D createPerpendicular(LinearShape2D linear, Point2D point) {
/* 157 */     StraightLine2D line = linear.supportingLine();
/* 158 */     return new StraightLine2D(point, -line.dy, line.dx);
/*     */   }
/*     */   
/*     */   public static StraightLine2D createCartesian(double a, double b, double c) {
/* 168 */     return new StraightLine2D(a, b, c);
/*     */   }
/*     */   
/*     */   public static Point2D getIntersection(Point2D p1, Point2D p2, Point2D p3, Point2D p4) {
/* 178 */     StraightLine2D line1 = new StraightLine2D(p1, p2);
/* 179 */     StraightLine2D line2 = new StraightLine2D(p3, p4);
/* 180 */     return line1.intersection(line2);
/*     */   }
/*     */   
/*     */   public StraightLine2D() {
/* 188 */     this(0.0D, 0.0D, 1.0D, 0.0D);
/*     */   }
/*     */   
/*     */   public StraightLine2D(Point2D point1, Point2D point2) {
/* 193 */     this(point1, new Vector2D(point1, point2));
/*     */   }
/*     */   
/*     */   public StraightLine2D(Point2D point, Vector2D direction) {
/* 201 */     this(point.x(), point.y(), direction.x(), direction.y());
/*     */   }
/*     */   
/*     */   public StraightLine2D(Point2D point, double dx, double dy) {
/* 209 */     this(point.x(), point.y(), dx, dy);
/*     */   }
/*     */   
/*     */   public StraightLine2D(Point2D point, double angle) {
/* 217 */     this(point.x(), point.y(), Math.cos(angle), Math.sin(angle));
/*     */   }
/*     */   
/*     */   public StraightLine2D(LinearShape2D line) {
/* 225 */     super(line);
/*     */   }
/*     */   
/*     */   public StraightLine2D(double xp, double yp, double dx, double dy) {
/* 233 */     super(xp, yp, dx, dy);
/*     */   }
/*     */   
/*     */   public StraightLine2D(LinearShape2D line, Point2D point) {
/* 241 */     this(point, line.direction());
/*     */   }
/*     */   
/*     */   public StraightLine2D(double a, double b, double c) {
/* 250 */     this(0.0D, 0.0D, 1.0D, 0.0D);
/* 251 */     double d = a * a + b * b;
/* 252 */     this.x0 = -a * c / d;
/* 253 */     this.y0 = -b * c / d;
/* 254 */     double theta = Math.atan2(-a, b);
/* 255 */     this.dx = Math.cos(theta);
/* 256 */     this.dy = Math.sin(theta);
/*     */   }
/*     */   
/*     */   public StraightLine2D parallel(Point2D point) {
/* 267 */     return new StraightLine2D(point, this.dx, this.dy);
/*     */   }
/*     */   
/*     */   public StraightLine2D parallel(double d) {
/* 282 */     double d2 = Math.hypot(this.dx, this.dy);
/* 283 */     if (Math.abs(d2) < 1.0E-12D)
/* 284 */       throw new DegeneratedLine2DException(
/* 285 */           "Can not compute parallel of degenerated line", this); 
/* 286 */     d2 = d / d2;
/* 287 */     return new StraightLine2D(this.x0 + this.dy * d2, this.y0 - this.dx * d2, this.dx, this.dy);
/*     */   }
/*     */   
/*     */   public StraightLine2D perpendicular(Point2D point) {
/* 296 */     return new StraightLine2D(point, -this.dy, this.dx);
/*     */   }
/*     */   
/*     */   public CircleLine2D transform(CircleInversion2D inv) {
/* 305 */     Point2D center = inv.center();
/* 306 */     double r = inv.radius();
/* 309 */     Point2D po = projectedPoint(center);
/* 310 */     double d = distance(center);
/* 314 */     if (Math.abs(d) < 1.0E-12D)
/* 315 */       return new StraightLine2D(this); 
/* 319 */     double angle = Angle2D.horizontalAngle(center, po);
/* 322 */     double r2 = r * r / d / 2.0D;
/* 323 */     Point2D c2 = Point2D.createPolar(center, r2, angle);
/* 326 */     boolean direct = isInside(center);
/* 329 */     return (CircleLine2D)new Circle2D(c2, r2, direct);
/*     */   }
/*     */   
/*     */   public CirculinearDomain2D domain() {
/* 340 */     return (CirculinearDomain2D)new GenericCirculinearDomain2D((CirculinearBoundary2D)this);
/*     */   }
/*     */   
/*     */   public void fill(Graphics2D g2) {
/* 347 */     g2.fill(getGeneralPath());
/*     */   }
/*     */   
/*     */   public double windingAngle(Point2D point) {
/* 360 */     double angle1 = Angle2D.horizontalAngle(-this.dx, -this.dy);
/* 361 */     double angle2 = Angle2D.horizontalAngle(this.dx, this.dy);
/* 363 */     if (isInside(point)) {
/* 364 */       if (angle2 > angle1)
/* 365 */         return angle2 - angle1; 
/* 367 */       return 6.283185307179586D - angle1 + angle2;
/*     */     } 
/* 369 */     if (angle2 > angle1)
/* 370 */       return angle2 - angle1 - 6.283185307179586D; 
/* 372 */     return angle2 - angle1;
/*     */   }
/*     */   
/*     */   public Polyline2D asPolyline(int n) {
/* 385 */     throw new UnboundedShape2DException(this);
/*     */   }
/*     */   
/*     */   public Point2D firstPoint() {
/* 395 */     throw new UnboundedShape2DException(this);
/*     */   }
/*     */   
/*     */   public Point2D lastPoint() {
/* 401 */     throw new UnboundedShape2DException(this);
/*     */   }
/*     */   
/*     */   public Collection<Point2D> singularPoints() {
/* 407 */     return new ArrayList<Point2D>(0);
/*     */   }
/*     */   
/*     */   public boolean isSingular(double pos) {
/* 413 */     return false;
/*     */   }
/*     */   
/*     */   public double t0() {
/* 421 */     return Double.NEGATIVE_INFINITY;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public double getT0() {
/* 429 */     return t0();
/*     */   }
/*     */   
/*     */   public double t1() {
/* 437 */     return Double.POSITIVE_INFINITY;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public double getT1() {
/* 445 */     return t1();
/*     */   }
/*     */   
/*     */   public Point2D point(double t) {
/* 453 */     return new Point2D(this.x0 + this.dx * t, this.y0 + this.dy * t);
/*     */   }
/*     */   
/*     */   public Collection<? extends StraightLine2D> continuousCurves() {
/* 461 */     ArrayList<StraightLine2D> list = 
/* 462 */       new ArrayList<StraightLine2D>(1);
/* 463 */     list.add(this);
/* 464 */     return list;
/*     */   }
/*     */   
/*     */   public StraightLine2D reverse() {
/* 472 */     return new StraightLine2D(this.x0, this.y0, -this.dx, -this.dy);
/*     */   }
/*     */   
/*     */   public GeneralPath appendPath(GeneralPath path) {
/* 476 */     throw new UnboundedShape2DException(this);
/*     */   }
/*     */   
/*     */   public boolean isBounded() {
/* 485 */     return false;
/*     */   }
/*     */   
/*     */   public double distance(double x, double y) {
/* 493 */     Point2D proj = projectedPoint(x, y);
/* 494 */     return proj.distance(x, y);
/*     */   }
/*     */   
/*     */   public Box2D boundingBox() {
/* 498 */     if (Math.abs(this.dx) < 1.0E-12D)
/* 499 */       return new Box2D(
/* 500 */           this.x0, this.x0, 
/* 501 */           Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY); 
/* 502 */     if (Math.abs(this.dy) < 1.0E-12D)
/* 503 */       return new Box2D(
/* 504 */           Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 
/* 505 */           this.x0, this.y0); 
/* 507 */     return new Box2D(
/* 508 */         Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 
/* 509 */         Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
/*     */   }
/*     */   
/*     */   public StraightLine2D transform(AffineTransform2D trans) {
/* 517 */     double[] tab = trans.coefficients();
/* 518 */     return new StraightLine2D(
/* 519 */         this.x0 * tab[0] + this.y0 * tab[1] + tab[2], 
/* 520 */         this.x0 * tab[3] + this.y0 * tab[4] + tab[5], 
/* 521 */         this.dx * tab[0] + this.dy * tab[1], 
/* 522 */         this.dx * tab[3] + this.dy * tab[4]);
/*     */   }
/*     */   
/*     */   public boolean contains(double x, double y) {
/* 534 */     return supportContains(x, y);
/*     */   }
/*     */   
/*     */   public boolean contains(Point2D p) {
/* 543 */     return supportContains(p.x(), p.y());
/*     */   }
/*     */   
/*     */   public GeneralPath getGeneralPath() {
/* 548 */     throw new UnboundedShape2DException(this);
/*     */   }
/*     */   
/*     */   public boolean almostEquals(GeometricObject2D obj, double eps) {
/* 558 */     if (this == obj)
/* 559 */       return true; 
/* 561 */     if (!(obj instanceof StraightLine2D))
/* 562 */       return false; 
/* 563 */     StraightLine2D line = (StraightLine2D)obj;
/* 565 */     if (Math.abs(this.x0 - line.x0) > eps)
/* 566 */       return false; 
/* 567 */     if (Math.abs(this.y0 - line.y0) > eps)
/* 568 */       return false; 
/* 569 */     if (Math.abs(this.dx - line.dx) > eps)
/* 570 */       return false; 
/* 571 */     if (Math.abs(this.dy - line.dy) > eps)
/* 572 */       return false; 
/* 574 */     return true;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 583 */     return new String("StraightLine2D(" + this.x0 + "," + this.y0 + "," + 
/* 584 */         this.dx + "," + this.dy + ")");
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 589 */     if (this == obj)
/* 590 */       return true; 
/* 591 */     if (!(obj instanceof StraightLine2D))
/* 592 */       return false; 
/* 593 */     StraightLine2D that = (StraightLine2D)obj;
/* 596 */     if (!EqualUtils.areEqual(this.x0, that.x0))
/* 597 */       return false; 
/* 598 */     if (!EqualUtils.areEqual(this.y0, that.y0))
/* 599 */       return false; 
/* 600 */     if (!EqualUtils.areEqual(this.dx, that.dx))
/* 601 */       return false; 
/* 602 */     if (!EqualUtils.areEqual(this.dy, that.dy))
/* 603 */       return false; 
/* 605 */     return true;
/*     */   }
/*     */   
/*     */   public StraightLine2D clone() {
/* 610 */     return new StraightLine2D(this.x0, this.y0, this.dx, this.dy);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\line\StraightLine2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
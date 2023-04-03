/*     */ package math.geom2d.line;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import math.geom2d.AffineTransform2D;
/*     */ import math.geom2d.Angle2D;
/*     */ import math.geom2d.Box2D;
/*     */ import math.geom2d.Point2D;
/*     */ import math.geom2d.Shape2D;
/*     */ import math.geom2d.Vector2D;
/*     */ import math.geom2d.circulinear.CirculinearContinuousCurve2D;
/*     */ import math.geom2d.circulinear.CirculinearCurve2D;
/*     */ import math.geom2d.circulinear.CirculinearDomain2D;
/*     */ import math.geom2d.circulinear.CirculinearElement2D;
/*     */ import math.geom2d.circulinear.CirculinearShape2D;
/*     */ import math.geom2d.circulinear.buffer.BufferCalculator;
/*     */ import math.geom2d.conic.Circle2D;
/*     */ import math.geom2d.conic.CircleArc2D;
/*     */ import math.geom2d.curve.AbstractSmoothCurve2D;
/*     */ import math.geom2d.curve.ContinuousCurve2D;
/*     */ import math.geom2d.curve.Curve2D;
/*     */ import math.geom2d.curve.CurveArray2D;
/*     */ import math.geom2d.curve.CurveSet2D;
/*     */ import math.geom2d.curve.Curves2D;
/*     */ import math.geom2d.curve.SmoothCurve2D;
/*     */ import math.geom2d.domain.ContinuousOrientedCurve2D;
/*     */ import math.geom2d.domain.OrientedCurve2D;
/*     */ import math.geom2d.domain.SmoothOrientedCurve2D;
/*     */ import math.geom2d.transform.CircleInversion2D;
/*     */ 
/*     */ public abstract class AbstractLine2D extends AbstractSmoothCurve2D implements SmoothOrientedCurve2D, LinearElement2D {
/*     */   protected double x0;
/*     */   
/*     */   protected double y0;
/*     */   
/*     */   protected double dx;
/*     */   
/*     */   protected double dy;
/*     */   
/*     */   public static Point2D getIntersection(AbstractLine2D line1, AbstractLine2D line2) {
/*  74 */     double denom = line1.dx * line2.dy - line1.dy * line2.dx;
/*  75 */     if (Math.abs(denom) < 1.0E-12D)
/*  76 */       return null; 
/*  79 */     double t = ((line1.y0 - line2.y0) * line2.dx - (
/*  80 */       line1.x0 - line2.x0) * line2.dy) / denom;
/*  81 */     return new Point2D(line1.x0 + t * line1.dx, line1.y0 + t * line1.dy);
/*     */   }
/*     */   
/*     */   public static boolean isColinear(AbstractLine2D line1, AbstractLine2D line2) {
/*  89 */     if (Math.abs(line1.dx * line2.dy - line1.dy * line2.dx) > 1.0E-12D)
/*  90 */       return false; 
/*  94 */     return 
/*     */       
/*  96 */       (Math.abs((line2.y0 - line1.y0) * line2.dx - (line2.x0 - line1.x0) * line2.dy) / Math.hypot(line2.dx, line2.dy) < 1.0E-12D);
/*     */   }
/*     */   
/*     */   public static boolean isParallel(AbstractLine2D line1, AbstractLine2D line2) {
/* 103 */     return (Math.abs(line1.dx * line2.dy - line1.dy * line2.dx) < 1.0E-12D);
/*     */   }
/*     */   
/*     */   protected AbstractLine2D(double x0, double y0, double dx, double dy) {
/* 125 */     this.x0 = x0;
/* 126 */     this.y0 = y0;
/* 127 */     this.dx = dx;
/* 128 */     this.dy = dy;
/*     */   }
/*     */   
/*     */   protected AbstractLine2D(Point2D point, Vector2D vector) {
/* 132 */     this.x0 = point.x();
/* 133 */     this.y0 = point.y();
/* 134 */     this.dx = vector.x();
/* 135 */     this.dy = vector.y();
/*     */   }
/*     */   
/*     */   protected AbstractLine2D(LinearShape2D line) {
/* 139 */     this(line.origin(), line.direction());
/*     */   }
/*     */   
/*     */   public boolean isColinear(LinearShape2D linear) {
/* 150 */     if (!isParallel(linear))
/* 151 */       return false; 
/* 155 */     StraightLine2D line = linear.supportingLine();
/* 156 */     if (Math.abs(this.dx) > Math.abs(this.dy)) {
/* 157 */       if (Math.abs((line.x0 - this.x0) * this.dy / this.dx + this.y0 - line.y0) > 1.0E-12D)
/* 158 */         return false; 
/* 160 */       return true;
/*     */     } 
/* 162 */     if (Math.abs((line.y0 - this.y0) * this.dx / this.dy + this.x0 - line.x0) > 1.0E-12D)
/* 163 */       return false; 
/* 165 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isParallel(LinearShape2D line) {
/* 173 */     return Vector2D.isColinear(direction(), line.direction());
/*     */   }
/*     */   
/*     */   protected boolean supportContains(double x, double y) {
/* 181 */     double denom = Math.hypot(this.dx, this.dy);
/* 182 */     if (denom < 1.0E-12D)
/* 183 */       throw new DegeneratedLine2DException(this); 
/* 184 */     return (Math.abs((x - this.x0) * this.dy - (y - this.y0) * this.dx) / denom < 1.0E-12D);
/*     */   }
/*     */   
/*     */   public double[][] parametric() {
/* 197 */     double[][] tab = new double[2][2];
/* 198 */     tab[0][0] = this.x0;
/* 199 */     tab[0][1] = this.dx;
/* 200 */     tab[1][0] = this.y0;
/* 201 */     tab[1][1] = this.dy;
/* 202 */     return tab;
/*     */   }
/*     */   
/*     */   public double[] cartesianEquation() {
/* 212 */     double[] tab = new double[3];
/* 213 */     tab[0] = this.dy;
/* 214 */     tab[1] = -this.dx;
/* 215 */     tab[2] = this.dx * this.y0 - this.dy * this.x0;
/* 216 */     return tab;
/*     */   }
/*     */   
/*     */   public double[] polarCoefficients() {
/* 227 */     double[] tab = new double[2];
/* 228 */     double d = signedDistance(0.0D, 0.0D);
/* 229 */     tab[0] = Math.abs(d);
/* 230 */     if (d > 0.0D) {
/* 231 */       tab[1] = (horizontalAngle() + Math.PI) % 6.283185307179586D;
/*     */     } else {
/* 233 */       tab[1] = horizontalAngle();
/*     */     } 
/* 234 */     return tab;
/*     */   }
/*     */   
/*     */   public double[] polarCoefficientsSigned() {
/* 246 */     double[] tab = new double[2];
/* 247 */     tab[0] = signedDistance(0.0D, 0.0D);
/* 248 */     tab[1] = horizontalAngle();
/* 249 */     return tab;
/*     */   }
/*     */   
/*     */   public double positionOnLine(Point2D point) {
/* 253 */     return positionOnLine(point.x(), point.y());
/*     */   }
/*     */   
/*     */   public double positionOnLine(double x, double y) {
/* 265 */     double denom = this.dx * this.dx + this.dy * this.dy;
/* 266 */     if (Math.abs(denom) < 1.0E-12D)
/* 267 */       throw new DegeneratedLine2DException(this); 
/* 268 */     return ((y - this.y0) * this.dy + (x - this.x0) * this.dx) / denom;
/*     */   }
/*     */   
/*     */   public Point2D projectedPoint(Point2D p) {
/* 280 */     return projectedPoint(p.x(), p.y());
/*     */   }
/*     */   
/*     */   public Point2D projectedPoint(double x, double y) {
/* 294 */     if (contains(x, y))
/* 295 */       return new Point2D(x, y); 
/* 298 */     double t = positionOnLine(x, y);
/* 301 */     return new Point2D(this.x0 + t * this.dx, this.y0 + t * this.dy);
/*     */   }
/*     */   
/*     */   public Point2D getSymmetric(Point2D p) {
/* 312 */     return getSymmetric(p.x(), p.y());
/*     */   }
/*     */   
/*     */   public Point2D getSymmetric(double x, double y) {
/* 325 */     double t = 2.0D * positionOnLine(x, y);
/* 328 */     return new Point2D(2.0D * this.x0 + t * this.dx - x, 2.0D * this.y0 + t * this.dy - y);
/*     */   }
/*     */   
/*     */   public StraightLine2D parallel(Point2D point) {
/* 339 */     return new StraightLine2D(point, this.dx, this.dy);
/*     */   }
/*     */   
/*     */   public StraightLine2D perpendicular(Point2D point) {
/* 350 */     return new StraightLine2D(point, -this.dy, this.dx);
/*     */   }
/*     */   
/*     */   public Point2D origin() {
/* 361 */     return new Point2D(this.x0, this.y0);
/*     */   }
/*     */   
/*     */   public Vector2D direction() {
/* 368 */     return new Vector2D(this.dx, this.dy);
/*     */   }
/*     */   
/*     */   public double horizontalAngle() {
/* 376 */     return (Math.atan2(this.dy, this.dx) + 6.283185307179586D) % 6.283185307179586D;
/*     */   }
/*     */   
/*     */   public Point2D intersection(LinearShape2D line) {
/* 384 */     Vector2D vect = line.direction();
/* 385 */     double dx2 = vect.x();
/* 386 */     double dy2 = vect.y();
/* 389 */     double denom = this.dx * dy2 - this.dy * dx2;
/* 390 */     if (Math.abs(denom) < 1.0E-12D)
/* 391 */       return null; 
/* 394 */     Point2D origin = line.origin();
/* 395 */     double x2 = origin.x();
/* 396 */     double y2 = origin.y();
/* 397 */     double t = ((this.y0 - y2) * dx2 - (this.x0 - x2) * dy2) / denom;
/* 400 */     Point2D point = new Point2D(this.x0 + t * this.dx, this.y0 + t * this.dy);
/* 404 */     if (contains(point) && line.contains(point))
/* 405 */       return point; 
/* 406 */     return null;
/*     */   }
/*     */   
/*     */   public StraightLine2D supportingLine() {
/* 413 */     return new StraightLine2D(this);
/*     */   }
/*     */   
/*     */   public double length() {
/* 424 */     if (!isBounded())
/* 425 */       return Double.POSITIVE_INFINITY; 
/* 426 */     return (t1() - t0()) * Math.hypot(this.dx, this.dy);
/*     */   }
/*     */   
/*     */   public double length(double pos) {
/* 433 */     return pos * Math.hypot(this.dx, this.dy);
/*     */   }
/*     */   
/*     */   public double position(double distance) {
/* 442 */     double delta = Math.hypot(this.dx, this.dy);
/* 443 */     if (delta < 1.0E-12D)
/* 444 */       throw new DegeneratedLine2DException(this); 
/* 445 */     return distance / delta;
/*     */   }
/*     */   
/*     */   public CirculinearElement2D transform(CircleInversion2D inv) {
/* 457 */     Point2D center = inv.center();
/* 458 */     double r = inv.radius();
/* 461 */     StraightLine2D line = supportingLine();
/* 462 */     Point2D po = line.projectedPoint(center);
/* 463 */     double d = line.distance(center);
/* 466 */     boolean inf0 = Double.isInfinite(t0());
/* 467 */     boolean inf1 = Double.isInfinite(t1());
/* 471 */     if (Math.abs(d) < 1.0E-12D) {
/* 472 */       if (inf0) {
/* 473 */         if (inf1)
/* 475 */           return new StraightLine2D(this); 
/* 479 */         Point2D point2D = lastPoint().transform(inv);
/* 480 */         return new InvertedRay2D(point2D, direction());
/*     */       } 
/* 483 */       Point2D point2D1 = firstPoint().transform(inv);
/* 484 */       if (inf1)
/* 486 */         return new Ray2D(point2D1, direction()); 
/* 489 */       Point2D point2D2 = lastPoint().transform(inv);
/* 490 */       return new LineSegment2D(point2D1, point2D2);
/*     */     } 
/* 496 */     double angle = Angle2D.horizontalAngle(center, po);
/* 499 */     double r2 = r * r / d / 2.0D;
/* 500 */     Point2D c2 = Point2D.createPolar(center, r2, angle);
/* 503 */     boolean direct = isInside(center);
/* 506 */     if (inf0 && inf1)
/* 507 */       return (CirculinearElement2D)new Circle2D(c2, r2, direct); 
/* 512 */     Point2D p1 = inf0 ? center : firstPoint().transform(inv);
/* 513 */     Point2D p2 = inf1 ? center : lastPoint().transform(inv);
/* 516 */     double theta1 = Angle2D.horizontalAngle(c2, p1);
/* 517 */     double theta2 = Angle2D.horizontalAngle(c2, p2);
/* 520 */     return (CirculinearElement2D)new CircleArc2D(c2, r2, theta1, theta2, direct);
/*     */   }
/*     */   
/*     */   public CirculinearDomain2D buffer(double dist) {
/* 527 */     BufferCalculator bc = BufferCalculator.getDefaultInstance();
/* 528 */     return bc.computeBuffer(this, dist);
/*     */   }
/*     */   
/*     */   public double windingAngle(Point2D point) {
/* 540 */     double angle1, angle2, t0 = t0();
/* 541 */     double t1 = t1();
/* 544 */     if (t0 == Double.NEGATIVE_INFINITY) {
/* 545 */       angle1 = Angle2D.horizontalAngle(-this.dx, -this.dy);
/*     */     } else {
/* 547 */       angle1 = Angle2D.horizontalAngle(point.x(), point.y(), 
/* 548 */           this.x0 + t0 * this.dx, this.y0 + t0 * this.dy);
/*     */     } 
/* 550 */     if (t1 == Double.POSITIVE_INFINITY) {
/* 551 */       angle2 = Angle2D.horizontalAngle(this.dx, this.dy);
/*     */     } else {
/* 553 */       angle2 = Angle2D.horizontalAngle(point.x(), point.y(), 
/* 554 */           this.x0 + t1 * this.dx, this.y0 + t1 * this.dy);
/*     */     } 
/* 556 */     if (isInside(point)) {
/* 557 */       if (angle2 > angle1)
/* 558 */         return angle2 - angle1; 
/* 560 */       return 6.283185307179586D - angle1 + angle2;
/*     */     } 
/* 562 */     if (angle2 > angle1)
/* 563 */       return angle2 - angle1 - 6.283185307179586D; 
/* 565 */     return angle2 - angle1;
/*     */   }
/*     */   
/*     */   public double signedDistance(Point2D p) {
/* 577 */     return signedDistance(p.x(), p.y());
/*     */   }
/*     */   
/*     */   public double signedDistance(double x, double y) {
/* 589 */     double delta = Math.hypot(this.dx, this.dy);
/* 590 */     if (delta < 1.0E-12D)
/* 591 */       throw new DegeneratedLine2DException(this); 
/* 592 */     return ((x - this.x0) * this.dy - (y - this.y0) * this.dx) / delta;
/*     */   }
/*     */   
/*     */   public boolean isInside(Point2D p) {
/* 603 */     return ((p.x() - this.x0) * this.dy - (p.y() - this.y0) * this.dx < 0.0D);
/*     */   }
/*     */   
/*     */   public Vector2D tangent(double t) {
/* 614 */     return new Vector2D(this.dx, this.dy);
/*     */   }
/*     */   
/*     */   public double curvature(double t) {
/* 621 */     return 0.0D;
/*     */   }
/*     */   
/*     */   public boolean isClosed() {
/* 633 */     return false;
/*     */   }
/*     */   
/*     */   public Collection<? extends AbstractLine2D> smoothPieces() {
/* 645 */     return wrapCurve((ContinuousCurve2D)this);
/*     */   }
/*     */   
/*     */   public Collection<Point2D> intersections(LinearShape2D line) {
/* 653 */     if (isParallel(line))
/* 654 */       return new ArrayList<Point2D>(0); 
/* 656 */     ArrayList<Point2D> points = new ArrayList<Point2D>(1);
/* 657 */     Point2D point = intersection(line);
/* 658 */     if (point != null)
/* 659 */       points.add(point); 
/* 662 */     return points;
/*     */   }
/*     */   
/*     */   public double position(Point2D point) {
/* 674 */     double pos = positionOnLine(point);
/* 677 */     double eps = Math.hypot(this.dx, this.dy) * 1.0E-12D;
/* 680 */     if (pos < t0() - eps)
/* 681 */       return Double.NaN; 
/* 682 */     if (pos > t1() + eps)
/* 683 */       return Double.NaN; 
/* 684 */     return pos;
/*     */   }
/*     */   
/*     */   public double project(Point2D point) {
/* 697 */     double pos = positionOnLine(point);
/* 700 */     return Math.min(Math.max(pos, t0()), t1());
/*     */   }
/*     */   
/*     */   public AbstractLine2D subCurve(double t0, double t1) {
/* 710 */     t0 = Math.max(t0, t0());
/* 711 */     t1 = Math.min(t1, t1());
/* 714 */     if (Double.isInfinite(t1)) {
/* 715 */       if (Double.isInfinite(t0))
/* 716 */         return new StraightLine2D(this); 
/* 718 */       return new Ray2D(point(t0), direction());
/*     */     } 
/* 721 */     if (Double.isInfinite(t0))
/* 722 */       return new InvertedRay2D(point(t1), direction()); 
/* 724 */     return new LineSegment2D(point(t0), point(t1));
/*     */   }
/*     */   
/*     */   public Collection<? extends AbstractLine2D> continuousCurves() {
/* 733 */     return wrapCurve((ContinuousCurve2D)this);
/*     */   }
/*     */   
/*     */   public double distance(Point2D p) {
/* 746 */     return distance(p.x(), p.y());
/*     */   }
/*     */   
/*     */   public double distance(double x, double y) {
/* 761 */     Point2D proj = projectedPoint(x, y);
/* 764 */     if (contains(proj))
/* 765 */       return proj.distance(x, y); 
/* 768 */     double dist = Double.POSITIVE_INFINITY;
/* 769 */     if (!Double.isInfinite(t0()))
/* 770 */       dist = firstPoint().distance(x, y); 
/* 771 */     if (!Double.isInfinite(t1()))
/* 772 */       dist = Math.min(dist, lastPoint().distance(x, y)); 
/* 773 */     return dist;
/*     */   }
/*     */   
/*     */   public boolean contains(Point2D p) {
/* 780 */     return contains(p.x(), p.y());
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 787 */     return (Math.hypot(this.dx, this.dy) < 1.0E-12D);
/*     */   }
/*     */   
/*     */   public CurveSet2D<? extends AbstractLine2D> clip(Box2D box) {
/* 797 */     CurveSet2D<ContinuousCurve2D> set = Curves2D.clipContinuousCurve(
/* 798 */         (ContinuousCurve2D)this, box);
/* 801 */     CurveArray2D<AbstractLine2D> result = 
/* 802 */       new CurveArray2D(set.size());
/* 805 */     for (Curve2D curve : set.curves()) {
/* 806 */       if (curve instanceof AbstractLine2D)
/* 807 */         result.add(curve); 
/*     */     } 
/* 809 */     return (CurveSet2D<? extends AbstractLine2D>)result;
/*     */   }
/*     */   
/*     */   public abstract AbstractLine2D transform(AffineTransform2D paramAffineTransform2D);
/*     */   
/*     */   public abstract AbstractLine2D clone();
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\line\AbstractLine2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
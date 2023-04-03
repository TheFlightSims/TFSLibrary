/*     */ package math.geom2d.line;
/*     */ 
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.util.Collection;
/*     */ import math.geom2d.AffineTransform2D;
/*     */ import math.geom2d.Angle2D;
/*     */ import math.geom2d.Box2D;
/*     */ import math.geom2d.GeometricObject2D;
/*     */ import math.geom2d.Point2D;
/*     */ import math.geom2d.Shape2D;
/*     */ import math.geom2d.Vector2D;
/*     */ import math.geom2d.circulinear.CirculinearContinuousCurve2D;
/*     */ import math.geom2d.circulinear.CirculinearCurve2D;
/*     */ import math.geom2d.circulinear.CirculinearDomain2D;
/*     */ import math.geom2d.circulinear.CirculinearElement2D;
/*     */ import math.geom2d.circulinear.CirculinearShape2D;
/*     */ import math.geom2d.circulinear.buffer.BufferCalculator;
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
/*     */ public class Line2D extends AbstractSmoothCurve2D implements LinearElement2D, Cloneable {
/*     */   public Point2D p1;
/*     */   
/*     */   public Point2D p2;
/*     */   
/*     */   public static boolean intersects(Line2D line1, Line2D line2) {
/*  96 */     Point2D e1p1 = line1.firstPoint();
/*  97 */     Point2D e1p2 = line1.lastPoint();
/*  98 */     Point2D e2p1 = line2.firstPoint();
/*  99 */     Point2D e2p2 = line2.lastPoint();
/* 101 */     boolean b1 = (Point2D.ccw(e1p1, e1p2, e2p1) * 
/* 102 */       Point2D.ccw(e1p1, e1p2, e2p2) <= 0);
/* 103 */     boolean b2 = (Point2D.ccw(e2p1, e2p2, e1p1) * 
/* 104 */       Point2D.ccw(e2p1, e2p2, e1p2) <= 0);
/* 105 */     return (b1 && b2);
/*     */   }
/*     */   
/*     */   public Line2D(Point2D point1, Point2D point2) {
/* 113 */     this.p1 = point1;
/* 114 */     this.p2 = point2;
/*     */   }
/*     */   
/*     */   public Line2D(double x1, double y1, double x2, double y2) {
/* 119 */     this.p1 = new Point2D(x1, y1);
/* 120 */     this.p2 = new Point2D(x2, y2);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static Line2D create(Point2D p1, Point2D p2) {
/* 133 */     return new Line2D(p1, p2);
/*     */   }
/*     */   
/*     */   public Point2D getPoint1() {
/* 146 */     return this.p1;
/*     */   }
/*     */   
/*     */   public Point2D getPoint2() {
/* 155 */     return this.p2;
/*     */   }
/*     */   
/*     */   public double getX1() {
/* 159 */     return this.p1.x();
/*     */   }
/*     */   
/*     */   public double getY1() {
/* 163 */     return this.p1.y();
/*     */   }
/*     */   
/*     */   public double getX2() {
/* 167 */     return this.p2.x();
/*     */   }
/*     */   
/*     */   public double getY2() {
/* 171 */     return this.p2.y();
/*     */   }
/*     */   
/*     */   public Point2D getOtherPoint(Point2D point) {
/* 181 */     if (point.equals(this.p1))
/* 182 */       return this.p2; 
/* 183 */     if (point.equals(this.p2))
/* 184 */       return this.p1; 
/* 185 */     return null;
/*     */   }
/*     */   
/*     */   public void setPoint1(Point2D point) {
/* 189 */     this.p1 = point;
/*     */   }
/*     */   
/*     */   public void setPoint2(Point2D point) {
/* 193 */     this.p2 = point;
/*     */   }
/*     */   
/*     */   public boolean isColinear(LinearShape2D line) {
/* 200 */     return (new LineSegment2D(this.p1, this.p2)).isColinear(line);
/*     */   }
/*     */   
/*     */   public boolean isParallel(LinearShape2D line) {
/* 208 */     return (new LineSegment2D(this.p1, this.p2)).isParallel(line);
/*     */   }
/*     */   
/*     */   public CirculinearDomain2D buffer(double dist) {
/* 218 */     BufferCalculator bc = BufferCalculator.getDefaultInstance();
/* 219 */     return bc.computeBuffer(this, dist);
/*     */   }
/*     */   
/*     */   public Line2D parallel(double d) {
/* 226 */     double x0 = getX1();
/* 227 */     double y0 = getY1();
/* 228 */     double dx = getX2() - x0;
/* 229 */     double dy = getY2() - y0;
/* 230 */     double d2 = d / Math.hypot(dx, dy);
/* 231 */     return new Line2D(
/* 232 */         x0 + dy * d2, y0 - dx * d2, 
/* 233 */         x0 + dx + dy * d2, y0 + dy - dx * d2);
/*     */   }
/*     */   
/*     */   public double length() {
/* 240 */     return this.p1.distance(this.p2);
/*     */   }
/*     */   
/*     */   public double length(double pos) {
/* 247 */     double dx = this.p2.x() - this.p1.x();
/* 248 */     double dy = this.p2.y() - this.p1.y();
/* 249 */     return pos * Math.hypot(dx, dy);
/*     */   }
/*     */   
/*     */   public double position(double length) {
/* 256 */     double dx = this.p2.x() - this.p1.x();
/* 257 */     double dy = this.p2.y() - this.p1.y();
/* 258 */     return length / Math.hypot(dx, dy);
/*     */   }
/*     */   
/*     */   public CirculinearElement2D transform(CircleInversion2D inv) {
/* 266 */     Point2D center = inv.center();
/* 267 */     double r = inv.radius();
/* 270 */     Point2D po = (new StraightLine2D(this)).projectedPoint(center);
/* 271 */     double d = distance(po);
/* 275 */     if (Math.abs(d) < 1.0E-12D) {
/* 276 */       Point2D p1 = firstPoint().transform(inv);
/* 277 */       Point2D p2 = lastPoint().transform(inv);
/* 278 */       return new LineSegment2D(p1, p2);
/*     */     } 
/* 282 */     double angle = Angle2D.horizontalAngle(center, po);
/* 285 */     double r2 = r * r / d / 2.0D;
/* 286 */     Point2D c2 = Point2D.createPolar(center, r2, angle);
/* 289 */     boolean direct = !isInside(center);
/* 292 */     double theta1 = Angle2D.horizontalAngle(c2, this.p1);
/* 293 */     double theta2 = Angle2D.horizontalAngle(c2, this.p2);
/* 296 */     return (CirculinearElement2D)new CircleArc2D(c2, r2, theta1, theta2, direct);
/*     */   }
/*     */   
/*     */   public double[][] parametric() {
/* 305 */     return (new LineSegment2D(this.p1, this.p2)).parametric();
/*     */   }
/*     */   
/*     */   public double[] cartesianEquation() {
/* 309 */     return (new LineSegment2D(this.p1, this.p2)).cartesianEquation();
/*     */   }
/*     */   
/*     */   public double[] polarCoefficients() {
/* 313 */     return (new LineSegment2D(this.p1, this.p2)).polarCoefficients();
/*     */   }
/*     */   
/*     */   public double[] polarCoefficientsSigned() {
/* 317 */     return (new LineSegment2D(this.p1, this.p2)).polarCoefficientsSigned();
/*     */   }
/*     */   
/*     */   public double horizontalAngle() {
/* 324 */     return (new LineSegment2D(this.p1, this.p2)).horizontalAngle();
/*     */   }
/*     */   
/*     */   public Point2D intersection(LinearShape2D line) {
/* 331 */     return (new LineSegment2D(this.p1, this.p2)).intersection(line);
/*     */   }
/*     */   
/*     */   public Point2D origin() {
/* 338 */     return this.p1;
/*     */   }
/*     */   
/*     */   public StraightLine2D supportingLine() {
/* 345 */     return new StraightLine2D(this.p1, this.p2);
/*     */   }
/*     */   
/*     */   public Vector2D direction() {
/* 352 */     return new Vector2D(this.p1, this.p2);
/*     */   }
/*     */   
/*     */   public double signedDistance(Point2D p) {
/* 360 */     return signedDistance(p.x(), p.y());
/*     */   }
/*     */   
/*     */   public double signedDistance(double x, double y) {
/* 364 */     return (new LineSegment2D(this.p1, this.p2)).signedDistance(x, y);
/*     */   }
/*     */   
/*     */   public Collection<? extends Line2D> smoothPieces() {
/* 376 */     return wrapCurve((ContinuousCurve2D)this);
/*     */   }
/*     */   
/*     */   public boolean isClosed() {
/* 384 */     return false;
/*     */   }
/*     */   
/*     */   public double distance(Point2D p) {
/* 394 */     return distance(p.x(), p.y());
/*     */   }
/*     */   
/*     */   public double distance(double x, double y) {
/* 402 */     StraightLine2D support = new StraightLine2D(this.p1, this.p2);
/* 403 */     Point2D proj = support.projectedPoint(x, y);
/* 406 */     if (contains(proj))
/* 407 */       return proj.distance(x, y); 
/* 410 */     double d1 = Math.hypot(this.p1.x() - x, this.p1.y() - y);
/* 411 */     double d2 = Math.hypot(this.p2.x() - x, this.p2.y() - y);
/* 412 */     return Math.min(d1, d2);
/*     */   }
/*     */   
/*     */   public StraightLine2D parallel(Point2D point) {
/* 423 */     return (new LineSegment2D(this.p1, this.p2)).parallel(point);
/*     */   }
/*     */   
/*     */   public StraightLine2D perpendicular(Point2D point) {
/* 434 */     return (new LineSegment2D(this.p1, this.p2)).perpendicular(point);
/*     */   }
/*     */   
/*     */   public CurveSet2D<? extends Line2D> clip(Box2D box) {
/* 445 */     CurveSet2D<? extends Curve2D> set = Curves2D.clipCurve((Curve2D)this, box);
/* 448 */     CurveArray2D<Line2D> result = 
/* 449 */       new CurveArray2D(set.size());
/* 452 */     for (Curve2D curve : set.curves()) {
/* 453 */       if (curve instanceof Line2D)
/* 454 */         result.add(curve); 
/*     */     } 
/* 456 */     return (CurveSet2D<? extends Line2D>)result;
/*     */   }
/*     */   
/*     */   public Box2D boundingBox() {
/* 463 */     return new Box2D(this.p1, this.p2);
/*     */   }
/*     */   
/*     */   public Vector2D tangent(double t) {
/* 470 */     return new Vector2D(this.p1, this.p2);
/*     */   }
/*     */   
/*     */   public double curvature(double t) {
/* 477 */     return 0.0D;
/*     */   }
/*     */   
/*     */   public double windingAngle(Point2D point) {
/* 484 */     return (new LineSegment2D(this.p1, this.p2)).windingAngle(point);
/*     */   }
/*     */   
/*     */   public boolean isInside(Point2D point) {
/* 488 */     return ((new LineSegment2D(this.p1, this.p2)).signedDistance(point) < 0.0D);
/*     */   }
/*     */   
/*     */   public double t0() {
/* 498 */     return 0.0D;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public double getT0() {
/* 506 */     return t0();
/*     */   }
/*     */   
/*     */   public double t1() {
/* 513 */     return 1.0D;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public double getT1() {
/* 521 */     return t1();
/*     */   }
/*     */   
/*     */   public Point2D point(double t) {
/* 525 */     t = Math.min(Math.max(t, 0.0D), 1.0D);
/* 526 */     double x = this.p1.x() * (1.0D - t) + this.p2.x() * t;
/* 527 */     double y = this.p1.y() * (1.0D - t) + this.p2.y() * t;
/* 528 */     return new Point2D(x, y);
/*     */   }
/*     */   
/*     */   public Point2D firstPoint() {
/* 538 */     return this.p1;
/*     */   }
/*     */   
/*     */   public Point2D lastPoint() {
/* 548 */     return this.p2;
/*     */   }
/*     */   
/*     */   public double position(Point2D point) {
/* 562 */     return (new LineSegment2D(this.p1, this.p2)).position(point);
/*     */   }
/*     */   
/*     */   public double project(Point2D point) {
/* 566 */     return (new LineSegment2D(this.p1, this.p2)).project(point);
/*     */   }
/*     */   
/*     */   public Line2D reverse() {
/* 574 */     return new Line2D(this.p2, this.p1);
/*     */   }
/*     */   
/*     */   public Collection<? extends Line2D> continuousCurves() {
/* 579 */     return wrapCurve((ContinuousCurve2D)this);
/*     */   }
/*     */   
/*     */   public Line2D subCurve(double t0, double t1) {
/* 587 */     if (t0 > t1)
/* 588 */       return null; 
/* 589 */     t0 = Math.max(t0, t0());
/* 590 */     t1 = Math.min(t1, t1());
/* 591 */     return new Line2D(point(t0), point(t1));
/*     */   }
/*     */   
/*     */   public Collection<Point2D> intersections(LinearShape2D line) {
/* 598 */     return (new LineSegment2D(this.p1, this.p2)).intersections(line);
/*     */   }
/*     */   
/*     */   public Line2D transform(AffineTransform2D trans) {
/* 605 */     return new Line2D(
/* 606 */         this.p1.transform(trans), 
/* 607 */         this.p2.transform(trans));
/*     */   }
/*     */   
/*     */   public boolean contains(double x, double y) {
/* 618 */     return (new LineSegment2D(this.p1, this.p2)).contains(x, y);
/*     */   }
/*     */   
/*     */   public boolean contains(Point2D p) {
/* 626 */     return contains(p.x(), p.y());
/*     */   }
/*     */   
/*     */   public boolean isBounded() {
/* 633 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 640 */     return false;
/*     */   }
/*     */   
/*     */   public GeneralPath getGeneralPath() {
/* 644 */     GeneralPath path = new GeneralPath();
/* 645 */     path.moveTo((float)this.p1.x(), (float)this.p1.y());
/* 646 */     path.lineTo((float)this.p2.x(), (float)this.p2.y());
/* 647 */     return path;
/*     */   }
/*     */   
/*     */   public GeneralPath appendPath(GeneralPath path) {
/* 651 */     path.lineTo((float)this.p2.x(), (float)this.p2.y());
/* 652 */     return path;
/*     */   }
/*     */   
/*     */   public boolean almostEquals(GeometricObject2D obj, double eps) {
/* 663 */     if (this == obj)
/* 664 */       return true; 
/* 667 */     if (!(obj instanceof Line2D))
/* 668 */       return false; 
/* 671 */     Line2D edge = (Line2D)obj;
/* 672 */     return (this.p1.almostEquals((GeometricObject2D)edge.p1, eps) && this.p2.almostEquals((GeometricObject2D)edge.p2, eps));
/*     */   }
/*     */   
/*     */   public String toString() {
/* 680 */     return "Line2D(" + this.p1 + ")-(" + this.p2 + ")";
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 692 */     if (this == obj)
/* 693 */       return true; 
/* 694 */     if (!(obj instanceof Line2D))
/* 695 */       return false; 
/* 698 */     Line2D edge = (Line2D)obj;
/* 699 */     return (this.p1.equals(edge.p1) && this.p2.equals(edge.p2));
/*     */   }
/*     */   
/*     */   public Line2D clone() {
/* 704 */     return new Line2D(this.p1.clone(), this.p2.clone());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\line\Line2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
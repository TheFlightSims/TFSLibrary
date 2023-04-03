/*      */ package math.geom2d.conic;
/*      */ 
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Shape;
/*      */ import java.awt.geom.AffineTransform;
/*      */ import java.awt.geom.Ellipse2D;
/*      */ import java.awt.geom.GeneralPath;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.Iterator;
/*      */ import java.util.Locale;
/*      */ import math.geom2d.AffineTransform2D;
/*      */ import math.geom2d.Angle2D;
/*      */ import math.geom2d.Box2D;
/*      */ import math.geom2d.ColinearPoints2DException;
/*      */ import math.geom2d.GeometricObject2D;
/*      */ import math.geom2d.Point2D;
/*      */ import math.geom2d.Shape2D;
/*      */ import math.geom2d.Vector2D;
/*      */ import math.geom2d.circulinear.CircleLine2D;
/*      */ import math.geom2d.circulinear.CirculinearBoundary2D;
/*      */ import math.geom2d.circulinear.CirculinearContinuousCurve2D;
/*      */ import math.geom2d.circulinear.CirculinearContour2D;
/*      */ import math.geom2d.circulinear.CirculinearCurve2D;
/*      */ import math.geom2d.circulinear.CirculinearDomain2D;
/*      */ import math.geom2d.circulinear.CirculinearElement2D;
/*      */ import math.geom2d.circulinear.CirculinearRing2D;
/*      */ import math.geom2d.circulinear.CirculinearShape2D;
/*      */ import math.geom2d.circulinear.GenericCirculinearDomain2D;
/*      */ import math.geom2d.circulinear.buffer.BufferCalculator;
/*      */ import math.geom2d.curve.AbstractSmoothCurve2D;
/*      */ import math.geom2d.curve.ContinuousCurve2D;
/*      */ import math.geom2d.curve.Curve2D;
/*      */ import math.geom2d.curve.CurveArray2D;
/*      */ import math.geom2d.curve.CurveSet2D;
/*      */ import math.geom2d.curve.Curves2D;
/*      */ import math.geom2d.curve.SmoothCurve2D;
/*      */ import math.geom2d.domain.Boundary2D;
/*      */ import math.geom2d.domain.ContinuousOrientedCurve2D;
/*      */ import math.geom2d.domain.Contour2D;
/*      */ import math.geom2d.domain.Domain2D;
/*      */ import math.geom2d.domain.OrientedCurve2D;
/*      */ import math.geom2d.domain.SmoothContour2D;
/*      */ import math.geom2d.domain.SmoothOrientedCurve2D;
/*      */ import math.geom2d.line.AbstractLine2D;
/*      */ import math.geom2d.line.LinearShape2D;
/*      */ import math.geom2d.line.StraightLine2D;
/*      */ import math.geom2d.polygon.LinearCurve2D;
/*      */ import math.geom2d.polygon.LinearRing2D;
/*      */ import math.geom2d.transform.CircleInversion2D;
/*      */ import math.utils.EqualUtils;
/*      */ 
/*      */ public class Circle2D extends AbstractSmoothCurve2D implements EllipseShape2D, CircleLine2D, CircularShape2D, CirculinearRing2D, Cloneable {
/*      */   protected double xc;
/*      */   
/*      */   protected double yc;
/*      */   
/*      */   @Deprecated
/*      */   public static Circle2D create(Point2D center, double radius) {
/*   69 */     return new Circle2D(center, radius);
/*      */   }
/*      */   
/*      */   @Deprecated
/*      */   public static Circle2D create(Point2D center, double radius, boolean direct) {
/*   79 */     return new Circle2D(center, radius, direct);
/*      */   }
/*      */   
/*      */   @Deprecated
/*      */   public static Circle2D create(Point2D p1, Point2D p2, Point2D p3) {
/*   88 */     if (Point2D.isColinear(p1, p2, p3))
/*   89 */       throw new ColinearPoints2DException(p1, p2, p3); 
/*   92 */     StraightLine2D line12 = StraightLine2D.createMedian(p1, p2);
/*   93 */     StraightLine2D line23 = StraightLine2D.createMedian(p2, p3);
/*   96 */     assert !AbstractLine2D.isParallel((AbstractLine2D)line12, (AbstractLine2D)line23) : 
/*   97 */       "If points are not colinear, medians should not be parallel";
/*  100 */     Point2D center = AbstractLine2D.getIntersection((AbstractLine2D)line12, (AbstractLine2D)line23);
/*  101 */     double radius = Point2D.distance(center, p2);
/*  104 */     return new Circle2D(center, radius);
/*      */   }
/*      */   
/*      */   @Deprecated
/*      */   public static Collection<Point2D> getIntersections(Circle2D circle1, Circle2D circle2) {
/*  113 */     ArrayList<Point2D> intersections = new ArrayList<Point2D>(2);
/*  116 */     Point2D center1 = circle1.center();
/*  117 */     Point2D center2 = circle2.center();
/*  118 */     double r1 = circle1.radius();
/*  119 */     double r2 = circle2.radius();
/*  121 */     double d = Point2D.distance(center1, center2);
/*  124 */     if (d < Math.abs(r1 - r2) || d > r1 + r2)
/*  125 */       return intersections; 
/*  128 */     double angle = Angle2D.horizontalAngle(center1, center2);
/*  131 */     double d1 = d / 2.0D + (r1 * r1 - r2 * r2) / 2.0D * d;
/*  132 */     Point2D tmp = Point2D.createPolar(center1, d1, angle);
/*  135 */     double h = Math.sqrt(r1 * r1 - d1 * d1);
/*  136 */     intersections.add(Point2D.createPolar(tmp, h, angle + 1.5707963267948966D));
/*  137 */     intersections.add(Point2D.createPolar(tmp, h, angle - 1.5707963267948966D));
/*  139 */     return intersections;
/*      */   }
/*      */   
/*      */   @Deprecated
/*      */   public static Collection<Point2D> getIntersections(CircularShape2D circle, LinearShape2D line) {
/*  154 */     ArrayList<Point2D> intersections = new ArrayList<Point2D>(2);
/*  157 */     Circle2D parent = circle.supportingCircle();
/*  158 */     Point2D center = parent.center();
/*  159 */     double radius = parent.radius();
/*  163 */     StraightLine2D perp = StraightLine2D.createPerpendicular(line, center);
/*  166 */     Point2D inter = perp.intersection((LinearShape2D)new StraightLine2D(line));
/*  167 */     assert inter != null;
/*  168 */     double dist = inter.distance(center);
/*  172 */     if (Math.abs(dist - radius) < 1.0E-12D) {
/*  173 */       if (line.contains(inter) && circle.contains(inter))
/*  174 */         intersections.add(inter); 
/*  175 */       return intersections;
/*      */     } 
/*  180 */     double angle = line.horizontalAngle();
/*  181 */     double d2 = Math.sqrt(radius * radius - dist * dist);
/*  184 */     Point2D p1 = Point2D.createPolar(inter, d2, angle + Math.PI);
/*  185 */     Point2D p2 = Point2D.createPolar(inter, d2, angle);
/*  188 */     if (line.contains(p1) && circle.contains(p1))
/*  189 */       intersections.add(p1); 
/*  190 */     if (line.contains(p2) && circle.contains(p2))
/*  191 */       intersections.add(p2); 
/*  194 */     return intersections;
/*      */   }
/*      */   
/*      */   public static Circle2D circumCircle(Point2D p1, Point2D p2, Point2D p3) {
/*  205 */     Point2D center = circumCenter(p1, p2, p3);
/*  208 */     double radius = Point2D.distance(center, p2);
/*  211 */     return new Circle2D(center, radius);
/*      */   }
/*      */   
/*      */   public static Point2D circumCenter(Point2D p1, Point2D p2, Point2D p3) {
/*  220 */     if (Point2D.isColinear(p1, p2, p3))
/*  221 */       throw new ColinearPoints2DException(p1, p2, p3); 
/*  224 */     StraightLine2D line12 = StraightLine2D.createMedian(p1, p2);
/*  225 */     StraightLine2D line23 = StraightLine2D.createMedian(p2, p3);
/*  228 */     assert !AbstractLine2D.isParallel((AbstractLine2D)line12, (AbstractLine2D)line23) : 
/*  229 */       "If points are not colinear, medians should not be parallel";
/*  232 */     Point2D center = AbstractLine2D.getIntersection((AbstractLine2D)line12, (AbstractLine2D)line23);
/*  235 */     return center;
/*      */   }
/*      */   
/*      */   public static Collection<Point2D> circlesIntersections(Circle2D circle1, Circle2D circle2) {
/*  250 */     Point2D center1 = circle1.center();
/*  251 */     Point2D center2 = circle2.center();
/*  252 */     double r1 = circle1.radius();
/*  253 */     double r2 = circle2.radius();
/*  255 */     double d = Point2D.distance(center1, center2);
/*  258 */     if (d < Math.abs(r1 - r2) || d > r1 + r2)
/*  259 */       return new ArrayList<Point2D>(0); 
/*  262 */     double angle = Angle2D.horizontalAngle(center1, center2);
/*  265 */     double d1 = d / 2.0D + (r1 * r1 - r2 * r2) / 2.0D * d;
/*  266 */     Point2D tmp = Point2D.createPolar(center1, d1, angle);
/*  269 */     double h = Math.sqrt(r1 * r1 - d1 * d1);
/*  272 */     ArrayList<Point2D> intersections = new ArrayList<Point2D>(2);
/*  275 */     Point2D p1 = Point2D.createPolar(tmp, h, angle + 1.5707963267948966D);
/*  276 */     intersections.add(p1);
/*  277 */     Point2D p2 = Point2D.createPolar(tmp, h, angle - 1.5707963267948966D);
/*  278 */     intersections.add(p2);
/*  280 */     return intersections;
/*      */   }
/*      */   
/*      */   public static Collection<Point2D> lineCircleIntersections(LinearShape2D line, CircularShape2D circle) {
/*  295 */     ArrayList<Point2D> intersections = new ArrayList<Point2D>(2);
/*  298 */     Circle2D parent = circle.supportingCircle();
/*  299 */     Point2D center = parent.center();
/*  300 */     double radius = parent.radius();
/*  304 */     StraightLine2D perp = StraightLine2D.createPerpendicular(line, center);
/*  307 */     Point2D inter = perp.intersection((LinearShape2D)new StraightLine2D(line));
/*  308 */     if (inter == null)
/*  309 */       throw new RuntimeException("Could not compute intersection point when computing line-cicle intersection"); 
/*  311 */     double dist = inter.distance(center);
/*  315 */     if (Math.abs(dist - radius) < 1.0E-12D) {
/*  316 */       if (line.contains(inter) && circle.contains(inter))
/*  317 */         intersections.add(inter); 
/*  318 */       return intersections;
/*      */     } 
/*  323 */     double angle = line.horizontalAngle();
/*  324 */     double d2 = Math.sqrt(radius * radius - dist * dist);
/*  327 */     Point2D p1 = Point2D.createPolar(inter, d2, angle + Math.PI);
/*  328 */     Point2D p2 = Point2D.createPolar(inter, d2, angle);
/*  331 */     if (line.contains(p1) && circle.contains(p1))
/*  332 */       intersections.add(p1); 
/*  333 */     if (line.contains(p2) && circle.contains(p2))
/*  334 */       intersections.add(p2); 
/*  337 */     return intersections;
/*      */   }
/*      */   
/*      */   public static StraightLine2D radicalAxis(Circle2D circle1, Circle2D circle2) {
/*  351 */     double r1 = circle1.radius();
/*  352 */     double r2 = circle2.radius();
/*  353 */     Point2D p1 = circle1.center();
/*  354 */     Point2D p2 = circle2.center();
/*  357 */     double angle = Angle2D.horizontalAngle(p1, p2);
/*  360 */     double dist = p1.distance(p2);
/*  361 */     if (dist < 1.0E-12D)
/*  362 */       throw new IllegalArgumentException("Input circles must have distinct centers"); 
/*  366 */     double d = (dist * dist + r1 * r1 - r2 * r2) * 0.5D / dist;
/*  369 */     double cot = Math.cos(angle);
/*  370 */     double sit = Math.sin(angle);
/*  373 */     double x0 = p1.x() + d * cot;
/*  374 */     double y0 = p1.y() + d * sit;
/*  375 */     double dx = -sit;
/*  376 */     double dy = cot;
/*  379 */     return new StraightLine2D(x0, y0, dx, dy);
/*      */   }
/*      */   
/*  391 */   protected double r = 0.0D;
/*      */   
/*      */   protected boolean direct = true;
/*      */   
/*  397 */   protected double theta = 0.0D;
/*      */   
/*      */   public Circle2D() {
/*  405 */     this(0.0D, 0.0D, 0.0D, true);
/*      */   }
/*      */   
/*      */   public Circle2D(Point2D center, double radius) {
/*  410 */     this(center.x(), center.y(), radius, true);
/*      */   }
/*      */   
/*      */   public Circle2D(Point2D center, double radius, boolean direct) {
/*  415 */     this(center.x(), center.y(), radius, direct);
/*      */   }
/*      */   
/*      */   public Circle2D(double xcenter, double ycenter, double radius) {
/*  420 */     this(xcenter, ycenter, radius, true);
/*      */   }
/*      */   
/*      */   public Circle2D(double xcenter, double ycenter, double radius, boolean direct) {
/*  426 */     this.xc = xcenter;
/*  427 */     this.yc = ycenter;
/*  428 */     this.r = radius;
/*  429 */     this.direct = direct;
/*      */   }
/*      */   
/*      */   public double radius() {
/*  440 */     return this.r;
/*      */   }
/*      */   
/*      */   public Collection<Point2D> intersections(Circle2D circle) {
/*  448 */     return circlesIntersections(this, circle);
/*      */   }
/*      */   
/*      */   public Circle2D supportingCircle() {
/*  458 */     return this;
/*      */   }
/*      */   
/*      */   public boolean isDirect() {
/*  469 */     return this.direct;
/*      */   }
/*      */   
/*      */   public Point2D center() {
/*  476 */     return new Point2D(this.xc, this.yc);
/*      */   }
/*      */   
/*      */   public Vector2D vector1() {
/*  484 */     return new Vector2D(Math.cos(this.theta), Math.sin(this.theta));
/*      */   }
/*      */   
/*      */   public Vector2D vector2() {
/*  492 */     if (this.direct)
/*  493 */       return new Vector2D(-Math.sin(this.theta), Math.cos(this.theta)); 
/*  495 */     return new Vector2D(Math.sin(this.theta), -Math.cos(this.theta));
/*      */   }
/*      */   
/*      */   public double angle() {
/*  502 */     return this.theta;
/*      */   }
/*      */   
/*      */   public Point2D focus1() {
/*  510 */     return new Point2D(this.xc, this.yc);
/*      */   }
/*      */   
/*      */   public Point2D focus2() {
/*  518 */     return new Point2D(this.xc, this.yc);
/*      */   }
/*      */   
/*      */   public boolean isCircle() {
/*  522 */     return true;
/*      */   }
/*      */   
/*      */   public Ellipse2D asEllipse() {
/*  530 */     return new Ellipse2D(this.xc, this.yc, this.r, this.r, this.theta, this.direct);
/*      */   }
/*      */   
/*      */   public Conic2D.Type conicType() {
/*  538 */     return Conic2D.Type.CIRCLE;
/*      */   }
/*      */   
/*      */   public double[] conicCoefficients() {
/*  549 */     return new double[] { 1.0D, 0.0D, 1.0D, -2.0D * this.xc, -2.0D * this.yc, 
/*  551 */         this.xc * this.xc + this.yc * this.yc - this.r * this.r };
/*      */   }
/*      */   
/*      */   public double eccentricity() {
/*  558 */     return 0.0D;
/*      */   }
/*      */   
/*      */   public CirculinearDomain2D buffer(double dist) {
/*  569 */     BufferCalculator bc = BufferCalculator.getDefaultInstance();
/*  570 */     return bc.computeBuffer((CirculinearCurve2D)this, dist);
/*      */   }
/*      */   
/*      */   public Circle2D parallel(double d) {
/*  579 */     double rp = Math.max(this.direct ? (this.r + d) : (this.r - d), 0.0D);
/*  580 */     return new Circle2D(this.xc, this.yc, rp, this.direct);
/*      */   }
/*      */   
/*      */   public double length() {
/*  585 */     return 6.283185307179586D * this.r;
/*      */   }
/*      */   
/*      */   public double length(double pos) {
/*  593 */     return pos * this.r;
/*      */   }
/*      */   
/*      */   public double position(double length) {
/*  600 */     return length / this.r;
/*      */   }
/*      */   
/*      */   public CircleLine2D transform(CircleInversion2D inv) {
/*  608 */     Point2D center = inv.center();
/*  609 */     Point2D c1 = center();
/*  612 */     if (center.distance(c1) < 1.0E-12D) {
/*  613 */       double r0 = inv.radius();
/*  614 */       double r2 = r0 * r0 / this.r;
/*  615 */       return new Circle2D(center, r2, this.direct);
/*      */     } 
/*  619 */     StraightLine2D centersLine = new StraightLine2D(center, c1);
/*  622 */     Collection<Point2D> points = intersections((LinearShape2D)centersLine);
/*  623 */     if (points.size() < 2)
/*  624 */       throw new RuntimeException("Intersection of circle with line through center has less than 2 points"); 
/*  626 */     Iterator<Point2D> iter = points.iterator();
/*  627 */     Point2D p1 = iter.next();
/*  628 */     Point2D p2 = iter.next();
/*  632 */     if (distance(center) < 1.0E-12D) {
/*  634 */       double dist1 = center.distance(p1);
/*  635 */       double dist2 = center.distance(p2);
/*  636 */       Point2D p0 = (dist1 < dist2) ? p2 : p1;
/*  639 */       p0 = p0.transform(inv);
/*  640 */       return (CircleLine2D)StraightLine2D.createPerpendicular((LinearShape2D)centersLine, p0);
/*      */     } 
/*  647 */     p1 = p1.transform(inv);
/*  648 */     p2 = p2.transform(inv);
/*  651 */     double diam = p1.distance(p2);
/*  652 */     c1 = Point2D.midPoint(p1, p2);
/*  655 */     int i = (isDirect() ? 0 : 1) ^ isInside(inv.center());
/*  656 */     return new Circle2D(c1, diam / 2.0D, i);
/*      */   }
/*      */   
/*      */   public CirculinearDomain2D domain() {
/*  664 */     return (CirculinearDomain2D)new GenericCirculinearDomain2D((CirculinearBoundary2D)this);
/*      */   }
/*      */   
/*      */   public void fill(Graphics2D g2) {
/*  669 */     Ellipse2D.Double ellipse = 
/*  670 */       new Ellipse2D.Double(this.xc - this.r, this.yc - this.r, 2.0D * this.r, 2.0D * this.r);
/*  673 */     AffineTransform trans = 
/*  674 */       AffineTransform.getRotateInstance(this.theta, this.xc, this.yc);
/*  675 */     Shape shape = trans.createTransformedShape(ellipse);
/*  678 */     g2.fill(shape);
/*      */   }
/*      */   
/*      */   public double windingAngle(Point2D point) {
/*  690 */     if (signedDistance(point) > 0.0D)
/*  691 */       return 0.0D; 
/*  693 */     return this.direct ? 6.283185307179586D : -6.283185307179586D;
/*      */   }
/*      */   
/*      */   public Vector2D tangent(double t) {
/*  701 */     if (!this.direct)
/*  702 */       t = -t; 
/*  703 */     double cot = Math.cos(this.theta);
/*  704 */     double sit = Math.sin(this.theta);
/*  705 */     double cost = Math.cos(t);
/*  706 */     double sint = Math.sin(t);
/*  708 */     if (this.direct)
/*  709 */       return new Vector2D(
/*  710 */           -this.r * sint * cot - this.r * cost * sit, 
/*  711 */           -this.r * sint * sit + this.r * cost * cot); 
/*  713 */     return new Vector2D(
/*  714 */         this.r * sint * cot + this.r * cost * sit, 
/*  715 */         this.r * sint * sit - this.r * cost * cot);
/*      */   }
/*      */   
/*      */   public double curvature(double t) {
/*  723 */     double k = 1.0D / this.r;
/*  724 */     return this.direct ? k : -k;
/*      */   }
/*      */   
/*      */   public Collection<? extends Circle2D> smoothPieces() {
/*  735 */     return wrapCurve((ContinuousCurve2D)this);
/*      */   }
/*      */   
/*      */   public boolean isClosed() {
/*  742 */     return true;
/*      */   }
/*      */   
/*      */   public LinearRing2D asPolyline(int n) {
/*  749 */     return asPolylineClosed(n);
/*      */   }
/*      */   
/*      */   public boolean isInside(Point2D point) {
/*  762 */     double xp = (point.x() - this.xc) / this.r;
/*  763 */     double yp = (point.y() - this.yc) / this.r;
/*  764 */     return ((xp * xp + yp * yp < 1.0D)) ^ (!this.direct);
/*      */   }
/*      */   
/*      */   public double signedDistance(Point2D point) {
/*  768 */     return signedDistance(point.x(), point.y());
/*      */   }
/*      */   
/*      */   public double signedDistance(double x, double y) {
/*  772 */     if (this.direct)
/*  773 */       return Point2D.distance(this.xc, this.yc, x, y) - this.r; 
/*  775 */     return this.r - Point2D.distance(this.xc, this.yc, x, y);
/*      */   }
/*      */   
/*      */   public boolean isBounded() {
/*  783 */     return true;
/*      */   }
/*      */   
/*      */   public boolean isEmpty() {
/*  788 */     return false;
/*      */   }
/*      */   
/*      */   public double t0() {
/*  795 */     return 0.0D;
/*      */   }
/*      */   
/*      */   @Deprecated
/*      */   public double getT0() {
/*  803 */     return t0();
/*      */   }
/*      */   
/*      */   public double t1() {
/*  810 */     return 6.283185307179586D;
/*      */   }
/*      */   
/*      */   @Deprecated
/*      */   public double getT1() {
/*  818 */     return t1();
/*      */   }
/*      */   
/*      */   public Point2D point(double t) {
/*  827 */     double angle = this.theta + t;
/*  828 */     if (!this.direct)
/*  829 */       angle = this.theta - t; 
/*  830 */     return new Point2D(this.xc + this.r * Math.cos(angle), this.yc + this.r * Math.sin(angle));
/*      */   }
/*      */   
/*      */   public Point2D firstPoint() {
/*  839 */     return new Point2D(this.xc + this.r * Math.cos(this.theta), this.yc + this.r * Math.sin(this.theta));
/*      */   }
/*      */   
/*      */   public Point2D lastPoint() {
/*  848 */     return new Point2D(this.xc + this.r * Math.cos(this.theta), this.yc + this.r * Math.sin(this.theta));
/*      */   }
/*      */   
/*      */   public double position(Point2D point) {
/*  852 */     double angle = Angle2D.horizontalAngle(this.xc, this.yc, point.x(), point.y());
/*  853 */     if (this.direct)
/*  854 */       return Angle2D.formatAngle(angle - this.theta); 
/*  856 */     return Angle2D.formatAngle(this.theta - angle);
/*      */   }
/*      */   
/*      */   public double project(Point2D point) {
/*  864 */     double xp = point.x() - this.xc;
/*  865 */     double yp = point.y() - this.yc;
/*  868 */     return Angle2D.horizontalAngle(xp, yp);
/*      */   }
/*      */   
/*      */   public Circle2D reverse() {
/*  876 */     return new Circle2D(this.xc, this.yc, this.r, !this.direct);
/*      */   }
/*      */   
/*      */   public CircleArc2D subCurve(double t0, double t1) {
/*      */     double startAngle;
/*      */     double extent;
/*  884 */     if (this.direct) {
/*  885 */       startAngle = t0;
/*  886 */       extent = Angle2D.formatAngle(t1 - t0);
/*      */     } else {
/*  888 */       extent = -Angle2D.formatAngle(t1 - t0);
/*  889 */       startAngle = Angle2D.formatAngle(-t0);
/*      */     } 
/*  891 */     return new CircleArc2D(this, startAngle, extent);
/*      */   }
/*      */   
/*      */   public Collection<? extends Circle2D> continuousCurves() {
/*  895 */     return wrapCurve((ContinuousCurve2D)this);
/*      */   }
/*      */   
/*      */   public double distance(Point2D point) {
/*  902 */     return Math.abs(Point2D.distance(this.xc, this.yc, point.x(), point.y()) - this.r);
/*      */   }
/*      */   
/*      */   public double distance(double x, double y) {
/*  906 */     return Math.abs(Point2D.distance(this.xc, this.yc, x, y) - this.r);
/*      */   }
/*      */   
/*      */   public Collection<Point2D> intersections(LinearShape2D line) {
/*  916 */     return lineCircleIntersections(line, this);
/*      */   }
/*      */   
/*      */   public CurveSet2D<? extends CircularShape2D> clip(Box2D box) {
/*  927 */     CurveSet2D<SmoothCurve2D> set = 
/*  928 */       Curves2D.clipSmoothCurve((SmoothCurve2D)this, box);
/*  931 */     CurveArray2D<CircularShape2D> result = 
/*  932 */       new CurveArray2D(set.size());
/*  935 */     for (Curve2D curve : set.curves()) {
/*  936 */       if (curve instanceof CircleArc2D)
/*  937 */         result.add(curve); 
/*  938 */       if (curve instanceof Circle2D)
/*  939 */         result.add(curve); 
/*      */     } 
/*  941 */     return (CurveSet2D<? extends CircularShape2D>)result;
/*      */   }
/*      */   
/*      */   public boolean contains(Point2D p) {
/*  952 */     return contains(p.x(), p.y());
/*      */   }
/*      */   
/*      */   public boolean contains(double x, double y) {
/*  959 */     return (Math.abs(distance(x, y)) <= 1.0E-12D);
/*      */   }
/*      */   
/*      */   public GeneralPath appendPath(GeneralPath path) {
/*  963 */     double cot = Math.cos(this.theta);
/*  964 */     double sit = Math.sin(this.theta);
/*  967 */     if (this.direct) {
/*  969 */       for (double t = 0.1D; t < 6.283185307179586D; t += 0.1D) {
/*  970 */         double cost = Math.cos(t);
/*  971 */         double sint = Math.sin(t);
/*  972 */         path.lineTo(
/*  973 */             (float)(this.xc + this.r * cost * cot - this.r * sint * sit), 
/*  974 */             (float)(this.yc + this.r * cost * sit + this.r * sint * cot));
/*      */       } 
/*      */     } else {
/*  978 */       for (double t = 0.1D; t < 6.283185307179586D; t += 0.1D) {
/*  979 */         double cost = Math.cos(t);
/*  980 */         double sint = Math.sin(t);
/*  981 */         path.lineTo(
/*  982 */             (float)(this.xc + this.r * cost * cot + this.r * sint * sit), 
/*  983 */             (float)(this.yc + this.r * cost * sit - this.r * sint * cot));
/*      */       } 
/*      */     } 
/*  988 */     path.lineTo((float)(this.xc + this.r * cot), (float)(this.yc + this.r * sit));
/*  990 */     return path;
/*      */   }
/*      */   
/*      */   public void draw(Graphics2D g2) {
/*  994 */     Ellipse2D.Double ellipse = 
/*  995 */       new Ellipse2D.Double(this.xc - this.r, this.yc - this.r, 2.0D * this.r, 2.0D * this.r);
/*  996 */     g2.draw(ellipse);
/*      */   }
/*      */   
/*      */   public boolean almostEquals(GeometricObject2D obj, double eps) {
/* 1006 */     if (!(obj instanceof Circle2D))
/* 1007 */       return false; 
/* 1009 */     Circle2D circle = (Circle2D)obj;
/* 1011 */     if (Math.abs(circle.xc - this.xc) > eps)
/* 1012 */       return false; 
/* 1013 */     if (Math.abs(circle.yc - this.yc) > eps)
/* 1014 */       return false; 
/* 1015 */     if (Math.abs(circle.r - this.r) > eps)
/* 1016 */       return false; 
/* 1017 */     if (circle.direct != this.direct)
/* 1018 */       return false; 
/* 1019 */     return true;
/*      */   }
/*      */   
/*      */   public Box2D boundingBox() {
/* 1026 */     return new Box2D(this.xc - this.r, this.xc + this.r, this.yc - this.r, this.yc + this.r);
/*      */   }
/*      */   
/*      */   public EllipseShape2D transform(AffineTransform2D trans) {
/* 1038 */     if (!AffineTransform2D.isSimilarity(trans))
/* 1039 */       return asEllipse().transform(trans); 
/* 1043 */     Point2D center = center().transform(trans);
/* 1044 */     Point2D p1 = firstPoint().transform(trans);
/* 1046 */     int i = (this.direct ? 0 : 1) ^ trans.isDirect();
/* 1047 */     Circle2D result = new Circle2D(center, center.distance(p1), i);
/* 1048 */     return result;
/*      */   }
/*      */   
/*      */   public String toString() {
/* 1057 */     return String.format(Locale.US, 
/* 1058 */         "Circle2D(%7.2f,%7.2f,%7.2f,%s)", new Object[] { Double.valueOf(this.xc), Double.valueOf(this.yc), Double.valueOf(this.r), this.direct ? "true" : "false" });
/*      */   }
/*      */   
/*      */   public boolean equals(Object obj) {
/* 1064 */     if (this == obj)
/* 1065 */       return true; 
/* 1066 */     if (obj instanceof Circle2D) {
/* 1067 */       Circle2D that = (Circle2D)obj;
/* 1070 */       if (!EqualUtils.areEqual(this.xc, that.xc))
/* 1071 */         return false; 
/* 1072 */       if (!EqualUtils.areEqual(this.yc, that.yc))
/* 1073 */         return false; 
/* 1074 */       if (!EqualUtils.areEqual(this.r, that.r))
/* 1075 */         return false; 
/* 1076 */       if (this.direct != that.direct)
/* 1077 */         return false; 
/* 1079 */       return true;
/*      */     } 
/* 1081 */     return super.equals(obj);
/*      */   }
/*      */   
/*      */   public Circle2D clone() {
/* 1086 */     return new Circle2D(this.xc, this.yc, this.r, this.direct);
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\conic\Circle2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
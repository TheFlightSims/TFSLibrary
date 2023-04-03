/*     */ package math.geom2d.conic;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.util.Collection;
/*     */ import java.util.Locale;
/*     */ import math.geom2d.AffineTransform2D;
/*     */ import math.geom2d.Angle2D;
/*     */ import math.geom2d.Box2D;
/*     */ import math.geom2d.GeometricObject2D;
/*     */ import math.geom2d.Point2D;
/*     */ import math.geom2d.Shape2D;
/*     */ import math.geom2d.Vector2D;
/*     */ import math.geom2d.circulinear.CircleLine2D;
/*     */ import math.geom2d.circulinear.CirculinearContinuousCurve2D;
/*     */ import math.geom2d.circulinear.CirculinearCurve2D;
/*     */ import math.geom2d.circulinear.CirculinearDomain2D;
/*     */ import math.geom2d.circulinear.CirculinearElement2D;
/*     */ import math.geom2d.circulinear.CirculinearShape2D;
/*     */ import math.geom2d.circulinear.buffer.BufferCalculator;
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
/*     */ import math.geom2d.line.LineSegment2D;
/*     */ import math.geom2d.line.LinearShape2D;
/*     */ import math.geom2d.line.Ray2D;
/*     */ import math.geom2d.line.StraightLine2D;
/*     */ import math.geom2d.polygon.LinearCurve2D;
/*     */ import math.geom2d.polygon.Polyline2D;
/*     */ import math.geom2d.transform.CircleInversion2D;
/*     */ import math.utils.EqualUtils;
/*     */ 
/*     */ public class CircleArc2D extends AbstractSmoothCurve2D implements EllipseArcShape2D, CircularShape2D, CirculinearElement2D, Cloneable {
/*     */   protected Circle2D circle;
/*     */   
/*     */   @Deprecated
/*     */   public static CircleArc2D create(Circle2D support, double startAngle, double angleExtent) {
/*  73 */     return new CircleArc2D(support, startAngle, angleExtent);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static CircleArc2D create(Circle2D support, double startAngle, double endAngle, boolean direct) {
/*  82 */     return new CircleArc2D(support, startAngle, endAngle, direct);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static CircleArc2D create(Point2D center, double radius, double startAngle, double angleExtent) {
/*  91 */     return new CircleArc2D(center, radius, startAngle, angleExtent);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static CircleArc2D create(Point2D center, double radius, double startAngle, double endAngle, boolean direct) {
/* 100 */     return new CircleArc2D(center, radius, startAngle, endAngle, direct);
/*     */   }
/*     */   
/* 111 */   protected double startAngle = 0.0D;
/*     */   
/* 114 */   protected double angleExtent = Math.PI;
/*     */   
/*     */   public CircleArc2D() {
/* 125 */     this(0.0D, 0.0D, 1.0D, 0.0D, 1.5707963267948966D);
/*     */   }
/*     */   
/*     */   public CircleArc2D(Circle2D circle, double startAngle, double angleExtent) {
/* 134 */     this(circle.xc, circle.yc, circle.r, startAngle, angleExtent);
/*     */   }
/*     */   
/*     */   public CircleArc2D(Circle2D circle, double startAngle, double endAngle, boolean direct) {
/* 143 */     this(circle.xc, circle.yc, circle.r, startAngle, endAngle, direct);
/*     */   }
/*     */   
/*     */   public CircleArc2D(Point2D center, double radius, double startAngle, double angleExtent) {
/* 151 */     this(center.x(), center.y(), radius, startAngle, angleExtent);
/*     */   }
/*     */   
/*     */   public CircleArc2D(Point2D center, double radius, double start, double end, boolean direct) {
/* 160 */     this(center.x(), center.y(), radius, start, end, direct);
/*     */   }
/*     */   
/*     */   public CircleArc2D(double xc, double yc, double r, double startAngle, double endAngle, boolean direct) {
/* 171 */     this.circle = new Circle2D(xc, yc, r);
/* 172 */     this.startAngle = startAngle;
/* 173 */     this.angleExtent = endAngle;
/* 174 */     this.angleExtent = Angle2D.formatAngle(endAngle - startAngle);
/* 175 */     if (!direct)
/* 176 */       this.angleExtent -= 6.283185307179586D; 
/*     */   }
/*     */   
/*     */   public CircleArc2D(double xc, double yc, double r, double start, double extent) {
/* 182 */     this.circle = new Circle2D(xc, yc, r);
/* 183 */     this.startAngle = start;
/* 184 */     this.angleExtent = extent;
/*     */   }
/*     */   
/*     */   public boolean isDirect() {
/* 195 */     return (this.angleExtent >= 0.0D);
/*     */   }
/*     */   
/*     */   public double getStartAngle() {
/* 199 */     return this.startAngle;
/*     */   }
/*     */   
/*     */   public double getAngleExtent() {
/* 203 */     return this.angleExtent;
/*     */   }
/*     */   
/*     */   public boolean containsAngle(double angle) {
/* 207 */     return Angle2D.containsAngle(
/* 208 */         this.startAngle, this.startAngle + this.angleExtent, angle, (this.angleExtent >= 0.0D));
/*     */   }
/*     */   
/*     */   public double getAngle(double position) {
/* 213 */     if (position < 0.0D)
/* 214 */       position = 0.0D; 
/* 215 */     if (position > Math.abs(this.angleExtent))
/* 216 */       position = Math.abs(this.angleExtent); 
/* 217 */     if (this.angleExtent < 0.0D)
/* 218 */       position = -position; 
/* 219 */     return Angle2D.formatAngle(this.startAngle + position);
/*     */   }
/*     */   
/*     */   private double positionToAngle(double t) {
/* 226 */     if (t > Math.abs(this.angleExtent))
/* 227 */       t = Math.abs(this.angleExtent); 
/* 228 */     if (t < 0.0D)
/* 229 */       t = 0.0D; 
/* 230 */     if (this.angleExtent < 0.0D)
/* 231 */       t = -t; 
/* 232 */     t += this.startAngle;
/* 233 */     return t;
/*     */   }
/*     */   
/*     */   public Circle2D supportingCircle() {
/* 243 */     return this.circle;
/*     */   }
/*     */   
/*     */   public CirculinearDomain2D buffer(double dist) {
/* 253 */     BufferCalculator bc = BufferCalculator.getDefaultInstance();
/* 254 */     return bc.computeBuffer((CirculinearCurve2D)this, dist);
/*     */   }
/*     */   
/*     */   public CircleArc2D parallel(double dist) {
/* 262 */     double r = this.circle.radius();
/* 263 */     double r2 = Math.max((this.angleExtent > 0.0D) ? (r + dist) : (r - dist), 0.0D);
/* 264 */     return new CircleArc2D(this.circle.center(), r2, this.startAngle, this.angleExtent);
/*     */   }
/*     */   
/*     */   public double length() {
/* 268 */     return this.circle.radius() * Math.abs(this.angleExtent);
/*     */   }
/*     */   
/*     */   public double length(double pos) {
/* 277 */     return pos * this.circle.radius();
/*     */   }
/*     */   
/*     */   public double position(double length) {
/* 286 */     return length / this.circle.radius();
/*     */   }
/*     */   
/*     */   public CirculinearElement2D transform(CircleInversion2D inv) {
/* 294 */     CircleLine2D circleLine2D = this.circle.transform(inv);
/* 297 */     Point2D p1 = firstPoint().transform(inv);
/* 298 */     Point2D p2 = lastPoint().transform(inv);
/* 300 */     if (circleLine2D instanceof Circle2D) {
/* 301 */       Circle2D circle2 = (Circle2D)circleLine2D;
/* 302 */       Point2D center = circle2.center();
/* 304 */       return new CircleArc2D(
/* 305 */           circle2.center(), circle2.radius(), 
/* 306 */           Angle2D.horizontalAngle(center, p1), 
/* 307 */           Angle2D.horizontalAngle(center, p2), (
/* 308 */           !isDirect()) ^ circle2.isDirect());
/*     */     } 
/* 310 */     if (circleLine2D instanceof StraightLine2D)
/* 312 */       return (CirculinearElement2D)new LineSegment2D(p1, p2); 
/* 315 */     System.err.println(
/* 316 */         "CircleArc2D.transform(): error in transforming circle by inversion");
/* 317 */     return null;
/*     */   }
/*     */   
/*     */   public double windingAngle(Point2D point) {
/* 325 */     Point2D p1 = firstPoint();
/* 326 */     Point2D p2 = lastPoint();
/* 329 */     double angle1 = Angle2D.horizontalAngle(point, p1);
/* 330 */     double angle2 = Angle2D.horizontalAngle(point, p2);
/* 332 */     boolean b1 = (new StraightLine2D(p1, p2)).isInside(point);
/* 333 */     boolean b2 = this.circle.isInside(point);
/* 335 */     if (this.angleExtent > 0.0D) {
/* 336 */       if (b1 || b2) {
/* 337 */         if (angle2 > angle1)
/* 338 */           return angle2 - angle1; 
/* 340 */         return 6.283185307179586D - angle1 + angle2;
/*     */       } 
/* 342 */       if (angle2 > angle1)
/* 343 */         return angle2 - angle1 - 6.283185307179586D; 
/* 345 */       return angle2 - angle1;
/*     */     } 
/* 348 */     if (!b1 || b2) {
/* 349 */       if (angle1 > angle2)
/* 350 */         return angle2 - angle1; 
/* 352 */       return angle2 - angle1 - 6.283185307179586D;
/*     */     } 
/* 354 */     if (angle1 > angle2)
/* 355 */       return angle2 - angle1 + 6.283185307179586D; 
/* 357 */     return angle2 - angle1;
/*     */   }
/*     */   
/*     */   public boolean isInside(Point2D point) {
/* 363 */     return (signedDistance(point.x(), point.y()) < 0.0D);
/*     */   }
/*     */   
/*     */   public double signedDistance(Point2D p) {
/* 367 */     return signedDistance(p.x(), p.y());
/*     */   }
/*     */   
/*     */   public double signedDistance(double x, double y) {
/* 371 */     double dist = distance(x, y);
/* 372 */     Point2D point = new Point2D(x, y);
/* 374 */     boolean direct = (this.angleExtent > 0.0D);
/* 375 */     boolean inCircle = this.circle.isInside(point);
/* 376 */     if (inCircle)
/* 377 */       return direct ? -dist : dist; 
/* 379 */     Point2D p1 = this.circle.point(this.startAngle);
/* 380 */     Point2D p2 = this.circle.point(this.startAngle + this.angleExtent);
/* 381 */     boolean onLeft = (new StraightLine2D(p1, p2)).isInside(point);
/* 383 */     if (direct && !onLeft)
/* 384 */       return dist; 
/* 385 */     if (!direct && onLeft)
/* 386 */       return -dist; 
/* 388 */     Vector2D tangent = this.circle.tangent(this.startAngle);
/* 389 */     boolean left1 = (new Ray2D(p1, tangent)).isInside(point);
/* 390 */     if (direct && !left1)
/* 391 */       return dist; 
/* 392 */     if (!direct && left1)
/* 393 */       return -dist; 
/* 395 */     tangent = this.circle.tangent(this.startAngle + this.angleExtent);
/* 396 */     boolean left2 = (new Ray2D(p2, tangent)).isInside(point);
/* 397 */     if (direct && !left2)
/* 398 */       return dist; 
/* 399 */     if (!direct && left2)
/* 400 */       return -dist; 
/* 402 */     if (direct)
/* 403 */       return -dist; 
/* 405 */     return dist;
/*     */   }
/*     */   
/*     */   public Vector2D tangent(double t) {
/* 412 */     t = positionToAngle(t);
/* 414 */     double r = this.circle.radius();
/* 415 */     if (this.angleExtent > 0.0D)
/* 416 */       return new Vector2D(-r * Math.sin(t), r * Math.cos(t)); 
/* 418 */     return new Vector2D(r * Math.sin(t), -r * Math.cos(t));
/*     */   }
/*     */   
/*     */   public double curvature(double t) {
/* 427 */     double kappa = this.circle.curvature(t);
/* 428 */     return isDirect() ? kappa : -kappa;
/*     */   }
/*     */   
/*     */   public Collection<? extends CircleArc2D> smoothPieces() {
/* 439 */     return wrapCurve((ContinuousCurve2D)this);
/*     */   }
/*     */   
/*     */   public boolean isClosed() {
/* 446 */     return false;
/*     */   }
/*     */   
/*     */   public Polyline2D asPolyline(int n) {
/* 455 */     double dt = Math.abs(this.angleExtent) / n;
/* 459 */     Point2D[] points = new Point2D[n + 1];
/* 460 */     for (int i = 0; i < n + 1; i++)
/* 461 */       points[i] = point(i * dt); 
/* 463 */     return new Polyline2D(points);
/*     */   }
/*     */   
/*     */   public double t0() {
/* 474 */     return 0.0D;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public double getT0() {
/* 482 */     return 0.0D;
/*     */   }
/*     */   
/*     */   public double t1() {
/* 490 */     return Math.abs(this.angleExtent);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public double getT1() {
/* 498 */     return Math.abs(this.angleExtent);
/*     */   }
/*     */   
/*     */   public Point2D point(double t) {
/* 505 */     t = positionToAngle(t);
/* 506 */     return this.circle.point(t);
/*     */   }
/*     */   
/*     */   public double position(Point2D point) {
/* 513 */     double angle = Angle2D.horizontalAngle(this.circle.center(), point);
/* 514 */     if (containsAngle(angle)) {
/* 515 */       if (this.angleExtent > 0.0D)
/* 516 */         return Angle2D.formatAngle(angle - this.startAngle); 
/* 518 */       return Angle2D.formatAngle(this.startAngle - angle);
/*     */     } 
/* 521 */     return 
/* 522 */       (firstPoint().distance(point) < lastPoint().distance(point)) ? 0.0D : Math.abs(this.angleExtent);
/*     */   }
/*     */   
/*     */   public Collection<Point2D> intersections(LinearShape2D line) {
/* 532 */     return Circle2D.lineCircleIntersections(line, this);
/*     */   }
/*     */   
/*     */   public double project(Point2D point) {
/* 536 */     double angle = this.circle.project(point);
/* 539 */     if (Angle2D.containsAngle(this.startAngle, this.startAngle + this.angleExtent, angle, 
/* 540 */         (this.angleExtent > 0.0D))) {
/* 541 */       if (this.angleExtent > 0.0D)
/* 542 */         return Angle2D.formatAngle(angle - this.startAngle); 
/* 544 */       return Angle2D.formatAngle(this.startAngle - angle);
/*     */     } 
/* 547 */     Point2D p1 = firstPoint();
/* 548 */     Point2D p2 = lastPoint();
/* 549 */     if (p1.distance(point) < p2.distance(point))
/* 550 */       return 0.0D; 
/* 552 */     return Math.abs(this.angleExtent);
/*     */   }
/*     */   
/*     */   public CircleArc2D subCurve(double t0, double t1) {
/* 561 */     if (this.angleExtent > 0.0D) {
/* 562 */       t0 = Angle2D.formatAngle(this.startAngle + t0);
/* 563 */       t1 = Angle2D.formatAngle(this.startAngle + t1);
/*     */     } else {
/* 565 */       t0 = Angle2D.formatAngle(this.startAngle - t0);
/* 566 */       t1 = Angle2D.formatAngle(this.startAngle - t1);
/*     */     } 
/* 570 */     if (!Angle2D.containsAngle(this.startAngle, this.startAngle + this.angleExtent, t0, 
/* 571 */         (this.angleExtent > 0.0D)))
/* 572 */       t0 = this.startAngle; 
/* 573 */     if (!Angle2D.containsAngle(this.startAngle, this.startAngle + this.angleExtent, t1, 
/* 574 */         (this.angleExtent > 0.0D)))
/* 575 */       t1 = Angle2D.formatAngle(this.startAngle + this.angleExtent); 
/* 578 */     return new CircleArc2D(this.circle, t0, t1, (this.angleExtent > 0.0D));
/*     */   }
/*     */   
/*     */   public CircleArc2D reverse() {
/* 586 */     double newStart = Angle2D.formatAngle(this.startAngle + this.angleExtent);
/* 587 */     return new CircleArc2D(this.circle, newStart, -this.angleExtent);
/*     */   }
/*     */   
/*     */   public Collection<? extends CircleArc2D> continuousCurves() {
/* 594 */     return wrapCurve((ContinuousCurve2D)this);
/*     */   }
/*     */   
/*     */   public double distance(Point2D p) {
/* 602 */     return distance(p.x(), p.y());
/*     */   }
/*     */   
/*     */   public double distance(double x, double y) {
/* 606 */     double angle = Angle2D.horizontalAngle(this.circle.xc, this.circle.yc, x, y);
/* 608 */     if (containsAngle(angle))
/* 609 */       return Math.abs(Point2D.distance(this.circle.xc, this.circle.yc, x, y) - this.circle.r); 
/* 611 */     return Math.min(firstPoint().distance(x, y), lastPoint().distance(x, y));
/*     */   }
/*     */   
/*     */   public boolean isBounded() {
/* 616 */     return true;
/*     */   }
/*     */   
/*     */   public CurveSet2D<CircleArc2D> clip(Box2D box) {
/* 626 */     CurveSet2D<SmoothCurve2D> set = Curves2D.clipSmoothCurve((SmoothCurve2D)this, box);
/* 629 */     CurveArray2D<CircleArc2D> result = 
/* 630 */       new CurveArray2D(set.size());
/* 633 */     for (Curve2D curve : set.curves()) {
/* 634 */       if (curve instanceof CircleArc2D)
/* 635 */         result.add(curve); 
/*     */     } 
/* 637 */     return (CurveSet2D<CircleArc2D>)result;
/*     */   }
/*     */   
/*     */   public EllipseArcShape2D transform(AffineTransform2D trans) {
/* 648 */     if (!AffineTransform2D.isSimilarity(trans)) {
/* 649 */       Ellipse2D ellipse = this.circle.asEllipse();
/* 650 */       EllipseArc2D arc = new EllipseArc2D(ellipse, this.startAngle, 
/* 651 */           this.angleExtent);
/* 652 */       return arc.transform(trans);
/*     */     } 
/* 658 */     Point2D center = this.circle.center();
/* 659 */     Point2D point1 = firstPoint();
/* 660 */     Point2D point2 = lastPoint();
/* 663 */     center = center.transform(trans);
/* 664 */     point1 = point1.transform(trans);
/* 665 */     point2 = point2.transform(trans);
/* 668 */     double angle1 = Angle2D.horizontalAngle(center, point1);
/* 669 */     double angle2 = Angle2D.horizontalAngle(center, point2);
/* 672 */     double[] coefs = trans.coefficients();
/* 673 */     double factor = Math.hypot(coefs[0], coefs[3]);
/* 676 */     double xc = center.x(), yc = center.y();
/* 677 */     double r2 = this.circle.radius() * factor;
/* 678 */     double startAngle = angle1;
/* 679 */     double angleExtent = Angle2D.formatAngle(angle2 - angle1);
/* 681 */     boolean b1 = AffineTransform2D.isDirect(trans);
/* 682 */     boolean b2 = isDirect();
/* 683 */     if ((b1 && !b2) || (!b1 && b2))
/* 684 */       angleExtent -= 6.283185307179586D; 
/* 687 */     return new CircleArc2D(xc, yc, r2, startAngle, angleExtent);
/*     */   }
/*     */   
/*     */   public boolean contains(Point2D p) {
/* 691 */     return contains(p.x(), p.y());
/*     */   }
/*     */   
/*     */   public boolean contains(double x, double y) {
/* 696 */     double r = this.circle.radius();
/* 697 */     if (Math.abs(Point2D.distance(this.circle.xc, this.circle.yc, x, y) - r) > 1.0E-12D)
/* 698 */       return false; 
/* 701 */     double angle = Angle2D.horizontalAngle(this.circle.xc, this.circle.yc, x, y);
/* 704 */     return containsAngle(angle);
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 711 */     return false;
/*     */   }
/*     */   
/*     */   public Box2D boundingBox() {
/* 717 */     Point2D p0 = firstPoint();
/* 718 */     Point2D p1 = lastPoint();
/* 721 */     double x0 = p0.x();
/* 722 */     double y0 = p0.y();
/* 723 */     double x1 = p1.x();
/* 724 */     double y1 = p1.y();
/* 727 */     double xmin = Math.min(x0, x1);
/* 728 */     double xmax = Math.max(x0, x1);
/* 729 */     double ymin = Math.min(y0, y1);
/* 730 */     double ymax = Math.max(y0, y1);
/* 733 */     Point2D center = this.circle.center();
/* 734 */     double xc = center.x();
/* 735 */     double yc = center.y();
/* 736 */     double endAngle = this.startAngle + this.angleExtent;
/* 737 */     boolean direct = (this.angleExtent >= 0.0D);
/* 740 */     if (Angle2D.containsAngle(this.startAngle, endAngle, 1.5707963267948966D + this.circle.theta, direct))
/* 741 */       ymax = Math.max(ymax, yc + this.circle.r); 
/* 742 */     if (Angle2D.containsAngle(this.startAngle, endAngle, 4.71238898038469D + 
/* 743 */         this.circle.theta, direct))
/* 744 */       ymin = Math.min(ymin, yc - this.circle.r); 
/* 745 */     if (Angle2D.containsAngle(this.startAngle, endAngle, this.circle.theta, direct))
/* 746 */       xmax = Math.max(xmax, xc + this.circle.r); 
/* 747 */     if (Angle2D.containsAngle(this.startAngle, endAngle, Math.PI + this.circle.theta, 
/* 748 */         direct))
/* 749 */       xmin = Math.min(xmin, xc - this.circle.r); 
/* 752 */     return new Box2D(xmin, xmax, ymin, ymax);
/*     */   }
/*     */   
/*     */   public GeneralPath appendPath(GeneralPath path) {
/* 757 */     int nSeg = (int)Math.ceil(Math.abs(this.angleExtent) / 1.5707963267948966D);
/* 758 */     nSeg = Math.min(nSeg, 4);
/* 761 */     double ext = this.angleExtent / nSeg;
/* 764 */     double k = btan(Math.abs(ext));
/* 766 */     for (int i = 0; i < nSeg; i++) {
/* 768 */       double ti0 = Math.abs(i * ext);
/* 769 */       double ti1 = Math.abs((i + 1) * ext);
/* 772 */       Point2D p1 = point(ti0);
/* 773 */       Point2D p2 = point(ti1);
/* 776 */       Vector2D v1 = tangent(ti0).times(k);
/* 777 */       Vector2D v2 = tangent(ti1).times(k);
/* 780 */       path.curveTo(
/* 781 */           p1.x() + v1.x(), p1.y() + v1.y(), 
/* 782 */           p2.x() - v2.x(), p2.y() - v2.y(), 
/* 783 */           p2.x(), p2.y());
/*     */     } 
/* 785 */     return path;
/*     */   }
/*     */   
/*     */   public GeneralPath getGeneralPath() {
/* 790 */     GeneralPath path = new GeneralPath();
/* 793 */     Point2D point = firstPoint();
/* 794 */     path.moveTo((float)point.x(), (float)point.y());
/* 797 */     path = appendPath(path);
/* 800 */     return path;
/*     */   }
/*     */   
/*     */   public void draw(Graphics2D g2) {
/* 805 */     g2.draw(getGeneralPath());
/*     */   }
/*     */   
/*     */   private static double btan(double increment) {
/* 888 */     increment /= 2.0D;
/* 889 */     return 1.3333333333333333D * Math.sin(increment) / (1.0D + Math.cos(increment));
/*     */   }
/*     */   
/*     */   public boolean almostEquals(GeometricObject2D obj, double eps) {
/* 899 */     if (this == obj)
/* 900 */       return true; 
/* 902 */     if (!(obj instanceof CircleArc2D))
/* 903 */       return super.equals(obj); 
/* 905 */     CircleArc2D arc = (CircleArc2D)obj;
/* 907 */     if (Math.abs(this.circle.xc - arc.circle.xc) > eps)
/* 908 */       return false; 
/* 909 */     if (Math.abs(this.circle.yc - arc.circle.yc) > eps)
/* 910 */       return false; 
/* 911 */     if (Math.abs(this.circle.r - arc.circle.r) > eps)
/* 912 */       return false; 
/* 913 */     if (Math.abs(this.circle.theta - arc.circle.theta) > eps)
/* 914 */       return false; 
/* 917 */     if (Math.abs(Angle2D.formatAngle(this.startAngle) - 
/* 918 */         Angle2D.formatAngle(arc.startAngle)) > eps)
/* 919 */       return false; 
/* 920 */     if (Math.abs(Angle2D.formatAngle(this.angleExtent) - 
/* 921 */         Angle2D.formatAngle(arc.angleExtent)) > eps)
/* 922 */       return false; 
/* 925 */     return true;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 932 */     Point2D center = this.circle.center();
/* 933 */     return String.format(Locale.US, 
/* 934 */         "CircleArc2D(%7.2f,%7.2f,%7.2f,%7.5f,%7.5f)", new Object[] { Double.valueOf(center.x()), Double.valueOf(center.y()), Double.valueOf(this.circle.radius()), 
/* 936 */           Double.valueOf(getStartAngle()), Double.valueOf(getAngleExtent()) });
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 945 */     if (this == obj)
/* 946 */       return true; 
/* 948 */     if (!(obj instanceof CircleArc2D))
/* 949 */       return false; 
/* 950 */     CircleArc2D that = (CircleArc2D)obj;
/* 953 */     if (!this.circle.equals(that.circle))
/* 954 */       return false; 
/* 957 */     if (!EqualUtils.areEqual(this.startAngle, that.startAngle))
/* 958 */       return false; 
/* 959 */     if (!EqualUtils.areEqual(this.angleExtent, that.angleExtent))
/* 960 */       return false; 
/* 963 */     return true;
/*     */   }
/*     */   
/*     */   public CircleArc2D clone() {
/* 968 */     return new CircleArc2D(this.circle.clone(), this.startAngle, this.angleExtent);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\conic\CircleArc2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
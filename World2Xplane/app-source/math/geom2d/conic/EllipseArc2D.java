/*     */ package math.geom2d.conic;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Locale;
/*     */ import math.geom2d.AffineTransform2D;
/*     */ import math.geom2d.Angle2D;
/*     */ import math.geom2d.Box2D;
/*     */ import math.geom2d.GeometricObject2D;
/*     */ import math.geom2d.Point2D;
/*     */ import math.geom2d.Shape2D;
/*     */ import math.geom2d.Vector2D;
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
/*     */ import math.geom2d.line.LinearShape2D;
/*     */ import math.geom2d.line.Ray2D;
/*     */ import math.geom2d.line.StraightLine2D;
/*     */ import math.geom2d.polygon.LinearCurve2D;
/*     */ import math.geom2d.polygon.Polyline2D;
/*     */ import math.utils.EqualUtils;
/*     */ 
/*     */ public class EllipseArc2D extends AbstractSmoothCurve2D implements SmoothOrientedCurve2D, EllipseArcShape2D, Cloneable {
/*     */   protected Ellipse2D ellipse;
/*     */   
/*     */   @Deprecated
/*     */   public static EllipseArc2D create(Ellipse2D ell, double start, double extent) {
/*  71 */     return new EllipseArc2D(ell.xc, ell.yc, ell.r1, ell.r2, ell.theta, 
/*  72 */         start, extent);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static EllipseArc2D create(Ellipse2D ell, double start, double end, boolean direct) {
/*  89 */     return new EllipseArc2D(ell.xc, ell.yc, ell.r1, ell.r2, ell.theta, 
/*  90 */         start, end, direct);
/*     */   }
/*     */   
/* 101 */   protected double startAngle = 0.0D;
/*     */   
/* 104 */   protected double angleExtent = Math.PI;
/*     */   
/*     */   public EllipseArc2D() {
/* 116 */     this(0.0D, 0.0D, 1.0D, 1.0D, 0.0D, 0.0D, 1.5707963267948966D);
/*     */   }
/*     */   
/*     */   public EllipseArc2D(Ellipse2D ell, double start, double extent) {
/* 127 */     this(ell.xc, ell.yc, ell.r1, ell.r2, ell.theta, start, extent);
/*     */   }
/*     */   
/*     */   public EllipseArc2D(Ellipse2D ell, double start, double end, boolean direct) {
/* 140 */     this(ell.xc, ell.yc, ell.r1, ell.r2, ell.theta, start, end, direct);
/*     */   }
/*     */   
/*     */   public EllipseArc2D(double xc, double yc, double a, double b, double theta, double start, double extent) {
/* 148 */     this.ellipse = new Ellipse2D(xc, yc, a, b, theta);
/* 149 */     this.startAngle = start;
/* 150 */     this.angleExtent = extent;
/*     */   }
/*     */   
/*     */   public EllipseArc2D(double xc, double yc, double a, double b, double theta, double start, double end, boolean direct) {
/* 159 */     this.ellipse = new Ellipse2D(xc, yc, a, b, theta);
/* 160 */     this.startAngle = start;
/* 161 */     this.angleExtent = Angle2D.formatAngle(end - start);
/* 162 */     if (!direct)
/* 163 */       this.angleExtent -= 6.283185307179586D; 
/*     */   }
/*     */   
/*     */   public Ellipse2D getSupportingEllipse() {
/* 170 */     return this.ellipse;
/*     */   }
/*     */   
/*     */   public double getStartAngle() {
/* 174 */     return this.startAngle;
/*     */   }
/*     */   
/*     */   public double getAngleExtent() {
/* 178 */     return this.angleExtent;
/*     */   }
/*     */   
/*     */   public boolean isDirect() {
/* 186 */     return (this.angleExtent >= 0.0D);
/*     */   }
/*     */   
/*     */   public boolean containsAngle(double angle) {
/* 190 */     return Angle2D.containsAngle(
/* 191 */         this.startAngle, this.startAngle + this.angleExtent, angle, (this.angleExtent > 0.0D));
/*     */   }
/*     */   
/*     */   public double getAngle(double position) {
/* 196 */     if (position < 0.0D)
/* 197 */       position = 0.0D; 
/* 198 */     if (position > Math.abs(this.angleExtent))
/* 199 */       position = Math.abs(this.angleExtent); 
/* 200 */     if (this.angleExtent < 0.0D)
/* 201 */       position = -position; 
/* 202 */     return Angle2D.formatAngle(this.startAngle + position);
/*     */   }
/*     */   
/*     */   public double windingAngle(Point2D point) {
/* 214 */     Point2D p1 = point(0.0D);
/* 215 */     Point2D p2 = point(Math.abs(this.angleExtent));
/* 218 */     double angle1 = Angle2D.horizontalAngle(point, p1);
/* 219 */     double angle2 = Angle2D.horizontalAngle(point, p2);
/* 222 */     boolean b1 = (new StraightLine2D(p1, p2)).isInside(point);
/* 223 */     boolean b2 = this.ellipse.isInside(point);
/* 225 */     if (this.angleExtent > 0.0D) {
/* 226 */       if (b1 || b2) {
/* 227 */         if (angle2 > angle1)
/* 228 */           return angle2 - angle1; 
/* 230 */         return 6.283185307179586D - angle1 + angle2;
/*     */       } 
/* 232 */       if (angle2 > angle1)
/* 233 */         return angle2 - angle1 - 6.283185307179586D; 
/* 235 */       return angle2 - angle1;
/*     */     } 
/* 238 */     if (!b1 || b2) {
/* 239 */       if (angle1 > angle2)
/* 240 */         return angle2 - angle1; 
/* 242 */       return angle2 - angle1 - 6.283185307179586D;
/*     */     } 
/* 244 */     if (angle1 > angle2)
/* 245 */       return angle2 - angle1 + 6.283185307179586D; 
/* 247 */     return angle2 - angle1;
/*     */   }
/*     */   
/*     */   public boolean isInside(Point2D p) {
/* 253 */     return (signedDistance(p.x(), p.y()) < 0.0D);
/*     */   }
/*     */   
/*     */   public double signedDistance(Point2D p) {
/* 257 */     return signedDistance(p.x(), p.y());
/*     */   }
/*     */   
/*     */   public double signedDistance(double x, double y) {
/* 266 */     boolean direct = (this.angleExtent >= 0.0D);
/* 268 */     double dist = distance(x, y);
/* 269 */     Point2D point = new Point2D(x, y);
/* 271 */     boolean inside = this.ellipse.isInside(point);
/* 272 */     if (inside)
/* 273 */       return (this.angleExtent > 0.0D) ? -dist : dist; 
/* 275 */     Point2D p1 = point(this.startAngle);
/* 276 */     double endAngle = this.startAngle + this.angleExtent;
/* 277 */     Point2D p2 = point(endAngle);
/* 278 */     boolean onLeft = (new StraightLine2D(p1, p2)).isInside(point);
/* 280 */     if (direct && !onLeft)
/* 281 */       return dist; 
/* 282 */     if (!direct && onLeft)
/* 283 */       return -dist; 
/* 286 */     Ray2D ray = new Ray2D(p1, -Math.sin(this.startAngle), Math.cos(this.startAngle));
/* 287 */     boolean left1 = ray.isInside(point);
/* 288 */     if (direct && !left1)
/* 289 */       return dist; 
/* 290 */     if (!direct && left1)
/* 291 */       return -dist; 
/* 293 */     ray = new Ray2D(p2, -Math.sin(endAngle), Math.cos(endAngle));
/* 294 */     boolean left2 = ray.isInside(point);
/* 295 */     if (direct && !left2)
/* 296 */       return dist; 
/* 297 */     if (!direct && left2)
/* 298 */       return -dist; 
/* 300 */     if (direct)
/* 301 */       return -dist; 
/* 303 */     return dist;
/*     */   }
/*     */   
/*     */   public Vector2D tangent(double t) {
/* 311 */     t = Math.min(Math.max(0.0D, t), Math.abs(this.angleExtent));
/* 314 */     if (this.angleExtent < 0.0D)
/* 316 */       return this.ellipse.tangent(this.startAngle - t).times(-1.0D); 
/* 318 */     return this.ellipse.tangent(this.startAngle + t);
/*     */   }
/*     */   
/*     */   public double curvature(double t) {
/* 328 */     if (this.angleExtent < 0.0D) {
/* 329 */       t = this.startAngle - t;
/*     */     } else {
/* 331 */       t = this.startAngle + t;
/*     */     } 
/* 332 */     double kappa = this.ellipse.curvature(t);
/* 333 */     return isDirect() ? kappa : -kappa;
/*     */   }
/*     */   
/*     */   public boolean isClosed() {
/* 341 */     return false;
/*     */   }
/*     */   
/*     */   public Collection<? extends EllipseArc2D> smoothPieces() {
/* 348 */     return wrapCurve((ContinuousCurve2D)this);
/*     */   }
/*     */   
/*     */   public Polyline2D asPolyline(int n) {
/* 357 */     double dt = Math.abs(this.angleExtent) / n;
/* 361 */     Point2D[] points = new Point2D[n + 1];
/* 362 */     for (int i = 0; i < n + 1; i++)
/* 363 */       points[i] = point(i * dt); 
/* 365 */     return new Polyline2D(points);
/*     */   }
/*     */   
/*     */   public double t0() {
/* 373 */     return 0.0D;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public double getT0() {
/* 381 */     return t0();
/*     */   }
/*     */   
/*     */   public double t1() {
/* 386 */     return Math.abs(this.angleExtent);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public double getT1() {
/* 394 */     return t1();
/*     */   }
/*     */   
/*     */   public Point2D point(double t) {
/* 404 */     t = Math.max(t, 0.0D);
/* 405 */     t = Math.min(t, Math.abs(this.angleExtent));
/* 408 */     if (this.angleExtent < 0.0D) {
/* 409 */       t = this.startAngle - t;
/*     */     } else {
/* 411 */       t = this.startAngle + t;
/*     */     } 
/* 414 */     return this.ellipse.point(t);
/*     */   }
/*     */   
/*     */   public double position(Point2D point) {
/* 423 */     double angle = Angle2D.horizontalAngle(this.ellipse.center(), point);
/* 424 */     if (containsAngle(angle)) {
/* 425 */       if (this.angleExtent > 0.0D)
/* 426 */         return Angle2D.formatAngle(angle - this.startAngle); 
/* 428 */       return Angle2D.formatAngle(this.startAngle - angle);
/*     */     } 
/* 431 */     return Double.NaN;
/*     */   }
/*     */   
/*     */   public double project(Point2D point) {
/* 435 */     double angle = this.ellipse.project(point);
/* 438 */     if (containsAngle(angle)) {
/* 439 */       if (this.angleExtent > 0.0D)
/* 440 */         return Angle2D.formatAngle(angle - this.startAngle); 
/* 442 */       return Angle2D.formatAngle(this.startAngle - angle);
/*     */     } 
/* 446 */     double d1 = firstPoint().distance(point);
/* 447 */     double d2 = lastPoint().distance(point);
/* 448 */     return (d1 < d2) ? 0.0D : Math.abs(this.angleExtent);
/*     */   }
/*     */   
/*     */   public Collection<Point2D> intersections(LinearShape2D line) {
/* 459 */     ArrayList<Point2D> array = new ArrayList<Point2D>();
/* 460 */     for (Point2D point : this.ellipse.intersections(line)) {
/* 461 */       if (contains(point))
/* 462 */         array.add(point); 
/*     */     } 
/* 464 */     return array;
/*     */   }
/*     */   
/*     */   public EllipseArc2D reverse() {
/* 472 */     double newStart = Angle2D.formatAngle(this.startAngle + this.angleExtent);
/* 473 */     return new EllipseArc2D(this.ellipse, newStart, -this.angleExtent);
/*     */   }
/*     */   
/*     */   public Collection<? extends EllipseArc2D> continuousCurves() {
/* 478 */     return wrapCurve((ContinuousCurve2D)this);
/*     */   }
/*     */   
/*     */   public EllipseArc2D subCurve(double t0, double t1) {
/* 486 */     t0 = Angle2D.formatAngle(this.startAngle + t0);
/* 487 */     t1 = Angle2D.formatAngle(this.startAngle + t1);
/* 490 */     if (!Angle2D.containsAngle(this.startAngle, this.startAngle + this.angleExtent, t0, 
/* 491 */         (this.angleExtent > 0.0D)))
/* 492 */       t0 = this.startAngle; 
/* 493 */     if (!Angle2D.containsAngle(this.startAngle, this.startAngle + this.angleExtent, t1, 
/* 494 */         (this.angleExtent > 0.0D)))
/* 495 */       t1 = this.angleExtent; 
/* 498 */     return new EllipseArc2D(this.ellipse, t0, t1, (this.angleExtent > 0.0D));
/*     */   }
/*     */   
/*     */   public double distance(Point2D point) {
/* 510 */     return distance(point.x(), point.y());
/*     */   }
/*     */   
/*     */   public double distance(double x, double y) {
/* 519 */     Point2D p = point(project(new Point2D(x, y)));
/* 520 */     return p.distance(x, y);
/*     */   }
/*     */   
/*     */   public boolean isBounded() {
/* 525 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 532 */     return false;
/*     */   }
/*     */   
/*     */   public CurveSet2D<? extends EllipseArc2D> clip(Box2D box) {
/* 543 */     CurveSet2D<SmoothCurve2D> set = Curves2D.clipSmoothCurve((SmoothCurve2D)this, box);
/* 546 */     CurveArray2D<EllipseArc2D> result = 
/* 547 */       new CurveArray2D(set.size());
/* 550 */     for (Curve2D curve : set.curves()) {
/* 551 */       if (curve instanceof EllipseArc2D)
/* 552 */         result.add(curve); 
/*     */     } 
/* 554 */     return (CurveSet2D<? extends EllipseArc2D>)result;
/*     */   }
/*     */   
/*     */   public Box2D boundingBox() {
/* 560 */     Point2D p0 = firstPoint();
/* 561 */     Point2D p1 = lastPoint();
/* 564 */     double x0 = p0.x();
/* 565 */     double y0 = p0.y();
/* 566 */     double x1 = p1.x();
/* 567 */     double y1 = p1.y();
/* 570 */     double xmin = Math.min(x0, x1);
/* 571 */     double xmax = Math.max(x0, x1);
/* 572 */     double ymin = Math.min(y0, y1);
/* 573 */     double ymax = Math.max(y0, y1);
/* 576 */     Point2D center = this.ellipse.center();
/* 577 */     double xc = center.x();
/* 578 */     double yc = center.y();
/* 579 */     double endAngle = this.startAngle + this.angleExtent;
/* 580 */     boolean direct = (this.angleExtent >= 0.0D);
/* 583 */     if (Angle2D.containsAngle(this.startAngle, endAngle, 1.5707963267948966D + this.ellipse.theta, direct))
/* 584 */       ymax = Math.max(ymax, yc + this.ellipse.r1); 
/* 585 */     if (Angle2D.containsAngle(this.startAngle, endAngle, 4.71238898038469D + 
/* 586 */         this.ellipse.theta, direct))
/* 587 */       ymin = Math.min(ymin, yc - this.ellipse.r1); 
/* 588 */     if (Angle2D.containsAngle(this.startAngle, endAngle, this.ellipse.theta, 
/* 589 */         direct))
/* 590 */       xmax = Math.max(xmax, xc + this.ellipse.r2); 
/* 591 */     if (Angle2D.containsAngle(this.startAngle, endAngle, Math.PI + this.ellipse.theta, 
/* 592 */         direct))
/* 593 */       xmin = Math.min(xmin, xc - this.ellipse.r2); 
/* 596 */     return new Box2D(xmin, xmax, ymin, ymax);
/*     */   }
/*     */   
/*     */   public EllipseArc2D transform(AffineTransform2D trans) {
/* 606 */     Ellipse2D ell = this.ellipse.transform(trans);
/* 609 */     if (!ell.isDirect())
/* 610 */       ell = ell.reverse(); 
/* 613 */     double startPos = ell.project(firstPoint().transform(trans));
/* 614 */     double endPos = ell.project(lastPoint().transform(trans));
/* 617 */     boolean direct = !((((this.angleExtent > 0.0D) ? 1 : 0) ^ trans.isDirect()) != 0);
/* 618 */     return new EllipseArc2D(ell, startPos, endPos, direct);
/*     */   }
/*     */   
/*     */   public boolean contains(double x, double y) {
/* 627 */     return (distance(x, y) > 1.0E-12D);
/*     */   }
/*     */   
/*     */   public boolean contains(Point2D point) {
/* 636 */     return contains(point.x(), point.y());
/*     */   }
/*     */   
/*     */   public GeneralPath appendPath(GeneralPath path) {
/* 641 */     int nSeg = (int)Math.ceil(Math.abs(this.angleExtent) / 1.5707963267948966D);
/* 642 */     nSeg = Math.min(nSeg, 4);
/* 645 */     double ext = this.angleExtent / nSeg;
/* 648 */     double k = btan(Math.abs(ext));
/* 650 */     for (int i = 0; i < nSeg; i++) {
/* 652 */       double ti0 = Math.abs(i * ext);
/* 653 */       double ti1 = Math.abs((i + 1) * ext);
/* 656 */       Point2D p1 = point(ti0);
/* 657 */       Point2D p2 = point(ti1);
/* 660 */       Vector2D v1 = tangent(ti0).times(k);
/* 661 */       Vector2D v2 = tangent(ti1).times(k);
/* 664 */       path.curveTo(
/* 665 */           p1.x() + v1.x(), p1.y() + v1.y(), 
/* 666 */           p2.x() - v2.x(), p2.y() - v2.y(), 
/* 667 */           p2.x(), p2.y());
/*     */     } 
/* 669 */     return path;
/*     */   }
/*     */   
/*     */   public GeneralPath getGeneralPath() {
/* 674 */     GeneralPath path = new GeneralPath();
/* 677 */     Point2D point = firstPoint();
/* 678 */     path.moveTo((float)point.x(), (float)point.y());
/* 681 */     path = appendPath(path);
/* 684 */     return path;
/*     */   }
/*     */   
/*     */   public void draw(Graphics2D g2) {
/* 689 */     g2.draw(getGeneralPath());
/*     */   }
/*     */   
/*     */   private static double btan(double increment) {
/* 772 */     increment /= 2.0D;
/* 773 */     return 1.3333333333333333D * Math.sin(increment) / (1.0D + Math.cos(increment));
/*     */   }
/*     */   
/*     */   public boolean almostEquals(GeometricObject2D obj, double eps) {
/* 783 */     if (this == obj)
/* 784 */       return true; 
/* 786 */     if (!(obj instanceof EllipseArc2D))
/* 787 */       return false; 
/* 788 */     EllipseArc2D arc = (EllipseArc2D)obj;
/* 791 */     if (Math.abs(this.ellipse.xc - arc.ellipse.xc) > eps)
/* 792 */       return false; 
/* 793 */     if (Math.abs(this.ellipse.yc - arc.ellipse.yc) > eps)
/* 794 */       return false; 
/* 795 */     if (Math.abs(this.ellipse.r1 - arc.ellipse.r1) > eps)
/* 796 */       return false; 
/* 797 */     if (Math.abs(this.ellipse.r2 - arc.ellipse.r2) > eps)
/* 798 */       return false; 
/* 799 */     if (Math.abs(this.ellipse.theta - arc.ellipse.theta) > eps)
/* 800 */       return false; 
/* 803 */     if (!Angle2D.equals(this.startAngle, arc.startAngle))
/* 804 */       return false; 
/* 805 */     if (!Angle2D.equals(this.angleExtent, arc.angleExtent))
/* 806 */       return false; 
/* 808 */     return true;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 816 */     Point2D center = this.ellipse.center();
/* 817 */     return String.format(Locale.US, 
/* 818 */         "EllipseArc2D(%7.2f,%7.2f,%7.2f,%7.2f,%7.5f,%7.5f,%7.5f)", new Object[] { Double.valueOf(center.x()), Double.valueOf(center.y()), 
/* 820 */           Double.valueOf(this.ellipse.r1), Double.valueOf(this.ellipse.r2), Double.valueOf(this.ellipse.theta), 
/* 821 */           Double.valueOf(this.startAngle), Double.valueOf(this.angleExtent) });
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 826 */     if (this == obj)
/* 827 */       return true; 
/* 829 */     if (!(obj instanceof EllipseArc2D))
/* 830 */       return false; 
/* 831 */     EllipseArc2D that = (EllipseArc2D)obj;
/* 834 */     if (!this.ellipse.equals(that.ellipse))
/* 835 */       return false; 
/* 838 */     if (!EqualUtils.areEqual(this.startAngle, that.startAngle))
/* 839 */       return false; 
/* 840 */     if (!EqualUtils.areEqual(this.angleExtent, that.angleExtent))
/* 841 */       return false; 
/* 843 */     return true;
/*     */   }
/*     */   
/*     */   public EllipseArc2D clone() {
/* 848 */     return new EllipseArc2D(this.ellipse, this.startAngle, this.angleExtent);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\conic\EllipseArc2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
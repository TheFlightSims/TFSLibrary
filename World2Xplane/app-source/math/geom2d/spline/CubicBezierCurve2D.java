/*     */ package math.geom2d.spline;
/*     */ 
/*     */ import java.awt.geom.CubicCurve2D;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.util.Collection;
/*     */ import math.geom2d.AffineTransform2D;
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
/*     */ import math.geom2d.line.LinearShape2D;
/*     */ import math.geom2d.polygon.LinearCurve2D;
/*     */ import math.geom2d.polygon.Polyline2D;
/*     */ 
/*     */ public class CubicBezierCurve2D extends AbstractSmoothCurve2D implements SmoothCurve2D, ContinuousOrientedCurve2D, Cloneable {
/*     */   protected double x1;
/*     */   
/*     */   protected double y1;
/*     */   
/*     */   protected double ctrlx1;
/*     */   
/*     */   protected double ctrly1;
/*     */   
/*     */   protected double ctrlx2;
/*     */   
/*     */   protected double ctrly2;
/*     */   
/*     */   protected double x2;
/*     */   
/*     */   protected double y2;
/*     */   
/*     */   @Deprecated
/*     */   public static final CubicBezierCurve2D create(Point2D p1, Point2D c1, Point2D c2, Point2D p2) {
/*  58 */     return new CubicBezierCurve2D(p1, c1, c2, p2);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static final CubicBezierCurve2D create(Point2D p1, Vector2D v1, Point2D p2, Vector2D v2) {
/*  67 */     return new CubicBezierCurve2D(p1, v1, p2, v2);
/*     */   }
/*     */   
/*     */   public CubicBezierCurve2D() {
/*  84 */     this(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
/*     */   }
/*     */   
/*     */   public CubicBezierCurve2D(double[][] coefs) {
/* 100 */     this(coefs[0][0], coefs[1][0], coefs[0][0] + coefs[0][1] / 3.0D, coefs[1][0] + coefs[1][1] / 3.0D, coefs[0][0] + 2.0D * coefs[0][1] / 3.0D + coefs[0][2] / 3.0D, coefs[1][0] + 2.0D * coefs[1][1] / 3.0D + coefs[1][2] / 3.0D, coefs[0][0] + coefs[0][1] + coefs[0][2] + coefs[0][3], coefs[1][0] + coefs[1][1] + coefs[1][2] + coefs[1][3]);
/*     */   }
/*     */   
/*     */   public CubicBezierCurve2D(Point2D p1, Point2D ctrl1, Point2D ctrl2, Point2D p2) {
/* 116 */     this(p1.x(), p1.y(), ctrl1.x(), ctrl1.y(), ctrl2.x(), ctrl2.y(), p2.x(), p2.y());
/*     */   }
/*     */   
/*     */   public CubicBezierCurve2D(Point2D p1, Vector2D v1, Point2D p2, Vector2D v2) {
/* 132 */     this(p1.x(), p1.y(), p1.x() + v1.x() / 3.0D, p1.y() + v1.y() / 3.0D, p2.x() - v2.x() / 3.0D, p2.y() - v2.y() / 3.0D, p2.x(), p2.y());
/*     */   }
/*     */   
/*     */   public CubicBezierCurve2D(double x1, double y1, double xctrl1, double yctrl1, double xctrl2, double yctrl2, double x2, double y2) {
/* 142 */     this.x1 = x1;
/* 143 */     this.y1 = y1;
/* 144 */     this.ctrlx1 = xctrl1;
/* 145 */     this.ctrly1 = yctrl1;
/* 146 */     this.ctrlx2 = xctrl2;
/* 147 */     this.ctrly2 = yctrl2;
/* 148 */     this.x2 = x2;
/* 149 */     this.y2 = y2;
/*     */   }
/*     */   
/*     */   public Point2D getControl1() {
/* 157 */     return new Point2D(this.ctrlx1, this.ctrly1);
/*     */   }
/*     */   
/*     */   public Point2D getControl2() {
/* 161 */     return new Point2D(this.ctrlx2, this.ctrly2);
/*     */   }
/*     */   
/*     */   public Point2D getP1() {
/* 165 */     return firstPoint();
/*     */   }
/*     */   
/*     */   public Point2D getP2() {
/* 169 */     return lastPoint();
/*     */   }
/*     */   
/*     */   public Point2D getCtrlP1() {
/* 173 */     return getControl1();
/*     */   }
/*     */   
/*     */   public Point2D getCtrlP2() {
/* 177 */     return getControl2();
/*     */   }
/*     */   
/*     */   public double[][] getParametric() {
/* 192 */     double[][] tab = new double[2][4];
/* 193 */     tab[0][0] = this.x1;
/* 194 */     tab[0][1] = 3.0D * this.ctrlx1 - 3.0D * this.x1;
/* 195 */     tab[0][2] = 3.0D * this.x1 - 6.0D * this.ctrlx1 + 3.0D * this.ctrlx2;
/* 196 */     tab[0][3] = this.x2 - 3.0D * this.ctrlx2 + 3.0D * this.ctrlx1 - this.x1;
/* 198 */     tab[1][0] = this.y1;
/* 199 */     tab[1][1] = 3.0D * this.ctrly1 - 3.0D * this.y1;
/* 200 */     tab[1][2] = 3.0D * this.y1 - 6.0D * this.ctrly1 + 3.0D * this.ctrly2;
/* 201 */     tab[1][3] = this.y2 - 3.0D * this.ctrly2 + 3.0D * this.ctrly1 - this.y1;
/* 202 */     return tab;
/*     */   }
/*     */   
/*     */   public double windingAngle(Point2D point) {
/* 215 */     return asPolyline(100).windingAngle(point);
/*     */   }
/*     */   
/*     */   public boolean isInside(Point2D pt) {
/* 226 */     return asPolyline(100).isInside(pt);
/*     */   }
/*     */   
/*     */   public double signedDistance(Point2D point) {
/* 230 */     if (isInside(point))
/* 231 */       return -distance(point.x(), point.y()); 
/* 233 */     return distance(point.x(), point.y());
/*     */   }
/*     */   
/*     */   public double signedDistance(double x, double y) {
/* 240 */     if (isInside(new Point2D(x, y)))
/* 241 */       return -distance(x, y); 
/* 243 */     return distance(x, y);
/*     */   }
/*     */   
/*     */   public Vector2D tangent(double t) {
/* 250 */     double[][] c = getParametric();
/* 251 */     double dx = c[0][1] + (2.0D * c[0][2] + 3.0D * c[0][3] * t) * t;
/* 252 */     double dy = c[1][1] + (2.0D * c[1][2] + 3.0D * c[1][3] * t) * t;
/* 253 */     return new Vector2D(dx, dy);
/*     */   }
/*     */   
/*     */   public double curvature(double t) {
/* 260 */     double[][] c = getParametric();
/* 261 */     double xp = c[0][1] + (2.0D * c[0][2] + 3.0D * c[0][3] * t) * t;
/* 262 */     double yp = c[1][1] + (2.0D * c[1][2] + 3.0D * c[1][3] * t) * t;
/* 263 */     double xs = 2.0D * c[0][2] + 6.0D * c[0][3] * t;
/* 264 */     double ys = 2.0D * c[1][2] + 6.0D * c[1][3] * t;
/* 266 */     return (xp * ys - yp * xs) / Math.pow(Math.hypot(xp, yp), 3.0D);
/*     */   }
/*     */   
/*     */   public boolean isClosed() {
/* 276 */     return false;
/*     */   }
/*     */   
/*     */   public Polyline2D asPolyline(int n) {
/* 285 */     double dt = 1.0D / n;
/* 289 */     Point2D[] points = new Point2D[n + 1];
/* 290 */     for (int i = 0; i < n + 1; i++)
/* 291 */       points[i] = point(i * dt); 
/* 293 */     return new Polyline2D(points);
/*     */   }
/*     */   
/*     */   public double t0() {
/* 304 */     return 0.0D;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public double getT0() {
/* 312 */     return t0();
/*     */   }
/*     */   
/*     */   public double t1() {
/* 319 */     return 1.0D;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public double getT1() {
/* 327 */     return t1();
/*     */   }
/*     */   
/*     */   public Collection<Point2D> intersections(LinearShape2D line) {
/* 336 */     return asPolyline(100).intersections(line);
/*     */   }
/*     */   
/*     */   public Point2D point(double t) {
/* 343 */     t = Math.min(Math.max(t, 0.0D), 1.0D);
/* 344 */     double[][] c = getParametric();
/* 345 */     double x = c[0][0] + (c[0][1] + (c[0][2] + c[0][3] * t) * t) * t;
/* 346 */     double y = c[1][0] + (c[1][1] + (c[1][2] + c[1][3] * t) * t) * t;
/* 347 */     return new Point2D(x, y);
/*     */   }
/*     */   
/*     */   public Point2D firstPoint() {
/* 357 */     return new Point2D(this.x1, this.y1);
/*     */   }
/*     */   
/*     */   public Point2D lastPoint() {
/* 367 */     return new Point2D(this.x2, this.y2);
/*     */   }
/*     */   
/*     */   public double position(Point2D point) {
/* 374 */     int N = 100;
/* 375 */     return asPolyline(N).position(point) / N;
/*     */   }
/*     */   
/*     */   public double project(Point2D point) {
/* 382 */     int N = 100;
/* 383 */     return asPolyline(N).project(point) / N;
/*     */   }
/*     */   
/*     */   public CubicBezierCurve2D reverse() {
/* 391 */     return new CubicBezierCurve2D(
/* 392 */         lastPoint(), getControl1(), 
/* 393 */         getControl2(), firstPoint());
/*     */   }
/*     */   
/*     */   public CubicBezierCurve2D subCurve(double t0, double t1) {
/* 400 */     t0 = Math.max(t0, 0.0D);
/* 401 */     t1 = Math.min(t1, 1.0D);
/* 402 */     if (t0 > t1)
/* 403 */       return null; 
/* 405 */     double dt = t1 - t0;
/* 406 */     Vector2D v0 = tangent(t0).times(dt);
/* 407 */     Vector2D v1 = tangent(t1).times(dt);
/* 408 */     return new CubicBezierCurve2D(point(t0), v0, point(t1), v1);
/*     */   }
/*     */   
/*     */   public boolean contains(double x, double y) {
/* 418 */     return (new CubicCurve2D.Double(
/* 419 */         this.x1, this.y1, this.ctrlx1, this.ctrly1, this.ctrlx2, this.ctrly2, this.x2, this.y2)).contains(x, y);
/*     */   }
/*     */   
/*     */   public boolean contains(Point2D p) {
/* 426 */     return contains(p.x(), p.y());
/*     */   }
/*     */   
/*     */   public double distance(Point2D p) {
/* 433 */     return distance(p.x(), p.y());
/*     */   }
/*     */   
/*     */   public double distance(double x, double y) {
/* 442 */     return asPolyline(100).distance(x, y);
/*     */   }
/*     */   
/*     */   public boolean isBounded() {
/* 449 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 453 */     return false;
/*     */   }
/*     */   
/*     */   public CurveSet2D<? extends CubicBezierCurve2D> clip(Box2D box) {
/* 461 */     CurveSet2D<SmoothCurve2D> set = 
/* 462 */       Curves2D.clipSmoothCurve(this, box);
/* 465 */     CurveArray2D<CubicBezierCurve2D> result = 
/* 466 */       new CurveArray2D(set.size());
/* 469 */     for (Curve2D curve : set.curves()) {
/* 470 */       if (curve instanceof CubicBezierCurve2D)
/* 471 */         result.add(curve); 
/*     */     } 
/* 473 */     return (CurveSet2D<? extends CubicBezierCurve2D>)result;
/*     */   }
/*     */   
/*     */   public Box2D boundingBox() {
/* 481 */     Point2D p1 = firstPoint();
/* 482 */     Point2D p2 = getControl1();
/* 483 */     Point2D p3 = getControl2();
/* 484 */     Point2D p4 = lastPoint();
/* 485 */     double xmin = Math.min(Math.min(p1.x(), p2.x()), 
/* 486 */         Math.min(p3.x(), p4.x()));
/* 487 */     double xmax = Math.max(Math.max(p1.x(), p2.x()), 
/* 488 */         Math.max(p3.x(), p4.x()));
/* 489 */     double ymin = Math.min(Math.min(p1.y(), p2.y()), 
/* 490 */         Math.min(p3.y(), p4.y()));
/* 491 */     double ymax = Math.max(Math.max(p1.y(), p2.y()), 
/* 492 */         Math.max(p3.y(), p4.y()));
/* 493 */     return new Box2D(xmin, xmax, ymin, ymax);
/*     */   }
/*     */   
/*     */   public CubicBezierCurve2D transform(AffineTransform2D trans) {
/* 501 */     return new CubicBezierCurve2D(
/* 502 */         trans.transform(firstPoint()), 
/* 503 */         trans.transform(getControl1()), 
/* 504 */         trans.transform(getControl2()), 
/* 505 */         trans.transform(lastPoint()));
/*     */   }
/*     */   
/*     */   public GeneralPath appendPath(GeneralPath path) {
/* 509 */     Point2D p2 = getControl1();
/* 510 */     Point2D p3 = getControl2();
/* 511 */     Point2D p4 = lastPoint();
/* 512 */     path.curveTo(
/* 513 */         p2.x(), p2.y(), 
/* 514 */         p3.x(), p3.y(), 
/* 515 */         p4.x(), p4.y());
/* 516 */     return path;
/*     */   }
/*     */   
/*     */   public GeneralPath getGeneralPath() {
/* 520 */     GeneralPath path = new GeneralPath();
/* 521 */     Point2D p1 = firstPoint();
/* 522 */     Point2D p2 = getControl1();
/* 523 */     Point2D p3 = getControl2();
/* 524 */     Point2D p4 = lastPoint();
/* 525 */     path.moveTo(p1.x(), p1.y());
/* 526 */     path.curveTo(
/* 527 */         p2.x(), p2.y(), 
/* 528 */         p3.x(), p3.y(), 
/* 529 */         p4.x(), p4.y());
/* 530 */     return path;
/*     */   }
/*     */   
/*     */   public boolean almostEquals(GeometricObject2D obj, double eps) {
/* 541 */     if (this == obj)
/* 542 */       return true; 
/* 544 */     if (!(obj instanceof CubicBezierCurve2D))
/* 545 */       return false; 
/* 548 */     CubicBezierCurve2D bezier = (CubicBezierCurve2D)obj;
/* 551 */     if (Math.abs(this.x1 - bezier.x1) > eps)
/* 552 */       return false; 
/* 553 */     if (Math.abs(this.y1 - bezier.y1) > eps)
/* 554 */       return false; 
/* 555 */     if (Math.abs(this.ctrlx1 - bezier.ctrlx1) > eps)
/* 556 */       return false; 
/* 557 */     if (Math.abs(this.ctrly1 - bezier.ctrly1) > eps)
/* 558 */       return false; 
/* 559 */     if (Math.abs(this.ctrlx2 - bezier.ctrlx2) > eps)
/* 560 */       return false; 
/* 561 */     if (Math.abs(this.ctrly2 - bezier.ctrly2) > eps)
/* 562 */       return false; 
/* 563 */     if (Math.abs(this.x2 - bezier.x2) > eps)
/* 564 */       return false; 
/* 565 */     if (Math.abs(this.y2 - bezier.y2) > eps)
/* 566 */       return false; 
/* 568 */     return true;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 576 */     if (this == obj)
/* 577 */       return true; 
/* 578 */     if (!(obj instanceof CubicBezierCurve2D))
/* 579 */       return false; 
/* 582 */     CubicBezierCurve2D bezier = (CubicBezierCurve2D)obj;
/* 585 */     if (Math.abs(this.x1 - bezier.x1) > 1.0E-12D)
/* 585 */       return false; 
/* 586 */     if (Math.abs(this.y1 - bezier.y1) > 1.0E-12D)
/* 586 */       return false; 
/* 587 */     if (Math.abs(this.ctrlx1 - bezier.ctrlx1) > 1.0E-12D)
/* 587 */       return false; 
/* 588 */     if (Math.abs(this.ctrly1 - bezier.ctrly1) > 1.0E-12D)
/* 588 */       return false; 
/* 589 */     if (Math.abs(this.ctrlx2 - bezier.ctrlx2) > 1.0E-12D)
/* 589 */       return false; 
/* 590 */     if (Math.abs(this.ctrly2 - bezier.ctrly2) > 1.0E-12D)
/* 590 */       return false; 
/* 591 */     if (Math.abs(this.x2 - bezier.x2) > 1.0E-12D)
/* 591 */       return false; 
/* 592 */     if (Math.abs(this.y2 - bezier.y2) > 1.0E-12D)
/* 592 */       return false; 
/* 594 */     return true;
/*     */   }
/*     */   
/*     */   public CubicBezierCurve2D clone() {
/* 599 */     return new CubicBezierCurve2D(
/* 600 */         this.x1, this.y1, this.ctrlx1, this.ctrly1, this.ctrlx2, this.ctrly2, this.x2, this.y2);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\spline\CubicBezierCurve2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
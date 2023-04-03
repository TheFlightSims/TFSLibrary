/*     */ package math.geom2d.spline;
/*     */ 
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.QuadCurve2D;
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
/*     */ import math.geom2d.line.StraightLine2D;
/*     */ import math.geom2d.polygon.LinearCurve2D;
/*     */ import math.geom2d.polygon.Polyline2D;
/*     */ 
/*     */ public class QuadBezierCurve2D extends AbstractSmoothCurve2D implements SmoothCurve2D, ContinuousOrientedCurve2D, Cloneable {
/*     */   protected double x1;
/*     */   
/*     */   protected double y1;
/*     */   
/*     */   protected double ctrlx;
/*     */   
/*     */   protected double ctrly;
/*     */   
/*     */   protected double x2;
/*     */   
/*     */   protected double y2;
/*     */   
/*     */   @Deprecated
/*     */   public static QuadBezierCurve2D create(Point2D p1, Point2D p2, Point2D p3) {
/*  60 */     return new QuadBezierCurve2D(p1, p2, p3);
/*     */   }
/*     */   
/*     */   public QuadBezierCurve2D() {
/*  89 */     this(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
/*     */   }
/*     */   
/*     */   public QuadBezierCurve2D(double[][] coefs) {
/* 103 */     this(coefs[0][0], coefs[1][0], coefs[0][0] + coefs[0][1] / 2.0D, coefs[1][0] + coefs[1][1] / 2.0D, coefs[0][0] + coefs[0][1] + coefs[0][2], coefs[1][0] + coefs[1][1] + coefs[1][2]);
/*     */   }
/*     */   
/*     */   public QuadBezierCurve2D(Point2D p1, Point2D ctrl, Point2D p2) {
/* 116 */     this(p1.x(), p1.y(), ctrl.x(), ctrl.y(), p2.x(), p2.y());
/*     */   }
/*     */   
/*     */   public QuadBezierCurve2D(Point2D[] pts) {
/* 121 */     this(pts[0].x(), pts[0].y(), pts[1].x(), pts[1].y(), pts[2].x(), pts[2].y());
/*     */   }
/*     */   
/*     */   public QuadBezierCurve2D(double x1, double y1, double xctrl, double yctrl, double x2, double y2) {
/* 131 */     this.x1 = x1;
/* 132 */     this.y1 = y1;
/* 133 */     this.ctrlx = xctrl;
/* 134 */     this.ctrly = yctrl;
/* 135 */     this.x2 = x2;
/* 136 */     this.y2 = y2;
/*     */   }
/*     */   
/*     */   public Point2D getControl() {
/* 143 */     return new Point2D(this.ctrlx, this.ctrly);
/*     */   }
/*     */   
/*     */   public Point2D getP1() {
/* 147 */     return firstPoint();
/*     */   }
/*     */   
/*     */   public Point2D getP2() {
/* 151 */     return lastPoint();
/*     */   }
/*     */   
/*     */   public Point2D getCtrl() {
/* 155 */     return getControl();
/*     */   }
/*     */   
/*     */   public double[][] getParametric() {
/* 172 */     double[][] tab = new double[2][3];
/* 173 */     tab[0][0] = this.x1;
/* 174 */     tab[0][1] = 2.0D * this.ctrlx - 2.0D * this.x1;
/* 175 */     tab[0][2] = this.x2 - 2.0D * this.ctrlx + this.x1;
/* 177 */     tab[1][0] = this.y1;
/* 178 */     tab[1][1] = 2.0D * this.ctrly - 2.0D * this.y1;
/* 179 */     tab[1][2] = this.y2 - 2.0D * this.ctrly + this.y1;
/* 180 */     return tab;
/*     */   }
/*     */   
/*     */   public double windingAngle(Point2D point) {
/* 192 */     return asPolyline(100).windingAngle(point);
/*     */   }
/*     */   
/*     */   public boolean isInside(Point2D pt) {
/* 203 */     return asPolyline(100).isInside(pt);
/*     */   }
/*     */   
/*     */   public double signedDistance(Point2D point) {
/* 207 */     if (isInside(point))
/* 208 */       return -distance(point.x(), point.y()); 
/* 210 */     return distance(point.x(), point.y());
/*     */   }
/*     */   
/*     */   public double signedDistance(double x, double y) {
/* 217 */     if (isInside(new Point2D(x, y)))
/* 218 */       return -distance(x, y); 
/* 220 */     return distance(x, y);
/*     */   }
/*     */   
/*     */   public Vector2D tangent(double t) {
/* 227 */     double[][] c = getParametric();
/* 228 */     double dx = c[0][1] + 2.0D * c[0][2] * t;
/* 229 */     double dy = c[1][1] + 2.0D * c[1][2] * t;
/* 230 */     return new Vector2D(dx, dy);
/*     */   }
/*     */   
/*     */   public double curvature(double t) {
/* 237 */     double[][] c = getParametric();
/* 238 */     double xp = c[0][1] + 2.0D * c[0][2] * t;
/* 239 */     double yp = c[1][1] + 2.0D * c[1][2] * t;
/* 240 */     double xs = 2.0D * c[0][2];
/* 241 */     double ys = 2.0D * c[1][2];
/* 243 */     return (xp * ys - yp * xs) / Math.pow(Math.hypot(xp, yp), 3.0D);
/*     */   }
/*     */   
/*     */   public boolean isClosed() {
/* 253 */     return false;
/*     */   }
/*     */   
/*     */   public Polyline2D asPolyline(int n) {
/* 262 */     double dt = 1.0D / n;
/* 266 */     Point2D[] points = new Point2D[n + 1];
/* 267 */     for (int i = 0; i < n + 1; i++)
/* 268 */       points[i] = point(i * dt); 
/* 270 */     return new Polyline2D(points);
/*     */   }
/*     */   
/*     */   public double t0() {
/* 280 */     return 0.0D;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public double getT0() {
/* 288 */     return t0();
/*     */   }
/*     */   
/*     */   public double t1() {
/* 295 */     return 1.0D;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public double getT1() {
/* 303 */     return t1();
/*     */   }
/*     */   
/*     */   public Collection<Point2D> intersections(LinearShape2D line) {
/* 312 */     return asPolyline(100).intersections(line);
/*     */   }
/*     */   
/*     */   public Point2D point(double t) {
/* 319 */     t = Math.min(Math.max(t, 0.0D), 1.0D);
/* 320 */     double[][] c = getParametric();
/* 321 */     double x = c[0][0] + (c[0][1] + c[0][2] * t) * t;
/* 322 */     double y = c[1][0] + (c[1][1] + c[1][2] * t) * t;
/* 323 */     return new Point2D(x, y);
/*     */   }
/*     */   
/*     */   public Point2D firstPoint() {
/* 333 */     return new Point2D(this.x1, this.y1);
/*     */   }
/*     */   
/*     */   public Point2D lastPoint() {
/* 343 */     return new Point2D(this.x2, this.y2);
/*     */   }
/*     */   
/*     */   public double position(Point2D point) {
/* 350 */     int N = 100;
/* 351 */     return asPolyline(N).position(point) / N;
/*     */   }
/*     */   
/*     */   public double project(Point2D point) {
/* 358 */     int N = 100;
/* 359 */     return asPolyline(N).project(point) / N;
/*     */   }
/*     */   
/*     */   public QuadBezierCurve2D reverse() {
/* 366 */     return new QuadBezierCurve2D(
/* 367 */         lastPoint(), getControl(), firstPoint());
/*     */   }
/*     */   
/*     */   public QuadBezierCurve2D subCurve(double t0, double t1) {
/* 374 */     t0 = Math.max(t0, 0.0D);
/* 375 */     t1 = Math.min(t1, 1.0D);
/* 376 */     if (t0 > t1)
/* 377 */       return null; 
/* 380 */     Point2D p0 = point(t0);
/* 381 */     Point2D p1 = point(t1);
/* 384 */     Vector2D v0 = tangent(t0);
/* 385 */     Vector2D v1 = tangent(t1);
/* 388 */     StraightLine2D tan0 = new StraightLine2D(p0, v0);
/* 389 */     StraightLine2D tan1 = new StraightLine2D(p1, v1);
/* 390 */     Point2D control = tan0.intersection((LinearShape2D)tan1);
/* 393 */     return new QuadBezierCurve2D(p0, control, p1);
/*     */   }
/*     */   
/*     */   public boolean contains(double x, double y) {
/* 403 */     return (new QuadCurve2D.Double(
/* 404 */         this.x1, this.y1, this.ctrlx, this.ctrly, this.x2, this.y2)).contains(x, y);
/*     */   }
/*     */   
/*     */   public boolean contains(Point2D p) {
/* 411 */     return contains(p.x(), p.y());
/*     */   }
/*     */   
/*     */   public double distance(Point2D p) {
/* 418 */     return distance(p.x(), p.y());
/*     */   }
/*     */   
/*     */   public double distance(double x, double y) {
/* 427 */     return asPolyline(100).distance(x, y);
/*     */   }
/*     */   
/*     */   public boolean isBounded() {
/* 434 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 438 */     return false;
/*     */   }
/*     */   
/*     */   public CurveSet2D<? extends QuadBezierCurve2D> clip(Box2D box) {
/* 449 */     CurveSet2D<SmoothCurve2D> set = Curves2D.clipSmoothCurve(this, box);
/* 452 */     CurveArray2D<QuadBezierCurve2D> result = 
/* 453 */       new CurveArray2D(set.size());
/* 456 */     for (Curve2D curve : set.curves()) {
/* 457 */       if (curve instanceof QuadBezierCurve2D)
/* 458 */         result.add(curve); 
/*     */     } 
/* 460 */     return (CurveSet2D<? extends QuadBezierCurve2D>)result;
/*     */   }
/*     */   
/*     */   public Box2D boundingBox() {
/* 468 */     Point2D p1 = firstPoint();
/* 469 */     Point2D p2 = getControl();
/* 470 */     Point2D p3 = lastPoint();
/* 471 */     double xmin = Math.min(Math.min(p1.x(), p2.x()), p3.x());
/* 472 */     double xmax = Math.max(Math.max(p1.x(), p2.x()), p3.x());
/* 473 */     double ymin = Math.min(Math.min(p1.y(), p2.y()), p3.y());
/* 474 */     double ymax = Math.max(Math.max(p1.y(), p2.y()), p3.y());
/* 475 */     return new Box2D(xmin, xmax, ymin, ymax);
/*     */   }
/*     */   
/*     */   public QuadBezierCurve2D transform(AffineTransform2D trans) {
/* 483 */     return new QuadBezierCurve2D(
/* 484 */         trans.transform(firstPoint()), 
/* 485 */         trans.transform(getControl()), 
/* 486 */         trans.transform(lastPoint()));
/*     */   }
/*     */   
/*     */   public GeneralPath appendPath(GeneralPath path) {
/* 490 */     Point2D p2 = getControl();
/* 491 */     Point2D p3 = lastPoint();
/* 492 */     path.quadTo(p2.x(), p2.y(), p3.x(), p3.y());
/* 493 */     return path;
/*     */   }
/*     */   
/*     */   public GeneralPath getGeneralPath() {
/* 497 */     GeneralPath path = new GeneralPath();
/* 498 */     Point2D p1 = firstPoint();
/* 499 */     Point2D p2 = getControl();
/* 500 */     Point2D p3 = lastPoint();
/* 501 */     path.moveTo(p1.x(), p1.y());
/* 502 */     path.quadTo(p2.x(), p2.y(), p3.x(), p3.y());
/* 503 */     return path;
/*     */   }
/*     */   
/*     */   public boolean almostEquals(GeometricObject2D obj, double eps) {
/* 514 */     if (this == obj)
/* 515 */       return true; 
/* 517 */     if (!(obj instanceof QuadBezierCurve2D))
/* 518 */       return false; 
/* 521 */     QuadBezierCurve2D bezier = (QuadBezierCurve2D)obj;
/* 524 */     if (Math.abs(this.x1 - bezier.x1) > eps)
/* 524 */       return false; 
/* 525 */     if (Math.abs(this.y1 - bezier.y1) > eps)
/* 525 */       return false; 
/* 526 */     if (Math.abs(this.ctrlx - bezier.ctrlx) > eps)
/* 526 */       return false; 
/* 527 */     if (Math.abs(this.ctrly - bezier.ctrly) > eps)
/* 527 */       return false; 
/* 528 */     if (Math.abs(this.x2 - bezier.x2) > eps)
/* 528 */       return false; 
/* 529 */     if (Math.abs(this.y2 - bezier.y2) > eps)
/* 529 */       return false; 
/* 531 */     return true;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 539 */     if (this == obj)
/* 540 */       return true; 
/* 542 */     if (!(obj instanceof QuadBezierCurve2D))
/* 543 */       return false; 
/* 546 */     QuadBezierCurve2D bezier = (QuadBezierCurve2D)obj;
/* 549 */     if (Math.abs(this.x1 - bezier.x1) > 1.0E-12D)
/* 549 */       return false; 
/* 550 */     if (Math.abs(this.y1 - bezier.y1) > 1.0E-12D)
/* 550 */       return false; 
/* 551 */     if (Math.abs(this.ctrlx - bezier.ctrlx) > 1.0E-12D)
/* 551 */       return false; 
/* 552 */     if (Math.abs(this.ctrly - bezier.ctrly) > 1.0E-12D)
/* 552 */       return false; 
/* 553 */     if (Math.abs(this.x2 - bezier.x2) > 1.0E-12D)
/* 553 */       return false; 
/* 554 */     if (Math.abs(this.y2 - bezier.y2) > 1.0E-12D)
/* 554 */       return false; 
/* 556 */     return true;
/*     */   }
/*     */   
/*     */   public QuadBezierCurve2D clone() {
/* 561 */     return new QuadBezierCurve2D(this.x1, this.y1, this.ctrlx, this.ctrly, this.x2, this.y2);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\spline\QuadBezierCurve2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
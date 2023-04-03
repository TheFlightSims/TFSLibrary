/*     */ package math.geom2d.conic;
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
/*     */ import math.geom2d.curve.AbstractSmoothCurve2D;
/*     */ import math.geom2d.curve.ContinuousCurve2D;
/*     */ import math.geom2d.curve.Curve2D;
/*     */ import math.geom2d.curve.CurveArray2D;
/*     */ import math.geom2d.curve.CurveSet2D;
/*     */ import math.geom2d.curve.Curves2D;
/*     */ import math.geom2d.curve.SmoothCurve2D;
/*     */ import math.geom2d.domain.Boundary2D;
/*     */ import math.geom2d.domain.ContinuousOrientedCurve2D;
/*     */ import math.geom2d.domain.Contour2D;
/*     */ import math.geom2d.domain.Domain2D;
/*     */ import math.geom2d.domain.GenericDomain2D;
/*     */ import math.geom2d.domain.OrientedCurve2D;
/*     */ import math.geom2d.line.LinearShape2D;
/*     */ import math.geom2d.line.StraightLine2D;
/*     */ import math.utils.EqualUtils;
/*     */ 
/*     */ public class Parabola2D extends AbstractSmoothCurve2D implements Contour2D, Conic2D, Cloneable {
/*     */   public static final Parabola2D create(Point2D vertex, Point2D focus) {
/*  73 */     double p = Point2D.distance(vertex, focus);
/*  74 */     double theta = Angle2D.horizontalAngle(vertex, focus) - 1.5707963267948966D;
/*  75 */     return new Parabola2D(vertex, 1.0D / 4.0D * p, theta);
/*     */   }
/*     */   
/*  83 */   protected double xv = 0.0D, yv = 0.0D;
/*     */   
/*  86 */   protected double theta = 0.0D;
/*     */   
/*  89 */   protected double a = 1.0D;
/*     */   
/*     */   private boolean debug = false;
/*     */   
/*     */   public Parabola2D(Point2D vertex, double a, double theta) {
/* 102 */     this(vertex.x(), vertex.y(), a, theta);
/*     */   }
/*     */   
/*     */   public Parabola2D(double xv, double yv, double a, double theta) {
/* 107 */     this.xv = xv;
/* 108 */     this.yv = yv;
/* 109 */     this.a = a;
/* 110 */     this.theta = theta;
/*     */   }
/*     */   
/*     */   public Point2D getFocus() {
/* 120 */     double c = 1.0D / this.a / 4.0D;
/* 121 */     return new Point2D(this.xv - c * Math.sin(this.theta), this.yv + c * Math.cos(this.theta));
/*     */   }
/*     */   
/*     */   public double getParameter() {
/* 125 */     return this.a;
/*     */   }
/*     */   
/*     */   public double getFocusDistance() {
/* 129 */     return 1.0D / 4.0D * this.a;
/*     */   }
/*     */   
/*     */   public Point2D getVertex() {
/* 133 */     return new Point2D(this.xv, this.yv);
/*     */   }
/*     */   
/*     */   public Vector2D getVector1() {
/* 140 */     Vector2D vect = new Vector2D(1.0D, 0.0D);
/* 141 */     return vect.transform(AffineTransform2D.createRotation(this.theta));
/*     */   }
/*     */   
/*     */   public Vector2D getVector2() {
/* 148 */     Vector2D vect = new Vector2D(1.0D, 0.0D);
/* 149 */     return vect.transform(AffineTransform2D.createRotation(this.theta + 1.5707963267948966D));
/*     */   }
/*     */   
/*     */   public double getAngle() {
/* 157 */     return this.theta;
/*     */   }
/*     */   
/*     */   public boolean isDirect() {
/* 164 */     return (this.a > 0.0D);
/*     */   }
/*     */   
/*     */   private Point2D formatPoint(Point2D point) {
/* 175 */     Point2D p2 = new Point2D(point);
/* 176 */     p2 = p2.transform(AffineTransform2D.createTranslation(-this.xv, -this.yv));
/* 177 */     p2 = p2.transform(AffineTransform2D.createRotation(-this.theta));
/* 178 */     p2 = p2.transform(AffineTransform2D.createScaling(1.0D, 1.0D / this.a));
/* 179 */     return p2;
/*     */   }
/*     */   
/*     */   private LinearShape2D formatLine(LinearShape2D line) {
/* 190 */     line = line.transform(AffineTransform2D.createTranslation(-this.xv, -this.yv));
/* 191 */     line = line.transform(AffineTransform2D.createRotation(-this.theta));
/* 192 */     line = line.transform(AffineTransform2D.createScaling(1.0D, 1.0D / this.a));
/* 193 */     return line;
/*     */   }
/*     */   
/*     */   public Conic2D.Type conicType() {
/* 200 */     return Conic2D.Type.PARABOLA;
/*     */   }
/*     */   
/*     */   public double[] conicCoefficients() {
/* 205 */     AffineTransform2D transform = 
/* 206 */       AffineTransform2D.createRotation(this.theta).chain(
/* 207 */         AffineTransform2D.createTranslation(this.xv, this.yv));
/* 210 */     double[][] coefs = transform.invert().affineMatrix();
/* 211 */     double m00 = coefs[0][0];
/* 212 */     double m01 = coefs[0][1];
/* 213 */     double m02 = coefs[0][2];
/* 214 */     double m10 = coefs[1][0];
/* 215 */     double m11 = coefs[1][1];
/* 216 */     double m12 = coefs[1][2];
/* 220 */     double A = this.a * m00 * m00;
/* 221 */     double B = 2.0D * this.a * m00 * m01;
/* 222 */     double C = this.a * m01 * m01;
/* 223 */     double D = 2.0D * this.a * m00 * m02 - m10;
/* 224 */     double E = 2.0D * this.a * m01 * m02 - m11;
/* 225 */     double F = this.a * m02 * m02 - m12;
/* 228 */     return new double[] { A, B, C, D, E, F };
/*     */   }
/*     */   
/*     */   public double eccentricity() {
/* 235 */     return 1.0D;
/*     */   }
/*     */   
/*     */   public Domain2D domain() {
/* 242 */     return (Domain2D)new GenericDomain2D(this);
/*     */   }
/*     */   
/*     */   public double windingAngle(Point2D point) {
/* 249 */     if (isDirect()) {
/* 250 */       if (isInside(point))
/* 251 */         return 6.283185307179586D; 
/* 253 */       return 0.0D;
/*     */     } 
/* 255 */     if (isInside(point))
/* 256 */       return 0.0D; 
/* 258 */     return -6.283185307179586D;
/*     */   }
/*     */   
/*     */   public double signedDistance(Point2D p) {
/* 263 */     return signedDistance(p.x(), p.y());
/*     */   }
/*     */   
/*     */   public double signedDistance(double x, double y) {
/* 267 */     if (isInside(new Point2D(x, y)))
/* 268 */       return -distance(x, y); 
/* 269 */     return -distance(x, y);
/*     */   }
/*     */   
/*     */   public boolean isInside(Point2D point) {
/* 275 */     Point2D p2 = formatPoint(point);
/* 278 */     double x = p2.x();
/* 279 */     double y = p2.y();
/* 282 */     return ((y > x * x)) ^ ((this.a < 0.0D));
/*     */   }
/*     */   
/*     */   public Vector2D tangent(double t) {
/* 289 */     Vector2D vect = new Vector2D(1.0D, 2.0D * this.a * t);
/* 290 */     return vect.transform(AffineTransform2D.createRotation(this.theta));
/*     */   }
/*     */   
/*     */   public double curvature(double t) {
/* 297 */     return 2.0D * this.a / Math.pow(Math.hypot(1.0D, 2.0D * this.a * t), 3.0D);
/*     */   }
/*     */   
/*     */   public Collection<? extends Parabola2D> continuousCurves() {
/* 307 */     return wrapCurve((ContinuousCurve2D)this);
/*     */   }
/*     */   
/*     */   public boolean isClosed() {
/* 314 */     return false;
/*     */   }
/*     */   
/*     */   public double t0() {
/* 325 */     return Double.NEGATIVE_INFINITY;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public double getT0() {
/* 333 */     return t0();
/*     */   }
/*     */   
/*     */   public double t1() {
/* 341 */     return Double.POSITIVE_INFINITY;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public double getT1() {
/* 349 */     return t1();
/*     */   }
/*     */   
/*     */   public Point2D point(double t) {
/* 354 */     Point2D point = new Point2D(t, this.a * t * t);
/* 355 */     point = AffineTransform2D.createRotation(this.theta).transform(point);
/* 356 */     point = AffineTransform2D.createTranslation(this.xv, this.yv).transform(point);
/* 357 */     return point;
/*     */   }
/*     */   
/*     */   public double position(Point2D point) {
/* 367 */     return formatPoint(point).x();
/*     */   }
/*     */   
/*     */   public double project(Point2D point) {
/* 377 */     return formatPoint(point).x();
/*     */   }
/*     */   
/*     */   public Collection<Point2D> intersections(LinearShape2D line) {
/* 382 */     LinearShape2D line2 = formatLine(line);
/* 383 */     double dx = line2.direction().x();
/* 384 */     double dy = line2.direction().y();
/* 386 */     ArrayList<Point2D> points = new ArrayList<Point2D>();
/* 389 */     if (Math.abs(dx) < 1.0E-12D) {
/* 390 */       if (this.debug)
/* 391 */         System.out.println("intersect parabola with vertical line "); 
/* 392 */       double d = line2.origin().x();
/* 393 */       Point2D point2D = new Point2D(d, d * d);
/* 394 */       if (line2.contains(point2D))
/* 395 */         points.add(line.point(line2.position(point2D))); 
/* 396 */       return points;
/*     */     } 
/* 400 */     Point2D origin = line2.origin();
/* 401 */     double x0 = origin.x();
/* 402 */     double y0 = origin.y();
/* 405 */     double k = dy / dx;
/* 406 */     double yl = k * x0 - y0;
/* 407 */     double delta = k * k - 4.0D * yl;
/* 410 */     if (delta < 0.0D)
/* 411 */       return points; 
/* 418 */     StraightLine2D support = line2.supportingLine();
/* 421 */     double x = (k - Math.sqrt(delta)) * 0.5D;
/* 422 */     Point2D point = new Point2D(x, x * x);
/* 423 */     if (line2.contains(support.projectedPoint(point)))
/* 424 */       points.add(line.point(line2.position(point))); 
/* 427 */     x = (k + Math.sqrt(delta)) * 0.5D;
/* 428 */     point = new Point2D(x, x * x);
/* 429 */     if (line2.contains(support.projectedPoint(point)))
/* 430 */       points.add(line.point(line2.position(point))); 
/* 432 */     return points;
/*     */   }
/*     */   
/*     */   public Parabola2D reverse() {
/* 440 */     return new Parabola2D(this.xv, this.yv, -this.a, Angle2D.formatAngle(this.theta + Math.PI));
/*     */   }
/*     */   
/*     */   public ParabolaArc2D subCurve(double t0, double t1) {
/* 447 */     if (this.debug)
/* 448 */       System.out.println("theta = " + Math.toDegrees(this.theta)); 
/* 449 */     if (t1 < t0)
/* 450 */       return null; 
/* 451 */     return new ParabolaArc2D(this, t0, t1);
/*     */   }
/*     */   
/*     */   public double distance(Point2D p) {
/* 455 */     return distance(p.x(), p.y());
/*     */   }
/*     */   
/*     */   public double distance(double x, double y) {
/* 461 */     return (new ParabolaArc2D(this, -100.0D, 100.0D)).distance(x, y);
/*     */   }
/*     */   
/*     */   public GeneralPath appendPath(GeneralPath path) {
/* 470 */     throw new UnboundedShape2DException(this);
/*     */   }
/*     */   
/*     */   public void fill(Graphics2D g2) {
/* 475 */     throw new UnboundedShape2DException(this);
/*     */   }
/*     */   
/*     */   public boolean isBounded() {
/* 483 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 490 */     return false;
/*     */   }
/*     */   
/*     */   public CurveSet2D<ParabolaArc2D> clip(Box2D box) {
/* 501 */     CurveSet2D<SmoothCurve2D> set = Curves2D.clipSmoothCurve((SmoothCurve2D)this, box);
/* 504 */     CurveArray2D<ParabolaArc2D> result = 
/* 505 */       new CurveArray2D(set.size());
/* 508 */     for (Curve2D curve : set.curves()) {
/* 509 */       if (curve instanceof ParabolaArc2D)
/* 510 */         result.add(curve); 
/*     */     } 
/* 512 */     return (CurveSet2D<ParabolaArc2D>)result;
/*     */   }
/*     */   
/*     */   public Box2D boundingBox() {
/* 517 */     return new Box2D(
/* 518 */         Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 
/* 519 */         Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
/*     */   }
/*     */   
/*     */   public Parabola2D transform(AffineTransform2D trans) {
/* 529 */     Point2D vertex = getVertex().transform(trans);
/* 530 */     Point2D focus = getFocus().transform(trans);
/* 531 */     double a = 1.0D / 4.0D * Point2D.distance(vertex, focus);
/* 532 */     double theta = Angle2D.horizontalAngle(vertex, focus) - 1.5707963267948966D;
/* 535 */     if ((((this.a < 0.0D) ? 1 : 0) ^ trans.isDirect()) != 0)
/* 537 */       return new Parabola2D(vertex, a, theta); 
/* 540 */     return new Parabola2D(vertex, -a, theta + Math.PI);
/*     */   }
/*     */   
/*     */   public boolean contains(double x, double y) {
/* 548 */     Point2D p2 = formatPoint(new Point2D(x, y));
/* 551 */     double xp = p2.x();
/* 552 */     double yp = p2.y();
/* 555 */     return (Math.abs(yp - xp * xp) < 1.0E-12D);
/*     */   }
/*     */   
/*     */   public boolean contains(Point2D point) {
/* 559 */     return contains(point.x(), point.y());
/*     */   }
/*     */   
/*     */   public boolean almostEquals(GeometricObject2D obj, double eps) {
/* 569 */     if (this == obj)
/* 570 */       return true; 
/* 572 */     if (!(obj instanceof Parabola2D))
/* 573 */       return false; 
/* 574 */     Parabola2D parabola = (Parabola2D)obj;
/* 576 */     if (this.xv - parabola.xv > eps)
/* 577 */       return false; 
/* 578 */     if (this.yv - parabola.yv > eps)
/* 579 */       return false; 
/* 580 */     if (this.a - parabola.a > eps)
/* 581 */       return false; 
/* 582 */     if (!Angle2D.almostEquals(this.theta, parabola.theta, eps))
/* 583 */       return false; 
/* 585 */     return true;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 593 */     return String.format("Parabola2D(%f,%f,%f,%f)", new Object[] { Double.valueOf(this.xv), Double.valueOf(this.yv), Double.valueOf(this.a), Double.valueOf(this.theta) });
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 599 */     if (!(obj instanceof Parabola2D))
/* 600 */       return false; 
/* 601 */     Parabola2D that = (Parabola2D)obj;
/* 604 */     if (!EqualUtils.areEqual(this.xv, that.xv))
/* 605 */       return false; 
/* 606 */     if (!EqualUtils.areEqual(this.yv, that.yv))
/* 607 */       return false; 
/* 608 */     if (!EqualUtils.areEqual(this.a, that.a))
/* 609 */       return false; 
/* 610 */     if (!EqualUtils.areEqual(this.theta, that.theta))
/* 611 */       return false; 
/* 613 */     return true;
/*     */   }
/*     */   
/*     */   public Parabola2D clone() {
/* 618 */     return new Parabola2D(this.xv, this.yv, this.a, this.theta);
/*     */   }
/*     */   
/*     */   public Parabola2D() {}
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\conic\Parabola2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
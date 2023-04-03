/*     */ package math.geom2d.conic;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import math.geom2d.AffineTransform2D;
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
/*     */ import math.geom2d.domain.SmoothContour2D;
/*     */ import math.geom2d.domain.SmoothOrientedCurve2D;
/*     */ import math.geom2d.line.LinearShape2D;
/*     */ 
/*     */ public class HyperbolaBranch2D extends AbstractSmoothCurve2D implements SmoothContour2D, Cloneable {
/*     */   public static HyperbolaBranch2D create(Hyperbola2D hyperbola, boolean b) {
/*  32 */     return new HyperbolaBranch2D(hyperbola, b);
/*     */   }
/*     */   
/*  40 */   Hyperbola2D hyperbola = null;
/*     */   
/*     */   boolean positive = true;
/*     */   
/*     */   public HyperbolaBranch2D(Hyperbola2D hyperbola, boolean b) {
/*  59 */     this.hyperbola = hyperbola;
/*  60 */     this.positive = b;
/*     */   }
/*     */   
/*     */   public Hyperbola2D getHyperbola() {
/*  70 */     return this.hyperbola;
/*     */   }
/*     */   
/*     */   public boolean isPositiveBranch() {
/*  80 */     return this.positive;
/*     */   }
/*     */   
/*     */   public double curvature(double t) {
/*  92 */     double a = this.hyperbola.a;
/*  93 */     double b = this.hyperbola.b;
/*  94 */     double asih = a * Math.sinh(t);
/*  95 */     double bcoh = b * Math.cosh(t);
/*  96 */     return a * b / Math.pow(Math.hypot(bcoh, asih), 3.0D);
/*     */   }
/*     */   
/*     */   public Vector2D tangent(double t) {
/* 100 */     double dx, dy, a = this.hyperbola.a;
/* 101 */     double b = this.hyperbola.b;
/* 102 */     double theta = this.hyperbola.theta;
/* 104 */     if (this.positive) {
/* 105 */       dx = a * Math.sinh(t);
/* 106 */       dy = b * Math.cosh(t);
/*     */     } else {
/* 108 */       dx = -a * Math.sinh(t);
/* 109 */       dy = -b * Math.cosh(t);
/*     */     } 
/* 111 */     double cot = Math.cos(theta);
/* 112 */     double sit = Math.sin(theta);
/* 113 */     return new Vector2D(dx * cot - dy * sit, dx * sit + dy * cot);
/*     */   }
/*     */   
/*     */   public Domain2D domain() {
/* 120 */     return (Domain2D)new GenericDomain2D((Boundary2D)this);
/*     */   }
/*     */   
/*     */   public void fill(Graphics2D g2) {
/* 125 */     throw new UnboundedShape2DException(this);
/*     */   }
/*     */   
/*     */   public double signedDistance(Point2D point) {
/* 132 */     double dist = distance(point);
/* 133 */     return isInside(point) ? -dist : dist;
/*     */   }
/*     */   
/*     */   public double signedDistance(double x, double y) {
/* 137 */     return signedDistance(new Point2D(x, y));
/*     */   }
/*     */   
/*     */   public double windingAngle(Point2D point) {
/* 142 */     return 0.0D;
/*     */   }
/*     */   
/*     */   public boolean isInside(Point2D point) {
/* 146 */     if (this.hyperbola.isDirect()) {
/* 147 */       if (this.hyperbola.isInside(point))
/* 148 */         return true; 
/* 149 */       double d = this.hyperbola.toLocal(new Point2D(point)).x();
/* 150 */       return this.positive ? ((d < 0.0D)) : ((d > 0.0D));
/*     */     } 
/* 152 */     if (!this.hyperbola.isInside(point))
/* 153 */       return false; 
/* 154 */     double x = this.hyperbola.toLocal(new Point2D(point)).x();
/* 155 */     return this.positive ? ((x > 0.0D)) : ((x < 0.0D));
/*     */   }
/*     */   
/*     */   public Collection<? extends HyperbolaBranch2D> continuousCurves() {
/* 166 */     return wrapCurve((ContinuousCurve2D)this);
/*     */   }
/*     */   
/*     */   public boolean isClosed() {
/* 171 */     return false;
/*     */   }
/*     */   
/*     */   public GeneralPath appendPath(GeneralPath path) {
/* 176 */     throw new UnboundedShape2DException(this);
/*     */   }
/*     */   
/*     */   public Point2D point(double t) {
/*     */     double x;
/*     */     double y;
/* 184 */     if (Double.isInfinite(t))
/* 185 */       throw new UnboundedShape2DException(this); 
/* 188 */     if (this.positive) {
/* 189 */       x = Math.cosh(t);
/* 190 */       if (Double.isInfinite(x))
/* 191 */         x = Math.abs(t); 
/* 192 */       y = Math.sinh(t);
/* 193 */       if (Double.isInfinite(y))
/* 194 */         y = t; 
/*     */     } else {
/* 196 */       x = -Math.cosh(t);
/* 197 */       if (Double.isInfinite(x))
/* 198 */         x = -Math.abs(t); 
/* 199 */       y = -Math.sinh(t);
/* 200 */       if (Double.isInfinite(y))
/* 201 */         y = -t; 
/*     */     } 
/* 203 */     return this.hyperbola.toGlobal(new Point2D(x, y));
/*     */   }
/*     */   
/*     */   public double position(Point2D point) {
/* 207 */     Point2D pt = this.hyperbola.toLocal(new Point2D(point));
/* 208 */     double y = this.positive ? pt.y() : -pt.y();
/* 209 */     return Math.log(y + Math.hypot(y, 1.0D));
/*     */   }
/*     */   
/*     */   public double project(Point2D point) {
/* 213 */     Point2D pt = this.hyperbola.toLocal(new Point2D(point));
/* 214 */     double y = this.positive ? pt.y() : -pt.y();
/* 215 */     return Math.log(y + Math.hypot(y, 1.0D));
/*     */   }
/*     */   
/*     */   public HyperbolaBranch2D reverse() {
/* 219 */     Hyperbola2D hyper2 = new Hyperbola2D(this.hyperbola.xc, this.hyperbola.yc, 
/* 220 */         this.hyperbola.a, this.hyperbola.b, this.hyperbola.theta, !this.hyperbola.direct);
/* 221 */     return new HyperbolaBranch2D(hyper2, this.positive);
/*     */   }
/*     */   
/*     */   public HyperbolaBranchArc2D subCurve(double t0, double t1) {
/* 229 */     return new HyperbolaBranchArc2D(this, t0, t1);
/*     */   }
/*     */   
/*     */   public double t0() {
/* 236 */     return Double.NEGATIVE_INFINITY;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public double getT0() {
/* 244 */     return t0();
/*     */   }
/*     */   
/*     */   public double t1() {
/* 251 */     return Double.POSITIVE_INFINITY;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public double getT1() {
/* 259 */     return t1();
/*     */   }
/*     */   
/*     */   public Collection<Point2D> intersections(LinearShape2D line) {
/* 264 */     Collection<Point2D> inters = this.hyperbola.intersections(line);
/* 267 */     Collection<Point2D> result = new ArrayList<Point2D>();
/* 268 */     for (Point2D point : inters) {
/* 269 */       if ((((this.hyperbola.toLocal(point).x() > 0.0D) ? 1 : 0) ^ this.positive) == 0)
/* 270 */         result.add(point); 
/*     */     } 
/* 274 */     return result;
/*     */   }
/*     */   
/*     */   public Box2D boundingBox() {
/* 283 */     return Box2D.INFINITE_BOX;
/*     */   }
/*     */   
/*     */   public CurveSet2D<? extends HyperbolaBranchArc2D> clip(Box2D box) {
/* 294 */     CurveSet2D<SmoothCurve2D> set = Curves2D.clipSmoothCurve((SmoothCurve2D)this, box);
/* 297 */     CurveArray2D<HyperbolaBranchArc2D> result = 
/* 298 */       new CurveArray2D(set.size());
/* 301 */     for (Curve2D curve : set.curves()) {
/* 302 */       if (curve instanceof HyperbolaBranchArc2D)
/* 303 */         result.add(curve); 
/*     */     } 
/* 305 */     return (CurveSet2D<? extends HyperbolaBranchArc2D>)result;
/*     */   }
/*     */   
/*     */   public double distance(Point2D point) {
/* 309 */     Point2D projected = point(project(new Point2D(point)));
/* 310 */     return projected.distance(point);
/*     */   }
/*     */   
/*     */   public double distance(double x, double y) {
/* 314 */     Point2D projected = point(project(new Point2D(x, y)));
/* 315 */     return projected.distance(x, y);
/*     */   }
/*     */   
/*     */   public boolean isBounded() {
/* 320 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 327 */     return false;
/*     */   }
/*     */   
/*     */   public HyperbolaBranch2D transform(AffineTransform2D trans) {
/* 332 */     Hyperbola2D hyperbola = this.hyperbola.transform(trans);
/* 333 */     Point2D base = point(0.0D).transform(trans);
/* 336 */     double d1 = hyperbola.positiveBranch().distance(base);
/* 337 */     double d2 = hyperbola.negativeBranch().distance(base);
/* 340 */     return new HyperbolaBranch2D(hyperbola, (d1 < d2));
/*     */   }
/*     */   
/*     */   public boolean contains(Point2D point) {
/* 344 */     return contains(point.x(), point.y());
/*     */   }
/*     */   
/*     */   public boolean contains(double x, double y) {
/* 348 */     if (!this.hyperbola.contains(x, y))
/* 349 */       return false; 
/* 350 */     Point2D point = this.hyperbola.toLocal(new Point2D(x, y));
/* 351 */     return (point.x() > 0.0D);
/*     */   }
/*     */   
/*     */   public boolean almostEquals(GeometricObject2D obj, double eps) {
/* 361 */     if (this == obj)
/* 362 */       return true; 
/* 364 */     if (!(obj instanceof HyperbolaBranch2D))
/* 365 */       return false; 
/* 366 */     HyperbolaBranch2D branch = (HyperbolaBranch2D)obj;
/* 368 */     if (!this.hyperbola.almostEquals((GeometricObject2D)branch.hyperbola, eps))
/* 368 */       return false; 
/* 369 */     return (this.positive == branch.positive);
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 377 */     if (this == obj)
/* 378 */       return true; 
/* 380 */     if (!(obj instanceof HyperbolaBranch2D))
/* 381 */       return false; 
/* 382 */     HyperbolaBranch2D branch = (HyperbolaBranch2D)obj;
/* 384 */     if (!this.hyperbola.equals(branch.hyperbola))
/* 384 */       return false; 
/* 385 */     return (this.positive == branch.positive);
/*     */   }
/*     */   
/*     */   public HyperbolaBranch2D clone() {
/* 390 */     return new HyperbolaBranch2D(this.hyperbola.clone(), this.positive);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\conic\HyperbolaBranch2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
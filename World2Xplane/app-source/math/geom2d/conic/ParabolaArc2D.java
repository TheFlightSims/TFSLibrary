/*     */ package math.geom2d.conic;
/*     */ 
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
/*     */ import math.geom2d.domain.ContinuousOrientedCurve2D;
/*     */ import math.geom2d.domain.OrientedCurve2D;
/*     */ import math.geom2d.domain.SmoothOrientedCurve2D;
/*     */ import math.geom2d.line.LinearShape2D;
/*     */ import math.geom2d.line.StraightLine2D;
/*     */ import math.geom2d.polygon.LinearCurve2D;
/*     */ import math.geom2d.polygon.Polyline2D;
/*     */ import math.utils.EqualUtils;
/*     */ 
/*     */ public class ParabolaArc2D extends AbstractSmoothCurve2D implements SmoothOrientedCurve2D, Cloneable {
/*     */   public static ParabolaArc2D create(Parabola2D parabola, double t0, double t1) {
/*  56 */     return new ParabolaArc2D(parabola, t0, t1);
/*     */   }
/*     */   
/*  66 */   protected Parabola2D parabola = new Parabola2D();
/*     */   
/*  71 */   double t0 = 0.0D;
/*     */   
/*  76 */   double t1 = 1.0D;
/*     */   
/*     */   public ParabolaArc2D(Parabola2D parabola, double t0, double t1) {
/*  82 */     this.parabola = parabola;
/*  83 */     this.t0 = t0;
/*  84 */     this.t1 = t1;
/*     */   }
/*     */   
/*     */   public Parabola2D getParabola() {
/*  91 */     return this.parabola;
/*     */   }
/*     */   
/*     */   public double windingAngle(Point2D point) {
/*     */     double angle0, angle1;
/* 100 */     boolean direct = this.parabola.isDirect();
/* 101 */     boolean inside = isInside(point);
/* 103 */     if (Double.isInfinite(this.t0)) {
/* 104 */       angle0 = this.parabola.getAngle() + (direct ? true : -1) * Math.PI / 2.0D;
/*     */     } else {
/* 106 */       angle0 = Angle2D.horizontalAngle(point, this.parabola.point(this.t0));
/*     */     } 
/* 109 */     if (Double.isInfinite(this.t1)) {
/* 110 */       angle1 = this.parabola.getAngle() + (direct ? true : -1) * Math.PI / 2.0D;
/*     */     } else {
/* 112 */       angle1 = Angle2D.horizontalAngle(point, this.parabola.point(this.t1));
/*     */     } 
/* 115 */     if (inside) {
/* 117 */       if (angle0 > angle1)
/* 118 */         return 6.283185307179586D - angle0 + angle1; 
/* 120 */       return angle1 - angle0;
/*     */     } 
/* 123 */     if (angle0 > angle1)
/* 124 */       return angle1 - angle0; 
/* 126 */     return angle1 - angle0 - 6.283185307179586D;
/*     */   }
/*     */   
/*     */   public double signedDistance(Point2D p) {
/* 131 */     return signedDistance(p.x(), p.y());
/*     */   }
/*     */   
/*     */   public double signedDistance(double x, double y) {
/* 135 */     if (isInside(new Point2D(x, y)))
/* 136 */       return -distance(x, y); 
/* 137 */     return -distance(x, y);
/*     */   }
/*     */   
/*     */   public boolean isInside(Point2D point) {
/* 141 */     boolean direct = this.parabola.isDirect();
/* 142 */     boolean inside = this.parabola.isInside(point);
/* 143 */     if (inside && direct)
/* 144 */       return true; 
/* 145 */     if (!inside && !direct)
/* 146 */       return false; 
/* 148 */     double pos = this.parabola.project(point);
/* 150 */     if (pos < this.t0) {
/* 151 */       Point2D p0 = this.parabola.point(this.t0);
/* 152 */       Vector2D v0 = this.parabola.tangent(this.t0);
/* 153 */       StraightLine2D line0 = new StraightLine2D(p0, v0);
/* 154 */       return line0.isInside(point);
/*     */     } 
/* 157 */     if (pos > this.t1) {
/* 158 */       Point2D p1 = this.parabola.point(this.t1);
/* 159 */       Vector2D v1 = this.parabola.tangent(this.t1);
/* 160 */       StraightLine2D line1 = new StraightLine2D(p1, v1);
/* 161 */       return line1.isInside(point);
/*     */     } 
/* 163 */     return !direct;
/*     */   }
/*     */   
/*     */   public Vector2D tangent(double t) {
/* 170 */     return this.parabola.tangent(t);
/*     */   }
/*     */   
/*     */   public double curvature(double t) {
/* 177 */     return this.parabola.curvature(t);
/*     */   }
/*     */   
/*     */   public boolean isClosed() {
/* 185 */     return false;
/*     */   }
/*     */   
/*     */   public Polyline2D asPolyline(int n) {
/* 190 */     if (!isBounded())
/* 191 */       throw new UnboundedShape2DException(this); 
/* 194 */     double t0 = t0();
/* 195 */     double dt = (t1() - t0) / n;
/* 198 */     Point2D[] points = new Point2D[n + 1];
/* 199 */     for (int i = 0; i < n + 1; i++)
/* 200 */       points[i] = point(t0 + i * dt); 
/* 202 */     return new Polyline2D(points);
/*     */   }
/*     */   
/*     */   public double t0() {
/* 212 */     return this.t0;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public double getT0() {
/* 220 */     return t0();
/*     */   }
/*     */   
/*     */   public double t1() {
/* 227 */     return this.t1;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public double getT1() {
/* 235 */     return t1();
/*     */   }
/*     */   
/*     */   public Point2D point(double t) {
/* 239 */     t = Math.min(Math.max(t, this.t0), this.t1);
/* 240 */     return this.parabola.point(t);
/*     */   }
/*     */   
/*     */   public double position(Point2D point) {
/* 244 */     if (!this.parabola.contains(point))
/* 245 */       return Double.NaN; 
/* 246 */     double t = this.parabola.position(point);
/* 247 */     if (t - this.t0 < -1.0E-12D)
/* 248 */       return Double.NaN; 
/* 249 */     if (this.t1 - t < 1.0E-12D)
/* 250 */       return Double.NaN; 
/* 251 */     return t;
/*     */   }
/*     */   
/*     */   public double project(Point2D point) {
/* 255 */     double t = this.parabola.project(point);
/* 256 */     return Math.min(Math.max(t, this.t0), this.t1);
/*     */   }
/*     */   
/*     */   public Collection<Point2D> intersections(LinearShape2D line) {
/* 260 */     Collection<Point2D> inters0 = this.parabola.intersections(line);
/* 261 */     ArrayList<Point2D> inters = new ArrayList<Point2D>(2);
/* 262 */     for (Point2D point : inters0) {
/* 263 */       double pos = this.parabola.position(point);
/* 264 */       if (pos > this.t0 && pos < this.t1)
/* 265 */         inters.add(point); 
/*     */     } 
/* 268 */     return inters;
/*     */   }
/*     */   
/*     */   public ParabolaArc2D reverse() {
/* 276 */     return new ParabolaArc2D(this.parabola.reverse(), -this.t1, -this.t0);
/*     */   }
/*     */   
/*     */   public ParabolaArc2D subCurve(double t0, double t1) {
/* 280 */     if (t1 < t0)
/* 281 */       return null; 
/* 282 */     t0 = Math.max(this.t0, t0);
/* 283 */     t1 = Math.min(this.t1, t1);
/* 284 */     return new ParabolaArc2D(this.parabola, t0, t1);
/*     */   }
/*     */   
/*     */   public double distance(Point2D p) {
/* 291 */     return distance(p.x(), p.y());
/*     */   }
/*     */   
/*     */   public double distance(double x, double y) {
/* 296 */     return asPolyline(100).distance(x, y);
/*     */   }
/*     */   
/*     */   public boolean isBounded() {
/* 303 */     if (this.t0 == Double.NEGATIVE_INFINITY)
/* 304 */       return false; 
/* 305 */     if (this.t1 == Double.POSITIVE_INFINITY)
/* 306 */       return false; 
/* 307 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 314 */     return (this.t1 <= this.t0);
/*     */   }
/*     */   
/*     */   public CurveSet2D<? extends ParabolaArc2D> clip(Box2D box) {
/* 325 */     CurveSet2D<SmoothCurve2D> set = Curves2D.clipSmoothCurve((SmoothCurve2D)this, box);
/* 328 */     CurveArray2D<ParabolaArc2D> result = 
/* 329 */       new CurveArray2D(set.size());
/* 332 */     for (Curve2D curve : set.curves()) {
/* 333 */       if (curve instanceof ParabolaArc2D)
/* 334 */         result.add(curve); 
/*     */     } 
/* 336 */     return (CurveSet2D<? extends ParabolaArc2D>)result;
/*     */   }
/*     */   
/*     */   public Box2D boundingBox() {
/* 341 */     return asPolyline(100).boundingBox();
/*     */   }
/*     */   
/*     */   public ParabolaArc2D transform(AffineTransform2D trans) {
/* 345 */     Parabola2D par = this.parabola.transform(trans);
/* 348 */     double startPos = Double.isInfinite(this.t0) ? Double.NEGATIVE_INFINITY : 
/* 349 */       par.project(firstPoint().transform(trans));
/* 350 */     double endPos = Double.isInfinite(this.t1) ? Double.POSITIVE_INFINITY : par
/* 351 */       .project(lastPoint().transform(trans));
/* 354 */     return new ParabolaArc2D(par, startPos, endPos);
/*     */   }
/*     */   
/*     */   public boolean contains(double x, double y) {
/* 362 */     if (!this.parabola.contains(x, y))
/* 363 */       return false; 
/* 366 */     double t = this.parabola.position(new Point2D(x, y));
/* 367 */     if (t < this.t0)
/* 368 */       return false; 
/* 369 */     if (t > this.t1)
/* 370 */       return false; 
/* 372 */     return true;
/*     */   }
/*     */   
/*     */   public boolean contains(Point2D point) {
/* 376 */     return contains(point.x(), point.y());
/*     */   }
/*     */   
/*     */   public GeneralPath appendPath(GeneralPath path) {
/* 384 */     if (!isBounded())
/* 385 */       throw new UnboundedShape2DException(this); 
/* 388 */     Point2D p1 = firstPoint();
/* 389 */     Point2D p2 = lastPoint();
/* 390 */     Vector2D v1 = tangent(this.t0);
/* 391 */     Vector2D v2 = tangent(this.t1);
/* 394 */     StraightLine2D line1 = new StraightLine2D(p1, v1);
/* 395 */     StraightLine2D line2 = new StraightLine2D(p2, v2);
/* 398 */     Point2D pc = line1.intersection((LinearShape2D)line2);
/* 401 */     path.quadTo(pc.x(), pc.y(), p2.x(), p2.y());
/* 402 */     return path;
/*     */   }
/*     */   
/*     */   public GeneralPath getGeneralPath() {
/* 406 */     if (!isBounded())
/* 407 */       throw new UnboundedShape2DException(this); 
/* 408 */     return asPolyline(32).asGeneralPath();
/*     */   }
/*     */   
/*     */   public boolean almostEquals(GeometricObject2D obj, double eps) {
/* 419 */     if (this == obj)
/* 420 */       return true; 
/* 422 */     if (!(obj instanceof ParabolaArc2D))
/* 423 */       return false; 
/* 424 */     ParabolaArc2D arc = (ParabolaArc2D)obj;
/* 426 */     if (!this.parabola.almostEquals((GeometricObject2D)arc.parabola, eps))
/* 427 */       return false; 
/* 428 */     if (Math.abs(this.t0 - arc.t0) > eps)
/* 429 */       return false; 
/* 430 */     if (Math.abs(this.t1 - arc.t1) > eps)
/* 431 */       return false; 
/* 433 */     return true;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 441 */     return String.format("ParabolaArc2D(%f,%f,%f,%f,%f,%f)", new Object[] { Double.valueOf(this.parabola.xv), Double.valueOf(this.parabola.yv), Double.valueOf(this.parabola.a), Double.valueOf(this.parabola.theta), Double.valueOf(this.t0), Double.valueOf(this.t1) });
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 447 */     if (!(obj instanceof ParabolaArc2D))
/* 448 */       return false; 
/* 449 */     ParabolaArc2D that = (ParabolaArc2D)obj;
/* 451 */     if (!this.parabola.equals(that.parabola))
/* 452 */       return false; 
/* 453 */     if (!EqualUtils.areEqual(this.t0, that.t0))
/* 454 */       return false; 
/* 455 */     if (!EqualUtils.areEqual(this.t1, that.t1))
/* 456 */       return false; 
/* 458 */     return true;
/*     */   }
/*     */   
/*     */   public ParabolaArc2D clone() {
/* 463 */     return new ParabolaArc2D(this.parabola.clone(), this.t0, this.t1);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\conic\ParabolaArc2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
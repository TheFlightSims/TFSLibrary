/*     */ package math.geom2d.line;
/*     */ 
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import math.geom2d.AffineTransform2D;
/*     */ import math.geom2d.Box2D;
/*     */ import math.geom2d.GeometricObject2D;
/*     */ import math.geom2d.Point2D;
/*     */ import math.geom2d.Shape2D;
/*     */ import math.geom2d.UnboundedShape2DException;
/*     */ import math.geom2d.circulinear.CirculinearContinuousCurve2D;
/*     */ import math.geom2d.circulinear.CirculinearCurve2D;
/*     */ import math.geom2d.circulinear.CirculinearElement2D;
/*     */ import math.geom2d.curve.ContinuousCurve2D;
/*     */ import math.geom2d.curve.Curve2D;
/*     */ import math.geom2d.curve.SmoothCurve2D;
/*     */ import math.geom2d.domain.ContinuousOrientedCurve2D;
/*     */ import math.geom2d.domain.OrientedCurve2D;
/*     */ import math.geom2d.domain.SmoothOrientedCurve2D;
/*     */ import math.utils.EqualUtils;
/*     */ 
/*     */ public class LineArc2D extends AbstractLine2D implements SmoothOrientedCurve2D, Cloneable {
/*     */   public static LineArc2D create(Point2D p1, Point2D p2, double t0, double t1) {
/*  60 */     return new LineArc2D(p1, p2, t0, t1);
/*     */   }
/*     */   
/*  68 */   protected double t0 = 0.0D;
/*     */   
/*  71 */   protected double t1 = 1.0D;
/*     */   
/*     */   public LineArc2D(Point2D point1, Point2D point2, double t0, double t1) {
/*  85 */     this(point1.x(), point1.y(), point2.x() - point1.x(), point2.y() - point1.y(), t0, t1);
/*     */   }
/*     */   
/*     */   public LineArc2D(LinearShape2D line, double t0, double t1) {
/*  97 */     super(line);
/*  98 */     this.t0 = t0;
/*  99 */     this.t1 = t1;
/*     */   }
/*     */   
/*     */   public LineArc2D(LineArc2D line) {
/* 108 */     this(line.x0, line.y0, line.dx, line.dy, line.t0, line.t1);
/*     */   }
/*     */   
/*     */   public LineArc2D(double x1, double y1, double dx, double dy, double t0, double t1) {
/* 124 */     super(x1, y1, dx, dy);
/* 125 */     this.t0 = t0;
/* 126 */     this.t1 = t1;
/*     */   }
/*     */   
/*     */   public double length() {
/* 138 */     if (isBounded())
/* 139 */       return firstPoint().distance(lastPoint()); 
/* 141 */     return Double.POSITIVE_INFINITY;
/*     */   }
/*     */   
/*     */   public double getX1() {
/* 145 */     if (this.t0 != Double.NEGATIVE_INFINITY)
/* 146 */       return this.x0 + this.t0 * this.dx; 
/* 148 */     return Double.NEGATIVE_INFINITY;
/*     */   }
/*     */   
/*     */   public double getY1() {
/* 152 */     if (this.t0 != Double.NEGATIVE_INFINITY)
/* 153 */       return this.y0 + this.t0 * this.dy; 
/* 155 */     return Double.NEGATIVE_INFINITY;
/*     */   }
/*     */   
/*     */   public double getX2() {
/* 159 */     if (this.t1 != Double.POSITIVE_INFINITY)
/* 160 */       return this.x0 + this.t1 * this.dx; 
/* 162 */     return Double.POSITIVE_INFINITY;
/*     */   }
/*     */   
/*     */   public double getY2() {
/* 166 */     if (this.t1 != Double.POSITIVE_INFINITY)
/* 167 */       return this.y0 + this.t1 * this.dy; 
/* 169 */     return Double.POSITIVE_INFINITY;
/*     */   }
/*     */   
/*     */   public LineArc2D parallel(double d) {
/* 179 */     double d2 = d / Math.hypot(this.dx, this.dy);
/* 180 */     return new LineArc2D(this.x0 + this.dy * d2, this.y0 - this.dx * d2, this.dx, this.dy, this.t0, this.t1);
/*     */   }
/*     */   
/*     */   public double t0() {
/* 192 */     return this.t0;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public double getT0() {
/* 200 */     return t0();
/*     */   }
/*     */   
/*     */   public double t1() {
/* 208 */     return this.t1;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public double getT1() {
/* 216 */     return t1();
/*     */   }
/*     */   
/*     */   public Point2D point(double t) {
/* 220 */     if (t < this.t0)
/* 221 */       t = this.t0; 
/* 222 */     if (t > this.t1)
/* 223 */       t = this.t1; 
/* 225 */     if (Double.isInfinite(t))
/* 226 */       throw new UnboundedShape2DException(this); 
/* 228 */     return new Point2D(this.x0 + this.dx * t, this.y0 + this.dy * t);
/*     */   }
/*     */   
/*     */   public Point2D firstPoint() {
/* 239 */     if (!Double.isInfinite(this.t0))
/* 240 */       return new Point2D(this.x0 + this.t0 * this.dx, this.y0 + this.t0 * this.dy); 
/* 242 */     throw new UnboundedShape2DException(this);
/*     */   }
/*     */   
/*     */   public Point2D lastPoint() {
/* 253 */     if (!Double.isInfinite(this.t1))
/* 254 */       return new Point2D(this.x0 + this.t1 * this.dx, this.y0 + this.t1 * this.dy); 
/* 256 */     throw new UnboundedShape2DException(this);
/*     */   }
/*     */   
/*     */   public Collection<Point2D> singularPoints() {
/* 261 */     ArrayList<Point2D> list = new ArrayList<Point2D>(2);
/* 262 */     if (this.t0 != Double.NEGATIVE_INFINITY)
/* 263 */       list.add(firstPoint()); 
/* 264 */     if (this.t1 != Double.POSITIVE_INFINITY)
/* 265 */       list.add(lastPoint()); 
/* 266 */     return list;
/*     */   }
/*     */   
/*     */   public boolean isSingular(double pos) {
/* 271 */     if (Math.abs(pos - this.t0) < 1.0E-12D)
/* 272 */       return true; 
/* 273 */     if (Math.abs(pos - this.t1) < 1.0E-12D)
/* 274 */       return true; 
/* 275 */     return false;
/*     */   }
/*     */   
/*     */   public Collection<? extends LineArc2D> continuousCurves() {
/* 280 */     return wrapCurve((ContinuousCurve2D)this);
/*     */   }
/*     */   
/*     */   public LineArc2D reverse() {
/* 288 */     return new LineArc2D(this.x0, this.y0, -this.dx, -this.dy, -this.t1, -this.t0);
/*     */   }
/*     */   
/*     */   public LineArc2D subCurve(double t0, double t1) {
/* 297 */     t0 = Math.max(t0, t0());
/* 298 */     t1 = Math.min(t1, t1());
/* 299 */     return new LineArc2D(this, t0, t1);
/*     */   }
/*     */   
/*     */   public boolean isBounded() {
/* 309 */     return (this.t0 != Double.NEGATIVE_INFINITY && this.t1 != Double.POSITIVE_INFINITY);
/*     */   }
/*     */   
/*     */   public Box2D boundingBox() {
/* 316 */     return new Box2D(this.x0 + this.t0 * this.dx, this.x0 + this.t1 * this.dx, this.y0 + this.t0 * this.dy, this.y0 + this.t1 * this.dy);
/*     */   }
/*     */   
/*     */   public boolean contains(Point2D pt) {
/* 324 */     return contains(pt.x(), pt.y());
/*     */   }
/*     */   
/*     */   public boolean contains(double xp, double yp) {
/* 328 */     if (!supportContains(xp, yp))
/* 329 */       return false; 
/* 332 */     double t = positionOnLine(xp, yp);
/* 334 */     if (t - this.t0 < -1.0E-12D)
/* 335 */       return false; 
/* 336 */     if (t - this.t1 > 1.0E-12D)
/* 337 */       return false; 
/* 339 */     return true;
/*     */   }
/*     */   
/*     */   public GeneralPath getGeneralPath() {
/* 343 */     if (!isBounded())
/* 344 */       throw new UnboundedShape2DException(this); 
/* 345 */     GeneralPath path = new GeneralPath();
/* 346 */     path.moveTo((float)(this.x0 + this.t0 * this.dx), (float)(this.y0 + this.t0 * this.dy));
/* 347 */     path.lineTo((float)(this.x0 + this.t1 * this.dx), (float)(this.y0 + this.t1 * this.dy));
/* 348 */     return path;
/*     */   }
/*     */   
/*     */   public GeneralPath appendPath(GeneralPath path) {
/* 359 */     if (!isBounded())
/* 360 */       throw new UnboundedShape2DException(this); 
/* 361 */     if (this.t0 == Double.NEGATIVE_INFINITY)
/* 362 */       return path; 
/* 363 */     if (this.t1 == Double.POSITIVE_INFINITY)
/* 364 */       return path; 
/* 365 */     path.lineTo((float)getX1(), (float)getY1());
/* 366 */     path.lineTo((float)getX2(), (float)getY2());
/* 367 */     return path;
/*     */   }
/*     */   
/*     */   public LineArc2D transform(AffineTransform2D trans) {
/* 372 */     double[] tab = trans.coefficients();
/* 373 */     double x1 = this.x0 * tab[0] + this.y0 * tab[1] + tab[2];
/* 374 */     double y1 = this.x0 * tab[3] + this.y0 * tab[4] + tab[5];
/* 375 */     return new LineArc2D(x1, y1, 
/* 376 */         this.dx * tab[0] + this.dy * tab[1], this.dx * tab[3] + this.dy * tab[4], this.t0, this.t1);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 381 */     return new String("LineArc2D(" + this.x0 + "," + this.y0 + "," + 
/* 382 */         this.dx + "," + this.dy + "," + this.t0 + "," + this.t1 + ")");
/*     */   }
/*     */   
/*     */   public boolean almostEquals(GeometricObject2D obj, double eps) {
/* 393 */     if (this == obj)
/* 394 */       return true; 
/* 396 */     if (!(obj instanceof LineArc2D))
/* 397 */       return false; 
/* 398 */     LineArc2D that = (LineArc2D)obj;
/* 401 */     if (!almostEquals(this.x0, that.x0, eps))
/* 402 */       return false; 
/* 403 */     if (!almostEquals(this.y0, that.y0, eps))
/* 404 */       return false; 
/* 405 */     if (!almostEquals(this.dx, that.dx, eps))
/* 406 */       return false; 
/* 407 */     if (!almostEquals(this.dy, that.dy, eps))
/* 408 */       return false; 
/* 409 */     if (!almostEquals(this.t0, that.t0, eps))
/* 410 */       return false; 
/* 411 */     if (!almostEquals(this.t1, that.t1, eps))
/* 412 */       return false; 
/* 414 */     return true;
/*     */   }
/*     */   
/*     */   private static boolean almostEquals(double d1, double d2, double eps) {
/* 422 */     if (d1 == Double.POSITIVE_INFINITY && d2 == Double.POSITIVE_INFINITY)
/* 423 */       return true; 
/* 424 */     if (d1 == Double.NEGATIVE_INFINITY && d2 == Double.NEGATIVE_INFINITY)
/* 425 */       return true; 
/* 427 */     return (Math.abs(d1 - d2) < eps);
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 435 */     if (this == obj)
/* 436 */       return true; 
/* 437 */     if (!(obj instanceof LineArc2D))
/* 438 */       return false; 
/* 439 */     LineArc2D that = (LineArc2D)obj;
/* 442 */     if (!EqualUtils.areEqual(this.x0, that.x0))
/* 443 */       return false; 
/* 444 */     if (!EqualUtils.areEqual(this.y0, that.y0))
/* 445 */       return false; 
/* 446 */     if (!EqualUtils.areEqual(this.dx, that.dx))
/* 447 */       return false; 
/* 448 */     if (!EqualUtils.areEqual(this.dy, that.dy))
/* 449 */       return false; 
/* 450 */     if (!EqualUtils.areEqual(this.t0, that.t0))
/* 451 */       return false; 
/* 452 */     if (!EqualUtils.areEqual(this.t1, that.t1))
/* 453 */       return false; 
/* 455 */     return true;
/*     */   }
/*     */   
/*     */   public LineArc2D clone() {
/* 460 */     return new LineArc2D(this.x0, this.y0, this.dx, this.dy, this.t0, this.t1);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\line\LineArc2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
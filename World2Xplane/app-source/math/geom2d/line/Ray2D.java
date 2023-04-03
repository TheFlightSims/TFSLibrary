/*     */ package math.geom2d.line;
/*     */ 
/*     */ import java.awt.geom.GeneralPath;
/*     */ import math.geom2d.AffineTransform2D;
/*     */ import math.geom2d.Box2D;
/*     */ import math.geom2d.GeometricObject2D;
/*     */ import math.geom2d.Point2D;
/*     */ import math.geom2d.Shape2D;
/*     */ import math.geom2d.UnboundedShape2DException;
/*     */ import math.geom2d.Vector2D;
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
/*     */ public class Ray2D extends AbstractLine2D implements Cloneable {
/*     */   @Deprecated
/*     */   public static Ray2D create(Point2D origin, Vector2D direction) {
/*  54 */     return new Ray2D(origin, direction);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static Ray2D create(Point2D origin, Point2D target) {
/*  65 */     return new Ray2D(origin, target);
/*     */   }
/*     */   
/*     */   public Ray2D() {
/*  77 */     this(0.0D, 0.0D, 1.0D, 0.0D);
/*     */   }
/*     */   
/*     */   public Ray2D(Point2D point1, Point2D point2) {
/*  88 */     this(point1.x(), point1.y(), point2.x() - point1.x(), point2.y() - point1.y());
/*     */   }
/*     */   
/*     */   public Ray2D(double x1, double y1, double dx, double dy) {
/*  97 */     super(x1, y1, dx, dy);
/*     */   }
/*     */   
/*     */   public Ray2D(Point2D point, double dx, double dy) {
/* 105 */     this(point.x(), point.y(), dx, dy);
/*     */   }
/*     */   
/*     */   public Ray2D(Point2D point, Vector2D vector) {
/* 113 */     this(point.x(), point.y(), vector.x(), vector.y());
/*     */   }
/*     */   
/*     */   public Ray2D(Point2D point, double angle) {
/* 121 */     this(point.x(), point.y(), Math.cos(angle), Math.sin(angle));
/*     */   }
/*     */   
/*     */   public Ray2D(double x, double y, double angle) {
/* 130 */     this(x, y, Math.cos(angle), Math.sin(angle));
/*     */   }
/*     */   
/*     */   public Ray2D(LinearShape2D line) {
/* 137 */     super(line);
/*     */   }
/*     */   
/*     */   public Ray2D parallel(double d) {
/* 148 */     double dd = Math.hypot(this.dx, this.dy);
/* 149 */     return new Ray2D(this.x0 + this.dy * d / dd, this.y0 - this.dx * d / dd, this.dx, this.dy);
/*     */   }
/*     */   
/*     */   public GeneralPath appendPath(GeneralPath path) {
/* 157 */     throw new UnboundedShape2DException(this);
/*     */   }
/*     */   
/*     */   public GeneralPath getGeneralPath() {
/* 162 */     throw new UnboundedShape2DException(this);
/*     */   }
/*     */   
/*     */   public Point2D firstPoint() {
/* 170 */     return new Point2D(this.x0, this.y0);
/*     */   }
/*     */   
/*     */   public Point2D point(double t) {
/* 174 */     t = Math.max(t, 0.0D);
/* 175 */     return new Point2D(this.x0 + t * this.dx, this.y0 + t * this.dy);
/*     */   }
/*     */   
/*     */   public double t0() {
/* 179 */     return 0.0D;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public double getT0() {
/* 187 */     return t0();
/*     */   }
/*     */   
/*     */   public double t1() {
/* 195 */     return Double.POSITIVE_INFINITY;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public double getT1() {
/* 203 */     return t1();
/*     */   }
/*     */   
/*     */   public InvertedRay2D reverse() {
/* 212 */     return new InvertedRay2D(this.x0, this.y0, -this.dx, -this.dy);
/*     */   }
/*     */   
/*     */   public boolean isBounded() {
/* 220 */     return false;
/*     */   }
/*     */   
/*     */   public boolean contains(double x, double y) {
/* 224 */     if (!supportContains(x, y))
/* 225 */       return false; 
/* 226 */     double t = positionOnLine(x, y);
/* 227 */     return (t > -1.0E-12D);
/*     */   }
/*     */   
/*     */   public Box2D boundingBox() {
/* 231 */     double t = Double.POSITIVE_INFINITY;
/* 232 */     Point2D p0 = new Point2D(this.x0, this.y0);
/* 233 */     Point2D p1 = new Point2D(t * this.dx, t * this.dy);
/* 234 */     return new Box2D(p0, p1);
/*     */   }
/*     */   
/*     */   public Ray2D transform(AffineTransform2D trans) {
/* 239 */     double[] tab = trans.coefficients();
/* 240 */     double x1 = this.x0 * tab[0] + this.y0 * tab[1] + tab[2];
/* 241 */     double y1 = this.x0 * tab[3] + this.y0 * tab[4] + tab[5];
/* 242 */     return new Ray2D(x1, y1, 
/* 243 */         this.dx * tab[0] + this.dy * tab[1], this.dx * tab[3] + this.dy * tab[4]);
/*     */   }
/*     */   
/*     */   public boolean almostEquals(GeometricObject2D obj, double eps) {
/* 254 */     if (this == obj)
/* 255 */       return true; 
/* 257 */     if (!(obj instanceof Ray2D))
/* 258 */       return false; 
/* 259 */     Ray2D ray = (Ray2D)obj;
/* 261 */     if (Math.abs(this.x0 - ray.x0) > eps)
/* 262 */       return false; 
/* 263 */     if (Math.abs(this.y0 - ray.y0) > eps)
/* 264 */       return false; 
/* 265 */     if (Math.abs(this.dx - ray.dx) > eps)
/* 266 */       return false; 
/* 267 */     if (Math.abs(this.dy - ray.dy) > eps)
/* 268 */       return false; 
/* 270 */     return true;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 278 */     return new String("Ray2D(" + this.x0 + "," + this.y0 + "," + 
/* 279 */         this.dx + "," + this.dy + ")");
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 284 */     if (this == obj)
/* 285 */       return true; 
/* 286 */     if (!(obj instanceof Ray2D))
/* 287 */       return false; 
/* 288 */     Ray2D that = (Ray2D)obj;
/* 291 */     if (!EqualUtils.areEqual(this.x0, that.x0))
/* 292 */       return false; 
/* 293 */     if (!EqualUtils.areEqual(this.y0, that.y0))
/* 294 */       return false; 
/* 295 */     if (!EqualUtils.areEqual(this.dx, that.dx))
/* 296 */       return false; 
/* 297 */     if (!EqualUtils.areEqual(this.dy, that.dy))
/* 298 */       return false; 
/* 300 */     return true;
/*     */   }
/*     */   
/*     */   public Ray2D clone() {
/* 305 */     return new Ray2D(this.x0, this.y0, this.dx, this.dy);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\line\Ray2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
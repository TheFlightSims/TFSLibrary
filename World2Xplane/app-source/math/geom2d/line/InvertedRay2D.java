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
/*     */ public class InvertedRay2D extends AbstractLine2D implements Cloneable {
/*     */   @Deprecated
/*     */   public static InvertedRay2D create(Point2D target, Vector2D direction) {
/*  56 */     return new InvertedRay2D(target, direction);
/*     */   }
/*     */   
/*     */   public InvertedRay2D() {
/*  68 */     this(0.0D, 0.0D, 1.0D, 0.0D);
/*     */   }
/*     */   
/*     */   public InvertedRay2D(Point2D point1, Point2D point2) {
/*  79 */     this(point1.x(), point1.y(), point2.x() - point1.x(), point2.y() - point1.y());
/*     */   }
/*     */   
/*     */   public InvertedRay2D(double x1, double y1, double dx, double dy) {
/*  88 */     super(x1, y1, dx, dy);
/*     */   }
/*     */   
/*     */   public InvertedRay2D(Point2D point, double dx, double dy) {
/*  96 */     this(point.x(), point.y(), dx, dy);
/*     */   }
/*     */   
/*     */   public InvertedRay2D(Point2D point, Vector2D vector) {
/* 104 */     this(point.x(), point.y(), vector.x(), vector.y());
/*     */   }
/*     */   
/*     */   public InvertedRay2D(Point2D point, double angle) {
/* 112 */     this(point.x(), point.y(), Math.cos(angle), Math.sin(angle));
/*     */   }
/*     */   
/*     */   public InvertedRay2D(double x, double y, double angle) {
/* 121 */     this(x, y, Math.cos(angle), Math.sin(angle));
/*     */   }
/*     */   
/*     */   public InvertedRay2D(LinearShape2D line) {
/* 128 */     super(line);
/*     */   }
/*     */   
/*     */   public InvertedRay2D parallel(double d) {
/* 140 */     double dd = Math.hypot(this.dx, this.dy);
/* 141 */     return new InvertedRay2D(this.x0 + this.dy * d / dd, this.y0 - this.dx * d / dd, this.dx, this.dy);
/*     */   }
/*     */   
/*     */   public GeneralPath appendPath(GeneralPath path) {
/* 149 */     throw new UnboundedShape2DException(this);
/*     */   }
/*     */   
/*     */   public GeneralPath getGeneralPath() {
/* 154 */     throw new UnboundedShape2DException(this);
/*     */   }
/*     */   
/*     */   public Point2D point(double t) {
/* 161 */     t = Math.min(t, 0.0D);
/* 162 */     return new Point2D(this.x0 + t * this.dx, this.y0 + t * this.dy);
/*     */   }
/*     */   
/*     */   public double t0() {
/* 169 */     return Double.NEGATIVE_INFINITY;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public double getT0() {
/* 177 */     return t0();
/*     */   }
/*     */   
/*     */   public double t1() {
/* 184 */     return 0.0D;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public double getT1() {
/* 192 */     return t1();
/*     */   }
/*     */   
/*     */   public Ray2D reverse() {
/* 200 */     return new Ray2D(this.x0, this.y0, -this.dx, -this.dy);
/*     */   }
/*     */   
/*     */   public boolean isBounded() {
/* 208 */     return false;
/*     */   }
/*     */   
/*     */   public boolean contains(double x, double y) {
/* 212 */     if (!supportContains(x, y))
/* 213 */       return false; 
/* 214 */     double t = positionOnLine(x, y);
/* 215 */     return (t < 1.0E-12D);
/*     */   }
/*     */   
/*     */   public Box2D boundingBox() {
/* 219 */     double t = Double.NEGATIVE_INFINITY;
/* 220 */     Point2D p0 = new Point2D(this.x0, this.y0);
/* 221 */     Point2D p1 = new Point2D(t * this.dx, t * this.dy);
/* 222 */     return new Box2D(p0, p1);
/*     */   }
/*     */   
/*     */   public InvertedRay2D transform(AffineTransform2D trans) {
/* 227 */     double[] tab = trans.coefficients();
/* 228 */     double x1 = this.x0 * tab[0] + this.y0 * tab[1] + tab[2];
/* 229 */     double y1 = this.x0 * tab[3] + this.y0 * tab[4] + tab[5];
/* 230 */     return new InvertedRay2D(x1, y1, 
/* 231 */         this.dx * tab[0] + this.dy * tab[1], this.dx * tab[3] + this.dy * tab[4]);
/*     */   }
/*     */   
/*     */   public boolean almostEquals(GeometricObject2D obj, double eps) {
/* 245 */     if (this == obj)
/* 246 */       return true; 
/* 248 */     if (!(obj instanceof InvertedRay2D))
/* 249 */       return false; 
/* 250 */     InvertedRay2D ray = (InvertedRay2D)obj;
/* 251 */     if (Math.abs(this.x0 - ray.x0) > eps)
/* 252 */       return false; 
/* 253 */     if (Math.abs(this.y0 - ray.y0) > eps)
/* 254 */       return false; 
/* 255 */     if (Math.abs(this.dx - ray.dx) > eps)
/* 256 */       return false; 
/* 257 */     if (Math.abs(this.dy - ray.dy) > eps)
/* 258 */       return false; 
/* 260 */     return true;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 268 */     return new String("InvertedRay2D(" + this.x0 + "," + this.y0 + "," + 
/* 269 */         this.dx + "," + this.dy + ")");
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 274 */     if (this == obj)
/* 275 */       return true; 
/* 276 */     if (!(obj instanceof InvertedRay2D))
/* 277 */       return false; 
/* 278 */     InvertedRay2D that = (InvertedRay2D)obj;
/* 281 */     if (!EqualUtils.areEqual(this.x0, that.x0))
/* 282 */       return false; 
/* 283 */     if (!EqualUtils.areEqual(this.y0, that.y0))
/* 284 */       return false; 
/* 285 */     if (!EqualUtils.areEqual(this.dx, that.dx))
/* 286 */       return false; 
/* 287 */     if (!EqualUtils.areEqual(this.dy, that.dy))
/* 288 */       return false; 
/* 290 */     return true;
/*     */   }
/*     */   
/*     */   public InvertedRay2D clone() {
/* 295 */     return new InvertedRay2D(this.x0, this.y0, this.dx, this.dy);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\line\InvertedRay2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
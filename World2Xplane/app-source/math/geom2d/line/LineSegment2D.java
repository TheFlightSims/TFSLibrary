/*     */ package math.geom2d.line;
/*     */ 
/*     */ import java.awt.geom.GeneralPath;
/*     */ import math.geom2d.AffineTransform2D;
/*     */ import math.geom2d.Angle2D;
/*     */ import math.geom2d.Box2D;
/*     */ import math.geom2d.GeometricObject2D;
/*     */ import math.geom2d.Point2D;
/*     */ import math.geom2d.Shape2D;
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
/*     */ public class LineSegment2D extends AbstractLine2D implements Cloneable, CirculinearElement2D {
/*     */   @Deprecated
/*     */   public static LineSegment2D create(Point2D p1, Point2D p2) {
/*  49 */     return new LineSegment2D(p1, p2);
/*     */   }
/*     */   
/*     */   public static StraightLine2D getMedian(LineSegment2D edge) {
/*  56 */     return new StraightLine2D(
/*  57 */         edge.x0 + edge.dx * 0.5D, 
/*  58 */         edge.y0 + edge.dy * 0.5D, 
/*  59 */         -edge.dy, edge.dx);
/*     */   }
/*     */   
/*     */   public static double getEdgeAngle(LineSegment2D edge1, LineSegment2D edge2) {
/*     */     double x0;
/*     */     double y0;
/*     */     double x1;
/*     */     double y1;
/*     */     double x2;
/*     */     double y2;
/*  68 */     if (Math.abs(edge1.x0 - edge2.x0) < 1.0E-12D && 
/*  69 */       Math.abs(edge1.y0 - edge2.y0) < 1.0E-12D) {
/*  70 */       x0 = edge1.x0;
/*  71 */       y0 = edge1.y0;
/*  72 */       x1 = edge1.x0 + edge1.dx;
/*  73 */       y1 = edge1.y0 + edge1.dy;
/*  74 */       x2 = edge2.x0 + edge2.dx;
/*  75 */       y2 = edge2.y0 + edge2.dy;
/*  76 */     } else if (Math.abs(edge1.x0 + edge1.dx - edge2.x0) < 1.0E-12D && 
/*  77 */       Math.abs(edge1.y0 + edge1.dy - edge2.y0) < 1.0E-12D) {
/*  78 */       x0 = edge1.x0 + edge1.dx;
/*  79 */       y0 = edge1.y0 + edge1.dy;
/*  80 */       x1 = edge1.x0;
/*  81 */       y1 = edge1.y0;
/*  82 */       x2 = edge2.x0 + edge2.dx;
/*  83 */       y2 = edge2.y0 + edge2.dy;
/*  84 */     } else if (Math.abs(edge1.x0 + edge1.dx - edge2.x0 - edge2.dx) < 1.0E-12D && 
/*  85 */       Math.abs(edge1.y0 + edge1.dy - edge2.y0 - edge2.dy) < 1.0E-12D) {
/*  86 */       x0 = edge1.x0 + edge1.dx;
/*  87 */       y0 = edge1.y0 + edge1.dy;
/*  88 */       x1 = edge1.x0;
/*  89 */       y1 = edge1.y0;
/*  90 */       x2 = edge2.x0;
/*  91 */       y2 = edge2.y0;
/*  92 */     } else if (Math.abs(edge1.x0 - edge2.x0 - edge2.dx) < 1.0E-12D && 
/*  93 */       Math.abs(edge1.y0 - edge2.y0 - edge2.dy) < 1.0E-12D) {
/*  94 */       x0 = edge1.x0;
/*  95 */       y0 = edge1.y0;
/*  96 */       x1 = edge1.x0 + edge1.dx;
/*  97 */       y1 = edge1.y0 + edge1.dy;
/*  98 */       x2 = edge2.x0;
/*  99 */       y2 = edge2.y0;
/*     */     } else {
/* 101 */       return Double.NaN;
/*     */     } 
/* 104 */     return Angle2D.angle(new Vector2D(x1 - x0, y1 - y0), new Vector2D(x2 - 
/* 105 */           x0, y2 - y0));
/*     */   }
/*     */   
/*     */   public static boolean intersects(LineSegment2D edge1, LineSegment2D edge2) {
/* 117 */     Point2D e1p1 = edge1.firstPoint();
/* 118 */     Point2D e1p2 = edge1.lastPoint();
/* 119 */     Point2D e2p1 = edge2.firstPoint();
/* 120 */     Point2D e2p2 = edge2.lastPoint();
/* 122 */     boolean b1 = (Point2D.ccw(e1p1, e1p2, e2p1) * 
/* 123 */       Point2D.ccw(e1p1, e1p2, e2p2) <= 0);
/* 124 */     boolean b2 = (Point2D.ccw(e2p1, e2p2, e1p1) * 
/* 125 */       Point2D.ccw(e2p1, e2p2, e1p2) <= 0);
/* 126 */     return (b1 && b2);
/*     */   }
/*     */   
/*     */   public LineSegment2D(Point2D point1, Point2D point2) {
/* 135 */     this(point1.x(), point1.y(), point2.x(), point2.y());
/*     */   }
/*     */   
/*     */   public LineSegment2D(double x1, double y1, double x2, double y2) {
/* 140 */     super(x1, y1, x2 - x1, y2 - y1);
/*     */   }
/*     */   
/*     */   public Point2D opposite(Point2D point) {
/* 154 */     if (point.equals(new Point2D(this.x0, this.y0)))
/* 155 */       return new Point2D(this.x0 + this.dx, this.y0 + this.dy); 
/* 156 */     if (point.equals(new Point2D(this.x0 + this.dx, this.y0 + this.dy)))
/* 157 */       return new Point2D(this.x0, this.y0); 
/* 158 */     return null;
/*     */   }
/*     */   
/*     */   public StraightLine2D getMedian() {
/* 168 */     return new StraightLine2D(this.x0 + this.dx * 0.5D, this.y0 + this.dy * 0.5D, -this.dy, this.dx);
/*     */   }
/*     */   
/*     */   public double length() {
/* 180 */     return Math.hypot(this.dx, this.dy);
/*     */   }
/*     */   
/*     */   public LineSegment2D parallel(double d) {
/* 187 */     double d2 = Math.hypot(this.dx, this.dy);
/* 188 */     if (Math.abs(d2) < 1.0E-12D)
/* 189 */       throw new DegeneratedLine2DException(
/* 190 */           "Can not compute parallel of degnerated edge", this); 
/* 191 */     d2 = d / d2;
/* 192 */     return new LineSegment2D(
/* 193 */         this.x0 + this.dy * d2, this.y0 - this.dx * d2, 
/* 194 */         this.x0 + this.dx + this.dy * d2, this.y0 + this.dy - this.dx * d2);
/*     */   }
/*     */   
/*     */   public double signedDistance(double x, double y) {
/* 206 */     Point2D proj = projectedPoint(x, y);
/* 207 */     if (contains(proj))
/* 208 */       return super.signedDistance(x, y); 
/* 210 */     double d = distance(x, y);
/* 211 */     return (super.signedDistance(x, y) > 0.0D) ? d : -d;
/*     */   }
/*     */   
/*     */   public Point2D firstPoint() {
/* 226 */     return new Point2D(this.x0, this.y0);
/*     */   }
/*     */   
/*     */   public Point2D lastPoint() {
/* 236 */     return new Point2D(this.x0 + this.dx, this.y0 + this.dy);
/*     */   }
/*     */   
/*     */   public double t0() {
/* 243 */     return 0.0D;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public double getT0() {
/* 251 */     return t0();
/*     */   }
/*     */   
/*     */   public double t1() {
/* 258 */     return 1.0D;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public double getT1() {
/* 266 */     return t1();
/*     */   }
/*     */   
/*     */   public Point2D point(double t) {
/* 270 */     t = Math.min(Math.max(t, 0.0D), 1.0D);
/* 271 */     return new Point2D(this.x0 + this.dx * t, this.y0 + this.dy * t);
/*     */   }
/*     */   
/*     */   public LineSegment2D reverse() {
/* 279 */     return new LineSegment2D(this.x0 + this.dx, this.y0 + this.dy, this.x0, this.y0);
/*     */   }
/*     */   
/*     */   public boolean isBounded() {
/* 289 */     return true;
/*     */   }
/*     */   
/*     */   public boolean contains(double xp, double yp) {
/* 293 */     if (!supportContains(xp, yp))
/* 294 */       return false; 
/* 297 */     double t = positionOnLine(xp, yp);
/* 299 */     if (t < -1.0E-12D)
/* 300 */       return false; 
/* 301 */     if (t - 1.0D > 1.0E-12D)
/* 302 */       return false; 
/* 304 */     return true;
/*     */   }
/*     */   
/*     */   public double distance(double x, double y) {
/* 312 */     Point2D proj = projectedPoint(x, y);
/* 313 */     if (contains(proj))
/* 314 */       return proj.distance(x, y); 
/* 315 */     double d1 = Math.hypot(this.x0 - x, this.y0 - y);
/* 316 */     double d2 = Math.hypot(this.x0 + this.dx - x, this.y0 + this.dy - y);
/* 317 */     return Math.min(d1, d2);
/*     */   }
/*     */   
/*     */   public LineSegment2D transform(AffineTransform2D trans) {
/* 322 */     double[] tab = trans.coefficients();
/* 323 */     double x1 = this.x0 * tab[0] + this.y0 * tab[1] + tab[2];
/* 324 */     double y1 = this.x0 * tab[3] + this.y0 * tab[4] + tab[5];
/* 325 */     double x2 = (this.x0 + this.dx) * tab[0] + (this.y0 + this.dy) * tab[1] + tab[2];
/* 326 */     double y2 = (this.x0 + this.dx) * tab[3] + (this.y0 + this.dy) * tab[4] + tab[5];
/* 327 */     return new LineSegment2D(x1, y1, x2, y2);
/*     */   }
/*     */   
/*     */   public Box2D boundingBox() {
/* 334 */     return new Box2D(this.x0, this.x0 + this.dx, this.y0, this.y0 + this.dy);
/*     */   }
/*     */   
/*     */   public GeneralPath appendPath(GeneralPath path) {
/* 347 */     path.lineTo((float)this.x0 + this.dx, (float)this.y0 + this.dy);
/* 348 */     return path;
/*     */   }
/*     */   
/*     */   public GeneralPath getGeneralPath() {
/* 355 */     GeneralPath path = new GeneralPath();
/* 356 */     path.moveTo((float)this.x0, (float)this.y0);
/* 357 */     path.lineTo((float)(this.x0 + this.dx), (float)(this.y0 + this.dy));
/* 358 */     return path;
/*     */   }
/*     */   
/*     */   public boolean almostEquals(GeometricObject2D obj, double eps) {
/* 373 */     if (this == obj)
/* 374 */       return true; 
/* 376 */     if (!(obj instanceof LineSegment2D))
/* 377 */       return false; 
/* 378 */     LineSegment2D edge = (LineSegment2D)obj;
/* 380 */     if (Math.abs(this.x0 - edge.x0) > eps)
/* 381 */       return false; 
/* 382 */     if (Math.abs(this.y0 - edge.y0) > eps)
/* 383 */       return false; 
/* 384 */     if (Math.abs(this.dx - edge.dx) > eps)
/* 385 */       return false; 
/* 386 */     if (Math.abs(this.dy - edge.dy) > eps)
/* 387 */       return false; 
/* 389 */     return true;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 397 */     return new String("LineSegment2D[(" + this.x0 + "," + this.y0 + ")-(" + (this.x0 + this.dx) + 
/* 398 */         "," + (this.y0 + this.dy) + ")]");
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 403 */     if (this == obj)
/* 404 */       return true; 
/* 405 */     if (!(obj instanceof LineSegment2D))
/* 406 */       return false; 
/* 407 */     LineSegment2D that = (LineSegment2D)obj;
/* 410 */     if (!EqualUtils.areEqual(this.x0, that.x0))
/* 411 */       return false; 
/* 412 */     if (!EqualUtils.areEqual(this.y0, that.y0))
/* 413 */       return false; 
/* 414 */     if (!EqualUtils.areEqual(this.dx, that.dx))
/* 415 */       return false; 
/* 416 */     if (!EqualUtils.areEqual(this.dy, that.dy))
/* 417 */       return false; 
/* 419 */     return true;
/*     */   }
/*     */   
/*     */   public LineSegment2D clone() {
/* 424 */     return new LineSegment2D(this.x0, this.y0, this.x0 + this.dx, this.y0 + this.dy);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\line\LineSegment2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
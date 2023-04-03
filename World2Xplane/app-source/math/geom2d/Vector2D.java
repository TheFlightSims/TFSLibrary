/*     */ package math.geom2d;
/*     */ 
/*     */ import math.utils.EqualUtils;
/*     */ 
/*     */ public class Vector2D implements GeometricObject2D, Cloneable {
/*     */   protected double x;
/*     */   
/*     */   protected double y;
/*     */   
/*     */   @Deprecated
/*     */   public static Vector2D create(double x, double y) {
/*  49 */     return new Vector2D(x, y);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static Vector2D create(Point2D point) {
/*  58 */     return new Vector2D(point.x, point.y);
/*     */   }
/*     */   
/*     */   public static Vector2D createPolar(double rho, double theta) {
/*  66 */     return new Vector2D(rho * Math.cos(theta), rho * Math.sin(theta));
/*     */   }
/*     */   
/*     */   public static double dot(Vector2D v1, Vector2D v2) {
/*  79 */     return v1.x * v2.x + v1.y * v2.y;
/*     */   }
/*     */   
/*     */   public static double cross(Vector2D v1, Vector2D v2) {
/*  92 */     return v1.x * v2.y - v2.x * v1.y;
/*     */   }
/*     */   
/*     */   public static boolean isColinear(Vector2D v1, Vector2D v2) {
/* 101 */     v1 = v1.normalize();
/* 102 */     v2 = v2.normalize();
/* 103 */     return (Math.abs(v1.x * v2.y - v1.y * v2.x) < 1.0E-12D);
/*     */   }
/*     */   
/*     */   public static boolean isOrthogonal(Vector2D v1, Vector2D v2) {
/* 112 */     v1 = v1.normalize();
/* 113 */     v2 = v2.normalize();
/* 114 */     return (Math.abs(v1.x * v2.x + v1.y * v2.y) < 1.0E-12D);
/*     */   }
/*     */   
/*     */   public Vector2D() {
/* 135 */     this(1.0D, 0.0D);
/*     */   }
/*     */   
/*     */   public Vector2D(double x, double y) {
/* 143 */     this.x = x;
/* 144 */     this.y = y;
/*     */   }
/*     */   
/*     */   public Vector2D(Point2D point) {
/* 151 */     this(point.x, point.y);
/*     */   }
/*     */   
/*     */   public Vector2D(Point2D point1, Point2D point2) {
/* 158 */     this(point2.x - point1.x, point2.y - point1.y);
/*     */   }
/*     */   
/*     */   public double x() {
/* 168 */     return this.x;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public double getX() {
/* 176 */     return this.x;
/*     */   }
/*     */   
/*     */   public double y() {
/* 183 */     return this.y;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public double getY() {
/* 191 */     return this.y;
/*     */   }
/*     */   
/*     */   public Vector2D opposite() {
/* 201 */     return new Vector2D(-this.x, -this.y);
/*     */   }
/*     */   
/*     */   public double norm() {
/* 210 */     return Math.hypot(this.x, this.y);
/*     */   }
/*     */   
/*     */   public double angle() {
/* 219 */     return Angle2D.horizontalAngle(this);
/*     */   }
/*     */   
/*     */   public Vector2D normalize() {
/* 227 */     double r = Math.hypot(this.x, this.y);
/* 228 */     return new Vector2D(this.x / r, this.y / r);
/*     */   }
/*     */   
/*     */   public boolean isColinear(Vector2D v) {
/* 240 */     return isColinear(this, v);
/*     */   }
/*     */   
/*     */   public boolean isOrthogonal(Vector2D v) {
/* 249 */     return isOrthogonal(this, v);
/*     */   }
/*     */   
/*     */   public double dot(Vector2D v) {
/* 267 */     return this.x * v.x + this.y * v.y;
/*     */   }
/*     */   
/*     */   public double cross(Vector2D v) {
/* 282 */     return this.x * v.y - v.x * this.y;
/*     */   }
/*     */   
/*     */   public Vector2D plus(Vector2D v) {
/* 290 */     return new Vector2D(this.x + v.x, this.y + v.y);
/*     */   }
/*     */   
/*     */   public Vector2D minus(Vector2D v) {
/* 298 */     return new Vector2D(this.x - v.x, this.y - v.y);
/*     */   }
/*     */   
/*     */   public Vector2D times(double k) {
/* 308 */     return new Vector2D(this.x * k, this.y * k);
/*     */   }
/*     */   
/*     */   public Vector2D rotate(double theta) {
/* 316 */     double cot = Math.cos(theta);
/* 317 */     double sit = Math.sin(theta);
/* 318 */     double x2 = this.x * cot - this.y * sit;
/* 319 */     double y2 = this.x * sit + this.y * cot;
/* 320 */     return new Vector2D(x2, y2);
/*     */   }
/*     */   
/*     */   public Vector2D transform(AffineTransform2D trans) {
/* 331 */     double[] tab = trans.coefficients();
/* 332 */     return new Vector2D(this.x * tab[0] + this.y * tab[1], this.x * tab[3] + this.y * tab[4]);
/*     */   }
/*     */   
/*     */   public boolean almostEquals(GeometricObject2D obj, double eps) {
/* 340 */     if (this == obj)
/* 341 */       return true; 
/* 343 */     if (!(obj instanceof Vector2D))
/* 344 */       return false; 
/* 345 */     Vector2D v = (Vector2D)obj;
/* 347 */     if (Math.abs(this.x - v.x) > eps)
/* 348 */       return false; 
/* 349 */     if (Math.abs(this.y - v.y) > eps)
/* 350 */       return false; 
/* 352 */     return true;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 362 */     if (this == obj)
/* 363 */       return true; 
/* 365 */     if (!(obj instanceof Vector2D))
/* 366 */       return false; 
/* 367 */     Vector2D that = (Vector2D)obj;
/* 370 */     if (!EqualUtils.areEqual(this.x, that.x))
/* 371 */       return false; 
/* 372 */     if (!EqualUtils.areEqual(this.y, that.y))
/* 373 */       return false; 
/* 375 */     return true;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 384 */     return new String("x=" + this.x + " y=" + this.y);
/*     */   }
/*     */   
/*     */   public Vector2D clone() {
/* 389 */     return new Vector2D(this.x, this.y);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\Vector2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
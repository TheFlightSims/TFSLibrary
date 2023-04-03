/*     */ package org.apache.commons.math3.geometry.euclidean.twod;
/*     */ 
/*     */ import java.text.NumberFormat;
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ import org.apache.commons.math3.exception.MathArithmeticException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.geometry.Space;
/*     */ import org.apache.commons.math3.geometry.Vector;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ import org.apache.commons.math3.util.MathUtils;
/*     */ 
/*     */ public class Vector2D implements Vector<Euclidean2D> {
/*  37 */   public static final Vector2D ZERO = new Vector2D(0.0D, 0.0D);
/*     */   
/*  41 */   public static final Vector2D NaN = new Vector2D(Double.NaN, Double.NaN);
/*     */   
/*  45 */   public static final Vector2D POSITIVE_INFINITY = new Vector2D(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
/*     */   
/*  49 */   public static final Vector2D NEGATIVE_INFINITY = new Vector2D(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
/*     */   
/*     */   private static final long serialVersionUID = 266938651998679754L;
/*     */   
/*     */   private final double x;
/*     */   
/*     */   private final double y;
/*     */   
/*     */   public Vector2D(double x, double y) {
/*  69 */     this.x = x;
/*  70 */     this.y = y;
/*     */   }
/*     */   
/*     */   public Vector2D(double[] v) throws DimensionMismatchException {
/*  80 */     if (v.length != 2)
/*  81 */       throw new DimensionMismatchException(v.length, 2); 
/*  83 */     this.x = v[0];
/*  84 */     this.y = v[1];
/*     */   }
/*     */   
/*     */   public Vector2D(double a, Vector2D u) {
/*  94 */     this.x = a * u.x;
/*  95 */     this.y = a * u.y;
/*     */   }
/*     */   
/*     */   public Vector2D(double a1, Vector2D u1, double a2, Vector2D u2) {
/* 107 */     this.x = a1 * u1.x + a2 * u2.x;
/* 108 */     this.y = a1 * u1.y + a2 * u2.y;
/*     */   }
/*     */   
/*     */   public Vector2D(double a1, Vector2D u1, double a2, Vector2D u2, double a3, Vector2D u3) {
/* 123 */     this.x = a1 * u1.x + a2 * u2.x + a3 * u3.x;
/* 124 */     this.y = a1 * u1.y + a2 * u2.y + a3 * u3.y;
/*     */   }
/*     */   
/*     */   public Vector2D(double a1, Vector2D u1, double a2, Vector2D u2, double a3, Vector2D u3, double a4, Vector2D u4) {
/* 141 */     this.x = a1 * u1.x + a2 * u2.x + a3 * u3.x + a4 * u4.x;
/* 142 */     this.y = a1 * u1.y + a2 * u2.y + a3 * u3.y + a4 * u4.y;
/*     */   }
/*     */   
/*     */   public double getX() {
/* 150 */     return this.x;
/*     */   }
/*     */   
/*     */   public double getY() {
/* 158 */     return this.y;
/*     */   }
/*     */   
/*     */   public double[] toArray() {
/* 166 */     return new double[] { this.x, this.y };
/*     */   }
/*     */   
/*     */   public Space getSpace() {
/* 171 */     return Euclidean2D.getInstance();
/*     */   }
/*     */   
/*     */   public Vector2D getZero() {
/* 176 */     return ZERO;
/*     */   }
/*     */   
/*     */   public double getNorm1() {
/* 181 */     return FastMath.abs(this.x) + FastMath.abs(this.y);
/*     */   }
/*     */   
/*     */   public double getNorm() {
/* 186 */     return FastMath.sqrt(this.x * this.x + this.y * this.y);
/*     */   }
/*     */   
/*     */   public double getNormSq() {
/* 191 */     return this.x * this.x + this.y * this.y;
/*     */   }
/*     */   
/*     */   public double getNormInf() {
/* 196 */     return FastMath.max(FastMath.abs(this.x), FastMath.abs(this.y));
/*     */   }
/*     */   
/*     */   public Vector2D add(Vector<Euclidean2D> v) {
/* 201 */     Vector2D v2 = (Vector2D)v;
/* 202 */     return new Vector2D(this.x + v2.getX(), this.y + v2.getY());
/*     */   }
/*     */   
/*     */   public Vector2D add(double factor, Vector<Euclidean2D> v) {
/* 207 */     Vector2D v2 = (Vector2D)v;
/* 208 */     return new Vector2D(this.x + factor * v2.getX(), this.y + factor * v2.getY());
/*     */   }
/*     */   
/*     */   public Vector2D subtract(Vector<Euclidean2D> p) {
/* 213 */     Vector2D p3 = (Vector2D)p;
/* 214 */     return new Vector2D(this.x - p3.x, this.y - p3.y);
/*     */   }
/*     */   
/*     */   public Vector2D subtract(double factor, Vector<Euclidean2D> v) {
/* 219 */     Vector2D v2 = (Vector2D)v;
/* 220 */     return new Vector2D(this.x - factor * v2.getX(), this.y - factor * v2.getY());
/*     */   }
/*     */   
/*     */   public Vector2D normalize() {
/* 225 */     double s = getNorm();
/* 226 */     if (s == 0.0D)
/* 227 */       throw new MathArithmeticException(LocalizedFormats.CANNOT_NORMALIZE_A_ZERO_NORM_VECTOR, new Object[0]); 
/* 229 */     return scalarMultiply(1.0D / s);
/*     */   }
/*     */   
/*     */   public Vector2D negate() {
/* 233 */     return new Vector2D(-this.x, -this.y);
/*     */   }
/*     */   
/*     */   public Vector2D scalarMultiply(double a) {
/* 238 */     return new Vector2D(a * this.x, a * this.y);
/*     */   }
/*     */   
/*     */   public boolean isNaN() {
/* 243 */     return (Double.isNaN(this.x) || Double.isNaN(this.y));
/*     */   }
/*     */   
/*     */   public boolean isInfinite() {
/* 248 */     return (!isNaN() && (Double.isInfinite(this.x) || Double.isInfinite(this.y)));
/*     */   }
/*     */   
/*     */   public double distance1(Vector<Euclidean2D> p) {
/* 253 */     Vector2D p3 = (Vector2D)p;
/* 254 */     double dx = FastMath.abs(p3.x - this.x);
/* 255 */     double dy = FastMath.abs(p3.y - this.y);
/* 256 */     return dx + dy;
/*     */   }
/*     */   
/*     */   public double distance(Vector<Euclidean2D> p) {
/* 261 */     Vector2D p3 = (Vector2D)p;
/* 262 */     double dx = p3.x - this.x;
/* 263 */     double dy = p3.y - this.y;
/* 264 */     return FastMath.sqrt(dx * dx + dy * dy);
/*     */   }
/*     */   
/*     */   public double distanceInf(Vector<Euclidean2D> p) {
/* 269 */     Vector2D p3 = (Vector2D)p;
/* 270 */     double dx = FastMath.abs(p3.x - this.x);
/* 271 */     double dy = FastMath.abs(p3.y - this.y);
/* 272 */     return FastMath.max(dx, dy);
/*     */   }
/*     */   
/*     */   public double distanceSq(Vector<Euclidean2D> p) {
/* 277 */     Vector2D p3 = (Vector2D)p;
/* 278 */     double dx = p3.x - this.x;
/* 279 */     double dy = p3.y - this.y;
/* 280 */     return dx * dx + dy * dy;
/*     */   }
/*     */   
/*     */   public double dotProduct(Vector<Euclidean2D> v) {
/* 285 */     Vector2D v2 = (Vector2D)v;
/* 286 */     return this.x * v2.x + this.y * v2.y;
/*     */   }
/*     */   
/*     */   public static double distance(Vector2D p1, Vector2D p2) {
/* 298 */     return p1.distance(p2);
/*     */   }
/*     */   
/*     */   public static double distanceInf(Vector2D p1, Vector2D p2) {
/* 310 */     return p1.distanceInf(p2);
/*     */   }
/*     */   
/*     */   public static double distanceSq(Vector2D p1, Vector2D p2) {
/* 322 */     return p1.distanceSq(p2);
/*     */   }
/*     */   
/*     */   public boolean equals(Object other) {
/* 347 */     if (this == other)
/* 348 */       return true; 
/* 351 */     if (other instanceof Vector2D) {
/* 352 */       Vector2D rhs = (Vector2D)other;
/* 353 */       if (rhs.isNaN())
/* 354 */         return isNaN(); 
/* 357 */       return (this.x == rhs.x && this.y == rhs.y);
/*     */     } 
/* 359 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 371 */     if (isNaN())
/* 372 */       return 542; 
/* 374 */     return 122 * (76 * MathUtils.hash(this.x) + MathUtils.hash(this.y));
/*     */   }
/*     */   
/*     */   public String toString() {
/* 382 */     return Vector2DFormat.getInstance().format(this);
/*     */   }
/*     */   
/*     */   public String toString(NumberFormat format) {
/* 387 */     return (new Vector2DFormat(format)).format(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\geometry\euclidean\twod\Vector2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
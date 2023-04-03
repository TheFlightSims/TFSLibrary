/*     */ package org.apache.commons.math3.geometry.euclidean.oned;
/*     */ 
/*     */ import java.text.NumberFormat;
/*     */ import org.apache.commons.math3.exception.MathArithmeticException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.geometry.Space;
/*     */ import org.apache.commons.math3.geometry.Vector;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ import org.apache.commons.math3.util.MathUtils;
/*     */ 
/*     */ public class Vector1D implements Vector<Euclidean1D> {
/*  36 */   public static final Vector1D ZERO = new Vector1D(0.0D);
/*     */   
/*  39 */   public static final Vector1D ONE = new Vector1D(1.0D);
/*     */   
/*  43 */   public static final Vector1D NaN = new Vector1D(Double.NaN);
/*     */   
/*  47 */   public static final Vector1D POSITIVE_INFINITY = new Vector1D(Double.POSITIVE_INFINITY);
/*     */   
/*  51 */   public static final Vector1D NEGATIVE_INFINITY = new Vector1D(Double.NEGATIVE_INFINITY);
/*     */   
/*     */   private static final long serialVersionUID = 7556674948671647925L;
/*     */   
/*     */   private final double x;
/*     */   
/*     */   public Vector1D(double x) {
/*  66 */     this.x = x;
/*     */   }
/*     */   
/*     */   public Vector1D(double a, Vector1D u) {
/*  76 */     this.x = a * u.x;
/*     */   }
/*     */   
/*     */   public Vector1D(double a1, Vector1D u1, double a2, Vector1D u2) {
/*  88 */     this.x = a1 * u1.x + a2 * u2.x;
/*     */   }
/*     */   
/*     */   public Vector1D(double a1, Vector1D u1, double a2, Vector1D u2, double a3, Vector1D u3) {
/* 103 */     this.x = a1 * u1.x + a2 * u2.x + a3 * u3.x;
/*     */   }
/*     */   
/*     */   public Vector1D(double a1, Vector1D u1, double a2, Vector1D u2, double a3, Vector1D u3, double a4, Vector1D u4) {
/* 120 */     this.x = a1 * u1.x + a2 * u2.x + a3 * u3.x + a4 * u4.x;
/*     */   }
/*     */   
/*     */   public double getX() {
/* 128 */     return this.x;
/*     */   }
/*     */   
/*     */   public Space getSpace() {
/* 133 */     return Euclidean1D.getInstance();
/*     */   }
/*     */   
/*     */   public Vector1D getZero() {
/* 138 */     return ZERO;
/*     */   }
/*     */   
/*     */   public double getNorm1() {
/* 143 */     return FastMath.abs(this.x);
/*     */   }
/*     */   
/*     */   public double getNorm() {
/* 148 */     return FastMath.abs(this.x);
/*     */   }
/*     */   
/*     */   public double getNormSq() {
/* 153 */     return this.x * this.x;
/*     */   }
/*     */   
/*     */   public double getNormInf() {
/* 158 */     return FastMath.abs(this.x);
/*     */   }
/*     */   
/*     */   public Vector1D add(Vector<Euclidean1D> v) {
/* 163 */     Vector1D v1 = (Vector1D)v;
/* 164 */     return new Vector1D(this.x + v1.getX());
/*     */   }
/*     */   
/*     */   public Vector1D add(double factor, Vector<Euclidean1D> v) {
/* 169 */     Vector1D v1 = (Vector1D)v;
/* 170 */     return new Vector1D(this.x + factor * v1.getX());
/*     */   }
/*     */   
/*     */   public Vector1D subtract(Vector<Euclidean1D> p) {
/* 175 */     Vector1D p3 = (Vector1D)p;
/* 176 */     return new Vector1D(this.x - p3.x);
/*     */   }
/*     */   
/*     */   public Vector1D subtract(double factor, Vector<Euclidean1D> v) {
/* 181 */     Vector1D v1 = (Vector1D)v;
/* 182 */     return new Vector1D(this.x - factor * v1.getX());
/*     */   }
/*     */   
/*     */   public Vector1D normalize() {
/* 187 */     double s = getNorm();
/* 188 */     if (s == 0.0D)
/* 189 */       throw new MathArithmeticException(LocalizedFormats.CANNOT_NORMALIZE_A_ZERO_NORM_VECTOR, new Object[0]); 
/* 191 */     return scalarMultiply(1.0D / s);
/*     */   }
/*     */   
/*     */   public Vector1D negate() {
/* 195 */     return new Vector1D(-this.x);
/*     */   }
/*     */   
/*     */   public Vector1D scalarMultiply(double a) {
/* 200 */     return new Vector1D(a * this.x);
/*     */   }
/*     */   
/*     */   public boolean isNaN() {
/* 205 */     return Double.isNaN(this.x);
/*     */   }
/*     */   
/*     */   public boolean isInfinite() {
/* 210 */     return (!isNaN() && Double.isInfinite(this.x));
/*     */   }
/*     */   
/*     */   public double distance1(Vector<Euclidean1D> p) {
/* 215 */     Vector1D p3 = (Vector1D)p;
/* 216 */     double dx = FastMath.abs(p3.x - this.x);
/* 217 */     return dx;
/*     */   }
/*     */   
/*     */   public double distance(Vector<Euclidean1D> p) {
/* 222 */     Vector1D p3 = (Vector1D)p;
/* 223 */     double dx = p3.x - this.x;
/* 224 */     return FastMath.abs(dx);
/*     */   }
/*     */   
/*     */   public double distanceInf(Vector<Euclidean1D> p) {
/* 229 */     Vector1D p3 = (Vector1D)p;
/* 230 */     double dx = FastMath.abs(p3.x - this.x);
/* 231 */     return dx;
/*     */   }
/*     */   
/*     */   public double distanceSq(Vector<Euclidean1D> p) {
/* 236 */     Vector1D p3 = (Vector1D)p;
/* 237 */     double dx = p3.x - this.x;
/* 238 */     return dx * dx;
/*     */   }
/*     */   
/*     */   public double dotProduct(Vector<Euclidean1D> v) {
/* 243 */     Vector1D v1 = (Vector1D)v;
/* 244 */     return this.x * v1.x;
/*     */   }
/*     */   
/*     */   public static double distance(Vector1D p1, Vector1D p2) {
/* 256 */     return p1.distance(p2);
/*     */   }
/*     */   
/*     */   public static double distanceInf(Vector1D p1, Vector1D p2) {
/* 268 */     return p1.distanceInf(p2);
/*     */   }
/*     */   
/*     */   public static double distanceSq(Vector1D p1, Vector1D p2) {
/* 280 */     return p1.distanceSq(p2);
/*     */   }
/*     */   
/*     */   public boolean equals(Object other) {
/* 305 */     if (this == other)
/* 306 */       return true; 
/* 309 */     if (other instanceof Vector1D) {
/* 310 */       Vector1D rhs = (Vector1D)other;
/* 311 */       if (rhs.isNaN())
/* 312 */         return isNaN(); 
/* 315 */       return (this.x == rhs.x);
/*     */     } 
/* 317 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 329 */     if (isNaN())
/* 330 */       return 7785; 
/* 332 */     return 997 * MathUtils.hash(this.x);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 340 */     return Vector1DFormat.getInstance().format(this);
/*     */   }
/*     */   
/*     */   public String toString(NumberFormat format) {
/* 345 */     return (new Vector1DFormat(format)).format(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\geometry\euclidean\oned\Vector1D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
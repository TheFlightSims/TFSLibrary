/*     */ package org.apache.commons.math3.distribution;
/*     */ 
/*     */ import org.apache.commons.math3.exception.NumberIsTooLargeException;
/*     */ import org.apache.commons.math3.exception.NumberIsTooSmallException;
/*     */ import org.apache.commons.math3.exception.OutOfRangeException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class TriangularDistribution extends AbstractRealDistribution {
/*     */   private static final long serialVersionUID = 20120112L;
/*     */   
/*     */   private final double a;
/*     */   
/*     */   private final double b;
/*     */   
/*     */   private final double c;
/*     */   
/*     */   private final double solverAbsoluteAccuracy;
/*     */   
/*     */   public TriangularDistribution(double a, double c, double b) throws NumberIsTooLargeException, NumberIsTooSmallException {
/*  63 */     if (a >= b)
/*  64 */       throw new NumberIsTooLargeException(LocalizedFormats.LOWER_BOUND_NOT_BELOW_UPPER_BOUND, Double.valueOf(a), Double.valueOf(b), false); 
/*  68 */     if (c < a)
/*  69 */       throw new NumberIsTooSmallException(LocalizedFormats.NUMBER_TOO_SMALL, Double.valueOf(c), Double.valueOf(a), true); 
/*  72 */     if (c > b)
/*  73 */       throw new NumberIsTooLargeException(LocalizedFormats.NUMBER_TOO_LARGE, Double.valueOf(c), Double.valueOf(b), true); 
/*  77 */     this.a = a;
/*  78 */     this.c = c;
/*  79 */     this.b = b;
/*  80 */     this.solverAbsoluteAccuracy = FastMath.max(FastMath.ulp(a), FastMath.ulp(b));
/*     */   }
/*     */   
/*     */   public double getMode() {
/*  89 */     return this.c;
/*     */   }
/*     */   
/*     */   protected double getSolverAbsoluteAccuracy() {
/* 107 */     return this.solverAbsoluteAccuracy;
/*     */   }
/*     */   
/*     */   public double probability(double x) {
/* 118 */     return 0.0D;
/*     */   }
/*     */   
/*     */   public double density(double x) {
/* 134 */     if (x < this.a)
/* 135 */       return 0.0D; 
/* 137 */     if (this.a <= x && x < this.c) {
/* 138 */       double divident = 2.0D * (x - this.a);
/* 139 */       double divisor = (this.b - this.a) * (this.c - this.a);
/* 140 */       return divident / divisor;
/*     */     } 
/* 142 */     if (x == this.c)
/* 143 */       return 2.0D / (this.b - this.a); 
/* 145 */     if (this.c < x && x <= this.b) {
/* 146 */       double divident = 2.0D * (this.b - x);
/* 147 */       double divisor = (this.b - this.a) * (this.b - this.c);
/* 148 */       return divident / divisor;
/*     */     } 
/* 150 */     return 0.0D;
/*     */   }
/*     */   
/*     */   public double cumulativeProbability(double x) {
/* 167 */     if (x < this.a)
/* 168 */       return 0.0D; 
/* 170 */     if (this.a <= x && x < this.c) {
/* 171 */       double divident = (x - this.a) * (x - this.a);
/* 172 */       double divisor = (this.b - this.a) * (this.c - this.a);
/* 173 */       return divident / divisor;
/*     */     } 
/* 175 */     if (x == this.c)
/* 176 */       return (this.c - this.a) / (this.b - this.a); 
/* 178 */     if (this.c < x && x <= this.b) {
/* 179 */       double divident = (this.b - x) * (this.b - x);
/* 180 */       double divisor = (this.b - this.a) * (this.b - this.c);
/* 181 */       return 1.0D - divident / divisor;
/*     */     } 
/* 183 */     return 1.0D;
/*     */   }
/*     */   
/*     */   public double getNumericalMean() {
/* 193 */     return (this.a + this.b + this.c) / 3.0D;
/*     */   }
/*     */   
/*     */   public double getNumericalVariance() {
/* 203 */     return (this.a * this.a + this.b * this.b + this.c * this.c - this.a * this.b - this.a * this.c - this.b * this.c) / 18.0D;
/*     */   }
/*     */   
/*     */   public double getSupportLowerBound() {
/* 215 */     return this.a;
/*     */   }
/*     */   
/*     */   public double getSupportUpperBound() {
/* 227 */     return this.b;
/*     */   }
/*     */   
/*     */   public boolean isSupportLowerBoundInclusive() {
/* 232 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isSupportUpperBoundInclusive() {
/* 237 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isSupportConnected() {
/* 248 */     return true;
/*     */   }
/*     */   
/*     */   public double inverseCumulativeProbability(double p) throws OutOfRangeException {
/* 254 */     if (p < 0.0D || p > 1.0D)
/* 255 */       throw new OutOfRangeException(Double.valueOf(p), Integer.valueOf(0), Integer.valueOf(1)); 
/* 257 */     if (p == 0.0D)
/* 258 */       return this.a; 
/* 260 */     if (p == 1.0D)
/* 261 */       return this.b; 
/* 263 */     if (p < (this.c - this.a) / (this.b - this.a))
/* 264 */       return this.a + FastMath.sqrt(p * (this.b - this.a) * (this.c - this.a)); 
/* 266 */     return this.b - FastMath.sqrt((1.0D - p) * (this.b - this.a) * (this.b - this.c));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\distribution\TriangularDistribution.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
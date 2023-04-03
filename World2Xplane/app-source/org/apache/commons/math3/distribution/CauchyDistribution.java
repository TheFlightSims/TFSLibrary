/*     */ package org.apache.commons.math3.distribution;
/*     */ 
/*     */ import org.apache.commons.math3.exception.NotStrictlyPositiveException;
/*     */ import org.apache.commons.math3.exception.OutOfRangeException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class CauchyDistribution extends AbstractRealDistribution {
/*     */   public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1.0E-9D;
/*     */   
/*     */   private static final long serialVersionUID = 8589540077390120676L;
/*     */   
/*     */   private final double median;
/*     */   
/*     */   private final double scale;
/*     */   
/*     */   private final double solverAbsoluteAccuracy;
/*     */   
/*     */   public CauchyDistribution() {
/*  52 */     this(0.0D, 1.0D);
/*     */   }
/*     */   
/*     */   public CauchyDistribution(double median, double scale) {
/*  62 */     this(median, scale, 1.0E-9D);
/*     */   }
/*     */   
/*     */   public CauchyDistribution(double median, double scale, double inverseCumAccuracy) {
/*  78 */     if (scale <= 0.0D)
/*  79 */       throw new NotStrictlyPositiveException(LocalizedFormats.SCALE, Double.valueOf(scale)); 
/*  81 */     this.scale = scale;
/*  82 */     this.median = median;
/*  83 */     this.solverAbsoluteAccuracy = inverseCumAccuracy;
/*     */   }
/*     */   
/*     */   public double cumulativeProbability(double x) {
/*  88 */     return 0.5D + FastMath.atan((x - this.median) / this.scale) / Math.PI;
/*     */   }
/*     */   
/*     */   public double getMedian() {
/*  97 */     return this.median;
/*     */   }
/*     */   
/*     */   public double getScale() {
/* 106 */     return this.scale;
/*     */   }
/*     */   
/*     */   public double probability(double x) {
/* 117 */     return 0.0D;
/*     */   }
/*     */   
/*     */   public double density(double x) {
/* 122 */     double dev = x - this.median;
/* 123 */     return 0.3183098861837907D * this.scale / (dev * dev + this.scale * this.scale);
/*     */   }
/*     */   
/*     */   public double inverseCumulativeProbability(double p) throws OutOfRangeException {
/*     */     double ret;
/* 135 */     if (p < 0.0D || p > 1.0D)
/* 136 */       throw new OutOfRangeException(Double.valueOf(p), Integer.valueOf(0), Integer.valueOf(1)); 
/* 137 */     if (p == 0.0D) {
/* 138 */       ret = Double.NEGATIVE_INFINITY;
/* 139 */     } else if (p == 1.0D) {
/* 140 */       ret = Double.POSITIVE_INFINITY;
/*     */     } else {
/* 142 */       ret = this.median + this.scale * FastMath.tan(Math.PI * (p - 0.5D));
/*     */     } 
/* 144 */     return ret;
/*     */   }
/*     */   
/*     */   protected double getSolverAbsoluteAccuracy() {
/* 150 */     return this.solverAbsoluteAccuracy;
/*     */   }
/*     */   
/*     */   public double getNumericalMean() {
/* 161 */     return Double.NaN;
/*     */   }
/*     */   
/*     */   public double getNumericalVariance() {
/* 172 */     return Double.NaN;
/*     */   }
/*     */   
/*     */   public double getSupportLowerBound() {
/* 184 */     return Double.NEGATIVE_INFINITY;
/*     */   }
/*     */   
/*     */   public double getSupportUpperBound() {
/* 196 */     return Double.POSITIVE_INFINITY;
/*     */   }
/*     */   
/*     */   public boolean isSupportLowerBoundInclusive() {
/* 201 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isSupportUpperBoundInclusive() {
/* 206 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isSupportConnected() {
/* 217 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\distribution\CauchyDistribution.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
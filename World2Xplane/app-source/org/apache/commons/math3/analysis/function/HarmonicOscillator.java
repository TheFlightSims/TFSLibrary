/*     */ package org.apache.commons.math3.analysis.function;
/*     */ 
/*     */ import org.apache.commons.math3.analysis.DifferentiableUnivariateFunction;
/*     */ import org.apache.commons.math3.analysis.ParametricUnivariateFunction;
/*     */ import org.apache.commons.math3.analysis.UnivariateFunction;
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class HarmonicOscillator implements DifferentiableUnivariateFunction {
/*     */   private final double amplitude;
/*     */   
/*     */   private final double omega;
/*     */   
/*     */   private final double phase;
/*     */   
/*     */   public HarmonicOscillator(double amplitude, double omega, double phase) {
/*  52 */     this.amplitude = amplitude;
/*  53 */     this.omega = omega;
/*  54 */     this.phase = phase;
/*     */   }
/*     */   
/*     */   public double value(double x) {
/*  59 */     return value(this.omega * x + this.phase, this.amplitude);
/*     */   }
/*     */   
/*     */   public UnivariateFunction derivative() {
/*  64 */     return new UnivariateFunction() {
/*     */         public double value(double x) {
/*  67 */           return -HarmonicOscillator.this.amplitude * HarmonicOscillator.this.omega * FastMath.sin(HarmonicOscillator.this.omega * x + HarmonicOscillator.this.phase);
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public static class Parametric implements ParametricUnivariateFunction {
/*     */     public double value(double x, double... param) {
/*  93 */       validateParameters(param);
/*  94 */       return HarmonicOscillator.value(x * param[1] + param[2], param[0]);
/*     */     }
/*     */     
/*     */     public double[] gradient(double x, double... param) {
/* 111 */       validateParameters(param);
/* 113 */       double amplitude = param[0];
/* 114 */       double omega = param[1];
/* 115 */       double phase = param[2];
/* 117 */       double xTimesOmegaPlusPhase = omega * x + phase;
/* 118 */       double a = HarmonicOscillator.value(xTimesOmegaPlusPhase, 1.0D);
/* 119 */       double p = -amplitude * FastMath.sin(xTimesOmegaPlusPhase);
/* 120 */       double w = p * x;
/* 122 */       return new double[] { a, w, p };
/*     */     }
/*     */     
/*     */     private void validateParameters(double[] param) {
/* 136 */       if (param == null)
/* 137 */         throw new NullArgumentException(); 
/* 139 */       if (param.length != 3)
/* 140 */         throw new DimensionMismatchException(param.length, 3); 
/*     */     }
/*     */   }
/*     */   
/*     */   private static double value(double xTimesOmegaPlusPhase, double amplitude) {
/* 152 */     return amplitude * FastMath.cos(xTimesOmegaPlusPhase);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\function\HarmonicOscillator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
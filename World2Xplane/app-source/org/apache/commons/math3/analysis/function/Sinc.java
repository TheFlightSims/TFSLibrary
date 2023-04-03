/*     */ package org.apache.commons.math3.analysis.function;
/*     */ 
/*     */ import org.apache.commons.math3.analysis.DifferentiableUnivariateFunction;
/*     */ import org.apache.commons.math3.analysis.UnivariateFunction;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class Sinc implements DifferentiableUnivariateFunction {
/*     */   private static final double SHORTCUT = 1.0E-9D;
/*     */   
/*     */   private final boolean normalized;
/*     */   
/*     */   public Sinc() {
/*  49 */     this(false);
/*     */   }
/*     */   
/*     */   public Sinc(boolean normalized) {
/*  59 */     this.normalized = normalized;
/*     */   }
/*     */   
/*     */   public double value(double x) {
/*  64 */     if (this.normalized) {
/*  65 */       double piTimesX = Math.PI * x;
/*  66 */       return sinc(piTimesX);
/*     */     } 
/*  68 */     return sinc(x);
/*     */   }
/*     */   
/*     */   public UnivariateFunction derivative() {
/*  74 */     if (this.normalized)
/*  75 */       return new UnivariateFunction() {
/*     */           public double value(double x) {
/*  78 */             double piTimesX = Math.PI * x;
/*  79 */             return Sinc.sincDerivative(piTimesX);
/*     */           }
/*     */         }; 
/*  83 */     return new UnivariateFunction() {
/*     */         public double value(double x) {
/*  86 */           return Sinc.sincDerivative(x);
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   private static double sinc(double x) {
/* 101 */     return (FastMath.abs(x) < 1.0E-9D) ? 1.0D : (FastMath.sin(x) / x);
/*     */   }
/*     */   
/*     */   private static double sincDerivative(double x) {
/* 114 */     return (FastMath.abs(x) < 1.0E-9D) ? 0.0D : ((FastMath.cos(x) - FastMath.sin(x) / x) / x);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\function\Sinc.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
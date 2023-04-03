/*     */ package org.apache.commons.math3.ode.nonstiff;
/*     */ 
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class HighamHall54Integrator extends EmbeddedRungeKuttaIntegrator {
/*     */   private static final String METHOD_NAME = "Higham-Hall 5(4)";
/*     */   
/*  43 */   private static final double[] STATIC_C = new double[] { 0.2222222222222222D, 0.3333333333333333D, 0.5D, 0.6D, 1.0D, 1.0D };
/*     */   
/*  48 */   private static final double[][] STATIC_A = new double[][] { { 0.2222222222222222D }, { 0.08333333333333333D, 0.25D }, { 0.125D, 0.0D, 0.375D }, { 0.182D, -0.27D, 0.624D, 0.064D }, { -0.55D, 1.35D, 2.4D, -7.2D, 5.0D }, { 0.08333333333333333D, 0.0D, 0.84375D, -1.3333333333333333D, 1.3020833333333333D, 0.10416666666666667D } };
/*     */   
/*  58 */   private static final double[] STATIC_B = new double[] { 0.08333333333333333D, 0.0D, 0.84375D, -1.3333333333333333D, 1.3020833333333333D, 0.10416666666666667D, 0.0D };
/*     */   
/*  63 */   private static final double[] STATIC_E = new double[] { -0.05D, 0.0D, 0.50625D, -1.2D, 0.78125D, 0.0625D, -0.1D };
/*     */   
/*     */   public HighamHall54Integrator(double minStep, double maxStep, double scalAbsoluteTolerance, double scalRelativeTolerance) {
/*  81 */     super("Higham-Hall 5(4)", false, STATIC_C, STATIC_A, STATIC_B, new HighamHall54StepInterpolator(), minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
/*     */   }
/*     */   
/*     */   public HighamHall54Integrator(double minStep, double maxStep, double[] vecAbsoluteTolerance, double[] vecRelativeTolerance) {
/*  99 */     super("Higham-Hall 5(4)", false, STATIC_C, STATIC_A, STATIC_B, new HighamHall54StepInterpolator(), minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
/*     */   }
/*     */   
/*     */   public int getOrder() {
/* 106 */     return 5;
/*     */   }
/*     */   
/*     */   protected double estimateError(double[][] yDotK, double[] y0, double[] y1, double h) {
/* 115 */     double error = 0.0D;
/* 117 */     for (int j = 0; j < this.mainSetDimension; j++) {
/* 118 */       double errSum = STATIC_E[0] * yDotK[0][j];
/* 119 */       for (int l = 1; l < STATIC_E.length; l++)
/* 120 */         errSum += STATIC_E[l] * yDotK[l][j]; 
/* 123 */       double yScale = FastMath.max(FastMath.abs(y0[j]), FastMath.abs(y1[j]));
/* 124 */       double tol = (this.vecAbsoluteTolerance == null) ? (this.scalAbsoluteTolerance + this.scalRelativeTolerance * yScale) : (this.vecAbsoluteTolerance[j] + this.vecRelativeTolerance[j] * yScale);
/* 127 */       double ratio = h * errSum / tol;
/* 128 */       error += ratio * ratio;
/*     */     } 
/* 132 */     return FastMath.sqrt(error / this.mainSetDimension);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\ode\nonstiff\HighamHall54Integrator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
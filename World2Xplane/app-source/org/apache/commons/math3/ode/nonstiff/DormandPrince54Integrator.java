/*     */ package org.apache.commons.math3.ode.nonstiff;
/*     */ 
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class DormandPrince54Integrator extends EmbeddedRungeKuttaIntegrator {
/*     */   private static final String METHOD_NAME = "Dormand-Prince 5(4)";
/*     */   
/*  55 */   private static final double[] STATIC_C = new double[] { 0.2D, 0.3D, 0.8D, 0.8888888888888888D, 1.0D, 1.0D };
/*     */   
/*  60 */   private static final double[][] STATIC_A = new double[][] { { 0.2D }, { 0.075D, 0.225D }, { 0.9777777777777777D, -3.7333333333333334D, 3.5555555555555554D }, { 2.9525986892242035D, -11.595793324188385D, 9.822892851699436D, -0.2908093278463649D }, { 2.8462752525252526D, -10.757575757575758D, 8.906422717743473D, 0.2784090909090909D, -0.2735313036020583D }, { 0.09114583333333333D, 0.0D, 0.44923629829290207D, 0.6510416666666666D, -0.322376179245283D, 0.13095238095238096D } };
/*     */   
/*  70 */   private static final double[] STATIC_B = new double[] { 0.09114583333333333D, 0.0D, 0.44923629829290207D, 0.6510416666666666D, -0.322376179245283D, 0.13095238095238096D, 0.0D };
/*     */   
/*     */   private static final double E1 = 0.0012326388888888888D;
/*     */   
/*     */   private static final double E3 = -0.0042527702905061394D;
/*     */   
/*     */   private static final double E4 = 0.03697916666666667D;
/*     */   
/*     */   private static final double E5 = -0.05086379716981132D;
/*     */   
/*     */   private static final double E6 = 0.0419047619047619D;
/*     */   
/*     */   private static final double E7 = -0.025D;
/*     */   
/*     */   public DormandPrince54Integrator(double minStep, double maxStep, double scalAbsoluteTolerance, double scalRelativeTolerance) {
/* 108 */     super("Dormand-Prince 5(4)", true, STATIC_C, STATIC_A, STATIC_B, new DormandPrince54StepInterpolator(), minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
/*     */   }
/*     */   
/*     */   public DormandPrince54Integrator(double minStep, double maxStep, double[] vecAbsoluteTolerance, double[] vecRelativeTolerance) {
/* 126 */     super("Dormand-Prince 5(4)", true, STATIC_C, STATIC_A, STATIC_B, new DormandPrince54StepInterpolator(), minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
/*     */   }
/*     */   
/*     */   public int getOrder() {
/* 133 */     return 5;
/*     */   }
/*     */   
/*     */   protected double estimateError(double[][] yDotK, double[] y0, double[] y1, double h) {
/* 142 */     double error = 0.0D;
/* 144 */     for (int j = 0; j < this.mainSetDimension; j++) {
/* 145 */       double errSum = 0.0012326388888888888D * yDotK[0][j] + -0.0042527702905061394D * yDotK[2][j] + 0.03697916666666667D * yDotK[3][j] + -0.05086379716981132D * yDotK[4][j] + 0.0419047619047619D * yDotK[5][j] + -0.025D * yDotK[6][j];
/* 149 */       double yScale = FastMath.max(FastMath.abs(y0[j]), FastMath.abs(y1[j]));
/* 150 */       double tol = (this.vecAbsoluteTolerance == null) ? (this.scalAbsoluteTolerance + this.scalRelativeTolerance * yScale) : (this.vecAbsoluteTolerance[j] + this.vecRelativeTolerance[j] * yScale);
/* 153 */       double ratio = h * errSum / tol;
/* 154 */       error += ratio * ratio;
/*     */     } 
/* 158 */     return FastMath.sqrt(error / this.mainSetDimension);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\ode\nonstiff\DormandPrince54Integrator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
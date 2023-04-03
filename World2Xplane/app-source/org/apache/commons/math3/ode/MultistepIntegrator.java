/*     */ package org.apache.commons.math3.ode;
/*     */ 
/*     */ import org.apache.commons.math3.exception.MathIllegalArgumentException;
/*     */ import org.apache.commons.math3.exception.MathIllegalStateException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.linear.Array2DRowRealMatrix;
/*     */ import org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator;
/*     */ import org.apache.commons.math3.ode.nonstiff.DormandPrince853Integrator;
/*     */ import org.apache.commons.math3.ode.sampling.StepHandler;
/*     */ import org.apache.commons.math3.ode.sampling.StepInterpolator;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public abstract class MultistepIntegrator extends AdaptiveStepsizeIntegrator {
/*     */   protected double[] scaled;
/*     */   
/*     */   protected Array2DRowRealMatrix nordsieck;
/*     */   
/*     */   private FirstOrderIntegrator starter;
/*     */   
/*     */   private final int nSteps;
/*     */   
/*     */   private double exp;
/*     */   
/*     */   private double safety;
/*     */   
/*     */   private double minReduction;
/*     */   
/*     */   private double maxGrowth;
/*     */   
/*     */   protected MultistepIntegrator(String name, int nSteps, int order, double minStep, double maxStep, double scalAbsoluteTolerance, double scalRelativeTolerance) {
/* 114 */     super(name, minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
/* 116 */     if (nSteps <= 1)
/* 117 */       throw new MathIllegalArgumentException(LocalizedFormats.INTEGRATION_METHOD_NEEDS_AT_LEAST_TWO_PREVIOUS_POINTS, new Object[] { name }); 
/* 122 */     this.starter = (FirstOrderIntegrator)new DormandPrince853Integrator(minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
/* 125 */     this.nSteps = nSteps;
/* 127 */     this.exp = -1.0D / order;
/* 130 */     setSafety(0.9D);
/* 131 */     setMinReduction(0.2D);
/* 132 */     setMaxGrowth(FastMath.pow(2.0D, -this.exp));
/*     */   }
/*     */   
/*     */   protected MultistepIntegrator(String name, int nSteps, int order, double minStep, double maxStep, double[] vecAbsoluteTolerance, double[] vecRelativeTolerance) {
/* 160 */     super(name, minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
/* 161 */     this.starter = (FirstOrderIntegrator)new DormandPrince853Integrator(minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
/* 164 */     this.nSteps = nSteps;
/* 166 */     this.exp = -1.0D / order;
/* 169 */     setSafety(0.9D);
/* 170 */     setMinReduction(0.2D);
/* 171 */     setMaxGrowth(FastMath.pow(2.0D, -this.exp));
/*     */   }
/*     */   
/*     */   public ODEIntegrator getStarterIntegrator() {
/* 180 */     return this.starter;
/*     */   }
/*     */   
/*     */   public void setStarterIntegrator(FirstOrderIntegrator starterIntegrator) {
/* 191 */     this.starter = starterIntegrator;
/*     */   }
/*     */   
/*     */   protected void start(double t0, double[] y0, double t) throws MathIllegalStateException {
/* 215 */     this.starter.clearEventHandlers();
/* 216 */     this.starter.clearStepHandlers();
/* 219 */     this.starter.addStepHandler(new NordsieckInitializer(this.nSteps, y0.length));
/*     */     try {
/* 223 */       this.starter.integrate(new CountingDifferentialEquations(y0.length), t0, y0, t, new double[y0.length]);
/* 225 */     } catch (InitializationCompletedMarkerException icme) {}
/* 230 */     this.starter.clearStepHandlers();
/*     */   }
/*     */   
/*     */   protected abstract Array2DRowRealMatrix initializeHighOrderDerivatives(double paramDouble, double[] paramArrayOfdouble, double[][] paramArrayOfdouble1, double[][] paramArrayOfdouble2);
/*     */   
/*     */   public double getMinReduction() {
/* 250 */     return this.minReduction;
/*     */   }
/*     */   
/*     */   public void setMinReduction(double minReduction) {
/* 257 */     this.minReduction = minReduction;
/*     */   }
/*     */   
/*     */   public double getMaxGrowth() {
/* 264 */     return this.maxGrowth;
/*     */   }
/*     */   
/*     */   public void setMaxGrowth(double maxGrowth) {
/* 271 */     this.maxGrowth = maxGrowth;
/*     */   }
/*     */   
/*     */   public double getSafety() {
/* 278 */     return this.safety;
/*     */   }
/*     */   
/*     */   public void setSafety(double safety) {
/* 285 */     this.safety = safety;
/*     */   }
/*     */   
/*     */   protected double computeStepGrowShrinkFactor(double error) {
/* 293 */     return FastMath.min(this.maxGrowth, FastMath.max(this.minReduction, this.safety * FastMath.pow(error, this.exp)));
/*     */   }
/*     */   
/*     */   public static interface NordsieckTransformer {
/*     */     Array2DRowRealMatrix initializeHighOrderDerivatives(double param1Double, double[] param1ArrayOfdouble, double[][] param1ArrayOfdouble1, double[][] param1ArrayOfdouble2);
/*     */   }
/*     */   
/*     */   private class NordsieckInitializer implements StepHandler {
/*     */     private int count;
/*     */     
/*     */     private final double[] t;
/*     */     
/*     */     private final double[][] y;
/*     */     
/*     */     private final double[][] yDot;
/*     */     
/*     */     public NordsieckInitializer(int nSteps, int n) {
/* 331 */       this.count = 0;
/* 332 */       this.t = new double[nSteps];
/* 333 */       this.y = new double[nSteps][n];
/* 334 */       this.yDot = new double[nSteps][n];
/*     */     }
/*     */     
/*     */     public void handleStep(StepInterpolator interpolator, boolean isLast) {
/* 340 */       double prev = interpolator.getPreviousTime();
/* 341 */       double curr = interpolator.getCurrentTime();
/* 343 */       if (this.count == 0) {
/* 345 */         interpolator.setInterpolatedTime(prev);
/* 346 */         this.t[0] = prev;
/* 347 */         System.arraycopy(interpolator.getInterpolatedState(), 0, this.y[0], 0, (this.y[0]).length);
/* 349 */         System.arraycopy(interpolator.getInterpolatedDerivatives(), 0, this.yDot[0], 0, (this.yDot[0]).length);
/*     */       } 
/* 354 */       this.count++;
/* 355 */       interpolator.setInterpolatedTime(curr);
/* 356 */       this.t[this.count] = curr;
/* 357 */       System.arraycopy(interpolator.getInterpolatedState(), 0, this.y[this.count], 0, (this.y[this.count]).length);
/* 359 */       System.arraycopy(interpolator.getInterpolatedDerivatives(), 0, this.yDot[this.count], 0, (this.yDot[this.count]).length);
/* 362 */       if (this.count == this.t.length - 1) {
/* 365 */         MultistepIntegrator.this.stepStart = this.t[0];
/* 366 */         MultistepIntegrator.this.stepSize = (this.t[this.t.length - 1] - this.t[0]) / (this.t.length - 1);
/* 369 */         MultistepIntegrator.this.scaled = (double[])this.yDot[0].clone();
/* 370 */         for (int j = 0; j < MultistepIntegrator.this.scaled.length; j++)
/* 371 */           MultistepIntegrator.this.scaled[j] = MultistepIntegrator.this.scaled[j] * MultistepIntegrator.this.stepSize; 
/* 375 */         MultistepIntegrator.this.nordsieck = MultistepIntegrator.this.initializeHighOrderDerivatives(MultistepIntegrator.this.stepSize, this.t, this.y, this.yDot);
/* 378 */         throw new MultistepIntegrator.InitializationCompletedMarkerException();
/*     */       } 
/*     */     }
/*     */     
/*     */     public void init(double t0, double[] y0, double time) {}
/*     */   }
/*     */   
/*     */   private static class InitializationCompletedMarkerException extends RuntimeException {
/*     */     private static final long serialVersionUID = -1914085471038046418L;
/*     */     
/*     */     public InitializationCompletedMarkerException() {
/* 400 */       super((Throwable)null);
/*     */     }
/*     */   }
/*     */   
/*     */   private class CountingDifferentialEquations implements FirstOrderDifferentialEquations {
/*     */     private final int dimension;
/*     */     
/*     */     public CountingDifferentialEquations(int dimension) {
/* 415 */       this.dimension = dimension;
/*     */     }
/*     */     
/*     */     public void computeDerivatives(double t, double[] y, double[] dot) {
/* 420 */       MultistepIntegrator.this.computeDerivatives(t, y, dot);
/*     */     }
/*     */     
/*     */     public int getDimension() {
/* 425 */       return this.dimension;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\ode\MultistepIntegrator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
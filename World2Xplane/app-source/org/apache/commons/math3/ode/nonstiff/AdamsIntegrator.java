/*     */ package org.apache.commons.math3.ode.nonstiff;
/*     */ 
/*     */ import org.apache.commons.math3.exception.MathIllegalArgumentException;
/*     */ import org.apache.commons.math3.exception.MathIllegalStateException;
/*     */ import org.apache.commons.math3.linear.Array2DRowRealMatrix;
/*     */ import org.apache.commons.math3.ode.ExpandableStatefulODE;
/*     */ import org.apache.commons.math3.ode.MultistepIntegrator;
/*     */ 
/*     */ public abstract class AdamsIntegrator extends MultistepIntegrator {
/*     */   private final AdamsNordsieckTransformer transformer;
/*     */   
/*     */   public AdamsIntegrator(String name, int nSteps, int order, double minStep, double maxStep, double scalAbsoluteTolerance, double scalRelativeTolerance) throws IllegalArgumentException {
/*  57 */     super(name, nSteps, order, minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
/*  59 */     this.transformer = AdamsNordsieckTransformer.getInstance(nSteps);
/*     */   }
/*     */   
/*     */   public AdamsIntegrator(String name, int nSteps, int order, double minStep, double maxStep, double[] vecAbsoluteTolerance, double[] vecRelativeTolerance) throws IllegalArgumentException {
/*  82 */     super(name, nSteps, order, minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
/*  84 */     this.transformer = AdamsNordsieckTransformer.getInstance(nSteps);
/*     */   }
/*     */   
/*     */   public abstract void integrate(ExpandableStatefulODE paramExpandableStatefulODE, double paramDouble) throws MathIllegalStateException, MathIllegalArgumentException;
/*     */   
/*     */   protected Array2DRowRealMatrix initializeHighOrderDerivatives(double h, double[] t, double[][] y, double[][] yDot) {
/*  97 */     return this.transformer.initializeHighOrderDerivatives(h, t, y, yDot);
/*     */   }
/*     */   
/*     */   public Array2DRowRealMatrix updateHighOrderDerivativesPhase1(Array2DRowRealMatrix highOrder) {
/* 112 */     return this.transformer.updateHighOrderDerivativesPhase1(highOrder);
/*     */   }
/*     */   
/*     */   public void updateHighOrderDerivativesPhase2(double[] start, double[] end, Array2DRowRealMatrix highOrder) {
/* 131 */     this.transformer.updateHighOrderDerivativesPhase2(start, end, highOrder);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\ode\nonstiff\AdamsIntegrator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
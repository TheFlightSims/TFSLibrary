/*     */ package org.apache.commons.math3.ode.nonstiff;
/*     */ 
/*     */ import org.apache.commons.math3.ode.sampling.StepInterpolator;
/*     */ 
/*     */ class MidpointStepInterpolator extends RungeKuttaStepInterpolator {
/*     */   private static final long serialVersionUID = 20111120L;
/*     */   
/*     */   public MidpointStepInterpolator() {}
/*     */   
/*     */   public MidpointStepInterpolator(MidpointStepInterpolator interpolator) {
/*  74 */     super(interpolator);
/*     */   }
/*     */   
/*     */   protected StepInterpolator doCopy() {
/*  80 */     return (StepInterpolator)new MidpointStepInterpolator(this);
/*     */   }
/*     */   
/*     */   protected void computeInterpolatedStateAndDerivatives(double theta, double oneMinusThetaH) {
/*  89 */     double coeffDot2 = 2.0D * theta;
/*  90 */     double coeffDot1 = 1.0D - coeffDot2;
/*  92 */     if (this.previousState != null && theta <= 0.5D) {
/*  93 */       double coeff1 = theta * oneMinusThetaH;
/*  94 */       double coeff2 = theta * theta * this.h;
/*  95 */       for (int i = 0; i < this.interpolatedState.length; i++) {
/*  96 */         double yDot1 = this.yDotK[0][i];
/*  97 */         double yDot2 = this.yDotK[1][i];
/*  98 */         this.interpolatedState[i] = this.previousState[i] + coeff1 * yDot1 + coeff2 * yDot2;
/*  99 */         this.interpolatedDerivatives[i] = coeffDot1 * yDot1 + coeffDot2 * yDot2;
/*     */       } 
/*     */     } else {
/* 102 */       double coeff1 = oneMinusThetaH * theta;
/* 103 */       double coeff2 = oneMinusThetaH * (1.0D + theta);
/* 104 */       for (int i = 0; i < this.interpolatedState.length; i++) {
/* 105 */         double yDot1 = this.yDotK[0][i];
/* 106 */         double yDot2 = this.yDotK[1][i];
/* 107 */         this.interpolatedState[i] = this.currentState[i] + coeff1 * yDot1 - coeff2 * yDot2;
/* 108 */         this.interpolatedDerivatives[i] = coeffDot1 * yDot1 + coeffDot2 * yDot2;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\ode\nonstiff\MidpointStepInterpolator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
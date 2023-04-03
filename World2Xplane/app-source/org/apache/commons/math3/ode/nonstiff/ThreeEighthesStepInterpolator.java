/*     */ package org.apache.commons.math3.ode.nonstiff;
/*     */ 
/*     */ import org.apache.commons.math3.ode.sampling.StepInterpolator;
/*     */ 
/*     */ class ThreeEighthesStepInterpolator extends RungeKuttaStepInterpolator {
/*     */   private static final long serialVersionUID = 20111120L;
/*     */   
/*     */   public ThreeEighthesStepInterpolator() {}
/*     */   
/*     */   public ThreeEighthesStepInterpolator(ThreeEighthesStepInterpolator interpolator) {
/*  84 */     super(interpolator);
/*     */   }
/*     */   
/*     */   protected StepInterpolator doCopy() {
/*  90 */     return (StepInterpolator)new ThreeEighthesStepInterpolator(this);
/*     */   }
/*     */   
/*     */   protected void computeInterpolatedStateAndDerivatives(double theta, double oneMinusThetaH) {
/*  99 */     double coeffDot3 = 0.75D * theta;
/* 100 */     double coeffDot1 = coeffDot3 * (4.0D * theta - 5.0D) + 1.0D;
/* 101 */     double coeffDot2 = coeffDot3 * (5.0D - 6.0D * theta);
/* 102 */     double coeffDot4 = coeffDot3 * (2.0D * theta - 1.0D);
/* 104 */     if (this.previousState != null && theta <= 0.5D) {
/* 105 */       double s = theta * this.h / 8.0D;
/* 106 */       double fourTheta2 = 4.0D * theta * theta;
/* 107 */       double coeff1 = s * (8.0D - 15.0D * theta + 2.0D * fourTheta2);
/* 108 */       double coeff2 = 3.0D * s * (5.0D * theta - fourTheta2);
/* 109 */       double coeff3 = 3.0D * s * theta;
/* 110 */       double coeff4 = s * (-3.0D * theta + fourTheta2);
/* 111 */       for (int i = 0; i < this.interpolatedState.length; i++) {
/* 112 */         double yDot1 = this.yDotK[0][i];
/* 113 */         double yDot2 = this.yDotK[1][i];
/* 114 */         double yDot3 = this.yDotK[2][i];
/* 115 */         double yDot4 = this.yDotK[3][i];
/* 116 */         this.interpolatedState[i] = this.previousState[i] + coeff1 * yDot1 + coeff2 * yDot2 + coeff3 * yDot3 + coeff4 * yDot4;
/* 118 */         this.interpolatedDerivatives[i] = coeffDot1 * yDot1 + coeffDot2 * yDot2 + coeffDot3 * yDot3 + coeffDot4 * yDot4;
/*     */       } 
/*     */     } else {
/* 123 */       double s = oneMinusThetaH / 8.0D;
/* 124 */       double fourTheta2 = 4.0D * theta * theta;
/* 125 */       double coeff1 = s * (1.0D - 7.0D * theta + 2.0D * fourTheta2);
/* 126 */       double coeff2 = 3.0D * s * (1.0D + theta - fourTheta2);
/* 127 */       double coeff3 = 3.0D * s * (1.0D + theta);
/* 128 */       double coeff4 = s * (1.0D + theta + fourTheta2);
/* 129 */       for (int i = 0; i < this.interpolatedState.length; i++) {
/* 130 */         double yDot1 = this.yDotK[0][i];
/* 131 */         double yDot2 = this.yDotK[1][i];
/* 132 */         double yDot3 = this.yDotK[2][i];
/* 133 */         double yDot4 = this.yDotK[3][i];
/* 134 */         this.interpolatedState[i] = this.currentState[i] - coeff1 * yDot1 - coeff2 * yDot2 - coeff3 * yDot3 - coeff4 * yDot4;
/* 136 */         this.interpolatedDerivatives[i] = coeffDot1 * yDot1 + coeffDot2 * yDot2 + coeffDot3 * yDot3 + coeffDot4 * yDot4;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\ode\nonstiff\ThreeEighthesStepInterpolator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
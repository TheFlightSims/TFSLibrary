/*     */ package org.apache.commons.math3.ode.nonstiff;
/*     */ 
/*     */ import org.apache.commons.math3.ode.sampling.StepInterpolator;
/*     */ 
/*     */ class ClassicalRungeKuttaStepInterpolator extends RungeKuttaStepInterpolator {
/*     */   private static final long serialVersionUID = 20111120L;
/*     */   
/*     */   public ClassicalRungeKuttaStepInterpolator() {}
/*     */   
/*     */   public ClassicalRungeKuttaStepInterpolator(ClassicalRungeKuttaStepInterpolator interpolator) {
/*  81 */     super(interpolator);
/*     */   }
/*     */   
/*     */   protected StepInterpolator doCopy() {
/*  87 */     return (StepInterpolator)new ClassicalRungeKuttaStepInterpolator(this);
/*     */   }
/*     */   
/*     */   protected void computeInterpolatedStateAndDerivatives(double theta, double oneMinusThetaH) {
/*  95 */     double oneMinusTheta = 1.0D - theta;
/*  96 */     double oneMinus2Theta = 1.0D - 2.0D * theta;
/*  97 */     double coeffDot1 = oneMinusTheta * oneMinus2Theta;
/*  98 */     double coeffDot23 = 2.0D * theta * oneMinusTheta;
/*  99 */     double coeffDot4 = -theta * oneMinus2Theta;
/* 100 */     if (this.previousState != null && theta <= 0.5D) {
/* 101 */       double fourTheta2 = 4.0D * theta * theta;
/* 102 */       double s = theta * this.h / 6.0D;
/* 103 */       double coeff1 = s * (6.0D - 9.0D * theta + fourTheta2);
/* 104 */       double coeff23 = s * (6.0D * theta - fourTheta2);
/* 105 */       double coeff4 = s * (-3.0D * theta + fourTheta2);
/* 106 */       for (int i = 0; i < this.interpolatedState.length; i++) {
/* 107 */         double yDot1 = this.yDotK[0][i];
/* 108 */         double yDot23 = this.yDotK[1][i] + this.yDotK[2][i];
/* 109 */         double yDot4 = this.yDotK[3][i];
/* 110 */         this.interpolatedState[i] = this.previousState[i] + coeff1 * yDot1 + coeff23 * yDot23 + coeff4 * yDot4;
/* 112 */         this.interpolatedDerivatives[i] = coeffDot1 * yDot1 + coeffDot23 * yDot23 + coeffDot4 * yDot4;
/*     */       } 
/*     */     } else {
/* 116 */       double fourTheta = 4.0D * theta;
/* 117 */       double s = oneMinusThetaH / 6.0D;
/* 118 */       double coeff1 = s * ((-fourTheta + 5.0D) * theta - 1.0D);
/* 119 */       double coeff23 = s * ((fourTheta - 2.0D) * theta - 2.0D);
/* 120 */       double coeff4 = s * ((-fourTheta - 1.0D) * theta - 1.0D);
/* 121 */       for (int i = 0; i < this.interpolatedState.length; i++) {
/* 122 */         double yDot1 = this.yDotK[0][i];
/* 123 */         double yDot23 = this.yDotK[1][i] + this.yDotK[2][i];
/* 124 */         double yDot4 = this.yDotK[3][i];
/* 125 */         this.interpolatedState[i] = this.currentState[i] + coeff1 * yDot1 + coeff23 * yDot23 + coeff4 * yDot4;
/* 127 */         this.interpolatedDerivatives[i] = coeffDot1 * yDot1 + coeffDot23 * yDot23 + coeffDot4 * yDot4;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\ode\nonstiff\ClassicalRungeKuttaStepInterpolator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
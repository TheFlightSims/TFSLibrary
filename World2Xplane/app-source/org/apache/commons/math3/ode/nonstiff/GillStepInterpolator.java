/*     */ package org.apache.commons.math3.ode.nonstiff;
/*     */ 
/*     */ import org.apache.commons.math3.ode.sampling.StepInterpolator;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ class GillStepInterpolator extends RungeKuttaStepInterpolator {
/*  60 */   private static final double ONE_MINUS_INV_SQRT_2 = 1.0D - FastMath.sqrt(0.5D);
/*     */   
/*  63 */   private static final double ONE_PLUS_INV_SQRT_2 = 1.0D + FastMath.sqrt(0.5D);
/*     */   
/*     */   private static final long serialVersionUID = 20111120L;
/*     */   
/*     */   public GillStepInterpolator() {}
/*     */   
/*     */   public GillStepInterpolator(GillStepInterpolator interpolator) {
/*  88 */     super(interpolator);
/*     */   }
/*     */   
/*     */   protected StepInterpolator doCopy() {
/*  94 */     return (StepInterpolator)new GillStepInterpolator(this);
/*     */   }
/*     */   
/*     */   protected void computeInterpolatedStateAndDerivatives(double theta, double oneMinusThetaH) {
/* 103 */     double twoTheta = 2.0D * theta;
/* 104 */     double fourTheta2 = twoTheta * twoTheta;
/* 105 */     double coeffDot1 = theta * (twoTheta - 3.0D) + 1.0D;
/* 106 */     double cDot23 = twoTheta * (1.0D - theta);
/* 107 */     double coeffDot2 = cDot23 * ONE_MINUS_INV_SQRT_2;
/* 108 */     double coeffDot3 = cDot23 * ONE_PLUS_INV_SQRT_2;
/* 109 */     double coeffDot4 = theta * (twoTheta - 1.0D);
/* 111 */     if (this.previousState != null && theta <= 0.5D) {
/* 112 */       double s = theta * this.h / 6.0D;
/* 113 */       double c23 = s * (6.0D * theta - fourTheta2);
/* 114 */       double coeff1 = s * (6.0D - 9.0D * theta + fourTheta2);
/* 115 */       double coeff2 = c23 * ONE_MINUS_INV_SQRT_2;
/* 116 */       double coeff3 = c23 * ONE_PLUS_INV_SQRT_2;
/* 117 */       double coeff4 = s * (-3.0D * theta + fourTheta2);
/* 118 */       for (int i = 0; i < this.interpolatedState.length; i++) {
/* 119 */         double yDot1 = this.yDotK[0][i];
/* 120 */         double yDot2 = this.yDotK[1][i];
/* 121 */         double yDot3 = this.yDotK[2][i];
/* 122 */         double yDot4 = this.yDotK[3][i];
/* 123 */         this.interpolatedState[i] = this.previousState[i] + coeff1 * yDot1 + coeff2 * yDot2 + coeff3 * yDot3 + coeff4 * yDot4;
/* 125 */         this.interpolatedDerivatives[i] = coeffDot1 * yDot1 + coeffDot2 * yDot2 + coeffDot3 * yDot3 + coeffDot4 * yDot4;
/*     */       } 
/*     */     } else {
/* 129 */       double s = oneMinusThetaH / 6.0D;
/* 130 */       double c23 = s * (2.0D + twoTheta - fourTheta2);
/* 131 */       double coeff1 = s * (1.0D - 5.0D * theta + fourTheta2);
/* 132 */       double coeff2 = c23 * ONE_MINUS_INV_SQRT_2;
/* 133 */       double coeff3 = c23 * ONE_PLUS_INV_SQRT_2;
/* 134 */       double coeff4 = s * (1.0D + theta + fourTheta2);
/* 135 */       for (int i = 0; i < this.interpolatedState.length; i++) {
/* 136 */         double yDot1 = this.yDotK[0][i];
/* 137 */         double yDot2 = this.yDotK[1][i];
/* 138 */         double yDot3 = this.yDotK[2][i];
/* 139 */         double yDot4 = this.yDotK[3][i];
/* 140 */         this.interpolatedState[i] = this.currentState[i] - coeff1 * yDot1 - coeff2 * yDot2 - coeff3 * yDot3 - coeff4 * yDot4;
/* 142 */         this.interpolatedDerivatives[i] = coeffDot1 * yDot1 + coeffDot2 * yDot2 + coeffDot3 * yDot3 + coeffDot4 * yDot4;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\ode\nonstiff\GillStepInterpolator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
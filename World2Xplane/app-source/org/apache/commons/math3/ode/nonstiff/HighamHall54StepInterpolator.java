/*     */ package org.apache.commons.math3.ode.nonstiff;
/*     */ 
/*     */ import org.apache.commons.math3.ode.sampling.StepInterpolator;
/*     */ 
/*     */ class HighamHall54StepInterpolator extends RungeKuttaStepInterpolator {
/*     */   private static final long serialVersionUID = 20111120L;
/*     */   
/*     */   public HighamHall54StepInterpolator() {}
/*     */   
/*     */   public HighamHall54StepInterpolator(HighamHall54StepInterpolator interpolator) {
/*  59 */     super(interpolator);
/*     */   }
/*     */   
/*     */   protected StepInterpolator doCopy() {
/*  65 */     return (StepInterpolator)new HighamHall54StepInterpolator(this);
/*     */   }
/*     */   
/*     */   protected void computeInterpolatedStateAndDerivatives(double theta, double oneMinusThetaH) {
/*  74 */     double bDot0 = 1.0D + theta * (-7.5D + theta * (16.0D - 10.0D * theta));
/*  75 */     double bDot2 = theta * (28.6875D + theta * (-91.125D + 67.5D * theta));
/*  76 */     double bDot3 = theta * (-44.0D + theta * (152.0D - 120.0D * theta));
/*  77 */     double bDot4 = theta * (23.4375D + theta * (-78.125D + 62.5D * theta));
/*  78 */     double bDot5 = theta * 5.0D / 8.0D * (2.0D * theta - 1.0D);
/*  80 */     if (this.previousState != null && theta <= 0.5D) {
/*  81 */       double hTheta = this.h * theta;
/*  82 */       double b0 = hTheta * (1.0D + theta * (-3.75D + theta * (5.333333333333333D - 2.5D * theta)));
/*  83 */       double b2 = hTheta * theta * (14.34375D + theta * (-30.375D + theta * 135.0D / 8.0D));
/*  84 */       double b3 = hTheta * theta * (-22.0D + theta * (50.666666666666664D + theta * -30.0D));
/*  85 */       double b4 = hTheta * theta * (11.71875D + theta * (-26.041666666666668D + theta * 125.0D / 8.0D));
/*  86 */       double b5 = hTheta * theta * (-0.3125D + theta * 5.0D / 12.0D);
/*  87 */       for (int i = 0; i < this.interpolatedState.length; i++) {
/*  88 */         double yDot0 = this.yDotK[0][i];
/*  89 */         double yDot2 = this.yDotK[2][i];
/*  90 */         double yDot3 = this.yDotK[3][i];
/*  91 */         double yDot4 = this.yDotK[4][i];
/*  92 */         double yDot5 = this.yDotK[5][i];
/*  93 */         this.interpolatedState[i] = this.previousState[i] + b0 * yDot0 + b2 * yDot2 + b3 * yDot3 + b4 * yDot4 + b5 * yDot5;
/*  95 */         this.interpolatedDerivatives[i] = bDot0 * yDot0 + bDot2 * yDot2 + bDot3 * yDot3 + bDot4 * yDot4 + bDot5 * yDot5;
/*     */       } 
/*     */     } else {
/*  99 */       double theta2 = theta * theta;
/* 100 */       double b0 = this.h * (-0.08333333333333333D + theta * (1.0D + theta * (-3.75D + theta * (5.333333333333333D + theta * -5.0D / 2.0D))));
/* 101 */       double b2 = this.h * (-0.84375D + theta2 * (14.34375D + theta * (-30.375D + theta * 135.0D / 8.0D)));
/* 102 */       double b3 = this.h * (1.3333333333333333D + theta2 * (-22.0D + theta * (50.666666666666664D + theta * -30.0D)));
/* 103 */       double b4 = this.h * (-1.3020833333333333D + theta2 * (11.71875D + theta * (-26.041666666666668D + theta * 125.0D / 8.0D)));
/* 104 */       double b5 = this.h * (-0.10416666666666667D + theta2 * (-0.3125D + theta * 5.0D / 12.0D));
/* 105 */       for (int i = 0; i < this.interpolatedState.length; i++) {
/* 106 */         double yDot0 = this.yDotK[0][i];
/* 107 */         double yDot2 = this.yDotK[2][i];
/* 108 */         double yDot3 = this.yDotK[3][i];
/* 109 */         double yDot4 = this.yDotK[4][i];
/* 110 */         double yDot5 = this.yDotK[5][i];
/* 111 */         this.interpolatedState[i] = this.currentState[i] + b0 * yDot0 + b2 * yDot2 + b3 * yDot3 + b4 * yDot4 + b5 * yDot5;
/* 113 */         this.interpolatedDerivatives[i] = bDot0 * yDot0 + bDot2 * yDot2 + bDot3 * yDot3 + bDot4 * yDot4 + bDot5 * yDot5;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\ode\nonstiff\HighamHall54StepInterpolator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
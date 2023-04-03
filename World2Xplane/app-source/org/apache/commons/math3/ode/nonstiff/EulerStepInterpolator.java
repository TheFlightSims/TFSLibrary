/*    */ package org.apache.commons.math3.ode.nonstiff;
/*    */ 
/*    */ import org.apache.commons.math3.ode.sampling.StepInterpolator;
/*    */ 
/*    */ class EulerStepInterpolator extends RungeKuttaStepInterpolator {
/*    */   private static final long serialVersionUID = 20111120L;
/*    */   
/*    */   public EulerStepInterpolator() {}
/*    */   
/*    */   public EulerStepInterpolator(EulerStepInterpolator interpolator) {
/* 72 */     super(interpolator);
/*    */   }
/*    */   
/*    */   protected StepInterpolator doCopy() {
/* 78 */     return (StepInterpolator)new EulerStepInterpolator(this);
/*    */   }
/*    */   
/*    */   protected void computeInterpolatedStateAndDerivatives(double theta, double oneMinusThetaH) {
/* 86 */     if (this.previousState != null && theta <= 0.5D) {
/* 87 */       for (int i = 0; i < this.interpolatedState.length; i++)
/* 88 */         this.interpolatedState[i] = this.previousState[i] + theta * this.h * this.yDotK[0][i]; 
/* 90 */       System.arraycopy(this.yDotK[0], 0, this.interpolatedDerivatives, 0, this.interpolatedDerivatives.length);
/*    */     } else {
/* 92 */       for (int i = 0; i < this.interpolatedState.length; i++)
/* 93 */         this.interpolatedState[i] = this.currentState[i] - oneMinusThetaH * this.yDotK[0][i]; 
/* 95 */       System.arraycopy(this.yDotK[0], 0, this.interpolatedDerivatives, 0, this.interpolatedDerivatives.length);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\ode\nonstiff\EulerStepInterpolator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
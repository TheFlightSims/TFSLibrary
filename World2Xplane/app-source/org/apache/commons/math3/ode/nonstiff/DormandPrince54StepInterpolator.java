/*     */ package org.apache.commons.math3.ode.nonstiff;
/*     */ 
/*     */ import org.apache.commons.math3.ode.AbstractIntegrator;
/*     */ import org.apache.commons.math3.ode.EquationsMapper;
/*     */ import org.apache.commons.math3.ode.sampling.StepInterpolator;
/*     */ 
/*     */ class DormandPrince54StepInterpolator extends RungeKuttaStepInterpolator {
/*     */   private static final double A70 = 0.09114583333333333D;
/*     */   
/*     */   private static final double A72 = 0.44923629829290207D;
/*     */   
/*     */   private static final double A73 = 0.6510416666666666D;
/*     */   
/*     */   private static final double A74 = -0.322376179245283D;
/*     */   
/*     */   private static final double A75 = 0.13095238095238096D;
/*     */   
/*     */   private static final double D0 = -1.1270175653862835D;
/*     */   
/*     */   private static final double D2 = 2.675424484351598D;
/*     */   
/*     */   private static final double D3 = -5.685526961588504D;
/*     */   
/*     */   private static final double D4 = 3.5219323679207912D;
/*     */   
/*     */   private static final double D5 = -1.7672812570757455D;
/*     */   
/*     */   private static final double D6 = 2.382468931778144D;
/*     */   
/*     */   private static final long serialVersionUID = 20111120L;
/*     */   
/*     */   private double[] v1;
/*     */   
/*     */   private double[] v2;
/*     */   
/*     */   private double[] v3;
/*     */   
/*     */   private double[] v4;
/*     */   
/*     */   private boolean vectorsInitialized;
/*     */   
/*     */   public DormandPrince54StepInterpolator() {
/* 103 */     this.v1 = null;
/* 104 */     this.v2 = null;
/* 105 */     this.v3 = null;
/* 106 */     this.v4 = null;
/* 107 */     this.vectorsInitialized = false;
/*     */   }
/*     */   
/*     */   public DormandPrince54StepInterpolator(DormandPrince54StepInterpolator interpolator) {
/* 117 */     super(interpolator);
/* 119 */     if (interpolator.v1 == null) {
/* 121 */       this.v1 = null;
/* 122 */       this.v2 = null;
/* 123 */       this.v3 = null;
/* 124 */       this.v4 = null;
/* 125 */       this.vectorsInitialized = false;
/*     */     } else {
/* 129 */       this.v1 = (double[])interpolator.v1.clone();
/* 130 */       this.v2 = (double[])interpolator.v2.clone();
/* 131 */       this.v3 = (double[])interpolator.v3.clone();
/* 132 */       this.v4 = (double[])interpolator.v4.clone();
/* 133 */       this.vectorsInitialized = interpolator.vectorsInitialized;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected StepInterpolator doCopy() {
/* 142 */     return (StepInterpolator)new DormandPrince54StepInterpolator(this);
/*     */   }
/*     */   
/*     */   public void reinitialize(AbstractIntegrator integrator, double[] y, double[][] yDotK, boolean forward, EquationsMapper primaryMapper, EquationsMapper[] secondaryMappers) {
/* 152 */     super.reinitialize(integrator, y, yDotK, forward, primaryMapper, secondaryMappers);
/* 153 */     this.v1 = null;
/* 154 */     this.v2 = null;
/* 155 */     this.v3 = null;
/* 156 */     this.v4 = null;
/* 157 */     this.vectorsInitialized = false;
/*     */   }
/*     */   
/*     */   public void storeTime(double t) {
/* 163 */     super.storeTime(t);
/* 164 */     this.vectorsInitialized = false;
/*     */   }
/*     */   
/*     */   protected void computeInterpolatedStateAndDerivatives(double theta, double oneMinusThetaH) {
/* 172 */     if (!this.vectorsInitialized) {
/* 174 */       if (this.v1 == null) {
/* 175 */         this.v1 = new double[this.interpolatedState.length];
/* 176 */         this.v2 = new double[this.interpolatedState.length];
/* 177 */         this.v3 = new double[this.interpolatedState.length];
/* 178 */         this.v4 = new double[this.interpolatedState.length];
/*     */       } 
/* 184 */       for (int i = 0; i < this.interpolatedState.length; i++) {
/* 185 */         double yDot0 = this.yDotK[0][i];
/* 186 */         double yDot2 = this.yDotK[2][i];
/* 187 */         double yDot3 = this.yDotK[3][i];
/* 188 */         double yDot4 = this.yDotK[4][i];
/* 189 */         double yDot5 = this.yDotK[5][i];
/* 190 */         double yDot6 = this.yDotK[6][i];
/* 191 */         this.v1[i] = 0.09114583333333333D * yDot0 + 0.44923629829290207D * yDot2 + 0.6510416666666666D * yDot3 + -0.322376179245283D * yDot4 + 0.13095238095238096D * yDot5;
/* 192 */         this.v2[i] = yDot0 - this.v1[i];
/* 193 */         this.v3[i] = this.v1[i] - this.v2[i] - yDot6;
/* 194 */         this.v4[i] = -1.1270175653862835D * yDot0 + 2.675424484351598D * yDot2 + -5.685526961588504D * yDot3 + 3.5219323679207912D * yDot4 + -1.7672812570757455D * yDot5 + 2.382468931778144D * yDot6;
/*     */       } 
/* 197 */       this.vectorsInitialized = true;
/*     */     } 
/* 202 */     double eta = 1.0D - theta;
/* 203 */     double twoTheta = 2.0D * theta;
/* 204 */     double dot2 = 1.0D - twoTheta;
/* 205 */     double dot3 = theta * (2.0D - 3.0D * theta);
/* 206 */     double dot4 = twoTheta * (1.0D + theta * (twoTheta - 3.0D));
/* 207 */     if (this.previousState != null && theta <= 0.5D) {
/* 208 */       for (int i = 0; i < this.interpolatedState.length; i++) {
/* 209 */         this.interpolatedState[i] = this.previousState[i] + theta * this.h * (this.v1[i] + eta * (this.v2[i] + theta * (this.v3[i] + eta * this.v4[i])));
/* 211 */         this.interpolatedDerivatives[i] = this.v1[i] + dot2 * this.v2[i] + dot3 * this.v3[i] + dot4 * this.v4[i];
/*     */       } 
/*     */     } else {
/* 214 */       for (int i = 0; i < this.interpolatedState.length; i++) {
/* 215 */         this.interpolatedState[i] = this.currentState[i] - oneMinusThetaH * (this.v1[i] - theta * (this.v2[i] + theta * (this.v3[i] + eta * this.v4[i])));
/* 217 */         this.interpolatedDerivatives[i] = this.v1[i] + dot2 * this.v2[i] + dot3 * this.v3[i] + dot4 * this.v4[i];
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\ode\nonstiff\DormandPrince54StepInterpolator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
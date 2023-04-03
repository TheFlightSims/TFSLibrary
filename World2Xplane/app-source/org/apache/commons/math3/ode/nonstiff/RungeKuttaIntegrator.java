/*     */ package org.apache.commons.math3.ode.nonstiff;
/*     */ 
/*     */ import org.apache.commons.math3.exception.MathIllegalArgumentException;
/*     */ import org.apache.commons.math3.exception.MathIllegalStateException;
/*     */ import org.apache.commons.math3.ode.AbstractIntegrator;
/*     */ import org.apache.commons.math3.ode.ExpandableStatefulODE;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public abstract class RungeKuttaIntegrator extends AbstractIntegrator {
/*     */   private final double[] c;
/*     */   
/*     */   private final double[][] a;
/*     */   
/*     */   private final double[] b;
/*     */   
/*     */   private final RungeKuttaStepInterpolator prototype;
/*     */   
/*     */   private final double step;
/*     */   
/*     */   protected RungeKuttaIntegrator(String name, double[] c, double[][] a, double[] b, RungeKuttaStepInterpolator prototype, double step) {
/*  83 */     super(name);
/*  84 */     this.c = c;
/*  85 */     this.a = a;
/*  86 */     this.b = b;
/*  87 */     this.prototype = prototype;
/*  88 */     this.step = FastMath.abs(step);
/*     */   }
/*     */   
/*     */   public void integrate(ExpandableStatefulODE equations, double t) throws MathIllegalStateException, MathIllegalArgumentException {
/*  96 */     sanityChecks(equations, t);
/*  97 */     setEquations(equations);
/*  98 */     boolean forward = (t > equations.getTime());
/* 101 */     double[] y0 = equations.getCompleteState();
/* 102 */     double[] y = (double[])y0.clone();
/* 103 */     int stages = this.c.length + 1;
/* 104 */     double[][] yDotK = new double[stages][];
/* 105 */     for (int i = 0; i < stages; i++)
/* 106 */       yDotK[i] = new double[y0.length]; 
/* 108 */     double[] yTmp = (double[])y0.clone();
/* 109 */     double[] yDotTmp = new double[y0.length];
/* 112 */     RungeKuttaStepInterpolator interpolator = (RungeKuttaStepInterpolator)this.prototype.copy();
/* 113 */     interpolator.reinitialize(this, yTmp, yDotK, forward, equations.getPrimaryMapper(), equations.getSecondaryMappers());
/* 115 */     interpolator.storeTime(equations.getTime());
/* 118 */     this.stepStart = equations.getTime();
/* 119 */     this.stepSize = forward ? this.step : -this.step;
/* 120 */     initIntegration(equations.getTime(), y0, t);
/* 123 */     this.isLastStep = false;
/*     */     do {
/* 126 */       interpolator.shift();
/* 129 */       computeDerivatives(this.stepStart, y, yDotK[0]);
/* 132 */       for (int k = 1; k < stages; k++) {
/* 134 */         for (int m = 0; m < y0.length; m++) {
/* 135 */           double sum = this.a[k - 1][0] * yDotK[0][m];
/* 136 */           for (int l = 1; l < k; l++)
/* 137 */             sum += this.a[k - 1][l] * yDotK[l][m]; 
/* 139 */           yTmp[m] = y[m] + this.stepSize * sum;
/*     */         } 
/* 142 */         computeDerivatives(this.stepStart + this.c[k - 1] * this.stepSize, yTmp, yDotK[k]);
/*     */       } 
/* 147 */       for (int j = 0; j < y0.length; j++) {
/* 148 */         double sum = this.b[0] * yDotK[0][j];
/* 149 */         for (int l = 1; l < stages; l++)
/* 150 */           sum += this.b[l] * yDotK[l][j]; 
/* 152 */         yTmp[j] = y[j] + this.stepSize * sum;
/*     */       } 
/* 156 */       interpolator.storeTime(this.stepStart + this.stepSize);
/* 157 */       System.arraycopy(yTmp, 0, y, 0, y0.length);
/* 158 */       System.arraycopy(yDotK[stages - 1], 0, yDotTmp, 0, y0.length);
/* 159 */       this.stepStart = acceptStep(interpolator, y, yDotTmp, t);
/* 161 */       if (this.isLastStep)
/*     */         continue; 
/* 164 */       interpolator.storeTime(this.stepStart);
/* 167 */       double nextT = this.stepStart + this.stepSize;
/* 168 */       boolean nextIsLast = forward ? ((nextT >= t)) : ((nextT <= t));
/* 169 */       if (!nextIsLast)
/*     */         continue; 
/* 170 */       this.stepSize = t - this.stepStart;
/* 174 */     } while (!this.isLastStep);
/* 177 */     equations.setTime(this.stepStart);
/* 178 */     equations.setCompleteState(y);
/* 180 */     this.stepStart = Double.NaN;
/* 181 */     this.stepSize = Double.NaN;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\ode\nonstiff\RungeKuttaIntegrator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package org.apache.commons.math3.ode.nonstiff;
/*     */ 
/*     */ import org.apache.commons.math3.exception.MathIllegalArgumentException;
/*     */ import org.apache.commons.math3.exception.MathIllegalStateException;
/*     */ import org.apache.commons.math3.ode.ExpandableStatefulODE;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public abstract class EmbeddedRungeKuttaIntegrator extends AdaptiveStepsizeIntegrator {
/*     */   private final boolean fsal;
/*     */   
/*     */   private final double[] c;
/*     */   
/*     */   private final double[][] a;
/*     */   
/*     */   private final double[] b;
/*     */   
/*     */   private final RungeKuttaStepInterpolator prototype;
/*     */   
/*     */   private final double exp;
/*     */   
/*     */   private double safety;
/*     */   
/*     */   private double minReduction;
/*     */   
/*     */   private double maxGrowth;
/*     */   
/*     */   protected EmbeddedRungeKuttaIntegrator(String name, boolean fsal, double[] c, double[][] a, double[] b, RungeKuttaStepInterpolator prototype, double minStep, double maxStep, double scalAbsoluteTolerance, double scalRelativeTolerance) {
/* 115 */     super(name, minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
/* 117 */     this.fsal = fsal;
/* 118 */     this.c = c;
/* 119 */     this.a = a;
/* 120 */     this.b = b;
/* 121 */     this.prototype = prototype;
/* 123 */     this.exp = -1.0D / getOrder();
/* 126 */     setSafety(0.9D);
/* 127 */     setMinReduction(0.2D);
/* 128 */     setMaxGrowth(10.0D);
/*     */   }
/*     */   
/*     */   protected EmbeddedRungeKuttaIntegrator(String name, boolean fsal, double[] c, double[][] a, double[] b, RungeKuttaStepInterpolator prototype, double minStep, double maxStep, double[] vecAbsoluteTolerance, double[] vecRelativeTolerance) {
/* 153 */     super(name, minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
/* 155 */     this.fsal = fsal;
/* 156 */     this.c = c;
/* 157 */     this.a = a;
/* 158 */     this.b = b;
/* 159 */     this.prototype = prototype;
/* 161 */     this.exp = -1.0D / getOrder();
/* 164 */     setSafety(0.9D);
/* 165 */     setMinReduction(0.2D);
/* 166 */     setMaxGrowth(10.0D);
/*     */   }
/*     */   
/*     */   public abstract int getOrder();
/*     */   
/*     */   public double getSafety() {
/* 179 */     return this.safety;
/*     */   }
/*     */   
/*     */   public void setSafety(double safety) {
/* 186 */     this.safety = safety;
/*     */   }
/*     */   
/*     */   public void integrate(ExpandableStatefulODE equations, double t) throws MathIllegalStateException, MathIllegalArgumentException {
/* 194 */     sanityChecks(equations, t);
/* 195 */     setEquations(equations);
/* 196 */     boolean forward = (t > equations.getTime());
/* 199 */     double[] y0 = equations.getCompleteState();
/* 200 */     double[] y = (double[])y0.clone();
/* 201 */     int stages = this.c.length + 1;
/* 202 */     double[][] yDotK = new double[stages][y.length];
/* 203 */     double[] yTmp = (double[])y0.clone();
/* 204 */     double[] yDotTmp = new double[y.length];
/* 207 */     RungeKuttaStepInterpolator interpolator = (RungeKuttaStepInterpolator)this.prototype.copy();
/* 208 */     interpolator.reinitialize(this, yTmp, yDotK, forward, equations.getPrimaryMapper(), equations.getSecondaryMappers());
/* 210 */     interpolator.storeTime(equations.getTime());
/* 213 */     this.stepStart = equations.getTime();
/* 214 */     double hNew = 0.0D;
/* 215 */     boolean firstTime = true;
/* 216 */     initIntegration(equations.getTime(), y0, t);
/* 219 */     this.isLastStep = false;
/*     */     do {
/* 222 */       interpolator.shift();
/* 225 */       double error = 10.0D;
/* 226 */       while (error >= 1.0D) {
/* 228 */         if (firstTime || !this.fsal)
/* 230 */           computeDerivatives(this.stepStart, y, yDotK[0]); 
/* 233 */         if (firstTime) {
/* 234 */           double[] scale = new double[this.mainSetDimension];
/* 235 */           if (this.vecAbsoluteTolerance == null) {
/* 236 */             for (int i = 0; i < scale.length; i++)
/* 237 */               scale[i] = this.scalAbsoluteTolerance + this.scalRelativeTolerance * FastMath.abs(y[i]); 
/*     */           } else {
/* 240 */             for (int i = 0; i < scale.length; i++)
/* 241 */               scale[i] = this.vecAbsoluteTolerance[i] + this.vecRelativeTolerance[i] * FastMath.abs(y[i]); 
/*     */           } 
/* 244 */           hNew = initializeStep(forward, getOrder(), scale, this.stepStart, y, yDotK[0], yTmp, yDotK[1]);
/* 246 */           firstTime = false;
/*     */         } 
/* 249 */         this.stepSize = hNew;
/* 250 */         if (forward) {
/* 251 */           if (this.stepStart + this.stepSize >= t)
/* 252 */             this.stepSize = t - this.stepStart; 
/* 255 */         } else if (this.stepStart + this.stepSize <= t) {
/* 256 */           this.stepSize = t - this.stepStart;
/*     */         } 
/* 261 */         for (int k = 1; k < stages; k++) {
/* 263 */           for (int i = 0; i < y0.length; i++) {
/* 264 */             double sum = this.a[k - 1][0] * yDotK[0][i];
/* 265 */             for (int l = 1; l < k; l++)
/* 266 */               sum += this.a[k - 1][l] * yDotK[l][i]; 
/* 268 */             yTmp[i] = y[i] + this.stepSize * sum;
/*     */           } 
/* 271 */           computeDerivatives(this.stepStart + this.c[k - 1] * this.stepSize, yTmp, yDotK[k]);
/*     */         } 
/* 276 */         for (int j = 0; j < y0.length; j++) {
/* 277 */           double sum = this.b[0] * yDotK[0][j];
/* 278 */           for (int l = 1; l < stages; l++)
/* 279 */             sum += this.b[l] * yDotK[l][j]; 
/* 281 */           yTmp[j] = y[j] + this.stepSize * sum;
/*     */         } 
/* 285 */         error = estimateError(yDotK, y, yTmp, this.stepSize);
/* 286 */         if (error >= 1.0D) {
/* 288 */           double d = FastMath.min(this.maxGrowth, FastMath.max(this.minReduction, this.safety * FastMath.pow(error, this.exp)));
/* 291 */           hNew = filterStep(this.stepSize * d, forward, false);
/*     */         } 
/*     */       } 
/* 297 */       interpolator.storeTime(this.stepStart + this.stepSize);
/* 298 */       System.arraycopy(yTmp, 0, y, 0, y0.length);
/* 299 */       System.arraycopy(yDotK[stages - 1], 0, yDotTmp, 0, y0.length);
/* 300 */       this.stepStart = acceptStep(interpolator, y, yDotTmp, t);
/* 301 */       System.arraycopy(y, 0, yTmp, 0, y.length);
/* 303 */       if (this.isLastStep)
/*     */         continue; 
/* 306 */       interpolator.storeTime(this.stepStart);
/* 308 */       if (this.fsal)
/* 310 */         System.arraycopy(yDotTmp, 0, yDotK[0], 0, y0.length); 
/* 314 */       double factor = FastMath.min(this.maxGrowth, FastMath.max(this.minReduction, this.safety * FastMath.pow(error, this.exp)));
/* 316 */       double scaledH = this.stepSize * factor;
/* 317 */       double nextT = this.stepStart + scaledH;
/* 318 */       boolean nextIsLast = forward ? ((nextT >= t)) : ((nextT <= t));
/* 319 */       hNew = filterStep(scaledH, forward, nextIsLast);
/* 321 */       double filteredNextT = this.stepStart + hNew;
/* 322 */       boolean filteredNextIsLast = forward ? ((filteredNextT >= t)) : ((filteredNextT <= t));
/* 323 */       if (!filteredNextIsLast)
/*     */         continue; 
/* 324 */       hNew = t - this.stepStart;
/* 329 */     } while (!this.isLastStep);
/* 332 */     equations.setTime(this.stepStart);
/* 333 */     equations.setCompleteState(y);
/* 335 */     resetInternalState();
/*     */   }
/*     */   
/*     */   public double getMinReduction() {
/* 343 */     return this.minReduction;
/*     */   }
/*     */   
/*     */   public void setMinReduction(double minReduction) {
/* 350 */     this.minReduction = minReduction;
/*     */   }
/*     */   
/*     */   public double getMaxGrowth() {
/* 357 */     return this.maxGrowth;
/*     */   }
/*     */   
/*     */   public void setMaxGrowth(double maxGrowth) {
/* 364 */     this.maxGrowth = maxGrowth;
/*     */   }
/*     */   
/*     */   protected abstract double estimateError(double[][] paramArrayOfdouble, double[] paramArrayOfdouble1, double[] paramArrayOfdouble2, double paramDouble);
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\ode\nonstiff\EmbeddedRungeKuttaIntegrator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
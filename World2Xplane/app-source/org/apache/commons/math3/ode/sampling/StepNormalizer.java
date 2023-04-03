/*     */ package org.apache.commons.math3.ode.sampling;
/*     */ 
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ import org.apache.commons.math3.util.Precision;
/*     */ 
/*     */ public class StepNormalizer implements StepHandler {
/*     */   private double h;
/*     */   
/*     */   private final FixedStepHandler handler;
/*     */   
/*     */   private double firstTime;
/*     */   
/*     */   private double lastTime;
/*     */   
/*     */   private double[] lastState;
/*     */   
/*     */   private double[] lastDerivatives;
/*     */   
/*     */   private boolean forward;
/*     */   
/*     */   private final StepNormalizerBounds bounds;
/*     */   
/*     */   private final StepNormalizerMode mode;
/*     */   
/*     */   public StepNormalizer(double h, FixedStepHandler handler) {
/* 126 */     this(h, handler, StepNormalizerMode.INCREMENT, StepNormalizerBounds.FIRST);
/*     */   }
/*     */   
/*     */   public StepNormalizer(double h, FixedStepHandler handler, StepNormalizerMode mode) {
/* 139 */     this(h, handler, mode, StepNormalizerBounds.FIRST);
/*     */   }
/*     */   
/*     */   public StepNormalizer(double h, FixedStepHandler handler, StepNormalizerBounds bounds) {
/* 151 */     this(h, handler, StepNormalizerMode.INCREMENT, bounds);
/*     */   }
/*     */   
/*     */   public StepNormalizer(double h, FixedStepHandler handler, StepNormalizerMode mode, StepNormalizerBounds bounds) {
/* 164 */     this.h = FastMath.abs(h);
/* 165 */     this.handler = handler;
/* 166 */     this.mode = mode;
/* 167 */     this.bounds = bounds;
/* 168 */     this.firstTime = Double.NaN;
/* 169 */     this.lastTime = Double.NaN;
/* 170 */     this.lastState = null;
/* 171 */     this.lastDerivatives = null;
/* 172 */     this.forward = true;
/*     */   }
/*     */   
/*     */   public void init(double t0, double[] y0, double t) {
/* 178 */     this.firstTime = Double.NaN;
/* 179 */     this.lastTime = Double.NaN;
/* 180 */     this.lastState = null;
/* 181 */     this.lastDerivatives = null;
/* 182 */     this.forward = true;
/* 185 */     this.handler.init(t0, y0, t);
/*     */   }
/*     */   
/*     */   public void handleStep(StepInterpolator interpolator, boolean isLast) {
/* 202 */     if (this.lastState == null) {
/* 203 */       this.firstTime = interpolator.getPreviousTime();
/* 204 */       this.lastTime = interpolator.getPreviousTime();
/* 205 */       interpolator.setInterpolatedTime(this.lastTime);
/* 206 */       this.lastState = (double[])interpolator.getInterpolatedState().clone();
/* 207 */       this.lastDerivatives = (double[])interpolator.getInterpolatedDerivatives().clone();
/* 210 */       this.forward = (interpolator.getCurrentTime() >= this.lastTime);
/* 211 */       if (!this.forward)
/* 212 */         this.h = -this.h; 
/*     */     } 
/* 217 */     double nextTime = (this.mode == StepNormalizerMode.INCREMENT) ? (this.lastTime + this.h) : ((FastMath.floor(this.lastTime / this.h) + 1.0D) * this.h);
/* 220 */     if (this.mode == StepNormalizerMode.MULTIPLES && Precision.equals(nextTime, this.lastTime, 1))
/* 222 */       nextTime += this.h; 
/* 226 */     boolean nextInStep = isNextInStep(nextTime, interpolator);
/* 227 */     while (nextInStep) {
/* 229 */       doNormalizedStep(false);
/* 232 */       storeStep(interpolator, nextTime);
/* 235 */       nextTime += this.h;
/* 236 */       nextInStep = isNextInStep(nextTime, interpolator);
/*     */     } 
/* 239 */     if (isLast) {
/* 243 */       boolean addLast = (this.bounds.lastIncluded() && this.lastTime != interpolator.getCurrentTime());
/* 245 */       doNormalizedStep(!addLast);
/* 246 */       if (addLast) {
/* 247 */         storeStep(interpolator, interpolator.getCurrentTime());
/* 248 */         doNormalizedStep(true);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean isNextInStep(double nextTime, StepInterpolator interpolator) {
/* 264 */     return this.forward ? ((nextTime <= interpolator.getCurrentTime())) : ((nextTime >= interpolator.getCurrentTime()));
/*     */   }
/*     */   
/*     */   private void doNormalizedStep(boolean isLast) {
/* 274 */     if (!this.bounds.firstIncluded() && this.firstTime == this.lastTime)
/*     */       return; 
/* 277 */     this.handler.handleStep(this.lastTime, this.lastState, this.lastDerivatives, isLast);
/*     */   }
/*     */   
/*     */   private void storeStep(StepInterpolator interpolator, double t) {
/* 287 */     this.lastTime = t;
/* 288 */     interpolator.setInterpolatedTime(this.lastTime);
/* 289 */     System.arraycopy(interpolator.getInterpolatedState(), 0, this.lastState, 0, this.lastState.length);
/* 291 */     System.arraycopy(interpolator.getInterpolatedDerivatives(), 0, this.lastDerivatives, 0, this.lastDerivatives.length);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\ode\sampling\StepNormalizer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
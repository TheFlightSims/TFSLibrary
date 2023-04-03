/*     */ package org.apache.commons.math3.ode.nonstiff;
/*     */ 
/*     */ import org.apache.commons.math3.exception.MathIllegalArgumentException;
/*     */ import org.apache.commons.math3.exception.MathIllegalStateException;
/*     */ import org.apache.commons.math3.linear.Array2DRowRealMatrix;
/*     */ import org.apache.commons.math3.ode.ExpandableStatefulODE;
/*     */ import org.apache.commons.math3.ode.sampling.AbstractStepInterpolator;
/*     */ import org.apache.commons.math3.ode.sampling.NordsieckStepInterpolator;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class AdamsBashforthIntegrator extends AdamsIntegrator {
/*     */   private static final String METHOD_NAME = "Adams-Bashforth";
/*     */   
/*     */   public AdamsBashforthIntegrator(int nSteps, double minStep, double maxStep, double scalAbsoluteTolerance, double scalRelativeTolerance) throws IllegalArgumentException {
/* 163 */     super("Adams-Bashforth", nSteps, nSteps, minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
/*     */   }
/*     */   
/*     */   public AdamsBashforthIntegrator(int nSteps, double minStep, double maxStep, double[] vecAbsoluteTolerance, double[] vecRelativeTolerance) throws IllegalArgumentException {
/* 185 */     super("Adams-Bashforth", nSteps, nSteps, minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
/*     */   }
/*     */   
/*     */   public void integrate(ExpandableStatefulODE equations, double t) throws MathIllegalStateException, MathIllegalArgumentException {
/* 194 */     sanityChecks(equations, t);
/* 195 */     setEquations(equations);
/* 196 */     boolean forward = (t > equations.getTime());
/* 199 */     double[] y0 = equations.getCompleteState();
/* 200 */     double[] y = (double[])y0.clone();
/* 201 */     double[] yDot = new double[y.length];
/* 204 */     NordsieckStepInterpolator interpolator = new NordsieckStepInterpolator();
/* 205 */     interpolator.reinitialize(y, forward, equations.getPrimaryMapper(), equations.getSecondaryMappers());
/* 209 */     initIntegration(equations.getTime(), y0, t);
/* 212 */     start(equations.getTime(), y, t);
/* 213 */     interpolator.reinitialize(this.stepStart, this.stepSize, this.scaled, this.nordsieck);
/* 214 */     interpolator.storeTime(this.stepStart);
/* 215 */     int lastRow = this.nordsieck.getRowDimension() - 1;
/* 218 */     double hNew = this.stepSize;
/* 219 */     interpolator.rescale(hNew);
/* 222 */     this.isLastStep = false;
/*     */     do {
/* 225 */       double error = 10.0D;
/* 226 */       while (error >= 1.0D) {
/* 228 */         this.stepSize = hNew;
/* 231 */         error = 0.0D;
/* 232 */         for (int i = 0; i < this.mainSetDimension; i++) {
/* 233 */           double yScale = FastMath.abs(y[i]);
/* 234 */           double tol = (this.vecAbsoluteTolerance == null) ? (this.scalAbsoluteTolerance + this.scalRelativeTolerance * yScale) : (this.vecAbsoluteTolerance[i] + this.vecRelativeTolerance[i] * yScale);
/* 237 */           double ratio = this.nordsieck.getEntry(lastRow, i) / tol;
/* 238 */           error += ratio * ratio;
/*     */         } 
/* 240 */         error = FastMath.sqrt(error / this.mainSetDimension);
/* 242 */         if (error >= 1.0D) {
/* 244 */           double d = computeStepGrowShrinkFactor(error);
/* 245 */           hNew = filterStep(this.stepSize * d, forward, false);
/* 246 */           interpolator.rescale(hNew);
/*     */         } 
/*     */       } 
/* 252 */       double stepEnd = this.stepStart + this.stepSize;
/* 253 */       interpolator.shift();
/* 254 */       interpolator.setInterpolatedTime(stepEnd);
/* 255 */       System.arraycopy(interpolator.getInterpolatedState(), 0, y, 0, y0.length);
/* 258 */       computeDerivatives(stepEnd, y, yDot);
/* 261 */       double[] predictedScaled = new double[y0.length];
/* 262 */       for (int j = 0; j < y0.length; j++)
/* 263 */         predictedScaled[j] = this.stepSize * yDot[j]; 
/* 265 */       Array2DRowRealMatrix nordsieckTmp = updateHighOrderDerivativesPhase1(this.nordsieck);
/* 266 */       updateHighOrderDerivativesPhase2(this.scaled, predictedScaled, nordsieckTmp);
/* 267 */       interpolator.reinitialize(stepEnd, this.stepSize, predictedScaled, nordsieckTmp);
/* 270 */       interpolator.storeTime(stepEnd);
/* 271 */       this.stepStart = acceptStep((AbstractStepInterpolator)interpolator, y, yDot, t);
/* 272 */       this.scaled = predictedScaled;
/* 273 */       this.nordsieck = nordsieckTmp;
/* 274 */       interpolator.reinitialize(stepEnd, this.stepSize, this.scaled, this.nordsieck);
/* 276 */       if (this.isLastStep)
/*     */         continue; 
/* 279 */       interpolator.storeTime(this.stepStart);
/* 281 */       if (this.resetOccurred) {
/* 284 */         start(this.stepStart, y, t);
/* 285 */         interpolator.reinitialize(this.stepStart, this.stepSize, this.scaled, this.nordsieck);
/*     */       } 
/* 289 */       double factor = computeStepGrowShrinkFactor(error);
/* 290 */       double scaledH = this.stepSize * factor;
/* 291 */       double nextT = this.stepStart + scaledH;
/* 292 */       boolean nextIsLast = forward ? ((nextT >= t)) : ((nextT <= t));
/* 293 */       hNew = filterStep(scaledH, forward, nextIsLast);
/* 295 */       double filteredNextT = this.stepStart + hNew;
/* 296 */       boolean filteredNextIsLast = forward ? ((filteredNextT >= t)) : ((filteredNextT <= t));
/* 297 */       if (filteredNextIsLast)
/* 298 */         hNew = t - this.stepStart; 
/* 301 */       interpolator.rescale(hNew);
/* 305 */     } while (!this.isLastStep);
/* 308 */     equations.setTime(this.stepStart);
/* 309 */     equations.setCompleteState(y);
/* 311 */     resetInternalState();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\ode\nonstiff\AdamsBashforthIntegrator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
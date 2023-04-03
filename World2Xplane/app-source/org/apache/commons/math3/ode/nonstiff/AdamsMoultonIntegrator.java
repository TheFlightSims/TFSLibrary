/*     */ package org.apache.commons.math3.ode.nonstiff;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import org.apache.commons.math3.exception.MathIllegalArgumentException;
/*     */ import org.apache.commons.math3.exception.MathIllegalStateException;
/*     */ import org.apache.commons.math3.linear.Array2DRowRealMatrix;
/*     */ import org.apache.commons.math3.linear.RealMatrixPreservingVisitor;
/*     */ import org.apache.commons.math3.ode.ExpandableStatefulODE;
/*     */ import org.apache.commons.math3.ode.sampling.AbstractStepInterpolator;
/*     */ import org.apache.commons.math3.ode.sampling.NordsieckStepInterpolator;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class AdamsMoultonIntegrator extends AdamsIntegrator {
/*     */   private static final String METHOD_NAME = "Adams-Moulton";
/*     */   
/*     */   public AdamsMoultonIntegrator(int nSteps, double minStep, double maxStep, double scalAbsoluteTolerance, double scalRelativeTolerance) throws IllegalArgumentException {
/* 179 */     super("Adams-Moulton", nSteps, nSteps + 1, minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
/*     */   }
/*     */   
/*     */   public AdamsMoultonIntegrator(int nSteps, double minStep, double maxStep, double[] vecAbsoluteTolerance, double[] vecRelativeTolerance) throws IllegalArgumentException {
/* 201 */     super("Adams-Moulton", nSteps, nSteps + 1, minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
/*     */   }
/*     */   
/*     */   public void integrate(ExpandableStatefulODE equations, double t) throws MathIllegalStateException, MathIllegalArgumentException {
/* 211 */     sanityChecks(equations, t);
/* 212 */     setEquations(equations);
/* 213 */     boolean forward = (t > equations.getTime());
/* 216 */     double[] y0 = equations.getCompleteState();
/* 217 */     double[] y = (double[])y0.clone();
/* 218 */     double[] yDot = new double[y.length];
/* 219 */     double[] yTmp = new double[y.length];
/* 220 */     double[] predictedScaled = new double[y.length];
/* 221 */     Array2DRowRealMatrix nordsieckTmp = null;
/* 224 */     NordsieckStepInterpolator interpolator = new NordsieckStepInterpolator();
/* 225 */     interpolator.reinitialize(y, forward, equations.getPrimaryMapper(), equations.getSecondaryMappers());
/* 229 */     initIntegration(equations.getTime(), y0, t);
/* 232 */     start(equations.getTime(), y, t);
/* 233 */     interpolator.reinitialize(this.stepStart, this.stepSize, this.scaled, this.nordsieck);
/* 234 */     interpolator.storeTime(this.stepStart);
/* 236 */     double hNew = this.stepSize;
/* 237 */     interpolator.rescale(hNew);
/* 239 */     this.isLastStep = false;
/*     */     do {
/* 242 */       double error = 10.0D;
/* 243 */       while (error >= 1.0D) {
/* 245 */         this.stepSize = hNew;
/* 248 */         double d = this.stepStart + this.stepSize;
/* 249 */         interpolator.setInterpolatedTime(d);
/* 250 */         System.arraycopy(interpolator.getInterpolatedState(), 0, yTmp, 0, y0.length);
/* 253 */         computeDerivatives(d, yTmp, yDot);
/* 256 */         for (int i = 0; i < y0.length; i++)
/* 257 */           predictedScaled[i] = this.stepSize * yDot[i]; 
/* 259 */         nordsieckTmp = updateHighOrderDerivativesPhase1(this.nordsieck);
/* 260 */         updateHighOrderDerivativesPhase2(this.scaled, predictedScaled, nordsieckTmp);
/* 263 */         error = nordsieckTmp.walkInOptimizedOrder(new Corrector(y, predictedScaled, yTmp));
/* 265 */         if (error >= 1.0D) {
/* 267 */           double d1 = computeStepGrowShrinkFactor(error);
/* 268 */           hNew = filterStep(this.stepSize * d1, forward, false);
/* 269 */           interpolator.rescale(hNew);
/*     */         } 
/*     */       } 
/* 274 */       double stepEnd = this.stepStart + this.stepSize;
/* 275 */       computeDerivatives(stepEnd, yTmp, yDot);
/* 278 */       double[] correctedScaled = new double[y0.length];
/* 279 */       for (int j = 0; j < y0.length; j++)
/* 280 */         correctedScaled[j] = this.stepSize * yDot[j]; 
/* 282 */       updateHighOrderDerivativesPhase2(predictedScaled, correctedScaled, nordsieckTmp);
/* 285 */       System.arraycopy(yTmp, 0, y, 0, y.length);
/* 286 */       interpolator.reinitialize(stepEnd, this.stepSize, correctedScaled, nordsieckTmp);
/* 287 */       interpolator.storeTime(this.stepStart);
/* 288 */       interpolator.shift();
/* 289 */       interpolator.storeTime(stepEnd);
/* 290 */       this.stepStart = acceptStep((AbstractStepInterpolator)interpolator, y, yDot, t);
/* 291 */       this.scaled = correctedScaled;
/* 292 */       this.nordsieck = nordsieckTmp;
/* 294 */       if (this.isLastStep)
/*     */         continue; 
/* 297 */       interpolator.storeTime(this.stepStart);
/* 299 */       if (this.resetOccurred) {
/* 302 */         start(this.stepStart, y, t);
/* 303 */         interpolator.reinitialize(this.stepStart, this.stepSize, this.scaled, this.nordsieck);
/*     */       } 
/* 308 */       double factor = computeStepGrowShrinkFactor(error);
/* 309 */       double scaledH = this.stepSize * factor;
/* 310 */       double nextT = this.stepStart + scaledH;
/* 311 */       boolean nextIsLast = forward ? ((nextT >= t)) : ((nextT <= t));
/* 312 */       hNew = filterStep(scaledH, forward, nextIsLast);
/* 314 */       double filteredNextT = this.stepStart + hNew;
/* 315 */       boolean filteredNextIsLast = forward ? ((filteredNextT >= t)) : ((filteredNextT <= t));
/* 316 */       if (filteredNextIsLast)
/* 317 */         hNew = t - this.stepStart; 
/* 320 */       interpolator.rescale(hNew);
/* 323 */     } while (!this.isLastStep);
/* 326 */     equations.setTime(this.stepStart);
/* 327 */     equations.setCompleteState(y);
/* 329 */     resetInternalState();
/*     */   }
/*     */   
/*     */   private class Corrector implements RealMatrixPreservingVisitor {
/*     */     private final double[] previous;
/*     */     
/*     */     private final double[] scaled;
/*     */     
/*     */     private final double[] before;
/*     */     
/*     */     private final double[] after;
/*     */     
/*     */     public Corrector(double[] previous, double[] scaled, double[] state) {
/* 361 */       this.previous = previous;
/* 362 */       this.scaled = scaled;
/* 363 */       this.after = state;
/* 364 */       this.before = (double[])state.clone();
/*     */     }
/*     */     
/*     */     public void start(int rows, int columns, int startRow, int endRow, int startColumn, int endColumn) {
/* 370 */       Arrays.fill(this.after, 0.0D);
/*     */     }
/*     */     
/*     */     public void visit(int row, int column, double value) {
/* 375 */       if ((row & 0x1) == 0) {
/* 376 */         this.after[column] = this.after[column] - value;
/*     */       } else {
/* 378 */         this.after[column] = this.after[column] + value;
/*     */       } 
/*     */     }
/*     */     
/*     */     public double end() {
/* 393 */       double error = 0.0D;
/* 394 */       for (int i = 0; i < this.after.length; i++) {
/* 395 */         this.after[i] = this.after[i] + this.previous[i] + this.scaled[i];
/* 396 */         if (i < AdamsMoultonIntegrator.this.mainSetDimension) {
/* 397 */           double yScale = FastMath.max(FastMath.abs(this.previous[i]), FastMath.abs(this.after[i]));
/* 398 */           double tol = (AdamsMoultonIntegrator.this.vecAbsoluteTolerance == null) ? (AdamsMoultonIntegrator.this.scalAbsoluteTolerance + AdamsMoultonIntegrator.this.scalRelativeTolerance * yScale) : (AdamsMoultonIntegrator.this.vecAbsoluteTolerance[i] + AdamsMoultonIntegrator.this.vecRelativeTolerance[i] * yScale);
/* 401 */           double ratio = (this.after[i] - this.before[i]) / tol;
/* 402 */           error += ratio * ratio;
/*     */         } 
/*     */       } 
/* 406 */       return FastMath.sqrt(error / AdamsMoultonIntegrator.this.mainSetDimension);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\ode\nonstiff\AdamsMoultonIntegrator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
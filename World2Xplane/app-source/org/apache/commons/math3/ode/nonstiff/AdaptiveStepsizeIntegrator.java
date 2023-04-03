/*     */ package org.apache.commons.math3.ode.nonstiff;
/*     */ 
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ import org.apache.commons.math3.exception.MathIllegalArgumentException;
/*     */ import org.apache.commons.math3.exception.MathIllegalStateException;
/*     */ import org.apache.commons.math3.exception.NumberIsTooSmallException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.ode.AbstractIntegrator;
/*     */ import org.apache.commons.math3.ode.ExpandableStatefulODE;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public abstract class AdaptiveStepsizeIntegrator extends AbstractIntegrator {
/*     */   protected double scalAbsoluteTolerance;
/*     */   
/*     */   protected double scalRelativeTolerance;
/*     */   
/*     */   protected double[] vecAbsoluteTolerance;
/*     */   
/*     */   protected double[] vecRelativeTolerance;
/*     */   
/*     */   protected int mainSetDimension;
/*     */   
/*     */   private double initialStep;
/*     */   
/*     */   private double minStep;
/*     */   
/*     */   private double maxStep;
/*     */   
/*     */   public AdaptiveStepsizeIntegrator(String name, double minStep, double maxStep, double scalAbsoluteTolerance, double scalRelativeTolerance) {
/* 110 */     super(name);
/* 111 */     setStepSizeControl(minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
/* 112 */     resetInternalState();
/*     */   }
/*     */   
/*     */   public AdaptiveStepsizeIntegrator(String name, double minStep, double maxStep, double[] vecAbsoluteTolerance, double[] vecRelativeTolerance) {
/* 133 */     super(name);
/* 134 */     setStepSizeControl(minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
/* 135 */     resetInternalState();
/*     */   }
/*     */   
/*     */   public void setStepSizeControl(double minimalStep, double maximalStep, double absoluteTolerance, double relativeTolerance) {
/* 157 */     this.minStep = FastMath.abs(minimalStep);
/* 158 */     this.maxStep = FastMath.abs(maximalStep);
/* 159 */     this.initialStep = -1.0D;
/* 161 */     this.scalAbsoluteTolerance = absoluteTolerance;
/* 162 */     this.scalRelativeTolerance = relativeTolerance;
/* 163 */     this.vecAbsoluteTolerance = null;
/* 164 */     this.vecRelativeTolerance = null;
/*     */   }
/*     */   
/*     */   public void setStepSizeControl(double minimalStep, double maximalStep, double[] absoluteTolerance, double[] relativeTolerance) {
/* 186 */     this.minStep = FastMath.abs(minimalStep);
/* 187 */     this.maxStep = FastMath.abs(maximalStep);
/* 188 */     this.initialStep = -1.0D;
/* 190 */     this.scalAbsoluteTolerance = 0.0D;
/* 191 */     this.scalRelativeTolerance = 0.0D;
/* 192 */     this.vecAbsoluteTolerance = (double[])absoluteTolerance.clone();
/* 193 */     this.vecRelativeTolerance = (double[])relativeTolerance.clone();
/*     */   }
/*     */   
/*     */   public void setInitialStepSize(double initialStepSize) {
/* 209 */     if (initialStepSize < this.minStep || initialStepSize > this.maxStep) {
/* 210 */       this.initialStep = -1.0D;
/*     */     } else {
/* 212 */       this.initialStep = initialStepSize;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void sanityChecks(ExpandableStatefulODE equations, double t) throws DimensionMismatchException, NumberIsTooSmallException {
/* 221 */     super.sanityChecks(equations, t);
/* 223 */     this.mainSetDimension = equations.getPrimaryMapper().getDimension();
/* 225 */     if (this.vecAbsoluteTolerance != null && this.vecAbsoluteTolerance.length != this.mainSetDimension)
/* 226 */       throw new DimensionMismatchException(this.mainSetDimension, this.vecAbsoluteTolerance.length); 
/* 229 */     if (this.vecRelativeTolerance != null && this.vecRelativeTolerance.length != this.mainSetDimension)
/* 230 */       throw new DimensionMismatchException(this.mainSetDimension, this.vecRelativeTolerance.length); 
/*     */   }
/*     */   
/*     */   public double initializeStep(boolean forward, int order, double[] scale, double t0, double[] y0, double[] yDot0, double[] y1, double[] yDot1) {
/* 250 */     if (this.initialStep > 0.0D)
/* 252 */       return forward ? this.initialStep : -this.initialStep; 
/* 258 */     double yOnScale2 = 0.0D;
/* 259 */     double yDotOnScale2 = 0.0D;
/* 260 */     for (int j = 0; j < scale.length; j++) {
/* 261 */       double ratio = y0[j] / scale[j];
/* 262 */       yOnScale2 += ratio * ratio;
/* 263 */       ratio = yDot0[j] / scale[j];
/* 264 */       yDotOnScale2 += ratio * ratio;
/*     */     } 
/* 267 */     double h = (yOnScale2 < 1.0E-10D || yDotOnScale2 < 1.0E-10D) ? 1.0E-6D : (0.01D * FastMath.sqrt(yOnScale2 / yDotOnScale2));
/* 269 */     if (!forward)
/* 270 */       h = -h; 
/* 274 */     for (int i = 0; i < y0.length; i++)
/* 275 */       y1[i] = y0[i] + h * yDot0[i]; 
/* 277 */     computeDerivatives(t0 + h, y1, yDot1);
/* 280 */     double yDDotOnScale = 0.0D;
/* 281 */     for (int k = 0; k < scale.length; k++) {
/* 282 */       double ratio = (yDot1[k] - yDot0[k]) / scale[k];
/* 283 */       yDDotOnScale += ratio * ratio;
/*     */     } 
/* 285 */     yDDotOnScale = FastMath.sqrt(yDDotOnScale) / h;
/* 289 */     double maxInv2 = FastMath.max(FastMath.sqrt(yDotOnScale2), yDDotOnScale);
/* 290 */     double h1 = (maxInv2 < 1.0E-15D) ? FastMath.max(1.0E-6D, 0.001D * FastMath.abs(h)) : FastMath.pow(0.01D / maxInv2, 1.0D / order);
/* 293 */     h = FastMath.min(100.0D * FastMath.abs(h), h1);
/* 294 */     h = FastMath.max(h, 1.0E-12D * FastMath.abs(t0));
/* 295 */     if (h < getMinStep())
/* 296 */       h = getMinStep(); 
/* 298 */     if (h > getMaxStep())
/* 299 */       h = getMaxStep(); 
/* 301 */     if (!forward)
/* 302 */       h = -h; 
/* 305 */     return h;
/*     */   }
/*     */   
/*     */   protected double filterStep(double h, boolean forward, boolean acceptSmall) throws NumberIsTooSmallException {
/* 321 */     double filteredH = h;
/* 322 */     if (FastMath.abs(h) < this.minStep)
/* 323 */       if (acceptSmall) {
/* 324 */         filteredH = forward ? this.minStep : -this.minStep;
/*     */       } else {
/* 326 */         throw new NumberIsTooSmallException(LocalizedFormats.MINIMAL_STEPSIZE_REACHED_DURING_INTEGRATION, Double.valueOf(FastMath.abs(h)), Double.valueOf(this.minStep), true);
/*     */       }  
/* 331 */     if (filteredH > this.maxStep) {
/* 332 */       filteredH = this.maxStep;
/* 333 */     } else if (filteredH < -this.maxStep) {
/* 334 */       filteredH = -this.maxStep;
/*     */     } 
/* 337 */     return filteredH;
/*     */   }
/*     */   
/*     */   public abstract void integrate(ExpandableStatefulODE paramExpandableStatefulODE, double paramDouble) throws MathIllegalStateException, MathIllegalArgumentException;
/*     */   
/*     */   public double getCurrentStepStart() {
/* 349 */     return this.stepStart;
/*     */   }
/*     */   
/*     */   protected void resetInternalState() {
/* 354 */     this.stepStart = Double.NaN;
/* 355 */     this.stepSize = FastMath.sqrt(this.minStep * this.maxStep);
/*     */   }
/*     */   
/*     */   public double getMinStep() {
/* 362 */     return this.minStep;
/*     */   }
/*     */   
/*     */   public double getMaxStep() {
/* 369 */     return this.maxStep;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\ode\nonstiff\AdaptiveStepsizeIntegrator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
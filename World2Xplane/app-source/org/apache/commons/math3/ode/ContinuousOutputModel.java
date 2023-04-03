/*     */ package org.apache.commons.math3.ode;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ import org.apache.commons.math3.exception.MathIllegalArgumentException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.ode.sampling.StepHandler;
/*     */ import org.apache.commons.math3.ode.sampling.StepInterpolator;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class ContinuousOutputModel implements StepHandler, Serializable {
/* 114 */   private List<StepInterpolator> steps = new ArrayList<StepInterpolator>();
/*     */   
/* 115 */   private double initialTime = Double.NaN;
/*     */   
/* 116 */   private double finalTime = Double.NaN;
/*     */   
/*     */   private boolean forward = true;
/*     */   
/* 118 */   private int index = 0;
/*     */   
/*     */   private static final long serialVersionUID = -1417964919405031606L;
/*     */   
/*     */   public void append(ContinuousOutputModel model) throws MathIllegalArgumentException {
/* 130 */     if (model.steps.size() == 0)
/*     */       return; 
/* 134 */     if (this.steps.size() == 0) {
/* 135 */       this.initialTime = model.initialTime;
/* 136 */       this.forward = model.forward;
/*     */     } else {
/* 139 */       if ((getInterpolatedState()).length != (model.getInterpolatedState()).length)
/* 140 */         throw new DimensionMismatchException((model.getInterpolatedState()).length, (getInterpolatedState()).length); 
/* 144 */       if ((this.forward ^ model.forward) != 0)
/* 145 */         throw new MathIllegalArgumentException(LocalizedFormats.PROPAGATION_DIRECTION_MISMATCH, new Object[0]); 
/* 148 */       StepInterpolator lastInterpolator = this.steps.get(this.index);
/* 149 */       double current = lastInterpolator.getCurrentTime();
/* 150 */       double previous = lastInterpolator.getPreviousTime();
/* 151 */       double step = current - previous;
/* 152 */       double gap = model.getInitialTime() - current;
/* 153 */       if (FastMath.abs(gap) > 0.001D * FastMath.abs(step))
/* 154 */         throw new MathIllegalArgumentException(LocalizedFormats.HOLE_BETWEEN_MODELS_TIME_RANGES, new Object[] { Double.valueOf(FastMath.abs(gap)) }); 
/*     */     } 
/* 160 */     for (StepInterpolator interpolator : model.steps)
/* 161 */       this.steps.add(interpolator.copy()); 
/* 164 */     this.index = this.steps.size() - 1;
/* 165 */     this.finalTime = ((StepInterpolator)this.steps.get(this.index)).getCurrentTime();
/*     */   }
/*     */   
/*     */   public void init(double t0, double[] y0, double t) {
/* 171 */     this.initialTime = Double.NaN;
/* 172 */     this.finalTime = Double.NaN;
/* 173 */     this.forward = true;
/* 174 */     this.index = 0;
/* 175 */     this.steps.clear();
/*     */   }
/*     */   
/*     */   public void handleStep(StepInterpolator interpolator, boolean isLast) {
/* 186 */     if (this.steps.size() == 0) {
/* 187 */       this.initialTime = interpolator.getPreviousTime();
/* 188 */       this.forward = interpolator.isForward();
/*     */     } 
/* 191 */     this.steps.add(interpolator.copy());
/* 193 */     if (isLast) {
/* 194 */       this.finalTime = interpolator.getCurrentTime();
/* 195 */       this.index = this.steps.size() - 1;
/*     */     } 
/*     */   }
/*     */   
/*     */   public double getInitialTime() {
/* 205 */     return this.initialTime;
/*     */   }
/*     */   
/*     */   public double getFinalTime() {
/* 213 */     return this.finalTime;
/*     */   }
/*     */   
/*     */   public double getInterpolatedTime() {
/* 223 */     return ((StepInterpolator)this.steps.get(this.index)).getInterpolatedTime();
/*     */   }
/*     */   
/*     */   public void setInterpolatedTime(double time) {
/* 241 */     int iMin = 0;
/* 242 */     StepInterpolator sMin = this.steps.get(iMin);
/* 243 */     double tMin = 0.5D * (sMin.getPreviousTime() + sMin.getCurrentTime());
/* 245 */     int iMax = this.steps.size() - 1;
/* 246 */     StepInterpolator sMax = this.steps.get(iMax);
/* 247 */     double tMax = 0.5D * (sMax.getPreviousTime() + sMax.getCurrentTime());
/* 251 */     if (locatePoint(time, sMin) <= 0) {
/* 252 */       this.index = iMin;
/* 253 */       sMin.setInterpolatedTime(time);
/*     */       return;
/*     */     } 
/* 256 */     if (locatePoint(time, sMax) >= 0) {
/* 257 */       this.index = iMax;
/* 258 */       sMax.setInterpolatedTime(time);
/*     */       return;
/*     */     } 
/* 263 */     while (iMax - iMin > 5) {
/* 266 */       StepInterpolator si = this.steps.get(this.index);
/* 267 */       int location = locatePoint(time, si);
/* 268 */       if (location < 0) {
/* 269 */         iMax = this.index;
/* 270 */         tMax = 0.5D * (si.getPreviousTime() + si.getCurrentTime());
/* 271 */       } else if (location > 0) {
/* 272 */         iMin = this.index;
/* 273 */         tMin = 0.5D * (si.getPreviousTime() + si.getCurrentTime());
/*     */       } else {
/* 276 */         si.setInterpolatedTime(time);
/*     */         return;
/*     */       } 
/* 281 */       int iMed = (iMin + iMax) / 2;
/* 282 */       StepInterpolator sMed = this.steps.get(iMed);
/* 283 */       double tMed = 0.5D * (sMed.getPreviousTime() + sMed.getCurrentTime());
/* 285 */       if (FastMath.abs(tMed - tMin) < 1.0E-6D || FastMath.abs(tMax - tMed) < 1.0E-6D) {
/* 287 */         this.index = iMed;
/*     */       } else {
/* 292 */         double d12 = tMax - tMed;
/* 293 */         double d23 = tMed - tMin;
/* 294 */         double d13 = tMax - tMin;
/* 295 */         double dt1 = time - tMax;
/* 296 */         double dt2 = time - tMed;
/* 297 */         double dt3 = time - tMin;
/* 298 */         double iLagrange = (dt2 * dt3 * d23 * iMax - dt1 * dt3 * d13 * iMed + dt1 * dt2 * d12 * iMin) / d12 * d23 * d13;
/* 302 */         this.index = (int)FastMath.rint(iLagrange);
/*     */       } 
/* 306 */       int low = FastMath.max(iMin + 1, (9 * iMin + iMax) / 10);
/* 307 */       int high = FastMath.min(iMax - 1, (iMin + 9 * iMax) / 10);
/* 308 */       if (this.index < low) {
/* 309 */         this.index = low;
/*     */         continue;
/*     */       } 
/* 310 */       if (this.index > high)
/* 311 */         this.index = high; 
/*     */     } 
/* 317 */     this.index = iMin;
/* 318 */     while (this.index <= iMax && locatePoint(time, this.steps.get(this.index)) > 0)
/* 319 */       this.index++; 
/* 322 */     ((StepInterpolator)this.steps.get(this.index)).setInterpolatedTime(time);
/*     */   }
/*     */   
/*     */   public double[] getInterpolatedState() {
/* 331 */     return ((StepInterpolator)this.steps.get(this.index)).getInterpolatedState();
/*     */   }
/*     */   
/*     */   private int locatePoint(double time, StepInterpolator interval) {
/* 342 */     if (this.forward) {
/* 343 */       if (time < interval.getPreviousTime())
/* 344 */         return -1; 
/* 345 */       if (time > interval.getCurrentTime())
/* 346 */         return 1; 
/* 348 */       return 0;
/*     */     } 
/* 351 */     if (time > interval.getPreviousTime())
/* 352 */       return -1; 
/* 353 */     if (time < interval.getCurrentTime())
/* 354 */       return 1; 
/* 356 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\ode\ContinuousOutputModel.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
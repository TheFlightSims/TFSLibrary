/*     */ package org.apache.commons.math3.ode.sampling;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectOutput;
/*     */ import org.apache.commons.math3.ode.EquationsMapper;
/*     */ 
/*     */ public abstract class AbstractStepInterpolator implements StepInterpolator {
/*     */   protected double h;
/*     */   
/*     */   protected double[] currentState;
/*     */   
/*     */   protected double interpolatedTime;
/*     */   
/*     */   protected double[] interpolatedState;
/*     */   
/*     */   protected double[] interpolatedDerivatives;
/*     */   
/*     */   protected double[] interpolatedPrimaryState;
/*     */   
/*     */   protected double[] interpolatedPrimaryDerivatives;
/*     */   
/*     */   protected double[][] interpolatedSecondaryState;
/*     */   
/*     */   protected double[][] interpolatedSecondaryDerivatives;
/*     */   
/*     */   private double globalPreviousTime;
/*     */   
/*     */   private double globalCurrentTime;
/*     */   
/*     */   private double softPreviousTime;
/*     */   
/*     */   private double softCurrentTime;
/*     */   
/*     */   private boolean finalized;
/*     */   
/*     */   private boolean forward;
/*     */   
/*     */   private boolean dirtyState;
/*     */   
/*     */   private EquationsMapper primaryMapper;
/*     */   
/*     */   private EquationsMapper[] secondaryMappers;
/*     */   
/*     */   protected AbstractStepInterpolator() {
/* 112 */     this.globalPreviousTime = Double.NaN;
/* 113 */     this.globalCurrentTime = Double.NaN;
/* 114 */     this.softPreviousTime = Double.NaN;
/* 115 */     this.softCurrentTime = Double.NaN;
/* 116 */     this.h = Double.NaN;
/* 117 */     this.interpolatedTime = Double.NaN;
/* 118 */     this.currentState = null;
/* 119 */     this.finalized = false;
/* 120 */     this.forward = true;
/* 121 */     this.dirtyState = true;
/* 122 */     this.primaryMapper = null;
/* 123 */     this.secondaryMappers = null;
/* 124 */     allocateInterpolatedArrays(-1);
/*     */   }
/*     */   
/*     */   protected AbstractStepInterpolator(double[] y, boolean forward, EquationsMapper primaryMapper, EquationsMapper[] secondaryMappers) {
/* 138 */     this.globalPreviousTime = Double.NaN;
/* 139 */     this.globalCurrentTime = Double.NaN;
/* 140 */     this.softPreviousTime = Double.NaN;
/* 141 */     this.softCurrentTime = Double.NaN;
/* 142 */     this.h = Double.NaN;
/* 143 */     this.interpolatedTime = Double.NaN;
/* 144 */     this.currentState = y;
/* 145 */     this.finalized = false;
/* 146 */     this.forward = forward;
/* 147 */     this.dirtyState = true;
/* 148 */     this.primaryMapper = primaryMapper;
/* 149 */     this.secondaryMappers = (secondaryMappers == null) ? null : (EquationsMapper[])secondaryMappers.clone();
/* 150 */     allocateInterpolatedArrays(y.length);
/*     */   }
/*     */   
/*     */   protected AbstractStepInterpolator(AbstractStepInterpolator interpolator) {
/* 173 */     this.globalPreviousTime = interpolator.globalPreviousTime;
/* 174 */     this.globalCurrentTime = interpolator.globalCurrentTime;
/* 175 */     this.softPreviousTime = interpolator.softPreviousTime;
/* 176 */     this.softCurrentTime = interpolator.softCurrentTime;
/* 177 */     this.h = interpolator.h;
/* 178 */     this.interpolatedTime = interpolator.interpolatedTime;
/* 180 */     if (interpolator.currentState == null) {
/* 181 */       this.currentState = null;
/* 182 */       this.primaryMapper = null;
/* 183 */       this.secondaryMappers = null;
/* 184 */       allocateInterpolatedArrays(-1);
/*     */     } else {
/* 186 */       this.currentState = (double[])interpolator.currentState.clone();
/* 187 */       this.interpolatedState = (double[])interpolator.interpolatedState.clone();
/* 188 */       this.interpolatedDerivatives = (double[])interpolator.interpolatedDerivatives.clone();
/* 189 */       this.interpolatedPrimaryState = (double[])interpolator.interpolatedPrimaryState.clone();
/* 190 */       this.interpolatedPrimaryDerivatives = (double[])interpolator.interpolatedPrimaryDerivatives.clone();
/* 191 */       this.interpolatedSecondaryState = new double[interpolator.interpolatedSecondaryState.length][];
/* 192 */       this.interpolatedSecondaryDerivatives = new double[interpolator.interpolatedSecondaryDerivatives.length][];
/* 193 */       for (int i = 0; i < this.interpolatedSecondaryState.length; i++) {
/* 194 */         this.interpolatedSecondaryState[i] = (double[])interpolator.interpolatedSecondaryState[i].clone();
/* 195 */         this.interpolatedSecondaryDerivatives[i] = (double[])interpolator.interpolatedSecondaryDerivatives[i].clone();
/*     */       } 
/*     */     } 
/* 199 */     this.finalized = interpolator.finalized;
/* 200 */     this.forward = interpolator.forward;
/* 201 */     this.dirtyState = interpolator.dirtyState;
/* 202 */     this.primaryMapper = interpolator.primaryMapper;
/* 203 */     this.secondaryMappers = (interpolator.secondaryMappers == null) ? null : (EquationsMapper[])interpolator.secondaryMappers.clone();
/*     */   }
/*     */   
/*     */   private void allocateInterpolatedArrays(int dimension) {
/* 212 */     if (dimension < 0) {
/* 213 */       this.interpolatedState = null;
/* 214 */       this.interpolatedDerivatives = null;
/* 215 */       this.interpolatedPrimaryState = null;
/* 216 */       this.interpolatedPrimaryDerivatives = null;
/* 217 */       this.interpolatedSecondaryState = (double[][])null;
/* 218 */       this.interpolatedSecondaryDerivatives = (double[][])null;
/*     */     } else {
/* 220 */       this.interpolatedState = new double[dimension];
/* 221 */       this.interpolatedDerivatives = new double[dimension];
/* 222 */       this.interpolatedPrimaryState = new double[this.primaryMapper.getDimension()];
/* 223 */       this.interpolatedPrimaryDerivatives = new double[this.primaryMapper.getDimension()];
/* 224 */       if (this.secondaryMappers == null) {
/* 225 */         this.interpolatedSecondaryState = (double[][])null;
/* 226 */         this.interpolatedSecondaryDerivatives = (double[][])null;
/*     */       } else {
/* 228 */         this.interpolatedSecondaryState = new double[this.secondaryMappers.length][];
/* 229 */         this.interpolatedSecondaryDerivatives = new double[this.secondaryMappers.length][];
/* 230 */         for (int i = 0; i < this.secondaryMappers.length; i++) {
/* 231 */           this.interpolatedSecondaryState[i] = new double[this.secondaryMappers[i].getDimension()];
/* 232 */           this.interpolatedSecondaryDerivatives[i] = new double[this.secondaryMappers[i].getDimension()];
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void reinitialize(double[] y, boolean isForward, EquationsMapper primary, EquationsMapper[] secondary) {
/* 248 */     this.globalPreviousTime = Double.NaN;
/* 249 */     this.globalCurrentTime = Double.NaN;
/* 250 */     this.softPreviousTime = Double.NaN;
/* 251 */     this.softCurrentTime = Double.NaN;
/* 252 */     this.h = Double.NaN;
/* 253 */     this.interpolatedTime = Double.NaN;
/* 254 */     this.currentState = y;
/* 255 */     this.finalized = false;
/* 256 */     this.forward = isForward;
/* 257 */     this.dirtyState = true;
/* 258 */     this.primaryMapper = primary;
/* 259 */     this.secondaryMappers = (EquationsMapper[])secondary.clone();
/* 260 */     allocateInterpolatedArrays(y.length);
/*     */   }
/*     */   
/*     */   public StepInterpolator copy() {
/* 268 */     finalizeStep();
/* 271 */     return doCopy();
/*     */   }
/*     */   
/*     */   protected abstract StepInterpolator doCopy();
/*     */   
/*     */   public void shift() {
/* 289 */     this.globalPreviousTime = this.globalCurrentTime;
/* 290 */     this.softPreviousTime = this.globalPreviousTime;
/* 291 */     this.softCurrentTime = this.globalCurrentTime;
/*     */   }
/*     */   
/*     */   public void storeTime(double t) {
/* 299 */     this.globalCurrentTime = t;
/* 300 */     this.softCurrentTime = this.globalCurrentTime;
/* 301 */     this.h = this.globalCurrentTime - this.globalPreviousTime;
/* 302 */     setInterpolatedTime(t);
/* 305 */     this.finalized = false;
/*     */   }
/*     */   
/*     */   public void setSoftPreviousTime(double softPreviousTime) {
/* 320 */     this.softPreviousTime = softPreviousTime;
/*     */   }
/*     */   
/*     */   public void setSoftCurrentTime(double softCurrentTime) {
/* 334 */     this.softCurrentTime = softCurrentTime;
/*     */   }
/*     */   
/*     */   public double getGlobalPreviousTime() {
/* 342 */     return this.globalPreviousTime;
/*     */   }
/*     */   
/*     */   public double getGlobalCurrentTime() {
/* 350 */     return this.globalCurrentTime;
/*     */   }
/*     */   
/*     */   public double getPreviousTime() {
/* 359 */     return this.softPreviousTime;
/*     */   }
/*     */   
/*     */   public double getCurrentTime() {
/* 368 */     return this.softCurrentTime;
/*     */   }
/*     */   
/*     */   public double getInterpolatedTime() {
/* 373 */     return this.interpolatedTime;
/*     */   }
/*     */   
/*     */   public void setInterpolatedTime(double time) {
/* 378 */     this.interpolatedTime = time;
/* 379 */     this.dirtyState = true;
/*     */   }
/*     */   
/*     */   public boolean isForward() {
/* 384 */     return this.forward;
/*     */   }
/*     */   
/*     */   protected abstract void computeInterpolatedStateAndDerivatives(double paramDouble1, double paramDouble2);
/*     */   
/*     */   private void evaluateCompleteInterpolatedState() {
/* 402 */     if (this.dirtyState) {
/* 403 */       double oneMinusThetaH = this.globalCurrentTime - this.interpolatedTime;
/* 404 */       double theta = (this.h == 0.0D) ? 0.0D : ((this.h - oneMinusThetaH) / this.h);
/* 405 */       computeInterpolatedStateAndDerivatives(theta, oneMinusThetaH);
/* 406 */       this.dirtyState = false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public double[] getInterpolatedState() {
/* 412 */     evaluateCompleteInterpolatedState();
/* 413 */     this.primaryMapper.extractEquationData(this.interpolatedState, this.interpolatedPrimaryState);
/* 415 */     return this.interpolatedPrimaryState;
/*     */   }
/*     */   
/*     */   public double[] getInterpolatedDerivatives() {
/* 420 */     evaluateCompleteInterpolatedState();
/* 421 */     this.primaryMapper.extractEquationData(this.interpolatedDerivatives, this.interpolatedPrimaryDerivatives);
/* 423 */     return this.interpolatedPrimaryDerivatives;
/*     */   }
/*     */   
/*     */   public double[] getInterpolatedSecondaryState(int index) {
/* 428 */     evaluateCompleteInterpolatedState();
/* 429 */     this.secondaryMappers[index].extractEquationData(this.interpolatedState, this.interpolatedSecondaryState[index]);
/* 431 */     return this.interpolatedSecondaryState[index];
/*     */   }
/*     */   
/*     */   public double[] getInterpolatedSecondaryDerivatives(int index) {
/* 436 */     evaluateCompleteInterpolatedState();
/* 437 */     this.secondaryMappers[index].extractEquationData(this.interpolatedDerivatives, this.interpolatedSecondaryDerivatives[index]);
/* 439 */     return this.interpolatedSecondaryDerivatives[index];
/*     */   }
/*     */   
/*     */   public final void finalizeStep() {
/* 482 */     if (!this.finalized) {
/* 483 */       doFinalize();
/* 484 */       this.finalized = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void doFinalize() {}
/*     */   
/*     */   public abstract void writeExternal(ObjectOutput paramObjectOutput) throws IOException;
/*     */   
/*     */   public abstract void readExternal(ObjectInput paramObjectInput) throws IOException, ClassNotFoundException;
/*     */   
/*     */   protected void writeBaseExternal(ObjectOutput out) throws IOException {
/* 512 */     if (this.currentState == null) {
/* 513 */       out.writeInt(-1);
/*     */     } else {
/* 515 */       out.writeInt(this.currentState.length);
/*     */     } 
/* 517 */     out.writeDouble(this.globalPreviousTime);
/* 518 */     out.writeDouble(this.globalCurrentTime);
/* 519 */     out.writeDouble(this.softPreviousTime);
/* 520 */     out.writeDouble(this.softCurrentTime);
/* 521 */     out.writeDouble(this.h);
/* 522 */     out.writeBoolean(this.forward);
/* 523 */     out.writeObject(this.primaryMapper);
/* 524 */     out.write(this.secondaryMappers.length);
/* 525 */     for (EquationsMapper mapper : this.secondaryMappers)
/* 526 */       out.writeObject(mapper); 
/* 529 */     if (this.currentState != null)
/* 530 */       for (int i = 0; i < this.currentState.length; i++)
/* 531 */         out.writeDouble(this.currentState[i]);  
/* 535 */     out.writeDouble(this.interpolatedTime);
/* 541 */     finalizeStep();
/*     */   }
/*     */   
/*     */   protected double readBaseExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 559 */     int dimension = in.readInt();
/* 560 */     this.globalPreviousTime = in.readDouble();
/* 561 */     this.globalCurrentTime = in.readDouble();
/* 562 */     this.softPreviousTime = in.readDouble();
/* 563 */     this.softCurrentTime = in.readDouble();
/* 564 */     this.h = in.readDouble();
/* 565 */     this.forward = in.readBoolean();
/* 566 */     this.primaryMapper = (EquationsMapper)in.readObject();
/* 567 */     this.secondaryMappers = new EquationsMapper[in.read()];
/*     */     int i;
/* 568 */     for (i = 0; i < this.secondaryMappers.length; i++)
/* 569 */       this.secondaryMappers[i] = (EquationsMapper)in.readObject(); 
/* 571 */     this.dirtyState = true;
/* 573 */     if (dimension < 0) {
/* 574 */       this.currentState = null;
/*     */     } else {
/* 576 */       this.currentState = new double[dimension];
/* 577 */       for (i = 0; i < this.currentState.length; i++)
/* 578 */         this.currentState[i] = in.readDouble(); 
/*     */     } 
/* 583 */     this.interpolatedTime = Double.NaN;
/* 584 */     allocateInterpolatedArrays(dimension);
/* 586 */     this.finalized = true;
/* 588 */     return in.readDouble();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\ode\sampling\AbstractStepInterpolator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
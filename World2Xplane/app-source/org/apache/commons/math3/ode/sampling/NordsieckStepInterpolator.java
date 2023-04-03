/*     */ package org.apache.commons.math3.ode.sampling;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectOutput;
/*     */ import java.util.Arrays;
/*     */ import org.apache.commons.math3.linear.Array2DRowRealMatrix;
/*     */ import org.apache.commons.math3.ode.EquationsMapper;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class NordsieckStepInterpolator extends AbstractStepInterpolator {
/*     */   private static final long serialVersionUID = -7179861704951334960L;
/*     */   
/*     */   protected double[] stateVariation;
/*     */   
/*     */   private double scalingH;
/*     */   
/*     */   private double referenceTime;
/*     */   
/*     */   private double[] scaled;
/*     */   
/*     */   private Array2DRowRealMatrix nordsieck;
/*     */   
/*     */   public NordsieckStepInterpolator() {}
/*     */   
/*     */   public NordsieckStepInterpolator(NordsieckStepInterpolator interpolator) {
/*  82 */     super(interpolator);
/*  83 */     this.scalingH = interpolator.scalingH;
/*  84 */     this.referenceTime = interpolator.referenceTime;
/*  85 */     if (interpolator.scaled != null)
/*  86 */       this.scaled = (double[])interpolator.scaled.clone(); 
/*  88 */     if (interpolator.nordsieck != null)
/*  89 */       this.nordsieck = new Array2DRowRealMatrix(interpolator.nordsieck.getDataRef(), true); 
/*  91 */     if (interpolator.stateVariation != null)
/*  92 */       this.stateVariation = (double[])interpolator.stateVariation.clone(); 
/*     */   }
/*     */   
/*     */   protected StepInterpolator doCopy() {
/*  99 */     return new NordsieckStepInterpolator(this);
/*     */   }
/*     */   
/*     */   public void reinitialize(double[] y, boolean forward, EquationsMapper primaryMapper, EquationsMapper[] secondaryMappers) {
/* 115 */     super.reinitialize(y, forward, primaryMapper, secondaryMappers);
/* 116 */     this.stateVariation = new double[y.length];
/*     */   }
/*     */   
/*     */   public void reinitialize(double time, double stepSize, double[] scaledDerivative, Array2DRowRealMatrix nordsieckVector) {
/* 132 */     this.referenceTime = time;
/* 133 */     this.scalingH = stepSize;
/* 134 */     this.scaled = scaledDerivative;
/* 135 */     this.nordsieck = nordsieckVector;
/* 138 */     setInterpolatedTime(getInterpolatedTime());
/*     */   }
/*     */   
/*     */   public void rescale(double stepSize) {
/* 149 */     double ratio = stepSize / this.scalingH;
/* 150 */     for (int i = 0; i < this.scaled.length; i++)
/* 151 */       this.scaled[i] = this.scaled[i] * ratio; 
/* 154 */     double[][] nData = this.nordsieck.getDataRef();
/* 155 */     double power = ratio;
/* 156 */     for (int j = 0; j < nData.length; j++) {
/* 157 */       power *= ratio;
/* 158 */       double[] nDataI = nData[j];
/* 159 */       for (int k = 0; k < nDataI.length; k++)
/* 160 */         nDataI[k] = nDataI[k] * power; 
/*     */     } 
/* 164 */     this.scalingH = stepSize;
/*     */   }
/*     */   
/*     */   public double[] getInterpolatedStateVariation() {
/* 182 */     getInterpolatedState();
/* 183 */     return this.stateVariation;
/*     */   }
/*     */   
/*     */   protected void computeInterpolatedStateAndDerivatives(double theta, double oneMinusThetaH) {
/* 190 */     double x = this.interpolatedTime - this.referenceTime;
/* 191 */     double normalizedAbscissa = x / this.scalingH;
/* 193 */     Arrays.fill(this.stateVariation, 0.0D);
/* 194 */     Arrays.fill(this.interpolatedDerivatives, 0.0D);
/* 198 */     double[][] nData = this.nordsieck.getDataRef();
/* 199 */     for (int i = nData.length - 1; i >= 0; i--) {
/* 200 */       int order = i + 2;
/* 201 */       double[] nDataI = nData[i];
/* 202 */       double power = FastMath.pow(normalizedAbscissa, order);
/* 203 */       for (int k = 0; k < nDataI.length; k++) {
/* 204 */         double d = nDataI[k] * power;
/* 205 */         this.stateVariation[k] = this.stateVariation[k] + d;
/* 206 */         this.interpolatedDerivatives[k] = this.interpolatedDerivatives[k] + order * d;
/*     */       } 
/*     */     } 
/* 210 */     for (int j = 0; j < this.currentState.length; j++) {
/* 211 */       this.stateVariation[j] = this.stateVariation[j] + this.scaled[j] * normalizedAbscissa;
/* 212 */       this.interpolatedState[j] = this.currentState[j] + this.stateVariation[j];
/* 213 */       this.interpolatedDerivatives[j] = (this.interpolatedDerivatives[j] + this.scaled[j] * normalizedAbscissa) / x;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 225 */     writeBaseExternal(out);
/* 228 */     out.writeDouble(this.scalingH);
/* 229 */     out.writeDouble(this.referenceTime);
/* 231 */     int n = (this.currentState == null) ? -1 : this.currentState.length;
/* 232 */     if (this.scaled == null) {
/* 233 */       out.writeBoolean(false);
/*     */     } else {
/* 235 */       out.writeBoolean(true);
/* 236 */       for (int j = 0; j < n; j++)
/* 237 */         out.writeDouble(this.scaled[j]); 
/*     */     } 
/* 241 */     if (this.nordsieck == null) {
/* 242 */       out.writeBoolean(false);
/*     */     } else {
/* 244 */       out.writeBoolean(true);
/* 245 */       out.writeObject(this.nordsieck);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 258 */     double t = readBaseExternal(in);
/* 261 */     this.scalingH = in.readDouble();
/* 262 */     this.referenceTime = in.readDouble();
/* 264 */     int n = (this.currentState == null) ? -1 : this.currentState.length;
/* 265 */     boolean hasScaled = in.readBoolean();
/* 266 */     if (hasScaled) {
/* 267 */       this.scaled = new double[n];
/* 268 */       for (int j = 0; j < n; j++)
/* 269 */         this.scaled[j] = in.readDouble(); 
/*     */     } else {
/* 272 */       this.scaled = null;
/*     */     } 
/* 275 */     boolean hasNordsieck = in.readBoolean();
/* 276 */     if (hasNordsieck) {
/* 277 */       this.nordsieck = (Array2DRowRealMatrix)in.readObject();
/*     */     } else {
/* 279 */       this.nordsieck = null;
/*     */     } 
/* 282 */     if (hasScaled && hasNordsieck) {
/* 284 */       this.stateVariation = new double[n];
/* 285 */       setInterpolatedTime(t);
/*     */     } else {
/* 287 */       this.stateVariation = null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\ode\sampling\NordsieckStepInterpolator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
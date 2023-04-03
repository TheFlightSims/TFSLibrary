/*     */ package org.apache.commons.math3.ode.nonstiff;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectOutput;
/*     */ import org.apache.commons.math3.ode.AbstractIntegrator;
/*     */ import org.apache.commons.math3.ode.EquationsMapper;
/*     */ import org.apache.commons.math3.ode.sampling.AbstractStepInterpolator;
/*     */ 
/*     */ abstract class RungeKuttaStepInterpolator extends AbstractStepInterpolator {
/*     */   protected double[] previousState;
/*     */   
/*     */   protected double[][] yDotK;
/*     */   
/*     */   protected AbstractIntegrator integrator;
/*     */   
/*     */   protected RungeKuttaStepInterpolator() {
/*  61 */     this.previousState = null;
/*  62 */     this.yDotK = (double[][])null;
/*  63 */     this.integrator = null;
/*     */   }
/*     */   
/*     */   public RungeKuttaStepInterpolator(RungeKuttaStepInterpolator interpolator) {
/*  85 */     super(interpolator);
/*  87 */     if (interpolator.currentState != null) {
/*  89 */       this.previousState = (double[])interpolator.previousState.clone();
/*  91 */       this.yDotK = new double[interpolator.yDotK.length][];
/*  92 */       for (int k = 0; k < interpolator.yDotK.length; k++)
/*  93 */         this.yDotK[k] = (double[])interpolator.yDotK[k].clone(); 
/*     */     } else {
/*  97 */       this.previousState = null;
/*  98 */       this.yDotK = (double[][])null;
/*     */     } 
/* 103 */     this.integrator = null;
/*     */   }
/*     */   
/*     */   public void reinitialize(AbstractIntegrator rkIntegrator, double[] y, double[][] yDotArray, boolean forward, EquationsMapper primaryMapper, EquationsMapper[] secondaryMappers) {
/* 134 */     reinitialize(y, forward, primaryMapper, secondaryMappers);
/* 135 */     this.previousState = null;
/* 136 */     this.yDotK = yDotArray;
/* 137 */     this.integrator = rkIntegrator;
/*     */   }
/*     */   
/*     */   public void shift() {
/* 143 */     this.previousState = (double[])this.currentState.clone();
/* 144 */     super.shift();
/*     */   }
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 153 */     writeBaseExternal(out);
/* 156 */     int n = (this.currentState == null) ? -1 : this.currentState.length;
/* 157 */     for (int i = 0; i < n; i++)
/* 158 */       out.writeDouble(this.previousState[i]); 
/* 161 */     int kMax = (this.yDotK == null) ? -1 : this.yDotK.length;
/* 162 */     out.writeInt(kMax);
/* 163 */     for (int k = 0; k < kMax; k++) {
/* 164 */       for (int j = 0; j < n; j++)
/* 165 */         out.writeDouble(this.yDotK[k][j]); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 179 */     double t = readBaseExternal(in);
/* 182 */     int n = (this.currentState == null) ? -1 : this.currentState.length;
/* 183 */     if (n < 0) {
/* 184 */       this.previousState = null;
/*     */     } else {
/* 186 */       this.previousState = new double[n];
/* 187 */       for (int i = 0; i < n; i++)
/* 188 */         this.previousState[i] = in.readDouble(); 
/*     */     } 
/* 192 */     int kMax = in.readInt();
/* 193 */     this.yDotK = (kMax < 0) ? (double[][])null : new double[kMax][];
/* 194 */     for (int k = 0; k < kMax; k++) {
/* 195 */       this.yDotK[k] = (n < 0) ? null : new double[n];
/* 196 */       for (int i = 0; i < n; i++)
/* 197 */         this.yDotK[k][i] = in.readDouble(); 
/*     */     } 
/* 201 */     this.integrator = null;
/* 203 */     if (this.currentState != null) {
/* 205 */       setInterpolatedTime(t);
/*     */     } else {
/* 207 */       this.interpolatedTime = t;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\ode\nonstiff\RungeKuttaStepInterpolator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
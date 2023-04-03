/*     */ package org.apache.commons.math3.ode.nonstiff;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectOutput;
/*     */ import org.apache.commons.math3.ode.EquationsMapper;
/*     */ import org.apache.commons.math3.ode.sampling.AbstractStepInterpolator;
/*     */ import org.apache.commons.math3.ode.sampling.StepInterpolator;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ class GraggBulirschStoerStepInterpolator extends AbstractStepInterpolator {
/*     */   private static final long serialVersionUID = 20110928L;
/*     */   
/*     */   private double[] y0Dot;
/*     */   
/*     */   private double[] y1;
/*     */   
/*     */   private double[] y1Dot;
/*     */   
/*     */   private double[][] yMidDots;
/*     */   
/*     */   private double[][] polynomials;
/*     */   
/*     */   private double[] errfac;
/*     */   
/*     */   private int currentDegree;
/*     */   
/*     */   public GraggBulirschStoerStepInterpolator() {
/* 112 */     this.y0Dot = null;
/* 113 */     this.y1 = null;
/* 114 */     this.y1Dot = null;
/* 115 */     this.yMidDots = (double[][])null;
/* 116 */     resetTables(-1);
/*     */   }
/*     */   
/*     */   public GraggBulirschStoerStepInterpolator(double[] y, double[] y0Dot, double[] y1, double[] y1Dot, double[][] yMidDots, boolean forward, EquationsMapper primaryMapper, EquationsMapper[] secondaryMappers) {
/* 140 */     super(y, forward, primaryMapper, secondaryMappers);
/* 141 */     this.y0Dot = y0Dot;
/* 142 */     this.y1 = y1;
/* 143 */     this.y1Dot = y1Dot;
/* 144 */     this.yMidDots = yMidDots;
/* 146 */     resetTables(yMidDots.length + 4);
/*     */   }
/*     */   
/*     */   public GraggBulirschStoerStepInterpolator(GraggBulirschStoerStepInterpolator interpolator) {
/* 158 */     super(interpolator);
/* 160 */     int dimension = this.currentState.length;
/* 164 */     this.y0Dot = null;
/* 165 */     this.y1 = null;
/* 166 */     this.y1Dot = null;
/* 167 */     this.yMidDots = (double[][])null;
/* 170 */     if (interpolator.polynomials == null) {
/* 171 */       this.polynomials = (double[][])null;
/* 172 */       this.currentDegree = -1;
/*     */     } else {
/* 174 */       resetTables(interpolator.currentDegree);
/* 175 */       for (int i = 0; i < this.polynomials.length; i++) {
/* 176 */         this.polynomials[i] = new double[dimension];
/* 177 */         System.arraycopy(interpolator.polynomials[i], 0, this.polynomials[i], 0, dimension);
/*     */       } 
/* 180 */       this.currentDegree = interpolator.currentDegree;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void resetTables(int maxDegree) {
/* 192 */     if (maxDegree < 0) {
/* 193 */       this.polynomials = (double[][])null;
/* 194 */       this.errfac = null;
/* 195 */       this.currentDegree = -1;
/*     */     } else {
/* 198 */       double[][] newPols = new double[maxDegree + 1][];
/* 199 */       if (this.polynomials != null) {
/* 200 */         System.arraycopy(this.polynomials, 0, newPols, 0, this.polynomials.length);
/* 201 */         for (int i = this.polynomials.length; i < newPols.length; i++)
/* 202 */           newPols[i] = new double[this.currentState.length]; 
/*     */       } else {
/* 205 */         for (int i = 0; i < newPols.length; i++)
/* 206 */           newPols[i] = new double[this.currentState.length]; 
/*     */       } 
/* 209 */       this.polynomials = newPols;
/* 212 */       if (maxDegree <= 4) {
/* 213 */         this.errfac = null;
/*     */       } else {
/* 215 */         this.errfac = new double[maxDegree - 4];
/* 216 */         for (int i = 0; i < this.errfac.length; i++) {
/* 217 */           int ip5 = i + 5;
/* 218 */           this.errfac[i] = 1.0D / (ip5 * ip5);
/* 219 */           double e = 0.5D * FastMath.sqrt((i + 1) / ip5);
/* 220 */           for (int j = 0; j <= i; j++)
/* 221 */             this.errfac[i] = this.errfac[i] * e / (j + 1); 
/*     */         } 
/*     */       } 
/* 226 */       this.currentDegree = 0;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected StepInterpolator doCopy() {
/* 235 */     return (StepInterpolator)new GraggBulirschStoerStepInterpolator(this);
/*     */   }
/*     */   
/*     */   public void computeCoefficients(int mu, double h) {
/* 245 */     if (this.polynomials == null || this.polynomials.length <= mu + 4)
/* 246 */       resetTables(mu + 4); 
/* 249 */     this.currentDegree = mu + 4;
/* 251 */     for (int i = 0; i < this.currentState.length; i++) {
/* 253 */       double yp0 = h * this.y0Dot[i];
/* 254 */       double yp1 = h * this.y1Dot[i];
/* 255 */       double ydiff = this.y1[i] - this.currentState[i];
/* 256 */       double aspl = ydiff - yp1;
/* 257 */       double bspl = yp0 - ydiff;
/* 259 */       this.polynomials[0][i] = this.currentState[i];
/* 260 */       this.polynomials[1][i] = ydiff;
/* 261 */       this.polynomials[2][i] = aspl;
/* 262 */       this.polynomials[3][i] = bspl;
/* 264 */       if (mu < 0)
/*     */         return; 
/* 269 */       double ph0 = 0.5D * (this.currentState[i] + this.y1[i]) + 0.125D * (aspl + bspl);
/* 270 */       this.polynomials[4][i] = 16.0D * (this.yMidDots[0][i] - ph0);
/* 272 */       if (mu > 0) {
/* 273 */         double ph1 = ydiff + 0.25D * (aspl - bspl);
/* 274 */         this.polynomials[5][i] = 16.0D * (this.yMidDots[1][i] - ph1);
/* 276 */         if (mu > 1) {
/* 277 */           double ph2 = yp1 - yp0;
/* 278 */           this.polynomials[6][i] = 16.0D * (this.yMidDots[2][i] - ph2 + this.polynomials[4][i]);
/* 280 */           if (mu > 2) {
/* 281 */             double ph3 = 6.0D * (bspl - aspl);
/* 282 */             this.polynomials[7][i] = 16.0D * (this.yMidDots[3][i] - ph3 + 3.0D * this.polynomials[5][i]);
/* 284 */             for (int j = 4; j <= mu; j++) {
/* 285 */               double fac1 = 0.5D * j * (j - 1);
/* 286 */               double fac2 = 2.0D * fac1 * (j - 2) * (j - 3);
/* 287 */               this.polynomials[j + 4][i] = 16.0D * (this.yMidDots[j][i] + fac1 * this.polynomials[j + 2][i] - fac2 * this.polynomials[j][i]);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public double estimateError(double[] scale) {
/* 303 */     double error = 0.0D;
/* 304 */     if (this.currentDegree >= 5) {
/* 305 */       for (int i = 0; i < scale.length; i++) {
/* 306 */         double e = this.polynomials[this.currentDegree][i] / scale[i];
/* 307 */         error += e * e;
/*     */       } 
/* 309 */       error = FastMath.sqrt(error / scale.length) * this.errfac[this.currentDegree - 5];
/*     */     } 
/* 311 */     return error;
/*     */   }
/*     */   
/*     */   protected void computeInterpolatedStateAndDerivatives(double theta, double oneMinusThetaH) {
/* 319 */     int dimension = this.currentState.length;
/* 321 */     double oneMinusTheta = 1.0D - theta;
/* 322 */     double theta05 = theta - 0.5D;
/* 323 */     double tOmT = theta * oneMinusTheta;
/* 324 */     double t4 = tOmT * tOmT;
/* 325 */     double t4Dot = 2.0D * tOmT * (1.0D - 2.0D * theta);
/* 326 */     double dot1 = 1.0D / this.h;
/* 327 */     double dot2 = theta * (2.0D - 3.0D * theta) / this.h;
/* 328 */     double dot3 = ((3.0D * theta - 4.0D) * theta + 1.0D) / this.h;
/* 330 */     for (int i = 0; i < dimension; i++) {
/* 332 */       double p0 = this.polynomials[0][i];
/* 333 */       double p1 = this.polynomials[1][i];
/* 334 */       double p2 = this.polynomials[2][i];
/* 335 */       double p3 = this.polynomials[3][i];
/* 336 */       this.interpolatedState[i] = p0 + theta * (p1 + oneMinusTheta * (p2 * theta + p3 * oneMinusTheta));
/* 337 */       this.interpolatedDerivatives[i] = dot1 * p1 + dot2 * p2 + dot3 * p3;
/* 339 */       if (this.currentDegree > 3) {
/* 340 */         double cDot = 0.0D;
/* 341 */         double c = this.polynomials[this.currentDegree][i];
/* 342 */         for (int j = this.currentDegree - 1; j > 3; j--) {
/* 343 */           double d = 1.0D / (j - 3);
/* 344 */           cDot = d * (theta05 * cDot + c);
/* 345 */           c = this.polynomials[j][i] + c * d * theta05;
/*     */         } 
/* 347 */         this.interpolatedState[i] = this.interpolatedState[i] + t4 * c;
/* 348 */         this.interpolatedDerivatives[i] = this.interpolatedDerivatives[i] + (t4 * cDot + t4Dot * c) / this.h;
/*     */       } 
/*     */     } 
/* 353 */     if (this.h == 0.0D)
/* 356 */       System.arraycopy(this.yMidDots[1], 0, this.interpolatedDerivatives, 0, dimension); 
/*     */   }
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 366 */     int dimension = (this.currentState == null) ? -1 : this.currentState.length;
/* 369 */     writeBaseExternal(out);
/* 372 */     out.writeInt(this.currentDegree);
/* 373 */     for (int k = 0; k <= this.currentDegree; k++) {
/* 374 */       for (int l = 0; l < dimension; l++)
/* 375 */         out.writeDouble(this.polynomials[k][l]); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 387 */     double t = readBaseExternal(in);
/* 388 */     int dimension = (this.currentState == null) ? -1 : this.currentState.length;
/* 391 */     int degree = in.readInt();
/* 392 */     resetTables(degree);
/* 393 */     this.currentDegree = degree;
/* 395 */     for (int k = 0; k <= this.currentDegree; k++) {
/* 396 */       for (int l = 0; l < dimension; l++)
/* 397 */         this.polynomials[k][l] = in.readDouble(); 
/*     */     } 
/* 402 */     setInterpolatedTime(t);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\ode\nonstiff\GraggBulirschStoerStepInterpolator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
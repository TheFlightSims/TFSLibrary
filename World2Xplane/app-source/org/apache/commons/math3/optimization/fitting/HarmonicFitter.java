/*     */ package org.apache.commons.math3.optimization.fitting;
/*     */ 
/*     */ import org.apache.commons.math3.analysis.ParametricUnivariateFunction;
/*     */ import org.apache.commons.math3.analysis.function.HarmonicOscillator;
/*     */ import org.apache.commons.math3.exception.NumberIsTooSmallException;
/*     */ import org.apache.commons.math3.exception.ZeroException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.optimization.DifferentiableMultivariateVectorOptimizer;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class HarmonicFitter extends CurveFitter {
/*     */   public HarmonicFitter(DifferentiableMultivariateVectorOptimizer optimizer) {
/*  45 */     super(optimizer);
/*     */   }
/*     */   
/*     */   public double[] fit(double[] initialGuess) {
/*  61 */     return fit((ParametricUnivariateFunction)new HarmonicOscillator.Parametric(), initialGuess);
/*     */   }
/*     */   
/*     */   public double[] fit() {
/*  76 */     return fit((new ParameterGuesser(getObservations())).guess());
/*     */   }
/*     */   
/*     */   public static class ParameterGuesser {
/*     */     private final WeightedObservedPoint[] observations;
/*     */     
/*     */     private double a;
/*     */     
/*     */     private double omega;
/*     */     
/*     */     private double phi;
/*     */     
/*     */     public ParameterGuesser(WeightedObservedPoint[] observations) {
/* 195 */       if (observations.length < 4)
/* 196 */         throw new NumberIsTooSmallException(LocalizedFormats.INSUFFICIENT_OBSERVED_POINTS_IN_SAMPLE, Integer.valueOf(observations.length), Integer.valueOf(4), true); 
/* 200 */       this.observations = (WeightedObservedPoint[])observations.clone();
/*     */     }
/*     */     
/*     */     public double[] guess() {
/* 214 */       sortObservations();
/* 215 */       guessAOmega();
/* 216 */       guessPhi();
/* 217 */       return new double[] { this.a, this.omega, this.phi };
/*     */     }
/*     */     
/*     */     private void sortObservations() {
/* 227 */       WeightedObservedPoint curr = this.observations[0];
/* 228 */       for (int j = 1; j < this.observations.length; j++) {
/* 229 */         WeightedObservedPoint prec = curr;
/* 230 */         curr = this.observations[j];
/* 231 */         if (curr.getX() < prec.getX()) {
/* 233 */           int i = j - 1;
/* 234 */           WeightedObservedPoint mI = this.observations[i];
/* 235 */           while (i >= 0 && curr.getX() < mI.getX()) {
/* 236 */             this.observations[i + 1] = mI;
/* 237 */             if (i-- != 0)
/* 238 */               mI = this.observations[i]; 
/*     */           } 
/* 241 */           this.observations[i + 1] = curr;
/* 242 */           curr = this.observations[j];
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     private void guessAOmega() {
/* 256 */       double sx2 = 0.0D;
/* 257 */       double sy2 = 0.0D;
/* 258 */       double sxy = 0.0D;
/* 259 */       double sxz = 0.0D;
/* 260 */       double syz = 0.0D;
/* 262 */       double currentX = this.observations[0].getX();
/* 263 */       double currentY = this.observations[0].getY();
/* 264 */       double f2Integral = 0.0D;
/* 265 */       double fPrime2Integral = 0.0D;
/* 266 */       double startX = currentX;
/* 267 */       for (int i = 1; i < this.observations.length; i++) {
/* 269 */         double previousX = currentX;
/* 270 */         double previousY = currentY;
/* 271 */         currentX = this.observations[i].getX();
/* 272 */         currentY = this.observations[i].getY();
/* 276 */         double dx = currentX - previousX;
/* 277 */         double dy = currentY - previousY;
/* 278 */         double f2StepIntegral = dx * (previousY * previousY + previousY * currentY + currentY * currentY) / 3.0D;
/* 280 */         double fPrime2StepIntegral = dy * dy / dx;
/* 282 */         double x = currentX - startX;
/* 283 */         f2Integral += f2StepIntegral;
/* 284 */         fPrime2Integral += fPrime2StepIntegral;
/* 286 */         sx2 += x * x;
/* 287 */         sy2 += f2Integral * f2Integral;
/* 288 */         sxy += x * f2Integral;
/* 289 */         sxz += x * fPrime2Integral;
/* 290 */         syz += f2Integral * fPrime2Integral;
/*     */       } 
/* 294 */       double c1 = sy2 * sxz - sxy * syz;
/* 295 */       double c2 = sxy * sxz - sx2 * syz;
/* 296 */       double c3 = sx2 * sy2 - sxy * sxy;
/* 297 */       if (c1 / c2 < 0.0D || c2 / c3 < 0.0D) {
/* 298 */         int last = this.observations.length - 1;
/* 301 */         double xRange = this.observations[last].getX() - this.observations[0].getX();
/* 302 */         if (xRange == 0.0D)
/* 303 */           throw new ZeroException(); 
/* 305 */         this.omega = 6.283185307179586D / xRange;
/* 307 */         double yMin = Double.POSITIVE_INFINITY;
/* 308 */         double yMax = Double.NEGATIVE_INFINITY;
/* 309 */         for (int j = 1; j < this.observations.length; j++) {
/* 310 */           double y = this.observations[j].getY();
/* 311 */           if (y < yMin)
/* 312 */             yMin = y; 
/* 314 */           if (y > yMax)
/* 315 */             yMax = y; 
/*     */         } 
/* 318 */         this.a = 0.5D * (yMax - yMin);
/*     */       } else {
/* 320 */         this.a = FastMath.sqrt(c1 / c2);
/* 321 */         this.omega = FastMath.sqrt(c2 / c3);
/*     */       } 
/*     */     }
/*     */     
/*     */     private void guessPhi() {
/* 330 */       double fcMean = 0.0D;
/* 331 */       double fsMean = 0.0D;
/* 333 */       double currentX = this.observations[0].getX();
/* 334 */       double currentY = this.observations[0].getY();
/* 335 */       for (int i = 1; i < this.observations.length; i++) {
/* 337 */         double previousX = currentX;
/* 338 */         double previousY = currentY;
/* 339 */         currentX = this.observations[i].getX();
/* 340 */         currentY = this.observations[i].getY();
/* 341 */         double currentYPrime = (currentY - previousY) / (currentX - previousX);
/* 343 */         double omegaX = this.omega * currentX;
/* 344 */         double cosine = FastMath.cos(omegaX);
/* 345 */         double sine = FastMath.sin(omegaX);
/* 346 */         fcMean += this.omega * currentY * cosine - currentYPrime * sine;
/* 347 */         fsMean += this.omega * currentY * sine + currentYPrime * cosine;
/*     */       } 
/* 350 */       this.phi = FastMath.atan2(-fsMean, fcMean);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\optimization\fitting\HarmonicFitter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
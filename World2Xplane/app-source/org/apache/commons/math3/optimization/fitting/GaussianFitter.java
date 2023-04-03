/*     */ package org.apache.commons.math3.optimization.fitting;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.Comparator;
/*     */ import org.apache.commons.math3.analysis.ParametricUnivariateFunction;
/*     */ import org.apache.commons.math3.analysis.function.Gaussian;
/*     */ import org.apache.commons.math3.exception.NotStrictlyPositiveException;
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.exception.NumberIsTooSmallException;
/*     */ import org.apache.commons.math3.exception.OutOfRangeException;
/*     */ import org.apache.commons.math3.exception.ZeroException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.optimization.DifferentiableMultivariateVectorOptimizer;
/*     */ 
/*     */ public class GaussianFitter extends CurveFitter {
/*     */   public GaussianFitter(DifferentiableMultivariateVectorOptimizer optimizer) {
/*  68 */     super(optimizer);
/*     */   }
/*     */   
/*     */   public double[] fit(double[] initialGuess) {
/*  85 */     ParametricUnivariateFunction f = new ParametricUnivariateFunction() {
/*  86 */         private final ParametricUnivariateFunction g = (ParametricUnivariateFunction)new Gaussian.Parametric();
/*     */         
/*     */         public double value(double x, double... p) {
/*  89 */           double v = Double.POSITIVE_INFINITY;
/*     */           try {
/*  91 */             v = this.g.value(x, p);
/*  92 */           } catch (NotStrictlyPositiveException e) {}
/*  95 */           return v;
/*     */         }
/*     */         
/*     */         public double[] gradient(double x, double... p) {
/*  99 */           double[] v = { Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY };
/*     */           try {
/* 103 */             v = this.g.gradient(x, p);
/* 104 */           } catch (NotStrictlyPositiveException e) {}
/* 107 */           return v;
/*     */         }
/*     */       };
/* 111 */     return fit(f, initialGuess);
/*     */   }
/*     */   
/*     */   public double[] fit() {
/* 121 */     double[] guess = (new ParameterGuesser(getObservations())).guess();
/* 122 */     return fit(guess);
/*     */   }
/*     */   
/*     */   public static class ParameterGuesser {
/*     */     private final WeightedObservedPoint[] observations;
/*     */     
/*     */     private double[] parameters;
/*     */     
/*     */     public ParameterGuesser(WeightedObservedPoint[] observations) {
/* 142 */       if (observations == null)
/* 143 */         throw new NullArgumentException(LocalizedFormats.INPUT_ARRAY, new Object[0]); 
/* 145 */       if (observations.length < 3)
/* 146 */         throw new NumberIsTooSmallException(Integer.valueOf(observations.length), Integer.valueOf(3), true); 
/* 148 */       this.observations = (WeightedObservedPoint[])observations.clone();
/*     */     }
/*     */     
/*     */     public double[] guess() {
/* 157 */       if (this.parameters == null)
/* 158 */         this.parameters = basicGuess(this.observations); 
/* 160 */       return (double[])this.parameters.clone();
/*     */     }
/*     */     
/*     */     private double[] basicGuess(WeightedObservedPoint[] points) {
/*     */       double d;
/* 170 */       Arrays.sort(points, createWeightedObservedPointComparator());
/* 171 */       double[] params = new double[3];
/* 173 */       int maxYIdx = findMaxY(points);
/* 174 */       params[0] = points[maxYIdx].getY();
/* 175 */       params[1] = points[maxYIdx].getX();
/*     */       try {
/* 179 */         double halfY = params[0] + (params[1] - params[0]) / 2.0D;
/* 180 */         double fwhmX1 = interpolateXAtY(points, maxYIdx, -1, halfY);
/* 181 */         double fwhmX2 = interpolateXAtY(points, maxYIdx, 1, halfY);
/* 182 */         d = fwhmX2 - fwhmX1;
/* 183 */       } catch (OutOfRangeException e) {
/* 184 */         d = points[points.length - 1].getX() - points[0].getX();
/*     */       } 
/* 186 */       params[2] = d / 2.0D * Math.sqrt(2.0D * Math.log(2.0D));
/* 188 */       return params;
/*     */     }
/*     */     
/*     */     private int findMaxY(WeightedObservedPoint[] points) {
/* 198 */       int maxYIdx = 0;
/* 199 */       for (int i = 1; i < points.length; i++) {
/* 200 */         if (points[i].getY() > points[maxYIdx].getY())
/* 201 */           maxYIdx = i; 
/*     */       } 
/* 204 */       return maxYIdx;
/*     */     }
/*     */     
/*     */     private double interpolateXAtY(WeightedObservedPoint[] points, int startIdx, int idxStep, double y) throws OutOfRangeException {
/* 224 */       if (idxStep == 0)
/* 225 */         throw new ZeroException(); 
/* 227 */       WeightedObservedPoint[] twoPoints = getInterpolationPointsForY(points, startIdx, idxStep, y);
/* 228 */       WeightedObservedPoint pointA = twoPoints[0];
/* 229 */       WeightedObservedPoint pointB = twoPoints[1];
/* 230 */       if (pointA.getY() == y)
/* 231 */         return pointA.getX(); 
/* 233 */       if (pointB.getY() == y)
/* 234 */         return pointB.getX(); 
/* 236 */       return pointA.getX() + (y - pointA.getY()) * (pointB.getX() - pointA.getX()) / (pointB.getY() - pointA.getY());
/*     */     }
/*     */     
/*     */     private WeightedObservedPoint[] getInterpolationPointsForY(WeightedObservedPoint[] points, int startIdx, int idxStep, double y) throws OutOfRangeException {
/* 259 */       if (idxStep == 0)
/* 260 */         throw new ZeroException(); 
/* 262 */       int i = startIdx;
/* 263 */       for (; (idxStep < 0) ? (i + idxStep >= 0) : (i + idxStep < points.length); 
/* 264 */         i += idxStep) {
/* 265 */         if (isBetween(y, points[i].getY(), points[i + idxStep].getY())) {
/* 266 */           (new WeightedObservedPoint[2])[0] = points[i + idxStep];
/* 266 */           (new WeightedObservedPoint[2])[1] = points[i];
/* 266 */           (new WeightedObservedPoint[2])[0] = points[i];
/* 266 */           (new WeightedObservedPoint[2])[1] = points[i + idxStep];
/* 266 */           return (idxStep < 0) ? new WeightedObservedPoint[2] : new WeightedObservedPoint[2];
/*     */         } 
/*     */       } 
/* 272 */       double minY = Double.POSITIVE_INFINITY;
/* 273 */       double maxY = Double.NEGATIVE_INFINITY;
/* 274 */       for (WeightedObservedPoint point : points) {
/* 275 */         minY = Math.min(minY, point.getY());
/* 276 */         maxY = Math.max(maxY, point.getY());
/*     */       } 
/* 278 */       throw new OutOfRangeException(Double.valueOf(y), Double.valueOf(minY), Double.valueOf(maxY));
/*     */     }
/*     */     
/*     */     private boolean isBetween(double value, double boundary1, double boundary2) {
/* 292 */       return ((value >= boundary1 && value <= boundary2) || (value >= boundary2 && value <= boundary1));
/*     */     }
/*     */     
/*     */     private Comparator<WeightedObservedPoint> createWeightedObservedPointComparator() {
/* 303 */       return new Comparator<WeightedObservedPoint>() {
/*     */           public int compare(WeightedObservedPoint p1, WeightedObservedPoint p2) {
/* 305 */             if (p1 == null && p2 == null)
/* 306 */               return 0; 
/* 308 */             if (p1 == null)
/* 309 */               return -1; 
/* 311 */             if (p2 == null)
/* 312 */               return 1; 
/* 314 */             if (p1.getX() < p2.getX())
/* 315 */               return -1; 
/* 317 */             if (p1.getX() > p2.getX())
/* 318 */               return 1; 
/* 320 */             if (p1.getY() < p2.getY())
/* 321 */               return -1; 
/* 323 */             if (p1.getY() > p2.getY())
/* 324 */               return 1; 
/* 326 */             if (p1.getWeight() < p2.getWeight())
/* 327 */               return -1; 
/* 329 */             if (p1.getWeight() > p2.getWeight())
/* 330 */               return 1; 
/* 332 */             return 0;
/*     */           }
/*     */         };
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\optimization\fitting\GaussianFitter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
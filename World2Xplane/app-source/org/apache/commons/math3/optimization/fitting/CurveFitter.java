/*     */ package org.apache.commons.math3.optimization.fitting;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.commons.math3.analysis.DifferentiableMultivariateVectorFunction;
/*     */ import org.apache.commons.math3.analysis.MultivariateMatrixFunction;
/*     */ import org.apache.commons.math3.analysis.MultivariateVectorFunction;
/*     */ import org.apache.commons.math3.analysis.ParametricUnivariateFunction;
/*     */ import org.apache.commons.math3.optimization.DifferentiableMultivariateVectorOptimizer;
/*     */ import org.apache.commons.math3.optimization.PointVectorValuePair;
/*     */ 
/*     */ public class CurveFitter {
/*     */   private final DifferentiableMultivariateVectorOptimizer optimizer;
/*     */   
/*     */   private final List<WeightedObservedPoint> observations;
/*     */   
/*     */   public CurveFitter(DifferentiableMultivariateVectorOptimizer optimizer) {
/*  52 */     this.optimizer = optimizer;
/*  53 */     this.observations = new ArrayList<WeightedObservedPoint>();
/*     */   }
/*     */   
/*     */   public void addObservedPoint(double x, double y) {
/*  67 */     addObservedPoint(1.0D, x, y);
/*     */   }
/*     */   
/*     */   public void addObservedPoint(double weight, double x, double y) {
/*  80 */     this.observations.add(new WeightedObservedPoint(weight, x, y));
/*     */   }
/*     */   
/*     */   public void addObservedPoint(WeightedObservedPoint observed) {
/*  90 */     this.observations.add(observed);
/*     */   }
/*     */   
/*     */   public WeightedObservedPoint[] getObservations() {
/* 100 */     return this.observations.<WeightedObservedPoint>toArray(new WeightedObservedPoint[this.observations.size()]);
/*     */   }
/*     */   
/*     */   public void clearObservations() {
/* 107 */     this.observations.clear();
/*     */   }
/*     */   
/*     */   public double[] fit(ParametricUnivariateFunction f, double[] initialGuess) {
/* 124 */     return fit(2147483647, f, initialGuess);
/*     */   }
/*     */   
/*     */   public double[] fit(int maxEval, ParametricUnivariateFunction f, double[] initialGuess) {
/* 147 */     double[] target = new double[this.observations.size()];
/* 148 */     double[] weights = new double[this.observations.size()];
/* 149 */     int i = 0;
/* 150 */     for (WeightedObservedPoint point : this.observations) {
/* 151 */       target[i] = point.getY();
/* 152 */       weights[i] = point.getWeight();
/* 153 */       i++;
/*     */     } 
/* 157 */     PointVectorValuePair optimum = this.optimizer.optimize(maxEval, (MultivariateVectorFunction)new TheoreticalValuesFunction(f), target, weights, initialGuess);
/* 162 */     return optimum.getPointRef();
/*     */   }
/*     */   
/*     */   private class TheoreticalValuesFunction implements DifferentiableMultivariateVectorFunction {
/*     */     private final ParametricUnivariateFunction f;
/*     */     
/*     */     public TheoreticalValuesFunction(ParametricUnivariateFunction f) {
/* 175 */       this.f = f;
/*     */     }
/*     */     
/*     */     public MultivariateMatrixFunction jacobian() {
/* 180 */       return new MultivariateMatrixFunction() {
/*     */           public double[][] value(double[] point) {
/* 182 */             double[][] jacobian = new double[CurveFitter.this.observations.size()][];
/* 184 */             int i = 0;
/* 185 */             for (WeightedObservedPoint observed : CurveFitter.this.observations)
/* 186 */               jacobian[i++] = CurveFitter.TheoreticalValuesFunction.this.f.gradient(observed.getX(), point); 
/* 189 */             return jacobian;
/*     */           }
/*     */         };
/*     */     }
/*     */     
/*     */     public double[] value(double[] point) {
/* 197 */       double[] values = new double[CurveFitter.this.observations.size()];
/* 198 */       int i = 0;
/* 199 */       for (WeightedObservedPoint observed : CurveFitter.this.observations)
/* 200 */         values[i++] = this.f.value(observed.getX(), point); 
/* 203 */       return values;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\optimization\fitting\CurveFitter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
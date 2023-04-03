/*     */ package org.apache.commons.math3.optimization;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.Comparator;
/*     */ import org.apache.commons.math3.analysis.MultivariateVectorFunction;
/*     */ import org.apache.commons.math3.exception.ConvergenceException;
/*     */ import org.apache.commons.math3.exception.MathIllegalStateException;
/*     */ import org.apache.commons.math3.exception.NotStrictlyPositiveException;
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.random.RandomVectorGenerator;
/*     */ 
/*     */ public class BaseMultivariateVectorMultiStartOptimizer<FUNC extends MultivariateVectorFunction> implements BaseMultivariateVectorOptimizer<FUNC> {
/*     */   private final BaseMultivariateVectorOptimizer<FUNC> optimizer;
/*     */   
/*     */   private int maxEvaluations;
/*     */   
/*     */   private int totalEvaluations;
/*     */   
/*     */   private int starts;
/*     */   
/*     */   private RandomVectorGenerator generator;
/*     */   
/*     */   private PointVectorValuePair[] optima;
/*     */   
/*     */   protected BaseMultivariateVectorMultiStartOptimizer(BaseMultivariateVectorOptimizer<FUNC> optimizer, int starts, RandomVectorGenerator generator) {
/*  73 */     if (optimizer == null || generator == null)
/*  75 */       throw new NullArgumentException(); 
/*  77 */     if (starts < 1)
/*  78 */       throw new NotStrictlyPositiveException(Integer.valueOf(starts)); 
/*  81 */     this.optimizer = optimizer;
/*  82 */     this.starts = starts;
/*  83 */     this.generator = generator;
/*     */   }
/*     */   
/*     */   public PointVectorValuePair[] getOptima() {
/* 114 */     if (this.optima == null)
/* 115 */       throw new MathIllegalStateException(LocalizedFormats.NO_OPTIMUM_COMPUTED_YET, new Object[0]); 
/* 117 */     return (PointVectorValuePair[])this.optima.clone();
/*     */   }
/*     */   
/*     */   public int getMaxEvaluations() {
/* 122 */     return this.maxEvaluations;
/*     */   }
/*     */   
/*     */   public int getEvaluations() {
/* 127 */     return this.totalEvaluations;
/*     */   }
/*     */   
/*     */   public ConvergenceChecker<PointVectorValuePair> getConvergenceChecker() {
/* 132 */     return this.optimizer.getConvergenceChecker();
/*     */   }
/*     */   
/*     */   public PointVectorValuePair optimize(int maxEval, FUNC f, double[] target, double[] weights, double[] startPoint) {
/* 141 */     this.maxEvaluations = maxEval;
/* 142 */     RuntimeException lastException = null;
/* 143 */     this.optima = new PointVectorValuePair[this.starts];
/* 144 */     this.totalEvaluations = 0;
/* 147 */     for (int i = 0; i < this.starts; i++) {
/*     */       try {
/* 151 */         this.optima[i] = this.optimizer.optimize(maxEval - this.totalEvaluations, f, target, weights, (i == 0) ? startPoint : this.generator.nextVector());
/* 153 */       } catch (ConvergenceException oe) {
/* 154 */         this.optima[i] = null;
/* 155 */       } catch (RuntimeException mue) {
/* 156 */         lastException = mue;
/* 157 */         this.optima[i] = null;
/*     */       } 
/* 161 */       this.totalEvaluations += this.optimizer.getEvaluations();
/*     */     } 
/* 164 */     sortPairs(target, weights);
/* 166 */     if (this.optima[0] == null)
/* 167 */       throw lastException; 
/* 171 */     return this.optima[0];
/*     */   }
/*     */   
/*     */   private void sortPairs(final double[] target, final double[] weights) {
/* 182 */     Arrays.sort(this.optima, new Comparator<PointVectorValuePair>() {
/*     */           public int compare(PointVectorValuePair o1, PointVectorValuePair o2) {
/* 185 */             if (o1 == null)
/* 186 */               return (o2 == null) ? 0 : 1; 
/* 187 */             if (o2 == null)
/* 188 */               return -1; 
/* 190 */             return Double.compare(weightedResidual(o1), weightedResidual(o2));
/*     */           }
/*     */           
/*     */           private double weightedResidual(PointVectorValuePair pv) {
/* 193 */             double[] value = pv.getValueRef();
/* 194 */             double sum = 0.0D;
/* 195 */             for (int i = 0; i < value.length; i++) {
/* 196 */               double ri = value[i] - target[i];
/* 197 */               sum += weights[i] * ri * ri;
/*     */             } 
/* 199 */             return sum;
/*     */           }
/*     */         });
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\optimization\BaseMultivariateVectorMultiStartOptimizer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
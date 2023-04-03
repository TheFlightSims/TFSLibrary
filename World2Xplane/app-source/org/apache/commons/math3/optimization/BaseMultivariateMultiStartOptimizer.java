/*     */ package org.apache.commons.math3.optimization;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.Comparator;
/*     */ import org.apache.commons.math3.analysis.MultivariateFunction;
/*     */ import org.apache.commons.math3.exception.MathIllegalStateException;
/*     */ import org.apache.commons.math3.exception.NotStrictlyPositiveException;
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.random.RandomVectorGenerator;
/*     */ 
/*     */ public class BaseMultivariateMultiStartOptimizer<FUNC extends MultivariateFunction> implements BaseMultivariateOptimizer<FUNC> {
/*     */   private final BaseMultivariateOptimizer<FUNC> optimizer;
/*     */   
/*     */   private int maxEvaluations;
/*     */   
/*     */   private int totalEvaluations;
/*     */   
/*     */   private int starts;
/*     */   
/*     */   private RandomVectorGenerator generator;
/*     */   
/*     */   private PointValuePair[] optima;
/*     */   
/*     */   protected BaseMultivariateMultiStartOptimizer(BaseMultivariateOptimizer<FUNC> optimizer, int starts, RandomVectorGenerator generator) {
/*  73 */     if (optimizer == null || generator == null)
/*  75 */       throw new NullArgumentException(); 
/*  77 */     if (starts < 1)
/*  78 */       throw new NotStrictlyPositiveException(Integer.valueOf(starts)); 
/*  81 */     this.optimizer = optimizer;
/*  82 */     this.starts = starts;
/*  83 */     this.generator = generator;
/*     */   }
/*     */   
/*     */   public PointValuePair[] getOptima() {
/* 113 */     if (this.optima == null)
/* 114 */       throw new MathIllegalStateException(LocalizedFormats.NO_OPTIMUM_COMPUTED_YET, new Object[0]); 
/* 116 */     return (PointValuePair[])this.optima.clone();
/*     */   }
/*     */   
/*     */   public int getMaxEvaluations() {
/* 121 */     return this.maxEvaluations;
/*     */   }
/*     */   
/*     */   public int getEvaluations() {
/* 126 */     return this.totalEvaluations;
/*     */   }
/*     */   
/*     */   public ConvergenceChecker<PointValuePair> getConvergenceChecker() {
/* 131 */     return this.optimizer.getConvergenceChecker();
/*     */   }
/*     */   
/*     */   public PointValuePair optimize(int maxEval, FUNC f, GoalType goal, double[] startPoint) {
/* 140 */     this.maxEvaluations = maxEval;
/* 141 */     RuntimeException lastException = null;
/* 142 */     this.optima = new PointValuePair[this.starts];
/* 143 */     this.totalEvaluations = 0;
/* 146 */     for (int i = 0; i < this.starts; i++) {
/*     */       try {
/* 149 */         this.optima[i] = this.optimizer.optimize(maxEval - this.totalEvaluations, f, goal, (i == 0) ? startPoint : this.generator.nextVector());
/* 151 */       } catch (RuntimeException mue) {
/* 152 */         lastException = mue;
/* 153 */         this.optima[i] = null;
/*     */       } 
/* 157 */       this.totalEvaluations += this.optimizer.getEvaluations();
/*     */     } 
/* 160 */     sortPairs(goal);
/* 162 */     if (this.optima[0] == null)
/* 163 */       throw lastException; 
/* 167 */     return this.optima[0];
/*     */   }
/*     */   
/*     */   private void sortPairs(final GoalType goal) {
/* 176 */     Arrays.sort(this.optima, new Comparator<PointValuePair>() {
/*     */           public int compare(PointValuePair o1, PointValuePair o2) {
/* 179 */             if (o1 == null)
/* 180 */               return (o2 == null) ? 0 : 1; 
/* 181 */             if (o2 == null)
/* 182 */               return -1; 
/* 184 */             double v1 = ((Double)o1.getValue()).doubleValue();
/* 185 */             double v2 = ((Double)o2.getValue()).doubleValue();
/* 186 */             return (goal == GoalType.MINIMIZE) ? Double.compare(v1, v2) : Double.compare(v2, v1);
/*     */           }
/*     */         });
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\optimization\BaseMultivariateMultiStartOptimizer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package org.apache.commons.math3.optimization.univariate;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.Comparator;
/*     */ import org.apache.commons.math3.analysis.UnivariateFunction;
/*     */ import org.apache.commons.math3.exception.MathIllegalStateException;
/*     */ import org.apache.commons.math3.exception.NotStrictlyPositiveException;
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.optimization.ConvergenceChecker;
/*     */ import org.apache.commons.math3.optimization.GoalType;
/*     */ import org.apache.commons.math3.random.RandomGenerator;
/*     */ 
/*     */ public class UnivariateMultiStartOptimizer<FUNC extends UnivariateFunction> implements BaseUnivariateOptimizer<FUNC> {
/*     */   private final BaseUnivariateOptimizer<FUNC> optimizer;
/*     */   
/*     */   private int maxEvaluations;
/*     */   
/*     */   private int totalEvaluations;
/*     */   
/*     */   private int starts;
/*     */   
/*     */   private RandomGenerator generator;
/*     */   
/*     */   private UnivariatePointValuePair[] optima;
/*     */   
/*     */   public UnivariateMultiStartOptimizer(BaseUnivariateOptimizer<FUNC> optimizer, int starts, RandomGenerator generator) {
/*  75 */     if (optimizer == null || generator == null)
/*  77 */       throw new NullArgumentException(); 
/*  79 */     if (starts < 1)
/*  80 */       throw new NotStrictlyPositiveException(Integer.valueOf(starts)); 
/*  83 */     this.optimizer = optimizer;
/*  84 */     this.starts = starts;
/*  85 */     this.generator = generator;
/*     */   }
/*     */   
/*     */   public ConvergenceChecker<UnivariatePointValuePair> getConvergenceChecker() {
/*  92 */     return this.optimizer.getConvergenceChecker();
/*     */   }
/*     */   
/*     */   public int getMaxEvaluations() {
/*  97 */     return this.maxEvaluations;
/*     */   }
/*     */   
/*     */   public int getEvaluations() {
/* 102 */     return this.totalEvaluations;
/*     */   }
/*     */   
/*     */   public UnivariatePointValuePair[] getOptima() {
/* 133 */     if (this.optima == null)
/* 134 */       throw new MathIllegalStateException(LocalizedFormats.NO_OPTIMUM_COMPUTED_YET, new Object[0]); 
/* 136 */     return (UnivariatePointValuePair[])this.optima.clone();
/*     */   }
/*     */   
/*     */   public UnivariatePointValuePair optimize(int maxEval, FUNC f, GoalType goal, double min, double max) {
/* 143 */     return optimize(maxEval, f, goal, min, max, min + 0.5D * (max - min));
/*     */   }
/*     */   
/*     */   public UnivariatePointValuePair optimize(int maxEval, FUNC f, GoalType goal, double min, double max, double startValue) {
/* 151 */     RuntimeException lastException = null;
/* 152 */     this.optima = new UnivariatePointValuePair[this.starts];
/* 153 */     this.totalEvaluations = 0;
/* 156 */     for (int i = 0; i < this.starts; i++) {
/*     */       try {
/* 159 */         double s = (i == 0) ? startValue : (min + this.generator.nextDouble() * (max - min));
/* 160 */         this.optima[i] = this.optimizer.optimize(maxEval - this.totalEvaluations, f, goal, min, max, s);
/* 161 */       } catch (RuntimeException mue) {
/* 162 */         lastException = mue;
/* 163 */         this.optima[i] = null;
/*     */       } 
/* 167 */       this.totalEvaluations += this.optimizer.getEvaluations();
/*     */     } 
/* 170 */     sortPairs(goal);
/* 172 */     if (this.optima[0] == null)
/* 173 */       throw lastException; 
/* 177 */     return this.optima[0];
/*     */   }
/*     */   
/*     */   private void sortPairs(final GoalType goal) {
/* 186 */     Arrays.sort(this.optima, new Comparator<UnivariatePointValuePair>() {
/*     */           public int compare(UnivariatePointValuePair o1, UnivariatePointValuePair o2) {
/* 189 */             if (o1 == null)
/* 190 */               return (o2 == null) ? 0 : 1; 
/* 191 */             if (o2 == null)
/* 192 */               return -1; 
/* 194 */             double v1 = o1.getValue();
/* 195 */             double v2 = o2.getValue();
/* 196 */             return (goal == GoalType.MINIMIZE) ? Double.compare(v1, v2) : Double.compare(v2, v1);
/*     */           }
/*     */         });
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\optimizatio\\univariate\UnivariateMultiStartOptimizer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
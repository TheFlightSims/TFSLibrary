/*     */ package org.apache.commons.math3.optimization.direct;
/*     */ 
/*     */ import java.util.Comparator;
/*     */ import org.apache.commons.math3.analysis.MultivariateFunction;
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.optimization.ConvergenceChecker;
/*     */ import org.apache.commons.math3.optimization.GoalType;
/*     */ import org.apache.commons.math3.optimization.MultivariateOptimizer;
/*     */ import org.apache.commons.math3.optimization.PointValuePair;
/*     */ import org.apache.commons.math3.optimization.SimpleValueChecker;
/*     */ 
/*     */ public class SimplexOptimizer extends BaseAbstractMultivariateOptimizer<MultivariateFunction> implements MultivariateOptimizer {
/*     */   private AbstractSimplex simplex;
/*     */   
/*     */   public SimplexOptimizer() {
/*  97 */     this((ConvergenceChecker<PointValuePair>)new SimpleValueChecker());
/*     */   }
/*     */   
/*     */   public SimplexOptimizer(ConvergenceChecker<PointValuePair> checker) {
/* 104 */     super(checker);
/*     */   }
/*     */   
/*     */   public SimplexOptimizer(double rel, double abs) {
/* 112 */     this((ConvergenceChecker<PointValuePair>)new SimpleValueChecker(rel, abs));
/*     */   }
/*     */   
/*     */   public void setSimplex(AbstractSimplex simplex) {
/* 121 */     this.simplex = simplex;
/*     */   }
/*     */   
/*     */   protected PointValuePair doOptimize() {
/* 127 */     if (this.simplex == null)
/* 128 */       throw new NullArgumentException(); 
/* 133 */     MultivariateFunction evalFunc = new MultivariateFunction() {
/*     */         public double value(double[] point) {
/* 136 */           return SimplexOptimizer.this.computeObjectiveValue(point);
/*     */         }
/*     */       };
/* 140 */     final boolean isMinim = (getGoalType() == GoalType.MINIMIZE);
/* 141 */     Comparator<PointValuePair> comparator = new Comparator<PointValuePair>() {
/*     */         public int compare(PointValuePair o1, PointValuePair o2) {
/* 145 */           double v1 = ((Double)o1.getValue()).doubleValue();
/* 146 */           double v2 = ((Double)o2.getValue()).doubleValue();
/* 147 */           return isMinim ? Double.compare(v1, v2) : Double.compare(v2, v1);
/*     */         }
/*     */       };
/* 152 */     this.simplex.build(getStartPoint());
/* 153 */     this.simplex.evaluate(evalFunc, comparator);
/* 155 */     PointValuePair[] previous = null;
/* 156 */     int iteration = 0;
/* 157 */     ConvergenceChecker<PointValuePair> checker = getConvergenceChecker();
/*     */     while (true) {
/* 159 */       if (iteration > 0) {
/* 160 */         boolean converged = true;
/* 161 */         for (int i = 0; i < this.simplex.getSize(); i++) {
/* 162 */           PointValuePair prev = previous[i];
/* 163 */           converged &= checker.converged(iteration, prev, this.simplex.getPoint(i));
/*     */         } 
/* 165 */         if (converged)
/* 167 */           return this.simplex.getPoint(0); 
/*     */       } 
/* 172 */       previous = this.simplex.getPoints();
/* 173 */       this.simplex.iterate(evalFunc, comparator);
/* 174 */       iteration++;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\optimization\direct\SimplexOptimizer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
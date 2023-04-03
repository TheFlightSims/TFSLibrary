/*     */ package org.apache.commons.math3.analysis.solvers;
/*     */ 
/*     */ import org.apache.commons.math3.analysis.UnivariateFunction;
/*     */ import org.apache.commons.math3.exception.ConvergenceException;
/*     */ import org.apache.commons.math3.exception.MathInternalError;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public abstract class BaseSecantSolver extends AbstractUnivariateSolver implements BracketedUnivariateSolver<UnivariateFunction> {
/*     */   protected static final double DEFAULT_ABSOLUTE_ACCURACY = 1.0E-6D;
/*     */   
/*     */   private AllowedSolution allowed;
/*     */   
/*     */   private final Method method;
/*     */   
/*     */   protected BaseSecantSolver(double absoluteAccuracy, Method method) {
/*  69 */     super(absoluteAccuracy);
/*  70 */     this.allowed = AllowedSolution.ANY_SIDE;
/*  71 */     this.method = method;
/*     */   }
/*     */   
/*     */   protected BaseSecantSolver(double relativeAccuracy, double absoluteAccuracy, Method method) {
/*  84 */     super(relativeAccuracy, absoluteAccuracy);
/*  85 */     this.allowed = AllowedSolution.ANY_SIDE;
/*  86 */     this.method = method;
/*     */   }
/*     */   
/*     */   protected BaseSecantSolver(double relativeAccuracy, double absoluteAccuracy, double functionValueAccuracy, Method method) {
/* 101 */     super(relativeAccuracy, absoluteAccuracy, functionValueAccuracy);
/* 102 */     this.allowed = AllowedSolution.ANY_SIDE;
/* 103 */     this.method = method;
/*     */   }
/*     */   
/*     */   public double solve(int maxEval, UnivariateFunction f, double min, double max, AllowedSolution allowedSolution) {
/* 110 */     return solve(maxEval, f, min, max, min + 0.5D * (max - min), allowedSolution);
/*     */   }
/*     */   
/*     */   public double solve(int maxEval, UnivariateFunction f, double min, double max, double startValue, AllowedSolution allowedSolution) {
/* 117 */     this.allowed = allowedSolution;
/* 118 */     return super.solve(maxEval, f, min, max, startValue);
/*     */   }
/*     */   
/*     */   public double solve(int maxEval, UnivariateFunction f, double min, double max, double startValue) {
/* 125 */     return solve(maxEval, f, min, max, startValue, AllowedSolution.ANY_SIDE);
/*     */   }
/*     */   
/*     */   protected final double doSolve() {
/* 132 */     double x0 = getMin();
/* 133 */     double x1 = getMax();
/* 134 */     double f0 = computeObjectiveValue(x0);
/* 135 */     double f1 = computeObjectiveValue(x1);
/* 140 */     if (f0 == 0.0D)
/* 141 */       return x0; 
/* 143 */     if (f1 == 0.0D)
/* 144 */       return x1; 
/* 148 */     verifyBracketing(x0, x1);
/* 151 */     double ftol = getFunctionValueAccuracy();
/* 152 */     double atol = getAbsoluteAccuracy();
/* 153 */     double rtol = getRelativeAccuracy();
/* 157 */     boolean inverted = false;
/*     */     do {
/* 162 */       double x = x1 - f1 * (x1 - x0) / (f1 - f0);
/* 163 */       double fx = computeObjectiveValue(x);
/* 168 */       if (fx == 0.0D)
/* 169 */         return x; 
/* 173 */       if (f1 * fx < 0.0D) {
/* 176 */         x0 = x1;
/* 177 */         f0 = f1;
/* 178 */         inverted = !inverted;
/*     */       } else {
/* 180 */         switch (this.method) {
/*     */           case ANY_SIDE:
/* 182 */             f0 *= 0.5D;
/*     */             break;
/*     */           case LEFT_SIDE:
/* 185 */             f0 *= f1 / (f1 + fx);
/*     */             break;
/*     */           case RIGHT_SIDE:
/* 190 */             if (x == x1)
/* 191 */               throw new ConvergenceException(); 
/*     */             break;
/*     */           default:
/* 196 */             throw new MathInternalError();
/*     */         } 
/*     */       } 
/* 200 */       x1 = x;
/* 201 */       f1 = fx;
/* 206 */       if (FastMath.abs(f1) > ftol)
/*     */         continue; 
/* 207 */       switch (this.allowed) {
/*     */         case ANY_SIDE:
/* 209 */           return x1;
/*     */         case LEFT_SIDE:
/* 211 */           if (inverted)
/* 212 */             return x1; 
/*     */           break;
/*     */         case RIGHT_SIDE:
/* 216 */           if (!inverted)
/* 217 */             return x1; 
/*     */           break;
/*     */         case BELOW_SIDE:
/* 221 */           if (f1 <= 0.0D)
/* 222 */             return x1; 
/*     */           break;
/*     */         case ABOVE_SIDE:
/* 226 */           if (f1 >= 0.0D)
/* 227 */             return x1; 
/*     */           break;
/*     */         default:
/* 231 */           throw new MathInternalError();
/*     */       } 
/* 237 */     } while (FastMath.abs(x1 - x0) >= FastMath.max(rtol * FastMath.abs(x1), atol));
/* 239 */     switch (this.allowed) {
/*     */       case ANY_SIDE:
/* 241 */         return x1;
/*     */       case LEFT_SIDE:
/* 243 */         return inverted ? x1 : x0;
/*     */       case RIGHT_SIDE:
/* 245 */         return inverted ? x0 : x1;
/*     */       case BELOW_SIDE:
/* 247 */         return (f1 <= 0.0D) ? x1 : x0;
/*     */       case ABOVE_SIDE:
/* 249 */         return (f1 >= 0.0D) ? x1 : x0;
/*     */     } 
/* 251 */     throw new MathInternalError();
/*     */   }
/*     */   
/*     */   protected enum Method {
/* 264 */     REGULA_FALSI, ILLINOIS, PEGASUS;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\analysis\solvers\BaseSecantSolver.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
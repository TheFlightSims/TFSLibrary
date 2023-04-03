/*     */ package org.apache.commons.math3.linear;
/*     */ 
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ import org.apache.commons.math3.exception.MaxCountExceededException;
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.exception.util.ExceptionContext;
/*     */ import org.apache.commons.math3.util.IterationManager;
/*     */ 
/*     */ public class ConjugateGradient extends PreconditionedIterativeLinearSolver {
/*     */   public static final String OPERATOR = "operator";
/*     */   
/*     */   public static final String VECTOR = "vector";
/*     */   
/*     */   private boolean check;
/*     */   
/*     */   private final double delta;
/*     */   
/*     */   public ConjugateGradient(int maxIterations, double delta, boolean check) {
/* 108 */     super(maxIterations);
/* 109 */     this.delta = delta;
/* 110 */     this.check = check;
/*     */   }
/*     */   
/*     */   public ConjugateGradient(IterationManager manager, double delta, boolean check) {
/* 124 */     super(manager);
/* 125 */     this.delta = delta;
/* 126 */     this.check = check;
/*     */   }
/*     */   
/*     */   public final boolean getCheck() {
/* 136 */     return this.check;
/*     */   }
/*     */   
/*     */   public RealVector solveInPlace(RealLinearOperator a, RealLinearOperator minv, RealVector b, RealVector x0) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, MaxCountExceededException {
/* 145 */     checkParameters(a, minv, b, x0);
/* 146 */     IterationManager manager = getIterationManager();
/* 148 */     manager.resetIterationCount();
/* 149 */     double rmax = this.delta * b.getNorm();
/* 150 */     RealVector bro = RealVector.unmodifiableRealVector(b);
/* 153 */     manager.incrementIterationCount();
/* 157 */     RealVector x = x0;
/* 158 */     RealVector xro = RealVector.unmodifiableRealVector(x);
/* 159 */     RealVector p = x.copy();
/* 160 */     RealVector q = a.operate(p);
/* 162 */     RealVector r = b.combine(1.0D, -1.0D, q);
/* 163 */     RealVector rro = RealVector.unmodifiableRealVector(r);
/* 164 */     double rnorm = r.getNorm();
/* 166 */     if (minv == null) {
/* 167 */       RealVector z = r;
/*     */     } else {
/* 169 */       RealVector z = null;
/*     */     } 
/* 172 */     IterativeLinearSolverEvent evt = new DefaultIterativeLinearSolverEvent(this, manager.getIterations(), xro, bro, rro, rnorm);
/* 174 */     manager.fireInitializationEvent(evt);
/* 175 */     if (rnorm <= rmax) {
/* 176 */       manager.fireTerminationEvent(evt);
/* 177 */       return x;
/*     */     } 
/* 179 */     double rhoPrev = 0.0D;
/*     */     while (true) {
/*     */       RealVector realVector;
/* 181 */       manager.incrementIterationCount();
/* 182 */       evt = new DefaultIterativeLinearSolverEvent(this, manager.getIterations(), xro, bro, rro, rnorm);
/* 184 */       manager.fireIterationStartedEvent(evt);
/* 185 */       if (minv != null)
/* 186 */         realVector = minv.operate(r); 
/* 188 */       double rhoNext = r.dotProduct(realVector);
/* 189 */       if (this.check && rhoNext <= 0.0D) {
/* 191 */         NonPositiveDefiniteOperatorException e = new NonPositiveDefiniteOperatorException();
/* 192 */         ExceptionContext context = e.getContext();
/* 193 */         context.setValue("operator", minv);
/* 194 */         context.setValue("vector", r);
/* 195 */         throw e;
/*     */       } 
/* 197 */       if (manager.getIterations() == 2) {
/* 198 */         p.setSubVector(0, realVector);
/*     */       } else {
/* 200 */         p.combineToSelf(rhoNext / rhoPrev, 1.0D, realVector);
/*     */       } 
/* 202 */       q = a.operate(p);
/* 203 */       double pq = p.dotProduct(q);
/* 204 */       if (this.check && pq <= 0.0D) {
/* 206 */         NonPositiveDefiniteOperatorException e = new NonPositiveDefiniteOperatorException();
/* 207 */         ExceptionContext context = e.getContext();
/* 208 */         context.setValue("operator", a);
/* 209 */         context.setValue("vector", p);
/* 210 */         throw e;
/*     */       } 
/* 212 */       double alpha = rhoNext / pq;
/* 213 */       x.combineToSelf(1.0D, alpha, p);
/* 214 */       r.combineToSelf(1.0D, -alpha, q);
/* 215 */       rhoPrev = rhoNext;
/* 216 */       rnorm = r.getNorm();
/* 217 */       evt = new DefaultIterativeLinearSolverEvent(this, manager.getIterations(), xro, bro, rro, rnorm);
/* 219 */       manager.fireIterationPerformedEvent(evt);
/* 220 */       if (rnorm <= rmax) {
/* 221 */         manager.fireTerminationEvent(evt);
/* 222 */         return x;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\linear\ConjugateGradient.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
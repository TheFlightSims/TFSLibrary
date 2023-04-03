/*     */ package org.apache.commons.math3.linear;
/*     */ 
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ import org.apache.commons.math3.exception.MaxCountExceededException;
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.util.IterationManager;
/*     */ import org.apache.commons.math3.util.MathUtils;
/*     */ 
/*     */ public abstract class IterativeLinearSolver {
/*     */   private final IterationManager manager;
/*     */   
/*     */   public IterativeLinearSolver(int maxIterations) {
/*  45 */     this.manager = new IterationManager(maxIterations);
/*     */   }
/*     */   
/*     */   public IterativeLinearSolver(IterationManager manager) throws NullArgumentException {
/*  56 */     MathUtils.checkNotNull(manager);
/*  57 */     this.manager = manager;
/*     */   }
/*     */   
/*     */   protected static void checkParameters(RealLinearOperator a, RealVector b, RealVector x0) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException {
/*  78 */     MathUtils.checkNotNull(a);
/*  79 */     MathUtils.checkNotNull(b);
/*  80 */     MathUtils.checkNotNull(x0);
/*  81 */     if (a.getRowDimension() != a.getColumnDimension())
/*  82 */       throw new NonSquareOperatorException(a.getRowDimension(), a.getColumnDimension()); 
/*  85 */     if (b.getDimension() != a.getRowDimension())
/*  86 */       throw new DimensionMismatchException(b.getDimension(), a.getRowDimension()); 
/*  89 */     if (x0.getDimension() != a.getColumnDimension())
/*  90 */       throw new DimensionMismatchException(x0.getDimension(), a.getColumnDimension()); 
/*     */   }
/*     */   
/*     */   public IterationManager getIterationManager() {
/* 101 */     return this.manager;
/*     */   }
/*     */   
/*     */   public RealVector solve(RealLinearOperator a, RealVector b) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, MaxCountExceededException {
/* 123 */     MathUtils.checkNotNull(a);
/* 124 */     RealVector x = new ArrayRealVector(a.getColumnDimension());
/* 125 */     x.set(0.0D);
/* 126 */     return solveInPlace(a, b, x);
/*     */   }
/*     */   
/*     */   public RealVector solve(RealLinearOperator a, RealVector b, RealVector x0) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, MaxCountExceededException {
/* 149 */     MathUtils.checkNotNull(x0);
/* 150 */     return solveInPlace(a, b, x0.copy());
/*     */   }
/*     */   
/*     */   public abstract RealVector solveInPlace(RealLinearOperator paramRealLinearOperator, RealVector paramRealVector1, RealVector paramRealVector2) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, MaxCountExceededException;
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\linear\IterativeLinearSolver.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
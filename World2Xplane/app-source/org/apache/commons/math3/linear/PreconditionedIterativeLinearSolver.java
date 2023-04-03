/*     */ package org.apache.commons.math3.linear;
/*     */ 
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ import org.apache.commons.math3.exception.MaxCountExceededException;
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.util.IterationManager;
/*     */ import org.apache.commons.math3.util.MathUtils;
/*     */ 
/*     */ public abstract class PreconditionedIterativeLinearSolver extends IterativeLinearSolver {
/*     */   public PreconditionedIterativeLinearSolver(int maxIterations) {
/*  52 */     super(maxIterations);
/*     */   }
/*     */   
/*     */   public PreconditionedIterativeLinearSolver(IterationManager manager) throws NullArgumentException {
/*  63 */     super(manager);
/*     */   }
/*     */   
/*     */   public RealVector solve(RealLinearOperator a, RealLinearOperator minv, RealVector b, RealVector x0) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, MaxCountExceededException {
/*  90 */     MathUtils.checkNotNull(x0);
/*  91 */     return solveInPlace(a, minv, b, x0.copy());
/*     */   }
/*     */   
/*     */   public RealVector solve(RealLinearOperator a, RealVector b) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, MaxCountExceededException {
/*  99 */     MathUtils.checkNotNull(a);
/* 100 */     RealVector x = new ArrayRealVector(a.getColumnDimension());
/* 101 */     x.set(0.0D);
/* 102 */     return solveInPlace(a, null, b, x);
/*     */   }
/*     */   
/*     */   public RealVector solve(RealLinearOperator a, RealVector b, RealVector x0) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, MaxCountExceededException {
/* 111 */     MathUtils.checkNotNull(x0);
/* 112 */     return solveInPlace(a, null, b, x0.copy());
/*     */   }
/*     */   
/*     */   protected static void checkParameters(RealLinearOperator a, RealLinearOperator minv, RealVector b, RealVector x0) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException {
/* 137 */     checkParameters(a, b, x0);
/* 138 */     if (minv != null) {
/* 139 */       if (minv.getColumnDimension() != minv.getRowDimension())
/* 140 */         throw new NonSquareOperatorException(minv.getColumnDimension(), minv.getRowDimension()); 
/* 143 */       if (minv.getRowDimension() != a.getRowDimension())
/* 144 */         throw new DimensionMismatchException(minv.getRowDimension(), a.getRowDimension()); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public RealVector solve(RealLinearOperator a, RealLinearOperator minv, RealVector b) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, MaxCountExceededException {
/* 172 */     MathUtils.checkNotNull(a);
/* 173 */     RealVector x = new ArrayRealVector(a.getColumnDimension());
/* 174 */     return solveInPlace(a, minv, b, x);
/*     */   }
/*     */   
/*     */   public abstract RealVector solveInPlace(RealLinearOperator paramRealLinearOperator1, RealLinearOperator paramRealLinearOperator2, RealVector paramRealVector1, RealVector paramRealVector2) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, MaxCountExceededException;
/*     */   
/*     */   public RealVector solveInPlace(RealLinearOperator a, RealVector b, RealVector x0) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, MaxCountExceededException {
/* 209 */     return solveInPlace(a, (RealLinearOperator)null, b, x0);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\linear\PreconditionedIterativeLinearSolver.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
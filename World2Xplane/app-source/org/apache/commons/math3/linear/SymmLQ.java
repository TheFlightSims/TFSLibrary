/*      */ package org.apache.commons.math3.linear;
/*      */ 
/*      */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*      */ import org.apache.commons.math3.exception.MaxCountExceededException;
/*      */ import org.apache.commons.math3.exception.NullArgumentException;
/*      */ import org.apache.commons.math3.exception.util.ExceptionContext;
/*      */ import org.apache.commons.math3.util.FastMath;
/*      */ import org.apache.commons.math3.util.IterationManager;
/*      */ import org.apache.commons.math3.util.MathUtils;
/*      */ 
/*      */ public class SymmLQ extends PreconditionedIterativeLinearSolver {
/*      */   private static final double CBRT_MACH_PREC;
/*      */   
/*      */   private class State {
/*      */     private final RealLinearOperator a;
/*      */     
/*      */     private final RealVector b;
/*      */     
/*      */     private double beta;
/*      */     
/*      */     private double beta1;
/*      */     
/*      */     private double bstep;
/*      */     
/*      */     private double cgnorm;
/*      */     
/*      */     private double dbar;
/*      */     
/*      */     private double gammaZeta;
/*      */     
/*      */     private double gbar;
/*      */     
/*      */     private double gmax;
/*      */     
/*      */     private double gmin;
/*      */     
/*      */     private final boolean goodb;
/*      */     
/*      */     private boolean hasConverged;
/*      */     
/*      */     private double lqnorm;
/*      */     
/*      */     private final RealLinearOperator minv;
/*      */     
/*      */     private double minusEpsZeta;
/*      */     
/*      */     private final RealVector minvb;
/*      */     
/*      */     private double oldb;
/*      */     
/*      */     private RealVector r1;
/*      */     
/*      */     private RealVector r2;
/*      */     
/*      */     private final double shift;
/*      */     
/*      */     private double snprod;
/*      */     
/*      */     private double tnorm;
/*      */     
/*      */     private RealVector wbar;
/*      */     
/*      */     private final RealVector x;
/*      */     
/*      */     private RealVector y;
/*      */     
/*      */     private double ynorm2;
/*      */     
/*      */     public State(RealLinearOperator a, RealLinearOperator minv, RealVector b, RealVector x, boolean goodb, double shift) {
/*  345 */       this.a = a;
/*  346 */       this.minv = minv;
/*  347 */       this.b = b;
/*  348 */       this.x = x;
/*  349 */       this.goodb = goodb;
/*  350 */       this.shift = shift;
/*  351 */       this.minvb = (minv == null) ? b : minv.operate(b);
/*  352 */       this.hasConverged = false;
/*  353 */       init();
/*      */     }
/*      */     
/*      */     public void refine(RealVector xRefined) {
/*  364 */       int n = this.x.getDimension();
/*  365 */       if (this.lqnorm < this.cgnorm) {
/*  366 */         if (!this.goodb) {
/*  367 */           xRefined.setSubVector(0, this.x);
/*      */         } else {
/*  369 */           double step = this.bstep / this.beta1;
/*  370 */           for (int i = 0; i < n; i++) {
/*  371 */             double bi = this.minvb.getEntry(i);
/*  372 */             double xi = this.x.getEntry(i);
/*  373 */             xRefined.setEntry(i, xi + step * bi);
/*      */           } 
/*      */         } 
/*      */       } else {
/*  377 */         double anorm = FastMath.sqrt(this.tnorm);
/*  378 */         double diag = (this.gbar == 0.0D) ? (anorm * SymmLQ.MACH_PREC) : this.gbar;
/*  379 */         double zbar = this.gammaZeta / diag;
/*  380 */         double step = (this.bstep + this.snprod * zbar) / this.beta1;
/*  382 */         if (!this.goodb) {
/*  383 */           for (int i = 0; i < n; i++) {
/*  384 */             double xi = this.x.getEntry(i);
/*  385 */             double wi = this.wbar.getEntry(i);
/*  386 */             xRefined.setEntry(i, xi + zbar * wi);
/*      */           } 
/*      */         } else {
/*  389 */           for (int i = 0; i < n; i++) {
/*  390 */             double xi = this.x.getEntry(i);
/*  391 */             double wi = this.wbar.getEntry(i);
/*  392 */             double bi = this.minvb.getEntry(i);
/*  393 */             xRefined.setEntry(i, xi + zbar * wi + step * bi);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/*      */     private void init() {
/*  405 */       this.x.set(0.0D);
/*  410 */       this.r1 = this.b.copy();
/*  411 */       this.y = (this.minv == null) ? this.b.copy() : this.minv.operate(this.r1);
/*  412 */       if (this.minv != null && SymmLQ.this.check)
/*  413 */         SymmLQ.checkSymmetry(this.minv, this.r1, this.y, this.minv.operate(this.y)); 
/*  416 */       this.beta1 = this.r1.dotProduct(this.y);
/*  417 */       if (this.beta1 < 0.0D)
/*  418 */         SymmLQ.throwNPDLOException(this.minv, this.y); 
/*  420 */       if (this.beta1 == 0.0D)
/*      */         return; 
/*  424 */       this.beta1 = FastMath.sqrt(this.beta1);
/*  430 */       RealVector v = this.y.mapMultiply(1.0D / this.beta1);
/*  431 */       this.y = this.a.operate(v);
/*  432 */       if (SymmLQ.this.check)
/*  433 */         SymmLQ.checkSymmetry(this.a, v, this.y, this.a.operate(this.y)); 
/*  439 */       SymmLQ.daxpy(-this.shift, v, this.y);
/*  440 */       double alpha = v.dotProduct(this.y);
/*  441 */       SymmLQ.daxpy(-alpha / this.beta1, this.r1, this.y);
/*  448 */       double vty = v.dotProduct(this.y);
/*  449 */       double vtv = v.dotProduct(v);
/*  450 */       SymmLQ.daxpy(-vty / vtv, v, this.y);
/*  451 */       this.r2 = this.y.copy();
/*  452 */       if (this.minv != null)
/*  453 */         this.y = this.minv.operate(this.r2); 
/*  455 */       this.oldb = this.beta1;
/*  456 */       this.beta = this.r2.dotProduct(this.y);
/*  457 */       if (this.beta < 0.0D)
/*  458 */         SymmLQ.throwNPDLOException(this.minv, this.y); 
/*  460 */       this.beta = FastMath.sqrt(this.beta);
/*  468 */       this.cgnorm = this.beta1;
/*  469 */       this.gbar = alpha;
/*  470 */       this.dbar = this.beta;
/*  471 */       this.gammaZeta = this.beta1;
/*  472 */       this.minusEpsZeta = 0.0D;
/*  473 */       this.bstep = 0.0D;
/*  474 */       this.snprod = 1.0D;
/*  475 */       this.tnorm = alpha * alpha + this.beta * this.beta;
/*  476 */       this.ynorm2 = 0.0D;
/*  477 */       this.gmax = FastMath.abs(alpha) + SymmLQ.MACH_PREC;
/*  478 */       this.gmin = this.gmax;
/*  480 */       if (this.goodb) {
/*  481 */         this.wbar = new ArrayRealVector(this.a.getRowDimension());
/*  482 */         this.wbar.set(0.0D);
/*      */       } else {
/*  484 */         this.wbar = v;
/*      */       } 
/*  486 */       updateNorms();
/*      */     }
/*      */     
/*      */     private void update() {
/*  496 */       RealVector v = this.y.mapMultiply(1.0D / this.beta);
/*  497 */       this.y = this.a.operate(v);
/*  498 */       SymmLQ.daxpbypz(-this.shift, v, -this.beta / this.oldb, this.r1, this.y);
/*  499 */       double alpha = v.dotProduct(this.y);
/*  510 */       SymmLQ.daxpy(-alpha / this.beta, this.r2, this.y);
/*  524 */       this.r1 = this.r2;
/*  525 */       this.r2 = this.y;
/*  526 */       if (this.minv != null)
/*  527 */         this.y = this.minv.operate(this.r2); 
/*  529 */       this.oldb = this.beta;
/*  530 */       this.beta = this.r2.dotProduct(this.y);
/*  531 */       if (this.beta < 0.0D)
/*  532 */         SymmLQ.throwNPDLOException(this.minv, this.y); 
/*  534 */       this.beta = FastMath.sqrt(this.beta);
/*  543 */       this.tnorm += alpha * alpha + this.oldb * this.oldb + this.beta * this.beta;
/*  551 */       double gamma = FastMath.sqrt(this.gbar * this.gbar + this.oldb * this.oldb);
/*  552 */       double c = this.gbar / gamma;
/*  553 */       double s = this.oldb / gamma;
/*  563 */       double deltak = c * this.dbar + s * alpha;
/*  564 */       this.gbar = s * this.dbar - c * alpha;
/*  565 */       double eps = s * this.beta;
/*  566 */       this.dbar = -c * this.beta;
/*  567 */       double zeta = this.gammaZeta / gamma;
/*  576 */       double zetaC = zeta * c;
/*  577 */       double zetaS = zeta * s;
/*  578 */       int n = this.x.getDimension();
/*  579 */       for (int i = 0; i < n; i++) {
/*  580 */         double xi = this.x.getEntry(i);
/*  581 */         double vi = v.getEntry(i);
/*  582 */         double wi = this.wbar.getEntry(i);
/*  583 */         this.x.setEntry(i, xi + wi * zetaC + vi * zetaS);
/*  584 */         this.wbar.setEntry(i, wi * s - vi * c);
/*      */       } 
/*  592 */       this.bstep += this.snprod * c * zeta;
/*  593 */       this.snprod *= s;
/*  594 */       this.gmax = FastMath.max(this.gmax, gamma);
/*  595 */       this.gmin = FastMath.min(this.gmin, gamma);
/*  596 */       this.ynorm2 += zeta * zeta;
/*  597 */       this.gammaZeta = this.minusEpsZeta - deltak * zeta;
/*  598 */       this.minusEpsZeta = -eps * zeta;
/*  611 */       updateNorms();
/*      */     }
/*      */     
/*      */     private void updateNorms() {
/*  619 */       double acond, anorm = FastMath.sqrt(this.tnorm);
/*  620 */       double ynorm = FastMath.sqrt(this.ynorm2);
/*  621 */       double epsa = anorm * SymmLQ.MACH_PREC;
/*  622 */       double epsx = anorm * ynorm * SymmLQ.MACH_PREC;
/*  623 */       double epsr = anorm * ynorm * SymmLQ.this.delta;
/*  624 */       double diag = (this.gbar == 0.0D) ? epsa : this.gbar;
/*  625 */       this.lqnorm = FastMath.sqrt(this.gammaZeta * this.gammaZeta + this.minusEpsZeta * this.minusEpsZeta);
/*  627 */       double qrnorm = this.snprod * this.beta1;
/*  628 */       this.cgnorm = qrnorm * this.beta / FastMath.abs(diag);
/*  637 */       if (this.lqnorm <= this.cgnorm) {
/*  638 */         acond = this.gmax / this.gmin;
/*      */       } else {
/*  640 */         acond = this.gmax / FastMath.min(this.gmin, FastMath.abs(diag));
/*      */       } 
/*  642 */       if (acond * SymmLQ.MACH_PREC >= 0.1D)
/*  643 */         throw new IllConditionedOperatorException(acond); 
/*  645 */       if (this.beta1 <= epsx)
/*  650 */         throw new SingularOperatorException(); 
/*  652 */       this.hasConverged = (this.cgnorm <= epsx || this.cgnorm <= epsr);
/*      */     }
/*      */   }
/*      */   
/*      */   private static class SymmLQEvent extends IterativeLinearSolverEvent {
/*      */     private static final long serialVersionUID = 2012012801L;
/*      */     
/*      */     private final transient SymmLQ.State state;
/*      */     
/*      */     public SymmLQEvent(SymmLQ source, SymmLQ.State state) {
/*  681 */       super(source, source.getIterationManager().getIterations());
/*  682 */       this.state = state;
/*      */     }
/*      */     
/*      */     public int getIterations() {
/*  688 */       return ((SymmLQ)getSource()).getIterationManager().getIterations();
/*      */     }
/*      */     
/*      */     public double getNormOfResidual() {
/*  694 */       return FastMath.min(this.state.cgnorm, this.state.lqnorm);
/*      */     }
/*      */     
/*      */     public RealVector getRightHandSideVector() {
/*  700 */       return RealVector.unmodifiableRealVector(this.state.b);
/*      */     }
/*      */     
/*      */     public RealVector getSolution() {
/*  706 */       int n = this.state.x.getDimension();
/*  707 */       RealVector x = new ArrayRealVector(n);
/*  708 */       this.state.refine(x);
/*  709 */       return x;
/*      */     }
/*      */   }
/*      */   
/*      */   public SymmLQ(int maxIterations, double delta, boolean check) {
/*  755 */     super(maxIterations);
/*  756 */     this.delta = delta;
/*  757 */     this.check = check;
/*      */   }
/*      */   
/*      */   public SymmLQ(IterationManager manager, double delta, boolean check) {
/*  773 */     super(manager);
/*  774 */     this.delta = delta;
/*  775 */     this.check = check;
/*      */   }
/*      */   
/*  779 */   private static final double MACH_PREC = Math.ulp(1.0D);
/*      */   
/*      */   private static final String OPERATOR = "operator";
/*      */   
/*      */   private static final String THRESHOLD = "threshold";
/*      */   
/*      */   private static final String VECTOR = "vector";
/*      */   
/*      */   private static final String VECTOR1 = "vector1";
/*      */   
/*      */   private static final String VECTOR2 = "vector2";
/*      */   
/*      */   private final boolean check;
/*      */   
/*      */   private final double delta;
/*      */   
/*      */   static {
/*  780 */     CBRT_MACH_PREC = Math.cbrt(MACH_PREC);
/*      */   }
/*      */   
/*      */   private static void checkSymmetry(RealLinearOperator l, RealVector x, RealVector y, RealVector z) throws NonSelfAdjointOperatorException {
/*  799 */     double s = y.dotProduct(y);
/*  800 */     double t = x.dotProduct(z);
/*  801 */     double epsa = (s + MACH_PREC) * CBRT_MACH_PREC;
/*  802 */     if (FastMath.abs(s - t) > epsa) {
/*  804 */       NonSelfAdjointOperatorException e = new NonSelfAdjointOperatorException();
/*  805 */       ExceptionContext context = e.getContext();
/*  806 */       context.setValue("operator", l);
/*  807 */       context.setValue("vector1", x);
/*  808 */       context.setValue("vector2", y);
/*  809 */       context.setValue("threshold", Double.valueOf(epsa));
/*  810 */       throw e;
/*      */     } 
/*      */   }
/*      */   
/*      */   private static void daxpbypz(double a, RealVector x, double b, RealVector y, RealVector z) {
/*  827 */     int n = z.getDimension();
/*  828 */     for (int i = 0; i < n; i++) {
/*  830 */       double zi = a * x.getEntry(i) + b * y.getEntry(i) + z.getEntry(i);
/*  831 */       z.setEntry(i, zi);
/*      */     } 
/*      */   }
/*      */   
/*      */   private static void daxpy(double a, RealVector x, RealVector y) {
/*  846 */     int n = x.getDimension();
/*  847 */     for (int i = 0; i < n; i++)
/*  848 */       y.setEntry(i, a * x.getEntry(i) + y.getEntry(i)); 
/*      */   }
/*      */   
/*      */   private static void throwNPDLOException(RealLinearOperator l, RealVector v) throws NonPositiveDefiniteOperatorException {
/*  863 */     NonPositiveDefiniteOperatorException e = new NonPositiveDefiniteOperatorException();
/*  864 */     ExceptionContext context = e.getContext();
/*  865 */     context.setValue("operator", l);
/*  866 */     context.setValue("vector", v);
/*  867 */     throw e;
/*      */   }
/*      */   
/*      */   public final boolean getCheck() {
/*  877 */     return this.check;
/*      */   }
/*      */   
/*      */   public RealVector solve(RealLinearOperator a, RealLinearOperator minv, RealVector b) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, MaxCountExceededException, NonSelfAdjointOperatorException, NonPositiveDefiniteOperatorException, IllConditionedOperatorException {
/*  896 */     MathUtils.checkNotNull(a);
/*  897 */     RealVector x = new ArrayRealVector(a.getColumnDimension());
/*  898 */     return solveInPlace(a, minv, b, x, false, 0.0D);
/*      */   }
/*      */   
/*      */   public RealVector solve(RealLinearOperator a, RealLinearOperator minv, RealVector b, boolean goodb, double shift) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, MaxCountExceededException, NonSelfAdjointOperatorException, NonPositiveDefiniteOperatorException, IllConditionedOperatorException {
/*  949 */     MathUtils.checkNotNull(a);
/*  950 */     RealVector x = new ArrayRealVector(a.getColumnDimension());
/*  951 */     return solveInPlace(a, minv, b, x, goodb, shift);
/*      */   }
/*      */   
/*      */   public RealVector solve(RealLinearOperator a, RealLinearOperator minv, RealVector b, RealVector x) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, NonSelfAdjointOperatorException, NonPositiveDefiniteOperatorException, IllConditionedOperatorException, MaxCountExceededException {
/*  972 */     MathUtils.checkNotNull(x);
/*  973 */     return solveInPlace(a, minv, b, x.copy(), false, 0.0D);
/*      */   }
/*      */   
/*      */   public RealVector solve(RealLinearOperator a, RealVector b) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, NonSelfAdjointOperatorException, IllConditionedOperatorException, MaxCountExceededException {
/*  988 */     MathUtils.checkNotNull(a);
/*  989 */     RealVector x = new ArrayRealVector(a.getColumnDimension());
/*  990 */     x.set(0.0D);
/*  991 */     return solveInPlace(a, (RealLinearOperator)null, b, x, false, 0.0D);
/*      */   }
/*      */   
/*      */   public RealVector solve(RealLinearOperator a, RealVector b, boolean goodb, double shift) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, NonSelfAdjointOperatorException, IllConditionedOperatorException, MaxCountExceededException {
/* 1034 */     MathUtils.checkNotNull(a);
/* 1035 */     RealVector x = new ArrayRealVector(a.getColumnDimension());
/* 1036 */     return solveInPlace(a, (RealLinearOperator)null, b, x, goodb, shift);
/*      */   }
/*      */   
/*      */   public RealVector solve(RealLinearOperator a, RealVector b, RealVector x) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, NonSelfAdjointOperatorException, IllConditionedOperatorException, MaxCountExceededException {
/* 1054 */     MathUtils.checkNotNull(x);
/* 1055 */     return solveInPlace(a, (RealLinearOperator)null, b, x.copy(), false, 0.0D);
/*      */   }
/*      */   
/*      */   public RealVector solveInPlace(RealLinearOperator a, RealLinearOperator minv, RealVector b, RealVector x) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, NonSelfAdjointOperatorException, NonPositiveDefiniteOperatorException, IllConditionedOperatorException, MaxCountExceededException {
/* 1076 */     return solveInPlace(a, minv, b, x, false, 0.0D);
/*      */   }
/*      */   
/*      */   public RealVector solveInPlace(RealLinearOperator a, RealLinearOperator minv, RealVector b, RealVector x, boolean goodb, double shift) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, NonSelfAdjointOperatorException, NonPositiveDefiniteOperatorException, IllConditionedOperatorException, MaxCountExceededException {
/* 1130 */     checkParameters(a, minv, b, x);
/* 1132 */     IterationManager manager = getIterationManager();
/* 1134 */     manager.resetIterationCount();
/* 1135 */     manager.incrementIterationCount();
/* 1137 */     State state = new State(a, minv, b, x, goodb, shift);
/* 1138 */     IterativeLinearSolverEvent event = new SymmLQEvent(this, state);
/* 1139 */     if (state.beta1 == 0.0D) {
/* 1141 */       manager.fireTerminationEvent(event);
/* 1142 */       return x;
/*      */     } 
/* 1146 */     boolean earlyStop = (state.beta < MACH_PREC || state.hasConverged);
/* 1147 */     manager.fireInitializationEvent(event);
/* 1148 */     if (!earlyStop)
/*      */       do {
/* 1150 */         manager.incrementIterationCount();
/* 1151 */         manager.fireIterationStartedEvent(event);
/* 1152 */         state.update();
/* 1153 */         manager.fireIterationPerformedEvent(event);
/* 1154 */       } while (!state.hasConverged); 
/* 1156 */     state.refine(x);
/* 1162 */     state.bstep = 0.0D;
/* 1163 */     state.gammaZeta = 0.0D;
/* 1164 */     manager.fireTerminationEvent(event);
/* 1165 */     return x;
/*      */   }
/*      */   
/*      */   public RealVector solveInPlace(RealLinearOperator a, RealVector b, RealVector x) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, NonSelfAdjointOperatorException, IllConditionedOperatorException, MaxCountExceededException {
/* 1183 */     return solveInPlace(a, (RealLinearOperator)null, b, x, false, 0.0D);
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\linear\SymmLQ.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
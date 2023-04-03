/*     */ package org.apache.commons.math3.optimization.general;
/*     */ 
/*     */ import org.apache.commons.math3.exception.ConvergenceException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.linear.ArrayRealVector;
/*     */ import org.apache.commons.math3.linear.BlockRealMatrix;
/*     */ import org.apache.commons.math3.linear.DecompositionSolver;
/*     */ import org.apache.commons.math3.linear.LUDecomposition;
/*     */ import org.apache.commons.math3.linear.QRDecomposition;
/*     */ import org.apache.commons.math3.linear.RealMatrix;
/*     */ import org.apache.commons.math3.linear.RealVector;
/*     */ import org.apache.commons.math3.linear.SingularMatrixException;
/*     */ import org.apache.commons.math3.optimization.ConvergenceChecker;
/*     */ import org.apache.commons.math3.optimization.PointVectorValuePair;
/*     */ import org.apache.commons.math3.optimization.SimpleVectorValueChecker;
/*     */ 
/*     */ public class GaussNewtonOptimizer extends AbstractLeastSquaresOptimizer {
/*     */   private final boolean useLU;
/*     */   
/*     */   public GaussNewtonOptimizer() {
/*  58 */     this(true);
/*     */   }
/*     */   
/*     */   public GaussNewtonOptimizer(ConvergenceChecker<PointVectorValuePair> checker) {
/*  68 */     this(true, checker);
/*     */   }
/*     */   
/*     */   public GaussNewtonOptimizer(boolean useLU) {
/*  81 */     this(useLU, (ConvergenceChecker<PointVectorValuePair>)new SimpleVectorValueChecker());
/*     */   }
/*     */   
/*     */   public GaussNewtonOptimizer(boolean useLU, ConvergenceChecker<PointVectorValuePair> checker) {
/*  92 */     super(checker);
/*  93 */     this.useLU = useLU;
/*     */   }
/*     */   
/*     */   public PointVectorValuePair doOptimize() {
/* 100 */     ConvergenceChecker<PointVectorValuePair> checker = getConvergenceChecker();
/* 104 */     PointVectorValuePair current = null;
/* 105 */     int iter = 0;
/*     */     boolean converged;
/* 106 */     for (converged = false; !converged; ) {
/* 107 */       iter++;
/* 110 */       PointVectorValuePair previous = current;
/* 111 */       updateResidualsAndCost();
/* 112 */       updateJacobian();
/* 113 */       current = new PointVectorValuePair(this.point, this.objective);
/* 115 */       double[] targetValues = getTargetRef();
/* 116 */       double[] residualsWeights = getWeightRef();
/* 119 */       double[] b = new double[this.cols];
/* 120 */       double[][] a = new double[this.cols][this.cols];
/* 121 */       for (int i = 0; i < this.rows; i++) {
/* 123 */         double[] grad = this.weightedResidualJacobian[i];
/* 124 */         double weight = residualsWeights[i];
/* 125 */         double residual = this.objective[i] - targetValues[i];
/* 128 */         double wr = weight * residual;
/* 129 */         for (int j = 0; j < this.cols; j++)
/* 130 */           b[j] = b[j] + wr * grad[j]; 
/* 134 */         for (int k = 0; k < this.cols; k++) {
/* 135 */           double[] ak = a[k];
/* 136 */           double wgk = weight * grad[k];
/* 137 */           for (int l = 0; l < this.cols; l++)
/* 138 */             ak[l] = ak[l] + wgk * grad[l]; 
/*     */         } 
/*     */       } 
/*     */       try {
/* 145 */         BlockRealMatrix blockRealMatrix = new BlockRealMatrix(a);
/* 146 */         DecompositionSolver solver = this.useLU ? (new LUDecomposition((RealMatrix)blockRealMatrix)).getSolver() : (new QRDecomposition((RealMatrix)blockRealMatrix)).getSolver();
/* 149 */         double[] dX = solver.solve((RealVector)new ArrayRealVector(b, false)).toArray();
/* 151 */         for (int j = 0; j < this.cols; j++)
/* 152 */           this.point[j] = this.point[j] + dX[j]; 
/* 154 */       } catch (SingularMatrixException e) {
/* 155 */         throw new ConvergenceException(LocalizedFormats.UNABLE_TO_SOLVE_SINGULAR_PROBLEM, new Object[0]);
/*     */       } 
/* 159 */       if (checker != null && 
/* 160 */         previous != null)
/* 161 */         converged = checker.converged(iter, previous, current); 
/*     */     } 
/* 166 */     return current;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\optimization\general\GaussNewtonOptimizer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
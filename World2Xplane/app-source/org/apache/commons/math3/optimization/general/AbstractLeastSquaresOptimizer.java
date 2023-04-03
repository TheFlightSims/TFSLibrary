/*     */ package org.apache.commons.math3.optimization.general;
/*     */ 
/*     */ import org.apache.commons.math3.analysis.DifferentiableMultivariateVectorFunction;
/*     */ import org.apache.commons.math3.analysis.MultivariateMatrixFunction;
/*     */ import org.apache.commons.math3.analysis.MultivariateVectorFunction;
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ import org.apache.commons.math3.exception.NumberIsTooSmallException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.linear.DecompositionSolver;
/*     */ import org.apache.commons.math3.linear.MatrixUtils;
/*     */ import org.apache.commons.math3.linear.QRDecomposition;
/*     */ import org.apache.commons.math3.optimization.ConvergenceChecker;
/*     */ import org.apache.commons.math3.optimization.DifferentiableMultivariateVectorOptimizer;
/*     */ import org.apache.commons.math3.optimization.PointVectorValuePair;
/*     */ import org.apache.commons.math3.optimization.direct.BaseAbstractMultivariateVectorOptimizer;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public abstract class AbstractLeastSquaresOptimizer extends BaseAbstractMultivariateVectorOptimizer<DifferentiableMultivariateVectorFunction> implements DifferentiableMultivariateVectorOptimizer {
/*     */   private static final double DEFAULT_SINGULARITY_THRESHOLD = 1.0E-14D;
/*     */   
/*     */   protected double[][] weightedResidualJacobian;
/*     */   
/*     */   protected int cols;
/*     */   
/*     */   protected int rows;
/*     */   
/*     */   protected double[] point;
/*     */   
/*     */   protected double[] objective;
/*     */   
/*     */   protected double[] weightedResiduals;
/*     */   
/*     */   protected double cost;
/*     */   
/*     */   private MultivariateMatrixFunction jF;
/*     */   
/*     */   private int jacobianEvaluations;
/*     */   
/*     */   protected AbstractLeastSquaresOptimizer() {}
/*     */   
/*     */   protected AbstractLeastSquaresOptimizer(ConvergenceChecker<PointVectorValuePair> checker) {
/*  91 */     super(checker);
/*     */   }
/*     */   
/*     */   public int getJacobianEvaluations() {
/*  98 */     return this.jacobianEvaluations;
/*     */   }
/*     */   
/*     */   protected void updateJacobian() {
/* 108 */     this.jacobianEvaluations++;
/* 109 */     this.weightedResidualJacobian = this.jF.value(this.point);
/* 110 */     if (this.weightedResidualJacobian.length != this.rows)
/* 111 */       throw new DimensionMismatchException(this.weightedResidualJacobian.length, this.rows); 
/* 114 */     double[] residualsWeights = getWeightRef();
/* 116 */     for (int i = 0; i < this.rows; i++) {
/* 117 */       double[] ji = this.weightedResidualJacobian[i];
/* 118 */       double wi = FastMath.sqrt(residualsWeights[i]);
/* 119 */       for (int j = 0; j < this.cols; j++)
/* 121 */         this.weightedResidualJacobian[i][j] = -ji[j] * wi; 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void updateResidualsAndCost() {
/* 134 */     this.objective = computeObjectiveValue(this.point);
/* 135 */     if (this.objective.length != this.rows)
/* 136 */       throw new DimensionMismatchException(this.objective.length, this.rows); 
/* 139 */     double[] targetValues = getTargetRef();
/* 140 */     double[] residualsWeights = getWeightRef();
/* 142 */     this.cost = 0.0D;
/* 143 */     for (int i = 0; i < this.rows; i++) {
/* 144 */       double residual = targetValues[i] - this.objective[i];
/* 145 */       this.weightedResiduals[i] = residual * FastMath.sqrt(residualsWeights[i]);
/* 146 */       this.cost += residualsWeights[i] * residual * residual;
/*     */     } 
/* 148 */     this.cost = FastMath.sqrt(this.cost);
/*     */   }
/*     */   
/*     */   public double getRMS() {
/* 162 */     return FastMath.sqrt(getChiSquare() / this.rows);
/*     */   }
/*     */   
/*     */   public double getChiSquare() {
/* 172 */     return this.cost * this.cost;
/*     */   }
/*     */   
/*     */   public double[][] getCovariances() {
/* 185 */     return getCovariances(1.0E-14D);
/*     */   }
/*     */   
/*     */   public double[][] getCovariances(double threshold) {
/* 205 */     updateJacobian();
/* 208 */     double[][] jTj = new double[this.cols][this.cols];
/* 209 */     for (int i = 0; i < this.cols; i++) {
/* 210 */       for (int j = i; j < this.cols; j++) {
/* 211 */         double sum = 0.0D;
/* 212 */         for (int k = 0; k < this.rows; k++)
/* 213 */           sum += this.weightedResidualJacobian[k][i] * this.weightedResidualJacobian[k][j]; 
/* 215 */         jTj[i][j] = sum;
/* 216 */         jTj[j][i] = sum;
/*     */       } 
/*     */     } 
/* 221 */     DecompositionSolver solver = (new QRDecomposition(MatrixUtils.createRealMatrix(jTj), threshold)).getSolver();
/* 223 */     return solver.getInverse().getData();
/*     */   }
/*     */   
/*     */   public double[] guessParametersErrors() {
/* 238 */     if (this.rows <= this.cols)
/* 239 */       throw new NumberIsTooSmallException(LocalizedFormats.NO_DEGREES_OF_FREEDOM, Integer.valueOf(this.rows), Integer.valueOf(this.cols), false); 
/* 242 */     double[] errors = new double[this.cols];
/* 243 */     double c = FastMath.sqrt(getChiSquare() / (this.rows - this.cols));
/* 244 */     double[][] covar = getCovariances();
/* 245 */     for (int i = 0; i < errors.length; i++)
/* 246 */       errors[i] = FastMath.sqrt(covar[i][i]) * c; 
/* 248 */     return errors;
/*     */   }
/*     */   
/*     */   public PointVectorValuePair optimize(int maxEval, DifferentiableMultivariateVectorFunction f, double[] target, double[] weights, double[] startPoint) {
/* 258 */     this.jacobianEvaluations = 0;
/* 261 */     this.jF = f.jacobian();
/* 264 */     this.point = (double[])startPoint.clone();
/* 265 */     this.rows = target.length;
/* 266 */     this.cols = this.point.length;
/* 268 */     this.weightedResidualJacobian = new double[this.rows][this.cols];
/* 269 */     this.weightedResiduals = new double[this.rows];
/* 271 */     this.cost = Double.POSITIVE_INFINITY;
/* 273 */     return super.optimize(maxEval, (MultivariateVectorFunction)f, target, weights, startPoint);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\optimization\general\AbstractLeastSquaresOptimizer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
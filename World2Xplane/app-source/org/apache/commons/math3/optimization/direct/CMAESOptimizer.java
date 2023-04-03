/*      */ package org.apache.commons.math3.optimization.direct;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.List;
/*      */ import org.apache.commons.math3.analysis.MultivariateFunction;
/*      */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*      */ import org.apache.commons.math3.exception.MathUnsupportedOperationException;
/*      */ import org.apache.commons.math3.exception.NotPositiveException;
/*      */ import org.apache.commons.math3.exception.OutOfRangeException;
/*      */ import org.apache.commons.math3.exception.TooManyEvaluationsException;
/*      */ import org.apache.commons.math3.linear.Array2DRowRealMatrix;
/*      */ import org.apache.commons.math3.linear.EigenDecomposition;
/*      */ import org.apache.commons.math3.linear.MatrixUtils;
/*      */ import org.apache.commons.math3.linear.RealMatrix;
/*      */ import org.apache.commons.math3.optimization.ConvergenceChecker;
/*      */ import org.apache.commons.math3.optimization.GoalType;
/*      */ import org.apache.commons.math3.optimization.MultivariateOptimizer;
/*      */ import org.apache.commons.math3.optimization.PointValuePair;
/*      */ import org.apache.commons.math3.optimization.SimpleValueChecker;
/*      */ import org.apache.commons.math3.random.MersenneTwister;
/*      */ import org.apache.commons.math3.random.RandomGenerator;
/*      */ import org.apache.commons.math3.util.MathArrays;
/*      */ 
/*      */ public class CMAESOptimizer extends BaseAbstractMultivariateSimpleBoundsOptimizer<MultivariateFunction> implements MultivariateOptimizer {
/*      */   public static final int DEFAULT_CHECKFEASABLECOUNT = 0;
/*      */   
/*      */   public static final double DEFAULT_STOPFITNESS = 0.0D;
/*      */   
/*      */   public static final boolean DEFAULT_ISACTIVECMA = true;
/*      */   
/*      */   public static final int DEFAULT_MAXITERATIONS = 30000;
/*      */   
/*      */   public static final int DEFAULT_DIAGONALONLY = 0;
/*      */   
/*   97 */   public static final RandomGenerator DEFAULT_RANDOMGENERATOR = (RandomGenerator)new MersenneTwister();
/*      */   
/*      */   private int lambda;
/*      */   
/*      */   private boolean isActiveCMA;
/*      */   
/*      */   private int checkFeasableCount;
/*      */   
/*      */   private double[][] boundaries;
/*      */   
/*      */   private double[] inputSigma;
/*      */   
/*      */   private int dimension;
/*      */   
/*  143 */   private int diagonalOnly = 0;
/*      */   
/*      */   private boolean isMinimize = true;
/*      */   
/*      */   private boolean generateStatistics = false;
/*      */   
/*      */   private int maxIterations;
/*      */   
/*      */   private double stopFitness;
/*      */   
/*      */   private double stopTolUpX;
/*      */   
/*      */   private double stopTolX;
/*      */   
/*      */   private double stopTolFun;
/*      */   
/*      */   private double stopTolHistFun;
/*      */   
/*      */   private int mu;
/*      */   
/*      */   private double logMu2;
/*      */   
/*      */   private RealMatrix weights;
/*      */   
/*      */   private double mueff;
/*      */   
/*      */   private double sigma;
/*      */   
/*      */   private double cc;
/*      */   
/*      */   private double cs;
/*      */   
/*      */   private double damps;
/*      */   
/*      */   private double ccov1;
/*      */   
/*      */   private double ccovmu;
/*      */   
/*      */   private double chiN;
/*      */   
/*      */   private double ccov1Sep;
/*      */   
/*      */   private double ccovmuSep;
/*      */   
/*      */   private RealMatrix xmean;
/*      */   
/*      */   private RealMatrix pc;
/*      */   
/*      */   private RealMatrix ps;
/*      */   
/*      */   private double normps;
/*      */   
/*      */   private RealMatrix B;
/*      */   
/*      */   private RealMatrix D;
/*      */   
/*      */   private RealMatrix BD;
/*      */   
/*      */   private RealMatrix diagD;
/*      */   
/*      */   private RealMatrix C;
/*      */   
/*      */   private RealMatrix diagC;
/*      */   
/*      */   private int iterations;
/*      */   
/*      */   private double[] fitnessHistory;
/*      */   
/*      */   private int historySize;
/*      */   
/*      */   private RandomGenerator random;
/*      */   
/*  226 */   private List<Double> statisticsSigmaHistory = new ArrayList<Double>();
/*      */   
/*  228 */   private List<RealMatrix> statisticsMeanHistory = new ArrayList<RealMatrix>();
/*      */   
/*  230 */   private List<Double> statisticsFitnessHistory = new ArrayList<Double>();
/*      */   
/*  232 */   private List<RealMatrix> statisticsDHistory = new ArrayList<RealMatrix>();
/*      */   
/*      */   public CMAESOptimizer() {
/*  238 */     this(0);
/*      */   }
/*      */   
/*      */   public CMAESOptimizer(int lambda) {
/*  245 */     this(lambda, (double[])null, 30000, 0.0D, true, 0, 0, DEFAULT_RANDOMGENERATOR, false);
/*      */   }
/*      */   
/*      */   public CMAESOptimizer(int lambda, double[] inputSigma) {
/*  255 */     this(lambda, inputSigma, 30000, 0.0D, true, 0, 0, DEFAULT_RANDOMGENERATOR, false);
/*      */   }
/*      */   
/*      */   public CMAESOptimizer(int lambda, double[] inputSigma, int maxIterations, double stopFitness, boolean isActiveCMA, int diagonalOnly, int checkFeasableCount, RandomGenerator random, boolean generateStatistics) {
/*  278 */     this(lambda, inputSigma, maxIterations, stopFitness, isActiveCMA, diagonalOnly, checkFeasableCount, random, generateStatistics, (ConvergenceChecker<PointValuePair>)new SimpleValueChecker());
/*      */   }
/*      */   
/*      */   public CMAESOptimizer(int lambda, double[] inputSigma, int maxIterations, double stopFitness, boolean isActiveCMA, int diagonalOnly, int checkFeasableCount, RandomGenerator random, boolean generateStatistics, ConvergenceChecker<PointValuePair> checker) {
/*  303 */     super(checker);
/*  304 */     this.lambda = lambda;
/*  305 */     this.inputSigma = (inputSigma == null) ? null : (double[])inputSigma.clone();
/*  306 */     this.maxIterations = maxIterations;
/*  307 */     this.stopFitness = stopFitness;
/*  308 */     this.isActiveCMA = isActiveCMA;
/*  309 */     this.diagonalOnly = diagonalOnly;
/*  310 */     this.checkFeasableCount = checkFeasableCount;
/*  311 */     this.random = random;
/*  312 */     this.generateStatistics = generateStatistics;
/*      */   }
/*      */   
/*      */   public List<Double> getStatisticsSigmaHistory() {
/*  319 */     return this.statisticsSigmaHistory;
/*      */   }
/*      */   
/*      */   public List<RealMatrix> getStatisticsMeanHistory() {
/*  326 */     return this.statisticsMeanHistory;
/*      */   }
/*      */   
/*      */   public List<Double> getStatisticsFitnessHistory() {
/*  333 */     return this.statisticsFitnessHistory;
/*      */   }
/*      */   
/*      */   public List<RealMatrix> getStatisticsDHistory() {
/*  340 */     return this.statisticsDHistory;
/*      */   }
/*      */   
/*      */   protected PointValuePair doOptimize() {
/*  346 */     checkParameters();
/*  348 */     this.isMinimize = getGoalType().equals(GoalType.MINIMIZE);
/*  349 */     FitnessFunction fitfun = new FitnessFunction();
/*  350 */     double[] guess = fitfun.encode(getStartPoint());
/*  352 */     this.dimension = guess.length;
/*  353 */     initializeCMA(guess);
/*  354 */     this.iterations = 0;
/*  355 */     double bestValue = fitfun.value(guess);
/*  356 */     push(this.fitnessHistory, bestValue);
/*  357 */     PointValuePair optimum = new PointValuePair(getStartPoint(), this.isMinimize ? bestValue : -bestValue);
/*  359 */     PointValuePair lastResult = null;
/*  364 */     label101: for (this.iterations = 1; this.iterations <= this.maxIterations; this.iterations++) {
/*  366 */       RealMatrix arz = randn1(this.dimension, this.lambda);
/*  367 */       RealMatrix arx = zeros(this.dimension, this.lambda);
/*  368 */       double[] fitness = new double[this.lambda];
/*  370 */       for (int k = 0; k < this.lambda; k++) {
/*  371 */         RealMatrix arxk = null;
/*  372 */         for (int j = 0; j < this.checkFeasableCount + 1; j++) {
/*  373 */           if (this.diagonalOnly <= 0) {
/*  374 */             arxk = this.xmean.add(this.BD.multiply(arz.getColumnMatrix(k)).scalarMultiply(this.sigma));
/*      */           } else {
/*  377 */             arxk = this.xmean.add(times(this.diagD, arz.getColumnMatrix(k)).scalarMultiply(this.sigma));
/*      */           } 
/*  380 */           if (j >= this.checkFeasableCount || fitfun.isFeasible(arxk.getColumn(0)))
/*      */             break; 
/*  384 */           arz.setColumn(k, randn(this.dimension));
/*      */         } 
/*  386 */         copyColumn(arxk, 0, arx, k);
/*      */         try {
/*  388 */           fitness[k] = fitfun.value(arx.getColumn(k));
/*  389 */         } catch (TooManyEvaluationsException e) {
/*      */           break label101;
/*      */         } 
/*      */       } 
/*  394 */       int[] arindex = sortedIndices(fitness);
/*  396 */       RealMatrix xold = this.xmean;
/*  397 */       RealMatrix bestArx = selectColumns(arx, MathArrays.copyOf(arindex, this.mu));
/*  398 */       this.xmean = bestArx.multiply(this.weights);
/*  399 */       RealMatrix bestArz = selectColumns(arz, MathArrays.copyOf(arindex, this.mu));
/*  400 */       RealMatrix zmean = bestArz.multiply(this.weights);
/*  401 */       boolean hsig = updateEvolutionPaths(zmean, xold);
/*  402 */       if (this.diagonalOnly <= 0) {
/*  403 */         updateCovariance(hsig, bestArx, arz, arindex, xold);
/*      */       } else {
/*  405 */         updateCovarianceDiagonalOnly(hsig, bestArz, xold);
/*      */       } 
/*  408 */       this.sigma *= Math.exp(Math.min(1.0D, (this.normps / this.chiN - 1.0D) * this.cs / this.damps));
/*  409 */       double bestFitness = fitness[arindex[0]];
/*  410 */       double worstFitness = fitness[arindex[arindex.length - 1]];
/*  411 */       if (bestValue > bestFitness) {
/*  412 */         bestValue = bestFitness;
/*  413 */         lastResult = optimum;
/*  414 */         optimum = new PointValuePair(fitfun.decode(bestArx.getColumn(0)), this.isMinimize ? bestFitness : -bestFitness);
/*  417 */         if (getConvergenceChecker() != null && lastResult != null && 
/*  418 */           getConvergenceChecker().converged(this.iterations, optimum, lastResult))
/*      */           break; 
/*      */       } 
/*  425 */       if (this.stopFitness != 0.0D && 
/*  426 */         bestFitness < (this.isMinimize ? this.stopFitness : -this.stopFitness))
/*      */         break; 
/*  430 */       double[] sqrtDiagC = sqrt(this.diagC).getColumn(0);
/*  431 */       double[] pcCol = this.pc.getColumn(0);
/*      */       int i;
/*  432 */       for (i = 0; i < this.dimension && 
/*  433 */         this.sigma * Math.max(Math.abs(pcCol[i]), sqrtDiagC[i]) <= this.stopTolX; i++) {
/*  436 */         if (i >= this.dimension - 1)
/*      */           break label101; 
/*      */       } 
/*  440 */       for (i = 0; i < this.dimension; i++) {
/*  441 */         if (this.sigma * sqrtDiagC[i] > this.stopTolUpX)
/*      */           break label101; 
/*      */       } 
/*  445 */       double historyBest = min(this.fitnessHistory);
/*  446 */       double historyWorst = max(this.fitnessHistory);
/*  447 */       if (this.iterations > 2 && Math.max(historyWorst, worstFitness) - Math.min(historyBest, bestFitness) < this.stopTolFun)
/*      */         break; 
/*  451 */       if (this.iterations > this.fitnessHistory.length && historyWorst - historyBest < this.stopTolHistFun)
/*      */         break; 
/*  456 */       if (max(this.diagD) / min(this.diagD) > 1.0E7D)
/*      */         break; 
/*  460 */       if (getConvergenceChecker() != null) {
/*  461 */         PointValuePair current = new PointValuePair(bestArx.getColumn(0), this.isMinimize ? bestFitness : -bestFitness);
/*  464 */         if (lastResult != null && getConvergenceChecker().converged(this.iterations, current, lastResult))
/*      */           break; 
/*  468 */         lastResult = current;
/*      */       } 
/*  471 */       if (bestValue == fitness[arindex[(int)(0.1D + this.lambda / 4.0D)]])
/*  472 */         this.sigma *= Math.exp(0.2D + this.cs / this.damps); 
/*  474 */       if (this.iterations > 2 && Math.max(historyWorst, bestFitness) - Math.min(historyBest, bestFitness) == 0.0D)
/*  476 */         this.sigma *= Math.exp(0.2D + this.cs / this.damps); 
/*  479 */       push(this.fitnessHistory, bestFitness);
/*  480 */       fitfun.setValueRange(worstFitness - bestFitness);
/*  481 */       if (this.generateStatistics) {
/*  482 */         this.statisticsSigmaHistory.add(Double.valueOf(this.sigma));
/*  483 */         this.statisticsFitnessHistory.add(Double.valueOf(bestFitness));
/*  484 */         this.statisticsMeanHistory.add(this.xmean.transpose());
/*  485 */         this.statisticsDHistory.add(this.diagD.transpose().scalarMultiply(100000.0D));
/*      */       } 
/*      */     } 
/*  488 */     return optimum;
/*      */   }
/*      */   
/*      */   private void checkParameters() {
/*  495 */     double[] init = getStartPoint();
/*  496 */     double[] lB = getLowerBound();
/*  497 */     double[] uB = getUpperBound();
/*  500 */     boolean hasFiniteBounds = false;
/*  501 */     for (int i = 0; i < lB.length; i++) {
/*  502 */       if (!Double.isInfinite(lB[i]) || !Double.isInfinite(uB[i])) {
/*  504 */         hasFiniteBounds = true;
/*      */         break;
/*      */       } 
/*      */     } 
/*  509 */     boolean hasInfiniteBounds = false;
/*  510 */     if (hasFiniteBounds) {
/*  511 */       for (int j = 0; j < lB.length; j++) {
/*  512 */         if (Double.isInfinite(lB[j]) || Double.isInfinite(uB[j])) {
/*  514 */           hasInfiniteBounds = true;
/*      */           break;
/*      */         } 
/*      */       } 
/*  519 */       if (hasInfiniteBounds)
/*  522 */         throw new MathUnsupportedOperationException(); 
/*  525 */       this.boundaries = new double[2][];
/*  526 */       this.boundaries[0] = lB;
/*  527 */       this.boundaries[1] = uB;
/*      */     } else {
/*  531 */       this.boundaries = (double[][])null;
/*      */     } 
/*  534 */     if (this.inputSigma != null) {
/*  535 */       if (this.inputSigma.length != init.length)
/*  536 */         throw new DimensionMismatchException(this.inputSigma.length, init.length); 
/*  538 */       for (int j = 0; j < init.length; j++) {
/*  539 */         if (this.inputSigma[j] < 0.0D)
/*  540 */           throw new NotPositiveException(Double.valueOf(this.inputSigma[j])); 
/*  542 */         if (this.boundaries != null && 
/*  543 */           this.inputSigma[j] > this.boundaries[1][j] - this.boundaries[0][j])
/*  544 */           throw new OutOfRangeException(Double.valueOf(this.inputSigma[j]), Integer.valueOf(0), Double.valueOf(this.boundaries[1][j] - this.boundaries[0][j])); 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void initializeCMA(double[] guess) {
/*  557 */     if (this.lambda <= 0)
/*  558 */       this.lambda = 4 + (int)(3.0D * Math.log(this.dimension)); 
/*  561 */     double[][] sigmaArray = new double[guess.length][1];
/*  562 */     for (int i = 0; i < guess.length; i++) {
/*  563 */       double range = (this.boundaries == null) ? 1.0D : (this.boundaries[1][i] - this.boundaries[0][i]);
/*  564 */       sigmaArray[i][0] = ((this.inputSigma == null) ? 0.3D : this.inputSigma[i]) / range;
/*      */     } 
/*  566 */     Array2DRowRealMatrix array2DRowRealMatrix = new Array2DRowRealMatrix(sigmaArray, false);
/*  567 */     this.sigma = max((RealMatrix)array2DRowRealMatrix);
/*  570 */     this.stopTolUpX = 1000.0D * max((RealMatrix)array2DRowRealMatrix);
/*  571 */     this.stopTolX = 1.0E-11D * max((RealMatrix)array2DRowRealMatrix);
/*  572 */     this.stopTolFun = 1.0E-12D;
/*  573 */     this.stopTolHistFun = 1.0E-13D;
/*  576 */     this.mu = this.lambda / 2;
/*  577 */     this.logMu2 = Math.log(this.mu + 0.5D);
/*  578 */     this.weights = log(sequence(1.0D, this.mu, 1.0D)).scalarMultiply(-1.0D).scalarAdd(this.logMu2);
/*  579 */     double sumw = 0.0D;
/*  580 */     double sumwq = 0.0D;
/*      */     int j;
/*  581 */     for (j = 0; j < this.mu; j++) {
/*  582 */       double w = this.weights.getEntry(j, 0);
/*  583 */       sumw += w;
/*  584 */       sumwq += w * w;
/*      */     } 
/*  586 */     this.weights = this.weights.scalarMultiply(1.0D / sumw);
/*  587 */     this.mueff = sumw * sumw / sumwq;
/*  590 */     this.cc = (4.0D + this.mueff / this.dimension) / (this.dimension + 4.0D + 2.0D * this.mueff / this.dimension);
/*  592 */     this.cs = (this.mueff + 2.0D) / (this.dimension + this.mueff + 3.0D);
/*  593 */     this.damps = (1.0D + 2.0D * Math.max(0.0D, Math.sqrt((this.mueff - 1.0D) / (this.dimension + 1.0D)) - 1.0D)) * Math.max(0.3D, 1.0D - this.dimension / (1.0E-6D + Math.min(this.maxIterations, getMaxEvaluations() / this.lambda))) + this.cs;
/*  598 */     this.ccov1 = 2.0D / ((this.dimension + 1.3D) * (this.dimension + 1.3D) + this.mueff);
/*  599 */     this.ccovmu = Math.min(1.0D - this.ccov1, 2.0D * (this.mueff - 2.0D + 1.0D / this.mueff) / ((this.dimension + 2.0D) * (this.dimension + 2.0D) + this.mueff));
/*  601 */     this.ccov1Sep = Math.min(1.0D, this.ccov1 * (this.dimension + 1.5D) / 3.0D);
/*  602 */     this.ccovmuSep = Math.min(1.0D - this.ccov1, this.ccovmu * (this.dimension + 1.5D) / 3.0D);
/*  603 */     this.chiN = Math.sqrt(this.dimension) * (1.0D - 1.0D / 4.0D * this.dimension + 1.0D / 21.0D * this.dimension * this.dimension);
/*  606 */     this.xmean = MatrixUtils.createColumnRealMatrix(guess);
/*  608 */     this.diagD = array2DRowRealMatrix.scalarMultiply(1.0D / this.sigma);
/*  609 */     this.diagC = square(this.diagD);
/*  610 */     this.pc = zeros(this.dimension, 1);
/*  611 */     this.ps = zeros(this.dimension, 1);
/*  612 */     this.normps = this.ps.getFrobeniusNorm();
/*  614 */     this.B = eye(this.dimension, this.dimension);
/*  615 */     this.D = ones(this.dimension, 1);
/*  616 */     this.BD = times(this.B, repmat(this.diagD.transpose(), this.dimension, 1));
/*  617 */     this.C = this.B.multiply(diag(square(this.D)).multiply(this.B.transpose()));
/*  618 */     this.historySize = 10 + (int)(30.0D * this.dimension / this.lambda);
/*  619 */     this.fitnessHistory = new double[this.historySize];
/*  620 */     for (j = 0; j < this.historySize; j++)
/*  621 */       this.fitnessHistory[j] = Double.MAX_VALUE; 
/*      */   }
/*      */   
/*      */   private boolean updateEvolutionPaths(RealMatrix zmean, RealMatrix xold) {
/*  634 */     this.ps = this.ps.scalarMultiply(1.0D - this.cs).add(this.B.multiply(zmean).scalarMultiply(Math.sqrt(this.cs * (2.0D - this.cs) * this.mueff)));
/*  637 */     this.normps = this.ps.getFrobeniusNorm();
/*  638 */     boolean hsig = (this.normps / Math.sqrt(1.0D - Math.pow(1.0D - this.cs, 2.0D * this.iterations)) / this.chiN < 1.4D + 2.0D / (this.dimension + 1.0D));
/*  641 */     this.pc = this.pc.scalarMultiply(1.0D - this.cc);
/*  642 */     if (hsig)
/*  643 */       this.pc = this.pc.add(this.xmean.subtract(xold).scalarMultiply(Math.sqrt(this.cc * (2.0D - this.cc) * this.mueff) / this.sigma)); 
/*  646 */     return hsig;
/*      */   }
/*      */   
/*      */   private void updateCovarianceDiagonalOnly(boolean hsig, RealMatrix bestArz, RealMatrix xold) {
/*  661 */     double oldFac = hsig ? 0.0D : (this.ccov1Sep * this.cc * (2.0D - this.cc));
/*  662 */     oldFac += 1.0D - this.ccov1Sep - this.ccovmuSep;
/*  663 */     this.diagC = this.diagC.scalarMultiply(oldFac).add(square(this.pc).scalarMultiply(this.ccov1Sep)).add(times(this.diagC, square(bestArz).multiply(this.weights)).scalarMultiply(this.ccovmuSep));
/*  669 */     this.diagD = sqrt(this.diagC);
/*  670 */     if (this.diagonalOnly > 1 && this.iterations > this.diagonalOnly) {
/*  672 */       this.diagonalOnly = 0;
/*  673 */       this.B = eye(this.dimension, this.dimension);
/*  674 */       this.BD = diag(this.diagD);
/*  675 */       this.C = diag(this.diagC);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void updateCovariance(boolean hsig, RealMatrix bestArx, RealMatrix arz, int[] arindex, RealMatrix xold) {
/*  692 */     double negccov = 0.0D;
/*  693 */     if (this.ccov1 + this.ccovmu > 0.0D) {
/*  694 */       RealMatrix arpos = bestArx.subtract(repmat(xold, 1, this.mu)).scalarMultiply(1.0D / this.sigma);
/*  696 */       RealMatrix roneu = this.pc.multiply(this.pc.transpose()).scalarMultiply(this.ccov1);
/*  699 */       double oldFac = hsig ? 0.0D : (this.ccov1 * this.cc * (2.0D - this.cc));
/*  700 */       oldFac += 1.0D - this.ccov1 - this.ccovmu;
/*  701 */       if (this.isActiveCMA) {
/*  703 */         negccov = (1.0D - this.ccovmu) * 0.25D * this.mueff / (Math.pow(this.dimension + 2.0D, 1.5D) + 2.0D * this.mueff);
/*  705 */         double negminresidualvariance = 0.66D;
/*  708 */         double negalphaold = 0.5D;
/*  711 */         int[] arReverseIndex = reverse(arindex);
/*  712 */         RealMatrix arzneg = selectColumns(arz, MathArrays.copyOf(arReverseIndex, this.mu));
/*  714 */         RealMatrix arnorms = sqrt(sumRows(square(arzneg)));
/*  715 */         int[] idxnorms = sortedIndices(arnorms.getRow(0));
/*  716 */         RealMatrix arnormsSorted = selectColumns(arnorms, idxnorms);
/*  717 */         int[] idxReverse = reverse(idxnorms);
/*  718 */         RealMatrix arnormsReverse = selectColumns(arnorms, idxReverse);
/*  719 */         arnorms = divide(arnormsReverse, arnormsSorted);
/*  720 */         int[] idxInv = inverse(idxnorms);
/*  721 */         RealMatrix arnormsInv = selectColumns(arnorms, idxInv);
/*  723 */         double negcovMax = (1.0D - negminresidualvariance) / square(arnormsInv).multiply(this.weights).getEntry(0, 0);
/*  725 */         if (negccov > negcovMax)
/*  726 */           negccov = negcovMax; 
/*  728 */         arzneg = times(arzneg, repmat(arnormsInv, this.dimension, 1));
/*  729 */         RealMatrix artmp = this.BD.multiply(arzneg);
/*  730 */         RealMatrix Cneg = artmp.multiply(diag(this.weights)).multiply(artmp.transpose());
/*  732 */         oldFac += negalphaold * negccov;
/*  733 */         this.C = this.C.scalarMultiply(oldFac).add(roneu).add(arpos.scalarMultiply(this.ccovmu + (1.0D - negalphaold) * negccov).multiply(times(repmat(this.weights, 1, this.dimension), arpos.transpose()))).subtract(Cneg.scalarMultiply(negccov));
/*      */       } else {
/*  746 */         this.C = this.C.scalarMultiply(oldFac).add(roneu).add(arpos.scalarMultiply(this.ccovmu).multiply(times(repmat(this.weights, 1, this.dimension), arpos.transpose())));
/*      */       } 
/*      */     } 
/*  755 */     updateBD(negccov);
/*      */   }
/*      */   
/*      */   private void updateBD(double negccov) {
/*  764 */     if (this.ccov1 + this.ccovmu + negccov > 0.0D && this.iterations % 1.0D / (this.ccov1 + this.ccovmu + negccov) / this.dimension / 10.0D < 1.0D) {
/*  767 */       this.C = triu(this.C, 0).add(triu(this.C, 1).transpose());
/*  769 */       EigenDecomposition eig = new EigenDecomposition(this.C, 1.0D);
/*  770 */       this.B = eig.getV();
/*  771 */       this.D = eig.getD();
/*  772 */       this.diagD = diag(this.D);
/*  773 */       if (min(this.diagD) <= 0.0D) {
/*  774 */         for (int i = 0; i < this.dimension; i++) {
/*  775 */           if (this.diagD.getEntry(i, 0) < 0.0D)
/*  776 */             this.diagD.setEntry(i, 0, 0.0D); 
/*      */         } 
/*  779 */         double tfac = max(this.diagD) / 1.0E14D;
/*  780 */         this.C = this.C.add(eye(this.dimension, this.dimension).scalarMultiply(tfac));
/*  781 */         this.diagD = this.diagD.add(ones(this.dimension, 1).scalarMultiply(tfac));
/*      */       } 
/*  783 */       if (max(this.diagD) > 1.0E14D * min(this.diagD)) {
/*  784 */         double tfac = max(this.diagD) / 1.0E14D - min(this.diagD);
/*  785 */         this.C = this.C.add(eye(this.dimension, this.dimension).scalarMultiply(tfac));
/*  786 */         this.diagD = this.diagD.add(ones(this.dimension, 1).scalarMultiply(tfac));
/*      */       } 
/*  788 */       this.diagC = diag(this.C);
/*  789 */       this.diagD = sqrt(this.diagD);
/*  790 */       this.BD = times(this.B, repmat(this.diagD.transpose(), this.dimension, 1));
/*      */     } 
/*      */   }
/*      */   
/*      */   private static void push(double[] vals, double val) {
/*  801 */     for (int i = vals.length - 1; i > 0; i--)
/*  802 */       vals[i] = vals[i - 1]; 
/*  804 */     vals[0] = val;
/*      */   }
/*      */   
/*      */   private int[] sortedIndices(double[] doubles) {
/*  814 */     DoubleIndex[] dis = new DoubleIndex[doubles.length];
/*  815 */     for (int i = 0; i < doubles.length; i++)
/*  816 */       dis[i] = new DoubleIndex(doubles[i], i); 
/*  818 */     Arrays.sort((Object[])dis);
/*  819 */     int[] indices = new int[doubles.length];
/*  820 */     for (int j = 0; j < doubles.length; j++)
/*  821 */       indices[j] = (dis[j]).index; 
/*  823 */     return indices;
/*      */   }
/*      */   
/*      */   private static class DoubleIndex implements Comparable<DoubleIndex> {
/*      */     private double value;
/*      */     
/*      */     private int index;
/*      */     
/*      */     DoubleIndex(double value, int index) {
/*  841 */       this.value = value;
/*  842 */       this.index = index;
/*      */     }
/*      */     
/*      */     public int compareTo(DoubleIndex o) {
/*  847 */       return Double.compare(this.value, o.value);
/*      */     }
/*      */     
/*      */     public boolean equals(Object other) {
/*  854 */       if (this == other)
/*  855 */         return true; 
/*  858 */       if (other instanceof DoubleIndex)
/*  859 */         return (Double.compare(this.value, ((DoubleIndex)other).value) == 0); 
/*  862 */       return false;
/*      */     }
/*      */     
/*      */     public int hashCode() {
/*  869 */       long bits = Double.doubleToLongBits(this.value);
/*  870 */       return (int)((0x15F34EL ^ bits >>> 32L ^ bits) & 0xFFFFFFFFFFFFFFFFL);
/*      */     }
/*      */   }
/*      */   
/*      */   private class FitnessFunction {
/*  892 */     private double valueRange = 1.0D;
/*      */     
/*      */     private boolean isRepairMode = true;
/*      */     
/*      */     public double[] encode(double[] x) {
/*  901 */       if (CMAESOptimizer.this.boundaries == null)
/*  902 */         return x; 
/*  904 */       double[] res = new double[x.length];
/*  905 */       for (int i = 0; i < x.length; i++) {
/*  906 */         double diff = CMAESOptimizer.this.boundaries[1][i] - CMAESOptimizer.this.boundaries[0][i];
/*  907 */         res[i] = (x[i] - CMAESOptimizer.this.boundaries[0][i]) / diff;
/*      */       } 
/*  909 */       return res;
/*      */     }
/*      */     
/*      */     public double[] decode(double[] x) {
/*  917 */       if (CMAESOptimizer.this.boundaries == null)
/*  918 */         return x; 
/*  920 */       double[] res = new double[x.length];
/*  921 */       for (int i = 0; i < x.length; i++) {
/*  922 */         double diff = CMAESOptimizer.this.boundaries[1][i] - CMAESOptimizer.this.boundaries[0][i];
/*  923 */         res[i] = diff * x[i] + CMAESOptimizer.this.boundaries[0][i];
/*      */       } 
/*  925 */       return res;
/*      */     }
/*      */     
/*      */     public double value(double[] point) {
/*      */       double value;
/*  934 */       if (CMAESOptimizer.this.boundaries != null && this.isRepairMode) {
/*  935 */         double[] repaired = repair(point);
/*  936 */         value = CMAESOptimizer.this.computeObjectiveValue(decode(repaired)) + penalty(point, repaired);
/*      */       } else {
/*  940 */         value = CMAESOptimizer.this.computeObjectiveValue(decode(point));
/*      */       } 
/*  943 */       return CMAESOptimizer.this.isMinimize ? value : -value;
/*      */     }
/*      */     
/*      */     public boolean isFeasible(double[] x) {
/*  951 */       if (CMAESOptimizer.this.boundaries == null)
/*  952 */         return true; 
/*  954 */       for (int i = 0; i < x.length; i++) {
/*  955 */         if (x[i] < 0.0D)
/*  956 */           return false; 
/*  958 */         if (x[i] > 1.0D)
/*  959 */           return false; 
/*      */       } 
/*  962 */       return true;
/*      */     }
/*      */     
/*      */     public void setValueRange(double valueRange) {
/*  969 */       this.valueRange = valueRange;
/*      */     }
/*      */     
/*      */     private double[] repair(double[] x) {
/*  977 */       double[] repaired = new double[x.length];
/*  978 */       for (int i = 0; i < x.length; i++) {
/*  979 */         if (x[i] < 0.0D) {
/*  980 */           repaired[i] = 0.0D;
/*  981 */         } else if (x[i] > 1.0D) {
/*  982 */           repaired[i] = 1.0D;
/*      */         } else {
/*  984 */           repaired[i] = x[i];
/*      */         } 
/*      */       } 
/*  987 */       return repaired;
/*      */     }
/*      */     
/*      */     private double penalty(double[] x, double[] repaired) {
/*  996 */       double penalty = 0.0D;
/*  997 */       for (int i = 0; i < x.length; i++) {
/*  998 */         double diff = Math.abs(x[i] - repaired[i]);
/*  999 */         penalty += diff * this.valueRange;
/*      */       } 
/* 1001 */       return CMAESOptimizer.this.isMinimize ? penalty : -penalty;
/*      */     }
/*      */   }
/*      */   
/*      */   private static RealMatrix log(RealMatrix m) {
/* 1012 */     double[][] d = new double[m.getRowDimension()][m.getColumnDimension()];
/* 1013 */     for (int r = 0; r < m.getRowDimension(); r++) {
/* 1014 */       for (int c = 0; c < m.getColumnDimension(); c++)
/* 1015 */         d[r][c] = Math.log(m.getEntry(r, c)); 
/*      */     } 
/* 1018 */     return (RealMatrix)new Array2DRowRealMatrix(d, false);
/*      */   }
/*      */   
/*      */   private static RealMatrix sqrt(RealMatrix m) {
/* 1027 */     double[][] d = new double[m.getRowDimension()][m.getColumnDimension()];
/* 1028 */     for (int r = 0; r < m.getRowDimension(); r++) {
/* 1029 */       for (int c = 0; c < m.getColumnDimension(); c++)
/* 1030 */         d[r][c] = Math.sqrt(m.getEntry(r, c)); 
/*      */     } 
/* 1033 */     return (RealMatrix)new Array2DRowRealMatrix(d, false);
/*      */   }
/*      */   
/*      */   private static RealMatrix square(RealMatrix m) {
/* 1041 */     double[][] d = new double[m.getRowDimension()][m.getColumnDimension()];
/* 1042 */     for (int r = 0; r < m.getRowDimension(); r++) {
/* 1043 */       for (int c = 0; c < m.getColumnDimension(); c++) {
/* 1044 */         double e = m.getEntry(r, c);
/* 1045 */         d[r][c] = e * e;
/*      */       } 
/*      */     } 
/* 1048 */     return (RealMatrix)new Array2DRowRealMatrix(d, false);
/*      */   }
/*      */   
/*      */   private static RealMatrix times(RealMatrix m, RealMatrix n) {
/* 1057 */     double[][] d = new double[m.getRowDimension()][m.getColumnDimension()];
/* 1058 */     for (int r = 0; r < m.getRowDimension(); r++) {
/* 1059 */       for (int c = 0; c < m.getColumnDimension(); c++)
/* 1060 */         d[r][c] = m.getEntry(r, c) * n.getEntry(r, c); 
/*      */     } 
/* 1063 */     return (RealMatrix)new Array2DRowRealMatrix(d, false);
/*      */   }
/*      */   
/*      */   private static RealMatrix divide(RealMatrix m, RealMatrix n) {
/* 1072 */     double[][] d = new double[m.getRowDimension()][m.getColumnDimension()];
/* 1073 */     for (int r = 0; r < m.getRowDimension(); r++) {
/* 1074 */       for (int c = 0; c < m.getColumnDimension(); c++)
/* 1075 */         d[r][c] = m.getEntry(r, c) / n.getEntry(r, c); 
/*      */     } 
/* 1078 */     return (RealMatrix)new Array2DRowRealMatrix(d, false);
/*      */   }
/*      */   
/*      */   private static RealMatrix selectColumns(RealMatrix m, int[] cols) {
/* 1087 */     double[][] d = new double[m.getRowDimension()][cols.length];
/* 1088 */     for (int r = 0; r < m.getRowDimension(); r++) {
/* 1089 */       for (int c = 0; c < cols.length; c++)
/* 1090 */         d[r][c] = m.getEntry(r, cols[c]); 
/*      */     } 
/* 1093 */     return (RealMatrix)new Array2DRowRealMatrix(d, false);
/*      */   }
/*      */   
/*      */   private static RealMatrix triu(RealMatrix m, int k) {
/* 1102 */     double[][] d = new double[m.getRowDimension()][m.getColumnDimension()];
/* 1103 */     for (int r = 0; r < m.getRowDimension(); r++) {
/* 1104 */       for (int c = 0; c < m.getColumnDimension(); c++)
/* 1105 */         d[r][c] = (r <= c - k) ? m.getEntry(r, c) : 0.0D; 
/*      */     } 
/* 1108 */     return (RealMatrix)new Array2DRowRealMatrix(d, false);
/*      */   }
/*      */   
/*      */   private static RealMatrix sumRows(RealMatrix m) {
/* 1116 */     double[][] d = new double[1][m.getColumnDimension()];
/* 1117 */     for (int c = 0; c < m.getColumnDimension(); c++) {
/* 1118 */       double sum = 0.0D;
/* 1119 */       for (int r = 0; r < m.getRowDimension(); r++)
/* 1120 */         sum += m.getEntry(r, c); 
/* 1122 */       d[0][c] = sum;
/*      */     } 
/* 1124 */     return (RealMatrix)new Array2DRowRealMatrix(d, false);
/*      */   }
/*      */   
/*      */   private static RealMatrix diag(RealMatrix m) {
/* 1133 */     if (m.getColumnDimension() == 1) {
/* 1134 */       double[][] arrayOfDouble = new double[m.getRowDimension()][m.getRowDimension()];
/* 1135 */       for (int j = 0; j < m.getRowDimension(); j++)
/* 1136 */         arrayOfDouble[j][j] = m.getEntry(j, 0); 
/* 1138 */       return (RealMatrix)new Array2DRowRealMatrix(arrayOfDouble, false);
/*      */     } 
/* 1140 */     double[][] d = new double[m.getRowDimension()][1];
/* 1141 */     for (int i = 0; i < m.getColumnDimension(); i++)
/* 1142 */       d[i][0] = m.getEntry(i, i); 
/* 1144 */     return (RealMatrix)new Array2DRowRealMatrix(d, false);
/*      */   }
/*      */   
/*      */   private static void copyColumn(RealMatrix m1, int col1, RealMatrix m2, int col2) {
/* 1157 */     for (int i = 0; i < m1.getRowDimension(); i++)
/* 1158 */       m2.setEntry(i, col2, m1.getEntry(i, col1)); 
/*      */   }
/*      */   
/*      */   private static RealMatrix ones(int n, int m) {
/* 1168 */     double[][] d = new double[n][m];
/* 1169 */     for (int r = 0; r < n; r++)
/* 1170 */       Arrays.fill(d[r], 1.0D); 
/* 1172 */     return (RealMatrix)new Array2DRowRealMatrix(d, false);
/*      */   }
/*      */   
/*      */   private static RealMatrix eye(int n, int m) {
/* 1181 */     double[][] d = new double[n][m];
/* 1182 */     for (int r = 0; r < n; r++) {
/* 1183 */       if (r < m)
/* 1184 */         d[r][r] = 1.0D; 
/*      */     } 
/* 1187 */     return (RealMatrix)new Array2DRowRealMatrix(d, false);
/*      */   }
/*      */   
/*      */   private static RealMatrix zeros(int n, int m) {
/* 1196 */     return (RealMatrix)new Array2DRowRealMatrix(n, m);
/*      */   }
/*      */   
/*      */   private static RealMatrix repmat(RealMatrix mat, int n, int m) {
/* 1206 */     int rd = mat.getRowDimension();
/* 1207 */     int cd = mat.getColumnDimension();
/* 1208 */     double[][] d = new double[n * rd][m * cd];
/* 1209 */     for (int r = 0; r < n * rd; r++) {
/* 1210 */       for (int c = 0; c < m * cd; c++)
/* 1211 */         d[r][c] = mat.getEntry(r % rd, c % cd); 
/*      */     } 
/* 1214 */     return (RealMatrix)new Array2DRowRealMatrix(d, false);
/*      */   }
/*      */   
/*      */   private static RealMatrix sequence(double start, double end, double step) {
/* 1224 */     int size = (int)((end - start) / step + 1.0D);
/* 1225 */     double[][] d = new double[size][1];
/* 1226 */     double value = start;
/* 1227 */     for (int r = 0; r < size; r++) {
/* 1228 */       d[r][0] = value;
/* 1229 */       value += step;
/*      */     } 
/* 1231 */     return (RealMatrix)new Array2DRowRealMatrix(d, false);
/*      */   }
/*      */   
/*      */   private static double max(RealMatrix m) {
/* 1239 */     double max = -1.7976931348623157E308D;
/* 1240 */     for (int r = 0; r < m.getRowDimension(); r++) {
/* 1241 */       for (int c = 0; c < m.getColumnDimension(); c++) {
/* 1242 */         double e = m.getEntry(r, c);
/* 1243 */         if (max < e)
/* 1244 */           max = e; 
/*      */       } 
/*      */     } 
/* 1248 */     return max;
/*      */   }
/*      */   
/*      */   private static double min(RealMatrix m) {
/* 1256 */     double min = Double.MAX_VALUE;
/* 1257 */     for (int r = 0; r < m.getRowDimension(); r++) {
/* 1258 */       for (int c = 0; c < m.getColumnDimension(); c++) {
/* 1259 */         double e = m.getEntry(r, c);
/* 1260 */         if (min > e)
/* 1261 */           min = e; 
/*      */       } 
/*      */     } 
/* 1265 */     return min;
/*      */   }
/*      */   
/*      */   private static double max(double[] m) {
/* 1273 */     double max = -1.7976931348623157E308D;
/* 1274 */     for (int r = 0; r < m.length; r++) {
/* 1275 */       if (max < m[r])
/* 1276 */         max = m[r]; 
/*      */     } 
/* 1279 */     return max;
/*      */   }
/*      */   
/*      */   private static double min(double[] m) {
/* 1287 */     double min = Double.MAX_VALUE;
/* 1288 */     for (int r = 0; r < m.length; r++) {
/* 1289 */       if (min > m[r])
/* 1290 */         min = m[r]; 
/*      */     } 
/* 1293 */     return min;
/*      */   }
/*      */   
/*      */   private static int[] inverse(int[] indices) {
/* 1301 */     int[] inverse = new int[indices.length];
/* 1302 */     for (int i = 0; i < indices.length; i++)
/* 1303 */       inverse[indices[i]] = i; 
/* 1305 */     return inverse;
/*      */   }
/*      */   
/*      */   private static int[] reverse(int[] indices) {
/* 1313 */     int[] reverse = new int[indices.length];
/* 1314 */     for (int i = 0; i < indices.length; i++)
/* 1315 */       reverse[i] = indices[indices.length - i - 1]; 
/* 1317 */     return reverse;
/*      */   }
/*      */   
/*      */   private double[] randn(int size) {
/* 1325 */     double[] randn = new double[size];
/* 1326 */     for (int i = 0; i < size; i++)
/* 1327 */       randn[i] = this.random.nextGaussian(); 
/* 1329 */     return randn;
/*      */   }
/*      */   
/*      */   private RealMatrix randn1(int size, int popSize) {
/* 1338 */     double[][] d = new double[size][popSize];
/* 1339 */     for (int r = 0; r < size; r++) {
/* 1340 */       for (int c = 0; c < popSize; c++)
/* 1341 */         d[r][c] = this.random.nextGaussian(); 
/*      */     } 
/* 1344 */     return (RealMatrix)new Array2DRowRealMatrix(d, false);
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\optimization\direct\CMAESOptimizer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
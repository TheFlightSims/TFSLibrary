/*     */ package org.apache.commons.math3.optimization.general;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import org.apache.commons.math3.exception.ConvergenceException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.optimization.ConvergenceChecker;
/*     */ import org.apache.commons.math3.optimization.PointVectorValuePair;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class LevenbergMarquardtOptimizer extends AbstractLeastSquaresOptimizer {
/*     */   private int solvedCols;
/*     */   
/*     */   private double[] diagR;
/*     */   
/*     */   private double[] jacNorm;
/*     */   
/*     */   private double[] beta;
/*     */   
/*     */   private int[] permutation;
/*     */   
/*     */   private int rank;
/*     */   
/*     */   private double lmPar;
/*     */   
/*     */   private double[] lmDir;
/*     */   
/*     */   private final double initialStepBoundFactor;
/*     */   
/*     */   private final double costRelativeTolerance;
/*     */   
/*     */   private final double parRelativeTolerance;
/*     */   
/*     */   private final double orthoTolerance;
/*     */   
/*     */   private final double qrRankingThreshold;
/*     */   
/*     */   public LevenbergMarquardtOptimizer() {
/* 151 */     this(100.0D, 1.0E-10D, 1.0E-10D, 1.0E-10D, 2.2250738585072014E-308D);
/*     */   }
/*     */   
/*     */   public LevenbergMarquardtOptimizer(ConvergenceChecker<PointVectorValuePair> checker) {
/* 170 */     this(100.0D, checker, 1.0E-10D, 1.0E-10D, 1.0E-10D, 2.2250738585072014E-308D);
/*     */   }
/*     */   
/*     */   public LevenbergMarquardtOptimizer(double initialStepBoundFactor, ConvergenceChecker<PointVectorValuePair> checker, double costRelativeTolerance, double parRelativeTolerance, double orthoTolerance, double threshold) {
/* 201 */     super(checker);
/* 202 */     this.initialStepBoundFactor = initialStepBoundFactor;
/* 203 */     this.costRelativeTolerance = costRelativeTolerance;
/* 204 */     this.parRelativeTolerance = parRelativeTolerance;
/* 205 */     this.orthoTolerance = orthoTolerance;
/* 206 */     this.qrRankingThreshold = threshold;
/*     */   }
/*     */   
/*     */   public LevenbergMarquardtOptimizer(double costRelativeTolerance, double parRelativeTolerance, double orthoTolerance) {
/* 230 */     this(100.0D, costRelativeTolerance, parRelativeTolerance, orthoTolerance, 2.2250738585072014E-308D);
/*     */   }
/*     */   
/*     */   public LevenbergMarquardtOptimizer(double initialStepBoundFactor, double costRelativeTolerance, double parRelativeTolerance, double orthoTolerance, double threshold) {
/* 263 */     this.initialStepBoundFactor = initialStepBoundFactor;
/* 264 */     this.costRelativeTolerance = costRelativeTolerance;
/* 265 */     this.parRelativeTolerance = parRelativeTolerance;
/* 266 */     this.orthoTolerance = orthoTolerance;
/* 267 */     this.qrRankingThreshold = threshold;
/*     */   }
/*     */   
/*     */   protected PointVectorValuePair doOptimize() {
/* 274 */     this.solvedCols = FastMath.min(this.rows, this.cols);
/* 275 */     this.diagR = new double[this.cols];
/* 276 */     this.jacNorm = new double[this.cols];
/* 277 */     this.beta = new double[this.cols];
/* 278 */     this.permutation = new int[this.cols];
/* 279 */     this.lmDir = new double[this.cols];
/* 282 */     double delta = 0.0D;
/* 283 */     double xNorm = 0.0D;
/* 284 */     double[] diag = new double[this.cols];
/* 285 */     double[] oldX = new double[this.cols];
/* 286 */     double[] oldRes = new double[this.rows];
/* 287 */     double[] oldObj = new double[this.rows];
/* 288 */     double[] qtf = new double[this.rows];
/* 289 */     double[] work1 = new double[this.cols];
/* 290 */     double[] work2 = new double[this.cols];
/* 291 */     double[] work3 = new double[this.cols];
/* 294 */     updateResidualsAndCost();
/* 297 */     this.lmPar = 0.0D;
/* 298 */     boolean firstIteration = true;
/* 299 */     PointVectorValuePair current = new PointVectorValuePair(this.point, this.objective);
/* 300 */     int iter = 0;
/* 301 */     ConvergenceChecker<PointVectorValuePair> checker = getConvergenceChecker();
/*     */     while (true) {
/* 303 */       iter++;
/* 305 */       for (int i = 0; i < this.rows; i++)
/* 306 */         qtf[i] = this.weightedResiduals[i]; 
/* 310 */       PointVectorValuePair previous = current;
/* 311 */       updateJacobian();
/* 312 */       qrDecomposition();
/* 315 */       qTy(qtf);
/*     */       int k;
/* 318 */       for (k = 0; k < this.solvedCols; k++) {
/* 319 */         int pk = this.permutation[k];
/* 320 */         this.weightedResidualJacobian[k][pk] = this.diagR[pk];
/*     */       } 
/* 323 */       if (firstIteration) {
/* 326 */         xNorm = 0.0D;
/* 327 */         for (k = 0; k < this.cols; k++) {
/* 328 */           double dk = this.jacNorm[k];
/* 329 */           if (dk == 0.0D)
/* 330 */             dk = 1.0D; 
/* 332 */           double xk = dk * this.point[k];
/* 333 */           xNorm += xk * xk;
/* 334 */           diag[k] = dk;
/*     */         } 
/* 336 */         xNorm = FastMath.sqrt(xNorm);
/* 339 */         delta = (xNorm == 0.0D) ? this.initialStepBoundFactor : (this.initialStepBoundFactor * xNorm);
/*     */       } 
/* 343 */       double maxCosine = 0.0D;
/* 344 */       if (this.cost != 0.0D)
/* 345 */         for (int m = 0; m < this.solvedCols; m++) {
/* 346 */           int pj = this.permutation[m];
/* 347 */           double s = this.jacNorm[pj];
/* 348 */           if (s != 0.0D) {
/* 349 */             double sum = 0.0D;
/* 350 */             for (int n = 0; n <= m; n++)
/* 351 */               sum += this.weightedResidualJacobian[n][pj] * qtf[n]; 
/* 353 */             maxCosine = FastMath.max(maxCosine, FastMath.abs(sum) / s * this.cost);
/*     */           } 
/*     */         }  
/* 357 */       if (maxCosine <= this.orthoTolerance) {
/* 359 */         updateResidualsAndCost();
/* 360 */         current = new PointVectorValuePair(this.point, this.objective);
/* 361 */         return current;
/*     */       } 
/* 365 */       for (int j = 0; j < this.cols; j++)
/* 366 */         diag[j] = FastMath.max(diag[j], this.jacNorm[j]); 
/* 370 */       for (double ratio = 0.0D; ratio < 1.0E-4D; ) {
/* 373 */         for (int m = 0; m < this.solvedCols; m++) {
/* 374 */           int pj = this.permutation[m];
/* 375 */           oldX[pj] = this.point[pj];
/*     */         } 
/* 377 */         double previousCost = this.cost;
/* 378 */         double[] tmpVec = this.weightedResiduals;
/* 379 */         this.weightedResiduals = oldRes;
/* 380 */         oldRes = tmpVec;
/* 381 */         tmpVec = this.objective;
/* 382 */         this.objective = oldObj;
/* 383 */         oldObj = tmpVec;
/* 386 */         determineLMParameter(qtf, delta, diag, work1, work2, work3);
/* 389 */         double lmNorm = 0.0D;
/* 390 */         for (int n = 0; n < this.solvedCols; n++) {
/* 391 */           int pj = this.permutation[n];
/* 392 */           this.lmDir[pj] = -this.lmDir[pj];
/* 393 */           this.point[pj] = oldX[pj] + this.lmDir[pj];
/* 394 */           double s = diag[pj] * this.lmDir[pj];
/* 395 */           lmNorm += s * s;
/*     */         } 
/* 397 */         lmNorm = FastMath.sqrt(lmNorm);
/* 399 */         if (firstIteration)
/* 400 */           delta = FastMath.min(delta, lmNorm); 
/* 404 */         updateResidualsAndCost();
/* 407 */         double actRed = -1.0D;
/* 408 */         if (0.1D * this.cost < previousCost) {
/* 409 */           double r = this.cost / previousCost;
/* 410 */           actRed = 1.0D - r * r;
/*     */         } 
/* 415 */         for (int i1 = 0; i1 < this.solvedCols; i1++) {
/* 416 */           int pj = this.permutation[i1];
/* 417 */           double dirJ = this.lmDir[pj];
/* 418 */           work1[i1] = 0.0D;
/* 419 */           for (int i3 = 0; i3 <= i1; i3++)
/* 420 */             work1[i3] = work1[i3] + this.weightedResidualJacobian[i3][pj] * dirJ; 
/*     */         } 
/* 423 */         double coeff1 = 0.0D;
/* 424 */         for (int i2 = 0; i2 < this.solvedCols; i2++)
/* 425 */           coeff1 += work1[i2] * work1[i2]; 
/* 427 */         double pc2 = previousCost * previousCost;
/* 428 */         coeff1 /= pc2;
/* 429 */         double coeff2 = this.lmPar * lmNorm * lmNorm / pc2;
/* 430 */         double preRed = coeff1 + 2.0D * coeff2;
/* 431 */         double dirDer = -(coeff1 + coeff2);
/* 434 */         ratio = (preRed == 0.0D) ? 0.0D : (actRed / preRed);
/* 437 */         if (ratio <= 0.25D) {
/* 438 */           double tmp = (actRed < 0.0D) ? (0.5D * dirDer / (dirDer + 0.5D * actRed)) : 0.5D;
/* 440 */           if (0.1D * this.cost >= previousCost || tmp < 0.1D)
/* 441 */             tmp = 0.1D; 
/* 443 */           delta = tmp * FastMath.min(delta, 10.0D * lmNorm);
/* 444 */           this.lmPar /= tmp;
/* 445 */         } else if (this.lmPar == 0.0D || ratio >= 0.75D) {
/* 446 */           delta = 2.0D * lmNorm;
/* 447 */           this.lmPar *= 0.5D;
/*     */         } 
/* 451 */         if (ratio >= 1.0E-4D) {
/* 453 */           firstIteration = false;
/* 454 */           xNorm = 0.0D;
/* 455 */           for (int i3 = 0; i3 < this.cols; i3++) {
/* 456 */             double xK = diag[i3] * this.point[i3];
/* 457 */             xNorm += xK * xK;
/*     */           } 
/* 459 */           xNorm = FastMath.sqrt(xNorm);
/* 460 */           current = new PointVectorValuePair(this.point, this.objective);
/* 463 */           if (checker != null)
/* 465 */             if (checker.converged(iter, previous, current))
/* 466 */               return current;  
/*     */         } else {
/* 471 */           this.cost = previousCost;
/* 472 */           for (int i3 = 0; i3 < this.solvedCols; i3++) {
/* 473 */             int pj = this.permutation[i3];
/* 474 */             this.point[pj] = oldX[pj];
/*     */           } 
/* 476 */           tmpVec = this.weightedResiduals;
/* 477 */           this.weightedResiduals = oldRes;
/* 478 */           oldRes = tmpVec;
/* 479 */           tmpVec = this.objective;
/* 480 */           this.objective = oldObj;
/* 481 */           oldObj = tmpVec;
/*     */         } 
/* 485 */         if ((FastMath.abs(actRed) <= this.costRelativeTolerance && preRed <= this.costRelativeTolerance && ratio <= 2.0D) || delta <= this.parRelativeTolerance * xNorm)
/* 489 */           return current; 
/* 494 */         if (FastMath.abs(actRed) <= 2.2204E-16D && preRed <= 2.2204E-16D && ratio <= 2.0D)
/* 495 */           throw new ConvergenceException(LocalizedFormats.TOO_SMALL_COST_RELATIVE_TOLERANCE, new Object[] { Double.valueOf(this.costRelativeTolerance) }); 
/* 497 */         if (delta <= 2.2204E-16D * xNorm)
/* 498 */           throw new ConvergenceException(LocalizedFormats.TOO_SMALL_PARAMETERS_RELATIVE_TOLERANCE, new Object[] { Double.valueOf(this.parRelativeTolerance) }); 
/* 500 */         if (maxCosine <= 2.2204E-16D)
/* 501 */           throw new ConvergenceException(LocalizedFormats.TOO_SMALL_ORTHOGONALITY_TOLERANCE, new Object[] { Double.valueOf(this.orthoTolerance) }); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void determineLMParameter(double[] qy, double delta, double[] diag, double[] work1, double[] work2, double[] work3) {
/*     */     int j;
/* 535 */     for (j = 0; j < this.rank; j++)
/* 536 */       this.lmDir[this.permutation[j]] = qy[j]; 
/* 538 */     for (j = this.rank; j < this.cols; j++)
/* 539 */       this.lmDir[this.permutation[j]] = 0.0D; 
/* 541 */     for (int k = this.rank - 1; k >= 0; k--) {
/* 542 */       int pk = this.permutation[k];
/* 543 */       double ypk = this.lmDir[pk] / this.diagR[pk];
/* 544 */       for (int n = 0; n < k; n++)
/* 545 */         this.lmDir[this.permutation[n]] = this.lmDir[this.permutation[n]] - ypk * this.weightedResidualJacobian[n][pk]; 
/* 547 */       this.lmDir[pk] = ypk;
/*     */     } 
/* 552 */     double dxNorm = 0.0D;
/* 553 */     for (int i = 0; i < this.solvedCols; i++) {
/* 554 */       int pj = this.permutation[i];
/* 555 */       double s = diag[pj] * this.lmDir[pj];
/* 556 */       work1[pj] = s;
/* 557 */       dxNorm += s * s;
/*     */     } 
/* 559 */     dxNorm = FastMath.sqrt(dxNorm);
/* 560 */     double fp = dxNorm - delta;
/* 561 */     if (fp <= 0.1D * delta) {
/* 562 */       this.lmPar = 0.0D;
/*     */       return;
/*     */     } 
/* 570 */     double parl = 0.0D;
/* 571 */     if (this.rank == this.solvedCols) {
/*     */       int n;
/* 572 */       for (n = 0; n < this.solvedCols; n++) {
/* 573 */         int pj = this.permutation[n];
/* 574 */         work1[pj] = work1[pj] * diag[pj] / dxNorm;
/*     */       } 
/* 576 */       double d = 0.0D;
/* 577 */       for (n = 0; n < this.solvedCols; n++) {
/* 578 */         int pj = this.permutation[n];
/* 579 */         double sum = 0.0D;
/* 580 */         for (int i1 = 0; i1 < n; i1++)
/* 581 */           sum += this.weightedResidualJacobian[i1][pj] * work1[this.permutation[i1]]; 
/* 583 */         double s = (work1[pj] - sum) / this.diagR[pj];
/* 584 */         work1[pj] = s;
/* 585 */         d += s * s;
/*     */       } 
/* 587 */       parl = fp / delta * d;
/*     */     } 
/* 591 */     double sum2 = 0.0D;
/* 592 */     for (int m = 0; m < this.solvedCols; m++) {
/* 593 */       int pj = this.permutation[m];
/* 594 */       double sum = 0.0D;
/* 595 */       for (int n = 0; n <= m; n++)
/* 596 */         sum += this.weightedResidualJacobian[n][pj] * qy[n]; 
/* 598 */       sum /= diag[pj];
/* 599 */       sum2 += sum * sum;
/*     */     } 
/* 601 */     double gNorm = FastMath.sqrt(sum2);
/* 602 */     double paru = gNorm / delta;
/* 603 */     if (paru == 0.0D)
/* 605 */       paru = 2.2251E-308D / FastMath.min(delta, 0.1D); 
/* 610 */     this.lmPar = FastMath.min(paru, FastMath.max(this.lmPar, parl));
/* 611 */     if (this.lmPar == 0.0D)
/* 612 */       this.lmPar = gNorm / dxNorm; 
/* 615 */     for (int countdown = 10; countdown >= 0; countdown--) {
/* 618 */       if (this.lmPar == 0.0D)
/* 619 */         this.lmPar = FastMath.max(2.2251E-308D, 0.001D * paru); 
/* 621 */       double sPar = FastMath.sqrt(this.lmPar);
/*     */       int n;
/* 622 */       for (n = 0; n < this.solvedCols; n++) {
/* 623 */         int pj = this.permutation[n];
/* 624 */         work1[pj] = sPar * diag[pj];
/*     */       } 
/* 626 */       determineLMDirection(qy, work1, work2, work3);
/* 628 */       dxNorm = 0.0D;
/* 629 */       for (n = 0; n < this.solvedCols; n++) {
/* 630 */         int pj = this.permutation[n];
/* 631 */         double s = diag[pj] * this.lmDir[pj];
/* 632 */         work3[pj] = s;
/* 633 */         dxNorm += s * s;
/*     */       } 
/* 635 */       dxNorm = FastMath.sqrt(dxNorm);
/* 636 */       double previousFP = fp;
/* 637 */       fp = dxNorm - delta;
/* 641 */       if (FastMath.abs(fp) <= 0.1D * delta || (parl == 0.0D && fp <= previousFP && previousFP < 0.0D))
/*     */         return; 
/*     */       int i1;
/* 647 */       for (i1 = 0; i1 < this.solvedCols; i1++) {
/* 648 */         int pj = this.permutation[i1];
/* 649 */         work1[pj] = work3[pj] * diag[pj] / dxNorm;
/*     */       } 
/* 651 */       for (i1 = 0; i1 < this.solvedCols; i1++) {
/* 652 */         int pj = this.permutation[i1];
/* 653 */         work1[pj] = work1[pj] / work2[i1];
/* 654 */         double tmp = work1[pj];
/* 655 */         for (int i2 = i1 + 1; i2 < this.solvedCols; i2++)
/* 656 */           work1[this.permutation[i2]] = work1[this.permutation[i2]] - this.weightedResidualJacobian[i2][pj] * tmp; 
/*     */       } 
/* 659 */       sum2 = 0.0D;
/* 660 */       for (i1 = 0; i1 < this.solvedCols; i1++) {
/* 661 */         double s = work1[this.permutation[i1]];
/* 662 */         sum2 += s * s;
/*     */       } 
/* 664 */       double correction = fp / delta * sum2;
/* 667 */       if (fp > 0.0D) {
/* 668 */         parl = FastMath.max(parl, this.lmPar);
/* 669 */       } else if (fp < 0.0D) {
/* 670 */         paru = FastMath.min(paru, this.lmPar);
/*     */       } 
/* 674 */       this.lmPar = FastMath.max(parl, this.lmPar + correction);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void determineLMDirection(double[] qy, double[] diag, double[] lmDiag, double[] work) {
/*     */     int j;
/* 704 */     for (j = 0; j < this.solvedCols; j++) {
/* 705 */       int pj = this.permutation[j];
/* 706 */       for (int k = j + 1; k < this.solvedCols; k++)
/* 707 */         this.weightedResidualJacobian[k][pj] = this.weightedResidualJacobian[j][this.permutation[k]]; 
/* 709 */       this.lmDir[j] = this.diagR[pj];
/* 710 */       work[j] = qy[j];
/*     */     } 
/* 714 */     for (j = 0; j < this.solvedCols; j++) {
/* 718 */       int pj = this.permutation[j];
/* 719 */       double dpj = diag[pj];
/* 720 */       if (dpj != 0.0D)
/* 721 */         Arrays.fill(lmDiag, j + 1, lmDiag.length, 0.0D); 
/* 723 */       lmDiag[j] = dpj;
/* 728 */       double qtbpj = 0.0D;
/* 729 */       for (int k = j; k < this.solvedCols; k++) {
/* 730 */         int pk = this.permutation[k];
/* 734 */         if (lmDiag[k] != 0.0D) {
/* 738 */           double sin, cos, rkk = this.weightedResidualJacobian[k][pk];
/* 739 */           if (FastMath.abs(rkk) < FastMath.abs(lmDiag[k])) {
/* 740 */             double cotan = rkk / lmDiag[k];
/* 741 */             sin = 1.0D / FastMath.sqrt(1.0D + cotan * cotan);
/* 742 */             cos = sin * cotan;
/*     */           } else {
/* 744 */             double tan = lmDiag[k] / rkk;
/* 745 */             cos = 1.0D / FastMath.sqrt(1.0D + tan * tan);
/* 746 */             sin = cos * tan;
/*     */           } 
/* 751 */           this.weightedResidualJacobian[k][pk] = cos * rkk + sin * lmDiag[k];
/* 752 */           double temp = cos * work[k] + sin * qtbpj;
/* 753 */           qtbpj = -sin * work[k] + cos * qtbpj;
/* 754 */           work[k] = temp;
/* 757 */           for (int m = k + 1; m < this.solvedCols; m++) {
/* 758 */             double rik = this.weightedResidualJacobian[m][pk];
/* 759 */             double temp2 = cos * rik + sin * lmDiag[m];
/* 760 */             lmDiag[m] = -sin * rik + cos * lmDiag[m];
/* 761 */             this.weightedResidualJacobian[m][pk] = temp2;
/*     */           } 
/*     */         } 
/*     */       } 
/* 768 */       lmDiag[j] = this.weightedResidualJacobian[j][this.permutation[j]];
/* 769 */       this.weightedResidualJacobian[j][this.permutation[j]] = this.lmDir[j];
/*     */     } 
/* 774 */     int nSing = this.solvedCols;
/*     */     int i;
/* 775 */     for (i = 0; i < this.solvedCols; i++) {
/* 776 */       if (lmDiag[i] == 0.0D && nSing == this.solvedCols)
/* 777 */         nSing = i; 
/* 779 */       if (nSing < this.solvedCols)
/* 780 */         work[i] = 0.0D; 
/*     */     } 
/* 783 */     if (nSing > 0)
/* 784 */       for (i = nSing - 1; i >= 0; i--) {
/* 785 */         int pj = this.permutation[i];
/* 786 */         double sum = 0.0D;
/* 787 */         for (int k = i + 1; k < nSing; k++)
/* 788 */           sum += this.weightedResidualJacobian[k][pj] * work[k]; 
/* 790 */         work[i] = (work[i] - sum) / lmDiag[i];
/*     */       }  
/* 795 */     for (i = 0; i < this.lmDir.length; i++)
/* 796 */       this.lmDir[this.permutation[i]] = work[i]; 
/*     */   }
/*     */   
/*     */   private void qrDecomposition() throws ConvergenceException {
/*     */     int k;
/* 825 */     for (k = 0; k < this.cols; k++) {
/* 826 */       this.permutation[k] = k;
/* 827 */       double norm2 = 0.0D;
/* 828 */       for (int i = 0; i < this.weightedResidualJacobian.length; i++) {
/* 829 */         double akk = this.weightedResidualJacobian[i][k];
/* 830 */         norm2 += akk * akk;
/*     */       } 
/* 832 */       this.jacNorm[k] = FastMath.sqrt(norm2);
/*     */     } 
/* 836 */     for (k = 0; k < this.cols; k++) {
/* 839 */       int nextColumn = -1;
/* 840 */       double ak2 = Double.NEGATIVE_INFINITY;
/* 841 */       for (int i = k; i < this.cols; i++) {
/* 842 */         double norm2 = 0.0D;
/* 843 */         for (int j = k; j < this.weightedResidualJacobian.length; j++) {
/* 844 */           double aki = this.weightedResidualJacobian[j][this.permutation[i]];
/* 845 */           norm2 += aki * aki;
/*     */         } 
/* 847 */         if (Double.isInfinite(norm2) || Double.isNaN(norm2))
/* 848 */           throw new ConvergenceException(LocalizedFormats.UNABLE_TO_PERFORM_QR_DECOMPOSITION_ON_JACOBIAN, new Object[] { Integer.valueOf(this.rows), Integer.valueOf(this.cols) }); 
/* 851 */         if (norm2 > ak2) {
/* 852 */           nextColumn = i;
/* 853 */           ak2 = norm2;
/*     */         } 
/*     */       } 
/* 856 */       if (ak2 <= this.qrRankingThreshold) {
/* 857 */         this.rank = k;
/*     */         return;
/*     */       } 
/* 860 */       int pk = this.permutation[nextColumn];
/* 861 */       this.permutation[nextColumn] = this.permutation[k];
/* 862 */       this.permutation[k] = pk;
/* 865 */       double akk = this.weightedResidualJacobian[k][pk];
/* 866 */       double alpha = (akk > 0.0D) ? -FastMath.sqrt(ak2) : FastMath.sqrt(ak2);
/* 867 */       double betak = 1.0D / (ak2 - akk * alpha);
/* 868 */       this.beta[pk] = betak;
/* 871 */       this.diagR[pk] = alpha;
/* 872 */       this.weightedResidualJacobian[k][pk] = this.weightedResidualJacobian[k][pk] - alpha;
/* 875 */       for (int dk = this.cols - 1 - k; dk > 0; dk--) {
/* 876 */         double gamma = 0.0D;
/*     */         int j;
/* 877 */         for (j = k; j < this.weightedResidualJacobian.length; j++)
/* 878 */           gamma += this.weightedResidualJacobian[j][pk] * this.weightedResidualJacobian[j][this.permutation[k + dk]]; 
/* 880 */         gamma *= betak;
/* 881 */         for (j = k; j < this.weightedResidualJacobian.length; j++)
/* 882 */           this.weightedResidualJacobian[j][this.permutation[k + dk]] = this.weightedResidualJacobian[j][this.permutation[k + dk]] - gamma * this.weightedResidualJacobian[j][pk]; 
/*     */       } 
/*     */     } 
/* 886 */     this.rank = this.solvedCols;
/*     */   }
/*     */   
/*     */   private void qTy(double[] y) {
/* 895 */     for (int k = 0; k < this.cols; k++) {
/* 896 */       int pk = this.permutation[k];
/* 897 */       double gamma = 0.0D;
/*     */       int i;
/* 898 */       for (i = k; i < this.rows; i++)
/* 899 */         gamma += this.weightedResidualJacobian[i][pk] * y[i]; 
/* 901 */       gamma *= this.beta[pk];
/* 902 */       for (i = k; i < this.rows; i++)
/* 903 */         y[i] = y[i] - gamma * this.weightedResidualJacobian[i][pk]; 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\optimization\general\LevenbergMarquardtOptimizer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
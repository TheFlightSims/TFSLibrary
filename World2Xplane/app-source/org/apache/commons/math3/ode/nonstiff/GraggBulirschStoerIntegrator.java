/*     */ package org.apache.commons.math3.ode.nonstiff;
/*     */ 
/*     */ import org.apache.commons.math3.analysis.solvers.UnivariateSolver;
/*     */ import org.apache.commons.math3.exception.MathIllegalArgumentException;
/*     */ import org.apache.commons.math3.exception.MathIllegalStateException;
/*     */ import org.apache.commons.math3.ode.ExpandableStatefulODE;
/*     */ import org.apache.commons.math3.ode.events.EventHandler;
/*     */ import org.apache.commons.math3.ode.sampling.AbstractStepInterpolator;
/*     */ import org.apache.commons.math3.ode.sampling.StepHandler;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class GraggBulirschStoerIntegrator extends AdaptiveStepsizeIntegrator {
/*     */   private static final String METHOD_NAME = "Gragg-Bulirsch-Stoer";
/*     */   
/*     */   private int maxOrder;
/*     */   
/*     */   private int[] sequence;
/*     */   
/*     */   private int[] costPerStep;
/*     */   
/*     */   private double[] costPerTimeUnit;
/*     */   
/*     */   private double[] optimalStep;
/*     */   
/*     */   private double[][] coeff;
/*     */   
/*     */   private boolean performTest;
/*     */   
/*     */   private int maxChecks;
/*     */   
/*     */   private int maxIter;
/*     */   
/*     */   private double stabilityReduction;
/*     */   
/*     */   private double stepControl1;
/*     */   
/*     */   private double stepControl2;
/*     */   
/*     */   private double stepControl3;
/*     */   
/*     */   private double stepControl4;
/*     */   
/*     */   private double orderControl1;
/*     */   
/*     */   private double orderControl2;
/*     */   
/*     */   private boolean useInterpolationError;
/*     */   
/*     */   private int mudif;
/*     */   
/*     */   public GraggBulirschStoerIntegrator(double minStep, double maxStep, double scalAbsoluteTolerance, double scalRelativeTolerance) {
/* 170 */     super("Gragg-Bulirsch-Stoer", minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
/* 172 */     setStabilityCheck(true, -1, -1, -1.0D);
/* 173 */     setControlFactors(-1.0D, -1.0D, -1.0D, -1.0D);
/* 174 */     setOrderControl(-1, -1.0D, -1.0D);
/* 175 */     setInterpolationControl(true, -1);
/*     */   }
/*     */   
/*     */   public GraggBulirschStoerIntegrator(double minStep, double maxStep, double[] vecAbsoluteTolerance, double[] vecRelativeTolerance) {
/* 192 */     super("Gragg-Bulirsch-Stoer", minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
/* 194 */     setStabilityCheck(true, -1, -1, -1.0D);
/* 195 */     setControlFactors(-1.0D, -1.0D, -1.0D, -1.0D);
/* 196 */     setOrderControl(-1, -1.0D, -1.0D);
/* 197 */     setInterpolationControl(true, -1);
/*     */   }
/*     */   
/*     */   public void setStabilityCheck(boolean performStabilityCheck, int maxNumIter, int maxNumChecks, double stepsizeReductionFactor) {
/* 222 */     this.performTest = performStabilityCheck;
/* 223 */     this.maxIter = (maxNumIter <= 0) ? 2 : maxNumIter;
/* 224 */     this.maxChecks = (maxNumChecks <= 0) ? 1 : maxNumChecks;
/* 226 */     if (stepsizeReductionFactor < 1.0E-4D || stepsizeReductionFactor > 0.9999D) {
/* 227 */       this.stabilityReduction = 0.5D;
/*     */     } else {
/* 229 */       this.stabilityReduction = stepsizeReductionFactor;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setControlFactors(double control1, double control2, double control3, double control4) {
/* 261 */     if (control1 < 1.0E-4D || control1 > 0.9999D) {
/* 262 */       this.stepControl1 = 0.65D;
/*     */     } else {
/* 264 */       this.stepControl1 = control1;
/*     */     } 
/* 267 */     if (control2 < 1.0E-4D || control2 > 0.9999D) {
/* 268 */       this.stepControl2 = 0.94D;
/*     */     } else {
/* 270 */       this.stepControl2 = control2;
/*     */     } 
/* 273 */     if (control3 < 1.0E-4D || control3 > 0.9999D) {
/* 274 */       this.stepControl3 = 0.02D;
/*     */     } else {
/* 276 */       this.stepControl3 = control3;
/*     */     } 
/* 279 */     if (control4 < 1.0001D || control4 > 999.9D) {
/* 280 */       this.stepControl4 = 4.0D;
/*     */     } else {
/* 282 */       this.stepControl4 = control4;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setOrderControl(int maximalOrder, double control1, double control2) {
/* 313 */     if (maximalOrder <= 6 || maximalOrder % 2 != 0)
/* 314 */       this.maxOrder = 18; 
/* 317 */     if (control1 < 1.0E-4D || control1 > 0.9999D) {
/* 318 */       this.orderControl1 = 0.8D;
/*     */     } else {
/* 320 */       this.orderControl1 = control1;
/*     */     } 
/* 323 */     if (control2 < 1.0E-4D || control2 > 0.9999D) {
/* 324 */       this.orderControl2 = 0.9D;
/*     */     } else {
/* 326 */       this.orderControl2 = control2;
/*     */     } 
/* 330 */     initializeArrays();
/*     */   }
/*     */   
/*     */   public void addStepHandler(StepHandler handler) {
/* 338 */     super.addStepHandler(handler);
/* 341 */     initializeArrays();
/*     */   }
/*     */   
/*     */   public void addEventHandler(EventHandler function, double maxCheckInterval, double convergence, int maxIterationCount, UnivariateSolver solver) {
/* 352 */     super.addEventHandler(function, maxCheckInterval, convergence, maxIterationCount, solver);
/* 356 */     initializeArrays();
/*     */   }
/*     */   
/*     */   private void initializeArrays() {
/* 363 */     int size = this.maxOrder / 2;
/* 365 */     if (this.sequence == null || this.sequence.length != size) {
/* 367 */       this.sequence = new int[size];
/* 368 */       this.costPerStep = new int[size];
/* 369 */       this.coeff = new double[size][];
/* 370 */       this.costPerTimeUnit = new double[size];
/* 371 */       this.optimalStep = new double[size];
/*     */     } 
/*     */     int k;
/* 375 */     for (k = 0; k < size; k++)
/* 376 */       this.sequence[k] = 4 * k + 2; 
/* 381 */     this.costPerStep[0] = this.sequence[0] + 1;
/* 382 */     for (k = 1; k < size; k++)
/* 383 */       this.costPerStep[k] = this.costPerStep[k - 1] + this.sequence[k]; 
/* 387 */     for (k = 0; k < size; k++) {
/* 388 */       this.coeff[k] = (k > 0) ? new double[k] : null;
/* 389 */       for (int l = 0; l < k; l++) {
/* 390 */         double ratio = this.sequence[k] / this.sequence[k - l - 1];
/* 391 */         this.coeff[k][l] = 1.0D / (ratio * ratio - 1.0D);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setInterpolationControl(boolean useInterpolationErrorForControl, int mudifControlParameter) {
/* 410 */     this.useInterpolationError = useInterpolationErrorForControl;
/* 412 */     if (mudifControlParameter <= 0 || mudifControlParameter >= 7) {
/* 413 */       this.mudif = 4;
/*     */     } else {
/* 415 */       this.mudif = mudifControlParameter;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void rescale(double[] y1, double[] y2, double[] scale) {
/* 426 */     if (this.vecAbsoluteTolerance == null) {
/* 427 */       for (int i = 0; i < scale.length; i++) {
/* 428 */         double yi = FastMath.max(FastMath.abs(y1[i]), FastMath.abs(y2[i]));
/* 429 */         scale[i] = this.scalAbsoluteTolerance + this.scalRelativeTolerance * yi;
/*     */       } 
/*     */     } else {
/* 432 */       for (int i = 0; i < scale.length; i++) {
/* 433 */         double yi = FastMath.max(FastMath.abs(y1[i]), FastMath.abs(y2[i]));
/* 434 */         scale[i] = this.vecAbsoluteTolerance[i] + this.vecRelativeTolerance[i] * yi;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean tryStep(double t0, double[] y0, double step, int k, double[] scale, double[][] f, double[] yMiddle, double[] yEnd, double[] yTmp) {
/* 459 */     int n = this.sequence[k];
/* 460 */     double subStep = step / n;
/* 461 */     double subStep2 = 2.0D * subStep;
/* 464 */     double t = t0 + subStep;
/* 465 */     for (int m = 0; m < y0.length; m++) {
/* 466 */       yTmp[m] = y0[m];
/* 467 */       yEnd[m] = y0[m] + subStep * f[0][m];
/*     */     } 
/* 469 */     computeDerivatives(t, yEnd, f[1]);
/* 472 */     for (int j = 1; j < n; j++) {
/* 474 */       if (2 * j == n)
/* 476 */         System.arraycopy(yEnd, 0, yMiddle, 0, y0.length); 
/* 479 */       t += subStep;
/* 480 */       for (int i1 = 0; i1 < y0.length; i1++) {
/* 481 */         double middle = yEnd[i1];
/* 482 */         yEnd[i1] = yTmp[i1] + subStep2 * f[j][i1];
/* 483 */         yTmp[i1] = middle;
/*     */       } 
/* 486 */       computeDerivatives(t, yEnd, f[j + 1]);
/* 489 */       if (this.performTest && j <= this.maxChecks && k < this.maxIter) {
/* 490 */         double initialNorm = 0.0D;
/* 491 */         for (int l = 0; l < scale.length; l++) {
/* 492 */           double ratio = f[0][l] / scale[l];
/* 493 */           initialNorm += ratio * ratio;
/*     */         } 
/* 495 */         double deltaNorm = 0.0D;
/* 496 */         for (int i2 = 0; i2 < scale.length; i2++) {
/* 497 */           double ratio = (f[j + 1][i2] - f[0][i2]) / scale[i2];
/* 498 */           deltaNorm += ratio * ratio;
/*     */         } 
/* 500 */         if (deltaNorm > 4.0D * FastMath.max(1.0E-15D, initialNorm))
/* 501 */           return false; 
/*     */       } 
/*     */     } 
/* 508 */     for (int i = 0; i < y0.length; i++)
/* 509 */       yEnd[i] = 0.5D * (yTmp[i] + yEnd[i] + subStep * f[n][i]); 
/* 512 */     return true;
/*     */   }
/*     */   
/*     */   private void extrapolate(int offset, int k, double[][] diag, double[] last) {
/* 527 */     for (int j = 1; j < k; j++) {
/* 528 */       for (int m = 0; m < last.length; m++)
/* 530 */         diag[k - j - 1][m] = diag[k - j][m] + this.coeff[k + offset][j - 1] * (diag[k - j][m] - diag[k - j - 1][m]); 
/*     */     } 
/* 536 */     for (int i = 0; i < last.length; i++)
/* 538 */       last[i] = diag[0][i] + this.coeff[k + offset][k - 1] * (diag[0][i] - last[i]); 
/*     */   }
/*     */   
/*     */   public void integrate(ExpandableStatefulODE equations, double t) throws MathIllegalStateException, MathIllegalArgumentException {
/* 547 */     sanityChecks(equations, t);
/* 548 */     setEquations(equations);
/* 549 */     boolean forward = (t > equations.getTime());
/* 552 */     double[] y0 = equations.getCompleteState();
/* 553 */     double[] y = (double[])y0.clone();
/* 554 */     double[] yDot0 = new double[y.length];
/* 555 */     double[] y1 = new double[y.length];
/* 556 */     double[] yTmp = new double[y.length];
/* 557 */     double[] yTmpDot = new double[y.length];
/* 559 */     double[][] diagonal = new double[this.sequence.length - 1][];
/* 560 */     double[][] y1Diag = new double[this.sequence.length - 1][];
/* 561 */     for (int k = 0; k < this.sequence.length - 1; k++) {
/* 562 */       diagonal[k] = new double[y.length];
/* 563 */       y1Diag[k] = new double[y.length];
/*     */     } 
/* 566 */     double[][][] fk = new double[this.sequence.length][][];
/* 567 */     for (int i = 0; i < this.sequence.length; i++) {
/* 569 */       fk[i] = new double[this.sequence[i] + 1][];
/* 572 */       fk[i][0] = yDot0;
/* 574 */       for (int l = 0; l < this.sequence[i]; l++)
/* 575 */         fk[i][l + 1] = new double[y0.length]; 
/*     */     } 
/* 580 */     if (y != y0)
/* 581 */       System.arraycopy(y0, 0, y, 0, y0.length); 
/* 584 */     double[] yDot1 = new double[y0.length];
/* 585 */     double[][] yMidDots = new double[1 + 2 * this.sequence.length][y0.length];
/* 588 */     double[] scale = new double[this.mainSetDimension];
/* 589 */     rescale(y, y, scale);
/* 592 */     double tol = (this.vecRelativeTolerance == null) ? this.scalRelativeTolerance : this.vecRelativeTolerance[0];
/* 594 */     double log10R = FastMath.log10(FastMath.max(1.0E-10D, tol));
/* 595 */     int targetIter = FastMath.max(1, FastMath.min(this.sequence.length - 2, (int)FastMath.floor(0.5D - 0.6D * log10R)));
/* 600 */     AbstractStepInterpolator interpolator = new GraggBulirschStoerStepInterpolator(y, yDot0, y1, yDot1, yMidDots, forward, equations.getPrimaryMapper(), equations.getSecondaryMappers());
/* 606 */     interpolator.storeTime(equations.getTime());
/* 608 */     this.stepStart = equations.getTime();
/* 609 */     double hNew = 0.0D;
/* 610 */     double maxError = Double.MAX_VALUE;
/* 611 */     boolean previousRejected = false;
/* 612 */     boolean firstTime = true;
/* 613 */     boolean newStep = true;
/* 614 */     boolean firstStepAlreadyComputed = false;
/* 615 */     initIntegration(equations.getTime(), y0, t);
/* 616 */     this.costPerTimeUnit[0] = 0.0D;
/* 617 */     this.isLastStep = false;
/*     */     do {
/* 621 */       boolean reject = false;
/* 623 */       if (newStep) {
/* 625 */         interpolator.shift();
/* 628 */         if (!firstStepAlreadyComputed)
/* 629 */           computeDerivatives(this.stepStart, y, yDot0); 
/* 632 */         if (firstTime)
/* 633 */           hNew = initializeStep(forward, 2 * targetIter + 1, scale, this.stepStart, y, yDot0, yTmp, yTmpDot); 
/* 637 */         newStep = false;
/*     */       } 
/* 641 */       this.stepSize = hNew;
/* 644 */       if ((forward && this.stepStart + this.stepSize > t) || (!forward && this.stepStart + this.stepSize < t))
/* 646 */         this.stepSize = t - this.stepStart; 
/* 648 */       double nextT = this.stepStart + this.stepSize;
/* 649 */       this.isLastStep = forward ? ((nextT >= t)) : ((nextT <= t));
/* 652 */       int j = -1;
/*     */       boolean loop;
/* 653 */       for (loop = true; loop; ) {
/* 655 */         j++;
/* 658 */         if (!tryStep(this.stepStart, y, this.stepSize, j, scale, fk[j], (j == 0) ? yMidDots[0] : diagonal[j - 1], (j == 0) ? y1 : y1Diag[j - 1], yTmp)) {
/* 664 */           hNew = FastMath.abs(filterStep(this.stepSize * this.stabilityReduction, forward, false));
/* 665 */           reject = true;
/* 666 */           loop = false;
/*     */           continue;
/*     */         } 
/* 671 */         if (j > 0) {
/*     */           double ratio;
/* 675 */           extrapolate(0, j, y1Diag, y1);
/* 676 */           rescale(y, y1, scale);
/* 679 */           double error = 0.0D;
/* 680 */           for (int m = 0; m < this.mainSetDimension; m++) {
/* 681 */             double e = FastMath.abs(y1[m] - y1Diag[0][m]) / scale[m];
/* 682 */             error += e * e;
/*     */           } 
/* 684 */           error = FastMath.sqrt(error / this.mainSetDimension);
/* 686 */           if (error > 1.0E15D || (j > 1 && error > maxError)) {
/* 688 */             hNew = FastMath.abs(filterStep(this.stepSize * this.stabilityReduction, forward, false));
/* 689 */             reject = true;
/* 690 */             loop = false;
/*     */             continue;
/*     */           } 
/* 693 */           maxError = FastMath.max(4.0D * error, 1.0D);
/* 696 */           double exp = 1.0D / (2 * j + 1);
/* 697 */           double fac = this.stepControl2 / FastMath.pow(error / this.stepControl1, exp);
/* 698 */           double pow = FastMath.pow(this.stepControl3, exp);
/* 699 */           fac = FastMath.max(pow / this.stepControl4, FastMath.min(1.0D / pow, fac));
/* 700 */           this.optimalStep[j] = FastMath.abs(filterStep(this.stepSize * fac, forward, true));
/* 701 */           this.costPerTimeUnit[j] = this.costPerStep[j] / this.optimalStep[j];
/* 704 */           switch (j - targetIter) {
/*     */             case -1:
/* 707 */               if (targetIter > 1 && !previousRejected) {
/* 710 */                 if (error <= 1.0D) {
/* 712 */                   loop = false;
/*     */                   continue;
/*     */                 } 
/* 717 */                 double d = this.sequence[targetIter] * this.sequence[targetIter + 1] / (this.sequence[0] * this.sequence[0]);
/* 719 */                 if (error > d * d) {
/* 722 */                   reject = true;
/* 723 */                   loop = false;
/* 724 */                   targetIter = j;
/* 725 */                   if (targetIter > 1 && this.costPerTimeUnit[targetIter - 1] < this.orderControl1 * this.costPerTimeUnit[targetIter])
/* 728 */                     targetIter--; 
/* 730 */                   hNew = this.optimalStep[targetIter];
/*     */                 } 
/*     */               } 
/*     */               continue;
/*     */             case 0:
/* 737 */               if (error <= 1.0D) {
/* 739 */                 loop = false;
/*     */                 continue;
/*     */               } 
/* 744 */               ratio = this.sequence[j + 1] / this.sequence[0];
/* 745 */               if (error > ratio * ratio) {
/* 748 */                 reject = true;
/* 749 */                 loop = false;
/* 750 */                 if (targetIter > 1 && this.costPerTimeUnit[targetIter - 1] < this.orderControl1 * this.costPerTimeUnit[targetIter])
/* 753 */                   targetIter--; 
/* 755 */                 hNew = this.optimalStep[targetIter];
/*     */               } 
/*     */               continue;
/*     */             case 1:
/* 761 */               if (error > 1.0D) {
/* 762 */                 reject = true;
/* 763 */                 if (targetIter > 1 && this.costPerTimeUnit[targetIter - 1] < this.orderControl1 * this.costPerTimeUnit[targetIter])
/* 766 */                   targetIter--; 
/* 768 */                 hNew = this.optimalStep[targetIter];
/*     */               } 
/* 770 */               loop = false;
/*     */               continue;
/*     */           } 
/* 774 */           if ((firstTime || this.isLastStep) && error <= 1.0D)
/* 775 */             loop = false; 
/*     */         } 
/*     */       } 
/* 786 */       if (!reject)
/* 788 */         computeDerivatives(this.stepStart + this.stepSize, y1, yDot1); 
/* 792 */       double hInt = getMaxStep();
/* 793 */       if (!reject) {
/* 796 */         for (int m = 1; m <= j; m++)
/* 797 */           extrapolate(0, m, diagonal, yMidDots[0]); 
/* 800 */         int mu = 2 * j - this.mudif + 3;
/* 802 */         for (int l = 0; l < mu; l++) {
/* 805 */           int l2 = l / 2;
/* 806 */           double factor = FastMath.pow(0.5D * this.sequence[l2], l);
/* 807 */           int middleIndex = (fk[l2]).length / 2;
/* 808 */           for (int i3 = 0; i3 < y0.length; i3++)
/* 809 */             yMidDots[l + 1][i3] = factor * fk[l2][middleIndex + l][i3]; 
/* 811 */           for (int i2 = 1; i2 <= j - l2; i2++) {
/* 812 */             factor = FastMath.pow(0.5D * this.sequence[i2 + l2], l);
/* 813 */             middleIndex = (fk[l2 + i2]).length / 2;
/* 814 */             for (int i4 = 0; i4 < y0.length; i4++)
/* 815 */               diagonal[i2 - 1][i4] = factor * fk[l2 + i2][middleIndex + l][i4]; 
/* 817 */             extrapolate(l2, i2, diagonal, yMidDots[l + 1]);
/*     */           } 
/* 819 */           for (int i1 = 0; i1 < y0.length; i1++)
/* 820 */             yMidDots[l + 1][i1] = yMidDots[l + 1][i1] * this.stepSize; 
/* 824 */           for (int n = (l + 1) / 2; n <= j; n++) {
/* 825 */             for (int i4 = (fk[n]).length - 1; i4 >= 2 * (l + 1); i4--) {
/* 826 */               for (int i5 = 0; i5 < y0.length; i5++)
/* 827 */                 fk[n][i4][i5] = fk[n][i4][i5] - fk[n][i4 - 2][i5]; 
/*     */             } 
/*     */           } 
/*     */         } 
/* 834 */         if (mu >= 0) {
/* 837 */           GraggBulirschStoerStepInterpolator gbsInterpolator = (GraggBulirschStoerStepInterpolator)interpolator;
/* 839 */           gbsInterpolator.computeCoefficients(mu, this.stepSize);
/* 841 */           if (this.useInterpolationError) {
/* 843 */             double interpError = gbsInterpolator.estimateError(scale);
/* 844 */             hInt = FastMath.abs(this.stepSize / FastMath.max(FastMath.pow(interpError, 1.0D / (mu + 4)), 0.01D));
/* 846 */             if (interpError > 10.0D) {
/* 847 */               hNew = hInt;
/* 848 */               reject = true;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 856 */       if (!reject) {
/*     */         int optimalIter;
/* 859 */         interpolator.storeTime(this.stepStart + this.stepSize);
/* 860 */         this.stepStart = acceptStep(interpolator, y1, yDot1, t);
/* 863 */         interpolator.storeTime(this.stepStart);
/* 864 */         System.arraycopy(y1, 0, y, 0, y0.length);
/* 865 */         System.arraycopy(yDot1, 0, yDot0, 0, y0.length);
/* 866 */         firstStepAlreadyComputed = true;
/* 869 */         if (j == 1) {
/* 870 */           optimalIter = 2;
/* 871 */           if (previousRejected)
/* 872 */             optimalIter = 1; 
/* 874 */         } else if (j <= targetIter) {
/* 875 */           optimalIter = j;
/* 876 */           if (this.costPerTimeUnit[j - 1] < this.orderControl1 * this.costPerTimeUnit[j]) {
/* 877 */             optimalIter = j - 1;
/* 878 */           } else if (this.costPerTimeUnit[j] < this.orderControl2 * this.costPerTimeUnit[j - 1]) {
/* 879 */             optimalIter = FastMath.min(j + 1, this.sequence.length - 2);
/*     */           } 
/*     */         } else {
/* 882 */           optimalIter = j - 1;
/* 883 */           if (j > 2 && this.costPerTimeUnit[j - 2] < this.orderControl1 * this.costPerTimeUnit[j - 1])
/* 885 */             optimalIter = j - 2; 
/* 887 */           if (this.costPerTimeUnit[j] < this.orderControl2 * this.costPerTimeUnit[optimalIter])
/* 888 */             optimalIter = FastMath.min(j, this.sequence.length - 2); 
/*     */         } 
/* 892 */         if (previousRejected) {
/* 895 */           targetIter = FastMath.min(optimalIter, j);
/* 896 */           hNew = FastMath.min(FastMath.abs(this.stepSize), this.optimalStep[targetIter]);
/*     */         } else {
/* 899 */           if (optimalIter <= j) {
/* 900 */             hNew = this.optimalStep[optimalIter];
/* 902 */           } else if (j < targetIter && this.costPerTimeUnit[j] < this.orderControl2 * this.costPerTimeUnit[j - 1]) {
/* 904 */             hNew = filterStep(this.optimalStep[j] * this.costPerStep[optimalIter + 1] / this.costPerStep[j], forward, false);
/*     */           } else {
/* 907 */             hNew = filterStep(this.optimalStep[j] * this.costPerStep[optimalIter] / this.costPerStep[j], forward, false);
/*     */           } 
/* 912 */           targetIter = optimalIter;
/*     */         } 
/* 916 */         newStep = true;
/*     */       } 
/* 920 */       hNew = FastMath.min(hNew, hInt);
/* 921 */       if (!forward)
/* 922 */         hNew = -hNew; 
/* 925 */       firstTime = false;
/* 927 */       if (reject) {
/* 928 */         this.isLastStep = false;
/* 929 */         previousRejected = true;
/*     */       } else {
/* 931 */         previousRejected = false;
/*     */       } 
/* 934 */     } while (!this.isLastStep);
/* 937 */     equations.setTime(this.stepStart);
/* 938 */     equations.setCompleteState(y);
/* 940 */     resetInternalState();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\ode\nonstiff\GraggBulirschStoerIntegrator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
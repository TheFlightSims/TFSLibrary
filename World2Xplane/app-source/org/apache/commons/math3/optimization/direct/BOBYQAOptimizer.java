/*      */ package org.apache.commons.math3.optimization.direct;
/*      */ 
/*      */ import java.util.Arrays;
/*      */ import org.apache.commons.math3.analysis.MultivariateFunction;
/*      */ import org.apache.commons.math3.exception.MathIllegalStateException;
/*      */ import org.apache.commons.math3.exception.NumberIsTooSmallException;
/*      */ import org.apache.commons.math3.exception.OutOfRangeException;
/*      */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*      */ import org.apache.commons.math3.linear.Array2DRowRealMatrix;
/*      */ import org.apache.commons.math3.linear.ArrayRealVector;
/*      */ import org.apache.commons.math3.linear.RealVector;
/*      */ import org.apache.commons.math3.optimization.GoalType;
/*      */ import org.apache.commons.math3.optimization.MultivariateOptimizer;
/*      */ import org.apache.commons.math3.optimization.PointValuePair;
/*      */ 
/*      */ public class BOBYQAOptimizer extends BaseAbstractMultivariateSimpleBoundsOptimizer<MultivariateFunction> implements MultivariateOptimizer {
/*      */   public static final int MINIMUM_PROBLEM_DIMENSION = 2;
/*      */   
/*      */   public static final double DEFAULT_INITIAL_RADIUS = 10.0D;
/*      */   
/*      */   public static final double DEFAULT_STOPPING_RADIUS = 1.0E-8D;
/*      */   
/*      */   private static final double ZERO = 0.0D;
/*      */   
/*      */   private static final double ONE = 1.0D;
/*      */   
/*      */   private static final double TWO = 2.0D;
/*      */   
/*      */   private static final double TEN = 10.0D;
/*      */   
/*      */   private static final double SIXTEEN = 16.0D;
/*      */   
/*      */   private static final double TWO_HUNDRED_FIFTY = 250.0D;
/*      */   
/*      */   private static final double MINUS_ONE = -1.0D;
/*      */   
/*      */   private static final double HALF = 0.5D;
/*      */   
/*      */   private static final double ONE_OVER_FOUR = 0.25D;
/*      */   
/*      */   private static final double ONE_OVER_EIGHT = 0.125D;
/*      */   
/*      */   private static final double ONE_OVER_TEN = 0.1D;
/*      */   
/*      */   private static final double ONE_OVER_A_THOUSAND = 0.001D;
/*      */   
/*      */   private final int numberOfInterpolationPoints;
/*      */   
/*      */   private double initialTrustRegionRadius;
/*      */   
/*      */   private final double stoppingTrustRegionRadius;
/*      */   
/*      */   private boolean isMinimize;
/*      */   
/*      */   private ArrayRealVector currentBest;
/*      */   
/*      */   private double[] boundDifference;
/*      */   
/*      */   private int trustRegionCenterInterpolationPointIndex;
/*      */   
/*      */   private Array2DRowRealMatrix bMatrix;
/*      */   
/*      */   private Array2DRowRealMatrix zMatrix;
/*      */   
/*      */   private Array2DRowRealMatrix interpolationPoints;
/*      */   
/*      */   private ArrayRealVector originShift;
/*      */   
/*      */   private ArrayRealVector fAtInterpolationPoints;
/*      */   
/*      */   private ArrayRealVector trustRegionCenterOffset;
/*      */   
/*      */   private ArrayRealVector gradientAtTrustRegionCenter;
/*      */   
/*      */   private ArrayRealVector lowerDifference;
/*      */   
/*      */   private ArrayRealVector upperDifference;
/*      */   
/*      */   private ArrayRealVector modelSecondDerivativesParameters;
/*      */   
/*      */   private ArrayRealVector newPoint;
/*      */   
/*      */   private ArrayRealVector alternativeNewPoint;
/*      */   
/*      */   private ArrayRealVector trialStepPoint;
/*      */   
/*      */   private ArrayRealVector lagrangeValuesAtNewPoint;
/*      */   
/*      */   private ArrayRealVector modelSecondDerivativesValues;
/*      */   
/*      */   public BOBYQAOptimizer(int numberOfInterpolationPoints) {
/*  212 */     this(numberOfInterpolationPoints, 10.0D, 1.0E-8D);
/*      */   }
/*      */   
/*      */   public BOBYQAOptimizer(int numberOfInterpolationPoints, double initialTrustRegionRadius, double stoppingTrustRegionRadius) {
/*  228 */     this.numberOfInterpolationPoints = numberOfInterpolationPoints;
/*  229 */     this.initialTrustRegionRadius = initialTrustRegionRadius;
/*  230 */     this.stoppingTrustRegionRadius = stoppingTrustRegionRadius;
/*      */   }
/*      */   
/*      */   protected PointValuePair doOptimize() {
/*  236 */     double[] lowerBound = getLowerBound();
/*  237 */     double[] upperBound = getUpperBound();
/*  240 */     setup(lowerBound, upperBound);
/*  242 */     this.isMinimize = (getGoalType() == GoalType.MINIMIZE);
/*  243 */     this.currentBest = new ArrayRealVector(getStartPoint());
/*  245 */     double value = bobyqa(lowerBound, upperBound);
/*  247 */     return new PointValuePair(this.currentBest.getDataRef(), this.isMinimize ? value : -value);
/*      */   }
/*      */   
/*      */   private double bobyqa(double[] lowerBound, double[] upperBound) {
/*  288 */     printMethod();
/*  290 */     int n = this.currentBest.getDimension();
/*  299 */     for (int j = 0; j < n; j++) {
/*  300 */       double boundDiff = this.boundDifference[j];
/*  301 */       this.lowerDifference.setEntry(j, lowerBound[j] - this.currentBest.getEntry(j));
/*  302 */       this.upperDifference.setEntry(j, upperBound[j] - this.currentBest.getEntry(j));
/*  303 */       if (this.lowerDifference.getEntry(j) >= -this.initialTrustRegionRadius) {
/*  304 */         if (this.lowerDifference.getEntry(j) >= 0.0D) {
/*  305 */           this.currentBest.setEntry(j, lowerBound[j]);
/*  306 */           this.lowerDifference.setEntry(j, 0.0D);
/*  307 */           this.upperDifference.setEntry(j, boundDiff);
/*      */         } else {
/*  309 */           this.currentBest.setEntry(j, lowerBound[j] + this.initialTrustRegionRadius);
/*  310 */           this.lowerDifference.setEntry(j, -this.initialTrustRegionRadius);
/*  312 */           double deltaOne = upperBound[j] - this.currentBest.getEntry(j);
/*  313 */           this.upperDifference.setEntry(j, Math.max(deltaOne, this.initialTrustRegionRadius));
/*      */         } 
/*  315 */       } else if (this.upperDifference.getEntry(j) <= this.initialTrustRegionRadius) {
/*  316 */         if (this.upperDifference.getEntry(j) <= 0.0D) {
/*  317 */           this.currentBest.setEntry(j, upperBound[j]);
/*  318 */           this.lowerDifference.setEntry(j, -boundDiff);
/*  319 */           this.upperDifference.setEntry(j, 0.0D);
/*      */         } else {
/*  321 */           this.currentBest.setEntry(j, upperBound[j] - this.initialTrustRegionRadius);
/*  323 */           double deltaOne = lowerBound[j] - this.currentBest.getEntry(j);
/*  324 */           double deltaTwo = -this.initialTrustRegionRadius;
/*  325 */           this.lowerDifference.setEntry(j, Math.min(deltaOne, deltaTwo));
/*  326 */           this.upperDifference.setEntry(j, this.initialTrustRegionRadius);
/*      */         } 
/*      */       } 
/*      */     } 
/*  333 */     return bobyqb(lowerBound, upperBound);
/*      */   }
/*      */   
/*      */   private double bobyqb(double[] lowerBound, double[] upperBound) {
/*  377 */     printMethod();
/*  379 */     int n = this.currentBest.getDimension();
/*  380 */     int npt = this.numberOfInterpolationPoints;
/*  381 */     int np = n + 1;
/*  382 */     int nptm = npt - np;
/*  383 */     int nh = n * np / 2;
/*  385 */     ArrayRealVector work1 = new ArrayRealVector(n);
/*  386 */     ArrayRealVector work2 = new ArrayRealVector(npt);
/*  387 */     ArrayRealVector work3 = new ArrayRealVector(npt);
/*  389 */     double cauchy = Double.NaN;
/*  390 */     double alpha = Double.NaN;
/*  391 */     double dsq = Double.NaN;
/*  392 */     double crvmin = Double.NaN;
/*  406 */     this.trustRegionCenterInterpolationPointIndex = 0;
/*  408 */     prelim(lowerBound, upperBound);
/*  409 */     double xoptsq = 0.0D;
/*  410 */     for (int i = 0; i < n; i++) {
/*  411 */       this.trustRegionCenterOffset.setEntry(i, this.interpolationPoints.getEntry(this.trustRegionCenterInterpolationPointIndex, i));
/*  413 */       double deltaOne = this.trustRegionCenterOffset.getEntry(i);
/*  414 */       xoptsq += deltaOne * deltaOne;
/*      */     } 
/*  416 */     double fsave = this.fAtInterpolationPoints.getEntry(0);
/*  417 */     int kbase = 0;
/*  421 */     int ntrits = 0;
/*  422 */     int itest = 0;
/*  423 */     int knew = 0;
/*  424 */     int nfsav = getEvaluations();
/*  425 */     double rho = this.initialTrustRegionRadius;
/*  426 */     double delta = rho;
/*  427 */     double diffa = 0.0D;
/*  428 */     double diffb = 0.0D;
/*  429 */     double diffc = 0.0D;
/*  430 */     double f = 0.0D;
/*  431 */     double beta = 0.0D;
/*  432 */     double adelt = 0.0D;
/*  433 */     double denom = 0.0D;
/*  434 */     double ratio = 0.0D;
/*  435 */     double dnorm = 0.0D;
/*  436 */     double scaden = 0.0D;
/*  437 */     double biglsq = 0.0D;
/*  438 */     double distsq = 0.0D;
/*  443 */     int state = 20;
/*      */     while (true) {
/*      */       ArrayRealVector gnew;
/*      */       double[] alphaCauchy;
/*      */       int i2;
/*      */       int m;
/*      */       double bsum;
/*      */       int i1;
/*      */       double fopt;
/*      */       int k;
/*      */       ArrayRealVector xbdi;
/*      */       int i3;
/*      */       ArrayRealVector s;
/*      */       double dx;
/*      */       double vquad;
/*      */       ArrayRealVector hs;
/*      */       ArrayRealVector hred;
/*      */       int j;
/*      */       int ih;
/*      */       double[] dsqCrvmin;
/*      */       int i5;
/*      */       int i4;
/*      */       double diff;
/*      */       double deltaOne;
/*      */       double pqold;
/*      */       double deltaTwo;
/*      */       int i10;
/*      */       int i9;
/*      */       int i8;
/*      */       int i7;
/*      */       int i6;
/*      */       double d1;
/*      */       double d2;
/*  444 */       switch (state) {
/*      */         case 20:
/*  446 */           printState(20);
/*  447 */           if (this.trustRegionCenterInterpolationPointIndex != 0) {
/*  448 */             int i11 = 0;
/*  449 */             for (int i12 = 0; i12 < n; i12++) {
/*  450 */               for (int i13 = 0; i13 <= i12; i13++) {
/*  451 */                 if (i13 < i12)
/*  452 */                   this.gradientAtTrustRegionCenter.setEntry(i12, this.gradientAtTrustRegionCenter.getEntry(i12) + this.modelSecondDerivativesValues.getEntry(i11) * this.trustRegionCenterOffset.getEntry(i13)); 
/*  454 */                 this.gradientAtTrustRegionCenter.setEntry(i13, this.gradientAtTrustRegionCenter.getEntry(i13) + this.modelSecondDerivativesValues.getEntry(i11) * this.trustRegionCenterOffset.getEntry(i12));
/*  455 */                 i11++;
/*      */               } 
/*      */             } 
/*  458 */             if (getEvaluations() > npt)
/*  459 */               for (int i13 = 0; i13 < npt; i13++) {
/*  460 */                 double temp = 0.0D;
/*  461 */                 for (int i15 = 0; i15 < n; i15++)
/*  462 */                   temp += this.interpolationPoints.getEntry(i13, i15) * this.trustRegionCenterOffset.getEntry(i15); 
/*  464 */                 temp *= this.modelSecondDerivativesParameters.getEntry(i13);
/*  465 */                 for (int i14 = 0; i14 < n; i14++)
/*  466 */                   this.gradientAtTrustRegionCenter.setEntry(i14, this.gradientAtTrustRegionCenter.getEntry(i14) + temp * this.interpolationPoints.getEntry(i13, i14)); 
/*      */               }  
/*      */           } 
/*      */         case 60:
/*  482 */           printState(60);
/*  483 */           gnew = new ArrayRealVector(n);
/*  484 */           xbdi = new ArrayRealVector(n);
/*  485 */           s = new ArrayRealVector(n);
/*  486 */           hs = new ArrayRealVector(n);
/*  487 */           hred = new ArrayRealVector(n);
/*  489 */           dsqCrvmin = trsbox(delta, gnew, xbdi, s, hs, hred);
/*  491 */           dsq = dsqCrvmin[0];
/*  492 */           crvmin = dsqCrvmin[1];
/*  495 */           deltaOne = delta;
/*  496 */           deltaTwo = Math.sqrt(dsq);
/*  497 */           dnorm = Math.min(deltaOne, deltaTwo);
/*  498 */           if (dnorm < 0.5D * rho) {
/*  499 */             ntrits = -1;
/*  501 */             deltaOne = 10.0D * rho;
/*  502 */             distsq = deltaOne * deltaOne;
/*  503 */             if (getEvaluations() <= nfsav + 2) {
/*  504 */               state = 650;
/*      */               continue;
/*      */             } 
/*  514 */             deltaOne = Math.max(diffa, diffb);
/*  515 */             double errbig = Math.max(deltaOne, diffc);
/*  516 */             double frhosq = rho * 0.125D * rho;
/*  517 */             if (crvmin > 0.0D && errbig > frhosq * crvmin) {
/*  519 */               state = 650;
/*      */               continue;
/*      */             } 
/*  521 */             double bdtol = errbig / rho;
/*  522 */             for (int i11 = 0; i11 < n; i11++) {
/*  523 */               double bdtest = bdtol;
/*  524 */               if (this.newPoint.getEntry(i11) == this.lowerDifference.getEntry(i11))
/*  525 */                 bdtest = work1.getEntry(i11); 
/*  527 */               if (this.newPoint.getEntry(i11) == this.upperDifference.getEntry(i11))
/*  528 */                 bdtest = -work1.getEntry(i11); 
/*  530 */               if (bdtest < bdtol) {
/*  531 */                 double curv = this.modelSecondDerivativesValues.getEntry((i11 + i11 * i11) / 2);
/*  532 */                 for (int i12 = 0; i12 < npt; i12++) {
/*  534 */                   double d = this.interpolationPoints.getEntry(i12, i11);
/*  535 */                   curv += this.modelSecondDerivativesParameters.getEntry(i12) * d * d;
/*      */                 } 
/*  537 */                 bdtest += 0.5D * curv * rho;
/*  538 */                 if (bdtest < bdtol) {
/*  539 */                   state = 650;
/*      */                   break;
/*      */                 } 
/*      */               } 
/*      */             } 
/*  544 */             state = 680;
/*      */             continue;
/*      */           } 
/*  546 */           ntrits++;
/*      */         case 90:
/*  556 */           printState(90);
/*  557 */           if (dsq <= xoptsq * 0.001D) {
/*  558 */             double fracsq = xoptsq * 0.25D;
/*  559 */             double sumpq = 0.0D;
/*  562 */             for (int i13 = 0; i13 < npt; i13++) {
/*  563 */               sumpq += this.modelSecondDerivativesParameters.getEntry(i13);
/*  564 */               double sum = -0.5D * xoptsq;
/*  565 */               for (int i16 = 0; i16 < n; i16++)
/*  566 */                 sum += this.interpolationPoints.getEntry(i13, i16) * this.trustRegionCenterOffset.getEntry(i16); 
/*  569 */               work2.setEntry(i13, sum);
/*  570 */               double temp = fracsq - 0.5D * sum;
/*  571 */               for (int i17 = 0; i17 < n; i17++) {
/*  572 */                 work1.setEntry(i17, this.bMatrix.getEntry(i13, i17));
/*  573 */                 this.lagrangeValuesAtNewPoint.setEntry(i17, sum * this.interpolationPoints.getEntry(i13, i17) + temp * this.trustRegionCenterOffset.getEntry(i17));
/*  574 */                 int ip = npt + i17;
/*  575 */                 for (int i18 = 0; i18 <= i17; i18++)
/*  576 */                   this.bMatrix.setEntry(ip, i18, this.bMatrix.getEntry(ip, i18) + work1.getEntry(i17) * this.lagrangeValuesAtNewPoint.getEntry(i18) + this.lagrangeValuesAtNewPoint.getEntry(i17) * work1.getEntry(i18)); 
/*      */               } 
/*      */             } 
/*  586 */             for (int i12 = 0; i12 < nptm; i12++) {
/*  587 */               double sumz = 0.0D;
/*  588 */               double sumw = 0.0D;
/*  589 */               for (int i18 = 0; i18 < npt; i18++) {
/*  590 */                 sumz += this.zMatrix.getEntry(i18, i12);
/*  591 */                 this.lagrangeValuesAtNewPoint.setEntry(i18, work2.getEntry(i18) * this.zMatrix.getEntry(i18, i12));
/*  592 */                 sumw += this.lagrangeValuesAtNewPoint.getEntry(i18);
/*      */               } 
/*  594 */               for (int i17 = 0; i17 < n; i17++) {
/*  595 */                 double sum = (fracsq * sumz - 0.5D * sumw) * this.trustRegionCenterOffset.getEntry(i17);
/*      */                 int i19;
/*  596 */                 for (i19 = 0; i19 < npt; i19++)
/*  597 */                   sum += this.lagrangeValuesAtNewPoint.getEntry(i19) * this.interpolationPoints.getEntry(i19, i17); 
/*  599 */                 work1.setEntry(i17, sum);
/*  600 */                 for (i19 = 0; i19 < npt; i19++)
/*  601 */                   this.bMatrix.setEntry(i19, i17, this.bMatrix.getEntry(i19, i17) + sum * this.zMatrix.getEntry(i19, i12)); 
/*      */               } 
/*  606 */               for (int i16 = 0; i16 < n; i16++) {
/*  607 */                 int ip = i16 + npt;
/*  608 */                 double temp = work1.getEntry(i16);
/*  609 */                 for (int i19 = 0; i19 <= i16; i19++)
/*  610 */                   this.bMatrix.setEntry(ip, i19, this.bMatrix.getEntry(ip, i19) + temp * work1.getEntry(i19)); 
/*      */               } 
/*      */             } 
/*  620 */             int i11 = 0;
/*  621 */             for (int i15 = 0; i15 < n; i15++) {
/*  622 */               work1.setEntry(i15, -0.5D * sumpq * this.trustRegionCenterOffset.getEntry(i15));
/*  623 */               for (int i17 = 0; i17 < npt; i17++) {
/*  624 */                 work1.setEntry(i15, work1.getEntry(i15) + this.modelSecondDerivativesParameters.getEntry(i17) * this.interpolationPoints.getEntry(i17, i15));
/*  625 */                 this.interpolationPoints.setEntry(i17, i15, this.interpolationPoints.getEntry(i17, i15) - this.trustRegionCenterOffset.getEntry(i15));
/*      */               } 
/*  627 */               for (int i16 = 0; i16 <= i15; i16++) {
/*  628 */                 this.modelSecondDerivativesValues.setEntry(i11, this.modelSecondDerivativesValues.getEntry(i11) + work1.getEntry(i16) * this.trustRegionCenterOffset.getEntry(i15) + this.trustRegionCenterOffset.getEntry(i16) * work1.getEntry(i15));
/*  632 */                 this.bMatrix.setEntry(npt + i16, i15, this.bMatrix.getEntry(npt + i15, i16));
/*  633 */                 i11++;
/*      */               } 
/*      */             } 
/*  636 */             for (int i14 = 0; i14 < n; i14++) {
/*  637 */               this.originShift.setEntry(i14, this.originShift.getEntry(i14) + this.trustRegionCenterOffset.getEntry(i14));
/*  638 */               this.newPoint.setEntry(i14, this.newPoint.getEntry(i14) - this.trustRegionCenterOffset.getEntry(i14));
/*  639 */               this.lowerDifference.setEntry(i14, this.lowerDifference.getEntry(i14) - this.trustRegionCenterOffset.getEntry(i14));
/*  640 */               this.upperDifference.setEntry(i14, this.upperDifference.getEntry(i14) - this.trustRegionCenterOffset.getEntry(i14));
/*  641 */               this.trustRegionCenterOffset.setEntry(i14, 0.0D);
/*      */             } 
/*  643 */             xoptsq = 0.0D;
/*      */           } 
/*  645 */           if (ntrits == 0) {
/*  646 */             state = 210;
/*      */             continue;
/*      */           } 
/*  648 */           state = 230;
/*      */           continue;
/*      */         case 210:
/*  661 */           printState(210);
/*  673 */           alphaCauchy = altmov(knew, adelt);
/*  674 */           alpha = alphaCauchy[0];
/*  675 */           cauchy = alphaCauchy[1];
/*  677 */           for (i3 = 0; i3 < n; i3++)
/*  678 */             this.trialStepPoint.setEntry(i3, this.newPoint.getEntry(i3) - this.trustRegionCenterOffset.getEntry(i3)); 
/*      */         case 230:
/*  687 */           printState(230);
/*  688 */           for (i2 = 0; i2 < npt; i2++) {
/*  689 */             double suma = 0.0D;
/*  690 */             double sumb = 0.0D;
/*  691 */             double sum = 0.0D;
/*  692 */             for (int i11 = 0; i11 < n; i11++) {
/*  693 */               suma += this.interpolationPoints.getEntry(i2, i11) * this.trialStepPoint.getEntry(i11);
/*  694 */               sumb += this.interpolationPoints.getEntry(i2, i11) * this.trustRegionCenterOffset.getEntry(i11);
/*  695 */               sum += this.bMatrix.getEntry(i2, i11) * this.trialStepPoint.getEntry(i11);
/*      */             } 
/*  697 */             work3.setEntry(i2, suma * (0.5D * suma + sumb));
/*  698 */             this.lagrangeValuesAtNewPoint.setEntry(i2, sum);
/*  699 */             work2.setEntry(i2, suma);
/*      */           } 
/*  701 */           beta = 0.0D;
/*  702 */           for (m = 0; m < nptm; m++) {
/*  703 */             double sum = 0.0D;
/*      */             int i11;
/*  704 */             for (i11 = 0; i11 < npt; i11++)
/*  705 */               sum += this.zMatrix.getEntry(i11, m) * work3.getEntry(i11); 
/*  707 */             beta -= sum * sum;
/*  708 */             for (i11 = 0; i11 < npt; i11++)
/*  709 */               this.lagrangeValuesAtNewPoint.setEntry(i11, this.lagrangeValuesAtNewPoint.getEntry(i11) + sum * this.zMatrix.getEntry(i11, m)); 
/*      */           } 
/*  712 */           dsq = 0.0D;
/*  713 */           bsum = 0.0D;
/*  714 */           dx = 0.0D;
/*  715 */           for (j = 0; j < n; j++) {
/*  717 */             double d3 = this.trialStepPoint.getEntry(j);
/*  718 */             dsq += d3 * d3;
/*  719 */             double sum = 0.0D;
/*  720 */             for (int i11 = 0; i11 < npt; i11++)
/*  721 */               sum += work3.getEntry(i11) * this.bMatrix.getEntry(i11, j); 
/*  723 */             bsum += sum * this.trialStepPoint.getEntry(j);
/*  724 */             int jp = npt + j;
/*  725 */             for (int i12 = 0; i12 < n; i12++)
/*  726 */               sum += this.bMatrix.getEntry(jp, i12) * this.trialStepPoint.getEntry(i12); 
/*  728 */             this.lagrangeValuesAtNewPoint.setEntry(jp, sum);
/*  729 */             bsum += sum * this.trialStepPoint.getEntry(j);
/*  730 */             dx += this.trialStepPoint.getEntry(j) * this.trustRegionCenterOffset.getEntry(j);
/*      */           } 
/*  733 */           beta = dx * dx + dsq * (xoptsq + dx + dx + 0.5D * dsq) + beta - bsum;
/*  737 */           this.lagrangeValuesAtNewPoint.setEntry(this.trustRegionCenterInterpolationPointIndex, this.lagrangeValuesAtNewPoint.getEntry(this.trustRegionCenterInterpolationPointIndex) + 1.0D);
/*  744 */           if (ntrits == 0) {
/*  746 */             double d = this.lagrangeValuesAtNewPoint.getEntry(knew);
/*  747 */             denom = d * d + alpha * beta;
/*  748 */             if (denom < cauchy && cauchy > 0.0D) {
/*  749 */               for (int i11 = 0; i11 < n; i11++) {
/*  750 */                 this.newPoint.setEntry(i11, this.alternativeNewPoint.getEntry(i11));
/*  751 */                 this.trialStepPoint.setEntry(i11, this.newPoint.getEntry(i11) - this.trustRegionCenterOffset.getEntry(i11));
/*      */               } 
/*  753 */               cauchy = 0.0D;
/*  754 */               state = 230;
/*      */               continue;
/*      */             } 
/*      */           } else {
/*  763 */             double delsq = delta * delta;
/*  764 */             scaden = 0.0D;
/*  765 */             biglsq = 0.0D;
/*  766 */             knew = 0;
/*  767 */             for (int i11 = 0; i11 < npt; i11++) {
/*  768 */               if (i11 != this.trustRegionCenterInterpolationPointIndex) {
/*  771 */                 double hdiag = 0.0D;
/*  772 */                 for (int i12 = 0; i12 < nptm; i12++) {
/*  774 */                   double d = this.zMatrix.getEntry(i11, i12);
/*  775 */                   hdiag += d * d;
/*      */                 } 
/*  778 */                 double d3 = this.lagrangeValuesAtNewPoint.getEntry(i11);
/*  779 */                 double den = beta * hdiag + d3 * d3;
/*  780 */                 distsq = 0.0D;
/*  781 */                 for (int i13 = 0; i13 < n; i13++) {
/*  783 */                   double d = this.interpolationPoints.getEntry(i11, i13) - this.trustRegionCenterOffset.getEntry(i13);
/*  784 */                   distsq += d * d;
/*      */                 } 
/*  788 */                 double d4 = distsq / delsq;
/*  789 */                 double temp = Math.max(1.0D, d4 * d4);
/*  790 */                 if (temp * den > scaden) {
/*  791 */                   scaden = temp * den;
/*  792 */                   knew = i11;
/*  793 */                   denom = den;
/*      */                 } 
/*  797 */                 double d5 = this.lagrangeValuesAtNewPoint.getEntry(i11);
/*  798 */                 biglsq = Math.max(biglsq, temp * d5 * d5);
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         case 360:
/*  810 */           printState(360);
/*  811 */           for (i1 = 0; i1 < n; i1++) {
/*  814 */             double d3 = lowerBound[i1];
/*  815 */             double d4 = this.originShift.getEntry(i1) + this.newPoint.getEntry(i1);
/*  816 */             double d5 = Math.max(d3, d4);
/*  817 */             double d6 = upperBound[i1];
/*  818 */             this.currentBest.setEntry(i1, Math.min(d5, d6));
/*  819 */             if (this.newPoint.getEntry(i1) == this.lowerDifference.getEntry(i1))
/*  820 */               this.currentBest.setEntry(i1, lowerBound[i1]); 
/*  822 */             if (this.newPoint.getEntry(i1) == this.upperDifference.getEntry(i1))
/*  823 */               this.currentBest.setEntry(i1, upperBound[i1]); 
/*      */           } 
/*  827 */           f = computeObjectiveValue(this.currentBest.toArray());
/*  829 */           if (!this.isMinimize)
/*  830 */             f = -f; 
/*  831 */           if (ntrits == -1) {
/*  832 */             fsave = f;
/*  833 */             state = 720;
/*      */             continue;
/*      */           } 
/*  839 */           fopt = this.fAtInterpolationPoints.getEntry(this.trustRegionCenterInterpolationPointIndex);
/*  840 */           vquad = 0.0D;
/*  841 */           ih = 0;
/*  842 */           for (i5 = 0; i5 < n; i5++) {
/*  843 */             vquad += this.trialStepPoint.getEntry(i5) * this.gradientAtTrustRegionCenter.getEntry(i5);
/*  844 */             for (int i11 = 0; i11 <= i5; i11++) {
/*  845 */               double temp = this.trialStepPoint.getEntry(i11) * this.trialStepPoint.getEntry(i5);
/*  846 */               if (i11 == i5)
/*  847 */                 temp *= 0.5D; 
/*  849 */               vquad += this.modelSecondDerivativesValues.getEntry(ih) * temp;
/*  850 */               ih++;
/*      */             } 
/*      */           } 
/*  853 */           for (i4 = 0; i4 < npt; i4++) {
/*  855 */             double d3 = work2.getEntry(i4);
/*  856 */             double d4 = d3 * d3;
/*  857 */             vquad += 0.5D * this.modelSecondDerivativesParameters.getEntry(i4) * d4;
/*      */           } 
/*  859 */           diff = f - fopt - vquad;
/*  860 */           diffc = diffb;
/*  861 */           diffb = diffa;
/*  862 */           diffa = Math.abs(diff);
/*  863 */           if (dnorm > rho)
/*  864 */             nfsav = getEvaluations(); 
/*  869 */           if (ntrits > 0) {
/*  870 */             if (vquad >= 0.0D)
/*  871 */               throw new MathIllegalStateException(LocalizedFormats.TRUST_REGION_STEP_FAILED, new Object[] { Double.valueOf(vquad) }); 
/*  873 */             ratio = (f - fopt) / vquad;
/*  874 */             double hDelta = 0.5D * delta;
/*  875 */             if (ratio <= 0.1D) {
/*  877 */               delta = Math.min(hDelta, dnorm);
/*  878 */             } else if (ratio <= 0.7D) {
/*  880 */               delta = Math.max(hDelta, dnorm);
/*      */             } else {
/*  883 */               delta = Math.max(hDelta, 2.0D * dnorm);
/*      */             } 
/*  885 */             if (delta <= rho * 1.5D)
/*  886 */               delta = rho; 
/*  891 */             if (f < fopt) {
/*  892 */               int ksav = knew;
/*  893 */               double densav = denom;
/*  894 */               double delsq = delta * delta;
/*  895 */               scaden = 0.0D;
/*  896 */               biglsq = 0.0D;
/*  897 */               knew = 0;
/*  898 */               for (int i11 = 0; i11 < npt; i11++) {
/*  899 */                 double hdiag = 0.0D;
/*  900 */                 for (int i12 = 0; i12 < nptm; i12++) {
/*  902 */                   double d = this.zMatrix.getEntry(i11, i12);
/*  903 */                   hdiag += d * d;
/*      */                 } 
/*  906 */                 double d6 = this.lagrangeValuesAtNewPoint.getEntry(i11);
/*  907 */                 double den = beta * hdiag + d6 * d6;
/*  908 */                 distsq = 0.0D;
/*  909 */                 for (int i13 = 0; i13 < n; i13++) {
/*  911 */                   double d = this.interpolationPoints.getEntry(i11, i13) - this.newPoint.getEntry(i13);
/*  912 */                   distsq += d * d;
/*      */                 } 
/*  916 */                 double d3 = distsq / delsq;
/*  917 */                 double temp = Math.max(1.0D, d3 * d3);
/*  918 */                 if (temp * den > scaden) {
/*  919 */                   scaden = temp * den;
/*  920 */                   knew = i11;
/*  921 */                   denom = den;
/*      */                 } 
/*  925 */                 double d4 = this.lagrangeValuesAtNewPoint.getEntry(i11);
/*  926 */                 double d5 = temp * d4 * d4;
/*  927 */                 biglsq = Math.max(biglsq, d5);
/*      */               } 
/*  929 */               if (scaden <= 0.5D * biglsq) {
/*  930 */                 knew = ksav;
/*  931 */                 denom = densav;
/*      */               } 
/*      */             } 
/*      */           } 
/*  939 */           update(beta, denom, knew);
/*  941 */           ih = 0;
/*  942 */           pqold = this.modelSecondDerivativesParameters.getEntry(knew);
/*  943 */           this.modelSecondDerivativesParameters.setEntry(knew, 0.0D);
/*  944 */           for (i10 = 0; i10 < n; i10++) {
/*  945 */             double temp = pqold * this.interpolationPoints.getEntry(knew, i10);
/*  946 */             for (int i11 = 0; i11 <= i10; i11++) {
/*  947 */               this.modelSecondDerivativesValues.setEntry(ih, this.modelSecondDerivativesValues.getEntry(ih) + temp * this.interpolationPoints.getEntry(knew, i11));
/*  948 */               ih++;
/*      */             } 
/*      */           } 
/*  951 */           for (i9 = 0; i9 < nptm; i9++) {
/*  952 */             double temp = diff * this.zMatrix.getEntry(knew, i9);
/*  953 */             for (int i11 = 0; i11 < npt; i11++)
/*  954 */               this.modelSecondDerivativesParameters.setEntry(i11, this.modelSecondDerivativesParameters.getEntry(i11) + temp * this.zMatrix.getEntry(i11, i9)); 
/*      */           } 
/*  961 */           this.fAtInterpolationPoints.setEntry(knew, f);
/*  962 */           for (i8 = 0; i8 < n; i8++) {
/*  963 */             this.interpolationPoints.setEntry(knew, i8, this.newPoint.getEntry(i8));
/*  964 */             work1.setEntry(i8, this.bMatrix.getEntry(knew, i8));
/*      */           } 
/*  966 */           for (i7 = 0; i7 < npt; i7++) {
/*  967 */             double suma = 0.0D;
/*  968 */             for (int i11 = 0; i11 < nptm; i11++)
/*  969 */               suma += this.zMatrix.getEntry(knew, i11) * this.zMatrix.getEntry(i7, i11); 
/*  971 */             double sumb = 0.0D;
/*  972 */             for (int i12 = 0; i12 < n; i12++)
/*  973 */               sumb += this.interpolationPoints.getEntry(i7, i12) * this.trustRegionCenterOffset.getEntry(i12); 
/*  975 */             double temp = suma * sumb;
/*  976 */             for (int i13 = 0; i13 < n; i13++)
/*  977 */               work1.setEntry(i13, work1.getEntry(i13) + temp * this.interpolationPoints.getEntry(i7, i13)); 
/*      */           } 
/*  980 */           for (i6 = 0; i6 < n; i6++)
/*  981 */             this.gradientAtTrustRegionCenter.setEntry(i6, this.gradientAtTrustRegionCenter.getEntry(i6) + diff * work1.getEntry(i6)); 
/*  986 */           if (f < fopt) {
/*  987 */             this.trustRegionCenterInterpolationPointIndex = knew;
/*  988 */             xoptsq = 0.0D;
/*  989 */             ih = 0;
/*  990 */             for (int i12 = 0; i12 < n; i12++) {
/*  991 */               this.trustRegionCenterOffset.setEntry(i12, this.newPoint.getEntry(i12));
/*  993 */               double d = this.trustRegionCenterOffset.getEntry(i12);
/*  994 */               xoptsq += d * d;
/*  995 */               for (int i13 = 0; i13 <= i12; i13++) {
/*  996 */                 if (i13 < i12)
/*  997 */                   this.gradientAtTrustRegionCenter.setEntry(i12, this.gradientAtTrustRegionCenter.getEntry(i12) + this.modelSecondDerivativesValues.getEntry(ih) * this.trialStepPoint.getEntry(i13)); 
/*  999 */                 this.gradientAtTrustRegionCenter.setEntry(i13, this.gradientAtTrustRegionCenter.getEntry(i13) + this.modelSecondDerivativesValues.getEntry(ih) * this.trialStepPoint.getEntry(i12));
/* 1000 */                 ih++;
/*      */               } 
/*      */             } 
/* 1003 */             for (int i11 = 0; i11 < npt; i11++) {
/* 1004 */               double temp = 0.0D;
/* 1005 */               for (int i14 = 0; i14 < n; i14++)
/* 1006 */                 temp += this.interpolationPoints.getEntry(i11, i14) * this.trialStepPoint.getEntry(i14); 
/* 1008 */               temp *= this.modelSecondDerivativesParameters.getEntry(i11);
/* 1009 */               for (int i13 = 0; i13 < n; i13++)
/* 1010 */                 this.gradientAtTrustRegionCenter.setEntry(i13, this.gradientAtTrustRegionCenter.getEntry(i13) + temp * this.interpolationPoints.getEntry(i11, i13)); 
/*      */             } 
/*      */           } 
/* 1019 */           if (ntrits > 0) {
/* 1020 */             for (int i13 = 0; i13 < npt; i13++) {
/* 1021 */               this.lagrangeValuesAtNewPoint.setEntry(i13, this.fAtInterpolationPoints.getEntry(i13) - this.fAtInterpolationPoints.getEntry(this.trustRegionCenterInterpolationPointIndex));
/* 1022 */               work3.setEntry(i13, 0.0D);
/*      */             } 
/* 1024 */             for (int i12 = 0; i12 < nptm; i12++) {
/* 1025 */               double sum = 0.0D;
/*      */               int i15;
/* 1026 */               for (i15 = 0; i15 < npt; i15++)
/* 1027 */                 sum += this.zMatrix.getEntry(i15, i12) * this.lagrangeValuesAtNewPoint.getEntry(i15); 
/* 1029 */               for (i15 = 0; i15 < npt; i15++)
/* 1030 */                 work3.setEntry(i15, work3.getEntry(i15) + sum * this.zMatrix.getEntry(i15, i12)); 
/*      */             } 
/* 1033 */             for (int i11 = 0; i11 < npt; i11++) {
/* 1034 */               double sum = 0.0D;
/* 1035 */               for (int i15 = 0; i15 < n; i15++)
/* 1036 */                 sum += this.interpolationPoints.getEntry(i11, i15) * this.trustRegionCenterOffset.getEntry(i15); 
/* 1038 */               work2.setEntry(i11, work3.getEntry(i11));
/* 1039 */               work3.setEntry(i11, sum * work3.getEntry(i11));
/*      */             } 
/* 1041 */             double gqsq = 0.0D;
/* 1042 */             double gisq = 0.0D;
/*      */             int i14;
/* 1043 */             for (i14 = 0; i14 < n; i14++) {
/* 1044 */               double sum = 0.0D;
/* 1045 */               for (int i15 = 0; i15 < npt; i15++)
/* 1046 */                 sum += this.bMatrix.getEntry(i15, i14) * this.lagrangeValuesAtNewPoint.getEntry(i15) + this.interpolationPoints.getEntry(i15, i14) * work3.getEntry(i15); 
/* 1049 */               if (this.trustRegionCenterOffset.getEntry(i14) == this.lowerDifference.getEntry(i14)) {
/* 1052 */                 double d3 = Math.min(0.0D, this.gradientAtTrustRegionCenter.getEntry(i14));
/* 1053 */                 gqsq += d3 * d3;
/* 1055 */                 double d4 = Math.min(0.0D, sum);
/* 1056 */                 gisq += d4 * d4;
/* 1057 */               } else if (this.trustRegionCenterOffset.getEntry(i14) == this.upperDifference.getEntry(i14)) {
/* 1060 */                 double d3 = Math.max(0.0D, this.gradientAtTrustRegionCenter.getEntry(i14));
/* 1061 */                 gqsq += d3 * d3;
/* 1063 */                 double d4 = Math.max(0.0D, sum);
/* 1064 */                 gisq += d4 * d4;
/*      */               } else {
/* 1067 */                 double d = this.gradientAtTrustRegionCenter.getEntry(i14);
/* 1068 */                 gqsq += d * d;
/* 1069 */                 gisq += sum * sum;
/*      */               } 
/* 1071 */               this.lagrangeValuesAtNewPoint.setEntry(npt + i14, sum);
/*      */             } 
/* 1077 */             itest++;
/* 1078 */             if (gqsq < 10.0D * gisq)
/* 1079 */               itest = 0; 
/* 1081 */             if (itest >= 3) {
/*      */               int max;
/* 1082 */               for (i14 = 0, max = Math.max(npt, nh); i14 < max; i14++) {
/* 1083 */                 if (i14 < n)
/* 1084 */                   this.gradientAtTrustRegionCenter.setEntry(i14, this.lagrangeValuesAtNewPoint.getEntry(npt + i14)); 
/* 1086 */                 if (i14 < npt)
/* 1087 */                   this.modelSecondDerivativesParameters.setEntry(i14, work2.getEntry(i14)); 
/* 1089 */                 if (i14 < nh)
/* 1090 */                   this.modelSecondDerivativesValues.setEntry(i14, 0.0D); 
/* 1092 */                 itest = 0;
/*      */               } 
/*      */             } 
/*      */           } 
/* 1101 */           if (ntrits == 0) {
/* 1102 */             state = 60;
/*      */             continue;
/*      */           } 
/* 1104 */           if (f <= fopt + 0.1D * vquad) {
/* 1105 */             state = 60;
/*      */             continue;
/*      */           } 
/* 1113 */           d1 = 2.0D * delta;
/* 1115 */           d2 = 10.0D * rho;
/* 1116 */           distsq = Math.max(d1 * d1, d2 * d2);
/*      */         case 650:
/* 1119 */           printState(650);
/* 1120 */           knew = -1;
/* 1121 */           for (k = 0; k < npt; k++) {
/* 1122 */             double sum = 0.0D;
/* 1123 */             for (int i11 = 0; i11 < n; i11++) {
/* 1125 */               double d = this.interpolationPoints.getEntry(k, i11) - this.trustRegionCenterOffset.getEntry(i11);
/* 1126 */               sum += d * d;
/*      */             } 
/* 1128 */             if (sum > distsq) {
/* 1129 */               knew = k;
/* 1130 */               distsq = sum;
/*      */             } 
/*      */           } 
/* 1140 */           if (knew >= 0) {
/* 1141 */             double dist = Math.sqrt(distsq);
/* 1142 */             if (ntrits == -1) {
/* 1144 */               delta = Math.min(0.1D * delta, 0.5D * dist);
/* 1145 */               if (delta <= rho * 1.5D)
/* 1146 */                 delta = rho; 
/*      */             } 
/* 1149 */             ntrits = 0;
/* 1152 */             double d3 = Math.min(0.1D * dist, delta);
/* 1153 */             adelt = Math.max(d3, rho);
/* 1154 */             dsq = adelt * adelt;
/* 1155 */             state = 90;
/*      */             continue;
/*      */           } 
/* 1157 */           if (ntrits == -1) {
/* 1158 */             state = 680;
/*      */             continue;
/*      */           } 
/* 1160 */           if (ratio > 0.0D) {
/* 1161 */             state = 60;
/*      */             continue;
/*      */           } 
/* 1163 */           if (Math.max(delta, dnorm) > rho) {
/* 1164 */             state = 60;
/*      */             continue;
/*      */           } 
/*      */         case 680:
/* 1171 */           printState(680);
/* 1172 */           if (rho > this.stoppingTrustRegionRadius) {
/* 1173 */             delta = 0.5D * rho;
/* 1174 */             ratio = rho / this.stoppingTrustRegionRadius;
/* 1175 */             if (ratio <= 16.0D) {
/* 1176 */               rho = this.stoppingTrustRegionRadius;
/* 1177 */             } else if (ratio <= 250.0D) {
/* 1178 */               rho = Math.sqrt(ratio) * this.stoppingTrustRegionRadius;
/*      */             } else {
/* 1180 */               rho *= 0.1D;
/*      */             } 
/* 1182 */             delta = Math.max(delta, rho);
/* 1183 */             ntrits = 0;
/* 1184 */             nfsav = getEvaluations();
/* 1185 */             state = 60;
/*      */             continue;
/*      */           } 
/* 1191 */           if (ntrits == -1) {
/* 1192 */             state = 360;
/*      */             continue;
/*      */           } 
/*      */         case 720:
/* 1196 */           printState(720);
/* 1197 */           if (this.fAtInterpolationPoints.getEntry(this.trustRegionCenterInterpolationPointIndex) <= fsave) {
/* 1198 */             for (int i11 = 0; i11 < n; i11++) {
/* 1201 */               double d3 = lowerBound[i11];
/* 1202 */               double d4 = this.originShift.getEntry(i11) + this.trustRegionCenterOffset.getEntry(i11);
/* 1203 */               double d5 = Math.max(d3, d4);
/* 1204 */               double d6 = upperBound[i11];
/* 1205 */               this.currentBest.setEntry(i11, Math.min(d5, d6));
/* 1206 */               if (this.trustRegionCenterOffset.getEntry(i11) == this.lowerDifference.getEntry(i11))
/* 1207 */                 this.currentBest.setEntry(i11, lowerBound[i11]); 
/* 1209 */               if (this.trustRegionCenterOffset.getEntry(i11) == this.upperDifference.getEntry(i11))
/* 1210 */                 this.currentBest.setEntry(i11, upperBound[i11]); 
/*      */             } 
/* 1213 */             f = this.fAtInterpolationPoints.getEntry(this.trustRegionCenterInterpolationPointIndex);
/*      */           } 
/* 1215 */           return f;
/*      */       } 
/*      */       break;
/*      */     } 
/* 1218 */     throw new MathIllegalStateException(LocalizedFormats.SIMPLE_MESSAGE, new Object[] { "bobyqb" });
/*      */   }
/*      */   
/*      */   private double[] altmov(int knew, double adelt) {
/* 1261 */     printMethod();
/* 1263 */     int n = this.currentBest.getDimension();
/* 1264 */     int npt = this.numberOfInterpolationPoints;
/* 1266 */     ArrayRealVector glag = new ArrayRealVector(n);
/* 1267 */     ArrayRealVector hcol = new ArrayRealVector(npt);
/* 1269 */     ArrayRealVector work1 = new ArrayRealVector(n);
/* 1270 */     ArrayRealVector work2 = new ArrayRealVector(n);
/* 1272 */     for (int k = 0; k < npt; k++)
/* 1273 */       hcol.setEntry(k, 0.0D); 
/* 1275 */     for (int j = 0, max = npt - n - 1; j < max; j++) {
/* 1276 */       double tmp = this.zMatrix.getEntry(knew, j);
/* 1277 */       for (int i3 = 0; i3 < npt; i3++)
/* 1278 */         hcol.setEntry(i3, hcol.getEntry(i3) + tmp * this.zMatrix.getEntry(i3, j)); 
/*      */     } 
/* 1281 */     double alpha = hcol.getEntry(knew);
/* 1282 */     double ha = 0.5D * alpha;
/* 1286 */     for (int i = 0; i < n; i++)
/* 1287 */       glag.setEntry(i, this.bMatrix.getEntry(knew, i)); 
/* 1289 */     for (int m = 0; m < npt; m++) {
/* 1290 */       double tmp = 0.0D;
/* 1291 */       for (int i4 = 0; i4 < n; i4++)
/* 1292 */         tmp += this.interpolationPoints.getEntry(m, i4) * this.trustRegionCenterOffset.getEntry(i4); 
/* 1294 */       tmp *= hcol.getEntry(m);
/* 1295 */       for (int i3 = 0; i3 < n; i3++)
/* 1296 */         glag.setEntry(i3, glag.getEntry(i3) + tmp * this.interpolationPoints.getEntry(m, i3)); 
/*      */     } 
/* 1306 */     double presav = 0.0D;
/* 1307 */     double step = Double.NaN;
/* 1308 */     int ksav = 0;
/* 1309 */     int ibdsav = 0;
/* 1310 */     double stpsav = 0.0D;
/* 1311 */     for (int i2 = 0; i2 < npt; i2++) {
/* 1312 */       if (i2 != this.trustRegionCenterInterpolationPointIndex) {
/* 1315 */         double dderiv = 0.0D;
/* 1316 */         double distsq = 0.0D;
/* 1317 */         for (int i3 = 0; i3 < n; i3++) {
/* 1318 */           double d = this.interpolationPoints.getEntry(i2, i3) - this.trustRegionCenterOffset.getEntry(i3);
/* 1319 */           dderiv += glag.getEntry(i3) * d;
/* 1320 */           distsq += d * d;
/*      */         } 
/* 1322 */         double subd = adelt / Math.sqrt(distsq);
/* 1323 */         double slbd = -subd;
/* 1324 */         int ilbd = 0;
/* 1325 */         int iubd = 0;
/* 1326 */         double sumin = Math.min(1.0D, subd);
/* 1330 */         for (int i4 = 0; i4 < n; i4++) {
/* 1331 */           double d = this.interpolationPoints.getEntry(i2, i4) - this.trustRegionCenterOffset.getEntry(i4);
/* 1332 */           if (d > 0.0D) {
/* 1333 */             if (slbd * d < this.lowerDifference.getEntry(i4) - this.trustRegionCenterOffset.getEntry(i4)) {
/* 1334 */               slbd = (this.lowerDifference.getEntry(i4) - this.trustRegionCenterOffset.getEntry(i4)) / d;
/* 1335 */               ilbd = -i4 - 1;
/*      */             } 
/* 1337 */             if (subd * d > this.upperDifference.getEntry(i4) - this.trustRegionCenterOffset.getEntry(i4)) {
/* 1339 */               subd = Math.max(sumin, (this.upperDifference.getEntry(i4) - this.trustRegionCenterOffset.getEntry(i4)) / d);
/* 1341 */               iubd = i4 + 1;
/*      */             } 
/* 1343 */           } else if (d < 0.0D) {
/* 1344 */             if (slbd * d > this.upperDifference.getEntry(i4) - this.trustRegionCenterOffset.getEntry(i4)) {
/* 1345 */               slbd = (this.upperDifference.getEntry(i4) - this.trustRegionCenterOffset.getEntry(i4)) / d;
/* 1346 */               ilbd = i4 + 1;
/*      */             } 
/* 1348 */             if (subd * d < this.lowerDifference.getEntry(i4) - this.trustRegionCenterOffset.getEntry(i4)) {
/* 1350 */               subd = Math.max(sumin, (this.lowerDifference.getEntry(i4) - this.trustRegionCenterOffset.getEntry(i4)) / d);
/* 1352 */               iubd = -i4 - 1;
/*      */             } 
/*      */           } 
/*      */         } 
/* 1360 */         step = slbd;
/* 1361 */         int isbd = ilbd;
/* 1362 */         double vlag = Double.NaN;
/* 1363 */         if (i2 == knew) {
/* 1364 */           double diff = dderiv - 1.0D;
/* 1365 */           vlag = slbd * (dderiv - slbd * diff);
/* 1366 */           double d1 = subd * (dderiv - subd * diff);
/* 1367 */           if (Math.abs(d1) > Math.abs(vlag)) {
/* 1368 */             step = subd;
/* 1369 */             vlag = d1;
/* 1370 */             isbd = iubd;
/*      */           } 
/* 1372 */           double d2 = 0.5D * dderiv;
/* 1373 */           double d3 = d2 - diff * slbd;
/* 1374 */           double d4 = d2 - diff * subd;
/* 1375 */           if (d3 * d4 < 0.0D) {
/* 1376 */             double d5 = d2 * d2 / diff;
/* 1377 */             if (Math.abs(d5) > Math.abs(vlag)) {
/* 1378 */               step = d2 / diff;
/* 1379 */               vlag = d5;
/* 1380 */               isbd = 0;
/*      */             } 
/*      */           } 
/*      */         } else {
/* 1387 */           vlag = slbd * (1.0D - slbd);
/* 1388 */           double d = subd * (1.0D - subd);
/* 1389 */           if (Math.abs(d) > Math.abs(vlag)) {
/* 1390 */             step = subd;
/* 1391 */             vlag = d;
/* 1392 */             isbd = iubd;
/*      */           } 
/* 1394 */           if (subd > 0.5D && 
/* 1395 */             Math.abs(vlag) < 0.25D) {
/* 1396 */             step = 0.5D;
/* 1397 */             vlag = 0.25D;
/* 1398 */             isbd = 0;
/*      */           } 
/* 1401 */           vlag *= dderiv;
/*      */         } 
/* 1406 */         double tmp = step * (1.0D - step) * distsq;
/* 1407 */         double predsq = vlag * vlag * (vlag * vlag + ha * tmp * tmp);
/* 1408 */         if (predsq > presav) {
/* 1409 */           presav = predsq;
/* 1410 */           ksav = i2;
/* 1411 */           stpsav = step;
/* 1412 */           ibdsav = isbd;
/*      */         } 
/*      */       } 
/*      */     } 
/* 1418 */     for (int i1 = 0; i1 < n; i1++) {
/* 1419 */       double tmp = this.trustRegionCenterOffset.getEntry(i1) + stpsav * (this.interpolationPoints.getEntry(ksav, i1) - this.trustRegionCenterOffset.getEntry(i1));
/* 1420 */       this.newPoint.setEntry(i1, Math.max(this.lowerDifference.getEntry(i1), Math.min(this.upperDifference.getEntry(i1), tmp)));
/*      */     } 
/* 1423 */     if (ibdsav < 0)
/* 1424 */       this.newPoint.setEntry(-ibdsav - 1, this.lowerDifference.getEntry(-ibdsav - 1)); 
/* 1426 */     if (ibdsav > 0)
/* 1427 */       this.newPoint.setEntry(ibdsav - 1, this.upperDifference.getEntry(ibdsav - 1)); 
/* 1434 */     double bigstp = adelt + adelt;
/* 1435 */     int iflag = 0;
/* 1436 */     double cauchy = Double.NaN;
/* 1437 */     double csave = 0.0D;
/*      */     while (true) {
/* 1439 */       double wfixsq = 0.0D;
/* 1440 */       double ggfree = 0.0D;
/* 1441 */       for (int i3 = 0; i3 < n; i3++) {
/* 1442 */         double glagValue = glag.getEntry(i3);
/* 1443 */         work1.setEntry(i3, 0.0D);
/* 1444 */         if (Math.min(this.trustRegionCenterOffset.getEntry(i3) - this.lowerDifference.getEntry(i3), glagValue) > 0.0D || Math.max(this.trustRegionCenterOffset.getEntry(i3) - this.upperDifference.getEntry(i3), glagValue) < 0.0D) {
/* 1446 */           work1.setEntry(i3, bigstp);
/* 1448 */           ggfree += glagValue * glagValue;
/*      */         } 
/*      */       } 
/* 1451 */       if (ggfree == 0.0D)
/* 1452 */         return new double[] { alpha, 0.0D }; 
/* 1456 */       double tmp1 = adelt * adelt - wfixsq;
/* 1457 */       if (tmp1 > 0.0D) {
/* 1458 */         step = Math.sqrt(tmp1 / ggfree);
/* 1459 */         ggfree = 0.0D;
/* 1460 */         for (int i6 = 0; i6 < n; i6++) {
/* 1461 */           if (work1.getEntry(i6) == bigstp) {
/* 1462 */             double tmp2 = this.trustRegionCenterOffset.getEntry(i6) - step * glag.getEntry(i6);
/* 1463 */             if (tmp2 <= this.lowerDifference.getEntry(i6)) {
/* 1464 */               work1.setEntry(i6, this.lowerDifference.getEntry(i6) - this.trustRegionCenterOffset.getEntry(i6));
/* 1466 */               double d1 = work1.getEntry(i6);
/* 1467 */               wfixsq += d1 * d1;
/* 1468 */             } else if (tmp2 >= this.upperDifference.getEntry(i6)) {
/* 1469 */               work1.setEntry(i6, this.upperDifference.getEntry(i6) - this.trustRegionCenterOffset.getEntry(i6));
/* 1471 */               double d1 = work1.getEntry(i6);
/* 1472 */               wfixsq += d1 * d1;
/*      */             } else {
/* 1475 */               double d1 = glag.getEntry(i6);
/* 1476 */               ggfree += d1 * d1;
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/* 1485 */       double gw = 0.0D;
/* 1486 */       for (int i4 = 0; i4 < n; i4++) {
/* 1487 */         double glagValue = glag.getEntry(i4);
/* 1488 */         if (work1.getEntry(i4) == bigstp) {
/* 1489 */           work1.setEntry(i4, -step * glagValue);
/* 1490 */           double min = Math.min(this.upperDifference.getEntry(i4), this.trustRegionCenterOffset.getEntry(i4) + work1.getEntry(i4));
/* 1492 */           this.alternativeNewPoint.setEntry(i4, Math.max(this.lowerDifference.getEntry(i4), min));
/* 1493 */         } else if (work1.getEntry(i4) == 0.0D) {
/* 1494 */           this.alternativeNewPoint.setEntry(i4, this.trustRegionCenterOffset.getEntry(i4));
/* 1495 */         } else if (glagValue > 0.0D) {
/* 1496 */           this.alternativeNewPoint.setEntry(i4, this.lowerDifference.getEntry(i4));
/*      */         } else {
/* 1498 */           this.alternativeNewPoint.setEntry(i4, this.upperDifference.getEntry(i4));
/*      */         } 
/* 1500 */         gw += glagValue * work1.getEntry(i4);
/*      */       } 
/* 1508 */       double curv = 0.0D;
/* 1509 */       for (int i5 = 0; i5 < npt; i5++) {
/* 1510 */         double tmp = 0.0D;
/* 1511 */         for (int i6 = 0; i6 < n; i6++)
/* 1512 */           tmp += this.interpolationPoints.getEntry(i5, i6) * work1.getEntry(i6); 
/* 1514 */         curv += hcol.getEntry(i5) * tmp * tmp;
/*      */       } 
/* 1516 */       if (iflag == 1)
/* 1517 */         curv = -curv; 
/* 1519 */       if (curv > -gw && curv < -gw * (1.0D + Math.sqrt(2.0D))) {
/* 1521 */         double scale = -gw / curv;
/* 1522 */         for (int i6 = 0; i6 < n; i6++) {
/* 1523 */           double tmp = this.trustRegionCenterOffset.getEntry(i6) + scale * work1.getEntry(i6);
/* 1524 */           this.alternativeNewPoint.setEntry(i6, Math.max(this.lowerDifference.getEntry(i6), Math.min(this.upperDifference.getEntry(i6), tmp)));
/*      */         } 
/* 1528 */         double d1 = 0.5D * gw * scale;
/* 1529 */         cauchy = d1 * d1;
/*      */       } else {
/* 1532 */         double d1 = gw + 0.5D * curv;
/* 1533 */         cauchy = d1 * d1;
/*      */       } 
/* 1540 */       if (iflag == 0) {
/* 1541 */         for (int i6 = 0; i6 < n; i6++) {
/* 1542 */           glag.setEntry(i6, -glag.getEntry(i6));
/* 1543 */           work2.setEntry(i6, this.alternativeNewPoint.getEntry(i6));
/*      */         } 
/* 1545 */         csave = cauchy;
/* 1546 */         iflag = 1;
/*      */         continue;
/*      */       } 
/*      */       break;
/*      */     } 
/* 1551 */     if (csave > cauchy) {
/* 1552 */       for (int i3 = 0; i3 < n; i3++)
/* 1553 */         this.alternativeNewPoint.setEntry(i3, work2.getEntry(i3)); 
/* 1555 */       cauchy = csave;
/*      */     } 
/* 1558 */     return new double[] { alpha, cauchy };
/*      */   }
/*      */   
/*      */   private void prelim(double[] lowerBound, double[] upperBound) {
/* 1585 */     printMethod();
/* 1587 */     int n = this.currentBest.getDimension();
/* 1588 */     int npt = this.numberOfInterpolationPoints;
/* 1589 */     int ndim = this.bMatrix.getRowDimension();
/* 1591 */     double rhosq = this.initialTrustRegionRadius * this.initialTrustRegionRadius;
/* 1592 */     double recip = 1.0D / rhosq;
/* 1593 */     int np = n + 1;
/* 1598 */     for (int j = 0; j < n; j++) {
/* 1599 */       this.originShift.setEntry(j, this.currentBest.getEntry(j));
/* 1600 */       for (int i1 = 0; i1 < npt; i1++)
/* 1601 */         this.interpolationPoints.setEntry(i1, j, 0.0D); 
/* 1603 */       for (int m = 0; m < ndim; m++)
/* 1604 */         this.bMatrix.setEntry(m, j, 0.0D); 
/*      */     } 
/* 1607 */     for (int i = 0, max = n * np / 2; i < max; i++)
/* 1608 */       this.modelSecondDerivativesValues.setEntry(i, 0.0D); 
/* 1610 */     for (int k = 0; k < npt; k++) {
/* 1611 */       this.modelSecondDerivativesParameters.setEntry(k, 0.0D);
/* 1612 */       for (int m = 0, i1 = npt - np; m < i1; m++)
/* 1613 */         this.zMatrix.setEntry(k, m, 0.0D); 
/*      */     } 
/* 1621 */     int ipt = 0;
/* 1622 */     int jpt = 0;
/* 1623 */     double fbeg = Double.NaN;
/*      */     do {
/* 1625 */       int nfm = getEvaluations();
/* 1626 */       int nfx = nfm - n;
/* 1627 */       int nfmm = nfm - 1;
/* 1628 */       int nfxm = nfx - 1;
/* 1629 */       double stepa = 0.0D;
/* 1630 */       double stepb = 0.0D;
/* 1631 */       if (nfm <= 2 * n) {
/* 1632 */         if (nfm >= 1 && nfm <= n) {
/* 1634 */           stepa = this.initialTrustRegionRadius;
/* 1635 */           if (this.upperDifference.getEntry(nfmm) == 0.0D)
/* 1636 */             stepa = -stepa; 
/* 1639 */           this.interpolationPoints.setEntry(nfm, nfmm, stepa);
/* 1640 */         } else if (nfm > n) {
/* 1641 */           stepa = this.interpolationPoints.getEntry(nfx, nfxm);
/* 1642 */           stepb = -this.initialTrustRegionRadius;
/* 1643 */           if (this.lowerDifference.getEntry(nfxm) == 0.0D)
/* 1644 */             stepb = Math.min(2.0D * this.initialTrustRegionRadius, this.upperDifference.getEntry(nfxm)); 
/* 1647 */           if (this.upperDifference.getEntry(nfxm) == 0.0D)
/* 1648 */             stepb = Math.max(-2.0D * this.initialTrustRegionRadius, this.lowerDifference.getEntry(nfxm)); 
/* 1651 */           this.interpolationPoints.setEntry(nfm, nfxm, stepb);
/*      */         } 
/*      */       } else {
/* 1654 */         int tmp1 = (nfm - np) / n;
/* 1655 */         jpt = nfm - tmp1 * n - n;
/* 1656 */         ipt = jpt + tmp1;
/* 1657 */         if (ipt > n) {
/* 1658 */           int tmp2 = jpt;
/* 1659 */           jpt = ipt - n;
/* 1660 */           ipt = tmp2;
/*      */         } 
/* 1663 */         int iptMinus1 = ipt - 1;
/* 1664 */         int jptMinus1 = jpt - 1;
/* 1665 */         this.interpolationPoints.setEntry(nfm, iptMinus1, this.interpolationPoints.getEntry(ipt, iptMinus1));
/* 1666 */         this.interpolationPoints.setEntry(nfm, jptMinus1, this.interpolationPoints.getEntry(jpt, jptMinus1));
/*      */       } 
/* 1672 */       for (int m = 0; m < n; m++) {
/* 1673 */         this.currentBest.setEntry(m, Math.min(Math.max(lowerBound[m], this.originShift.getEntry(m) + this.interpolationPoints.getEntry(nfm, m)), upperBound[m]));
/* 1676 */         if (this.interpolationPoints.getEntry(nfm, m) == this.lowerDifference.getEntry(m))
/* 1677 */           this.currentBest.setEntry(m, lowerBound[m]); 
/* 1679 */         if (this.interpolationPoints.getEntry(nfm, m) == this.upperDifference.getEntry(m))
/* 1680 */           this.currentBest.setEntry(m, upperBound[m]); 
/*      */       } 
/* 1684 */       double objectiveValue = computeObjectiveValue(this.currentBest.toArray());
/* 1685 */       double f = this.isMinimize ? objectiveValue : -objectiveValue;
/* 1686 */       int numEval = getEvaluations();
/* 1687 */       this.fAtInterpolationPoints.setEntry(nfm, f);
/* 1689 */       if (numEval == 1) {
/* 1690 */         fbeg = f;
/* 1691 */         this.trustRegionCenterInterpolationPointIndex = 0;
/* 1692 */       } else if (f < this.fAtInterpolationPoints.getEntry(this.trustRegionCenterInterpolationPointIndex)) {
/* 1693 */         this.trustRegionCenterInterpolationPointIndex = nfm;
/*      */       } 
/* 1702 */       if (numEval <= 2 * n + 1) {
/* 1703 */         if (numEval >= 2 && numEval <= n + 1) {
/* 1705 */           this.gradientAtTrustRegionCenter.setEntry(nfmm, (f - fbeg) / stepa);
/* 1706 */           if (npt < numEval + n) {
/* 1707 */             double oneOverStepA = 1.0D / stepa;
/* 1708 */             this.bMatrix.setEntry(0, nfmm, -oneOverStepA);
/* 1709 */             this.bMatrix.setEntry(nfm, nfmm, oneOverStepA);
/* 1710 */             this.bMatrix.setEntry(npt + nfmm, nfmm, -0.5D * rhosq);
/*      */           } 
/* 1713 */         } else if (numEval >= n + 2) {
/* 1714 */           int ih = nfx * (nfx + 1) / 2 - 1;
/* 1715 */           double tmp = (f - fbeg) / stepb;
/* 1716 */           double diff = stepb - stepa;
/* 1717 */           this.modelSecondDerivativesValues.setEntry(ih, 2.0D * (tmp - this.gradientAtTrustRegionCenter.getEntry(nfxm)) / diff);
/* 1718 */           this.gradientAtTrustRegionCenter.setEntry(nfxm, (this.gradientAtTrustRegionCenter.getEntry(nfxm) * stepb - tmp * stepa) / diff);
/* 1719 */           if (stepa * stepb < 0.0D && 
/* 1720 */             f < this.fAtInterpolationPoints.getEntry(nfm - n)) {
/* 1721 */             this.fAtInterpolationPoints.setEntry(nfm, this.fAtInterpolationPoints.getEntry(nfm - n));
/* 1722 */             this.fAtInterpolationPoints.setEntry(nfm - n, f);
/* 1723 */             if (this.trustRegionCenterInterpolationPointIndex == nfm)
/* 1724 */               this.trustRegionCenterInterpolationPointIndex = nfm - n; 
/* 1726 */             this.interpolationPoints.setEntry(nfm - n, nfxm, stepb);
/* 1727 */             this.interpolationPoints.setEntry(nfm, nfxm, stepa);
/*      */           } 
/* 1730 */           this.bMatrix.setEntry(0, nfxm, -(stepa + stepb) / stepa * stepb);
/* 1731 */           this.bMatrix.setEntry(nfm, nfxm, -0.5D / this.interpolationPoints.getEntry(nfm - n, nfxm));
/* 1732 */           this.bMatrix.setEntry(nfm - n, nfxm, -this.bMatrix.getEntry(0, nfxm) - this.bMatrix.getEntry(nfm, nfxm));
/* 1734 */           this.zMatrix.setEntry(0, nfxm, Math.sqrt(2.0D) / stepa * stepb);
/* 1735 */           this.zMatrix.setEntry(nfm, nfxm, Math.sqrt(0.5D) / rhosq);
/* 1737 */           this.zMatrix.setEntry(nfm - n, nfxm, -this.zMatrix.getEntry(0, nfxm) - this.zMatrix.getEntry(nfm, nfxm));
/*      */         } 
/*      */       } else {
/* 1745 */         this.zMatrix.setEntry(0, nfxm, recip);
/* 1746 */         this.zMatrix.setEntry(nfm, nfxm, recip);
/* 1747 */         this.zMatrix.setEntry(ipt, nfxm, -recip);
/* 1748 */         this.zMatrix.setEntry(jpt, nfxm, -recip);
/* 1750 */         int ih = ipt * (ipt - 1) / 2 + jpt - 1;
/* 1751 */         double tmp = this.interpolationPoints.getEntry(nfm, ipt - 1) * this.interpolationPoints.getEntry(nfm, jpt - 1);
/* 1752 */         this.modelSecondDerivativesValues.setEntry(ih, (fbeg - this.fAtInterpolationPoints.getEntry(ipt) - this.fAtInterpolationPoints.getEntry(jpt) + f) / tmp);
/*      */       } 
/* 1755 */     } while (getEvaluations() < npt);
/*      */   }
/*      */   
/*      */   private double[] trsbox(double delta, ArrayRealVector gnew, ArrayRealVector xbdi, ArrayRealVector s, ArrayRealVector hs, ArrayRealVector hred) {
/* 1813 */     printMethod();
/* 1815 */     int n = this.currentBest.getDimension();
/* 1816 */     int npt = this.numberOfInterpolationPoints;
/* 1818 */     double dsq = Double.NaN;
/* 1819 */     double crvmin = Double.NaN;
/* 1824 */     double beta = 0.0D;
/* 1825 */     int iact = -1;
/* 1826 */     int nact = 0;
/* 1827 */     double angt = 0.0D;
/* 1829 */     double temp = 0.0D, xsav = 0.0D, xsum = 0.0D, angbd = 0.0D, dredg = 0.0D, sredg = 0.0D;
/* 1831 */     double resid = 0.0D, delsq = 0.0D, ggsav = 0.0D, tempa = 0.0D, tempb = 0.0D;
/* 1832 */     double redmax = 0.0D, dredsq = 0.0D, redsav = 0.0D, gredsq = 0.0D, rednew = 0.0D;
/* 1833 */     int itcsav = 0;
/* 1834 */     double rdprev = 0.0D, rdnext = 0.0D, stplen = 0.0D, stepsq = 0.0D;
/* 1835 */     int itermax = 0;
/* 1848 */     int iterc = 0;
/* 1849 */     nact = 0;
/* 1850 */     for (int i = 0; i < n; i++) {
/* 1851 */       xbdi.setEntry(i, 0.0D);
/* 1852 */       if (this.trustRegionCenterOffset.getEntry(i) <= this.lowerDifference.getEntry(i)) {
/* 1853 */         if (this.gradientAtTrustRegionCenter.getEntry(i) >= 0.0D)
/* 1854 */           xbdi.setEntry(i, -1.0D); 
/* 1856 */       } else if (this.trustRegionCenterOffset.getEntry(i) >= this.upperDifference.getEntry(i) && 
/* 1857 */         this.gradientAtTrustRegionCenter.getEntry(i) <= 0.0D) {
/* 1858 */         xbdi.setEntry(i, 1.0D);
/*      */       } 
/* 1861 */       if (xbdi.getEntry(i) != 0.0D)
/* 1862 */         nact++; 
/* 1864 */       this.trialStepPoint.setEntry(i, 0.0D);
/* 1865 */       gnew.setEntry(i, this.gradientAtTrustRegionCenter.getEntry(i));
/*      */     } 
/* 1867 */     delsq = delta * delta;
/* 1868 */     double qred = 0.0D;
/* 1869 */     crvmin = -1.0D;
/* 1877 */     int state = 20;
/*      */     while (true) {
/*      */       double ds;
/*      */       int iu;
/*      */       double dhd;
/*      */       double dhs;
/*      */       double cth;
/*      */       double shs;
/*      */       double sth;
/*      */       double sdec;
/*      */       double blen;
/*      */       int isav;
/*      */       int m;
/*      */       int ih;
/*      */       int j;
/*      */       RealVector tmp;
/*      */       int k;
/*      */       int i1;
/* 1879 */       switch (state) {
/*      */         case 20:
/* 1881 */           printState(20);
/* 1882 */           beta = 0.0D;
/*      */         case 30:
/* 1885 */           printState(30);
/* 1886 */           stepsq = 0.0D;
/* 1887 */           for (m = 0; m < n; m++) {
/* 1888 */             if (xbdi.getEntry(m) != 0.0D) {
/* 1889 */               s.setEntry(m, 0.0D);
/* 1890 */             } else if (beta == 0.0D) {
/* 1891 */               s.setEntry(m, -gnew.getEntry(m));
/*      */             } else {
/* 1893 */               s.setEntry(m, beta * s.getEntry(m) - gnew.getEntry(m));
/*      */             } 
/* 1896 */             double d1 = s.getEntry(m);
/* 1897 */             stepsq += d1 * d1;
/*      */           } 
/* 1899 */           if (stepsq == 0.0D) {
/* 1900 */             state = 190;
/*      */             continue;
/*      */           } 
/* 1902 */           if (beta == 0.0D) {
/* 1903 */             gredsq = stepsq;
/* 1904 */             itermax = iterc + n - nact;
/*      */           } 
/* 1906 */           if (gredsq * delsq <= qred * 1.0E-4D * qred) {
/* 1907 */             state = 190;
/*      */             continue;
/*      */           } 
/* 1915 */           state = 210;
/*      */           continue;
/*      */         case 50:
/* 1918 */           printState(50);
/* 1919 */           resid = delsq;
/* 1920 */           ds = 0.0D;
/* 1921 */           shs = 0.0D;
/* 1922 */           for (m = 0; m < n; m++) {
/* 1923 */             if (xbdi.getEntry(m) == 0.0D) {
/* 1925 */               double d1 = this.trialStepPoint.getEntry(m);
/* 1926 */               resid -= d1 * d1;
/* 1927 */               ds += s.getEntry(m) * this.trialStepPoint.getEntry(m);
/* 1928 */               shs += s.getEntry(m) * hs.getEntry(m);
/*      */             } 
/*      */           } 
/* 1931 */           if (resid <= 0.0D) {
/* 1932 */             state = 90;
/*      */             continue;
/*      */           } 
/* 1934 */           temp = Math.sqrt(stepsq * resid + ds * ds);
/* 1935 */           if (ds < 0.0D) {
/* 1936 */             blen = (temp - ds) / stepsq;
/*      */           } else {
/* 1938 */             blen = resid / (temp + ds);
/*      */           } 
/* 1940 */           stplen = blen;
/* 1941 */           if (shs > 0.0D)
/* 1943 */             stplen = Math.min(blen, gredsq / shs); 
/* 1949 */           iact = -1;
/* 1950 */           for (m = 0; m < n; m++) {
/* 1951 */             if (s.getEntry(m) != 0.0D) {
/* 1952 */               xsum = this.trustRegionCenterOffset.getEntry(m) + this.trialStepPoint.getEntry(m);
/* 1953 */               if (s.getEntry(m) > 0.0D) {
/* 1954 */                 temp = (this.upperDifference.getEntry(m) - xsum) / s.getEntry(m);
/*      */               } else {
/* 1956 */                 temp = (this.lowerDifference.getEntry(m) - xsum) / s.getEntry(m);
/*      */               } 
/* 1958 */               if (temp < stplen) {
/* 1959 */                 stplen = temp;
/* 1960 */                 iact = m;
/*      */               } 
/*      */             } 
/*      */           } 
/* 1967 */           sdec = 0.0D;
/* 1968 */           if (stplen > 0.0D) {
/* 1969 */             iterc++;
/* 1970 */             temp = shs / stepsq;
/* 1971 */             if (iact == -1 && temp > 0.0D) {
/* 1972 */               crvmin = Math.min(crvmin, temp);
/* 1973 */               if (crvmin == -1.0D)
/* 1974 */                 crvmin = temp; 
/*      */             } 
/* 1977 */             ggsav = gredsq;
/* 1978 */             gredsq = 0.0D;
/* 1979 */             for (m = 0; m < n; m++) {
/* 1980 */               gnew.setEntry(m, gnew.getEntry(m) + stplen * hs.getEntry(m));
/* 1981 */               if (xbdi.getEntry(m) == 0.0D) {
/* 1983 */                 double d = gnew.getEntry(m);
/* 1984 */                 gredsq += d * d;
/*      */               } 
/* 1986 */               this.trialStepPoint.setEntry(m, this.trialStepPoint.getEntry(m) + stplen * s.getEntry(m));
/*      */             } 
/* 1989 */             double d1 = stplen * (ggsav - 0.5D * stplen * shs);
/* 1990 */             sdec = Math.max(d1, 0.0D);
/* 1991 */             qred += sdec;
/*      */           } 
/* 1996 */           if (iact >= 0) {
/* 1997 */             nact++;
/* 1998 */             xbdi.setEntry(iact, 1.0D);
/* 1999 */             if (s.getEntry(iact) < 0.0D)
/* 2000 */               xbdi.setEntry(iact, -1.0D); 
/* 2003 */             double d1 = this.trialStepPoint.getEntry(iact);
/* 2004 */             delsq -= d1 * d1;
/* 2005 */             if (delsq <= 0.0D) {
/* 2006 */               state = 190;
/*      */               continue;
/*      */             } 
/* 2008 */             state = 20;
/*      */             continue;
/*      */           } 
/* 2014 */           if (stplen < blen) {
/* 2015 */             if (iterc == itermax) {
/* 2016 */               state = 190;
/*      */               continue;
/*      */             } 
/* 2018 */             if (sdec <= qred * 0.01D) {
/* 2019 */               state = 190;
/*      */               continue;
/*      */             } 
/* 2021 */             beta = gredsq / ggsav;
/* 2022 */             state = 30;
/*      */             continue;
/*      */           } 
/*      */         case 90:
/* 2026 */           printState(90);
/* 2027 */           crvmin = 0.0D;
/*      */         case 100:
/* 2035 */           printState(100);
/* 2036 */           if (nact >= n - 1) {
/* 2037 */             state = 190;
/*      */             continue;
/*      */           } 
/* 2039 */           dredsq = 0.0D;
/* 2040 */           dredg = 0.0D;
/* 2041 */           gredsq = 0.0D;
/* 2042 */           for (m = 0; m < n; m++) {
/* 2043 */             if (xbdi.getEntry(m) == 0.0D) {
/* 2045 */               double d1 = this.trialStepPoint.getEntry(m);
/* 2046 */               dredsq += d1 * d1;
/* 2047 */               dredg += this.trialStepPoint.getEntry(m) * gnew.getEntry(m);
/* 2049 */               d1 = gnew.getEntry(m);
/* 2050 */               gredsq += d1 * d1;
/* 2051 */               s.setEntry(m, this.trialStepPoint.getEntry(m));
/*      */             } else {
/* 2053 */               s.setEntry(m, 0.0D);
/*      */             } 
/*      */           } 
/* 2056 */           itcsav = iterc;
/* 2057 */           state = 210;
/*      */           continue;
/*      */         case 120:
/* 2062 */           printState(120);
/* 2063 */           iterc++;
/* 2064 */           temp = gredsq * dredsq - dredg * dredg;
/* 2065 */           if (temp <= qred * 1.0E-4D * qred) {
/* 2066 */             state = 190;
/*      */             continue;
/*      */           } 
/* 2068 */           temp = Math.sqrt(temp);
/* 2069 */           for (m = 0; m < n; m++) {
/* 2070 */             if (xbdi.getEntry(m) == 0.0D) {
/* 2071 */               s.setEntry(m, (dredg * this.trialStepPoint.getEntry(m) - dredsq * gnew.getEntry(m)) / temp);
/*      */             } else {
/* 2073 */               s.setEntry(m, 0.0D);
/*      */             } 
/*      */           } 
/* 2076 */           sredg = -temp;
/* 2083 */           angbd = 1.0D;
/* 2084 */           iact = -1;
/* 2085 */           for (m = 0; m < n; m++) {
/* 2086 */             if (xbdi.getEntry(m) == 0.0D) {
/* 2087 */               tempa = this.trustRegionCenterOffset.getEntry(m) + this.trialStepPoint.getEntry(m) - this.lowerDifference.getEntry(m);
/* 2088 */               tempb = this.upperDifference.getEntry(m) - this.trustRegionCenterOffset.getEntry(m) - this.trialStepPoint.getEntry(m);
/* 2089 */               if (tempa <= 0.0D) {
/* 2090 */                 nact++;
/* 2091 */                 xbdi.setEntry(m, -1.0D);
/* 2092 */                 state = 100;
/*      */                 break;
/*      */               } 
/* 2093 */               if (tempb <= 0.0D) {
/* 2094 */                 nact++;
/* 2095 */                 xbdi.setEntry(m, 1.0D);
/* 2096 */                 state = 100;
/*      */                 break;
/*      */               } 
/* 2099 */               double d1 = this.trialStepPoint.getEntry(m);
/* 2101 */               double d2 = s.getEntry(m);
/* 2102 */               double ssq = d1 * d1 + d2 * d2;
/* 2104 */               d1 = this.trustRegionCenterOffset.getEntry(m) - this.lowerDifference.getEntry(m);
/* 2105 */               temp = ssq - d1 * d1;
/* 2106 */               if (temp > 0.0D) {
/* 2107 */                 temp = Math.sqrt(temp) - s.getEntry(m);
/* 2108 */                 if (angbd * temp > tempa) {
/* 2109 */                   angbd = tempa / temp;
/* 2110 */                   iact = m;
/* 2111 */                   xsav = -1.0D;
/*      */                 } 
/*      */               } 
/* 2115 */               d1 = this.upperDifference.getEntry(m) - this.trustRegionCenterOffset.getEntry(m);
/* 2116 */               temp = ssq - d1 * d1;
/* 2117 */               if (temp > 0.0D) {
/* 2118 */                 temp = Math.sqrt(temp) + s.getEntry(m);
/* 2119 */                 if (angbd * temp > tempb) {
/* 2120 */                   angbd = tempb / temp;
/* 2121 */                   iact = m;
/* 2122 */                   xsav = 1.0D;
/*      */                 } 
/*      */               } 
/*      */             } 
/*      */           } 
/* 2130 */           state = 210;
/*      */           continue;
/*      */         case 150:
/* 2133 */           printState(150);
/* 2134 */           shs = 0.0D;
/* 2135 */           dhs = 0.0D;
/* 2136 */           dhd = 0.0D;
/* 2137 */           for (m = 0; m < n; m++) {
/* 2138 */             if (xbdi.getEntry(m) == 0.0D) {
/* 2139 */               shs += s.getEntry(m) * hs.getEntry(m);
/* 2140 */               dhs += this.trialStepPoint.getEntry(m) * hs.getEntry(m);
/* 2141 */               dhd += this.trialStepPoint.getEntry(m) * hred.getEntry(m);
/*      */             } 
/*      */           } 
/* 2149 */           redmax = 0.0D;
/* 2150 */           isav = -1;
/* 2151 */           redsav = 0.0D;
/* 2152 */           iu = (int)(angbd * 17.0D + 3.1D);
/* 2153 */           for (m = 0; m < iu; m++) {
/* 2154 */             angt = angbd * m / iu;
/* 2155 */             double d = (angt + angt) / (1.0D + angt * angt);
/* 2156 */             temp = shs + angt * (angt * dhd - dhs - dhs);
/* 2157 */             rednew = d * (angt * dredg - sredg - 0.5D * d * temp);
/* 2158 */             if (rednew > redmax) {
/* 2159 */               redmax = rednew;
/* 2160 */               isav = m;
/* 2161 */               rdprev = redsav;
/* 2162 */             } else if (m == isav + 1) {
/* 2163 */               rdnext = rednew;
/*      */             } 
/* 2165 */             redsav = rednew;
/*      */           } 
/* 2171 */           if (isav < 0) {
/* 2172 */             state = 190;
/*      */             continue;
/*      */           } 
/* 2174 */           if (isav < iu) {
/* 2175 */             temp = (rdnext - rdprev) / (redmax + redmax - rdprev - rdnext);
/* 2176 */             angt = angbd * (isav + 0.5D * temp) / iu;
/*      */           } 
/* 2178 */           cth = (1.0D - angt * angt) / (1.0D + angt * angt);
/* 2179 */           sth = (angt + angt) / (1.0D + angt * angt);
/* 2180 */           temp = shs + angt * (angt * dhd - dhs - dhs);
/* 2181 */           sdec = sth * (angt * dredg - sredg - 0.5D * sth * temp);
/* 2182 */           if (sdec <= 0.0D) {
/* 2183 */             state = 190;
/*      */             continue;
/*      */           } 
/* 2190 */           dredg = 0.0D;
/* 2191 */           gredsq = 0.0D;
/* 2192 */           for (m = 0; m < n; m++) {
/* 2193 */             gnew.setEntry(m, gnew.getEntry(m) + (cth - 1.0D) * hred.getEntry(m) + sth * hs.getEntry(m));
/* 2194 */             if (xbdi.getEntry(m) == 0.0D) {
/* 2195 */               this.trialStepPoint.setEntry(m, cth * this.trialStepPoint.getEntry(m) + sth * s.getEntry(m));
/* 2196 */               dredg += this.trialStepPoint.getEntry(m) * gnew.getEntry(m);
/* 2198 */               double d1 = gnew.getEntry(m);
/* 2199 */               gredsq += d1 * d1;
/*      */             } 
/* 2201 */             hred.setEntry(m, cth * hred.getEntry(m) + sth * hs.getEntry(m));
/*      */           } 
/* 2203 */           qred += sdec;
/* 2204 */           if (iact >= 0 && isav == iu) {
/* 2205 */             nact++;
/* 2206 */             xbdi.setEntry(iact, xsav);
/* 2207 */             state = 100;
/*      */             continue;
/*      */           } 
/* 2213 */           if (sdec > qred * 0.01D) {
/* 2214 */             state = 120;
/*      */             continue;
/*      */           } 
/*      */         case 190:
/* 2218 */           printState(190);
/* 2219 */           dsq = 0.0D;
/* 2220 */           for (m = 0; m < n; m++) {
/* 2223 */             double min = Math.min(this.trustRegionCenterOffset.getEntry(m) + this.trialStepPoint.getEntry(m), this.upperDifference.getEntry(m));
/* 2225 */             this.newPoint.setEntry(m, Math.max(min, this.lowerDifference.getEntry(m)));
/* 2226 */             if (xbdi.getEntry(m) == -1.0D)
/* 2227 */               this.newPoint.setEntry(m, this.lowerDifference.getEntry(m)); 
/* 2229 */             if (xbdi.getEntry(m) == 1.0D)
/* 2230 */               this.newPoint.setEntry(m, this.upperDifference.getEntry(m)); 
/* 2232 */             this.trialStepPoint.setEntry(m, this.newPoint.getEntry(m) - this.trustRegionCenterOffset.getEntry(m));
/* 2234 */             double d1 = this.trialStepPoint.getEntry(m);
/* 2235 */             dsq += d1 * d1;
/*      */           } 
/* 2237 */           return new double[] { dsq, crvmin };
/*      */         case 210:
/* 2244 */           printState(210);
/* 2245 */           ih = 0;
/* 2246 */           for (j = 0; j < n; j++) {
/* 2247 */             hs.setEntry(j, 0.0D);
/* 2248 */             for (int i2 = 0; i2 <= j; i2++) {
/* 2249 */               if (i2 < j)
/* 2250 */                 hs.setEntry(j, hs.getEntry(j) + this.modelSecondDerivativesValues.getEntry(ih) * s.getEntry(i2)); 
/* 2252 */               hs.setEntry(i2, hs.getEntry(i2) + this.modelSecondDerivativesValues.getEntry(ih) * s.getEntry(j));
/* 2253 */               ih++;
/*      */             } 
/*      */           } 
/* 2256 */           tmp = this.interpolationPoints.operate((RealVector)s).ebeMultiply((RealVector)this.modelSecondDerivativesParameters);
/* 2257 */           for (k = 0; k < npt; k++) {
/* 2258 */             if (this.modelSecondDerivativesParameters.getEntry(k) != 0.0D)
/* 2259 */               for (int i2 = 0; i2 < n; i2++)
/* 2260 */                 hs.setEntry(i2, hs.getEntry(i2) + tmp.getEntry(k) * this.interpolationPoints.getEntry(k, i2));  
/*      */           } 
/* 2264 */           if (crvmin != 0.0D) {
/* 2265 */             state = 50;
/*      */             continue;
/*      */           } 
/* 2267 */           if (iterc > itcsav) {
/* 2268 */             state = 150;
/*      */             continue;
/*      */           } 
/* 2270 */           for (i1 = 0; i1 < n; i1++)
/* 2271 */             hred.setEntry(i1, hs.getEntry(i1)); 
/* 2273 */           state = 120;
/*      */           continue;
/*      */       } 
/*      */       break;
/*      */     } 
/* 2276 */     throw new MathIllegalStateException(LocalizedFormats.SIMPLE_MESSAGE, new Object[] { "trsbox" });
/*      */   }
/*      */   
/*      */   private void update(double beta, double denom, int knew) {
/* 2301 */     printMethod();
/* 2303 */     int n = this.currentBest.getDimension();
/* 2304 */     int npt = this.numberOfInterpolationPoints;
/* 2305 */     int nptm = npt - n - 1;
/* 2308 */     ArrayRealVector work = new ArrayRealVector(npt + n);
/* 2310 */     double ztest = 0.0D;
/* 2311 */     for (int k = 0; k < npt; k++) {
/* 2312 */       for (int i2 = 0; i2 < nptm; i2++)
/* 2314 */         ztest = Math.max(ztest, Math.abs(this.zMatrix.getEntry(k, i2))); 
/*      */     } 
/* 2317 */     ztest *= 1.0E-20D;
/* 2321 */     for (int j = 1; j < nptm; j++) {
/* 2322 */       double d = this.zMatrix.getEntry(knew, j);
/* 2323 */       if (Math.abs(d) > ztest) {
/* 2325 */         double d7 = this.zMatrix.getEntry(knew, 0);
/* 2327 */         double d3 = this.zMatrix.getEntry(knew, j);
/* 2328 */         double d4 = Math.sqrt(d7 * d7 + d3 * d3);
/* 2329 */         double d5 = this.zMatrix.getEntry(knew, 0) / d4;
/* 2330 */         double d6 = this.zMatrix.getEntry(knew, j) / d4;
/* 2331 */         for (int i2 = 0; i2 < npt; i2++) {
/* 2332 */           double d8 = d5 * this.zMatrix.getEntry(i2, 0) + d6 * this.zMatrix.getEntry(i2, j);
/* 2333 */           this.zMatrix.setEntry(i2, j, d5 * this.zMatrix.getEntry(i2, j) - d6 * this.zMatrix.getEntry(i2, 0));
/* 2334 */           this.zMatrix.setEntry(i2, 0, d8);
/*      */         } 
/*      */       } 
/* 2337 */       this.zMatrix.setEntry(knew, j, 0.0D);
/*      */     } 
/* 2343 */     for (int i = 0; i < npt; i++)
/* 2344 */       work.setEntry(i, this.zMatrix.getEntry(knew, 0) * this.zMatrix.getEntry(i, 0)); 
/* 2346 */     double alpha = work.getEntry(knew);
/* 2347 */     double tau = this.lagrangeValuesAtNewPoint.getEntry(knew);
/* 2348 */     this.lagrangeValuesAtNewPoint.setEntry(knew, this.lagrangeValuesAtNewPoint.getEntry(knew) - 1.0D);
/* 2352 */     double sqrtDenom = Math.sqrt(denom);
/* 2353 */     double d1 = tau / sqrtDenom;
/* 2354 */     double d2 = this.zMatrix.getEntry(knew, 0) / sqrtDenom;
/* 2355 */     for (int i1 = 0; i1 < npt; i1++)
/* 2356 */       this.zMatrix.setEntry(i1, 0, d1 * this.zMatrix.getEntry(i1, 0) - d2 * this.lagrangeValuesAtNewPoint.getEntry(i1)); 
/* 2362 */     for (int m = 0; m < n; m++) {
/* 2363 */       int jp = npt + m;
/* 2364 */       work.setEntry(jp, this.bMatrix.getEntry(knew, m));
/* 2365 */       double d3 = (alpha * this.lagrangeValuesAtNewPoint.getEntry(jp) - tau * work.getEntry(jp)) / denom;
/* 2366 */       double d4 = (-beta * work.getEntry(jp) - tau * this.lagrangeValuesAtNewPoint.getEntry(jp)) / denom;
/* 2367 */       for (int i2 = 0; i2 <= jp; i2++) {
/* 2368 */         this.bMatrix.setEntry(i2, m, this.bMatrix.getEntry(i2, m) + d3 * this.lagrangeValuesAtNewPoint.getEntry(i2) + d4 * work.getEntry(i2));
/* 2370 */         if (i2 >= npt)
/* 2371 */           this.bMatrix.setEntry(jp, i2 - npt, this.bMatrix.getEntry(i2, m)); 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void setup(double[] lowerBound, double[] upperBound) {
/* 2385 */     printMethod();
/* 2387 */     double[] init = getStartPoint();
/* 2388 */     int dimension = init.length;
/* 2391 */     if (dimension < 2)
/* 2392 */       throw new NumberIsTooSmallException(Integer.valueOf(dimension), Integer.valueOf(2), true); 
/* 2395 */     int[] nPointsInterval = { dimension + 2, (dimension + 2) * (dimension + 1) / 2 };
/* 2396 */     if (this.numberOfInterpolationPoints < nPointsInterval[0] || this.numberOfInterpolationPoints > nPointsInterval[1])
/* 2398 */       throw new OutOfRangeException(LocalizedFormats.NUMBER_OF_INTERPOLATION_POINTS, Integer.valueOf(this.numberOfInterpolationPoints), Integer.valueOf(nPointsInterval[0]), Integer.valueOf(nPointsInterval[1])); 
/* 2405 */     this.boundDifference = new double[dimension];
/* 2407 */     double requiredMinDiff = 2.0D * this.initialTrustRegionRadius;
/* 2408 */     double minDiff = Double.POSITIVE_INFINITY;
/* 2409 */     for (int i = 0; i < dimension; i++) {
/* 2410 */       this.boundDifference[i] = upperBound[i] - lowerBound[i];
/* 2411 */       minDiff = Math.min(minDiff, this.boundDifference[i]);
/*      */     } 
/* 2413 */     if (minDiff < requiredMinDiff)
/* 2414 */       this.initialTrustRegionRadius = minDiff / 3.0D; 
/* 2418 */     this.bMatrix = new Array2DRowRealMatrix(dimension + this.numberOfInterpolationPoints, dimension);
/* 2420 */     this.zMatrix = new Array2DRowRealMatrix(this.numberOfInterpolationPoints, this.numberOfInterpolationPoints - dimension - 1);
/* 2422 */     this.interpolationPoints = new Array2DRowRealMatrix(this.numberOfInterpolationPoints, dimension);
/* 2424 */     this.originShift = new ArrayRealVector(dimension);
/* 2425 */     this.fAtInterpolationPoints = new ArrayRealVector(this.numberOfInterpolationPoints);
/* 2426 */     this.trustRegionCenterOffset = new ArrayRealVector(dimension);
/* 2427 */     this.gradientAtTrustRegionCenter = new ArrayRealVector(dimension);
/* 2428 */     this.lowerDifference = new ArrayRealVector(dimension);
/* 2429 */     this.upperDifference = new ArrayRealVector(dimension);
/* 2430 */     this.modelSecondDerivativesParameters = new ArrayRealVector(this.numberOfInterpolationPoints);
/* 2431 */     this.newPoint = new ArrayRealVector(dimension);
/* 2432 */     this.alternativeNewPoint = new ArrayRealVector(dimension);
/* 2433 */     this.trialStepPoint = new ArrayRealVector(dimension);
/* 2434 */     this.lagrangeValuesAtNewPoint = new ArrayRealVector(dimension + this.numberOfInterpolationPoints);
/* 2435 */     this.modelSecondDerivativesValues = new ArrayRealVector(dimension * (dimension + 1) / 2);
/*      */   }
/*      */   
/*      */   private static double[] fillNewArray(int n, double value) {
/* 2448 */     double[] ds = new double[n];
/* 2449 */     Arrays.fill(ds, value);
/* 2450 */     return ds;
/*      */   }
/*      */   
/*      */   private static String caller(int n) {
/* 2455 */     Throwable t = new Throwable();
/* 2456 */     StackTraceElement[] elements = t.getStackTrace();
/* 2457 */     StackTraceElement e = elements[n];
/* 2458 */     return e.getMethodName() + " (at line " + e.getLineNumber() + ")";
/*      */   }
/*      */   
/*      */   private static void printState(int s) {}
/*      */   
/*      */   private static void printMethod() {}
/*      */   
/*      */   private static class PathIsExploredException extends RuntimeException {
/*      */     private static final long serialVersionUID = 745350979634801853L;
/*      */     
/*      */     private static final String PATH_IS_EXPLORED = "If this exception is thrown, just remove it from the code";
/*      */     
/*      */     PathIsExploredException() {
/* 2480 */       super("If this exception is thrown, just remove it from the code " + BOBYQAOptimizer.caller(3));
/*      */     }
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\optimization\direct\BOBYQAOptimizer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
/*      */ package org.apache.commons.math3.stat.regression;
/*      */ 
/*      */ import java.util.Arrays;
/*      */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*      */ import org.apache.commons.math3.util.FastMath;
/*      */ import org.apache.commons.math3.util.MathArrays;
/*      */ 
/*      */ public class MillerUpdatingRegression implements UpdatingMultipleLinearRegression {
/*      */   private final int nvars;
/*      */   
/*      */   private final double[] d;
/*      */   
/*      */   private final double[] rhs;
/*      */   
/*      */   private final double[] r;
/*      */   
/*      */   private final double[] tol;
/*      */   
/*      */   private final double[] rss;
/*      */   
/*      */   private final int[] vorder;
/*      */   
/*      */   private final double[] work_tolset;
/*      */   
/*   62 */   private long nobs = 0L;
/*      */   
/*   64 */   private double sserr = 0.0D;
/*      */   
/*      */   private boolean rss_set = false;
/*      */   
/*      */   private boolean tol_set = false;
/*      */   
/*      */   private final boolean[] lindep;
/*      */   
/*      */   private final double[] x_sing;
/*      */   
/*      */   private final double[] work_sing;
/*      */   
/*   76 */   private double sumy = 0.0D;
/*      */   
/*   78 */   private double sumsqy = 0.0D;
/*      */   
/*      */   private boolean hasIntercept;
/*      */   
/*      */   private final double epsilon;
/*      */   
/*      */   private MillerUpdatingRegression() {
/*   89 */     this.d = null;
/*   90 */     this.hasIntercept = false;
/*   91 */     this.lindep = null;
/*   92 */     this.nobs = -1L;
/*   93 */     this.nvars = -1;
/*   94 */     this.r = null;
/*   95 */     this.rhs = null;
/*   96 */     this.rss = null;
/*   97 */     this.rss_set = false;
/*   98 */     this.sserr = Double.NaN;
/*   99 */     this.sumsqy = Double.NaN;
/*  100 */     this.sumy = Double.NaN;
/*  101 */     this.tol = null;
/*  102 */     this.tol_set = false;
/*  103 */     this.vorder = null;
/*  104 */     this.work_sing = null;
/*  105 */     this.work_tolset = null;
/*  106 */     this.x_sing = null;
/*  107 */     this.epsilon = Double.NaN;
/*      */   }
/*      */   
/*      */   public MillerUpdatingRegression(int numberOfVariables, boolean includeConstant, double errorTolerance) {
/*  118 */     if (numberOfVariables < 1)
/*  119 */       throw new ModelSpecificationException(LocalizedFormats.NO_REGRESSORS, new Object[0]); 
/*  121 */     if (includeConstant) {
/*  122 */       this.nvars = numberOfVariables + 1;
/*      */     } else {
/*  124 */       this.nvars = numberOfVariables;
/*      */     } 
/*  126 */     this.hasIntercept = includeConstant;
/*  127 */     this.nobs = 0L;
/*  128 */     this.d = new double[this.nvars];
/*  129 */     this.rhs = new double[this.nvars];
/*  130 */     this.r = new double[this.nvars * (this.nvars - 1) / 2];
/*  131 */     this.tol = new double[this.nvars];
/*  132 */     this.rss = new double[this.nvars];
/*  133 */     this.vorder = new int[this.nvars];
/*  134 */     this.x_sing = new double[this.nvars];
/*  135 */     this.work_sing = new double[this.nvars];
/*  136 */     this.work_tolset = new double[this.nvars];
/*  137 */     this.lindep = new boolean[this.nvars];
/*  138 */     for (int i = 0; i < this.nvars; i++)
/*  139 */       this.vorder[i] = i; 
/*  141 */     if (errorTolerance > 0.0D) {
/*  142 */       this.epsilon = errorTolerance;
/*      */     } else {
/*  144 */       this.epsilon = -errorTolerance;
/*      */     } 
/*      */   }
/*      */   
/*      */   public MillerUpdatingRegression(int numberOfVariables, boolean includeConstant) {
/*  156 */     this(numberOfVariables, includeConstant, 1.1102230246251565E-16D);
/*      */   }
/*      */   
/*      */   public boolean hasIntercept() {
/*  164 */     return this.hasIntercept;
/*      */   }
/*      */   
/*      */   public long getN() {
/*  172 */     return this.nobs;
/*      */   }
/*      */   
/*      */   public void addObservation(double[] x, double y) {
/*  184 */     if ((!this.hasIntercept && x.length != this.nvars) || (this.hasIntercept && x.length + 1 != this.nvars))
/*  186 */       throw new ModelSpecificationException(LocalizedFormats.INVALID_REGRESSION_OBSERVATION, new Object[] { Integer.valueOf(x.length), Integer.valueOf(this.nvars) }); 
/*  189 */     if (!this.hasIntercept) {
/*  190 */       include(MathArrays.copyOf(x, x.length), 1.0D, y);
/*      */     } else {
/*  192 */       double[] tmp = new double[x.length + 1];
/*  193 */       System.arraycopy(x, 0, tmp, 1, x.length);
/*  194 */       tmp[0] = 1.0D;
/*  195 */       include(tmp, 1.0D, y);
/*      */     } 
/*  197 */     this.nobs++;
/*      */   }
/*      */   
/*      */   public void addObservations(double[][] x, double[] y) {
/*  210 */     if (x == null || y == null || x.length != y.length)
/*  211 */       throw new ModelSpecificationException(LocalizedFormats.DIMENSIONS_MISMATCH_SIMPLE, new Object[] { Integer.valueOf((x == null) ? 0 : x.length), Integer.valueOf((y == null) ? 0 : y.length) }); 
/*  216 */     if (x.length == 0)
/*  217 */       throw new ModelSpecificationException(LocalizedFormats.NO_DATA, new Object[0]); 
/*  220 */     if ((x[0]).length + 1 > x.length)
/*  221 */       throw new ModelSpecificationException(LocalizedFormats.NOT_ENOUGH_DATA_FOR_NUMBER_OF_PREDICTORS, new Object[] { Integer.valueOf(x.length), Integer.valueOf((x[0]).length) }); 
/*  225 */     for (int i = 0; i < x.length; i++)
/*  226 */       addObservation(x[i], y[i]); 
/*      */   }
/*      */   
/*      */   private void include(double[] x, double wi, double yi) {
/*  246 */     int nextr = 0;
/*  247 */     double w = wi;
/*  248 */     double y = yi;
/*  255 */     this.rss_set = false;
/*  256 */     this.sumy = smartAdd(yi, this.sumy);
/*  257 */     this.sumsqy = smartAdd(this.sumsqy, yi * yi);
/*  258 */     for (int i = 0; i < x.length; i++) {
/*  259 */       if (w == 0.0D)
/*      */         return; 
/*  262 */       double xi = x[i];
/*  264 */       if (xi == 0.0D) {
/*  265 */         nextr += this.nvars - i - 1;
/*      */       } else {
/*  268 */         double dpi, di = this.d[i];
/*  269 */         double wxi = w * xi;
/*  270 */         double _w = w;
/*  271 */         if (di != 0.0D) {
/*  272 */           dpi = smartAdd(di, wxi * xi);
/*  273 */           double tmp = wxi * xi / di;
/*  274 */           if (FastMath.abs(tmp) > 1.1102230246251565E-16D)
/*  275 */             w = di * w / dpi; 
/*      */         } else {
/*  278 */           dpi = wxi * xi;
/*  279 */           w = 0.0D;
/*      */         } 
/*  281 */         this.d[i] = dpi;
/*  282 */         for (int k = i + 1; k < this.nvars; k++) {
/*  283 */           double d = x[k];
/*  284 */           x[k] = smartAdd(d, -xi * this.r[nextr]);
/*  285 */           if (di != 0.0D) {
/*  286 */             this.r[nextr] = smartAdd(di * this.r[nextr], _w * xi * d) / dpi;
/*      */           } else {
/*  288 */             this.r[nextr] = d / xi;
/*      */           } 
/*  290 */           nextr++;
/*      */         } 
/*  292 */         double xk = y;
/*  293 */         y = smartAdd(xk, -xi * this.rhs[i]);
/*  294 */         if (di != 0.0D) {
/*  295 */           this.rhs[i] = smartAdd(di * this.rhs[i], wxi * xk) / dpi;
/*      */         } else {
/*  297 */           this.rhs[i] = xk / xi;
/*      */         } 
/*      */       } 
/*      */     } 
/*  300 */     this.sserr = smartAdd(this.sserr, w * y * y);
/*      */   }
/*      */   
/*      */   private double smartAdd(double a, double b) {
/*  312 */     double _a = FastMath.abs(a);
/*  313 */     double _b = FastMath.abs(b);
/*  314 */     if (_a > _b) {
/*  315 */       double d = _a * 1.1102230246251565E-16D;
/*  316 */       if (_b > d)
/*  317 */         return a + b; 
/*  319 */       return a;
/*      */     } 
/*  321 */     double eps = _b * 1.1102230246251565E-16D;
/*  322 */     if (_a > eps)
/*  323 */       return a + b; 
/*  325 */     return b;
/*      */   }
/*      */   
/*      */   public void clear() {
/*  334 */     Arrays.fill(this.d, 0.0D);
/*  335 */     Arrays.fill(this.rhs, 0.0D);
/*  336 */     Arrays.fill(this.r, 0.0D);
/*  337 */     Arrays.fill(this.tol, 0.0D);
/*  338 */     Arrays.fill(this.rss, 0.0D);
/*  339 */     Arrays.fill(this.work_tolset, 0.0D);
/*  340 */     Arrays.fill(this.work_sing, 0.0D);
/*  341 */     Arrays.fill(this.x_sing, 0.0D);
/*  342 */     Arrays.fill(this.lindep, false);
/*  343 */     for (int i = 0; i < this.nvars; i++)
/*  344 */       this.vorder[i] = i; 
/*  346 */     this.nobs = 0L;
/*  347 */     this.sserr = 0.0D;
/*  348 */     this.sumy = 0.0D;
/*  349 */     this.sumsqy = 0.0D;
/*  350 */     this.rss_set = false;
/*  351 */     this.tol_set = false;
/*      */   }
/*      */   
/*      */   private void tolset() {
/*  361 */     double eps = this.epsilon;
/*  362 */     for (int i = 0; i < this.nvars; i++)
/*  363 */       this.work_tolset[i] = Math.sqrt(this.d[i]); 
/*  365 */     this.tol[0] = eps * this.work_tolset[0];
/*  366 */     for (int col = 1; col < this.nvars; col++) {
/*  367 */       int pos = col - 1;
/*  368 */       double total = this.work_tolset[col];
/*  369 */       for (int row = 0; row < col; row++) {
/*  370 */         total += Math.abs(this.r[pos]) * this.work_tolset[row];
/*  371 */         pos += this.nvars - row - 2;
/*      */       } 
/*  373 */       this.tol[col] = eps * total;
/*      */     } 
/*  375 */     this.tol_set = true;
/*      */   }
/*      */   
/*      */   private double[] regcf(int nreq) {
/*  390 */     if (nreq < 1)
/*  391 */       throw new ModelSpecificationException(LocalizedFormats.NO_REGRESSORS, new Object[0]); 
/*  393 */     if (nreq > this.nvars)
/*  394 */       throw new ModelSpecificationException(LocalizedFormats.TOO_MANY_REGRESSORS, new Object[] { Integer.valueOf(nreq), Integer.valueOf(this.nvars) }); 
/*  397 */     if (!this.tol_set)
/*  398 */       tolset(); 
/*  400 */     double[] ret = new double[nreq];
/*  401 */     boolean rankProblem = false;
/*      */     int i;
/*  402 */     for (i = nreq - 1; i > -1; i--) {
/*  403 */       if (Math.sqrt(this.d[i]) < this.tol[i]) {
/*  404 */         ret[i] = 0.0D;
/*  405 */         this.d[i] = 0.0D;
/*  406 */         rankProblem = true;
/*      */       } else {
/*  408 */         ret[i] = this.rhs[i];
/*  409 */         int nextr = i * (this.nvars + this.nvars - i - 1) / 2;
/*  410 */         for (int j = i + 1; j < nreq; j++) {
/*  411 */           ret[i] = smartAdd(ret[i], -this.r[nextr] * ret[j]);
/*  412 */           nextr++;
/*      */         } 
/*      */       } 
/*      */     } 
/*  416 */     if (rankProblem)
/*  417 */       for (i = 0; i < nreq; i++) {
/*  418 */         if (this.lindep[i])
/*  419 */           ret[i] = Double.NaN; 
/*      */       }  
/*  423 */     return ret;
/*      */   }
/*      */   
/*      */   private void singcheck() {
/*  435 */     for (int i = 0; i < this.nvars; i++)
/*  436 */       this.work_sing[i] = Math.sqrt(this.d[i]); 
/*  438 */     for (int col = 0; col < this.nvars; col++) {
/*  442 */       double temp = this.tol[col];
/*  443 */       int pos = col - 1;
/*  444 */       for (int row = 0; row < col - 1; row++) {
/*  445 */         if (Math.abs(this.r[pos]) * this.work_sing[row] < temp)
/*  446 */           this.r[pos] = 0.0D; 
/*  448 */         pos += this.nvars - row - 2;
/*      */       } 
/*  453 */       this.lindep[col] = false;
/*  454 */       if (this.work_sing[col] < temp) {
/*  455 */         this.lindep[col] = true;
/*  456 */         if (col < this.nvars - 1) {
/*  457 */           Arrays.fill(this.x_sing, 0.0D);
/*  458 */           int _pi = col * (this.nvars + this.nvars - col - 1) / 2;
/*  459 */           for (int _xi = col + 1; _xi < this.nvars; _xi++, _pi++) {
/*  460 */             this.x_sing[_xi] = this.r[_pi];
/*  461 */             this.r[_pi] = 0.0D;
/*      */           } 
/*  463 */           double y = this.rhs[col];
/*  464 */           double weight = this.d[col];
/*  465 */           this.d[col] = 0.0D;
/*  466 */           this.rhs[col] = 0.0D;
/*  467 */           include(this.x_sing, weight, y);
/*      */         } else {
/*  469 */           this.sserr += this.d[col] * this.rhs[col] * this.rhs[col];
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void ss() {
/*  486 */     double total = this.sserr;
/*  487 */     this.rss[this.nvars - 1] = this.sserr;
/*  488 */     for (int i = this.nvars - 1; i > 0; i--) {
/*  489 */       total += this.d[i] * this.rhs[i] * this.rhs[i];
/*  490 */       this.rss[i - 1] = total;
/*      */     } 
/*  492 */     this.rss_set = true;
/*      */   }
/*      */   
/*      */   private double[] cov(int nreq) {
/*  515 */     if (this.nobs <= nreq)
/*  516 */       return null; 
/*  518 */     double rnk = 0.0D;
/*  519 */     for (int i = 0; i < nreq; i++) {
/*  520 */       if (!this.lindep[i])
/*  521 */         rnk++; 
/*      */     } 
/*  524 */     double var = this.rss[nreq - 1] / (this.nobs - rnk);
/*  525 */     double[] rinv = new double[nreq * (nreq - 1) / 2];
/*  526 */     inverse(rinv, nreq);
/*  527 */     double[] covmat = new double[nreq * (nreq + 1) / 2];
/*  528 */     Arrays.fill(covmat, Double.NaN);
/*  531 */     int start = 0;
/*  532 */     double total = 0.0D;
/*  533 */     for (int row = 0; row < nreq; row++) {
/*  534 */       int pos2 = start;
/*  535 */       if (!this.lindep[row])
/*  536 */         for (int col = row; col < nreq; col++) {
/*  537 */           if (!this.lindep[col]) {
/*  538 */             int pos1 = start + col - row;
/*  539 */             if (row == col) {
/*  540 */               total = 1.0D / this.d[col];
/*      */             } else {
/*  542 */               total = rinv[pos1 - 1] / this.d[col];
/*      */             } 
/*  544 */             for (int k = col + 1; k < nreq; k++) {
/*  545 */               if (!this.lindep[k])
/*  546 */                 total += rinv[pos1] * rinv[pos2] / this.d[k]; 
/*  548 */               pos1++;
/*  549 */               pos2++;
/*      */             } 
/*  551 */             covmat[(col + 1) * col / 2 + row] = total * var;
/*      */           } else {
/*  553 */             pos2 += nreq - col - 1;
/*      */           } 
/*      */         }  
/*  557 */       start += nreq - row - 1;
/*      */     } 
/*  559 */     return covmat;
/*      */   }
/*      */   
/*      */   private void inverse(double[] rinv, int nreq) {
/*  570 */     int pos = nreq * (nreq - 1) / 2 - 1;
/*  571 */     int pos1 = -1;
/*  572 */     int pos2 = -1;
/*  573 */     double total = 0.0D;
/*  575 */     Arrays.fill(rinv, Double.NaN);
/*  576 */     for (int row = nreq - 1; row > 0; row--) {
/*  577 */       if (!this.lindep[row]) {
/*  578 */         int start = (row - 1) * (this.nvars + this.nvars - row) / 2;
/*  579 */         for (int col = nreq; col > row; col--) {
/*  580 */           pos1 = start;
/*  581 */           pos2 = pos;
/*  582 */           total = 0.0D;
/*  583 */           for (int k = row; k < col - 1; k++) {
/*  584 */             pos2 += nreq - k - 1;
/*  585 */             if (!this.lindep[k])
/*  586 */               total += -this.r[pos1] * rinv[pos2]; 
/*  588 */             pos1++;
/*      */           } 
/*  590 */           rinv[pos] = total - this.r[pos1];
/*  591 */           pos--;
/*      */         } 
/*      */       } else {
/*  594 */         pos -= nreq - row;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public double[] getPartialCorrelations(int in) {
/*  634 */     double[] output = new double[(this.nvars - in + 1) * (this.nvars - in) / 2];
/*  639 */     int rms_off = -in;
/*  640 */     int wrk_off = -(in + 1);
/*  641 */     double[] rms = new double[this.nvars - in];
/*  642 */     double[] work = new double[this.nvars - in - 1];
/*  646 */     int offXX = (this.nvars - in) * (this.nvars - in - 1) / 2;
/*  647 */     if (in < -1 || in >= this.nvars)
/*  648 */       return null; 
/*  650 */     int nvm = this.nvars - 1;
/*  651 */     int base_pos = this.r.length - (nvm - in) * (nvm - in + 1) / 2;
/*  652 */     if (this.d[in] > 0.0D)
/*  653 */       rms[in + rms_off] = 1.0D / Math.sqrt(this.d[in]); 
/*  655 */     for (int col = in + 1; col < this.nvars; col++) {
/*  656 */       int i = base_pos + col - 1 - in;
/*  657 */       double sumxx = this.d[col];
/*  658 */       for (int j = in; j < col; j++) {
/*  659 */         sumxx += this.d[j] * this.r[i] * this.r[i];
/*  660 */         i += this.nvars - j - 2;
/*      */       } 
/*  662 */       if (sumxx > 0.0D) {
/*  663 */         rms[col + rms_off] = 1.0D / Math.sqrt(sumxx);
/*      */       } else {
/*  665 */         rms[col + rms_off] = 0.0D;
/*      */       } 
/*      */     } 
/*  668 */     double sumyy = this.sserr;
/*  669 */     for (int row = in; row < this.nvars; row++)
/*  670 */       sumyy += this.d[row] * this.rhs[row] * this.rhs[row]; 
/*  672 */     if (sumyy > 0.0D)
/*  673 */       sumyy = 1.0D / Math.sqrt(sumyy); 
/*  675 */     int pos = 0;
/*  676 */     for (int col1 = in; col1 < this.nvars; col1++) {
/*  677 */       double sumxy = 0.0D;
/*  678 */       Arrays.fill(work, 0.0D);
/*  679 */       int pos1 = base_pos + col1 - in - 1;
/*  680 */       for (int i = in; i < col1; i++) {
/*  681 */         int j = pos1 + 1;
/*  682 */         for (int k = col1 + 1; k < this.nvars; k++) {
/*  683 */           work[k + wrk_off] = work[k + wrk_off] + this.d[i] * this.r[pos1] * this.r[j];
/*  684 */           j++;
/*      */         } 
/*  686 */         sumxy += this.d[i] * this.r[pos1] * this.rhs[i];
/*  687 */         pos1 += this.nvars - i - 2;
/*      */       } 
/*  689 */       int pos2 = pos1 + 1;
/*  690 */       for (int col2 = col1 + 1; col2 < this.nvars; col2++) {
/*  691 */         work[col2 + wrk_off] = work[col2 + wrk_off] + this.d[col1] * this.r[pos2];
/*  692 */         pos2++;
/*  693 */         output[(col2 - 1 - in) * (col2 - in) / 2 + col1 - in] = work[col2 + wrk_off] * rms[col1 + rms_off] * rms[col2 + rms_off];
/*  695 */         pos++;
/*      */       } 
/*  697 */       sumxy += this.d[col1] * this.rhs[col1];
/*  698 */       output[col1 + rms_off + offXX] = sumxy * rms[col1 + rms_off] * sumyy;
/*      */     } 
/*  701 */     return output;
/*      */   }
/*      */   
/*      */   private void vmove(int from, int to) {
/*      */     int first, inc;
/*  727 */     boolean bSkipTo40 = false;
/*  728 */     if (from == to)
/*      */       return; 
/*  731 */     if (!this.rss_set)
/*  732 */       ss(); 
/*  734 */     int count = 0;
/*  735 */     if (from < to) {
/*  736 */       first = from;
/*  737 */       inc = 1;
/*  738 */       count = to - from;
/*      */     } else {
/*  740 */       first = from - 1;
/*  741 */       inc = -1;
/*  742 */       count = from - to;
/*      */     } 
/*  745 */     int m = first;
/*  746 */     int idx = 0;
/*  747 */     while (idx < count) {
/*  748 */       int m1 = m * (this.nvars + this.nvars - m - 1) / 2;
/*  749 */       int m2 = m1 + this.nvars - m - 1;
/*  750 */       int mp1 = m + 1;
/*  752 */       double d1 = this.d[m];
/*  753 */       double d2 = this.d[mp1];
/*  755 */       if (d1 > this.epsilon || d2 > this.epsilon) {
/*  756 */         double d = this.r[m1];
/*  757 */         if (Math.abs(d) * Math.sqrt(d1) < this.tol[mp1])
/*  758 */           d = 0.0D; 
/*  760 */         if (d1 < this.epsilon || Math.abs(d) < this.epsilon) {
/*  761 */           this.d[m] = d2;
/*  762 */           this.d[mp1] = d1;
/*  763 */           this.r[m1] = 0.0D;
/*  764 */           for (int col = m + 2; col < this.nvars; col++) {
/*  765 */             m1++;
/*  766 */             d = this.r[m1];
/*  767 */             this.r[m1] = this.r[m2];
/*  768 */             this.r[m2] = d;
/*  769 */             m2++;
/*      */           } 
/*  771 */           d = this.rhs[m];
/*  772 */           this.rhs[m] = this.rhs[mp1];
/*  773 */           this.rhs[mp1] = d;
/*  774 */           bSkipTo40 = true;
/*  776 */         } else if (d2 < this.epsilon) {
/*  777 */           this.d[m] = d1 * d * d;
/*  778 */           this.r[m1] = 1.0D / d;
/*  779 */           for (int _i = m1 + 1; _i < m1 + this.nvars - m - 1; _i++)
/*  780 */             this.r[_i] = this.r[_i] / d; 
/*  782 */           this.rhs[m] = this.rhs[m] / d;
/*  783 */           bSkipTo40 = true;
/*      */         } 
/*  786 */         if (!bSkipTo40) {
/*  787 */           double d1new = d2 + d1 * d * d;
/*  788 */           double cbar = d2 / d1new;
/*  789 */           double sbar = d * d1 / d1new;
/*  790 */           double d2new = d1 * cbar;
/*  791 */           this.d[m] = d1new;
/*  792 */           this.d[mp1] = d2new;
/*  793 */           this.r[m1] = sbar;
/*  794 */           for (int col = m + 2; col < this.nvars; col++) {
/*  795 */             m1++;
/*  796 */             double d3 = this.r[m1];
/*  797 */             this.r[m1] = cbar * this.r[m2] + sbar * d3;
/*  798 */             this.r[m2] = d3 - d * this.r[m2];
/*  799 */             m2++;
/*      */           } 
/*  801 */           double Y = this.rhs[m];
/*  802 */           this.rhs[m] = cbar * this.rhs[mp1] + sbar * Y;
/*  803 */           this.rhs[mp1] = Y - d * this.rhs[mp1];
/*      */         } 
/*      */       } 
/*  806 */       if (m > 0) {
/*  807 */         int pos = m;
/*  808 */         for (int row = 0; row < m; row++) {
/*  809 */           double d = this.r[pos];
/*  810 */           this.r[pos] = this.r[pos - 1];
/*  811 */           this.r[pos - 1] = d;
/*  812 */           pos += this.nvars - row - 2;
/*      */         } 
/*      */       } 
/*  817 */       m1 = this.vorder[m];
/*  818 */       this.vorder[m] = this.vorder[mp1];
/*  819 */       this.vorder[mp1] = m1;
/*  820 */       double X = this.tol[m];
/*  821 */       this.tol[m] = this.tol[mp1];
/*  822 */       this.tol[mp1] = X;
/*  823 */       this.rss[m] = this.rss[mp1] + this.d[mp1] * this.rhs[mp1] * this.rhs[mp1];
/*  825 */       m += inc;
/*  826 */       idx++;
/*      */     } 
/*      */   }
/*      */   
/*      */   private int reorderRegressors(int[] list, int pos1) {
/*  849 */     if (list.length < 1 || list.length > this.nvars + 1 - pos1)
/*  850 */       return -1; 
/*  852 */     int next = pos1;
/*  853 */     int i = pos1;
/*  854 */     while (i < this.nvars) {
/*  855 */       int l = this.vorder[i];
/*  856 */       for (int j = 0; j < list.length; j++) {
/*  857 */         if (l == list[j] && 
/*  858 */           i > next) {
/*  859 */           vmove(i, next);
/*  860 */           next++;
/*  861 */           if (next >= list.length + pos1)
/*  862 */             return 0; 
/*      */           break;
/*      */         } 
/*      */       } 
/*  869 */       i++;
/*      */     } 
/*  871 */     return 0;
/*      */   }
/*      */   
/*      */   public double getDiagonalOfHatMatrix(double[] row_data) {
/*  881 */     double[] xrow, wk = new double[this.nvars];
/*  885 */     if (row_data.length > this.nvars)
/*  886 */       return Double.NaN; 
/*  889 */     if (this.hasIntercept) {
/*  890 */       xrow = new double[row_data.length + 1];
/*  891 */       xrow[0] = 1.0D;
/*  892 */       System.arraycopy(row_data, 0, xrow, 1, row_data.length);
/*      */     } else {
/*  894 */       xrow = row_data;
/*      */     } 
/*  896 */     double hii = 0.0D;
/*  897 */     for (int col = 0; col < xrow.length; col++) {
/*  898 */       if (Math.sqrt(this.d[col]) < this.tol[col]) {
/*  899 */         wk[col] = 0.0D;
/*      */       } else {
/*  901 */         int pos = col - 1;
/*  902 */         double total = xrow[col];
/*  903 */         for (int row = 0; row < col; row++) {
/*  904 */           total = smartAdd(total, -wk[row] * this.r[pos]);
/*  905 */           pos += this.nvars - row - 2;
/*      */         } 
/*  907 */         wk[col] = total;
/*  908 */         hii = smartAdd(hii, total * total / this.d[col]);
/*      */       } 
/*      */     } 
/*  911 */     return hii;
/*      */   }
/*      */   
/*      */   public int[] getOrderOfRegressors() {
/*  922 */     return MathArrays.copyOf(this.vorder);
/*      */   }
/*      */   
/*      */   public RegressionResults regress() throws ModelSpecificationException {
/*  933 */     return regress(this.nvars);
/*      */   }
/*      */   
/*      */   public RegressionResults regress(int numberOfRegressors) throws ModelSpecificationException {
/*  947 */     if (this.nobs <= numberOfRegressors)
/*  948 */       throw new ModelSpecificationException(LocalizedFormats.NOT_ENOUGH_DATA_FOR_NUMBER_OF_PREDICTORS, new Object[] { Long.valueOf(this.nobs), Integer.valueOf(numberOfRegressors) }); 
/*  952 */     if (numberOfRegressors > this.nvars)
/*  953 */       throw new ModelSpecificationException(LocalizedFormats.TOO_MANY_REGRESSORS, new Object[] { Integer.valueOf(numberOfRegressors), Integer.valueOf(this.nvars) }); 
/*  956 */     tolset();
/*  958 */     singcheck();
/*  960 */     double[] beta = regcf(numberOfRegressors);
/*  962 */     ss();
/*  964 */     double[] cov = cov(numberOfRegressors);
/*  966 */     int rnk = 0;
/*  967 */     for (int i = 0; i < this.lindep.length; i++) {
/*  968 */       if (!this.lindep[i])
/*  969 */         rnk++; 
/*      */     } 
/*  973 */     boolean needsReorder = false;
/*  974 */     for (int j = 0; j < numberOfRegressors; j++) {
/*  975 */       if (this.vorder[j] != j) {
/*  976 */         needsReorder = true;
/*      */         break;
/*      */       } 
/*      */     } 
/*  980 */     if (!needsReorder)
/*  981 */       return new RegressionResults(beta, new double[][] { cov }, true, this.nobs, rnk, this.sumy, this.sumsqy, this.sserr, this.hasIntercept, false); 
/*  985 */     double[] betaNew = new double[beta.length];
/*  986 */     double[] covNew = new double[cov.length];
/*  988 */     int[] newIndices = new int[beta.length];
/*  989 */     for (int k = 0; k < this.nvars; k++) {
/*  990 */       for (int n = 0; n < numberOfRegressors; n++) {
/*  991 */         if (this.vorder[n] == k) {
/*  992 */           betaNew[k] = beta[n];
/*  993 */           newIndices[k] = n;
/*      */         } 
/*      */       } 
/*      */     } 
/*  998 */     int idx1 = 0;
/* 1002 */     for (int m = 0; m < beta.length; m++) {
/* 1003 */       int _i = newIndices[m];
/* 1004 */       for (int n = 0; n <= m; n++, idx1++) {
/* 1005 */         int idx2, _j = newIndices[n];
/* 1006 */         if (_i > _j) {
/* 1007 */           idx2 = _i * (_i + 1) / 2 + _j;
/*      */         } else {
/* 1009 */           idx2 = _j * (_j + 1) / 2 + _i;
/*      */         } 
/* 1011 */         covNew[idx1] = cov[idx2];
/*      */       } 
/*      */     } 
/* 1014 */     return new RegressionResults(betaNew, new double[][] { covNew }, true, this.nobs, rnk, this.sumy, this.sumsqy, this.sserr, this.hasIntercept, false);
/*      */   }
/*      */   
/*      */   public RegressionResults regress(int[] variablesToInclude) throws ModelSpecificationException {
/*      */     int[] series;
/* 1033 */     if (variablesToInclude.length > this.nvars)
/* 1034 */       throw new ModelSpecificationException(LocalizedFormats.TOO_MANY_REGRESSORS, new Object[] { Integer.valueOf(variablesToInclude.length), Integer.valueOf(this.nvars) }); 
/* 1037 */     if (this.nobs <= this.nvars)
/* 1038 */       throw new ModelSpecificationException(LocalizedFormats.NOT_ENOUGH_DATA_FOR_NUMBER_OF_PREDICTORS, new Object[] { Long.valueOf(this.nobs), Integer.valueOf(this.nvars) }); 
/* 1042 */     Arrays.sort(variablesToInclude);
/* 1043 */     int iExclude = 0;
/* 1044 */     for (int i = 0; i < variablesToInclude.length; i++) {
/* 1045 */       if (i >= this.nvars)
/* 1046 */         throw new ModelSpecificationException(LocalizedFormats.INDEX_LARGER_THAN_MAX, new Object[] { Integer.valueOf(i), Integer.valueOf(this.nvars) }); 
/* 1049 */       if (i > 0 && variablesToInclude[i] == variablesToInclude[i - 1]) {
/* 1050 */         variablesToInclude[i] = -1;
/* 1051 */         iExclude++;
/*      */       } 
/*      */     } 
/* 1055 */     if (iExclude > 0) {
/* 1056 */       int i1 = 0;
/* 1057 */       series = new int[variablesToInclude.length - iExclude];
/* 1058 */       for (int i2 = 0; i2 < variablesToInclude.length; i2++) {
/* 1059 */         if (variablesToInclude[i2] > -1) {
/* 1060 */           series[i1] = variablesToInclude[i2];
/* 1061 */           i1++;
/*      */         } 
/*      */       } 
/*      */     } else {
/* 1065 */       series = variablesToInclude;
/*      */     } 
/* 1068 */     reorderRegressors(series, 0);
/* 1070 */     tolset();
/* 1072 */     singcheck();
/* 1074 */     double[] beta = regcf(series.length);
/* 1076 */     ss();
/* 1078 */     double[] cov = cov(series.length);
/* 1080 */     int rnk = 0;
/* 1081 */     for (int j = 0; j < this.lindep.length; j++) {
/* 1082 */       if (!this.lindep[j])
/* 1083 */         rnk++; 
/*      */     } 
/* 1087 */     boolean needsReorder = false;
/* 1088 */     for (int k = 0; k < this.nvars; k++) {
/* 1089 */       if (this.vorder[k] != series[k]) {
/* 1090 */         needsReorder = true;
/*      */         break;
/*      */       } 
/*      */     } 
/* 1094 */     if (!needsReorder)
/* 1095 */       return new RegressionResults(beta, new double[][] { cov }, true, this.nobs, rnk, this.sumy, this.sumsqy, this.sserr, this.hasIntercept, false); 
/* 1099 */     double[] betaNew = new double[beta.length];
/* 1100 */     int[] newIndices = new int[beta.length];
/* 1101 */     for (int m = 0; m < series.length; m++) {
/* 1102 */       for (int i1 = 0; i1 < this.vorder.length; i1++) {
/* 1103 */         if (this.vorder[i1] == series[m]) {
/* 1104 */           betaNew[m] = beta[i1];
/* 1105 */           newIndices[m] = i1;
/*      */         } 
/*      */       } 
/*      */     } 
/* 1109 */     double[] covNew = new double[cov.length];
/* 1110 */     int idx1 = 0;
/* 1114 */     for (int n = 0; n < beta.length; n++) {
/* 1115 */       int _i = newIndices[n];
/* 1116 */       for (int i1 = 0; i1 <= n; i1++, idx1++) {
/* 1117 */         int idx2, _j = newIndices[i1];
/* 1118 */         if (_i > _j) {
/* 1119 */           idx2 = _i * (_i + 1) / 2 + _j;
/*      */         } else {
/* 1121 */           idx2 = _j * (_j + 1) / 2 + _i;
/*      */         } 
/* 1123 */         covNew[idx1] = cov[idx2];
/*      */       } 
/*      */     } 
/* 1126 */     return new RegressionResults(betaNew, new double[][] { covNew }, true, this.nobs, rnk, this.sumy, this.sumsqy, this.sserr, this.hasIntercept, false);
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\stat\regression\MillerUpdatingRegression.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
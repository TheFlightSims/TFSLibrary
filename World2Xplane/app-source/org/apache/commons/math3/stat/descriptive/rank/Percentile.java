/*     */ package org.apache.commons.math3.stat.descriptive.rank;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Arrays;
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.exception.OutOfRangeException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.stat.descriptive.AbstractUnivariateStatistic;
/*     */ import org.apache.commons.math3.stat.descriptive.UnivariateStatistic;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ import org.apache.commons.math3.util.MathUtils;
/*     */ 
/*     */ public class Percentile extends AbstractUnivariateStatistic implements Serializable {
/*     */   private static final long serialVersionUID = -8091216485095130416L;
/*     */   
/*     */   private static final int MIN_SELECT_SIZE = 15;
/*     */   
/*     */   private static final int MAX_CACHED_LEVELS = 10;
/*     */   
/*  96 */   private double quantile = 0.0D;
/*     */   
/*     */   private int[] cachedPivots;
/*     */   
/*     */   public Percentile() {
/* 106 */     this(50.0D);
/*     */   }
/*     */   
/*     */   public Percentile(double p) {
/* 116 */     setQuantile(p);
/* 117 */     this.cachedPivots = null;
/*     */   }
/*     */   
/*     */   public Percentile(Percentile original) {
/* 127 */     copy(original, this);
/*     */   }
/*     */   
/*     */   public void setData(double[] values) {
/* 133 */     if (values == null) {
/* 134 */       this.cachedPivots = null;
/*     */     } else {
/* 136 */       this.cachedPivots = new int[1023];
/* 137 */       Arrays.fill(this.cachedPivots, -1);
/*     */     } 
/* 139 */     super.setData(values);
/*     */   }
/*     */   
/*     */   public void setData(double[] values, int begin, int length) {
/* 145 */     if (values == null) {
/* 146 */       this.cachedPivots = null;
/*     */     } else {
/* 148 */       this.cachedPivots = new int[1023];
/* 149 */       Arrays.fill(this.cachedPivots, -1);
/*     */     } 
/* 151 */     super.setData(values, begin, length);
/*     */   }
/*     */   
/*     */   public double evaluate(double p) {
/* 163 */     return evaluate(getDataRef(), p);
/*     */   }
/*     */   
/*     */   public double evaluate(double[] values, double p) {
/* 193 */     test(values, 0, 0);
/* 194 */     return evaluate(values, 0, values.length, p);
/*     */   }
/*     */   
/*     */   public double evaluate(double[] values, int start, int length) {
/* 223 */     return evaluate(values, start, length, this.quantile);
/*     */   }
/*     */   
/*     */   public double evaluate(double[] values, int begin, int length, double p) {
/*     */     double[] work;
/*     */     int[] pivotsHeap;
/* 259 */     test(values, begin, length);
/* 261 */     if (p > 100.0D || p <= 0.0D)
/* 262 */       throw new OutOfRangeException(LocalizedFormats.OUT_OF_BOUNDS_QUANTILE_VALUE, Double.valueOf(p), Integer.valueOf(0), Integer.valueOf(100)); 
/* 264 */     if (length == 0)
/* 265 */       return Double.NaN; 
/* 267 */     if (length == 1)
/* 268 */       return values[begin]; 
/* 270 */     double n = length;
/* 271 */     double pos = p * (n + 1.0D) / 100.0D;
/* 272 */     double fpos = FastMath.floor(pos);
/* 273 */     int intPos = (int)fpos;
/* 274 */     double dif = pos - fpos;
/* 277 */     if (values == getDataRef()) {
/* 278 */       work = getDataRef();
/* 279 */       pivotsHeap = this.cachedPivots;
/*     */     } else {
/* 281 */       work = new double[length];
/* 282 */       System.arraycopy(values, begin, work, 0, length);
/* 283 */       pivotsHeap = new int[1023];
/* 284 */       Arrays.fill(pivotsHeap, -1);
/*     */     } 
/* 287 */     if (pos < 1.0D)
/* 288 */       return select(work, pivotsHeap, 0); 
/* 290 */     if (pos >= n)
/* 291 */       return select(work, pivotsHeap, length - 1); 
/* 293 */     double lower = select(work, pivotsHeap, intPos - 1);
/* 294 */     double upper = select(work, pivotsHeap, intPos);
/* 295 */     return lower + dif * (upper - lower);
/*     */   }
/*     */   
/*     */   private double select(double[] work, int[] pivotsHeap, int k) {
/* 311 */     int begin = 0;
/* 312 */     int end = work.length;
/* 313 */     int node = 0;
/* 315 */     while (end - begin > 15) {
/*     */       int pivot;
/* 318 */       if (node < pivotsHeap.length && pivotsHeap[node] >= 0) {
/* 321 */         pivot = pivotsHeap[node];
/*     */       } else {
/* 324 */         pivot = partition(work, begin, end, medianOf3(work, begin, end));
/* 325 */         if (node < pivotsHeap.length)
/* 326 */           pivotsHeap[node] = pivot; 
/*     */       } 
/* 330 */       if (k == pivot)
/* 332 */         return work[k]; 
/* 333 */       if (k < pivot) {
/* 335 */         end = pivot;
/* 336 */         node = Math.min(2 * node + 1, pivotsHeap.length);
/*     */         continue;
/*     */       } 
/* 339 */       begin = pivot + 1;
/* 340 */       node = Math.min(2 * node + 2, pivotsHeap.length);
/*     */     } 
/* 347 */     insertionSort(work, begin, end);
/* 348 */     return work[k];
/*     */   }
/*     */   
/*     */   int medianOf3(double[] work, int begin, int end) {
/* 361 */     int inclusiveEnd = end - 1;
/* 362 */     int middle = begin + (inclusiveEnd - begin) / 2;
/* 363 */     double wBegin = work[begin];
/* 364 */     double wMiddle = work[middle];
/* 365 */     double wEnd = work[inclusiveEnd];
/* 367 */     if (wBegin < wMiddle) {
/* 368 */       if (wMiddle < wEnd)
/* 369 */         return middle; 
/* 371 */       return (wBegin < wEnd) ? inclusiveEnd : begin;
/*     */     } 
/* 374 */     if (wBegin < wEnd)
/* 375 */       return begin; 
/* 377 */     return (wMiddle < wEnd) ? inclusiveEnd : middle;
/*     */   }
/*     */   
/*     */   private int partition(double[] work, int begin, int end, int pivot) {
/* 398 */     double value = work[pivot];
/* 399 */     work[pivot] = work[begin];
/* 401 */     int i = begin + 1;
/* 402 */     int j = end - 1;
/* 403 */     while (i < j) {
/* 404 */       while (i < j && work[j] >= value)
/* 405 */         j--; 
/* 407 */       while (i < j && work[i] <= value)
/* 408 */         i++; 
/* 411 */       if (i < j) {
/* 412 */         double tmp = work[i];
/* 413 */         work[i++] = work[j];
/* 414 */         work[j--] = tmp;
/*     */       } 
/*     */     } 
/* 418 */     if (i >= end || work[i] > value)
/* 419 */       i--; 
/* 421 */     work[begin] = work[i];
/* 422 */     work[i] = value;
/* 423 */     return i;
/*     */   }
/*     */   
/*     */   private void insertionSort(double[] work, int begin, int end) {
/* 434 */     for (int j = begin + 1; j < end; j++) {
/* 435 */       double saved = work[j];
/* 436 */       int i = j - 1;
/* 437 */       while (i >= begin && saved < work[i]) {
/* 438 */         work[i + 1] = work[i];
/* 439 */         i--;
/*     */       } 
/* 441 */       work[i + 1] = saved;
/*     */     } 
/*     */   }
/*     */   
/*     */   public double getQuantile() {
/* 452 */     return this.quantile;
/*     */   }
/*     */   
/*     */   public void setQuantile(double p) {
/* 464 */     if (p <= 0.0D || p > 100.0D)
/* 465 */       throw new OutOfRangeException(LocalizedFormats.OUT_OF_BOUNDS_QUANTILE_VALUE, Double.valueOf(p), Integer.valueOf(0), Integer.valueOf(100)); 
/* 467 */     this.quantile = p;
/*     */   }
/*     */   
/*     */   public Percentile copy() {
/* 475 */     Percentile result = new Percentile();
/* 476 */     copy(this, result);
/* 477 */     return result;
/*     */   }
/*     */   
/*     */   public static void copy(Percentile source, Percentile dest) throws NullArgumentException {
/* 490 */     MathUtils.checkNotNull(source);
/* 491 */     MathUtils.checkNotNull(dest);
/* 492 */     dest.setData(source.getDataRef());
/* 493 */     if (source.cachedPivots != null)
/* 494 */       System.arraycopy(source.cachedPivots, 0, dest.cachedPivots, 0, source.cachedPivots.length); 
/* 496 */     dest.quantile = source.quantile;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\stat\descriptive\rank\Percentile.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
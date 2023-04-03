/*     */ package org.apache.commons.math3.stat.inference;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.apache.commons.math3.distribution.FDistribution;
/*     */ import org.apache.commons.math3.exception.ConvergenceException;
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ import org.apache.commons.math3.exception.MaxCountExceededException;
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.exception.OutOfRangeException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.stat.descriptive.summary.Sum;
/*     */ import org.apache.commons.math3.stat.descriptive.summary.SumOfSquares;
/*     */ 
/*     */ public class OneWayAnova {
/*     */   public double anovaFValue(Collection<double[]> categoryData) throws NullArgumentException, DimensionMismatchException {
/*  90 */     AnovaStats a = anovaStats(categoryData);
/*  91 */     return a.F;
/*     */   }
/*     */   
/*     */   public double anovaPValue(Collection<double[]> categoryData) throws NullArgumentException, DimensionMismatchException, ConvergenceException, MaxCountExceededException {
/* 127 */     AnovaStats a = anovaStats(categoryData);
/* 128 */     FDistribution fdist = new FDistribution(a.dfbg, a.dfwg);
/* 129 */     return 1.0D - fdist.cumulativeProbability(a.F);
/*     */   }
/*     */   
/*     */   public boolean anovaTest(Collection<double[]> categoryData, double alpha) throws NullArgumentException, DimensionMismatchException, OutOfRangeException, ConvergenceException, MaxCountExceededException {
/* 172 */     if (alpha <= 0.0D || alpha > 0.5D)
/* 173 */       throw new OutOfRangeException(LocalizedFormats.OUT_OF_BOUND_SIGNIFICANCE_LEVEL, Double.valueOf(alpha), Integer.valueOf(0), Double.valueOf(0.5D)); 
/* 177 */     return (anovaPValue(categoryData) < alpha);
/*     */   }
/*     */   
/*     */   private AnovaStats anovaStats(Collection<double[]> categoryData) throws NullArgumentException, DimensionMismatchException {
/* 195 */     if (categoryData == null)
/* 196 */       throw new NullArgumentException(); 
/* 200 */     if (categoryData.size() < 2)
/* 201 */       throw new DimensionMismatchException(LocalizedFormats.TWO_OR_MORE_CATEGORIES_REQUIRED, categoryData.size(), 2); 
/* 207 */     for (double[] array : categoryData) {
/* 208 */       if (array.length <= 1)
/* 209 */         throw new DimensionMismatchException(LocalizedFormats.TWO_OR_MORE_VALUES_IN_CATEGORY_REQUIRED, array.length, 2); 
/*     */     } 
/* 215 */     int dfwg = 0;
/* 216 */     double sswg = 0.0D;
/* 217 */     Sum totsum = new Sum();
/* 218 */     SumOfSquares totsumsq = new SumOfSquares();
/* 219 */     int totnum = 0;
/* 221 */     for (double[] data : categoryData) {
/* 223 */       Sum sum = new Sum();
/* 224 */       SumOfSquares sumsq = new SumOfSquares();
/* 225 */       int num = 0;
/* 227 */       for (int i = 0; i < data.length; i++) {
/* 228 */         double val = data[i];
/* 231 */         num++;
/* 232 */         sum.increment(val);
/* 233 */         sumsq.increment(val);
/* 236 */         totnum++;
/* 237 */         totsum.increment(val);
/* 238 */         totsumsq.increment(val);
/*     */       } 
/* 240 */       dfwg += num - 1;
/* 241 */       double ss = sumsq.getResult() - sum.getResult() * sum.getResult() / num;
/* 242 */       sswg += ss;
/*     */     } 
/* 244 */     double sst = totsumsq.getResult() - totsum.getResult() * totsum.getResult() / totnum;
/* 246 */     double ssbg = sst - sswg;
/* 247 */     int dfbg = categoryData.size() - 1;
/* 248 */     double msbg = ssbg / dfbg;
/* 249 */     double mswg = sswg / dfwg;
/* 250 */     double F = msbg / mswg;
/* 252 */     return new AnovaStats(dfbg, dfwg, F);
/*     */   }
/*     */   
/*     */   private static class AnovaStats {
/*     */     private final int dfbg;
/*     */     
/*     */     private final int dfwg;
/*     */     
/*     */     private final double F;
/*     */     
/*     */     private AnovaStats(int dfbg, int dfwg, double F) {
/* 277 */       this.dfbg = dfbg;
/* 278 */       this.dfwg = dfwg;
/* 279 */       this.F = F;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\stat\inference\OneWayAnova.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
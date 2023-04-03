/*     */ package org.apache.commons.math3.stat.inference;
/*     */ 
/*     */ import org.apache.commons.math3.distribution.NormalDistribution;
/*     */ import org.apache.commons.math3.exception.ConvergenceException;
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ import org.apache.commons.math3.exception.MaxCountExceededException;
/*     */ import org.apache.commons.math3.exception.NoDataException;
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.exception.NumberIsTooLargeException;
/*     */ import org.apache.commons.math3.stat.ranking.NaNStrategy;
/*     */ import org.apache.commons.math3.stat.ranking.NaturalRanking;
/*     */ import org.apache.commons.math3.stat.ranking.TiesStrategy;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class WilcoxonSignedRankTest {
/*     */   private NaturalRanking naturalRanking;
/*     */   
/*     */   public WilcoxonSignedRankTest() {
/*  47 */     this.naturalRanking = new NaturalRanking(NaNStrategy.FIXED, TiesStrategy.AVERAGE);
/*     */   }
/*     */   
/*     */   public WilcoxonSignedRankTest(NaNStrategy nanStrategy, TiesStrategy tiesStrategy) {
/*  62 */     this.naturalRanking = new NaturalRanking(nanStrategy, tiesStrategy);
/*     */   }
/*     */   
/*     */   private void ensureDataConformance(double[] x, double[] y) throws NullArgumentException, NoDataException, DimensionMismatchException {
/*  78 */     if (x == null || y == null)
/*  80 */       throw new NullArgumentException(); 
/*  82 */     if (x.length == 0 || y.length == 0)
/*  84 */       throw new NoDataException(); 
/*  86 */     if (y.length != x.length)
/*  87 */       throw new DimensionMismatchException(y.length, x.length); 
/*     */   }
/*     */   
/*     */   private double[] calculateDifferences(double[] x, double[] y) {
/* 100 */     double[] z = new double[x.length];
/* 102 */     for (int i = 0; i < x.length; i++)
/* 103 */       z[i] = y[i] - x[i]; 
/* 106 */     return z;
/*     */   }
/*     */   
/*     */   private double[] calculateAbsoluteDifferences(double[] z) throws NullArgumentException, NoDataException {
/* 120 */     if (z == null)
/* 121 */       throw new NullArgumentException(); 
/* 124 */     if (z.length == 0)
/* 125 */       throw new NoDataException(); 
/* 128 */     double[] zAbs = new double[z.length];
/* 130 */     for (int i = 0; i < z.length; i++)
/* 131 */       zAbs[i] = FastMath.abs(z[i]); 
/* 134 */     return zAbs;
/*     */   }
/*     */   
/*     */   public double wilcoxonSignedRank(double[] x, double[] y) throws NullArgumentException, NoDataException, DimensionMismatchException {
/* 175 */     ensureDataConformance(x, y);
/* 179 */     double[] z = calculateDifferences(x, y);
/* 180 */     double[] zAbs = calculateAbsoluteDifferences(z);
/* 182 */     double[] ranks = this.naturalRanking.rank(zAbs);
/* 184 */     double Wplus = 0.0D;
/* 186 */     for (int i = 0; i < z.length; i++) {
/* 187 */       if (z[i] > 0.0D)
/* 188 */         Wplus += ranks[i]; 
/*     */     } 
/* 192 */     int N = x.length;
/* 193 */     double Wminus = (N * (N + 1)) / 2.0D - Wplus;
/* 195 */     return FastMath.max(Wplus, Wminus);
/*     */   }
/*     */   
/*     */   private double calculateExactPValue(double Wmax, int N) {
/* 211 */     int m = 1 << N;
/* 213 */     int largerRankSums = 0;
/* 215 */     for (int i = 0; i < m; i++) {
/* 216 */       int rankSum = 0;
/* 219 */       for (int j = 0; j < N; j++) {
/* 222 */         if ((i >> j & 0x1) == 1)
/* 223 */           rankSum += j + 1; 
/*     */       } 
/* 227 */       if (rankSum >= Wmax)
/* 228 */         largerRankSums++; 
/*     */     } 
/* 236 */     return 2.0D * largerRankSums / m;
/*     */   }
/*     */   
/*     */   private double calculateAsymptoticPValue(double Wmin, int N) {
/* 246 */     double ES = (N * (N + 1)) / 4.0D;
/* 251 */     double VarS = ES * (2 * N + 1) / 6.0D;
/* 254 */     double z = (Wmin - ES - 0.5D) / FastMath.sqrt(VarS);
/* 256 */     NormalDistribution standardNormal = new NormalDistribution(0.0D, 1.0D);
/* 258 */     return 2.0D * standardNormal.cumulativeProbability(z);
/*     */   }
/*     */   
/*     */   public double wilcoxonSignedRankTest(double[] x, double[] y, boolean exactPValue) throws NullArgumentException, NoDataException, DimensionMismatchException, NumberIsTooLargeException, ConvergenceException, MaxCountExceededException {
/* 308 */     ensureDataConformance(x, y);
/* 310 */     int N = x.length;
/* 311 */     double Wmax = wilcoxonSignedRank(x, y);
/* 313 */     if (exactPValue && N > 30)
/* 314 */       throw new NumberIsTooLargeException(Integer.valueOf(N), Integer.valueOf(30), true); 
/* 317 */     if (exactPValue)
/* 318 */       return calculateExactPValue(Wmax, N); 
/* 320 */     double Wmin = (N * (N + 1)) / 2.0D - Wmax;
/* 321 */     return calculateAsymptoticPValue(Wmin, N);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\stat\inference\WilcoxonSignedRankTest.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
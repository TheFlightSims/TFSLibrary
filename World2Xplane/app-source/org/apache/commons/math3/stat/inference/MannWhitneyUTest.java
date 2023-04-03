/*     */ package org.apache.commons.math3.stat.inference;
/*     */ 
/*     */ import org.apache.commons.math3.distribution.NormalDistribution;
/*     */ import org.apache.commons.math3.exception.ConvergenceException;
/*     */ import org.apache.commons.math3.exception.MaxCountExceededException;
/*     */ import org.apache.commons.math3.exception.NoDataException;
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.stat.ranking.NaNStrategy;
/*     */ import org.apache.commons.math3.stat.ranking.NaturalRanking;
/*     */ import org.apache.commons.math3.stat.ranking.TiesStrategy;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class MannWhitneyUTest {
/*     */   private NaturalRanking naturalRanking;
/*     */   
/*     */   public MannWhitneyUTest() {
/*  45 */     this.naturalRanking = new NaturalRanking(NaNStrategy.FIXED, TiesStrategy.AVERAGE);
/*     */   }
/*     */   
/*     */   public MannWhitneyUTest(NaNStrategy nanStrategy, TiesStrategy tiesStrategy) {
/*  60 */     this.naturalRanking = new NaturalRanking(nanStrategy, tiesStrategy);
/*     */   }
/*     */   
/*     */   private void ensureDataConformance(double[] x, double[] y) throws NullArgumentException, NoDataException {
/*  74 */     if (x == null || y == null)
/*  76 */       throw new NullArgumentException(); 
/*  78 */     if (x.length == 0 || y.length == 0)
/*  80 */       throw new NoDataException(); 
/*     */   }
/*     */   
/*     */   private double[] concatenateSamples(double[] x, double[] y) {
/*  90 */     double[] z = new double[x.length + y.length];
/*  92 */     System.arraycopy(x, 0, z, 0, x.length);
/*  93 */     System.arraycopy(y, 0, z, x.length, y.length);
/*  95 */     return z;
/*     */   }
/*     */   
/*     */   public double mannWhitneyU(double[] x, double[] y) throws NullArgumentException, NoDataException {
/* 129 */     ensureDataConformance(x, y);
/* 131 */     double[] z = concatenateSamples(x, y);
/* 132 */     double[] ranks = this.naturalRanking.rank(z);
/* 134 */     double sumRankX = 0.0D;
/* 140 */     for (int i = 0; i < x.length; i++)
/* 141 */       sumRankX += ranks[i]; 
/* 148 */     double U1 = sumRankX - (x.length * (x.length + 1) / 2);
/* 153 */     double U2 = (x.length * y.length) - U1;
/* 155 */     return FastMath.max(U1, U2);
/*     */   }
/*     */   
/*     */   private double calculateAsymptoticPValue(double Umin, int n1, int n2) throws ConvergenceException, MaxCountExceededException {
/* 173 */     int n1n2prod = n1 * n2;
/* 176 */     double EU = n1n2prod / 2.0D;
/* 177 */     double VarU = (n1n2prod * (n1 + n2 + 1)) / 12.0D;
/* 179 */     double z = (Umin - EU) / FastMath.sqrt(VarU);
/* 181 */     NormalDistribution standardNormal = new NormalDistribution(0.0D, 1.0D);
/* 183 */     return 2.0D * standardNormal.cumulativeProbability(z);
/*     */   }
/*     */   
/*     */   public double mannWhitneyUTest(double[] x, double[] y) throws NullArgumentException, NoDataException, ConvergenceException, MaxCountExceededException {
/* 222 */     ensureDataConformance(x, y);
/* 224 */     double Umax = mannWhitneyU(x, y);
/* 229 */     double Umin = (x.length * y.length) - Umax;
/* 231 */     return calculateAsymptoticPValue(Umin, x.length, y.length);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\stat\inference\MannWhitneyUTest.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package org.apache.commons.math3.stat.correlation;
/*     */ 
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ import org.apache.commons.math3.exception.MathIllegalArgumentException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.linear.BlockRealMatrix;
/*     */ import org.apache.commons.math3.linear.RealMatrix;
/*     */ import org.apache.commons.math3.stat.ranking.NaturalRanking;
/*     */ import org.apache.commons.math3.stat.ranking.RankingAlgorithm;
/*     */ 
/*     */ public class SpearmansCorrelation {
/*     */   private final RealMatrix data;
/*     */   
/*     */   private final RankingAlgorithm rankingAlgorithm;
/*     */   
/*     */   private final PearsonsCorrelation rankCorrelation;
/*     */   
/*     */   public SpearmansCorrelation(RealMatrix dataMatrix, RankingAlgorithm rankingAlgorithm) {
/*  61 */     this.data = dataMatrix.copy();
/*  62 */     this.rankingAlgorithm = rankingAlgorithm;
/*  63 */     rankTransform(this.data);
/*  64 */     this.rankCorrelation = new PearsonsCorrelation(this.data);
/*     */   }
/*     */   
/*     */   public SpearmansCorrelation(RealMatrix dataMatrix) {
/*  74 */     this(dataMatrix, (RankingAlgorithm)new NaturalRanking());
/*     */   }
/*     */   
/*     */   public SpearmansCorrelation() {
/*  81 */     this.data = null;
/*  82 */     this.rankingAlgorithm = (RankingAlgorithm)new NaturalRanking();
/*  83 */     this.rankCorrelation = null;
/*     */   }
/*     */   
/*     */   public RealMatrix getCorrelationMatrix() {
/*  92 */     return this.rankCorrelation.getCorrelationMatrix();
/*     */   }
/*     */   
/*     */   public PearsonsCorrelation getRankCorrelation() {
/* 108 */     return this.rankCorrelation;
/*     */   }
/*     */   
/*     */   public RealMatrix computeCorrelationMatrix(RealMatrix matrix) {
/* 119 */     RealMatrix matrixCopy = matrix.copy();
/* 120 */     rankTransform(matrixCopy);
/* 121 */     return (new PearsonsCorrelation()).computeCorrelationMatrix(matrixCopy);
/*     */   }
/*     */   
/*     */   public RealMatrix computeCorrelationMatrix(double[][] matrix) {
/* 133 */     return computeCorrelationMatrix((RealMatrix)new BlockRealMatrix(matrix));
/*     */   }
/*     */   
/*     */   public double correlation(double[] xArray, double[] yArray) {
/* 146 */     if (xArray.length != yArray.length)
/* 147 */       throw new DimensionMismatchException(xArray.length, yArray.length); 
/* 148 */     if (xArray.length < 2)
/* 149 */       throw new MathIllegalArgumentException(LocalizedFormats.INSUFFICIENT_DIMENSION, new Object[] { Integer.valueOf(xArray.length), Integer.valueOf(2) }); 
/* 152 */     return (new PearsonsCorrelation()).correlation(this.rankingAlgorithm.rank(xArray), this.rankingAlgorithm.rank(yArray));
/*     */   }
/*     */   
/*     */   private void rankTransform(RealMatrix matrix) {
/* 164 */     for (int i = 0; i < matrix.getColumnDimension(); i++)
/* 165 */       matrix.setColumn(i, this.rankingAlgorithm.rank(matrix.getColumn(i))); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\stat\correlation\SpearmansCorrelation.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
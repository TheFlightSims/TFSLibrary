/*    */ package org.apache.commons.math3.random;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*    */ 
/*    */ public class UncorrelatedRandomVectorGenerator implements RandomVectorGenerator {
/*    */   private final NormalizedRandomGenerator generator;
/*    */   
/*    */   private final double[] mean;
/*    */   
/*    */   private final double[] standardDeviation;
/*    */   
/*    */   public UncorrelatedRandomVectorGenerator(double[] mean, double[] standardDeviation, NormalizedRandomGenerator generator) {
/* 56 */     if (mean.length != standardDeviation.length)
/* 57 */       throw new DimensionMismatchException(mean.length, standardDeviation.length); 
/* 59 */     this.mean = (double[])mean.clone();
/* 60 */     this.standardDeviation = (double[])standardDeviation.clone();
/* 61 */     this.generator = generator;
/*    */   }
/*    */   
/*    */   public UncorrelatedRandomVectorGenerator(int dimension, NormalizedRandomGenerator generator) {
/* 73 */     this.mean = new double[dimension];
/* 74 */     this.standardDeviation = new double[dimension];
/* 75 */     Arrays.fill(this.standardDeviation, 1.0D);
/* 76 */     this.generator = generator;
/*    */   }
/*    */   
/*    */   public double[] nextVector() {
/* 84 */     double[] random = new double[this.mean.length];
/* 85 */     for (int i = 0; i < random.length; i++)
/* 86 */       random[i] = this.mean[i] + this.standardDeviation[i] * this.generator.nextNormalizedDouble(); 
/* 89 */     return random;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\random\UncorrelatedRandomVectorGenerator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
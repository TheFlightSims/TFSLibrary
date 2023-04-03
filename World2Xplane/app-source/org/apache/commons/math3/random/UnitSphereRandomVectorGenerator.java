/*    */ package org.apache.commons.math3.random;
/*    */ 
/*    */ import org.apache.commons.math3.util.FastMath;
/*    */ 
/*    */ public class UnitSphereRandomVectorGenerator implements RandomVectorGenerator {
/*    */   private final RandomGenerator rand;
/*    */   
/*    */   private final int dimension;
/*    */   
/*    */   public UnitSphereRandomVectorGenerator(int dimension, RandomGenerator rand) {
/* 47 */     this.dimension = dimension;
/* 48 */     this.rand = rand;
/*    */   }
/*    */   
/*    */   public UnitSphereRandomVectorGenerator(int dimension) {
/* 57 */     this(dimension, new MersenneTwister());
/*    */   }
/*    */   
/*    */   public double[] nextVector() {
/* 63 */     double normSq, v[] = new double[this.dimension];
/*    */     do {
/* 67 */       normSq = 0.0D;
/* 68 */       for (int j = 0; j < this.dimension; j++) {
/* 69 */         double comp = 2.0D * this.rand.nextDouble() - 1.0D;
/* 70 */         v[j] = comp;
/* 71 */         normSq += comp * comp;
/*    */       } 
/* 73 */     } while (normSq > 1.0D);
/* 75 */     double f = 1.0D / FastMath.sqrt(normSq);
/* 76 */     for (int i = 0; i < this.dimension; i++)
/* 77 */       v[i] = v[i] * f; 
/* 80 */     return v;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\random\UnitSphereRandomVectorGenerator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
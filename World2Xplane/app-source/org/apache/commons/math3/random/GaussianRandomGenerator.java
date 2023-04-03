/*    */ package org.apache.commons.math3.random;
/*    */ 
/*    */ public class GaussianRandomGenerator implements NormalizedRandomGenerator {
/*    */   private final RandomGenerator generator;
/*    */   
/*    */   public GaussianRandomGenerator(RandomGenerator generator) {
/* 37 */     this.generator = generator;
/*    */   }
/*    */   
/*    */   public double nextNormalizedDouble() {
/* 44 */     return this.generator.nextGaussian();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\random\GaussianRandomGenerator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
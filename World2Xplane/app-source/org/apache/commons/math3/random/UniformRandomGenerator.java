/*    */ package org.apache.commons.math3.random;
/*    */ 
/*    */ import org.apache.commons.math3.util.FastMath;
/*    */ 
/*    */ public class UniformRandomGenerator implements NormalizedRandomGenerator {
/* 37 */   private static final double SQRT3 = FastMath.sqrt(3.0D);
/*    */   
/*    */   private final RandomGenerator generator;
/*    */   
/*    */   public UniformRandomGenerator(RandomGenerator generator) {
/* 46 */     this.generator = generator;
/*    */   }
/*    */   
/*    */   public double nextNormalizedDouble() {
/* 55 */     return SQRT3 * (2.0D * this.generator.nextDouble() - 1.0D);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\random\UniformRandomGenerator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
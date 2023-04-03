/*    */ package org.apache.commons.math3.random;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ public class JDKRandomGenerator extends Random implements RandomGenerator {
/*    */   private static final long serialVersionUID = -7745277476784028798L;
/*    */   
/*    */   public void setSeed(int seed) {
/* 35 */     setSeed(seed);
/*    */   }
/*    */   
/*    */   public void setSeed(int[] seed) {
/* 41 */     long prime = 4294967291L;
/* 43 */     long combined = 0L;
/* 44 */     for (int s : seed)
/* 45 */       combined = combined * 4294967291L + s; 
/* 47 */     setSeed(combined);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\random\JDKRandomGenerator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
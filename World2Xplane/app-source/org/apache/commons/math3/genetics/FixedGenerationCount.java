/*    */ package org.apache.commons.math3.genetics;
/*    */ 
/*    */ import org.apache.commons.math3.exception.NumberIsTooSmallException;
/*    */ 
/*    */ public class FixedGenerationCount implements StoppingCondition {
/* 33 */   private int numGenerations = 0;
/*    */   
/*    */   private final int maxGenerations;
/*    */   
/*    */   public FixedGenerationCount(int maxGenerations) {
/* 45 */     if (maxGenerations <= 0)
/* 46 */       throw new NumberIsTooSmallException(Integer.valueOf(maxGenerations), Integer.valueOf(1), true); 
/* 48 */     this.maxGenerations = maxGenerations;
/*    */   }
/*    */   
/*    */   public boolean isSatisfied(Population population) {
/* 60 */     if (this.numGenerations < this.maxGenerations) {
/* 61 */       this.numGenerations++;
/* 62 */       return false;
/*    */     } 
/* 64 */     return true;
/*    */   }
/*    */   
/*    */   public int getNumGenerations() {
/* 71 */     return this.numGenerations;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\genetics\FixedGenerationCount.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
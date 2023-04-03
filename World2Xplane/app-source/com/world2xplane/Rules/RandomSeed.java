/*    */ package com.world2xplane.Rules;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ public class RandomSeed {
/* 32 */   private int min = 0;
/*    */   
/* 33 */   private int max = 100;
/*    */   
/*    */   private String identifier;
/*    */   
/* 36 */   private int currentSeed = 0;
/*    */   
/*    */   private Random randomGenerator;
/*    */   
/*    */   public int getMin() {
/* 40 */     return this.min;
/*    */   }
/*    */   
/*    */   public void setMin(int min) {
/* 44 */     this.min = min;
/*    */   }
/*    */   
/*    */   public int getMax() {
/* 48 */     return this.max;
/*    */   }
/*    */   
/*    */   public void setMax(int max) {
/* 52 */     this.max = max;
/*    */   }
/*    */   
/*    */   public String getIdentifier() {
/* 56 */     return this.identifier;
/*    */   }
/*    */   
/*    */   public void setIdentifier(String identifier) {
/* 60 */     this.identifier = identifier;
/*    */   }
/*    */   
/*    */   public int seed() {
/* 64 */     if (this.min == this.max)
/* 65 */       return this.max; 
/* 67 */     if (this.randomGenerator == null)
/* 68 */       this.randomGenerator = new Random(); 
/* 70 */     this.currentSeed = this.randomGenerator.nextInt(this.max - this.min) + this.min;
/* 71 */     return this.currentSeed;
/*    */   }
/*    */   
/*    */   public int getCurrentSeed() {
/* 76 */     return this.currentSeed;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Rules\RandomSeed.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package org.apache.commons.math3.random;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ public class RandomAdaptor extends Random implements RandomGenerator {
/*     */   private static final long serialVersionUID = 2306581345647615033L;
/*     */   
/*     */   private final RandomGenerator randomGenerator;
/*     */   
/*     */   private RandomAdaptor() {
/*  40 */     this.randomGenerator = null;
/*     */   }
/*     */   
/*     */   public RandomAdaptor(RandomGenerator randomGenerator) {
/*  48 */     this.randomGenerator = randomGenerator;
/*     */   }
/*     */   
/*     */   public static Random createAdaptor(RandomGenerator randomGenerator) {
/*  59 */     return new RandomAdaptor(randomGenerator);
/*     */   }
/*     */   
/*     */   public boolean nextBoolean() {
/*  73 */     return this.randomGenerator.nextBoolean();
/*     */   }
/*     */   
/*     */   public void nextBytes(byte[] bytes) {
/*  86 */     this.randomGenerator.nextBytes(bytes);
/*     */   }
/*     */   
/*     */   public double nextDouble() {
/* 100 */     return this.randomGenerator.nextDouble();
/*     */   }
/*     */   
/*     */   public float nextFloat() {
/* 114 */     return this.randomGenerator.nextFloat();
/*     */   }
/*     */   
/*     */   public double nextGaussian() {
/* 129 */     return this.randomGenerator.nextGaussian();
/*     */   }
/*     */   
/*     */   public int nextInt() {
/* 143 */     return this.randomGenerator.nextInt();
/*     */   }
/*     */   
/*     */   public int nextInt(int n) {
/* 159 */     return this.randomGenerator.nextInt(n);
/*     */   }
/*     */   
/*     */   public long nextLong() {
/* 173 */     return this.randomGenerator.nextLong();
/*     */   }
/*     */   
/*     */   public void setSeed(int seed) {
/* 178 */     if (this.randomGenerator != null)
/* 179 */       this.randomGenerator.setSeed(seed); 
/*     */   }
/*     */   
/*     */   public void setSeed(int[] seed) {
/* 185 */     if (this.randomGenerator != null)
/* 186 */       this.randomGenerator.setSeed(seed); 
/*     */   }
/*     */   
/*     */   public void setSeed(long seed) {
/* 193 */     if (this.randomGenerator != null)
/* 194 */       this.randomGenerator.setSeed(seed); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\random\RandomAdaptor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package org.apache.commons.math3.random;
/*     */ 
/*     */ import org.apache.commons.math3.exception.NotStrictlyPositiveException;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public abstract class AbstractRandomGenerator implements RandomGenerator {
/*  45 */   private double cachedNormalDeviate = Double.NaN;
/*     */   
/*     */   public void clear() {
/*  62 */     this.cachedNormalDeviate = Double.NaN;
/*     */   }
/*     */   
/*     */   public void setSeed(int seed) {
/*  67 */     setSeed(seed);
/*     */   }
/*     */   
/*     */   public void setSeed(int[] seed) {
/*  73 */     long prime = 4294967291L;
/*  75 */     long combined = 0L;
/*  76 */     for (int s : seed)
/*  77 */       combined = combined * 4294967291L + s; 
/*  79 */     setSeed(combined);
/*     */   }
/*     */   
/*     */   public abstract void setSeed(long paramLong);
/*     */   
/*     */   public void nextBytes(byte[] bytes) {
/* 107 */     int bytesOut = 0;
/* 108 */     while (bytesOut < bytes.length) {
/* 109 */       int randInt = nextInt();
/* 110 */       for (int i = 0; i < 3; i++) {
/* 111 */         if (i > 0)
/* 112 */           randInt >>= 8; 
/* 114 */         bytes[bytesOut++] = (byte)randInt;
/* 115 */         if (bytesOut == bytes.length)
/*     */           return; 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public int nextInt() {
/* 137 */     return (int)((2.0D * nextDouble() - 1.0D) * 2.147483647E9D);
/*     */   }
/*     */   
/*     */   public int nextInt(int n) {
/* 157 */     if (n <= 0)
/* 158 */       throw new NotStrictlyPositiveException(Integer.valueOf(n)); 
/* 160 */     int result = (int)(nextDouble() * n);
/* 161 */     return (result < n) ? result : (n - 1);
/*     */   }
/*     */   
/*     */   public long nextLong() {
/* 179 */     return (long)((2.0D * nextDouble() - 1.0D) * 9.223372036854776E18D);
/*     */   }
/*     */   
/*     */   public boolean nextBoolean() {
/* 197 */     return (nextDouble() <= 0.5D);
/*     */   }
/*     */   
/*     */   public float nextFloat() {
/* 215 */     return (float)nextDouble();
/*     */   }
/*     */   
/*     */   public abstract double nextDouble();
/*     */   
/*     */   public double nextGaussian() {
/* 253 */     if (!Double.isNaN(this.cachedNormalDeviate)) {
/* 254 */       double dev = this.cachedNormalDeviate;
/* 255 */       this.cachedNormalDeviate = Double.NaN;
/* 256 */       return dev;
/*     */     } 
/* 258 */     double v1 = 0.0D;
/* 259 */     double v2 = 0.0D;
/* 260 */     double s = 1.0D;
/* 261 */     while (s >= 1.0D) {
/* 262 */       v1 = 2.0D * nextDouble() - 1.0D;
/* 263 */       v2 = 2.0D * nextDouble() - 1.0D;
/* 264 */       s = v1 * v1 + v2 * v2;
/*     */     } 
/* 266 */     if (s != 0.0D)
/* 267 */       s = FastMath.sqrt(-2.0D * FastMath.log(s) / s); 
/* 269 */     this.cachedNormalDeviate = v2 * s;
/* 270 */     return v1 * s;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\random\AbstractRandomGenerator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
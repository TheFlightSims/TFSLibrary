/*     */ package org.apache.commons.lang.math;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ public final class JVMRandom extends Random {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*  49 */   private static final Random SHARED_RANDOM = new Random();
/*     */   
/*     */   private boolean constructed = false;
/*     */   
/*     */   public JVMRandom() {
/*  60 */     this.constructed = true;
/*     */   }
/*     */   
/*     */   public synchronized void setSeed(long seed) {
/*  70 */     if (this.constructed)
/*  71 */       throw new UnsupportedOperationException(); 
/*     */   }
/*     */   
/*     */   public synchronized double nextGaussian() {
/*  82 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void nextBytes(byte[] byteArray) {
/*  92 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public int nextInt() {
/* 105 */     return nextInt(2147483647);
/*     */   }
/*     */   
/*     */   public int nextInt(int n) {
/* 118 */     return SHARED_RANDOM.nextInt(n);
/*     */   }
/*     */   
/*     */   public long nextLong() {
/* 131 */     return nextLong(Long.MAX_VALUE);
/*     */   }
/*     */   
/*     */   public static long nextLong(long n) {
/* 145 */     if (n <= 0L)
/* 146 */       throw new IllegalArgumentException("Upper bound for nextInt must be positive"); 
/* 151 */     if ((n & -n) == n)
/* 153 */       return next63bits() >> 63 - bitsRequired(n - 1L); 
/*     */     while (true) {
/* 160 */       long bits = next63bits();
/* 161 */       long val = bits % n;
/* 162 */       if (bits - val + n - 1L >= 0L)
/* 163 */         return val; 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean nextBoolean() {
/* 173 */     return SHARED_RANDOM.nextBoolean();
/*     */   }
/*     */   
/*     */   public float nextFloat() {
/* 184 */     return SHARED_RANDOM.nextFloat();
/*     */   }
/*     */   
/*     */   public double nextDouble() {
/* 193 */     return SHARED_RANDOM.nextDouble();
/*     */   }
/*     */   
/*     */   private static long next63bits() {
/* 202 */     return SHARED_RANDOM.nextLong() & Long.MAX_VALUE;
/*     */   }
/*     */   
/*     */   private static int bitsRequired(long num) {
/* 213 */     long y = num;
/* 214 */     int n = 0;
/*     */     while (true) {
/* 217 */       if (num < 0L)
/* 218 */         return 64 - n; 
/* 220 */       if (y == 0L)
/* 221 */         return n; 
/* 223 */       n++;
/* 224 */       num <<= 1L;
/* 225 */       y >>= 1L;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\math\JVMRandom.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
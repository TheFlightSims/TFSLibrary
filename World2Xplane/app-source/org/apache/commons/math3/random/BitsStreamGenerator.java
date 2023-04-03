/*     */ package org.apache.commons.math3.random;
/*     */ 
/*     */ import org.apache.commons.math3.exception.NotStrictlyPositiveException;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public abstract class BitsStreamGenerator implements RandomGenerator {
/*  36 */   private double nextGaussian = Double.NaN;
/*     */   
/*     */   public abstract void setSeed(int paramInt);
/*     */   
/*     */   public abstract void setSeed(int[] paramArrayOfint);
/*     */   
/*     */   public abstract void setSeed(long paramLong);
/*     */   
/*     */   protected abstract int next(int paramInt);
/*     */   
/*     */   public boolean nextBoolean() {
/*  61 */     return (next(1) != 0);
/*     */   }
/*     */   
/*     */   public void nextBytes(byte[] bytes) {
/*  66 */     int i = 0;
/*  67 */     int iEnd = bytes.length - 3;
/*  68 */     while (i < iEnd) {
/*  69 */       int j = next(32);
/*  70 */       bytes[i] = (byte)(j & 0xFF);
/*  71 */       bytes[i + 1] = (byte)(j >> 8 & 0xFF);
/*  72 */       bytes[i + 2] = (byte)(j >> 16 & 0xFF);
/*  73 */       bytes[i + 3] = (byte)(j >> 24 & 0xFF);
/*  74 */       i += 4;
/*     */     } 
/*  76 */     int random = next(32);
/*  77 */     while (i < bytes.length) {
/*  78 */       bytes[i++] = (byte)(random & 0xFF);
/*  79 */       random >>= 8;
/*     */     } 
/*     */   }
/*     */   
/*     */   public double nextDouble() {
/*  85 */     long high = next(26) << 26L;
/*  86 */     int low = next(26);
/*  87 */     return (high | low) * 2.220446049250313E-16D;
/*     */   }
/*     */   
/*     */   public float nextFloat() {
/*  92 */     return next(23) * 1.1920929E-7F;
/*     */   }
/*     */   
/*     */   public double nextGaussian() {
/*     */     double random;
/*  99 */     if (Double.isNaN(this.nextGaussian)) {
/* 101 */       double x = nextDouble();
/* 102 */       double y = nextDouble();
/* 103 */       double alpha = 6.283185307179586D * x;
/* 104 */       double r = FastMath.sqrt(-2.0D * FastMath.log(y));
/* 105 */       random = r * FastMath.cos(alpha);
/* 106 */       this.nextGaussian = r * FastMath.sin(alpha);
/*     */     } else {
/* 109 */       random = this.nextGaussian;
/* 110 */       this.nextGaussian = Double.NaN;
/*     */     } 
/* 113 */     return random;
/*     */   }
/*     */   
/*     */   public int nextInt() {
/* 119 */     return next(32);
/*     */   }
/*     */   
/*     */   public int nextInt(int n) throws IllegalArgumentException {
/* 138 */     if (n > 0) {
/* 139 */       if ((n & -n) == n)
/* 140 */         return (int)(n * next(31) >> 31L); 
/*     */       while (true) {
/* 145 */         int bits = next(31);
/* 146 */         int val = bits % n;
/* 147 */         if (bits - val + n - 1 >= 0)
/* 148 */           return val; 
/*     */       } 
/*     */     } 
/* 150 */     throw new NotStrictlyPositiveException(Integer.valueOf(n));
/*     */   }
/*     */   
/*     */   public long nextLong() {
/* 155 */     long high = next(32) << 32L;
/* 156 */     long low = next(32) & 0xFFFFFFFFL;
/* 157 */     return high | low;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 165 */     this.nextGaussian = Double.NaN;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\random\BitsStreamGenerator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package scala.concurrent.forkjoin;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ public class ThreadLocalRandom extends Random {
/*     */   private static final long multiplier = 25214903917L;
/*     */   
/*     */   private static final long addend = 11L;
/*     */   
/*     */   private static final long mask = 281474976710655L;
/*     */   
/*     */   private long rnd;
/*     */   
/*     */   boolean initialized;
/*     */   
/*     */   private long pad0;
/*     */   
/*     */   private long pad1;
/*     */   
/*     */   private long pad2;
/*     */   
/*     */   private long pad3;
/*     */   
/*     */   private long pad4;
/*     */   
/*     */   private long pad5;
/*     */   
/*     */   private long pad6;
/*     */   
/*     */   private long pad7;
/*     */   
/*  62 */   private static final ThreadLocal<ThreadLocalRandom> localRandom = new ThreadLocal<ThreadLocalRandom>() {
/*     */       protected ThreadLocalRandom initialValue() {
/*  65 */         return new ThreadLocalRandom();
/*     */       }
/*     */     };
/*     */   
/*     */   private static final long serialVersionUID = -5851777807851030925L;
/*     */   
/*     */   ThreadLocalRandom() {
/*  75 */     this.initialized = true;
/*     */   }
/*     */   
/*     */   public static ThreadLocalRandom current() {
/*  84 */     return localRandom.get();
/*     */   }
/*     */   
/*     */   public void setSeed(long seed) {
/*  94 */     if (this.initialized)
/*  95 */       throw new UnsupportedOperationException(); 
/*  96 */     this.rnd = (seed ^ 0x5DEECE66DL) & 0xFFFFFFFFFFFFL;
/*     */   }
/*     */   
/*     */   protected int next(int bits) {
/* 100 */     this.rnd = this.rnd * 25214903917L + 11L & 0xFFFFFFFFFFFFL;
/* 101 */     return (int)(this.rnd >>> 48 - bits);
/*     */   }
/*     */   
/*     */   public int nextInt(int least, int bound) {
/* 115 */     if (least >= bound)
/* 116 */       throw new IllegalArgumentException(); 
/* 117 */     return nextInt(bound - least) + least;
/*     */   }
/*     */   
/*     */   public long nextLong(long n) {
/* 130 */     if (n <= 0L)
/* 131 */       throw new IllegalArgumentException("n must be positive"); 
/* 137 */     long offset = 0L;
/* 138 */     while (n >= 2147483647L) {
/* 139 */       int bits = next(2);
/* 140 */       long half = n >>> 1L;
/* 141 */       long nextn = ((bits & 0x2) == 0) ? half : (n - half);
/* 142 */       if ((bits & 0x1) == 0)
/* 143 */         offset += n - nextn; 
/* 144 */       n = nextn;
/*     */     } 
/* 146 */     return offset + nextInt((int)n);
/*     */   }
/*     */   
/*     */   public long nextLong(long least, long bound) {
/* 160 */     if (least >= bound)
/* 161 */       throw new IllegalArgumentException(); 
/* 162 */     return nextLong(bound - least) + least;
/*     */   }
/*     */   
/*     */   public double nextDouble(double n) {
/* 175 */     if (n <= 0.0D)
/* 176 */       throw new IllegalArgumentException("n must be positive"); 
/* 177 */     return nextDouble() * n;
/*     */   }
/*     */   
/*     */   public double nextDouble(double least, double bound) {
/* 191 */     if (least >= bound)
/* 192 */       throw new IllegalArgumentException(); 
/* 193 */     return nextDouble() * (bound - least) + least;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\concurrent\forkjoin\ThreadLocalRandom.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
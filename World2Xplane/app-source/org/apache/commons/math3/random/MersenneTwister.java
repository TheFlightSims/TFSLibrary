/*     */ package org.apache.commons.math3.random;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class MersenneTwister extends BitsStreamGenerator implements Serializable {
/*     */   private static final long serialVersionUID = 8661194735290153518L;
/*     */   
/*     */   private static final int N = 624;
/*     */   
/*     */   private static final int M = 397;
/*     */   
/*  94 */   private static final int[] MAG01 = new int[] { 0, -1727483681 };
/*     */   
/*     */   private int[] mt;
/*     */   
/*     */   private int mti;
/*     */   
/*     */   public MersenneTwister() {
/* 107 */     this.mt = new int[624];
/* 108 */     setSeed(System.currentTimeMillis() + System.identityHashCode(this));
/*     */   }
/*     */   
/*     */   public MersenneTwister(int seed) {
/* 115 */     this.mt = new int[624];
/* 116 */     setSeed(seed);
/*     */   }
/*     */   
/*     */   public MersenneTwister(int[] seed) {
/* 124 */     this.mt = new int[624];
/* 125 */     setSeed(seed);
/*     */   }
/*     */   
/*     */   public MersenneTwister(long seed) {
/* 132 */     this.mt = new int[624];
/* 133 */     setSeed(seed);
/*     */   }
/*     */   
/*     */   public void setSeed(int seed) {
/* 144 */     long longMT = seed;
/* 146 */     this.mt[0] = (int)longMT;
/* 147 */     for (this.mti = 1; this.mti < 624; this.mti++) {
/* 150 */       longMT = 1812433253L * (longMT ^ longMT >> 30L) + this.mti & 0xFFFFFFFFL;
/* 151 */       this.mt[this.mti] = (int)longMT;
/*     */     } 
/* 154 */     clear();
/*     */   }
/*     */   
/*     */   public void setSeed(int[] seed) {
/* 167 */     if (seed == null) {
/* 168 */       setSeed(System.currentTimeMillis() + System.identityHashCode(this));
/*     */       return;
/*     */     } 
/* 172 */     setSeed(19650218);
/* 173 */     int i = 1;
/* 174 */     int j = 0;
/*     */     int k;
/* 176 */     for (k = FastMath.max(624, seed.length); k != 0; k--) {
/* 177 */       long l0 = this.mt[i] & 0x7FFFFFFFL | ((this.mt[i] < 0) ? 2147483648L : 0L);
/* 178 */       long l1 = this.mt[i - 1] & 0x7FFFFFFFL | ((this.mt[i - 1] < 0) ? 2147483648L : 0L);
/* 179 */       long l = (l0 ^ (l1 ^ l1 >> 30L) * 1664525L) + seed[j] + j;
/* 180 */       this.mt[i] = (int)(l & 0xFFFFFFFFL);
/* 181 */       i++;
/* 181 */       j++;
/* 182 */       if (i >= 624) {
/* 183 */         this.mt[0] = this.mt[623];
/* 184 */         i = 1;
/*     */       } 
/* 186 */       if (j >= seed.length)
/* 187 */         j = 0; 
/*     */     } 
/* 191 */     for (k = 623; k != 0; k--) {
/* 192 */       long l0 = this.mt[i] & 0x7FFFFFFFL | ((this.mt[i] < 0) ? 2147483648L : 0L);
/* 193 */       long l1 = this.mt[i - 1] & 0x7FFFFFFFL | ((this.mt[i - 1] < 0) ? 2147483648L : 0L);
/* 194 */       long l = (l0 ^ (l1 ^ l1 >> 30L) * 1566083941L) - i;
/* 195 */       this.mt[i] = (int)(l & 0xFFFFFFFFL);
/* 196 */       i++;
/* 197 */       if (i >= 624) {
/* 198 */         this.mt[0] = this.mt[623];
/* 199 */         i = 1;
/*     */       } 
/*     */     } 
/* 203 */     this.mt[0] = Integer.MIN_VALUE;
/* 205 */     clear();
/*     */   }
/*     */   
/*     */   public void setSeed(long seed) {
/* 216 */     setSeed(new int[] { (int)(seed >>> 32L), (int)(seed & 0xFFFFFFFFL) });
/*     */   }
/*     */   
/*     */   protected int next(int bits) {
/* 233 */     if (this.mti >= 624) {
/* 234 */       int mtNext = this.mt[0];
/*     */       int k;
/* 235 */       for (k = 0; k < 227; k++) {
/* 236 */         int mtCurr = mtNext;
/* 237 */         mtNext = this.mt[k + 1];
/* 238 */         int j = mtCurr & Integer.MIN_VALUE | mtNext & Integer.MAX_VALUE;
/* 239 */         this.mt[k] = this.mt[k + 397] ^ j >>> 1 ^ MAG01[j & 0x1];
/*     */       } 
/* 241 */       for (k = 227; k < 623; k++) {
/* 242 */         int mtCurr = mtNext;
/* 243 */         mtNext = this.mt[k + 1];
/* 244 */         int j = mtCurr & Integer.MIN_VALUE | mtNext & Integer.MAX_VALUE;
/* 245 */         this.mt[k] = this.mt[k + -227] ^ j >>> 1 ^ MAG01[j & 0x1];
/*     */       } 
/* 247 */       int i = mtNext & Integer.MIN_VALUE | this.mt[0] & Integer.MAX_VALUE;
/* 248 */       this.mt[623] = this.mt[396] ^ i >>> 1 ^ MAG01[i & 0x1];
/* 250 */       this.mti = 0;
/*     */     } 
/* 253 */     int y = this.mt[this.mti++];
/* 256 */     y ^= y >>> 11;
/* 257 */     y ^= y << 7 & 0x9D2C5680;
/* 258 */     y ^= y << 15 & 0xEFC60000;
/* 259 */     y ^= y >>> 18;
/* 261 */     return y >>> 32 - bits;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\random\MersenneTwister.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
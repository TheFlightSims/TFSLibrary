/*     */ package org.apache.commons.math3.random;
/*     */ 
/*     */ public class Well19937a extends AbstractWell {
/*     */   private static final long serialVersionUID = -7462102162223815419L;
/*     */   
/*     */   private static final int K = 19937;
/*     */   
/*     */   private static final int M1 = 70;
/*     */   
/*     */   private static final int M2 = 179;
/*     */   
/*     */   private static final int M3 = 449;
/*     */   
/*     */   public Well19937a() {
/*  57 */     super(19937, 70, 179, 449);
/*     */   }
/*     */   
/*     */   public Well19937a(int seed) {
/*  64 */     super(19937, 70, 179, 449, seed);
/*     */   }
/*     */   
/*     */   public Well19937a(int[] seed) {
/*  72 */     super(19937, 70, 179, 449, seed);
/*     */   }
/*     */   
/*     */   public Well19937a(long seed) {
/*  79 */     super(19937, 70, 179, 449, seed);
/*     */   }
/*     */   
/*     */   protected int next(int bits) {
/*  86 */     int indexRm1 = this.iRm1[this.index];
/*  87 */     int indexRm2 = this.iRm2[this.index];
/*  89 */     int v0 = this.v[this.index];
/*  90 */     int vM1 = this.v[this.i1[this.index]];
/*  91 */     int vM2 = this.v[this.i2[this.index]];
/*  92 */     int vM3 = this.v[this.i3[this.index]];
/*  94 */     int z0 = Integer.MIN_VALUE & this.v[indexRm1] ^ Integer.MAX_VALUE & this.v[indexRm2];
/*  95 */     int z1 = v0 ^ v0 << 25 ^ vM1 ^ vM1 >>> 27;
/*  96 */     int z2 = vM2 >>> 9 ^ vM3 ^ vM3 >>> 1;
/*  97 */     int z3 = z1 ^ z2;
/*  98 */     int z4 = z0 ^ z1 ^ z1 << 9 ^ z2 ^ z2 << 21 ^ z3 ^ z3 >>> 21;
/* 100 */     this.v[this.index] = z3;
/* 101 */     this.v[indexRm1] = z4;
/* 102 */     this.v[indexRm2] = this.v[indexRm2] & Integer.MIN_VALUE;
/* 103 */     this.index = indexRm1;
/* 105 */     return z4 >>> 32 - bits;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\random\Well19937a.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
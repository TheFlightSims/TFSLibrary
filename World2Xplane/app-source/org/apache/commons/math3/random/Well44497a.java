/*     */ package org.apache.commons.math3.random;
/*     */ 
/*     */ public class Well44497a extends AbstractWell {
/*     */   private static final long serialVersionUID = -3859207588353972099L;
/*     */   
/*     */   private static final int K = 44497;
/*     */   
/*     */   private static final int M1 = 23;
/*     */   
/*     */   private static final int M2 = 481;
/*     */   
/*     */   private static final int M3 = 229;
/*     */   
/*     */   public Well44497a() {
/*  57 */     super(44497, 23, 481, 229);
/*     */   }
/*     */   
/*     */   public Well44497a(int seed) {
/*  64 */     super(44497, 23, 481, 229, seed);
/*     */   }
/*     */   
/*     */   public Well44497a(int[] seed) {
/*  72 */     super(44497, 23, 481, 229, seed);
/*     */   }
/*     */   
/*     */   public Well44497a(long seed) {
/*  79 */     super(44497, 23, 481, 229, seed);
/*     */   }
/*     */   
/*     */   protected int next(int bits) {
/*  86 */     int indexRm1 = this.iRm1[this.index];
/*  87 */     int indexRm2 = this.iRm2[this.index];
/*  89 */     int v0 = this.v[this.index];
/*  90 */     int vM1 = this.v[this.i1[this.index]];
/*  91 */     int vM2 = this.v[this.i2[this.index]];
/*  92 */     int vM3 = this.v[this.i3[this.index]];
/*  95 */     int z0 = 0xFFFF8000 & this.v[indexRm1] ^ 0x7FFF & this.v[indexRm2];
/*  96 */     int z1 = v0 ^ v0 << 24 ^ vM1 ^ vM1 >>> 30;
/*  97 */     int z2 = vM2 ^ vM2 << 10 ^ vM3 << 26;
/*  98 */     int z3 = z1 ^ z2;
/*  99 */     int z2Prime = (z2 << 9 ^ z2 >>> 23) & 0xFBFFFFFF;
/* 100 */     int z2Second = ((z2 & 0x20000) != 0) ? (z2Prime ^ 0xB729FCEC) : z2Prime;
/* 101 */     int z4 = z0 ^ z1 ^ z1 >>> 20 ^ z2Second ^ z3;
/* 103 */     this.v[this.index] = z3;
/* 104 */     this.v[indexRm1] = z4;
/* 105 */     this.v[indexRm2] = this.v[indexRm2] & 0xFFFF8000;
/* 106 */     this.index = indexRm1;
/* 108 */     return z4 >>> 32 - bits;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\random\Well44497a.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
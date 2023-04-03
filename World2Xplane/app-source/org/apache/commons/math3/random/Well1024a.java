/*     */ package org.apache.commons.math3.random;
/*     */ 
/*     */ public class Well1024a extends AbstractWell {
/*     */   private static final long serialVersionUID = 5680173464174485492L;
/*     */   
/*     */   private static final int K = 1024;
/*     */   
/*     */   private static final int M1 = 3;
/*     */   
/*     */   private static final int M2 = 24;
/*     */   
/*     */   private static final int M3 = 10;
/*     */   
/*     */   public Well1024a() {
/*  57 */     super(1024, 3, 24, 10);
/*     */   }
/*     */   
/*     */   public Well1024a(int seed) {
/*  64 */     super(1024, 3, 24, 10, seed);
/*     */   }
/*     */   
/*     */   public Well1024a(int[] seed) {
/*  72 */     super(1024, 3, 24, 10, seed);
/*     */   }
/*     */   
/*     */   public Well1024a(long seed) {
/*  79 */     super(1024, 3, 24, 10, seed);
/*     */   }
/*     */   
/*     */   protected int next(int bits) {
/*  86 */     int indexRm1 = this.iRm1[this.index];
/*  88 */     int v0 = this.v[this.index];
/*  89 */     int vM1 = this.v[this.i1[this.index]];
/*  90 */     int vM2 = this.v[this.i2[this.index]];
/*  91 */     int vM3 = this.v[this.i3[this.index]];
/*  93 */     int z0 = this.v[indexRm1];
/*  94 */     int z1 = v0 ^ vM1 ^ vM1 >>> 8;
/*  95 */     int z2 = vM2 ^ vM2 << 19 ^ vM3 ^ vM3 << 14;
/*  96 */     int z3 = z1 ^ z2;
/*  97 */     int z4 = z0 ^ z0 << 11 ^ z1 ^ z1 << 7 ^ z2 ^ z2 << 13;
/*  99 */     this.v[this.index] = z3;
/* 100 */     this.v[indexRm1] = z4;
/* 101 */     this.index = indexRm1;
/* 103 */     return z4 >>> 32 - bits;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\random\Well1024a.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package org.apache.commons.math3.random;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class AbstractWell extends BitsStreamGenerator implements Serializable {
/*     */   private static final long serialVersionUID = -817701723016583596L;
/*     */   
/*     */   protected int index;
/*     */   
/*     */   protected final int[] v;
/*     */   
/*     */   protected final int[] iRm1;
/*     */   
/*     */   protected final int[] iRm2;
/*     */   
/*     */   protected final int[] i1;
/*     */   
/*     */   protected final int[] i2;
/*     */   
/*     */   protected final int[] i3;
/*     */   
/*     */   protected AbstractWell(int k, int m1, int m2, int m3) {
/*  72 */     this(k, m1, m2, m3, (int[])null);
/*     */   }
/*     */   
/*     */   protected AbstractWell(int k, int m1, int m2, int m3, int seed) {
/*  83 */     this(k, m1, m2, m3, new int[] { seed });
/*     */   }
/*     */   
/*     */   protected AbstractWell(int k, int m1, int m2, int m3, int[] seed) {
/*  99 */     int w = 32;
/* 100 */     int r = (k + 32 - 1) / 32;
/* 101 */     this.v = new int[r];
/* 102 */     this.index = 0;
/* 106 */     this.iRm1 = new int[r];
/* 107 */     this.iRm2 = new int[r];
/* 108 */     this.i1 = new int[r];
/* 109 */     this.i2 = new int[r];
/* 110 */     this.i3 = new int[r];
/* 111 */     for (int j = 0; j < r; j++) {
/* 112 */       this.iRm1[j] = (j + r - 1) % r;
/* 113 */       this.iRm2[j] = (j + r - 2) % r;
/* 114 */       this.i1[j] = (j + m1) % r;
/* 115 */       this.i2[j] = (j + m2) % r;
/* 116 */       this.i3[j] = (j + m3) % r;
/*     */     } 
/* 120 */     setSeed(seed);
/*     */   }
/*     */   
/*     */   protected AbstractWell(int k, int m1, int m2, int m3, long seed) {
/* 132 */     this(k, m1, m2, m3, new int[] { (int)(seed >>> 32L), (int)(seed & 0xFFFFFFFFL) });
/*     */   }
/*     */   
/*     */   public void setSeed(int seed) {
/* 142 */     setSeed(new int[] { seed });
/*     */   }
/*     */   
/*     */   public void setSeed(int[] seed) {
/* 154 */     if (seed == null) {
/* 155 */       setSeed(System.currentTimeMillis() + System.identityHashCode(this));
/*     */       return;
/*     */     } 
/* 159 */     System.arraycopy(seed, 0, this.v, 0, Math.min(seed.length, this.v.length));
/* 161 */     if (seed.length < this.v.length)
/* 162 */       for (int i = seed.length; i < this.v.length; i++) {
/* 163 */         long l = this.v[i - seed.length];
/* 164 */         this.v[i] = (int)(1812433253L * (l ^ l >> 30L) + i & 0xFFFFFFFFL);
/*     */       }  
/* 168 */     this.index = 0;
/* 169 */     clear();
/*     */   }
/*     */   
/*     */   public void setSeed(long seed) {
/* 179 */     setSeed(new int[] { (int)(seed >>> 32L), (int)(seed & 0xFFFFFFFFL) });
/*     */   }
/*     */   
/*     */   protected abstract int next(int paramInt);
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\random\AbstractWell.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
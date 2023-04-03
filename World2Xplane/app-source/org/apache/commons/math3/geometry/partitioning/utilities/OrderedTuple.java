/*     */ package org.apache.commons.math3.geometry.partitioning.utilities;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class OrderedTuple implements Comparable<OrderedTuple> {
/*     */   private static final long SIGN_MASK = -9223372036854775808L;
/*     */   
/*     */   private static final long EXPONENT_MASK = 9218868437227405312L;
/*     */   
/*     */   private static final long MANTISSA_MASK = 4503599627370495L;
/*     */   
/*     */   private static final long IMPLICIT_ONE = 4503599627370496L;
/*     */   
/*     */   private double[] components;
/*     */   
/*     */   private int offset;
/*     */   
/*     */   private int lsb;
/*     */   
/*     */   private long[] encoding;
/*     */   
/*     */   private boolean posInf;
/*     */   
/*     */   private boolean negInf;
/*     */   
/*     */   private boolean nan;
/*     */   
/*     */   public OrderedTuple(double... components) {
/* 133 */     this.components = (double[])components.clone();
/* 134 */     int msb = Integer.MIN_VALUE;
/* 135 */     this.lsb = Integer.MAX_VALUE;
/* 136 */     this.posInf = false;
/* 137 */     this.negInf = false;
/* 138 */     this.nan = false;
/* 139 */     for (int i = 0; i < components.length; i++) {
/* 140 */       if (Double.isInfinite(components[i])) {
/* 141 */         if (components[i] < 0.0D) {
/* 142 */           this.negInf = true;
/*     */         } else {
/* 144 */           this.posInf = true;
/*     */         } 
/* 146 */       } else if (Double.isNaN(components[i])) {
/* 147 */         this.nan = true;
/*     */       } else {
/* 149 */         long b = Double.doubleToLongBits(components[i]);
/* 150 */         long m = mantissa(b);
/* 151 */         if (m != 0L) {
/* 152 */           int e = exponent(b);
/* 153 */           msb = FastMath.max(msb, e + computeMSB(m));
/* 154 */           this.lsb = FastMath.min(this.lsb, e + computeLSB(m));
/*     */         } 
/*     */       } 
/*     */     } 
/* 159 */     if (this.posInf && this.negInf) {
/* 161 */       this.posInf = false;
/* 162 */       this.negInf = false;
/* 163 */       this.nan = true;
/*     */     } 
/* 166 */     if (this.lsb <= msb) {
/* 168 */       encode(msb + 16);
/*     */     } else {
/* 170 */       this.encoding = new long[] { 0L };
/*     */     } 
/*     */   }
/*     */   
/*     */   private void encode(int minOffset) {
/* 184 */     this.offset = minOffset + 31;
/* 185 */     this.offset -= this.offset % 32;
/* 187 */     if (this.encoding != null && this.encoding.length == 1 && this.encoding[0] == 0L)
/*     */       return; 
/* 194 */     int neededBits = this.offset + 1 - this.lsb;
/* 195 */     int neededLongs = (neededBits + 62) / 63;
/* 196 */     this.encoding = new long[this.components.length * neededLongs];
/* 199 */     int eIndex = 0;
/* 200 */     int shift = 62;
/* 201 */     long word = 0L;
/* 202 */     for (int k = this.offset; eIndex < this.encoding.length; k--) {
/* 203 */       for (int vIndex = 0; vIndex < this.components.length; vIndex++) {
/* 204 */         if (getBit(vIndex, k) != 0)
/* 205 */           word |= 1L << shift; 
/* 207 */         if (shift-- == 0) {
/* 208 */           this.encoding[eIndex++] = word;
/* 209 */           word = 0L;
/* 210 */           shift = 62;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public int compareTo(OrderedTuple ot) {
/* 248 */     if (this.components.length == ot.components.length) {
/* 249 */       if (this.nan)
/* 250 */         return 1; 
/* 251 */       if (ot.nan)
/* 252 */         return -1; 
/* 253 */       if (this.negInf || ot.posInf)
/* 254 */         return -1; 
/* 255 */       if (this.posInf || ot.negInf)
/* 256 */         return 1; 
/* 259 */       if (this.offset < ot.offset) {
/* 260 */         encode(ot.offset);
/* 261 */       } else if (this.offset > ot.offset) {
/* 262 */         ot.encode(this.offset);
/*     */       } 
/* 265 */       int limit = FastMath.min(this.encoding.length, ot.encoding.length);
/* 266 */       for (int i = 0; i < limit; i++) {
/* 267 */         if (this.encoding[i] < ot.encoding[i])
/* 268 */           return -1; 
/* 269 */         if (this.encoding[i] > ot.encoding[i])
/* 270 */           return 1; 
/*     */       } 
/* 274 */       if (this.encoding.length < ot.encoding.length)
/* 275 */         return -1; 
/* 276 */       if (this.encoding.length > ot.encoding.length)
/* 277 */         return 1; 
/* 279 */       return 0;
/*     */     } 
/* 285 */     return this.components.length - ot.components.length;
/*     */   }
/*     */   
/*     */   public boolean equals(Object other) {
/* 292 */     if (this == other)
/* 293 */       return true; 
/* 294 */     if (other instanceof OrderedTuple)
/* 295 */       return (compareTo((OrderedTuple)other) == 0); 
/* 297 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 304 */     return Arrays.hashCode(this.components) ^ Integer.valueOf(this.offset).hashCode() ^ Integer.valueOf(this.lsb).hashCode() ^ Boolean.valueOf(this.posInf).hashCode() ^ Boolean.valueOf(this.negInf).hashCode() ^ Boolean.valueOf(this.nan).hashCode();
/*     */   }
/*     */   
/*     */   public double[] getComponents() {
/* 316 */     return (double[])this.components.clone();
/*     */   }
/*     */   
/*     */   private static long sign(long bits) {
/* 324 */     return bits & Long.MIN_VALUE;
/*     */   }
/*     */   
/*     */   private static int exponent(long bits) {
/* 332 */     return (int)((bits & 0x7FF0000000000000L) >> 52L) - 1075;
/*     */   }
/*     */   
/*     */   private static long mantissa(long bits) {
/* 340 */     return ((bits & 0x7FF0000000000000L) == 0L) ? ((bits & 0xFFFFFFFFFFFFFL) << 1L) : (0x10000000000000L | bits & 0xFFFFFFFFFFFFFL);
/*     */   }
/*     */   
/*     */   private static int computeMSB(long l) {
/* 353 */     long ll = l;
/* 354 */     long mask = 4294967295L;
/* 355 */     int scale = 32;
/* 356 */     int msb = 0;
/* 358 */     while (scale != 0) {
/* 359 */       if ((ll & mask) != ll) {
/* 360 */         msb |= scale;
/* 361 */         ll >>= scale;
/*     */       } 
/* 363 */       scale >>= 1;
/* 364 */       mask >>= scale;
/*     */     } 
/* 367 */     return msb;
/*     */   }
/*     */   
/*     */   private static int computeLSB(long l) {
/* 379 */     long ll = l;
/* 380 */     long mask = -4294967296L;
/* 381 */     int scale = 32;
/* 382 */     int lsb = 0;
/* 384 */     while (scale != 0) {
/* 385 */       if ((ll & mask) == ll) {
/* 386 */         lsb |= scale;
/* 387 */         ll >>= scale;
/*     */       } 
/* 389 */       scale >>= 1;
/* 390 */       mask >>= scale;
/*     */     } 
/* 393 */     return lsb;
/*     */   }
/*     */   
/*     */   private int getBit(int i, int k) {
/* 404 */     long bits = Double.doubleToLongBits(this.components[i]);
/* 405 */     int e = exponent(bits);
/* 406 */     if (k < e || k > this.offset)
/* 407 */       return 0; 
/* 408 */     if (k == this.offset)
/* 409 */       return (sign(bits) == 0L) ? 1 : 0; 
/* 410 */     if (k > e + 52)
/* 411 */       return (sign(bits) == 0L) ? 0 : 1; 
/* 413 */     long m = (sign(bits) == 0L) ? mantissa(bits) : -mantissa(bits);
/* 414 */     return (int)(m >> k - e & 0x1L);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\geometry\partitionin\\utilities\OrderedTuple.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
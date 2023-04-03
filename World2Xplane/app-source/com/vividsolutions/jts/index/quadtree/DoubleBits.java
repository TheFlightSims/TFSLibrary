/*     */ package com.vividsolutions.jts.index.quadtree;
/*     */ 
/*     */ public class DoubleBits {
/*     */   public static final int EXPONENT_BIAS = 1023;
/*     */   
/*     */   private double x;
/*     */   
/*     */   private long xBits;
/*     */   
/*     */   public static double powerOf2(int exp) {
/*  54 */     if (exp > 1023 || exp < -1022)
/*  55 */       throw new IllegalArgumentException("Exponent out of bounds"); 
/*  56 */     long expBias = (exp + 1023);
/*  57 */     long bits = expBias << 52L;
/*  58 */     return Double.longBitsToDouble(bits);
/*     */   }
/*     */   
/*     */   public static int exponent(double d) {
/*  63 */     DoubleBits db = new DoubleBits(d);
/*  64 */     return db.getExponent();
/*     */   }
/*     */   
/*     */   public static double truncateToPowerOfTwo(double d) {
/*  69 */     DoubleBits db = new DoubleBits(d);
/*  70 */     db.zeroLowerBits(52);
/*  71 */     return db.getDouble();
/*     */   }
/*     */   
/*     */   public static String toBinaryString(double d) {
/*  76 */     DoubleBits db = new DoubleBits(d);
/*  77 */     return db.toString();
/*     */   }
/*     */   
/*     */   public static double maximumCommonMantissa(double d1, double d2) {
/*  82 */     if (d1 == 0.0D || d2 == 0.0D)
/*  82 */       return 0.0D; 
/*  84 */     DoubleBits db1 = new DoubleBits(d1);
/*  85 */     DoubleBits db2 = new DoubleBits(d2);
/*  87 */     if (db1.getExponent() != db2.getExponent())
/*  87 */       return 0.0D; 
/*  89 */     int maxCommon = db1.numCommonMantissaBits(db2);
/*  90 */     db1.zeroLowerBits(64 - 12 + maxCommon);
/*  91 */     return db1.getDouble();
/*     */   }
/*     */   
/*     */   public DoubleBits(double x) {
/*  99 */     this.x = x;
/* 100 */     this.xBits = Double.doubleToLongBits(x);
/*     */   }
/*     */   
/*     */   public double getDouble() {
/* 105 */     return Double.longBitsToDouble(this.xBits);
/*     */   }
/*     */   
/*     */   public int biasedExponent() {
/* 113 */     int signExp = (int)(this.xBits >> 52L);
/* 114 */     int exp = signExp & 0x7FF;
/* 115 */     return exp;
/*     */   }
/*     */   
/*     */   public int getExponent() {
/* 123 */     return biasedExponent() - 1023;
/*     */   }
/*     */   
/*     */   public void zeroLowerBits(int nBits) {
/* 128 */     long invMask = (1L << nBits) - 1L;
/* 129 */     long mask = invMask ^ 0xFFFFFFFFFFFFFFFFL;
/* 130 */     this.xBits &= mask;
/*     */   }
/*     */   
/*     */   public int getBit(int i) {
/* 135 */     long mask = 1L << i;
/* 136 */     return ((this.xBits & mask) != 0L) ? 1 : 0;
/*     */   }
/*     */   
/*     */   public int numCommonMantissaBits(DoubleBits db) {
/* 149 */     for (int i = 0; i < 52; i++) {
/* 151 */       int bitIndex = i + 12;
/* 152 */       if (getBit(i) != db.getBit(i))
/* 153 */         return i; 
/*     */     } 
/* 155 */     return 52;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 163 */     String numStr = Long.toBinaryString(this.xBits);
/* 165 */     String zero64 = "0000000000000000000000000000000000000000000000000000000000000000";
/* 166 */     String padStr = zero64 + numStr;
/* 167 */     String bitStr = padStr.substring(padStr.length() - 64);
/* 168 */     String str = bitStr.substring(0, 1) + "  " + bitStr.substring(1, 12) + "(" + getExponent() + ") " + bitStr.substring(12) + " [ " + this.x + " ]";
/* 172 */     return str;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\index\quadtree\DoubleBits.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package com.vividsolutions.jts.precision;
/*     */ 
/*     */ public class CommonBits {
/*     */   public static long signExpBits(long num) {
/*  56 */     return num >> 52L;
/*     */   }
/*     */   
/*     */   public static int numCommonMostSigMantissaBits(long num1, long num2) {
/*  72 */     int count = 0;
/*  73 */     for (int i = 52; i >= 0; i--) {
/*  75 */       if (getBit(num1, i) != getBit(num2, i))
/*  76 */         return count; 
/*  77 */       count++;
/*     */     } 
/*  79 */     return 52;
/*     */   }
/*     */   
/*     */   public static long zeroLowerBits(long bits, int nBits) {
/*  90 */     long invMask = (1L << nBits) - 1L;
/*  91 */     long mask = invMask ^ 0xFFFFFFFFFFFFFFFFL;
/*  92 */     long zeroed = bits & mask;
/*  93 */     return zeroed;
/*     */   }
/*     */   
/*     */   public static int getBit(long bits, int i) {
/* 105 */     long mask = 1L << i;
/* 106 */     return ((bits & mask) != 0L) ? 1 : 0;
/*     */   }
/*     */   
/*     */   private boolean isFirst = true;
/*     */   
/* 110 */   private int commonMantissaBitsCount = 53;
/*     */   
/* 111 */   private long commonBits = 0L;
/*     */   
/*     */   private long commonSignExp;
/*     */   
/*     */   public void add(double num) {
/* 119 */     long numBits = Double.doubleToLongBits(num);
/* 120 */     if (this.isFirst) {
/* 121 */       this.commonBits = numBits;
/* 122 */       this.commonSignExp = signExpBits(this.commonBits);
/* 123 */       this.isFirst = false;
/*     */       return;
/*     */     } 
/* 127 */     long numSignExp = signExpBits(numBits);
/* 128 */     if (numSignExp != this.commonSignExp) {
/* 129 */       this.commonBits = 0L;
/*     */       return;
/*     */     } 
/* 135 */     this.commonMantissaBitsCount = numCommonMostSigMantissaBits(this.commonBits, numBits);
/* 136 */     this.commonBits = zeroLowerBits(this.commonBits, 64 - 12 + this.commonMantissaBitsCount);
/*     */   }
/*     */   
/*     */   public double getCommon() {
/* 142 */     return Double.longBitsToDouble(this.commonBits);
/*     */   }
/*     */   
/*     */   public String toString(long bits) {
/* 149 */     double x = Double.longBitsToDouble(bits);
/* 150 */     String numStr = Long.toBinaryString(bits);
/* 151 */     String padStr = "0000000000000000000000000000000000000000000000000000000000000000" + numStr;
/* 152 */     String bitStr = padStr.substring(padStr.length() - 64);
/* 153 */     String str = bitStr.substring(0, 1) + "  " + bitStr.substring(1, 12) + "(exp) " + bitStr.substring(12) + " [ " + x + " ]";
/* 157 */     return str;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\precision\CommonBits.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
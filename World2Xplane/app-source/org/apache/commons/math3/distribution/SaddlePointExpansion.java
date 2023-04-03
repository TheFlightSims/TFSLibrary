/*     */ package org.apache.commons.math3.distribution;
/*     */ 
/*     */ import org.apache.commons.math3.special.Gamma;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ final class SaddlePointExpansion {
/*  49 */   private static final double HALF_LOG_2_PI = 0.5D * FastMath.log(6.283185307179586D);
/*     */   
/*  52 */   private static final double[] EXACT_STIRLING_ERRORS = new double[] { 
/*  52 */       0.0D, 0.15342640972002736D, 0.08106146679532726D, 0.05481412105191765D, 0.0413406959554093D, 0.03316287351993629D, 0.02767792568499834D, 0.023746163656297496D, 0.020790672103765093D, 0.018488450532673187D, 
/*  52 */       0.016644691189821193D, 0.015134973221917378D, 0.013876128823070748D, 0.012810465242920227D, 0.01189670994589177D, 0.011104559758206917D, 0.010411265261972096D, 0.009799416126158804D, 0.009255462182712733D, 0.008768700134139386D, 
/*  52 */       0.00833056343336287D, 0.00793411456431402D, 0.007573675487951841D, 0.007244554301320383D, 0.00694284010720953D, 0.006665247032707682D, 0.006408994188004207D, 0.006171712263039458D, 0.0059513701127588475D, 0.0057462165130101155D, 
/*  52 */       0.005554733551962801D };
/*     */   
/*     */   static double getStirlingError(double z) {
/*     */     double ret;
/* 109 */     if (z < 15.0D) {
/* 110 */       double z2 = 2.0D * z;
/* 111 */       if (FastMath.floor(z2) == z2) {
/* 112 */         ret = EXACT_STIRLING_ERRORS[(int)z2];
/*     */       } else {
/* 114 */         ret = Gamma.logGamma(z + 1.0D) - (z + 0.5D) * FastMath.log(z) + z - HALF_LOG_2_PI;
/*     */       } 
/*     */     } else {
/* 118 */       double z2 = z * z;
/* 119 */       ret = (0.08333333333333333D - (0.002777777777777778D - (7.936507936507937E-4D - (5.952380952380953E-4D - 8.417508417508417E-4D / z2) / z2) / z2) / z2) / z;
/*     */     } 
/* 126 */     return ret;
/*     */   }
/*     */   
/*     */   static double getDeviancePart(double x, double mu) {
/*     */     double ret;
/* 147 */     if (FastMath.abs(x - mu) < 0.1D * (x + mu)) {
/* 148 */       double d = x - mu;
/* 149 */       double v = d / (x + mu);
/* 150 */       double s1 = v * d;
/* 151 */       double s = Double.NaN;
/* 152 */       double ej = 2.0D * x * v;
/* 153 */       v *= v;
/* 154 */       int j = 1;
/* 155 */       while (s1 != s) {
/* 156 */         s = s1;
/* 157 */         ej *= v;
/* 158 */         s1 = s + ej / (j * 2 + 1);
/* 159 */         j++;
/*     */       } 
/* 161 */       ret = s1;
/*     */     } else {
/* 163 */       ret = x * FastMath.log(x / mu) + mu - x;
/*     */     } 
/* 165 */     return ret;
/*     */   }
/*     */   
/*     */   static double logBinomialProbability(int x, int n, double p, double q) {
/*     */     double ret;
/* 180 */     if (x == 0) {
/* 181 */       if (p < 0.1D) {
/* 182 */         ret = -getDeviancePart(n, n * q) - n * p;
/*     */       } else {
/* 184 */         ret = n * FastMath.log(q);
/*     */       } 
/* 186 */     } else if (x == n) {
/* 187 */       if (q < 0.1D) {
/* 188 */         ret = -getDeviancePart(n, n * p) - n * q;
/*     */       } else {
/* 190 */         ret = n * FastMath.log(p);
/*     */       } 
/*     */     } else {
/* 193 */       ret = getStirlingError(n) - getStirlingError(x) - getStirlingError((n - x)) - getDeviancePart(x, n * p) - getDeviancePart((n - x), n * q);
/* 196 */       double f = 6.283185307179586D * x * (n - x) / n;
/* 197 */       ret = -0.5D * FastMath.log(f) + ret;
/*     */     } 
/* 199 */     return ret;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\distribution\SaddlePointExpansion.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
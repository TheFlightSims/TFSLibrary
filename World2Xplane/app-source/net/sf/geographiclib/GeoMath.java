/*     */ package net.sf.geographiclib;
/*     */ 
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class GeoMath {
/*     */   public static final int digits = 53;
/*     */   
/*  42 */   public static final double epsilon = FastMath.pow(0.5D, 52.0D);
/*     */   
/*  47 */   public static final double min = FastMath.pow(0.5D, 1022.0D);
/*     */   
/*     */   public static final double degree = 0.017453292519943295D;
/*     */   
/*     */   public static double sq(double x) {
/*  60 */     return x * x;
/*     */   }
/*     */   
/*     */   public static double hypot(double x, double y) {
/*  71 */     x = FastMath.abs(x);
/*  71 */     y = FastMath.abs(y);
/*  72 */     double a = FastMath.max(x, y), b = FastMath.min(x, y) / ((a != 0.0D) ? a : 1.0D);
/*  73 */     return a * FastMath.sqrt(1.0D + b * b);
/*     */   }
/*     */   
/*     */   public static double log1p(double x) {
/*  94 */     double y = 1.0D + x;
/*  95 */     double z = y - 1.0D;
/* 100 */     return (z == 0.0D) ? x : (x * FastMath.log(y) / z);
/*     */   }
/*     */   
/*     */   public static double atanh(double x) {
/* 112 */     double y = FastMath.abs(x);
/* 113 */     y = FastMath.log1p(2.0D * y / (1.0D - y)) / 2.0D;
/* 114 */     return (x < 0.0D) ? -y : y;
/*     */   }
/*     */   
/*     */   public static double cbrt(double x) {
/* 125 */     double y = FastMath.pow(FastMath.abs(x), 0.3333333333333333D);
/* 126 */     return (x < 0.0D) ? -y : y;
/*     */   }
/*     */   
/*     */   public static Pair sum(double u, double v) {
/* 140 */     double s = u + v;
/* 141 */     double up = s - v;
/* 142 */     double vpp = s - up;
/* 143 */     up -= u;
/* 144 */     vpp -= v;
/* 145 */     double t = -(up + vpp);
/* 148 */     return new Pair(s, t);
/*     */   }
/*     */   
/*     */   public static double AngNormalize(double x) {
/* 160 */     return (x >= 180.0D) ? (x - 360.0D) : ((x < -180.0D) ? (x + 360.0D) : x);
/*     */   }
/*     */   
/*     */   public static double AngNormalize2(double x) {
/* 171 */     return AngNormalize(x % 360.0D);
/*     */   }
/*     */   
/*     */   public static double AngDiff(double x, double y) {
/* 189 */     Pair r = sum(-x, y);
/* 189 */     double d = r.first, t = r.second;
/* 190 */     if (d - 180.0D + t > 0.0D) {
/* 191 */       d -= 360.0D;
/* 192 */     } else if (d + 180.0D + t <= 0.0D) {
/* 193 */       d += 360.0D;
/*     */     } 
/* 194 */     return d + t;
/*     */   }
/*     */   
/*     */   public static boolean isfinite(double x) {
/* 203 */     return (FastMath.abs(x) <= Double.MAX_VALUE);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\net\sf\geographiclib\GeoMath.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */